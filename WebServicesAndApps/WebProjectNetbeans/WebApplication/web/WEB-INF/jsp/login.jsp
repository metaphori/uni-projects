<%-- 
    Document   : login
    Created on : 02-Jan-2015, 12:01:39
    Author     : Roberto Casadei <roberto.casadei12@studio.unibo.it>
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>

        <title>Ipse Dixit: Login</title>
        
        <%@ include file="/WEB-INF/jspf/head.jspf" %>
        
    </head>
    <body>

        <%@ include file="/WEB-INF/jspf/prologue.jspf" %>

        <h1>Login</h1>
        
        <%@ include file="/WEB-INF/jspf/form_errors.jspf" %>
        
        <% if(session.getAttribute("username")==null){ %>

        <form method="post" action="${pageContext.request.contextPath}/Login">

            <div class="formrow">
                <label for="username">Username</label>
                <input type="text" size="20" maxlength="20" name="username" id="username" 
                       value="${pageContext.request.getParameter("username")}"/>
            </div>    

            <div class="formrow">
                <label for="password">Password</label>
                <input type="password" size="20" maxlength="20" name="password" id="password" />
            </div>

            <div class="formrow">
                <input type="submit" value="Login!" />
            </div>

        </form>
        <% } else { %>
        <p class="error">You are already logged in.</p>
        <% } %>

        <%@ include file="/WEB-INF/jspf/epilogue.jspf" %>

    </body>
    
</html>