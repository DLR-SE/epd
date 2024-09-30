package de.emir.model.universal.plugincore.var.impl;

import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.model.universal.plugincore.var.ViewKeyBinding;
import de.emir.model.universal.plugincore.var.impl.AbstractKeyBindingImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UPackage;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = ViewKeyBinding.class)
public class ViewKeyBindingImpl extends AbstractKeyBindingImpl implements ViewKeyBinding  
{
	
	
	/**
	 *	@generated 
	 */
	private String mViewID = "";
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ViewKeyBindingImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ViewKeyBindingImpl(final ViewKeyBinding _copy) {
		super(_copy);
		mViewID = _copy.getViewID();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ViewKeyBindingImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name, String _commandID, String _key, String _viewID) {
		super(_documentation,_annotations,_package,_name,_commandID,_key);
		mViewID = _viewID;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.ViewKeyBinding;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setViewID(String _viewID) {
		if (needNotification(VarPackage.Literals.ViewKeyBinding_viewID)){
			String _oldValue = mViewID;
			mViewID = _viewID;
			notify(_oldValue, _viewID, VarPackage.Literals.ViewKeyBinding_viewID, NotificationType.SET);
		}else{
			mViewID = _viewID;
		}
	}
	/**
	 *	@generated 
	 */
	public String getViewID() {
		return mViewID;
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
		return "ViewKeyBindingImpl{" +
		" documentation = " + getDocumentation() + 
		" name = " + getName() + 
		" commandID = " + getCommandID() + 
		" key = " + getKey() + 
		" viewID = " + getViewID() + 
		"}";
	}
    
    @Override
    public AbstractKeyBinding copy() {
        ViewKeyBinding result = new ViewKeyBindingImpl();
        result.setCommandID(getCommandID());
        result.setViewID(getViewID());
        result.setKey(getKey());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof ViewKeyBinding == false) {
            return false;
        }

        ViewKeyBinding theOther = (ViewKeyBinding) obj;
        String otherKey = theOther.getKey();
        String otherViewId = theOther.getViewID();

        if (otherKey.equals(getKey()) == false) {
            return false;
        }

        if (getViewID().equals(otherViewId) == false) {
            return false;
        }

        return getCommandID().equals(theOther.getCommandID());

    }
}
