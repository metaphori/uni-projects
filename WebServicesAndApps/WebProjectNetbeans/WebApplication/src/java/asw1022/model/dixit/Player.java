package asw1022.model.dixit;

import asw1022.model.BasicObject;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * It represents a  player in Dixit.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Player extends BasicObject {
    
    protected List<Card> cards;
    
    public void setcards(List<Card> cards) { this.cards = cards; }
    public List<Card> getCards(){ return this.cards; }    
    
}
