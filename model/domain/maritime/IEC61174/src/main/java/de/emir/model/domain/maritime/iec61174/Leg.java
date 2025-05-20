package de.emir.model.domain.maritime.iec61174;

import de.emir.model.domain.maritime.iec61174.LegGeometryType;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Speed;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass	
public interface Leg extends UObject 
{

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "geometryType", associationType = AssociationType.PROPERTY)
	public void setGeometryType(LegGeometryType _geometryType);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "starboardXTD", associationType = AssociationType.PROPERTY)
	public Distance getStarboardXTD();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "portsideXTD", associationType = AssociationType.PROPERTY)
	public void setPortsideXTD(Distance _portsideXTD);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "portsideXTD", associationType = AssociationType.PROPERTY)
	public Distance getPortsideXTD();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "safetyContour", associationType = AssociationType.PROPERTY)
	public void setSafetyContour(Distance _safetyContour);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "legInfo", associationType = AssociationType.PROPERTY)
	public String getLegInfo();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "draughtForward", associationType = AssociationType.PROPERTY)
	public Distance getDraughtForward();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "safetyDepth", associationType = AssociationType.PROPERTY)
	public void setSafetyDepth(Distance _safetyDepth);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "safetyContour", associationType = AssociationType.PROPERTY)
	public Distance getSafetyContour();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "starboardXTD", associationType = AssociationType.PROPERTY)
	public void setStarboardXTD(Distance _starboardXTD);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "planSpeedMin", associationType = AssociationType.PROPERTY)
	public Speed getPlanSpeedMin();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "geometryType", associationType = AssociationType.PROPERTY)
	public LegGeometryType getGeometryType();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "safetyDepth", associationType = AssociationType.PROPERTY)
	public Distance getSafetyDepth();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "planSpeedMin", associationType = AssociationType.PROPERTY)
	public void setPlanSpeedMin(Speed _planSpeedMin);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "planSpeedMax", associationType = AssociationType.PROPERTY)
	public void setPlanSpeedMax(Speed _planSpeedMax);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "draughtAft", associationType = AssociationType.PROPERTY)
	public Distance getDraughtAft();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "legInfo", associationType = AssociationType.PROPERTY)
	public void setLegInfo(String _legInfo);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "legNote1", associationType = AssociationType.PROPERTY)
	public void setLegNote1(String _legNote1);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "legNote1", associationType = AssociationType.PROPERTY)
	public String getLegNote1();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "legNote2", associationType = AssociationType.PROPERTY)
	public void setLegNote2(String _legNote2);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "legNote2", associationType = AssociationType.PROPERTY)
	public String getLegNote2();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "planSpeedMax", associationType = AssociationType.PROPERTY)
	public Speed getPlanSpeedMax();

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "draughtForward", associationType = AssociationType.PROPERTY)
	public void setDraughtForward(Distance _draughtForward);

	/**
	 *	@generated 
	 */
	@UMLProperty(name = "draughtAft", associationType = AssociationType.PROPERTY)
	public void setDraughtAft(Distance _draughtAft);

    /**
     * @generated_not
     * @return The cross track distance on the starboard side or distance 0 meter if XTD is not set.
     */
    public Distance getStarboardXTDNotNull();
    
    /**
     * @generated_not
     * @return The cross track distance on the port side or distance 0 meter if XTD is not set.
     */
    public Distance getPortsideXTDNotNull();
	
}
