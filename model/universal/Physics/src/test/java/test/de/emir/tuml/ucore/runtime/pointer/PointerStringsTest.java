package test.de.emir.tuml.ucore.runtime.pointer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import de.emir.model.universal.math.impl.Vector3DImpl;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.impl.DynamicObjectCharacteristicImpl;
import de.emir.model.universal.physics.impl.MultiViewObjectSurfaceInforamtionImpl;
import de.emir.model.universal.physics.impl.PhysicalObjectImpl;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.AngularSpeedUnit;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.impl.AngularVelocityImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.model.universal.units.impl.QuaternionImpl;
import de.emir.model.universal.units.impl.VelocityImpl;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.pointer.PointerStrings;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import junit.textui.TestRunner;

public class PointerStringsTest {

	
	private static final PhysicalObject 	testObject;
	
	static {
		testObject = new PhysicalObjectImpl();
		testObject.getPose().set(new CoordinateImpl(1,2,3,null), new QuaternionImpl());
		testObject.getCharacteristics().add(new DynamicObjectCharacteristicImpl(
				new AngularVelocityImpl(new EulerImpl(6, 5, 4, AngleUnit.DEGREE), AngularSpeedUnit.DEGREES_PER_HOUR, null),
				new VelocityImpl(new Vector3DImpl(7, 8, 9), SpeedUnit.KM_PER_MINUTE, null)));
		testObject.getCharacteristics().add(new DynamicObjectCharacteristicImpl(
				new AngularVelocityImpl(new EulerImpl(16, 15, 14, AngleUnit.DEGREE), AngularSpeedUnit.DEGREES_PER_HOUR, null),
				new VelocityImpl(new Vector3DImpl(17, 18, 19), SpeedUnit.KM_PER_MINUTE, null)));
		testObject.getCharacteristics().add(new MultiViewObjectSurfaceInforamtionImpl());
		
	}
	
	public static final String[] validPointerStrings = new String[] {
			"UComponent<MultiViewObjectSurfaceInforamtion>.geometry",							//There is no MultiViewObjectSurfaceInformation available however it may come in later
			"pose.coordinate.x", 																// == 1
			"pose.getCoordinate().getX()", 														// == 1
			"characteristics:0.angularVelocity.value.z.value", 									// == 4
			"characteristics<DynamicObjectCharacteristic>:0.angularVelocity.value.z.value",		// == 4
			"UComponent<DynamicObjectCharacteristic>.linearVelocity.value.x", 					//==7
			"UComponent<DynamicObjectCharacteristic>:0.getLinearVelocity().value.x", 			//==7
			"UComponent<DynamicObjectCharacteristic>:1.linearVelocity.value.x",	 				//==17
			"UComponent<ObjectSurfaceInformation>.geometry",						 			//==null
	};
	public static final String[] validSyntaxInvalidPointer = new String[] {
			"characteristics<ObjectSurfaceInformation>:0.angularVelocity.value.z.value",		//characteristics:0 is a DynamicObjectCharacteristic
			
	};
	public static final Object[] resultValues = new Object[] {
			null, 1.0, 1.0, 4.0, 4.0, 7.0, 7.0, 17.0, null
	};
	
	public static final String[] toPointerStringResults = new String[] {
			"UComponent<MultiViewObjectSurfaceInforamtion>.geometry",
			"pose.coordinate.x", 																// == 1
			"pose.coordinate.x",		 														// == 1
			"characteristics:0.angularVelocity.value.z.value", 									// == 4
			"characteristics:0.angularVelocity.value.z.value",									// == 4
			"UComponent<DynamicObjectCharacteristic>.linearVelocity.value.x", 					//==7
			"UComponent<DynamicObjectCharacteristic>:0.linearVelocity.value.x", 				//==7
			"UComponent<DynamicObjectCharacteristic>:1.linearVelocity.value.x",	 				//==17
			"UComponent<ObjectSurfaceInformation>.geometry",						 			//==null
	};
	
	
	public static void main(String[] args) {
		PointerStringsTest pst = new PointerStringsTest();
		pst.testValidSyntax();
		pst.testValidPointer();
		pst.testInvalidPointer();
		pst.testAssignWithUComponent();
		pst.testToPointerString();
	}
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testValidSyntax() {
		for (String str : validPointerStrings)
			if (!PointerStrings.syntaxCheckPointerString(str))
				fail(str + " has not been detected as syntactically correct");
	}
	
	
	@Test
	public void testValidPointer() {
		UObject root = new PhysicalObjectImpl(testObject);
		for (int i = 0; i < validPointerStrings.length; i++) {
			String str = validPointerStrings[i];
			Pointer ptr = PointerStrings.create(root, str);
			if (ptr == null || !ptr.isValid()) {
				fail("Ptr: " + str + " is not a valid pointer");
			}
			Object val = ptr.getValue();
			if (val != null && !val.equals(resultValues[i]))
				fail("Ptr-Value for: " + str + " is not as expected. Expected: " + resultValues[i] + " Got: " + val);
			if (val == null && resultValues[i] != null)
				fail("Ptr-Value for: " + str + " is not as expected. Expected: " + resultValues[i] + " Got: " + val);
		}
	}
	
	@Test
	public void testToPointerString() {
		UObject root = new PhysicalObjectImpl(testObject);
		for (int i = 0; i < validPointerStrings.length; i++) {
			String str = validPointerStrings[i];
			Pointer ptr = PointerStrings.create(root, str);
			String ptrStr = PointerStrings.toPointerString(ptr);
			String expected = toPointerStringResults[i];
			if (!expected.equals(ptrStr))
				fail("To Pointer String failed, expected " + expected + " got: " + ptrStr);
		}
	}
	
	@Test
	public void testAssignWithUComponent() {
		UObject root = new PhysicalObjectImpl(testObject);
		Pointer ptrUComp = PointerStrings.create(root, validPointerStrings[3]);
		assertNotNull(ptrUComp);
		assertEquals(ptrUComp.getValue(), resultValues[3]);
		ptrUComp.setValue(42.0);
		assertEquals(ptrUComp.getValue(), 42.0);
	}
	
	
	@Test
	public void testInvalidPointer() {
		UObject root = new PhysicalObjectImpl(testObject);
		for (int i = 0; i < validSyntaxInvalidPointer.length; i++) {
			String str = validSyntaxInvalidPointer[i];
			Pointer ptr = PointerStrings.create(root, str);
			if (ptr != null )
				fail("Pointer " + str + " should not be a valid pointer");
		}
	}

}
