package de.emir.model.universal.core.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.impl.VariableImpl;
import de.emir.model.universal.core.var.BoolVariable;
import de.emir.model.universal.core.var.VarPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = BoolVariable.class)
public class BoolVariableImpl extends VariableImpl implements BoolVariable  
{
	
	
	/**
	 *	@generated 
	 */
	private boolean mValue = false;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public BoolVariableImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public BoolVariableImpl(final BoolVariable _copy) {
		super(_copy);
		mValue = _copy.getValue();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public BoolVariableImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, boolean _value) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mValue = _value;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.BoolVariable;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setValue(boolean _value) {
		if (needNotification(VarPackage.Literals.BoolVariable_value)){
			boolean _oldValue = mValue;
			mValue = _value;
			notify(_oldValue, mValue, VarPackage.Literals.BoolVariable_value, NotificationType.SET);
		}else{
			mValue = _value;
		}
	}
	/**
	 *	@generated 
	 */
	public boolean getValue() {
		return mValue;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "BoolVariableImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" value = " + getValue() + 
		"}";
	}

	@Override
	public void setObjectValue(Object value) {
		setValue((Boolean)value);
	}

	@Override
	public Object getValueAsObject() {
		return (Boolean)getValue();
	}
}
