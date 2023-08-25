package de.emir.model.application.vehicle.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.application.vehicle.Track;
import de.emir.model.application.vehicle.TrackCharacteristic;
import de.emir.model.application.vehicle.VehiclePackage;
import de.emir.model.universal.physics.impl.CharacteristicImpl;
import de.emir.tuml.ucore.runtime.lists.ListUtils;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = TrackCharacteristic.class)
public class TrackCharacteristicImpl extends CharacteristicImpl implements TrackCharacteristic  
{
	
	
	/**
	 *	@generated 
	 */
	private List<Track> mTracks = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public TrackCharacteristicImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public TrackCharacteristicImpl(final TrackCharacteristic _copy) {
		super(_copy);
		mTracks = _copy.getTracks();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public TrackCharacteristicImpl(List<Track> _tracks) {
		super();
		mTracks = _tracks; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VehiclePackage.Literals.TrackCharacteristic;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public List<Track> getTracks() {
		if (mTracks == null) {
			mTracks = ListUtils.<Track>asList(this, VehiclePackage.theInstance.getTrackCharacteristic_tracks()); 
		}
		return mTracks;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TrackCharacteristicImpl{" +
		"}";
	}
}
