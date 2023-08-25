package de.emir.model.universal.crs.impl;

import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.crs.Polar3D;
import de.emir.model.universal.crs.impl.PolarCRSImpl;
import de.emir.model.universal.math.Vector;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Polar3D.class)
public class Polar3DImpl extends PolarCRSImpl implements Polar3D  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public Polar3DImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public Polar3DImpl(final Polar3D _copy) {
		super(_copy);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public Polar3DImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, CoordinateSystem _cs, Vector _origin, List<Double> _orientationOffset) {
		super(_name,_allias,_remarks,_observers,_identifier,_cs,_origin,_orientationOffset);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CrsPackage.Literals.Polar3D;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "Polar3DImpl{" +
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
	public List<Double> directionToBearing(double x, double y, double z) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public Vector bearingToDirection(double yaw, double pitch) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public CoordinateReferenceSystem copy() {
		return new Polar3DImpl(this);
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
		int z = (int) (o[2] * 100000);
		int w = (int) (getOrientationOffset().get(0) * 100);
		int v = (int) (getOrientationOffset().get(1) * 100);
		int hash = 17;
        hash = ((hash + x) << 5) - (hash + x);
        hash = ((hash + y) << 5) - (hash + y);
        hash = ((hash + z) << 5) - (hash + z);
        hash = ((hash + w) << 5) - (hash + w);
        hash = ((hash + v) << 5) - (hash + v);
        return hash;
	}
}
