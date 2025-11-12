package de.emir.model.universal.detection;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.UObject;

import de.emir.model.universal.CoreModel;
import de.emir.model.universal.PhysicsModel;
import de.emir.model.universal.SpatialModel;
import de.emir.model.universal.detection.impl.TargetAssociationCharacteristicImpl;
import de.emir.model.universal.detection.impl.TargetImpl;
import de.emir.model.universal.detection.impl.TrackImpl;
import de.emir.model.universal.detection.impl.TrackPointImpl;
import de.emir.model.universal.detection.impl.TrackedTargetImpl;
import de.emir.model.universal.UnitsModel;
import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;

/**
 *	@generated 
 */
public class TargetPackage  
{
	
	/**
	 * @generated
	 */
	public static TargetPackage theInstance = new TargetPackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier Target
		*/
		UClass Target = TargetPackage.theInstance.getTarget();
		/**
		* @generated
		* @return meta type for classifier TrackedTarget
		*/
		UClass TrackedTarget = TargetPackage.theInstance.getTrackedTarget();
		/**
		* @generated
		* @return meta type for classifier Track
		*/
		UClass Track = TargetPackage.theInstance.getTrack();
		/**
		* @generated
		* @return meta type for classifier TrackPoint
		*/
		UClass TrackPoint = TargetPackage.theInstance.getTrackPoint();
		/**
		* @generated
		* @return meta type for classifier TargetAssociationCharacteristic
		*/
		UClass TargetAssociationCharacteristic = TargetPackage.theInstance.getTargetAssociationCharacteristic();
		
		/**
		 * @generated
		 * @return feature descriptor id in type Target
		 */
		 UStructuralFeature Target_id = TargetPackage.theInstance.getTarget_id();
		/**
		 * @generated
		 * @return feature descriptor timestamp in type Target
		 */
		 UStructuralFeature Target_timestamp = TargetPackage.theInstance.getTarget_timestamp();
		/**
		 * @generated
		 * @return feature descriptor reference in type Target
		 */
		 UStructuralFeature Target_reference = TargetPackage.theInstance.getTarget_reference();
		/**
		 * @generated
		 * @return feature descriptor referenceDistance in type Target
		 */
		 UStructuralFeature Target_referenceDistance = TargetPackage.theInstance.getTarget_referenceDistance();
		/**
		 * @generated
		 * @return feature descriptor referenceBearing in type Target
		 */
		 UStructuralFeature Target_referenceBearing = TargetPackage.theInstance.getTarget_referenceBearing();
		/**
		 * @generated
		 * @return feature descriptor id in type TrackedTarget
		 */
		 UStructuralFeature TrackedTarget_id = TargetPackage.theInstance.getTrackedTarget_id();
		/**
		 * @generated
		 * @return feature descriptor track in type TrackedTarget
		 */
		 UStructuralFeature TrackedTarget_track = TargetPackage.theInstance.getTrackedTarget_track();
		/**
		 * @generated
		 * @return feature descriptor id in type Track
		 */
		 UStructuralFeature Track_id = TargetPackage.theInstance.getTrack_id();
		/**
		 * @generated
		 * @return feature descriptor lastUpdate in type Track
		 */
		 UStructuralFeature Track_lastUpdate = TargetPackage.theInstance.getTrack_lastUpdate();
		/**
		 * @generated
		 * @return feature descriptor reference in type Track
		 */
		 UStructuralFeature Track_reference = TargetPackage.theInstance.getTrack_reference();
		/**
		 * @generated
		 * @return feature descriptor trackPoints in type Track
		 */
		 UStructuralFeature Track_trackPoints = TargetPackage.theInstance.getTrack_trackPoints();
		/**
		 * @generated
		 * @return feature descriptor id in type TrackPoint
		 */
		 UStructuralFeature TrackPoint_id = TargetPackage.theInstance.getTrackPoint_id();
		/**
		 * @generated
		 * @return feature descriptor timestamp in type TrackPoint
		 */
		 UStructuralFeature TrackPoint_timestamp = TargetPackage.theInstance.getTrackPoint_timestamp();
		/**
		 * @generated
		 * @return feature descriptor reference in type TrackPoint
		 */
		 UStructuralFeature TrackPoint_reference = TargetPackage.theInstance.getTrackPoint_reference();
		/**
		 * @generated
		 * @return feature descriptor associationID in type TargetAssociationCharacteristic
		 */
		 UStructuralFeature TargetAssociationCharacteristic_associationID = TargetPackage.theInstance.getTargetAssociationCharacteristic_associationID();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mTarget = null;
	/**
	* @generated
	*/
	private UClass mTrackedTarget = null;
	/**
	* @generated
	*/
	private UClass mTrack = null;
	/**
	* @generated
	*/
	private UClass mTrackPoint = null;
	/**
	* @generated
	*/
	private UClass mTargetAssociationCharacteristic = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	//Features for classifier Target
	/**
	 * @generated
	 */
	private UStructuralFeature mTarget_id = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTarget_timestamp = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTarget_reference = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTarget_referenceDistance = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTarget_referenceBearing = null;
	
