package de.emir.model.domain.maritime.vessel;

import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.model.universal.units.Speed;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface AutopilotConfiguration extends UObject 
{
	/**
	 Maximum rudder angle the autopilot is allowed to use 
	 * @generated 
	 */
	@UMLProperty(name = "angleLimit", associationType = AssociationType.COMPOSITE)
	public void setAngleLimit(Angle _angleLimit);
	/**
	 Maximum rudder angle the autopilot is allowed to use 
	 * @generated 
	 */
	@UMLProperty(name = "angleLimit", associationType = AssociationType.COMPOSITE)
	public Angle getAngleLimit();
	/**
	 Maximum rate of turn, the autopilot ia allowed to use 
	 * @generated 
	 */
	@UMLProperty(name = "maxTurnRate", associationType = AssociationType.COMPOSITE)
	public void setMaxTurnRate(AngularSpeed _maxTurnRate);
	/**
	 Maximum rate of turn, the autopilot ia allowed to use 
	 * @generated 
	 */
	@UMLProperty(name = "maxTurnRate", associationType = AssociationType.COMPOSITE)
	public AngularSpeed getMaxTurnRate();
	/**
	 minimum speed (sog), the autopilot is allowed to use 
	 * @generated 
	 */
	@UMLProperty(name = "minSpeed", associationType = AssociationType.COMPOSITE)
	public void setMinSpeed(Speed _minSpeed);
	/**
	 minimum speed (sog), the autopilot is allowed to use 
	 * @generated 
	 */
	@UMLProperty(name = "minSpeed", associationType = AssociationType.COMPOSITE)
	public Speed getMinSpeed();
	/**
	 maximum speed (sog), the autopliot is allowed to use 
	 * @generated 
	 */
	@UMLProperty(name = "maxSpeed", associationType = AssociationType.COMPOSITE)
	public void setMaxSpeed(Speed _maxSpeed);
	/**
	 maximum speed (sog), the autopliot is allowed to use 
	 * @generated 
	 */
	@UMLProperty(name = "maxSpeed", associationType = AssociationType.COMPOSITE)
	public Speed getMaxSpeed();
	
}
