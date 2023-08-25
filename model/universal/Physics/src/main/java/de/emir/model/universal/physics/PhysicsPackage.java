package de.emir.model.universal.physics;

import de.emir.model.universal.CRSModel;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.model.universal.CoreModel;
import de.emir.model.universal.SpatialModel;
import de.emir.model.universal.UnitsModel;
import de.emir.model.universal.core.CorePackage;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.math.MathPackage;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.physics.impl.CapabilityImpl;
import de.emir.model.universal.physics.impl.CharacteristicImpl;
import de.emir.model.universal.physics.impl.DynamicObjectCharacteristicImpl;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.model.universal.physics.impl.EnvironmentFactorImpl;
import de.emir.model.universal.physics.impl.EnvironmentFactorLayerImpl;
import de.emir.model.universal.physics.impl.EnvironmentImpl;
import de.emir.model.universal.physics.impl.EnvironmentLayerImpl;
import de.emir.model.universal.physics.impl.ForceImpl;
import de.emir.model.universal.physics.impl.GeographicLayerImpl;
import de.emir.model.universal.physics.impl.LocatableObjectImpl;
import de.emir.model.universal.physics.impl.MultiViewObjectSurfaceInforamtionImpl;
import de.emir.model.universal.physics.impl.ObjectLayerImpl;
import de.emir.model.universal.physics.impl.ObjectSurfaceInformationImpl;
import de.emir.model.universal.physics.Capability;
import de.emir.model.universal.physics.impl.PhysicalObjectImpl;
import de.emir.model.universal.physics.impl.PoseChangeCapabilityImpl;
import de.emir.model.universal.physics.Characteristic;
import de.emir.model.universal.physics.DynamicObjectCharacteristic;
import de.emir.model.universal.physics.EnvironmentFactor;
import de.emir.model.universal.physics.ICapability;
import de.emir.model.universal.physics.ICharacteristic;
import de.emir.model.universal.physics.IExecutableCapability;
import de.emir.model.universal.physics.impl.RelativeEngineering2DImpl;
import de.emir.model.universal.physics.EnvironmentLayer;
import de.emir.model.universal.physics.Environment;
import de.emir.model.universal.physics.impl.TorqueImpl;
import de.emir.model.universal.physics.impl.WindImpl;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.physics.Force;
import de.emir.model.universal.physics.GeographicLayer;
import de.emir.model.universal.physics.ObjectLayer;
import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.physics.MultiViewObjectSurfaceInforamtion;
import de.emir.model.universal.physics.RelativeEngineering2D;
import de.emir.model.universal.physics.Wind;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.physics.EnvironmentFactorLayer;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.physics.ObjectSurfaceInformation;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.units.AngularVelocity;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.Velocity;
import de.emir.tuml.ucore.UCoreModel;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.model.universal.physics.PoseChangeCapability;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.model.universal.physics.Torque;
import de.emir.model.universal.spatial.Envelope;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.Orientation;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.Angle;
import de.emir.tuml.ucore.runtime.RuntimePackage;
import java.util.List;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;

/**
 *	@generated 
 */
public class PhysicsPackage  
{
	
	/**
	 * @generated
	 */
	public static PhysicsPackage theInstance = new PhysicsPackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier RelativeEngineering2D
		*/
		UClass RelativeEngineering2D = PhysicsPackage.theInstance.getRelativeEngineering2D();
		/**
		* @generated
		* @return meta type for classifier LocatableObject
		*/
		UClass LocatableObject = PhysicsPackage.theInstance.getLocatableObject();
		/**
		 * @generated
		 * @return meta type for interface ICapability
		 */
		UInterface ICapability = PhysicsPackage.theInstance.getICapability();
		/**
		 * @generated
		 * @return meta type for interface IExecutableCapability
		 */
		UInterface IExecutableCapability = PhysicsPackage.theInstance.getIExecutableCapability();
		/**
		* @generated
		* @return meta type for classifier Capability
		*/
		UClass Capability = PhysicsPackage.theInstance.getCapability();
		/**
		 * @generated
		 * @return meta type for interface ICharacteristic
		 */
		UInterface ICharacteristic = PhysicsPackage.theInstance.getICharacteristic();
		/**
		* @generated
		* @return meta type for classifier Characteristic
		*/
		UClass Characteristic = PhysicsPackage.theInstance.getCharacteristic();
		/**
		* @generated
		* @return meta type for classifier ObjectSurfaceInformation
		*/
		UClass ObjectSurfaceInformation = PhysicsPackage.theInstance.getObjectSurfaceInformation();
		/**
		* @generated
		* @return meta type for classifier MultiViewObjectSurfaceInforamtion
		*/
		UClass MultiViewObjectSurfaceInforamtion = PhysicsPackage.theInstance.getMultiViewObjectSurfaceInforamtion();
		/**
		* @generated
		* @return meta type for classifier DynamicObjectCharacteristic
		*/
		UClass DynamicObjectCharacteristic = PhysicsPackage.theInstance.getDynamicObjectCharacteristic();
		/**
		* @generated
		* @return meta type for classifier PoseChangeCapability
		*/
		UClass PoseChangeCapability = PhysicsPackage.theInstance.getPoseChangeCapability();
		/**
		* @generated
		* @return meta type for classifier PhysicalObject
		*/
		UClass PhysicalObject = PhysicsPackage.theInstance.getPhysicalObject();
		/**
		* @generated
		* @return meta type for classifier EnvironmentFactor
		*/
		UClass EnvironmentFactor = PhysicsPackage.theInstance.getEnvironmentFactor();
		/**
		* @generated
		* @return meta type for classifier Wind
		*/
		UClass Wind = PhysicsPackage.theInstance.getWind();
		/**
		* @generated
		* @return meta type for classifier EnvironmentLayer
		*/
		UClass EnvironmentLayer = PhysicsPackage.theInstance.getEnvironmentLayer();
		/**
		* @generated
		* @return meta type for classifier ObjectLayer
		*/
		UClass ObjectLayer = PhysicsPackage.theInstance.getObjectLayer();
		/**
		* @generated
		* @return meta type for classifier GeographicLayer
		*/
		UClass GeographicLayer = PhysicsPackage.theInstance.getGeographicLayer();
		/**
		* @generated
		* @return meta type for classifier EnvironmentFactorLayer
		*/
		UClass EnvironmentFactorLayer = PhysicsPackage.theInstance.getEnvironmentFactorLayer();
		/**
		* @generated
		* @return meta type for classifier Environment
		*/
		UClass Environment = PhysicsPackage.theInstance.getEnvironment();
		/**
		* @generated
		* @return meta type for classifier Force
		*/
		UClass Force = PhysicsPackage.theInstance.getForce();
		/**
		* @generated
		* @return meta type for classifier Torque
		*/
		UClass Torque = PhysicsPackage.theInstance.getTorque();
		
