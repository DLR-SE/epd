package de.emir.model.universal.physics.impl;

import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.physics.EnvironmentFactor;
import de.emir.model.universal.physics.EnvironmentFactorLayer;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.physics.impl.EnvironmentLayerImpl;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;


/**
 *	@generated 
 */
@UMLImplementation(classifier = EnvironmentFactorLayer.class)
public class EnvironmentFactorLayerImpl extends EnvironmentLayerImpl implements EnvironmentFactorLayer  
{
	
	
	/**
	 *	@generated 
	 */
	private List<EnvironmentFactor> mEnvironmentFactors = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public EnvironmentFactorLayerImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public EnvironmentFactorLayerImpl(final EnvironmentFactorLayer _copy) {
		super(_copy);
		mEnvironmentFactors = _copy.getEnvironmentFactors();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public EnvironmentFactorLayerImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, List<EnvironmentFactor> _environmentFactors) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mEnvironmentFactors = _environmentFactors; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.EnvironmentFactorLayer;
	}

	/**
	 *	@generated 
	 */
	public List<EnvironmentFactor> getEnvironmentFactors() {
		if (mEnvironmentFactors == null) {
			mEnvironmentFactors = new UContainmentList<EnvironmentFactor>(this, PhysicsPackage.theInstance.getEnvironmentFactorLayer_environmentFactors()); 
		}
		return mEnvironmentFactors;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "EnvironmentFactorLayerImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
