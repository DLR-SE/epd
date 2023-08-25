package de.emir.model.universal.crs.impl;

import java.util.ArrayList;
import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.crs.Engineering3D;
import de.emir.model.universal.crs.impl.EngineeringCRSImpl;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector3D;
import de.emir.model.universal.math.impl.Vector3DImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import net.jafama.FastMath;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Engineering3D.class)
public class Engineering3DImpl extends EngineeringCRSImpl implements Engineering3D  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public Engineering3DImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public Engineering3DImpl(final Engineering3D _copy) {
		super(_copy);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public Engineering3DImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, CoordinateSystem _cs, Vector _origin, List<Double> _orientationOffset) {
		super(_name,_allias,_remarks,_observers,_identifier,_cs,_origin,_orientationOffset);
	}
	
	public Engineering3DImpl(Vector origin) {
		setOrigin(origin);
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CrsPackage.Literals.Engineering3D;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "Engineering3DImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" orientationOffset = " + getOrientationOffset() + 
		"}";
	}

	@Override
	public int dimension() {
		return 3;
	}

	@Override
	public Vector getDirectionToNorth() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public List<Double> directionToBearing(double dx, double dy, double dz) {
		double lsq = dx * dx + dy * dy + dz*dz;
		double l = StrictMath.sqrt(lsq);
		// Divide by zero check
        if(l < 1e-6f)
            l = 1e-6f;
        double fy = dy / l; //dot product with 0,1,0 // == north
        double fz = dz / l;
        if (fy < -1) fy = -1; if (fy > 1) fy = 1; //clamp(f, -1, 1);
        if (fz < -1) fz = -1; if (fz > 1) fz = 1; //clamp(f, -1, 1);
        double angleNorth = FastMath.acos(fy);
        double angleUp = FastMath.acos(fz);
        
		double crossY = -1 * dx; //crossproduct with 0,1
		if (crossY>0)          
			angleNorth = (Math.PI*2.0)- angleNorth;
		double crossZ = -1 * dy;
		if (crossZ > 0)
			angleUp = (Math.PI * 2.0) - angleUp;
		ArrayList<Double> out = new ArrayList<>();
		out.add(angleNorth);
		out.add(angleUp);
		return out;
	}

	@Override
	public Vector bearingToDirection(double yaw, double pitch) {
		//actually this is the rotation of the vector 0,1,0 (north) with a quaternion build from yaw and pitch
		double angle;
		double sinY, sinZ, sinX, cosY, cosZ, cosX;
        angle = yaw * 0.5f;
        sinZ = Math.sin(angle);
        cosZ = Math.cos(angle);
        angle = 0 * 0.5f;
        sinY = Math.sin(angle);
        cosY = Math.cos(angle);
        angle = pitch * 0.5f;
        sinX = Math.sin(angle);
        cosX = Math.cos(angle);

        // variables used to reduce multiplication calls.
        double cosYXcosZ = cosY * cosZ;
        double sinYXsinZ = sinY * sinZ;
        double cosYXsinZ = cosY * sinZ;
        double sinYXcosZ = sinY * cosZ;

        //using setter to support the callbacks / eAdapters
        double mW = (cosYXcosZ * cosX - sinYXsinZ * sinX);
        double mX = (cosYXcosZ * sinX + sinYXsinZ * cosX);
        double mY = (sinYXcosZ * cosX + cosYXsinZ * sinX);
        double mZ = (cosYXsinZ * cosX - sinYXcosZ * sinX);
		
        double vx = 0, vy = 1, vz = 0;
        double xx = mW * mW * vx + 2 * mY * mW * vz - 2 * mZ * mW * vy + mX * mX
                * vx + 2 * mY * mX * vy + 2 * mZ * mX * vz - mZ * mZ * vx - mY
                * mY * vx;
        double yy = 2 * mX * mY * vx + mY * mY * vy + 2 * mZ * mY * vz + 2 * mW
                * mZ * vx - mZ * mZ * vy + mW * mW * vy - 2 * mX * mW * vz - mX
                * mX * vy;
        double zz = 2 * mX * mZ * vx + 2 * mY * mZ * vy + mZ * mZ * vz - 2 * mW
                * mY * vx - mY * mY * vz + 2 * mW * mX * vy - mX * mX * vz + mW
                * mW * vz;
        return new Vector3DImpl(xx, yy, zz);
	}

	@Override
	public CoordinateReferenceSystem copy() {
		return new Engineering3DImpl(this);
	}

	@Override
	public double getDistance(Vector loc1, Vector loc2) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public Vector getDistanceAndAzimuth(Vector loc1, Vector loc2) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	@Override
	public int getHash() {
		double[] o = getOrigin().toArray();
		int x = (int) (o[0] * 100000);
		int y = (int) (o[1] * 100000);
		int z = o.length == 3 ? (int)(o[2] * 100000) : 0;
		int w = (int) (getOrientationOffset().get(0) * 100);
		int v = getOrientationOffset().size() == 2 ? (int) (getOrientationOffset().get(1) * 100) : 0;
		int hash = 17;
        hash = ((hash + x) << 5) - (hash + x);
        hash = ((hash + y) << 5) - (hash + y);
        hash = ((hash + z) << 5) - (hash + z);
        hash = ((hash + w) << 5) - (hash + w);
        hash = ((hash + v) << 5) - (hash + v);
        return hash;
	}
}
