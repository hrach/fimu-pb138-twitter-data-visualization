package com.skrasek.school.pb138;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.*;
import java.io.*;
import java.net.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


/**
 * Importing model
 * @author Jan Skrasek <hrach.cz@gmail.com>
 */
public class ImportModel extends BaseModel {

    protected Document doc;
    protected XPathFactory xpathFactory;

    public ImportModel(URI uri) throws SAXException, ParserConfigurationException, IOException
    {
        super(uri);
    }

    public void importData() throws XPathExpressionException
    {
        String json = parseData();
        Gson gson = new Gson();
        
        Type type = new TypeToken<Map<String, ImportModelTrends>>() {}.getType();
        Map<String, ImportModelTrends> trends = gson.fromJson(json, type);
        
        for (Map.Entry<String, ImportModelTrends> entry : trends.entrySet()) {
            
            String date = "";
            XPathExpression expr = getxPath().compile("//trendsgroup[@date='" + date + "'][0]");
            Element node = (Element) expr.evaluate(doc, XPathConstants.NODE);

            if (node == null) {
                NodeList rootList = doc.getElementsByTagName("trends");
                Element root = (Element) rootList.item(0);
                
                
                node = doc.createElement("trendsgroup");
                node.setAttribute("date", date);
                root.appendChild(node);
            }

            for (ImportModelTrend t : entry.getValue().getTrends()) {
                Element trend = doc.createElement("trend");
                trend.setAttribute("name", t.getName());
                trend.setAttribute("query", t.getQuery());
                node.appendChild(trend);
            }

        }

        saveDb();
    }

    private String parseData()
    {
        URL u;
        InputStream is = null;
        DataInputStream dis;
        String s, res;

        res = "";
        try {
            u = new URL("https://api.twitter.com/1/trends/daily.json");

            is = u.openStream();         // throws an IOException
            dis = new DataInputStream(new BufferedInputStream(is));
            while ((s = dis.readUTF()) != null) {
                res += s;
            }

        } catch (MalformedURLException mue) {
            return "";

        } catch (IOException ioe) {
            return "";

        } finally {
            try {
                is.close();
            } catch (IOException ioe) {
            }

        }

        return res;
    }

}
