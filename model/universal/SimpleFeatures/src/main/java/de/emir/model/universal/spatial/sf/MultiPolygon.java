package de.emir.model.universal.spatial.sf;

import java.util.List;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = MultiGeometry.class)	
public interface MultiPolygon extends MultiGeometry 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "polygons", associationType = AssociationType.COMPOSITE)
	public List<Polygon> getPolygons();
	
}
