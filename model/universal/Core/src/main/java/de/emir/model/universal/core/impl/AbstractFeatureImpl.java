package de.emir.model.universal.core.impl;

import java.util.List;

import de.emir.model.universal.core.AbstractFeature;
import de.emir.model.universal.core.AbstractInformation;
import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.core.impl.IdentifiedObjectImpl;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.ListUtils;


/**
 *	@generated 
 */
@UMLImplementation(classifier = AbstractFeature.class)
abstract public class AbstractFeatureImpl extends IdentifiedObjectImpl implements AbstractFeature  
{
	
	
	/**
	 *	@generated 
	 */
	private List<AbstractInformation> mInformationObjects = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public AbstractFeatureImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public AbstractFeatureImpl(final AbstractFeature _copy) {
		super(_copy);
		mInformationObjects = _copy.getInformationObjects();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public AbstractFeatureImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, List<AbstractInformation> _informationObjects) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mInformationObjects = _informationObjects; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CorePackage.Literals.AbstractFeature;
	}

	/**
	 *	@generated 
	 */
	public List<AbstractInformation> getInformationObjects() {
		if (mInformationObjects == null) {
			mInformationObjects = ListUtils.<AbstractInformation>asList(this, CorePackage.theInstance.getAbstractFeature_informationObjects()); 
		}
		return mInformationObjects;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "AbstractFeatureImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
