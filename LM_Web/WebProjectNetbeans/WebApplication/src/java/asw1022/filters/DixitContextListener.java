package asw1022.filters;

import asw1022.model.dixit.GameExecution;
import asw1022.repositories.CardRepository;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.xml.bind.JAXBException;

/**
 * Web application lifecycle listener.
 *
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public class DixitContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext app = sce.getServletContext();
        /*
        Object obj = app.getAttribute("Matches");
        if (obj == null) {
            // TODO: Look for persisted matches
            
            HashMap<String, GameExecution> matches = new HashMap<String, GameExecution>();
            app.setAttribute("Matches", matches);
        }
        */
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /*
        HashMap<String, GameExecution> matches = 
                (HashMap<String, GameExecution>) sce.getServletContext().getAttribute("Matches");

        // TODO: Persist matches
        */
    }
}
