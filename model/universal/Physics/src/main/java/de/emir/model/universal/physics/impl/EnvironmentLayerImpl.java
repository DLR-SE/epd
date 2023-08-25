package de.emir.model.universal.physics.impl;

import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.impl.IdentifiedObjectImpl;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.physics.Environment;
import de.emir.model.universal.physics.EnvironmentLayer;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = EnvironmentLayer.class)
abstract public class EnvironmentLayerImpl extends IdentifiedObjectImpl implements EnvironmentLayer  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public EnvironmentLayerImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public EnvironmentLayerImpl(final EnvironmentLayer _copy) {
		super(_copy);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public EnvironmentLayerImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier) {
		super(_name,_allias,_remarks,_observers,_identifier);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.EnvironmentLayer;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public CoordinateReferenceSystem getEnvironmentCRS()
	{
		Environment env = getEnvironment();
		if (env != null)
			return env.getCrs();
		return null;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Environment getEnvironment()
	{
		UObject c = getUContainer();
		if (c instanceof Environment)
			return (Environment)c;
		return null;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "EnvironmentLayerImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
