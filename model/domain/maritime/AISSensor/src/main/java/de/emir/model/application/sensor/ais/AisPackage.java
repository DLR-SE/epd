package de.emir.model.application.sensor.ais;

import de.emir.model.application.SenseModel;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.model.application.sense.SensePackage;
import de.emir.model.application.sense.measurements.MeasurementsPackage;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.model.application.sense.ports.PortsPackage;
import de.emir.model.application.sensor.ais.impl.AISEmitterImpl;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.model.application.sensor.ais.impl.AISMeasurementImpl;
import de.emir.model.application.sensor.ais.impl.AISPortImpl;
import de.emir.model.universal.CoreModel;
import de.emir.model.universal.PhysicsModel;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.model.application.sensor.ais.AISEmitterClass;
import de.emir.model.application.sensor.ais.AISEmitter;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.model.application.sensor.ais.AISCharacteristic;
import de.emir.model.application.sensor.ais.AISPort;
import de.emir.model.application.sensor.ais.AISMeasurement;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.model.application.sensor.ais.impl.AISCharacteristicImpl;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.physics.PhysicsPackage;

/**
 *	@generated 
 */
public class AisPackage  
{
	
	/**
	 * @generated
	 */
	public static AisPackage theInstance = new AisPackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier AISEmitter
		*/
		UClass AISEmitter = AisPackage.theInstance.getAISEmitter();
		/**
		* @generated
		* @return meta type for classifier AISPort
		*/
		UClass AISPort = AisPackage.theInstance.getAISPort();
		/**
		* @generated
		* @return meta type for enumeration AISEmitterClass
		*/
		UEnum AISEmitterClass = AisPackage.theInstance.getAISEmitterClass();
		/**
		* @generated
		* @return meta type for classifier AISMeasurement
		*/
		UClass AISMeasurement = AisPackage.theInstance.getAISMeasurement();
		/**
		* @generated
		* @return meta type for classifier AISCharacteristic
		*/
		UClass AISCharacteristic = AisPackage.theInstance.getAISCharacteristic();
		/**
		 * @generated
		 * @return feature descriptor fixedAISInvterval in type AISEmitter
		 */
		 UStructuralFeature AISEmitter_fixedAISInvterval = AisPackage.theInstance.getAISEmitter_fixedAISInvterval();
		/**
		 * @generated
		 * @return feature descriptor sendInFixedInterval in type AISEmitter
		 */
		 UStructuralFeature AISEmitter_sendInFixedInterval = AisPackage.theInstance.getAISEmitter_sendInFixedInterval();
		/**
		 * @generated
		 * @return feature descriptor aisEmitterClass in type AISEmitter
		 */
		 UStructuralFeature AISEmitter_aisEmitterClass = AisPackage.theInstance.getAISEmitter_aisEmitterClass();
		
