package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.VesselCharacteristic;
import de.emir.model.universal.units.Volume;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "CapacityCharacteristic", parent = VesselCharacteristic.class)	
public interface CapacityCharacteristic extends VesselCharacteristic 
{
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "liquids", associationType = AssociationType.COMPOSITE)
	public void setLiquids(Volume _liquids);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "liquids", associationType = AssociationType.COMPOSITE)
	public Volume getLiquids();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "ligquidGas", associationType = AssociationType.COMPOSITE)
	public void setLigquidGas(Volume _ligquidGas);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "ligquidGas", associationType = AssociationType.COMPOSITE)
	public Volume getLigquidGas();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "oil", associationType = AssociationType.COMPOSITE)
	public void setOil(Volume _oil);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "oil", associationType = AssociationType.COMPOSITE)
	public Volume getOil();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "teu", associationType = AssociationType.PROPERTY)
	public void setTeu(int _teu);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "teu", associationType = AssociationType.PROPERTY)
	public int getTeu();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "passengers", associationType = AssociationType.PROPERTY)
	public void setPassengers(int _passengers);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "passengers", associationType = AssociationType.PROPERTY)
	public int getPassengers();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "cars", associationType = AssociationType.PROPERTY)
	public void setCars(int _cars);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "cars", associationType = AssociationType.PROPERTY)
	public int getCars();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "trucks", associationType = AssociationType.PROPERTY)
	public void setTrucks(int _trucks);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "trucks", associationType = AssociationType.PROPERTY)
	public int getTrucks();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "roRoLanes", associationType = AssociationType.PROPERTY)
	public void setRoRoLanes(int _roRoLanes);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "roRoLanes", associationType = AssociationType.PROPERTY)
	public int getRoRoLanes();

	
}
