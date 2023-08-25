package de.emir.model.application.sense.measurements;

import de.emir.model.application.sense.SensePackage;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.model.application.sense.measurements.impl.DistanceMeasurementImpl;
import de.emir.model.application.sense.measurements.impl.LocationMeasurementImpl;
import de.emir.model.application.sense.measurements.impl.ObjectMeasurementImpl;
import de.emir.model.application.sense.measurements.impl.OrientationMeasurementImpl;
import de.emir.model.application.sense.measurements.impl.PositionMeasurementImpl;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.model.application.sense.measurements.impl.RotationMeasurementImpl;
import de.emir.model.application.sense.measurements.impl.TemperatureMeasurementImpl;
import de.emir.model.application.sense.measurements.impl.VelocityMeasurementImpl;
import de.emir.model.universal.CoreModel;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.model.universal.PhysicsModel;
import de.emir.model.universal.SpatialModel;
import de.emir.model.universal.UnitsModel;
import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.IdentifiedObject;
import de.emir.model.application.sense.measurements.LocationMeasurement;
import de.emir.model.application.sense.measurements.DistanceMeasurement;
import de.emir.model.application.sense.measurements.ObjectMeasurement;
import de.emir.model.application.sense.measurements.RotationMeasurement;
import de.emir.model.application.sense.measurements.OrientationMeasurement;
import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.application.sense.measurements.VelocityMeasurement;
import de.emir.model.application.sense.measurements.PositionMeasurement;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.application.sense.measurements.TemperatureMeasurement;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.units.AngularVelocity;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Orientation;
import de.emir.model.universal.units.Temperature;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.Velocity;
import de.emir.tuml.ucore.UCoreModel;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPackage;
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
public class MeasurementsPackage  
{
	
	/**
	 * @generated
	 */
	public static MeasurementsPackage theInstance = new MeasurementsPackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier LocationMeasurement
		*/
		UClass LocationMeasurement = MeasurementsPackage.theInstance.getLocationMeasurement();
		/**
		* @generated
		* @return meta type for classifier OrientationMeasurement
		*/
		UClass OrientationMeasurement = MeasurementsPackage.theInstance.getOrientationMeasurement();
		/**
		* @generated
		* @return meta type for classifier VelocityMeasurement
		*/
		UClass VelocityMeasurement = MeasurementsPackage.theInstance.getVelocityMeasurement();
		/**
		* @generated
		* @return meta type for classifier RotationMeasurement
		*/
		UClass RotationMeasurement = MeasurementsPackage.theInstance.getRotationMeasurement();
		/**
		* @generated
		* @return meta type for classifier TemperatureMeasurement
		*/
		UClass TemperatureMeasurement = MeasurementsPackage.theInstance.getTemperatureMeasurement();
		/**
		* @generated
		* @return meta type for classifier DistanceMeasurement
		*/
		UClass DistanceMeasurement = MeasurementsPackage.theInstance.getDistanceMeasurement();
		/**
		* @generated
		* @return meta type for classifier PositionMeasurement
		*/
		UClass PositionMeasurement = MeasurementsPackage.theInstance.getPositionMeasurement();
		/**
		* @generated
		* @return meta type for classifier ObjectMeasurement
		*/
		UClass ObjectMeasurement = MeasurementsPackage.theInstance.getObjectMeasurement();
		
