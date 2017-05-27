package asw1022.util.functional;

/**
 * It represents a predicate on objects of type T.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public interface IPredicate<T> {
    
    /**
     * Predicate  function.
     * @param obj The obj to be tested.
     * @return the result of the application of the predicate to obj.
     */
    public boolean test(T obj);
    
}
