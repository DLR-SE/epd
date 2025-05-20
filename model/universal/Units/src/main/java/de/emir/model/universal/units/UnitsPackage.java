package de.emir.model.universal.units;

import de.emir.model.universal.CRSModel;
import de.emir.model.universal.CoreModel;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.math.MathPackage;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.math.Vector2D;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.model.universal.math.Vector3D;
import de.emir.model.universal.units.impl.AccelerationImpl;
import de.emir.model.universal.units.impl.AccelerationUnitImpl;
import de.emir.model.universal.units.impl.AngleImpl;
import de.emir.model.universal.units.impl.AngularAccelerationImpl;
import de.emir.model.universal.units.impl.AngularAccelerationUnitImpl;
import de.emir.model.universal.units.impl.AngularSpeedImpl;
import de.emir.model.universal.units.impl.AngularSpeedUnitImpl;
import de.emir.model.universal.units.impl.AngularVelocityImpl;
import de.emir.model.universal.units.impl.ColorImpl;
import de.emir.model.universal.units.impl.DirectedMeasureImpl;
import de.emir.model.universal.units.impl.DistanceImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.model.universal.units.impl.LengthImpl;
import de.emir.model.universal.units.impl.MassImpl;
import de.emir.model.universal.units.impl.MeasureImpl;
import de.emir.model.universal.math.Matrix3;
import de.emir.model.universal.units.impl.PercentageImpl;
import de.emir.model.universal.units.impl.QuaternionImpl;
import de.emir.model.universal.units.impl.RGBColorImpl;
import de.emir.model.universal.units.impl.SpeedImpl;
import de.emir.model.universal.units.Acceleration;
import de.emir.model.universal.units.AngularAcceleration;
import de.emir.model.universal.units.AccelerationUnit;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.AngularSpeedUnit;
import de.emir.model.universal.units.AngularAccelerationUnit;
import de.emir.model.universal.units.AngleUnit;
import de.emir.model.universal.units.AngularVelocity;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.impl.SpeedUnitImpl;
import de.emir.model.universal.units.impl.TemperatureImpl;
import de.emir.model.universal.units.DirectedMeasure;
import de.emir.model.universal.units.Color;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Mass;
import de.emir.model.universal.units.Orientation;
import de.emir.model.universal.units.Euler;
import de.emir.model.universal.units.PowerUnit;
import de.emir.model.universal.units.impl.TimeImpl;
import de.emir.model.universal.units.PredefinedColour;
import de.emir.model.universal.units.Percentage;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.impl.VelocityImpl;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.impl.VolumeImpl;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.model.universal.units.MassUnit;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.model.universal.units.RGBColor;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.Power;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.model.universal.units.Measure;
import de.emir.model.universal.units.VolumeUnit;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.model.universal.units.Temperature;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.model.universal.units.TimeUnit;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.model.universal.units.Quaternion;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.model.universal.units.Rotation;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.Velocity;
import de.emir.model.universal.units.TemperatureUnit;
import de.emir.model.universal.units.Volume;
import de.emir.model.universal.units.impl.PowerImpl;

/**

 * This package is based on the ISO 19103 Conceptual Schema Language - Package Units of Measure
 * There is a small difference in handling units, where units may be realized as enumerations instead of complex types. 
 * @generated 
 */
public class UnitsPackage  
{
	
	/**
	 * @generated
	 */
	public static UnitsPackage theInstance = new UnitsPackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier Measure
		*/
		UClass Measure = UnitsPackage.theInstance.getMeasure();
		/**
		* @generated
		* @return meta type for classifier DirectedMeasure
		*/
		UClass DirectedMeasure = UnitsPackage.theInstance.getDirectedMeasure();
		/**
		* @generated
		* @return meta type for enumeration DistanceUnit
		*/
		UEnum DistanceUnit = UnitsPackage.theInstance.getDistanceUnit();
		/**
		* @generated
		* @return meta type for classifier Length
		*/
		UClass Length = UnitsPackage.theInstance.getLength();
		/**
		* @generated
		* @return meta type for classifier Distance
		*/
		UClass Distance = UnitsPackage.theInstance.getDistance();
		/**
		* @generated
		* @return meta type for enumeration VolumeUnit
		*/
		UEnum VolumeUnit = UnitsPackage.theInstance.getVolumeUnit();
		/**
		* @generated
		* @return meta type for classifier Volume
		*/
		UClass Volume = UnitsPackage.theInstance.getVolume();
		/**
		* @generated
		* @return meta type for enumeration TemperatureUnit
		*/
		UEnum TemperatureUnit = UnitsPackage.theInstance.getTemperatureUnit();
		/**
		* @generated
		* @return meta type for classifier Temperature
		*/
		UClass Temperature = UnitsPackage.theInstance.getTemperature();
		/**
		* @generated
		* @return meta type for enumeration AngleUnit
		*/
		UEnum AngleUnit = UnitsPackage.theInstance.getAngleUnit();
		/**
		* @generated
		* @return meta type for classifier Percentage
		*/
		UClass Percentage = UnitsPackage.theInstance.getPercentage();
		/**
		* @generated
		* @return meta type for classifier Angle
		*/
		UClass Angle = UnitsPackage.theInstance.getAngle();
		/**
		* @generated
		* @return meta type for enumeration TimeUnit
		*/
		UEnum TimeUnit = UnitsPackage.theInstance.getTimeUnit();
		/**
		* @generated
		* @return meta type for classifier Time
		*/
		UClass Time = UnitsPackage.theInstance.getTime();
		/**
		* @generated
		* @return meta type for classifier SpeedUnit
		*/
		UClass SpeedUnit = UnitsPackage.theInstance.getSpeedUnit();
		/**
		* @generated
		* @return meta type for classifier Speed
		*/
		UClass Speed = UnitsPackage.theInstance.getSpeed();
		/**
		* @generated
		* @return meta type for classifier AngularSpeedUnit
		*/
		UClass AngularSpeedUnit = UnitsPackage.theInstance.getAngularSpeedUnit();
		/**
		* @generated
		* @return meta type for classifier AngularSpeed
		*/
		UClass AngularSpeed = UnitsPackage.theInstance.getAngularSpeed();
		/**
		* @generated
		* @return meta type for classifier AccelerationUnit
		*/
		UClass AccelerationUnit = UnitsPackage.theInstance.getAccelerationUnit();
		/**
		* @generated
		* @return meta type for classifier Acceleration
		*/
		UClass Acceleration = UnitsPackage.theInstance.getAcceleration();
		/**
		* @generated
		* @return meta type for classifier AngularAccelerationUnit
		*/
		UClass AngularAccelerationUnit = UnitsPackage.theInstance.getAngularAccelerationUnit();
		/**
		* @generated
		* @return meta type for classifier AngularAcceleration
		*/
		UClass AngularAcceleration = UnitsPackage.theInstance.getAngularAcceleration();
		/**
		* @generated
		* @return meta type for enumeration MassUnit
		*/
		UEnum MassUnit = UnitsPackage.theInstance.getMassUnit();
		/**
		* @generated
		* @return meta type for classifier Mass
		*/
		UClass Mass = UnitsPackage.theInstance.getMass();
		/**
		 * @generated
		 * @return meta type for interface Orientation
		 */
		UInterface Orientation = UnitsPackage.theInstance.getOrientation();
		/**
		 * @generated
		 * @return meta type for interface Rotation
		 */
		UInterface Rotation = UnitsPackage.theInstance.getRotation();
		/**
		* @generated
		* @return meta type for classifier Euler
		*/
		UClass Euler = UnitsPackage.theInstance.getEuler();
		/**
		* @generated
		* @return meta type for classifier Quaternion
		*/
		UClass Quaternion = UnitsPackage.theInstance.getQuaternion();
		/**
		* @generated
		* @return meta type for classifier Velocity
		*/
		UClass Velocity = UnitsPackage.theInstance.getVelocity();
		/**
		* @generated
		* @return meta type for classifier AngularVelocity
		*/
		UClass AngularVelocity = UnitsPackage.theInstance.getAngularVelocity();
		/**
		* @generated
		* @return meta type for enumeration PowerUnit
		*/
		UEnum PowerUnit = UnitsPackage.theInstance.getPowerUnit();
		/**
		* @generated
		* @return meta type for classifier Power
		*/
		UClass Power = UnitsPackage.theInstance.getPower();
		/**
		* @generated
		* @return meta type for classifier RGBColor
		*/
		UClass RGBColor = UnitsPackage.theInstance.getRGBColor();
		/**
		* @generated
		* @return meta type for enumeration PredefinedColour
		*/
		UEnum PredefinedColour = UnitsPackage.theInstance.getPredefinedColour();
		/**
		* @generated
		* @return meta type for classifier Color
		*/
		UClass Color = UnitsPackage.theInstance.getColor();
		
		/**
		 * @generated
		 * @return feature descriptor value in type Measure
		 */
		 UStructuralFeature Measure_value = UnitsPackage.theInstance.getMeasure_value();
		/**
		 * @generated
		 * @return feature descriptor unit in type Length
		 */
		 UStructuralFeature Length_unit = UnitsPackage.theInstance.getLength_unit();
		/**
		 * @generated
		 * @return feature descriptor unit in type Volume
		 */
		 UStructuralFeature Volume_unit = UnitsPackage.theInstance.getVolume_unit();
		/**
		 * @generated
		 * @return feature descriptor unit in type Angle
		 */
		 UStructuralFeature Angle_unit = UnitsPackage.theInstance.getAngle_unit();
		/**
		 * @generated
		 * @return feature descriptor unit in type Time
		 */
		 UStructuralFeature Time_unit = UnitsPackage.theInstance.getTime_unit();
		/**
		 * @generated
		 * @return feature descriptor unit in type Temperature
		 */
		 UStructuralFeature Temperature_unit = UnitsPackage.theInstance.getTemperature_unit();
		/**
		 * @generated
		 * @return feature descriptor distanceUnit in type SpeedUnit
		 */
		 UStructuralFeature SpeedUnit_distanceUnit = UnitsPackage.theInstance.getSpeedUnit_distanceUnit();
		/**
		 * @generated
		 * @return feature descriptor timeUnit in type SpeedUnit
		 */
		 UStructuralFeature SpeedUnit_timeUnit = UnitsPackage.theInstance.getSpeedUnit_timeUnit();
		/**
		 * @generated
		 * @return feature descriptor unit in type Speed
		 */
		 UStructuralFeature Speed_unit = UnitsPackage.theInstance.getSpeed_unit();
		/**
		 * @generated
		 * @return feature descriptor angleUnit in type AngularSpeedUnit
		 */
		 UStructuralFeature AngularSpeedUnit_angleUnit = UnitsPackage.theInstance.getAngularSpeedUnit_angleUnit();
		/**
		 * @generated
		 * @return feature descriptor timeUnit in type AngularSpeedUnit
		 */
		 UStructuralFeature AngularSpeedUnit_timeUnit = UnitsPackage.theInstance.getAngularSpeedUnit_timeUnit();
		/**
		 * @generated
		 * @return feature descriptor unit in type AngularSpeed
		 */
		 UStructuralFeature AngularSpeed_unit = UnitsPackage.theInstance.getAngularSpeed_unit();
		/**
		 * @generated
		 * @return feature descriptor timeUnit in type AccelerationUnit
		 */
		 UStructuralFeature AccelerationUnit_timeUnit = UnitsPackage.theInstance.getAccelerationUnit_timeUnit();
		/**
		 * @generated
		 * @return feature descriptor speedUnit in type AccelerationUnit
		 */
		 UStructuralFeature AccelerationUnit_speedUnit = UnitsPackage.theInstance.getAccelerationUnit_speedUnit();
		/**
		 * @generated
		 * @return feature descriptor unit in type Acceleration
		 */
		 UStructuralFeature Acceleration_unit = UnitsPackage.theInstance.getAcceleration_unit();
		/**
		 * @generated
		 * @return feature descriptor timeUnit in type AngularAccelerationUnit
		 */
		 UStructuralFeature AngularAccelerationUnit_timeUnit = UnitsPackage.theInstance.getAngularAccelerationUnit_timeUnit();
		/**
		 * @generated
		 * @return feature descriptor speedUnit in type AngularAccelerationUnit
		 */
		 UStructuralFeature AngularAccelerationUnit_speedUnit = UnitsPackage.theInstance.getAngularAccelerationUnit_speedUnit();
		/**
		 * @generated
		 * @return feature descriptor unit in type AngularAcceleration
		 */
		 UStructuralFeature AngularAcceleration_unit = UnitsPackage.theInstance.getAngularAcceleration_unit();
		/**
		 * @generated
		 * @return feature descriptor unit in type Mass
		 */
		 UStructuralFeature Mass_unit = UnitsPackage.theInstance.getMass_unit();
		/**
		 * @generated
		 * @return feature descriptor x in type Euler
		 */
		 UStructuralFeature Euler_x = UnitsPackage.theInstance.getEuler_x();
		/**
		 * @generated
		 * @return feature descriptor y in type Euler
		 */
		 UStructuralFeature Euler_y = UnitsPackage.theInstance.getEuler_y();
		/**
		 * @generated
		 * @return feature descriptor z in type Euler
		 */
		 UStructuralFeature Euler_z = UnitsPackage.theInstance.getEuler_z();
		/**
		 * @generated
		 * @return feature descriptor x in type Quaternion
		 */
		 UStructuralFeature Quaternion_x = UnitsPackage.theInstance.getQuaternion_x();
		/**
		 * @generated
		 * @return feature descriptor y in type Quaternion
		 */
		 UStructuralFeature Quaternion_y = UnitsPackage.theInstance.getQuaternion_y();
		/**
		 * @generated
		 * @return feature descriptor z in type Quaternion
		 */
		 UStructuralFeature Quaternion_z = UnitsPackage.theInstance.getQuaternion_z();
		/**
		 * @generated
		 * @return feature descriptor w in type Quaternion
		 */
		 UStructuralFeature Quaternion_w = UnitsPackage.theInstance.getQuaternion_w();
		/**
		 * @generated
		 * @return feature descriptor value in type Velocity
		 */
		 UStructuralFeature Velocity_value = UnitsPackage.theInstance.getVelocity_value();
		/**
		 * @generated
		 * @return feature descriptor unit in type Velocity
		 */
		 UStructuralFeature Velocity_unit = UnitsPackage.theInstance.getVelocity_unit();
		/**
		 * @generated
		 * @return feature descriptor crs in type Velocity
		 */
		 UStructuralFeature Velocity_crs = UnitsPackage.theInstance.getVelocity_crs();
		/**
		 * @generated
		 * @return feature descriptor value in type AngularVelocity
		 */
		 UStructuralFeature AngularVelocity_value = UnitsPackage.theInstance.getAngularVelocity_value();
		/**
		 * @generated
		 * @return feature descriptor unit in type AngularVelocity
		 */
		 UStructuralFeature AngularVelocity_unit = UnitsPackage.theInstance.getAngularVelocity_unit();
		/**
		 * @generated
		 * @return feature descriptor crs in type AngularVelocity
		 */
		 UStructuralFeature AngularVelocity_crs = UnitsPackage.theInstance.getAngularVelocity_crs();
		/**
		 * @generated
		 * @return feature descriptor r in type RGBColor
		 */
		 UStructuralFeature RGBColor_r = UnitsPackage.theInstance.getRGBColor_r();
		/**
		 * @generated
		 * @return feature descriptor unit in type Power
		 */
		 UStructuralFeature Power_unit = UnitsPackage.theInstance.getPower_unit();
		/**
		 * @generated
		 * @return feature descriptor g in type RGBColor
		 */
		 UStructuralFeature RGBColor_g = UnitsPackage.theInstance.getRGBColor_g();
		/**
		 * @generated
		 * @return feature descriptor b in type RGBColor
		 */
		 UStructuralFeature RGBColor_b = UnitsPackage.theInstance.getRGBColor_b();
		/**
		 * @generated
		 * @return feature descriptor a in type RGBColor
		 */
		 UStructuralFeature RGBColor_a = UnitsPackage.theInstance.getRGBColor_a();
		/**
		 * @generated
		 * @return feature descriptor color in type Color
		 */
		 UStructuralFeature Color_color = UnitsPackage.theInstance.getColor_color();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mMeasure = null;
	/**
	* @generated
	*/
	private UClass mDirectedMeasure = null;
	/**
	* @generated
	*/
	private UEnum mDistanceUnit = null;
	/**
	* @generated
	*/
	private UClass mLength = null;
	/**
	* @generated
	*/
	private UClass mDistance = null;
	/**
	* @generated
	*/
	private UEnum mVolumeUnit = null;
	/**
	* @generated
	*/
	private UClass mVolume = null;
	/**
	* @generated
	*/
	private UEnum mTemperatureUnit = null;
	/**
	* @generated
	*/
	private UClass mTemperature = null;
	/**
	* @generated
	*/
	private UEnum mAngleUnit = null;
	/**
	* @generated
	*/
	private UClass mPercentage = null;
	/**
	* @generated
	*/
	private UClass mAngle = null;
	/**
	* @generated
	*/
	private UEnum mTimeUnit = null;
	/**
	* @generated
	*/
	private UClass mTime = null;
	/**
	* @generated
	*/
	private UClass mSpeedUnit = null;
	/**
	* @generated
	*/
	private UClass mSpeed = null;
	/**
	* @generated
	*/
	private UClass mAngularSpeedUnit = null;
	/**
	* @generated
	*/
	private UClass mAngularSpeed = null;
	/**
	* @generated
	*/
	private UClass mAccelerationUnit = null;
	/**
	* @generated
	*/
	private UClass mAcceleration = null;
	/**
	* @generated
	*/
	private UClass mAngularAccelerationUnit = null;
	/**
	* @generated
	*/
	private UClass mAngularAcceleration = null;
	/**
	* @generated
	*/
	private UEnum mMassUnit = null;
	/**
	* @generated
	*/
	private UClass mMass = null;
	/**
	 * @generated
	 */
	private UInterface mOrientation = null;
	/**
	 * @generated
	 */
	private UInterface mRotation = null;
	/**
	* @generated
	*/
	private UClass mEuler = null;
	/**
	* @generated
	*/
	private UClass mQuaternion = null;
	/**
	* @generated
	*/
	private UClass mVelocity = null;
	/**
	* @generated
	*/
	private UClass mAngularVelocity = null;
	/**
	* @generated
	*/
	private UEnum mPowerUnit = null;
	/**
	* @generated
	*/
	private UClass mPower = null;
	/**
	* @generated
	*/
	private UClass mRGBColor = null;
	/**
	* @generated
	*/
	private UEnum mPredefinedColour = null;
	/**
	* @generated
	*/
	private UClass mColor = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	//Features for classifier Measure
	/**
	 * @generated
	 */
	private UStructuralFeature mMeasure_value = null;
	
	
	//Features for classifier Length
	/**
	 * @generated
	 */
	private UStructuralFeature mLength_unit = null;
	
	
	
	//Features for classifier Volume
	/**
	 * @generated
	 */
	private UStructuralFeature mVolume_unit = null;
	
	//Features for classifier Angle
	/**
	 * @generated
	 */
	private UStructuralFeature mAngle_unit = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mTemperature_unit = null;
	
	//Features for classifier Time
	/**
	 * @generated
	 */
	private UStructuralFeature mTime_unit = null;
	
	//Features for classifier SpeedUnit
	/**
	 * @generated
	 */
	private UStructuralFeature mSpeedUnit_distanceUnit = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mSpeedUnit_timeUnit = null;
	
	//Features for classifier Speed
	/**
	 * @generated
	 */
	private UStructuralFeature mSpeed_unit = null;
	
	//Features for classifier AngularSpeedUnit
	/**
	 * @generated
	 */
	private UStructuralFeature mAngularSpeedUnit_angleUnit = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAngularSpeedUnit_timeUnit = null;
	
	//Features for classifier AngularSpeed
	/**
	 * @generated
	 */
	private UStructuralFeature mAngularSpeed_unit = null;
	
	//Features for classifier AccelerationUnit
	/**
	 * @generated
	 */
	private UStructuralFeature mAccelerationUnit_timeUnit = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAccelerationUnit_speedUnit = null;
	
	//Features for classifier Acceleration
	/**
	 * @generated
	 */
	private UStructuralFeature mAcceleration_unit = null;
	
	//Features for classifier AngularAccelerationUnit
	/**
	 * @generated
	 */
	private UStructuralFeature mAngularAccelerationUnit_timeUnit = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAngularAccelerationUnit_speedUnit = null;
	
	//Features for classifier AngularAcceleration
	/**
	 * @generated
	 */
	private UStructuralFeature mAngularAcceleration_unit = null;
	
	//Features for classifier Mass
	/**
	 * @generated
	 */
	private UStructuralFeature mMass_unit = null;
	
	//Features for classifier Euler
	/**
	 * @generated
	 */
	private UStructuralFeature mEuler_x = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mEuler_y = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mEuler_z = null;
	
	//Features for classifier Quaternion
	/**
	 * @generated
	 */
	private UStructuralFeature mQuaternion_x = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mQuaternion_y = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mQuaternion_z = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mQuaternion_w = null;
	
