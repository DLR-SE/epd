package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.AngularDistance;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import java.util.List;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

    * Represents a measured distance from a vessel to detected objects surrounding the vessel.
    * The ReferencedDistance stores the distance from the point of reference (i.e. the position of
    * the detection sensor in reference to the vessels coordinate system) to a
    * specific range which is defined by the minimum opening angle and maximum opening angle
    * which is measured based on the ships relative northern position.
 * @generated 
 */
@UMLClass	
public interface ReferencedDistance extends UObject 
{
	/**
	 The reference point of measurement. This is the position of the detection sensor in reference to the vessels coordinate system. 
	 * @generated 
	 */
	@UMLProperty(name = "reference", associationType = AssociationType.COMPOSITE)
	public void setReference(PhysicalObject _reference);
	/**
	 The reference point of measurement. This is the position of the detection sensor in reference to the vessels coordinate system. 
	 * @generated 
	 */
	@UMLProperty(name = "reference", associationType = AssociationType.COMPOSITE)
	public PhysicalObject getReference();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "angularDistances", associationType = AssociationType.COMPOSITE)
	public List<AngularDistance> getAngularDistances();
	/**
	 Identifier of the sensor which detected the distance. 
	 * @generated 
	 */
	@UMLProperty(name = "source", associationType = AssociationType.COMPOSITE)
	public void setSource(String _source);
	/**
	 Identifier of the sensor which detected the distance. 
	 * @generated 
	 */
	@UMLProperty(name = "source", associationType = AssociationType.COMPOSITE)
	public String getSource();
	
}