	//Features for classifier TrackedTarget
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackedTarget_id = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackedTarget_track = null;
	
	//Features for classifier Track
	/**
	 * @generated
	 */
	private UStructuralFeature mTrack_id = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrack_lastUpdate = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrack_reference = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrack_trackPoints = null;
	
	//Features for classifier TrackPoint
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackPoint_id = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackPoint_timestamp = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackPoint_reference = null;
	
	//Features for classifier TargetAssociationCharacteristic
	/**
	 * @generated
	 */
	private UStructuralFeature mTargetAssociationCharacteristic_associationID = null;
	
	
	
	
	/**
	 * @generated
	 */
	public static TargetPackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package TargetPackage ...", 1);
		theInstance = new TargetPackage();
		//initialize referenced models
		PhysicsModel.init();
		SpatialModel.init();
		UnitsModel.init();
		CoreModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.universal.Target");
		p.getContent().add(theInstance.mTarget);
		p.getContent().add(theInstance.mTrackedTarget);
		p.getContent().add(theInstance.mTrack);
		p.getContent().add(theInstance.mTrackPoint);
		p.getContent().add(theInstance.mTargetAssociationCharacteristic);
		p.freeze();
		
		
		
		ULog.debug(-1, "... package TargetPackage initialized");
		
