var MSG;


/******************************/
/** View Model (Knockout JS) **/
/******************************/
function ViewModel(){
    this.match = ko.observable(null);
    this.phase = ko.observable('setup');
    this.turn = ko.observable(null);
    this.me = ko.observable(null);
    this.clue = ko.observable(null);
    this.mycards = ko.observableArray([]);
    this.tablecards = ko.observableArray([]);
    this.players = ko.observableArray([]);
    this.winner  = ko.observable(null);
    
    this.selectedCard = ko.observable(null);
    this.votedCard = ko.observable(null);
    this.joined = ko.observable(false);
    this.proceeded = ko.observable(false);
    
    this.suggestion = ko.computed(function(){
        phase = this.phase();
        if(!phase || phase==="setup"){
            return this.joined() ? 
                "Wait for the other players to join the game":
                "Enter the game";
        } else if(phase==="setPhrase"){            
            return this.turn()===this.me() ? 
                "Tell a clue." : 
                "Wait " + this.turn() + " to tell a clue.";
        } else if(phase==="selectCard"){
            return this.selectedCard() ? 
                "Wait for the other players to select their card." : 
                "Now, select a card";
        } else if(phase==="vote"){
            if(this.votedCard()){
                return "Wait for the other players to vote.";
            } else{
                return (this.turn()===this.me()) ? 
                    "Wait for the other players to vote." : 
                    "Vote a card.";
            }
        } else if(phase==="results"){
            return this.proceeded() ? 
                "Wait for the other players to do the same." : 
                "Round finished.";
        } else if(phase==="end"){
            return "END OF MATCH. Winner is " + VIEWMODEL.winner();
        } else{
            return "Don't know";
        }
    }, this);    
    
    this.canTellClue = ko.computed(function(){
        if(this.phase()==="setPhrase" && this.turn()===this.me())
            return true;
        return false;
    }, this);
    
    this.canSelectCard = ko.computed(function(){
        if(this.phase()==="selectCard")
            return true;
        return false;        
    }, this);
    
    this.canVoteCard = ko.computed(function(){
        if(this.phase()==="vote" && this.turn()!==this.me())
            return true;
        return false; 
    }, this);
    
    this.join = function(){
        performAction("join", {user: this.me(), match: this.match()});
    };
    
    this.setphrase = function(){
        myclue = $('#cluetxt').val();
        performAction("setPhrase", {user: this.me(), match: this.match(), phrase: myclue});
    };
    
    this.proceed = function(){
        performAction("proceed", {user: this.me(), match: this.match() });
    };
    
    this.request = ko.computed(function(){
        if(this.phase()==="setup" && !this.joined()){
            return 'Enter the game';
        } else if(this.phase()==='results' && !this.proceeded()){
            return 'Next';
        }
        return null;
    }, this);       
    
    this.next_action = ko.computed(function(){
        if(this.phase()==="setup"){
            return this.join;
        } else if(this.phase()==="setPhrase" && this.me()===this.turn()){
            return this.setphrase;
        } 
        else if(this.phase()==='results'){
            return this.proceed;
        } 
        return function(){ alert('ERROR: no next action'); }        
    }, this);
};

VIEWMODEL = new ViewModel();

/* On page load */
$().ready(function(){
    
    ko.applyBindings(VIEWMODEL);
    
    
    
});


/************************************/
/** Azioni utente e richieste AJAX **/
/************************************/

