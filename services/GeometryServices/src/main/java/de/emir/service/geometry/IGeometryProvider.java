package de.emir.service.geometry;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLInterface;

/**
 *	@generated 
 */
@UMLInterface
public interface IGeometryProvider extends UObject 
{
	
	/**
	 *	@generated 
	 */
	Geometry createGeometry(final Object native_geom, final CoordinateReferenceSystem sourceCRS, final CoordinateReferenceSystem dstCRS);
}
