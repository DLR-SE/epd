package de.emir.model.universal.physics.impl;

import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.physics.PoseChangeCapability;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.Orientation;
import de.emir.model.universal.physics.impl.CapabilityImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = PoseChangeCapability.class)
public class PoseChangeCapabilityImpl extends CapabilityImpl implements PoseChangeCapability  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public PoseChangeCapabilityImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public PoseChangeCapabilityImpl(final PoseChangeCapability _copy) {
		super(_copy);
	}
	
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.PoseChangeCapability;
	}

	

	
	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean setLocation(final Coordinate coordinate)
	{
		//TODO: 
		throw new UnsupportedOperationException("setLocation not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean setOrientation(final Orientation rotation)
	{
		//TODO: 
		throw new UnsupportedOperationException("setOrientation not yet implemented");
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "PoseChangeCapabilityImpl{" +
		"}";
	}
}
