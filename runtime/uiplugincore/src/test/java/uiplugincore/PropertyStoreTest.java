/**
 * 
 */
package uiplugincore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;

/**
 * @author stefanb
 *
 */
public class PropertyStoreTest {
	private PropertyStore ps = new PropertyStore();
	private PropertyContext context = ps.getContext("TestContext");
	
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
		
		ps.save(new File("testproperties.data"));
		ps.load(new File("testproperties.data"));
		
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
		IProperty<Long> testProperty2 = (GenericProperty<Long>) context.getProperty("TestProperty2", "The second test property.", 23L);
		assertNotNull(testProperty2);
		assertNotNull(testProperty2.getValue());
		assertEquals(testProperty2.getValue(), Long.valueOf(23L));
		
		ps.save(new File("testproperties.data"));
		ps.load(new File("testproperties.data"));
		
		testProperty2 = context.getProperty("TestProperty2");
		assertNotNull(testProperty2);
		assertNotNull(testProperty2.getValue());
		assertEquals(testProperty2.getValue(), Long.valueOf(23L));
		
		IProperty<Integer> testProperty3 = context.getProperty("TestProperty3", "The third test property, nested.", 42);
		GenericProperty<Boolean> testProperty4 = new GenericProperty<Boolean>("TestProperty4", "The fourth test property, nested.", true);
		((GenericProperty<Long>)testProperty2).addChild((GenericProperty<Integer>) testProperty3);
		((GenericProperty<Integer>)testProperty3).setParentProperty(testProperty2);
		testProperty4.setParentProperty(testProperty2);
		
		ps.save(new File("testproperties.data"));
		ps.load(new File("testproperties.data"));
	}

}
