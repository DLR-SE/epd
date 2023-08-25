package de.emir.model.universal.spatial.impl;

import java.util.List;

import de.emir.model.universal.spatial.EFeature;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.SpatialLayer;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.spatial.delegate.ISpatialLayerDelegationInterface;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;


/**
 *	@generated 
 */
@UMLImplementation(classifier = SpatialLayer.class)
public class SpatialLayerImpl extends UObjectImpl implements SpatialLayer  
{
	
	
	/**
	 *	@generated 
	 */
	private String mName = "";
	/**
	 *	@generated 
	 */
	private List<EFeature> mFeatures = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public SpatialLayerImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public SpatialLayerImpl(final SpatialLayer _copy) {
		mName = _copy.getName();
		mFeatures = _copy.getFeatures();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public SpatialLayerImpl(String _name, List<EFeature> _features) {
		mName = _name;
		mFeatures = _features; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SpatialPackage.Literals.SpatialLayer;
	}

	/**
	 *	@generated 
	 */
	public void setName(String _name) {
		if (needNotification(SpatialPackage.Literals.SpatialLayer_name)){
			String _oldValue = mName;
			mName = _name;
			notify(_oldValue, mName, SpatialPackage.Literals.SpatialLayer_name, NotificationType.SET);
		}else{
			mName = _name;
		}
	}

	/**
	 *	@generated 
	 */
	public String getName() {
		return mName;
	}

	/**
	 *	@generated 
	 */
	public List<EFeature> getFeatures() {
		if (mFeatures == null) {
			mFeatures = new UContainmentList<EFeature>(this, SpatialPackage.theInstance.getSpatialLayer_features()); 
		}
		return mFeatures;
	}

	

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public List<EFeature> queryFeatures(final Geometry geom, final boolean exactQuery)
	{
		//TODO: 
		// 
		//  * returns all features that intersect with the given query geometry (geom). 
		//  * if exactQuery is set to false, only the corresponding envelopes will be checked, otherwise a full intersection test is performed (slower)
		//  
		throw new UnsupportedOperationException("queryFeatures not yet implemented");
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "SpatialLayerImpl{" +
		" name = " + getName() + 
		"}";
	}
}
