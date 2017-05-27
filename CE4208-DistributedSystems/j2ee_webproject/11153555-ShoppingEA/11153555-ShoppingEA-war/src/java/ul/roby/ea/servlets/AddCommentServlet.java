/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ul.roby.ea.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import ul.roby.ea.ejb.CommentBeanLocal;
import ul.roby.ea.helpers.WebUtils;

/**
 *
 * @author Roberto Casadei, 11153555
 */
public class AddCommentServlet extends HttpServlet {
    
    @javax.ejb.EJB
    private CommentBeanLocal comments;
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect( request.getContextPath() );
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        RequestDispatcher rd = request.getRequestDispatcher("jspf/top.jspf");
        rd.include(request, response);        
        
        String bid = request.getParameter("bookid");
        String author = request.getParameter("author");
        String content = request.getParameter("content");
        if( bid==null || author==null || content==null || 
                bid.length()==0){
            response.sendRedirect( request.getContextPath() );
        }
        else{
            
            // purify input through AntiSamy
            boolean purification = false;
            try{
                URL pfile = getServletContext().getResource("/antisamy-slashdot-1.4.4.xml");
                Policy p = Policy.getInstance( pfile );
                AntiSamy antisamy = new AntiSamy();
                CleanResults purified = antisamy.scan(content,p);
                content = purified.getCleanHTML();
                purified = antisamy.scan(author,p);
                author = purified.getCleanHTML();
                purification = true;
            } catch(Exception exc){
                System.out.println(exc.toString());
            }
            
            // check if a valid book id is provided
            Matcher correctBookId = WebUtils.numcheck.matcher(bid);
            
            // check if comments can be persisted
            if(!correctBookId.matches() || !purification || author.length()==0 || content.length()==0){
                out.println("<p class=\"imp\">Invalid inputs.</p>");
            }
            else{
                comments.addComment(Integer.parseInt(bid), author, content);
                out.println("<p class=\"imp\">Comment added.</p>");
            }
            
            out.println("<p><a href=\"BookServlet?id="+bid+"\">Go back to book's page</a></p>");
            
        }

        rd = request.getRequestDispatcher("jspf/bottom.jspf");
        rd.include(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
