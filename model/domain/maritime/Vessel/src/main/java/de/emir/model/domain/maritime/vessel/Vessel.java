package de.emir.model.domain.maritime.vessel;

import de.emir.model.application.vehicle.Watercraft;
import de.emir.model.domain.maritime.vessel.VesselType;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**

 *  a nautical term for all kinds of craft designed for transportation on water, such as ships or boats.
 * @generated 
 */
@UMLClass(name = "Vessel", parent = Watercraft.class)	
public interface Vessel extends Watercraft 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "mmsi", associationType = AssociationType.PROPERTY)
	public void setMmsi(long _mmsi);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "mmsi", associationType = AssociationType.PROPERTY)
	public long getMmsi();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "imo", associationType = AssociationType.PROPERTY)
	public void setImo(long _imo);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "imo", associationType = AssociationType.PROPERTY)
	public long getImo();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "callSign", associationType = AssociationType.PROPERTY)
	public void setCallSign(String _callSign);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "callSign", associationType = AssociationType.PROPERTY)
	public String getCallSign();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "type", associationType = AssociationType.PROPERTY)
	public void setType(VesselType _type);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "type", associationType = AssociationType.PROPERTY)
	public VesselType getType();
	
}
