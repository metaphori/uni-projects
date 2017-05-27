package asw1022.model.dixit;

import asw1022.model.BasicObject;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * It represents a card in Dixit.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Card extends BasicObject {
    protected String title;
    protected String url;
    
    public void setTitle(String title) { this.title = title; }
    public String getTitle(){ return this.title; }
    
    public void setUrl(String url){ this.url = url; }
    public String getUrl(){ return this.url; }
    
}
