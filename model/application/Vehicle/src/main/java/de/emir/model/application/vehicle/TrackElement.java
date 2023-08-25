package de.emir.model.application.vehicle;

import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 * One track point
 * A track usually is a set of positions (with additional information) that have been seen in the past
 * @generated 
 */
@UMLClass	
public interface TrackElement extends UObject 
{
	/**
	 Time of this measurement 
	 * @generated 
	 */
	@UMLProperty(name = "time", associationType = AssociationType.COMPOSITE)
	public void setTime(Time _time);
	/**
	 Time of this measurement 
	 * @generated 
	 */
	@UMLProperty(name = "time", associationType = AssociationType.COMPOSITE)
	public Time getTime();
	/**
	 Coordiante of the object at the given time 
	 * @generated 
	 */
	@UMLProperty(name = "coordinate", associationType = AssociationType.COMPOSITE)
	public void setCoordinate(Coordinate _coordinate);
	/**
	 Coordiante of the object at the given time 
	 * @generated 
	 */
	@UMLProperty(name = "coordinate", associationType = AssociationType.COMPOSITE)
	public Coordinate getCoordinate();
	/**
	 orientation of the object at the given time 
	 * @generated 
	 */
	@UMLProperty(name = "heading", associationType = AssociationType.COMPOSITE)
	public void setHeading(Angle _heading);
	/**
	 orientation of the object at the given time 
	 * @generated 
	 */
	@UMLProperty(name = "heading", associationType = AssociationType.COMPOSITE)
	public Angle getHeading();
	/**
	 course / direction of movement, that may differ from the orientation, of the object at the given time 
	 * @generated 
	 */
	@UMLProperty(name = "course", associationType = AssociationType.COMPOSITE)
	public void setCourse(Angle _course);
	/**
	 course / direction of movement, that may differ from the orientation, of the object at the given time 
	 * @generated 
	 */
	@UMLProperty(name = "course", associationType = AssociationType.COMPOSITE)
	public Angle getCourse();
	/**
	 magnitude of movement in direction of course 
	 * @generated 
	 */
	@UMLProperty(name = "speed", associationType = AssociationType.COMPOSITE)
	public void setSpeed(Speed _speed);
	/**
	 magnitude of movement in direction of course 
	 * @generated 
	 */
	@UMLProperty(name = "speed", associationType = AssociationType.COMPOSITE)
	public Speed getSpeed();
	/**
	 an optional reference to the receiver / generator of this track element
	 * @generated 
	 */
	@UMLProperty(name = "source", associationType = AssociationType.SHARED)
	public void setSource(Object _source);
	/**
	 an optional reference to the receiver / generator of this track element
	 * @generated 
	 */
	@UMLProperty(name = "source", associationType = AssociationType.SHARED)
	public Object getSource();
	
}
