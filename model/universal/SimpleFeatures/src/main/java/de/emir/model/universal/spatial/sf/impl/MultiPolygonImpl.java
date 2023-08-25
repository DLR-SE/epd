package de.emir.model.universal.spatial.sf.impl;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.CoordinateSequenceImpl;
import de.emir.model.universal.spatial.sf.MultiPolygon;
import de.emir.model.universal.spatial.sf.Polygon;
import de.emir.model.universal.spatial.sf.SfPackage;
import de.emir.model.universal.spatial.sf.delegate.IMultiPolygonDelegationInterface;
import de.emir.model.universal.spatial.sf.impl.MultiGeometryImpl;
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
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
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
	 * @generated
	 */
	public CoordinateSequence getCoordinates()
	{
		/*IMultiPolygonDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: MultiPolygon");
		return delegate.getCoordinates(this);
        */
        CoordinateSequence result = new CoordinateSequenceImpl();
        if (getPolygons() == null) return null;
        for (Polygon p : getPolygons()) {
            for (int i = 0; i < p.getCoordinates().numCoordinates(); i++) {
                Coordinate c = p.getCoordinate(i);
                result.addCoordinate(c);
            }
        }
        return result;
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
	public int getNumGeometries() {
		return mPolygons.size();
	}

	@Override
	public Geometry getGeometry(int idx) {
		return mPolygons.get(idx);
	}
}
