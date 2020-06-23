import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Walk an XML folder structure
 *
 */
public class Folders {
    public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document doc = builder.parse(is);

        Collection<String> folderNames = new ArrayList<>();
        if (doc.hasChildNodes()) {
            folderNames = getFolderNames(doc.getChildNodes(), startingLetter);
        }

        return folderNames;
    }

    private static Collection<String> getFolderNames(NodeList childNodes, char startingLetter) {
        Collection<String> foundFolderNames = new ArrayList();
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node tempNode = childNodes.item(count);
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) tempNode;
                if (element.getAttribute("name").startsWith(Character.toString(startingLetter))) {
                    foundFolderNames.add(element.getAttribute("name"));
                }
                if (tempNode.hasChildNodes()) {
                    foundFolderNames.addAll(getFolderNames(tempNode.getChildNodes(), startingLetter));
                }
            }
        }
        return foundFolderNames;
    }

    public static void main(String[] args) throws Exception {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                        "<folder name=\"c\">" +
                        "<folder name=\"program files\">" +
                        "<folder name=\"uninstall information\" />" +
                        "</folder>" +
                        "<folder name=\"users\" />" +
                        "</folder>";

        Collection<String> names = folderNames(xml, 'u');
        for(String name: names)
            System.out.println(name);
    }
}