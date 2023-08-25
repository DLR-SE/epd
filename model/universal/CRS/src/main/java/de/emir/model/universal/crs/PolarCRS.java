package de.emir.model.universal.crs;

import de.emir.model.universal.crs.LocalCRS;
import java.util.List;

import de.emir.model.universal.math.Vector;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 *	@generated 
 */
@UMLClass(name = "PolarCRS", isAbstract = true, parent = LocalCRS.class)	
public interface PolarCRS extends LocalCRS 
{
	
	/**
	 *	@generated 
	 */
	List<Vector> toPolarCoordinates(final List<Vector> vertices);

	/**
	 *	@generated 
	 */
	List<Vector> toPolarCoordinates(final Vector center, final List<Vector> vertices);
	
}
