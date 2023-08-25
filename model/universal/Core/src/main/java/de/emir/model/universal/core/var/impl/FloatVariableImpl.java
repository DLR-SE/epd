package de.emir.model.universal.core.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.impl.VariableImpl;
import de.emir.model.universal.core.var.FloatVariable;
import de.emir.model.universal.core.var.VarPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = FloatVariable.class)
public class FloatVariableImpl extends VariableImpl implements FloatVariable  
{
	
	
	/**
	 *	@generated 
	 */
	private float mValue = 0.0f;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public FloatVariableImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public FloatVariableImpl(final FloatVariable _copy) {
		super(_copy);
		mValue = _copy.getValue();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public FloatVariableImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, float _value) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mValue = _value;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.FloatVariable;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setValue(float _value) {
		if (needNotification(VarPackage.Literals.FloatVariable_value)){
			float _oldValue = mValue;
			mValue = _value;
			notify(_oldValue, mValue, VarPackage.Literals.FloatVariable_value, NotificationType.SET);
		}else{
			mValue = _value;
		}
	}
	/**
	 *	@generated 
	 */
	public float getValue() {
		return mValue;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "FloatVariableImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" value = " + getValue() + 
		"}";
	}

	@Override
	public void setObjectValue(Object value) {
		setValue((Float)value);
	}

	@Override
	public Object getValueAsObject() {
		return getValue();
	}
}
