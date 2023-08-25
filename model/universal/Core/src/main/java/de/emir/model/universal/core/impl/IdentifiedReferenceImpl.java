package de.emir.model.universal.core.impl;

import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.IdentifiedReference;
import de.emir.model.universal.core.ModelReference;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.utils.Pointer;


/**
 *	@generated 
 */
@UMLImplementation(classifier = IdentifiedReference.class)
public class IdentifiedReferenceImpl extends UObjectImpl implements IdentifiedReference , ModelReference 
{
	
	
	/**
	 contains the code of an RSIdentifier of an NamedElement 
	 * @generated 
	 */
	private String mReferencedIdentifier = "";
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public IdentifiedReferenceImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public IdentifiedReferenceImpl(final IdentifiedReference _copy) {
		mReferencedIdentifier = _copy.getReferencedIdentifier();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public IdentifiedReferenceImpl(String _referencedIdentifier) {
		mReferencedIdentifier = _referencedIdentifier;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CorePackage.Literals.IdentifiedReference;
	}

	/**
	 contains the code of an RSIdentifier of an NamedElement 
	 * @generated 
	 */
	public void setReferencedIdentifier(String _referencedIdentifier) {
		if (needNotification(CorePackage.Literals.IdentifiedReference_referencedIdentifier)){
			String _oldValue = mReferencedIdentifier;
			mReferencedIdentifier = _referencedIdentifier;
			notify(_oldValue, mReferencedIdentifier, CorePackage.Literals.IdentifiedReference_referencedIdentifier, NotificationType.SET);
		}else{
			mReferencedIdentifier = _referencedIdentifier;
		}
	}

	/**
	 contains the code of an RSIdentifier of an NamedElement 
	 * @generated 
	 */
	public String getReferencedIdentifier() {
		return mReferencedIdentifier;
	}

	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */
	public Pointer getPointer()
	{
		//TODO: 
		// 
		//  * returns a pointer to the referenced object or null if the object could not be found or there is any other 
		//  * error within the configuration of the ModelReference 
		//  * @returns a pointer to the referenced element or null
		//  
		throw new UnsupportedOperationException("getPointer not yet implemented");
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "IdentifiedReferenceImpl{" +
		" referencedIdentifier = " + getReferencedIdentifier() + 
		"}";
	}
}
