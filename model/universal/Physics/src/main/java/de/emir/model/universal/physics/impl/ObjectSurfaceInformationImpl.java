package de.emir.model.universal.physics.impl;

import de.emir.model.universal.crs.Engineering3D;
import de.emir.model.universal.crs.impl.Engineering3DImpl;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.physics.impl.CharacteristicImpl;
import de.emir.model.universal.physics.ObjectSurfaceInformation;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.impl.LengthImpl;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;


/**
 *	@generated 
 */
@UMLImplementation(classifier = ObjectSurfaceInformation.class)
public class ObjectSurfaceInformationImpl extends CharacteristicImpl implements ObjectSurfaceInformation  
{
	
	
	/**
	 *	@generated 
	 */
	private Geometry mGeometry = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public ObjectSurfaceInformationImpl(){
		super();
		//set the default values and assign them to this instance 
		setGeometry(mGeometry);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public ObjectSurfaceInformationImpl(final ObjectSurfaceInformation _copy) {
		super(_copy);
		mGeometry = _copy.getGeometry();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public ObjectSurfaceInformationImpl(Geometry _geometry) {
		super();
		mGeometry = _geometry; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return PhysicsPackage.Literals.ObjectSurfaceInformation;
	}

	/**
	 *	@generated 
	 */
	public void setGeometry(Geometry _geometry) {
		Notification<Geometry> notification = basicSet(mGeometry, _geometry, PhysicsPackage.Literals.ObjectSurfaceInformation_geometry);
		mGeometry = _geometry;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public Geometry getGeometry() {
		return mGeometry;
	}

	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Length getWidth()
	{
		Geometry geometry = getGeometry();
		if(geometry == null)
			return null;
		Envelope envelop = geometry.getEnvelope();
		if(envelop == null)
			return null;
		Vector2D vector = (Vector2D) envelop.getSize(DistanceUnit.METER);
		if(vector == null)
			return null;
		return new LengthImpl(vector.getX(), DistanceUnit.METER);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Length getLength()
	{
		Geometry geometry = getGeometry();
		if(geometry == null)
			return null;
		Envelope envelop = geometry.getEnvelope();
		if(envelop == null)
			return null;
		Vector2D vector = (Vector2D) envelop.getSize(DistanceUnit.METER);
		if(vector == null)
			return null;
		return new LengthImpl(vector.getY(), DistanceUnit.METER);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Length getHeight()
	{
		if (getGeometry() != null && getGeometry().getDimension() > 2){
			Envelope env = getGeometry().getEnvelope();
			if (env == null) return null;
			if (env.getMinPoint() != null && env.getMaxPoint() != null && env.getMinPoint().dimension() == 3){
				//need to convert it into a known crs to know which part of the coordinate we have to interpret as height
				Engineering3D crs = new Engineering3DImpl();
				crs.setOrigin(env.getCenter().get(CRSUtils.WGS84_3D).toVector());
				Coordinate min = env.getMinPoint().get(crs);
				Coordinate max = env.getMaxPoint().get(crs);
				return new LengthImpl(max.getZ()-min.getZ(), DistanceUnit.METER);
			}
		}
		return null; 
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Envelope getBoundingBox2D()
	{
		//at this point we can always return the boundingbox of the geometry
		return getGeometry() != null ? getGeometry().getEnvelope() : null;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public Envelope getBoundingBox3D()
	{
		//here we do the same, in case the geometry is 3D we will get a 3D Envelope, otherwise we are allowed to return a 2D BoundingBox (see javadoc)
		return getGeometry() != null ? getGeometry().getEnvelope() : null;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "ObjectSurfaceInformationImpl{" +
		"}";
	}
}
