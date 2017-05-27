<%-- 
    Document   : about
    Created on : 02-Jan-2015, 12:00:56
    Author     : Roberto Casadei <roberto.casadei12@studio.unibo.it>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>

        <title>Ipse Dixit: About</title>
        
        <%@ include file="/WEB-INF/jspf/head.jspf" %>
        
    </head>
    <body>

        <%@ include file="/WEB-INF/jspf/prologue.jspf" %>

        <p>Adapted from Wikipedia: <a href="http://en.wikipedia.org/wiki/Dixit_%28card_game%29">Dixit</a></p>

        <h1>About</h1>

        <p><em>Dixit</em> is a card game created by Jean-Louis Roubira, 
            and published by Libellud. Using a deck of cards illustrated with dreamlike images, 
            players select cards that match a title suggested by the "storyteller", 
            and attempt to guess which player selected which card.</p>

        <h2>Gameplay</h2>

        <p>Each player starts the game with a number of random <em>cards</em>. 
            Players then take turns being the storyteller. 
            The player whose turn it is to be storyteller looks at the images in his or her hand. 
            From one of these, he or she makes up a sentence or <em>phrase</em> that might describe it 
            and says it out loud (without showing the card to the other players).</p>
        <p>Each other player then <e>selects</em> from among their own cards the one that 
            best matches the sentence given by the storyteller. 
            Then, each player gives their selected card to the storyteller, 
            without showing it to the others. The storyteller shuffles his or her chosen card 
            with the cards received from the other players, and all cards are then dealt face up. 
            The players (except for the storyteller) then secretly <em>guess</em> which picture 
            was the storyteller's, using numbered voting chips.</p>
        <p>If nobody or everybody finds the correct picture, 
            the storyteller scores 0, and each of the other players scores 2. 
            Otherwise the storyteller and all players who found the correct answer score 3. 
            Players other than the storyteller score 1 point for each vote 
            their own pictures receive.</p>
        <p>A large part of the <em>skill</em> of the game comes from being able, 
            when acting as the storyteller, 
            to offer a title which is neither too obscure 
            (such that no other player can identify it) nor too obvious 
            (such that every player is able to guess it).</p>
        <p>The game ends when the card deck is empty or when a given point threshold is reached. 
            The player with the highest point total wins the game.</p>


        <%@ include file="/WEB-INF/jspf/epilogue.jspf" %>

    </body>
    
</html>