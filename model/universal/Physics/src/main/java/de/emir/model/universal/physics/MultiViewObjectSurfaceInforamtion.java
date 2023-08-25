package de.emir.model.universal.physics;

import de.emir.model.universal.physics.ObjectSurfaceInformation;
import de.emir.model.universal.spatial.Geometry;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = ObjectSurfaceInformation.class)	
public interface MultiViewObjectSurfaceInforamtion extends ObjectSurfaceInformation 
{
	/**
	 
	 * Geometry as seen from a 90 degree angle along the main axis of the vessel
	 * 
	 * @generated 
	 */
	@UMLProperty(name = "sideGeometry", associationType = AssociationType.PROPERTY)
	public void setSideGeometry(Geometry _sideGeometry);
	/**
	 
	 * Geometry as seen from a 90 degree angle along the main axis of the vessel
	 * 
	 * @generated 
	 */
	@UMLProperty(name = "sideGeometry", associationType = AssociationType.PROPERTY)
	public Geometry getSideGeometry();
	/**
	
	 * Geometry as seen if the vessel moves directly towards the own position
	 * @generated 
	 */
	@UMLProperty(name = "frontGeometry", associationType = AssociationType.PROPERTY)
	public void setFrontGeometry(Geometry _frontGeometry);
	/**
	
	 * Geometry as seen if the vessel moves directly towards the own position
	 * @generated 
	 */
	@UMLProperty(name = "frontGeometry", associationType = AssociationType.PROPERTY)
	public Geometry getFrontGeometry();

	
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 returns the ObjectSurfaceInformation.geometry that is regarded as the geometry as viewed from top 
	 * @generated 
	 */
	Geometry getTopGeometry();
	
}
