package de.emir.model.universal.spatial.impl;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.spatial.delegate.IGeometryDelegationInterface;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Geometry.class)
abstract public class GeometryImpl extends UObjectImpl implements Geometry  
{
	
	
	/**
	 *	Default constructor
	 *	@generated
	 */
	public GeometryImpl(){
		super();
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public GeometryImpl(final Geometry _copy) {

	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public abstract CoordinateSequence getCoordinates();

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SpatialPackage.Literals.Geometry;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public int getDimension() {
		return getCoordinate(0).dimension();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public int numCoordinates() {
		if (getNumGeometries() != 1)
			return 0;
		return getCoordinates().numCoordinates();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Coordinate getCoordinate(final int index) {
		if (getNumGeometries() != 1)
			return null;
		return getCoordinates().getCoordinate(index);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void setCoordinate(final int index, final Coordinate coord) {
		if (getNumGeometries() != 1)
			throw new UnsupportedOperationException("This operation is only available on single geometries");
		getCoordinates().setCoordinate(index, coord);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void removeCoordinate(final int index) {
		if (getNumGeometries() != 1)
			throw new UnsupportedOperationException("This operation is only available on single geometries");
		getCoordinates().removeCoordinate(index);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public int getNumGeometries() {
		return 1;//to be overwritten by MultiGeometry
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Geometry getGeometry(final int idx) {
		if (idx == 0)
			return this; 
		return null; //to be overwritten by MultiGeometry
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Envelope getEnvelope() {
		return getCoordinates().getEnvelope(); //to be overwritten by MultiPolygon
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean intersects(final Geometry geom) {
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.intersects(this, geom);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean isConvex() {
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.isConvex(this);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void applyCRS(final CoordinateReferenceSystem crs) {
		for (int i = 0; i < numCoordinates(); i++){
			Coordinate c = getCoordinate(i);
			c.set(c.get(crs));
		}
		getCoordinates().setCrs(crs);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void recursiveSetCRS(final CoordinateReferenceSystem crs) {
		getCoordinates().setCrs(crs);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public CoordinateReferenceSystem getCRS() {
		return getCoordinates().getCrs();
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "GeometryImpl{" +
		"}";
	}
}