	//Features for classifier Velocity
	/**
	 * @generated
	 */
	private UStructuralFeature mVelocity_value = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mVelocity_unit = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mVelocity_crs = null;
	
	//Features for classifier AngularVelocity
	/**
	 * @generated
	 */
	private UStructuralFeature mAngularVelocity_value = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAngularVelocity_unit = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAngularVelocity_crs = null;
	
	//Features for classifier RGBColor
	/**
	 * @generated
	 */
	private UStructuralFeature mRGBColor_r = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mRGBColor_g = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mPower_unit = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mRGBColor_b = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mRGBColor_a = null;
	
	//Features for classifier Color
	/**
	 * @generated
	 */
	private UStructuralFeature mColor_color = null;
	
	/**
	 * @generated
	 */
	public static UnitsPackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package UnitsPackage ...");
		theInstance = new UnitsPackage();
		//initialize referenced models
		CRSModel.init();
		CoreModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.universal.units");
		p.getContent().add(theInstance.mMeasure);
		p.getContent().add(theInstance.mDirectedMeasure);
		p.getContent().add(theInstance.mDistanceUnit);
		p.getContent().add(theInstance.mLength);
		p.getContent().add(theInstance.mDistance);
		p.getContent().add(theInstance.mVolumeUnit);
		p.getContent().add(theInstance.mVolume);
		p.getContent().add(theInstance.mTemperatureUnit);
		p.getContent().add(theInstance.mTemperature);
		p.getContent().add(theInstance.mAngleUnit);
		p.getContent().add(theInstance.mPercentage);
		p.getContent().add(theInstance.mAngle);
		p.getContent().add(theInstance.mTimeUnit);
		p.getContent().add(theInstance.mTime);
		p.getContent().add(theInstance.mSpeedUnit);
		p.getContent().add(theInstance.mSpeed);
		p.getContent().add(theInstance.mAngularSpeedUnit);
		p.getContent().add(theInstance.mAngularSpeed);
		p.getContent().add(theInstance.mAccelerationUnit);
		p.getContent().add(theInstance.mAcceleration);
		p.getContent().add(theInstance.mAngularAccelerationUnit);
		p.getContent().add(theInstance.mAngularAcceleration);
		p.getContent().add(theInstance.mMassUnit);
		p.getContent().add(theInstance.mMass);
		p.getContent().add(theInstance.mOrientation);
		p.getContent().add(theInstance.mRotation);
		p.getContent().add(theInstance.mEuler);
		p.getContent().add(theInstance.mQuaternion);
		p.getContent().add(theInstance.mVelocity);
		p.getContent().add(theInstance.mAngularVelocity);
		p.getContent().add(theInstance.mPowerUnit);
		p.getContent().add(theInstance.mPower);
		p.getContent().add(theInstance.mRGBColor);
		p.getContent().add(theInstance.mPredefinedColour);
		p.getContent().add(theInstance.mColor);
		p.freeze();
		
		
		
		ULog.debug("... package UnitsPackage initialized");
		
		return theInstance;
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mMeasure = UMetaBuilder.manual().createClass("Measure", true, Measure.class, MeasureImpl.class);
			mMeasure.setDocumentation("\r\n * The result from performing the act or process of ascertaining the extent, dimensions, or quantity of some entity.\r\n * A measure is always associated to a unit of measure. Ratio measures where the two base measures are in the same units (often considered unit-less) should be associated to UnitOfMeasure (same meter/meter for map scale) so that conversions to non-unitless ratios can be accomplished (such as miles/inch).\r\n ");
			//Annotations for Measure
			mMeasure.createAnnotation("struct");
		
		mDirectedMeasure = UMetaBuilder.manual().createClass("DirectedMeasure", true, DirectedMeasure.class, DirectedMeasureImpl.class);
			//Annotations for DirectedMeasure
			mDirectedMeasure.createAnnotation("struct");
		
		mDistanceUnit = UMetaBuilder.manual().createEnumeration("DistanceUnit", DistanceUnit.class);
			mDistanceUnit.setDocumentation("\r\n * Any of the measuring systems to measure the length, distance between two entities.  \r\n * Example are the English System of feet and inches or the metric system of  millimeters, centimeters and meters, \r\n * also the System International (SI) System of Units.\r\n ");
		