function performAction(action, actionData){
    onsuccess = function(res){};
    onsuccessEnd = function(res){};
    onerror = function(err){ 
        alert("Errore: " + err.documentElement.textContent); 
    };
    
    if(action==="join" && VIEWMODEL.phase()==="setup" && !VIEWMODEL.joined()){
        console.log("Joining the game...");
        data = createJoinXmlData(actionData.user, actionData.match);
        onsuccess = function(res){
            VIEWMODEL.joined(true);
        };
        onsuccessEnd = function(res){
            performAction("pop", actionData);
        };
    } else if(action==="pop"){
        console.log("Now I wait for updates...");
        data = createGameUpdateXmlData(actionData.user, actionData.match);
        waitForUpdate(data);
        return;
    } else if(action==="setPhrase" && VIEWMODEL.phase()==="setPhrase" && VIEWMODEL.turn()==VIEWMODEL.me()){ 
        console.log("Setting phrase...");
        data = createPhraseXmlData(actionData.user, actionData.match, actionData.phrase);
    } else if(action==="selectCard" && VIEWMODEL.phase()==="selectCard" && !VIEWMODEL.selectedCard()){
        console.log("Selecting card " + actionData.card + " by " +actionData.user + " in " +actionData.match);
        data = createSelectCardXmlData(actionData.user, actionData.match, actionData.card);
        onsuccess = function(res){
            VIEWMODEL.selectedCard(actionData.card);
        };
    } else if(action==="voteCard" && VIEWMODEL.phase()==="vote" && !VIEWMODEL.votedCard()){
        console.log("Voting card " + actionData.card);
        onsuccess = function(res){
            VIEWMODEL.votedCard(actionData.card);
        };        
        data = createVoteCardXmlData(actionData.user, actionData.match, actionData.card);
    } else if(action==="proceed" && VIEWMODEL.phase()==="results"){
        console.log("Proceeding to next round");
        data = createProceedXmlData(actionData.user, actionData.match);
        onsuccess = function(res){
            VIEWMODEL.proceeded(true);
        };        
    }
    else {
        setError("Unknown action or action not available in current phase.");
        return;
    }
    
    $.ajax({
            url: getCtx()+"/Dixit",
            type: "POST",
            processData: false,
            contentType: "text/xml; charset=utf-8",
            dataType: "xml",
            data: data,
            success: function(res){
                if(res){
                    root = res.documentElement.tagName;
                    
                    if(root==="error"){
                        onerror(res);
                        return;
                    }
                    
                    onsuccess(res);
                    
                    var gameinfo = res.documentElement.getElementsByTagName("GameInfo")[0];
                    if (gameinfo) {
                        MSG = res;
                        syncWithGameInfo(gameinfo);
                    }
                    
                    onsuccessEnd(res);
                }
            },
            error: function(err){
                setError(err);
                if(action!=="pop")
                    performAction("pop", actionData);                 
            }
        });    
}

function waitForUpdate(data){
    xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            onGameUpdate(xhr.responseXML);
        }
    };
    xhr.open("POST", getCtx()+"/Dixit", true);
    xhr.send(data);    
}

function onGameUpdate(res){
    console.log("Game update: " + res);
    
    if(res===null){ 
        return;
    }
    
    docname = res.documentElement.tagName;
    
    if(docname==="timeout"){
        console.log("Timeout");
    }
    else if(docname==="update"){
        MSG = res;
        console.log("Msg " + res.documentElement.tagName);
        var gameinfo = res.documentElement.getElementsByTagName("GameInfo")[0];
        if (gameinfo !== null) {
            syncWithGameInfo(gameinfo);
        }
    }
    else{
        return;
    }
    
    performAction("pop", {user: VIEWMODEL.me(), match: VIEWMODEL.match()});
}


/*******************************/
/** Aggiornamento stato gioco **/
/*******************************/

