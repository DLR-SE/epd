package de.emir.model.application.vehicle;

import de.emir.model.application.vehicle.impl.LandcraftImpl;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.model.application.vehicle.impl.VehicleImpl;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.model.application.vehicle.impl.WatercraftImpl;
import de.emir.model.universal.CoreModel;
import de.emir.model.universal.PhysicsModel;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.model.application.vehicle.Track;
import de.emir.model.application.vehicle.TrackCharacteristic;
import de.emir.model.application.vehicle.TrackElement;
import de.emir.model.application.vehicle.Trajectory;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.model.application.vehicle.Landcraft;
import de.emir.model.application.vehicle.Vehicle;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.model.application.vehicle.impl.TrajectoryImpl;
import de.emir.model.application.vehicle.impl.TrajectorySegmentImpl;
import de.emir.model.application.vehicle.impl.TrackCharacteristicImpl;
import de.emir.model.application.vehicle.impl.TrackElementImpl;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.model.application.vehicle.impl.TrackImpl;
import de.emir.model.application.vehicle.Watercraft;
import de.emir.model.application.vehicle.TrajectorySegment;
import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.ModelReference;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.units.Orientation;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Time;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;

/**
 *	@generated 
 */
public class VehiclePackage  
{
	/**
	 * @generated
	 */
	public static VehiclePackage theInstance = new VehiclePackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier Vehicle
		*/
		UClass Vehicle = VehiclePackage.theInstance.getVehicle();
		/**
		* @generated
		* @return meta type for classifier Trajectory
		*/
		UClass Trajectory = VehiclePackage.theInstance.getTrajectory();
		/**
		* @generated
		* @return meta type for classifier TrackElement
		*/
		UClass TrackElement = VehiclePackage.theInstance.getTrackElement();
		/**
		* @generated
		* @return meta type for classifier Track
		*/
		UClass Track = VehiclePackage.theInstance.getTrack();
		/**
		* @generated
		* @return meta type for classifier TrackCharacteristic
		*/
		UClass TrackCharacteristic = VehiclePackage.theInstance.getTrackCharacteristic();
		/**
		* @generated
		* @return meta type for classifier Landcraft
		*/
		UClass Landcraft = VehiclePackage.theInstance.getLandcraft();
		/**
		* @generated
		* @return meta type for classifier Watercraft
		*/
		UClass Watercraft = VehiclePackage.theInstance.getWatercraft();
		/**
		* @generated
		* @return meta type for classifier TrajectorySegment
		*/
		UClass TrajectorySegment = VehiclePackage.theInstance.getTrajectorySegment();
		/**
		 * @generated
		 * @return feature descriptor origin in type TrajectorySegment
		 */
		 UStructuralFeature TrajectorySegment_origin = VehiclePackage.theInstance.getTrajectorySegment_origin();
		/**
		 * @generated
		 * @return feature descriptor name in type TrajectorySegment
		 */
		 UStructuralFeature TrajectorySegment_name = VehiclePackage.theInstance.getTrajectorySegment_name();
		/**
		 * @generated
		 * @return feature descriptor id in type TrajectorySegment
		 */
		 UStructuralFeature TrajectorySegment_id = VehiclePackage.theInstance.getTrajectorySegment_id();
		/**
		 * @generated
		 * @return feature descriptor destination in type TrajectorySegment
		 */
		 UStructuralFeature TrajectorySegment_destination = VehiclePackage.theInstance.getTrajectorySegment_destination();
		/**
		 * @generated
		 * @return feature descriptor segments in type Trajectory
		 */
		 UStructuralFeature Trajectory_segments = VehiclePackage.theInstance.getTrajectory_segments();
		/**
		 * @generated
		 * @return feature descriptor speed in type TrajectorySegment
		 */
		 UStructuralFeature TrajectorySegment_speed = VehiclePackage.theInstance.getTrajectorySegment_speed();
		/**
		 * @generated
		 * @return feature descriptor allowedStarboardCTE in type TrajectorySegment
		 */
		 UStructuralFeature TrajectorySegment_allowedStarboardCTE = VehiclePackage.theInstance.getTrajectorySegment_allowedStarboardCTE();
		/**
		 * @generated
		 * @return feature descriptor name in type Trajectory
		 */
		 UStructuralFeature Trajectory_name = VehiclePackage.theInstance.getTrajectory_name();
		/**
		 * @generated
		 * @return feature descriptor allowedPortCTE in type TrajectorySegment
		 */
		 UStructuralFeature TrajectorySegment_allowedPortCTE = VehiclePackage.theInstance.getTrajectorySegment_allowedPortCTE();
		/**
		 * @generated
		 * @return feature descriptor source in type Trajectory
		 */
		 UStructuralFeature Trajectory_source = VehiclePackage.theInstance.getTrajectory_source();
		/**
		 * @generated
		 * @return feature descriptor heading in type TrackElement
		 */
		 UStructuralFeature TrackElement_heading = VehiclePackage.theInstance.getTrackElement_heading();
		/**
		 * @generated
		 * @return feature descriptor coordinate in type TrackElement
		 */
		 UStructuralFeature TrackElement_coordinate = VehiclePackage.theInstance.getTrackElement_coordinate();
		/**
		 * @generated
		 * @return feature descriptor elements in type Track
		 */
		 UStructuralFeature Track_elements = VehiclePackage.theInstance.getTrack_elements();
		/**
		 * @generated
		 * @return feature descriptor time in type TrackElement
		 */
		 UStructuralFeature TrackElement_time = VehiclePackage.theInstance.getTrackElement_time();
		/**
		 * @generated
		 * @return feature descriptor tracks in type TrackCharacteristic
		 */
		 UStructuralFeature TrackCharacteristic_tracks = VehiclePackage.theInstance.getTrackCharacteristic_tracks();
		/**
		 * @generated
		 * @return feature descriptor course in type TrackElement
		 */
		 UStructuralFeature TrackElement_course = VehiclePackage.theInstance.getTrackElement_course();
		/**
		 * @generated
		 * @return feature descriptor speed in type TrackElement
		 */
		 UStructuralFeature TrackElement_speed = VehiclePackage.theInstance.getTrackElement_speed();
		/**
		 * @generated
		 * @return feature descriptor source in type TrackElement
		 */
		 UStructuralFeature TrackElement_source = VehiclePackage.theInstance.getTrackElement_source();
		
		
	}

	/**
	* @generated
	*/
	private UClass mTrajectorySegment = null;
	/**
	* @generated
	*/
	private UClass mTrajectory = null;
	/**
	* @generated
	*/
	private UClass mTrackElement = null;
	/**
	* @generated
	*/
	private UClass mTrack = null;
	/**
	* @generated
	*/
	private UClass mTrackCharacteristic = null; 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mVehicle = null;
	/**
	* @generated
	*/
	private UClass mLandcraft = null;
	/**
	* @generated
	*/
	private UClass mWatercraft = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	/**
	 * @generated
	 */
	private UStructuralFeature mTrajectorySegment_id = null;



	/**
	 * @generated
	 */
	public static VehiclePackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package VehiclePackage ...", 1);
		theInstance = new VehiclePackage();
		//initialize referenced models
		PhysicsModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.application.vehicle");
		p.getContent().add(theInstance.mTrajectorySegment);
		p.getContent().add(theInstance.mTrajectory);
		p.getContent().add(theInstance.mTrackElement);
		p.getContent().add(theInstance.mTrack);
		p.getContent().add(theInstance.mTrackCharacteristic);
		p.getContent().add(theInstance.mVehicle);
		p.getContent().add(theInstance.mLandcraft);
		p.getContent().add(theInstance.mWatercraft);
		p.freeze();
		
		
		
		ULog.debug(-1, "... package VehiclePackage initialized");
		
		return theInstance;
	}
	/**
	 * @generated
	 */
	private UStructuralFeature mTrajectorySegment_destination = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrajectorySegment_name = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrajectorySegment_speed = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrajectorySegment_allowedPortCTE = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrajectory_segments = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrajectory_name = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrajectorySegment_origin = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrajectory_source = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackElement_time = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackElement_coordinate = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrajectorySegment_allowedStarboardCTE = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackElement_heading = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackElement_course = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackElement_speed = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrack_elements = null;

	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mTrajectorySegment = UMetaBuilder.manual().createClass("TrajectorySegment", false, TrajectorySegment.class, TrajectorySegmentImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mTrajectorySegment, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new TrajectorySegmentImpl();
				}
			});
			//Annotations for TrajectorySegment
			mTrajectorySegment.createAnnotation("struct");
		
		mTrajectory = UMetaBuilder.manual().createClass("Trajectory", false, Trajectory.class, TrajectoryImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mTrajectory, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new TrajectoryImpl();
				}
			});
			mTrajectory.setDocumentation("\r\n * A Trajectory defines the planned path of the vehicle. \r\n * The difference between a trajectory and a route is a time behaviour and maybe some additional meta data. \r\n * In this case the trajectory is build up from different segments with an desired speed, which represents the time behaviour.<br> \r\n * \r\n ");
			//Annotations for Trajectory
			mTrajectory.createAnnotation("struct");
		
		mTrackElement = UMetaBuilder.manual().createClass("TrackElement", false, TrackElement.class, TrackElementImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mTrackElement, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new TrackElementImpl();
				}
			});
			mTrackElement.setDocumentation("\r\n * One track point\r\n * A track usually is a set of positions (with additional information) that have been seen in the past\r\n  ");
			//Annotations for TrackElement
			mTrackElement.createAnnotation("struct");
		
		mTrack = UMetaBuilder.manual().createClass("Track", false, Track.class, TrackImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mTrack, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new TrackImpl();
				}
			});
		
		mTrackCharacteristic = UMetaBuilder.manual().createClass("TrackCharacteristic", false, TrackCharacteristic.class, TrackCharacteristicImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mTrackCharacteristic, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new TrackCharacteristicImpl();
				}
			});
		
		mVehicle = UMetaBuilder.manual().createClass("Vehicle", false, Vehicle.class, VehicleImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mVehicle, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new VehicleImpl();
				}
			});
		
		mLandcraft = UMetaBuilder.manual().createClass("Landcraft", false, Landcraft.class, LandcraftImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mLandcraft, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new LandcraftImpl();
				}
			});
			mLandcraft.setDocumentation("\r\n * LandTransport includes all vehicles regarding rail, road and off-road transport\r\n ");
		
		mWatercraft = UMetaBuilder.manual().createClass("Watercraft", false, Watercraft.class, WatercraftImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mWatercraft, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new WatercraftImpl();
				}
			});
			mWatercraft.setDocumentation("\r\n * A watercraft, such as a barge, boat, ship or sailboat, successfully passes over a \r\n * body of water, such as a sea, ocean, lake, canal or river.\r\n ");
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of TrajectorySegment
			mTrajectorySegment_name = UMetaBuilder.manual().createFeature("name", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrajectorySegment_name, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrajectorySegment)instance).getName(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrajectorySegment)instance).setName((String)value); } }
				);
			mTrajectorySegment_id = UMetaBuilder.manual().createFeature("id", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrajectorySegment_id, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrajectorySegment)instance).getId(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrajectorySegment)instance).setId((int)value); } }
				);
			mTrajectorySegment_origin = UMetaBuilder.manual().createFeature("origin", SpatialPackage.theInstance.getCoordinate(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrajectorySegment_origin, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrajectorySegment)instance).getOrigin(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrajectorySegment)instance).setOrigin((Coordinate)value); } }
				);
			mTrajectorySegment_destination = UMetaBuilder.manual().createFeature("destination", SpatialPackage.theInstance.getCoordinate(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrajectorySegment_destination, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrajectorySegment)instance).getDestination(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrajectorySegment)instance).setDestination((Coordinate)value); } }
				);
			mTrajectorySegment_speed = UMetaBuilder.manual().createFeature("speed", UnitsPackage.theInstance.getSpeed(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrajectorySegment_speed, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrajectorySegment)instance).getSpeed(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrajectorySegment)instance).setSpeed((Speed)value); } }
				);
			mTrajectorySegment_allowedPortCTE = UMetaBuilder.manual().createFeature("allowedPortCTE", UnitsPackage.theInstance.getDistance(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrajectorySegment_allowedPortCTE, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrajectorySegment)instance).getAllowedPortCTE(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrajectorySegment)instance).setAllowedPortCTE((Distance)value); } }
				);
			mTrajectorySegment_allowedStarboardCTE = UMetaBuilder.manual().createFeature("allowedStarboardCTE", UnitsPackage.theInstance.getDistance(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrajectorySegment_allowedStarboardCTE, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrajectorySegment)instance).getAllowedStarboardCTE(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrajectorySegment)instance).setAllowedStarboardCTE((Distance)value); } }
				);
			
			//Features of Trajectory
			mTrajectory_name = UMetaBuilder.manual().createFeature("name", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrajectory_name, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Trajectory)instance).getName(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Trajectory)instance).setName((String)value); } }
				);
				mTrajectory_name.setDocumentation(" Optional: name of the Trajectory, may be used to identify the Trajectory ");
			mTrajectory_segments = UMetaBuilder.manual().createFeature("segments", VehiclePackage.theInstance.getTrajectorySegment(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mTrajectory_segments, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Trajectory)instance).getSegments(); } }, 
						null
				);
			mTrajectory_source = UMetaBuilder.manual().createFeature("source", CorePackage.theInstance.getModelReference(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrajectory_source, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Trajectory)instance).getSource(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Trajectory)instance).setSource((ModelReference)value); } }
				);
				mTrajectory_source.setDocumentation(" Reference to an element that has been used to create this trajectory. This could be anything but in general this should be some kind of Route ");
			
			//Features of TrackElement
			mTrackElement_time = UMetaBuilder.manual().createFeature("time", UnitsPackage.theInstance.getTime(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackElement_time, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrackElement)instance).getTime(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrackElement)instance).setTime((Time)value); } }
				);
				mTrackElement_time.setDocumentation(" Time of this measurement ");
			mTrackElement_coordinate = UMetaBuilder.manual().createFeature("coordinate", SpatialPackage.theInstance.getCoordinate(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackElement_coordinate, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrackElement)instance).getCoordinate(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrackElement)instance).setCoordinate((Coordinate)value); } }
				);
				mTrackElement_coordinate.setDocumentation(" Coordiante of the object at the given time ");
			mTrackElement_heading = UMetaBuilder.manual().createFeature("heading", UnitsPackage.theInstance.getAngle(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackElement_heading, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrackElement)instance).getHeading(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrackElement)instance).setHeading((Angle)value); } }
				);
				mTrackElement_heading.setDocumentation(" orientation of the object at the given time ");
			mTrackElement_course = UMetaBuilder.manual().createFeature("course", UnitsPackage.theInstance.getAngle(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackElement_course, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrackElement)instance).getCourse(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrackElement)instance).setCourse((Angle)value); } }
				);
				mTrackElement_course.setDocumentation(" course / direction of movement, that may differ from the orientation, of the object at the given time ");
			mTrackElement_speed = UMetaBuilder.manual().createFeature("speed", UnitsPackage.theInstance.getSpeed(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackElement_speed, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrackElement)instance).getSpeed(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrackElement)instance).setSpeed((Speed)value); } }
				);
				mTrackElement_speed.setDocumentation(" magnitude of movement in direction of course ");
			mTrackElement_source = UMetaBuilder.manual().createFeature("source", TypeUtils.getPrimitiveType(Object.class), UAssociationType.AGGREGATION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackElement_source, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrackElement)instance).getSource(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrackElement)instance).setSource((Object)value); } }
				);
				mTrackElement_source.setDocumentation(" an optional reference to the receiver / generator of this track element");
			
			//Features of Track
			mTrack_elements = UMetaBuilder.manual().createFeature("elements", VehiclePackage.theInstance.getTrackElement(), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mTrack_elements, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Track)instance).getElements(); } }, 
						null
				);
				mTrack_elements.setDocumentation(" ordered list of elements  \r\n\t\t\t\t\t\t\t ");
			
			//Features of TrackCharacteristic
			mTrackCharacteristic_tracks = UMetaBuilder.manual().createFeature("tracks", VehiclePackage.theInstance.getTrack(), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackCharacteristic_tracks, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrackCharacteristic)instance).getTracks(); } }, 
						null
				);
			
		}
		{ //assign features
			mTrajectorySegment.getStructuralFeatures().add(mTrajectorySegment_name);
			mTrajectorySegment.getStructuralFeatures().add(mTrajectorySegment_id);
			mTrajectorySegment.getStructuralFeatures().add(mTrajectorySegment_origin);
			mTrajectorySegment.getStructuralFeatures().add(mTrajectorySegment_destination);
			mTrajectorySegment.getStructuralFeatures().add(mTrajectorySegment_speed);
			mTrajectorySegment.getStructuralFeatures().add(mTrajectorySegment_allowedPortCTE);
			mTrajectorySegment.getStructuralFeatures().add(mTrajectorySegment_allowedStarboardCTE);
			mTrajectory.getStructuralFeatures().add(mTrajectory_name);
			mTrajectory.getStructuralFeatures().add(mTrajectory_segments);
			mTrajectory.getStructuralFeatures().add(mTrajectory_source);
			mTrackElement.getStructuralFeatures().add(mTrackElement_time);
			mTrackElement.getStructuralFeatures().add(mTrackElement_coordinate);
			mTrackElement.getStructuralFeatures().add(mTrackElement_heading);
			mTrackElement.getStructuralFeatures().add(mTrackElement_course);
			mTrackElement.getStructuralFeatures().add(mTrackElement_speed);
			mTrackElement.getStructuralFeatures().add(mTrackElement_source);
			mTrack.getStructuralFeatures().add(mTrack_elements);
			mTrackCharacteristic.getStructuralFeatures().add(mTrackCharacteristic_tracks);
		}
		
	}
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackCharacteristic_tracks = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackElement_source = null;



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
		{		//Operations of TrajectorySegment
			UOperation operation = null;
			//operation : getDistance(Distance, Coordinate)
			operation = UMetaBuilder.manual().createOperation("getDistance", false, UnitsPackage.theInstance.getDistance(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((TrajectorySegment)instance).getDistance((Coordinate)parameter[0]);
				}
			});
				operation.setDocumentation(" returns the smallest distance from the given coodinate to the line segment ");
				//Annotations for TrajectorySegment:getDistance(Distance, Coordinate)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "loc", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mTrajectorySegment.getOperations().add(operation);
			//operation : getOrientation(Angle)
			operation = UMetaBuilder.manual().createOperation("getOrientation", false, UnitsPackage.theInstance.getAngle(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((TrajectorySegment)instance).getOrientation();
				}
			});
				operation.setDocumentation(" returns the orientation of the vector from origin -> destination as angle against north.  ");
				//Annotations for TrajectorySegment:getOrientation(Angle)
				operation.createAnnotation("const");
				mTrajectorySegment.getOperations().add(operation);
		}
		{		//Operations of Trajectory
			UOperation operation = null;
			//operation : getNearestSegment(TrajectorySegment, Coordinate, Orientation)
			operation = UMetaBuilder.manual().createOperation("getNearestSegment", false, VehiclePackage.theInstance.getTrajectorySegment(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Trajectory)instance).getNearestSegment((Coordinate)parameter[0], (Orientation)parameter[1]);
				}
			});
				operation.setDocumentation(" returns the next valid segment  for this trajectory");
				//Annotations for Trajectory:getNearestSegment(TrajectorySegment, Coordinate, Orientation)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "location", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "orientation", UnitsPackage.theInstance.getOrientation(), 0, 1, UDirectionType.IN);
				mTrajectory.getOperations().add(operation);
		}
		{		//Operations of Track
			UOperation operation = null;
			//operation : first(Time)
			operation = UMetaBuilder.manual().createOperation("first", false, UnitsPackage.theInstance.getTime(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Track)instance).first();
				}
			});
				operation.setDocumentation(" returns the time of the first track point ");
				//Annotations for Track:first(Time)
				operation.createAnnotation("const");
				mTrack.getOperations().add(operation);
			//operation : last(Time)
			operation = UMetaBuilder.manual().createOperation("last", false, UnitsPackage.theInstance.getTime(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Track)instance).last();
				}
			});
				operation.setDocumentation(" returns the time of the last track point ");
				//Annotations for Track:last(Time)
				operation.createAnnotation("const");
				mTrack.getOperations().add(operation);
			//operation : getPositionAt(TrackElement, Time)
			operation = UMetaBuilder.manual().createOperation("getPositionAt", false, VehiclePackage.theInstance.getTrackElement(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Track)instance).getPositionAt((Time)parameter[0]);
				}
			});
				operation.setDocumentation("\r\n * returns a new track point for the given time. If there is no measurement available for this time point\r\n * the method will (linear) interpolate / may extrapolate the values\r\n * @return null, if elements is empty\r\n ");
				UMetaBuilder.manual().addParameter(operation, "p", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mTrack.getOperations().add(operation);
		}
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mTrackCharacteristic.setSuperType(PhysicsPackage.theInstance.getCharacteristic());
		mVehicle.setSuperType(PhysicsPackage.theInstance.getPhysicalObject());
		mLandcraft.setSuperType(VehiclePackage.theInstance.getVehicle());
		mWatercraft.setSuperType(VehiclePackage.theInstance.getVehicle());
		
	}



	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getVehicle(){
		if (mVehicle == null){
			mVehicle = UCoreMetaRepository.getUClass(Vehicle.class);
		}
		return mVehicle;
	}
	/**
	* @generated
	*/
	public UClass getLandcraft(){
		if (mLandcraft == null){
			mLandcraft = UCoreMetaRepository.getUClass(Landcraft.class);
		}
		return mLandcraft;
	}



	/**
	* @generated
	*/
	public UClass getWatercraft(){
		if (mWatercraft == null){
			mWatercraft = UCoreMetaRepository.getUClass(Watercraft.class);
		}
		return mWatercraft;
	}



	/**
	* @generated
	*/
	public UClass getTrackElement(){
		if (mTrackElement == null){
			mTrackElement = UCoreMetaRepository.getUClass(TrackElement.class);
		}
		return mTrackElement;
	}



	/**
	* @generated
	*/
	public UClass getTrack(){
		if (mTrack == null){
			mTrack = UCoreMetaRepository.getUClass(Track.class);
		}
		return mTrack;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrajectorySegment_allowedPortCTE(){
		if (mTrajectorySegment_allowedPortCTE == null)
			mTrajectorySegment_allowedPortCTE = getTrajectorySegment().getFeature("allowedPortCTE");
		return mTrajectorySegment_allowedPortCTE;
	}



	/**
	* @generated
	*/
	public UClass getTrajectorySegment(){
		if (mTrajectorySegment == null){
			mTrajectorySegment = UCoreMetaRepository.getUClass(TrajectorySegment.class);
		}
		return mTrajectorySegment;
	}



	/**
	* @generated
	*/
	public UClass getTrajectory(){
		if (mTrajectory == null){
			mTrajectory = UCoreMetaRepository.getUClass(Trajectory.class);
		}
		return mTrajectory;
	}



	/**
	* @generated
	*/
	public UClass getTrackCharacteristic(){
		if (mTrackCharacteristic == null){
			mTrackCharacteristic = UCoreMetaRepository.getUClass(TrackCharacteristic.class);
		}
		return mTrackCharacteristic;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrajectorySegment_destination(){
		if (mTrajectorySegment_destination == null)
			mTrajectorySegment_destination = getTrajectorySegment().getFeature("destination");
		return mTrajectorySegment_destination;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrajectorySegment_id(){
		if (mTrajectorySegment_id == null)
			mTrajectorySegment_id = getTrajectorySegment().getFeature("id");
		return mTrajectorySegment_id;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrajectorySegment_name(){
		if (mTrajectorySegment_name == null)
			mTrajectorySegment_name = getTrajectorySegment().getFeature("name");
		return mTrajectorySegment_name;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrajectorySegment_origin(){
		if (mTrajectorySegment_origin == null)
			mTrajectorySegment_origin = getTrajectorySegment().getFeature("origin");
		return mTrajectorySegment_origin;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrajectorySegment_speed(){
		if (mTrajectorySegment_speed == null)
			mTrajectorySegment_speed = getTrajectorySegment().getFeature("speed");
		return mTrajectorySegment_speed;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrajectorySegment_allowedStarboardCTE(){
		if (mTrajectorySegment_allowedStarboardCTE == null)
			mTrajectorySegment_allowedStarboardCTE = getTrajectorySegment().getFeature("allowedStarboardCTE");
		return mTrajectorySegment_allowedStarboardCTE;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrajectory_segments(){
		if (mTrajectory_segments == null)
			mTrajectory_segments = getTrajectory().getFeature("segments");
		return mTrajectory_segments;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrajectory_name(){
		if (mTrajectory_name == null)
			mTrajectory_name = getTrajectory().getFeature("name");
		return mTrajectory_name;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrajectory_source(){
		if (mTrajectory_source == null)
			mTrajectory_source = getTrajectory().getFeature("source");
		return mTrajectory_source;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrackCharacteristic_tracks(){
		if (mTrackCharacteristic_tracks == null)
			mTrackCharacteristic_tracks = getTrackCharacteristic().getFeature("tracks");
		return mTrackCharacteristic_tracks;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrackElement_course(){
		if (mTrackElement_course == null)
			mTrackElement_course = getTrackElement().getFeature("course");
		return mTrackElement_course;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrackElement_coordinate(){
		if (mTrackElement_coordinate == null)
			mTrackElement_coordinate = getTrackElement().getFeature("coordinate");
		return mTrackElement_coordinate;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrackElement_heading(){
		if (mTrackElement_heading == null)
			mTrackElement_heading = getTrackElement().getFeature("heading");
		return mTrackElement_heading;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrackElement_source(){
		if (mTrackElement_source == null)
			mTrackElement_source = getTrackElement().getFeature("source");
		return mTrackElement_source;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrackElement_time(){
		if (mTrackElement_time == null)
			mTrackElement_time = getTrackElement().getFeature("time");
		return mTrackElement_time;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrack_elements(){
		if (mTrack_elements == null)
			mTrack_elements = getTrack().getFeature("elements");
		return mTrack_elements;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTrackElement_speed(){
		if (mTrackElement_speed == null)
			mTrackElement_speed = getTrackElement().getFeature("speed");
		return mTrackElement_speed;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
}