		/**
		 * @generated
		 * @return feature descriptor location in type LocationMeasurement
		 */
		 UStructuralFeature LocationMeasurement_location = MeasurementsPackage.theInstance.getLocationMeasurement_location();
		/**
		 * @generated
		 * @return feature descriptor orientation in type OrientationMeasurement
		 */
		 UStructuralFeature OrientationMeasurement_orientation = MeasurementsPackage.theInstance.getOrientationMeasurement_orientation();
		/**
		 * @generated
		 * @return feature descriptor velocity in type VelocityMeasurement
		 */
		 UStructuralFeature VelocityMeasurement_velocity = MeasurementsPackage.theInstance.getVelocityMeasurement_velocity();
		/**
		 * @generated
		 * @return feature descriptor velocity in type RotationMeasurement
		 */
		 UStructuralFeature RotationMeasurement_velocity = MeasurementsPackage.theInstance.getRotationMeasurement_velocity();
		/**
		 * @generated
		 * @return feature descriptor temperature in type TemperatureMeasurement
		 */
		 UStructuralFeature TemperatureMeasurement_temperature = MeasurementsPackage.theInstance.getTemperatureMeasurement_temperature();
		/**
		 * @generated
		 * @return feature descriptor distance in type DistanceMeasurement
		 */
		 UStructuralFeature DistanceMeasurement_distance = MeasurementsPackage.theInstance.getDistanceMeasurement_distance();
		/**
		 * @generated
		 * @return feature descriptor object in type PositionMeasurement
		 */
		 UStructuralFeature PositionMeasurement_object = MeasurementsPackage.theInstance.getPositionMeasurement_object();
		/**
		 * @generated
		 * @return feature descriptor object in type ObjectMeasurement
		 */
		 UStructuralFeature ObjectMeasurement_object = MeasurementsPackage.theInstance.getObjectMeasurement_object();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mLocationMeasurement = null;
	/**
	* @generated
	*/
	private UClass mOrientationMeasurement = null;
	/**
	* @generated
	*/
	private UClass mVelocityMeasurement = null;
	/**
	* @generated
	*/
	private UClass mRotationMeasurement = null;
	/**
	* @generated
	*/
	private UClass mTemperatureMeasurement = null;
	/**
	* @generated
	*/
	private UClass mDistanceMeasurement = null;
	/**
	* @generated
	*/
	private UClass mPositionMeasurement = null;
	/**
	* @generated
	*/
	private UClass mObjectMeasurement = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	//Features for classifier LocationMeasurement
	/**
	 * @generated
	 */
	private UStructuralFeature mLocationMeasurement_location = null;
	
	//Features for classifier OrientationMeasurement
	/**
	 * @generated
	 */
	private UStructuralFeature mOrientationMeasurement_orientation = null;
	
	//Features for classifier VelocityMeasurement
	/**
	 * @generated
	 */
	private UStructuralFeature mVelocityMeasurement_velocity = null;
	
	//Features for classifier RotationMeasurement
	/**
	 * @generated
	 */
	private UStructuralFeature mRotationMeasurement_velocity = null;
	
	//Features for classifier TemperatureMeasurement
	/**
	 * @generated
	 */
	private UStructuralFeature mTemperatureMeasurement_temperature = null;
	
	//Features for classifier DistanceMeasurement
	/**
	 * @generated
	 */
	private UStructuralFeature mDistanceMeasurement_distance = null;
	
	//Features for classifier PositionMeasurement
	/**
	 * @generated
	 */
	private UStructuralFeature mPositionMeasurement_object = null;
	
	/**
	 * @generated
	 */
	private UStructuralFeature mObjectMeasurement_object = null;
	
	
	
	/**
	 * @generated
	 */
	public static MeasurementsPackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package MeasurementsPackage ...", 1);
		theInstance = new MeasurementsPackage();
		//initialize referenced models
		PhysicsModel.init();
		CoreModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.application.sense.measurements");
		p.getContent().add(theInstance.mLocationMeasurement);
		p.getContent().add(theInstance.mOrientationMeasurement);
		p.getContent().add(theInstance.mVelocityMeasurement);
		p.getContent().add(theInstance.mRotationMeasurement);
		p.getContent().add(theInstance.mTemperatureMeasurement);
		p.getContent().add(theInstance.mDistanceMeasurement);
		p.getContent().add(theInstance.mPositionMeasurement);
		p.getContent().add(theInstance.mObjectMeasurement);
		p.freeze();
		
		
		
		ULog.debug(-1, "... package MeasurementsPackage initialized");
		
