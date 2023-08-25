package de.emir.model.universal.spatial.sf.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.impl.CoordinateSequenceImpl;
import de.emir.model.universal.spatial.impl.GeometryImpl;
import de.emir.model.universal.spatial.sf.Point;
import de.emir.model.universal.spatial.sf.SfPackage;
import de.emir.model.universal.spatial.sf.delegate.IPointDelegationInterface;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Point.class)
public class PointImpl extends GeometryImpl implements Point  
{
	
	
	/**
	 *	@generated 
	 */
	private Coordinate mCoordinate = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public PointImpl(){
		super();
		//set the default values and assign them to this instance 
		setCoordinate(mCoordinate);
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public PointImpl(final Point _copy) {
		super(_copy);
		mCoordinate = _copy.getCoordinate();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public PointImpl(Coordinate _coordinate) {
		super();
		mCoordinate = _coordinate; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SfPackage.Literals.Point;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setCoordinate(Coordinate _coordinate) {
		Notification<Coordinate> notification = basicSet(mCoordinate, _coordinate, SfPackage.Literals.Point_coordinate);
		mCoordinate = _coordinate;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 *	@generated 
	 */
	public Coordinate getCoordinate() {
		return mCoordinate;
	}
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	public CoordinateSequence getCoordinates()
	{
		CoordinateSequence cs = new CoordinateSequenceImpl();
		cs.addCoordinate(getCoordinate());
		return cs;
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "PointImpl{" +
		"}";
	}
}
