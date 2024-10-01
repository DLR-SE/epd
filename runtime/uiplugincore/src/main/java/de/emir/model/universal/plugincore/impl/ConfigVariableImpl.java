package de.emir.model.universal.plugincore.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.plugincore.ConfigVariable;
import de.emir.model.universal.plugincore.PlugincorePackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.impl.UNamedElementImpl;
import java.util.List;


/**

 * An ConfigVariable can be used to store somewhat complex types with are often needed.
 * The variable's name and documentation are to be taken from the UNamedElement
 * @generated 
 */
@UMLImplementation(classifier = ConfigVariable.class)
abstract public class ConfigVariableImpl extends UNamedElementImpl implements ConfigVariable  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ConfigVariableImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ConfigVariableImpl(final ConfigVariable _copy) {
		super(_copy);
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ConfigVariableImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name) {
		super(_documentation,_annotations,_package,_name);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PlugincorePackage.Literals.ConfigVariable;
	}
	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ConfigVariableImpl{" +
		" documentation = " + getDocumentation() + 
		" name = " + getName() + 
		"}";
	}
}
