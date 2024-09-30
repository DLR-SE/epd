package de.emir.model.universal.plugincore.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.plugincore.ConfigVariable;
import de.emir.model.universal.plugincore.impl.ConfigVariableImpl;
import de.emir.model.universal.plugincore.var.ConfigPair;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPackage;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = ConfigPair.class)
public class ConfigPairImpl extends ConfigVariableImpl implements ConfigPair  
{
	
	
	/**
	 *	@generated 
	 */
	private String mKey = "";
	/**
	 *	@generated_not
	 */
	private ConfigVariable mValue = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ConfigPairImpl(){
		super();
		//set the default values and assign them to this instance 
		setValue(mValue);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ConfigPairImpl(final ConfigPair _copy) {
		super(_copy);
		mKey = _copy.getKey();
		mValue = _copy.getValue();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ConfigPairImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name, String _key, ConfigVariable _value) {
		super(_documentation,_annotations,_package,_name);
		mKey = _key;
		mValue = _value; 
	}

	/**
	 *	Default attribute constructor
	 *	@generated_not
	 */
	public ConfigPairImpl(String _key, ConfigVariable _value) {
		super();
		mKey = _key;
		mValue = _value;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.ConfigPair;
	}

	/**
	 *	@generated 
	 */
	public void setValue(ConfigVariable _value) {
		Notification<ConfigVariable> notification = basicSet(mValue, _value, VarPackage.Literals.ConfigPair_value);
		mValue = _value;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setKey(String _key) {
		if (needNotification(VarPackage.Literals.ConfigPair_key)){
			String _oldValue = mKey;
			mKey = _key;
			notify(_oldValue, _key, VarPackage.Literals.ConfigPair_key, NotificationType.SET);
		}else{
			mKey = _key;
		}
	}
	/**
	 *	@generated 
	 */
	public String getKey() {
		return mKey;
	}
	/**
	 *	@generated 
	 */
	public ConfigVariable getValue() {
		return mValue;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */
	public void build()
	{
		//TODO: 
		// 
		//  * initializes the model element, e.g. create private member for reflection access
		//  
		throw new UnsupportedOperationException("build not yet implemented");
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ConfigPairImpl{" +
		" documentation = " + getDocumentation() + 
		" name = " + getName() + 
		" key = " + getKey() + 
		"}";
	}
}
