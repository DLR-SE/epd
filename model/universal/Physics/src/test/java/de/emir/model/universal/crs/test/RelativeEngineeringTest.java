package de.emir.model.universal.crs.test;

import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.physics.impl.PhysicalObjectImpl;
import de.emir.model.universal.physics.impl.RelativeEngineering2DImpl;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.spatial.impl.PoseImpl;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import org.junit.Assert;

public class RelativeEngineeringTest {

	
	public static void main(String[] args) {
		testRelativeEngineering2DGetOrientationOffsetPositive();
        testRelativeEngineering2DGetOrientationOffsetNegative();
	}

    /**
     * Test getOrientationOffset behaviour with orientation offsets > 720°
     */
	public static void testRelativeEngineering2DGetOrientationOffsetPositive() {
        PhysicalObject obj = new PhysicalObjectImpl();
        Pose pose = new PoseImpl();
        pose.setOrientation(
                new EulerImpl(
                        new AngleImpl(0.0, AngleUnit.DEGREE),
                        new AngleImpl(0.0, AngleUnit.DEGREE),
                        new AngleImpl(820.0, AngleUnit.DEGREE) // angle defined here
                )
        );

        obj.setPose(pose);


        RelativeEngineering2D crs = new RelativeEngineering2DImpl();
        // no reference set
        Assert.assertEquals(1, crs.getOrientationOffset().size());
        Assert.assertEquals(0.0, crs.getOrientationOffset().getFirst(), 1e-6);

        crs.setReference(obj);

        // reference was set
        Assert.assertEquals(1, crs.getOrientationOffset().size());
        Assert.assertEquals(1.7453292519943293, crs.getOrientationOffset().getFirst(), 1e-6);
	}

    /**
     * Test getOrientationOffset behaviour with orientation offsets < -720°
     */
    public static void testRelativeEngineering2DGetOrientationOffsetNegative() {
        PhysicalObject obj = new PhysicalObjectImpl();
        Pose pose = new PoseImpl();
        pose.setOrientation(
                new EulerImpl(
                        new AngleImpl(0.0, AngleUnit.DEGREE),
                        new AngleImpl(0.0, AngleUnit.DEGREE),
                        new AngleImpl(-820.0, AngleUnit.DEGREE) // angle defined here
                )
        );

        obj.setPose(pose);


        RelativeEngineering2D crs = new RelativeEngineering2DImpl();
        // no reference set
        Assert.assertEquals(1, crs.getOrientationOffset().size());
        Assert.assertEquals(0.0, crs.getOrientationOffset().getFirst(), 1e-6);

        crs.setReference(obj);

        // reference was set
        Assert.assertEquals(1, crs.getOrientationOffset().size());
        Assert.assertEquals(4.537856055185257, crs.getOrientationOffset().getFirst(), 1e-6);
    }

}
