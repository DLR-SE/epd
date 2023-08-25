package de.emir.model.universal.crs.impl;

import java.util.Arrays;
import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.crs.WGS843D;
import de.emir.model.universal.crs.impl.WGS84CRSImpl;
import de.emir.model.universal.crs.internal.calc.IGeodeticCalculator;
import de.emir.model.universal.crs.internal.calc.VincentCalculator;
import de.emir.model.universal.math.Vector;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 Defines the default global CRS to be used in eMIR, for 3 dimensions. 
 * The WGS843D differes from its 2D version (WGS842D) that the z component of a coordinate is interpreted as altitude (in meters). 
 * @generated 
 */
@UMLImplementation(classifier = WGS843D.class)
public class WGS843DImpl extends WGS84CRSImpl implements WGS843D  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public WGS843DImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public WGS843DImpl(final WGS843D _copy) {
		super(_copy);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public WGS843DImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, CoordinateSystem _cs) {
		super(_name,_allias,_remarks,_observers,_identifier,_cs);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CrsPackage.Literals.WGS843D;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "WGS843DImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}

	@Override
	public int dimension() {
		return 3;
	}

	private IGeodeticCalculator mCalculator = null;
	private IGeodeticCalculator getCalculator() {
		if (mCalculator == null)
			mCalculator = new VincentCalculator();
		return mCalculator;
	}
	
	@Override
	public Vector getDirectionToNorth() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public List<Double> directionToBearing(double x, double y, double z) {
		double azimuth = getCalculator().getAzimuth(0, 0, y, x); //the WGS84 is always centered at 0,0 and we have no other options
		if (z != z) //z=NaN
			return Arrays.asList(azimuth, 0.0);
		//? does it matter on which plane we do the calculation?
		
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public Vector bearingToDirection(double yaw, double pitch) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public CoordinateReferenceSystem copy() {
		return new WGS843DImpl(this);
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
		return 1; //no parameters, so always the same
	}
}
