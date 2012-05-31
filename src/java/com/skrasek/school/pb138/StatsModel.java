/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skrasek.school.pb138;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.net.URI;


/**
 * @author Jan Skrasek <hrach.cz@gmail.com>
 */
public class StatsModel
{

    private Document doc;

    private XPathFactory xpathFactory;
    
    public StatsModel(URI uri) throws SAXException, ParserConfigurationException, IOException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(uri.toString());

        xpathFactory = XPathFactory.newInstance();
    }

    XPath getxPath()
    {
        return xpathFactory.newXPath();
    }

    public void getStats(Date start, Date end)
    {
        
    }

    public NodeList findData(Date start, Date end) throws XPathExpressionException
    {
        // xpath 2 approach
        // /doc/event[xs:date(@date) le xs:date('2011-08-31') and xs:date(@date) ge xs:date('2011-08-01')]

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmm");

        XPathExpression expr = getxPath().compile("//trendsgroup[" +
            "@date >= " + sdf.format(start) + " and @date <= " + sdf.format(end) +
            "]/trend");

        return (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
    }

}
