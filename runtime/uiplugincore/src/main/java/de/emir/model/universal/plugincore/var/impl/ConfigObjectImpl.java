package de.emir.model.universal.plugincore.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.plugincore.impl.ConfigVariableImpl;
import de.emir.model.universal.plugincore.var.ConfigObject;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UPackage;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = ConfigObject.class)
public class ConfigObjectImpl extends ConfigVariableImpl implements ConfigObject  
{
	
	
	/**
	 *	@generated 
	 */
	private String mValue = "";
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ConfigObjectImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ConfigObjectImpl(final ConfigObject _copy) {
		super(_copy);
		mValue = _copy.getValue();
	}
    
    /**
	 *	Default copy constructor
	 *	@generated
	 */
	public ConfigObjectImpl(final Object _value) {
		mValue = _value.toString();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ConfigObjectImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name, String _value) {
		super(_documentation,_annotations,_package,_name);
		mValue = _value;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.ConfigObject;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setValue(Object _value) {
		if (needNotification(VarPackage.Literals.ConfigObject_value)){
			String _oldValue = mValue;
			mValue = _value.toString();
			notify(_oldValue, _value, VarPackage.Literals.ConfigObject_value, NotificationType.SET);
		}else{
			mValue = _value.toString();
		}
	}
	/**
	 *	@generated 
	 */
	public String getValue() {
		return mValue;
	}
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated_not
	 */
	public int getAsInteger()
	{
		return Integer.parseInt(mValue);
	}

	/**
	 * @inheritDoc
	 * @generated_not
	 */
	public double getAsDouble()
	{
		return Double.parseDouble(mValue);
	}

	/**
	 * @inheritDoc
	 * @generated_not
	 */
	public float getAsFloat()
	{
		return Integer.parseInt(mValue);
	}

	/**
	 * @inheritDoc
	 * @generated_not
	 */
	public long getAsLong()
	{
		return Long.parseLong(mValue);
	}

	/**
	 * @inheritDoc
	 * @generated_not
	 */
	public boolean getAsBoolean()
	{
		return Boolean.parseBoolean(mValue);
	}

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
		return "ConfigObjectImpl{" +
		" documentation = " + getDocumentation() + 
		" name = " + getName() + 
		" value = " + getValue() + 
		"}";
	}
}
