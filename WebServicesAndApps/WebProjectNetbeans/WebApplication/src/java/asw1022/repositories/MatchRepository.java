package asw1022.repositories;

import asw1022.model.dixit.Card;
import asw1022.model.dixit.GameExecution;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBException;

/**
 * The repository of the matches.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public class MatchRepository implements IRepository<GameExecution> {
    
    protected ServletContext ctx;
    
    protected static IRepository<GameExecution> singleton;
    
    protected MatchRepository(ServletContext ctx){
        this.ctx = ctx;
    }
    
    public static IRepository<GameExecution> getInstance(ServletContext ctx) {
        if(singleton==null){
            singleton = new MatchRepository(ctx);
        }
        return singleton;
    }    
    
    @Override
    public List<GameExecution> readAll() {
        List<GameExecution> matches = 
                (List<GameExecution>) this.ctx.getAttribute("Matches");
        if(matches==null)
            return new ArrayList<GameExecution>();
        return new ArrayList<GameExecution>(matches);
    }

    @Override
    public synchronized GameExecution getByName(String name) {
        List<GameExecution> matches = readAll();
        for(GameExecution match : matches){
            if(match.getName().equals(name))
                return match;
        }
        return null;
    }

    @Override
    public synchronized void add(GameExecution m) {
        List<GameExecution> matches = this.readAll();
        matches.add(m);
        this.ctx.setAttribute("Matches", matches);
    }
    
}
