package de.emir.model.universal.crs.impl;

import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.crs.NativeCRS;
import de.emir.model.universal.crs.impl.CoordinateReferenceSystemImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.model.universal.math.Vector;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 generic coordinate reference system, that can either be specified using an EPSG code (like 4326 for WGS84) or an WKT string 
 * @generated 
 */
@UMLImplementation(classifier = NativeCRS.class)
public class NativeCRSImpl extends CoordinateReferenceSystemImpl implements NativeCRS  
{
	
	
	/**
	 wkt string, describing the native coordinate reference system 
	 * @generated 
	 */
	private String mWkt = "";
	/**
	 *	Default constructor
	 *	@generated
	 */
	public NativeCRSImpl(){
		super();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public NativeCRSImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, CoordinateSystem _cs, String _wkt) {
		super(_name,_allias,_remarks,_observers,_identifier,_cs);
		mWkt = _wkt;
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public NativeCRSImpl(final NativeCRS _copy) {
		super(_copy);
		mWkt = _copy.getWkt();
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CrsPackage.Literals.NativeCRS;
	}

	/**
	 wkt string, describing the native coordinate reference system 
	 * @generated 
	 */
	public void setWkt(String _wkt) {
		if (needNotification(CrsPackage.Literals.NativeCRS_wkt)){
			String _oldValue = mWkt;
			mWkt = _wkt;
			notify(_oldValue, mWkt, CrsPackage.Literals.NativeCRS_wkt, NotificationType.SET);
		}else{
			mWkt = _wkt;
		}
	}

	/**
	 wkt string, describing the native coordinate reference system 
	 * @generated 
	 */
	public String getWkt() {
		return mWkt;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "NativeCRSImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" wkt = " + getWkt() + 
		"}";
	}

	@Override
	public int dimension() {
		throw new UnsupportedOperationException("Not yet implemented");
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
		return new NativeCRSImpl(this);
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
	public double[] getTarget(double[] origin, double distance_meter, double azimuth_radian, double polar_radian) {
		double[] wgs = CRSUtils.transform(origin, this, CRSUtils.WGS84_2D); //TODO: change to WGS_3D
		double[] target = CRSUtils.getTarget(wgs, distance_meter, azimuth_radian, polar_radian, CRSUtils.WGS84_2D);
		return CRSUtils.transform(target, CRSUtils.WGS84_2D, this);
	}
	
	@Override
	public int getHash() {
		return getWkt().hashCode();
	}
}
