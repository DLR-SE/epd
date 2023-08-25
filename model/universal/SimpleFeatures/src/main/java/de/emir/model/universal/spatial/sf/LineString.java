package de.emir.model.universal.spatial.sf;

import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = Geometry.class)	
public interface LineString extends Geometry 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "points", associationType = AssociationType.COMPOSITE)
	public void setPoints(CoordinateSequence _points);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "points", associationType = AssociationType.COMPOSITE)
	public CoordinateSequence getPoints();
	
}
