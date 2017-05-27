/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ul.roby.ea.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ul.roby.ea.entities.Book;

/**
 *
 * @author Roberto Casadei, 11153555
 */
@Stateless
public class BookBean implements BookBeanLocal {
    @PersistenceContext(unitName = "11153555-ShoppingEA-ejbPU")
    private EntityManager em;

    @Override
    public List<Book> findAll() {
        return em.createNamedQuery("Book.findAll").getResultList();
    }

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return em.createNamedQuery("Book.findByTitle").setParameter("title", "%"+title+"%").getResultList();
    }

    @Override
    public List<Book> findByDescription(String desc) {
        return em.createNamedQuery("Book.findByDescription").setParameter("description", "%"+desc+"%").getResultList();
    }

    @Override
    public List<Book> findByKeywords(String key) {
        return em.createNamedQuery("Book.findByKeywords").setParameter("keywords", "%"+key+"%").getResultList();
    }

    @Override
    public Book findById(int id) {
        try{
        Object b = em.createNamedQuery("Book.findById").setParameter("id", id).getSingleResult();
            if(b!=null)
                return (Book)b;
            else
                return null;
        } catch(Exception exc){
            return null;
        }
    }
    
    @Override
    public void populate() {
        
        Book b1 = new Book(1);
        b1.setTitle("Tuscany SCA in Action");
        b1.setDescription("This book provides an introduction to the Service Component Architecture and to the framework Apache Tuscany.");
        b1.setKeywords("sca,service,component,architecture,apache,tuscany");
        b1.setQuantity(2);
        
        Book b2 = new Book(2);
        b2.setTitle("The Rails 3 Way");
        b2.setDescription("An in-deep opinionated guide that explores the features of the Rails framework version 3.");
        b2.setKeywords("rails,ruby,ror,mvc,framework");
        b2.setQuantity(3);
        
        Book b3 = new Book(3);
        b3.setTitle("Programming Groovy");
        b3.setDescription("Venkat Subramanian tells us how to improve the productivity on the Java platform by using the Groovy language.");
        b3.setKeywords("groovy,java,metaprogramming,dynamic,language");
        b3.setQuantity(5);
        
        Book b4 = new Book(4);
        b4.setTitle("Designing Interactive Systems: People, Activities, Contexts, and Technologies");
        b4.setDescription("This book is about HCI (Human-Computer Interaction) and provides a framework for the design and evaluation of interactive systems");
        b4.setKeywords("hci,design,interaction");
        b4.setQuantity(1);
        
        Book b5 = new Book(5);
        b5.setTitle("Design Patterns");
        b5.setDescription("This bestselling book by the Gang of Four proposes several design patterns for the construction of reusable software.");
        b5.setKeywords("design pattern,singleton,abstract factory,prototype,pattern,");
        b5.setQuantity(1);
        
        Book b6 = new Book(6);
        b6.setTitle("Real World Haskell");
        b6.setDescription("A real-world guide to Haskell and functional programming");
        b6.setKeywords("haskell,functional,ghc");
        
        Book b7 = new Book(7);
        b7.setTitle("Essential Skills for the Agile Developer");
        b7.setDescription("Programming tips and techniques that can be really valuable in agile software development.");
        b7.setKeywords("agile,programming,coding");
        
        Book b8 = new Book(8);
        b8.setTitle("Spring in Action");
        b8.setDescription("Published by Manning (in the Action Series), this books introduces the Spring framework, a lightweight solution for developing enterprise Java applications.");
        b8.setKeywords("spring,framework,java,enterprise");
        
        Book b9 = new Book(9);
        b9.setTitle("Eloquent Ruby");
        b9.setDescription("Ruby allows for express ideas in a concise and powerful manner. This book helps..");
        b9.setKeywords("ruby,dsl,language,metaprogramming");
        
        Book b10 = new Book(10);
        b10.setTitle("OSGI in Depth");
        b10.setDescription("This book sets out from where \"OSGI in Action\" left.");
        b10.setKeywords("osgi,modular,java,component,bundle");
        b10.setQuantity(1);
        
        persist(b1);
        persist(b2);
        persist(b3);
        persist(b4);
        persist(b5);
        persist(b6);
        persist(b7);
        persist(b8);
        persist(b9);
        persist(b10);
    }
    
    @Override
    public boolean isEmpty(){
          return em.createNamedQuery("Book.findAll").getResultList().isEmpty();
    }
    
}
