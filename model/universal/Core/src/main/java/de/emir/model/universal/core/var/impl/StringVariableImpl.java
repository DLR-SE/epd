package de.emir.model.universal.core.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.impl.VariableImpl;
import de.emir.model.universal.core.var.StringVariable;
import de.emir.model.universal.core.var.VarPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = StringVariable.class)
public class StringVariableImpl extends VariableImpl implements StringVariable  
{
	
	
	/**
	 *	@generated 
	 */
	private String mValue = "";
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public StringVariableImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public StringVariableImpl(final StringVariable _copy) {
		super(_copy);
		mValue = _copy.getValue();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public StringVariableImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, String _value) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mValue = _value;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.StringVariable;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setValue(String _value) {
		if (needNotification(VarPackage.Literals.StringVariable_value)){
			String _oldValue = mValue;
			mValue = _value;
			notify(_oldValue, mValue, VarPackage.Literals.StringVariable_value, NotificationType.SET);
		}else{
			mValue = _value;
		}
	}
	/**
	 *	@generated 
	 */
	public String getValue() {
		return mValue;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "StringVariableImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" value = " + getValue() + 
		"}";
	}

	@Override
	public void setObjectValue(Object value) {
		setValue(String.valueOf(value));
	}

	@Override
	public Object getValueAsObject() {
		return getValue();
	}
}
