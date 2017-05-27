package asw1022.db;

import asw1022.model.User;
import asw1022.model.dixit.Card;
import asw1022.model.dixit.MatchConfiguration;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The database for the cards of the game.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
@XmlRootElement(name="cards")
@XmlAccessorType (XmlAccessType.FIELD)
public class CardDB implements IDB<Card> {
    
    @XmlElement(name="card")
    protected List<Card> cards;
    
    public List<Card> getItems(){ return cards==null ? new ArrayList<Card>() : cards; }
    public void setItems(List<Card> cards){ this.cards = cards; }
    
}
