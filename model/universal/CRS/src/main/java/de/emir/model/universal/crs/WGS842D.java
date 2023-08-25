package de.emir.model.universal.crs;

import de.emir.model.universal.crs.WGS84CRS;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 Defines the default global coordinate reference system (WGS84) for eMIR with 2 dimensions.
 * The WGS842D CRS corresponds to the EPSG code: urn:ogc:def:crs:EPSG:6.6:4326 (defined by GeoTools)
 * @note since, the order of WGS84 coordinates differes between different applications (either lat/lon or lon/lat)
 * we define the lat/lon order to be the default order for WGS84 in eMIR (thats the reason to not use the EPSG:4326) 
 * Thereby we follow the chartographic order instead of the informatics/mathematics point of view. 
 * @generated 
 */
@UMLClass(name = "WGS842D", parent = WGS84CRS.class)	
public interface WGS842D extends WGS84CRS 
{
	
}
