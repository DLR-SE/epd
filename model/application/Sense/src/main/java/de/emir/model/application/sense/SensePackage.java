package de.emir.model.application.sense;

import de.emir.model.application.sense.impl.EmitterImpl;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.model.application.sense.impl.ErrorModelImpl;
import de.emir.model.application.sense.impl.MeasurementImpl;
import de.emir.model.application.sense.impl.ObservationImpl;
import de.emir.model.application.sense.impl.SensorImpl;
import de.emir.model.application.sense.impl.SensorPortImpl;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.model.universal.CoreModel;
import de.emir.model.universal.PhysicsModel;
import de.emir.model.universal.SpatialModel;
import de.emir.model.universal.UnitsModel;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.application.sense.Emitter;
import de.emir.model.universal.units.Time;
import de.emir.model.application.sense.Measurement;
import de.emir.model.application.sense.ErrorModel;
import de.emir.model.application.sense.Observation;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.application.sense.SensorPort;
import de.emir.tuml.ucore.UCoreModel;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.application.sense.Sensor;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;
import java.util.List;

/**
 *	@generated 
 */
public class SensePackage  
{
	
	/**
	 * @generated
	 */
	public static SensePackage theInstance = new SensePackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier Measurement
		*/
		UClass Measurement = SensePackage.theInstance.getMeasurement();
		/**
		* @generated
		* @return meta type for classifier Observation
		*/
		UClass Observation = SensePackage.theInstance.getObservation();
		/**
		* @generated
		* @return meta type for classifier SensorPort
		*/
		UClass SensorPort = SensePackage.theInstance.getSensorPort();
		/**
		* @generated
		* @return meta type for classifier Sensor
		*/
		UClass Sensor = SensePackage.theInstance.getSensor();
		/**
		* @generated
		* @return meta type for classifier Emitter
		*/
		UClass Emitter = SensePackage.theInstance.getEmitter();
		/**
		* @generated
		* @return meta type for classifier ErrorModel
		*/
		UClass ErrorModel = SensePackage.theInstance.getErrorModel();
		/**
		 * @generated
		 * @return feature descriptor sender in type Measurement
		 */
		 UStructuralFeature Measurement_sender = SensePackage.theInstance.getMeasurement_sender();
		
		/**
		 * @generated
		 * @return feature descriptor timestamp in type Measurement
		 */
		 UStructuralFeature Measurement_timestamp = SensePackage.theInstance.getMeasurement_timestamp();
		/**
		 * @generated
		 * @return feature descriptor measurements in type Observation
		 */
		 UStructuralFeature Observation_measurements = SensePackage.theInstance.getObservation_measurements();
		/**
		 * @generated
		 * @return feature descriptor observedRegion in type SensorPort
		 */
		 UStructuralFeature SensorPort_observedRegion = SensePackage.theInstance.getSensorPort_observedRegion();
		/**
		 * @generated
		 * @return feature descriptor timestamp in type Observation
		 */
		 UStructuralFeature Observation_timestamp = SensePackage.theInstance.getObservation_timestamp();
		/**
		 * @generated
		 * @return feature descriptor errorModel in type SensorPort
		 */
		 UStructuralFeature SensorPort_errorModel = SensePackage.theInstance.getSensorPort_errorModel();
		/**
		 * @generated
		 * @return feature descriptor active in type Sensor
		 */
		 UStructuralFeature Sensor_active = SensePackage.theInstance.getSensor_active();
		/**
		 * @generated
		 * @return feature descriptor frequency in type Sensor
		 */
		 UStructuralFeature Sensor_frequency = SensePackage.theInstance.getSensor_frequency();
		/**
		 * @generated
		 * @return feature descriptor ports in type Sensor
		 */
		 UStructuralFeature Sensor_ports = SensePackage.theInstance.getSensor_ports();
		/**
		 * @generated
		 * @return feature descriptor areaOfInfluence in type Emitter
		 */
		 UStructuralFeature Emitter_areaOfInfluence = SensePackage.theInstance.getEmitter_areaOfInfluence();
		/**
		 * @generated
		 * @return feature descriptor observationType in type ErrorModel
		 */
		 UStructuralFeature ErrorModel_observationType = SensePackage.theInstance.getErrorModel_observationType();
		/**
		 * @generated
		 * @return feature descriptor pointers in type ErrorModel
		 */
		 UStructuralFeature ErrorModel_pointers = SensePackage.theInstance.getErrorModel_pointers();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mMeasurement = null;
	/**
	* @generated
	*/
	private UClass mObservation = null;
	/**
	* @generated
	*/
	private UClass mSensorPort = null;
	/**
	* @generated
	*/
	private UClass mSensor = null;
	/**
	* @generated
	*/
	private UClass mEmitter = null;
	/**
	* @generated
	*/
	private UClass mErrorModel = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMeasurement_sender = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	//Features for classifier Measurement
	/**
	 * @generated
	 */
	private UStructuralFeature mMeasurement_timestamp = null;
	