		return theInstance;
	}
	
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mTarget = UMetaBuilder.manual().createClass("Target", false, ITarget.class, TargetImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mTarget, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new TargetImpl();
				}
			});
			mTarget.setDocumentation("\r\n    * A Target is a single detection point generated by the detection system. It consists\r\n    * of a detection timestamp, unique ID and bearing/distance to a reference point. This\r\n    * is usually the location of the sensor which detected the object. While the Target can also\r\n    * contain a georeferenced location as part of the Pose, this is usually calculated using\r\n    * the distance/bearing references. The name, properties and position is inherited from\r\n    * PhysicalObject.\r\n    ");
		
		mTrackedTarget = UMetaBuilder.manual().createClass("TrackedTarget", false, ITrackedTarget.class, TrackedTargetImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mTrackedTarget, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new TrackedTargetImpl();
				}
			});
			mTrackedTarget.setDocumentation("\r\n    * A TrackedTarget is an object that is detected by a tracking system. The name, properties and\r\n    * position is inherited from PhysicalObject.\r\n    ");
		
		mTrack = UMetaBuilder.manual().createClass("Track", false, ITrack.class, TrackImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mTrack, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new TrackImpl();
				}
			});
			mTrack.setDocumentation("\r\n    * A track is a collection of TrackPoints associated to a TrackedTarget. It represents historical\r\n    * positions associated to a TrackedTarget. This is for example produced by a tracking system.\r\n    ");
		
		mTrackPoint = UMetaBuilder.manual().createClass("TrackPoint", false, ITrackPoint.class, TrackPointImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mTrackPoint, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new TrackPointImpl();
				}
			});
			mTrackPoint.setDocumentation("\r\n    * Represents a observation point for a TrackedTarget which is part of a Track. The name,\r\n    * properties and position is inherited from PhysicalObject.\r\n    ");
		
		mTargetAssociationCharacteristic = UMetaBuilder.manual().createClass("TargetAssociationCharacteristic", false, ITargetAssociationCharacteristic.class, TargetAssociationCharacteristicImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mTargetAssociationCharacteristic, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new TargetAssociationCharacteristicImpl();
				}
			});
			mTargetAssociationCharacteristic.setDocumentation("\r\n    * The TargetAssociationCharacteristic is used for keeping associations between multiple\r\n    * PhysicalObjects. When a new Target is for example created from fusing AIS and RADAR data,\r\n    * a TargetAssociationCharacteristic could be appended to the AIS object, RADAR target and the\r\n    * newly created object to handle associations between these objects.\r\n    ");
		
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of Target
			mTarget_id = UMetaBuilder.manual().createFeature("id", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTarget_id, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ITarget)instance).getId(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ITarget)instance).setId((String)value); } }
				);
				mTarget_id.setDocumentation(" Unique identifier of the detected Target. ");
			mTarget_timestamp = UMetaBuilder.manual().createFeature("timestamp", UnitsPackage.theInstance.getTime(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTarget_timestamp, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ITarget)instance).getTimestamp(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ITarget)instance).setTimestamp((Time)value); } }
				);
				mTarget_timestamp.setDocumentation(" Timestamp of the detection of the Target. ");
			mTarget_reference = UMetaBuilder.manual().createFeature("reference", PhysicsPackage.theInstance.getPhysicalObject(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTarget_reference, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ITarget)instance).getReference(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ITarget)instance).setReference((PhysicalObject)value); } }
				);
				mTarget_reference.setDocumentation(" Reference object from which the detection was made. This could be a sensor or vessel. ");
			mTarget_referenceDistance = UMetaBuilder.manual().createFeature("referenceDistance", UnitsPackage.theInstance.getDistance(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTarget_referenceDistance, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ITarget)instance).getReferenceDistance(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ITarget)instance).setReferenceDistance((Distance)value); } }
				);
				mTarget_referenceDistance.setDocumentation(" Distance to the reference object from which the detection was made. ");
			mTarget_referenceBearing = UMetaBuilder.manual().createFeature("referenceBearing", UnitsPackage.theInstance.getAngle(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTarget_referenceBearing, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ITarget)instance).getReferenceBearing(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ITarget)instance).setReferenceBearing((Angle)value); } }
				);
				mTarget_referenceBearing.setDocumentation(" Bearing to the reference object from which the detection was made. ");
			
			//Features of TrackedTarget
			mTrackedTarget_id = UMetaBuilder.manual().createFeature("id", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackedTarget_id, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ITrackedTarget)instance).getId(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ITrackedTarget)instance).setId((String)value); } }
				);
				mTrackedTarget_id.setDocumentation(" Unique identifier of the TrackedTarget. ");
			mTrackedTarget_track = UMetaBuilder.manual().createFeature("track", TargetPackage.theInstance.getTrack(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackedTarget_track, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ITrackedTarget)instance).getTrack(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ITrackedTarget)instance).setTrack((ITrack)value); } }
				);
				mTrackedTarget_track.setDocumentation(" Track of the TrackedTarget. ");
			
			//Features of Track
			mTrack_id = UMetaBuilder.manual().createFeature("id", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrack_id, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ITrack)instance).getId(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ITrack)instance).setId((String)value); } }
				);
				mTrack_id.setDocumentation(" Unique identifier of the Track. ");
			mTrack_lastUpdate = UMetaBuilder.manual().createFeature("lastUpdate", UnitsPackage.theInstance.getTime(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrack_lastUpdate, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ITrack)instance).getLastUpdate(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ITrack)instance).setLastUpdate((Time)value); } }
				);
				mTrack_lastUpdate.setDocumentation(" Timestamp when the Track was last updated. ");
			mTrack_reference = UMetaBuilder.manual().createFeature("reference", TargetPackage.theInstance.getTrackedTarget(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrack_reference, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ITrack)instance).getReference(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ITrack)instance).setReference((ITrackedTarget)value); } }
				);
				mTrack_reference.setDocumentation(" TrackedTarget reference which is associated to the Track. ");
			mTrack_trackPoints = UMetaBuilder.manual().createFeature("trackPoints", TargetPackage.theInstance.getTrackPoint(), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mTrack_trackPoints, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ITrack)instance).getTrackPoints(); } }, 
						null
				);
				mTrack_trackPoints.setDocumentation(" List of TrackPoints associated to a Track. ");
			
			//Features of TrackPoint
			mTrackPoint_id = UMetaBuilder.manual().createFeature("id", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackPoint_id, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ITrackPoint)instance).getId(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ITrackPoint)instance).setId((String)value); } }
				);
				mTrackPoint_id.setDocumentation(" Unique identifier of the TrackPoint. ");
			mTrackPoint_timestamp = UMetaBuilder.manual().createFeature("timestamp", UnitsPackage.theInstance.getTime(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackPoint_timestamp, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ITrackPoint)instance).getTimestamp(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ITrackPoint)instance).setTimestamp((Time)value); } }
				);
				mTrackPoint_timestamp.setDocumentation(" Timestamp of the TrackPoint creation. ");
			mTrackPoint_reference = UMetaBuilder.manual().createFeature("reference", TargetPackage.theInstance.getTrack(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackPoint_reference, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ITrackPoint)instance).getReference(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ITrackPoint)instance).setReference((ITrack)value); } }
				);
				mTrackPoint_reference.setDocumentation(" Reference to the Track object which references to the TrackPoint. ");
			
			//Features of TargetAssociationCharacteristic
			mTargetAssociationCharacteristic_associationID = UMetaBuilder.manual().createFeature("associationID", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTargetAssociationCharacteristic_associationID, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ITargetAssociationCharacteristic)instance).getAssociationID(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ITargetAssociationCharacteristic)instance).setAssociationID((String)value); } }
				);
				mTargetAssociationCharacteristic_associationID.setDocumentation(" Unique association identifier. ");
			
		}
		{ //assign features
			mTarget.getStructuralFeatures().add(mTarget_id);
			mTarget.getStructuralFeatures().add(mTarget_timestamp);
			mTarget.getStructuralFeatures().add(mTarget_reference);
			mTarget.getStructuralFeatures().add(mTarget_referenceDistance);
			mTarget.getStructuralFeatures().add(mTarget_referenceBearing);
			mTrackedTarget.getStructuralFeatures().add(mTrackedTarget_id);
			mTrackedTarget.getStructuralFeatures().add(mTrackedTarget_track);
			mTrack.getStructuralFeatures().add(mTrack_id);
			mTrack.getStructuralFeatures().add(mTrack_lastUpdate);
			mTrack.getStructuralFeatures().add(mTrack_reference);
			mTrack.getStructuralFeatures().add(mTrack_trackPoints);
			mTrackPoint.getStructuralFeatures().add(mTrackPoint_id);
			mTrackPoint.getStructuralFeatures().add(mTrackPoint_timestamp);
			mTrackPoint.getStructuralFeatures().add(mTrackPoint_reference);
			mTargetAssociationCharacteristic.getStructuralFeatures().add(mTargetAssociationCharacteristic_associationID);
		}
		
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mTarget.setSuperType(PhysicsPackage.theInstance.getPhysicalObject());
		mTrackedTarget.setSuperType(PhysicsPackage.theInstance.getPhysicalObject());
		mTrack.setSuperType(CorePackage.theInstance.getIdentifiedObject());
		mTrackPoint.setSuperType(PhysicsPackage.theInstance.getLocatableObject());
		mTargetAssociationCharacteristic.setSuperType(PhysicsPackage.theInstance.getCharacteristic());
		
	}
	
	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getTarget(){
		if (mTarget == null){
			mTarget = UCoreMetaRepository.getUClass(ITarget.class);
		}
		return mTarget;
	}
	/**
	* @generated
	*/
	public UClass getTrackedTarget(){
		if (mTrackedTarget == null){
			mTrackedTarget = UCoreMetaRepository.getUClass(ITrackedTarget.class);
		}
		return mTrackedTarget;
	}
	/**
	* @generated
	*/
	public UClass getTrack(){
		if (mTrack == null){
			mTrack = UCoreMetaRepository.getUClass(ITrack.class);
		}
		return mTrack;
	}
	/**
	* @generated
	*/
	public UClass getTrackPoint(){
		if (mTrackPoint == null){
			mTrackPoint = UCoreMetaRepository.getUClass(ITrackPoint.class);
		}
		return mTrackPoint;
	}
	/**
	* @generated
	*/
	public UClass getTargetAssociationCharacteristic(){
		if (mTargetAssociationCharacteristic == null){
			mTargetAssociationCharacteristic = UCoreMetaRepository.getUClass(ITargetAssociationCharacteristic.class);
		}
		return mTargetAssociationCharacteristic;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getTarget_id(){
		if (mTarget_id == null)
			mTarget_id = getTarget().getFeature("id");
		return mTarget_id;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTarget_timestamp(){
		if (mTarget_timestamp == null)
			mTarget_timestamp = getTarget().getFeature("timestamp");
		return mTarget_timestamp;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTarget_reference(){
		if (mTarget_reference == null)
			mTarget_reference = getTarget().getFeature("reference");
		return mTarget_reference;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTarget_referenceDistance(){
		if (mTarget_referenceDistance == null)
			mTarget_referenceDistance = getTarget().getFeature("referenceDistance");
		return mTarget_referenceDistance;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTarget_referenceBearing(){
		if (mTarget_referenceBearing == null)
			mTarget_referenceBearing = getTarget().getFeature("referenceBearing");
		return mTarget_referenceBearing;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTrackedTarget_id(){
		if (mTrackedTarget_id == null)
			mTrackedTarget_id = getTrackedTarget().getFeature("id");
		return mTrackedTarget_id;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTrackedTarget_track(){
		if (mTrackedTarget_track == null)
			mTrackedTarget_track = getTrackedTarget().getFeature("track");
		return mTrackedTarget_track;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTrack_id(){
		if (mTrack_id == null)
			mTrack_id = getTrack().getFeature("id");
		return mTrack_id;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTrack_lastUpdate(){
		if (mTrack_lastUpdate == null)
			mTrack_lastUpdate = getTrack().getFeature("lastUpdate");
		return mTrack_lastUpdate;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTrack_reference(){
		if (mTrack_reference == null)
			mTrack_reference = getTrack().getFeature("reference");
		return mTrack_reference;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTrack_trackPoints(){
		if (mTrack_trackPoints == null)
			mTrack_trackPoints = getTrack().getFeature("trackPoints");
		return mTrack_trackPoints;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTrackPoint_id(){
		if (mTrackPoint_id == null)
			mTrackPoint_id = getTrackPoint().getFeature("id");
		return mTrackPoint_id;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTrackPoint_timestamp(){
		if (mTrackPoint_timestamp == null)
			mTrackPoint_timestamp = getTrackPoint().getFeature("timestamp");
		return mTrackPoint_timestamp;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTrackPoint_reference(){
		if (mTrackPoint_reference == null)
			mTrackPoint_reference = getTrackPoint().getFeature("reference");
		return mTrackPoint_reference;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTargetAssociationCharacteristic_associationID(){
		if (mTargetAssociationCharacteristic_associationID == null)
			mTargetAssociationCharacteristic_associationID = getTargetAssociationCharacteristic().getFeature("associationID");
		return mTargetAssociationCharacteristic_associationID;
	}
}
