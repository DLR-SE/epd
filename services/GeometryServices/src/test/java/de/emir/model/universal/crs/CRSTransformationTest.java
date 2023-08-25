package de.emir.model.universal.crs;

import org.junit.Test;

import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.impl.PhysicalObjectImpl;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.SpatialDelegateProviders;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.EnvelopeImpl;
import de.emir.service.geometry.impl.GeometryTransform;

public class CRSTransformationTest {
	
	static {
		SpatialDelegateProviders.register();
	}

	@Test
	public void testEng2DToEng2DWith5408(){
		//this resamples the rotation of a boundingbox that is given in local coordinates 
		
		Engineering2D globalCRS = new Engineering2DImpl(new Vector2DImpl(54,8)); //somewhere located in the north sea
		PhysicalObject pobj = new PhysicalObjectImpl();
		pobj.getPose().setCoordinate(new CoordinateImpl(250, 0, globalCRS));
		
		//build a simple geometry to be rotated
		Envelope env = new EnvelopeImpl(-10, -100, 10, 100, pobj.getOwnedCoordinateSystem());
		
		GeometryTransform gt = new GeometryTransform();
//		Envelope rotEnv = gt.rotate2DCW(env, new AngleImpl(45, AngleUnit.DEGREE));
//		assertNotNull(rotEnv);
//		assertNotEquals(rotEnv, env);
		
		
	}
}
