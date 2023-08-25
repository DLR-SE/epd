package de.emir.model.domain.maritime.iec61174;

import de.emir.model.domain.maritime.iec61174.IECElementWithExtension;
import de.emir.model.domain.maritime.iec61174.Leg;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(parent = IECElementWithExtension.class)	
public interface Waypoint extends IECElementWithExtension 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "position", associationType = AssociationType.COMPOSITE)
	public void setPosition(Coordinate _position);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "position", associationType = AssociationType.COMPOSITE)
	public Coordinate getPosition();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "leg", associationType = AssociationType.COMPOSITE)
	public void setLeg(Leg _leg);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "leg", associationType = AssociationType.COMPOSITE)
	public Leg getLeg();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "id", associationType = AssociationType.PROPERTY)
	public void setId(int _id);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "id", associationType = AssociationType.PROPERTY)
	public int getId();
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
	 *	@generated 
	 */
	@UMLProperty(name = "radius", associationType = AssociationType.PROPERTY)
	public void setRadius(double _radius);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "radius", associationType = AssociationType.PROPERTY)
	public double getRadius();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "revision", associationType = AssociationType.PROPERTY)
	public void setRevision(int _revision);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "revision", associationType = AssociationType.PROPERTY)
	public int getRevision();

	
}
