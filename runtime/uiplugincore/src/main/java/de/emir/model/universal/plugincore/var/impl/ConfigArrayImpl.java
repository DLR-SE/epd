package de.emir.model.universal.plugincore.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.plugincore.impl.ConfigVariableImpl;
import de.emir.model.universal.plugincore.var.ConfigArray;
import de.emir.model.universal.plugincore.var.ConfigObject;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.lists.ListUtils;
import java.util.ArrayList;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UPackage;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = ConfigArray.class)
public class ConfigArrayImpl extends ConfigVariableImpl implements ConfigArray  
{
	
	
	/**
	 *	@generated 
	 */
	private List<ConfigObject> mArray = null;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public ConfigArrayImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ConfigArrayImpl(final ConfigArray _copy) {
		super(_copy);
		mArray = _copy.getArray();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ConfigArrayImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name, List<ConfigObject> _array) {
		super(_documentation,_annotations,_package,_name);
		mArray = _array; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.ConfigArray;
	}
	
	/**
	 *	@generated 
	 */
	public List<ConfigObject> getArray() {
		if (mArray == null) {
			mArray = ListUtils.<ConfigObject>asList(this, VarPackage.theInstance.getConfigArray_array()); 
		}
		return mArray;
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
		return "ConfigArrayImpl{" +
		" documentation = " + getDocumentation() + 
		" name = " + getName() + 
		"}";
	}
}
