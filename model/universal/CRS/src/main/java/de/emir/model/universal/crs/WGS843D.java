package de.emir.model.universal.crs;

import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 * Defines the default global CRS to be used in eMIR, for 3 dimensions.
 * The WGS843D differs fom its 2D counterpart (WGS842D) by adding the 3rd dimension
 * altitude as the z component. The altitude is defined as the distance away from the
 * center of the ellipsoid defined by WGS84 from the sea level in meters.
 * A Coordinate is defined as lat,lon,altitude in a left-hand Coordinate System.
 *
 * 2-dimensional objects (WGS842D) are defined as infinite lines from the center of the
 * ellipsoid towards its 2D coordinates (WGS842D) on sea level.
 * @generated 
 */
@UMLClass(name = "WGS843D", parent = WGS84CRS.class)	
public interface WGS843D extends WGS84CRS 
{
	
}
