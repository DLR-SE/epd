package de.emir.model.universal.crs.impl;

import java.util.Arrays;
import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.crs.impl.PolarCRSImpl;
import de.emir.model.universal.crs.Polar2D;
import de.emir.model.universal.math.Vector;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Polar2D.class)
public class Polar2DImpl extends PolarCRSImpl implements Polar2D  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public Polar2DImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public Polar2DImpl(final Polar2D _copy) {
		super(_copy);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public Polar2DImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, CoordinateSystem _cs, Vector _origin, List<Double> _orientationOffset) {
		super(_name,_allias,_remarks,_observers,_identifier,_cs,_origin,_orientationOffset);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CrsPackage.Literals.Polar2D;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "Polar2DImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" orientationOffset = " + getOrientationOffset() + 
		"}";
	}

	@Override
	public int dimension() {
		return 2;
	}

	@Override
	public Vector getDirectionToNorth() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public List<Double> directionToBearing(double x, double y, double z) {
		return Arrays.asList(y);
	}

	@Override
	public Vector bearingToDirection(double yaw, double pitch) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public CoordinateReferenceSystem copy() {
		return new Polar2DImpl(this);
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
		int z = (int) (getOrientationOffset().get(0) * 100);
		int hash = 17;
        hash = ((hash + x) << 5) - (hash + x);
        hash = ((hash + y) << 5) - (hash + y);
        hash = ((hash + z) << 5) - (hash + z);
        return hash;
	}
}
