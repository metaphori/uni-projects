/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ul.roby.ea.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import ul.roby.ea.entities.Comment;

/**
 *
 * @author Roberto Casadei, 11153555
 */
@Stateless
public class CommentBean implements CommentBeanLocal {
    @PersistenceContext(unitName = "11153555-ShoppingEA-ejbPU")
    private EntityManager em;

    @Override
    public List<Comment> findAllByBookId(int id) {
        return em.createNamedQuery("Comment.findByBookid").setParameter("bookid", id).getResultList();
    }
    
    
    
    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public void addComment(int bookid, String author, String content) {
        int lastid;
        try{
            lastid = Integer.parseInt(em.createNamedQuery("Comment.findMaxId").getSingleResult().toString());
        } catch(NoResultException exc){
            System.out.println("****" + exc.getMessage());
            lastid = 0;
        }    
        
        Comment c = new Comment( lastid+1 );
        c.setAuthor(author);
        c.setContent(content);
        c.setBookid(bookid);
        
        try{
            persist(c);
        } catch(Exception exc){
            System.out.println("****" + exc.getMessage() + " ["+author+" ---- " + content + " ----- " +bookid+"]");
        }
    }

    
    
}
