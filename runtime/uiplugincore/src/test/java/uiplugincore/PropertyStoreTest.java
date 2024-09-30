/**
 * 
 */
package uiplugincore;

import de.emir.model.universal.plugincore.var.ConfigArray;
import de.emir.model.universal.plugincore.var.ConfigMap;
import de.emir.model.universal.plugincore.var.impl.ConfigArrayImpl;
import de.emir.model.universal.plugincore.var.impl.ConfigColorImpl;
import de.emir.model.universal.plugincore.var.impl.ConfigMapImpl;
import de.emir.model.universal.plugincore.var.impl.ConfigObjectImpl;
import de.emir.model.universal.plugincore.var.impl.ConfigRectangleImpl;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.impl.UPackageImpl;
import de.emir.tuml.ucore.runtime.prop.AbstractProperty;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;

/**
 * @author stefanb
 *
 */
public class PropertyStoreTest {
	private PropertyStore ps = new PropertyStore();
	private PropertyContext context = ps.getContext("TestContext");
    private PropertyContext context2 = ps.getContext("2ndTestContext");
	
	@Test
	public void testGetContext() {
		assertNotNull(context);
	}

	/**
	 * Test method for {@link de.emir.rcp.properties.PropertyStore#load()}.
	 */
	@Test
	public void testSaveLoad() {
		IProperty<String> testProperty1 = context.getProperty("TestProperty1", "The first test property a, simple String.", "StringValue");
		assertNotNull(testProperty1);
		assertNotNull(testProperty1.getValue());
		assertEquals(testProperty1.getValue(), "StringValue");
        UPackageImpl uobj = new UPackageImpl();
        uobj.setName("MyName");
        IProperty<UPackageImpl> testPropertyU = context2.getProperty("TestPropertyU", "UObject based property", uobj);
//        PointerProperty testPropertyU = new PointerProperty(new ObjectPointerImpl(uobj));
        
        ConfigColorImpl uobj2 = new ConfigColorImpl(123, 0, 66, 255);
        uobj2.setName("ConfigColorTest1");
        IProperty<ConfigColorImpl> testPropertyU2 = context.getProperty("TestPropertyU2", "2nd UObject based property", uobj2);
        
        ConfigRectangleImpl uobj3 = new ConfigRectangleImpl(0, 0, 1920, 1080);
        uobj3.setName("ConfigRectangleTest1");
        IProperty<ConfigRectangleImpl> testPropertyU3 = context.getProperty("TestPropertyU3", "3rd UObject based property", uobj3);
        
        ConfigArrayImpl uobj4 = new ConfigArrayImpl();
        uobj4.setName("ConfigArrayTest1");
        uobj4.getArray().add(new ConfigObjectImpl("String in Array1"));
        uobj4.getArray().add(new ConfigObjectImpl("String in Array2"));
        IProperty<ConfigArrayImpl> testPropertyU4 = context.getProperty("TestPropertyU4", "4th UObject based property", uobj4);
        
        ConfigMapImpl uobj5 = new ConfigMapImpl();
        uobj5.setName("ConfigMapTest1");
        uobj5.put("first", "String item");
        uobj5.put("second", Long.valueOf(23L));
        uobj5.put("third", Boolean.TRUE);
        uobj5.put("fourth", uobj4);
//        IProperty<ConfigMapImpl> testPropertyU5 = context.getProperty("TestPropertyU5", "5th UObject based property", uobj5);
        IProperty<ConfigMapImpl> testPropertyU5 = context.getProperty("TestPropertyU5", "5th UObject based property", new ConfigMapImpl());
        testPropertyU5.setValue(uobj5);
//        IProperty<ConfigMapImpl> testPropertyU5 = new GenericProperty<ConfigMapImpl>("TestPropertyU5", "5th UObject based property", true, uobj5);
//        PointerProperty testPropertyU5 = new PointerProperty(new ObjectPointerImpl(uobj5));
//        testPropertyU5.setParentProperty(testProperty1);
//        ((AbstractProperty) testProperty1).addChild(testPropertyU5);
        
//        IProperty<Boolean> testProperty2 = context.getProperty("TestProperty2", "The second test property a boolean.", Boolean.TRUE);
        IProperty<Boolean> testProperty2 = new GenericProperty("TestProperty2", "The second test property a boolean.", true, Boolean.TRUE);
        ((AbstractProperty) testProperty2).setParentProperty(testProperty1);

        testPropertyU.setValue(uobj);
        testPropertyU2.setValue(uobj2);
        testPropertyU3.setValue(uobj3);
        testPropertyU4.setValue(uobj4);
        testPropertyU5.setValue(uobj5);
		PropertyStore ps2 = new PropertyStore();
        try {
            ps.save(new File("testproperties.data"));
            
            ps2.load(new File("testproperties.data"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        ps2.getContext("TestContext");
        testPropertyU5 = context.getProperty("TestPropertyU5", "5th UObject based property", uobj5);
        ConfigMap cm = testPropertyU5.getValue();
        ConfigArray ca = (ConfigArrayImpl) cm.get("fourth");
        assertNotNull(ca);
        for (Object o : ca.getArray()) {
            assertNotNull(o);
            System.out.println(o.toString());
        }
		
		testProperty1 = context.getProperty("TestProperty1");
		assertNotNull(testProperty1);
		assertNotNull(testProperty1.getValue());
		assertEquals(testProperty1.getValue(), "StringValue");
	}
	
	/**
	 * Test method for {@link de.emir.rcp.properties.PropertyStore#load()}.
	 */
	@Test
	public void testSaveLoad2() {
		IProperty<Long> testProperty2 = context.getProperty("TestProperty2", "The second test property.", 23L);
		assertNotNull(testProperty2);
		assertNotNull(testProperty2.getValue());
		assertEquals(testProperty2.getValue(), Long.valueOf(23L));
		
		ps.save(new File("testproperties.data"));
		ps.load(new File("testproperties.data"));
		
		IProperty<Long> testProperty2a = context.getProperty("TestProperty2");
		assertNotNull(testProperty2a);
		assertNotNull(testProperty2a.getValue());
		assertEquals(testProperty2a.getValue(), Long.valueOf(23L));
		
		GenericProperty<Integer> testProperty3 = new GenericProperty<Integer>("TestProperty3", "The third test property, nested.", true, Integer.valueOf(42));
		GenericProperty<Boolean> testProperty4 = new GenericProperty<Boolean>("TestProperty4", "The fourth test property, nested.", true, Boolean.TRUE);
		((GenericProperty<Long>) testProperty2).addChild(testProperty3);
		testProperty3.addChild(testProperty4);
//        testProperty4.setParentProperty(testProperty2);
        assertNotNull(testProperty2.getSubProperties());
        assertNotNull(testProperty2.getSubProperties().get(0));
//        context.setValue("TestProperty2", testProperty2);
		
		ps.save(new File("testproperties.data"));
		ps.load(new File("testproperties.data"));
	}
    
    	/**
	 * Test method for {@link de.emir.rcp.properties.PropertyStore#load()}.
	 */
//	@Test
	public void testSaveLoad3() {
        ArrayList<String> data = new ArrayList<>();
        data.add("String1");
        data.add("String2");
        data.add("String3");
		IProperty<ArrayList<String>> testProperty5 = (GenericProperty<ArrayList<String>>) context.getProperty("TestProperty5", "The second test property.", data);
		assertNotNull(testProperty5);
		assertNotNull(testProperty5.getValue());
		assertEquals(testProperty5.getValue(), data);
		
		ps.save(new File("testproperties.data"));
		ps.load(new File("testproperties.data"));
		
		testProperty5 = context.getProperty("TestProperty5");
		assertNotNull(testProperty5);
		assertNotNull(testProperty5.getValue());
		assertEquals(testProperty5.getValue(), data);
		
		data.add("New Line");
		
		ps.save(new File("testproperties.data"));
		ps.load(new File("testproperties.data"));
	}

}
