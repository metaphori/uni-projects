<%-- 
    Document   : index
    Created on : 20-Dec-2014, 12:19:56
    Author     : Roberto Casadei <roberto.casadei12@studio.unibo.it>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>

        <title>Ipse Dixit: Home Page</title>
        
        <%@ include file="/WEB-INF/jspf/head.jspf" %>
        
    </head>
    <body>

        <%@ include file="/WEB-INF/jspf/prologue.jspf" %>

        <%@ include file="/WEB-INF/jspf/msg.jspf" %>

        <h1>Welcome to Ipse Dixit</h1>
        
        <p><em>Ipse Dixit</em> is a web application project developed by <em>Roberto Casadei</em>
            in the context of the Master Degree module <em>Applicazioni e Servizi Web</em> 
            at Alma Mater Studiorum - University
            of Bologna.</p>
        
        <p>It represents a case study, an <em>exercise</em> on the content covered in the lectures.</p>
        
        <p>The subject of the project is <em>Dixit</em>, a card game built on creativity and 
            intuition. <a href="${pageContext.request.contextPath}/About">Discover more about Dixit</a>.</p>
        
        <h2><i class="fa fa-gamepad"></i> How to play</h2>

        <ol>
            <li>Register to the site</li>
            <li>Log in</li>
            <li>Create a match or join an existing match</li>
            <li>Enjoy</li>
        </ol>

        <%@ include file="/WEB-INF/jspf/epilogue.jspf" %>

    </body>
    
</html>