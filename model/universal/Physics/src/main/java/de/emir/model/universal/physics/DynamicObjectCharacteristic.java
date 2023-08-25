package de.emir.model.universal.physics;

import de.emir.model.universal.physics.Characteristic;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngularVelocity;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.Velocity;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "DynamicObjectCharacteristic", parent = Characteristic.class)	
public interface DynamicObjectCharacteristic extends Characteristic 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "angularVelocity", associationType = AssociationType.COMPOSITE)
	public void setAngularVelocity(AngularVelocity _angularVelocity);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "angularVelocity", associationType = AssociationType.COMPOSITE)
	public AngularVelocity getAngularVelocity();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "linearVelocity", associationType = AssociationType.COMPOSITE)
	public void setLinearVelocity(Velocity _linearVelocity);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "linearVelocity", associationType = AssociationType.COMPOSITE)
	public Velocity getLinearVelocity();
	
	/**
	 *	@generated 
	 */
	void set(final Velocity linVel, final AngularVelocity angVal);
	/**
	 *	@generated 
	 */
	void calculate(final Coordinate loc0, final Coordinate loc1, final Angle heading0, final Angle heading1, final Time elapsed);
	
}
