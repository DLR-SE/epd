package de.emir.model.application.track;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.tuml.ucore.runtime.UObject;

import de.emir.model.application.track.Track;
import de.emir.model.application.track.TrackCharacteristic;
import de.emir.model.application.track.TrackPackage;
import de.emir.model.application.track.TrackPoint;
import de.emir.model.application.track.impl.TrackCharacteristicImpl;
import de.emir.model.application.track.impl.TrackImpl;
import de.emir.model.application.track.impl.TrackPointImpl;
import de.emir.model.universal.PhysicsModel;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;

/**
 *	@generated 
 */
public class TrackPackage  
{
	
	/**
	 * @generated
	 */
	public static TrackPackage theInstance = new TrackPackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier TrackPoint
		*/
		UClass TrackPoint = TrackPackage.theInstance.getTrackPoint();
		/**
		* @generated
		* @return meta type for classifier Track
		*/
		UClass Track = TrackPackage.theInstance.getTrack();
		/**
		* @generated
		* @return meta type for classifier TrackCharacteristic
		*/
		UClass TrackCharacteristic = TrackPackage.theInstance.getTrackCharacteristic();
		
		/**
		 * @generated
		 * @return feature descriptor time in type TrackPoint
		 */
		 UStructuralFeature TrackPoint_time = TrackPackage.theInstance.getTrackPoint_time();
		/**
		 * @generated
		 * @return feature descriptor coordinate in type TrackPoint
		 */
		 UStructuralFeature TrackPoint_coordinate = TrackPackage.theInstance.getTrackPoint_coordinate();
		/**
		 * @generated
		 * @return feature descriptor heading in type TrackPoint
		 */
		 UStructuralFeature TrackPoint_heading = TrackPackage.theInstance.getTrackPoint_heading();
		/**
		 * @generated
		 * @return feature descriptor course in type TrackPoint
		 */
		 UStructuralFeature TrackPoint_course = TrackPackage.theInstance.getTrackPoint_course();
		/**
		 * @generated
		 * @return feature descriptor speed in type TrackPoint
		 */
		 UStructuralFeature TrackPoint_speed = TrackPackage.theInstance.getTrackPoint_speed();
		/**
		 * @generated
		 * @return feature descriptor source in type TrackPoint
		 */
		 UStructuralFeature TrackPoint_source = TrackPackage.theInstance.getTrackPoint_source();
		/**
		 * @generated
		 * @return feature descriptor elements in type Track
		 */
		 UStructuralFeature Track_elements = TrackPackage.theInstance.getTrack_elements();
		/**
		 * @generated
		 * @return feature descriptor maxTrackPoints in type TrackCharacteristic
		 */
		 UStructuralFeature TrackCharacteristic_maxTrackPoints = TrackPackage.theInstance.getTrackCharacteristic_maxTrackPoints();
		/**
		 * @generated
		 * @return feature descriptor track in type TrackCharacteristic
		 */
		 UStructuralFeature TrackCharacteristic_track = TrackPackage.theInstance.getTrackCharacteristic_track();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mTrackPoint = null;
	/**
	* @generated
	*/
	private UClass mTrack = null;
	/**
	* @generated
	*/
	private UClass mTrackCharacteristic = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	//Features for classifier TrackPoint
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackPoint_time = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackPoint_coordinate = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackPoint_heading = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackPoint_course = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackPoint_speed = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackPoint_source = null;
	
	//Features for classifier Track
	/**
	 * @generated
	 */
	private UStructuralFeature mTrack_elements = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackCharacteristic_maxTrackPoints = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTrackCharacteristic_track = null;
	
	/**
	 * @generated
	 */
	public static TrackPackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package TrackPackage ...");
		theInstance = new TrackPackage();
		//initialize referenced models
		PhysicsModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.application.track");
		p.getContent().add(theInstance.mTrackPoint);
		p.getContent().add(theInstance.mTrack);
		p.getContent().add(theInstance.mTrackCharacteristic);
		p.freeze();
		
		
		
		ULog.debug("... package TrackPackage initialized");
		
