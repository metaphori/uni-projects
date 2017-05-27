package asw1022.utils;

/**
 * Structure for a validation error.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public class ValidationError {
    
    public final String fieldName;
    public final String errorMsg;
    
    public ValidationError(String fieldName, String errorMsg){
        this.fieldName = fieldName;
        this.errorMsg = errorMsg;
    }
    
}