		mLength = UMetaBuilder.manual().createClass("Length", false, Length.class, LengthImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mLength, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new LengthImpl();
				}
			});
			mLength.setDocumentation(" \r\n * The measure of distance as an integral, i.e. the limit of an infinite sum of distances between points on a curve.  For example the length of curve, the perimeter of a polygon as the length of the boundary.\r\n ");
			//Annotations for Length
			mLength.createAnnotation("struct");
		
		mDistance = UMetaBuilder.manual().createClass("Distance", false, Distance.class, DistanceImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mDistance, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new DistanceImpl();
				}
			});
			mDistance.setDocumentation("\r\n * Used as a type for returning distances and possibly lengths. Care must be taken when using distance where length is meant. The distance from start to end of a curve is not the length of the curve, but represents the length of the shortest curve between these two points. Since Distance is a length of some curve (albeit a hypothetical one), the unit of measure is the same.\r\n ");
			//Annotations for Distance
			mDistance.createAnnotation("struct");
		
		mVolumeUnit = UMetaBuilder.manual().createEnumeration("VolumeUnit", VolumeUnit.class);
		
		mVolume = UMetaBuilder.manual().createClass("Volume", false, Volume.class, VolumeImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mVolume, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new VolumeImpl();
				}
			});
			//Annotations for Volume
			mVolume.createAnnotation("struct");
		
		mTemperatureUnit = UMetaBuilder.manual().createEnumeration("TemperatureUnit", TemperatureUnit.class);
		
		mTemperature = UMetaBuilder.manual().createClass("Temperature", false, Temperature.class, TemperatureImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mTemperature, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new TemperatureImpl();
				}
			});
			//Annotations for Temperature
			mTemperature.createAnnotation("struct");
		
		mAngleUnit = UMetaBuilder.manual().createEnumeration("AngleUnit", AngleUnit.class);
		
		mPercentage = UMetaBuilder.manual().createClass("Percentage", false, Percentage.class, PercentageImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mPercentage, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new PercentageImpl();
				}
			});
			//Annotations for Percentage
			mPercentage.createAnnotation("struct");
		
		mAngle = UMetaBuilder.manual().createClass("Angle", false, Angle.class, AngleImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mAngle, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new AngleImpl();
				}
			});
			mAngle.setDocumentation("\r\n * The amount of rotation needed to bring one line or plane into coincidence with another, generally measured in radians or degrees.\r\n ");
			//Annotations for Angle
			mAngle.createAnnotation("struct");
		
		mTimeUnit = UMetaBuilder.manual().createEnumeration("TimeUnit", TimeUnit.class);
			mTimeUnit.setDocumentation("\r\n * A TimeUnit represents time durations at a given unit of granularity (...). \r\n * A nanosecond is defined as one thousandth of a microsecond, \r\n * a microsecond as one thousandth of a millisecond, \r\n * a millisecond as one thousandth of a second, \r\n * a minute as sixty seconds, \r\n * an hour as sixty minutes, \r\n * and a day as twenty four hours. \r\n * \r\n * @cite[doc. java.util.concurrent] \r\n ");
		
		mTime = UMetaBuilder.manual().createClass("Time", false, Time.class, TimeImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mTime, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new TimeImpl();
				}
			});
			//Annotations for Time
			mTime.createAnnotation("struct");
		
		mSpeedUnit = UMetaBuilder.manual().createClass("SpeedUnit", false, SpeedUnit.class, SpeedUnitImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mSpeedUnit, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new SpeedUnitImpl();
				}
			});
			//Annotations for SpeedUnit
			mSpeedUnit.createAnnotation("struct");
		
		mSpeed = UMetaBuilder.manual().createClass("Speed", false, Speed.class, SpeedImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mSpeed, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new SpeedImpl();
				}
			});
			mSpeed.setDocumentation(" \r\n * Undirected: distance divided by time\r\n * \\source (oriented) ISO 19130\r\n * \\note for the directed version see Velocity\r\n ");
			//Annotations for Speed
			mSpeed.createAnnotation("struct");
		
		mAngularSpeedUnit = UMetaBuilder.manual().createClass("AngularSpeedUnit", false, AngularSpeedUnit.class, AngularSpeedUnitImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mAngularSpeedUnit, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new AngularSpeedUnitImpl();
				}
			});
			//Annotations for AngularSpeedUnit
			mAngularSpeedUnit.createAnnotation("struct");
		
		mAngularSpeed = UMetaBuilder.manual().createClass("AngularSpeed", false, AngularSpeed.class, AngularSpeedImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mAngularSpeed, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new AngularSpeedImpl();
				}
			});
			mAngularSpeed.setDocumentation(" \r\n * Undirected: angular distance divided by time\r\n * \\source (oriented) ISO 19130\r\n * \\note for the directed version see AngularVelocity\r\n ");
			//Annotations for AngularSpeed
			mAngularSpeed.createAnnotation("struct");
		
		mAccelerationUnit = UMetaBuilder.manual().createClass("AccelerationUnit", false, AccelerationUnit.class, AccelerationUnitImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mAccelerationUnit, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new AccelerationUnitImpl();
				}
			});
			//Annotations for AccelerationUnit
			mAccelerationUnit.createAnnotation("struct");
		
		mAcceleration = UMetaBuilder.manual().createClass("Acceleration", false, Acceleration.class, AccelerationImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mAcceleration, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new AccelerationImpl();
				}
			});
			mAcceleration.setDocumentation(" \r\n * Undirected: Acceleration is the rate of change of velocity as a function of time.\r\n * In calculus terms, acceleration is the second derivative of position with respect to time or, alternately, the first derivative of the velocity with respect to time.\r\n * The SI units for acceleration are m / s^2 (meters per second squared or meters per second per second), however it may be defined using other combinations of units, as defined in AccelerationUnit\r\n *    \r\n * \\source (oriented) ISO 19130 - SD_UndirectedAcceleration\r\n * \\note for the directed version see Velocity\r\n ");
			//Annotations for Acceleration
			mAcceleration.createAnnotation("struct");
		
		mAngularAccelerationUnit = UMetaBuilder.manual().createClass("AngularAccelerationUnit", false, AngularAccelerationUnit.class, AngularAccelerationUnitImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mAngularAccelerationUnit, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new AngularAccelerationUnitImpl();
				}
			});
			//Annotations for AngularAccelerationUnit
			mAngularAccelerationUnit.createAnnotation("struct");
		
		mAngularAcceleration = UMetaBuilder.manual().createClass("AngularAcceleration", false, AngularAcceleration.class, AngularAccelerationImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mAngularAcceleration, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new AngularAccelerationImpl();
				}
			});
			mAngularAcceleration.setDocumentation(" \r\n * Undirected: AngularAcceleration is the rate of change of the angular velocity as a function of time.\r\n * In calculus terms, AngularAcceleration is the second derivative of angle with respect to time or, alternately, the first derivative of the angular velocity with respect to time.\r\n * The SI units for AngularAcceleration are rad / s^2 (radians per second squared or radians per second per second), however it may be defined using other combinations of units, as defined in AngularAccelerationUnit\r\n *    \r\n * \\source (oriented) ISO 19130 - SD_UndirectedAngularAcceleration\r\n * \\note for the directed version see AngularVelocity\r\n ");
			//Annotations for AngularAcceleration
			mAngularAcceleration.createAnnotation("struct");
		
		mMassUnit = UMetaBuilder.manual().createEnumeration("MassUnit", MassUnit.class);
		
		mMass = UMetaBuilder.manual().createClass("Mass", false, Mass.class, MassImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mMass, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new MassImpl();
				}
			});
			//Annotations for Mass
			mMass.createAnnotation("struct");
		
		mOrientation = UMetaBuilder.manual().createInterface("Orientation", Orientation.class);
			mOrientation.setDocumentation("\r\n * one's position in relation to true north, to points on the compass, or to a specific place or object.\r\n * \\TODO: again what is the orientation defined? Against North or a specific CoordinateSystem?\r\n ");
		
		mRotation = UMetaBuilder.manual().createInterface("Rotation", Rotation.class);
			mRotation.setDocumentation("\r\n * A rotation normally is an action. It uses the same representation as a orientation.  \r\n ");
		
		mEuler = UMetaBuilder.manual().createClass("Euler", false, Euler.class, EulerImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mEuler, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new EulerImpl();
				}
			});
			//Annotations for Euler
			mEuler.createAnnotation("struct");
		
		mQuaternion = UMetaBuilder.manual().createClass("Quaternion", false, Quaternion.class, QuaternionImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mQuaternion, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new QuaternionImpl();
				}
			});
			//Annotations for Quaternion
			mQuaternion.createAnnotation("struct");
		
		mVelocity = UMetaBuilder.manual().createClass("Velocity", false, Velocity.class, VelocityImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mVelocity, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new VelocityImpl();
				}
			});
			mVelocity.setDocumentation("\r\n * Velocity is the instantaneous rate of change of position with time in a specified direction.  \r\n * The magnitude of the vector representing a value for velocity is expressed in units of measure appropriate for speed.\r\n * \\source ISO 19103 Conceptual Schema Language \r\n ");
			//Annotations for Velocity
			mVelocity.createAnnotation("struct");
		
		mAngularVelocity = UMetaBuilder.manual().createClass("AngularVelocity", false, AngularVelocity.class, AngularVelocityImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mAngularVelocity, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new AngularVelocityImpl();
				}
			});
			mAngularVelocity.setDocumentation("\r\n * AngularVelocity is the instantaneous rate of change of angular displacement with time in a specified direction.\r\n * \\source ISO 19103 Conceptual Schema Language \r\n ");
			//Annotations for AngularVelocity
			mAngularVelocity.createAnnotation("struct");
		
		mPowerUnit = UMetaBuilder.manual().createEnumeration("PowerUnit", PowerUnit.class);
		
		mPower = UMetaBuilder.manual().createClass("Power", false, Power.class, PowerImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mPower, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new PowerImpl();
				}
			});
			//Annotations for Power
			mPower.createAnnotation("struct");
		
		mRGBColor = UMetaBuilder.manual().createClass("RGBColor", false, RGBColor.class, RGBColorImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mRGBColor, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new RGBColorImpl();
				}
			});
			//Annotations for RGBColor
			mRGBColor.createAnnotation("struct");
		
		mPredefinedColour = UMetaBuilder.manual().createEnumeration("PredefinedColour", PredefinedColour.class);
			mPredefinedColour.setDocumentation(" Predefined set of named colors (hex value is annotated)\r\n * \\note the first 13 colors has been sorted as they are sorted in the S57 standard\r\n * to allow a direct mapping between S57 enumeration and enumerated color values\r\n ");
		
		mColor = UMetaBuilder.manual().createClass("Color", false, Color.class, ColorImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mColor, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ColorImpl();
				}
			});
			//Annotations for Color
			mColor.createAnnotation("struct");
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of Measure
			mMeasure_value = UMetaBuilder.manual().createFeature("value", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMeasure_value, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Measure)instance).getValue(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Measure)instance).setValue((double)value); } }
				);
				mMeasure_value.setDocumentation(" The numerical value quantifying the extent or size of some quantity, in the units specified by the associated UnitOfMeasure class.*");
			
			//Features of Length
			mLength_unit = UMetaBuilder.manual().createFeature("unit", UnitsPackage.theInstance.getDistanceUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLength_unit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Length)instance).getUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Length)instance).setUnit((DistanceUnit)value); } }
				);
			
			//Features of Volume
			mVolume_unit = UMetaBuilder.manual().createFeature("unit", UnitsPackage.theInstance.getVolumeUnit(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVolume_unit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Volume)instance).getUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Volume)instance).setUnit((VolumeUnit)value); } }
				);
			
			//Features of Temperature
			mTemperature_unit = UMetaBuilder.manual().createFeature("unit", UnitsPackage.theInstance.getTemperatureUnit(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTemperature_unit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Temperature)instance).getUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Temperature)instance).setUnit((TemperatureUnit)value); } }
				);
			
			//Features of Angle
			mAngle_unit = UMetaBuilder.manual().createFeature("unit", UnitsPackage.theInstance.getAngleUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAngle_unit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Angle)instance).getUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Angle)instance).setUnit((AngleUnit)value); } }
				);
			
			//Features of Time
			mTime_unit = UMetaBuilder.manual().createFeature("unit", UnitsPackage.theInstance.getTimeUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTime_unit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Time)instance).getUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Time)instance).setUnit((TimeUnit)value); } }
				);
			
			//Features of SpeedUnit
			mSpeedUnit_distanceUnit = UMetaBuilder.manual().createFeature("distanceUnit", UnitsPackage.theInstance.getDistanceUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mSpeedUnit_distanceUnit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((SpeedUnit)instance).getDistanceUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((SpeedUnit)instance).setDistanceUnit((DistanceUnit)value); } }
				);
			mSpeedUnit_timeUnit = UMetaBuilder.manual().createFeature("timeUnit", UnitsPackage.theInstance.getTimeUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mSpeedUnit_timeUnit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((SpeedUnit)instance).getTimeUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((SpeedUnit)instance).setTimeUnit((TimeUnit)value); } }
				);
			
			//Features of Speed
			mSpeed_unit = UMetaBuilder.manual().createFeature("unit", UnitsPackage.theInstance.getSpeedUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mSpeed_unit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Speed)instance).getUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Speed)instance).setUnit((SpeedUnit)value); } }
				);
			
			//Features of AngularSpeedUnit
			mAngularSpeedUnit_angleUnit = UMetaBuilder.manual().createFeature("angleUnit", UnitsPackage.theInstance.getAngleUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAngularSpeedUnit_angleUnit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AngularSpeedUnit)instance).getAngleUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AngularSpeedUnit)instance).setAngleUnit((AngleUnit)value); } }
				);
			mAngularSpeedUnit_timeUnit = UMetaBuilder.manual().createFeature("timeUnit", UnitsPackage.theInstance.getTimeUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAngularSpeedUnit_timeUnit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AngularSpeedUnit)instance).getTimeUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AngularSpeedUnit)instance).setTimeUnit((TimeUnit)value); } }
				);
			
			//Features of AngularSpeed
			mAngularSpeed_unit = UMetaBuilder.manual().createFeature("unit", UnitsPackage.theInstance.getAngularSpeedUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAngularSpeed_unit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AngularSpeed)instance).getUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AngularSpeed)instance).setUnit((AngularSpeedUnit)value); } }
				);
			
			//Features of AccelerationUnit
			mAccelerationUnit_timeUnit = UMetaBuilder.manual().createFeature("timeUnit", UnitsPackage.theInstance.getTimeUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAccelerationUnit_timeUnit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AccelerationUnit)instance).getTimeUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AccelerationUnit)instance).setTimeUnit((TimeUnit)value); } }
				);
			mAccelerationUnit_speedUnit = UMetaBuilder.manual().createFeature("speedUnit", UnitsPackage.theInstance.getSpeedUnit(), UAssociationType.COMPOSITION, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAccelerationUnit_speedUnit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AccelerationUnit)instance).getSpeedUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AccelerationUnit)instance).setSpeedUnit((SpeedUnit)value); } }
				);
			
			//Features of Acceleration
			mAcceleration_unit = UMetaBuilder.manual().createFeature("unit", UnitsPackage.theInstance.getAccelerationUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAcceleration_unit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Acceleration)instance).getUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Acceleration)instance).setUnit((AccelerationUnit)value); } }
				);
			
			//Features of AngularAccelerationUnit
			mAngularAccelerationUnit_speedUnit = UMetaBuilder.manual().createFeature("speedUnit", UnitsPackage.theInstance.getAngularSpeedUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAngularAccelerationUnit_speedUnit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AngularAccelerationUnit)instance).getSpeedUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AngularAccelerationUnit)instance).setSpeedUnit((AngularSpeedUnit)value); } }
				);
			mAngularAccelerationUnit_timeUnit = UMetaBuilder.manual().createFeature("timeUnit", UnitsPackage.theInstance.getTimeUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAngularAccelerationUnit_timeUnit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AngularAccelerationUnit)instance).getTimeUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AngularAccelerationUnit)instance).setTimeUnit((TimeUnit)value); } }
				);
			
			//Features of AngularAcceleration
			mAngularAcceleration_unit = UMetaBuilder.manual().createFeature("unit", UnitsPackage.theInstance.getAngularAccelerationUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAngularAcceleration_unit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AngularAcceleration)instance).getUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AngularAcceleration)instance).setUnit((AngularAccelerationUnit)value); } }
				);
			
			//Features of Mass
			mMass_unit = UMetaBuilder.manual().createFeature("unit", UnitsPackage.theInstance.getMassUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMass_unit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Mass)instance).getUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Mass)instance).setUnit((MassUnit)value); } }
				);
			
			//Features of Euler
			mEuler_x = UMetaBuilder.manual().createFeature("x", UnitsPackage.theInstance.getAngle(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEuler_x, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Euler)instance).getX(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Euler)instance).setX((Angle)value); } }
				);
			mEuler_y = UMetaBuilder.manual().createFeature("y", UnitsPackage.theInstance.getAngle(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEuler_y, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Euler)instance).getY(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Euler)instance).setY((Angle)value); } }
				);
			mEuler_z = UMetaBuilder.manual().createFeature("z", UnitsPackage.theInstance.getAngle(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEuler_z, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Euler)instance).getZ(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Euler)instance).setZ((Angle)value); } }
				);
			
			//Features of Quaternion
			mQuaternion_x = UMetaBuilder.manual().createFeature("x", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mQuaternion_x, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Quaternion)instance).getX(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Quaternion)instance).setX((double)value); } }
				);
			mQuaternion_y = UMetaBuilder.manual().createFeature("y", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mQuaternion_y, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Quaternion)instance).getY(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Quaternion)instance).setY((double)value); } }
				);
			mQuaternion_z = UMetaBuilder.manual().createFeature("z", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mQuaternion_z, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Quaternion)instance).getZ(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Quaternion)instance).setZ((double)value); } }
				);
			mQuaternion_w = UMetaBuilder.manual().createFeature("w", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mQuaternion_w, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Quaternion)instance).getW(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Quaternion)instance).setW((double)value); } }
				);
			
			//Features of Velocity
			mVelocity_value = UMetaBuilder.manual().createFeature("value", MathPackage.theInstance.getVector(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVelocity_value, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Velocity)instance).getValue(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Velocity)instance).setValue((Vector)value); } }
				);
			mVelocity_unit = UMetaBuilder.manual().createFeature("unit", UnitsPackage.theInstance.getSpeedUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVelocity_unit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Velocity)instance).getUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Velocity)instance).setUnit((SpeedUnit)value); } }
				);
			mVelocity_crs = UMetaBuilder.manual().createFeature("crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVelocity_crs, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Velocity)instance).getCrs(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Velocity)instance).setCrs((CoordinateReferenceSystem)value); } }
				);
			
			//Features of AngularVelocity
			mAngularVelocity_value = UMetaBuilder.manual().createFeature("value", UnitsPackage.theInstance.getEuler(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAngularVelocity_value, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AngularVelocity)instance).getValue(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AngularVelocity)instance).setValue((Euler)value); } }
				);
			mAngularVelocity_unit = UMetaBuilder.manual().createFeature("unit", UnitsPackage.theInstance.getAngularSpeedUnit(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAngularVelocity_unit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AngularVelocity)instance).getUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AngularVelocity)instance).setUnit((AngularSpeedUnit)value); } }
				); 
			mAngularVelocity_crs = UMetaBuilder.manual().createFeature("crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAngularVelocity_crs, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AngularVelocity)instance).getCrs(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AngularVelocity)instance).setCrs((CoordinateReferenceSystem)value); } }
				);
			
			//Features of Power
			mPower_unit = UMetaBuilder.manual().createFeature("unit", UnitsPackage.theInstance.getPowerUnit(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mPower_unit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Power)instance).getUnit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Power)instance).setUnit((PowerUnit)value); } }
				);
			
			//Features of RGBColor
			mRGBColor_r = UMetaBuilder.manual().createFeature("r", TypeUtils.getPrimitiveType(float.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRGBColor_r, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((RGBColor)instance).getR(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((RGBColor)instance).setR((float)value); } }
				);
			mRGBColor_g = UMetaBuilder.manual().createFeature("g", TypeUtils.getPrimitiveType(float.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRGBColor_g, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((RGBColor)instance).getG(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((RGBColor)instance).setG((float)value); } }
				);
			mRGBColor_b = UMetaBuilder.manual().createFeature("b", TypeUtils.getPrimitiveType(float.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRGBColor_b, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((RGBColor)instance).getB(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((RGBColor)instance).setB((float)value); } }
				);
			mRGBColor_a = UMetaBuilder.manual().createFeature("a", TypeUtils.getPrimitiveType(float.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRGBColor_a, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((RGBColor)instance).getA(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((RGBColor)instance).setA((float)value); } }
				);
				mRGBColor_a.setDocumentation(" Alpha or transparency value a == 1 no transparency; a == 0 : full transparency");
			
			//Features of Color
			mColor_color = UMetaBuilder.manual().createFeature("color", UnitsPackage.theInstance.getPredefinedColour(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mColor_color, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Color)instance).getColor(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Color)instance).setColor((PredefinedColour)value); } }
				);
			
		}
		{ //assign features
			mMeasure.getStructuralFeatures().add(mMeasure_value);
			mLength.getStructuralFeatures().add(mLength_unit);
			mVolume.getStructuralFeatures().add(mVolume_unit);
			mTemperature.getStructuralFeatures().add(mTemperature_unit);
			mAngle.getStructuralFeatures().add(mAngle_unit);
			mTime.getStructuralFeatures().add(mTime_unit);
			mSpeedUnit.getStructuralFeatures().add(mSpeedUnit_distanceUnit);
			mSpeedUnit.getStructuralFeatures().add(mSpeedUnit_timeUnit);
			mSpeed.getStructuralFeatures().add(mSpeed_unit);
			mAngularSpeedUnit.getStructuralFeatures().add(mAngularSpeedUnit_angleUnit);
			mAngularSpeedUnit.getStructuralFeatures().add(mAngularSpeedUnit_timeUnit);
			mAngularSpeed.getStructuralFeatures().add(mAngularSpeed_unit);
			mAccelerationUnit.getStructuralFeatures().add(mAccelerationUnit_timeUnit);
			mAccelerationUnit.getStructuralFeatures().add(mAccelerationUnit_speedUnit);
			mAcceleration.getStructuralFeatures().add(mAcceleration_unit);
			mAngularAccelerationUnit.getStructuralFeatures().add(mAngularAccelerationUnit_speedUnit);
			mAngularAccelerationUnit.getStructuralFeatures().add(mAngularAccelerationUnit_timeUnit);
			mAngularAcceleration.getStructuralFeatures().add(mAngularAcceleration_unit);
			mMass.getStructuralFeatures().add(mMass_unit);
			mEuler.getStructuralFeatures().add(mEuler_x);
			mEuler.getStructuralFeatures().add(mEuler_y);
			mEuler.getStructuralFeatures().add(mEuler_z);
			mQuaternion.getStructuralFeatures().add(mQuaternion_x);
			mQuaternion.getStructuralFeatures().add(mQuaternion_y);
			mQuaternion.getStructuralFeatures().add(mQuaternion_z);
			mQuaternion.getStructuralFeatures().add(mQuaternion_w);
			mVelocity.getStructuralFeatures().add(mVelocity_value);
			mVelocity.getStructuralFeatures().add(mVelocity_unit);
			mVelocity.getStructuralFeatures().add(mVelocity_crs);
			mAngularVelocity.getStructuralFeatures().add(mAngularVelocity_value);
			mAngularVelocity.getStructuralFeatures().add(mAngularVelocity_unit);
			mAngularVelocity.getStructuralFeatures().add(mAngularVelocity_crs);
			mPower.getStructuralFeatures().add(mPower_unit);
			mRGBColor.getStructuralFeatures().add(mRGBColor_r);
			mRGBColor.getStructuralFeatures().add(mRGBColor_g);
			mRGBColor.getStructuralFeatures().add(mRGBColor_b);
			mRGBColor.getStructuralFeatures().add(mRGBColor_a);
			mColor.getStructuralFeatures().add(mColor_color);
		}
		
		UMetaBuilder.manual().addLiteral(mDistanceUnit, "METER", 0, DistanceUnit.METER);
		UMetaBuilder.manual().addLiteral(mDistanceUnit, "KILOMETER", 1, DistanceUnit.KILOMETER);
		UMetaBuilder.manual().addLiteral(mDistanceUnit, "CENTIMETER", 2, DistanceUnit.CENTIMETER);
		UMetaBuilder.manual().addLiteral(mDistanceUnit, "MILLIMETER", 3, DistanceUnit.MILLIMETER);
		UMetaBuilder.manual().addLiteral(mDistanceUnit, "MILES", 4, DistanceUnit.MILES);
			mDistanceUnit.getLiteral("MILES").setDocumentation(" also called statue mile or land mile. One mile is exactly 1609.344 meters ");
		UMetaBuilder.manual().addLiteral(mDistanceUnit, "NAUTICAL_MILES", 5, DistanceUnit.NAUTICAL_MILES);
			mDistanceUnit.getLiteral("NAUTICAL_MILES").setDocumentation(" a nautical mile is exactly 1852 meters or 1.15 miles ");
		UMetaBuilder.manual().addLiteral(mDistanceUnit, "FOOT", 6, DistanceUnit.FOOT);
			mDistanceUnit.getLiteral("FOOT").setDocumentation(" One foot is exaclty 30.48 centimeters ");
		UMetaBuilder.manual().addLiteral(mDistanceUnit, "YARD", 7, DistanceUnit.YARD);
			mDistanceUnit.getLiteral("YARD").setDocumentation(" one yard is defined as 3 foot or 0.9144m ");
		UMetaBuilder.manual().addLiteral(mDistanceUnit, "CABLE", 8, DistanceUnit.CABLE);
			mDistanceUnit.getLiteral("CABLE").setDocumentation(" one cable is a tenth 1/10 of a nautical mile therefore 1 kbl = 185.2 meters ");
		UMetaBuilder.manual().addLiteral(mVolumeUnit, "CUBIC_METER", 0, VolumeUnit.CUBIC_METER);
		UMetaBuilder.manual().addLiteral(mVolumeUnit, "BARREL", 1, VolumeUnit.BARREL);
		UMetaBuilder.manual().addLiteral(mVolumeUnit, "CUBIC_FOOT", 2, VolumeUnit.CUBIC_FOOT);
		UMetaBuilder.manual().addLiteral(mVolumeUnit, "CUBIC_DECIMETER", 3, VolumeUnit.CUBIC_DECIMETER);
		UMetaBuilder.manual().addLiteral(mVolumeUnit, "LITRE", 4, VolumeUnit.LITRE);
		UMetaBuilder.manual().addLiteral(mVolumeUnit, "GALLON", 5, VolumeUnit.GALLON);
		UMetaBuilder.manual().addLiteral(mVolumeUnit, "PINT", 6, VolumeUnit.PINT);
		UMetaBuilder.manual().addLiteral(mVolumeUnit, "CUBIC_INCH", 7, VolumeUnit.CUBIC_INCH);
		UMetaBuilder.manual().addLiteral(mVolumeUnit, "CUBIC_CENTIMETER", 8, VolumeUnit.CUBIC_CENTIMETER);
		UMetaBuilder.manual().addLiteral(mTemperatureUnit, "KELVIN", 0, TemperatureUnit.KELVIN);
		UMetaBuilder.manual().addLiteral(mTemperatureUnit, "CELSIUS", 1, TemperatureUnit.CELSIUS);
		UMetaBuilder.manual().addLiteral(mTemperatureUnit, "FARENHEIT", 2, TemperatureUnit.FARENHEIT);
		UMetaBuilder.manual().addLiteral(mAngleUnit, "RADIAN", 0, AngleUnit.RADIAN);
		UMetaBuilder.manual().addLiteral(mAngleUnit, "DEGREE", 1, AngleUnit.DEGREE);
		UMetaBuilder.manual().addLiteral(mAngleUnit, "REVOLUTION", 2, AngleUnit.REVOLUTION);
		UMetaBuilder.manual().addLiteral(mTimeUnit, "NANOSECOND", 0, TimeUnit.NANOSECOND);
		UMetaBuilder.manual().addLiteral(mTimeUnit, "MICROSECOND", 1, TimeUnit.MICROSECOND);
		UMetaBuilder.manual().addLiteral(mTimeUnit, "MILLISECOND", 2, TimeUnit.MILLISECOND);
		UMetaBuilder.manual().addLiteral(mTimeUnit, "SECOND", 3, TimeUnit.SECOND);
		UMetaBuilder.manual().addLiteral(mTimeUnit, "MINUTE", 4, TimeUnit.MINUTE);
		UMetaBuilder.manual().addLiteral(mTimeUnit, "HOUR", 5, TimeUnit.HOUR);
		UMetaBuilder.manual().addLiteral(mTimeUnit, "DAY", 6, TimeUnit.DAY);
		UMetaBuilder.manual().addLiteral(mMassUnit, "KILOGRAM", 0, MassUnit.KILOGRAM);
		UMetaBuilder.manual().addLiteral(mMassUnit, "GRAM", 1, MassUnit.GRAM);
		UMetaBuilder.manual().addLiteral(mMassUnit, "TONNE", 2, MassUnit.TONNE);
		UMetaBuilder.manual().addLiteral(mPowerUnit, "WATT", 0, PowerUnit.WATT);
		UMetaBuilder.manual().addLiteral(mPowerUnit, "MILLI_WATT", 1, PowerUnit.MILLI_WATT);
		UMetaBuilder.manual().addLiteral(mPowerUnit, "MICRO_WATT", 2, PowerUnit.MICRO_WATT);
		UMetaBuilder.manual().addLiteral(mPowerUnit, "NANNO_WATT", 3, PowerUnit.NANNO_WATT);
		UMetaBuilder.manual().addLiteral(mPowerUnit, "KILO_WATT", 4, PowerUnit.KILO_WATT);
		UMetaBuilder.manual().addLiteral(mPowerUnit, "MEGA_WATT", 5, PowerUnit.MEGA_WATT);
		UMetaBuilder.manual().addLiteral(mPowerUnit, "GIGA_WATT", 6, PowerUnit.GIGA_WATT);
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "COLOUR_UNKNOWN", 0, PredefinedColour.COLOUR_UNKNOWN);
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "WHITE", 1, PredefinedColour.WHITE);
			//Annotations for PredefinedColour:WHITE
			mPredefinedColour.getLiteral("WHITE").createAnnotationDetail("Color", "Hex", "#FFFFFF");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "BLACK", 2, PredefinedColour.BLACK);
			//Annotations for PredefinedColour:BLACK
			mPredefinedColour.getLiteral("BLACK").createAnnotationDetail("Color", "Hex", "#000000");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "RED", 3, PredefinedColour.RED);
			//Annotations for PredefinedColour:RED
			mPredefinedColour.getLiteral("RED").createAnnotationDetail("Color", "Hex", "#FF0000");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "GREEN", 4, PredefinedColour.GREEN);
			//Annotations for PredefinedColour:GREEN
			mPredefinedColour.getLiteral("GREEN").createAnnotationDetail("Color", "Hex", "#00FF00");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "BLUE", 5, PredefinedColour.BLUE);
			//Annotations for PredefinedColour:BLUE
			mPredefinedColour.getLiteral("BLUE").createAnnotationDetail("Color", "Hex", "#0000FF");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "YELLOW", 6, PredefinedColour.YELLOW);
			//Annotations for PredefinedColour:YELLOW
			mPredefinedColour.getLiteral("YELLOW").createAnnotationDetail("Color", "Hex", "#FFFF00");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "GREY", 7, PredefinedColour.GREY);
			//Annotations for PredefinedColour:GREY
			mPredefinedColour.getLiteral("GREY").createAnnotationDetail("Color", "Hex", "#808080");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "BROWN", 8, PredefinedColour.BROWN);
			//Annotations for PredefinedColour:BROWN
			mPredefinedColour.getLiteral("BROWN").createAnnotationDetail("Color", "Hex", "#964B00");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "AMBER", 9, PredefinedColour.AMBER);
			//Annotations for PredefinedColour:AMBER
			mPredefinedColour.getLiteral("AMBER").createAnnotationDetail("Color", "Hex", "#FFBF00");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "VIOLET", 10, PredefinedColour.VIOLET);
			//Annotations for PredefinedColour:VIOLET
			mPredefinedColour.getLiteral("VIOLET").createAnnotationDetail("Color", "Hex", "#8F00FF");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "ORANGE", 11, PredefinedColour.ORANGE);
			//Annotations for PredefinedColour:ORANGE
			mPredefinedColour.getLiteral("ORANGE").createAnnotationDetail("Color", "Hex", "#FF4F00");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "MAGENTA", 12, PredefinedColour.MAGENTA);
			//Annotations for PredefinedColour:MAGENTA
			mPredefinedColour.getLiteral("MAGENTA").createAnnotationDetail("Color", "Hex", "#FF00FF");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "PINK", 13, PredefinedColour.PINK);
			//Annotations for PredefinedColour:PINK
			mPredefinedColour.getLiteral("PINK").createAnnotationDetail("Color", "Hex", "#FFC0CB");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "AliceBlue", 15, PredefinedColour.AliceBlue);
			//Annotations for PredefinedColour:AliceBlue
			mPredefinedColour.getLiteral("AliceBlue").createAnnotationDetail("Color", "Hex", "#F0F8FF");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "AntiqueWhite", 16, PredefinedColour.AntiqueWhite);
			//Annotations for PredefinedColour:AntiqueWhite
			mPredefinedColour.getLiteral("AntiqueWhite").createAnnotationDetail("Color", "Hex", "#FAEBD7");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Aqua", 17, PredefinedColour.Aqua);
			//Annotations for PredefinedColour:Aqua
			mPredefinedColour.getLiteral("Aqua").createAnnotationDetail("Color", "Hex", "#00FFFF");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Aquamarine", 18, PredefinedColour.Aquamarine);
			//Annotations for PredefinedColour:Aquamarine
			mPredefinedColour.getLiteral("Aquamarine").createAnnotationDetail("Color", "Hex", "#7FFFD4");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Azure", 19, PredefinedColour.Azure);
			//Annotations for PredefinedColour:Azure
			mPredefinedColour.getLiteral("Azure").createAnnotationDetail("Color", "Hex", "#F0FFFF");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Beige", 20, PredefinedColour.Beige);
			//Annotations for PredefinedColour:Beige
			mPredefinedColour.getLiteral("Beige").createAnnotationDetail("Color", "Hex", "#F5F5DC");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Bisque", 21, PredefinedColour.Bisque);
			//Annotations for PredefinedColour:Bisque
			mPredefinedColour.getLiteral("Bisque").createAnnotationDetail("Color", "Hex", "#FFE4C4");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "BlanchedAlmond", 23, PredefinedColour.BlanchedAlmond);
			//Annotations for PredefinedColour:BlanchedAlmond
			mPredefinedColour.getLiteral("BlanchedAlmond").createAnnotationDetail("Color", "Hex", "#FFEBCD");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "BlueViolet", 25, PredefinedColour.BlueViolet);
			//Annotations for PredefinedColour:BlueViolet
			mPredefinedColour.getLiteral("BlueViolet").createAnnotationDetail("Color", "Hex", "#8A2BE2");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "BurlyWood", 27, PredefinedColour.BurlyWood);
			//Annotations for PredefinedColour:BurlyWood
			mPredefinedColour.getLiteral("BurlyWood").createAnnotationDetail("Color", "Hex", "#DEB887");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "CadetBlue", 28, PredefinedColour.CadetBlue);
			//Annotations for PredefinedColour:CadetBlue
			mPredefinedColour.getLiteral("CadetBlue").createAnnotationDetail("Color", "Hex", "#5F9EA0");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Chartreuse", 29, PredefinedColour.Chartreuse);
			//Annotations for PredefinedColour:Chartreuse
			mPredefinedColour.getLiteral("Chartreuse").createAnnotationDetail("Color", "Hex", "#7FFF00");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Chocolate", 30, PredefinedColour.Chocolate);
			//Annotations for PredefinedColour:Chocolate
			mPredefinedColour.getLiteral("Chocolate").createAnnotationDetail("Color", "Hex", "#D2691E");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Coral", 31, PredefinedColour.Coral);
			//Annotations for PredefinedColour:Coral
			mPredefinedColour.getLiteral("Coral").createAnnotationDetail("Color", "Hex", "#FF7F50");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "CornflowerBlue", 32, PredefinedColour.CornflowerBlue);
			//Annotations for PredefinedColour:CornflowerBlue
			mPredefinedColour.getLiteral("CornflowerBlue").createAnnotationDetail("Color", "Hex", "#6495ED");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Cornsilk", 33, PredefinedColour.Cornsilk);
			//Annotations for PredefinedColour:Cornsilk
			mPredefinedColour.getLiteral("Cornsilk").createAnnotationDetail("Color", "Hex", "#FFF8DC");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Crimson", 34, PredefinedColour.Crimson);
			//Annotations for PredefinedColour:Crimson
			mPredefinedColour.getLiteral("Crimson").createAnnotationDetail("Color", "Hex", "#DC143C");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Cyan", 35, PredefinedColour.Cyan);
			//Annotations for PredefinedColour:Cyan
			mPredefinedColour.getLiteral("Cyan").createAnnotationDetail("Color", "Hex", "#00FFFF");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkBlue", 36, PredefinedColour.DarkBlue);
			//Annotations for PredefinedColour:DarkBlue
			mPredefinedColour.getLiteral("DarkBlue").createAnnotationDetail("Color", "Hex", "#00008B");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkCyan", 37, PredefinedColour.DarkCyan);
			//Annotations for PredefinedColour:DarkCyan
			mPredefinedColour.getLiteral("DarkCyan").createAnnotationDetail("Color", "Hex", "#008B8B");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkGoldenRod", 38, PredefinedColour.DarkGoldenRod);
			//Annotations for PredefinedColour:DarkGoldenRod
			mPredefinedColour.getLiteral("DarkGoldenRod").createAnnotationDetail("Color", "Hex", "#B8860B");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkGray", 39, PredefinedColour.DarkGray);
			//Annotations for PredefinedColour:DarkGray
			mPredefinedColour.getLiteral("DarkGray").createAnnotationDetail("Color", "Hex", "#A9A9A9");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkGrey", 40, PredefinedColour.DarkGrey);
			//Annotations for PredefinedColour:DarkGrey
			mPredefinedColour.getLiteral("DarkGrey").createAnnotationDetail("Color", "Hex", "#A9A9A9");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkGreen", 41, PredefinedColour.DarkGreen);
			//Annotations for PredefinedColour:DarkGreen
			mPredefinedColour.getLiteral("DarkGreen").createAnnotationDetail("Color", "Hex", "#006400");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkKhaki", 42, PredefinedColour.DarkKhaki);
			//Annotations for PredefinedColour:DarkKhaki
			mPredefinedColour.getLiteral("DarkKhaki").createAnnotationDetail("Color", "Hex", "#BDB76B");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkMagenta", 43, PredefinedColour.DarkMagenta);
			//Annotations for PredefinedColour:DarkMagenta
			mPredefinedColour.getLiteral("DarkMagenta").createAnnotationDetail("Color", "Hex", "#8B008B");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkOliveGreen", 44, PredefinedColour.DarkOliveGreen);
			//Annotations for PredefinedColour:DarkOliveGreen
			mPredefinedColour.getLiteral("DarkOliveGreen").createAnnotationDetail("Color", "Hex", "#556B2F");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkOrange", 45, PredefinedColour.DarkOrange);
			//Annotations for PredefinedColour:DarkOrange
			mPredefinedColour.getLiteral("DarkOrange").createAnnotationDetail("Color", "Hex", "#FF8C00");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkOrchid", 46, PredefinedColour.DarkOrchid);
			//Annotations for PredefinedColour:DarkOrchid
			mPredefinedColour.getLiteral("DarkOrchid").createAnnotationDetail("Color", "Hex", "#9932CC");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkRed", 47, PredefinedColour.DarkRed);
			//Annotations for PredefinedColour:DarkRed
			mPredefinedColour.getLiteral("DarkRed").createAnnotationDetail("Color", "Hex", "#8B0000");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkSalmon", 48, PredefinedColour.DarkSalmon);
			//Annotations for PredefinedColour:DarkSalmon
			mPredefinedColour.getLiteral("DarkSalmon").createAnnotationDetail("Color", "Hex", "#E9967A");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkSeaGreen", 49, PredefinedColour.DarkSeaGreen);
			//Annotations for PredefinedColour:DarkSeaGreen
			mPredefinedColour.getLiteral("DarkSeaGreen").createAnnotationDetail("Color", "Hex", "#8FBC8F");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkSlateBlue", 50, PredefinedColour.DarkSlateBlue);
			//Annotations for PredefinedColour:DarkSlateBlue
			mPredefinedColour.getLiteral("DarkSlateBlue").createAnnotationDetail("Color", "Hex", "#483D8B");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkSlateGray", 51, PredefinedColour.DarkSlateGray);
			//Annotations for PredefinedColour:DarkSlateGray
			mPredefinedColour.getLiteral("DarkSlateGray").createAnnotationDetail("Color", "Hex", "#2F4F4F");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkSlateGrey", 52, PredefinedColour.DarkSlateGrey);
			//Annotations for PredefinedColour:DarkSlateGrey
			mPredefinedColour.getLiteral("DarkSlateGrey").createAnnotationDetail("Color", "Hex", "#2F4F4F");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkTurquoise", 53, PredefinedColour.DarkTurquoise);
			//Annotations for PredefinedColour:DarkTurquoise
			mPredefinedColour.getLiteral("DarkTurquoise").createAnnotationDetail("Color", "Hex", "#00CED1");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DarkViolet", 54, PredefinedColour.DarkViolet);
			//Annotations for PredefinedColour:DarkViolet
			mPredefinedColour.getLiteral("DarkViolet").createAnnotationDetail("Color", "Hex", "#9400D3");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DeepPink", 55, PredefinedColour.DeepPink);
			//Annotations for PredefinedColour:DeepPink
			mPredefinedColour.getLiteral("DeepPink").createAnnotationDetail("Color", "Hex", "#FF1493");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DeepSkyBlue", 56, PredefinedColour.DeepSkyBlue);
			//Annotations for PredefinedColour:DeepSkyBlue
			mPredefinedColour.getLiteral("DeepSkyBlue").createAnnotationDetail("Color", "Hex", "#00BFFF");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DimGray", 57, PredefinedColour.DimGray);
			//Annotations for PredefinedColour:DimGray
			mPredefinedColour.getLiteral("DimGray").createAnnotationDetail("Color", "Hex", "#696969");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DimGrey", 58, PredefinedColour.DimGrey);
			//Annotations for PredefinedColour:DimGrey
			mPredefinedColour.getLiteral("DimGrey").createAnnotationDetail("Color", "Hex", "#696969");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "DodgerBlue", 59, PredefinedColour.DodgerBlue);
			//Annotations for PredefinedColour:DodgerBlue
			mPredefinedColour.getLiteral("DodgerBlue").createAnnotationDetail("Color", "Hex", "#1E90FF");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "FireBrick", 60, PredefinedColour.FireBrick);
			//Annotations for PredefinedColour:FireBrick
			mPredefinedColour.getLiteral("FireBrick").createAnnotationDetail("Color", "Hex", "#B22222");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "FloralWhite", 61, PredefinedColour.FloralWhite);
			//Annotations for PredefinedColour:FloralWhite
			mPredefinedColour.getLiteral("FloralWhite").createAnnotationDetail("Color", "Hex", "#FFFAF0");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "ForestGreen", 62, PredefinedColour.ForestGreen);
			//Annotations for PredefinedColour:ForestGreen
			mPredefinedColour.getLiteral("ForestGreen").createAnnotationDetail("Color", "Hex", "#228B22");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Fuchsia", 63, PredefinedColour.Fuchsia);
			//Annotations for PredefinedColour:Fuchsia
			mPredefinedColour.getLiteral("Fuchsia").createAnnotationDetail("Color", "Hex", "#FF00FF");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Gainsboro", 64, PredefinedColour.Gainsboro);
			//Annotations for PredefinedColour:Gainsboro
			mPredefinedColour.getLiteral("Gainsboro").createAnnotationDetail("Color", "Hex", "#DCDCDC");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "GhostWhite", 65, PredefinedColour.GhostWhite);
			//Annotations for PredefinedColour:GhostWhite
			mPredefinedColour.getLiteral("GhostWhite").createAnnotationDetail("Color", "Hex", "#F8F8FF");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Gold", 66, PredefinedColour.Gold);
			//Annotations for PredefinedColour:Gold
			mPredefinedColour.getLiteral("Gold").createAnnotationDetail("Color", "Hex", "#FFD700");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "GoldenRod", 67, PredefinedColour.GoldenRod);
			//Annotations for PredefinedColour:GoldenRod
			mPredefinedColour.getLiteral("GoldenRod").createAnnotationDetail("Color", "Hex", "#DAA520");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Gray", 68, PredefinedColour.Gray);
			//Annotations for PredefinedColour:Gray
			mPredefinedColour.getLiteral("Gray").createAnnotationDetail("Color", "Hex", "#808080");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "GreenYellow", 71, PredefinedColour.GreenYellow);
			//Annotations for PredefinedColour:GreenYellow
			mPredefinedColour.getLiteral("GreenYellow").createAnnotationDetail("Color", "Hex", "#ADFF2F");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "HoneyDew", 72, PredefinedColour.HoneyDew);
			//Annotations for PredefinedColour:HoneyDew
			mPredefinedColour.getLiteral("HoneyDew").createAnnotationDetail("Color", "Hex", "#F0FFF0");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "HotPink", 73, PredefinedColour.HotPink);
			//Annotations for PredefinedColour:HotPink
			mPredefinedColour.getLiteral("HotPink").createAnnotationDetail("Color", "Hex", "#FF69B4");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "IndianRed", 74, PredefinedColour.IndianRed);
			//Annotations for PredefinedColour:IndianRed
			mPredefinedColour.getLiteral("IndianRed").createAnnotationDetail("Color", "Hex", "#CD5C5C");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Indigo", 75, PredefinedColour.Indigo);
			//Annotations for PredefinedColour:Indigo
			mPredefinedColour.getLiteral("Indigo").createAnnotationDetail("Color", "Hex", "#4B0082");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Ivory", 76, PredefinedColour.Ivory);
			//Annotations for PredefinedColour:Ivory
			mPredefinedColour.getLiteral("Ivory").createAnnotationDetail("Color", "Hex", "#FFFFF0");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Khaki", 77, PredefinedColour.Khaki);
			//Annotations for PredefinedColour:Khaki
			mPredefinedColour.getLiteral("Khaki").createAnnotationDetail("Color", "Hex", "#F0E68C");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Lavender", 78, PredefinedColour.Lavender);
			//Annotations for PredefinedColour:Lavender
			mPredefinedColour.getLiteral("Lavender").createAnnotationDetail("Color", "Hex", "#E6E6FA");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LavenderBlush", 79, PredefinedColour.LavenderBlush);
			//Annotations for PredefinedColour:LavenderBlush
			mPredefinedColour.getLiteral("LavenderBlush").createAnnotationDetail("Color", "Hex", "#FFF0F5");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LawnGreen", 80, PredefinedColour.LawnGreen);
			//Annotations for PredefinedColour:LawnGreen
			mPredefinedColour.getLiteral("LawnGreen").createAnnotationDetail("Color", "Hex", "#7CFC00");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LemonChiffon", 81, PredefinedColour.LemonChiffon);
			//Annotations for PredefinedColour:LemonChiffon
			mPredefinedColour.getLiteral("LemonChiffon").createAnnotationDetail("Color", "Hex", "#FFFACD");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LightBlue", 82, PredefinedColour.LightBlue);
			//Annotations for PredefinedColour:LightBlue
			mPredefinedColour.getLiteral("LightBlue").createAnnotationDetail("Color", "Hex", "#ADD8E6");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LightCoral", 83, PredefinedColour.LightCoral);
			//Annotations for PredefinedColour:LightCoral
			mPredefinedColour.getLiteral("LightCoral").createAnnotationDetail("Color", "Hex", "#F08080");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LightCyan", 84, PredefinedColour.LightCyan);
			//Annotations for PredefinedColour:LightCyan
			mPredefinedColour.getLiteral("LightCyan").createAnnotationDetail("Color", "Hex", "#E0FFFF");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LightGoldenRodYellow", 85, PredefinedColour.LightGoldenRodYellow);
			//Annotations for PredefinedColour:LightGoldenRodYellow
			mPredefinedColour.getLiteral("LightGoldenRodYellow").createAnnotationDetail("Color", "Hex", "#FAFAD2");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LightGray", 86, PredefinedColour.LightGray);
			//Annotations for PredefinedColour:LightGray
			mPredefinedColour.getLiteral("LightGray").createAnnotationDetail("Color", "Hex", "#D3D3D3");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LightGrey", 87, PredefinedColour.LightGrey);
			//Annotations for PredefinedColour:LightGrey
			mPredefinedColour.getLiteral("LightGrey").createAnnotationDetail("Color", "Hex", "#D3D3D3");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LightGreen", 88, PredefinedColour.LightGreen);
			//Annotations for PredefinedColour:LightGreen
			mPredefinedColour.getLiteral("LightGreen").createAnnotationDetail("Color", "Hex", "#90EE90");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LightPink", 89, PredefinedColour.LightPink);
			//Annotations for PredefinedColour:LightPink
			mPredefinedColour.getLiteral("LightPink").createAnnotationDetail("Color", "Hex", "#FFB6C1");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LightSalmon", 90, PredefinedColour.LightSalmon);
			//Annotations for PredefinedColour:LightSalmon
			mPredefinedColour.getLiteral("LightSalmon").createAnnotationDetail("Color", "Hex", "#FFA07A");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LightSeaGreen", 91, PredefinedColour.LightSeaGreen);
			//Annotations for PredefinedColour:LightSeaGreen
			mPredefinedColour.getLiteral("LightSeaGreen").createAnnotationDetail("Color", "Hex", "#20B2AA");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LightSkyBlue", 92, PredefinedColour.LightSkyBlue);
			//Annotations for PredefinedColour:LightSkyBlue
			mPredefinedColour.getLiteral("LightSkyBlue").createAnnotationDetail("Color", "Hex", "#87CEFA");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LightSlateGray", 93, PredefinedColour.LightSlateGray);
			//Annotations for PredefinedColour:LightSlateGray
			mPredefinedColour.getLiteral("LightSlateGray").createAnnotationDetail("Color", "Hex", "#778899");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LightSlateGrey", 94, PredefinedColour.LightSlateGrey);
			//Annotations for PredefinedColour:LightSlateGrey
			mPredefinedColour.getLiteral("LightSlateGrey").createAnnotationDetail("Color", "Hex", "#778899");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LightSteelBlue", 95, PredefinedColour.LightSteelBlue);
			//Annotations for PredefinedColour:LightSteelBlue
			mPredefinedColour.getLiteral("LightSteelBlue").createAnnotationDetail("Color", "Hex", "#B0C4DE");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LightYellow", 96, PredefinedColour.LightYellow);
			//Annotations for PredefinedColour:LightYellow
			mPredefinedColour.getLiteral("LightYellow").createAnnotationDetail("Color", "Hex", "#FFFFE0");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Lime", 97, PredefinedColour.Lime);
			//Annotations for PredefinedColour:Lime
			mPredefinedColour.getLiteral("Lime").createAnnotationDetail("Color", "Hex", "#00FF00");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "LimeGreen", 98, PredefinedColour.LimeGreen);
			//Annotations for PredefinedColour:LimeGreen
			mPredefinedColour.getLiteral("LimeGreen").createAnnotationDetail("Color", "Hex", "#32CD32");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Linen", 99, PredefinedColour.Linen);
			//Annotations for PredefinedColour:Linen
			mPredefinedColour.getLiteral("Linen").createAnnotationDetail("Color", "Hex", "#FAF0E6");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Maroon", 101, PredefinedColour.Maroon);
			//Annotations for PredefinedColour:Maroon
			mPredefinedColour.getLiteral("Maroon").createAnnotationDetail("Color", "Hex", "#800000");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "MediumAquaMarine", 102, PredefinedColour.MediumAquaMarine);
			//Annotations for PredefinedColour:MediumAquaMarine
			mPredefinedColour.getLiteral("MediumAquaMarine").createAnnotationDetail("Color", "Hex", "#66CDAA");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "MediumBlue", 103, PredefinedColour.MediumBlue);
			//Annotations for PredefinedColour:MediumBlue
			mPredefinedColour.getLiteral("MediumBlue").createAnnotationDetail("Color", "Hex", "#0000CD");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "MediumOrchid", 104, PredefinedColour.MediumOrchid);
			//Annotations for PredefinedColour:MediumOrchid
			mPredefinedColour.getLiteral("MediumOrchid").createAnnotationDetail("Color", "Hex", "#BA55D3");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "MediumPurple", 105, PredefinedColour.MediumPurple);
			//Annotations for PredefinedColour:MediumPurple
			mPredefinedColour.getLiteral("MediumPurple").createAnnotationDetail("Color", "Hex", "#9370DB");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "MediumSeaGreen", 106, PredefinedColour.MediumSeaGreen);
			//Annotations for PredefinedColour:MediumSeaGreen
			mPredefinedColour.getLiteral("MediumSeaGreen").createAnnotationDetail("Color", "Hex", "#3CB371");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "MediumSlateBlue", 107, PredefinedColour.MediumSlateBlue);
			//Annotations for PredefinedColour:MediumSlateBlue
			mPredefinedColour.getLiteral("MediumSlateBlue").createAnnotationDetail("Color", "Hex", "#7B68EE");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "MediumSpringGreen", 108, PredefinedColour.MediumSpringGreen);
			//Annotations for PredefinedColour:MediumSpringGreen
			mPredefinedColour.getLiteral("MediumSpringGreen").createAnnotationDetail("Color", "Hex", "#00FA9A");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "MediumTurquoise", 109, PredefinedColour.MediumTurquoise);
			//Annotations for PredefinedColour:MediumTurquoise
			mPredefinedColour.getLiteral("MediumTurquoise").createAnnotationDetail("Color", "Hex", "#48D1CC");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "MediumVioletRed", 110, PredefinedColour.MediumVioletRed);
			//Annotations for PredefinedColour:MediumVioletRed
			mPredefinedColour.getLiteral("MediumVioletRed").createAnnotationDetail("Color", "Hex", "#C71585");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "MidnightBlue", 111, PredefinedColour.MidnightBlue);
			//Annotations for PredefinedColour:MidnightBlue
			mPredefinedColour.getLiteral("MidnightBlue").createAnnotationDetail("Color", "Hex", "#191970");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "MintCream", 112, PredefinedColour.MintCream);
			//Annotations for PredefinedColour:MintCream
			mPredefinedColour.getLiteral("MintCream").createAnnotationDetail("Color", "Hex", "#F5FFFA");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "MistyRose", 113, PredefinedColour.MistyRose);
			//Annotations for PredefinedColour:MistyRose
			mPredefinedColour.getLiteral("MistyRose").createAnnotationDetail("Color", "Hex", "#FFE4E1");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Moccasin", 114, PredefinedColour.Moccasin);
			//Annotations for PredefinedColour:Moccasin
			mPredefinedColour.getLiteral("Moccasin").createAnnotationDetail("Color", "Hex", "#FFE4B5");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "NavajoWhite", 115, PredefinedColour.NavajoWhite);
			//Annotations for PredefinedColour:NavajoWhite
			mPredefinedColour.getLiteral("NavajoWhite").createAnnotationDetail("Color", "Hex", "#FFDEAD");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Navy", 116, PredefinedColour.Navy);
			//Annotations for PredefinedColour:Navy
			mPredefinedColour.getLiteral("Navy").createAnnotationDetail("Color", "Hex", "#000080");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "OldLace", 117, PredefinedColour.OldLace);
			//Annotations for PredefinedColour:OldLace
			mPredefinedColour.getLiteral("OldLace").createAnnotationDetail("Color", "Hex", "#FDF5E6");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Olive", 118, PredefinedColour.Olive);
			//Annotations for PredefinedColour:Olive
			mPredefinedColour.getLiteral("Olive").createAnnotationDetail("Color", "Hex", "#808000");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "OliveDrab", 119, PredefinedColour.OliveDrab);
			//Annotations for PredefinedColour:OliveDrab
			mPredefinedColour.getLiteral("OliveDrab").createAnnotationDetail("Color", "Hex", "#6B8E23");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "OrangeRed", 121, PredefinedColour.OrangeRed);
			//Annotations for PredefinedColour:OrangeRed
			mPredefinedColour.getLiteral("OrangeRed").createAnnotationDetail("Color", "Hex", "#FF4500");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Orchid", 122, PredefinedColour.Orchid);
			//Annotations for PredefinedColour:Orchid
			mPredefinedColour.getLiteral("Orchid").createAnnotationDetail("Color", "Hex", "#DA70D6");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "PaleGoldenRod", 123, PredefinedColour.PaleGoldenRod);
			//Annotations for PredefinedColour:PaleGoldenRod
			mPredefinedColour.getLiteral("PaleGoldenRod").createAnnotationDetail("Color", "Hex", "#EEE8AA");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "PaleGreen", 124, PredefinedColour.PaleGreen);
			//Annotations for PredefinedColour:PaleGreen
			mPredefinedColour.getLiteral("PaleGreen").createAnnotationDetail("Color", "Hex", "#98FB98");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "PaleTurquoise", 125, PredefinedColour.PaleTurquoise);
			//Annotations for PredefinedColour:PaleTurquoise
			mPredefinedColour.getLiteral("PaleTurquoise").createAnnotationDetail("Color", "Hex", "#AFEEEE");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "PaleVioletRed", 126, PredefinedColour.PaleVioletRed);
			//Annotations for PredefinedColour:PaleVioletRed
			mPredefinedColour.getLiteral("PaleVioletRed").createAnnotationDetail("Color", "Hex", "#DB7093");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "PapayaWhip", 127, PredefinedColour.PapayaWhip);
			//Annotations for PredefinedColour:PapayaWhip
			mPredefinedColour.getLiteral("PapayaWhip").createAnnotationDetail("Color", "Hex", "#FFEFD5");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "PeachPuff", 128, PredefinedColour.PeachPuff);
			//Annotations for PredefinedColour:PeachPuff
			mPredefinedColour.getLiteral("PeachPuff").createAnnotationDetail("Color", "Hex", "#FFDAB9");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Peru", 129, PredefinedColour.Peru);
			//Annotations for PredefinedColour:Peru
			mPredefinedColour.getLiteral("Peru").createAnnotationDetail("Color", "Hex", "#CD853F");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Plum", 131, PredefinedColour.Plum);
			//Annotations for PredefinedColour:Plum
			mPredefinedColour.getLiteral("Plum").createAnnotationDetail("Color", "Hex", "#DDA0DD");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "PowderBlue", 132, PredefinedColour.PowderBlue);
			//Annotations for PredefinedColour:PowderBlue
			mPredefinedColour.getLiteral("PowderBlue").createAnnotationDetail("Color", "Hex", "#B0E0E6");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Purple", 133, PredefinedColour.Purple);
			//Annotations for PredefinedColour:Purple
			mPredefinedColour.getLiteral("Purple").createAnnotationDetail("Color", "Hex", "#800080");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "RebeccaPurple", 134, PredefinedColour.RebeccaPurple);
			//Annotations for PredefinedColour:RebeccaPurple
			mPredefinedColour.getLiteral("RebeccaPurple").createAnnotationDetail("Color", "Hex", "#663399");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "RosyBrown", 136, PredefinedColour.RosyBrown);
			//Annotations for PredefinedColour:RosyBrown
			mPredefinedColour.getLiteral("RosyBrown").createAnnotationDetail("Color", "Hex", "#BC8F8F");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "RoyalBlue", 137, PredefinedColour.RoyalBlue);
			//Annotations for PredefinedColour:RoyalBlue
			mPredefinedColour.getLiteral("RoyalBlue").createAnnotationDetail("Color", "Hex", "#4169E1");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "SaddleBrown", 138, PredefinedColour.SaddleBrown);
			//Annotations for PredefinedColour:SaddleBrown
			mPredefinedColour.getLiteral("SaddleBrown").createAnnotationDetail("Color", "Hex", "#8B4513");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Salmon", 139, PredefinedColour.Salmon);
			//Annotations for PredefinedColour:Salmon
			mPredefinedColour.getLiteral("Salmon").createAnnotationDetail("Color", "Hex", "#FA8072");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "SandyBrown", 140, PredefinedColour.SandyBrown);
			//Annotations for PredefinedColour:SandyBrown
			mPredefinedColour.getLiteral("SandyBrown").createAnnotationDetail("Color", "Hex", "#F4A460");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "SeaGreen", 141, PredefinedColour.SeaGreen);
			//Annotations for PredefinedColour:SeaGreen
			mPredefinedColour.getLiteral("SeaGreen").createAnnotationDetail("Color", "Hex", "#2E8B57");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "SeaShell", 142, PredefinedColour.SeaShell);
			//Annotations for PredefinedColour:SeaShell
			mPredefinedColour.getLiteral("SeaShell").createAnnotationDetail("Color", "Hex", "#FFF5EE");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Sienna", 143, PredefinedColour.Sienna);
			//Annotations for PredefinedColour:Sienna
			mPredefinedColour.getLiteral("Sienna").createAnnotationDetail("Color", "Hex", "#A0522D");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Silver", 144, PredefinedColour.Silver);
			//Annotations for PredefinedColour:Silver
			mPredefinedColour.getLiteral("Silver").createAnnotationDetail("Color", "Hex", "#C0C0C0");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "SkyBlue", 145, PredefinedColour.SkyBlue);
			//Annotations for PredefinedColour:SkyBlue
			mPredefinedColour.getLiteral("SkyBlue").createAnnotationDetail("Color", "Hex", "#87CEEB");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "SlateBlue", 146, PredefinedColour.SlateBlue);
			//Annotations for PredefinedColour:SlateBlue
			mPredefinedColour.getLiteral("SlateBlue").createAnnotationDetail("Color", "Hex", "#6A5ACD");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "SlateGray", 147, PredefinedColour.SlateGray);
			//Annotations for PredefinedColour:SlateGray
			mPredefinedColour.getLiteral("SlateGray").createAnnotationDetail("Color", "Hex", "#708090");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "SlateGrey", 148, PredefinedColour.SlateGrey);
			//Annotations for PredefinedColour:SlateGrey
			mPredefinedColour.getLiteral("SlateGrey").createAnnotationDetail("Color", "Hex", "#708090");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Snow", 149, PredefinedColour.Snow);
			//Annotations for PredefinedColour:Snow
			mPredefinedColour.getLiteral("Snow").createAnnotationDetail("Color", "Hex", "#FFFAFA");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "SpringGreen", 150, PredefinedColour.SpringGreen);
			//Annotations for PredefinedColour:SpringGreen
			mPredefinedColour.getLiteral("SpringGreen").createAnnotationDetail("Color", "Hex", "#00FF7F");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "SteelBlue", 151, PredefinedColour.SteelBlue);
			//Annotations for PredefinedColour:SteelBlue
			mPredefinedColour.getLiteral("SteelBlue").createAnnotationDetail("Color", "Hex", "#4682B4");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Tan", 152, PredefinedColour.Tan);
			//Annotations for PredefinedColour:Tan
			mPredefinedColour.getLiteral("Tan").createAnnotationDetail("Color", "Hex", "#D2B48C");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Teal", 153, PredefinedColour.Teal);
			//Annotations for PredefinedColour:Teal
			mPredefinedColour.getLiteral("Teal").createAnnotationDetail("Color", "Hex", "#008080");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Thistle", 154, PredefinedColour.Thistle);
			//Annotations for PredefinedColour:Thistle
			mPredefinedColour.getLiteral("Thistle").createAnnotationDetail("Color", "Hex", "#D8BFD8");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Tomato", 155, PredefinedColour.Tomato);
			//Annotations for PredefinedColour:Tomato
			mPredefinedColour.getLiteral("Tomato").createAnnotationDetail("Color", "Hex", "#FF6347");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Turquoise", 156, PredefinedColour.Turquoise);
			//Annotations for PredefinedColour:Turquoise
			mPredefinedColour.getLiteral("Turquoise").createAnnotationDetail("Color", "Hex", "#40E0D0");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "Wheat", 158, PredefinedColour.Wheat);
			//Annotations for PredefinedColour:Wheat
			mPredefinedColour.getLiteral("Wheat").createAnnotationDetail("Color", "Hex", "#F5DEB3");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "WhiteSmoke", 160, PredefinedColour.WhiteSmoke);
			//Annotations for PredefinedColour:WhiteSmoke
			mPredefinedColour.getLiteral("WhiteSmoke").createAnnotationDetail("Color", "Hex", "#F5F5F5");
		UMetaBuilder.manual().addLiteral(mPredefinedColour, "YellowGreen", 162, PredefinedColour.YellowGreen);
			//Annotations for PredefinedColour:YellowGreen
			mPredefinedColour.getLiteral("YellowGreen").createAnnotationDetail("Color", "Hex", "#9ACD32");
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
		{		//Operations of Measure
			UOperation operation = null;
			//operation : roundLocal(double, int)
			operation = UMetaBuilder.manual().createOperation("roundLocal", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Measure)instance).roundLocal((int)parameter[0]);
				}
			});
				operation.setDocumentation(" rounds the result to the given decimal values\r\n * e.g. if numDecimals == 2 and value == 1.22392 => 1.22 == value\r\n * @return the old and accurate (not rounded) value\r\n ");
				UMetaBuilder.manual().addParameter(operation, "numDecimals", TypeUtils.getPrimitiveType(int.class), 0, 1, UDirectionType.IN);
				mMeasure.getOperations().add(operation);
		}
		{		//Operations of Length
			UOperation operation = null;
			//operation : getAs(double, DistanceUnit)
			operation = UMetaBuilder.manual().createOperation("getAs", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Length)instance).getAs((DistanceUnit)parameter[0]);
				}
			});
				operation.setDocumentation(" transform the value into the given unit, taking into account the current unit ");
				//Annotations for Length:getAs(double, DistanceUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "dst_unit", UnitsPackage.theInstance.getDistanceUnit(), 0, 1, UDirectionType.IN);
				mLength.getOperations().add(operation);
			//operation : set(void, double, DistanceUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Length)instance).set((double)parameter[0], (DistanceUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getDistanceUnit(), 0, 1, UDirectionType.IN);
				mLength.getOperations().add(operation);
			//operation : set(void, Length)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Length)instance).set((Length)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getLength(), 0, 1, UDirectionType.IN);
				mLength.getOperations().add(operation);
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Length)instance).readableString();
				}
			});
				//Annotations for Length:readableString(String)
				operation.createAnnotation("const");
				mLength.getOperations().add(operation);
			//operation : smaller(boolean, Length)
			operation = UMetaBuilder.manual().createOperation("smaller", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Length)instance).smaller((Length)parameter[0]);
				}
			});
				//Annotations for Length:smaller(boolean, Length)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getLength(), 0, 1, UDirectionType.IN);
				mLength.getOperations().add(operation);
			//operation : greater(boolean, Length)
			operation = UMetaBuilder.manual().createOperation("greater", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Length)instance).greater((Length)parameter[0]);
				}
			});
				//Annotations for Length:greater(boolean, Length)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getLength(), 0, 1, UDirectionType.IN);
				mLength.getOperations().add(operation);
			//operation : equals(boolean, Length)
			operation = UMetaBuilder.manual().createOperation("equals", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Length)instance).equals((Length)parameter[0]);
				}
			});
				//Annotations for Length:equals(boolean, Length)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getLength(), 0, 1, UDirectionType.IN);
				mLength.getOperations().add(operation);
			//operation : add(Length, Length)
			operation = UMetaBuilder.manual().createOperation("add", false, UnitsPackage.theInstance.getLength(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Length)instance).add((Length)parameter[0]);
				}
			});
				//Annotations for Length:add(Length, Length)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getLength(), 0, 1, UDirectionType.IN);
				mLength.getOperations().add(operation);
			//operation : addLocal(void, Length)
			operation = UMetaBuilder.manual().createOperation("addLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Length)instance).addLocal((Length)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getLength(), 0, 1, UDirectionType.IN);
				mLength.getOperations().add(operation);
			//operation : sub(Length, Length)
			operation = UMetaBuilder.manual().createOperation("sub", false, UnitsPackage.theInstance.getLength(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Length)instance).sub((Length)parameter[0]);
				}
			});
				//Annotations for Length:sub(Length, Length)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getLength(), 0, 1, UDirectionType.IN);
				mLength.getOperations().add(operation);
			//operation : subLocal(void, Length)
			operation = UMetaBuilder.manual().createOperation("subLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Length)instance).subLocal((Length)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getLength(), 0, 1, UDirectionType.IN);
				mLength.getOperations().add(operation);
			//operation : divide(Length, double)
			operation = UMetaBuilder.manual().createOperation("divide", false, UnitsPackage.theInstance.getLength(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Length)instance).divide((double)parameter[0]);
				}
			});
				//Annotations for Length:divide(Length, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "factor", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mLength.getOperations().add(operation);
			//operation : divideLocal(void, double)
			operation = UMetaBuilder.manual().createOperation("divideLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Length)instance).divideLocal((double)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "factor", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mLength.getOperations().add(operation);
			//operation : multiply(Length, double)
			operation = UMetaBuilder.manual().createOperation("multiply", false, UnitsPackage.theInstance.getLength(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Length)instance).multiply((double)parameter[0]);
				}
			});
				//Annotations for Length:multiply(Length, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "factor", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mLength.getOperations().add(operation);
			//operation : multiplyLocal(void, double)
			operation = UMetaBuilder.manual().createOperation("multiplyLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Length)instance).multiplyLocal((double)parameter[0]);
					return null;
				}
			});
				//Annotations for Length:multiplyLocal(void, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "factor", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mLength.getOperations().add(operation);
			//operation : lerp(Length, Length, double)
			operation = UMetaBuilder.manual().createOperation("lerp", false, UnitsPackage.theInstance.getLength(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Length)instance).lerp((Length)parameter[0], (double)parameter[1]);
				}
			});
				operation.setDocumentation(" linear interpolation between two length's");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getLength(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "factor", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mLength.getOperations().add(operation);
		}
		{		//Operations of Distance
			UOperation operation = null;
			//operation : lerp(Distance, Distance, double)
			operation = UMetaBuilder.manual().createOperation("lerp", false, UnitsPackage.theInstance.getDistance(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Distance)instance).lerp((Distance)parameter[0], (double)parameter[1]);
				}
			});
				operation.setDocumentation(" linear interpolation between two distance's");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getDistance(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "factor", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mDistance.getOperations().add(operation);
		}
		{		//Operations of Volume
			UOperation operation = null;
			//operation : getAs(double, VolumeUnit)
			operation = UMetaBuilder.manual().createOperation("getAs", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Volume)instance).getAs((VolumeUnit)parameter[0]);
				}
			});
				//Annotations for Volume:getAs(double, VolumeUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "dst_unit", UnitsPackage.theInstance.getVolumeUnit(), 0, 1, UDirectionType.IN);
				mVolume.getOperations().add(operation);
			//operation : set(void, double, VolumeUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Volume)instance).set((double)parameter[0], (VolumeUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getVolumeUnit(), 0, 1, UDirectionType.IN);
				mVolume.getOperations().add(operation);
			//operation : set(void, Volume)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Volume)instance).set((Volume)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getVolume(), 0, 1, UDirectionType.IN);
				mVolume.getOperations().add(operation);
			//operation : smaller(boolean, Volume)
			operation = UMetaBuilder.manual().createOperation("smaller", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Volume)instance).smaller((Volume)parameter[0]);
				}
			});
				//Annotations for Volume:smaller(boolean, Volume)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getVolume(), 0, 1, UDirectionType.IN);
				mVolume.getOperations().add(operation);
			//operation : greater(boolean, Volume)
			operation = UMetaBuilder.manual().createOperation("greater", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Volume)instance).greater((Volume)parameter[0]);
				}
			});
				//Annotations for Volume:greater(boolean, Volume)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getVolume(), 0, 1, UDirectionType.IN);
				mVolume.getOperations().add(operation);
			//operation : equals(boolean, Volume)
			operation = UMetaBuilder.manual().createOperation("equals", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Volume)instance).equals((Volume)parameter[0]);
				}
			});
				//Annotations for Volume:equals(boolean, Volume)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getVolume(), 0, 1, UDirectionType.IN);
				mVolume.getOperations().add(operation);
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Volume)instance).readableString();
				}
			});
				//Annotations for Volume:readableString(String)
				operation.createAnnotation("const");
				mVolume.getOperations().add(operation);
		}
		{		//Operations of Temperature
			UOperation operation = null;
			//operation : getAs(double, TemperatureUnit)
			operation = UMetaBuilder.manual().createOperation("getAs", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Temperature)instance).getAs((TemperatureUnit)parameter[0]);
				}
			});
				//Annotations for Temperature:getAs(double, TemperatureUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getTemperatureUnit(), 0, 1, UDirectionType.IN);
				mTemperature.getOperations().add(operation);
			//operation : set(void, double, TemperatureUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Temperature)instance).set((double)parameter[0], (TemperatureUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getTemperatureUnit(), 0, 1, UDirectionType.IN);
				mTemperature.getOperations().add(operation);
			//operation : set(void, Temperature)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Temperature)instance).set((Temperature)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getTemperature(), 0, 1, UDirectionType.IN);
				mTemperature.getOperations().add(operation);
			//operation : add(Temperature, Temperature)
			operation = UMetaBuilder.manual().createOperation("add", false, UnitsPackage.theInstance.getTemperature(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Temperature)instance).add((Temperature)parameter[0]);
				}
			});
				//Annotations for Temperature:add(Temperature, Temperature)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getTemperature(), 0, 1, UDirectionType.IN);
				mTemperature.getOperations().add(operation);
			//operation : add(Temperature, double, TemperatureUnit)
			operation = UMetaBuilder.manual().createOperation("add", false, UnitsPackage.theInstance.getTemperature(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Temperature)instance).add((double)parameter[0], (TemperatureUnit)parameter[1]);
				}
			});
				//Annotations for Temperature:add(Temperature, double, TemperatureUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getTemperatureUnit(), 0, 1, UDirectionType.IN);
				mTemperature.getOperations().add(operation);
			//operation : addLocal(void, Temperature)
			operation = UMetaBuilder.manual().createOperation("addLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Temperature)instance).addLocal((Temperature)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getTemperature(), 0, 1, UDirectionType.IN);
				mTemperature.getOperations().add(operation);
			//operation : addLocal(void, double, TemperatureUnit)
			operation = UMetaBuilder.manual().createOperation("addLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Temperature)instance).addLocal((double)parameter[0], (TemperatureUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getTemperatureUnit(), 0, 1, UDirectionType.IN);
				mTemperature.getOperations().add(operation);
			//operation : sub(Temperature, Temperature)
			operation = UMetaBuilder.manual().createOperation("sub", false, UnitsPackage.theInstance.getTemperature(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Temperature)instance).sub((Temperature)parameter[0]);
				}
			});
				//Annotations for Temperature:sub(Temperature, Temperature)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getTemperature(), 0, 1, UDirectionType.IN);
				mTemperature.getOperations().add(operation);
			//operation : sub(Temperature, double, TemperatureUnit)
			operation = UMetaBuilder.manual().createOperation("sub", false, UnitsPackage.theInstance.getTemperature(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Temperature)instance).sub((double)parameter[0], (TemperatureUnit)parameter[1]);
				}
			});
				//Annotations for Temperature:sub(Temperature, double, TemperatureUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getTemperatureUnit(), 0, 1, UDirectionType.IN);
				mTemperature.getOperations().add(operation);
			//operation : subLocal(void, Temperature)
			operation = UMetaBuilder.manual().createOperation("subLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Temperature)instance).subLocal((Temperature)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getTemperature(), 0, 1, UDirectionType.IN);
				mTemperature.getOperations().add(operation);
			//operation : subLocal(void, double, TemperatureUnit)
			operation = UMetaBuilder.manual().createOperation("subLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Temperature)instance).subLocal((double)parameter[0], (TemperatureUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getTemperatureUnit(), 0, 1, UDirectionType.IN);
				mTemperature.getOperations().add(operation);
		}
		{		//Operations of Angle
			UOperation operation = null;
			//operation : normalizeLocal(void)
			operation = UMetaBuilder.manual().createOperation("normalizeLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Angle)instance).normalizeLocal();
					return null;
				}
			});
				mAngle.getOperations().add(operation);
			//operation : normalize(Angle)
			operation = UMetaBuilder.manual().createOperation("normalize", false, UnitsPackage.theInstance.getAngle(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Angle)instance).normalize();
				}
			});
				//Annotations for Angle:normalize(Angle)
				operation.createAnnotation("const");
				mAngle.getOperations().add(operation);
			//operation : normalize180Local(void)
			operation = UMetaBuilder.manual().createOperation("normalize180Local", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Angle)instance).normalize180Local();
					return null;
				}
			});
				operation.setDocumentation(" normalize the Angle to an interval between -180\u00EF\u00BF\u00BD and 180\u00EF\u00BF\u00BD or -PI to PI ");
				mAngle.getOperations().add(operation);
			//operation : normalize180(Angle)
			operation = UMetaBuilder.manual().createOperation("normalize180", false, UnitsPackage.theInstance.getAngle(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Angle)instance).normalize180();
				}
			});
				operation.setDocumentation(" normalize the Angle to an interval between -180\u00EF\u00BF\u00BD and 180\u00EF\u00BF\u00BD or -PI to PI ");
				//Annotations for Angle:normalize180(Angle)
				operation.createAnnotation("const");
				mAngle.getOperations().add(operation);
			//operation : get(double, AngleUnit)
			operation = UMetaBuilder.manual().createOperation("get", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Angle)instance).get((AngleUnit)parameter[0]);
				}
			});
				operation.setDocumentation("\r\n * @deprecated use getAs(unit) instead\r\n ");
				//Annotations for Angle:get(double, AngleUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAngleUnit(), 0, 1, UDirectionType.IN);
				mAngle.getOperations().add(operation);
			//operation : getAs(double, AngleUnit)
			operation = UMetaBuilder.manual().createOperation("getAs", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Angle)instance).getAs((AngleUnit)parameter[0]);
				}
			});
				//Annotations for Angle:getAs(double, AngleUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAngleUnit(), 0, 1, UDirectionType.IN);
				mAngle.getOperations().add(operation);
			//operation : set(void, double, AngleUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Angle)instance).set((double)parameter[0], (AngleUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAngleUnit(), 0, 1, UDirectionType.IN);
				mAngle.getOperations().add(operation);
			//operation : set(void, Angle)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Angle)instance).set((Angle)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngle(), 0, 1, UDirectionType.IN);
				mAngle.getOperations().add(operation);
			//operation : add(Angle, Angle)
			operation = UMetaBuilder.manual().createOperation("add", false, UnitsPackage.theInstance.getAngle(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Angle)instance).add((Angle)parameter[0]);
				}
			});
				//Annotations for Angle:add(Angle, Angle)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngle(), 0, 1, UDirectionType.IN);
				mAngle.getOperations().add(operation);
			//operation : add(Angle, double, AngleUnit)
			operation = UMetaBuilder.manual().createOperation("add", false, UnitsPackage.theInstance.getAngle(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Angle)instance).add((double)parameter[0], (AngleUnit)parameter[1]);
				}
			});
				//Annotations for Angle:add(Angle, double, AngleUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAngleUnit(), 0, 1, UDirectionType.IN);
				mAngle.getOperations().add(operation);
			//operation : addLocal(void, Angle)
			operation = UMetaBuilder.manual().createOperation("addLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Angle)instance).addLocal((Angle)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngle(), 0, 1, UDirectionType.IN);
				mAngle.getOperations().add(operation);
			//operation : addLocal(void, double, AngleUnit)
			operation = UMetaBuilder.manual().createOperation("addLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Angle)instance).addLocal((double)parameter[0], (AngleUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAngleUnit(), 0, 1, UDirectionType.IN);
				mAngle.getOperations().add(operation);
			//operation : sub(Angle, Angle)
			operation = UMetaBuilder.manual().createOperation("sub", false, UnitsPackage.theInstance.getAngle(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Angle)instance).sub((Angle)parameter[0]);
				}
			});
				//Annotations for Angle:sub(Angle, Angle)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngle(), 0, 1, UDirectionType.IN);
				mAngle.getOperations().add(operation);
			//operation : sub(Angle, double, AngleUnit)
			operation = UMetaBuilder.manual().createOperation("sub", false, UnitsPackage.theInstance.getAngle(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Angle)instance).sub((double)parameter[0], (AngleUnit)parameter[1]);
				}
			});
				//Annotations for Angle:sub(Angle, double, AngleUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAngleUnit(), 0, 1, UDirectionType.IN);
				mAngle.getOperations().add(operation);
			//operation : subLocal(void, Angle)
			operation = UMetaBuilder.manual().createOperation("subLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Angle)instance).subLocal((Angle)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngle(), 0, 1, UDirectionType.IN);
				mAngle.getOperations().add(operation);
			//operation : subLocal(void, double, AngleUnit)
			operation = UMetaBuilder.manual().createOperation("subLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Angle)instance).subLocal((double)parameter[0], (AngleUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAngleUnit(), 0, 1, UDirectionType.IN);
				mAngle.getOperations().add(operation);
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Angle)instance).readableString();
				}
			});
				//Annotations for Angle:readableString(String)
				operation.createAnnotation("const");
				mAngle.getOperations().add(operation);
		}
		{		//Operations of Time
			UOperation operation = null;
			//operation : getAs(double, TimeUnit)
			operation = UMetaBuilder.manual().createOperation("getAs", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Time)instance).getAs((TimeUnit)parameter[0]);
				}
			});
				operation.setDocumentation(" returns the time value in the given unit, taking into account the current unit ");
				//Annotations for Time:getAs(double, TimeUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "targetUnit", UnitsPackage.theInstance.getTimeUnit(), 0, 1, UDirectionType.IN);
				mTime.getOperations().add(operation);
			//operation : set(void, double, TimeUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Time)instance).set((double)parameter[0], (TimeUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getTimeUnit(), 0, 1, UDirectionType.IN);
				mTime.getOperations().add(operation);
			//operation : set(void, Time)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Time)instance).set((Time)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mTime.getOperations().add(operation);
			//operation : smaller(boolean, Time)
			operation = UMetaBuilder.manual().createOperation("smaller", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Time)instance).smaller((Time)parameter[0]);
				}
			});
				//Annotations for Time:smaller(boolean, Time)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mTime.getOperations().add(operation);
			//operation : greater(boolean, Time)
			operation = UMetaBuilder.manual().createOperation("greater", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Time)instance).greater((Time)parameter[0]);
				}
			});
				//Annotations for Time:greater(boolean, Time)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mTime.getOperations().add(operation);
			//operation : equals(boolean, Time, double)
			operation = UMetaBuilder.manual().createOperation("equals", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Time)instance).equals((Time)parameter[0], (double)parameter[1]);
				}
			});
				operation.setDocumentation(" compares the time with another one, allowing a small epsilon\r\n * @returns false if the difference between both times, is more than the epsilon value\r\n ");
				//Annotations for Time:equals(boolean, Time, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "epsilon", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mTime.getOperations().add(operation);
			//operation : add(Time, Time)
			operation = UMetaBuilder.manual().createOperation("add", false, UnitsPackage.theInstance.getTime(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Time)instance).add((Time)parameter[0]);
				}
			});
				//Annotations for Time:add(Time, Time)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mTime.getOperations().add(operation);
			//operation : addLocal(void, Time)
			operation = UMetaBuilder.manual().createOperation("addLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Time)instance).addLocal((Time)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mTime.getOperations().add(operation);
			//operation : addLocal(void, double, TimeUnit)
			operation = UMetaBuilder.manual().createOperation("addLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Time)instance).addLocal((double)parameter[0], (TimeUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getTimeUnit(), 0, 1, UDirectionType.IN);
				mTime.getOperations().add(operation);
			//operation : sub(Time, Time)
			operation = UMetaBuilder.manual().createOperation("sub", false, UnitsPackage.theInstance.getTime(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Time)instance).sub((Time)parameter[0]);
				}
			});
				//Annotations for Time:sub(Time, Time)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mTime.getOperations().add(operation);
			//operation : subLocal(void, Time)
			operation = UMetaBuilder.manual().createOperation("subLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Time)instance).subLocal((Time)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mTime.getOperations().add(operation);
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Time)instance).readableString();
				}
			});
				//Annotations for Time:readableString(String)
				operation.createAnnotation("const");
				mTime.getOperations().add(operation);
			//operation : getYear(int)
			operation = UMetaBuilder.manual().createOperation("getYear", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Time)instance).getYear();
				}
			});
				//Annotations for Time:getYear(int)
				operation.createAnnotation("const");
				mTime.getOperations().add(operation);
			//operation : getMonth(int)
			operation = UMetaBuilder.manual().createOperation("getMonth", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Time)instance).getMonth();
				}
			});
				//Annotations for Time:getMonth(int)
				operation.createAnnotation("const");
				mTime.getOperations().add(operation);
			//operation : getDay(int)
			operation = UMetaBuilder.manual().createOperation("getDay", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Time)instance).getDay();
				}
			});
				//Annotations for Time:getDay(int)
				operation.createAnnotation("const");
				mTime.getOperations().add(operation);
			//operation : getHourOfDay(int)
			operation = UMetaBuilder.manual().createOperation("getHourOfDay", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Time)instance).getHourOfDay();
				}
			});
				//Annotations for Time:getHourOfDay(int)
				operation.createAnnotation("const");
				mTime.getOperations().add(operation);
			//operation : getMinuteOfHour(int)
			operation = UMetaBuilder.manual().createOperation("getMinuteOfHour", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Time)instance).getMinuteOfHour();
				}
			});
				//Annotations for Time:getMinuteOfHour(int)
				operation.createAnnotation("const");
				mTime.getOperations().add(operation);
			//operation : getSecondsOfMinute(int)
			operation = UMetaBuilder.manual().createOperation("getSecondsOfMinute", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Time)instance).getSecondsOfMinute();
				}
			});
				//Annotations for Time:getSecondsOfMinute(int)
				operation.createAnnotation("const");
				mTime.getOperations().add(operation);
			//operation : getMillisecondsOfSecond(int)
			operation = UMetaBuilder.manual().createOperation("getMillisecondsOfSecond", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Time)instance).getMillisecondsOfSecond();
				}
			});
				//Annotations for Time:getMillisecondsOfSecond(int)
				operation.createAnnotation("const");
				mTime.getOperations().add(operation);
		}
		{		//Operations of SpeedUnit
			UOperation operation = null;
			//operation : set(void, DistanceUnit, TimeUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((SpeedUnit)instance).set((DistanceUnit)parameter[0], (TimeUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "du", UnitsPackage.theInstance.getDistanceUnit(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "tu", UnitsPackage.theInstance.getTimeUnit(), 0, 1, UDirectionType.IN);
				mSpeedUnit.getOperations().add(operation);
			//operation : set(void, SpeedUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((SpeedUnit)instance).set((SpeedUnit)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getSpeedUnit(), 0, 1, UDirectionType.IN);
				mSpeedUnit.getOperations().add(operation);
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((SpeedUnit)instance).readableString();
				}
			});
				//Annotations for SpeedUnit:readableString(String)
				operation.createAnnotation("const");
				mSpeedUnit.getOperations().add(operation);
		}
		{		//Operations of Speed
			UOperation operation = null;
			//operation : getAs(double, SpeedUnit)
			operation = UMetaBuilder.manual().createOperation("getAs", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Speed)instance).getAs((SpeedUnit)parameter[0]);
				}
			});
				operation.setDocumentation(" \r\n * returns the speed in the provided unit \r\n ");
				//Annotations for Speed:getAs(double, SpeedUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getSpeedUnit(), 0, 1, UDirectionType.IN);
				mSpeed.getOperations().add(operation);
			//operation : set(void, double, SpeedUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Speed)instance).set((double)parameter[0], (SpeedUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getSpeedUnit(), 0, 1, UDirectionType.IN);
				mSpeed.getOperations().add(operation);
			//operation : set(void, Speed)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Speed)instance).set((Speed)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "speed", UnitsPackage.theInstance.getSpeed(), 0, 1, UDirectionType.IN);
				mSpeed.getOperations().add(operation);
			//operation : smaller(boolean, Speed)
			operation = UMetaBuilder.manual().createOperation("smaller", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Speed)instance).smaller((Speed)parameter[0]);
				}
			});
				//Annotations for Speed:smaller(boolean, Speed)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getSpeed(), 0, 1, UDirectionType.IN);
				mSpeed.getOperations().add(operation);
			//operation : greater(boolean, Speed)
			operation = UMetaBuilder.manual().createOperation("greater", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Speed)instance).greater((Speed)parameter[0]);
				}
			});
				//Annotations for Speed:greater(boolean, Speed)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getSpeed(), 0, 1, UDirectionType.IN);
				mSpeed.getOperations().add(operation);
			//operation : equals(boolean, Speed, double)
			operation = UMetaBuilder.manual().createOperation("equals", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Speed)instance).equals((Speed)parameter[0], (double)parameter[1]);
				}
			});
				//Annotations for Speed:equals(boolean, Speed, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getSpeed(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "epsilon", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mSpeed.getOperations().add(operation);
			//operation : add(Speed, Speed)
			operation = UMetaBuilder.manual().createOperation("add", false, UnitsPackage.theInstance.getSpeed(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Speed)instance).add((Speed)parameter[0]);
				}
			});
				//Annotations for Speed:add(Speed, Speed)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getSpeed(), 0, 1, UDirectionType.IN);
				mSpeed.getOperations().add(operation);
			//operation : addLocal(void, Speed)
			operation = UMetaBuilder.manual().createOperation("addLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Speed)instance).addLocal((Speed)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getSpeed(), 0, 1, UDirectionType.IN);
				mSpeed.getOperations().add(operation);
			//operation : sub(Speed, Speed)
			operation = UMetaBuilder.manual().createOperation("sub", false, UnitsPackage.theInstance.getSpeed(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Speed)instance).sub((Speed)parameter[0]);
				}
			});
				//Annotations for Speed:sub(Speed, Speed)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getSpeed(), 0, 1, UDirectionType.IN);
				mSpeed.getOperations().add(operation);
			//operation : subLocal(void, Speed)
			operation = UMetaBuilder.manual().createOperation("subLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Speed)instance).subLocal((Speed)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getSpeed(), 0, 1, UDirectionType.IN);
				mSpeed.getOperations().add(operation);
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Speed)instance).readableString();
				}
			});
				//Annotations for Speed:readableString(String)
				operation.createAnnotation("const");
				mSpeed.getOperations().add(operation);
			//operation : getDistanceOverTime(Distance, Time)
			operation = UMetaBuilder.manual().createOperation("getDistanceOverTime", false, UnitsPackage.theInstance.getDistance(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Speed)instance).getDistanceOverTime((Time)parameter[0]);
				}
			});
				operation.setDocumentation("\r\n * @deprecated use integrate instead\r\n ");
				//Annotations for Speed:getDistanceOverTime(Distance, Time)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "etablished", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mSpeed.getOperations().add(operation);
			//operation : integrate(Distance, Time)
			operation = UMetaBuilder.manual().createOperation("integrate", false, UnitsPackage.theInstance.getDistance(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Speed)instance).integrate((Time)parameter[0]);
				}
			});
				//Annotations for Speed:integrate(Distance, Time)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "etablished", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mSpeed.getOperations().add(operation);
			//operation : lerp(Speed, Speed, double)
			operation = UMetaBuilder.manual().createOperation("lerp", false, UnitsPackage.theInstance.getSpeed(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Speed)instance).lerp((Speed)parameter[0], (double)parameter[1]);
				}
			});
				//Annotations for Speed:lerp(Speed, Speed, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getSpeed(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "factor", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mSpeed.getOperations().add(operation);
		}
		{		//Operations of AngularSpeedUnit
			UOperation operation = null;
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularSpeedUnit)instance).readableString();
				}
			});
				//Annotations for AngularSpeedUnit:readableString(String)
				operation.createAnnotation("const");
				mAngularSpeedUnit.getOperations().add(operation);
			//operation : set(void, AngleUnit, TimeUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((AngularSpeedUnit)instance).set((AngleUnit)parameter[0], (TimeUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "au", UnitsPackage.theInstance.getAngleUnit(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "tu", UnitsPackage.theInstance.getTimeUnit(), 0, 1, UDirectionType.IN);
				mAngularSpeedUnit.getOperations().add(operation);
			//operation : set(void, AngularSpeedUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((AngularSpeedUnit)instance).set((AngularSpeedUnit)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularSpeedUnit(), 0, 1, UDirectionType.IN);
				mAngularSpeedUnit.getOperations().add(operation);
		}
		{		//Operations of AngularSpeed
			UOperation operation = null;
			//operation : getAs(double, AngularSpeedUnit)
			operation = UMetaBuilder.manual().createOperation("getAs", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularSpeed)instance).getAs((AngularSpeedUnit)parameter[0]);
				}
			});
				operation.setDocumentation(" \r\n * returns the angular speed in the provided unit \r\n ");
				//Annotations for AngularSpeed:getAs(double, AngularSpeedUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAngularSpeedUnit(), 0, 1, UDirectionType.IN);
				mAngularSpeed.getOperations().add(operation);
			//operation : set(void, double, AngularSpeedUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((AngularSpeed)instance).set((double)parameter[0], (AngularSpeedUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAngularSpeedUnit(), 0, 1, UDirectionType.IN);
				mAngularSpeed.getOperations().add(operation);
			//operation : set(void, AngularSpeed)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((AngularSpeed)instance).set((AngularSpeed)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", UnitsPackage.theInstance.getAngularSpeed(), 0, 1, UDirectionType.IN);
				mAngularSpeed.getOperations().add(operation);
			//operation : smaller(boolean, AngularSpeed)
			operation = UMetaBuilder.manual().createOperation("smaller", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularSpeed)instance).smaller((AngularSpeed)parameter[0]);
				}
			});
				//Annotations for AngularSpeed:smaller(boolean, AngularSpeed)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularSpeed(), 0, 1, UDirectionType.IN);
				mAngularSpeed.getOperations().add(operation);
			//operation : greater(boolean, AngularSpeed)
			operation = UMetaBuilder.manual().createOperation("greater", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularSpeed)instance).greater((AngularSpeed)parameter[0]);
				}
			});
				//Annotations for AngularSpeed:greater(boolean, AngularSpeed)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularSpeed(), 0, 1, UDirectionType.IN);
				mAngularSpeed.getOperations().add(operation);
			//operation : equals(boolean, AngularSpeed, double)
			operation = UMetaBuilder.manual().createOperation("equals", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularSpeed)instance).equals((AngularSpeed)parameter[0], (double)parameter[1]);
				}
			});
				//Annotations for AngularSpeed:equals(boolean, AngularSpeed, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularSpeed(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "epsilon", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mAngularSpeed.getOperations().add(operation);
			//operation : add(AngularSpeed, AngularSpeed)
			operation = UMetaBuilder.manual().createOperation("add", false, UnitsPackage.theInstance.getAngularSpeed(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularSpeed)instance).add((AngularSpeed)parameter[0]);
				}
			});
				//Annotations for AngularSpeed:add(AngularSpeed, AngularSpeed)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularSpeed(), 0, 1, UDirectionType.IN);
				mAngularSpeed.getOperations().add(operation);
			//operation : addLocal(void, AngularSpeed)
			operation = UMetaBuilder.manual().createOperation("addLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((AngularSpeed)instance).addLocal((AngularSpeed)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularSpeed(), 0, 1, UDirectionType.IN);
				mAngularSpeed.getOperations().add(operation);
			//operation : sub(AngularSpeed, AngularSpeed)
			operation = UMetaBuilder.manual().createOperation("sub", false, UnitsPackage.theInstance.getAngularSpeed(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularSpeed)instance).sub((AngularSpeed)parameter[0]);
				}
			});
				//Annotations for AngularSpeed:sub(AngularSpeed, AngularSpeed)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularSpeed(), 0, 1, UDirectionType.IN);
				mAngularSpeed.getOperations().add(operation);
			//operation : subLocal(void, AngularSpeed)
			operation = UMetaBuilder.manual().createOperation("subLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((AngularSpeed)instance).subLocal((AngularSpeed)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularSpeed(), 0, 1, UDirectionType.IN);
				mAngularSpeed.getOperations().add(operation);
			//operation : getDeltaAngleOverTime(Angle, Time)
			operation = UMetaBuilder.manual().createOperation("getDeltaAngleOverTime", false, UnitsPackage.theInstance.getAngle(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularSpeed)instance).getDeltaAngleOverTime((Time)parameter[0]);
				}
			});
				operation.setDocumentation("\r\n * @Deprecated use integrate(etablished) instead\r\n ");
				//Annotations for AngularSpeed:getDeltaAngleOverTime(Angle, Time)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "etablished", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mAngularSpeed.getOperations().add(operation);
			//operation : integrate(Angle, Time)
			operation = UMetaBuilder.manual().createOperation("integrate", false, UnitsPackage.theInstance.getAngle(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularSpeed)instance).integrate((Time)parameter[0]);
				}
			});
				//Annotations for AngularSpeed:integrate(Angle, Time)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "etablished", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mAngularSpeed.getOperations().add(operation);
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularSpeed)instance).readableString();
				}
			});
				//Annotations for AngularSpeed:readableString(String)
				operation.createAnnotation("const");
				mAngularSpeed.getOperations().add(operation);
		}
		{		//Operations of AccelerationUnit
			UOperation operation = null;
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AccelerationUnit)instance).readableString();
				}
			});
				//Annotations for AccelerationUnit:readableString(String)
				operation.createAnnotation("const");
				mAccelerationUnit.getOperations().add(operation);
		}
		{		//Operations of Acceleration
			UOperation operation = null;
			//operation : getAs(double, AccelerationUnit)
			operation = UMetaBuilder.manual().createOperation("getAs", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Acceleration)instance).getAs((AccelerationUnit)parameter[0]);
				}
			});
				operation.setDocumentation(" \r\n * returns the acceleration in the provided unit \r\n ");
				//Annotations for Acceleration:getAs(double, AccelerationUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAccelerationUnit(), 0, 1, UDirectionType.IN);
				mAcceleration.getOperations().add(operation);
			//operation : set(void, double, AccelerationUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Acceleration)instance).set((double)parameter[0], (AccelerationUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAccelerationUnit(), 0, 1, UDirectionType.IN);
				mAcceleration.getOperations().add(operation);
			//operation : set(void, Acceleration)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Acceleration)instance).set((Acceleration)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAcceleration(), 0, 1, UDirectionType.IN);
				mAcceleration.getOperations().add(operation);
			//operation : smaller(boolean, Acceleration)
			operation = UMetaBuilder.manual().createOperation("smaller", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Acceleration)instance).smaller((Acceleration)parameter[0]);
				}
			});
				//Annotations for Acceleration:smaller(boolean, Acceleration)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAcceleration(), 0, 1, UDirectionType.IN);
				mAcceleration.getOperations().add(operation);
			//operation : greater(boolean, Acceleration)
			operation = UMetaBuilder.manual().createOperation("greater", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Acceleration)instance).greater((Acceleration)parameter[0]);
				}
			});
				//Annotations for Acceleration:greater(boolean, Acceleration)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAcceleration(), 0, 1, UDirectionType.IN);
				mAcceleration.getOperations().add(operation);
			//operation : equals(boolean, Acceleration, double)
			operation = UMetaBuilder.manual().createOperation("equals", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Acceleration)instance).equals((Acceleration)parameter[0], (double)parameter[1]);
				}
			});
				//Annotations for Acceleration:equals(boolean, Acceleration, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAcceleration(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "epsilon", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mAcceleration.getOperations().add(operation);
			//operation : add(Acceleration, Acceleration)
			operation = UMetaBuilder.manual().createOperation("add", false, UnitsPackage.theInstance.getAcceleration(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Acceleration)instance).add((Acceleration)parameter[0]);
				}
			});
				//Annotations for Acceleration:add(Acceleration, Acceleration)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAcceleration(), 0, 1, UDirectionType.IN);
				mAcceleration.getOperations().add(operation);
			//operation : addLocal(void, Acceleration)
			operation = UMetaBuilder.manual().createOperation("addLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Acceleration)instance).addLocal((Acceleration)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAcceleration(), 0, 1, UDirectionType.IN);
				mAcceleration.getOperations().add(operation);
			//operation : sub(Acceleration, Acceleration)
			operation = UMetaBuilder.manual().createOperation("sub", false, UnitsPackage.theInstance.getAcceleration(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Acceleration)instance).sub((Acceleration)parameter[0]);
				}
			});
				//Annotations for Acceleration:sub(Acceleration, Acceleration)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAcceleration(), 0, 1, UDirectionType.IN);
				mAcceleration.getOperations().add(operation);
			//operation : subLocal(void, Acceleration)
			operation = UMetaBuilder.manual().createOperation("subLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Acceleration)instance).subLocal((Acceleration)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAcceleration(), 0, 1, UDirectionType.IN);
				mAcceleration.getOperations().add(operation);
			//operation : getDeltaSpeedOverTime(Speed, Time)
			operation = UMetaBuilder.manual().createOperation("getDeltaSpeedOverTime", false, UnitsPackage.theInstance.getSpeed(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Acceleration)instance).getDeltaSpeedOverTime((Time)parameter[0]);
				}
			});
				//Annotations for Acceleration:getDeltaSpeedOverTime(Speed, Time)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "etablished", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mAcceleration.getOperations().add(operation);
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Acceleration)instance).readableString();
				}
			});
				//Annotations for Acceleration:readableString(String)
				operation.createAnnotation("const");
				mAcceleration.getOperations().add(operation);
		}
		{		//Operations of AngularAccelerationUnit
			UOperation operation = null;
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularAccelerationUnit)instance).readableString();
				}
			});
				//Annotations for AngularAccelerationUnit:readableString(String)
				operation.createAnnotation("const");
				mAngularAccelerationUnit.getOperations().add(operation);
		}
		{		//Operations of AngularAcceleration
			UOperation operation = null;
			//operation : getAs(double, AngularAccelerationUnit)
			operation = UMetaBuilder.manual().createOperation("getAs", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularAcceleration)instance).getAs((AngularAccelerationUnit)parameter[0]);
				}
			});
				operation.setDocumentation(" \r\n * returns the angular acceleration in the provided unit \r\n ");
				//Annotations for AngularAcceleration:getAs(double, AngularAccelerationUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAngularAccelerationUnit(), 0, 1, UDirectionType.IN);
				mAngularAcceleration.getOperations().add(operation);
			//operation : set(void, double, AngularAccelerationUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((AngularAcceleration)instance).set((double)parameter[0], (AngularAccelerationUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAngularAccelerationUnit(), 0, 1, UDirectionType.IN);
				mAngularAcceleration.getOperations().add(operation);
			//operation : set(void, AngularAcceleration)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((AngularAcceleration)instance).set((AngularAcceleration)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularAcceleration(), 0, 1, UDirectionType.IN);
				mAngularAcceleration.getOperations().add(operation);
			//operation : smaller(boolean, AngularAcceleration)
			operation = UMetaBuilder.manual().createOperation("smaller", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularAcceleration)instance).smaller((AngularAcceleration)parameter[0]);
				}
			});
				//Annotations for AngularAcceleration:smaller(boolean, AngularAcceleration)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularAcceleration(), 0, 1, UDirectionType.IN);
				mAngularAcceleration.getOperations().add(operation);
			//operation : greater(boolean, AngularAcceleration)
			operation = UMetaBuilder.manual().createOperation("greater", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularAcceleration)instance).greater((AngularAcceleration)parameter[0]);
				}
			});
				//Annotations for AngularAcceleration:greater(boolean, AngularAcceleration)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularAcceleration(), 0, 1, UDirectionType.IN);
				mAngularAcceleration.getOperations().add(operation);
			//operation : equals(boolean, AngularAcceleration, double)
			operation = UMetaBuilder.manual().createOperation("equals", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularAcceleration)instance).equals((AngularAcceleration)parameter[0], (double)parameter[1]);
				}
			});
				//Annotations for AngularAcceleration:equals(boolean, AngularAcceleration, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularAcceleration(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "epsilon", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mAngularAcceleration.getOperations().add(operation);
			//operation : add(AngularAcceleration, AngularAcceleration)
			operation = UMetaBuilder.manual().createOperation("add", false, UnitsPackage.theInstance.getAngularAcceleration(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularAcceleration)instance).add((AngularAcceleration)parameter[0]);
				}
			});
				//Annotations for AngularAcceleration:add(AngularAcceleration, AngularAcceleration)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularAcceleration(), 0, 1, UDirectionType.IN);
				mAngularAcceleration.getOperations().add(operation);
			//operation : addLocal(void, AngularAcceleration)
			operation = UMetaBuilder.manual().createOperation("addLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((AngularAcceleration)instance).addLocal((AngularAcceleration)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularAcceleration(), 0, 1, UDirectionType.IN);
				mAngularAcceleration.getOperations().add(operation);
			//operation : sub(AngularAcceleration, AngularAcceleration)
			operation = UMetaBuilder.manual().createOperation("sub", false, UnitsPackage.theInstance.getAngularAcceleration(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularAcceleration)instance).sub((AngularAcceleration)parameter[0]);
				}
			});
				//Annotations for AngularAcceleration:sub(AngularAcceleration, AngularAcceleration)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularAcceleration(), 0, 1, UDirectionType.IN);
				mAngularAcceleration.getOperations().add(operation);
			//operation : subLocal(void, AngularAcceleration)
			operation = UMetaBuilder.manual().createOperation("subLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((AngularAcceleration)instance).subLocal((AngularAcceleration)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularAcceleration(), 0, 1, UDirectionType.IN);
				mAngularAcceleration.getOperations().add(operation);
			//operation : getDeltaSpeedOverTime(AngularSpeed, Time)
			operation = UMetaBuilder.manual().createOperation("getDeltaSpeedOverTime", false, UnitsPackage.theInstance.getAngularSpeed(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularAcceleration)instance).getDeltaSpeedOverTime((Time)parameter[0]);
				}
			});
				//Annotations for AngularAcceleration:getDeltaSpeedOverTime(AngularSpeed, Time)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "etablished", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mAngularAcceleration.getOperations().add(operation);
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularAcceleration)instance).readableString();
				}
			});
				//Annotations for AngularAcceleration:readableString(String)
				operation.createAnnotation("const");
				mAngularAcceleration.getOperations().add(operation);
		}
		{		//Operations of Mass
			UOperation operation = null;
			//operation : getAs(double, MassUnit)
			operation = UMetaBuilder.manual().createOperation("getAs", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Mass)instance).getAs((MassUnit)parameter[0]);
				}
			});
				operation.setDocumentation(" \r\n * returns the mass value with the provided unit \r\n ");
				//Annotations for Mass:getAs(double, MassUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getMassUnit(), 0, 1, UDirectionType.IN);
				mMass.getOperations().add(operation);
			//operation : set(void, double, MassUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Mass)instance).set((double)parameter[0], (MassUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getMassUnit(), 0, 1, UDirectionType.IN);
				mMass.getOperations().add(operation);
			//operation : set(void, Mass)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Mass)instance).set((Mass)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getMass(), 0, 1, UDirectionType.IN);
				mMass.getOperations().add(operation);
			//operation : smaller(boolean, Mass)
			operation = UMetaBuilder.manual().createOperation("smaller", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Mass)instance).smaller((Mass)parameter[0]);
				}
			});
				//Annotations for Mass:smaller(boolean, Mass)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getMass(), 0, 1, UDirectionType.IN);
				mMass.getOperations().add(operation);
			//operation : greater(boolean, Mass)
			operation = UMetaBuilder.manual().createOperation("greater", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Mass)instance).greater((Mass)parameter[0]);
				}
			});
				//Annotations for Mass:greater(boolean, Mass)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getMass(), 0, 1, UDirectionType.IN);
				mMass.getOperations().add(operation);
			//operation : equals(boolean, Mass, double)
			operation = UMetaBuilder.manual().createOperation("equals", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Mass)instance).equals((Mass)parameter[0], (double)parameter[1]);
				}
			});
				//Annotations for Mass:equals(boolean, Mass, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getMass(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "epsilon", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mMass.getOperations().add(operation);
			//operation : add(Mass, Mass)
			operation = UMetaBuilder.manual().createOperation("add", false, UnitsPackage.theInstance.getMass(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Mass)instance).add((Mass)parameter[0]);
				}
			});
				//Annotations for Mass:add(Mass, Mass)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getMass(), 0, 1, UDirectionType.IN);
				mMass.getOperations().add(operation);
			//operation : addLocal(void, Mass)
			operation = UMetaBuilder.manual().createOperation("addLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Mass)instance).addLocal((Mass)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getMass(), 0, 1, UDirectionType.IN);
				mMass.getOperations().add(operation);
			//operation : sub(Mass, Mass)
			operation = UMetaBuilder.manual().createOperation("sub", false, UnitsPackage.theInstance.getMass(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Mass)instance).sub((Mass)parameter[0]);
				}
			});
				//Annotations for Mass:sub(Mass, Mass)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getMass(), 0, 1, UDirectionType.IN);
				mMass.getOperations().add(operation);
			//operation : subLocal(void, Mass)
			operation = UMetaBuilder.manual().createOperation("subLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Mass)instance).subLocal((Mass)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getMass(), 0, 1, UDirectionType.IN);
				mMass.getOperations().add(operation);
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Mass)instance).readableString();
				}
			});
				//Annotations for Mass:readableString(String)
				operation.createAnnotation("const");
				mMass.getOperations().add(operation);
		}
		{		//Operations of Orientation
			UOperation operation = null;
			//operation : toEuler(Euler)
			operation = UMetaBuilder.manual().createOperation("toEuler", false, UnitsPackage.theInstance.getEuler(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Orientation)instance).toEuler();
				}
			});
				//Annotations for Orientation:toEuler(Euler)
				operation.createAnnotation("const");
				mOrientation.getOperations().add(operation);
			//operation : toQuaternion(Quaternion)
			operation = UMetaBuilder.manual().createOperation("toQuaternion", false, UnitsPackage.theInstance.getQuaternion(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Orientation)instance).toQuaternion();
				}
			});
				//Annotations for Orientation:toQuaternion(Quaternion)
				operation.createAnnotation("const");
				mOrientation.getOperations().add(operation);
			//operation : toMatrix3(Matrix3)
			operation = UMetaBuilder.manual().createOperation("toMatrix3", false, MathPackage.theInstance.getMatrix3(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Orientation)instance).toMatrix3();
				}
			});
				//Annotations for Orientation:toMatrix3(Matrix3)
				operation.createAnnotation("const");
				mOrientation.getOperations().add(operation);
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Orientation)instance).readableString();
				}
			});
				//Annotations for Orientation:readableString(String)
				operation.createAnnotation("const");
				mOrientation.getOperations().add(operation);
		}
		{		//Operations of Rotation
			UOperation operation = null;
			//operation : transform2D(Vector2D, Vector2D)
			operation = UMetaBuilder.manual().createOperation("transform2D", false, MathPackage.theInstance.getVector2D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Rotation)instance).transform2D((Vector2D)parameter[0]);
				}
			});
				//Annotations for Rotation:transform2D(Vector2D, Vector2D)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector2D(), 0, 1, UDirectionType.IN);
				mRotation.getOperations().add(operation);
			//operation : transform3D(Vector3D, Vector3D)
			operation = UMetaBuilder.manual().createOperation("transform3D", false, MathPackage.theInstance.getVector3D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Rotation)instance).transform3D((Vector3D)parameter[0]);
				}
			});
				//Annotations for Rotation:transform3D(Vector3D, Vector3D)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", MathPackage.theInstance.getVector3D(), 0, 1, UDirectionType.IN);
				mRotation.getOperations().add(operation);
		}
		{		//Operations of Euler
			UOperation operation = null;
			//operation : normalize(void)
			operation = UMetaBuilder.manual().createOperation("normalize", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Euler)instance).normalize();
					return null;
				}
			});
				operation.setDocumentation(" normalizes all angles to a value between 0 and 360 Degree or 0 and 2*PI ");
				mEuler.getOperations().add(operation);
			//operation : set(void, double, double, double, AngleUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Euler)instance).set((double)parameter[0], (double)parameter[1], (double)parameter[2], (AngleUnit)parameter[3]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "x", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "y", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "z", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAngleUnit(), 0, 1, UDirectionType.IN);
				mEuler.getOperations().add(operation);
			//operation : set(void, Angle, Angle, Angle)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Euler)instance).set((Angle)parameter[0], (Angle)parameter[1], (Angle)parameter[2]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "x", UnitsPackage.theInstance.getAngle(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "y", UnitsPackage.theInstance.getAngle(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "z", UnitsPackage.theInstance.getAngle(), 0, 1, UDirectionType.IN);
				mEuler.getOperations().add(operation);
			//operation : set(void, Euler)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Euler)instance).set((Euler)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getEuler(), 0, 1, UDirectionType.IN);
				mEuler.getOperations().add(operation);
			//operation : toEuler(Euler)
			operation = UMetaBuilder.manual().createOperation("toEuler", false, UnitsPackage.theInstance.getEuler(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Euler)instance).toEuler();
				}
			});
				//Annotations for Euler:toEuler(Euler)
				operation.createAnnotation("const");
				mEuler.getOperations().add(operation);
			//operation : toQuaternion(Quaternion)
			operation = UMetaBuilder.manual().createOperation("toQuaternion", false, UnitsPackage.theInstance.getQuaternion(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Euler)instance).toQuaternion();
				}
			});
				//Annotations for Euler:toQuaternion(Quaternion)
				operation.createAnnotation("const");
				mEuler.getOperations().add(operation);
			//operation : toMatrix3(Matrix3)
			operation = UMetaBuilder.manual().createOperation("toMatrix3", false, MathPackage.theInstance.getMatrix3(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Euler)instance).toMatrix3();
				}
			});
				//Annotations for Euler:toMatrix3(Matrix3)
				operation.createAnnotation("const");
				mEuler.getOperations().add(operation);
		}
		{		//Operations of Quaternion
			UOperation operation = null;
			//operation : fromEuler(void, Euler)
			operation = UMetaBuilder.manual().createOperation("fromEuler", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Quaternion)instance).fromEuler((Euler)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "e", UnitsPackage.theInstance.getEuler(), 0, 1, UDirectionType.IN);
				mQuaternion.getOperations().add(operation);
			//operation : multiply(Quaternion, Quaternion)
			operation = UMetaBuilder.manual().createOperation("multiply", false, UnitsPackage.theInstance.getQuaternion(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Quaternion)instance).multiply((Quaternion)parameter[0]);
				}
			});
				//Annotations for Quaternion:multiply(Quaternion, Quaternion)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getQuaternion(), 0, 1, UDirectionType.IN);
				mQuaternion.getOperations().add(operation);
			//operation : toQuaternion(Quaternion)
			operation = UMetaBuilder.manual().createOperation("toQuaternion", false, UnitsPackage.theInstance.getQuaternion(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Quaternion)instance).toQuaternion();
				}
			});
				//Annotations for Quaternion:toQuaternion(Quaternion)
				operation.createAnnotation("const");
				mQuaternion.getOperations().add(operation);
			//operation : toEuler(Euler)
			operation = UMetaBuilder.manual().createOperation("toEuler", false, UnitsPackage.theInstance.getEuler(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Quaternion)instance).toEuler();
				}
			});
				mQuaternion.getOperations().add(operation);
			//operation : toMatrix3(Matrix3)
			operation = UMetaBuilder.manual().createOperation("toMatrix3", false, MathPackage.theInstance.getMatrix3(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Quaternion)instance).toMatrix3();
				}
			});
				mQuaternion.getOperations().add(operation);
			//operation : set(void, double, double, double, AngleUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Quaternion)instance).set((double)parameter[0], (double)parameter[1], (double)parameter[2], (AngleUnit)parameter[3]);
					return null;
				}
			});
				operation.setDocumentation(" set the orientation from Euler angles ");
				UMetaBuilder.manual().addParameter(operation, "x", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "y", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "z", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAngleUnit(), 0, 1, UDirectionType.IN);
				mQuaternion.getOperations().add(operation);
			//operation : set(void, double, double, double, double)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Quaternion)instance).set((double)parameter[0], (double)parameter[1], (double)parameter[2], (double)parameter[3]);
					return null;
				}
			});
				operation.setDocumentation(" convenience method to set all variables at once ");
				UMetaBuilder.manual().addParameter(operation, "x", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "y", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "z", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "w", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mQuaternion.getOperations().add(operation);
			//operation : set(void, Quaternion)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Quaternion)instance).set((Quaternion)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", UnitsPackage.theInstance.getQuaternion(), 0, 1, UDirectionType.IN);
				mQuaternion.getOperations().add(operation);
			//operation : normalizeLocal(void)
			operation = UMetaBuilder.manual().createOperation("normalizeLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Quaternion)instance).normalizeLocal();
					return null;
				}
			});
				mQuaternion.getOperations().add(operation);
			//operation : normalize(Quaternion)
			operation = UMetaBuilder.manual().createOperation("normalize", false, UnitsPackage.theInstance.getQuaternion(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Quaternion)instance).normalize();
				}
			});
				mQuaternion.getOperations().add(operation);
			//operation : norm(double)
			operation = UMetaBuilder.manual().createOperation("norm", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Quaternion)instance).norm();
				}
			});
				operation.setDocumentation(" \r\n * returns the norm of this quaternion. This is the dot\r\n     * product of this quaternion with itself.\r\n*");
				mQuaternion.getOperations().add(operation);
		}
		{		//Operations of Velocity
			UOperation operation = null;
			//operation : getAs(Vector, SpeedUnit)
			operation = UMetaBuilder.manual().createOperation("getAs", false, MathPackage.theInstance.getVector(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Velocity)instance).getAs((SpeedUnit)parameter[0]);
				}
			});
				operation.setDocumentation(" \r\n * returns a vector which components are given in the provided SpeedUnit\r\n * @note if the provided unit is the same as this.unit, this.value is returned. \r\n ");
				//Annotations for Velocity:getAs(Vector, SpeedUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getSpeedUnit(), 0, 1, UDirectionType.IN);
				mVelocity.getOperations().add(operation);
			//operation : interpolate(Vector, Vector, Time)
			operation = UMetaBuilder.manual().createOperation("interpolate", false, MathPackage.theInstance.getVector(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Velocity)instance).interpolate((Vector)parameter[0], (Time)parameter[1]);
				}
			});
				//Annotations for Velocity:interpolate(Vector, Vector, Time)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "current", MathPackage.theInstance.getVector(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "etablished", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mVelocity.getOperations().add(operation);
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Velocity)instance).readableString();
				}
			});
				//Annotations for Velocity:readableString(String)
				operation.createAnnotation("const");
				mVelocity.getOperations().add(operation);
			//operation : getMagnitude(Speed)
			operation = UMetaBuilder.manual().createOperation("getMagnitude", false, UnitsPackage.theInstance.getSpeed(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Velocity)instance).getMagnitude();
				}
			});
				//Annotations for Velocity:getMagnitude(Speed)
				operation.createAnnotation("const");
				mVelocity.getOperations().add(operation);
			//operation : set(void, Vector, SpeedUnit, CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Velocity)instance).set((Vector)parameter[0], (SpeedUnit)parameter[1], (CoordinateReferenceSystem)parameter[2]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", MathPackage.theInstance.getVector(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getSpeedUnit(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, UDirectionType.IN);
				mVelocity.getOperations().add(operation);
			//operation : set(void, Velocity)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Velocity)instance).set((Velocity)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getVelocity(), 0, 1, UDirectionType.IN);
				mVelocity.getOperations().add(operation);
			//operation : get(Velocity, CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("get", false, UnitsPackage.theInstance.getVelocity(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Velocity)instance).get((CoordinateReferenceSystem)parameter[0]);
				}
			});
				operation.setDocumentation("\r\n * transforms the velocity into the <param>targetCRS</param> coordinate reference system\r\n * if targetCRS equals to crs, a copy of this velocity is returned\r\n * @param targetCRS destination coordinate reference system\r\n * @return a new velocity, expressed in targetCRS\r\n ");
				//Annotations for Velocity:get(Velocity, CoordinateReferenceSystem)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "targetCRS", CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, UDirectionType.IN);
				mVelocity.getOperations().add(operation);
		}
		{		//Operations of AngularVelocity
			UOperation operation = null;
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularVelocity)instance).readableString();
				}
			});
				//Annotations for AngularVelocity:readableString(String)
				operation.createAnnotation("const");
				mAngularVelocity.getOperations().add(operation);
			//operation : set(void, Euler, AngularSpeedUnit, CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((AngularVelocity)instance).set((Euler)parameter[0], (AngularSpeedUnit)parameter[1], (CoordinateReferenceSystem)parameter[2]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", UnitsPackage.theInstance.getEuler(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAngularSpeedUnit(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, UDirectionType.IN);
				mAngularVelocity.getOperations().add(operation);
			//operation : set(void, AngularVelocity)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((AngularVelocity)instance).set((AngularVelocity)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getAngularVelocity(), 0, 1, UDirectionType.IN);
				mAngularVelocity.getOperations().add(operation);
			//operation : getChange(Euler, Time)
			operation = UMetaBuilder.manual().createOperation("getChange", false, UnitsPackage.theInstance.getEuler(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularVelocity)instance).getChange((Time)parameter[0]);
				}
			});
				operation.setDocumentation(" Returns the changed angle for each axis in a given time\r\n * @deprecated use integrate instead\r\n ");
				//Annotations for AngularVelocity:getChange(Euler, Time)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "elapsed", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mAngularVelocity.getOperations().add(operation);
			//operation : integrate(Euler, Time)
			operation = UMetaBuilder.manual().createOperation("integrate", false, UnitsPackage.theInstance.getEuler(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularVelocity)instance).integrate((Time)parameter[0]);
				}
			});
				operation.setDocumentation(" Returns the changed angle for each axis in a given time ");
				//Annotations for AngularVelocity:integrate(Euler, Time)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "elapsed", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mAngularVelocity.getOperations().add(operation);
			//operation : getX(AngularSpeed)
			operation = UMetaBuilder.manual().createOperation("getX", false, UnitsPackage.theInstance.getAngularSpeed(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularVelocity)instance).getX();
				}
			});
				//Annotations for AngularVelocity:getX(AngularSpeed)
				operation.createAnnotation("const");
				mAngularVelocity.getOperations().add(operation);
			//operation : getY(AngularSpeed)
			operation = UMetaBuilder.manual().createOperation("getY", false, UnitsPackage.theInstance.getAngularSpeed(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularVelocity)instance).getY();
				}
			});
				//Annotations for AngularVelocity:getY(AngularSpeed)
				operation.createAnnotation("const");
				mAngularVelocity.getOperations().add(operation);
			//operation : getZ(AngularSpeed)
			operation = UMetaBuilder.manual().createOperation("getZ", false, UnitsPackage.theInstance.getAngularSpeed(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularVelocity)instance).getZ();
				}
			});
				//Annotations for AngularVelocity:getZ(AngularSpeed)
				operation.createAnnotation("const");
				mAngularVelocity.getOperations().add(operation);
			//operation : getAs(Euler, AngularSpeedUnit)
			operation = UMetaBuilder.manual().createOperation("getAs", false, UnitsPackage.theInstance.getEuler(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((AngularVelocity)instance).getAs((AngularSpeedUnit)parameter[0]);
				}
			});
				//Annotations for AngularVelocity:getAs(Euler, AngularSpeedUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getAngularSpeedUnit(), 0, 1, UDirectionType.IN);
				mAngularVelocity.getOperations().add(operation);
		}
		{		//Operations of Power
			UOperation operation = null;
			//operation : getAs(double, PowerUnit)
			operation = UMetaBuilder.manual().createOperation("getAs", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Power)instance).getAs((PowerUnit)parameter[0]);
				}
			});
				//Annotations for Power:getAs(double, PowerUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "dst_unit", UnitsPackage.theInstance.getPowerUnit(), 0, 1, UDirectionType.IN);
				mPower.getOperations().add(operation);
			//operation : set(void, double, PowerUnit)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Power)instance).set((double)parameter[0], (PowerUnit)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getPowerUnit(), 0, 1, UDirectionType.IN);
				mPower.getOperations().add(operation);
			//operation : set(void, Power)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Power)instance).set((Power)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getPower(), 0, 1, UDirectionType.IN);
				mPower.getOperations().add(operation);
			//operation : smaller(boolean, Power)
			operation = UMetaBuilder.manual().createOperation("smaller", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Power)instance).smaller((Power)parameter[0]);
				}
			});
				//Annotations for Power:smaller(boolean, Power)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getPower(), 0, 1, UDirectionType.IN);
				mPower.getOperations().add(operation);
			//operation : greater(boolean, Power)
			operation = UMetaBuilder.manual().createOperation("greater", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Power)instance).greater((Power)parameter[0]);
				}
			});
				//Annotations for Power:greater(boolean, Power)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getPower(), 0, 1, UDirectionType.IN);
				mPower.getOperations().add(operation);
			//operation : equals(boolean, Power)
			operation = UMetaBuilder.manual().createOperation("equals", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Power)instance).equals((Power)parameter[0]);
				}
			});
				//Annotations for Power:equals(boolean, Power)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", UnitsPackage.theInstance.getPower(), 0, 1, UDirectionType.IN);
				mPower.getOperations().add(operation);
		}
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mLength.setSuperType(UnitsPackage.theInstance.getMeasure());
		mDistance.setSuperType(UnitsPackage.theInstance.getLength());
		mVolume.setSuperType(UnitsPackage.theInstance.getMeasure());
		mTemperature.setSuperType(UnitsPackage.theInstance.getMeasure());
		mPercentage.setSuperType(UnitsPackage.theInstance.getMeasure());
		mAngle.setSuperType(UnitsPackage.theInstance.getMeasure());
		mTime.setSuperType(UnitsPackage.theInstance.getMeasure());
		mSpeed.setSuperType(UnitsPackage.theInstance.getMeasure());
		mAngularSpeed.setSuperType(UnitsPackage.theInstance.getMeasure());
		mAcceleration.setSuperType(UnitsPackage.theInstance.getMeasure());
		mAngularAcceleration.setSuperType(UnitsPackage.theInstance.getMeasure());
		mMass.setSuperType(UnitsPackage.theInstance.getMeasure());
		mEuler.getInterfaces().add(UnitsPackage.theInstance.getOrientation());
		mEuler.getInterfaces().add(UnitsPackage.theInstance.getRotation());
		mQuaternion.getInterfaces().add(UnitsPackage.theInstance.getOrientation());
		mQuaternion.getInterfaces().add(UnitsPackage.theInstance.getRotation());
		mVelocity.setSuperType(UnitsPackage.theInstance.getDirectedMeasure());
		mAngularVelocity.setSuperType(UnitsPackage.theInstance.getDirectedMeasure());
		mPower.setSuperType(UnitsPackage.theInstance.getMeasure());
		mColor.setSuperType(UnitsPackage.theInstance.getRGBColor());
		
	}



	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getMeasure(){
		if (mMeasure == null){
			mMeasure = UCoreMetaRepository.getUClass(Measure.class);
		}
		return mMeasure;
	}
	/**
	* @generated
	*/
	public UClass getDirectedMeasure(){
		if (mDirectedMeasure == null){
			mDirectedMeasure = UCoreMetaRepository.getUClass(DirectedMeasure.class);
		}
		return mDirectedMeasure;
	}
	/**
	* @generated
	*/
	public UEnum getDistanceUnit(){
		if (mDistanceUnit == null){
			mDistanceUnit = UCoreMetaRepository.getUEnumeration(DistanceUnit.class);
		}
		return mDistanceUnit;
	}
	/**
	* @generated
	*/
	public UClass getLength(){
		if (mLength == null){
			mLength = UCoreMetaRepository.getUClass(Length.class);
		}
		return mLength;
	}
	/**
	* @generated
	*/
	public UClass getDistance(){
		if (mDistance == null){
			mDistance = UCoreMetaRepository.getUClass(Distance.class);
		}
		return mDistance;
	}



	/**
	* @generated
	*/
	public UEnum getVolumeUnit(){
		if (mVolumeUnit == null){
			mVolumeUnit = UCoreMetaRepository.getUEnumeration(VolumeUnit.class);
		}
		return mVolumeUnit;
	}
	/**
	* @generated
	*/
	public UClass getVolume(){
		if (mVolume == null){
			mVolume = UCoreMetaRepository.getUClass(Volume.class);
		}
		return mVolume;
	}
	/**
	* @generated
	*/
	public UEnum getAngleUnit(){
		if (mAngleUnit == null){
			mAngleUnit = UCoreMetaRepository.getUEnumeration(AngleUnit.class);
		}
		return mAngleUnit;
	}



	/**
	* @generated
	*/
	public UEnum getTemperatureUnit(){
		if (mTemperatureUnit == null){
			mTemperatureUnit = UCoreMetaRepository.getUEnumeration(TemperatureUnit.class);
		}
		return mTemperatureUnit;
	}
	/**
	* @generated
	*/
	public UClass getAngle(){
		if (mAngle == null){
			mAngle = UCoreMetaRepository.getUClass(Angle.class);
		}
		return mAngle;
	}



	/**
	* @generated
	*/
	public UClass getTemperature(){
		if (mTemperature == null){
			mTemperature = UCoreMetaRepository.getUClass(Temperature.class);
		}
		return mTemperature;
	}



	/**
	* @generated
	*/
	public UClass getPercentage(){
		if (mPercentage == null){
			mPercentage = UCoreMetaRepository.getUClass(Percentage.class);
		}
		return mPercentage;
	}
	/**
	* @generated
	*/
	public UEnum getTimeUnit(){
		if (mTimeUnit == null){
			mTimeUnit = UCoreMetaRepository.getUEnumeration(TimeUnit.class);
		}
		return mTimeUnit;
	}
	/**
	* @generated
	*/
	public UClass getTime(){
		if (mTime == null){
			mTime = UCoreMetaRepository.getUClass(Time.class);
		}
		return mTime;
	}
	/**
	* @generated
	*/
	public UClass getSpeedUnit(){
		if (mSpeedUnit == null){
			mSpeedUnit = UCoreMetaRepository.getUClass(SpeedUnit.class);
		}
		return mSpeedUnit;
	}
	/**
	* @generated
	*/
	public UClass getSpeed(){
		if (mSpeed == null){
			mSpeed = UCoreMetaRepository.getUClass(Speed.class);
		}
		return mSpeed;
	}
	/**
	* @generated
	*/
	public UClass getAngularSpeedUnit(){
		if (mAngularSpeedUnit == null){
			mAngularSpeedUnit = UCoreMetaRepository.getUClass(AngularSpeedUnit.class);
		}
		return mAngularSpeedUnit;
	}
	/**
	* @generated
	*/
	public UClass getAngularSpeed(){
		if (mAngularSpeed == null){
			mAngularSpeed = UCoreMetaRepository.getUClass(AngularSpeed.class);
		}
		return mAngularSpeed;
	}
	/**
	* @generated
	*/
	public UClass getAccelerationUnit(){
		if (mAccelerationUnit == null){
			mAccelerationUnit = UCoreMetaRepository.getUClass(AccelerationUnit.class);
		}
		return mAccelerationUnit;
	}
	/**
	* @generated
	*/
	public UClass getAcceleration(){
		if (mAcceleration == null){
			mAcceleration = UCoreMetaRepository.getUClass(Acceleration.class);
		}
		return mAcceleration;
	}
	/**
	* @generated
	*/
	public UClass getAngularAccelerationUnit(){
		if (mAngularAccelerationUnit == null){
			mAngularAccelerationUnit = UCoreMetaRepository.getUClass(AngularAccelerationUnit.class);
		}
		return mAngularAccelerationUnit;
	}
	/**
	* @generated
	*/
	public UClass getAngularAcceleration(){
		if (mAngularAcceleration == null){
			mAngularAcceleration = UCoreMetaRepository.getUClass(AngularAcceleration.class);
		}
		return mAngularAcceleration;
	}
	/**
	* @generated
	*/
	public UEnum getMassUnit(){
		if (mMassUnit == null){
			mMassUnit = UCoreMetaRepository.getUEnumeration(MassUnit.class);
		}
		return mMassUnit;
	}
	/**
	* @generated
	*/
	public UClass getMass(){
		if (mMass == null){
			mMass = UCoreMetaRepository.getUClass(Mass.class);
		}
		return mMass;
	}
	/**
	* @generated
	*/
	public UInterface getOrientation(){
		if (mOrientation == null){
			mOrientation = UCoreMetaRepository.getUInterface(Orientation.class);
		}
		return mOrientation;
	}
	/**
	* @generated
	*/
	public UInterface getRotation(){
		if (mRotation == null){
			mRotation = UCoreMetaRepository.getUInterface(Rotation.class);
		}
		return mRotation;
	}
	/**
	* @generated
	*/
	public UClass getEuler(){
		if (mEuler == null){
			mEuler = UCoreMetaRepository.getUClass(Euler.class);
		}
		return mEuler;
	}
	/**
	* @generated
	*/
	public UClass getQuaternion(){
		if (mQuaternion == null){
			mQuaternion = UCoreMetaRepository.getUClass(Quaternion.class);
		}
		return mQuaternion;
	}
	/**
	* @generated
	*/
	public UClass getVelocity(){
		if (mVelocity == null){
			mVelocity = UCoreMetaRepository.getUClass(Velocity.class);
		}
		return mVelocity;
	}
	/**
	* @generated
	*/
	public UClass getAngularVelocity(){
		if (mAngularVelocity == null){
			mAngularVelocity = UCoreMetaRepository.getUClass(AngularVelocity.class);
		}
		return mAngularVelocity;
	}



	/**
	* @generated
	*/
	public UEnum getPowerUnit(){
		if (mPowerUnit == null){
			mPowerUnit = UCoreMetaRepository.getUEnumeration(PowerUnit.class);
		}
		return mPowerUnit;
	}



	/**
	* @generated
	*/
	public UClass getPower(){
		if (mPower == null){
			mPower = UCoreMetaRepository.getUClass(Power.class);
		}
		return mPower;
	}
	/**
	* @generated
	*/
	public UClass getRGBColor(){
		if (mRGBColor == null){
			mRGBColor = UCoreMetaRepository.getUClass(RGBColor.class);
		}
		return mRGBColor;
	}
	/**
	* @generated
	*/
	public UEnum getPredefinedColour(){
		if (mPredefinedColour == null){
			mPredefinedColour = UCoreMetaRepository.getUEnumeration(PredefinedColour.class);
		}
		return mPredefinedColour;
	}
	/**
	* @generated
	*/
	public UClass getColor(){
		if (mColor == null){
			mColor = UCoreMetaRepository.getUClass(Color.class);
		}
		return mColor;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getMeasure_value(){
		if (mMeasure_value == null)
			mMeasure_value = getMeasure().getFeature("value");
		return mMeasure_value;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLength_unit(){
		if (mLength_unit == null)
			mLength_unit = getLength().getFeature("unit");
		return mLength_unit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVolume_unit(){
		if (mVolume_unit == null)
			mVolume_unit = getVolume().getFeature("unit");
		return mVolume_unit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAngle_unit(){
		if (mAngle_unit == null)
			mAngle_unit = getAngle().getFeature("unit");
		return mAngle_unit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTime_unit(){
		if (mTime_unit == null)
			mTime_unit = getTime().getFeature("unit");
		return mTime_unit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getSpeedUnit_distanceUnit(){
		if (mSpeedUnit_distanceUnit == null)
			mSpeedUnit_distanceUnit = getSpeedUnit().getFeature("distanceUnit");
		return mSpeedUnit_distanceUnit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getSpeedUnit_timeUnit(){
		if (mSpeedUnit_timeUnit == null)
			mSpeedUnit_timeUnit = getSpeedUnit().getFeature("timeUnit");
		return mSpeedUnit_timeUnit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getSpeed_unit(){
		if (mSpeed_unit == null)
			mSpeed_unit = getSpeed().getFeature("unit");
		return mSpeed_unit;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getTemperature_unit(){
		if (mTemperature_unit == null)
			mTemperature_unit = getTemperature().getFeature("unit");
		return mTemperature_unit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAngularSpeedUnit_angleUnit(){
		if (mAngularSpeedUnit_angleUnit == null)
			mAngularSpeedUnit_angleUnit = getAngularSpeedUnit().getFeature("angleUnit");
		return mAngularSpeedUnit_angleUnit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAngularSpeedUnit_timeUnit(){
		if (mAngularSpeedUnit_timeUnit == null)
			mAngularSpeedUnit_timeUnit = getAngularSpeedUnit().getFeature("timeUnit");
		return mAngularSpeedUnit_timeUnit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAngularSpeed_unit(){
		if (mAngularSpeed_unit == null)
			mAngularSpeed_unit = getAngularSpeed().getFeature("unit");
		return mAngularSpeed_unit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAccelerationUnit_timeUnit(){
		if (mAccelerationUnit_timeUnit == null)
			mAccelerationUnit_timeUnit = getAccelerationUnit().getFeature("timeUnit");
		return mAccelerationUnit_timeUnit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAccelerationUnit_speedUnit(){
		if (mAccelerationUnit_speedUnit == null)
			mAccelerationUnit_speedUnit = getAccelerationUnit().getFeature("speedUnit");
		return mAccelerationUnit_speedUnit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAcceleration_unit(){
		if (mAcceleration_unit == null)
			mAcceleration_unit = getAcceleration().getFeature("unit");
		return mAcceleration_unit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAngularAccelerationUnit_timeUnit(){
		if (mAngularAccelerationUnit_timeUnit == null)
			mAngularAccelerationUnit_timeUnit = getAngularAccelerationUnit().getFeature("timeUnit");
		return mAngularAccelerationUnit_timeUnit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAngularAccelerationUnit_speedUnit(){
		if (mAngularAccelerationUnit_speedUnit == null)
			mAngularAccelerationUnit_speedUnit = getAngularAccelerationUnit().getFeature("speedUnit");
		return mAngularAccelerationUnit_speedUnit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAngularAcceleration_unit(){
		if (mAngularAcceleration_unit == null)
			mAngularAcceleration_unit = getAngularAcceleration().getFeature("unit");
		return mAngularAcceleration_unit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMass_unit(){
		if (mMass_unit == null)
			mMass_unit = getMass().getFeature("unit");
		return mMass_unit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEuler_x(){
		if (mEuler_x == null)
			mEuler_x = getEuler().getFeature("x");
		return mEuler_x;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEuler_y(){
		if (mEuler_y == null)
			mEuler_y = getEuler().getFeature("y");
		return mEuler_y;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEuler_z(){
		if (mEuler_z == null)
			mEuler_z = getEuler().getFeature("z");
		return mEuler_z;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getQuaternion_x(){
		if (mQuaternion_x == null)
			mQuaternion_x = getQuaternion().getFeature("x");
		return mQuaternion_x;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getQuaternion_y(){
		if (mQuaternion_y == null)
			mQuaternion_y = getQuaternion().getFeature("y");
		return mQuaternion_y;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getQuaternion_z(){
		if (mQuaternion_z == null)
			mQuaternion_z = getQuaternion().getFeature("z");
		return mQuaternion_z;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getQuaternion_w(){
		if (mQuaternion_w == null)
			mQuaternion_w = getQuaternion().getFeature("w");
		return mQuaternion_w;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVelocity_value(){
		if (mVelocity_value == null)
			mVelocity_value = getVelocity().getFeature("value");
		return mVelocity_value;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVelocity_unit(){
		if (mVelocity_unit == null)
			mVelocity_unit = getVelocity().getFeature("unit");
		return mVelocity_unit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVelocity_crs(){
		if (mVelocity_crs == null)
			mVelocity_crs = getVelocity().getFeature("crs");
		return mVelocity_crs;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAngularVelocity_value(){
		if (mAngularVelocity_value == null)
			mAngularVelocity_value = getAngularVelocity().getFeature("value");
		return mAngularVelocity_value;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAngularVelocity_unit(){
		if (mAngularVelocity_unit == null)
			mAngularVelocity_unit = getAngularVelocity().getFeature("unit");
		return mAngularVelocity_unit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAngularVelocity_crs(){
		if (mAngularVelocity_crs == null)
			mAngularVelocity_crs = getAngularVelocity().getFeature("crs");
		return mAngularVelocity_crs;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getRGBColor_r(){
		if (mRGBColor_r == null)
			mRGBColor_r = getRGBColor().getFeature("r");
		return mRGBColor_r;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getRGBColor_g(){
		if (mRGBColor_g == null)
			mRGBColor_g = getRGBColor().getFeature("g");
		return mRGBColor_g;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getRGBColor_b(){
		if (mRGBColor_b == null)
			mRGBColor_b = getRGBColor().getFeature("b");
		return mRGBColor_b;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getColor_color(){
		if (mColor_color == null)
			mColor_color = getColor().getFeature("color");
		return mColor_color;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getPower_unit(){
		if (mPower_unit == null)
			mPower_unit = getPower().getFeature("unit");
		return mPower_unit;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getRGBColor_a(){
		if (mRGBColor_a == null)
			mRGBColor_a = getRGBColor().getFeature("a");
		return mRGBColor_a;
	}
}