	/**
	 * @generated
	 */
	private UStructuralFeature mObservation_measurements = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mSensorPort_observedRegion = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mObservation_timestamp = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mSensorPort_errorModel = null;
	//Features for classifier Sensor
	/**
	 * @generated
	 */
	private UStructuralFeature mSensor_active = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mSensor_frequency = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mSensor_ports = null;
	
	//Features for classifier Emitter
	/**
	 * @generated
	 */
	private UStructuralFeature mEmitter_areaOfInfluence = null;
	
	//Features for classifier ErrorModel
	/**
	 * @generated
	 */
	private UStructuralFeature mErrorModel_observationType = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mErrorModel_pointers = null;
	
	/**
	 * @generated
	 */
	public static SensePackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package SensePackage ...");
		theInstance = new SensePackage();
		//initialize referenced models
		PhysicsModel.init();
		CoreModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.application.sense");
		p.getContent().add(theInstance.mMeasurement);
		p.getContent().add(theInstance.mObservation);
		p.getContent().add(theInstance.mSensorPort);
		p.getContent().add(theInstance.mSensor);
		p.getContent().add(theInstance.mEmitter);
		p.getContent().add(theInstance.mErrorModel);
		p.freeze();
		
		
		
		ULog.debug("... package SensePackage initialized");
		
		return theInstance;
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mMeasurement = UMetaBuilder.manual().createClass("Measurement", true, Measurement.class, MeasurementImpl.class);
			//Annotations for Measurement
			mMeasurement.createAnnotation("struct");
		
