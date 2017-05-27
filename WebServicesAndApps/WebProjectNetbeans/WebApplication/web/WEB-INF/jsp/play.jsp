<%-- 
    Document   : index
    Created on : 20-Dec-2014, 12:19:56
    Author     : Roberto Casadei <roberto.casadei12@studio.unibo.it>
--%>

<%@page import="asw1022.model.dixit.GameExecution"%>
<%@page import="asw1022.model.dixit.MatchConfiguration.MatchVisibility"%>
<%@page import="asw1022.model.dixit.MatchConfiguration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        
        <title>Ipse Dixit: Play</title>

        <%@ include file="/WEB-INF/jspf/head.jspf" %>
        
        <script type="text/javascript">
        function getCtx(){
            return '${pageContext.request.contextPath}';
        }
        </script>
            
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/dixit.js"></script>
        
    </head>
    <body>

<%@ include file="/WEB-INF/jspf/prologue.jspf" %>

<script type="text/javascript">
    VIEWMODEL.me('<%= session.getAttribute("username") %>');
    VIEWMODEL.match('<%= request.getAttribute("MatchId") %>');
</script>


<div id="table">
    <h1>Table</h1>
    
    <p data-bind="style: { display: clue() ? 'block' : 'none' }">Clue: <span id="clue" data-bind="text: clue"></span></p>
    
    <div id="tablecards" data-bind="foreach: tablecards">
        
        <div class="dixitImgDiv"
             data-bind="style: { width: Math.floor(100/($root.mycards().length+1))+'%',
                id: 'tableImgDiv_'+cardName }" >
            <img data-bind="attr: { 
                        src: cardUrl, name:cardName, title:cardTitle, width:'100%',
                        id: 'tableImg_'+cardName }, 
                        style: { opacity: (votable() ? '1' : '0.6')},
                        click: vote,
                        event: { mouseover: showDetails, mouseout: hideDetails }" />          
        </div>
        
    </div>
    
    <div id="cardDetails">
            <!--
            <p data-bind="style:{display: by() ? 'inline-block' : 'none'}">Chosen by: <em><span data-bind="text: by"></span></em></p>
            <div data-bind="foreach: votes">
                <i class="fa fa-check-square"></i> <span data-bind="text: $data"></span>
            </div>  
            -->
            <p>&nbsp;</p>
    </div>
    
</div>
        
<div id="gameinfo">
    <a href="${pageContext.request.contextPath}/<%= "Play?"+request.getQueryString() %>">
        <i class="fa fa-link"></i> MATCH URL</a>
        
    <h1><i class="fa fa-arrow-circle-right"></i> <span data-bind="text: suggestion"></span></h1>  
    <div class="infobutton">
        <input type="hidden" name="match" value="<%= request.getAttribute("MatchId")%>" />
        <input type="hidden" name="user" value="<%= session.getAttribute("username")%>" />
        <input type="button" id="request_button" class="fancybutton" 
               data-bind="attr: { value: request }, 
                style: { display: request()===null?'none':'inline-block'},
                click: next_action()" /> 
    </div>    
    
    <h1><i class="fa fa-info-circle"></i> Info</h1>
    <p class="info"><span class="label">You are </span> <span id="username" data-bind="text: me"></span></p>
    <p class="info"><span class="label">Turn:</span> <span id="turntxt" data-bind="text: turn"></span></p>
    <p class="info"><span class="label">Phase:</span> <span id="phasetxt" data-bind="text: phase"></span></p>
    
    <h1><i class="fa fa-list-alt"></i> Results</h1>
    
    <table>
        <thead>
            <tr>
                <td>Player</td>
                <td>Point</td>
            </tr>
        </thead>
        <tbody id="resultsbody" data-bind="foreach: players">
            <tr>
                <td data-bind="text: name"></td>
                <td data-bind="text: points"></td>
            </tr>
        </tbody>
    </table>
    
    <h1><i class="fa fa-calculator"></i> Match configuration</h1>
    <% MatchConfiguration mc = (MatchConfiguration)request.getAttribute("MatchConfiguration"); %>
    <p class="info"><span class="label">Match title: </span> <%= ((GameExecution)request.getAttribute("Match")).getTitle() %> </p>
    <p class="info"><span class="label">Num players: </span> <%= mc.getNumPlayers() %> </p>
    <p class="info"><span class="label">Points to reach: </span> <%= mc.getNumPoints()%> </p>
    <p class="info"><span class="label">Num cards per player: </span> <%= mc.getNumCardsForPlayers()%> </p>    
    <p class="info"><span class="label">Visibility: </span> 
        <%= mc.getVisibility()==MatchVisibility.All ? "public" : "secret" %> </p>
</div>        

<div id="playerpanel">
    <h1>Your panel</h1>
    
    <div id="playercards" data-bind="foreach: mycards">
        <img data-bind="attr: { 
                src: cardUrl, name:cardName, title:cardTitle,
                id: 'mycard_'+cardName,
                width: Math.floor(100/($root.mycards().length+1))+'%'},
                style: { opacity: (selectable() ? '1' : '0.6')},
                css: { selected: selected() },
                click: select" />
    </div>
    
    <div data-bind="style: { display: canTellClue() ? 'block' : 'none' }">
            <label for="cluetxt">Clue:</label>
            <input type="text" name="clue" id="cluetxt" 
                   data-bind="enable: canTellClue"/>
            <input type="button" value="Give it!" id="phrasebtn" 
                   data-bind="enable: canTellClue, click:next_action()" />
    </div>
</div>      
    
<div class="clearer"></div>  


<%@ include file="/WEB-INF/jspf/epilogue.jspf" %>


    </body>
    
</html>