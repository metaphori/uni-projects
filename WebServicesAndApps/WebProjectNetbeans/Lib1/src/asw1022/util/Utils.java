package asw1022.util;

import java.util.*;

/**
 * Generic utility class.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public class Utils {
    
    /**
     * Shuffles a collection.
     * @param <T> The type of the elements in the collection.
     * @param coll The collection of elements to be shuffled.
     * @return a list with the elements of coll shuffled.
     */
    public static <T> List<T> Shuffle(Collection<T> coll){
        List<T> result = new LinkedList<>();
        List<T> input = new ArrayList<>(coll);
        Random rand = new Random();
        for(int k=input.size(); k>0; k--){
           T elem = input.remove(rand.nextInt(k));
           result.add(elem);
        }
        return result;
    }
    
}
