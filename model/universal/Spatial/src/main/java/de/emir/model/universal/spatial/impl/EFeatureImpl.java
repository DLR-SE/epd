package de.emir.model.universal.spatial.impl;

import de.emir.model.universal.spatial.EFeature;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = EFeature.class)
abstract public class EFeatureImpl extends UObjectImpl implements EFeature  
{
	
	
	/**
	 *	@generated 
	 */
	private String mFeatureType = "";
	/**
	 *	@generated 
	 */
	private Geometry mGeometry = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public EFeatureImpl(){
		super();
		//set the default values and assign them to this instance 
		setGeometry(mGeometry);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public EFeatureImpl(final EFeature _copy) {
		mFeatureType = _copy.getFeatureType();
		mGeometry = _copy.getGeometry();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public EFeatureImpl(String _featureType, Geometry _geometry) {
		mFeatureType = _featureType;
		mGeometry = _geometry; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SpatialPackage.Literals.EFeature;
	}

	/**
	 *	@generated 
	 */
	public void setFeatureType(String _featureType) {
		if (needNotification(SpatialPackage.Literals.EFeature_featureType)){
			String _oldValue = mFeatureType;
			mFeatureType = _featureType;
			notify(_oldValue, mFeatureType, SpatialPackage.Literals.EFeature_featureType, NotificationType.SET);
		}else{
			mFeatureType = _featureType;
		}
	}

	/**
	 *	@generated 
	 */
	public String getFeatureType() {
		return mFeatureType;
	}

	/**
	 *	@generated 
	 */
	public void setGeometry(Geometry _geometry) {
		Notification<Geometry> notification = basicSet(mGeometry, _geometry, SpatialPackage.Literals.EFeature_geometry);
		mGeometry = _geometry;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Geometry getGeometry() {
		return mGeometry;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "EFeatureImpl{" +
		" featureType = " + getFeatureType() + 
		"}";
	}
}
