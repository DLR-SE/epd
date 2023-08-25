package de.emir.model.universal.core.impl;

import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.MDIdentifier;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 Identifier from ISO 19115-3 Metadata
 * \note for now the CI_Citation (also ISO 19115-3) is missing and should be added if required
 * @generated 
 */
@UMLImplementation(classifier = MDIdentifier.class)
public class MDIdentifierImpl extends UObjectImpl implements MDIdentifier  
{
	
	
	/**
	 Identifier code or name, often from a controlled list or pattern defined by a code space.  
	 * @generated 
	 */
	private String mCode = "";
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public MDIdentifierImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public MDIdentifierImpl(final MDIdentifier _copy) {
		mCode = _copy.getCode();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public MDIdentifierImpl(String _code) {
		mCode = _code;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CorePackage.Literals.MDIdentifier;
	}

	/**
	 Identifier code or name, often from a controlled list or pattern defined by a code space.  
	 * @generated 
	 */
	public void setCode(String _code) {
		if (needNotification(CorePackage.Literals.MDIdentifier_code)){
			String _oldValue = mCode;
			mCode = _code;
			notify(_oldValue, mCode, CorePackage.Literals.MDIdentifier_code, NotificationType.SET);
		}else{
			mCode = _code;
		}
	}

	/**
	 Identifier code or name, often from a controlled list or pattern defined by a code space.  
	 * @generated 
	 */
	public String getCode() {
		return mCode;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "MDIdentifierImpl{" +
		" code = " + getCode() + 
		"}";
	}
}
