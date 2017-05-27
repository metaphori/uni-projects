<%-- 
    Document   : index
    Created on : 20-Dec-2014, 12:19:56
    Author     : Roberto Casadei <roberto.casadei12@studio.unibo.it>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        
        <title>Ipse Dixit: Error</title>

        <%@ include file="/WEB-INF/jspf/head.jspf" %>
        
    </head>
    <body>

        <%@ include file="/WEB-INF/jspf/prologue.jspf" %>

        <h1 class="error"><%= request.getAttribute("errorTitle") %></h1>

        <p class="error"><%= request.getAttribute("errorMsg") %></p>

        <%@ include file="/WEB-INF/jspf/epilogue.jspf" %>

    </body>
    
</html>