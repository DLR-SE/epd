package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.Engine;
import de.emir.model.domain.maritime.vessel.Propeller;
import java.util.List;

import de.emir.model.universal.units.Acceleration;
import de.emir.model.universal.units.Velocity;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * Propulsion is a means of creating force leading to movement. 
 * 
 * A propulsion system consists of a source of mechanical power, and a propulsor (means of converting this power into propulsive force).
 * A technological system uses an engine or motor as the power source, and wheels and axles, propellers, or a propulsive nozzle to generate the force. 
 * Components such as clutches or gearboxes may be needed to connect the motor to axles, wheels, or propellors.
 * 
 * \source wikipedia
 * @generated 
 */
@UMLClass(name = "PropulsionSystem")	
public interface PropulsionSystem extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "maximumVelocity", associationType = AssociationType.COMPOSITE)
	public void setMaximumVelocity(Velocity _maximumVelocity);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "maximumVelocity", associationType = AssociationType.COMPOSITE)
	public Velocity getMaximumVelocity();
	/**
	 may be negativ to indicate backwards movements 
	 * @generated 
	 */
	@UMLProperty(name = "minimumVelocity", associationType = AssociationType.COMPOSITE)
	public void setMinimumVelocity(Velocity _minimumVelocity);
	/**
	 may be negativ to indicate backwards movements 
	 * @generated 
	 */
	@UMLProperty(name = "minimumVelocity", associationType = AssociationType.COMPOSITE)
	public Velocity getMinimumVelocity();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "maximumAcceleration", associationType = AssociationType.COMPOSITE)
	public void setMaximumAcceleration(Acceleration _maximumAcceleration);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "maximumAcceleration", associationType = AssociationType.COMPOSITE)
	public Acceleration getMaximumAcceleration();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "minimumAcceleration", associationType = AssociationType.COMPOSITE)
	public void setMinimumAcceleration(Acceleration _minimumAcceleration);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "minimumAcceleration", associationType = AssociationType.COMPOSITE)
	public Acceleration getMinimumAcceleration();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "engines", associationType = AssociationType.COMPOSITE)
	public List<Engine> getEngines();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "propellers", associationType = AssociationType.COMPOSITE)
	public List<Propeller> getPropellers();
	
}
