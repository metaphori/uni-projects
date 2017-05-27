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
 * Servlet controller that handles the registration of a new user.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
@WebServlet(urlPatterns = "/Register")
public class RegisterServlet extends BaseServlet {

    public static final int USERNAME_MIN_LENGTH = 4;
    public static final int PASSWORD_MIN_LENGTH = 4;    
    
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
        Forward(request, response, "register", "New user");
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
        boolean ok = registerAction(request, response);
        if(ok) {
            Forward(request, response, "home", "Home Page");
        } else{
            Forward(request, response, "register", "New user");
        }
    }
    
    public boolean registerAction(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        // 1) Prepare data
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        List<ValidationError> errors = new ArrayList<ValidationError>();
        
        // 2) Validate data
        if(username==null){
            errors.add(new ValidationError("username","Username must be provided."));
        } else {
            if(username.trim().length()==0)
                errors.add(new ValidationError("username", "Username must be provided."));
            else if(username.trim().length()<USERNAME_MIN_LENGTH)
                errors.add(new ValidationError("username", "Username must be at least of size " + USERNAME_MIN_LENGTH + "."));
        }
        if(password==null){
            errors.add(new ValidationError("password", "Password must be provided."));
        } else{
            if(password.trim().length()==0)
                errors.add(new ValidationError("password", "Password must be provided."));            
            else if(password.trim().length()<PASSWORD_MIN_LENGTH)
                errors.add(new ValidationError("password", "Password must be at least of size " + PASSWORD_MIN_LENGTH + "."));
        }
        if(password2==null || password2.trim().length()==0){
            errors.add(new ValidationError("password2", "Password must be repeated."));
        }
        if(password!=null && password2!=null && !password.equals(password2)){
            errors.add(new ValidationError("password2", "The two passwords are not the same."));
        }
        
        if(errors.size()>0){
            request.setAttribute("Errors", errors);
            return false;
        }
                
        // 3) Transform/normalize data
        username = username.trim().toLowerCase();
        password = SecurityUtils.SHA(password.trim());
        
        // 4) Check availability and add user
        ServletContext app = getServletContext();
        String userDB = app.getRealPath(app.getInitParameter("userDB"));
        try {
            IRepository<User> repo = new UserRepository(userDB);
            UserManager umng = new UserManager(repo);
            
            // A) Check if a user with the same name exists
            User user = umng.findByUsername(username);
            if(user!=null){
                errors.add(new ValidationError("username", "The chosen username already exists."));
                request.setAttribute("Errors", errors);
                return false;
            }
            
            // B) Add the user to the repository
            umng.addUser(username, password);
            request.setAttribute("msg", "Registration has been performed successfully.");
            return true;
        } catch (Exception ex) {
            Logger.getLogger("UserController#registerAction").log(Level.SEVERE, null, ex);
            request.setAttribute("msg", "Registration FAILED.");
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
