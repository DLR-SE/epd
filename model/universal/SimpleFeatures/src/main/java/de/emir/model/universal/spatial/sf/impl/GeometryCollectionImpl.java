package de.emir.model.universal.spatial.sf.impl;

import de.emir.model.universal.spatial.sf.delegate.IGeometryCollectionDelegationInterface;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.impl.GeometryImpl;
import de.emir.model.universal.spatial.sf.GeometryCollection;
import de.emir.model.universal.spatial.sf.SfPackage;


/**
 Base class for collections of geometries 
 * @generated 
 */
@UMLImplementation(classifier = GeometryCollection.class)
abstract public class GeometryCollectionImpl extends GeometryImpl implements GeometryCollection  {
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public GeometryCollectionImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public GeometryCollectionImpl(final GeometryCollection _copy) {
		super(_copy);
	}
	
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SfPackage.Literals.GeometryCollection;
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
		IGeometryCollectionDelegationInterface delegate = getDelegate();
		if (delegate == null)
			throw new NullPointerException("Operationsdelegate has not been initialized for: GeometryCollection");
		return delegate.getCoordinates(this);
	}


	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "GeometryCollectionImpl{" +
		"}";
	}
}
