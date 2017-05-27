package asw1022.db;

import asw1022.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The database for the website users.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
@XmlRootElement(name="users")
@XmlAccessorType (XmlAccessType.FIELD)
public class UserDB implements IDB<User> {
    
    @XmlElement(name="user")
    protected List<User> users;
    
    public List<User> getItems(){ return users==null? new ArrayList<User>() : users; }
    public void setItems(List<User> users){ this.users = users; }
    
}
