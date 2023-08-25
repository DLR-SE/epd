package de.emir.service.geometry.impl;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.math.Matrix2;
import de.emir.model.universal.math.Matrix3;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.ops.GeometryOperations;
import de.emir.model.universal.units.Rotation;
import de.emir.service.geometry.GeometryPackage;
import de.emir.service.geometry.IGeometryTransform;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = IGeometryTransform.class)
public class GeometryTransform extends UObjectImpl implements IGeometryTransform  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public GeometryTransform(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public GeometryTransform(final IGeometryTransform _copy) {
	}
	
	
	/**
	 * @generated
	 */
	@Override
	public UClass getUClassifier() {
		return GeometryPackage.Literals.GeometryTransform;
	}
	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */
	@Override
	public Geometry transformGeometry2D(final Geometry geom, final Matrix2 mat)
	{
		//TODO: 
		throw new UnsupportedOperationException("transformGeometry2D not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	@Override
	public Geometry transformGeometry3D(final Geometry geom, final Matrix3 mat)
	{
		//TODO: 
		throw new UnsupportedOperationException("transformGeometry3D not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	@Override
	public Geometry transformGeometry(final Geometry geom, final Rotation rot, final Coordinate translation)
	{
		//TODO: 
		throw new UnsupportedOperationException("transformGeometry not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	@Override
	public Geometry normalizeGeometry(final Geometry geom)
	{
		//TODO: 
		throw new UnsupportedOperationException("normalizeGeometry not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void scaleGeometryLocal(final Vector _scale, Geometry geom)
	{
		if (_scale.dimensions() != geom.getDimension()) //nullpointer on scale or geom == null is wanted.
			throw new UnsupportedOperationException("Scale factors and geometry dimensions differ");

		
		CoordinateReferenceSystem currentCRS = geom.getCRS();
		if (currentCRS == null)
			throw new NullPointerException("Missing Coordinate Reference System");
		
		if (_scale.dimensions() == 2){
			Vector2D scale = (Vector2D)_scale;
			//convert all points into an engineering crs (relative to the center of the bounding box) and do the operation there
//			Envelope env = geom.getEnvelope();
//			Engineering2DImpl crs = new Engineering2DImpl();
//			crs.setOrigin(env.getCenter().get(CRSUtils.WGS84_2D).toVector());
			
			for (int i = 0; i < geom.numCoordinates(); i++){
				Coordinate c = geom.getCoordinate(i);//.get(crs);
				c.set(c.getX() * scale.getX(), c.getY() * scale.getY(), Double.NaN);
//				geom.getCoordinate(i).set(c.get(currentCRS));
			}
		}else{
			throw new UnsupportedOperationException("Only 2D Scale supported for now");			
		}
		
		//invalidate the envelope of the geometry
		GeometryOperations del = geom.getDelegate();
		if (del != null)
			del.invalidate();
		
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void transformGeometryLocal(final Coordinate dst, Geometry geom)
	{
		if (dst.dimension() != geom.getDimension()) //nullpointer on dst or geom == null is wanted.
			throw new UnsupportedOperationException("Scale factors and geometry dimensions differ");
		
		CoordinateReferenceSystem currentCRS = geom.getCRS();
		if (currentCRS == null)
			throw new NullPointerException("Missing Coordinate Reference System");
		
		if (dst.dimension() == 2){
			GeometryOperations del = geom.getDelegate();
			if (del != null)
				del.invalidate();
			//convert all points into an engineering crs (relative to the center of the bounding box) and do the operation there
			Envelope env = geom.getEnvelope();
			Coordinate center = env.getCenter();
//			Engineering2DImpl crs = new Engineering2DImpl();
//			crs.setOrigin(env.getCenter().get(CRSUtils.WGS84_2D).toVector());
			
			Coordinate d = dst.get(currentCRS);
			d.setXY(d.getX() - center.getX(), d.getY() - center.getY());
			
			for (int i = 0; i < geom.numCoordinates(); i++){
				Coordinate c = geom.getCoordinate(i);//.get(crs);
				c.set(c.getX() + d.getX(), c.getY() + d.getY(), Double.NaN);
				geom.getCoordinate(i).set(c.get(currentCRS));
			}
		}else{
			throw new UnsupportedOperationException("Only 2D transform supported for now");			
		}
		
		//invalidate the envelope of the geometry
		GeometryOperations del = geom.getDelegate();
		if (del != null)
			del.invalidate();
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "GeometryTransform{" +
		"}";
	}
}
