package de.emir.model.universal.spatial.sf.impl;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.GeometryImpl;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;
import java.util.List;
import de.emir.model.universal.spatial.sf.MultiGeometry;
import de.emir.model.universal.spatial.sf.SfPackage;


/**
 *	@generated 
 */
@UMLImplementation(classifier = MultiGeometry.class)
public class MultiGeometryImpl extends GeometryImpl implements MultiGeometry {
    /**
     *	@generated
     */
    private List<Geometry> mGeometries = null;


    /**
	 *	Default constructor
	 *	@generated
	 */
	public MultiGeometryImpl(){
		super();
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public MultiGeometryImpl(final MultiGeometry _copy) {
		super(_copy);
		mGeometries = _copy.getGeometries();
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SfPackage.Literals.MultiGeometry;
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public MultiGeometryImpl(List<Geometry> _geometries) {
		super();
		mGeometries = _geometries; 
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
	public CoordinateSequence getCoordinates() {
		throw new UnsupportedOperationException("Select a single geometry to get the coordinates");
	}

	/**
	 *	@generated 
	 */
	public List<Geometry> getGeometries() {
		if (mGeometries == null) {
			mGeometries = new UContainmentList<Geometry>(this, SfPackage.theInstance.getMultiGeometry_geometries()); 
		}
		return mGeometries;
	}


	@Override
	public Envelope getEnvelope() {
		Envelope env = getGeometry(0).getEnvelope().copy();
		for (int i = 1; i < getNumGeometries(); i++) {
			env.expandLocal(getGeometry(i).getEnvelope());
		}
		return env;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "MultiGeometryImpl{" +
		"}";
	}

    @Override
    public int getNumGeometries() {
        return getGeometries().size();
    }

    @Override
    public Geometry getGeometry(int idx) {
        return getGeometries().get(idx);
    }
}
