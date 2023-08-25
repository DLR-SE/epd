package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.Displacement;
import de.emir.model.domain.maritime.vessel.VesselDimensionCharacteristic;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.domain.maritime.vessel.WatercraftHull;
import de.emir.model.universal.physics.impl.MultiViewObjectSurfaceInforamtionImpl;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.units.Length;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = VesselDimensionCharacteristic.class)
public class VesselDimensionCharacteristicImpl extends MultiViewObjectSurfaceInforamtionImpl implements VesselDimensionCharacteristic  
{
	
	
	/**
	
	 * Net tonnage (often abbreviated as NT, N.T. or nt) is a dimensionless index calculated from the total moulded volume of the ship's cargo spaces by using a mathematical formula. 
	 * Defined in The International Convention on Tonnage Measurement of Ships that was adopted by the International Maritime Organization in 1969, the net tonnage replaced the earlier net register tonnage (NRT) which denoted the volume of the ship's revenue-earning spaces in "register tons", units of volume equal to 100 cubic feet (2.83 m3).
	 * Net tonnage is used to calculate the port duties and should not be taken as less than 30 per cent of the ship's gross tonnage.
	 * \source Wikipedia 
	 * @generated 
	 */
	private double mNeadTonnage = 0.0;
	/**
	
	 *  Block coefficient (Cb) is the volume (V) divided by the LWL x BWL x T. If you draw a box around the submerged part of the ship, it is the ratio of the box volume occupied by the ship. It gives a sense of how much of the block defined by the LWL, beam (B) & draft (T) is filled by the hull. Full forms such as oil tankers will have a high Cb where fine shapes such as sailboats will have a low Cb.
	 * \source Wikipedia / Beschnidt2010
	 * @generated 
	 */
	private double mBlockCoefficient = 0.0;
	/**
	
	 * A measurement of the weight of the vessel, usually used for warships. (Merchant ships are usually measured based on the volume of cargo space; see tonnage).
	 * @generated 
	 */
	private Displacement mDisplacement = null;
	/**
	 *	@generated 
	 */
	private WatercraftHull mHull = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public VesselDimensionCharacteristicImpl(){
		super();
		//set the default values and assign them to this instance 
		setDisplacement(mDisplacement);
		setHull(mHull);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public VesselDimensionCharacteristicImpl(final VesselDimensionCharacteristic _copy) {
		super(_copy);
		mNeadTonnage = _copy.getNeadTonnage();
		mBlockCoefficient = _copy.getBlockCoefficient();
		mDisplacement = _copy.getDisplacement();
		mHull = _copy.getHull();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public VesselDimensionCharacteristicImpl(Geometry _geometry, Geometry _sideGeometry, Geometry _frontGeometry, double _neadTonnage, double _blockCoefficient, Displacement _displacement, WatercraftHull _hull) {
		super(_geometry,_sideGeometry,_frontGeometry);
		mNeadTonnage = _neadTonnage;
		mBlockCoefficient = _blockCoefficient;
		mDisplacement = _displacement; 
		mHull = _hull; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.VesselDimensionCharacteristic;
	}

	/**
	
	 * Net tonnage (often abbreviated as NT, N.T. or nt) is a dimensionless index calculated from the total moulded volume of the ship's cargo spaces by using a mathematical formula. 
	 * Defined in The International Convention on Tonnage Measurement of Ships that was adopted by the International Maritime Organization in 1969, the net tonnage replaced the earlier net register tonnage (NRT) which denoted the volume of the ship's revenue-earning spaces in "register tons", units of volume equal to 100 cubic feet (2.83 m3).
	 * Net tonnage is used to calculate the port duties and should not be taken as less than 30 per cent of the ship's gross tonnage.
	 * \source Wikipedia 
	 * @generated 
	 */
	public void setNeadTonnage(double _neadTonnage) {
		if (needNotification(VesselPackage.Literals.VesselDimensionCharacteristic_neadTonnage)){
			double _oldValue = mNeadTonnage;
			mNeadTonnage = _neadTonnage;
			notify(_oldValue, mNeadTonnage, VesselPackage.Literals.VesselDimensionCharacteristic_neadTonnage, NotificationType.SET);
		}else{
			mNeadTonnage = _neadTonnage;
		}
	}

	/**
	
	 * Net tonnage (often abbreviated as NT, N.T. or nt) is a dimensionless index calculated from the total moulded volume of the ship's cargo spaces by using a mathematical formula. 
	 * Defined in The International Convention on Tonnage Measurement of Ships that was adopted by the International Maritime Organization in 1969, the net tonnage replaced the earlier net register tonnage (NRT) which denoted the volume of the ship's revenue-earning spaces in "register tons", units of volume equal to 100 cubic feet (2.83 m3).
	 * Net tonnage is used to calculate the port duties and should not be taken as less than 30 per cent of the ship's gross tonnage.
	 * \source Wikipedia 
	 * @generated 
	 */
	public double getNeadTonnage() {
		return mNeadTonnage;
	}

	/**
	
	 *  Block coefficient (Cb) is the volume (V) divided by the LWL x BWL x T. If you draw a box around the submerged part of the ship, it is the ratio of the box volume occupied by the ship. It gives a sense of how much of the block defined by the LWL, beam (B) & draft (T) is filled by the hull. Full forms such as oil tankers will have a high Cb where fine shapes such as sailboats will have a low Cb.
	 * \source Wikipedia / Beschnidt2010
	 * @generated 
	 */
	public void setBlockCoefficient(double _blockCoefficient) {
		if (needNotification(VesselPackage.Literals.VesselDimensionCharacteristic_blockCoefficient)){
			double _oldValue = mBlockCoefficient;
			mBlockCoefficient = _blockCoefficient;
			notify(_oldValue, mBlockCoefficient, VesselPackage.Literals.VesselDimensionCharacteristic_blockCoefficient, NotificationType.SET);
		}else{
			mBlockCoefficient = _blockCoefficient;
		}
	}

	/**
	
	 *  Block coefficient (Cb) is the volume (V) divided by the LWL x BWL x T. If you draw a box around the submerged part of the ship, it is the ratio of the box volume occupied by the ship. It gives a sense of how much of the block defined by the LWL, beam (B) & draft (T) is filled by the hull. Full forms such as oil tankers will have a high Cb where fine shapes such as sailboats will have a low Cb.
	 * \source Wikipedia / Beschnidt2010
	 * @generated 
	 */
	public double getBlockCoefficient() {
		return mBlockCoefficient;
	}

	/**
	
	 * A measurement of the weight of the vessel, usually used for warships. (Merchant ships are usually measured based on the volume of cargo space; see tonnage).
	 * @generated 
	 */
	public void setDisplacement(Displacement _displacement) {
		Notification<Displacement> notification = basicSet(mDisplacement, _displacement, VesselPackage.Literals.VesselDimensionCharacteristic_displacement);
		mDisplacement = _displacement;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	
	 * A measurement of the weight of the vessel, usually used for warships. (Merchant ships are usually measured based on the volume of cargo space; see tonnage).
	 * @generated 
	 */
	public Displacement getDisplacement() {
		return mDisplacement;
	}

	/**
	 *	@generated 
	 */
	public void setHull(WatercraftHull _hull) {
		Notification<WatercraftHull> notification = basicSet(mHull, _hull, VesselPackage.Literals.VesselDimensionCharacteristic_hull);
		mHull = _hull;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public WatercraftHull getHull() {
		return mHull;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Length getOverallLength()
	{
		return getLength(); //TODO: is this correct?
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Length getOverallBeam()
	{
		return getWidth(); //TODO: is this correct?
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "VesselDimensionCharacteristicImpl{" +
		" neadTonnage = " + getNeadTonnage() + 
		" blockCoefficient = " + getBlockCoefficient() + 
		"}";
	}
	
	@Override
	public Length getWidth() {
		if (getHull() != null && getHull().getBeam() != null)
			return getHull().getBeam();
		return super.getWidth();
	}
	@Override
	public Length getLength() {
		if (getHull() != null && getHull().getOverAllLength() != null)
			return getHull().getOverAllLength();
		return super.getLength();
	}
}
