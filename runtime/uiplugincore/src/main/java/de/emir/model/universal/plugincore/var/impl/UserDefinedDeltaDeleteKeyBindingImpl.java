package de.emir.model.universal.plugincore.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import de.emir.model.universal.plugincore.var.IUserDefinedDelta;
import de.emir.model.universal.plugincore.var.UserDefinedDeltaDeleteKeyBinding;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import java.util.List;
import java.util.Map;


/**
 *	@generated 
 */
@UMLImplementation(classifier = UserDefinedDeltaDeleteKeyBinding.class)
public class UserDefinedDeltaDeleteKeyBindingImpl extends UObjectImpl implements UserDefinedDeltaDeleteKeyBinding , IUserDefinedDelta 
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public UserDefinedDeltaDeleteKeyBindingImpl(){
		super();
		//set the default values and assign them to this instance 
		setOldBinding(mOldBinding);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public UserDefinedDeltaDeleteKeyBindingImpl(final UserDefinedDeltaDeleteKeyBinding _copy) {
		mOldBinding = _copy.getOldBinding();
	}



	/**
	 *	@generated 
	 */
	private AbstractKeyBinding mOldBinding = null;



	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.UserDefinedDeltaDeleteKeyBinding;
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public UserDefinedDeltaDeleteKeyBindingImpl(AbstractKeyBinding _oldBinding) {
		mOldBinding = _oldBinding; 
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
        String key = mOldBinding.getKey();

        List<AbstractKeyBinding> bindingList = bindings.get(key);

        if (bindingList == null) {
            // Can't be present, nothing to delete
            return;
        }

        AbstractKeyBinding bindingToDelete = null;

        for (AbstractKeyBinding kb : bindingList) {

            if (kb.equals(mOldBinding)) {
                bindingToDelete = kb;
            }

        }

        if (bindingToDelete != null) {
            bindingList.remove(bindingToDelete);
        }
	}

	/**
	 *	@generated 
	 */
	public AbstractKeyBinding getOldBinding() {
		return mOldBinding;
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
	* @generated
	*/
	@Override
	public String toString() {
		return "UserDefinedDeltaDeleteKeyBindingImpl{" +
		"}";
	}

	/**
	 *	@generated 
	 */
	public void setOldBinding(AbstractKeyBinding _oldBinding) {
		Notification<AbstractKeyBinding> notification = basicSet(mOldBinding, _oldBinding, VarPackage.Literals.UserDefinedDeltaDeleteKeyBinding_oldBinding);
		mOldBinding = _oldBinding;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
}
