package test.de.emir.tuml.ucore.runtime.observer;

import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.physics.impl.PhysicalObjectImpl;
import de.emir.model.universal.physics.impl.RelativeEngineering2DImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Euler;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.internal.ObserverOptions.TargetTypeTreeObserverOptions;
import de.emir.tuml.ucore.runtime.utils.internal.ObserverOptions.TreeObserverWhiteListOptions;
import de.emir.tuml.ucore.runtime.utils.internal.TreeObserverUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TreeObserverTest {

	
	class MyTreeListener implements ITreeValueChangeListener {
		boolean mTriggered = false;
		
		@Override
		public void onValueChange(Notification<Object> notification) {
			mTriggered = true;
		}
		
	}
	
	@Test
	public void testTreeListener() {
		
		PhysicalObject pobj = new PhysicalObjectImpl();
		MyTreeListener listener = new MyTreeListener();
		pobj.registerTreeListener(listener);
		
//		assertFalse(listener.mTriggered);
		pobj.getPose().getCoordinate().setX(1);
		assertTrue(listener.mTriggered);
		listener.mTriggered = false;
		
		pobj.getPose().getCoordinate().setY(1);
		assertTrue(listener.mTriggered);
		listener.mTriggered = false;
		
		Coordinate oldCoord = pobj.getPose().getCoordinate();
		
		pobj.getPose().setCoordinate(new CoordinateImpl());
		assertTrue(listener.mTriggered);
		listener.mTriggered = false;
		pobj.getPose().getCoordinate().setX(2);
		assertTrue(listener.mTriggered);
		assertEquals(pobj.getPose().getCoordinate().getX(), 2.0, 0.00000000001);
		listener.mTriggered = false;
		
		//check if removed from replaced instance
		assertFalse(listener.mTriggered);
		oldCoord.setY(9);
		assertFalse(listener.mTriggered);
		
		//check removal of the whole tree
		pobj.removeTreeListener(listener);
		listener.mTriggered = false;
		pobj.getPose().getCoordinate().setX(3);
		assertFalse(listener.mTriggered);
	}

	/**
	 * Tests for WhiteListOptions
	 */
	@Test
	public void testWhiteListOptions() {
		PhysicalObject pobj = new PhysicalObjectImpl();
		MyTreeListener listener = new MyTreeListener();
		ArrayList<UStructuralFeature> whiteList = new ArrayList<>();
		whiteList.add(PhysicsPackage.theInstance.getLocatableObject_pose()); // Feature to Pose
		whiteList.add(SpatialPackage.theInstance.getPose_coordinate()); // Feature to Coordinate
		whiteList.add(SpatialPackage.theInstance.getCoordinate_x()); // x of Coordinate
		whiteList.add(SpatialPackage.theInstance.getCoordinate_y()); // y of Coordinate
		TreeObserverWhiteListOptions whiteListOptions = new TreeObserverWhiteListOptions(true, true,true, true, whiteList);
		pobj.registerTreeListener(listener, whiteListOptions);

		assertFalse(listener.mTriggered);
		pobj.getPose().getCoordinate().setX(1);
		assertTrue(listener.mTriggered);
		listener.mTriggered = false;

		pobj.getPose().getCoordinate().setY(1);
		assertTrue(listener.mTriggered);
		listener.mTriggered = false;

		// trigger in not whitelisted owned coordinate system
		listener.mTriggered = false;
		pobj.getOwnedCoordinateSystem().setReference(new PhysicalObjectImpl());
		assertFalse(listener.mTriggered);

		// now add owned coordinate system to whitelist and check if it still works
		whiteListOptions.addWhiteListedFeature(PhysicsPackage.init().getLocatableObject_ownedCoordinateSystem());
		pobj.removeTreeListener(listener);
		pobj.registerTreeListener(listener, whiteListOptions);
		listener.mTriggered = false;
		pobj.setOwnedCoordinateSystem(new RelativeEngineering2DImpl());
		assertTrue(listener.mTriggered);

		Coordinate oldCoord = pobj.getPose().getCoordinate();

		pobj.getPose().setCoordinate(new CoordinateImpl());
		assertTrue(listener.mTriggered);
		listener.mTriggered = false;
		pobj.getPose().getCoordinate().setX(2);
		assertTrue(listener.mTriggered);
		assertEquals(2.0, pobj.getPose().getCoordinate().getX(), 0.00000000001);
		listener.mTriggered = false;

		//check if removed from replaced instance
		assertFalse(listener.mTriggered);
		oldCoord.setY(9);
		assertFalse(listener.mTriggered);

		//check removal of the whole tree
		pobj.removeTreeListener(listener);
		listener.mTriggered = false;
		pobj.getPose().getCoordinate().setX(3);
		assertFalse(listener.mTriggered);
	}

	/**
	 * Tests for the ClassifierTreeObserverOptions
	 */
	@Test
	public void testClassifierOption() {
		PhysicalObject physicalObject = new PhysicalObjectImpl();
		MyTreeListener listener = new MyTreeListener();

		TreeObserverUtil.ITreeObserverOptions classifierOptions = new TargetTypeTreeObserverOptions(Coordinate.class);
		physicalObject.registerTreeListener(listener, classifierOptions);
		// test trigger of target
		physicalObject.getPose().getCoordinate().setX(1);
		assertTrue(listener.mTriggered);
		listener.mTriggered = false;

		physicalObject.getPose().getCoordinate().setY(1);
		assertTrue(listener.mTriggered);

		// do not trigger changes in owned coordinate system
		listener.mTriggered = false;
		physicalObject.getOwnedCoordinateSystem().setReference(new PhysicalObjectImpl());
		assertFalse(listener.mTriggered);

		Coordinate oldCoord = physicalObject.getPose().getCoordinate();

		// test triggering of changes in chain
		physicalObject.getPose().setCoordinate(new CoordinateImpl());
		assertTrue(listener.mTriggered);
		listener.mTriggered = false;
		physicalObject.getPose().getCoordinate().setX(2);
		assertTrue(listener.mTriggered);
		assertEquals(physicalObject.getPose().getCoordinate().getX(), 2.0, 0.00000000001);

		//check if removed from replaced instance
		listener.mTriggered = false;
		oldCoord.setY(9);
		assertFalse(listener.mTriggered);

		//check removal of the whole tree
		physicalObject.removeTreeListener(listener);
		listener.mTriggered = false;
		physicalObject.getPose().getCoordinate().setX(3);
		assertFalse(listener.mTriggered);

		// test using multiple targets

		List<UClassifier> classifierList = new ArrayList<>();
		classifierList.add(UCoreMetaRepository.getClassifier(Pose.class));
		classifierList.add(UCoreMetaRepository.getClassifier(RelativeEngineering2D.class));
		classifierOptions = new TargetTypeTreeObserverOptions(classifierList, true, true, true, true, true);
		physicalObject.registerTreeListener(listener, classifierOptions);

		listener.mTriggered = false;
		physicalObject.getOwnedCoordinateSystem().setReference(new PhysicalObjectImpl());
		assertTrue(listener.mTriggered);

		listener.mTriggered = false;
		physicalObject.getPose().setCoordinate(new CoordinateImpl());
		assertTrue(listener.mTriggered);

		listener.mTriggered = false;
		physicalObject.getPose().getCoordinate().setX(567);
		assertFalse(listener.mTriggered); // this is because a coordinate can own a RelativeEngineering2D Coordinate System but the feature x is not listened to

		listener.mTriggered = false;
		physicalObject.getPose().getCoordinate().setCrs(new RelativeEngineering2DImpl());
		assertTrue(listener.mTriggered);

		physicalObject.getPose().setOrientation(new EulerImpl());
		((Euler) physicalObject.getPose().getOrientation()).setX(new AngleImpl());
		listener.mTriggered = false;
		((Euler) physicalObject.getPose().getOrientation()).getX().set(5, AngleUnit.DEGREE);
		assertFalse(listener.mTriggered);
	}

}
