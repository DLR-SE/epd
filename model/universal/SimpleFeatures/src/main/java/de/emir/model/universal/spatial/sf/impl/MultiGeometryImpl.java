package de.emir.model.universal.spatial.sf.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.GeometryImpl;
import de.emir.model.universal.spatial.sf.MultiGeometry;
import de.emir.model.universal.spatial.sf.SfPackage;
import de.emir.model.universal.spatial.sf.delegate.IMultiGeometryDelegationInterface;


/**
 *	@generated 
 */
@UMLImplementation(classifier = MultiGeometry.class)
abstract public class MultiGeometryImpl extends GeometryImpl implements MultiGeometry  
{
	
	
			
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
	}
	
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SfPackage.Literals.MultiGeometry;
	}
	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public CoordinateSequence getCoordinates()
	{
		throw new UnsupportedOperationException("Select a single geometry to get the coordinates");
	}

	
	public abstract int getNumGeometries();
	public abstract Geometry getGeometry(final int idx);
	
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
}
