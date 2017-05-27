/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package asw1022.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Base class for application servlets.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public class BaseServlet extends HttpServlet {
    
    protected Logger logger = Logger.getLogger(this.getClass().getName());
    
    /**
     * Performs JSP forwarding.
     * @param request
     * @param response
     * @param jspName
     * @param pageTitle 
     * @throws ServletException 
     */
    public void Forward(HttpServletRequest request, 
            HttpServletResponse response,
            String jspName,
            String pageTitle) throws ServletException {
        request.setAttribute("PageTitle", pageTitle);
        String url = "/WEB-INF/jsp/" + jspName + ".jsp";

        try {
            logger.info("Dispatching to url " + url);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            logger.severe("Eccezione: " + ex.getMessage());
        }        
    }
    
    public void Forward(HttpServletRequest request, 
            HttpServletResponse response,
            String jspName) throws ServletException {
        Forward(request, response, jspName, null);
    }       
    
}
