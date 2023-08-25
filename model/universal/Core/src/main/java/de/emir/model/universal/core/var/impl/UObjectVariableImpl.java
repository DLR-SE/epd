package de.emir.model.universal.core.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.impl.RSIdentifierImpl;
import de.emir.model.universal.core.impl.VariableImpl;
import de.emir.model.universal.core.var.UObjectVariable;
import de.emir.model.universal.core.var.VarPackage;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UObject;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = UObjectVariable.class)
public class UObjectVariableImpl extends VariableImpl implements UObjectVariable  
{
	
	
	/**
	 *	@generated 
	 */
	private UObject mValue = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public UObjectVariableImpl(){
		super();
		//set the default values and assign them to this instance 
		setValue(mValue);
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public UObjectVariableImpl(final UObjectVariable _copy) {
		super(_copy);
		mValue = _copy.getValue();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public UObjectVariableImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, UObject _value) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mValue = _value; 
	}
	
	public UObjectVariableImpl(String name, UObject value) {
		super();
		setName(name);
		setValue(value);
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.UObjectVariable;
	}
	
	/**
	 *	@generated 
	 */
	public void setValue(UObject _value) {
		Notification<UObject> notification = basicSet(mValue, _value, VarPackage.Literals.UObjectVariable_value);
		mValue = _value;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public UObject getValue() {
		return mValue;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "UObjectVariableImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}

	@Override
	public void setObjectValue(Object value) {
		setValue((UObject)value);
	}

	@Override
	public Object getValueAsObject() {
		return getValue();
	}
}
