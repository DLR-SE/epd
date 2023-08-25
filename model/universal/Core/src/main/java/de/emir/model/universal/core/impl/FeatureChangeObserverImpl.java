package de.emir.model.universal.core.impl;

import java.util.List;

import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.FeatureChangeObserver;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.ModelPath;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.model.universal.core.impl.ObserverImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;


/**

 * Observes one or more structural features and triggers the operations, if one of the 
 * assigned trigger's fire. 
 * @generated not
 */
@UMLImplementation(classifier = FeatureChangeObserver.class)
public class FeatureChangeObserverImpl extends ObserverImpl implements FeatureChangeObserver 
{

	/**
	 *	@generated 
	 */
	private ModelPath mReference = null;
	
	
	private IValueChangeListener mReferenceListener ;
	private IValueChangeListener mValueListener = null;


	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public FeatureChangeObserverImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, List<IValueChangeListener> _operations, ModelPath _reference) {
		super(_name,_allias,_remarks,_observers,_identifier,_operations);
		mReference = _reference; 
	}
	private Pointer mPointer;


	private IDisposable mPointerDisposable;
	
	
	private IValueChangeListener getReferenceListener() {
		if (mReferenceListener == null){
			mReferenceListener = new IValueChangeListener() {
				@Override
				public void onValueChange(Notification notification) {
					onReferenceChanged();
				}
			};
		}
		return mReferenceListener;
	}
		
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
	 *	Default constructor
	 *	@generated
	 */
	public FeatureChangeObserverImpl(){
		super();
		//set the default values and assign them to this instance 
		setReference(mReference);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public FeatureChangeObserverImpl(final FeatureChangeObserver _copy) {
		super(_copy);
		mReference = _copy.getReference();
	}

	

	/**
	 *	@generated not 
	 */
	@Override
	public void setReference(ModelPath _reference) {
		if (mReference != null)
			removeReference();
		Notification<ModelPath> notification = basicSet(mReference, _reference, CorePackage.Literals.FeatureChangeObserver_reference);
		mReference = _reference;
		if (notification != null){
			dispatchNotification(notification);
		}
		if (mReference != null)
			addReference();
	}

	private void removeReference() {
		if (mReferenceListener != null)
			mReference.removeListener(getReferenceListener());
		if (mPointerDisposable != null)
			mPointerDisposable.dispose();
		mPointerDisposable = null;
	}
	private void addReference(){
		mReference.registerListener(getReferenceListener());
		mPointer = mReference.getPointer();
		if (mPointer != null)
			mPointerDisposable = PointerOperations.observePointer(mPointer, getValueListener());	
	}

	/**
	 *	@generated 
	 */
	public ModelPath getReference() {
		return mReference;
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CorePackage.Literals.FeatureChangeObserver;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "FeatureChangeObserverImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}	

	
	
	/**
	 * Called, if the reference changes, and thus we have to change our observation target
	 */
	protected void onReferenceChanged() {
		if (mPointerDisposable != null)
			mPointerDisposable.dispose();
		mPointerDisposable = null;
		mPointer = mReference.getPointer();
		if (mPointer != null)
			mPointerDisposable = PointerOperations.observePointer(mPointer, getValueListener());	
//		removeReference();	//remove listener from pointer
//		addReference(); //recreate the pointer and register the listener
	}
	
	
	
	/**
	 * Called if the observed value has been changed
	 * @param _notification
	 */
	public void onValueChangeNotification(Notification _notification) {
		for (IValueChangeListener op : getOperations()){
			try{
				op.onValueChange(_notification);
			}catch(Exception e){
				ULog.error("Failed to notify value change listener: " + op + "; Error: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
