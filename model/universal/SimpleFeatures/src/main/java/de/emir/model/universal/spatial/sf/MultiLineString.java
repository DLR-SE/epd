package de.emir.model.universal.spatial.sf;

import java.util.List;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = MultiGeometry.class)	
public interface MultiLineString extends MultiGeometry 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "lines", associationType = AssociationType.COMPOSITE)
	public List<LineString> getLines();
	
}
