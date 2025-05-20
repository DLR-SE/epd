package de.emir.model.universal.crs.impl;

import java.util.ArrayList;
import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


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

	/**
	 * @inheritDoc
	 */
	@Override
	public int dimension() {
		return 2;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Vector getDirectionToNorth() {
		return new Vector2DImpl(0, 1);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<Double> directionToBearing(double dx, double dy, double dz) {
		double result = getAzimuth(0, 0, dx, dy);
		List<Double> outList = new ArrayList<>(1);
		outList.add(result);
		return outList;
	}

	/**
	 * @inheritDoc
	 * @implNote pitch has no influence in 2 dimensions
	 */
	@Override
	public Vector bearingToDirection(double yaw, double pitch) {
		double s = Math.sin(yaw);
		double c = Math.cos(yaw);

		return new Vector2DImpl(s, c);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public CoordinateReferenceSystem copy() {
		return new Engineering2DImpl(this);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public double getDistance(Vector fromLocation, Vector toLocation) {
		double deltaX = fromLocation.get(0) - toLocation.get(0);
		double deltaY = fromLocation.get(1) - toLocation.get(1);

		return Math.sqrt(deltaX*deltaX+deltaY*deltaY);
	}

	/**
	 * calculate azimuth
	 * @return azimuth in rad
	 */
	private double getAzimuth(double xFrom, double yFrom, double xTo, double yTo) {
		double deltaX = xTo - xFrom;
		double deltaY = yTo - yFrom;

		//z is ignored in 2D
		double azimuth = Math.atan2(deltaX, deltaY); // note that xFrom and yFrom are inverted to atan2 javadoc because azimuth starts at Y-Axis towards the X-Axis
		if (azimuth < 0) {
			azimuth = 2*Math.PI + azimuth;
		}
		return azimuth;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Vector getDistanceAndAzimuth(Vector fromLocation, Vector toLocation) {
		double distance = getDistance(fromLocation, toLocation);
		double azimuth = getAzimuth(fromLocation.get(0), fromLocation.get(1), toLocation.get(0), toLocation.get(1));

		return new Vector2DImpl(distance, azimuth);
	}

	/**
	 * @inheritDoc
	 */
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
