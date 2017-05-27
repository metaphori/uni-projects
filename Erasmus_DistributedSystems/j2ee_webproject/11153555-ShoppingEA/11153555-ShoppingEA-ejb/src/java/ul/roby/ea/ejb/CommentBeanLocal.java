/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ul.roby.ea.ejb;

import java.util.List;
import javax.ejb.Local;
import ul.roby.ea.entities.Comment;

/**
 *
 * @author Roberto Casadei, 11153555
 */
@Local
public interface CommentBeanLocal {

    List<Comment> findAllByBookId(int id);

    void addComment(int bookid, String author, String content);
    
}
