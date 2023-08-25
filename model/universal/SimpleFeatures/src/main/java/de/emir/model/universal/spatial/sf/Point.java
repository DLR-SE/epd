package de.emir.model.universal.spatial.sf;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = Geometry.class)	
public interface Point extends Geometry 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "coordinate", associationType = AssociationType.COMPOSITE)
	public void setCoordinate(Coordinate _coordinate);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "coordinate", associationType = AssociationType.COMPOSITE)
	public Coordinate getCoordinate();
	
}
