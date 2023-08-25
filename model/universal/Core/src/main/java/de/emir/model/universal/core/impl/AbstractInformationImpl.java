package de.emir.model.universal.core.impl;

import java.util.List;

import de.emir.model.universal.core.AbstractInformation;
import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.core.impl.IdentifiedObjectImpl;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = AbstractInformation.class)
abstract public class AbstractInformationImpl extends IdentifiedObjectImpl implements AbstractInformation  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public AbstractInformationImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AbstractInformationImpl(final AbstractInformation _copy) {
		super(_copy);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AbstractInformationImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier) {
		super(_name,_allias,_remarks,_observers,_identifier);
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CorePackage.Literals.AbstractInformation;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "AbstractInformationImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
