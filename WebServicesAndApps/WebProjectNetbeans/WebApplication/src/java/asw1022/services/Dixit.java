package asw1022.services;

import asw1022.model.dixit.Card;
import asw1022.model.dixit.GameAction;
import asw1022.model.dixit.GameException;
import asw1022.model.dixit.GameExecution;
import asw1022.model.dixit.GamePhase;
import asw1022.model.dixit.Player;
import asw1022.repositories.CardRepository;
import asw1022.repositories.IRepository;
import asw1022.repositories.MatchRepository;
import asw1022.util.Utils;
import asw1022.util.xml.ManageXML;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.w3c.dom.*;

/**
 * This servlet acts as an XML-over-HTTP web service for the game.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
@WebServlet(urlPatterns = {"/Dixit"}, asyncSupported = true)
public class Dixit extends HttpServlet {

    private HashMap<String, UserAsyncContext> contexts = new HashMap<String, UserAsyncContext>();

    private IRepository<Card> crepo;
    private IRepository<GameExecution> mrepo;
    private List<Card> cards;

    private ManageXML mngXML;

    Logger logger = Logger.getLogger(Dixit.class.getName());

    @Override
    public void init() throws ServletException {
        try {
            ServletContext app = getServletContext();
            String cardDB = app.getRealPath(app.getInitParameter("cardDB"));
            this.crepo = CardRepository.getInstance(cardDB);
            this.mrepo = MatchRepository.getInstance(app);

            // We can read all the cards during initialization
            this.cards = crepo.readAll();

            this.mngXML = new ManageXML();

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error during Dixit Servlet initialization", ex);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        InputStream is = request.getInputStream();
        response.setContentType("text/xml; charset=UTF-8");

        try {
            Document data = null;
            synchronized (this) {
                data = mngXML.parse(is);
            }
            operations(data, request, response, mngXML);
        } catch (Exception ex) {
            logger.severe("Eccezione: " + ex + "\n" + ex.getMessage() + " \n");
            StringWriter stacktrace = new StringWriter();
            ex.printStackTrace(new PrintWriter(stacktrace));
            logger.severe("Stacktrace: " + stacktrace.toString());
        }
    }

    private void operations(Document data,
            HttpServletRequest request,
            HttpServletResponse response,
            ManageXML mngXML) throws Exception {

        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);

        Element root = data.getDocumentElement(); //name of operation is message root
        GameAction operation = getOperationFromString(root.getTagName());
        Object user = request.getSession().getAttribute("username");
        user = user == null ? "" : user.toString();
        logger.info("###### Received msg " + root.getTagName() + " by " + user.toString());
        Document answer = null;
        OutputStream os;
        switch (operation) {
            case JoinMatch:
                answer = joinMatch(root, request, response);
                break;
            case GivePhrase:
                answer = setPhrase(root, request, response);
                break;
            case SelectCard:
                answer = selectCard(root, request, response);
                break;
            case VoteCard:
                answer = voteCard(root, request, response);
                break;
            case Ok:
                answer = proceed(root, request, response);
                break;
            case None:
                answer = getUpdate(root, request, response);
                break;
        }

        if (answer != null) {
            logger.info("Writing response to outstream to req " + root.getTagName() + " by " + user);
            os = response.getOutputStream();
            mngXML.transform(os, answer);
            os.close();
        }
    }

    /**
     * ****************************************
     */
    /* Operation: JoinGame */
    /**
     * ****************************************
     */
    public Document joinMatch(Element root, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        // 0) Get data from XML message
        NodeList children = root.getChildNodes();
        Node usernode = ((Node) children.item(0));
        Node matchnamenode = ((Node) children.item(1));
        String user = usernode.getTextContent();
        String matchname = matchnamenode.getTextContent();
        List<Player> mates = null;

        // Check if provided username matches with the one on the session
        if (!user.equals(session.getAttribute("username"))) {
            return buildErrorMsg("Unexpected error while providing the clue.");
        }

        // 1) Get the match
        GameExecution ge = mrepo.getByName(matchname);
        if (ge == null) {
            return buildErrorMsg("Bad request. This match does not exist.");
        }
        
        synchronized (this) {
            // 2) Create a new context for the user
            UserAsyncContext userCtx = new UserAsyncContext(user);
            contexts.put(user, userCtx);

            // 3) Check if the player has already joined the match
            // If so, provide him the current state of the game, AND RETURN.
            Player player = ge.getPlayerByName(user);
            if (player != null) {
                logger.info("Player " + user + " has already joined the match " + matchname);
                Document answer = mngXML.newDocument("Ok");
                if (!ge.waitingForPlayers()) {
                    logger.info("Passing current game status");
                    this.addGameInfoSubtree(answer, ge, user, null);
                }
                return answer;
            }

            // 4a) The player must be added to the game
            logger.info("Adding new player " + user + " to the match " + matchname);

            // 4b) Let's check first if more players can join the game
            // I.e., if we need more players, let's add the player to the match
            if (ge.waitingForPlayers()) {
                // 4c) Add the player to the game
                Player newplayer = new Player();
                newplayer.setName(user);
                
                try{
                    ge.addPlayer(newplayer);
                } catch (GameException gameException) {
                    return buildErrorMsg(gameException.getMessage());
                } catch (Exception exc) {
                    return buildErrorMsg("Unexpected error while adding new user.");
                }
                
                // 4d) Let's check if, after this addition, we are done
                // If so, let's notify all the players and provide them the game status.
                boolean waitingForPlayers = ge.waitingForPlayers();
                Document answer = mngXML.newDocument("Ok");
                if (!waitingForPlayers) {
                    logger.info(">>>>>>>>>>>>>>>>> SETUP is done. New phase: " + ge.getPhase()); 
                    
                    pushUpdate(ge, GamePhase.Setup);
                }

                return answer;
            } // end if(waitingforplayers)
            else {
                return null;
            }
        } // end synchronized(this)

    }

    /**
     * ****************************************
     */
    /* Operation: JoinGame */
    /**
     * ****************************************
     */
    public Document setPhrase(Element root, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        // 0) Get data from XML message
        NodeList children = root.getChildNodes();
        Node usernode = ((Node) children.item(0));
        Node matchnamenode = ((Node) children.item(1));
        Node phrasenode = ((Node) children.item(2));
        String user = usernode.getTextContent();
        String matchname = matchnamenode.getTextContent();
        String phrasetxt = phrasenode.getTextContent();

        // Check if provided username matches with the one on the session
        if (!user.equals(session.getAttribute("username"))) {
            return buildErrorMsg("Unexpected error while providing the clue.");
        }

        synchronized (this) {
            // Get match
            GameExecution ge = mrepo.getByName(matchname);

            // Get user
            Player player = ge.getPlayerByName(user);

            // Set phrase
            try {
                ge.setPhrase(player, phrasetxt);
            } catch (GameException gameException) {
                return buildErrorMsg(gameException.getMessage());
            } catch(Exception exc){
                return buildErrorMsg("Unexpected error while providing the clue.");
            }
                
            Document answer = mngXML.newDocument("Ok");
            
            if(ge.getPhase()==GamePhase.SelectCard){
                logger.info(">>>>>>>>>>>>>>>>> SET_PHRASE is done. New phase: " + ge.getPhase());

                pushUpdate(ge, GamePhase.SetPhrase);
            }

            return answer;
        } // end synchronized(this)

    }

    /**
     * ****************************************
     */
    /* Operation: SelectCard */
    /**
     * ****************************************
     */
    public Document selectCard(Element root, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        // 0) Get data from XML message
        NodeList children = root.getChildNodes();
        Node usernode = ((Node) children.item(0));
        Node matchnamenode = ((Node) children.item(1));
        Node cardnode = ((Node) children.item(2));
        String user = usernode.getTextContent();
        String matchname = matchnamenode.getTextContent();
        String cardName = cardnode.getTextContent();

        // Check if provided username matches with the one on the session
        if (!user.equals(session.getAttribute("username"))) {
            return buildErrorMsg("Unexpected error while providing the clue.");
        }

        synchronized (this) {
            GameExecution ge = mrepo.getByName(matchname);
            Player player = ge.getPlayerByName(user);

            try{
                ge.selectCard(player, cardName);
            } catch (GameException gameException) {
                return buildErrorMsg(gameException.getMessage());
            } catch(Exception exc){
                return buildErrorMsg("Unexpected error while selecting a card.");
            }
            
            Document answer = mngXML.newDocument("Ok");

            // When all the players have selected a card, the game switch phase to "vote"
            if (ge.getPhase() == GamePhase.Vote) {
                logger.info(">>>>>>>>>>>>>>>>> SELECTCARD is done. New phase: " + ge.getPhase());
                
                pushUpdate(ge, GamePhase.SelectCard);
            } // end if(phase="vote")
            return answer;
        } // end synchronized(this)

    }

    /**
     * ****************************************
     */
    /* Operation: VoteCard */
    /**
     * ****************************************
     */
    public Document voteCard(Element root, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        // 0) Get data from XML message
        NodeList children = root.getChildNodes();
        Node usernode = ((Node) children.item(0));
        Node matchnamenode = ((Node) children.item(1));
        Node cardnode = ((Node) children.item(2));
        String user = usernode.getTextContent();
        String matchname = matchnamenode.getTextContent();
        String cardName = cardnode.getTextContent();

        // Check if provided username matches with the one on the session
        if (!user.equals(session.getAttribute("username"))) {
            return buildErrorMsg("Unexpected error while providing the clue.");
        }

        synchronized (this) {
            GameExecution ge = mrepo.getByName(matchname);
            Player player = ge.getPlayerByName(user);

            try{
                ge.vote(player, cardName);
            } catch (GameException gameException) {
                return buildErrorMsg(gameException.getMessage());
            } catch(Exception exc){
                return buildErrorMsg("Unexpected error while voting a card.");
            }
            
            Document answer = mngXML.newDocument("Ok");

            // When all the players have voted a card, the game switch phase to "results"
            if (ge.getPhase() == GamePhase.Results || ge.getPhase() == GamePhase.End) {
                logger.info(">>>>>>>>>>>>>>>>> VOTE is done. New phase: " + ge.getPhase());

                pushUpdate(ge, GamePhase.Vote);
                
            } // end if(phase="vote")
            return answer;
        } // end synchronized(this)

    }

    /**
     * ****************************************
     */
    /* Operation: Proceed */
    /**
     * ****************************************
     */
    public Document proceed(Element root, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        // 0) Get data from XML message
        NodeList children = root.getChildNodes();
        Node usernode = ((Node) children.item(0));
        Node matchnamenode = ((Node) children.item(1));
        String user = usernode.getTextContent();
        String matchname = matchnamenode.getTextContent();

        // Check if provided username matches with the one on the session
        if (!user.equals(session.getAttribute("username"))) {
            return buildErrorMsg("Unexpected error while providing the clue.");
        }

        synchronized (this) {
            GameExecution ge = mrepo.getByName(matchname);
            Player player = ge.getPlayerByName(user);

            logger.info(player.getName() + " is done and wants to proceed.");

            try{
                ge.proceed(player);
            } catch (GameException gameException) {
                return buildErrorMsg(gameException.getMessage());
            } catch(Exception exc){
                return buildErrorMsg("Unexpected error while proceeding.");
            }            
            
            Document answer = mngXML.newDocument("Ok");

            // When all the players have voted a card, the game switch phase to "results"
            if (ge.getPhase() == GamePhase.SetPhrase) {
                logger.info("READY TO GO TO NEXT TURN. New phase: " + ge.getPhase());
                
                pushUpdate(ge, GamePhase.Results);
            } // end if(phase="setphrase")
            return answer;
        } // end synchronized(this)

    }

    /**
     * ****************************************
     */
    /* Operation: GetUpdate */
    /**
     * ****************************************
     */
    public synchronized Document getUpdate(Element root,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String user = (String) request.getSession().getAttribute("username");
        logger.info("User " + user + " wants to receive updates");

        boolean async;
        UserAsyncContext ctx = contexts.get(user);

        if (ctx == null) {
            logger.info("The context is null for " + user);
            return null;
        }

        synchronized (ctx) {
            if (ctx._queue.size() == 0) {
                if (ctx._async.get() == null) {
                    logger.info("The list is empty and asyncCtx null for " + user + " => async");
                    AsyncContext asyncContext = request.startAsync();
                    asyncContext.setTimeout(20 * 1000);
                    asyncContext.addListener(ctx);
                    if (!ctx._async.compareAndSet(null, asyncContext)) {
                        logger.severe("Eeeh?!? " + user);
                        return null;
                    }
                } else {
                    logger.severe("Error: async context not null for " + user);
                    return null;
                }
            } else {
                logger.info("Synchronously return the queued message to " + user);
                return ctx._queue.remove();
            }
        }
        return null;
    }

    private void addGameInfoSubtree(Document doc, 
            GameExecution ge, 
            String currUser,
            GamePhase completedPhase) throws Exception {
        Element root = doc.getDocumentElement();

        // Add info about the ongoing match: phase, the turn's player, the players, and their points
        Element gameInfo = doc.createElement("GameInfo");
        gameInfo.setAttribute("phase", ge.getPhase().toString());
        gameInfo.setAttribute("turn", ge.getTurn().getName());
        Element playersElem = doc.createElement("Players");
        for (Player p : ge.getPlayers()) {
            Element playerElem = doc.createElement("Player");
            playerElem.setAttribute("points", "" + ge.getPointsPerPlayer(p.getName()));
            playerElem.appendChild(doc.createTextNode(p.getName()));
            playersElem.appendChild(playerElem);
        }
        gameInfo.appendChild(playersElem);        
        
        // Add cards to the response
        if(completedPhase==null 
                || completedPhase==GamePhase.Setup 
                || completedPhase==GamePhase.SelectCard){
            Element cards = doc.createElement("YourCards");
            for (Card c : ge.getCardsForPlayer(currUser)) {
                Element card = doc.createElement("Card");
                card.setAttribute("cardId", c.getName());
                card.setAttribute("cardTitle", c.getTitle());
                card.setAttribute("cardUrl", c.getUrl());
                cards.appendChild(card);
            }
            gameInfo.appendChild(cards);
        }

        // Add the clue, if available
        if(completedPhase==null 
                || completedPhase==GamePhase.SetPhrase){
            String phrase = ge.getPhrase();
            if (phrase != null) {
                Element phraseElem = doc.createElement("Phrase");
                phraseElem.appendChild(doc.createTextNode(phrase));
                gameInfo.appendChild(phraseElem);
            }
        }

        // Add the card selected by this very user
        Player thisplayer = ge.getPlayerByName(currUser);
        Map<Player, Card> selcards = ge.getCardSelection();

        Card cselected = selcards.get(thisplayer);
        if (cselected != null) {
            Element selcardElem = doc.createElement("SelectedCard");
            selcardElem.setTextContent(cselected.getName());
            gameInfo.appendChild(selcardElem);
        }

        // Add the card voted by this very player
        Card cvoted = ge.getVotes().get(thisplayer);
        if (cvoted != null) {
            Element votedElem = doc.createElement("VotedCard");
            votedElem.setTextContent(cvoted.getName());
            gameInfo.appendChild(votedElem);
        }

        // Add the "table cards", i.e., all the cards that have been selected
        if(completedPhase==null 
                || completedPhase==GamePhase.SelectCard){
            List<Player> pskeys = Utils.Shuffle(selcards.keySet());
            if (ge.getPhase() == GamePhase.Vote || ge.getPhase() == GamePhase.Results
                    || ge.getPhase() == GamePhase.End) {
                if (selcards != null && selcards.size() > 0) {
                    Element selcardsElem = doc.createElement("CardsOnTable");
                    for (Player p : pskeys) {
                        Card c = selcards.get(p);
                        Element card = doc.createElement("Card");
                        card.setAttribute("cardId", c.getName());
                        card.setAttribute("cardTitle", c.getTitle());
                        card.setAttribute("cardUrl", c.getUrl());
                        selcardsElem.appendChild(card);
                    }
                    gameInfo.appendChild(selcardsElem);
                }
            }
        }

        // Add the results of this round: cards and votes are uncovered
        if(completedPhase==null 
                || completedPhase==GamePhase.Vote){
            if (ge.getPhase() == GamePhase.Results
                    || ge.getPhase() == GamePhase.End) {
                Element votesElem = doc.createElement("Votes");
                Element selectionElem = doc.createElement("UncoveredCards");

                Map<Player, Card> selection = ge.getCardSelection();
                Map<Player, Card> votes = ge.getVotes();

                for (Player chooser : selection.keySet()) {
                    Card selectedCard = selection.get(chooser);
                    Element selElem = doc.createElement("Selection");
                    selElem.setAttribute("by", chooser.getName());
                    selElem.setAttribute("card", selectedCard.getName());
                    selectionElem.appendChild(selElem);
                }
                gameInfo.appendChild(selectionElem);

                for (Player voter : votes.keySet()) {
                    Card votedCard = votes.get(voter);
                    Element voteElem = doc.createElement("Vote");
                    voteElem.setAttribute("by", voter.getName());
                    voteElem.setAttribute("toCard", votedCard.getName());
                    votesElem.appendChild(voteElem);
                }
                gameInfo.appendChild(votesElem);
            }
        }

        // If the match has been finished, let's communicate the winner
        if (ge.getPhase() == GamePhase.End) {
            gameInfo.setAttribute("winner", ge.getWinner().getName());
        }

        root.appendChild(gameInfo);
    }
    
    public synchronized void pushUpdate(GameExecution ge, GamePhase completed) throws Exception {
        for (Player player : ge.getPlayers()) {
            String destUser = player.getName();
            Document data = mngXML.newDocument("update");
            this.addGameInfoSubtree(data, ge, destUser, completed);

            deliverMessage(destUser, data);
        } // end for(contexts)        
    }
    
    public synchronized void deliverMessage(String destUser, Document data) throws Exception {
        UserAsyncContext ctx = contexts.get(destUser);
        synchronized (ctx) {
            ctx._queue.add(data);
            AsyncContext asyncCtx = ctx._async.get();
            if (asyncCtx != null && ctx._async.compareAndSet(asyncCtx, null)) {
                OutputStream os = asyncCtx.getResponse().getOutputStream();
                mngXML.transform(os, data);
                asyncCtx.complete();
            }
        }       
    }
    
    public Document buildErrorMsg(String msg){
        Document errMsg = mngXML.newDocument("error");
        Element root = errMsg.getDocumentElement();
        root.setTextContent(msg);
        return errMsg;
    }

    public GameAction getOperationFromString(String str) {
        if (str.equals("join")) {
            return GameAction.JoinMatch;
        } else if (str.equals("setPhrase")) {
            return GameAction.GivePhrase;
        } else if (str.equals("selectCard")) {
            return GameAction.SelectCard;
        } else if (str.equals("voteCard")) {
            return GameAction.VoteCard;
        } else if (str.equals("ok")) {
            return GameAction.Ok;
        } else {
            return GameAction.None;
        }
    }
}
