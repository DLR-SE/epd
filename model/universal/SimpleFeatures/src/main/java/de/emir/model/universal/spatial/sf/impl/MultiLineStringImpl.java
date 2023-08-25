package de.emir.model.universal.spatial.sf.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.sf.LineString;
import de.emir.model.universal.spatial.sf.MultiLineString;
import de.emir.model.universal.spatial.sf.SfPackage;
import de.emir.model.universal.spatial.sf.delegate.IMultiLineStringDelegationInterface;
import de.emir.model.universal.spatial.sf.impl.MultiGeometryImpl;
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
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
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
	 * @generated
	 */
	public CoordinateSequence getCoordinates()
	{
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
	public int getNumGeometries() {
		return mLines.size();
	}

	@Override
	public Geometry getGeometry(int idx) {
		return mLines.get(idx);
	}
}
