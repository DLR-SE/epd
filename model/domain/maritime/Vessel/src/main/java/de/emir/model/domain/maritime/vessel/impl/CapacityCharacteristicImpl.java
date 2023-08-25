package de.emir.model.domain.maritime.vessel.impl;

import de.emir.model.domain.maritime.vessel.CapacityCharacteristic;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.universal.units.Volume;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.model.domain.maritime.vessel.impl.VesselCharacteristicImpl;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = CapacityCharacteristic.class)
public class CapacityCharacteristicImpl extends VesselCharacteristicImpl implements CapacityCharacteristic  
{
	
	
	/**
	 *	@generated 
	 */
	private int mTeu = 0;
	/**
	 *	@generated 
	 */
	private int mPassengers = 0;
	/**
	 *	@generated 
	 */
	private int mCars = 0;
	/**
	 *	@generated 
	 */
	private int mTrucks = 0;
	/**
	 *	@generated 
	 */
	private int mRoRoLanes = 0;
	/**
	 *	@generated 
	 */
	private Volume mLiquids = null;
	/**
	 *	@generated 
	 */
	private Volume mLigquidGas = null;
	/**
	 *	@generated 
	 */
	private Volume mOil = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public CapacityCharacteristicImpl(){
		super();
		//set the default values and assign them to this instance 
		setLiquids(mLiquids);
		setLigquidGas(mLigquidGas);
		setOil(mOil);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public CapacityCharacteristicImpl(final CapacityCharacteristic _copy) {
		super(_copy);
		mTeu = _copy.getTeu();
		mPassengers = _copy.getPassengers();
		mCars = _copy.getCars();
		mTrucks = _copy.getTrucks();
		mRoRoLanes = _copy.getRoRoLanes();
		mLiquids = _copy.getLiquids();
		mLigquidGas = _copy.getLigquidGas();
		mOil = _copy.getOil();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public CapacityCharacteristicImpl(int _teu, int _passengers, int _cars, int _trucks, int _roRoLanes, Volume _liquids, Volume _ligquidGas, Volume _oil) {
		super();
		mTeu = _teu;
		mPassengers = _passengers;
		mCars = _cars;
		mTrucks = _trucks;
		mRoRoLanes = _roRoLanes;
		mLiquids = _liquids; 
		mLigquidGas = _ligquidGas; 
		mOil = _oil; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.CapacityCharacteristic;
	}

	/**
	 *	@generated 
	 */
	public void setTeu(int _teu) {
		if (needNotification(VesselPackage.Literals.CapacityCharacteristic_teu)){
			int _oldValue = mTeu;
			mTeu = _teu;
			notify(_oldValue, mTeu, VesselPackage.Literals.CapacityCharacteristic_teu, NotificationType.SET);
		}else{
			mTeu = _teu;
		}
	}

	/**
	 *	@generated 
	 */
	public int getTeu() {
		return mTeu;
	}

	/**
	 *	@generated 
	 */
	public void setPassengers(int _passengers) {
		if (needNotification(VesselPackage.Literals.CapacityCharacteristic_passengers)){
			int _oldValue = mPassengers;
			mPassengers = _passengers;
			notify(_oldValue, mPassengers, VesselPackage.Literals.CapacityCharacteristic_passengers, NotificationType.SET);
		}else{
			mPassengers = _passengers;
		}
	}

	/**
	 *	@generated 
	 */
	public int getPassengers() {
		return mPassengers;
	}

	/**
	 *	@generated 
	 */
	public void setCars(int _cars) {
		if (needNotification(VesselPackage.Literals.CapacityCharacteristic_cars)){
			int _oldValue = mCars;
			mCars = _cars;
			notify(_oldValue, mCars, VesselPackage.Literals.CapacityCharacteristic_cars, NotificationType.SET);
		}else{
			mCars = _cars;
		}
	}

	/**
	 *	@generated 
	 */
	public int getCars() {
		return mCars;
	}

	/**
	 *	@generated 
	 */
	public void setTrucks(int _trucks) {
		if (needNotification(VesselPackage.Literals.CapacityCharacteristic_trucks)){
			int _oldValue = mTrucks;
			mTrucks = _trucks;
			notify(_oldValue, mTrucks, VesselPackage.Literals.CapacityCharacteristic_trucks, NotificationType.SET);
		}else{
			mTrucks = _trucks;
		}
	}

	/**
	 *	@generated 
	 */
	public int getTrucks() {
		return mTrucks;
	}

	/**
	 *	@generated 
	 */
	public void setRoRoLanes(int _roRoLanes) {
		if (needNotification(VesselPackage.Literals.CapacityCharacteristic_roRoLanes)){
			int _oldValue = mRoRoLanes;
			mRoRoLanes = _roRoLanes;
			notify(_oldValue, mRoRoLanes, VesselPackage.Literals.CapacityCharacteristic_roRoLanes, NotificationType.SET);
		}else{
			mRoRoLanes = _roRoLanes;
		}
	}

	/**
	 *	@generated 
	 */
	public int getRoRoLanes() {
		return mRoRoLanes;
	}

	/**
	 *	@generated 
	 */
	public void setLiquids(Volume _liquids) {
		Notification<Volume> notification = basicSet(mLiquids, _liquids, VesselPackage.Literals.CapacityCharacteristic_liquids);
		mLiquids = _liquids;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Volume getLiquids() {
		return mLiquids;
	}

	/**
	 *	@generated 
	 */
	public void setLigquidGas(Volume _ligquidGas) {
		Notification<Volume> notification = basicSet(mLigquidGas, _ligquidGas, VesselPackage.Literals.CapacityCharacteristic_ligquidGas);
		mLigquidGas = _ligquidGas;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Volume getLigquidGas() {
		return mLigquidGas;
	}

	/**
	 *	@generated 
	 */
	public void setOil(Volume _oil) {
		Notification<Volume> notification = basicSet(mOil, _oil, VesselPackage.Literals.CapacityCharacteristic_oil);
		mOil = _oil;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Volume getOil() {
		return mOil;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "CapacityCharacteristicImpl{" +
		" teu = " + getTeu() + 
		" passengers = " + getPassengers() + 
		" cars = " + getCars() + 
		" trucks = " + getTrucks() + 
		" roRoLanes = " + getRoRoLanes() + 
		"}";
	}
}
