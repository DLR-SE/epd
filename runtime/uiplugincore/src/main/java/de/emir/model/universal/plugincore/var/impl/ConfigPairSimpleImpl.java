package de.emir.model.universal.plugincore.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.plugincore.impl.ConfigVariableImpl;
import de.emir.model.universal.plugincore.var.ConfigObject;
import de.emir.model.universal.plugincore.var.ConfigPairSimple;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UPackage;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = ConfigPairSimple.class)
public class ConfigPairSimpleImpl extends ConfigVariableImpl implements ConfigPairSimple  
{
	
	
	/**
	 *	@generated 
	 */
	private String mKey = "";
	/**
	 *	@generated_not
	 */
	private ConfigObject mValue = null;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public ConfigPairSimpleImpl(){
		super();
		//set the default values and assign them to this instance 
		setValue(mValue);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ConfigPairSimpleImpl(final ConfigPairSimple _copy) {
		super(_copy);
		mKey = _copy.getKey();
		mValue = _copy.getValue();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ConfigPairSimpleImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name, String _key, ConfigObject _value) {
		super(_documentation,_annotations,_package,_name);
		mKey = _key;
		mValue = _value; 
	}
    
    /**
	 *	Default attribute constructor
	 *	@generated_not
	 */
	public ConfigPairSimpleImpl(String _key, ConfigObject _value) {
		super();
		mKey = _key;
		mValue = _value;
	}
    
    /**
	 *	Default attribute constructor
	 *	@generated_not
	 */
	public ConfigPairSimpleImpl(String _key, Object _value) {
		super();
		mKey = _key;
		mValue = new ConfigObjectImpl(_value);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.ConfigPairSimple;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setKey(String _key) {
		if (needNotification(VarPackage.Literals.ConfigPairSimple_key)){
			String _oldValue = mKey;
			mKey = _key;
			notify(_oldValue, _key, VarPackage.Literals.ConfigPairSimple_key, NotificationType.SET);
		}else{
			mKey = _key;
		}
	}

	/**
	 *	@generated 
	 */
	public void setValue(ConfigObject _value) {
		Notification<ConfigObject> notification = basicSet(mValue, _value, VarPackage.Literals.ConfigPairSimple_value);
		mValue = _value;
		if (notification != null){
			dispatchNotification(notification);
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
	public ConfigObject getValue() {
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
		return "ConfigPairSimpleImpl{" +
		" documentation = " + getDocumentation() + 
		" name = " + getName() + 
		" key = " + getKey() + 
		"}";
	}
}
