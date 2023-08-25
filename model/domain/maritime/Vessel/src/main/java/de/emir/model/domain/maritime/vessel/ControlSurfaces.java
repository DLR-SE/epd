package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.Rudder;
import java.util.List;

import de.emir.model.universal.units.AngularSpeed;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * These forces and moments arise due to the Control Surfaces (CS) like rudder, fins, etc. movement
 * 
 * \source Mathematical Ship Modeling for Control Applications, 2002, Perez, Blanket
 * @generated 
 */
@UMLClass(name = "ControlSurfaces")	
public interface ControlSurfaces extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "maximumRateOfTurn", associationType = AssociationType.COMPOSITE)
	public void setMaximumRateOfTurn(AngularSpeed _maximumRateOfTurn);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "maximumRateOfTurn", associationType = AssociationType.COMPOSITE)
	public AngularSpeed getMaximumRateOfTurn();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "rudders", associationType = AssociationType.COMPOSITE)
	public List<Rudder> getRudders();
	
}
