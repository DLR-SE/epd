package de.emir.model.universal.spatial.sf.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.sf.LinearRing;
import de.emir.model.universal.spatial.sf.SfPackage;
import de.emir.model.universal.spatial.sf.delegate.ILinearRingDelegationInterface;
import de.emir.model.universal.spatial.sf.impl.LineStringImpl;
import de.emir.model.universal.units.DistanceUnit;


/**
 Same as the LineString but has to be closed (first coordinate has to be the same as the last) 
 * @generated 
 */
@UMLImplementation(classifier = LinearRing.class)
public class LinearRingImpl extends LineStringImpl implements LinearRing  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public LinearRingImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public LinearRingImpl(final LinearRing _copy) {
		super(_copy);
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public LinearRingImpl(CoordinateSequence _points) {
		super(_points);
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SfPackage.Literals.LinearRing;
	}
	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public boolean isClosed()
	{
		Coordinate c0 = getCoordinate(0);
		Coordinate cN = getCoordinate(numCoordinates()-1);
		if (c0.getDistance(cN).getAs(DistanceUnit.MILLIMETER) < 0.00001)
			return true;
		return false;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	public void close()
	{
		if (isClosed())
			return ;
		Coordinate c0 = getCoordinate(0);
		getCoordinates().addCoordinate(c0.copy());
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "LinearRingImpl{" +
		"}";
	}
}
