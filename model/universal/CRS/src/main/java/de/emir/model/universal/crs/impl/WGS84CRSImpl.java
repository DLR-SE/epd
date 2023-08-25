package de.emir.model.universal.crs.impl;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.crs.WGS84CRS;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.crs.impl.CoordinateReferenceSystemImpl;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 @Brief Global WGS84 coordinate reference system, commonly used in most domains.  
 * the WGS84 coordinate reference system is one of the most frequently used systems, 
 * therefore we provide optimized implementations
 * @generated 
 */
@UMLImplementation(classifier = WGS84CRS.class)
abstract public class WGS84CRSImpl extends CoordinateReferenceSystemImpl implements WGS84CRS  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public WGS84CRSImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public WGS84CRSImpl(final WGS84CRS _copy) {
		super(_copy);
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public WGS84CRSImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, CoordinateSystem _cs) {
		super(_name,_allias,_remarks,_observers,_identifier,_cs);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CrsPackage.Literals.WGS84CRS;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "WGS84CRSImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
	
	
	public double[] getTarget(double[] origin, double distance_meter, double azimuth_radian, double polar_radian) {
		throw new UnsupportedOperationException("Not Yet Implemented");
	}
}
