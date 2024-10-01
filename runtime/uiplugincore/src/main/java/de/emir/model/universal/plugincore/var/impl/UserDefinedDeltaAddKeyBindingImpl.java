package de.emir.model.universal.plugincore.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import de.emir.model.universal.plugincore.var.IUserDefinedDelta;
import de.emir.model.universal.plugincore.var.UserDefinedDeltaAddKeyBinding;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *	@generated 
 */
@UMLImplementation(classifier = UserDefinedDeltaAddKeyBinding.class)
public class UserDefinedDeltaAddKeyBindingImpl extends UObjectImpl implements UserDefinedDeltaAddKeyBinding , IUserDefinedDelta 
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public UserDefinedDeltaAddKeyBindingImpl(){
		super();
		//set the default values and assign them to this instance 
		setNewBinding(mNewBinding);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public UserDefinedDeltaAddKeyBindingImpl(final UserDefinedDeltaAddKeyBinding _copy) {
		mNewBinding = _copy.getNewBinding();
	}



	/**
	 *	@generated 
	 */
	private AbstractKeyBinding mNewBinding = null;



	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.UserDefinedDeltaAddKeyBinding;
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public UserDefinedDeltaAddKeyBindingImpl(AbstractKeyBinding _newBinding) {
		mNewBinding = _newBinding; 
	}
	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated_not
	 */
    @Override
	public void apply(final Map<String, List<AbstractKeyBinding>> bindings)
	{
		String key = mNewBinding.getKey();

        List<AbstractKeyBinding> bindingList = bindings.get(key);

        if (bindingList == null) {
            bindingList = new ArrayList<>();
            bindings.put(key, bindingList);
        }

        bindingList.add(mNewBinding.copy());
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "UserDefinedDeltaAddKeyBindingImpl{" +
		"}";
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public void apply(final List<AbstractKeyBinding> bindings)
	{
		//TODO: 
		throw new UnsupportedOperationException("apply not yet implemented");
	}

	/**
	 *	@generated 
	 */
	public void setNewBinding(AbstractKeyBinding _newBinding) {
		Notification<AbstractKeyBinding> notification = basicSet(mNewBinding, _newBinding, VarPackage.Literals.UserDefinedDeltaAddKeyBinding_newBinding);
		mNewBinding = _newBinding;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public AbstractKeyBinding getNewBinding() {
		return mNewBinding;
	}
}
