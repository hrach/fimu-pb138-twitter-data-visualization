package com.skrasek.school.pb138;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.util.Calendar;
import java.util.Date;
import javax.xml.xpath.XPathExpressionException;



/**
 *
 * @author tomasbobek
 */
public class Controller {

    private URI dbFileName;

    public Controller() throws IOException, URISyntaxException {
        dbFileName = Controller.class.getResource("db.xml").toURI();
        if (dbFileName == null) {
            throw new IOException("Nelze nalezt testovaci XSD soubor");
        }
    }
    
    public Set<String> getTrends(String from, String to) throws SAXException, ParserConfigurationException, IOException, XPathExpressionException {
        StatsModel statsModel = new  StatsModel(dbFileName);
        Map<String,Trend> stats = statsModel.getStats(getDate(from), getDate(to));
        return stats.keySet();
    }
    
    public void reloadData() throws SAXException, ParserConfigurationException, IOException {
        ImportModel importModel = new ImportModel(dbFileName);
        importModel.importData();
    }

    private Date getDate(String date) {
        Calendar s = Calendar.getInstance();
        s.set(
                Integer.parseInt(date.substring(0, 4)),
                Integer.parseInt(date.substring(6, 7)) - 1,
                Integer.parseInt(date.substring(9))
        );
        return s.getTime();
    }

}
