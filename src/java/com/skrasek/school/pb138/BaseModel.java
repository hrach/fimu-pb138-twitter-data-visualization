package com.skrasek.school.pb138;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;



/**
 * Base model working with DB
 * @author Jan Skrasek <hrach.cz@gmail.com>
 */
public abstract class BaseModel {

    protected Document doc;
    protected XPathFactory xpathFactory;
    protected URI fileName;

    public BaseModel(URI uri) throws SAXException, ParserConfigurationException, IOException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(uri.toString());
        xpathFactory = XPathFactory.newInstance();
        fileName = uri;
    }

    protected XPath getxPath()
    {
        return xpathFactory.newXPath();
    }

    protected boolean saveDb()
    {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(fileName.getRawPath()));
            transformer.transform(source, result);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
