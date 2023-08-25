package de.emir.model.universal.spatial.sf;

import java.util.List;

import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = Geometry.class)	
public interface Polygon extends Geometry 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "shell", associationType = AssociationType.COMPOSITE)
	public void setShell(LinearRing _shell);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "shell", associationType = AssociationType.COMPOSITE)
	public LinearRing getShell();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "holes", associationType = AssociationType.COMPOSITE)
	public List<LinearRing> getHoles();
	
}
