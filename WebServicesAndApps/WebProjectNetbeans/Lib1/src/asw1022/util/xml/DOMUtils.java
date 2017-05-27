package asw1022.util.xml;

import org.w3c.dom.*;
import java.util.*;

/**
 * This class provides static utility methods for DOM manipulation.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public class DOMUtils {
    
    public static List<Element> getChildrenElementsByName(Element elem, String name){
        List<Element> elems = new ArrayList<Element>();
        NodeList nlist = elem.getElementsByTagName(name);
        if (nlist != null) {
            for (int i = 0; i < nlist.getLength(); i++) {
                Node node = nlist.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    elems.add((Element)node);
                }
            }
        }
        return elems;
    }
    
    public static String getFirstChildElementTextContentByTagName(Element elem, String name){
        List<Element> children = getChildrenElementsByName(elem, name);
        if(children.isEmpty())
            return null;
        return children.get(0).getTextContent();
    }
    
}