function syncWithGameInfo(gameinfo){
    if(!gameinfo){ console.log("CANNOT SYNC: GAMEINFO is NULL"); return; }
    console.log("Synch with game info: parse XML DOM and set viewmodel.");
    
    var phase = gameinfo.getAttribute("phase");
    var turn = gameinfo.getAttribute("turn");
    var winner = gameinfo.getAttribute("winner");
    var phrase = gameinfo.getElementsByTagName("Phrase")[0];
    var players = gameinfo.getElementsByTagName("Player");
    var mycards = gameinfo.getElementsByTagName("YourCards")[0];
    var tablecards = gameinfo.getElementsByTagName("CardsOnTable")[0];
    var votes = gameinfo.getElementsByTagName("Votes")[0];
    var selection = gameinfo.getElementsByTagName("UncoveredCards")[0];
    var selcard = gameinfo.getElementsByTagName("SelectedCard")[0];
    var votedcard = gameinfo.getElementsByTagName("VotedCard")[0];
    
    if(phase==="setPhrase"){
        // cleanup at beginning of every round
        VIEWMODEL.clue(null);
        VIEWMODEL.tablecards.removeAll();
        VIEWMODEL.selectedCard(null);
        VIEWMODEL.votedCard(null);
        VIEWMODEL.joined(false);
        VIEWMODEL.proceeded(false);        
    }
    
    VIEWMODEL.winner(winner);
    VIEWMODEL.phase(phase);
    VIEWMODEL.turn(turn);
    
    if(selcard)
        VIEWMODEL.selectedCard(selcard.textContent);
    if(votedcard)
        VIEWMODEL.votedCard(votedcard.textContent);
    if(mycards){
        setMyCards(mycards);   
    }
    if(tablecards){
        setTableCards(tablecards);
    }    
    if(phrase){
        $('#clue').text(phrase.textContent);
        VIEWMODEL.clue(phrase.textContent);
    }
    if(selection){
        uncoverCards(selection);
    }
    if(votes){
        setVotes(votes);
    }
    
    VIEWMODEL.players.removeAll(); // reset
    for (i = 0; i < players.length; i++) {
        playerName = players[i].textContent;
        points = players[i].getAttribute("points");
        VIEWMODEL.players.push({ name: playerName, points: points });
    }
}

function uncoverCards(selectionElem){
    var sel = selectionElem.getElementsByTagName("Selection");
    for(i = 0; i<sel.length; i++){
        by = sel[i].getAttribute("by");
        to = sel[i].getAttribute("card");
        // TODO: ensure card names do not contain spaces or illegal chars wrt css selectors
        isTurn = VIEWMODEL.me()===VIEWMODEL.turn() ? '*' : '';
        for(k = 0; k < VIEWMODEL.tablecards().length; k++){
            if(VIEWMODEL.tablecards()[k].cardName===to){
                if(by===VIEWMODEL.turn())
                    by = by + " (turn)";
                VIEWMODEL.tablecards()[k].by(by);
            }
        }
    }
}

function setVotes(votesElem){
    var votes = votesElem.getElementsByTagName("Vote");
    
    // Reset vote count
    for (k = 0; k < VIEWMODEL.tablecards().length; k++) {
        VIEWMODEL.tablecards()[k].votes.removeAll();
    }      
    
    // Add votes
    for(i = 0; i<votes.length; i++){
        by = votes[i].getAttribute("by");
        to = votes[i].getAttribute("toCard");
        for(k = 0; k < VIEWMODEL.tablecards().length; k++){
            if(VIEWMODEL.tablecards()[k].cardName===to){
                VIEWMODEL.tablecards()[k].votes.push(by);
                continue;
            }
        }        
    }
}

