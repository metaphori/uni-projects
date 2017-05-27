package asw1022.util.xml;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Utility class to manage XML Document (DOM).
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public class ManageXML {

    private Transformer transformer;
    private DocumentBuilder builder;
    
    public ManageXML() throws TransformerConfigurationException, ParserConfigurationException {
        transformer = TransformerFactory.newInstance().newTransformer();
        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();        
    }

    /**
     * Builds a new XML DOM document.
     * @param rootTag The name of the root tag.
     * @return a new XML DOM document.
     */
    public Document newDocument(String rootTag) {
        Document newDoc = builder.newDocument(); 
        newDoc.appendChild(newDoc.createElement(rootTag));
        return newDoc;
    }

    public void transform(OutputStream out, Document document) throws TransformerException, IOException {
        transformer.transform(new DOMSource(document), new StreamResult(out));
    }

    /**
     * Parses an XML document from an input stream.
     * @param in The input stream.
     * @return the parsed XML DOM document.
     * @throws IOException
     * @throws SAXException 
     */
    public Document parse(InputStream in) throws IOException, SAXException {
        return builder.parse(in);
    }
}
