package de.emir.model.universal.physics.impl;

import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.physics.ObjectLayer;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.physics.impl.EnvironmentLayerImpl;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;


/**
 *	@generated 
 */
@UMLImplementation(classifier = ObjectLayer.class)
public class ObjectLayerImpl extends EnvironmentLayerImpl implements ObjectLayer  
{
	
	
	/**
	 *	@generated 
	 */
	private List<LocatableObject> mObjects = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ObjectLayerImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ObjectLayerImpl(final ObjectLayer _copy) {
		super(_copy);
		mObjects = _copy.getObjects();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ObjectLayerImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, List<LocatableObject> _objects) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mObjects = _objects; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.ObjectLayer;
	}

	/**
	 *	@generated 
	 */
	public List<LocatableObject> getObjects() {
		if (mObjects == null) {
			mObjects = new UContainmentList<LocatableObject>(this, PhysicsPackage.theInstance.getObjectLayer_objects()); 
		}
		return mObjects;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ObjectLayerImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