		/**
		 * @generated
		 * @return feature descriptor reference in type RelativeEngineering2D
		 */
		 UStructuralFeature RelativeEngineering2D_reference = PhysicsPackage.theInstance.getRelativeEngineering2D_reference();
		/**
		 * @generated
		 * @return feature descriptor ownedCoordinateSystem in type LocatableObject
		 */
		 UStructuralFeature LocatableObject_ownedCoordinateSystem = PhysicsPackage.theInstance.getLocatableObject_ownedCoordinateSystem();
		/**
		 * @generated
		 * @return feature descriptor pose in type LocatableObject
		 */
		 UStructuralFeature LocatableObject_pose = PhysicsPackage.theInstance.getLocatableObject_pose();
		/**
		 * @generated
		 * @return feature descriptor geometry in type ObjectSurfaceInformation
		 */
		 UStructuralFeature ObjectSurfaceInformation_geometry = PhysicsPackage.theInstance.getObjectSurfaceInformation_geometry();
		/**
		 * @generated
		 * @return feature descriptor sideGeometry in type MultiViewObjectSurfaceInforamtion
		 */
		 UStructuralFeature MultiViewObjectSurfaceInforamtion_sideGeometry = PhysicsPackage.theInstance.getMultiViewObjectSurfaceInforamtion_sideGeometry();
		/**
		 * @generated
		 * @return feature descriptor frontGeometry in type MultiViewObjectSurfaceInforamtion
		 */
		 UStructuralFeature MultiViewObjectSurfaceInforamtion_frontGeometry = PhysicsPackage.theInstance.getMultiViewObjectSurfaceInforamtion_frontGeometry();
		/**
		 * @generated
		 * @return feature descriptor angularVelocity in type DynamicObjectCharacteristic
		 */
		 UStructuralFeature DynamicObjectCharacteristic_angularVelocity = PhysicsPackage.theInstance.getDynamicObjectCharacteristic_angularVelocity();
		/**
		 * @generated
		 * @return feature descriptor linearVelocity in type DynamicObjectCharacteristic
		 */
		 UStructuralFeature DynamicObjectCharacteristic_linearVelocity = PhysicsPackage.theInstance.getDynamicObjectCharacteristic_linearVelocity();
		/**
		 * @generated
		 * @return feature descriptor characteristics in type PhysicalObject
		 */
		 UStructuralFeature PhysicalObject_characteristics = PhysicsPackage.theInstance.getPhysicalObject_characteristics();
		/**
		 * @generated
		 * @return feature descriptor capabilities in type PhysicalObject
		 */
		 UStructuralFeature PhysicalObject_capabilities = PhysicsPackage.theInstance.getPhysicalObject_capabilities();
		/**
		 * @generated
		 * @return feature descriptor children in type PhysicalObject
		 */
		 UStructuralFeature PhysicalObject_children = PhysicsPackage.theInstance.getPhysicalObject_children();
		/**
		 * @generated
		 * @return feature descriptor areaOfInfluence in type EnvironmentFactor
		 */
		 UStructuralFeature EnvironmentFactor_areaOfInfluence = PhysicsPackage.theInstance.getEnvironmentFactor_areaOfInfluence();
		/**
		 * @generated
		 * @return feature descriptor velocity in type Wind
		 */
		 UStructuralFeature Wind_velocity = PhysicsPackage.theInstance.getWind_velocity();
		/**
		 * @generated
		 * @return feature descriptor objects in type ObjectLayer
		 */
		 UStructuralFeature ObjectLayer_objects = PhysicsPackage.theInstance.getObjectLayer_objects();
		/**
		 * @generated
		 * @return feature descriptor spatials in type GeographicLayer
		 */
		 UStructuralFeature GeographicLayer_spatials = PhysicsPackage.theInstance.getGeographicLayer_spatials();
		/**
		 * @generated
		 * @return feature descriptor environmentFactors in type EnvironmentFactorLayer
		 */
		 UStructuralFeature EnvironmentFactorLayer_environmentFactors = PhysicsPackage.theInstance.getEnvironmentFactorLayer_environmentFactors();
		/**
		 * @generated
		 * @return feature descriptor crs in type Environment
		 */
		 UStructuralFeature Environment_crs = PhysicsPackage.theInstance.getEnvironment_crs();
		/**
		 * @generated
		 * @return feature descriptor layer in type Environment
		 */
		 UStructuralFeature Environment_layer = PhysicsPackage.theInstance.getEnvironment_layer();
		/**
		 * @generated
		 * @return feature descriptor children in type Environment
		 */
		 UStructuralFeature Environment_children = PhysicsPackage.theInstance.getEnvironment_children();
		/**
		 * @generated
		 * @return feature descriptor direction in type Force
		 */
		 UStructuralFeature Force_direction = PhysicsPackage.theInstance.getForce_direction();
		/**
		 * @generated
		 * @return feature descriptor origin in type Force
		 */
		 UStructuralFeature Force_origin = PhysicsPackage.theInstance.getForce_origin();
		/**
		 * @generated
		 * @return feature descriptor value in type Torque
		 */
		 UStructuralFeature Torque_value = PhysicsPackage.theInstance.getTorque_value();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mRelativeEngineering2D = null;
	/**
	* @generated
	*/
	private UClass mLocatableObject = null;
	/**
	 * @generated
	 */
	private UInterface mICapability = null;
	/**
	 * @generated
	 */
	private UInterface mIExecutableCapability = null;
	/**
	* @generated
	*/
	private UClass mCapability = null;
	/**
	 * @generated
	 */
	private UInterface mICharacteristic = null;
	/**
	* @generated
	*/
	private UClass mCharacteristic = null;
	/**
	* @generated
	*/
	private UClass mObjectSurfaceInformation = null;
	/**
	* @generated
	*/
	private UClass mMultiViewObjectSurfaceInforamtion = null;
	/**
	* @generated
	*/
	private UClass mDynamicObjectCharacteristic = null;
	/**
	* @generated
	*/
	private UClass mPoseChangeCapability = null;
	/**
	* @generated
	*/
	private UClass mPhysicalObject = null;
	/**
	* @generated
	*/
	private UClass mEnvironmentFactor = null;
	/**
	* @generated
	*/
	private UClass mWind = null;
	/**
	* @generated
	*/
	private UClass mEnvironmentLayer = null;
	/**
	* @generated
	*/
	private UClass mObjectLayer = null;
	/**
	* @generated
	*/
	private UClass mGeographicLayer = null;
	/**
	* @generated
	*/
	private UClass mEnvironmentFactorLayer = null;
	/**
	* @generated
	*/
	private UClass mEnvironment = null;
	/**
	* @generated
	*/
	private UClass mForce = null;
	/**
	* @generated
	*/
	private UClass mTorque = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	//Features for classifier RelativeEngineering2D
	/**
	 * @generated
	 */
	private UStructuralFeature mRelativeEngineering2D_reference = null;
	
	//Features for classifier LocatableObject
	/**
	 * @generated
	 */
	private UStructuralFeature mLocatableObject_ownedCoordinateSystem = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mLocatableObject_pose = null;
	
	
	
	//Features for classifier ObjectSurfaceInformation
	/**
	 * @generated
	 */
	private UStructuralFeature mObjectSurfaceInformation_geometry = null;
	
	//Features for classifier MultiViewObjectSurfaceInforamtion
	/**
	 * @generated
	 */
	private UStructuralFeature mMultiViewObjectSurfaceInforamtion_sideGeometry = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mMultiViewObjectSurfaceInforamtion_frontGeometry = null;
	
