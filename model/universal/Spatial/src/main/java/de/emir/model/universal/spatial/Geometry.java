package de.emir.model.universal.spatial;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Envelope;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "Geometry", isAbstract = true)	
public interface Geometry extends UObject 
{
	/**
	 *	@generated 
	 */
	CoordinateSequence getCoordinates();
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	int getDimension();
	
	/**
	 *	@generated 
	 */
	int numCoordinates();
	
	/**
	 *	@generated 
	 */
	Coordinate getCoordinate(final int index);
	/**
	 *	@generated 
	 */
	void setCoordinate(final int index, final Coordinate coord);
	/**
	 *	@generated 
	 */
	void removeCoordinate(final int index);
	/**
	 *	@generated 
	 */
	int getNumGeometries();
	
	/**
	 *	@generated 
	 */
	Geometry getGeometry(final int idx);
	/**
	 *	@generated 
	 */
	Envelope getEnvelope();
	
	/**
	 *	@generated 
	 */
	boolean intersects(final Geometry geom);
	/**
	 *	@generated 
	 */
	boolean isConvex();
	/**
	 Applys the coordinate reference system to all coordinates in this geometry, without changing their instance
	 * e.g. change the values of the coordinate
	 * @generated 
	 */
	void applyCRS(final CoordinateReferenceSystem crs);
	/**
	 sets the coordinate system for the geometrie and all contained coordinates 
	 * @generated 
	 */
	void recursiveSetCRS(final CoordinateReferenceSystem crs);
	/**
	 *	@generated 
	 */
	CoordinateReferenceSystem getCRS();	
	
}
