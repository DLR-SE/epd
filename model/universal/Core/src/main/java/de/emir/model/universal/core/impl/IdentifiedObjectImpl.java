package de.emir.model.universal.core.impl;

import java.util.ArrayList;
import java.util.List;

import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.IdentifiedObject;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.impl.NamedElementImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.ListUtils;


/**
 Any object that can be identified shall inherit from this class 
 * @generated 
 */
@UMLImplementation(classifier = IdentifiedObject.class)
abstract public class IdentifiedObjectImpl extends NamedElementImpl implements IdentifiedObject  
{
	
	
	/**
	 An alternative name of the object 
	 * @generated 
	 */
	private List<String> mAllias = null;
	/**
	 Comments on or information about the object 
	 * @generated 
	 */
	private String mRemarks = "";
	/**
	
	 * Member Observer may be used to observe changes of structural features within this object
	 * @deprecated will be removed soon, as not used
	 * @generated not
	 */
	@Deprecated
	private List<MemberObserver> mObservers = null;
	/**
	 An identifier that references the (external) definition of the object 
	 * @generated 
	 */
	private RSIdentifier mIdentifier = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public IdentifiedObjectImpl(){
		super();
		//set the default values and assign them to this instance 
		setIdentifier(mIdentifier);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public IdentifiedObjectImpl(final IdentifiedObject _copy) {
		super(_copy);
		getAllias().addAll(_copy.getAllias());
		mRemarks = _copy.getRemarks();
		mObservers = _copy.getObservers();
		mIdentifier = _copy.getIdentifier();
	}
	

	/**
	 *	Old attribute constructor for backwards compatibility
	 */
	public IdentifiedObjectImpl(RSIdentifier _name, List<String> _allias, String _remarks, RSIdentifier _identifier) {
		super(_name);
		getAllias().addAll(_allias);
		mRemarks = _remarks;
		mIdentifier = _identifier; 
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public IdentifiedObjectImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier) {
		super(_name);
		getAllias().addAll(_allias);
		mRemarks = _remarks;
		mObservers = _observers; 
		mIdentifier = _identifier; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CorePackage.Literals.IdentifiedObject;
	}

	/**
	 An alternative name of the object 
	 * @generated 
	 */
	public List<String> getAllias() {
		if (mAllias == null) {
			mAllias = new ArrayList<String>(); 
		}
		return mAllias;
	}

	/**
	 Comments on or information about the object 
	 * @generated 
	 */
	public void setRemarks(String _remarks) {
		if (needNotification(CorePackage.Literals.IdentifiedObject_remarks)){
			String _oldValue = mRemarks;
			mRemarks = _remarks;
			notify(_oldValue, mRemarks, CorePackage.Literals.IdentifiedObject_remarks, NotificationType.SET);
		}else{
			mRemarks = _remarks;
		}
	}

	/**
	 Comments on or information about the object 
	 * @generated 
	 */
	public String getRemarks() {
		return mRemarks;
	}

	/**
	 An identifier that references the (external) definition of the object 
	 * @generated 
	 */
	public void setIdentifier(RSIdentifier _identifier) {
		Notification<RSIdentifier> notification = basicSet(mIdentifier, _identifier, CorePackage.Literals.IdentifiedObject_identifier);
		mIdentifier = _identifier;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	
	 * Member Observer may be used to observe changes of structural features within this object
	 * @deprecated will be removed soon, as not used
	 * @generated 
	 */
	public List<MemberObserver> getObservers() {
		if (mObservers == null) {
			mObservers = ListUtils.<MemberObserver>asList(this, CorePackage.theInstance.getIdentifiedObject_observers()); 
		}
		return mObservers;
	}

	/**
	 An identifier that references the (external) definition of the object 
	 * @generated 
	 */
	public RSIdentifier getIdentifier() {
		return mIdentifier;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean hasAlias(final String _name)
	{
		for (String str : getAllias())
			if (_name.equals(str))
				return true;
		return false;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "IdentifiedObjectImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
