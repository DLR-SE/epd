package de.emir.model.universal.core.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.ModelPath;
import de.emir.model.universal.core.ModelReference;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;


/**

 * A ModelReferene can be used to reference to a position within the model tree or any subtree. 
 * Thereby it is the direct representation of the Pointer (defined in UCore), but without the need
 * of knowing the UStructuralFeatures. 
 *  
 * @generated 
 */
@UMLImplementation(classifier = ModelPath.class)
public class ModelPathImpl extends UObjectImpl implements ModelPath  
{
	
	
	/**
	 textual description from the instance, using the features (and list indices) to the 
	 * required / observed feature
	 * @note the pointerString shall follow this BNF: <FeatureName>(:<ListIndex>)?(,<FeatureName>(:<ListIndex>)?)*
	 * @generated 
	 */
	private String mPointerString = "";
	/**
	 Root element from which the pointerString starts and defines the Tree or SubTree root 
	 * @generated 
	 */
	private UObject mRootInstance = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ModelPathImpl(){
		super();
		//set the default values and assign them to this instance 
		setRootInstance(mRootInstance);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ModelPathImpl(final ModelPath _copy) {
		mPointerString = _copy.getPointerString();
		mRootInstance = _copy.getRootInstance();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ModelPathImpl(String _pointerString, UObject _rootInstance) {
		mPointerString = _pointerString;
		mRootInstance = _rootInstance; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CorePackage.Literals.ModelPath;
	}

	/**
	 textual description from the instance, using the features (and list indices) to the 
	 * required / observed feature
	 * @note the pointerString shall follow this BNF: <FeatureName>(:<ListIndex>)?(,<FeatureName>(:<ListIndex>)?)*
	 * @generated 
	 */
	public void setPointerString(String _pointerString) {
		if (needNotification(CorePackage.Literals.ModelPath_pointerString)){
			String _oldValue = mPointerString;
			mPointerString = _pointerString;
			notify(_oldValue, mPointerString, CorePackage.Literals.ModelPath_pointerString, NotificationType.SET);
		}else{
			mPointerString = _pointerString;
		}
	}

	/**
	 textual description from the instance, using the features (and list indices) to the 
	 * required / observed feature
	 * @note the pointerString shall follow this BNF: <FeatureName>(:<ListIndex>)?(,<FeatureName>(:<ListIndex>)?)*
	 * @generated 
	 */
	public String getPointerString() {
		return mPointerString;
	}

	/**
	 Root element from which the pointerString starts and defines the Tree or SubTree root 
	 * @generated 
	 */
	public void setRootInstance(UObject _rootInstance) {
		Notification<UObject> notification = basicSet(mRootInstance, _rootInstance, CorePackage.Literals.ModelPath_rootInstance);
		mRootInstance = _rootInstance;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 Root element from which the pointerString starts and defines the Tree or SubTree root 
	 * @generated 
	 */
	public UObject getRootInstance() {
		return mRootInstance;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	private static Pattern mSyntaxPattern = Pattern.compile("(\\w*)(:(\\d*))?((,|\\.)(\\w*)(:(\\d*))?)*");
	private static Pattern mFindPattern = Pattern.compile("(\\w*)(:(\\d*))?"); 
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean checkPointerString()
	{
		if (getPointerString() == null || getPointerString().isEmpty())
			return true;
		return PointerOperations.checkPointerString(getPointerString().trim());
	}
	
	
	
	
	public static void main(String[] args){
		ModelPathImpl mr = new ModelPathImpl("objects:0.pose.coordinate,x", null);
		boolean c = mr.checkPointerString();
		Matcher m = mFindPattern.matcher(mr.getPointerString());
		int i = 0;
		while(m.find()){
			String featureName = m.group(1);
			if (featureName == null || featureName.isEmpty())
				continue;
			String idx_str = m.group(3);
			int lidx = -1;
			if (idx_str != null && idx_str.isEmpty() == false){
				lidx = Integer.parseInt(idx_str);
			}
			if (lidx >= 0)
				System.out.println(featureName + ":" + lidx);
			else
				System.out.println(featureName);
		}
		
		System.out.println();
	}

	
	private IValueChangeListener mChangeListener = null;
	private Pointer mPointer = null; //stores the last returned pointer
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Pointer getPointer()
	{
		if (mPointer == null && checkPointerString()){
			if (mChangeListener == null){
				//register for own updates, to change the pointer, only if required
				mChangeListener = new IValueChangeListener() {
					@Override
					public void onValueChange(Notification notification) {
						mPointer = null; //invalidate the last returned pointer
					}
				};
				registerListener(mChangeListener);
			}
			mPointer = PointerOperations.createPointerFromString(getRootInstance(), getPointerString());
		}
		return mPointer;
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ModelPathImpl{" +
		" pointerString = " + getPointerString() + 
		"}";
	}
}
