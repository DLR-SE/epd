package de.emir.model.universal.spatial.sf.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.impl.CoordinateSequenceImpl;
import de.emir.model.universal.spatial.impl.GeometryImpl;
import de.emir.model.universal.spatial.sf.LineString;
import de.emir.model.universal.spatial.sf.SfPackage;
import de.emir.tuml.ucore.runtime.Notification;


/**
 *	@generated 
 */
@UMLImplementation(classifier = LineString.class)
public class LineStringImpl extends GeometryImpl implements LineString  
{
	
	
	/**
	 *	@generated not
	 */
	private CoordinateSequence mPoints = new CoordinateSequenceImpl();
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public LineStringImpl(){
		super();
		//set the default values and assign them to this instance 
		setPoints(mPoints);
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public LineStringImpl(final LineString _copy) {
		super(_copy);
		mPoints = _copy.getPoints();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public LineStringImpl(CoordinateSequence _points) {
		super();
		mPoints = _points; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SfPackage.Literals.LineString;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setPoints(CoordinateSequence _points) {
		Notification<CoordinateSequence> notification = basicSet(mPoints, _points, SfPackage.Literals.LineString_points);
		mPoints = _points;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 *	@generated 
	 */
	public CoordinateSequence getPoints() {
		return mPoints;
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
		return mPoints;
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "LineStringImpl{" +
		"}";
	}
}
