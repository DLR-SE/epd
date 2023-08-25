package de.emir.service.geometry;

import de.emir.model.universal.math.Matrix2;
import de.emir.model.universal.math.Matrix3;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.units.Rotation;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;

/**
 *	@generated 
 */
@UMLClass	
public interface IGeometryTransform extends UObject 
{
	
	/**
	 *	@generated 
	 */
	Geometry transformGeometry2D(final Geometry geom, final Matrix2 mat);

	/**
	 *	@generated 
	 */
	Geometry transformGeometry3D(final Geometry geom, final Matrix3 mat);

	/**
	 *	@generated 
	 */
	Geometry transformGeometry(final Geometry geom, final Rotation rot, final Coordinate translation);

	/**
	 *	@generated 
	 */
	Geometry normalizeGeometry(final Geometry geom);

	/**
	 *	@generated 
	 */
	void scaleGeometryLocal(final Vector scale, Geometry geom);

	/**
	 Moves all points that the center of the geometry corresponds with the dst coordinate 
	 * @generated 
	 */
	void transformGeometryLocal(final Coordinate dst, Geometry geom);
	
}
