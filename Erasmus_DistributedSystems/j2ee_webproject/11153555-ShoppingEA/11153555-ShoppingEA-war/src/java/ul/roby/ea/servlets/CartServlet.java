/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ul.roby.ea.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.SessionBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ul.roby.ea.ejb.CartBean;
import ul.roby.ea.ejb.CartBeanLocal;
import ul.roby.ea.ejb.CartEntry;
import ul.roby.ea.entities.Book;
import ul.roby.ea.helpers.WebUtils;

/**
 *
 * @author Roberto Casadei, 11153555
 */
public class CartServlet extends HttpServlet {
    private CartBeanLocal cartBean = null;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            RequestDispatcher rd = request.getRequestDispatcher("jspf/top.jspf");
            rd.include(request, response);
            
            // get the cart from the session, or creates a new one
            HttpSession sess = request.getSession();
            Object cart = sess.getAttribute("cart");
            if( cart == null){
                System.out.println("#### GETTED NEW ####");
                // pick a new cart
                cartBean = lookupCartBeanLocal();
                // and add it to the session
                sess.setAttribute("cart", cartBean);
            } else{
                System.out.println("#### PICKED FROM SESSION ####");
                cartBean = (CartBeanLocal) cart;
            }
            
            Pattern qtypattern = Pattern.compile("qt[0-9]{1,8}");
            Pattern removepattern = Pattern.compile("rem[0-9]{1,8}");
            Matcher match;
            
            if( request.getMethod().equals("GET") ){
                String bookToAdd = request.getParameter("add");
                if(bookToAdd!=null){
                    match = WebUtils.numcheck.matcher(bookToAdd);
                    if( match.matches() && cartBean.addToCart(Integer.parseInt(bookToAdd), 1, true) ){
                        out.println("<p class=\"imp\">Item successfully added into the cart.</p>");
                    } else{
                        out.println("<p class=\"imp\">The item cannot be added into the cart.</p>");
                    }
                }
            } else if( request.getMethod().equals("POST")){
                // UPDATE CART
                
                String operation = request.getParameter("send");
                if(operation == null){
                    response.sendRedirect("CartServlet");
                } else if( operation.contains("Empty") ){
                        // empty the cart
                        
                        cartBean.empty();
                        
                        out.println("<p class=\"imp\">The cart has been emptied.</p>");
                    
                } else {
                
                    Enumeration<String> params = request.getParameterNames();
                    while( params.hasMoreElements() ){
                        String param = params.nextElement();

                        // check for quantity param format
                        match = qtypattern.matcher(param);
                        if( match.matches() ){
                            // check format of quantity value
                            String qtparam = request.getParameter(param);
                            match = WebUtils.numcheck.matcher(qtparam);
                            if(match.matches()){
                                // set new quantity
                                cartBean.addToCart(Integer.parseInt(param.replaceAll("qt", "")), 
                                        Integer.parseInt(qtparam), false);
                            }
                        }

                        // check for remove param format
                        match = removepattern.matcher(param);
                        if( match.matches() ){
                            cartBean.addToCart(Integer.parseInt(param.replaceAll("rem", "")), 
                                    0, false);
                        }
                    }
                
                }
                
            } else{
                response.sendRedirect( request.getServletContext().getContextPath() );
            }
            
            out.println("<form action=\"CartServlet\" method=\"post\">");
            out.println("<table width=\"100%\"><thead><tr><td>Id</td><td>Book</td><td>Quantity</td><td>Action</td></tr></thead>");
            
            int i=0;
            for(CartEntry cb : cartBean.getEntries()){
                i++;
                Book b = cb.getBook();
                out.println("<tr style=\"background-color:#" + (i%2==0 ? "fff" : "aaa") + "\"><td>" + b.getId() +"</td>" );
                out.println("<td><h4>"+b.getTitle()+"</h4><p>"+b.getDescription()+"</p></td>");
                out.println("<td><input type=\"text\" name=\"qt"+b.getId()+"\" value=\""+cb.getQuantity()+"\" /></td>");
                out.println("<td><input type=\"checkbox\" name=\"rem"+b.getId()+"\" value=\"remove\"/> Remove</td></tr>");
            }
            out.println("</table>");
            out.println("<input type=\"submit\" name=\"send\" value=\"Empty the cart\" />");
            out.println("<input type=\"submit\" name=\"send\" value=\"Update the cart\" /> </form>");
            out.println("<a href=\"CheckoutServlet\">Proceed to CHECKOUT</a><br />");
            rd = request.getRequestDispatcher("jspf/bottom.jspf");
            rd.include(request, response);  
            
        } finally {            
            out.close();
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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

    private CartBeanLocal lookupCartBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CartBeanLocal) c.lookup("java:global/11153555-ShoppingEA/11153555-ShoppingEA-ejb/CartBean!ul.roby.ea.ejb.CartBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