function setTableCards(cards){
    VIEWMODEL.tablecards.removeAll();
    console.log("Setting cards on table");
    var ccs = cards.getElementsByTagName("Card");
    for(i = 0; i<ccs.length; i++){
        cardUrl = ccs[i].getAttribute("cardUrl");
        cardName = ccs[i].getAttribute("cardId");
        cardTitle = ccs[i].getAttribute("cardTitle");

        VIEWMODEL.tablecards.push({
               cardUrl: cardUrl,
               cardName: cardName,
               cardTitle: cardTitle,
               votable: ko.computed(function(){
                   return   VIEWMODEL.canVoteCard() &&
                            VIEWMODEL.selectedCard() !== cardName && 
                            VIEWMODEL.me() !== VIEWMODEL.turn() && 
                            VIEWMODEL.phase()==="vote";
               },this),
               vote: function(card){
                   if(card.votable){
                        performAction('voteCard', {
                            user: VIEWMODEL.me(), 
                            match: VIEWMODEL.match(), 
                            card: card.cardName
                        });
                        card.vote = function(){};
                        // $(this).css('border', '5px solid #000');
                    }
               },
               showDetails: function(card){
                   if(VIEWMODEL.phase()==="results" || VIEWMODEL.phase()==="end"){
                        content = $('<p>Chosen by: <em>' + this.by() + "</em></p>");
                        for(i=0; i<this.votes().length; i++){
                            content.append(' <i class="fa fa-check-square"></i> <span>'+this.votes()[i]+'</span> ')
                        }
                        $('#cardDetails').html(content);
                   }
               },
               hideDetails: function(){
                   $('#cardDetails').html('<p>&nbsp;</p>');
               },
               by: ko.observable(),
               votes: ko.observableArray([])
        });        
    }    
}

function setMyCards(cards){
    var ccs = cards.getElementsByTagName("Card");
    VIEWMODEL.mycards.removeAll();
    for(i = 0; i<ccs.length; i++){
        cardUrl = ccs[i].getAttribute("cardUrl");
        cardName = ccs[i].getAttribute("cardId");
        cardTitle = ccs[i].getAttribute("cardTitle");
        VIEWMODEL.mycards.push({
               cardUrl: cardUrl,
               cardName: cardName,
               cardTitle: cardTitle,
               selectable: ko.computed(function(){
                   return VIEWMODEL.canSelectCard() && 
                           VIEWMODEL.phase()==="selectCard" && 
                           !VIEWMODEL.selectedCard();
               },this),
               selected: ko.observable(false),
               select: function(card){
                   performAction('selectCard', {
                       user: VIEWMODEL.me(), 
                       match: VIEWMODEL.match(), 
                       card: card.cardName
                   });
                   card.selected(true);
                   card.select = function(){};  
                   // $(this).css('border', '5px solid #000');
               }
        });         
    }
}


/******************************/
/** Costruzione messaggi XML **/
/******************************/

function createBasicXmlData(rootName, user, match){
    var data = document.implementation.createDocument("", "", null);
    var root = data.createElement(rootName);
    data.appendChild(root);
    var userelem = data.createElement("user");
    userelem.appendChild(data.createTextNode(user));
    var matchelem = data.createElement("match");
    matchelem.appendChild(data.createTextNode(match));
    root.appendChild(userelem);
    root.appendChild(matchelem);
    return data;    
}

function createJoinXmlData(user, match){
    return createBasicXmlData("join", user, match);
}

function createGameUpdateXmlData(user, match){
    return createBasicXmlData("pop", user, match);
}

function createPhraseXmlData(user, match, phrase){
    var data = createBasicXmlData("setPhrase", user, match);
    var phraseElem = data.createElement("phrase");
    phraseElem.appendChild(data.createTextNode(phrase));
    data.documentElement.appendChild(phraseElem);
    return data;
}

function createSelectCardXmlData(user, match, cardId){
    var data = createBasicXmlData("selectCard", user, match);
    var cardElem = data.createElement("card");
    cardElem.appendChild(data.createTextNode(cardId));
    data.documentElement.appendChild(cardElem);
    return data;    
}

function createVoteCardXmlData(user, match, cardId){
    var data = createBasicXmlData("voteCard", user, match);
    var cardElem = data.createElement("card");
    cardElem.appendChild(data.createTextNode(cardId));
    data.documentElement.appendChild(cardElem);
    return data;    
}

function createProceedXmlData(user, match){
    return createBasicXmlData("ok", user, match);
}

/*************************/
/** Funzioni di utilità **/
/*************************/

function setError(msg){
    console.log("ERROR: " + msg);
}

