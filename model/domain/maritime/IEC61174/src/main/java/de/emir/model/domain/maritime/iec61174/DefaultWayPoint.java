package de.emir.model.domain.maritime.iec61174;

import de.emir.model.domain.maritime.iec61174.IECElementWithExtension;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = IECElementWithExtension.class)	
public interface DefaultWayPoint extends IECElementWithExtension 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "radius", associationType = AssociationType.PROPERTY)
	public void setRadius(double _radius);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "radius", associationType = AssociationType.PROPERTY)
	public double getRadius();

	
}
