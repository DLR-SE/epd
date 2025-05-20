package de.emir.model.universal.crs.impl;

import java.util.Arrays;
import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.crs.impl.WGS84CRSImpl;
import de.emir.model.universal.crs.WGS842D;
import de.emir.model.universal.crs.internal.calc.IGeodeticCalculator;
import de.emir.model.universal.crs.internal.calc.VincentCalculator;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;


/**
 * Defines the default global coordinate reference system (WGS84) for eMIR with 2 dimensions.
 * The WGS842D CRS corresponds to the EPSG code: urn:ogc:def:crs:EPSG:6.6:4326 (defined by GeoTools)
 * @note since, the order of WGS84 coordinates differes between different applications (either lat/lon or lon/lat)
 * we define the lat/lon order to be the default order for WGS84 in eMIR (that's the reason to not use the EPSG:4326)
 * Thereby we follow the chartographic order instead of the informatics/mathematics point of view. 
 * @generated 
 */
@UMLImplementation(classifier = WGS842D.class)
public class WGS842DImpl extends WGS84CRSImpl implements WGS842D  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public WGS842DImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public WGS842DImpl(final WGS842D _copy) {
		super(_copy);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public WGS842DImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, CoordinateSystem _cs) {
		super(_name,_allias,_remarks,_observers,_identifier,_cs);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CrsPackage.Literals.WGS842D;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "WGS842DImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
	
	private IGeodeticCalculator mCalculator = null;
	private IGeodeticCalculator getCalculator() {
		if (mCalculator == null)
			mCalculator = new VincentCalculator();
		return mCalculator;
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
	public List<Double> directionToBearing(double x, double y, double z) {
		return Arrays.asList(getCalculator().getAzimuth(0, 0, x, y)); //the WGS84 is always centered at 0,0 and we have no other options
	}

	@Override
	public Vector bearingToDirection(double yaw, double pitch) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public CoordinateReferenceSystem copy() {
		return new WGS842DImpl(this);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public double getDistance(Vector loc1, Vector loc2) {
		assert loc1.dimensions() >= 2;
		assert loc2.dimensions() >= 2;
		return getCalculator().getDistance(loc1.get(0), loc1.get(1), loc2.get(0), loc2.get(1));
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Vector getDistanceAndAzimuth(Vector loc1, Vector loc2) {
		assert loc1.dimensions() >= 2;
		assert loc2.dimensions() >= 2;
		double[] r = getCalculator().getDistanceAndAzimuth(loc1.get(0), loc1.get(1), loc2.get(0), loc2.get(1));
		return new Vector2DImpl(r);
	}
	
	@Override
	public int getHash() {
		return 0; //no parameters, so always the same
	}

	@Override
	public double[] getTarget(double[] center, double distance_meter, double azimuth_radian, double polar_radian) {
		GeodesicData dest = Geodesic.WGS84.Direct(center[0], center[1], Math.toDegrees(azimuth_radian), distance_meter);
		return new double[]{dest.lat2, dest.lon2};
	}
	
}