	//Features for classifier DynamicObjectCharacteristic
	/**
	 * @generated
	 */
	private UStructuralFeature mDynamicObjectCharacteristic_angularVelocity = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mDynamicObjectCharacteristic_linearVelocity = null;
	
	
	//Features for classifier PhysicalObject
	/**
	 * @generated
	 */
	private UStructuralFeature mPhysicalObject_characteristics = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mPhysicalObject_capabilities = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mPhysicalObject_children = null;
	
	//Features for classifier EnvironmentFactor
	/**
	 * @generated
	 */
	private UStructuralFeature mEnvironmentFactor_areaOfInfluence = null;
	
	//Features for classifier Wind
	/**
	 * @generated
	 */
	private UStructuralFeature mWind_velocity = null;
	
	
	//Features for classifier ObjectLayer
	/**
	 * @generated
	 */
	private UStructuralFeature mObjectLayer_objects = null;
	
	//Features for classifier GeographicLayer
	/**
	 * @generated
	 */
	private UStructuralFeature mGeographicLayer_spatials = null;
	
	//Features for classifier EnvironmentFactorLayer
	/**
	 * @generated
	 */
	private UStructuralFeature mEnvironmentFactorLayer_environmentFactors = null;
	
	//Features for classifier Environment
	/**
	 * @generated
	 */
	private UStructuralFeature mEnvironment_crs = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mEnvironment_layer = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mEnvironment_children = null;
	
	//Features for classifier Force
	/**
	 * @generated
	 */
	private UStructuralFeature mForce_direction = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mForce_origin = null;
	
	//Features for classifier Torque
	/**
	 * @generated
	 */
	private UStructuralFeature mTorque_value = null;
	
	/**
	 * @generated not
	 */
	private static void initAnnotations(){
		ULog.debug("generate Annotations");
		theInstance.getCharacteristic().createAnnotationDetail("GMF", "alias", "PhysicalObjectCharacteristic");
		theInstance.getPhysicalObject_characteristics().createAnnotationDetail("GMF", "alias", "objectCharacteristics");
	}
	
	
	
	/**
	 * @generated
	 */
	public static PhysicsPackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package PhysicsPackage ...", 1);
		theInstance = new PhysicsPackage();
		//initialize referenced models
		UnitsModel.init();
		SpatialModel.init();
		CoreModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.universal.physics");
		p.getContent().add(theInstance.mRelativeEngineering2D);
		p.getContent().add(theInstance.mLocatableObject);
		p.getContent().add(theInstance.mICapability);
		p.getContent().add(theInstance.mIExecutableCapability);
		p.getContent().add(theInstance.mCapability);
		p.getContent().add(theInstance.mICharacteristic);
		p.getContent().add(theInstance.mCharacteristic);
		p.getContent().add(theInstance.mObjectSurfaceInformation);
		p.getContent().add(theInstance.mMultiViewObjectSurfaceInforamtion);
		p.getContent().add(theInstance.mDynamicObjectCharacteristic);
		p.getContent().add(theInstance.mPoseChangeCapability);
		p.getContent().add(theInstance.mPhysicalObject);
		p.getContent().add(theInstance.mEnvironmentFactor);
		p.getContent().add(theInstance.mWind);
		p.getContent().add(theInstance.mEnvironmentLayer);
		p.getContent().add(theInstance.mObjectLayer);
		p.getContent().add(theInstance.mGeographicLayer);
		p.getContent().add(theInstance.mEnvironmentFactorLayer);
		p.getContent().add(theInstance.mEnvironment);
		p.getContent().add(theInstance.mForce);
		p.getContent().add(theInstance.mTorque);
		p.freeze();
		
		
		
		ULog.debug(-1, "... package PhysicsPackage initialized");
		
