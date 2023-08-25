package de.emir.service.geometry;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 *	@generated 
 */
@UMLClass(isAbstract = true)	
public interface ISpatialIndexVisitor extends UObject 
{
	
	/**
	 returns true, if the next item should be visited 
	 * @generated 
	 */
	boolean visit(final Object obj);
	
}
