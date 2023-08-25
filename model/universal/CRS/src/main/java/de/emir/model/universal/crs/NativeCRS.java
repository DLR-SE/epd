package de.emir.model.universal.crs;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 generic coordinate reference system, that can either be specified using an EPSG code (like 4326 for WGS84) or an WKT string 
 * @generated 
 */
@UMLClass(name = "NativeCRS", parent = CoordinateReferenceSystem.class)	
public interface NativeCRS extends CoordinateReferenceSystem 
{
	/**
	 wkt string, describing the native coordinate reference system 
	 * @generated 
	 */
	@UMLProperty(name = "wkt", associationType = AssociationType.PROPERTY)
	public void setWkt(String _wkt);
	/**
	 wkt string, describing the native coordinate reference system 
	 * @generated 
	 */
	@UMLProperty(name = "wkt", associationType = AssociationType.PROPERTY)
	public String getWkt();

	
}