		return theInstance;
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mRelativeEngineering2D = UMetaBuilder.manual().createClass("RelativeEngineering2D", false, RelativeEngineering2D.class, RelativeEngineering2DImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mRelativeEngineering2D, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new RelativeEngineering2DImpl();
				}
			});
			mRelativeEngineering2D.setDocumentation(" Convinience CRS that follows the referenced LocatableObject and thus creates\r\n * a stable coordinate reference system on top of the referenced object. \r\n ");
			//Annotations for RelativeEngineering2D
			mRelativeEngineering2D.createAnnotation("FeatureType");
		
		mLocatableObject = UMetaBuilder.manual().createClass("LocatableObject", true, LocatableObject.class, LocatableObjectImpl.class);
			mLocatableObject.setDocumentation(" Each object that is located somehow shall extend this object\r\n * this has been separated from the PhysicalObject, since also non physical Objects \r\n * like EnvironmentFactors or Information could be located somehow and may be handled the same\r\n * way (regarding their location operations) as a physical object. \r\n ");
		
		mICapability = UMetaBuilder.manual().createInterface("ICapability", ICapability.class);
			mICapability.setDocumentation("\r\n* The ability to achieve a Desired Effect under specified (performance)\r\n*standards and conditions through combinations of ways and means (activities and\r\n* resources) to perform a set of activities [DoDAF v2.02]\r\n");
		
		mIExecutableCapability = UMetaBuilder.manual().createInterface("IExecutableCapability", IExecutableCapability.class);
		
		mCapability = UMetaBuilder.manual().createClass("Capability", true, Capability.class, CapabilityImpl.class);
			mCapability.setDocumentation("\r\n * Abstract implementation of an ICapability used for backward compatibility\r\n ");
		
		mICharacteristic = UMetaBuilder.manual().createInterface("ICharacteristic", ICharacteristic.class);
			mICharacteristic.setDocumentation(" An (I)Characteristic is used to specialize an PhysicalObject. \r\n * The interface is meant to be empty and has to be filled by implementing classes.\r\n * @note ICharacteristics are identified (within a PhysicalObject) through its type (UClassifier). However it\r\n * is possible to add more than one characteristic of the same type to an PhysicalObject\r\n ");
		
		mCharacteristic = UMetaBuilder.manual().createClass("Characteristic", true, Characteristic.class, CharacteristicImpl.class);
			mCharacteristic.setDocumentation("\r\n\t\t\t\t\t\t * Abstract implementation of an ICharacteristics, mainly used for backward compatibility ");
			//Annotations for Characteristic
			mCharacteristic.createAnnotationDetail("GMF", "alias", "PhysicalObjectCharacteristic");
		
		mObjectSurfaceInformation = UMetaBuilder.manual().createClass("ObjectSurfaceInformation", false, ObjectSurfaceInformation.class, ObjectSurfaceInformationImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mObjectSurfaceInformation, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ObjectSurfaceInformationImpl();
				}
			});
			//Annotations for ObjectSurfaceInformation
			mObjectSurfaceInformation.createAnnotation("struct");
		
		mMultiViewObjectSurfaceInforamtion = UMetaBuilder.manual().createClass("MultiViewObjectSurfaceInforamtion", false, MultiViewObjectSurfaceInforamtion.class, MultiViewObjectSurfaceInforamtionImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mMultiViewObjectSurfaceInforamtion, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new MultiViewObjectSurfaceInforamtionImpl();
				}
			});
			//Annotations for MultiViewObjectSurfaceInforamtion
			mMultiViewObjectSurfaceInforamtion.createAnnotation("struct");
		
		mDynamicObjectCharacteristic = UMetaBuilder.manual().createClass("DynamicObjectCharacteristic", false, DynamicObjectCharacteristic.class, DynamicObjectCharacteristicImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mDynamicObjectCharacteristic, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new DynamicObjectCharacteristicImpl();
				}
			});
			//Annotations for DynamicObjectCharacteristic
			mDynamicObjectCharacteristic.createAnnotation("struct");
		
		mPoseChangeCapability = UMetaBuilder.manual().createClass("PoseChangeCapability", false, PoseChangeCapability.class, PoseChangeCapabilityImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mPoseChangeCapability, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new PoseChangeCapabilityImpl();
				}
			});
			//Annotations for PoseChangeCapability
			mPoseChangeCapability.createAnnotation("ComplexAttributeType");
		
		mPhysicalObject = UMetaBuilder.manual().createClass("PhysicalObject", false, PhysicalObject.class, PhysicalObjectImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mPhysicalObject, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new PhysicalObjectImpl();
				}
			});
			//Annotations for PhysicalObject
			mPhysicalObject.createAnnotation("FeatureType");
		
		mEnvironmentFactor = UMetaBuilder.manual().createClass("EnvironmentFactor", true, EnvironmentFactor.class, EnvironmentFactorImpl.class);
		
		mWind = UMetaBuilder.manual().createClass("Wind", false, Wind.class, WindImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mWind, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new WindImpl();
				}
			});
		
		mEnvironmentLayer = UMetaBuilder.manual().createClass("EnvironmentLayer", true, EnvironmentLayer.class, EnvironmentLayerImpl.class);
		
		mObjectLayer = UMetaBuilder.manual().createClass("ObjectLayer", false, ObjectLayer.class, ObjectLayerImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mObjectLayer, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ObjectLayerImpl();
				}
			});
		
		mGeographicLayer = UMetaBuilder.manual().createClass("GeographicLayer", false, GeographicLayer.class, GeographicLayerImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mGeographicLayer, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new GeographicLayerImpl();
				}
			});
		
		mEnvironmentFactorLayer = UMetaBuilder.manual().createClass("EnvironmentFactorLayer", false, EnvironmentFactorLayer.class, EnvironmentFactorLayerImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mEnvironmentFactorLayer, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new EnvironmentFactorLayerImpl();
				}
			});
		
		mEnvironment = UMetaBuilder.manual().createClass("Environment", false, Environment.class, EnvironmentImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mEnvironment, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new EnvironmentImpl();
				}
			});
		
		mForce = UMetaBuilder.manual().createClass("Force", false, Force.class, ForceImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mForce, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ForceImpl();
				}
			});
			//Annotations for Force
			mForce.createAnnotation("struct");
		
		mTorque = UMetaBuilder.manual().createClass("Torque", false, Torque.class, TorqueImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mTorque, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new TorqueImpl();
				}
			});
			//Annotations for Torque
			mTorque.createAnnotation("struct");
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
		{		//Operations of IExecutableCapability
			UOperation operation = null;
			//operation : execute(void)
			operation = UMetaBuilder.manual().createOperation("execute", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((IExecutableCapability)instance).execute();
					return null;
				}
			});
				mIExecutableCapability.getOperations().add(operation);
		}
		{		//Operations of ObjectSurfaceInformation
			UOperation operation = null;
			//operation : getWidth(Length)
			operation = UMetaBuilder.manual().createOperation("getWidth", false, UnitsPackage.theInstance.getLength(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((ObjectSurfaceInformation)instance).getWidth();
				}
			});
				operation.setDocumentation(" Returns the with of the Object. \r\n\t\t\t\t\t\t\t * The Width may be calculated from the defined geometry");
				//Annotations for ObjectSurfaceInformation:getWidth(Length)
				operation.createAnnotation("const");
				mObjectSurfaceInformation.getOperations().add(operation);
			//operation : getLength(Length)
			operation = UMetaBuilder.manual().createOperation("getLength", false, UnitsPackage.theInstance.getLength(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((ObjectSurfaceInformation)instance).getLength();
				}
			});
				operation.setDocumentation(" returns the length of the object\r\n * The length of the object may be calculated from the geometry\r\n ");
				//Annotations for ObjectSurfaceInformation:getLength(Length)
				operation.createAnnotation("const");
				mObjectSurfaceInformation.getOperations().add(operation);
			//operation : getHeight(Length)
			operation = UMetaBuilder.manual().createOperation("getHeight", false, UnitsPackage.theInstance.getLength(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((ObjectSurfaceInformation)instance).getHeight();
				}
			});
				operation.setDocumentation(" returns the height of the object (bounds)\r\n * The height of the object may be calculated from the geometry, if the geometry is defined in 3 dimensions, \r\n * otherwise null is returned\r\n ");
				//Annotations for ObjectSurfaceInformation:getHeight(Length)
				operation.createAnnotation("const");
				mObjectSurfaceInformation.getOperations().add(operation);
			//operation : getBoundingBox2D(Envelope)
			operation = UMetaBuilder.manual().createOperation("getBoundingBox2D", false, SpatialPackage.theInstance.getEnvelope(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((ObjectSurfaceInformation)instance).getBoundingBox2D();
				}
			});
				operation.setDocumentation(" returns the 2D boundingbox of this object");
				mObjectSurfaceInformation.getOperations().add(operation);
			//operation : getBoundingBox3D(Envelope)
			operation = UMetaBuilder.manual().createOperation("getBoundingBox3D", false, SpatialPackage.theInstance.getEnvelope(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((ObjectSurfaceInformation)instance).getBoundingBox3D();
				}
			});
				operation.setDocumentation(" returns the 3D boundingbox of this object, if a third dimension could be detected, otherwise this is equals with getBoundingBox2D() ");
				mObjectSurfaceInformation.getOperations().add(operation);
		}
		{		//Operations of MultiViewObjectSurfaceInforamtion
			UOperation operation = null;
			//operation : getTopGeometry(Geometry)
			operation = UMetaBuilder.manual().createOperation("getTopGeometry", false, SpatialPackage.theInstance.getGeometry(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((MultiViewObjectSurfaceInforamtion)instance).getTopGeometry();
				}
			});
				operation.setDocumentation(" returns the ObjectSurfaceInformation.geometry that is regarded as the geometry as viewed from top ");
				//Annotations for MultiViewObjectSurfaceInforamtion:getTopGeometry(Geometry)
				operation.createAnnotation("const");
				mMultiViewObjectSurfaceInforamtion.getOperations().add(operation);
		}
		{		//Operations of DynamicObjectCharacteristic
			UOperation operation = null;
			//operation : set(void, Velocity, AngularVelocity)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((DynamicObjectCharacteristic)instance).set((Velocity)parameter[0], (AngularVelocity)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "linVel", UnitsPackage.theInstance.getVelocity(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "angVal", UnitsPackage.theInstance.getAngularVelocity(), 0, 1, UDirectionType.IN);
				mDynamicObjectCharacteristic.getOperations().add(operation);
			//operation : calculate(void, Coordinate, Coordinate, Angle, Angle, Time)
			operation = UMetaBuilder.manual().createOperation("calculate", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((DynamicObjectCharacteristic)instance).calculate((Coordinate)parameter[0], (Coordinate)parameter[1], (Angle)parameter[2], (Angle)parameter[3], (Time)parameter[4]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "loc0", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "loc1", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "heading0", UnitsPackage.theInstance.getAngle(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "heading1", UnitsPackage.theInstance.getAngle(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "elapsed", UnitsPackage.theInstance.getTime(), 0, 1, UDirectionType.IN);
				mDynamicObjectCharacteristic.getOperations().add(operation);
		}
		{		//Operations of PoseChangeCapability
			UOperation operation = null;
			//operation : setLocation(boolean, Coordinate)
			operation = UMetaBuilder.manual().createOperation("setLocation", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((PoseChangeCapability)instance).setLocation((Coordinate)parameter[0]);
				}
			});
				UMetaBuilder.manual().addParameter(operation, "coordinate", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mPoseChangeCapability.getOperations().add(operation);
			//operation : setOrientation(boolean, Orientation)
			operation = UMetaBuilder.manual().createOperation("setOrientation", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((PoseChangeCapability)instance).setOrientation((Orientation)parameter[0]);
				}
			});
				UMetaBuilder.manual().addParameter(operation, "rotation", UnitsPackage.theInstance.getOrientation(), 0, 1, UDirectionType.IN);
				mPoseChangeCapability.getOperations().add(operation);
		}
		{		//Operations of PhysicalObject
			UOperation operation = null;
			//operation : getFirstCharacteristic(ICharacteristic, UClassifier, boolean)
			operation = UMetaBuilder.manual().createOperation("getFirstCharacteristic", false, PhysicsPackage.theInstance.getICharacteristic(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((PhysicalObject)instance).getFirstCharacteristic((UClassifier)parameter[0], (boolean)parameter[1]);
				}
			});
				UMetaBuilder.manual().addParameter(operation, "type", RuntimePackage.theInstance.getUClassifier(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "includeSubTypes", TypeUtils.getPrimitiveType(boolean.class), 0, 1, UDirectionType.IN);
				mPhysicalObject.getOperations().add(operation);
			//operation : getAllCharacteristics(ICharacteristic, UClassifier, boolean)
			operation = UMetaBuilder.manual().createOperation("getAllCharacteristics", false, PhysicsPackage.theInstance.getICharacteristic(), 0, -1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((PhysicalObject)instance).getAllCharacteristics((UClassifier)parameter[0], (boolean)parameter[1]);
				}
			});
				UMetaBuilder.manual().addParameter(operation, "type", RuntimePackage.theInstance.getUClassifier(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "includeSubTypes", TypeUtils.getPrimitiveType(boolean.class), 0, 1, UDirectionType.IN);
				mPhysicalObject.getOperations().add(operation);
			//operation : getFirstCapability(ICapability, UClassifier, boolean)
			operation = UMetaBuilder.manual().createOperation("getFirstCapability", false, PhysicsPackage.theInstance.getICapability(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((PhysicalObject)instance).getFirstCapability((UClassifier)parameter[0], (boolean)parameter[1]);
				}
			});
				UMetaBuilder.manual().addParameter(operation, "type", RuntimePackage.theInstance.getUClassifier(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "includeSubTypes", TypeUtils.getPrimitiveType(boolean.class), 0, 1, UDirectionType.IN);
				mPhysicalObject.getOperations().add(operation);
			//operation : getAllCapabilities(ICapability, UClassifier, boolean)
			operation = UMetaBuilder.manual().createOperation("getAllCapabilities", false, PhysicsPackage.theInstance.getICapability(), 0, -1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((PhysicalObject)instance).getAllCapabilities((UClassifier)parameter[0], (boolean)parameter[1]);
				}
			});
				UMetaBuilder.manual().addParameter(operation, "type", RuntimePackage.theInstance.getUClassifier(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "includeSubTypes", TypeUtils.getPrimitiveType(boolean.class), 0, 1, UDirectionType.IN);
				mPhysicalObject.getOperations().add(operation);
		}
		{		//Operations of EnvironmentLayer
			UOperation operation = null;
			//operation : getEnvironmentCRS(CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("getEnvironmentCRS", false, CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((EnvironmentLayer)instance).getEnvironmentCRS();
				}
			});
				//Annotations for EnvironmentLayer:getEnvironmentCRS(CoordinateReferenceSystem)
				operation.createAnnotation("const");
				mEnvironmentLayer.getOperations().add(operation);
			//operation : getEnvironment(Environment)
			operation = UMetaBuilder.manual().createOperation("getEnvironment", false, PhysicsPackage.theInstance.getEnvironment(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((EnvironmentLayer)instance).getEnvironment();
				}
			});
				//Annotations for EnvironmentLayer:getEnvironment(Environment)
				operation.createAnnotation("const");
				mEnvironmentLayer.getOperations().add(operation);
		}
		{		//Operations of Force
			UOperation operation = null;
			//operation : getMagnitude(double)
			operation = UMetaBuilder.manual().createOperation("getMagnitude", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Force)instance).getMagnitude();
				}
			});
				//Annotations for Force:getMagnitude(double)
				operation.createAnnotation("const");
				mForce.getOperations().add(operation);
			//operation : getTorque(Torque, Coordinate)
			operation = UMetaBuilder.manual().createOperation("getTorque", false, PhysicsPackage.theInstance.getTorque(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Force)instance).getTorque((Coordinate)parameter[0]);
				}
			});
				//Annotations for Force:getTorque(Torque, Coordinate)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "atPosition", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mForce.getOperations().add(operation);
			//operation : getForceAtPosition(Force, Coordinate)
			operation = UMetaBuilder.manual().createOperation("getForceAtPosition", false, PhysicsPackage.theInstance.getForce(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Force)instance).getForceAtPosition((Coordinate)parameter[0]);
				}
			});
				//Annotations for Force:getForceAtPosition(Force, Coordinate)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "atPosition", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mForce.getOperations().add(operation);
		}
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of RelativeEngineering2D
			mRelativeEngineering2D_reference = UMetaBuilder.manual().createFeature("reference", PhysicsPackage.theInstance.getLocatableObject(), UAssociationType.AGGREGATION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRelativeEngineering2D_reference, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((RelativeEngineering2D)instance).getReference(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((RelativeEngineering2D)instance).setReference((LocatableObject)value); } }
				);
				mRelativeEngineering2D_reference.setDocumentation(" locatable object, used to resolve the origin and orientation offset for this local coordinate reference system ");
			
			//Features of LocatableObject
			mLocatableObject_ownedCoordinateSystem = UMetaBuilder.manual().createFeature("ownedCoordinateSystem", PhysicsPackage.theInstance.getRelativeEngineering2D(), UAssociationType.COMPOSITION, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLocatableObject_ownedCoordinateSystem, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((LocatableObject)instance).getOwnedCoordinateSystem(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((LocatableObject)instance).setOwnedCoordinateSystem((RelativeEngineering2D)value); } }
				);
				mLocatableObject_ownedCoordinateSystem.setDocumentation(" Each locatable objects builds its own coordinate system, whereas the objects center is also \r\n * the center of the reference system.\r\n *");
			mLocatableObject_pose = UMetaBuilder.manual().createFeature("pose", SpatialPackage.theInstance.getPose(), UAssociationType.COMPOSITION, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLocatableObject_pose, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((LocatableObject)instance).getPose(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((LocatableObject)instance).setPose((Pose)value); } }
				);
				mLocatableObject_pose.setDocumentation("\r\n * the pose is set to readonly to indicate, that an object that has an location \r\n * not necessarily can move, e.g. change its pose. For example a building does not move but is locatable\r\n ");
			
			//Features of ObjectSurfaceInformation
			mObjectSurfaceInformation_geometry = UMetaBuilder.manual().createFeature("geometry", SpatialPackage.theInstance.getGeometry(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mObjectSurfaceInformation_geometry, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ObjectSurfaceInformation)instance).getGeometry(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ObjectSurfaceInformation)instance).setGeometry((Geometry)value); } }
				);
			
			//Features of MultiViewObjectSurfaceInforamtion
			mMultiViewObjectSurfaceInforamtion_sideGeometry = UMetaBuilder.manual().createFeature("sideGeometry", SpatialPackage.theInstance.getGeometry(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMultiViewObjectSurfaceInforamtion_sideGeometry, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((MultiViewObjectSurfaceInforamtion)instance).getSideGeometry(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((MultiViewObjectSurfaceInforamtion)instance).setSideGeometry((Geometry)value); } }
				);
				mMultiViewObjectSurfaceInforamtion_sideGeometry.setDocumentation(" \r\n * Geometry as seen from a 90 degree angle along the main axis of the vessel\r\n * \r\n ");
			mMultiViewObjectSurfaceInforamtion_frontGeometry = UMetaBuilder.manual().createFeature("frontGeometry", SpatialPackage.theInstance.getGeometry(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mMultiViewObjectSurfaceInforamtion_frontGeometry, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((MultiViewObjectSurfaceInforamtion)instance).getFrontGeometry(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((MultiViewObjectSurfaceInforamtion)instance).setFrontGeometry((Geometry)value); } }
				);
				mMultiViewObjectSurfaceInforamtion_frontGeometry.setDocumentation("\r\n * Geometry as seen if the vessel moves directly towards the own position\r\n ");
			
			//Features of DynamicObjectCharacteristic
			mDynamicObjectCharacteristic_angularVelocity = UMetaBuilder.manual().createFeature("angularVelocity", UnitsPackage.theInstance.getAngularVelocity(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mDynamicObjectCharacteristic_angularVelocity, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((DynamicObjectCharacteristic)instance).getAngularVelocity(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((DynamicObjectCharacteristic)instance).setAngularVelocity((AngularVelocity)value); } }
				);
			mDynamicObjectCharacteristic_linearVelocity = UMetaBuilder.manual().createFeature("linearVelocity", UnitsPackage.theInstance.getVelocity(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mDynamicObjectCharacteristic_linearVelocity, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((DynamicObjectCharacteristic)instance).getLinearVelocity(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((DynamicObjectCharacteristic)instance).setLinearVelocity((Velocity)value); } }
				);
			
			//Features of PhysicalObject
			mPhysicalObject_characteristics = UMetaBuilder.manual().createFeature("characteristics", PhysicsPackage.theInstance.getICharacteristic(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mPhysicalObject_characteristics, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((PhysicalObject)instance).getCharacteristics(); } }, 
						null
				);
				//Annotations for PhysicalObject:characteristics
				mPhysicalObject_characteristics.createAnnotationDetail("GMF", "alias", "objectCharacteristics");
			mPhysicalObject_capabilities = UMetaBuilder.manual().createFeature("capabilities", PhysicsPackage.theInstance.getICapability(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mPhysicalObject_capabilities, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((PhysicalObject)instance).getCapabilities(); } }, 
						null
				);
			mPhysicalObject_children = UMetaBuilder.manual().createFeature("children", PhysicsPackage.theInstance.getPhysicalObject(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mPhysicalObject_children, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((PhysicalObject)instance).getChildren(); } }, 
						null
				);
			
			//Features of EnvironmentFactor
			mEnvironmentFactor_areaOfInfluence = UMetaBuilder.manual().createFeature("areaOfInfluence", SpatialPackage.theInstance.getGeometry(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEnvironmentFactor_areaOfInfluence, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((EnvironmentFactor)instance).getAreaOfInfluence(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((EnvironmentFactor)instance).setAreaOfInfluence((Geometry)value); } }
				);
			
			//Features of Wind
			mWind_velocity = UMetaBuilder.manual().createFeature("velocity", UnitsPackage.theInstance.getVelocity(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mWind_velocity, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Wind)instance).getVelocity(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Wind)instance).setVelocity((Velocity)value); } }
				);
			
			//Features of ObjectLayer
			mObjectLayer_objects = UMetaBuilder.manual().createFeature("objects", PhysicsPackage.theInstance.getLocatableObject(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mObjectLayer_objects, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ObjectLayer)instance).getObjects(); } }, 
						null
				);
			
			//Features of GeographicLayer
			mGeographicLayer_spatials = UMetaBuilder.manual().createFeature("spatials", SpatialPackage.theInstance.getSpatialLayer(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mGeographicLayer_spatials, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((GeographicLayer)instance).getSpatials(); } }, 
						null
				);
			
			//Features of EnvironmentFactorLayer
			mEnvironmentFactorLayer_environmentFactors = UMetaBuilder.manual().createFeature("environmentFactors", PhysicsPackage.theInstance.getEnvironmentFactor(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mEnvironmentFactorLayer_environmentFactors, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((EnvironmentFactorLayer)instance).getEnvironmentFactors(); } }, 
						null
				);
			
			//Features of Environment
			mEnvironment_crs = UMetaBuilder.manual().createFeature("crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), UAssociationType.COMPOSITION, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEnvironment_crs, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Environment)instance).getCrs(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Environment)instance).setCrs((CoordinateReferenceSystem)value); } }
				);
			mEnvironment_layer = UMetaBuilder.manual().createFeature("layer", PhysicsPackage.theInstance.getEnvironmentLayer(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mEnvironment_layer, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Environment)instance).getLayer(); } }, 
						null
				);
			mEnvironment_children = UMetaBuilder.manual().createFeature("children", PhysicsPackage.theInstance.getEnvironment(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mEnvironment_children, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Environment)instance).getChildren(); } }, 
						null
				);
			
			//Features of Force
			mForce_direction = UMetaBuilder.manual().createFeature("direction", MathPackage.theInstance.getVector(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mForce_direction, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Force)instance).getDirection(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Force)instance).setDirection((Vector)value); } }
				);
			mForce_origin = UMetaBuilder.manual().createFeature("origin", SpatialPackage.theInstance.getCoordinate(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mForce_origin, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Force)instance).getOrigin(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Force)instance).setOrigin((Coordinate)value); } }
				);
			
			//Features of Torque
			mTorque_value = UMetaBuilder.manual().createFeature("value", MathPackage.theInstance.getVector(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mTorque_value, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Torque)instance).getValue(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Torque)instance).setValue((Vector)value); } }
				);
			
		}
		{ //assign features
			mRelativeEngineering2D.getStructuralFeatures().add(mRelativeEngineering2D_reference);
			mLocatableObject.getStructuralFeatures().add(mLocatableObject_ownedCoordinateSystem);
			mLocatableObject.getStructuralFeatures().add(mLocatableObject_pose);
			mObjectSurfaceInformation.getStructuralFeatures().add(mObjectSurfaceInformation_geometry);
			mMultiViewObjectSurfaceInforamtion.getStructuralFeatures().add(mMultiViewObjectSurfaceInforamtion_sideGeometry);
			mMultiViewObjectSurfaceInforamtion.getStructuralFeatures().add(mMultiViewObjectSurfaceInforamtion_frontGeometry);
			mDynamicObjectCharacteristic.getStructuralFeatures().add(mDynamicObjectCharacteristic_angularVelocity);
			mDynamicObjectCharacteristic.getStructuralFeatures().add(mDynamicObjectCharacteristic_linearVelocity);
			mPhysicalObject.getStructuralFeatures().add(mPhysicalObject_characteristics);
			mPhysicalObject.getStructuralFeatures().add(mPhysicalObject_capabilities);
			mPhysicalObject.getStructuralFeatures().add(mPhysicalObject_children);
			mEnvironmentFactor.getStructuralFeatures().add(mEnvironmentFactor_areaOfInfluence);
			mWind.getStructuralFeatures().add(mWind_velocity);
			mObjectLayer.getStructuralFeatures().add(mObjectLayer_objects);
			mGeographicLayer.getStructuralFeatures().add(mGeographicLayer_spatials);
			mEnvironmentFactorLayer.getStructuralFeatures().add(mEnvironmentFactorLayer_environmentFactors);
			mEnvironment.getStructuralFeatures().add(mEnvironment_crs);
			mEnvironment.getStructuralFeatures().add(mEnvironment_layer);
			mEnvironment.getStructuralFeatures().add(mEnvironment_children);
			mForce.getStructuralFeatures().add(mForce_direction);
			mForce.getStructuralFeatures().add(mForce_origin);
			mTorque.getStructuralFeatures().add(mTorque_value);
		}
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mRelativeEngineering2D.setSuperType(CrsPackage.theInstance.getEngineering2D());
		mLocatableObject.setSuperType(CorePackage.theInstance.getIdentifiedObject());
		mCapability.getInterfaces().add(PhysicsPackage.theInstance.getICapability());
		mCharacteristic.getInterfaces().add(PhysicsPackage.theInstance.getICharacteristic());
		mObjectSurfaceInformation.setSuperType(PhysicsPackage.theInstance.getCharacteristic());
		mMultiViewObjectSurfaceInforamtion.setSuperType(PhysicsPackage.theInstance.getObjectSurfaceInformation());
		mDynamicObjectCharacteristic.setSuperType(PhysicsPackage.theInstance.getCharacteristic());
		mPoseChangeCapability.setSuperType(PhysicsPackage.theInstance.getCapability());
		mPhysicalObject.setSuperType(PhysicsPackage.theInstance.getLocatableObject());
		mEnvironmentFactor.setSuperType(PhysicsPackage.theInstance.getLocatableObject());
		mWind.setSuperType(PhysicsPackage.theInstance.getEnvironmentFactor());
		mEnvironmentLayer.setSuperType(CorePackage.theInstance.getIdentifiedObject());
		mObjectLayer.setSuperType(PhysicsPackage.theInstance.getEnvironmentLayer());
		mGeographicLayer.setSuperType(PhysicsPackage.theInstance.getEnvironmentLayer());
		mEnvironmentFactorLayer.setSuperType(PhysicsPackage.theInstance.getEnvironmentLayer());
		mEnvironment.setSuperType(CorePackage.theInstance.getIdentifiedObject());
		mForce.setSuperType(UnitsPackage.theInstance.getDirectedMeasure());
		mTorque.setSuperType(UnitsPackage.theInstance.getDirectedMeasure());
		
		mIExecutableCapability.getInterfaces().add(PhysicsPackage.theInstance.getICapability());
	}



	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getRelativeEngineering2D(){
		if (mRelativeEngineering2D == null){
			mRelativeEngineering2D = UCoreMetaRepository.getUClass(RelativeEngineering2D.class);
		}
		return mRelativeEngineering2D;
	}
	/**
	* @generated
	*/
	public UClass getLocatableObject(){
		if (mLocatableObject == null){
			mLocatableObject = UCoreMetaRepository.getUClass(LocatableObject.class);
		}
		return mLocatableObject;
	}



	/**
	* @generated
	*/
	public UInterface getIExecutableCapability(){
		if (mIExecutableCapability == null){
			mIExecutableCapability = UCoreMetaRepository.getUInterface(IExecutableCapability.class);
		}
		return mIExecutableCapability;
	}
	/**
	* @generated
	*/
	public UClass getCapability(){
		if (mCapability == null){
			mCapability = UCoreMetaRepository.getUClass(Capability.class);
		}
		return mCapability;
	}



	/**
	* @generated
	*/
	public UInterface getICapability(){
		if (mICapability == null){
			mICapability = UCoreMetaRepository.getUInterface(ICapability.class);
		}
		return mICapability;
	}



	/**
	* @generated
	*/
	public UInterface getICharacteristic(){
		if (mICharacteristic == null){
			mICharacteristic = UCoreMetaRepository.getUInterface(ICharacteristic.class);
		}
		return mICharacteristic;
	}
	/**
	* @generated
	*/
	public UClass getCharacteristic(){
		if (mCharacteristic == null){
			mCharacteristic = UCoreMetaRepository.getUClass(Characteristic.class);
		}
		return mCharacteristic;
	}
	/**
	* @generated
	*/
	public UClass getObjectSurfaceInformation(){
		if (mObjectSurfaceInformation == null){
			mObjectSurfaceInformation = UCoreMetaRepository.getUClass(ObjectSurfaceInformation.class);
		}
		return mObjectSurfaceInformation;
	}
	/**
	* @generated
	*/
	public UClass getMultiViewObjectSurfaceInforamtion(){
		if (mMultiViewObjectSurfaceInforamtion == null){
			mMultiViewObjectSurfaceInforamtion = UCoreMetaRepository.getUClass(MultiViewObjectSurfaceInforamtion.class);
		}
		return mMultiViewObjectSurfaceInforamtion;
	}
	/**
	* @generated
	*/
	public UClass getDynamicObjectCharacteristic(){
		if (mDynamicObjectCharacteristic == null){
			mDynamicObjectCharacteristic = UCoreMetaRepository.getUClass(DynamicObjectCharacteristic.class);
		}
		return mDynamicObjectCharacteristic;
	}
	/**
	* @generated
	*/
	public UClass getPoseChangeCapability(){
		if (mPoseChangeCapability == null){
			mPoseChangeCapability = UCoreMetaRepository.getUClass(PoseChangeCapability.class);
		}
		return mPoseChangeCapability;
	}
	/**
	* @generated
	*/
	public UClass getPhysicalObject(){
		if (mPhysicalObject == null){
			mPhysicalObject = UCoreMetaRepository.getUClass(PhysicalObject.class);
		}
		return mPhysicalObject;
	}
	/**
	* @generated
	*/
	public UClass getEnvironmentFactor(){
		if (mEnvironmentFactor == null){
			mEnvironmentFactor = UCoreMetaRepository.getUClass(EnvironmentFactor.class);
		}
		return mEnvironmentFactor;
	}
	/**
	* @generated
	*/
	public UClass getWind(){
		if (mWind == null){
			mWind = UCoreMetaRepository.getUClass(Wind.class);
		}
		return mWind;
	}
	/**
	* @generated
	*/
	public UClass getEnvironmentLayer(){
		if (mEnvironmentLayer == null){
			mEnvironmentLayer = UCoreMetaRepository.getUClass(EnvironmentLayer.class);
		}
		return mEnvironmentLayer;
	}
	/**
	* @generated
	*/
	public UClass getObjectLayer(){
		if (mObjectLayer == null){
			mObjectLayer = UCoreMetaRepository.getUClass(ObjectLayer.class);
		}
		return mObjectLayer;
	}
	/**
	* @generated
	*/
	public UClass getGeographicLayer(){
		if (mGeographicLayer == null){
			mGeographicLayer = UCoreMetaRepository.getUClass(GeographicLayer.class);
		}
		return mGeographicLayer;
	}
	/**
	* @generated
	*/
	public UClass getEnvironmentFactorLayer(){
		if (mEnvironmentFactorLayer == null){
			mEnvironmentFactorLayer = UCoreMetaRepository.getUClass(EnvironmentFactorLayer.class);
		}
		return mEnvironmentFactorLayer;
	}
	/**
	* @generated
	*/
	public UClass getEnvironment(){
		if (mEnvironment == null){
			mEnvironment = UCoreMetaRepository.getUClass(Environment.class);
		}
		return mEnvironment;
	}
	/**
	* @generated
	*/
	public UClass getForce(){
		if (mForce == null){
			mForce = UCoreMetaRepository.getUClass(Force.class);
		}
		return mForce;
	}
	/**
	* @generated
	*/
	public UClass getTorque(){
		if (mTorque == null){
			mTorque = UCoreMetaRepository.getUClass(Torque.class);
		}
		return mTorque;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getRelativeEngineering2D_reference(){
		if (mRelativeEngineering2D_reference == null)
			mRelativeEngineering2D_reference = getRelativeEngineering2D().getFeature("reference");
		return mRelativeEngineering2D_reference;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLocatableObject_ownedCoordinateSystem(){
		if (mLocatableObject_ownedCoordinateSystem == null)
			mLocatableObject_ownedCoordinateSystem = getLocatableObject().getFeature("ownedCoordinateSystem");
		return mLocatableObject_ownedCoordinateSystem;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLocatableObject_pose(){
		if (mLocatableObject_pose == null)
			mLocatableObject_pose = getLocatableObject().getFeature("pose");
		return mLocatableObject_pose;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getObjectSurfaceInformation_geometry(){
		if (mObjectSurfaceInformation_geometry == null)
			mObjectSurfaceInformation_geometry = getObjectSurfaceInformation().getFeature("geometry");
		return mObjectSurfaceInformation_geometry;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMultiViewObjectSurfaceInforamtion_sideGeometry(){
		if (mMultiViewObjectSurfaceInforamtion_sideGeometry == null)
			mMultiViewObjectSurfaceInforamtion_sideGeometry = getMultiViewObjectSurfaceInforamtion().getFeature("sideGeometry");
		return mMultiViewObjectSurfaceInforamtion_sideGeometry;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMultiViewObjectSurfaceInforamtion_frontGeometry(){
		if (mMultiViewObjectSurfaceInforamtion_frontGeometry == null)
			mMultiViewObjectSurfaceInforamtion_frontGeometry = getMultiViewObjectSurfaceInforamtion().getFeature("frontGeometry");
		return mMultiViewObjectSurfaceInforamtion_frontGeometry;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getDynamicObjectCharacteristic_angularVelocity(){
		if (mDynamicObjectCharacteristic_angularVelocity == null)
			mDynamicObjectCharacteristic_angularVelocity = getDynamicObjectCharacteristic().getFeature("angularVelocity");
		return mDynamicObjectCharacteristic_angularVelocity;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getDynamicObjectCharacteristic_linearVelocity(){
		if (mDynamicObjectCharacteristic_linearVelocity == null)
			mDynamicObjectCharacteristic_linearVelocity = getDynamicObjectCharacteristic().getFeature("linearVelocity");
		return mDynamicObjectCharacteristic_linearVelocity;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getPhysicalObject_characteristics(){
		if (mPhysicalObject_characteristics == null)
			mPhysicalObject_characteristics = getPhysicalObject().getFeature("characteristics");
		return mPhysicalObject_characteristics;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getPhysicalObject_capabilities(){
		if (mPhysicalObject_capabilities == null)
			mPhysicalObject_capabilities = getPhysicalObject().getFeature("capabilities");
		return mPhysicalObject_capabilities;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getPhysicalObject_children(){
		if (mPhysicalObject_children == null)
			mPhysicalObject_children = getPhysicalObject().getFeature("children");
		return mPhysicalObject_children;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEnvironmentFactor_areaOfInfluence(){
		if (mEnvironmentFactor_areaOfInfluence == null)
			mEnvironmentFactor_areaOfInfluence = getEnvironmentFactor().getFeature("areaOfInfluence");
		return mEnvironmentFactor_areaOfInfluence;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getWind_velocity(){
		if (mWind_velocity == null)
			mWind_velocity = getWind().getFeature("velocity");
		return mWind_velocity;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getObjectLayer_objects(){
		if (mObjectLayer_objects == null)
			mObjectLayer_objects = getObjectLayer().getFeature("objects");
		return mObjectLayer_objects;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getGeographicLayer_spatials(){
		if (mGeographicLayer_spatials == null)
			mGeographicLayer_spatials = getGeographicLayer().getFeature("spatials");
		return mGeographicLayer_spatials;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEnvironmentFactorLayer_environmentFactors(){
		if (mEnvironmentFactorLayer_environmentFactors == null)
			mEnvironmentFactorLayer_environmentFactors = getEnvironmentFactorLayer().getFeature("environmentFactors");
		return mEnvironmentFactorLayer_environmentFactors;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEnvironment_crs(){
		if (mEnvironment_crs == null)
			mEnvironment_crs = getEnvironment().getFeature("crs");
		return mEnvironment_crs;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEnvironment_layer(){
		if (mEnvironment_layer == null)
			mEnvironment_layer = getEnvironment().getFeature("layer");
		return mEnvironment_layer;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEnvironment_children(){
		if (mEnvironment_children == null)
			mEnvironment_children = getEnvironment().getFeature("children");
		return mEnvironment_children;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getForce_direction(){
		if (mForce_direction == null)
			mForce_direction = getForce().getFeature("direction");
		return mForce_direction;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getForce_origin(){
		if (mForce_origin == null)
			mForce_origin = getForce().getFeature("origin");
		return mForce_origin;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getTorque_value(){
		if (mTorque_value == null)
			mTorque_value = getTorque().getFeature("value");
		return mTorque_value;
	}
}
