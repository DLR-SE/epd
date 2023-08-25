package de.emir.model.universal.core.impl;

import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.NamedElement;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.core.impl.RSIdentifierImpl;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.NotificationType;


/**
 *	@generated 
 */
@UMLImplementation(classifier = NamedElement.class)
abstract public class NamedElementImpl extends UObjectImpl implements NamedElement  
{
	
	
	/**
	 The primary name by which the object can be identified 
	 * @generated 
	 */
	private RSIdentifier mName = new RSIdentifierImpl();
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public NamedElementImpl(){
		super();
		//set the default values and assign them to this instance 
		setName(mName);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public NamedElementImpl(final NamedElement _copy) {
		mName = _copy.getName();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public NamedElementImpl(RSIdentifier _name) {
		mName = _name; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CorePackage.Literals.NamedElement;
	}

	/**
	 The primary name by which the object can be identified 
	 * @generated 
	 */
	public void setName(RSIdentifier _name) {
		Notification<RSIdentifier> notification = basicSet(mName, _name, CorePackage.Literals.NamedElement_name);
		mName = _name;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 The primary name by which the object can be identified 
	 * @generated 
	 */
	public RSIdentifier getName() {
		return mName;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void setName(final String _name)
	{
		if (getName() == null)
			setName(new RSIdentifierImpl());
		getName().setCode(_name);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public boolean hasValidName()
	{
		if (getName() == null) return false;
		if (getName().getCode() == null) return false;
		if (getName().getCode().isEmpty()) return false;
		return true;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public String getNameAsString()
	{
		if (hasValidName())
			return getName().getCode();
		return null;
	}

	/**
	* @generated not
	*/
	@Override
	public String toString() {
		return "NamedElementImpl{" + getName() +  "}";
	}
}
