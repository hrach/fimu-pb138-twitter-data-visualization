package com.skrasek.school.pb138;

import java.io.StringWriter;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Source;
import javax.xml.transform.Result;

/**
 *
 * @author tomasbobek
 */
public class XMLRender {
    
    private Set<String> trends;
    private String dateFrom;
    private String dateTo;
    
    public XMLRender(Set<String> trends, String from, String to) {
        this.dateFrom = from;
        this.dateTo = to;
        this.trends = new HashSet<String>();
        this.trends.addAll(trends);
    }
    
    public String createRender() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("render");
            doc.appendChild(rootElement);

            Element inputs = doc.createElement("inputs");
            rootElement.appendChild(inputs);
            
            Element input;
            Attr attr;
            input = doc.createElement("input");
            input.appendChild(doc.createTextNode(this.dateFrom));
            inputs.appendChild(input);
            attr = doc.createAttribute("name");
            attr.setValue("from");
            input.setAttributeNode(attr);
            input = doc.createElement("input");
            input.appendChild(doc.createTextNode(this.dateTo));
            inputs.appendChild(input);
            attr = doc.createAttribute("name");
            attr.setValue("to");
            input.setAttributeNode(attr);
            
            Element rtrends = doc.createElement("trends");
            rootElement.appendChild(rtrends);
            attr = doc.createAttribute("from");
            attr.setValue(this.dateFrom);
            rtrends.setAttributeNode(attr);
            attr = doc.createAttribute("to");
            attr.setValue(this.dateTo);
            rtrends.setAttributeNode(attr);
            
            for(String s : this.trends) {
                Element trend;
                trend = doc.createElement("trend");
                trend.appendChild(doc.createTextNode(s));
                rtrends.appendChild(trend);
            }
            
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            StringWriter writer = new StringWriter();
            Result result = new StreamResult(writer);
            Source source = new DOMSource(doc);
            transformer.transform(source, result);
            writer.close();
            String xml = writer.toString();
            return xml;
            
        } catch (ParserConfigurationException e) {
            return null;
	} catch (TransformerException e) {
            return null;
	} catch (IOException e) {
            return null;
        }
        
    }
    
    @Override
    public String toString() {
        return this.createRender();
    }
    
}
