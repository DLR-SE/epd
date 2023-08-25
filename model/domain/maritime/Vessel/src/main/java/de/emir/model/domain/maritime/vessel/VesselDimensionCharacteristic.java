package de.emir.model.domain.maritime.vessel;

import de.emir.model.domain.maritime.vessel.Displacement;
import de.emir.model.domain.maritime.vessel.WatercraftHull;
import de.emir.model.universal.physics.MultiViewObjectSurfaceInforamtion;
import de.emir.model.universal.units.Length;
import de.emir.tuml.ucore.runtime.annotations.UMLClass;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty;
import de.emir.tuml.ucore.runtime.annotations.UMLProperty.AssociationType;

/**
 *	@generated 
 */
@UMLClass(name = "VesselDimensionCharacteristic", parent = MultiViewObjectSurfaceInforamtion.class)	
public interface VesselDimensionCharacteristic extends MultiViewObjectSurfaceInforamtion 
{
	/**
	
	 * A measurement of the weight of the vessel, usually used for warships. (Merchant ships are usually measured based on the volume of cargo space; see tonnage).
	 * @generated 
	 */
	@UMLProperty(name = "displacement", associationType = AssociationType.COMPOSITE)
	public void setDisplacement(Displacement _displacement);
	/**
	
	 * A measurement of the weight of the vessel, usually used for warships. (Merchant ships are usually measured based on the volume of cargo space; see tonnage).
	 * @generated 
	 */
	@UMLProperty(name = "displacement", associationType = AssociationType.COMPOSITE)
	public Displacement getDisplacement();
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "hull", associationType = AssociationType.COMPOSITE)
	public void setHull(WatercraftHull _hull);
	/**
	 *	@generated 
	 */
	@UMLProperty(name = "hull", associationType = AssociationType.COMPOSITE)
	public WatercraftHull getHull();
	/**
	
	 * Net tonnage (often abbreviated as NT, N.T. or nt) is a dimensionless index calculated from the total moulded volume of the ship's cargo spaces by using a mathematical formula. 
	 * Defined in The International Convention on Tonnage Measurement of Ships that was adopted by the International Maritime Organization in 1969, the net tonnage replaced the earlier net register tonnage (NRT) which denoted the volume of the ship's revenue-earning spaces in "register tons", units of volume equal to 100 cubic feet (2.83 m3).
	 * Net tonnage is used to calculate the port duties and should not be taken as less than 30 per cent of the ship's gross tonnage.
	 * \source Wikipedia 
	 * @generated 
	 */
	@UMLProperty(name = "neadTonnage", associationType = AssociationType.PROPERTY)
	public void setNeadTonnage(double _neadTonnage);
	/**
	
	 * Net tonnage (often abbreviated as NT, N.T. or nt) is a dimensionless index calculated from the total moulded volume of the ship's cargo spaces by using a mathematical formula. 
	 * Defined in The International Convention on Tonnage Measurement of Ships that was adopted by the International Maritime Organization in 1969, the net tonnage replaced the earlier net register tonnage (NRT) which denoted the volume of the ship's revenue-earning spaces in "register tons", units of volume equal to 100 cubic feet (2.83 m3).
	 * Net tonnage is used to calculate the port duties and should not be taken as less than 30 per cent of the ship's gross tonnage.
	 * \source Wikipedia 
	 * @generated 
	 */
	@UMLProperty(name = "neadTonnage", associationType = AssociationType.PROPERTY)
	public double getNeadTonnage();
	/**
	
	 *  Block coefficient (Cb) is the volume (V) divided by the LWL x BWL x T. If you draw a box around the submerged part of the ship, it is the ratio of the box volume occupied by the ship. It gives a sense of how much of the block defined by the LWL, beam (B) & draft (T) is filled by the hull. Full forms such as oil tankers will have a high Cb where fine shapes such as sailboats will have a low Cb.
	 * \source Wikipedia / Beschnidt2010
	 * @generated 
	 */
	@UMLProperty(name = "blockCoefficient", associationType = AssociationType.PROPERTY)
	public void setBlockCoefficient(double _blockCoefficient);
	/**
	
	 *  Block coefficient (Cb) is the volume (V) divided by the LWL x BWL x T. If you draw a box around the submerged part of the ship, it is the ratio of the box volume occupied by the ship. It gives a sense of how much of the block defined by the LWL, beam (B) & draft (T) is filled by the hull. Full forms such as oil tankers will have a high Cb where fine shapes such as sailboats will have a low Cb.
	 * \source Wikipedia / Beschnidt2010
	 * @generated 
	 */
	@UMLProperty(name = "blockCoefficient", associationType = AssociationType.PROPERTY)
	public double getBlockCoefficient();
	//////////////////////////////////////////////////////////////////
	//						Owned Operations						//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	Length getOverallLength();
	
	/**
	 *	@generated 
	 */
	Length getOverallBeam();
	
}
