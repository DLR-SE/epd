/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import de.emir.service.codecs.nmea0183.encoding.sentence.AISSentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.Sentence;
import de.emir.service.codecs.nmea0183.encoding.sentence.SentenceFactory;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.AISSentenceHandler;
import de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.payload.decoded.DecodedAISPayload;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author stefanb
 */
public class AISParserTest {
    AISSentenceHandler aisSentenceHandler;
    
    public AISParserTest() {
    }

    @Test
    public void rotTest() {
        String data = "!AIVDM,1,1,,B,139K90P1Br0W8lNNpNPrk8b00D2C,0*26";
        AISSentence parsed = new AISSentence(data);
		parsed.parse();
        aisSentenceHandler.handleAISSentence(parsed);
        DecodedAISPayload decoded = aisSentenceHandler.getDecodedAISMessage();
        String str = decoded.toString();
        System.out.println(str);
        Map map = decoded.toMap();
        Assert.assertNotNull(map.get("rateOfTurn"), "no field rateOfTurn" );
        Assert.assertEquals(map.get("rateOfTurn"), 5, "wrong value");
        Map map2 = new HashMap<String, Object>();
        decoded.fillMap(map2);
        Assert.assertNotNull(map2.get("rateOfTurn"), "no field rateOfTurn" );
        Assert.assertEquals(map2.get("rateOfTurn"), 5, "wrong value");
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        aisSentenceHandler = new AISSentenceHandler();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
