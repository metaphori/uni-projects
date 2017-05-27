/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ul.roby.ea.ejb;

import java.util.List;
import javax.ejb.Local;
import ul.roby.ea.entities.Book;

/**
 *
 * @author Roberto Casadei, 11153555
 */
@Local
public interface BookBeanLocal {

    List<Book> findAll();

    List<Book> findByTitle(String title);

    List<Book> findByDescription(String desc);

    List<Book> findByKeywords(String key);

    Book findById(int id);
    
    void populate();

    boolean isEmpty();
}
