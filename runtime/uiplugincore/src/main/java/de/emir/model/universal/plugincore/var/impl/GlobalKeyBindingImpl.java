package de.emir.model.universal.plugincore.var.impl;

import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.plugincore.var.GlobalKeyBinding;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.model.universal.plugincore.var.impl.AbstractKeyBindingImpl;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UPackage;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = GlobalKeyBinding.class)
public class GlobalKeyBindingImpl extends AbstractKeyBindingImpl implements GlobalKeyBinding  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public GlobalKeyBindingImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public GlobalKeyBindingImpl(final GlobalKeyBinding _copy) {
		super(_copy);
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public GlobalKeyBindingImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name, String _commandID, String _key) {
		super(_documentation,_annotations,_package,_name,_commandID,_key);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.GlobalKeyBinding;
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
		return "GlobalKeyBindingImpl{" +
		" documentation = " + getDocumentation() + 
		" name = " + getName() + 
		" commandID = " + getCommandID() + 
		" key = " + getKey() + 
		"}";
	}
    
    @Override
    public AbstractKeyBinding copy() {
        GlobalKeyBindingImpl result = new GlobalKeyBindingImpl();
        result.setCommandID(getCommandID());
        result.setKey(getKey());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof GlobalKeyBindingImpl == false) {
            return false;
        }

        GlobalKeyBindingImpl theOther = (GlobalKeyBindingImpl) obj;
        String otherKey = theOther.getKey();

        if (otherKey.equals(getKey()) == false) {
            return false;
        }

        return getCommandID().equals(theOther.getCommandID());

    }

}
