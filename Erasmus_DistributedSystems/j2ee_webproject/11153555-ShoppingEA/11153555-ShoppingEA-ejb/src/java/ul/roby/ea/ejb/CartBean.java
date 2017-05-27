/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ul.roby.ea.ejb;

import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import ul.roby.ea.entities.Book;

/**
 *
 * @author Roberto Casadei, 11153555
 */
@Stateful
public class CartBean implements CartBeanLocal {
    @javax.ejb.EJB
    BookBeanLocal books;
    
    ArrayList<CartEntry> entries;
    
    @PostConstruct
    public void init(){
        entries = new ArrayList<CartEntry>();
    }
    
    @Override
    public boolean addToCart(int bookId, int quantity, boolean addToQuantity) {
        
        Book b = books.findById(bookId);
        
        if(b==null){ // book not found
            return false;
        }
        else { // book found => quantity check
            if( b.getQuantity()<0 || b.getQuantity()<quantity ) 
                return false;
        }
        
        // check if the book is already in the cart
        boolean found = false;
        CartEntry toremove = null;
        for(CartEntry entry : entries){
            if( entry.getBook().equals(b) ){
                // found ==> update quantity
                int newquantity = addToQuantity ? entry.getQuantity()+quantity : quantity;
                if ( entry.getBook().getQuantity() < newquantity)
                    return false;
                if(newquantity==0)
                    toremove = entry;
                else
                    entry.setQuantity( newquantity );
                found = true;
                break;
            }
        }
        
        if(toremove!=null)
            entries.remove(toremove);
        
        // not already in the cart, let's add it
        if(!found && quantity>0){
            
            CartEntry ce = new CartEntry(b, quantity);
            entries.add(ce);
        }
        
        return true;
    }

    
    
    @Override
    @Remove
    public void cancel(){
        
    }
    
    @Override
    @Remove
    public void checkout(){
        
    }
    
    @Override
    public List<CartEntry> getEntries(){
        return entries;
    }

    @Override
    public void empty() {
        entries.clear();
    }
    
}
