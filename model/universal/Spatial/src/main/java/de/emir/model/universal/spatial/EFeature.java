package de.emir.model.universal.spatial;

import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "EFeature", isAbstract = true)	
public interface EFeature extends UObject 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "geometry", associationType = AssociationType.COMPOSITE)
	public void setGeometry(Geometry _geometry);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "geometry", associationType = AssociationType.COMPOSITE)
	public Geometry getGeometry();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "featureType", associationType = AssociationType.PROPERTY)
	public void setFeatureType(String _featureType);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "featureType", associationType = AssociationType.PROPERTY)
	public String getFeatureType();

	
}
