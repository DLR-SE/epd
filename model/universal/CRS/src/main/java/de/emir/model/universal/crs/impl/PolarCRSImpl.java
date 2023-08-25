package de.emir.model.universal.crs.impl;

import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.crs.PolarCRS;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.crs.impl.LocalCRSImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = PolarCRS.class)
abstract public class PolarCRSImpl extends LocalCRSImpl implements PolarCRS  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public PolarCRSImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public PolarCRSImpl(final PolarCRS _copy) {
		super(_copy);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public PolarCRSImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, CoordinateSystem _cs, Vector _origin, List<Double> _orientationOffset) {
		super(_name,_allias,_remarks,_observers,_identifier,_cs,_origin,_orientationOffset);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CrsPackage.Literals.PolarCRS;
	}

	
	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated
	 */
	public List<Vector> toPolarCoordinates(final List<Vector> vertices)
	{
		//TODO: 
		throw new UnsupportedOperationException("toPolarCoordinates not yet implemented");
	}

	/**
	 * @inheritDoc
	 * @generated
	 */
	public List<Vector> toPolarCoordinates(final Vector center, final List<Vector> vertices)
	{
		//TODO: 
		throw new UnsupportedOperationException("toPolarCoordinates not yet implemented");
	}

	@Override
	public double[] getTarget(double[] origin, double distance_meter, double azimuth_radian, double polar_radian) {
		throw new UnsupportedOperationException("getTarget not yet implemented");
	}
	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "PolarCRSImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" orientationOffset = " + getOrientationOffset() + 
		"}";
	}
}
