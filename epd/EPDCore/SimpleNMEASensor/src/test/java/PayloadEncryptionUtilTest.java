import static de.emir.service.codecs.nmea0183.encoding.sentence.aissentences.encryption.AISPayloadEncryptionUtil.getROTValue;
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
public class PayloadEncryptionUtilTest {
    
    public PayloadEncryptionUtilTest() {
    }

    @Test
    public void testGetROTValue() {
        assertNotEquals(Math.abs(getROTValue(50)), 0d, "no value");
        assertTrue(Math.abs(getROTValue(50)) > 1, "value too small");
        assertTrue(Math.abs(getROTValue(126)) < 709d, "value mismatch");
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
