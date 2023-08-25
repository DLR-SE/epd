package de.emir.model.universal.physics.impl;

import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.physics.MultiViewObjectSurfaceInforamtion;
import de.emir.model.universal.physics.impl.ObjectSurfaceInformationImpl;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.impl.EnvelopeImpl;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.impl.LengthImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = MultiViewObjectSurfaceInforamtion.class)
public class MultiViewObjectSurfaceInforamtionImpl extends ObjectSurfaceInformationImpl implements MultiViewObjectSurfaceInforamtion  
{
	
	
	/**
	 
	 * Geometry as seen from a 90 degree angle along the main axis of the vessel
	 * 
	 * @generated 
	 */
	private Geometry mSideGeometry = null;
	/**
	
	 * Geometry as seen if the vessel moves directly towards the own position
	 * @generated 
	 */
	private Geometry mFrontGeometry = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public MultiViewObjectSurfaceInforamtionImpl(){
		super();
		//set the default values and assign them to this instance 
		setSideGeometry(mSideGeometry);
		setFrontGeometry(mFrontGeometry);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public MultiViewObjectSurfaceInforamtionImpl(final MultiViewObjectSurfaceInforamtion _copy) {
		super(_copy);
		mSideGeometry = _copy.getSideGeometry();
		mFrontGeometry = _copy.getFrontGeometry();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public MultiViewObjectSurfaceInforamtionImpl(Geometry _geometry, Geometry _sideGeometry, Geometry _frontGeometry) {
		super(_geometry);
		mSideGeometry = _sideGeometry; 
		mFrontGeometry = _frontGeometry; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.MultiViewObjectSurfaceInforamtion;
	}

	/**
	 
	 * Geometry as seen from a 90 degree angle along the main axis of the vessel
	 * 
	 * @generated 
	 */
	public void setSideGeometry(Geometry _sideGeometry) {
		Notification<Geometry> notification = basicSet(mSideGeometry, _sideGeometry, PhysicsPackage.Literals.MultiViewObjectSurfaceInforamtion_sideGeometry);
		mSideGeometry = _sideGeometry;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 
	 * Geometry as seen from a 90 degree angle along the main axis of the vessel
	 * 
	 * @generated 
	 */
	public Geometry getSideGeometry() {
		return mSideGeometry;
	}

	/**
	
	 * Geometry as seen if the vessel moves directly towards the own position
	 * @generated 
	 */
	public void setFrontGeometry(Geometry _frontGeometry) {
		Notification<Geometry> notification = basicSet(mFrontGeometry, _frontGeometry, PhysicsPackage.Literals.MultiViewObjectSurfaceInforamtion_frontGeometry);
		mFrontGeometry = _frontGeometry;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	
	 * Geometry as seen if the vessel moves directly towards the own position
	 * @generated 
	 */
	public Geometry getFrontGeometry() {
		return mFrontGeometry;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Geometry getTopGeometry()
	{
		return getGeometry();
	}


	
	@Override
	public Envelope getBoundingBox2D() {
		return getGeometry() != null ? getGeometry().getEnvelope() : null;
	}
	@Override
	public Envelope getBoundingBox3D() {
		Envelope env2D = getBoundingBox2D();
		if (env2D == null) 
			return null;
		Envelope other = null;
		if (getSideGeometry() != null)
			other = getSideGeometry().getEnvelope();
		if (other == null && getFrontGeometry() != null)
			other = getFrontGeometry().getEnvelope();
		if (other == null)
			return env2D; //we are allowed to return 2D geometries
		//other.y actually contains the height
		
		Engineering2D crs = new Engineering2DImpl();
		crs.setOrigin(env2D.getCenter().get(CRSUtils.WGS84_2D).toVector());
		Coordinate min = env2D.getMinPoint().copy();
		Coordinate max = env2D.getMaxPoint().copy();
		min.setZ(other.getMinPoint().getY()); //FIXME: coordinate transform required??
		max.setZ(other.getMaxPoint().getY());
		
		return new EnvelopeImpl(min, max);
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "MultiViewObjectSurfaceInforamtionImpl{" +
		"}";
	}
	
	
	@Override
	public Length getHeight() {
		if (getSideGeometry() != null) {
			double hm = getSideGeometry().getEnvelope().getSize(DistanceUnit.METER).get(1);
			return new LengthImpl(hm, DistanceUnit.METER);
		}else if (getFrontGeometry() != null) {
			double hm = getSideGeometry().getEnvelope().getSize(DistanceUnit.METER).get(1);
			return new LengthImpl(hm, DistanceUnit.METER);
		}
		return super.getHeight();
	}
}
