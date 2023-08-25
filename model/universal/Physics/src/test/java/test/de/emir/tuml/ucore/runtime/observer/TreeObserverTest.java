package test.de.emir.tuml.ucore.runtime.observer;

import static org.junit.Assert.*;

import org.junit.Test;

import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.impl.PhysicalObjectImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;

public class TreeObserverTest {

	public static void main(String[] args) {
		new TreeObserverTest().test();
	}
	
	class MyTreeListener implements ITreeValueChangeListener {
		boolean mTriggered = false;
		
		@Override
		public void onValueChange(Notification<Object> notification) {
			mTriggered = true;
		}
		
	}
	
	@Test
	public void test() {
		
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

}
