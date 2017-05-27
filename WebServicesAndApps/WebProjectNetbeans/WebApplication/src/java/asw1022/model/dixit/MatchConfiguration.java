package asw1022.model.dixit;

import asw1022.model.BasicObject;
import java.util.HashMap;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * A match configuration.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MatchConfiguration extends BasicObject {
    protected int numPlayers;
    protected int numPoints;
    protected int numCardsForPlayers;
    
    protected MatchVisibility visibility;
    
    public enum MatchVisibility {
        All, SecretUrl
    }
    
    public void setVisiblity(MatchVisibility visibility){ this.visibility = visibility; }
    public MatchVisibility getVisibility(){ return this.visibility; }

    public int getNumPlayers() { return this.numPlayers; }
    public void setNumPlayers(int numPlayers) { this.numPlayers = numPlayers; }    

    public int getNumPoints() { return this.numPoints; }
    public void setNumPoints(int numPoints) { this.numPoints = numPoints; }     
    
    public int getNumCardsForPlayers(){ return this.numCardsForPlayers; }
    public void setNumCardsForPlayers(int numCardsForPlayers){ this.numCardsForPlayers = numCardsForPlayers; }
    
}
