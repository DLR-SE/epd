package de.emir.model.universal.units;

import de.emir.model.universal.units.PredefinedColour;
import de.emir.model.universal.units.RGBColor;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "Color", parent = RGBColor.class)	
public interface Color extends RGBColor 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "color", associationType = AssociationType.PROPERTY)
	public void setColor(PredefinedColour _color);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "color", associationType = AssociationType.PROPERTY)
	public PredefinedColour getColor();

	
}
