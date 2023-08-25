package de.emir.model.universal.crs;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import java.util.List;

import de.emir.model.universal.math.Vector;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 @Brief local coordinate reference system, only valid in a "small" area around its global position
 * The abstract local CRS indicates is a CRS that is only valid in a certain area (at least in terms of accuracy)
  * therefore it contains a field to define its position within a global coordinate reference system
 * @warn since units are not known yet (defined in unit package) in this class, all distances are defined in Meters (distances), radians (angles) or WGS84 (coordinates)  
 * @generated 
 */
@UMLClass(name = "LocalCRS", isAbstract = true, parent = CoordinateReferenceSystem.class)	
public interface LocalCRS extends CoordinateReferenceSystem 
{
	/**
	 returns the origin of the CRS within a global CRS (e.g. WGS84)
	 *  @note the dimension of the origin vector has to be the same as CoordinateReferenceSystem.dimension()
	 * 
	 * @generated 
	 */
	@UMLProperty(name = "origin", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public void setOrigin(Vector _origin);
	/**
	 returns the origin of the CRS within a global CRS (e.g. WGS84)
	 *  @note the dimension of the origin vector has to be the same as CoordinateReferenceSystem.dimension()
	 * 
	 * @generated 
	 */
	@UMLProperty(name = "origin", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public Vector getOrigin();
	/**
	 returns the orientation offset of this CRS, relativ to a global CRS (e.g. WGS84) 
	 * @return a one dimensional list with the azimuth in radians, if the dimension is 2, otherwise a 3 dimensional rotation in the order [pitch, roll, yaw] (also in radians)
	 * @generated 
	 */
	@UMLProperty(name = "orientationOffset", associationType = AssociationType.PROPERTY, lowerBound = 1)
	public List<Double> getOrientationOffset();

	
}
