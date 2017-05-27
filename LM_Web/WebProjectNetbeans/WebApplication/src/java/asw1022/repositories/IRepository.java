package asw1022.repositories;

import java.util.List;

/**
 *
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public interface IRepository<T> {
    
    List<T> readAll();
    
    T getByName(String name);
    
    void add(T m);
    
}
