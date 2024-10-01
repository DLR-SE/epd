package de.emir.model.universal.plugincore.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import de.emir.model.universal.plugincore.var.IUserDefinedDelta;
import de.emir.model.universal.plugincore.var.UserDefinedDeltaAddKeyBinding;
import de.emir.model.universal.plugincore.var.UserDefinedDeltaChangeKeyBinding;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.model.universal.plugincore.var.UserDefinedDeltaDeleteKeyBinding;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import java.util.List;
import java.util.Map;


/**
 *	@generated 
 */
@UMLImplementation(classifier = UserDefinedDeltaChangeKeyBinding.class)
public class UserDefinedDeltaChangeKeyBindingImpl extends UObjectImpl implements UserDefinedDeltaChangeKeyBinding , IUserDefinedDelta 
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public UserDefinedDeltaChangeKeyBindingImpl(){
		super();
		//set the default values and assign them to this instance 
		setRemove(mRemove);
		setAdd(mAdd);
	}



	/**
	 *	@generated 
	 */
	private UserDefinedDeltaAddKeyBinding mAdd = null;



	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public UserDefinedDeltaChangeKeyBindingImpl(final UserDefinedDeltaChangeKeyBinding _copy) {
		mRemove = _copy.getRemove();
		mAdd = _copy.getAdd();
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.UserDefinedDeltaChangeKeyBinding;
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public UserDefinedDeltaChangeKeyBindingImpl(UserDefinedDeltaDeleteKeyBinding _remove, UserDefinedDeltaAddKeyBinding _add) {
		mRemove = _remove; 
		mAdd = _add; 
	}

	/**
	 *	Default attribute constructor
	 *	@generated_not
	 */
	public UserDefinedDeltaChangeKeyBindingImpl(AbstractKeyBinding oldBinding, AbstractKeyBinding newBinding) {
		mRemove = new UserDefinedDeltaDeleteKeyBindingImpl(oldBinding); 
		mAdd = new UserDefinedDeltaAddKeyBindingImpl(newBinding); 
	}



	/**
	 *	@generated 
	 */
	private UserDefinedDeltaDeleteKeyBinding mRemove = null;
	
	
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
        mRemove.apply(bindings);
        mAdd.apply(bindings);
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "UserDefinedDeltaChangeKeyBindingImpl{" +
		"}";
	}

	/**
	 *	@generated 
	 */
	public void setRemove(UserDefinedDeltaDeleteKeyBinding _remove) {
		Notification<UserDefinedDeltaDeleteKeyBinding> notification = basicSet(mRemove, _remove, VarPackage.Literals.UserDefinedDeltaChangeKeyBinding_remove);
		mRemove = _remove;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public UserDefinedDeltaAddKeyBinding getAdd() {
		return mAdd;
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
	public UserDefinedDeltaDeleteKeyBinding getRemove() {
		return mRemove;
	}

	/**
	 *	@generated 
	 */
	public void setAdd(UserDefinedDeltaAddKeyBinding _add) {
		Notification<UserDefinedDeltaAddKeyBinding> notification = basicSet(mAdd, _add, VarPackage.Literals.UserDefinedDeltaChangeKeyBinding_add);
		mAdd = _add;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
}
