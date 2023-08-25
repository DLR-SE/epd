package de.emir.model.universal.physics.impl;

import de.emir.model.universal.math.Vector;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.physics.Torque;
import de.emir.model.universal.units.impl.DirectedMeasureImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Torque.class)
public class TorqueImpl extends DirectedMeasureImpl implements Torque  
{
	
	
	/**
	 *	@generated 
	 */
	private Vector mValue = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public TorqueImpl(){
		super();
		//set the default values and assign them to this instance 
		setValue(mValue);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public TorqueImpl(final Torque _copy) {
		super(_copy);
		mValue = _copy.getValue();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public TorqueImpl(Vector _value) {
		super();
		mValue = _value; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.Torque;
	}

	/**
	 *	@generated 
	 */
	public void setValue(Vector _value) {
		Notification<Vector> notification = basicSet(mValue, _value, PhysicsPackage.Literals.Torque_value);
		mValue = _value;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Vector getValue() {
		return mValue;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TorqueImpl{" +
		"}";
	}
}
