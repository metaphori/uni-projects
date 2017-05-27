package asw1022.repositories;

import asw1022.model.User;
import asw1022.util.xml.DOMUtils;
import asw1022.util.xml.ManageXML;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * User repository implemented using DOM parsing.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public class UserRepositoryDOM implements IRepository<User> {

    protected String db;
    protected ManageXML xmlManager;
    
    public UserRepositoryDOM(String db){
        this.db = db;
        try{
            this.xmlManager = new ManageXML();
        } catch(Exception exc){
            // TODO: handle error
        }
    }
    
    @Override
    public List<User> readAll() {
        List<User> res = new ArrayList<User>();
        try {
            Document doc = this.xmlManager.parse(new FileInputStream(this.db));
            Element root = doc.getDocumentElement();
            if(root.getTagName().equals("users")){
                List<Element> userElems = DOMUtils.getChildrenElementsByName(root, "user");
                for(Element el : userElems){
                    String username = DOMUtils.getFirstChildElementTextContentByTagName(el, "name");
                    String password = DOMUtils.getFirstChildElementTextContentByTagName(el, "password");
                    if(username!=null && password!=null){
                        User user = new User();
                        user.setName(username);
                        user.setPassword(password);
                        res.add(user);
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UserRepositoryDOM.class.getName()).log(Level.SEVERE, 
                    "Cannot read users. " + ex.getMessage(), ex);
        }
        return res;
    }

    @Override
    public User getByName(String name) {
        List<User> allusers = this.readAll();
        for(User us : allusers){
            if(us.getName().equals(name))
                return us;
        }
        return null;
    }

    @Override
    public void add(User m) {
        List<User> users = this.readAll();
        users.add(m);
        
        Document doc = xmlManager.newDocument("users");
        Element root = doc.getDocumentElement();
        for(User us : users){
            Element usElem = doc.createElement("user");
            
            Element usNameElem = doc.createElement("name");
            Element usPswElem = doc.createElement("password");
            usNameElem.setTextContent(us.getName());
            usPswElem.setTextContent(us.getPassword());
            
            usElem.appendChild(usNameElem);
            usElem.appendChild(usPswElem);
            
            root.appendChild(usElem);
        }
        try{
            xmlManager.transform(new FileOutputStream(this.db), doc);
        } catch(Exception exc){
            // TODO: handle exception or log
        }
    }
    
    
    
}
