package de.emir.model.universal.crs.impl;

import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.crs.EngineeringCRS;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.math.impl.Vector2DImpl;
import de.emir.model.universal.crs.impl.LocalCRSImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**

 * A contextually local coordinate reference system which can be divided into two broad categories: 
 * - earth-fixed systems applied to engineering activities on or near the surface of the earth; 
 * - CRSs on moving platforms such as road vehicles, vessels, aircraft or spacecraft.
 * \source ISO 19111:2007
 * @generated 
 */
@UMLImplementation(classifier = EngineeringCRS.class)
abstract public class EngineeringCRSImpl extends LocalCRSImpl implements EngineeringCRS  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public EngineeringCRSImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public EngineeringCRSImpl(final EngineeringCRS _copy) {
		super(_copy);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public EngineeringCRSImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, CoordinateSystem _cs, Vector _origin, List<Double> _orientationOffset) {
		super(_name,_allias,_remarks,_observers,_identifier,_cs,_origin,_orientationOffset);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CrsPackage.Literals.EngineeringCRS;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "EngineeringCRSImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		" orientationOffset = " + getOrientationOffset() + 
		"}";
	}
	
	@Override
	public double[] getTarget(double[] origin, double distance_meter, double azimuth_radian, double polar_radian) {
		if (polar_radian != polar_radian) {
			Vector2D v = new Vector2DImpl(0, 1);
			v.rotateLocalCW(azimuth_radian);
			v.multLocal(distance_meter);
			if (origin.length == 2)
				return new double[] {origin[0] + v.getX(), origin[1] + v.getY() };
			return new double[] {origin[0] + v.getX(), origin[1] + v.getY(), origin[2]};
		}
		throw new UnsupportedOperationException("getTarget with polar_radian not yet implemented");
	}
}
