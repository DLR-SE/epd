package test.de.emir.tuml.ucore.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.StringBufferInputStream;

import org.junit.Test;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.impl.UClassImpl;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLSerializer;

public class PropertyTest {
    /**
     * @generated
     */
    public static void main(String[] args) {
        PropertyTest test = new PropertyTest();
        test.testCreation();
        test.testSerialisation();
    }

    @Test
    public void testCreation() {
        UClass cl = new UClassImpl();
        final String propName = "some.sample.property";
        final String propValue = "My String Value";
        cl.setPropertyValue(propName, propValue);
        IProperty<?> prop = cl.getProperty(propName);

        assertNotNull(prop);
        assertEquals("property", prop.getName());
        assertNotNull(prop.getParentProperty());
        assertEquals(prop.getValue(), propValue);

        assertEquals(propValue, cl.getPropertyValueAsString(propName));

    }

    @Test
    public void testSerialisation() {
        UClass cl = new UClassImpl();
        final String propNameStr = "some.sample.str";
        final String propValueStr = "My String Value";
        final String propNameDou = "some.sample.dou";
        final double propValueDou = 42.42;

        cl.setPropertyValue(propNameStr, propValueStr);
        cl.setPropertyValue(propNameDou, propValueDou);

        assertNotNull(cl.getProperty(propNameDou));
        assertNotNull(cl.getProperty(propNameStr));

        String str = new XMLSerializer().serialize(cl);
        System.out.println("\n" + str + "\n");
        UClass cl2 = (UClass) new XMLSerializer().deserialize(new StringBufferInputStream(str));

        assertNotNull(cl2.getProperty(propNameDou));
        assertNotNull(cl2.getProperty(propNameStr));

        assertEquals(cl2.getPropertyValueAsString(propNameStr), propValueStr);

    }

}
