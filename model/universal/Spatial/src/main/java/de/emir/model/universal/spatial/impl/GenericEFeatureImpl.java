package de.emir.model.universal.spatial.impl;

import de.emir.model.universal.spatial.GenericEFeature;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.model.universal.spatial.impl.EFeatureImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = GenericEFeature.class)
public class GenericEFeatureImpl extends EFeatureImpl implements GenericEFeature  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public GenericEFeatureImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public GenericEFeatureImpl(final GenericEFeature _copy) {
		super(_copy);
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public GenericEFeatureImpl(String _featureType, Geometry _geometry) {
		super(_featureType,_geometry);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SpatialPackage.Literals.GenericEFeature;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "GenericEFeatureImpl{" +
		" featureType = " + getFeatureType() + 
		"}";
	}
}
