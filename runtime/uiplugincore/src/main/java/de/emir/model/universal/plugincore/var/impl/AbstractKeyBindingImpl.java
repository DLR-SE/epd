package de.emir.model.universal.plugincore.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.plugincore.impl.ConfigVariableImpl;
import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UPackage;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = AbstractKeyBinding.class)
abstract public class AbstractKeyBindingImpl extends ConfigVariableImpl implements AbstractKeyBinding  
{
	
	
	/**
	 *	@generated 
	 */
	private String mCommandID = "";
	/**
	 *	@generated 
	 */
	private String mKey = "";
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public AbstractKeyBindingImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AbstractKeyBindingImpl(final AbstractKeyBinding _copy) {
		super(_copy);
		mCommandID = _copy.getCommandID();
		mKey = _copy.getKey();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AbstractKeyBindingImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name, String _commandID, String _key) {
		super(_documentation,_annotations,_package,_name);
		mCommandID = _commandID;
		mKey = _key;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.AbstractKeyBinding;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setCommandID(String _commandID) {
		if (needNotification(VarPackage.Literals.AbstractKeyBinding_commandID)){
			String _oldValue = mCommandID;
			mCommandID = _commandID;
			notify(_oldValue, _commandID, VarPackage.Literals.AbstractKeyBinding_commandID, NotificationType.SET);
		}else{
			mCommandID = _commandID;
		}
	}
	/**
	 *	@generated 
	 */
	public String getCommandID() {
		return mCommandID;
	}
	/**
	 *	@generated 
	 */
	public void setKey(String _key) {
		if (needNotification(VarPackage.Literals.AbstractKeyBinding_key)){
			String _oldValue = mKey;
			mKey = _key;
			notify(_oldValue, _key, VarPackage.Literals.AbstractKeyBinding_key, NotificationType.SET);
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
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean equals(final Object obj)
	{
		//TODO: 
		throw new UnsupportedOperationException("equals not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public AbstractKeyBinding copy()
	{
		//TODO: 
		throw new UnsupportedOperationException("copy not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "AbstractKeyBindingImpl{" +
		" documentation = " + getDocumentation() + 
		" name = " + getName() + 
		" commandID = " + getCommandID() + 
		" key = " + getKey() + 
		"}";
	}
}
