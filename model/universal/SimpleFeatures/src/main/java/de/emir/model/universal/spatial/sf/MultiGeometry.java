package de.emir.model.universal.spatial.sf;

import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import java.util.List;

/**
 *	@generated 
 */
@UMLClass(isAbstract = true, parent = Geometry.class)	
public interface MultiGeometry extends Geometry 
{

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "geometries", associationType = AssociationType.COMPOSITE)
	public List<Geometry> getGeometries();
	
}
