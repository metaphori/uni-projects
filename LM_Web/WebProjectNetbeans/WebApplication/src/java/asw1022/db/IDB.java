package asw1022.db;

import java.util.List;

/**
 * Generic interface for a database of items of type T.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public interface IDB<T> {
    
    List<T> getItems();
    
    void setItems(List<T> items);
    
}
