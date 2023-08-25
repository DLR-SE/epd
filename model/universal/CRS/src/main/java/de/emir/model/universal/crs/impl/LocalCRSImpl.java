package de.emir.model.universal.crs.impl;

import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.crs.LocalCRS;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.crs.impl.CoordinateReferenceSystemImpl;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.math.impl.Vector4DImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.model.universal.math.impl.Vector3DImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.lists.ListUtils;


/**
 @Brief local coordinate reference system, only valid in a "small" area around its global position
 * The abstract local CRS indicates is a CRS that is only valid in a certain area (at least in terms of accuracy)
  * therefore it contains a field to define its position within a global coordinate reference system
 * @warn since units are not known yet (defined in unit package) in this class, all distances are defined in Meters (distances), radians (angles) or WGS84 (coordinates)  
 * @generated 
 */
@UMLImplementation(classifier = LocalCRS.class)
abstract public class LocalCRSImpl extends CoordinateReferenceSystemImpl implements LocalCRS  
{
	
	
	/**
	 returns the origin of the CRS within a global CRS (e.g. WGS84)
	 *  @note the dimension of the origin vector has to be the same as CoordinateReferenceSystem.dimension()
	 * 
	 * @generated not
	 */
	private Vector mOrigin = new Vector2DImpl();
	/**
	 returns the orientation offset of this CRS, relativ to a global CRS (e.g. WGS84) 
	 * @return a one dimensional list with the azimuth in radians, if the dimension is 2, otherwise a 3 dimensional rotation in the order [pitch, roll, yaw] (also in radians)
	 * @generated 
	 */
	private List<Double> mOrientationOffset = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public LocalCRSImpl(){
		super();
		//set the default values and assign them to this instance 
		setOrigin(mOrigin);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public LocalCRSImpl(final LocalCRS _copy) {
		super(_copy);
		getOrientationOffset().addAll(_copy.getOrientationOffset());
		mOrigin = _copy.getOrigin();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public LocalCRSImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, CoordinateSystem _cs, Vector _origin, List<Double> _orientationOffset) {
		super(_name,_allias,_remarks,_observers,_identifier,_cs);
		getOrientationOffset().addAll(_orientationOffset);
		mOrigin = _origin; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CrsPackage.Literals.LocalCRS;
	}

	/**
	 returns the origin of the CRS within a global CRS (e.g. WGS84)
	 *  @note the dimension of the origin vector has to be the same as CoordinateReferenceSystem.dimension()
	 * 
	 * @generated 
	 */
	public void setOrigin(Vector _origin) {
		Notification<Vector> notification = basicSet(mOrigin, _origin, CrsPackage.Literals.LocalCRS_origin);
		mOrigin = _origin;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 returns the origin of the CRS within a global CRS (e.g. WGS84)
	 *  @note the dimension of the origin vector has to be the same as CoordinateReferenceSystem.dimension()
	 * 
	 * @generated 
	 */
	public Vector getOrigin() {
		return mOrigin;
	}

	/**
	 returns the orientation offset of this CRS, relativ to a global CRS (e.g. WGS84) 
	 * @return a one dimensional list with the azimuth in radians, if the dimension is 2, otherwise a 3 dimensional rotation in the order [pitch, roll, yaw] (also in radians)
	 * @generated 
	 */
	public List<Double> getOrientationOffset() {
		if (mOrientationOffset == null) {
			mOrientationOffset = ListUtils.asList(0.0); 
		}
		return mOrientationOffset;
	}

	/**
	* @generated not
	*/
	@Override
	public String toString() {
		return "LocalCRSImpl{" + getNameAsString() + 
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" orientationOffset = " + getOrientationOffset() + 
		"}";
	}
	
	
	
	@Override
	public int dimension() {
		Vector o = getOrigin();
		if (o == null) return 0;
		return o.dimensions();
	}
}
