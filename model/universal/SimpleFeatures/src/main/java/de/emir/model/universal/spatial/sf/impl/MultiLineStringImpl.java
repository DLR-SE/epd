package de.emir.model.universal.spatial.sf.impl;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.sf.LineString;
import de.emir.model.universal.spatial.sf.MultiLineString;
import de.emir.model.universal.spatial.sf.SfPackage;
import de.emir.model.universal.spatial.sf.delegate.IMultiLineStringDelegationInterface;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = MultiLineString.class)
public class MultiLineStringImpl extends MultiGeometryImpl implements MultiLineString  
{
	
	
	/**
	 *	@generated 
	 */
	private List<LineString> mLines = null;

	/**
	 *	Default constructor
	 *	@generated
	 */
	public MultiLineStringImpl(){
		super();
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public MultiLineStringImpl(final MultiLineString _copy) {
		super(_copy);
		mLines = _copy.getLines();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public MultiLineStringImpl(List<LineString> _lines) {
		super();
		mLines = _lines; 
	}

	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SfPackage.Literals.MultiLineString;
	}

	/**
	 *	@generated 
	 */
	public List<LineString> getLines() {
		if (mLines == null) {
			mLines = new UContainmentList<LineString>(this, SfPackage.theInstance.getMultiLineString_lines()); 
		}
		return mLines;
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
	 * @generated
	 */
	public CoordinateSequence getCoordinates()  {
		IMultiLineStringDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: MultiLineString");
		return delegate.getCoordinates(this);
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "MultiLineStringImpl{" +
		"}";
	}

    @Override
    public List<Geometry> getGeometries() {
        // down case from lines to geometries -> https://stackoverflow.com/a/933600
        return (List<Geometry>) (List<?>) getLines();
    }

    @Override
	public int getNumGeometries() {
		return getLines().size();
	}

	@Override
	public Geometry getGeometry(int idx) {
		return getLines().get(idx);
	}
}
