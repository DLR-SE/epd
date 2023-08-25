package de.emir.model.universal.physics;

import de.emir.model.universal.physics.Characteristic;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.units.Length;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "ObjectSurfaceInformation", parent = Characteristic.class)	
public interface ObjectSurfaceInformation extends Characteristic 
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
	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 Returns the with of the Object. 
								 * The Width may be calculated from the defined geometry
	 * @generated 
	 */
	Length getWidth();
	
	/**
	 returns the length of the object
	 * The length of the object may be calculated from the geometry
	 * @generated 
	 */
	Length getLength();
	
	/**
	 returns the height of the object (bounds)
	 * The height of the object may be calculated from the geometry, if the geometry is defined in 3 dimensions, 
	 * otherwise null is returned
	 * @generated 
	 */
	Length getHeight();
	/**
	 returns the 2D boundingbox of this object
	 * @generated 
	 */
	Envelope getBoundingBox2D();
	/**
	 returns the 3D boundingbox of this object, if a third dimension could be detected, otherwise this is equals with getBoundingBox2D() 
	 * @generated 
	 */
	Envelope getBoundingBox3D();
	
}