		return theInstance;
	}
	
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mTrackPoint = UMetaBuilder.manual().createClass("TrackPoint", false, TrackPoint.class, TrackPointImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mTrackPoint, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new TrackPointImpl();
				}
			});
			mTrackPoint.setDocumentation("\r\n * One track point\r\n * A track usually is a set of positions (with additional information) that have been seen in the past\r\n  ");
			//Annotations for TrackPoint
			mTrackPoint.createAnnotation("struct");
		
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
		
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of TrackPoint
			mTrackPoint_time = UMetaBuilder.manual().createFeature("time", UnitsPackage.theInstance.getTime(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackPoint_time, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrackPoint)instance).getTime(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrackPoint)instance).setTime((Time)value); } }
				);
				mTrackPoint_time.setDocumentation(" Time of this measurement ");
			mTrackPoint_coordinate = UMetaBuilder.manual().createFeature("coordinate", SpatialPackage.theInstance.getCoordinate(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackPoint_coordinate, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrackPoint)instance).getCoordinate(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrackPoint)instance).setCoordinate((Coordinate)value); } }
				);
				mTrackPoint_coordinate.setDocumentation(" Coordiante of the object at the given time ");
			mTrackPoint_heading = UMetaBuilder.manual().createFeature("heading", UnitsPackage.theInstance.getAngle(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackPoint_heading, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrackPoint)instance).getHeading(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrackPoint)instance).setHeading((Angle)value); } }
				);
				mTrackPoint_heading.setDocumentation(" orientation of the object at the given time ");
			mTrackPoint_course = UMetaBuilder.manual().createFeature("course", UnitsPackage.theInstance.getAngle(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackPoint_course, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrackPoint)instance).getCourse(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrackPoint)instance).setCourse((Angle)value); } }
				);
				mTrackPoint_course.setDocumentation(" course / direction of movement, that may differ from the orientation, of the object at the given time ");
			mTrackPoint_speed = UMetaBuilder.manual().createFeature("speed", UnitsPackage.theInstance.getSpeed(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackPoint_speed, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrackPoint)instance).getSpeed(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrackPoint)instance).setSpeed((Speed)value); } }
				);
				mTrackPoint_speed.setDocumentation(" magnitude of movement in direction of course ");
			mTrackPoint_source = UMetaBuilder.manual().createFeature("source", RuntimePackage.theInstance.getUObject(), UAssociationType.AGGREGATION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackPoint_source, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrackPoint)instance).getSource(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrackPoint)instance).setSource((UObject)value); } }
				);
				mTrackPoint_source.setDocumentation(" an optional reference to the receiver / generator of this track element");
			
			//Features of Track
			mTrack_elements = UMetaBuilder.manual().createFeature("elements", TrackPackage.theInstance.getTrackPoint(), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mTrack_elements, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Track)instance).getElements(); } }, 
						null
				);
				mTrack_elements.setDocumentation(" ordered list of elements  \r\n\t\t\t\t\t\t\t ");
			
			//Features of TrackCharacteristic
			mTrackCharacteristic_maxTrackPoints = UMetaBuilder.manual().createFeature("maxTrackPoints", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackCharacteristic_maxTrackPoints, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrackCharacteristic)instance).getMaxTrackPoints(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrackCharacteristic)instance).setMaxTrackPoints((int)value); } }
				);
				mTrackCharacteristic_maxTrackPoints.setDocumentation(" maximum number of track points, if the limit is exceeded the oldest track point will be automatically removed. \r\n\t\t\t\t\t\t\t * if maxTrackPoints is set to < 0 no points will be deleted ");
			mTrackCharacteristic_track = UMetaBuilder.manual().createFeature("track", TrackPackage.theInstance.getTrack(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTrackCharacteristic_track, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TrackCharacteristic)instance).getTrack(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TrackCharacteristic)instance).setTrack((Track)value); } }
				);
			
		}
		{ //assign features
			mTrackPoint.getStructuralFeatures().add(mTrackPoint_time);
			mTrackPoint.getStructuralFeatures().add(mTrackPoint_coordinate);
			mTrackPoint.getStructuralFeatures().add(mTrackPoint_heading);
			mTrackPoint.getStructuralFeatures().add(mTrackPoint_course);
			mTrackPoint.getStructuralFeatures().add(mTrackPoint_speed);
			mTrackPoint.getStructuralFeatures().add(mTrackPoint_source);
			mTrack.getStructuralFeatures().add(mTrack_elements);
			mTrackCharacteristic.getStructuralFeatures().add(mTrackCharacteristic_maxTrackPoints);
			mTrackCharacteristic.getStructuralFeatures().add(mTrackCharacteristic_track);
		}
		
	}

	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
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
			//operation : getPositionAt(TrackPoint, Time)
			operation = UMetaBuilder.manual().createOperation("getPositionAt", false, TrackPackage.theInstance.getTrackPoint(), 0, 1, new IOperationInvoker() {				
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
		
	}
	
	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getTrackPoint(){
		if (mTrackPoint == null){
			mTrackPoint = UCoreMetaRepository.getUClass(TrackPoint.class);
		}
		return mTrackPoint;
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
	public UClass getTrackCharacteristic(){
		if (mTrackCharacteristic == null){
			mTrackCharacteristic = UCoreMetaRepository.getUClass(TrackCharacteristic.class);
		}
		return mTrackCharacteristic;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getTrackPoint_time(){
		if (mTrackPoint_time == null)
			mTrackPoint_time = getTrackPoint().getFeature("time");
		return mTrackPoint_time;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTrackPoint_coordinate(){
		if (mTrackPoint_coordinate == null)
			mTrackPoint_coordinate = getTrackPoint().getFeature("coordinate");
		return mTrackPoint_coordinate;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTrackPoint_heading(){
		if (mTrackPoint_heading == null)
			mTrackPoint_heading = getTrackPoint().getFeature("heading");
		return mTrackPoint_heading;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTrackPoint_course(){
		if (mTrackPoint_course == null)
			mTrackPoint_course = getTrackPoint().getFeature("course");
		return mTrackPoint_course;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTrackPoint_speed(){
		if (mTrackPoint_speed == null)
			mTrackPoint_speed = getTrackPoint().getFeature("speed");
		return mTrackPoint_speed;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTrackPoint_source(){
		if (mTrackPoint_source == null)
			mTrackPoint_source = getTrackPoint().getFeature("source");
		return mTrackPoint_source;
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
	public UStructuralFeature getTrackCharacteristic_maxTrackPoints(){
		if (mTrackCharacteristic_maxTrackPoints == null)
			mTrackCharacteristic_maxTrackPoints = getTrackCharacteristic().getFeature("maxTrackPoints");
		return mTrackCharacteristic_maxTrackPoints;
	}

	/**
	* @generated
	*/
	public UStructuralFeature getTrackCharacteristic_track(){
		if (mTrackCharacteristic_track == null)
			mTrackCharacteristic_track = getTrackCharacteristic().getFeature("track");
		return mTrackCharacteristic_track;
	}
}
