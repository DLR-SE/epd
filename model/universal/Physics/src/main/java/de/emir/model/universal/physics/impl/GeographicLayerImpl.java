package de.emir.model.universal.physics.impl;

import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.physics.GeographicLayer;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.spatial.SpatialLayer;
import de.emir.model.universal.physics.impl.EnvironmentLayerImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;


/**
 *	@generated 
 */
@UMLImplementation(classifier = GeographicLayer.class)
public class GeographicLayerImpl extends EnvironmentLayerImpl implements GeographicLayer  
{
	
	
	/**
	 *	@generated 
	 */
	private List<SpatialLayer> mSpatials = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public GeographicLayerImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public GeographicLayerImpl(final GeographicLayer _copy) {
		super(_copy);
		mSpatials = _copy.getSpatials();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public GeographicLayerImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, List<SpatialLayer> _spatials) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mSpatials = _spatials; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.GeographicLayer;
	}

	/**
	 *	@generated 
	 */
	public List<SpatialLayer> getSpatials() {
		if (mSpatials == null) {
			mSpatials = new UContainmentList<SpatialLayer>(this, PhysicsPackage.theInstance.getGeographicLayer_spatials()); 
		}
		return mSpatials;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "GeographicLayerImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
