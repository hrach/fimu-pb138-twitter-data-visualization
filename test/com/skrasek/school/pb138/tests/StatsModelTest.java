package com.skrasek.school.pb138.tests;

import org.junit.*;
import static org.junit.Assert.*;
import com.skrasek.school.pb138.StatsModel;
import com.skrasek.school.pb138.Trend;
import java.io.IOException;
import java.util.Calendar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import java.net.URI;
import java.util.Map;



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
    public void setUp() throws Exception, SAXException, ParserConfigurationException, IOException {
        URI uri = StatsModelTest.class.getResource("test-db.xml").toURI();
        if (uri == null) {
            throw new IOException("Nelze nalezt testovaci XML soubor");
        }
        model = new StatsModel(uri);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getStats() throws XPathExpressionException {
        Calendar s, e;

        s = Calendar.getInstance();
        s.set(2012, 5 - 1, 28, 10, 0);
        e = Calendar.getInstance();
        e.set(2012, 5 - 1, 28, 12, 0);

        Map<String,Trend> trends = model.getStats(s.getTime(), e.getTime());
        assertSame(5, trends.size());

        Trend t = new Trend("#hashtag", "query");
        t.addTermin("201205281000");
        t.addTermin("201205281200");

        assertEquals(t, trends.get("#hashtag"));
    }

    @Test
    public void findData() throws XPathExpressionException {
        Calendar s, e;

        s = Calendar.getInstance();
        s.set(2012, 5 - 1, 28, 11, 0);
        e = Calendar.getInstance();
        e.set(2012, 5 - 1, 28, 12, 0);

        assertSame(3, model.findData(s.getTime(), e.getTime()).getLength());


        s = Calendar.getInstance();
        s.set(2012, 5 - 1, 28, 10, 0);
        e = Calendar.getInstance();
        e.set(2012, 5 - 1, 28, 12, 0);

        assertSame(6, model.findData(s.getTime(), e.getTime()).getLength());


        s = Calendar.getInstance();
        s.set(2012, 5 - 1, 28, 10, 0);
        e = Calendar.getInstance();
        e.set(2012, 5 - 1, 28, 11, 59);

        assertSame(3, model.findData(s.getTime(), e.getTime()).getLength());
    }

}
