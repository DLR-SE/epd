package de.emir.model.application.sense.ports;

import de.emir.model.application.sense.SensePackage;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.model.application.sense.measurements.DistanceMeasurement;
import de.emir.model.application.sense.measurements.LocationMeasurement;
import de.emir.model.application.sense.measurements.MeasurementsPackage;
import de.emir.model.application.sense.measurements.OrientationMeasurement;
import de.emir.model.application.sense.measurements.PositionMeasurement;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.model.application.sense.measurements.RotationMeasurement;
import de.emir.model.application.sense.measurements.TemperatureMeasurement;
import de.emir.model.application.sense.measurements.VelocityMeasurement;
import de.emir.model.application.sense.ports.impl.DistancePortImpl;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.model.application.sense.ports.impl.LocationPortImpl;
import de.emir.model.application.sense.ports.impl.OrientationPortImpl;
import de.emir.model.application.sense.ports.impl.PositionPortImpl;
import de.emir.model.application.sense.ports.impl.RotationPortImpl;
import de.emir.model.application.sense.ports.impl.TemperaturePortImpl;
import de.emir.model.application.sense.measurements.ObjectMeasurement;
import de.emir.model.application.sense.ports.impl.VelocityPortImpl;
import de.emir.model.universal.CoreModel;
import de.emir.model.universal.PhysicsModel;
import de.emir.model.universal.SpatialModel;
import de.emir.model.universal.UnitsModel;
import de.emir.tuml.ucore.UCoreModel;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.model.application.sense.ports.ObjectPort;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.application.sense.ports.DistancePort;
import de.emir.model.application.sense.ports.LocationPort;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.model.application.sense.ports.OrientationPort;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.model.application.sense.ports.VelocityPort;
import de.emir.model.application.sense.ports.PositionPort;
import de.emir.model.application.sense.ports.RotationPort;
import de.emir.model.application.sense.ports.impl.ObjectPortImpl;
import de.emir.model.application.sense.ports.TemperaturePort;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;

/**
 *	@generated 
 */
public class PortsPackage  
{
	
	/**
	 * @generated
	 */
	public static PortsPackage theInstance = new PortsPackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier LocationPort
		*/
		UClass LocationPort = PortsPackage.theInstance.getLocationPort();
		/**
		* @generated
		* @return meta type for classifier OrientationPort
		*/
		UClass OrientationPort = PortsPackage.theInstance.getOrientationPort();
		/**
		* @generated
		* @return meta type for classifier VelocityPort
		*/
		UClass VelocityPort = PortsPackage.theInstance.getVelocityPort();
		/**
		* @generated
		* @return meta type for classifier RotationPort
		*/
		UClass RotationPort = PortsPackage.theInstance.getRotationPort();
		/**
		* @generated
		* @return meta type for classifier TemperaturePort
		*/
		UClass TemperaturePort = PortsPackage.theInstance.getTemperaturePort();
		/**
		* @generated
		* @return meta type for classifier DistancePort
		*/
		UClass DistancePort = PortsPackage.theInstance.getDistancePort();
		/**
		* @generated
		* @return meta type for classifier PositionPort
		*/
		UClass PositionPort = PortsPackage.theInstance.getPositionPort();
		/**
		* @generated
		* @return meta type for classifier ObjectPort
		*/
		UClass ObjectPort = PortsPackage.theInstance.getObjectPort();
		
