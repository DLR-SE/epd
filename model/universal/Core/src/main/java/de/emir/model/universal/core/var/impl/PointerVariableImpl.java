package de.emir.model.universal.core.var.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.core.ModelPath;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.impl.ModelPathImpl;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.impl.VariableImpl;
import de.emir.model.universal.core.var.PointerVariable;
import de.emir.model.universal.core.var.VarPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.UCoreUtils;

import java.lang.ref.WeakReference;
import java.util.List;


/**
 The PointerVariable is similar to an UObjectVariable (if pointed to an primitive type may also to the others)
 * however a pointer variable can never store its own values and thus is a reference to another value. 
 * @generated 
 */
@UMLImplementation(classifier = PointerVariable.class)
public class PointerVariableImpl extends VariableImpl implements PointerVariable  
{
	
	
	/**
	 *	Default constructor
	 *	@generated
	 */
	public PointerVariableImpl(){
		super();
		//set the default values and assign them to this instance 
		setPath(mPath);
	}

	/**
	 path to the represented value
	 * @generated 
	 */
	private ModelPath mPath = new ModelPathImpl();

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public PointerVariableImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, ModelPath _path) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mPath = _path; 
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public PointerVariableImpl(final PointerVariable _copy) {
		super(_copy);
		mPath = _copy.getPath();
	}

	public PointerVariableImpl(Pointer adress) {
		super();
		Pointer common = PointerOperations.findCommonBase(adress, this);
		if (common != null) {
			common = PointerOperations.convertToPointerFromRoot(adress);
		}
		if (common == null) common = adress;
		getPath().setRootInstance(PointerOperations.getPointerRoot(common));
		getPath().setPointerString(PointerOperations.toPointerString(common));
	}

	/**
	 path to the represented value
	 * @generated 
	 */
	public ModelPath getPath() {
		return mPath;
	}

	/**
	 path to the represented value
	 * @generated 
	 */
	public void setPath(ModelPath _path) {
		Notification<ModelPath> notification = basicSet(mPath, _path, VarPackage.Literals.PointerVariable_path);
		mPath = _path;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VarPackage.Literals.PointerVariable;
	}
	
	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "PointerVariableImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}

	private Pointer getPointer() {
		if (getPath() != null) {
			return getPath().getPointer();
		}
		return null;
	}
	
	@Override
	public void setObjectValue(Object value) {
		assert(getPointer() != null);
		getPointer().setValue(value);
	}

	@Override
	public Object getValueAsObject() {
		assert(getPointer() != null);
		return getPointer().getValue();
	}
}
