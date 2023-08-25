package de.emir.model.universal.units;

import de.emir.model.universal.units.Length;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**

 * Used as a type for returning distances and possibly lengths. Care must be taken when using distance where length is meant. The distance from start to end of a curve is not the length of the curve, but represents the length of the shortest curve between these two points. Since Distance is a length of some curve (albeit a hypothetical one), the unit of measure is the same.
 * @generated 
 */
@UMLClass(name = "Distance", parent = Length.class)	
public interface Distance extends Length 
{

	/**
	 linear interpolation between two distances
	 * @generated 
	 */
	Distance lerp(final Distance other, final double factor);
	
}
