package test.de.emir.tuml.ucore.runtime.pointer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.physics.Environment;
import de.emir.model.universal.physics.ObjectLayer;
import de.emir.model.universal.physics.impl.EnvironmentImpl;
import de.emir.model.universal.physics.impl.ObjectLayerImpl;
import de.emir.model.universal.physics.impl.PhysicalObjectImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.PoseImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.model.universal.units.impl.QuaternionImpl;
import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.pointer.PointerChangeListener;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class PointerObserver {
	
	public static void main(String[] args) throws Exception{
		PointerObserver.setUpBeforeClass();
		PointerObserver po = new PointerObserver();
		po.setUp();
		po.testObservePointer();
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	private Environment mEnvironment;
	private ObjectLayer mObjectLayer;
	private PhysicalObjectImpl mPhysicalObject;

	@Before
	public void setUp() throws Exception {
		mEnvironment = new EnvironmentImpl();
		mEnvironment.getLayer().add(new ObjectLayerImpl());
		mObjectLayer = (ObjectLayer)mEnvironment.getLayer().get(0);
		mPhysicalObject = new PhysicalObjectImpl();
		mObjectLayer.getObjects().add(mPhysicalObject);
		mPhysicalObject.getPose().setCoordinate(new CoordinateImpl(0.5, 0.0, CRSUtils.ENGINEERING_2D));
		mPhysicalObject.getPose().setOrientation(new EulerImpl());
		
	}

	
	
	
	
	
	
	
	private boolean mNotified = false;
	@Test
	public void testObservePointer() {		
		Pointer ptr = PointerOperations.createPointerFromString(mEnvironment, "layer:0.objects:0.pose.coordinate.x");
		assertNotNull(ptr);
		assertTrue(ptr.isValid());
		assertEquals(0.5, (double)ptr.getValue(), 0.0000001);
		
		
		IValueChangeListener vc = new IValueChangeListener() {
			@Override
			public void onValueChange(Notification notification) {
				mNotified = true;
			}
		};
		
		IDisposable pcl = PointerOperations.observePointer(ptr, vc);
		mNotified = false;
		Coordinate oldCoord = mPhysicalObject.getPose().getCoordinate(); //remember to check if the observer has been removed
		mPhysicalObject.getPose().getCoordinate().setX(1);
		assertTrue(mNotified);
		mNotified = false;
		mPhysicalObject.getPose().getCoordinate().setY(1);
		assertFalse(mNotified);
		mNotified = false;
		mPhysicalObject.getPose().setCoordinate(new CoordinateImpl(0, 0, 0, null));
		assertTrue(mNotified);
		assertEquals(0.0, (double)ptr.getValue(), 0.000001);
		//check again, if the setX fires
		mNotified = false;
		mPhysicalObject.getPose().getCoordinate().setX(1);
		assertTrue(mNotified);
		mNotified = false;
		mPhysicalObject.getPose().getCoordinate().setY(1);
		assertFalse(mNotified);
	
		//check if the old Coordinate has no observer attached
		mNotified = false;
		oldCoord.setX(42.0);
		assertFalse(mNotified);
		
		
		mNotified = false;
		mPhysicalObject.getPose().setOrientation(new QuaternionImpl());
		assertFalse(mNotified);
		mNotified = false;
		Pose p = new PoseImpl(new CoordinateImpl(2, 0, null), new EulerImpl());
		mPhysicalObject.setPose(p);
		assertTrue(mNotified);
		assertEquals(2.0, (double)ptr.getValue(), 0.000001);
			
		//check again, if the setX fires
		mNotified = false;
		mPhysicalObject.getPose().getCoordinate().setX(1);
		assertTrue(mNotified);
		mNotified = false;
		mPhysicalObject.getPose().getCoordinate().setY(1);
		assertFalse(mNotified);
		
		
		pcl.dispose();
		//check if we do not get notified if we change anything
		mNotified = false;
		mPhysicalObject.getPose().getCoordinate().setX(1);
		assertFalse(mNotified);
	}

}
