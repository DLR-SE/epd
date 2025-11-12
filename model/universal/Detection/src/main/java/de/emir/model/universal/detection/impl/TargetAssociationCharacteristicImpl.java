package de.emir.model.universal.detection.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.detection.ITargetAssociationCharacteristic;
import de.emir.model.universal.detection.TargetPackage;
import de.emir.model.universal.physics.impl.CharacteristicImpl;
import de.emir.tuml.ucore.runtime.NotificationType;


/**
* The TargetAssociationCharacteristic is used for keeping associations between multiple
* PhysicalObjects. When a new Target is for example created from fusing AIS and RADAR data,
* a TargetAssociationCharacteristic could be appended to the AIS object, RADAR target and the
* newly created object to handle associations between these objects.
 * @generated 
 */
@UMLImplementation(classifier = ITargetAssociationCharacteristic.class)
public class TargetAssociationCharacteristicImpl extends CharacteristicImpl implements ITargetAssociationCharacteristic  
{
	
	
	/**
	 Unique association identifier. 
	 * @generated 
	 */
	private String mAssociationID = "";
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public TargetAssociationCharacteristicImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public TargetAssociationCharacteristicImpl(final ITargetAssociationCharacteristic _copy) {
		super(_copy);
		mAssociationID = _copy.getAssociationID();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public TargetAssociationCharacteristicImpl(String _associationID) {
		super();
		mAssociationID = _associationID;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return TargetPackage.Literals.TargetAssociationCharacteristic;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 Unique association identifier. 
	 * @generated 
	 */
	public void setAssociationID(String _associationID) {
		if (needNotification(TargetPackage.Literals.TargetAssociationCharacteristic_associationID)){
			String _oldValue = mAssociationID;
			mAssociationID = _associationID;
			notify(_oldValue, _associationID, TargetPackage.Literals.TargetAssociationCharacteristic_associationID, NotificationType.SET);
		}else{
			mAssociationID = _associationID;
		}
	}
	/**
	 Unique association identifier. 
	 * @generated 
	 */
	public String getAssociationID() {
		return mAssociationID;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TargetAssociationCharacteristicImpl{" +
		" associationID = " + getAssociationID() + 
		"}";
	}
}
