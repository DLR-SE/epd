package de.emir.service.geometry.impl;

import de.emir.service.geometry.GeometryPackage;
import de.emir.service.geometry.ISpatialIndexVisitor;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = ISpatialIndexVisitor.class)
abstract public class SpatialIndexVisitor extends UObjectImpl implements ISpatialIndexVisitor  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public SpatialIndexVisitor(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public SpatialIndexVisitor(final ISpatialIndexVisitor _copy) {
	}
	
	
	/**
	 * @generated
	 */
	@Override
	public UClass getUClassifier() {
		return GeometryPackage.Literals.SpatialIndexVisitor;
	}
	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */
	@Override
	public boolean visit(final Object obj)
	{
		//TODO: 
		//  returns true, if the next item should be visited 
		throw new UnsupportedOperationException("visit not yet implemented");
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "SpatialIndexVisitor{" +
		"}";
	}
}
