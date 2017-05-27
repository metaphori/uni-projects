package asw1022.model.dixit;

/**
 * It represents an exception related to the game (e.g., a bad action).
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public class GameException extends Exception {
    
    public GameException(String msg){
        super(msg);
    }
    
}
