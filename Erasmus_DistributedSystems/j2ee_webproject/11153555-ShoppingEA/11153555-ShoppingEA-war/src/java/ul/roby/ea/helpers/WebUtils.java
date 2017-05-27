/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ul.roby.ea.helpers;

import java.util.regex.Pattern;

/**
 *
 * @author Roberto Casadei, 11153555
 */
public class WebUtils {
    
    // the number of digits must be able to stay in one int
    public final static Pattern numcheck = Pattern.compile("[0-9]{1,8}");
    
}
