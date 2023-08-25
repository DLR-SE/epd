package de.emir.model.universal.physics.test;


import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.SpeedImpl;
import de.emir.model.universal.units.impl.VelocityImpl;

public class CPATest2 {

	public static void main(String[] args) {
		new CPATest2().test();
	}
	
	public void test() {
//		Engineering2DImpl eng = new Engineering2DImpl();
//		CoordinateImpl pos1 = new CoordinateImpl(100, 0, eng);
//		CoordinateImpl pos2 = new CoordinateImpl(0, 0, eng);
		CoordinateImpl pos1 = new CoordinateImpl(54.150960, 7.5425071, CRSUtils.WGS84_2D);
		CoordinateImpl pos2 = new CoordinateImpl(54.15185840945781, 7.542507, CRSUtils.WGS84_2D);

		Vector2D v1 = new Vector2DImpl(1, 0);
		AngleImpl cog1 = new AngleImpl(0, AngleUnit.DEGREE);
		SpeedImpl sog1 = new SpeedImpl(5, SpeedUnit.KMH);
		v1.rotateLocalCW(cog1.getAs(AngleUnit.RADIAN));
		v1.multLocal(sog1.getAs(SpeedUnit.KMH));

		VelocityImpl vel1 = new VelocityImpl(v1, SpeedUnit.KMH, CRSUtils.WGS84_2D);

		Vector2D v2 = new Vector2DImpl(1, 0);
		AngleImpl cog2 = new AngleImpl(0, AngleUnit.DEGREE);
		SpeedImpl sog2 = new SpeedImpl(10, SpeedUnit.KMH);
		v2.rotateLocalCW(cog2.getAs(AngleUnit.RADIAN));
		v2.multLocal(sog2.getAs(SpeedUnit.KMH));

		VelocityImpl vel2 = new VelocityImpl(v2, SpeedUnit.KMH, CRSUtils.WGS84_2D);

		Distance DCPA = PhysicalObjectUtils.getDistanceAtClosedPointOfApproach(pos1, pos2, vel1, vel2);
		System.out.println(DCPA.getValue());
	}

}
