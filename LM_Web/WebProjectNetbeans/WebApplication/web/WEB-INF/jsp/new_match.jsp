<%-- 
    Document   : newjspnew_game
    Created on : 02-Jan-2015, 12:01:03
    Author     : Roberto Casadei <roberto.casadei12@studio.unibo.it>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        
        <title>Ipse Dixit: New match</title>

        <%@ include file="/WEB-INF/jspf/head.jspf" %>
        
    </head>
    <body>

        <%@ include file="/WEB-INF/jspf/prologue.jspf" %>

        <h1>Create a new match</h1>
        
        <%@ include file="/WEB-INF/jspf/form_errors.jspf" %>
        
        <% if(request.getAttribute("done")==null) { %>

        <form method="post" action="${pageContext.request.contextPath}/NewMatch">

            <div class="formrow">
                <label for="matchtitle">Title of the match</label>
                <input type="text" size="20" maxlength="20" name="matchtitle" id="matchtitle" 
                       value="${pageContext.request.getParameter("matchTitle")}"/>
            </div>    

            <div class="formrow">
                <label for="numplayers">Number of players</label>
                <input type="text" size="2" maxlength="2" name="numplayers" id="numplayers"
                       value="<%= request.getParameter("numplayers")==null ? "3" : request.getParameter("numplayers") %>" />
            </div>
            
            <div class="formrow">
                <label for="npoints">Points to reach</label>
                <input type="text" size="2" maxlength="2" name="npoints" id="npoints"
                       value="<%= request.getParameter("npoints")==null ? "5" : request.getParameter("npoints") %>" />
            </div>            

            <div class="formrow">
                <label for="ncardsforplayer">Num cards per player</label>
                <input type="text" size="2" maxlength="2" name="ncardsforplayer" id="ncardsforplayer" 
                       value="<%= request.getParameter("ncardsforplayer")==null ? "4" : request.getParameter("ncardsforplayer") %>" />
            </div>                  

            <div class="formrow">
                <label for="visibility">Visibility</label>
                <select name="visibility" id="visibility">
                    <option value="all">All</option>
                    <option value="onlyurl">Only those who know the URL</option>
                </select>
            </div>

            <div class="formrow">
                <input type="submit" value="Create a new match!" />
            </div>

        </form>
        
        <% } %>

        <%@ include file="/WEB-INF/jspf/epilogue.jspf" %>

    </body>
    
</html>
