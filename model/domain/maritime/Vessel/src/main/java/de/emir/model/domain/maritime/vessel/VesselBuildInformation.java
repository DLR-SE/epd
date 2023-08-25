package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.VesselCharacteristic;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "VesselBuildInformation", parent = VesselCharacteristic.class)	
public interface VesselBuildInformation extends VesselCharacteristic 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "manufactor", associationType = AssociationType.COMPOSITE)
	public void setManufactor(RSIdentifier _manufactor);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "manufactor", associationType = AssociationType.COMPOSITE)
	public RSIdentifier getManufactor();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "buildTime", associationType = AssociationType.COMPOSITE)
	public void setBuildTime(Time _buildTime);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "buildTime", associationType = AssociationType.COMPOSITE)
	public Time getBuildTime();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "hullNumber", associationType = AssociationType.PROPERTY)
	public void setHullNumber(String _hullNumber);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "hullNumber", associationType = AssociationType.PROPERTY)
	public String getHullNumber();

	
}
