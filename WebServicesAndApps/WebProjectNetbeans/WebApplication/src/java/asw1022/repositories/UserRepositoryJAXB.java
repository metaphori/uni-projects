package asw1022.repositories;

import asw1022.db.UserDB;
import asw1022.kb.AppKB;
import asw1022.model.User;
import javax.xml.bind.JAXBException;

/**
 * JAXB-based implementation of a User repository.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public class UserRepositoryJAXB extends RepositoryJAXB<User> {
   
    public UserRepositoryJAXB(String xmlDB) throws JAXBException{
        super(xmlDB, AppKB.XML_SCHEMA_DIR+"db/user_db.xsd", UserDB.class, User.class);
    }    
    
}
