package de.emir.model.universal.spatial;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;
import java.util.List;

/**

 * A coordinate sequence serves as container for the coordinates, forming a geometry
 * @generated 
 */
@UMLClass	
public interface CoordinateSequence extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "crs", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setCrs(CoordinateReferenceSystem _crs);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "crs", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public CoordinateReferenceSystem getCrs();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "xCoordinates", associationType = AssociationType.PROPERTY)
	public List<Double> getXCoordinates();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "yCoordinates", associationType = AssociationType.PROPERTY)
	public List<Double> getYCoordinates();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "zCoordinates", associationType = AssociationType.PROPERTY)
	public List<Double> getZCoordinates();
	/**
	 *	@generated 
	 */
	int dimension();
	/**
	 *	@generated 
	 */
	int numCoordinates();

	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	Coordinate getCoordinate(final int idx);
	
	/**
	 *	@generated 
	 */
	void setCoordinate(final int idx, final Coordinate value);
	
	/**
	 *	@generated 
	 */
	void addCoordinate(final Coordinate value);
	/**
	 *	@generated 
	 */
	void addCoordinate(final int idx, final Coordinate value);
	
	/**
	 *	@generated 
	 */
	void removeCoordinate(final int idx);
	/**
	 *	@generated 
	 */
	void removeCoordinate(final Coordinate coord);
	
	/**
	 returns the boundingbox containing all coordinates within this sequence 
	 * @generated 
	 */
	Envelope getEnvelope();
	/**
	 *	@generated 
	 */
	int getIndexOf(final Coordinate coord);
	
}
