package de.emir.model.domain.maritime.vessel.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.domain.maritime.vessel.DistanceCharacteristic;
import de.emir.model.domain.maritime.vessel.ReferencedDistance;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.model.domain.maritime.vessel.impl.VesselCharacteristicImpl;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;
import java.util.List;


/**

    * Stores the measured distances from the vessel to detected objects in the vessels surroundings.
 * @generated 
 */
@UMLImplementation(classifier = DistanceCharacteristic.class)
public class DistanceCharacteristicImpl extends VesselCharacteristicImpl implements DistanceCharacteristic  
{
	
	
	/**
	 List of ReferenceDistance measurements. The source identifier of each ReferencedDistance should act as the key, i.e. for each source only the most recent ReferencedDistance should exist. 
	 * @generated 
	 */
	private List<ReferencedDistance> mDistances = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public DistanceCharacteristicImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public DistanceCharacteristicImpl(final DistanceCharacteristic _copy) {
		super(_copy);
		mDistances = _copy.getDistances();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public DistanceCharacteristicImpl(List<ReferencedDistance> _distances) {
		super();
		mDistances = _distances; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return VesselPackage.Literals.DistanceCharacteristic;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 List of ReferenceDistance measurements. The source identifier of each ReferencedDistance should act as the key, i.e. for each source only the most recent ReferencedDistance should exist. 
	 * @generated 
	 */
	public List<ReferencedDistance> getDistances() {
		if (mDistances == null) {
			mDistances = new UContainmentList<ReferencedDistance>(this, VesselPackage.theInstance.getDistanceCharacteristic_distances()); 
		}
		return mDistances;
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "DistanceCharacteristicImpl{" +
		"}";
	}
}
