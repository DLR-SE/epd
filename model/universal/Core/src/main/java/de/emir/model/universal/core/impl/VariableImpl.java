package de.emir.model.universal.core.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.Variable;
import de.emir.model.universal.core.impl.IdentifiedObjectImpl;
import java.util.List;


/**

 * An Variable can be used to store (or reference) a single value. 
 * The variable's name and documentation are to be taken from the IdentifiedObject
 * @generated 
 */
@UMLImplementation(classifier = Variable.class)
abstract public class VariableImpl extends IdentifiedObjectImpl implements Variable  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public VariableImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public VariableImpl(final Variable _copy) {
		super(_copy);
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public VariableImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier) {
		super(_name,_allias,_remarks,_observers,_identifier);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CorePackage.Literals.Variable;
	}
	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public abstract void setObjectValue(final Object value);

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public abstract Object getValueAsObject();



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "VariableImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
