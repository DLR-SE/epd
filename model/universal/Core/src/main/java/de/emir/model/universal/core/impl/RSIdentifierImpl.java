package de.emir.model.universal.core.impl;

import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.core.impl.MDIdentifierImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = RSIdentifier.class)
public class RSIdentifierImpl extends MDIdentifierImpl implements RSIdentifier  
{
	
	
	/**
	 which is a version for the identifier  
	 * @generated 
	 */
	private String mVersion = "";
	/**
	 which unambiguously defines the namespace for the identifier  
	 * @generated 
	 */
	private String mCodeSpace = "";
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public RSIdentifierImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public RSIdentifierImpl(final RSIdentifier _copy) {
		super(_copy);
		mVersion = _copy.getVersion();
		mCodeSpace = _copy.getCodeSpace();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public RSIdentifierImpl(String _code, String _version, String _codeSpace) {
		super(_code);
		mVersion = _version;
		mCodeSpace = _codeSpace;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CorePackage.Literals.RSIdentifier;
	}

	/**
	 which is a version for the identifier  
	 * @generated 
	 */
	public void setVersion(String _version) {
		if (needNotification(CorePackage.Literals.RSIdentifier_version)){
			String _oldValue = mVersion;
			mVersion = _version;
			notify(_oldValue, mVersion, CorePackage.Literals.RSIdentifier_version, NotificationType.SET);
		}else{
			mVersion = _version;
		}
	}

	/**
	 which is a version for the identifier  
	 * @generated 
	 */
	public String getVersion() {
		return mVersion;
	}

	/**
	 which unambiguously defines the namespace for the identifier  
	 * @generated 
	 */
	public void setCodeSpace(String _codeSpace) {
		if (needNotification(CorePackage.Literals.RSIdentifier_codeSpace)){
			String _oldValue = mCodeSpace;
			mCodeSpace = _codeSpace;
			notify(_oldValue, mCodeSpace, CorePackage.Literals.RSIdentifier_codeSpace, NotificationType.SET);
		}else{
			mCodeSpace = _codeSpace;
		}
	}

	/**
	 which unambiguously defines the namespace for the identifier  
	 * @generated 
	 */
	public String getCodeSpace() {
		return mCodeSpace;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "RSIdentifierImpl{" +
		" code = " + getCode() + 
		" version = " + getVersion() + 
		" codeSpace = " + getCodeSpace() + 
		"}";
	}
}
