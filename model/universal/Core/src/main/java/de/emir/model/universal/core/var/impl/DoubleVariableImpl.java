package de.emir.model.universal.core.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.impl.VariableImpl;
import de.emir.model.universal.core.var.DoubleVariable;
import de.emir.model.universal.core.var.VarPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = DoubleVariable.class)
public class DoubleVariableImpl extends VariableImpl implements DoubleVariable  
{
	
	
	/**
	 *	@generated 
	 */
	private double mValue = 0.0;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public DoubleVariableImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public DoubleVariableImpl(final DoubleVariable _copy) {
		super(_copy);
		mValue = _copy.getValue();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public DoubleVariableImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, double _value) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mValue = _value;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.DoubleVariable;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setValue(double _value) {
		if (needNotification(VarPackage.Literals.DoubleVariable_value)){
			double _oldValue = mValue;
			mValue = _value;
			notify(_oldValue, mValue, VarPackage.Literals.DoubleVariable_value, NotificationType.SET);
		}else{
			mValue = _value;
		}
	}
	/**
	 *	@generated 
	 */
	public double getValue() {
		return mValue;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "DoubleVariableImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" value = " + getValue() + 
		"}";
	}

	@Override
	public void setObjectValue(Object value) {
		setValue((Double)value);
	}

	@Override
	public Object getValueAsObject() {
		return getValue();
	}
}
