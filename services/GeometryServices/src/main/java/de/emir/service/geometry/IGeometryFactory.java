package de.emir.service.geometry;

import java.util.List;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface IGeometryFactory extends IGeometryProvider 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "provider", associationType = AssociationType.COMPOSITE)
	public List<IGeometryProvider> getProvider();

	/**
	 *	@generated 
	 */
	@Override
	Geometry createGeometry(final Object native_geom, final CoordinateReferenceSystem sourceCRS, final CoordinateReferenceSystem dstCRS);
	
}
