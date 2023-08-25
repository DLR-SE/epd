package de.emir.model.universal.spatial.sf.impl;

import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.impl.GeometryImpl;
import de.emir.model.universal.spatial.sf.LinearRing;
import de.emir.model.universal.spatial.sf.Polygon;
import de.emir.model.universal.spatial.sf.SfPackage;
import de.emir.model.universal.spatial.sf.delegate.IPolygonDelegationInterface;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.lists.UContainmentList;
import java.util.List;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Polygon.class)
public class PolygonImpl extends GeometryImpl implements Polygon  
{
	
	
	/**
	 *	@generated not
	 */
	private LinearRing mShell = new LinearRingImpl();
	/**
	 *	@generated 
	 */
	private List<LinearRing> mHoles = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public PolygonImpl(){
		super();
		//set the default values and assign them to this instance 
		setShell(mShell);
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public PolygonImpl(final Polygon _copy) {
		super(_copy);
		mShell = _copy.getShell();
		mHoles = _copy.getHoles();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public PolygonImpl(LinearRing _shell, List<LinearRing> _holes) {
		super();
		mShell = _shell; 
		mHoles = _holes; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SfPackage.Literals.Polygon;
	}
	
	//////////////////////////////////////////////////////////////////
	//						Setter / Getter							//
	//////////////////////////////////////////////////////////////////
	/**
	 *	@generated 
	 */
	public void setShell(LinearRing _shell) {
		Notification<LinearRing> notification = basicSet(mShell, _shell, SfPackage.Literals.Polygon_shell);
		mShell = _shell;
		if (notification != null){
			dispatchNotification(notification);
		}
	}
	/**
	 *	@generated 
	 */
	public LinearRing getShell() {
		return mShell;
	}
	/**
	 *	@generated 
	 */
	public List<LinearRing> getHoles() {
		if (mHoles == null) {
			mHoles = new UContainmentList<LinearRing>(this, SfPackage.theInstance.getPolygon_holes()); 
		}
		return mHoles;
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
		//is that correct?
		return getShell().getCoordinates();
	}



	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "PolygonImpl{" +
		"}";
	}
}
