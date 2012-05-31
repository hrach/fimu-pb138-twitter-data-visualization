package com.skrasek.school.pb138.tests;

import org.junit.*;
import static org.junit.Assert.*;
import com.skrasek.school.pb138.StatsModel;
import java.io.IOException;
import java.util.Calendar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;


/**
 * StatsModel Test
 * @author Jan Skrasek <hrach.cz@gmail.com>
 */
public class StatsModelTest {

    public StatsModelTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    private StatsModel model;

    @Before
    public void setUp()  throws SAXException, ParserConfigurationException, IOException {
        URI uri = StatsModelTest.class.getResource("people.xml").toURI();
        String path = getClass().getClassLoader().getResource(".").getPath();
        if (uri == null) {
            throw new IOException("Nelze nalezt testovaci XML soubor");
        }
        model = new StatsModel(path + "/../../../../test-db.xml");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void findData() throws XPathExpressionException {
        Calendar s, e;
        
        s = Calendar.getInstance();
        s.set(2012, 5 - 1, 28, 11, 0);
        e = Calendar.getInstance();
        e.set(2012, 5 - 1, 28, 12, 0);

        assertSame(2, model.findData(s.getTime(), e.getTime()).getLength());


        s = Calendar.getInstance();
        s.set(2012, 5 - 1, 28, 10, 0);
        e = Calendar.getInstance();
        e.set(2012, 5 - 1, 28, 12, 0);

        assertSame(5, model.findData(s.getTime(), e.getTime()).getLength());


        s = Calendar.getInstance();
        s.set(2012, 5 - 1, 28, 10, 0);
        e = Calendar.getInstance();
        e.set(2012, 5 - 1, 28, 11, 59);

        assertSame(3, model.findData(s.getTime(), e.getTime()).getLength());
    }

}
