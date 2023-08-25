package test.de.emir.tuml.ucore.runtime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import de.emir.tuml.ucore.UCoreModel;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.serialization.json.JsonSerializer;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLSerializer;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

public class SerializerTest {

    static {
        UCoreModel.init();// just init the model
    }

    public static void main(String[] args) {
        new SerializerTest().BinarySerializerTest();
        new SerializerTest().XMLSerializerTest();
        new SerializerTest().JsonSerializerTest();
    }

    @Test
    public void XMLSerializerTest() {
        XMLSerializer ser = new XMLSerializer();
        UClass obj = UCoreMetaRepository.findClassBySimpleName("UAnnotation");
        assertNotNull(obj);

        File tmp = new File("test.xml");
        try {
            ser.writeFile(obj, tmp);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed with exception: " + e.getMessage());
        }

    }
    
    @Test
    public void JsonSerializerTest() {
        JsonSerializer ser = new JsonSerializer();
        UClass obj = UCoreMetaRepository.findClassBySimpleName("UAnnotation");
        assertNotNull(obj);

        File tmp = new File("test.json");
        try {
            ser.writeFile(obj, tmp);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed with exception: " + e.getMessage());
        }

    }

    @Test
    public void BinarySerializerTest() {

        // BinarySerializer bs = new BinarySerializer();
        // short[] arr = {-2735, -2, 1, 23805, 14170, 15001, 14381, 18598, 8, 12338, 12337, 12343, 12336, -4, 18632,
        // -4900, 2032, 3, 4093, 0, -21312, 16500, 16854, -4, 23823, 27827, 7958, 3231, 3874, -26469, -1060, 2172,
        // 27042, 1738, 16652, 2032, 1, 4093, 18629, -16124, 32751, 16464, -4, -4, 27032, -14559, 1736, 314, -31667,
        // -4851, 16458, 1737, -22156, -30885, 948, 16418, 8232, -16644, -4, -4, -4, -2, 2, -3865, 27999, -6240, 0, -4,
        // -27214, 12085, -31886, 2032, -19440, 5931, 3, 16695, 1, -4, 4093, 27042, 1738, 16652, 4093, 0, 0, 0, 0, -4,
        // -4, -4, 21034, 32220, 2032, 32738, 2487, 0, 5931, 3, -4, 4093, -2414, 1736, 9308, -29196, -17383, 16400,
        // 1737, 4234, -25522, 23466, 16383, -4, -4, -4, -4, -4, -4};
        // try {
        // UObject r = bs.deserialize(arr);
        // } catch (IOException e1) {
        // e1.printStackTrace();
        // }
        //
        //
        // BinarySerializer ser = new BinarySerializer();
        //// UPackage de_package = UCoreMetaRepository.getPackage("de");
        // UClass obj_in = UCoreMetaRepository.findClassBySimpleName("UAnnotation");
        // assertNotNull(obj_in);
        //
        // File tmp = new File("test.bin");
        // try {
        // ser.writeFile(obj_in, tmp);
        //
        //
        // System.out.println();
        // System.out.println("------------------------------------------------------------------------------------------");
        // System.out.println();
        // ser = new BinarySerializer();
        // Object obj =ser.readFile(tmp);
        //
        // if (obj == null) fail("Object = null");
        // assertTrue(obj instanceof UPackage);
        // } catch (IOException e) {
        // e.printStackTrace();
        // fail("Failed with exception: " + e.getMessage());
        // }

    }

}
