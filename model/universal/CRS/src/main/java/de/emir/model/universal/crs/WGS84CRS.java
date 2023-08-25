package de.emir.model.universal.crs;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 @Brief Global WGS84 coordinate reference system, commonly used in most domains.  
 * the WGS84 coordinate reference system is one of the most frequently used systems, 
 * therefore we provide optimized implementations
 * @generated 
 */
@UMLClass(name = "WGS84CRS", isAbstract = true, parent = CoordinateReferenceSystem.class)	
public interface WGS84CRS extends CoordinateReferenceSystem 
{
	
}
