package de.emir.model.universal.spatial.sf;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 Same as the LineString but has to be closed (first coordinate has to be the same as the last) 
 * @generated 
 */
@UMLClass(parent = LineString.class)	
public interface LinearRing extends LineString 
{
	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	boolean isClosed();
	
	/**
	 closes the ring by copying the first coordinate to the point list, if not already closed 
	 * @generated 
	 */
	void close();
	
}
