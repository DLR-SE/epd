package de.emir.model.universal.crs;

import de.emir.model.universal.crs.WGS84CRS;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 Defines the default global CRS to be used in eMIR, for 3 dimensions. 
 * The WGS843D differes from its 2D version (WGS842D) that the z component of a coordinate is interpreted as altitude (in meters). 
 * @generated 
 */
@UMLClass(name = "WGS843D", parent = WGS84CRS.class)	
public interface WGS843D extends WGS84CRS 
{
	
}
