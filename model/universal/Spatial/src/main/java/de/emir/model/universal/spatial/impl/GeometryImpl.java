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
	 * This calls the delegate getDimension method. If used on geometries this will not result in the dimensions of the
	 * coordinates within the geometry. Use 
	 * de.emir.model.universal.spatial.sf.ops.GeometryOperationUtil.getMaxDimension(Geometry) for this purpose or work
	 * with getGeometry(a).getCoordinate(b).dimension() directly.
	 *
	 * @inheritDoc
	 * @generated not
	 */
	public int getDimension() {
		if (getNumGeometries() < 1) {
			// No geometry -> no dimension.
			return 0;
		}
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null) {
			throw new NullPointerException("Operationsdelegate has not been initialized for: " + this);
		}
		return delegate.getDimension(this);
	}

	/**
	 * Get the number of coordinates in this geometry. This does not work on multi-geometries and will throw an
	 * UnsupportedOperationException. Use getGeometry(a).numCoordinates() instead, or use
	 * de.emir.model.universal.spatial.sf.ops.GeometryOperationUtil.countCoordinates(Geometry).
	 * 
	 * @inheritDoc
	 * @generated not
	 */
	public int numCoordinates() {
		if (getNumGeometries() < 1) {
			// No geometry -> no coordinates.
			return 0;
		} else if (getNumGeometries() > 1) {
			throw new UnsupportedOperationException(
					"numCoordinates() is not available on multi-geometries, use getGeometry() first.");
		}
		// One geometry -> count coordinates.
		return getCoordinates().numCoordinates();
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public Coordinate getCoordinate(final int index) {
		if (getNumGeometries() != 1) {
			throw new UnsupportedOperationException(
					"getCoordinate() is not available on multi-geometries, use getGeometry() first.");
		}
		return getCoordinates().getCoordinate(index);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void setCoordinate(final int index, final Coordinate coord) {
		if (getNumGeometries() != 1) {
			throw new UnsupportedOperationException(
					"setCoordinate() is not available on multi-geometries, use getGeometry() first.");
		}
		getCoordinates().setCoordinate(index, coord);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void removeCoordinate(final int index) {
		if (getNumGeometries() != 1) {
			throw new UnsupportedOperationException(
					"removeCoordinate() is not available on multi-geometries, use getGeometry() first.");
		}
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
	public boolean intersects(final Geometry geom)
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.intersects(this, geom);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean coveredBy(final Geometry g)
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.coveredBy(this, g);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean covers(final Geometry g)
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.covers(this, g);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean crosses(final Geometry g)
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.crosses(this, g);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean contains(final Geometry geom)
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.contains(this, geom);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public Geometry difference(final Geometry g)
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.difference(this, g);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public double distance(final Geometry g)
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.distance(this, g);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean equalsExact(final Geometry g)
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.equalsExact(this, g);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public double getArea()
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.getArea(this);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean disjoint(final Geometry g)
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.disjoint(this, g);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public double getLength()
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.getLength(this);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean within(final Geometry geom)
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.within(this, geom);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public Geometry normalized()
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.normalized(this);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public Geometry intersection(final Geometry geom)
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.intersection(this, geom);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public void normalize()
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		delegate.normalize(this);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public Geometry reversed()
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.reversed(this);
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
	 * @generated
	 */
	public boolean overlaps(final Geometry geom)
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.overlaps(this, geom);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public Geometry symDifference(final Geometry geom)
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.symDifference(this, geom);
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

	/**
	 * @inheritDoc
	 * @generated
	 */
	public Geometry union(final Geometry geom)
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.union(this, geom);
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public boolean touches(final Geometry geom)
	{
		IGeometryDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: Geometry");
		return delegate.touches(this, geom);
	}
}
