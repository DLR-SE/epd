package de.emir.model.universal.crs.impl;

import java.util.ArrayList;
import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.crs.impl.EngineeringCRSImpl;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import net.jafama.FastMath;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Engineering2D.class)
public class Engineering2DImpl extends EngineeringCRSImpl implements Engineering2D  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public Engineering2DImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public Engineering2DImpl(final Engineering2D _copy) {
		super(_copy);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public Engineering2DImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, CoordinateSystem _cs, Vector _origin, List<Double> _orientationOffset) {
		super(_name,_allias,_remarks,_observers,_identifier,_cs,_origin,_orientationOffset);
	}
	
	public Engineering2DImpl(Vector origin) {
		setOrigin(origin);
	}

	public Engineering2DImpl(Vector origin, double headingRad) {
		setOrigin(origin);
		getOrientationOffset().set(0, headingRad);
	}
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CrsPackage.Literals.Engineering2D;
	}

	/**
	* @generated not
	*/
	@Override
	public String toString() {
		return "Engineering2DImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" orientationOffset = " + getOrientationOffset() +
		" origin = " + getOrigin() + 
		"}";
	}

	@Override
	public int dimension() {
		return 2;
	}

	@Override
	public Vector getDirectionToNorth() {
		return new Vector2DImpl(0, 1);
	}

	@Override
	public List<Double> directionToBearing(double dx, double dy, double dz) {
		double lsq = dx * dx + dy * dy;
		double l = StrictMath.sqrt(lsq);
		// Divide by zero check
        if(l < 1e-6f)
            l = 1e-6f;
        double f = dy / l; //dot product with 0,1
        if (f < -1) f = -1; if (f > 1) f = 1; //clamp(f, -1, 1);
        double angle = FastMath.acos(f);
        
		double cross = -1 * dx; //crossproduct with 0,1
		if (cross>0)          
			angle = (Math.PI*2.0)- angle;
		ArrayList<Double> out = new ArrayList<>();
		out.add(angle);
		return out;
	}

	@Override
	public Vector bearingToDirection(double yaw, double pitch) {
		double c = Math.cos(yaw);
		double s = Math.sin(yaw);
		if (c != c || s != s || Double.isInfinite(c) || Double.isInfinite(s)){
			//TODO: FIXME: This is just a workaround, need to find out where the true error is - how this could be called with Infinity or NaN
			return new Vector2DImpl();
		}
		return new Vector2DImpl(s, c);
	}

	@Override
	public CoordinateReferenceSystem copy() {
		return new Engineering2DImpl(this);
	}


	@Override
	public double getDistance(Vector _loc1, Vector _loc2) {
		double dx = _loc1.get(0) - _loc2.get(0);
		double dy = _loc1.get(1) - _loc2.get(1);
		double dz = 0;
		int dim = Math.min(_loc1.dimensions(), _loc2.dimensions());
		if (dim == 3)
			dz = _loc1.get(2) - _loc2.get(2);
		return Math.sqrt(dx*dx+dy*dy+dz*dz);
	}

	public double getAzimuth(double x, double y, double z, double x2, double y2, double z2) {
		double dx = x2 - x;
		double dy = y2 - y;
		return directionToBearing(dx, dy, 0).get(0); //z is ignored in 2D
	}
	
	@Override
	public Vector getDistanceAndAzimuth(Vector _loc1, Vector _loc2) {
		int dim = Math.min(_loc1.dimensions(), _loc2.dimensions());
		if (dim == 2){
			double d = getDistance(_loc1, _loc2);
			Vector2D loc1 = (Vector2D)_loc1; //cast is faster, this way we do not have to use the delegate operatins
			Vector2D loc2 = (Vector2D)_loc2;
			double a = getAzimuth(loc1.getX(), loc1.getY(), Double.NaN, loc2.getX(), loc2.getY(), Double.NaN);
			return new Vector2DImpl(d, a);
		}
		return null;
	}

	
	@Override
	public int getHash() {
		double[] o = getOrigin().toArray();
		int x = (int) (o[0] * 100000);
		int y = (int) (o[1] * 100000);
		int z = (int) (getOrientationOffset().get(0) * 100);
		int hash = 17;
        hash = ((hash + x) << 5) - (hash + x);
        hash = ((hash + y) << 5) - (hash + y);
        hash = ((hash + z) << 5) - (hash + z);
        return hash;
	}
}
