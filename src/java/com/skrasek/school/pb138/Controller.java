package com.skrasek.school.pb138;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;



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
    
    public List<String> getTrends(String from, String to) {
        return new ArrayList<String>();
    }
    
    public void reloadData() throws SAXException, ParserConfigurationException, IOException {
        ImportModel importModel = new ImportModel(dbFileName);
        importModel.importData();
    }

}
