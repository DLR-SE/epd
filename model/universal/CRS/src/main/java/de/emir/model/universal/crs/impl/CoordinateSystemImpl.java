package de.emir.model.universal.crs.impl;

import java.util.List;

import de.emir.model.universal.core.MemberObserver;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.core.impl.IdentifiedObjectImpl;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.CoordinateSystemAxis;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.model.universal.crs.impl.CoordinateSystemAxisImpl;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
The set of coordinate system axes that spans a given coordinate space. A coordinate system (CS)
 * is derived from a set of (mathematical) rules for specifying how coordinates in a given space
 * are to be assigned to points. The coordinate values in a coordinate tuple shall be recorded in
 * the order in which the coordinate system axes associations are recorded, whenever those
 * coordinates use a coordinate reference system that uses this coordinate system.
 * [GeoAPI]
 * @generated 
 */
@UMLImplementation(classifier = CoordinateSystem.class)
abstract public class CoordinateSystemImpl extends IdentifiedObjectImpl implements CoordinateSystem  
{
	
	
	/**
	 *	@generated 
	 */
	private CoordinateSystemAxis mAxes = new CoordinateSystemAxisImpl();
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public CoordinateSystemImpl(){
		super();
		//set the default values and assign them to this instance 
		setAxes(mAxes);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public CoordinateSystemImpl(final CoordinateSystem _copy) {
		super(_copy);
		mAxes = _copy.getAxes();
	}

	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public CoordinateSystemImpl(RSIdentifier _name, List<String> _allias, String _remarks, List<MemberObserver> _observers, RSIdentifier _identifier, CoordinateSystemAxis _axes) {
		super(_name,_allias,_remarks,_observers,_identifier);
		mAxes = _axes; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return CrsPackage.Literals.CoordinateSystem;
	}

	/**
	 *	@generated 
	 */
	public void setAxes(CoordinateSystemAxis _axes) {
		Notification<CoordinateSystemAxis> notification = basicSet(mAxes, _axes, CrsPackage.Literals.CoordinateSystem_axes);
		mAxes = _axes;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public CoordinateSystemAxis getAxes() {
		return mAxes;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "CoordinateSystemImpl{" +
		" allias = " + getAllias() + 
		" remarks = " + getRemarks() + 
		"}";
	}
}
