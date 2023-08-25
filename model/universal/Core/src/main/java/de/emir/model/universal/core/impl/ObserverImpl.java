package de.emir.model.universal.core.impl;

import java.util.ArrayList;
import java.util.List;

import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.Observer;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.impl.IdentifiedObjectImpl;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.lists.ListUtils;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**

 * An Observer is used to observe external events and execute some operation if such an event occurs
 * @generated 
 */
@UMLImplementation(classifier = Observer.class)
abstract public class ObserverImpl extends IdentifiedObjectImpl implements Observer  
{
	
	
	/**
	 *	@generated 
	 */
	private List<IValueChangeListener> mOperations = null;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public ObserverImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ObserverImpl(final Observer _copy) {
		super(_copy);
		mOperations = _copy.getOperations();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ObserverImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, List<IValueChangeListener> _operations) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mOperations = _operations; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CorePackage.Literals.Observer;
	}

	/**
	 *	@generated not
	 */
	@Override
	public List<IValueChangeListener> getOperations() {
		if (mOperations == null) {
			mOperations = new ArrayList<IValueChangeListener>(); 
		}
		return mOperations; 
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ObserverImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}

	
}
