/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ul.roby.ea.ejb;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Roberto Casadei, 11153555
 */
@Local
public interface CartBeanLocal {
    
    public boolean addToCart(int bookid, int quantity, boolean addToQuantity);
    
    public void cancel();
    
    public void checkout();
    
    public List<CartEntry> getEntries();

    void empty();
    
}
