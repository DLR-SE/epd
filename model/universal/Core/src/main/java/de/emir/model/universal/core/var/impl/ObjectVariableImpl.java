package de.emir.model.universal.core.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.impl.VariableImpl;
import de.emir.model.universal.core.var.ObjectVariable;
import de.emir.model.universal.core.var.VarPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = ObjectVariable.class)
public class ObjectVariableImpl extends VariableImpl implements ObjectVariable  
{
	
	
	/**
	 *	@generated not
	 */
	private Object mValue = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ObjectVariableImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ObjectVariableImpl(final ObjectVariable _copy) {
		super(_copy);
		mValue = _copy.getValue();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ObjectVariableImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, Object _value) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mValue = _value;
	}
	
	public ObjectVariableImpl(String name, Object value) {
		this();
		setName(name);
		mValue = value;
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.ObjectVariable;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setValue(Object _value) {
		if (needNotification(VarPackage.Literals.ObjectVariable_value)){
			Object _oldValue = mValue;
			mValue = _value;
			notify(_oldValue, mValue, VarPackage.Literals.ObjectVariable_value, NotificationType.SET);
		}else{
			mValue = _value;
		}
	}
	/**
	 *	@generated 
	 */
	public Object getValue() {
		return mValue;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ObjectVariableImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" value = " + getValue() + 
		"}";
	}

	@Override
	public void setObjectValue(Object value) {
		setValue(value);		
	}

	@Override
	public Object getValueAsObject() {
		return getValue();
	}
}