		/**
		 * @generated
		 * @return feature descriptor measurement in type LocationPort
		 */
		 UStructuralFeature LocationPort_measurement = PortsPackage.theInstance.getLocationPort_measurement();
		/**
		 * @generated
		 * @return feature descriptor measurement in type OrientationPort
		 */
		 UStructuralFeature OrientationPort_measurement = PortsPackage.theInstance.getOrientationPort_measurement();
		/**
		 * @generated
		 * @return feature descriptor measurement in type VelocityPort
		 */
		 UStructuralFeature VelocityPort_measurement = PortsPackage.theInstance.getVelocityPort_measurement();
		/**
		 * @generated
		 * @return feature descriptor measurement in type RotationPort
		 */
		 UStructuralFeature RotationPort_measurement = PortsPackage.theInstance.getRotationPort_measurement();
		/**
		 * @generated
		 * @return feature descriptor measurement in type TemperaturePort
		 */
		 UStructuralFeature TemperaturePort_measurement = PortsPackage.theInstance.getTemperaturePort_measurement();
		/**
		 * @generated
		 * @return feature descriptor measurement in type DistancePort
		 */
		 UStructuralFeature DistancePort_measurement = PortsPackage.theInstance.getDistancePort_measurement();
		/**
		 * @generated
		 * @return feature descriptor measurement in type PositionPort
		 */
		 UStructuralFeature PositionPort_measurement = PortsPackage.theInstance.getPositionPort_measurement();
		/**
		 * @generated
		 * @return feature descriptor measurement in type ObjectPort
		 */
		 UStructuralFeature ObjectPort_measurement = PortsPackage.theInstance.getObjectPort_measurement();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mLocationPort = null;
	/**
	* @generated
	*/
	private UClass mOrientationPort = null;
	/**
	* @generated
	*/
	private UClass mVelocityPort = null;
	/**
	* @generated
	*/
	private UClass mRotationPort = null;
	/**
	* @generated
	*/
	private UClass mTemperaturePort = null;
	/**
	* @generated
	*/
	private UClass mDistancePort = null;
	/**
	* @generated
	*/
	private UClass mPositionPort = null;
	/**
	* @generated
	*/
	private UClass mObjectPort = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	//Features for classifier LocationPort
	/**
	 * @generated
	 */
	private UStructuralFeature mLocationPort_measurement = null;
	
	//Features for classifier OrientationPort
	/**
	 * @generated
	 */
	private UStructuralFeature mOrientationPort_measurement = null;
	
	//Features for classifier VelocityPort
	/**
	 * @generated
	 */
	private UStructuralFeature mVelocityPort_measurement = null;
	
	//Features for classifier RotationPort
	/**
	 * @generated
	 */
	private UStructuralFeature mRotationPort_measurement = null;
	
	//Features for classifier TemperaturePort
	/**
	 * @generated
	 */
	private UStructuralFeature mTemperaturePort_measurement = null;
	
	//Features for classifier DistancePort
	/**
	 * @generated
	 */
	private UStructuralFeature mDistancePort_measurement = null;
	
	//Features for classifier PositionPort
	/**
	 * @generated
	 */
	private UStructuralFeature mPositionPort_measurement = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mObjectPort_measurement = null;
	
	/**
	 * @generated
	 */
	public static PortsPackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package PortsPackage ...");
		theInstance = new PortsPackage();
		//initialize referenced models
		PhysicsModel.init();
		CoreModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.application.sense.ports");
		p.getContent().add(theInstance.mLocationPort);
		p.getContent().add(theInstance.mOrientationPort);
		p.getContent().add(theInstance.mVelocityPort);
		p.getContent().add(theInstance.mRotationPort);
		p.getContent().add(theInstance.mTemperaturePort);
		p.getContent().add(theInstance.mDistancePort);
		p.getContent().add(theInstance.mPositionPort);
		p.getContent().add(theInstance.mObjectPort);
		p.freeze();
		
		
		
		ULog.debug("... package PortsPackage initialized");
		
