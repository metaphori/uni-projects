package asw1022.repositories;

import asw1022.model.dixit.Card;
import java.util.List;
import javax.xml.bind.JAXBException;

/**
 * Cached version of a Card repository.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public class CachedCardRepository extends CardRepositoryJAXB {

    protected List<Card> cards;
    protected boolean fresh = false;
    protected static IRepository<Card> singleton;
    
    protected CachedCardRepository(String xmlDB) throws JAXBException {
        super(xmlDB);
    }
    
    public static IRepository<Card> getInstance(String xmlDB) throws JAXBException {
        if(singleton==null){
            singleton = new CachedCardRepository(xmlDB);
        }
        return singleton;
    }
    
    @Override
    public List<Card> readAll() {
        if(this.cards!=null && this.fresh)
            return this.cards;
        List<Card> allcards = super.readAll();
        this.cards = allcards;
        this.fresh = true;
        return allcards;
    }    
    
    @Override
    public void add(Card item) {
        super.add(item);
        this.fresh = false;
    }    
    
    @Override
    public Card getByName(String name){
        List<Card> allcards;
        if(this.cards==null || !this.fresh){
            allcards = readAll();
        } else{
            allcards = this.cards;
        }
        return super.getByName(name);
    }
}
