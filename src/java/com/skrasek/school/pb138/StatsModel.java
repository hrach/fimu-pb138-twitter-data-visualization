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
import java.util.*;


/**
 * Return counted stats for trends
 * @author Jan Skrasek <hrach.cz@gmail.com>
 */
public class StatsModel extends BaseModel
{

    public StatsModel(URI uri) throws SAXException, ParserConfigurationException, IOException
    {
        super(uri);
    }

    public Map<String,Trend> getStats(Date start, Date end) throws XPathExpressionException
    {
        Map<String,Trend> trends = new HashMap<String,Trend>();

        NodeList nodes = findData(start, end);
        for (int i = 0; i < nodes.getLength(); i++) {
            Element node = (Element) nodes.item(i);

            String date    = ((Element) node.getParentNode()).getAttribute("date");
            String hashTag = node.getAttribute("name");
            String query   = node.getAttribute("query");

            if (!trends.containsKey(hashTag)) {
                trends.put(hashTag, new Trend(hashTag, query));
            }

            Trend trend = trends.get(hashTag);
            trend.addTermin(date);
        }

        return trends;
    }

    public NodeList findData(Date start, Date end) throws XPathExpressionException
    {
        // xpath 2 approach
        // /doc/event[xs:date(@date) le xs:date('2011-08-31') and xs:date(@date) ge xs:date('2011-08-01')]

        String query = "//trendsgroup[@date >= " + DateUtils.fromDateToString(start) + " and @date <= " + DateUtils.fromDateToString(end) + "]/trend";
        XPathExpression expr = getxPath().compile(query);

        return (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
    }

}
