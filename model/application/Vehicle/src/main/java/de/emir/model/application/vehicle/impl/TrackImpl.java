package de.emir.model.application.vehicle.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.application.vehicle.Track;
import de.emir.model.application.vehicle.TrackElement;
import de.emir.model.application.vehicle.VehiclePackage;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.lists.ListUtils;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Track.class)
public class TrackImpl extends UObjectImpl implements Track  
{
	
	
	/**
	 ordered list of elements  
	 * @generated 
	 */
	private List<TrackElement> mElements = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public TrackImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public TrackImpl(final Track _copy) {
		mElements = _copy.getElements();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public TrackImpl(List<TrackElement> _elements) {
		mElements = _elements; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VehiclePackage.Literals.Track;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 ordered list of elements  
	 * @generated 
	 */
	public List<TrackElement> getElements() {
		if (mElements == null) {
			mElements = ListUtils.<TrackElement>asList(this, VehiclePackage.theInstance.getTrack_elements()); 
		}
		return mElements;
	}
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */
	public Time first()
	{
		//TODO: 
		//  returns the time of the first track point 
		throw new UnsupportedOperationException("first not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public Time last()
	{
		//TODO: 
		//  returns the time of the last track point 
		throw new UnsupportedOperationException("last not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public TrackElement getPositionAt(final Time p)
	{
		//TODO: 
		// 
		//  * returns a new track point for the given time. If there is no measurement available for this time point
		//  * the method will (linear) interpolate / may extrapolate the values
		//  * @return null, if elements is empty
		//  
		throw new UnsupportedOperationException("getPositionAt not yet implemented");
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "TrackImpl{" +
		"}";
	}
}
