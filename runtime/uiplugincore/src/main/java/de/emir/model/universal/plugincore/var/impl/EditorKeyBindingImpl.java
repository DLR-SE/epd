package de.emir.model.universal.plugincore.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.plugincore.var.EditorKeyBinding;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.model.universal.plugincore.var.impl.AbstractKeyBindingImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.UPackage;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = EditorKeyBinding.class)
public class EditorKeyBindingImpl extends AbstractKeyBindingImpl implements EditorKeyBinding  
{
	
	
	/**
	 *	@generated 
	 */
	private String mEditorID = "";
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public EditorKeyBindingImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public EditorKeyBindingImpl(final EditorKeyBinding _copy) {
		super(_copy);
		mEditorID = _copy.getEditorID();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public EditorKeyBindingImpl(String _documentation, List<UAnnotation> _annotations, UPackage _package, String _name, String _commandID, String _key, String _editorID) {
		super(_documentation,_annotations,_package,_name,_commandID,_key);
		mEditorID = _editorID;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.EditorKeyBinding;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setEditorID(String _editorID) {
		if (needNotification(VarPackage.Literals.EditorKeyBinding_editorID)){
			String _oldValue = mEditorID;
			mEditorID = _editorID;
			notify(_oldValue, _editorID, VarPackage.Literals.EditorKeyBinding_editorID, NotificationType.SET);
		}else{
			mEditorID = _editorID;
		}
	}
	/**
	 *	@generated 
	 */
	public String getEditorID() {
		return mEditorID;
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
		return "EditorKeyBindingImpl{" +
		" documentation = " + getDocumentation() + 
		" name = " + getName() + 
		" commandID = " + getCommandID() + 
		" key = " + getKey() + 
		" editorID = " + getEditorID() + 
		"}";
	}

    @Override
    public AbstractKeyBindingImpl copy() {
        EditorKeyBindingImpl result = new EditorKeyBindingImpl();
        result.setCommandID(getCommandID());
        result.setEditorID(getEditorID());
        result.setKey(getKey());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof EditorKeyBindingImpl == false) {
            return false;
        }

        EditorKeyBindingImpl theOther = (EditorKeyBindingImpl) obj;
        String otherKey = theOther.getKey();
        String otherEditorId = theOther.getEditorID();

        if (otherKey.equals(getKey()) == false) {
            return false;
        }

        if (getEditorID().equals(otherEditorId) == false) {
            return false;
        }

        return getCommandID().equals(theOther.getCommandID());

    }
}
