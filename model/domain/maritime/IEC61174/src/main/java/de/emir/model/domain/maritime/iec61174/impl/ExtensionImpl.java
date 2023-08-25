package de.emir.model.domain.maritime.iec61174.impl;

import de.emir.model.domain.maritime.iec61174.Extension;
import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Extension.class)
public class ExtensionImpl extends UObjectImpl implements Extension  
{
	
	
	/**
	 *	@generated 
	 */
	private String mManufactor = "";
	/**
	 *	@generated 
	 */
	private String mName = "";
	/**
	 *	@generated 
	 */
	private String mVersion = "";
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ExtensionImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ExtensionImpl(final Extension _copy) {
		mManufactor = _copy.getManufactor();
		mName = _copy.getName();
		mVersion = _copy.getVersion();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ExtensionImpl(String _manufactor, String _name, String _version) {
		mManufactor = _manufactor;
		mName = _name;
		mVersion = _version;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return Iec61174Package.Literals.Extension;
	}

	/**
	 *	@generated 
	 */
	public void setManufactor(String _manufactor) {
		if (needNotification(Iec61174Package.Literals.Extension_manufactor)){
			String _oldValue = mManufactor;
			mManufactor = _manufactor;
			notify(_oldValue, mManufactor, Iec61174Package.Literals.Extension_manufactor, NotificationType.SET);
		}else{
			mManufactor = _manufactor;
		}
	}

	/**
	 *	@generated 
	 */
	public String getManufactor() {
		return mManufactor;
	}

	/**
	 *	@generated 
	 */
	public void setName(String _name) {
		if (needNotification(Iec61174Package.Literals.Extension_name)){
			String _oldValue = mName;
			mName = _name;
			notify(_oldValue, mName, Iec61174Package.Literals.Extension_name, NotificationType.SET);
		}else{
			mName = _name;
		}
	}

	/**
	 *	@generated 
	 */
	public String getName() {
		return mName;
	}

	/**
	 *	@generated 
	 */
	public void setVersion(String _version) {
		if (needNotification(Iec61174Package.Literals.Extension_version)){
			String _oldValue = mVersion;
			mVersion = _version;
			notify(_oldValue, mVersion, Iec61174Package.Literals.Extension_version, NotificationType.SET);
		}else{
			mVersion = _version;
		}
	}

	/**
	 *	@generated 
	 */
	public String getVersion() {
		return mVersion;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ExtensionImpl{" +
		" manufactor = " + getManufactor() + 
		" name = " + getName() + 
		" version = " + getVersion() + 
		"}";
	}
}
