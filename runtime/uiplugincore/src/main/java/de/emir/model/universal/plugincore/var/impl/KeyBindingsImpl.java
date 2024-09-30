package de.emir.model.universal.plugincore.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.plugincore.impl.ConfigVariableImpl;
import de.emir.model.universal.plugincore.var.IUserDefinedDelta;
import de.emir.model.universal.plugincore.var.KeyBindings;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.lists.ListUtils;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = KeyBindings.class)
public class KeyBindingsImpl extends ConfigVariableImpl implements KeyBindings  
{
	
	
	/**
	 *	@generated 
	 */
	private List<IUserDefinedDelta> mDeltas = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public KeyBindingsImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public KeyBindingsImpl(final KeyBindings _copy) {
		super(_copy);
		mDeltas = _copy.getDeltas();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public KeyBindingsImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name, List<IUserDefinedDelta> _deltas) {
		super(_documentation,_annotations,_package,_name);
		mDeltas = _deltas; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.KeyBindings;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public List<IUserDefinedDelta> getDeltas() {
		if (mDeltas == null) {
			mDeltas = ListUtils.<IUserDefinedDelta>asList(this, VarPackage.theInstance.getKeyBindings_deltas()); 
		}
		return mDeltas;
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
		return "KeyBindingsImpl{" +
		" documentation = " + getDocumentation() + 
		" name = " + getName() + 
		"}";
	}
}
