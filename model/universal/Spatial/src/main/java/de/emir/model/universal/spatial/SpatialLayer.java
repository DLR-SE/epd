package de.emir.model.universal.spatial;

import de.emir.model.universal.spatial.EFeature;
import de.emir.model.universal.spatial.Geometry;
import java.util.List;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "SpatialLayer")	
public interface SpatialLayer extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "features", associationType = AssociationType.COMPOSITE)
	public List<EFeature> getFeatures();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "name", associationType = AssociationType.PROPERTY)
	public void setName(String _name);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "name", associationType = AssociationType.PROPERTY)
	public String getName();
	/**
	
	 * returns all features that intersect with the given query geometry (geom). 
	 * if exactQuery is set to false, only the corresponding envelopes will be checked, otherwise a full intersection test is performed (slower)
	 * @generated 
	 */
	List<EFeature> queryFeatures(final Geometry geom, final boolean exactQuery);
	
}
