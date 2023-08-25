package de.emir.model.universal.spatial.sf;

import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 The WKTGeometry is a helper geometry, where the geometry is specified using a WKT (Well known text) String
 * the internal geometry (getNativeGeometry()) is determinated at runtime
 * @generated 
 */
@UMLClass(parent = Geometry.class)	
public interface WKTGeometry extends Geometry 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "wkt", associationType = AssociationType.PROPERTY)
	public void setWkt(String _wkt);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "wkt", associationType = AssociationType.PROPERTY)
	public String getWkt();

	
}
