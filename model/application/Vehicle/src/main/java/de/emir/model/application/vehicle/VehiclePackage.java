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
import de.emir.model.application.vehicle.Trajectory;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.model.application.vehicle.Landcraft;
import de.emir.model.application.vehicle.Vehicle;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.model.application.vehicle.impl.TrajectoryImpl;
import de.emir.model.application.vehicle.impl.TrajectorySegmentImpl;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
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
		
	}

	/**
	* @generated
	*/
	private UClass mTrajectorySegment = null;
	/**
	* @generated
	*/
	private UClass mTrajectory = null;
	
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
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
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

	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
}