		return theInstance;
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mLocationPort = UMetaBuilder.manual().createClass("LocationPort", false, LocationPort.class, LocationPortImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mLocationPort, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new LocationPortImpl();
				}
			});
			mLocationPort.setDocumentation(" Measurement that holds the location of a not further specified object \r\n * this could be for example an GPS, Lorence or Galileo measure. Inherited structs may extend some additional \r\n * metadata like used satellites (if Satellite based sensor)");
		
		mOrientationPort = UMetaBuilder.manual().createClass("OrientationPort", false, OrientationPort.class, OrientationPortImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mOrientationPort, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new OrientationPortImpl();
				}
			});
			mOrientationPort.setDocumentation(" Measurement that represents the orientation of the measured object, e.g. heading. \r\n * Orientation measures may be created by compass sensors. \r\n ");
		
		mVelocityPort = UMetaBuilder.manual().createClass("VelocityPort", false, VelocityPort.class, VelocityPortImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mVelocityPort, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new VelocityPortImpl();
				}
			});
			mVelocityPort.setDocumentation(" Measurement representing the velocity / speed of the measured object, e.g. could be a tachometer, \r\n * a wind sensor (wind speed), log (speed through water)\r\n ");
		
		mRotationPort = UMetaBuilder.manual().createClass("RotationPort", false, RotationPort.class, RotationPortImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mRotationPort, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new RotationPortImpl();
				}
			});
			mRotationPort.setDocumentation("\r\n * Measurement that represents the change of the orientation over time, e.g. the rotation. \r\n * This could be created by a gyroscope\r\n ");
		
		mTemperaturePort = UMetaBuilder.manual().createClass("TemperaturePort", false, TemperaturePort.class, TemperaturePortImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mTemperaturePort, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new TemperaturePortImpl();
				}
			});
			mTemperaturePort.setDocumentation(" Measurement containing the temperature of something ");
		
		mDistancePort = UMetaBuilder.manual().createClass("DistancePort", false, DistancePort.class, DistancePortImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mDistancePort, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new DistancePortImpl();
				}
			});
		
		mPositionPort = UMetaBuilder.manual().createClass("PositionPort", false, PositionPort.class, PositionPortImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mPositionPort, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new PositionPortImpl();
				}
			});
			mPositionPort.setDocumentation(" The position Measurement differs from the LocationMeasurement in that form, that it measures the position\r\n * of an external object. Within the maritime domain such a measurement may be generated by an AIS sensor but also \r\n * by an Radar (reasoned from the own position of the radar and a DistanceMeasurement) \r\n ");
		
		mObjectPort = UMetaBuilder.manual().createClass("ObjectPort", false, ObjectPort.class, ObjectPortImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mObjectPort, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ObjectPortImpl();
				}
			});
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of LocationPort
			mLocationPort_measurement = UMetaBuilder.manual().createFeature("measurement", MeasurementsPackage.theInstance.getLocationMeasurement(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLocationPort_measurement, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((LocationPort)instance).getMeasurement(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((LocationPort)instance).setMeasurement((LocationMeasurement)value); } }
				);
			
			//Features of OrientationPort
			mOrientationPort_measurement = UMetaBuilder.manual().createFeature("measurement", MeasurementsPackage.theInstance.getOrientationMeasurement(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mOrientationPort_measurement, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((OrientationPort)instance).getMeasurement(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((OrientationPort)instance).setMeasurement((OrientationMeasurement)value); } }
				);
			
			//Features of VelocityPort
			mVelocityPort_measurement = UMetaBuilder.manual().createFeature("measurement", MeasurementsPackage.theInstance.getVelocityMeasurement(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVelocityPort_measurement, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((VelocityPort)instance).getMeasurement(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((VelocityPort)instance).setMeasurement((VelocityMeasurement)value); } }
				);
			
			//Features of RotationPort
			mRotationPort_measurement = UMetaBuilder.manual().createFeature("measurement", MeasurementsPackage.theInstance.getRotationMeasurement(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRotationPort_measurement, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((RotationPort)instance).getMeasurement(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((RotationPort)instance).setMeasurement((RotationMeasurement)value); } }
				);
			
			//Features of TemperaturePort
			mTemperaturePort_measurement = UMetaBuilder.manual().createFeature("measurement", MeasurementsPackage.theInstance.getTemperatureMeasurement(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTemperaturePort_measurement, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TemperaturePort)instance).getMeasurement(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TemperaturePort)instance).setMeasurement((TemperatureMeasurement)value); } }
				);
			
			//Features of DistancePort
			mDistancePort_measurement = UMetaBuilder.manual().createFeature("measurement", MeasurementsPackage.theInstance.getDistanceMeasurement(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mDistancePort_measurement, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((DistancePort)instance).getMeasurement(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((DistancePort)instance).setMeasurement((DistanceMeasurement)value); } }
				);
			
			//Features of PositionPort
			mPositionPort_measurement = UMetaBuilder.manual().createFeature("measurement", MeasurementsPackage.theInstance.getPositionMeasurement(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mPositionPort_measurement, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((PositionPort)instance).getMeasurement(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((PositionPort)instance).setMeasurement((PositionMeasurement)value); } }
				);
			
			//Features of ObjectPort
			mObjectPort_measurement = UMetaBuilder.manual().createFeature("measurement", MeasurementsPackage.theInstance.getObjectMeasurement(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mObjectPort_measurement, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ObjectPort)instance).getMeasurement(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ObjectPort)instance).setMeasurement((ObjectMeasurement)value); } }
				);
			
		}
		{ //assign features
			mLocationPort.getStructuralFeatures().add(mLocationPort_measurement);
			mOrientationPort.getStructuralFeatures().add(mOrientationPort_measurement);
			mVelocityPort.getStructuralFeatures().add(mVelocityPort_measurement);
			mRotationPort.getStructuralFeatures().add(mRotationPort_measurement);
			mTemperaturePort.getStructuralFeatures().add(mTemperaturePort_measurement);
			mDistancePort.getStructuralFeatures().add(mDistancePort_measurement);
			mPositionPort.getStructuralFeatures().add(mPositionPort_measurement);
			mObjectPort.getStructuralFeatures().add(mObjectPort_measurement);
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
		mLocationPort.setSuperType(SensePackage.theInstance.getSensorPort());
		mOrientationPort.setSuperType(SensePackage.theInstance.getSensorPort());
		mVelocityPort.setSuperType(SensePackage.theInstance.getSensorPort());
		mRotationPort.setSuperType(SensePackage.theInstance.getSensorPort());
		mTemperaturePort.setSuperType(SensePackage.theInstance.getSensorPort());
		mDistancePort.setSuperType(SensePackage.theInstance.getSensorPort());
		mPositionPort.setSuperType(SensePackage.theInstance.getSensorPort());
		mObjectPort.setSuperType(SensePackage.theInstance.getSensorPort());
		
	}



	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getLocationPort(){
		if (mLocationPort == null){
			mLocationPort = UCoreMetaRepository.getUClass(LocationPort.class);
		}
		return mLocationPort;
	}
	/**
	* @generated
	*/
	public UClass getOrientationPort(){
		if (mOrientationPort == null){
			mOrientationPort = UCoreMetaRepository.getUClass(OrientationPort.class);
		}
		return mOrientationPort;
	}
	/**
	* @generated
	*/
	public UClass getVelocityPort(){
		if (mVelocityPort == null){
			mVelocityPort = UCoreMetaRepository.getUClass(VelocityPort.class);
		}
		return mVelocityPort;
	}
	/**
	* @generated
	*/
	public UClass getRotationPort(){
		if (mRotationPort == null){
			mRotationPort = UCoreMetaRepository.getUClass(RotationPort.class);
		}
		return mRotationPort;
	}
	/**
	* @generated
	*/
	public UClass getTemperaturePort(){
		if (mTemperaturePort == null){
			mTemperaturePort = UCoreMetaRepository.getUClass(TemperaturePort.class);
		}
		return mTemperaturePort;
	}
	/**
	* @generated
	*/
	public UClass getDistancePort(){
		if (mDistancePort == null){
			mDistancePort = UCoreMetaRepository.getUClass(DistancePort.class);
		}
		return mDistancePort;
	}
	/**
	* @generated
	*/
	public UClass getPositionPort(){
		if (mPositionPort == null){
			mPositionPort = UCoreMetaRepository.getUClass(PositionPort.class);
		}
		return mPositionPort;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getLocationPort_measurement(){
		if (mLocationPort_measurement == null)
			mLocationPort_measurement = getLocationPort().getFeature("measurement");
		return mLocationPort_measurement;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getOrientationPort_measurement(){
		if (mOrientationPort_measurement == null)
			mOrientationPort_measurement = getOrientationPort().getFeature("measurement");
		return mOrientationPort_measurement;
	}



	/**
	* @generated
	*/
	public UClass getObjectPort(){
		if (mObjectPort == null){
			mObjectPort = UCoreMetaRepository.getUClass(ObjectPort.class);
		}
		return mObjectPort;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVelocityPort_measurement(){
		if (mVelocityPort_measurement == null)
			mVelocityPort_measurement = getVelocityPort().getFeature("measurement");
		return mVelocityPort_measurement;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getRotationPort_measurement(){
		if (mRotationPort_measurement == null)
			mRotationPort_measurement = getRotationPort().getFeature("measurement");
		return mRotationPort_measurement;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTemperaturePort_measurement(){
		if (mTemperaturePort_measurement == null)
			mTemperaturePort_measurement = getTemperaturePort().getFeature("measurement");
		return mTemperaturePort_measurement;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getDistancePort_measurement(){
		if (mDistancePort_measurement == null)
			mDistancePort_measurement = getDistancePort().getFeature("measurement");
		return mDistancePort_measurement;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getPositionPort_measurement(){
		if (mPositionPort_measurement == null)
			mPositionPort_measurement = getPositionPort().getFeature("measurement");
		return mPositionPort_measurement;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getObjectPort_measurement(){
		if (mObjectPort_measurement == null)
			mObjectPort_measurement = getObjectPort().getFeature("measurement");
		return mObjectPort_measurement;
	}
}
