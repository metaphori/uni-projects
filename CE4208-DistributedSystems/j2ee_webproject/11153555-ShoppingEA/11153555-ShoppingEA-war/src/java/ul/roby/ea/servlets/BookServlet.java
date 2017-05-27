/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ul.roby.ea.servlets;

import ul.roby.ea.entities.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ul.roby.ea.ejb.BookBeanLocal;
import ul.roby.ea.ejb.CommentBeanLocal;
import ul.roby.ea.entities.Comment;
import ul.roby.ea.helpers.WebUtils;

/**
 *
 * @author Roberto Casadei, 11153555
 */
public class BookServlet extends HttpServlet {
    @javax.ejb.EJB
    BookBeanLocal books;
    
    @javax.ejb.EJB
    CommentBeanLocal comments;
    
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
                
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        RequestDispatcher rd = request.getRequestDispatcher("jspf/top.jspf");
        rd.include(request, response);
        
        String bid = request.getParameter("id");
        if(bid==null || bid.length()==0){
            response.sendRedirect( request.getContextPath() );
        } else{
            Matcher match =  WebUtils.numcheck.matcher(bid);
            if(! match.matches() ){
                response.sendRedirect( request.getContextPath() );
            } else{
                
                Book book = books.findById(Integer.parseInt(bid));
                if( book == null ){
                    response.sendRedirect( request.getContextPath() );
                } else{
                    // we have a book => show book's information
                    out.println("<h2>#"+book.getId()+" " + book.getTitle() +"</h2>");
                    out.println("<p>" + book.getDescription() + "</p><br />");
                    out.println("<p><b>Keywords</b>: " + book.getKeywords().replace(",", ", "));
                    
                    if( book.getQuantity()==0 ){
                        out.println("<p style=\"color:#f00;\">OUT OF STOCK</p><br />");
                    } else{
                        out.println("<br /><a href=\"CartServlet?add="+book.getId()+"\">Add to cart</a>.</p><br />");
                    }
                    out.println("<h3>Comments</h3>");
                    out.println("<form action=\"AddCommentServlet\" method=\"post\">");
                    out.println("Your name: <input type=\"text\" name=\"author\" /><br />");
                    out.println("Comment text: <textarea cols=\"40\" rows=\"10\" name=\"content\"></textarea><br />");
                    out.println("<input type=\"hidden\" name=\"bookid\" value=\""+book.getId()+"\" />");
                    out.println("<input type=\"submit\" value=\"Send comment!\">");
                    out.println("</form><br /><br />");
                    
                    // retrieve all the comments associated to the selected book
                    List<Comment> commlist = comments.findAllByBookId(book.getId());
                    if( commlist==null || commlist.isEmpty()){
                        out.println("<p>No comments.</p>");
                    } else{
                        // show the comments
                        for(Comment c : commlist){
                        
                            out.println("<p>Comment "+c.getId()+", sent by <b>"+c.getAuthor()+"</b></p>");
                            out.println("<p>"+c.getContent()+"</p>");
                            out.println("<hr style=\"border:1px solid #464646\" /><br />");
                        }
                    }
                    
                }
                
            }
        }
        
        rd = request.getRequestDispatcher("jspf/bottom.jspf");
        rd.include(request, response);
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
        response.sendRedirect( request.getContextPath() );
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Book page detail";
    }// </editor-fold>
}