		return theInstance;
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mLocationMeasurement = UMetaBuilder.manual().createClass("LocationMeasurement", false, LocationMeasurement.class, LocationMeasurementImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mLocationMeasurement, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new LocationMeasurementImpl();
				}
			});
			mLocationMeasurement.setDocumentation(" Measurement that holds the location of a not further specified object \r\n * this could be for example an GPS, Lorence or Galileo measure. Inherited structs may extend some additional \r\n * metadata like used satellites (if Satellite based sensor)");
			//Annotations for LocationMeasurement
			mLocationMeasurement.createAnnotation("struct");
		
		mOrientationMeasurement = UMetaBuilder.manual().createClass("OrientationMeasurement", false, OrientationMeasurement.class, OrientationMeasurementImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mOrientationMeasurement, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new OrientationMeasurementImpl();
				}
			});
			mOrientationMeasurement.setDocumentation(" Measurement that represents the orientation of the measured object, e.g. heading. \r\n * Orientation measures may be created by compass sensors. \r\n ");
			//Annotations for OrientationMeasurement
			mOrientationMeasurement.createAnnotation("struct");
		
		mVelocityMeasurement = UMetaBuilder.manual().createClass("VelocityMeasurement", false, VelocityMeasurement.class, VelocityMeasurementImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mVelocityMeasurement, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new VelocityMeasurementImpl();
				}
			});
			mVelocityMeasurement.setDocumentation(" Measurement representing the velocity / speed of the measured object, e.g. could be a tachometer, \r\n * a wind sensor (wind speed), log (speed through water)\r\n ");
			//Annotations for VelocityMeasurement
			mVelocityMeasurement.createAnnotation("struct");
		
		mRotationMeasurement = UMetaBuilder.manual().createClass("RotationMeasurement", false, RotationMeasurement.class, RotationMeasurementImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mRotationMeasurement, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new RotationMeasurementImpl();
				}
			});
			mRotationMeasurement.setDocumentation("\r\n * Measurement that represents the change of the orientation over time, e.g. the rotation. \r\n * This could be created by a gyroscope\r\n ");
			//Annotations for RotationMeasurement
			mRotationMeasurement.createAnnotation("struct");
		
		mTemperatureMeasurement = UMetaBuilder.manual().createClass("TemperatureMeasurement", false, TemperatureMeasurement.class, TemperatureMeasurementImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mTemperatureMeasurement, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new TemperatureMeasurementImpl();
				}
			});
			mTemperatureMeasurement.setDocumentation(" Measurement containing the temperature of something ");
			//Annotations for TemperatureMeasurement
			mTemperatureMeasurement.createAnnotation("struct");
		
		mDistanceMeasurement = UMetaBuilder.manual().createClass("DistanceMeasurement", false, DistanceMeasurement.class, DistanceMeasurementImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mDistanceMeasurement, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new DistanceMeasurementImpl();
				}
			});
			//Annotations for DistanceMeasurement
			mDistanceMeasurement.createAnnotation("struct");
		
		mPositionMeasurement = UMetaBuilder.manual().createClass("PositionMeasurement", false, PositionMeasurement.class, PositionMeasurementImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mPositionMeasurement, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new PositionMeasurementImpl();
				}
			});
			mPositionMeasurement.setDocumentation(" The position Measurement differs from the LocationMeasurement in that form, that it measures the position\r\n * of an external object. Within the maritime domain such a measurement may be generated by an AIS sensor but also \r\n * by an Radar (reasoned from the own position of the radar and a DistanceMeasurement) \r\n ");
			//Annotations for PositionMeasurement
			mPositionMeasurement.createAnnotation("struct");
		
		mObjectMeasurement = UMetaBuilder.manual().createClass("ObjectMeasurement", false, ObjectMeasurement.class, ObjectMeasurementImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mObjectMeasurement, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ObjectMeasurementImpl();
				}
			});
			mObjectMeasurement.setDocumentation(" The position Measurement differs from the LocationMeasurement in that form, that it measures the position\r\n * of an external object. Within the maritime domain such a measurement may be generated by an AIS sensor but also \r\n * by an Radar (reasoned from the own position of the radar and a DistanceMeasurement) \r\n ");
			//Annotations for ObjectMeasurement
			mObjectMeasurement.createAnnotation("struct");
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of LocationMeasurement
			mLocationMeasurement_location = UMetaBuilder.manual().createFeature("location", SpatialPackage.theInstance.getCoordinate(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLocationMeasurement_location, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((LocationMeasurement)instance).getLocation(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((LocationMeasurement)instance).setLocation((Coordinate)value); } }
				);
			
			//Features of OrientationMeasurement
			mOrientationMeasurement_orientation = UMetaBuilder.manual().createFeature("orientation", UnitsPackage.theInstance.getOrientation(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mOrientationMeasurement_orientation, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((OrientationMeasurement)instance).getOrientation(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((OrientationMeasurement)instance).setOrientation((Orientation)value); } }
				);
			
			//Features of VelocityMeasurement
			mVelocityMeasurement_velocity = UMetaBuilder.manual().createFeature("velocity", UnitsPackage.theInstance.getVelocity(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVelocityMeasurement_velocity, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((VelocityMeasurement)instance).getVelocity(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((VelocityMeasurement)instance).setVelocity((Velocity)value); } }
				);
			
			//Features of RotationMeasurement
			mRotationMeasurement_velocity = UMetaBuilder.manual().createFeature("velocity", UnitsPackage.theInstance.getAngularVelocity(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRotationMeasurement_velocity, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((RotationMeasurement)instance).getVelocity(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((RotationMeasurement)instance).setVelocity((AngularVelocity)value); } }
				);
			
			//Features of TemperatureMeasurement
			mTemperatureMeasurement_temperature = UMetaBuilder.manual().createFeature("temperature", UnitsPackage.theInstance.getTemperature(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTemperatureMeasurement_temperature, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((TemperatureMeasurement)instance).getTemperature(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((TemperatureMeasurement)instance).setTemperature((Temperature)value); } }
				);
			
			//Features of DistanceMeasurement
			mDistanceMeasurement_distance = UMetaBuilder.manual().createFeature("distance", UnitsPackage.theInstance.getDistance(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mDistanceMeasurement_distance, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((DistanceMeasurement)instance).getDistance(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((DistanceMeasurement)instance).setDistance((Distance)value); } }
				);
			
			//Features of PositionMeasurement
			mPositionMeasurement_object = UMetaBuilder.manual().createFeature("object", PhysicsPackage.theInstance.getLocatableObject(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mPositionMeasurement_object, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((PositionMeasurement)instance).getObject(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((PositionMeasurement)instance).setObject((LocatableObject)value); } }
				);
			
			//Features of ObjectMeasurement
			mObjectMeasurement_object = UMetaBuilder.manual().createFeature("object", CorePackage.theInstance.getIdentifiedObject(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mObjectMeasurement_object, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ObjectMeasurement)instance).getObject(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ObjectMeasurement)instance).setObject((IdentifiedObject)value); } }
				);
			
		}
		{ //assign features
			mLocationMeasurement.getStructuralFeatures().add(mLocationMeasurement_location);
			mOrientationMeasurement.getStructuralFeatures().add(mOrientationMeasurement_orientation);
			mVelocityMeasurement.getStructuralFeatures().add(mVelocityMeasurement_velocity);
			mRotationMeasurement.getStructuralFeatures().add(mRotationMeasurement_velocity);
			mTemperatureMeasurement.getStructuralFeatures().add(mTemperatureMeasurement_temperature);
			mDistanceMeasurement.getStructuralFeatures().add(mDistanceMeasurement_distance);
			mPositionMeasurement.getStructuralFeatures().add(mPositionMeasurement_object);
			mObjectMeasurement.getStructuralFeatures().add(mObjectMeasurement_object);
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
		mLocationMeasurement.setSuperType(SensePackage.theInstance.getMeasurement());
		mOrientationMeasurement.setSuperType(SensePackage.theInstance.getMeasurement());
		mVelocityMeasurement.setSuperType(SensePackage.theInstance.getMeasurement());
		mRotationMeasurement.setSuperType(SensePackage.theInstance.getMeasurement());
		mTemperatureMeasurement.setSuperType(SensePackage.theInstance.getMeasurement());
		mDistanceMeasurement.setSuperType(SensePackage.theInstance.getMeasurement());
		mPositionMeasurement.setSuperType(SensePackage.theInstance.getMeasurement());
		mObjectMeasurement.setSuperType(SensePackage.theInstance.getMeasurement());
		
	}



	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getLocationMeasurement(){
		if (mLocationMeasurement == null){
			mLocationMeasurement = UCoreMetaRepository.getUClass(LocationMeasurement.class);
		}
		return mLocationMeasurement;
	}
	/**
	* @generated
	*/
	public UClass getOrientationMeasurement(){
		if (mOrientationMeasurement == null){
			mOrientationMeasurement = UCoreMetaRepository.getUClass(OrientationMeasurement.class);
		}
		return mOrientationMeasurement;
	}
	/**
	* @generated
	*/
	public UClass getVelocityMeasurement(){
		if (mVelocityMeasurement == null){
			mVelocityMeasurement = UCoreMetaRepository.getUClass(VelocityMeasurement.class);
		}
		return mVelocityMeasurement;
	}
	/**
	* @generated
	*/
	public UClass getRotationMeasurement(){
		if (mRotationMeasurement == null){
			mRotationMeasurement = UCoreMetaRepository.getUClass(RotationMeasurement.class);
		}
		return mRotationMeasurement;
	}
	/**
	* @generated
	*/
	public UClass getTemperatureMeasurement(){
		if (mTemperatureMeasurement == null){
			mTemperatureMeasurement = UCoreMetaRepository.getUClass(TemperatureMeasurement.class);
		}
		return mTemperatureMeasurement;
	}
	/**
	* @generated
	*/
	public UClass getDistanceMeasurement(){
		if (mDistanceMeasurement == null){
			mDistanceMeasurement = UCoreMetaRepository.getUClass(DistanceMeasurement.class);
		}
		return mDistanceMeasurement;
	}
	/**
	* @generated
	*/
	public UClass getPositionMeasurement(){
		if (mPositionMeasurement == null){
			mPositionMeasurement = UCoreMetaRepository.getUClass(PositionMeasurement.class);
		}
		return mPositionMeasurement;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getLocationMeasurement_location(){
		if (mLocationMeasurement_location == null)
			mLocationMeasurement_location = getLocationMeasurement().getFeature("location");
		return mLocationMeasurement_location;
	}



	/**
	* @generated
	*/
	public UClass getObjectMeasurement(){
		if (mObjectMeasurement == null){
			mObjectMeasurement = UCoreMetaRepository.getUClass(ObjectMeasurement.class);
		}
		return mObjectMeasurement;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getOrientationMeasurement_orientation(){
		if (mOrientationMeasurement_orientation == null)
			mOrientationMeasurement_orientation = getOrientationMeasurement().getFeature("orientation");
		return mOrientationMeasurement_orientation;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVelocityMeasurement_velocity(){
		if (mVelocityMeasurement_velocity == null)
			mVelocityMeasurement_velocity = getVelocityMeasurement().getFeature("velocity");
		return mVelocityMeasurement_velocity;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getRotationMeasurement_velocity(){
		if (mRotationMeasurement_velocity == null)
			mRotationMeasurement_velocity = getRotationMeasurement().getFeature("velocity");
		return mRotationMeasurement_velocity;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTemperatureMeasurement_temperature(){
		if (mTemperatureMeasurement_temperature == null)
			mTemperatureMeasurement_temperature = getTemperatureMeasurement().getFeature("temperature");
		return mTemperatureMeasurement_temperature;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getDistanceMeasurement_distance(){
		if (mDistanceMeasurement_distance == null)
			mDistanceMeasurement_distance = getDistanceMeasurement().getFeature("distance");
		return mDistanceMeasurement_distance;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getPositionMeasurement_object(){
		if (mPositionMeasurement_object == null)
			mPositionMeasurement_object = getPositionMeasurement().getFeature("object");
		return mPositionMeasurement_object;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getObjectMeasurement_object(){
		if (mObjectMeasurement_object == null)
			mObjectMeasurement_object = getObjectMeasurement().getFeature("object");
		return mObjectMeasurement_object;
	}
}
