package de.emir.model.universal.physics.impl;

import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.impl.IdentifiedObjectImpl;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.impl.NativeCRSImpl;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.crs.impl.Polar3DImpl;
import de.emir.model.universal.physics.Environment;
import de.emir.model.universal.physics.EnvironmentLayer;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Environment.class)
public class EnvironmentImpl extends IdentifiedObjectImpl implements Environment  
{
	
	
	/**
	 *	@generated 
	 */
	private CoordinateReferenceSystem mCrs = new Engineering2DImpl();
	/**
	 *	@generated 
	 */
	private List<EnvironmentLayer> mLayer = null;
	/**
	 *	@generated 
	 */
	private List<Environment> mChildren = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public EnvironmentImpl(){
		super();
		//set the default values and assign them to this instance 
		setCrs(mCrs);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public EnvironmentImpl(final Environment _copy) {
		super(_copy);
		mCrs = _copy.getCrs();
		mLayer = _copy.getLayer();
		mChildren = _copy.getChildren();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public EnvironmentImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, CoordinateReferenceSystem _crs, List<EnvironmentLayer> _layer, List<Environment> _children) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mCrs = _crs; 
		mLayer = _layer; 
		mChildren = _children; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.Environment;
	}

	/**
	 *	@generated 
	 */
	public void setCrs(CoordinateReferenceSystem _crs) {
		Notification<CoordinateReferenceSystem> notification = basicSet(mCrs, _crs, PhysicsPackage.Literals.Environment_crs);
		mCrs = _crs;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public CoordinateReferenceSystem getCrs() {
		return mCrs;
	}

	/**
	 *	@generated 
	 */
	public List<EnvironmentLayer> getLayer() {
		if (mLayer == null) {
			mLayer = new UContainmentList<EnvironmentLayer>(this, PhysicsPackage.theInstance.getEnvironment_layer()); 
		}
		return mLayer;
	}

	/**
	 *	@generated 
	 */
	public List<Environment> getChildren() {
		if (mChildren == null) {
			mChildren = new UContainmentList<Environment>(this, PhysicsPackage.theInstance.getEnvironment_children()); 
		}
		return mChildren;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "EnvironmentImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
