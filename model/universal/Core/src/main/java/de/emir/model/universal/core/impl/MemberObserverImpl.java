package de.emir.model.universal.core.impl;

import java.util.List;

import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.model.universal.core.impl.ObserverImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;


/**
 *	@generated 
 */
@UMLImplementation(classifier = MemberObserver.class)
public class MemberObserverImpl extends ObserverImpl implements MemberObserver  
{
	
	
	private Pointer mPointer;
	@SuppressWarnings("rawtypes")
	private IValueChangeListener mValueListener = null;
			


	@SuppressWarnings("rawtypes")
	private IValueChangeListener getValueListener() {
		if (mValueListener == null){
			mValueListener = new IValueChangeListener() {
				@Override
				public void onValueChange(Notification notification) {
					onValueChangeNotification(notification);
				}
			};
		}
		return mValueListener;
	}
	


	/**
	 textual description from the instance, using the features (and list indices) to the 
	 * required / observed feature
	 * @note the pointerString shall follow this BNF: <FeatureName>(:<ListIndex>)?(,<FeatureName>(:<ListIndex>)?)*
	 * @generated 
	 */
	private String mPointerString = "";
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public MemberObserverImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public MemberObserverImpl(final MemberObserver _copy) {
		super(_copy);
		mPointerString = _copy.getPointerString();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public MemberObserverImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, List<IValueChangeListener> _operations, String _pointerString) {
		super(_name,_allias,_remarks,_observers,_identifier,_operations);
		mPointerString = _pointerString;
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CorePackage.Literals.MemberObserver;
	}

	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 textual description from the instance, using the features (and list indices) to the 
	 * required / observed feature
	 * @note the pointerString shall follow this BNF: <FeatureName>(:<ListIndex>)?(,<FeatureName>(:<ListIndex>)?)*
	 * @generated not
	 */
	@Override
	public void setPointerString(String _pointerString) {
		if (mPointerString != null)
			removeReference();
		if (needNotification(CorePackage.Literals.MemberObserver_pointerString)){
			notify(mPointerString, _pointerString, CorePackage.Literals.MemberObserver_pointerString, NotificationType.SET);
			mPointerString = _pointerString;
		}else{
			mPointerString = _pointerString;
		}
		if (mPointerString != null)
			addReference();		
	}
	
	private void removeReference() {
		mPointer = null; //invalidate the pointer to the observed object
		if (mPointerString == null) return ;
		if (mValueListener == null) return ; //has not been registered yet (first call)
		
		Pointer ptr = getPointer();
		if (ptr == null || ptr.isValid() == false) return ; //we have nothing to deregister
		if (mPointerDisposable != null)
			mPointerDisposable.dispose();		
	}
	private IDisposable mPointerDisposable;
	private void addReference() {
		if (mPointerString == null) return ; //nothing to observe
		
		UObject container = getUContainer(); 
		if (container == null){
			//this should only happen, if the object is loaded from file. The deserializer first fully equippe the model and than add it to the parent
			//in this case we have to wait a few milliseconds until the object is fully loaded
			//TODO: find a better solution
			new Thread(){
				@Override
				public void run(){
					for (int i = 0; i < 30; i++) { //do 3 trys
						try{Thread.sleep(10);}catch(Exception e){}
						Pointer ptr = getPointer();
						if (ptr != null && ptr.isValid()){
							mPointerDisposable = PointerOperations.observePointer(ptr, getValueListener()); //also creates the listener if not yet available.
							return ;
						}
					}
					return ;
				}
			}.start();
		}else{
			Pointer ptr = getPointer(); 
			if (ptr == null || ptr.isValid() == false) return ; //nothing to observe
			
			mPointerDisposable = PointerOperations.observePointer(ptr, getValueListener()); //also creates the listener if not yet available.
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

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean checkPointerString()
	{
		if (mPointerString == null || mPointerString.isEmpty())
			return false;
		return PointerOperations.checkPointerString(mPointerString);
	}

	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Pointer getPointer()
	{
		if (mPointer == null && checkPointerString()){
			UObject parent = getUContainer(); 
			if (parent == null){
				return null; 
			}
			mPointer = PointerOperations.createPointerFromString(parent, getPointerString());
		}
		return mPointer; //may return null, if pointerstring is not valid 
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "MemberObserverImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" pointerString = " + getPointerString() + 
		"}";
	}
	
	/**
	 * Called if the observed feature changes
	 * @param notification
	 */
	protected void onValueChangeNotification(Notification notification) {
		List<IValueChangeListener> obs = getOperations();
		if (obs.isEmpty() == false){
			for (IValueChangeListener vcl : obs){
				try{
					vcl.onValueChange(notification);
				}catch(Exception e){
					ULog.error("Failed to execute Operation " + vcl);
					e.printStackTrace();
				}
			}
		}
	}
}
