package com.skrasek.school.pb138;


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

import org.json.simple.*;




/**
 * Importing model
 * @author Jan Skrasek <hrach.cz@gmail.com>
 */
public class ImportModel extends BaseModel {

    public ImportModel(URI uri) throws SAXException, ParserConfigurationException, IOException
    {
        super(uri);
    }

    public void importData()
    {
        String json = parseData();
        
        JSONObject jsonObj  = (JSONObject) JSONValue.parse(json);
        JSONObject trends = (JSONObject) jsonObj.get("trends");

        for (Object key : trends.keySet()) {
            String entryKey = key.toString();
            String date = DateUtils.fromJsonToString(entryKey);
            Element node;

            try {
                XPathExpression expr = getxPath().compile("//trendsgroup[@date='" + date + "'][1]");
                node = (Element) expr.evaluate(this.doc, XPathConstants.NODE);
            } catch (XPathExpressionException ex) {
                return;
            }

            if (node == null) {
                node = doc.createElement("trendsgroup");
                node.setAttribute("date", date);
                doc.getDocumentElement().appendChild(node);
            }

            JSONArray jsonArray = (JSONArray) trends.get(entryKey);
            Iterator i = jsonArray.iterator();
            while (i.hasNext()) {
                JSONObject t = (JSONObject) i.next();
                Element trend = doc.createElement("trend");
                trend.setAttribute("name", (String) t.get("name"));
                trend.setAttribute("query", (String) t.get("query"));
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
            while ((s = dis.readLine()) != null) {
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
