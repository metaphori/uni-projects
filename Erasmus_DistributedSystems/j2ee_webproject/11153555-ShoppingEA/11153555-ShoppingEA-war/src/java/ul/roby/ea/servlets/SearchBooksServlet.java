/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ul.roby.ea.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ul.roby.ea.ejb.BookBeanLocal;
import ul.roby.ea.entities.Book;
import ul.roby.ea.helpers.WebUtils;

/**
 *
 * @author Roberto Casadei, 11153555
 */
public class SearchBooksServlet extends HttpServlet {
    @javax.ejb.EJB
    BookBeanLocal books;
            
            
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
        
        String skey = request.getParameter("skey"); // search string
        List<Book> lst = null;
        
        try{
        
            // fetch the results
            if( skey == null || skey.length()==0 ){ 
                // display all the books
                lst = books.findAll();

            } else{
                // search by id, title, description, keywords
                String field = request.getParameter("sfield");
                if(field!=null){

                    if(field.equals("id")){

                        Matcher m = WebUtils.numcheck.matcher(skey);
                                               
                        if( m.matches() ){
                        Book b = books.findById(Integer.parseInt(skey));
                            if(b!=null){
                                lst = new ArrayList<Book>();
                                lst.add(b);
                            }
                        }
                        else{
                            out.println("<p class=\"imp\">Invalid input for ID search.</p>");
                        }
                    } else if(field.equals("title")){
                        lst = books.findByTitle(skey);
                    } else if(field.equals("keywords")){
                        lst = books.findByKeywords(skey);
                    } else if(field.equals("description")){
                        lst = books.findByDescription(skey);
                    }

                }

            }

            if( lst == null || lst.isEmpty() ){
                out.println("<p class=\"imp\">No results found</p>");
            }
            else{
                // display the results
                out.println("<p>Your search has produced the following results:</p><br />");
                for(Book b : lst){
                    out.println("<h3>#"+b.getId()+" " + b.getTitle()+"</h3>");
                    out.println("<div style=\"margin-left:20px; border-left:2px solid #bcbcbc; margin-bottom:25px; padding-left:5px;\">");

                    out.println("<p>"+ b.getDescription()+"</p><br />");
                    out.println("<p><b>Keywords</b>: "+ b.getKeywords().replaceAll(",", ", ") +"</p>");
                    if( b.getQuantity()==0 ){
                        out.println("<p style=\"color:#f00;\">OUT OF STOCK</p>");
                    } else{
                        out.println("<p><a href=\"CartServlet?add="+b.getId()+"\">Add to cart</a></p>");
                    }
                    out.println("<a href=\"BookServlet?id="+b.getId()+"\">Go to book page</a><br />");
                    out.println("</div>");
                }
            }

        } catch(Exception exc){
            out.println("<p class=\"imp\">Something unexpected happened.</p>");
        } finally{
            out.close();
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
        return "Book search";
    }// </editor-fold>
}
