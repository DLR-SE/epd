package de.emir.model.universal.spatial.sf.impl;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.sf.MultiPolygon;
import de.emir.model.universal.spatial.sf.Polygon;
import de.emir.model.universal.spatial.sf.SfPackage;
import de.emir.model.universal.spatial.sf.delegate.IMultiPolygonDelegationInterface;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = MultiPolygon.class)
public class MultiPolygonImpl extends MultiGeometryImpl implements MultiPolygon  
{
	
	
	/**
	 *	@generated 
	 */
	private List<Polygon> mPolygons = null;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public MultiPolygonImpl(){
		super();
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public MultiPolygonImpl(final MultiPolygon _copy) {
		super(_copy);
		mPolygons = _copy.getPolygons();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public MultiPolygonImpl(List<Polygon> _polygons) {
		super();
		mPolygons = _polygons; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SfPackage.Literals.MultiPolygon;
	}

	/**
	 *	@generated 
	 */
	public List<Polygon> getPolygons() {
		if (mPolygons == null) {
			mPolygons = new UContainmentList<Polygon>(this, SfPackage.theInstance.getMultiPolygon_polygons()); 
		}
		return mPolygons;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////

    /**
     * @inheritDoc
     * @generated not
     */
    public CoordinateReferenceSystem getCRS() {
        // there is no CRS for all geometries in this collection, thus we simply return null
        return null;
    }

	/**
	 * @inheritDoc
	 * @generated not
	 */
    @Override
	public CoordinateSequence getCoordinates() {
		IMultiPolygonDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: MultiPolygon");
		return delegate.getCoordinates(this);
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "MultiPolygonImpl{" +
		"}";
	}

    @Override
    public List<Geometry> getGeometries() {
        // down case from polygons to geometries -> https://stackoverflow.com/a/933600
        return (List<Geometry>) (List<?>) getPolygons();
    }

    @Override
	public int getNumGeometries() {
		return getPolygons().size();
	}

	@Override
	public Geometry getGeometry(int idx) {
		return getPolygons().get(idx);
	}
}
