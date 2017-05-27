/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ul.roby.ea.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ul.roby.ea.ejb.BookBeanLocal;

/**
 * This servlet is used to populate the database with data.
 * @author Roberto Casadei, 11153555
 */
public class PopulatorServlet extends HttpServlet {

    @javax.ejb.EJB
    BookBeanLocal populator;
    

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
        
        if( populator.isEmpty() ){
            // database needs to be populated
            populator.populate();
            out.println("<p>The database has been populated.</p>");
        } else{
            out.println("<p>The database contains data already.</p>");
        }
        
        out.println("<br /><a href=\"" + request.getContextPath() +"\">Go back</a>");
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
        return "This servlet populates the database with data.";
    }// </editor-fold>
}
