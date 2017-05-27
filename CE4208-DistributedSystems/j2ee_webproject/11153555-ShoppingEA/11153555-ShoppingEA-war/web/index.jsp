<%-- 
    Document   : index
    Created on : Mar 30, 2012, 10:28:04 AM
    Author     : roby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
        
        <%@include file="jspf/top.jspf" %>
        
        <h2>Search books</h2>
        <form action="SearchBooksServlet" method="get">
            Search string <input type="text" name="skey" />
            in <select name="sfield">
                <option value="id">id</option>
                <option value="title">title</option>
                <option value="keywords">keywords</option>
                <option value="description">description</option>
            </select>
            
            <input type="submit" value="Search!" />
        </form>
        
        <%@include file="jspf/bottom.jspf" %>
        
    </body>
</html>
