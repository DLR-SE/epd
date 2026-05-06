package de.emir.model.domain.maritime.vessel;

import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

    * Represents a measured distance to detected objects surrounding the vessel.
                            * The AngularDistance is a wrapper for modeling multiple opening degrees for one sensor where
                            * each opening degree is a specific range which is defined by the minimum opening angle and maximum opening angle
                            * which is measured based on the ships relative northern position. It should be used as
                            * part of the ReferencedDistance which specifies the origin point of the distance and the source identifier.
 * @generated 
 */
@UMLClass	
public interface AngularDistance extends UObject 
{
	/**
	 The minimum opening degree of the sensor which detected the distance. This is in reference to the vessels heading with 0 being north. 
	 * @generated 
	 */
	@UMLProperty(name = "minAngle", associationType = AssociationType.COMPOSITE)
	public void setMinAngle(Angle _minAngle);
	/**
	 The minimum opening degree of the sensor which detected the distance. This is in reference to the vessels heading with 0 being north. 
	 * @generated 
	 */
	@UMLProperty(name = "minAngle", associationType = AssociationType.COMPOSITE)
	public Angle getMinAngle();
	/**
	 The maximum opening degree of the sensor which detected the distance. This is in reference to the vessels heading with 0 being north. 
	 * @generated 
	 */
	@UMLProperty(name = "maxAngle", associationType = AssociationType.COMPOSITE)
	public void setMaxAngle(Angle _maxAngle);
	/**
	 The maximum opening degree of the sensor which detected the distance. This is in reference to the vessels heading with 0 being north. 
	 * @generated 
	 */
	@UMLProperty(name = "maxAngle", associationType = AssociationType.COMPOSITE)
	public Angle getMaxAngle();
	/**
	 The distance detected by the sensor. It is constrained by the minAngle and maxAngle which specify the FOV. 
	 * @generated 
	 */
	@UMLProperty(name = "distance", associationType = AssociationType.COMPOSITE)
	public void setDistance(Distance _distance);
	/**
	 The distance detected by the sensor. It is constrained by the minAngle and maxAngle which specify the FOV. 
	 * @generated 
	 */
	@UMLProperty(name = "distance", associationType = AssociationType.COMPOSITE)
	public Distance getDistance();
	/**
	 Detection timestamp. 
	 * @generated 
	 */
	@UMLProperty(name = "timestamp", associationType = AssociationType.COMPOSITE)
	public void setTimestamp(Time _timestamp);
	/**
	 Detection timestamp. 
	 * @generated 
	 */
	@UMLProperty(name = "timestamp", associationType = AssociationType.COMPOSITE)
	public Time getTimestamp();
	
}
