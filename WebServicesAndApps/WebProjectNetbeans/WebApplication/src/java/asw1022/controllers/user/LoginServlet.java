package asw1022.controllers.user;

import asw1022.controllers.BaseServlet;
import asw1022.managers.UserManager;
import asw1022.model.User;
import asw1022.repositories.IRepository;
import asw1022.repositories.UserRepository;
import asw1022.util.security.SecurityUtils;
import asw1022.utils.ValidationError;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet controller that handles the login procedure.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
@WebServlet(urlPatterns = "/Login")
public class LoginServlet extends BaseServlet {
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Forward(request, response, "login", "Login");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean ok = loginAction(request, response);
        if (ok) {
            // Login ok
            Forward(request, response, "home", "Home Page");
        } else{
            // Error
            Forward(request, response, "login", "Login");
        }
    }
    
    public boolean loginAction(HttpServletRequest request, HttpServletResponse res) throws ServletException {
        // 1) Prepare data
        HttpSession session = request.getSession();
        List<ValidationError> errors = new ArrayList<ValidationError>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // 2) Validate presence
        if(username==null || username.trim().length()==0){
            errors.add(new ValidationError("username", "Username must be provided."));
        }
        if(password==null || password.trim().length()==0){
            errors.add(new ValidationError("password", "Password must be provided"));
        }
        
        if(errors.size()>0){
            request.setAttribute("Errors", errors);
            return false;
        }
        
        // 3) Transform/normalize data
        username = username.trim().toLowerCase();
        password = SecurityUtils.SHA(password.trim());
        
        // 4) Check if the user/pass pair exists
        ServletContext app = getServletContext();
        String userDB = app.getRealPath(app.getInitParameter("userDB"));
        boolean found = false;
        try {
            IRepository<User> repo = new UserRepository(userDB);
            UserManager umng = new UserManager(repo);
            User userFound = umng.findByUsernameAndPassword(username,password);
            if(userFound!=null)
                found = true;
        } catch (Exception ex) {
            Logger.getLogger("UserController#loginAction").log(Level.SEVERE, 
                    "Due to an exception, we cannot verify if login was successful.", ex);
        }
        
        if(found){
            request.setAttribute("msg", "Login has been performed successfully.");
            session.setAttribute("username", username);
            return true;
        }
        else{
            errors.add(new ValidationError(null, "The provided credentials have no match. Login failed."));
            request.setAttribute("Errors", errors);
            return false;
        }        
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
