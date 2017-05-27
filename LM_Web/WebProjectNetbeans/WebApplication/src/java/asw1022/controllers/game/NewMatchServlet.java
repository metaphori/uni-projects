package asw1022.controllers.game;

import asw1022.controllers.BaseServlet;
import asw1022.model.dixit.Card;
import asw1022.model.dixit.GameExecution;
import asw1022.model.dixit.MatchConfiguration;
import asw1022.model.dixit.Player;
import asw1022.repositories.CardRepository;
import asw1022.repositories.IRepository;
import asw1022.repositories.MatchRepository;
import asw1022.utils.ValidationError;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
import javax.xml.bind.JAXBException;

/**
 * Servlet controller that handles the creation of a new match.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
@WebServlet(urlPatterns = "/NewMatch")
public class NewMatchServlet extends BaseServlet {

    private IRepository<Card> crepo;
    private IRepository<GameExecution> mrepo;
    private List<Card> cards;
    
    @Override public void init() throws ServletException {
        try {
            ServletContext app = getServletContext();
            String cardDB = app.getRealPath(app.getInitParameter("cardDB"));
            this.crepo = CardRepository.getInstance(cardDB);
            this.mrepo = MatchRepository.getInstance(app);
            
            // We can read all the cards during initialization
            this.cards = crepo.readAll();        
        } catch (JAXBException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Init error.", ex);
        }
    }    
    
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
        Forward(request, response, "new_match", "New match");
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
        String match = newMatchAction(request, response);
        logger.info("Match " + match + " created");
        if (match != null) {
            request.setAttribute("done", "true");
            String dest = "/Play?match=" + match;
            logger.info("Moving to dest " + dest);
            request.getRequestDispatcher(dest).forward(request, response);
            return;
        } else{
            Forward(request, response, "new_match", "New match");
        }
    }

    
    public String newMatchAction(HttpServletRequest request, HttpServletResponse response) {
        HttpSession user = request.getSession(false);
        ServletContext app = getServletContext();
        String matchname = null;
        List<ValidationError> errors = new ArrayList<ValidationError>();
        List<GameExecution> matches = mrepo.readAll();
        
        try{
            if(user==null || user.getAttribute("username")==null){ 
                // User not logged
                request.setAttribute("msg", "You must be logged in to perform this action.");
                request.getRequestDispatcher("index.jsp").forward(request, response); 
                return null;
            }
        
            // 1) Prepare data
            String username = user.getAttribute("username").toString();
            String matchTitle = request.getParameter("matchtitle");
            String numPys = request.getParameter("numplayers");
            String numPts = request.getParameter("npoints"); 
            String numCds = request.getParameter("ncardsforplayer");
            
            if(matchTitle==null || matchTitle.trim().length()==0){
                errors.add(new ValidationError("matchtitle","The title of the match must be provided."));
            }
            if(numPys==null || numPys.isEmpty()){
                errors.add(new ValidationError("numplayers","The number of players must be provided."));
            } else if(!numPys.matches("[1-9]|1[1-5]?")){
                errors.add(new ValidationError("numplayers","The number of players must be > 0 and < 16."));
            }
            if(numPts==null || numPts.isEmpty()){
                errors.add(new ValidationError("npoints","The number of points must be provided."));
            } else if(!numPts.matches("[1-9][0-9]?")){
                errors.add(new ValidationError("npoints","The number of points must be > 0 and < 99."));
            }
            if(numCds==null || numCds.isEmpty()){
                errors.add(new ValidationError("ncardsforplayer","The number of cards must be provided."));
            } else if(!numCds.matches("[1-9]")){
                errors.add(new ValidationError("ncardsforplayer","The number of cards must be > 0 and < 10."));
            }            
            
            if(errors.size()>0){
                request.setAttribute("Errors", errors);
                return null;
            }
            
            matchTitle = matchTitle.trim();
            int numplayers = Integer.parseInt(request.getParameter("numplayers"));
            int numpoints = Integer.parseInt(request.getParameter("npoints"));
            int numcards = Integer.parseInt(request.getParameter("ncardsforplayer"));
            String visibilityStr = request.getParameter("visibility");
            MatchConfiguration.MatchVisibility visibility = visibilityStr.equals("all") ?
                    MatchConfiguration.MatchVisibility.All : MatchConfiguration.MatchVisibility.SecretUrl;
            
            
            // 2) Create new match configuration
            MatchConfiguration m = new MatchConfiguration();
            m.setVisiblity(visibility);
            m.setNumPlayers(numplayers);
            m.setNumPoints(numpoints);
            m.setNumCardsForPlayers(numcards);
            
            // 3) Create new game execution
            Player player = new Player();
            player.setName(username);
            GameExecution newge = new GameExecution(m, this.cards, player);
            newge.setTitle(matchTitle);
            matchname = newge.getName();
            
            mrepo.add(newge);
            
            request.setAttribute("msg", "The match has been created successfully.");
        } catch (Exception ex) {
            Logger.getLogger("GameController#NewMatch").log(Level.SEVERE, null, ex);
            request.setAttribute("msg", "Game creation FAILED.");
            return null;
        }
        
        return matchname;        
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
