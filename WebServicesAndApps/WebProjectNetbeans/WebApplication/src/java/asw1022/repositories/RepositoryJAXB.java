package asw1022.repositories;

import asw1022.db.IDB;
import asw1022.model.User;
import asw1022.db.UserDB;
import asw1022.kb.AppKB;
import asw1022.model.BasicObject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public class RepositoryJAXB<T extends BasicObject> implements IRepository<T> {

    protected JAXBContext jc;
    protected String xmlDB;
    protected String xsd;
    
    protected Class dbClass;
    protected Class itemClass;
    
    public RepositoryJAXB(String xmlDB, String xsdFile, Class dbClass, Class itemClass) throws JAXBException{
        this.jc = JAXBContext.newInstance(dbClass, itemClass);
        this.dbClass = dbClass;
        this.itemClass = itemClass;
        this.xmlDB = xmlDB;
        this.xsd = xsdFile;
    }
    
    public RepositoryJAXB(String xmlDB, Class dbClass, Class itemClass) throws JAXBException {
        this(xmlDB, null, dbClass, itemClass);
    }
    
    @Override
    public List<T> readAll() {
        Unmarshaller um;
        try {
            um = jc.createUnmarshaller();
            IDB<T> db = (IDB<T>) um.unmarshal(new File(this.xmlDB));
            return db.getItems();
        } catch (JAXBException ex) {
            Logger.getLogger(RepositoryJAXB.class.getName()).log(Level.SEVERE, "readAll failed", ex);
        }
        return new ArrayList<T>();
    }

    @Override
    public void add(T item) {
        List<T> items = readAll();
        items.add(item);
        
        Marshaller ms;
        try {
            ms = jc.createMarshaller();
            ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            if(this.xsd!=null)
                ms.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, this.xsd);

            IDB<T> db = (IDB<T>)dbClass.newInstance();
            db.setItems(items);
            File file = new File(xmlDB);
            ms.marshal(db, file);
        } catch (Exception ex) {
            Logger.getLogger(RepositoryJAXB.class.getName()).log(Level.SEVERE, "add failed", ex);
        }
    }

    @Override
    public T getByName(String name) {
        List<T> all = readAll();
        for(T elem : all){
            if(elem.getName().equals(name))
                return elem;
        }
        return null;
    }
    
}
