package de.emir.model.universal.physics;

import java.util.Collection;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.EngineeringCRS;
import de.emir.model.universal.crs.LocalCRS;
import de.emir.model.universal.crs.WGS84CRS;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.Vector3D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.math.impl.Vector3DImpl;
import de.emir.model.universal.physics.impl.DynamicObjectCharacteristicImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.model.universal.units.AngularVelocity;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Euler;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.Quaternion;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.Velocity;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.AngularVelocityImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.model.universal.units.impl.SpeedUnitImpl;
import de.emir.model.universal.units.impl.TimeImpl;
import de.emir.model.universal.units.impl.VelocityImpl;
import de.emir.tuml.ucore.runtime.IDelegateInterface;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class PhysicalObjectUtils {

	
	public static Angle getHeading(LocatableObject lobj){
		return getHeading(lobj.getPose());
	}

	public static Angle getHeading(Pose pose) {
		if (pose.getOrientation() == null)
			return new AngleImpl(); //default
//		CoordinateReferenceSystem crs = pose.getCoordinate() != null ? pose.getCoordinate().getCrs() : null;
//		if (crs == null)
//			crs = CRSUtils.ENGINEERING_2D;
		
		Euler e = pose.getOrientation().toEuler();
		return e.getZ() != null ? e.getZ() : new AngleImpl();
	}
	
	public static Speed getSOG(PhysicalObject pobj){
		return getSOG(pobj.getFirstCharacteristic(DynamicObjectCharacteristic.class, true));
	}
	public static Speed getSOG(DynamicObjectCharacteristic doc) {
		if (doc == null || doc.getLinearVelocity() == null || doc.getLinearVelocity().getValue() == null)
			return null;
		return doc.getLinearVelocity().getMagnitude();		
	}
	
	public static Angle getCOG(PhysicalObject pobj){
		try{
			if (pobj == null) return null;
			DynamicObjectCharacteristic doc = (pobj.getFirstCharacteristic(DynamicObjectCharacteristic.class, true));
			if (doc == null || doc.getLinearVelocity() == null || doc.getLinearVelocity().getValue() == null) 
				return null;
			CoordinateReferenceSystem crs = doc.getLinearVelocity().getCrs();
			if (crs == null)
				crs = doc.getLinearVelocity().getValue().dimensions() == 2 ? CRSUtils.ENGINEERING_2D : CRSUtils.ENGINEERING_3D;
			double angle_rad = 0;
			if (doc.getLinearVelocity().getValue().dimensions() == 2){
				Engineering2D eng = new Engineering2DImpl();
				eng.setOrigin(pobj.getPose().getCoordinate().get(CRSUtils.WGS84_2D).toVector());
				Vector2D v = (Vector2D)doc.getLinearVelocity().get(eng).getValue(); //we are just interested in the direction
				angle_rad = crs.directionToBearing(v.getX(), v.getY(), Double.NaN).get(0);
			}else{
				Vector3D v = (Vector3D)doc.getLinearVelocity().get(CRSUtils.ENGINEERING_3D).getValue(); //we are just interested in the direction
				angle_rad = crs.directionToBearing(v.getX(), v.getY(), v.getZ()).get(0);
			}
			return new AngleImpl(angle_rad, AngleUnit.RADIAN).normalize();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public static Angle getCOG(DynamicObjectCharacteristic doc){
		if (doc == null || doc.getLinearVelocity() == null || doc.getLinearVelocity().getValue() == null) 
			return null;
		CoordinateReferenceSystem crs = doc.getLinearVelocity().getCrs();
		if (crs == null)
			crs = doc.getLinearVelocity().getValue().dimensions() == 2 ? CRSUtils.ENGINEERING_2D : CRSUtils.ENGINEERING_3D;
		double angle_rad = 0;
		Velocity vel = doc.getLinearVelocity();
		if (vel.getValue().dimensions() == 2){
			Engineering2D dstCRS = new Engineering2DImpl();
			if (vel.getCrs() != null && vel.getCrs() instanceof LocalCRS)
				dstCRS.setOrigin(((LocalCRS)vel.getCrs()).getOrigin());
			Vector2D v = (Vector2D)doc.getLinearVelocity().get(dstCRS).getValue(); //we are just interested in the direction
			double l = v.getLength();
			if (l == 0){
				v.setX(0);v.setY(0);
			}else
				v = v.div(v.getLength());
			angle_rad = crs.directionToBearing(v.getX(), v.getY(), Double.NaN).get(0);
		}else{
			Vector3D v = (Vector3D)doc.getLinearVelocity().get(CRSUtils.ENGINEERING_3D).getValue(); //we are just interested in the direction
			angle_rad = crs.directionToBearing(v.getX(), v.getY(), v.getZ()).get(0);
		}
		return new AngleImpl(angle_rad, AngleUnit.RADIAN);
	}

	public static Angle getCOG(Velocity vel) {
		double angle_rad;
		CoordinateReferenceSystem crs = vel.getCrs();
		if (crs == null)
			crs = vel.getValue().dimensions() == 2 ? CRSUtils.ENGINEERING_2D : CRSUtils.ENGINEERING_3D;
		if (vel.getValue().dimensions() == 2){
			Engineering2D dstCRS = new Engineering2DImpl();
			if (vel.getCrs() != null && vel.getCrs() instanceof LocalCRS)
				dstCRS.setOrigin(((LocalCRS)vel.getCrs()).getOrigin());
			Vector2D v = (Vector2D)vel.get(dstCRS).getValue(); //we are just interested in the direction
			double l = v.getLength();
			if (l == 0){
				v.setX(0);v.setY(0);
			}else
				v = v.div(v.getLength());
			angle_rad = crs.directionToBearing(v.getX(), v.getY(), Double.NaN).get(0);
		}else{
			Vector3D v = (Vector3D)vel.get(CRSUtils.ENGINEERING_3D).getValue(); //we are just interested in the direction
			angle_rad = crs.directionToBearing(v.getX(), v.getY(), v.getZ()).get(0);
		}
		return new AngleImpl(angle_rad, AngleUnit.RADIAN);
	}
	/**
	 * This method is used to get ROT (Rate of Turn) 
	 **/
	public static AngularSpeed getRateOfTurn(PhysicalObject obj) {
		return getRateOfTurn(obj.getFirstCharacteristic(DynamicObjectCharacteristic.class, true));
	}

	public static AngularSpeed getRateOfTurn(DynamicObjectCharacteristic doc) {
		if (doc == null || doc.getAngularVelocity() == null || doc.getAngularVelocity().getValue() == null)
			return null;
		CoordinateReferenceSystem crs = doc.getAngularVelocity().getCrs();
		if (crs == null || crs instanceof WGS84CRS || crs instanceof EngineeringCRS)
			return doc.getAngularVelocity().getZ(); //using ENGINEERING_CRS as default, and rotation around the default up axis (z-axis)
		//TODO: how to handle other CRS?
		return null;
	}
	
	/**
	 * Sets the rate of turn of an physical object 
	 * writes: pobj.<DynamicObjectCharacteristic>.angularSpeed.value.z
	 * @note if an instance does not exists (e.g. DynamicObjectCharacteristic, AngularVelocity, Euler or z-Angle) it will be created
	 * @param speed
	 * @param pobj
	 */
	public static void setRateOfTurn(AngularSpeed speed, PhysicalObject pobj) {
		DynamicObjectCharacteristic doc = pobj.getFirstCharacteristic(DynamicObjectCharacteristic.class, true);
		if (doc == null)
			pobj.getCharacteristics().add(doc = new DynamicObjectCharacteristicImpl());
		setRateOfTurn(speed, doc);
	}
	
	
	public static void setRateOfTurn(AngularSpeed speed, DynamicObjectCharacteristic doc) {
		AngularVelocity av = doc.getAngularVelocity();
		if (av == null)
			doc.setAngularVelocity(av = new AngularVelocityImpl());
		setRateOfTurn(speed, av);
	}

	public static void setRateOfTurn(AngularSpeed speed, AngularVelocity av) {
		if (av.getValue() == null)
			av.setValue(new EulerImpl());
		if (av.getUnit() == null)
			av.setUnit(speed.getUnit());
		if (av.getValue().getZ() == null)
			av.getValue().setZ(new AngleImpl(speed.getAs(av.getUnit()), av.getUnit().getAngleUnit()));
		else
			av.getValue().getZ().set(speed.getAs(av.getUnit()), av.getUnit().getAngleUnit());
	}

	public static Length getLength(PhysicalObject obj) {
		if (obj == null) return null;
		return getLength(obj.getFirstCharacteristic(ObjectSurfaceInformation.class, true));
	}

	public static Length getLength(ObjectSurfaceInformation osi) {
		if (osi == null)
			return null;
		return osi.getLength();
	}
	
	public static Length getWidth(PhysicalObject obj){
		if (obj == null)
			return null;	
		return getWidth(obj.getFirstCharacteristic(ObjectSurfaceInformation.class, true));
	}

	public static Length getWidth(ObjectSurfaceInformation osi) {
		if (osi == null)
			return null;
		return osi.getWidth();
	}

	/**
	 * Sets the Orientation of the given pose, that it matches the angle and keeps the Type of the Orientation (e.g. Euler or Quaternion)
	 * @param pose
	 * @param angle
	 */
	public static void changeOrientationZ(Pose pose, Angle angle) {
		if (pose == null) return ;
		if (pose.getOrientation() == null) 
			pose.setOrientation(new EulerImpl(null, null, angle));
		else {
			Euler e = pose.getOrientation().toEuler();
			if (e.getZ() == null) 
				e.setZ(angle);
			else
				e.getZ().set(angle);
			if (pose.getOrientation() != e) //in this case the orientation is an quaternion, otherwhise the value has been set with the previous command
				((Quaternion)pose.getOrientation()).set(e.toQuaternion());
		}
	}

	
	public static void setCOG(PhysicalObject target, Angle cog) {
		DynamicObjectCharacteristic doc = target.getFirstCharacteristic(DynamicObjectCharacteristic.class, true);
		if (doc == null)
			target.getCharacteristics().add(doc = new DynamicObjectCharacteristicImpl());
		setCOG(doc, cog);
	}

	public static void setCOG(DynamicObjectCharacteristic doc, Angle cog) {
		Velocity vel = doc.getLinearVelocity();
		if (vel == null) doc.setLinearVelocity(vel = new VelocityImpl());
		Speed mag = vel.getMagnitude();
		if (mag.getValue() != mag.getValue()) // == NaN
			mag.setValue(0);
		Vector2D north = new Vector2DImpl(0, 1);
		north.rotateLocalCW(cog.getAs(AngleUnit.RADIAN));
		north.multLocal(mag.getAs(SpeedUnit.METER_PER_SECOND));
		vel.set(north, SpeedUnit.METER_PER_SECOND, vel.getCrs()); //TODO: FIXME: vel.getCRS is not correct but should work for now
	}
	
	public static void setSOG(PhysicalObject target, Speed sog) {
		DynamicObjectCharacteristic doc = target.getFirstCharacteristic(DynamicObjectCharacteristic.class, true);
		if (doc == null)
			target.getCharacteristics().add(doc = new DynamicObjectCharacteristicImpl());
		setSOG(doc, sog);
	}
	public static void setSOG(DynamicObjectCharacteristic doc, Speed sog) {
		Velocity vel = doc.getLinearVelocity();
		if (vel == null) {
			doc.setLinearVelocity(vel = new VelocityImpl());
			return ; //we can not encode the magnitude if we have no direction
		}
		Vector2D v = (Vector2D)vel.getValue();
		v.normalizeLocal();
		v.multLocal(sog.getValue());
		vel.set(v, sog.getUnit(), vel.getCrs()); //TODO: FIXME: vel.getCRS is not correct but should work for now
	}
	
	public static boolean setCOGAndSOG(PhysicalObject target, Angle cog, Speed sog) {
		DynamicObjectCharacteristic doc = target.getFirstCharacteristic(DynamicObjectCharacteristic.class, true);
		if (doc == null)
			target.getCharacteristics().add(doc = new DynamicObjectCharacteristicImpl());
		return setCOGAndSOG(doc, cog, sog);
	}

	public static boolean setCOGAndSOG(DynamicObjectCharacteristic doc, Angle cog, Speed sog) {
		if (cog == null || sog == null) 
			return false;
		Velocity vel = doc.getLinearVelocity();
		if (vel == null) {
			doc.setLinearVelocity(vel = new VelocityImpl());
		}
		Vector2D v = new Vector2DImpl(0, 1);
		v.rotateLocalCW(cog.getAs(AngleUnit.RADIAN));
		v.multLocal(sog.getAs(SpeedUnit.METER_PER_SECOND));
		vel.set(v, SpeedUnit.METER_PER_SECOND, vel.getCrs()); //TODO: FIXME: vel.getCRS is not correct but should work for now
		return true;
	}
	
	
	
	
	
	/**
	 * Calculates the time to closed point of approach
	 * @warn the calculation is done in cartesian space, e.g. there is no consideration of earth form
	 *  
	 * @param pos1 location of the first object
	 * @param pos2 location of the other object
	 * @param vel1 velocity vector of the first object
	 * @param vel2 velocity vector of the second object
	 * @return the time to point of closest approach or null, if both velocity vectors are allmost parallel. 
	 */
	public static Time getTimeToClosedPointOfApproach(Coordinate pos1, Coordinate pos2, Velocity vel1, Velocity vel2) {
		CoordinateReferenceSystem crs = new Engineering2DImpl(pos1.get(CRSUtils.WGS84_2D).toVector());//calculate in local space - local for l1
		Vector2D P1 = new Vector2DImpl(0,0);
		Vector2D P2 = pos2.get(crs).toVector2D();

		Vector2D v1 = (Vector2D) vel1.get(crs).getAs(SpeedUnit.METER_PER_SECOND);//TODO: check if realy is a 2D vector
		Vector2D v2 = (Vector2D) vel2.get(crs).getAs(SpeedUnit.METER_PER_SECOND);//TODO: check if realy is a 2D vector
		
		Vector2D dv = v1.sub(v2);		
		double dv2 = dv.dot(dv);
		if (dv2 < 0.00001)
			return null; //they are allmost parallel
		
		Vector2D w0 = P1.sub(P2);
		return new TimeImpl(-w0.dot(dv) / dv2, TimeUnit.SECOND);			
	}
	
	
	public static Time getTimeToClosedPointOfApproach(Coordinate pos1, Angle course1, Speed speed1, Coordinate pos2, Angle course2, Speed speed2) {
		CoordinateReferenceSystem crs = new Engineering2DImpl(pos1.get(CRSUtils.WGS84_2D).toVector());//calculate in local space - local for l1
		Vector2D north = new Vector2DImpl(0, 1);
		Vector2D v1 = north.rotateCW(course1.getAs(AngleUnit.RADIAN)).mult(speed1.getAs(SpeedUnit.METER_PER_SECOND));
		Vector2D v2 = north.rotateCW(course2.getAs(AngleUnit.RADIAN)).mult(speed2.getAs(SpeedUnit.METER_PER_SECOND));
		return getTimeToClosedPointOfApproach(pos1, pos2, new VelocityImpl(v1, SpeedUnit.METER_PER_SECOND, crs), new VelocityImpl(v2, SpeedUnit.METER_PER_SECOND, crs));
	}
	
	
	/**
	 * Returns the distance of both objects when both reach their closed point of approach
	 * @param pos1 location of the first object
	 * @param pos2 location of the other object
	 * @param vel1 velocity vector of the first object
	 * @param vel2 velocity vector of the second object
	 * @return the miss distance, e.g. the nearest distance of both objects
	 */
	public static Distance getDistanceAtClosedPointOfApproach(Coordinate pos1, Coordinate pos2, Velocity vel1, Velocity vel2) {
		Time tcpa = getTimeToClosedPointOfApproach(pos1, pos2, vel1, vel2);
		//calculate both pcpa's (position at closed point of approach)
		CoordinateReferenceSystem crs = new Engineering2DImpl(pos1.get(CRSUtils.WGS84_2D).toVector());//calculate in local space - local for l1
		CoordinateImpl pcpa1 = new CoordinateImpl(pos1.get(crs).toVector2D().add(((Vector2D) vel1.get(crs).getAs(SpeedUnit.METER_PER_SECOND)).mult(tcpa.getAs(TimeUnit.SECOND))), crs);
		CoordinateImpl pcpa2 = new CoordinateImpl(pos2.get(crs).toVector2D().add(((Vector2D) vel2.get(crs).getAs(SpeedUnit.METER_PER_SECOND)).mult(tcpa.getAs(TimeUnit.SECOND))), crs);
		
		return pcpa1.getDistance(pcpa2);
	}
	
	public static Distance getDistanceAtClosedPointOfApproach(Coordinate pos1, Angle course1, Speed speed1, Coordinate pos2, Angle course2, Speed speed2) {
		CoordinateReferenceSystem crs = new Engineering2DImpl(pos1.get(CRSUtils.WGS84_2D).toVector());//calculate in local space - local for l1
		Vector2D north = new Vector2DImpl(0, 1);
		Vector2D v1 = north.rotateCW(course1.getAs(AngleUnit.RADIAN)).mult(speed1.getAs(SpeedUnit.METER_PER_SECOND));
		Vector2D v2 = north.rotateCW(course2.getAs(AngleUnit.RADIAN)).mult(speed2.getAs(SpeedUnit.METER_PER_SECOND));
		return getDistanceAtClosedPointOfApproach(pos1, pos2, new VelocityImpl(v1, SpeedUnit.METER_PER_SECOND, crs), new VelocityImpl(v2, SpeedUnit.METER_PER_SECOND, crs));
	}

	/**
	 * sets the velocity of an DynamicObjectCharacteristic (doc). 
	 * Thereby check if there is already an instance of each complex object. In case an instance could been found, the value is assigned instead of replaceing the instance. 
	 * if no instance could be found, an instance is created and assigned
	 * @param doc 	target
	 * @param velX	x component of velocity in relation to the crs
	 * @param velY	y component of velocity in relation to the crs
	 * @param velZ	z component of velocity in relation to the crs
	 * @param speedUnit unit of velX, velY and velZ
	 * @param crs coordinate reference system of velX, velY, velZ @note this is the only value that will be set every time
	 */
	public static void setLinearVelocity(DynamicObjectCharacteristic doc, double velX, double velY, double velZ, SpeedUnit speedUnit, CoordinateReferenceSystem crs) {
		Velocity vel = doc.getLinearVelocity();
		if (vel == null) doc.setLinearVelocity(vel = new VelocityImpl());
		Vector v = vel.getValue();
		if (v == null || v.dimensions() < 3) vel.setValue(v = new Vector3DImpl());
		v.set(0, velX);
		v.set(1, velY);
		v.set(2, velZ);
		if (vel.getUnit() == null) vel.setUnit(new SpeedUnitImpl(speedUnit));
		else vel.getUnit().set(speedUnit);
		vel.setCrs(crs);
	}
	
}