		mObservation = UMetaBuilder.manual().createClass("Observation", false, Observation.class, ObservationImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mObservation, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ObservationImpl();
				}
			});
			mObservation.setDocumentation("\r\n * Contains the observed phenomenon / measurement.\r\n * the associated meta informations (like observedRegion, FeatureOfInterest, ...) are stored within the referenced port\r\n ");
			//Annotations for Observation
			mObservation.createAnnotation("struct");
		
		mSensorPort = UMetaBuilder.manual().createClass("SensorPort", true, SensorPort.class, SensorPortImpl.class);
			mSensorPort.setDocumentation("\r\n * A sensor outputs a piece of information (an observed value), the value itself being represented by an ObservationValue.\r\n * [SSN] \r\n * \r\n * @note to observe the occurrence of new measurements, put a listener on the measurement Feature\r\n * @note the measurement feature is defined in specialized classifiers\r\n ");
			//Annotations for SensorPort
			mSensorPort.createAnnotationDetail("GMF", "alias", "Port");
		
		mSensor = UMetaBuilder.manual().createClass("Sensor", false, Sensor.class, SensorImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mSensor, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new SensorImpl();
				}
			});
			mSensor.setDocumentation("\r\n * A sensor can do (implements) sensing: that is, a sensor is any entity that can follow a sensing method and thus observe some Property of a FeatureOfInterest. Sensors may be physical devices, computational methods, a laboratory setup with a person following a method, or any other thing that can follow a Sensing Method to observe a Property.\r\n * [SSN]\r\n * \r\n ");
		
		mEmitter = UMetaBuilder.manual().createClass("Emitter", true, Emitter.class, EmitterImpl.class);
		
		mErrorModel = UMetaBuilder.manual().createClass("ErrorModel", true, ErrorModel.class, ErrorModelImpl.class);
			mErrorModel.setDocumentation("\r\n * \\defgroup SensorErrorModel\r\n * An error model describes the expected error for parts of the sensor Observation\r\n ");
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of Measurement
			mMeasurement_timestamp = UMetaBuilder.manual().createFeature("timestamp", UnitsPackage.theInstance.getTime(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMeasurement_timestamp, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Measurement)instance).getTimestamp(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Measurement)instance).setTimestamp((Time)value); } }
				);
				mMeasurement_timestamp.setDocumentation(" Time, when the measurement was created ");
			mMeasurement_sender = UMetaBuilder.manual().createFeature("sender", SensePackage.theInstance.getSensorPort(), UAssociationType.AGGREGATION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMeasurement_sender, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Measurement)instance).getSender(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Measurement)instance).setSender((SensorPort)value); } }
				);
			
			//Features of Observation
			mObservation_timestamp = UMetaBuilder.manual().createFeature("timestamp", UnitsPackage.theInstance.getTime(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mObservation_timestamp, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Observation)instance).getTimestamp(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Observation)instance).setTimestamp((Time)value); } }
				);
			mObservation_measurements = UMetaBuilder.manual().createFeature("measurements", SensePackage.theInstance.getMeasurement(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mObservation_measurements, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Observation)instance).getMeasurements(); } }, 
						null
				);
			
			//Features of SensorPort
			mSensorPort_observedRegion = UMetaBuilder.manual().createFeature("observedRegion", SpatialPackage.theInstance.getGeometry(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mSensorPort_observedRegion, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((SensorPort)instance).getObservedRegion(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((SensorPort)instance).setObservedRegion((Geometry)value); } }
				);
				mSensorPort_observedRegion.setDocumentation("\r\n * describes the region for which the observation is valid.\r\n * Is always given relative to the attribute pose of the associated sensor\r\n ");
			mSensorPort_errorModel = UMetaBuilder.manual().createFeature("errorModel", SensePackage.theInstance.getErrorModel(), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mSensorPort_errorModel, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((SensorPort)instance).getErrorModel(); } }, 
						null
				);
				mSensorPort_errorModel.setDocumentation("\r\n * optional: expected error models\r\n ");
			
			//Features of Sensor
			mSensor_active = UMetaBuilder.manual().createFeature("active", TypeUtils.getPrimitiveType(boolean.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mSensor_active, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Sensor)instance).getActive(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Sensor)instance).setActive((boolean)value); } }
				);
				mSensor_active.setDocumentation("\r\n * Only if the sensor is active, it will take measurements, with the \r\n * frequency defined with the attribute frequency\r\n ");
			mSensor_frequency = UMetaBuilder.manual().createFeature("frequency", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mSensor_frequency, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Sensor)instance).getFrequency(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Sensor)instance).setFrequency((int)value); } }
				);
				mSensor_frequency.setDocumentation("\r\n * The frequency in Hz, that is used to take measurements\r\n ");
			mSensor_ports = UMetaBuilder.manual().createFeature("ports", SensePackage.theInstance.getSensorPort(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mSensor_ports, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Sensor)instance).getPorts(); } }, 
						null
				);
				mSensor_ports.setDocumentation(" Ports (measurements) generated by this sensor \r\n * @note to observe the occurence of new measurements, put a listener to the port (@see Port)\r\n * ");
			
			//Features of Emitter
			mEmitter_areaOfInfluence = UMetaBuilder.manual().createFeature("areaOfInfluence", SpatialPackage.theInstance.getGeometry(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEmitter_areaOfInfluence, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Emitter)instance).getAreaOfInfluence(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Emitter)instance).setAreaOfInfluence((Geometry)value); } }
				);
				mEmitter_areaOfInfluence.setDocumentation(" Area / Range of the emitter. Only sensors within this area shall be capable of receiving the emitted value\r\n * if the areaOfInfluence is not defined, the emitted value is visible everywhere. \r\n ");
			
			//Features of ErrorModel
			mErrorModel_observationType = UMetaBuilder.manual().createFeature("observationType", RuntimePackage.theInstance.getUClassifier(), UAssociationType.AGGREGATION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mErrorModel_observationType, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ErrorModel)instance).getObservationType(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ErrorModel)instance).setObservationType((UClassifier)value); } }
				);
				mErrorModel_observationType.setDocumentation(" Expected type of observation ");
			mErrorModel_pointers = UMetaBuilder.manual().createFeature("pointers", UtilsPackage.theInstance.getPointer(), UAssociationType.AGGREGATION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mErrorModel_pointers, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ErrorModel)instance).getPointers(); } }, 
						null
				);
				mErrorModel_pointers.setDocumentation(" Pointer to features, that will be affected by this error model\r\n * each pointer should be positioned, relative to the observation that must inherit the observationType\r\n ");
			
		}
		{ //assign features
			mMeasurement.getStructuralFeatures().add(mMeasurement_timestamp);
			mMeasurement.getStructuralFeatures().add(mMeasurement_sender);
			mObservation.getStructuralFeatures().add(mObservation_timestamp);
			mObservation.getStructuralFeatures().add(mObservation_measurements);
			mSensorPort.getStructuralFeatures().add(mSensorPort_observedRegion);
			mSensorPort.getStructuralFeatures().add(mSensorPort_errorModel);
			mSensor.getStructuralFeatures().add(mSensor_active);
			mSensor.getStructuralFeatures().add(mSensor_frequency);
			mSensor.getStructuralFeatures().add(mSensor_ports);
			mEmitter.getStructuralFeatures().add(mEmitter_areaOfInfluence);
			mErrorModel.getStructuralFeatures().add(mErrorModel_observationType);
			mErrorModel.getStructuralFeatures().add(mErrorModel_pointers);
		}
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
		{		//Operations of SensorPort
			UOperation operation = null;
			//operation : setCurrentMeasurement(boolean, Measurement)
			operation = UMetaBuilder.manual().createOperation("setCurrentMeasurement", true, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((SensorPort)instance).setCurrentMeasurement((Measurement)parameter[0]);
				}
			});
				operation.setDocumentation(" replace the current measurement with a new one (convenience method to avoid casting, depending on the port) ");
				UMetaBuilder.manual().addParameter(operation, "measurement", SensePackage.theInstance.getMeasurement(), 0, 1, UDirectionType.IN);
				mSensorPort.getOperations().add(operation);
			//operation : getLatestMeasurement(Measurement)
			operation = UMetaBuilder.manual().createOperation("getLatestMeasurement", false, SensePackage.theInstance.getMeasurement(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((SensorPort)instance).getLatestMeasurement();
				}
			});
				//Annotations for SensorPort:getLatestMeasurement(Measurement)
				operation.createAnnotation("const");
				mSensorPort.getOperations().add(operation);
		}
		{		//Operations of Sensor
			UOperation operation = null;
			//operation : getAllPorts(SensorPort)
			operation = UMetaBuilder.manual().createOperation("getAllPorts", true, SensePackage.theInstance.getSensorPort(), 0, -1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Sensor)instance).getAllPorts();
				}
			});
				operation.setDocumentation(" returns a list of all ports of this sensor ");
				mSensor.getOperations().add(operation);
			//operation : getObservation(Observation)
			operation = UMetaBuilder.manual().createOperation("getObservation", false, SensePackage.theInstance.getObservation(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Sensor)instance).getObservation();
				}
			});
				operation.setDocumentation(" returns an observation that contains the measurments of all ports ");
				//Annotations for Sensor:getObservation(Observation)
				operation.createAnnotation("const");
				mSensor.getOperations().add(operation);
		}
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mSensorPort.setSuperType(CorePackage.theInstance.getIdentifiedObject());
		mSensor.setSuperType(PhysicsPackage.theInstance.getPhysicalObject());
		mEmitter.setSuperType(PhysicsPackage.theInstance.getPhysicalObject());
		
	}



	/**
	* @generated
	*/
	public UClass getObservation(){
		if (mObservation == null){
			mObservation = UCoreMetaRepository.getUClass(Observation.class);
		}
		return mObservation;
	}



	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getMeasurement(){
		if (mMeasurement == null){
			mMeasurement = UCoreMetaRepository.getUClass(Measurement.class);
		}
		return mMeasurement;
	}
	/**
	* @generated
	*/
	public UClass getSensor(){
		if (mSensor == null){
			mSensor = UCoreMetaRepository.getUClass(Sensor.class);
		}
		return mSensor;
	}
	/**
	* @generated
	*/
	public UClass getEmitter(){
		if (mEmitter == null){
			mEmitter = UCoreMetaRepository.getUClass(Emitter.class);
		}
		return mEmitter;
	}
	/**
	* @generated
	*/
	public UClass getErrorModel(){
		if (mErrorModel == null){
			mErrorModel = UCoreMetaRepository.getUClass(ErrorModel.class);
		}
		return mErrorModel;
	}



	/**
	* @generated
	*/
	public UClass getSensorPort(){
		if (mSensorPort == null){
			mSensorPort = UCoreMetaRepository.getUClass(SensorPort.class);
		}
		return mSensorPort;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getMeasurement_timestamp(){
		if (mMeasurement_timestamp == null)
			mMeasurement_timestamp = getMeasurement().getFeature("timestamp");
		return mMeasurement_timestamp;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getMeasurement_sender(){
		if (mMeasurement_sender == null)
			mMeasurement_sender = getMeasurement().getFeature("sender");
		return mMeasurement_sender;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getSensorPort_errorModel(){
		if (mSensorPort_errorModel == null)
			mSensorPort_errorModel = getSensorPort().getFeature("errorModel");
		return mSensorPort_errorModel;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getObservation_timestamp(){
		if (mObservation_timestamp == null)
			mObservation_timestamp = getObservation().getFeature("timestamp");
		return mObservation_timestamp;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getSensor_active(){
		if (mSensor_active == null)
			mSensor_active = getSensor().getFeature("active");
		return mSensor_active;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getSensor_frequency(){
		if (mSensor_frequency == null)
			mSensor_frequency = getSensor().getFeature("frequency");
		return mSensor_frequency;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getSensor_ports(){
		if (mSensor_ports == null)
			mSensor_ports = getSensor().getFeature("ports");
		return mSensor_ports;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getObservation_measurements(){
		if (mObservation_measurements == null)
			mObservation_measurements = getObservation().getFeature("measurements");
		return mObservation_measurements;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getSensorPort_observedRegion(){
		if (mSensorPort_observedRegion == null)
			mSensorPort_observedRegion = getSensorPort().getFeature("observedRegion");
		return mSensorPort_observedRegion;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEmitter_areaOfInfluence(){
		if (mEmitter_areaOfInfluence == null)
			mEmitter_areaOfInfluence = getEmitter().getFeature("areaOfInfluence");
		return mEmitter_areaOfInfluence;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getErrorModel_observationType(){
		if (mErrorModel_observationType == null)
			mErrorModel_observationType = getErrorModel().getFeature("observationType");
		return mErrorModel_observationType;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getErrorModel_pointers(){
		if (mErrorModel_pointers == null)
			mErrorModel_pointers = getErrorModel().getFeature("pointers");
		return mErrorModel_pointers;
	}
}
