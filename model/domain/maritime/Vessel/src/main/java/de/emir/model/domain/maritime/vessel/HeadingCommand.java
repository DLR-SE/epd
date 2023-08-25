package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.SteeringCommand;
import de.emir.model.universal.units.Angle;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * The commanded heading, which is not directly depended
 * on any specific rudder angle.
 * @generated 
 */
@UMLClass(parent = SteeringCommand.class)	
public interface HeadingCommand extends SteeringCommand 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "heading", associationType = AssociationType.COMPOSITE)
	public void setHeading(Angle _heading);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "heading", associationType = AssociationType.COMPOSITE)
	public Angle getHeading();
	
}
