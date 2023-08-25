package de.emir.service.geometry.impl;

import de.emir.service.geometry.GeometryPackage;
import de.emir.service.geometry.IQuadTree;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = IQuadTree.class)
public class QuadTree extends SpatialIndex implements IQuadTree  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public QuadTree(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public QuadTree(final IQuadTree _copy) {
		super(_copy);
	}
	
	
	/**
	 * @generated
	 */
	@Override
	public UClass getUClassifier() {
		return GeometryPackage.Literals.QuadTree;
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "QuadTree{" +
		"}";
	}
}
