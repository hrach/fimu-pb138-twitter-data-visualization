/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skrasek.school.pb138.tests;

import java.io.IOException;
import java.net.URI;
import org.junit.*;
import static org.junit.Assert.*;
import com.skrasek.school.pb138.StatsModel;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;


/**
 * Validity test
 * @author Jan Skrasek <hrach.cz@gmail.com>
 */
public class SchemaTest {
    
    public SchemaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    private Schema schema;

    @Before
    public void setUp() throws Exception {
        URI uri = StatsModel.class.getResource("db-schema.xsd").toURI();
        if (uri == null) {
            throw new IOException("Nelze nalezt testovaci XSD soubor");
        }
        
        String schemaLang = "http://www.w3.org/2001/XMLSchema";
        SchemaFactory factory = SchemaFactory.newInstance(schemaLang);
        schema = factory.newSchema(new StreamSource(uri.toString()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSchema() throws Exception {
        URI uri = SchemaTest.class.getResource("test-db.xml").toURI();
        if (uri == null) {
            throw new IOException("Nelze nalezt testovaci XML soubor");
        }

        Validator validator = schema.newValidator();
        SAXException e = null;
        try {
            validator.validate(new StreamSource(uri.toString()));
        } catch (SAXException ex) {
            e = ex;
        }

        assertFalse("Validation xml", e instanceof SAXException);
    }

    @Test
    public void testInvalidSchema() throws Exception {
        for (int i = 1; i < 6; i += 1) {
            URI uri = SchemaTest.class.getResource("test-db-invalid-" + i + ".xml").toURI();
            if (uri == null) {
                throw new IOException("Nelze nalezt testovaci XML soubor");
            }

            Validator validator = schema.newValidator();
            SAXException e = null;
            try {
                validator.validate(new StreamSource(uri.toString()));
            } catch (SAXException ex) {
                e = ex;
            }

            assertTrue("Test with invalid document n." + i, e instanceof SAXException);
        }
    }
}