		/**
		 * @generated
		 * @return feature descriptor messageID in type AISMeasurement
		 */
		 UStructuralFeature AISMeasurement_messageID = AisPackage.theInstance.getAISMeasurement_messageID();
		/**
		 * @generated
		 * @return feature descriptor lastMeasurement in type AISCharacteristic
		 */
		 UStructuralFeature AISCharacteristic_lastMeasurement = AisPackage.theInstance.getAISCharacteristic_lastMeasurement();
		/**
		 * @generated
		 * @return feature descriptor measurementDevice in type AISCharacteristic
		 */
		 UStructuralFeature AISCharacteristic_measurementDevice = AisPackage.theInstance.getAISCharacteristic_measurementDevice();
		
	}

	/**
	* @generated
	*/
	private UEnum mAISEmitterClass = null; 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mAISEmitter = null;
	/**
	* @generated
	*/
	private UClass mAISPort = null;
	/**
	* @generated
	*/
	private UClass mAISMeasurement = null;
	/**
	* @generated
	*/
	private UClass mAISCharacteristic = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAISEmitter_fixedAISInvterval = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAISEmitter_sendInFixedInterval = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	
	//Features for classifier AISMeasurement
	/**
	 * @generated
	 */
	private UStructuralFeature mAISMeasurement_messageID = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAISEmitter_aisEmitterClass = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAISCharacteristic_lastMeasurement = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAISCharacteristic_measurementDevice = null;
	
	/**
	 * @generated
	 */
	public static AisPackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package AisPackage ...");
		theInstance = new AisPackage();
		//initialize referenced models
		SenseModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.application.sensor.ais");
		p.getContent().add(theInstance.mAISEmitterClass);
		p.getContent().add(theInstance.mAISEmitter);
		p.getContent().add(theInstance.mAISPort);
		p.getContent().add(theInstance.mAISMeasurement);
		p.getContent().add(theInstance.mAISCharacteristic);
		p.freeze();
		
		
		
		ULog.debug("... package AisPackage initialized");
		
		return theInstance;
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mAISEmitterClass = UMetaBuilder.manual().createEnumeration("AISEmitterClass", AISEmitterClass.class);
			mAISEmitterClass.setDocumentation("\r\n * AIS Emitter Class. Commercial ships are required to use A, private ships and small vessels usually use B.\r\n ");
		
		mAISEmitter = UMetaBuilder.manual().createClass("AISEmitter", false, AISEmitter.class, AISEmitterImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mAISEmitter, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new AISEmitterImpl();
				}
			});
			mAISEmitter.setDocumentation("\r\n * An AISEmitter is used to emit / send AISMessages. \r\n * @note we distinguish between sensor (AISSensor) and emitter since not all available sensors are able to send own messages, e.g. small vessels like sailing yacht\r\n * (in fact this would lead to more traffic as the AIS network could handle)\r\n ");
		
		mAISPort = UMetaBuilder.manual().createClass("AISPort", false, AISPort.class, AISPortImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mAISPort, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new AISPortImpl();
				}
			});
		
		mAISMeasurement = UMetaBuilder.manual().createClass("AISMeasurement", false, AISMeasurement.class, AISMeasurementImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mAISMeasurement, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new AISMeasurementImpl();
				}
			});
			//Annotations for AISMeasurement
			mAISMeasurement.createAnnotation("struct");
		
		mAISCharacteristic = UMetaBuilder.manual().createClass("AISCharacteristic", false, AISCharacteristic.class, AISCharacteristicImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mAISCharacteristic, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new AISCharacteristicImpl();
				}
			});
			mAISCharacteristic.setDocumentation(" \r\n * Characteristic that indicates, that an Object has been captured / discovered through an AIS sensor\r\n ");
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of AISEmitter
			mAISEmitter_fixedAISInvterval = UMetaBuilder.manual().createFeature("fixedAISInvterval", UnitsPackage.theInstance.getTime(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAISEmitter_fixedAISInvterval, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AISEmitter)instance).getFixedAISInvterval(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AISEmitter)instance).setFixedAISInvterval((Time)value); } }
				);
			mAISEmitter_sendInFixedInterval = UMetaBuilder.manual().createFeature("sendInFixedInterval", TypeUtils.getPrimitiveType(boolean.class), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAISEmitter_sendInFixedInterval, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AISEmitter)instance).getSendInFixedInterval(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AISEmitter)instance).setSendInFixedInterval((boolean)value); } }
				);
			mAISEmitter_aisEmitterClass = UMetaBuilder.manual().createFeature("aisEmitterClass", AisPackage.theInstance.getAISEmitterClass(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAISEmitter_aisEmitterClass, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AISEmitter)instance).getAisEmitterClass(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AISEmitter)instance).setAisEmitterClass((AISEmitterClass)value); } }
				);
			
			//Features of AISMeasurement
			mAISMeasurement_messageID = UMetaBuilder.manual().createFeature("messageID", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAISMeasurement_messageID, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AISMeasurement)instance).getMessageID(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AISMeasurement)instance).setMessageID((int)value); } }
				);
			
			//Features of AISCharacteristic
			mAISCharacteristic_lastMeasurement = UMetaBuilder.manual().createFeature("lastMeasurement", UnitsPackage.theInstance.getTime(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAISCharacteristic_lastMeasurement, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AISCharacteristic)instance).getLastMeasurement(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AISCharacteristic)instance).setLastMeasurement((Time)value); } }
				);
				mAISCharacteristic_lastMeasurement.setDocumentation(" contains the time of the last AIS measurement ");
			mAISCharacteristic_measurementDevice = UMetaBuilder.manual().createFeature("measurementDevice", AisPackage.theInstance.getAISPort(), UAssociationType.AGGREGATION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAISCharacteristic_measurementDevice, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AISCharacteristic)instance).getMeasurementDevice(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AISCharacteristic)instance).setMeasurementDevice((AISPort)value); } }
				);
				mAISCharacteristic_measurementDevice.setDocumentation(" the device that has been used ");
			
		}
		{ //assign features
			mAISEmitter.getStructuralFeatures().add(mAISEmitter_fixedAISInvterval);
			mAISEmitter.getStructuralFeatures().add(mAISEmitter_sendInFixedInterval);
			mAISEmitter.getStructuralFeatures().add(mAISEmitter_aisEmitterClass);
			mAISMeasurement.getStructuralFeatures().add(mAISMeasurement_messageID);
			mAISCharacteristic.getStructuralFeatures().add(mAISCharacteristic_lastMeasurement);
			mAISCharacteristic.getStructuralFeatures().add(mAISCharacteristic_measurementDevice);
		}
		
		UMetaBuilder.manual().addLiteral(mAISEmitterClass, "A", 0, AISEmitterClass.A);
		UMetaBuilder.manual().addLiteral(mAISEmitterClass, "B", 1, AISEmitterClass.B);
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mAISEmitter.setSuperType(SensePackage.theInstance.getEmitter());
		mAISPort.setSuperType(PortsPackage.theInstance.getPositionPort());
		mAISMeasurement.setSuperType(MeasurementsPackage.theInstance.getPositionMeasurement());
		mAISCharacteristic.setSuperType(PhysicsPackage.theInstance.getCharacteristic());
		
	}



	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getAISEmitter(){
		if (mAISEmitter == null){
			mAISEmitter = UCoreMetaRepository.getUClass(AISEmitter.class);
		}
		return mAISEmitter;
	}



	/**
	* @generated
	*/
	public UEnum getAISEmitterClass(){
		if (mAISEmitterClass == null){
			mAISEmitterClass = UCoreMetaRepository.getUEnumeration(AISEmitterClass.class);
		}
		return mAISEmitterClass;
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
		{		//Operations of AISPort
			UOperation operation = null;
			//operation : getAISMeasurement(AISMeasurement)
			operation = UMetaBuilder.manual().createOperation("getAISMeasurement", false, AisPackage.theInstance.getAISMeasurement(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AISPort)instance).getAISMeasurement();
				}
			});
				//Annotations for AISPort:getAISMeasurement(AISMeasurement)
				operation.createAnnotation("const");
				mAISPort.getOperations().add(operation);
		}
	}
	/**
	* @generated
	*/
	public UClass getAISPort(){
		if (mAISPort == null){
			mAISPort = UCoreMetaRepository.getUClass(AISPort.class);
		}
		return mAISPort;
	}



	/**
	* @generated
	*/
	public UClass getAISCharacteristic(){
		if (mAISCharacteristic == null){
			mAISCharacteristic = UCoreMetaRepository.getUClass(AISCharacteristic.class);
		}
		return mAISCharacteristic;
	}
	/**
	* @generated
	*/
	public UClass getAISMeasurement(){
		if (mAISMeasurement == null){
			mAISMeasurement = UCoreMetaRepository.getUClass(AISMeasurement.class);
		}
		return mAISMeasurement;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getAISCharacteristic_lastMeasurement(){
		if (mAISCharacteristic_lastMeasurement == null)
			mAISCharacteristic_lastMeasurement = getAISCharacteristic().getFeature("lastMeasurement");
		return mAISCharacteristic_lastMeasurement;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getAISEmitter_fixedAISInvterval(){
		if (mAISEmitter_fixedAISInvterval == null)
			mAISEmitter_fixedAISInvterval = getAISEmitter().getFeature("fixedAISInvterval");
		return mAISEmitter_fixedAISInvterval;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getAISEmitter_sendInFixedInterval(){
		if (mAISEmitter_sendInFixedInterval == null)
			mAISEmitter_sendInFixedInterval = getAISEmitter().getFeature("sendInFixedInterval");
		return mAISEmitter_sendInFixedInterval;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getAISCharacteristic_measurementDevice(){
		if (mAISCharacteristic_measurementDevice == null)
			mAISCharacteristic_measurementDevice = getAISCharacteristic().getFeature("measurementDevice");
		return mAISCharacteristic_measurementDevice;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getAISEmitter_aisEmitterClass(){
		if (mAISEmitter_aisEmitterClass == null)
			mAISEmitter_aisEmitterClass = getAISEmitter().getFeature("aisEmitterClass");
		return mAISEmitter_aisEmitterClass;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getAISMeasurement_messageID(){
		if (mAISMeasurement_messageID == null)
			mAISMeasurement_messageID = getAISMeasurement().getFeature("messageID");
		return mAISMeasurement_messageID;
	}
}
