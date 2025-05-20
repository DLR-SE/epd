package de.emir.model.domain.maritime.vessel;

import de.emir.model.application.VehicleModel;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.model.application.vehicle.VehiclePackage;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.model.domain.maritime.IEC61174Model;
import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.model.domain.maritime.vessel.impl.CapacityCharacteristicImpl;
import de.emir.model.domain.maritime.vessel.impl.CommandedEngineRpmImpl;
import de.emir.model.domain.maritime.vessel.impl.CommandedPropellerPitchImpl;
import de.emir.model.domain.maritime.vessel.impl.CommandedRudderAngleImpl;
import de.emir.model.domain.maritime.vessel.impl.CommandedValueImpl;
import de.emir.model.domain.maritime.vessel.impl.ControlSurfacesImpl;
import de.emir.model.domain.maritime.vessel.impl.DisplacementImpl;
import de.emir.model.domain.maritime.vessel.impl.DynamicSystemImpl;
import de.emir.model.domain.maritime.vessel.impl.EngineBuildInformationImpl;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.model.domain.maritime.vessel.impl.EngineImpl;
import de.emir.model.domain.maritime.vessel.impl.HeadingCommandImpl;
import de.emir.model.application.vehicle.Trajectory;
import de.emir.model.domain.maritime.vessel.impl.InternalCombustionEngineImpl;
import de.emir.model.application.vehicle.TrajectorySegment;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.Leg;
import de.emir.model.domain.maritime.vessel.impl.MachineCommandImpl;
import de.emir.model.domain.maritime.vessel.Autopilot;
import de.emir.model.domain.maritime.vessel.impl.NavigationInformationImpl;
import de.emir.model.domain.maritime.vessel.impl.PropellerImpl;
import de.emir.model.domain.maritime.vessel.CapacityCharacteristic;
import de.emir.model.domain.maritime.vessel.CommandedEngineRpm;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.vessel.CommandedPropellerPitch;
import de.emir.model.domain.maritime.vessel.AutopilotAlarm;
import de.emir.model.domain.maritime.vessel.DynamicSystem;
import de.emir.model.domain.maritime.vessel.impl.PropulsionSystemImpl;
import de.emir.model.domain.maritime.vessel.impl.RudderImpl;
import de.emir.model.domain.maritime.vessel.AutopilotConfiguration;
import de.emir.model.domain.maritime.vessel.CommandedRudderAngle;
import de.emir.model.domain.maritime.vessel.CommandedValue;
import de.emir.model.domain.maritime.vessel.impl.SpeedCommandImpl;
import de.emir.model.domain.maritime.vessel.impl.StandingCommandsImpl;
import de.emir.model.domain.maritime.vessel.Engine;
import de.emir.model.domain.maritime.vessel.impl.SteeringCommandImpl;
import de.emir.model.domain.maritime.vessel.NavigationStatus;
import de.emir.model.domain.maritime.vessel.NavigationInformation;
import de.emir.model.domain.maritime.vessel.InternalCombustionEngine;
import de.emir.model.domain.maritime.vessel.impl.VesselBuildInformationImpl;
import de.emir.model.domain.maritime.vessel.ControlSurfaces;
import de.emir.model.domain.maritime.vessel.VesselSafetyCharacteristic;
import de.emir.model.domain.maritime.vessel.Displacement;
import de.emir.model.domain.maritime.vessel.impl.VesselCharacteristicImpl;
import de.emir.model.domain.maritime.vessel.SpeedCommand;
import de.emir.model.domain.maritime.vessel.VesselBuildInformation;
import de.emir.model.domain.maritime.vessel.VesselDimensionCharacteristic;
import de.emir.model.domain.maritime.vessel.SteeringCommand;
import de.emir.model.domain.maritime.vessel.impl.VesselDimensionCharacteristicImpl;
import de.emir.model.domain.maritime.vessel.HeadingCommand;
import de.emir.model.domain.maritime.vessel.impl.VesselImpl;
import de.emir.model.domain.maritime.vessel.EngineBuildInformation;
import de.emir.model.domain.maritime.vessel.impl.VoyageCharacteristicImpl;
import de.emir.model.domain.maritime.vessel.MachineCommand;
import de.emir.model.domain.maritime.vessel.impl.WatercraftHullImpl;
import de.emir.model.domain.maritime.vessel.PropulsionSystem;
import de.emir.model.universal.CoreModel;
import de.emir.model.domain.maritime.vessel.Rudder;
import de.emir.model.universal.PhysicsModel;
import de.emir.model.domain.maritime.vessel.Propeller;
import de.emir.model.domain.maritime.vessel.VesselType;
import de.emir.model.domain.maritime.vessel.VoyageCharacteristic;
import de.emir.model.universal.UnitsModel;
import de.emir.model.domain.maritime.vessel.WatercraftHull;
import de.emir.model.domain.maritime.vessel.StandingCommands;
import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.core.IdentifiedObject;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.universal.core.RSIdentifier;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.model.universal.units.Acceleration;
import de.emir.model.universal.units.Angle;
import de.emir.model.domain.maritime.vessel.impl.VesselSafetyCharacteristicImpl;
import de.emir.model.domain.maritime.vessel.impl.AutopilotImpl;
import de.emir.model.domain.maritime.vessel.impl.AutopilotAlarmImpl;
import de.emir.model.domain.maritime.vessel.impl.AutopilotConfigurationImpl;
import de.emir.model.domain.maritime.vessel.VesselCharacteristic;
import de.emir.model.universal.units.AngularSpeed;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.model.universal.units.Velocity;
import de.emir.model.universal.units.Volume;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.model.universal.units.Mass;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.tuml.ucore.runtime.UAnnotation;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;
import de.emir.model.universal.core.ModelReference;
import de.emir.model.universal.units.Distance;

/**
 *	@generated 
 */
public class VesselPackage  
{
	/**
	 * @generated
	 */
	public static VesselPackage theInstance = new VesselPackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for enumeration VesselType
		*/
		UEnum VesselType = VesselPackage.theInstance.getVesselType();
		/**
		* @generated
		* @return meta type for enumeration NavigationStatus
		*/
		UEnum NavigationStatus = VesselPackage.theInstance.getNavigationStatus();
		/**
		* @generated
		* @return meta type for classifier NavigationInformation
		*/
		UClass NavigationInformation = VesselPackage.theInstance.getNavigationInformation();
		/**
		* @generated
		* @return meta type for classifier Vessel
		*/
		UClass Vessel = VesselPackage.theInstance.getVessel();
		/**
		* @generated
		* @return meta type for classifier VesselCharacteristic
		*/
		UClass VesselCharacteristic = VesselPackage.theInstance.getVesselCharacteristic();
		/**
		* @generated
		* @return meta type for classifier VesselBuildInformation
		*/
		UClass VesselBuildInformation = VesselPackage.theInstance.getVesselBuildInformation();
		/**
		* @generated
		* @return meta type for classifier Displacement
		*/
		UClass Displacement = VesselPackage.theInstance.getDisplacement();
		/**
		* @generated
		* @return meta type for classifier WatercraftHull
		*/
		UClass WatercraftHull = VesselPackage.theInstance.getWatercraftHull();
		/**
		* @generated
		* @return meta type for classifier VesselDimensionCharacteristic
		*/
		UClass VesselDimensionCharacteristic = VesselPackage.theInstance.getVesselDimensionCharacteristic();
        /**
		* @generated
		* @return meta type for classifier VesselSafetyCharacteristic
		*/
		UClass VesselSafetyCharacteristic = VesselPackage.theInstance.getVesselSafetyCharacteristic();
		/**
		* @generated
		* @return meta type for classifier EngineBuildInformation
		*/
		UClass EngineBuildInformation = VesselPackage.theInstance.getEngineBuildInformation();
		/**
		* @generated
		* @return meta type for classifier Engine
		*/
		UClass Engine = VesselPackage.theInstance.getEngine();
		/**
		* @generated
		* @return meta type for classifier InternalCombustionEngine
		*/
		UClass InternalCombustionEngine = VesselPackage.theInstance.getInternalCombustionEngine();
		/**
		* @generated
		* @return meta type for classifier Propeller
		*/
		UClass Propeller = VesselPackage.theInstance.getPropeller();
		/**
		* @generated
		* @return meta type for classifier Rudder
		*/
		UClass Rudder = VesselPackage.theInstance.getRudder();
		/**
		* @generated
		* @return meta type for classifier PropulsionSystem
		*/
		UClass PropulsionSystem = VesselPackage.theInstance.getPropulsionSystem();
		/**
		* @generated
		* @return meta type for classifier ControlSurfaces
		*/
		UClass ControlSurfaces = VesselPackage.theInstance.getControlSurfaces();
		/**
		* @generated
		* @return meta type for classifier DynamicSystem
		*/
		UClass DynamicSystem = VesselPackage.theInstance.getDynamicSystem();
		/**
		* @generated
		* @return meta type for classifier CapacityCharacteristic
		*/
		UClass CapacityCharacteristic = VesselPackage.theInstance.getCapacityCharacteristic();
		/**
		* @generated
		* @return meta type for classifier VoyageCharacteristic
		*/
		UClass VoyageCharacteristic = VesselPackage.theInstance.getVoyageCharacteristic();
		/**
		* @generated
		* @return meta type for classifier AutopilotConfiguration
		*/
		UClass AutopilotConfiguration = VesselPackage.theInstance.getAutopilotConfiguration();
		/**
		* @generated
		* @return meta type for classifier AutopilotAlarm
		*/
		UClass AutopilotAlarm = VesselPackage.theInstance.getAutopilotAlarm();
		/**
		* @generated
		* @return meta type for classifier StandingCommands
		*/
		UClass StandingCommands = VesselPackage.theInstance.getStandingCommands();
		/**
		* @generated
		* @return meta type for classifier Autopilot
		*/
		UClass Autopilot = VesselPackage.theInstance.getAutopilot();
		/**
		 * @generated
		 * @return feature descriptor status in type NavigationInformation
		 */
		 UStructuralFeature NavigationInformation_status = VesselPackage.theInstance.getNavigationInformation_status();
		/**
		* @generated
		* @return meta type for classifier SteeringCommand
		*/
		UClass SteeringCommand = VesselPackage.theInstance.getSteeringCommand();
		/**
		* @generated
		* @return meta type for classifier SpeedCommand
		*/
		UClass SpeedCommand = VesselPackage.theInstance.getSpeedCommand();
		/**
		* @generated
		* @return meta type for classifier HeadingCommand
		*/
		UClass HeadingCommand = VesselPackage.theInstance.getHeadingCommand();
		/**
		* @generated
		* @return meta type for classifier CommandedValue
		*/
		UClass CommandedValue = VesselPackage.theInstance.getCommandedValue();
		/**
		* @generated
		* @return meta type for classifier MachineCommand
		*/
		UClass MachineCommand = VesselPackage.theInstance.getMachineCommand();
		/**
		 * @generated
		 * @return feature descriptor mmsi in type Vessel
		 */
		 UStructuralFeature Vessel_mmsi = VesselPackage.theInstance.getVessel_mmsi();
		/**
		* @generated
		* @return meta type for classifier CommandedPropellerPitch
		*/
		UClass CommandedPropellerPitch = VesselPackage.theInstance.getCommandedPropellerPitch();
		/**
		 * @generated
		 * @return feature descriptor imo in type Vessel
		 */
		 UStructuralFeature Vessel_imo = VesselPackage.theInstance.getVessel_imo();
		/**
		* @generated
		* @return meta type for classifier CommandedRudderAngle
		*/
		UClass CommandedRudderAngle = VesselPackage.theInstance.getCommandedRudderAngle();
		/**
		* @generated
		* @return meta type for classifier CommandedEngineRpm
		*/
		UClass CommandedEngineRpm = VesselPackage.theInstance.getCommandedEngineRpm();
		/**
		 * @generated
		 * @return feature descriptor callSign in type Vessel
		 */
		 UStructuralFeature Vessel_callSign = VesselPackage.theInstance.getVessel_callSign();
		/**
		 * @generated
		 * @return feature descriptor type in type Vessel
		 */
		 UStructuralFeature Vessel_type = VesselPackage.theInstance.getVessel_type();
		/**
		 * @generated
		 * @return feature descriptor hullNumber in type VesselBuildInformation
		 */
		 UStructuralFeature VesselBuildInformation_hullNumber = VesselPackage.theInstance.getVesselBuildInformation_hullNumber();
		/**
		 * @generated
		 * @return feature descriptor manufactor in type VesselBuildInformation
		 */
		 UStructuralFeature VesselBuildInformation_manufactor = VesselPackage.theInstance.getVesselBuildInformation_manufactor();
		/**
		 * @generated
		 * @return feature descriptor buildTime in type VesselBuildInformation
		 */
		 UStructuralFeature VesselBuildInformation_buildTime = VesselPackage.theInstance.getVesselBuildInformation_buildTime();
		/**
		 * @generated
		 * @return feature descriptor light in type Displacement
		 */
		 UStructuralFeature Displacement_light = VesselPackage.theInstance.getDisplacement_light();
		/**
		 * @generated
		 * @return feature descriptor loaded in type Displacement
		 */
		 UStructuralFeature Displacement_loaded = VesselPackage.theInstance.getDisplacement_loaded();
		/**
		 * @generated
		 * @return feature descriptor deadweight in type Displacement
		 */
		 UStructuralFeature Displacement_deadweight = VesselPackage.theInstance.getDisplacement_deadweight();
		/**
		 * @generated
		 * @return feature descriptor cargoDeadweigthTons in type Displacement
		 */
		 UStructuralFeature Displacement_cargoDeadweigthTons = VesselPackage.theInstance.getDisplacement_cargoDeadweigthTons();
		/**
		 * @generated
		 * @return feature descriptor beam in type WatercraftHull
		 */
		 UStructuralFeature WatercraftHull_beam = VesselPackage.theInstance.getWatercraftHull_beam();
		/**
		 * @generated
		 * @return feature descriptor lengthAtWaterline in type WatercraftHull
		 */
		 UStructuralFeature WatercraftHull_lengthAtWaterline = VesselPackage.theInstance.getWatercraftHull_lengthAtWaterline();
		/**
		 * @generated
		 * @return feature descriptor overAllLength in type WatercraftHull
		 */
		 UStructuralFeature WatercraftHull_overAllLength = VesselPackage.theInstance.getWatercraftHull_overAllLength();
		/**
		 * @generated
		 * @return feature descriptor draft in type WatercraftHull
		 */
		 UStructuralFeature WatercraftHull_draft = VesselPackage.theInstance.getWatercraftHull_draft();
		/**
		 * @generated
		 * @return feature descriptor mouldedDepth in type WatercraftHull
		 */
		 UStructuralFeature WatercraftHull_mouldedDepth = VesselPackage.theInstance.getWatercraftHull_mouldedDepth();
		/**
		 * @generated
		 * @return feature descriptor freeboard in type WatercraftHull
		 */
		 UStructuralFeature WatercraftHull_freeboard = VesselPackage.theInstance.getWatercraftHull_freeboard();
		/**
		 * @generated
		 * @return feature descriptor neadTonnage in type VesselDimensionCharacteristic
		 */
		 UStructuralFeature VesselDimensionCharacteristic_neadTonnage = VesselPackage.theInstance.getVesselDimensionCharacteristic_neadTonnage();
		/**
		 * @generated
		 * @return feature descriptor blockCoefficient in type VesselDimensionCharacteristic
		 */
		 UStructuralFeature VesselDimensionCharacteristic_blockCoefficient = VesselPackage.theInstance.getVesselDimensionCharacteristic_blockCoefficient();
		/**
		 * @generated
		 * @return feature descriptor displacement in type VesselDimensionCharacteristic
		 */
		 UStructuralFeature VesselDimensionCharacteristic_displacement = VesselPackage.theInstance.getVesselDimensionCharacteristic_displacement();
		/**
		 * @generated
		 * @return feature descriptor hull in type VesselDimensionCharacteristic
		 */
		 UStructuralFeature VesselDimensionCharacteristic_hull = VesselPackage.theInstance.getVesselDimensionCharacteristic_hull();
        /**
		 * @generated
		 * @return feature descriptor underKeelClearance in type VesselSafetyCharacteristic
		 */
		 UStructuralFeature VesselSafetyCharacteristic_underKeelClearance = VesselPackage.theInstance.getVesselSafetyCharacteristic_underKeelClearance();
		/**
		 * @generated
		 * @return feature descriptor model in type EngineBuildInformation
		 */
		 UStructuralFeature EngineBuildInformation_model = VesselPackage.theInstance.getEngineBuildInformation_model();
        /**
		 * @generated
		 * @return feature descriptor personalSpace in type VesselSafetyCharacteristic
		 */
		 UStructuralFeature VesselSafetyCharacteristic_personalSpace = VesselPackage.theInstance.getVesselSafetyCharacteristic_personalSpace();
		/**
		 * @generated
		 * @return feature descriptor manufactor in type EngineBuildInformation
		 */
		 UStructuralFeature EngineBuildInformation_manufactor = VesselPackage.theInstance.getEngineBuildInformation_manufactor();
		/**
		 * @generated
		 * @return feature descriptor buildYear in type EngineBuildInformation
		 */
		 UStructuralFeature EngineBuildInformation_buildYear = VesselPackage.theInstance.getEngineBuildInformation_buildYear();
		/**
		 * @generated
		 * @return feature descriptor buildInformation in type Engine
		 */
		 UStructuralFeature Engine_buildInformation = VesselPackage.theInstance.getEngine_buildInformation();
		/**
		 * @generated
		 * @return feature descriptor acceleration in type Engine
		 */
		 UStructuralFeature Engine_acceleration = VesselPackage.theInstance.getEngine_acceleration();
		/**
		 * @generated
		 * @return feature descriptor rateOfTurn in type Rudder
		 */
		 UStructuralFeature Rudder_rateOfTurn = VesselPackage.theInstance.getRudder_rateOfTurn();
		/**
		 * @generated
		 * @return feature descriptor maximumVelocity in type PropulsionSystem
		 */
		 UStructuralFeature PropulsionSystem_maximumVelocity = VesselPackage.theInstance.getPropulsionSystem_maximumVelocity();
		/**
		 * @generated
		 * @return feature descriptor maximumRpm in type InternalCombustionEngine
		 */
		 UStructuralFeature InternalCombustionEngine_maximumRpm = VesselPackage.theInstance.getInternalCombustionEngine_maximumRpm();
		/**
		 * @generated
		 * @return feature descriptor minimumVelocity in type PropulsionSystem
		 */
		 UStructuralFeature PropulsionSystem_minimumVelocity = VesselPackage.theInstance.getPropulsionSystem_minimumVelocity();
		/**
		 * @generated
		 * @return feature descriptor maximumAcceleration in type PropulsionSystem
		 */
		 UStructuralFeature PropulsionSystem_maximumAcceleration = VesselPackage.theInstance.getPropulsionSystem_maximumAcceleration();
		/**
		 * @generated
		 * @return feature descriptor minimumAcceleration in type PropulsionSystem
		 */
		 UStructuralFeature PropulsionSystem_minimumAcceleration = VesselPackage.theInstance.getPropulsionSystem_minimumAcceleration();
		/**
		 * @generated
		 * @return feature descriptor commandedEngineForce in type Engine
		 */
		 UStructuralFeature Engine_commandedEngineForce = VesselPackage.theInstance.getEngine_commandedEngineForce();
		/**
		 * @generated
		 * @return feature descriptor engines in type PropulsionSystem
		 */
		 UStructuralFeature PropulsionSystem_engines = VesselPackage.theInstance.getPropulsionSystem_engines();
		/**
		 * @generated
		 * @return feature descriptor maximumRateOfTurn in type ControlSurfaces
		 */
		 UStructuralFeature ControlSurfaces_maximumRateOfTurn = VesselPackage.theInstance.getControlSurfaces_maximumRateOfTurn();
		/**
		 * @generated
		 * @return feature descriptor rpm in type InternalCombustionEngine
		 */
		 UStructuralFeature InternalCombustionEngine_rpm = VesselPackage.theInstance.getInternalCombustionEngine_rpm();
		/**
		 * @generated
		 * @return feature descriptor rudders in type ControlSurfaces
		 */
		 UStructuralFeature ControlSurfaces_rudders = VesselPackage.theInstance.getControlSurfaces_rudders();
		/**
		 * @generated
		 * @return feature descriptor maximumAngle in type Rudder
		 */
		 UStructuralFeature Rudder_maximumAngle = VesselPackage.theInstance.getRudder_maximumAngle();
		/**
		 * @generated
		 * @return feature descriptor propulsion in type DynamicSystem
		 */
		 UStructuralFeature DynamicSystem_propulsion = VesselPackage.theInstance.getDynamicSystem_propulsion();
		/**
		 * @generated
		 * @return feature descriptor commandedRpm in type InternalCombustionEngine
		 */
		 UStructuralFeature InternalCombustionEngine_commandedRpm = VesselPackage.theInstance.getInternalCombustionEngine_commandedRpm();
		/**
		 * @generated
		 * @return feature descriptor pitch in type Propeller
		 */
		 UStructuralFeature Propeller_pitch = VesselPackage.theInstance.getPropeller_pitch();
		/**
		 * @generated
		 * @return feature descriptor minimumRpm in type InternalCombustionEngine
		 */
		 UStructuralFeature InternalCombustionEngine_minimumRpm = VesselPackage.theInstance.getInternalCombustionEngine_minimumRpm();
		/**
		 * @generated
		 * @return feature descriptor minimumPitch in type Propeller
		 */
		 UStructuralFeature Propeller_minimumPitch = VesselPackage.theInstance.getPropeller_minimumPitch();
		/**
		 * @generated
		 * @return feature descriptor maximumPitch in type Propeller
		 */
		 UStructuralFeature Propeller_maximumPitch = VesselPackage.theInstance.getPropeller_maximumPitch();
		/**
		 * @generated
		 * @return feature descriptor commandedPitch in type Propeller
		 */
		 UStructuralFeature Propeller_commandedPitch = VesselPackage.theInstance.getPropeller_commandedPitch();
		/**
		 * @generated
		 * @return feature descriptor control in type DynamicSystem
		 */
		 UStructuralFeature DynamicSystem_control = VesselPackage.theInstance.getDynamicSystem_control();
		/**
		 * @generated
		 * @return feature descriptor teu in type CapacityCharacteristic
		 */
		 UStructuralFeature CapacityCharacteristic_teu = VesselPackage.theInstance.getCapacityCharacteristic_teu();
		/**
		 * @generated
		 * @return feature descriptor passengers in type CapacityCharacteristic
		 */
		 UStructuralFeature CapacityCharacteristic_passengers = VesselPackage.theInstance.getCapacityCharacteristic_passengers();
		/**
		 * @generated
		 * @return feature descriptor cars in type CapacityCharacteristic
		 */
		 UStructuralFeature CapacityCharacteristic_cars = VesselPackage.theInstance.getCapacityCharacteristic_cars();
		/**
		 * @generated
		 * @return feature descriptor commandedAngle in type Rudder
		 */
		 UStructuralFeature Rudder_commandedAngle = VesselPackage.theInstance.getRudder_commandedAngle();
		/**
		 * @generated
		 * @return feature descriptor propellers in type PropulsionSystem
		 */
		 UStructuralFeature PropulsionSystem_propellers = VesselPackage.theInstance.getPropulsionSystem_propellers();
		/**
		 * @generated
		 * @return feature descriptor trucks in type CapacityCharacteristic
		 */
		 UStructuralFeature CapacityCharacteristic_trucks = VesselPackage.theInstance.getCapacityCharacteristic_trucks();
		/**
		 * @generated
		 * @return feature descriptor roRoLanes in type CapacityCharacteristic
		 */
		 UStructuralFeature CapacityCharacteristic_roRoLanes = VesselPackage.theInstance.getCapacityCharacteristic_roRoLanes();
		/**
		 * @generated
		 * @return feature descriptor speed in type SpeedCommand
		 */
		 UStructuralFeature SpeedCommand_speed = VesselPackage.theInstance.getSpeedCommand_speed();
		/**
		 * @generated
		 * @return feature descriptor heading in type HeadingCommand
		 */
		 UStructuralFeature HeadingCommand_heading = VesselPackage.theInstance.getHeadingCommand_heading();
		/**
		 * @generated
		 * @return feature descriptor liquids in type CapacityCharacteristic
		 */
		 UStructuralFeature CapacityCharacteristic_liquids = VesselPackage.theInstance.getCapacityCharacteristic_liquids();
		/**
		 * @generated
		 * @return feature descriptor ligquidGas in type CapacityCharacteristic
		 */
		 UStructuralFeature CapacityCharacteristic_ligquidGas = VesselPackage.theInstance.getCapacityCharacteristic_ligquidGas();
		/**
		 * @generated
		 * @return feature descriptor activeRoute in type VoyageCharacteristic
		 */
		 UStructuralFeature VoyageCharacteristic_activeRoute = VesselPackage.theInstance.getVoyageCharacteristic_activeRoute();
		/**
		 * @generated
		 * @return feature descriptor maxTurnRate in type AutopilotConfiguration
		 */
		 UStructuralFeature AutopilotConfiguration_maxTurnRate = VesselPackage.theInstance.getAutopilotConfiguration_maxTurnRate();
		/**
		 * @generated
		 * @return feature descriptor commands in type StandingCommands
		 */
		 UStructuralFeature StandingCommands_commands = VesselPackage.theInstance.getStandingCommands_commands();
		/**
		 * @generated
		 * @return feature descriptor angleLimit in type AutopilotConfiguration
		 */
		 UStructuralFeature AutopilotConfiguration_angleLimit = VesselPackage.theInstance.getAutopilotConfiguration_angleLimit();
		/**
		 * @generated
		 * @return feature descriptor message in type AutopilotAlarm
		 */
		 UStructuralFeature AutopilotAlarm_message = VesselPackage.theInstance.getAutopilotAlarm_message();
		/**
		 * @generated
		 * @return feature descriptor minSpeed in type AutopilotConfiguration
		 */
		 UStructuralFeature AutopilotConfiguration_minSpeed = VesselPackage.theInstance.getAutopilotConfiguration_minSpeed();
		/**
		 * @generated
		 * @return feature descriptor oil in type CapacityCharacteristic
		 */
		 UStructuralFeature CapacityCharacteristic_oil = VesselPackage.theInstance.getCapacityCharacteristic_oil();
		/**
		 * @generated
		 * @return feature descriptor trajectory in type Autopilot
		 */
		 UStructuralFeature Autopilot_trajectory = VesselPackage.theInstance.getAutopilot_trajectory();
		/**
		 * @generated
		 * @return feature descriptor maxSpeed in type AutopilotConfiguration
		 */
		 UStructuralFeature AutopilotConfiguration_maxSpeed = VesselPackage.theInstance.getAutopilotConfiguration_maxSpeed();
		/**
		 * @generated
		 * @return feature descriptor creationTime in type AutopilotAlarm
		 */
		 UStructuralFeature AutopilotAlarm_creationTime = VesselPackage.theInstance.getAutopilotAlarm_creationTime();
		/**
		 * @generated
		 * @return feature descriptor routes in type VoyageCharacteristic
		 */
		 UStructuralFeature VoyageCharacteristic_routes = VesselPackage.theInstance.getVoyageCharacteristic_routes();
		/**
		 * @generated
		 * @return feature descriptor segment in type Autopilot
		 */
		 UStructuralFeature Autopilot_segment = VesselPackage.theInstance.getAutopilot_segment();
		/**
		 * @generated
		 * @return feature descriptor angle in type CommandedRudderAngle
		 */
		 UStructuralFeature CommandedRudderAngle_angle = VesselPackage.theInstance.getCommandedRudderAngle_angle();
		/**
		 * @generated
		 * @return feature descriptor pitch in type CommandedPropellerPitch
		 */
		 UStructuralFeature CommandedPropellerPitch_pitch = VesselPackage.theInstance.getCommandedPropellerPitch_pitch();
		/**
		 * @generated
		 * @return feature descriptor rpm in type CommandedEngineRpm
		 */
		 UStructuralFeature CommandedEngineRpm_rpm = VesselPackage.theInstance.getCommandedEngineRpm_rpm();
		/**
		 * @generated
		 * @return feature descriptor lastCommand in type Autopilot
		 */
		 UStructuralFeature Autopilot_lastCommand = VesselPackage.theInstance.getAutopilot_lastCommand();
		/**
		 * @generated
		 * @return feature descriptor configuration in type Autopilot
		 */
		 UStructuralFeature Autopilot_configuration = VesselPackage.theInstance.getAutopilot_configuration();
		/**
		 * @generated
		 * @return feature descriptor alarms in type Autopilot
		 */
		 UStructuralFeature Autopilot_alarms = VesselPackage.theInstance.getAutopilot_alarms();
		/**
		 * @generated
		 * @return feature descriptor creationTime in type CommandedValue
		 */
		 UStructuralFeature CommandedValue_creationTime = VesselPackage.theInstance.getCommandedValue_creationTime();
		/**
		 * @generated
		 * @return feature descriptor source in type CommandedValue
		 */
		 UStructuralFeature CommandedValue_source = VesselPackage.theInstance.getCommandedValue_source();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UEnum mVesselType = null;
	/**
	* @generated
	*/
	private UEnum mNavigationStatus = null;
	/**
	* @generated
	*/
	private UClass mNavigationInformation = null;
	/**
	* @generated
	*/
	private UClass mVessel = null;
	/**
	* @generated
	*/
	private UClass mVesselCharacteristic = null;
	/**
	* @generated
	*/
	private UClass mVesselBuildInformation = null;
	/**
	* @generated
	*/
	private UClass mDisplacement = null;
	/**
	* @generated
	*/
	private UClass mWatercraftHull = null;
	/**
	* @generated
	*/
	private UClass mVesselDimensionCharacteristic = null;
    /**
	* @generated
	*/
	private UClass mVesselSafetyCharacteristic = null;
	/**
	* @generated
	*/
	private UClass mEngineBuildInformation = null;
	/**
	* @generated
	*/
	private UClass mEngine = null;
	/**
	* @generated
	*/
	private UClass mInternalCombustionEngine = null;
	/**
	* @generated
	*/
	private UClass mPropeller = null;
	/**
	* @generated
	*/
	private UClass mRudder = null;
	/**
	* @generated
	*/
	private UClass mPropulsionSystem = null;
	/**
	* @generated
	*/
	private UClass mControlSurfaces = null;
	/**
	* @generated
	*/
	private UClass mDynamicSystem = null;
	/**
	* @generated
	*/
	private UClass mCapacityCharacteristic = null;
	/**
	* @generated
	*/
	private UClass mVoyageCharacteristic = null;
	/**
	* @generated
	*/
	private UClass mAutopilotConfiguration = null;
	/**
	* @generated
	*/
	private UClass mAutopilotAlarm = null;
	/**
	* @generated
	*/
	private UClass mStandingCommands = null;
	/**
	* @generated
	*/
	private UClass mAutopilot = null;
	/**
	* @generated
	*/
	private UClass mCommandedValue = null;
	/**
	* @generated
	*/
	private UClass mSteeringCommand = null;
	/**
	* @generated
	*/
	private UClass mSpeedCommand = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	//Features for classifier NavigationInformation
	/**
	 * @generated
	 */
	private UStructuralFeature mNavigationInformation_status = null;
	/**
	* @generated
	*/
	private UClass mHeadingCommand = null;
	/**
	* @generated
	*/
	private UClass mMachineCommand = null;
	//Features for classifier Vessel
	/**
	 * @generated
	 */
	private UStructuralFeature mVessel_mmsi = null;
	//Features for classifier Vessel
	/**
	 * @generated
	 */
	private UStructuralFeature mVessel_imo = null;
	/**
	* @generated
	*/
	private UClass mCommandedRudderAngle = null;
	/**
	* @generated
	*/
	private UClass mCommandedEngineRpm = null;
	//Features for classifier Vessel
	/**
	 * @generated
	 */
	private UStructuralFeature mVessel_callSign = null;
	/**
	* @generated
	*/
	private UClass mCommandedPropellerPitch = null;
	//Features for classifier Vessel
	/**
	 * @generated
	 */
	private UStructuralFeature mVessel_type = null;
	//Features for classifier VesselBuildInformation
	/**
	 * @generated
	 */
	private UStructuralFeature mVesselBuildInformation_hullNumber = null;
	//Features for classifier VesselBuildInformation
	/**
	 * @generated
	 */
	private UStructuralFeature mVesselBuildInformation_manufactor = null;
	//Features for classifier VesselBuildInformation
	/**
	 * @generated
	 */
	private UStructuralFeature mVesselBuildInformation_buildTime = null;
	//Features for classifier Displacement
	/**
	 * @generated
	 */
	private UStructuralFeature mDisplacement_light = null;
	//Features for classifier Displacement
	/**
	 * @generated
	 */
	private UStructuralFeature mDisplacement_loaded = null;
	//Features for classifier Displacement
	/**
	 * @generated
	 */
	private UStructuralFeature mDisplacement_deadweight = null;
	//Features for classifier Displacement
	/**
	 * @generated
	 */
	private UStructuralFeature mDisplacement_cargoDeadweigthTons = null;
	//Features for classifier WatercraftHull
	/**
	 * @generated
	 */
	private UStructuralFeature mWatercraftHull_beam = null;
	//Features for classifier WatercraftHull
	/**
	 * @generated
	 */
	private UStructuralFeature mWatercraftHull_lengthAtWaterline = null;
	//Features for classifier WatercraftHull
	/**
	 * @generated
	 */
	private UStructuralFeature mWatercraftHull_overAllLength = null;
	//Features for classifier WatercraftHull
	/**
	 * @generated
	 */
	private UStructuralFeature mWatercraftHull_draft = null;
	//Features for classifier WatercraftHull
	/**
	 * @generated
	 */
	private UStructuralFeature mWatercraftHull_mouldedDepth = null;
	//Features for classifier WatercraftHull
	/**
	 * @generated
	 */
	private UStructuralFeature mWatercraftHull_freeboard = null;
	//Features for classifier VesselDimensionCharacteristic
	/**
	 * @generated
	 */
	private UStructuralFeature mVesselDimensionCharacteristic_neadTonnage = null;
	//Features for classifier VesselDimensionCharacteristic
	/**
	 * @generated
	 */
	private UStructuralFeature mVesselDimensionCharacteristic_blockCoefficient = null;
	//Features for classifier VesselDimensionCharacteristic
	/**
	 * @generated
	 */
	private UStructuralFeature mVesselDimensionCharacteristic_displacement = null;
	//Features for classifier VesselDimensionCharacteristic
	/**
	 * @generated
	 */
	private UStructuralFeature mVesselDimensionCharacteristic_hull = null;
    /**
	 * @generated
	 */
	private UStructuralFeature mVesselSafetyCharacteristic_underKeelClearance = null;
	//Features for classifier EngineBuildInformation
	/**
	 * @generated
	 */
	private UStructuralFeature mEngineBuildInformation_model = null;
    /**
	 * @generated
	 */
	private UStructuralFeature mVesselSafetyCharacteristic_personalSpace = null;
	//Features for classifier EngineBuildInformation
	/**
	 * @generated
	 */
	private UStructuralFeature mEngineBuildInformation_manufactor = null;
	//Features for classifier EngineBuildInformation
	/**
	 * @generated
	 */
	private UStructuralFeature mEngineBuildInformation_buildYear = null;
	//Features for classifier Engine
	/**
	 * @generated
	 */
	private UStructuralFeature mEngine_buildInformation = null;
	//Features for classifier Engine
	/**
	 * @generated
	 */
	private UStructuralFeature mEngine_acceleration = null;
	//Features for classifier Rudder
	/**
	 * @generated
	 */
	private UStructuralFeature mRudder_rateOfTurn = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mInternalCombustionEngine_rpm = null;
	//Features for classifier PropulsionSystem
	/**
	 * @generated
	 */
	private UStructuralFeature mPropulsionSystem_maximumVelocity = null;
	//Features for classifier PropulsionSystem
	/**
	 * @generated
	 */
	private UStructuralFeature mPropulsionSystem_minimumVelocity = null;
	//Features for classifier PropulsionSystem
	/**
	 * @generated
	 */
	private UStructuralFeature mPropulsionSystem_maximumAcceleration = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mInternalCombustionEngine_maximumRpm = null;
	//Features for classifier PropulsionSystem
	/**
	 * @generated
	 */
	private UStructuralFeature mPropulsionSystem_minimumAcceleration = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mInternalCombustionEngine_commandedRpm = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mPropeller_commandedPitch = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mInternalCombustionEngine_minimumRpm = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mPropeller_pitch = null;
	//Features for classifier PropulsionSystem
	/**
	 * @generated
	 */
	private UStructuralFeature mPropulsionSystem_engines = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mRudder_maximumAngle = null;
	//Features for classifier ControlSurfaces
	/**
	 * @generated
	 */
	private UStructuralFeature mControlSurfaces_maximumRateOfTurn = null;
	//Features for classifier ControlSurfaces
	/**
	 * @generated
	 */
	private UStructuralFeature mControlSurfaces_rudders = null;
	//Features for classifier DynamicSystem
	/**
	 * @generated
	 */
	private UStructuralFeature mDynamicSystem_propulsion = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mPropeller_minimumPitch = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mEngine_commandedEngineForce = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mPropeller_maximumPitch = null;
	//Features for classifier DynamicSystem
	/**
	 * @generated
	 */
	private UStructuralFeature mDynamicSystem_control = null;
	//Features for classifier CapacityCharacteristic
	/**
	 * @generated
	 */
	private UStructuralFeature mCapacityCharacteristic_teu = null;
	//Features for classifier CapacityCharacteristic
	/**
	 * @generated
	 */
	private UStructuralFeature mCapacityCharacteristic_passengers = null;
	//Features for classifier CapacityCharacteristic
	/**
	 * @generated
	 */
	private UStructuralFeature mCapacityCharacteristic_cars = null;
	//Features for classifier CapacityCharacteristic
	/**
	 * @generated
	 */
	private UStructuralFeature mCapacityCharacteristic_trucks = null;
	//Features for classifier CapacityCharacteristic
	/**
	 * @generated
	 */
	private UStructuralFeature mCapacityCharacteristic_roRoLanes = null;
	//Features for classifier CapacityCharacteristic
	/**
	 * @generated
	 */
	private UStructuralFeature mCapacityCharacteristic_liquids = null;
	//Features for classifier CapacityCharacteristic
	/**
	 * @generated
	 */
	private UStructuralFeature mCapacityCharacteristic_ligquidGas = null;
	//Features for classifier CapacityCharacteristic
	/**
	 * @generated
	 */
	private UStructuralFeature mCapacityCharacteristic_oil = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mRudder_commandedAngle = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAutopilotConfiguration_angleLimit = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAutopilotConfiguration_maxTurnRate = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mVoyageCharacteristic_activeRoute = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAutopilotConfiguration_minSpeed = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAutopilotConfiguration_maxSpeed = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mSpeedCommand_speed = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mStandingCommands_commands = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAutopilotAlarm_message = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mHeadingCommand_heading = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAutopilot_trajectory = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAutopilotAlarm_creationTime = null;



	/**
	 * @generated
	 */
	public static VesselPackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package VesselPackage ...");
		theInstance = new VesselPackage();
		//initialize referenced models
		VehicleModel.init();
		IEC61174Model.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.domain.maritime.vessel");
		p.getContent().add(theInstance.mVesselType);
		p.getContent().add(theInstance.mNavigationStatus);
		p.getContent().add(theInstance.mNavigationInformation);
		p.getContent().add(theInstance.mVessel);
		p.getContent().add(theInstance.mVesselCharacteristic);
		p.getContent().add(theInstance.mVesselBuildInformation);
		p.getContent().add(theInstance.mDisplacement);
		p.getContent().add(theInstance.mWatercraftHull);
		p.getContent().add(theInstance.mVesselDimensionCharacteristic);
		p.getContent().add(theInstance.mVesselSafetyCharacteristic);
		p.getContent().add(theInstance.mEngineBuildInformation);
		p.getContent().add(theInstance.mEngine);
		p.getContent().add(theInstance.mInternalCombustionEngine);
		p.getContent().add(theInstance.mPropeller);
		p.getContent().add(theInstance.mRudder);
		p.getContent().add(theInstance.mPropulsionSystem);
		p.getContent().add(theInstance.mControlSurfaces);
		p.getContent().add(theInstance.mDynamicSystem);
		p.getContent().add(theInstance.mCapacityCharacteristic);
		p.getContent().add(theInstance.mVoyageCharacteristic);
		p.getContent().add(theInstance.mAutopilotConfiguration);
		p.getContent().add(theInstance.mAutopilotAlarm);
		p.getContent().add(theInstance.mAutopilot);
		p.getContent().add(theInstance.mStandingCommands);
		p.getContent().add(theInstance.mCommandedValue);
		p.getContent().add(theInstance.mSteeringCommand);
		p.getContent().add(theInstance.mSpeedCommand);
		p.getContent().add(theInstance.mHeadingCommand);
		p.getContent().add(theInstance.mMachineCommand);
		p.getContent().add(theInstance.mCommandedRudderAngle);
		p.getContent().add(theInstance.mCommandedEngineRpm);
		p.getContent().add(theInstance.mCommandedPropellerPitch);
		p.freeze();
		
		
		
		ULog.debug("... package VesselPackage initialized");
		
		return theInstance;
	}
	/**
	 * @generated
	 */
	private UStructuralFeature mVoyageCharacteristic_routes = null;

	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UEnum getVesselType(){
		if (mVesselType == null){
			mVesselType = UCoreMetaRepository.getUEnumeration(VesselType.class);
		}
		return mVesselType;
	}
	/**
	 * @generated
	 */
	private UStructuralFeature mAutopilot_segment = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAutopilot_alarms = null;
	/**
	* @generated
	*/
	public UEnum getNavigationStatus(){
		if (mNavigationStatus == null){
			mNavigationStatus = UCoreMetaRepository.getUEnumeration(NavigationStatus.class);
		}
		return mNavigationStatus;
	}
	/**
	 * @generated
	 */
	private UStructuralFeature mPropulsionSystem_propellers = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAutopilot_lastCommand = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mAutopilot_configuration = null;
	/**
	* @generated
	*/
	public UClass getNavigationInformation(){
		if (mNavigationInformation == null){
			mNavigationInformation = UCoreMetaRepository.getUClass(NavigationInformation.class);
		}
		return mNavigationInformation;
	}
	/**
	* @generated
	*/
	public UClass getVessel(){
		if (mVessel == null){
			mVessel = UCoreMetaRepository.getUClass(Vessel.class);
		}
		return mVessel;
	}
    /**
	* @generated
	*/
	public UClass getVesselCharacteristic(){
		if (mVesselCharacteristic == null){
			mVesselCharacteristic = UCoreMetaRepository.getUClass(VesselCharacteristic.class);
		}
		return mVesselCharacteristic;
	}
	/**
	* @generated
	*/
	public UClass getVesselBuildInformation(){
		if (mVesselBuildInformation == null){
			mVesselBuildInformation = UCoreMetaRepository.getUClass(VesselBuildInformation.class);
		}
		return mVesselBuildInformation;
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
		{		//Operations of Displacement
			UOperation operation = null;
			//operation : getDeadWeightMass(Mass, float)
			operation = UMetaBuilder.manual().createOperation("getDeadWeightMass", false, UnitsPackage.theInstance.getMass(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Displacement)instance).getDeadWeightMass((float)parameter[0]);
				}
			});
				operation.setDocumentation(" returns the dead weight mass, in relation to sea water (1025 kg/m^3)\r\n * @param seawaterFactor scales the mass to get the displaced weight in fresh water use 1000 [kg/m^3] / 1025 [kg/m^3]\r\n * @return the mass of the displaced water\r\n ");
				//Annotations for Displacement:getDeadWeightMass(Mass, float)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "seawaterFactor", TypeUtils.getPrimitiveType(float.class), 0, 1, UDirectionType.IN);
				mDisplacement.getOperations().add(operation);
		}
		{		//Operations of VesselDimensionCharacteristic
			UOperation operation = null;
			//operation : getOverallLength(Length)
			operation = UMetaBuilder.manual().createOperation("getOverallLength", false, UnitsPackage.theInstance.getLength(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((VesselDimensionCharacteristic)instance).getOverallLength();
				}
			});
				//Annotations for VesselDimensionCharacteristic:getOverallLength(Length)
				operation.createAnnotation("const");
				mVesselDimensionCharacteristic.getOperations().add(operation);
			//operation : getOverallBeam(Length)
			operation = UMetaBuilder.manual().createOperation("getOverallBeam", false, UnitsPackage.theInstance.getLength(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((VesselDimensionCharacteristic)instance).getOverallBeam();
				}
			});
				//Annotations for VesselDimensionCharacteristic:getOverallBeam(Length)
				operation.createAnnotation("const");
				mVesselDimensionCharacteristic.getOperations().add(operation);
		}
	}
	/**
	 * @generated
	 */
	private UStructuralFeature mCommandedPropellerPitch_pitch = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mCommandedValue_source = null;
	/**
	* @generated
	*/
	public UClass getDisplacement(){
		if (mDisplacement == null){
			mDisplacement = UCoreMetaRepository.getUClass(Displacement.class);
		}
		return mDisplacement;
	}
	/**
	 * @generated
	 */
	private UStructuralFeature mCommandedValue_creationTime = null;



    /**
	* @generated
	*/
	public UClass getWatercraftHull(){
		if (mWatercraftHull == null){
			mWatercraftHull = UCoreMetaRepository.getUClass(WatercraftHull.class);
		}
		return mWatercraftHull;
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of NavigationInformation
			mNavigationInformation_status = UMetaBuilder.manual().createFeature("status", VesselPackage.theInstance.getNavigationStatus(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mNavigationInformation_status, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((NavigationInformation)instance).getStatus(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((NavigationInformation)instance).setStatus((NavigationStatus)value); } }
				);
			
			//Features of Vessel
			mVessel_mmsi = UMetaBuilder.manual().createFeature("mmsi", TypeUtils.getPrimitiveType(long.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVessel_mmsi, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Vessel)instance).getMmsi(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Vessel)instance).setMmsi((long)value); } }
				);
			mVessel_imo = UMetaBuilder.manual().createFeature("imo", TypeUtils.getPrimitiveType(long.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVessel_imo, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Vessel)instance).getImo(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Vessel)instance).setImo((long)value); } }
				);
			mVessel_callSign = UMetaBuilder.manual().createFeature("callSign", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVessel_callSign, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Vessel)instance).getCallSign(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Vessel)instance).setCallSign((String)value); } }
				);
			mVessel_type = UMetaBuilder.manual().createFeature("type", VesselPackage.theInstance.getVesselType(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVessel_type, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Vessel)instance).getType(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Vessel)instance).setType((VesselType)value); } }
				);
			
			//Features of VesselBuildInformation
			mVesselBuildInformation_hullNumber = UMetaBuilder.manual().createFeature("hullNumber", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVesselBuildInformation_hullNumber, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((VesselBuildInformation)instance).getHullNumber(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((VesselBuildInformation)instance).setHullNumber((String)value); } }
				);
			mVesselBuildInformation_manufactor = UMetaBuilder.manual().createFeature("manufactor", CorePackage.theInstance.getRSIdentifier(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVesselBuildInformation_manufactor, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((VesselBuildInformation)instance).getManufactor(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((VesselBuildInformation)instance).setManufactor((RSIdentifier)value); } }
				);
			mVesselBuildInformation_buildTime = UMetaBuilder.manual().createFeature("buildTime", UnitsPackage.theInstance.getTime(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVesselBuildInformation_buildTime, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((VesselBuildInformation)instance).getBuildTime(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((VesselBuildInformation)instance).setBuildTime((Time)value); } }
				);
			
			//Features of Displacement
			mDisplacement_light = UMetaBuilder.manual().createFeature("light", UnitsPackage.theInstance.getVolume(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mDisplacement_light, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Displacement)instance).getLight(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Displacement)instance).setLight((Volume)value); } }
				);
				mDisplacement_light.setDocumentation("\r\n * The weight of the ship excluding cargo, fuel, ballast, stores, passengers, and crew, but with water in the boilers to steaming level.\r\n ");
			mDisplacement_loaded = UMetaBuilder.manual().createFeature("loaded", UnitsPackage.theInstance.getVolume(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mDisplacement_loaded, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Displacement)instance).getLoaded(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Displacement)instance).setLoaded((Volume)value); } }
				);
				mDisplacement_loaded.setDocumentation("\r\n * The weight of the ship including cargo, passengers, fuel, water, stores, dunnage and such other items necessary for use on a voyage, which brings the vessel down to her load draft.\r\n ");
			mDisplacement_deadweight = UMetaBuilder.manual().createFeature("deadweight", UnitsPackage.theInstance.getVolume(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mDisplacement_deadweight, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Displacement)instance).getDeadweight(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Displacement)instance).setDeadweight((Volume)value); } }
				);
				mDisplacement_deadweight.setDocumentation("\r\n * (DWT) - The difference between displacement, light and displacement, loaded. A measure of the ship's total carrying capacity.\r\n ");
			mDisplacement_cargoDeadweigthTons = UMetaBuilder.manual().createFeature("cargoDeadweigthTons", UnitsPackage.theInstance.getVolume(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mDisplacement_cargoDeadweigthTons, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Displacement)instance).getCargoDeadweigthTons(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Displacement)instance).setCargoDeadweigthTons((Volume)value); } }
				);
				mDisplacement_cargoDeadweigthTons.setDocumentation("\r\n * The weight remaining after deducting fuel, water, stores, dunnage and such other items necessary for use on a voyage from the deadweight of the vessel.\r\n ");
			
			//Features of WatercraftHull
			mWatercraftHull_beam = UMetaBuilder.manual().createFeature("beam", UnitsPackage.theInstance.getLength(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mWatercraftHull_beam, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((WatercraftHull)instance).getBeam(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((WatercraftHull)instance).setBeam((Length)value); } }
				);
				mWatercraftHull_beam.setDocumentation("\r\n * Beam or breadth (B) is the width of the hull. (ex: BWL is the maximum beam at the waterline)\r\n ");
			mWatercraftHull_lengthAtWaterline = UMetaBuilder.manual().createFeature("lengthAtWaterline", UnitsPackage.theInstance.getLength(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mWatercraftHull_lengthAtWaterline, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((WatercraftHull)instance).getLengthAtWaterline(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((WatercraftHull)instance).setLengthAtWaterline((Length)value); } }
				);
				mWatercraftHull_lengthAtWaterline.setDocumentation("\r\n * (LWL) is the length from the forwardmost point of the waterline measured in profile to the stern-most point of the waterline.\r\n ");
			mWatercraftHull_overAllLength = UMetaBuilder.manual().createFeature("overAllLength", UnitsPackage.theInstance.getLength(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mWatercraftHull_overAllLength, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((WatercraftHull)instance).getOverAllLength(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((WatercraftHull)instance).setOverAllLength((Length)value); } }
				);
				mWatercraftHull_overAllLength.setDocumentation("\r\n * (LOA) is the extreme length from one end to the other\r\n ");
			mWatercraftHull_draft = UMetaBuilder.manual().createFeature("draft", UnitsPackage.theInstance.getLength(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mWatercraftHull_draft, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((WatercraftHull)instance).getDraft(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((WatercraftHull)instance).setDraft((Length)value); } }
				);
				mWatercraftHull_draft.setDocumentation("\r\n * Draft (d) or (T) is the vertical distance from the bottom of the keel to the waterline.\r\n ");
			mWatercraftHull_mouldedDepth = UMetaBuilder.manual().createFeature("mouldedDepth", UnitsPackage.theInstance.getLength(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mWatercraftHull_mouldedDepth, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((WatercraftHull)instance).getMouldedDepth(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((WatercraftHull)instance).setMouldedDepth((Length)value); } }
				);
				mWatercraftHull_mouldedDepth.setDocumentation("\r\n * (D) is the vertical distance measured from the top of the keel to the underside of the upper deck at side\r\n ");
			mWatercraftHull_freeboard = UMetaBuilder.manual().createFeature("freeboard", UnitsPackage.theInstance.getLength(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mWatercraftHull_freeboard, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((WatercraftHull)instance).getFreeboard(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((WatercraftHull)instance).setFreeboard((Length)value); } }
				);
				mWatercraftHull_freeboard.setDocumentation("\r\n * Freeboard (FB) is depth plus the height of the keel structure minus draft.\r\n ");
			
			//Features of VesselDimensionCharacteristic
			mVesselDimensionCharacteristic_neadTonnage = UMetaBuilder.manual().createFeature("neadTonnage", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVesselDimensionCharacteristic_neadTonnage, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((VesselDimensionCharacteristic)instance).getNeadTonnage(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((VesselDimensionCharacteristic)instance).setNeadTonnage((double)value); } }
				);
				mVesselDimensionCharacteristic_neadTonnage.setDocumentation("\r\n * Net tonnage (often abbreviated as NT, N.T. or nt) is a dimensionless index calculated from the total moulded volume of the ship's cargo spaces by using a mathematical formula. \r\n * Defined in The International Convention on Tonnage Measurement of Ships that was adopted by the International Maritime Organization in 1969, the net tonnage replaced the earlier net register tonnage (NRT) which denoted the volume of the ship's revenue-earning spaces in \"register tons\", units of volume equal to 100 cubic feet (2.83 m3).\r\n * Net tonnage is used to calculate the port duties and should not be taken as less than 30 per cent of the ship's gross tonnage.\r\n * \\source Wikipedia \r\n ");
			mVesselDimensionCharacteristic_blockCoefficient = UMetaBuilder.manual().createFeature("blockCoefficient", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVesselDimensionCharacteristic_blockCoefficient, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((VesselDimensionCharacteristic)instance).getBlockCoefficient(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((VesselDimensionCharacteristic)instance).setBlockCoefficient((double)value); } }
				);
				mVesselDimensionCharacteristic_blockCoefficient.setDocumentation("\r\n *  Block coefficient (Cb) is the volume (V) divided by the LWL x BWL x T. If you draw a box around the submerged part of the ship, it is the ratio of the box volume occupied by the ship. It gives a sense of how much of the block defined by the LWL, beam (B) & draft (T) is filled by the hull. Full forms such as oil tankers will have a high Cb where fine shapes such as sailboats will have a low Cb.\r\n * \\source Wikipedia / Beschnidt2010\r\n ");
			mVesselDimensionCharacteristic_displacement = UMetaBuilder.manual().createFeature("displacement", VesselPackage.theInstance.getDisplacement(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVesselDimensionCharacteristic_displacement, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((VesselDimensionCharacteristic)instance).getDisplacement(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((VesselDimensionCharacteristic)instance).setDisplacement((Displacement)value); } }
				);
				mVesselDimensionCharacteristic_displacement.setDocumentation("\r\n * A measurement of the weight of the vessel, usually used for warships. (Merchant ships are usually measured based on the volume of cargo space; see tonnage).\r\n ");
			mVesselDimensionCharacteristic_hull = UMetaBuilder.manual().createFeature("hull", VesselPackage.theInstance.getWatercraftHull(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVesselDimensionCharacteristic_hull, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((VesselDimensionCharacteristic)instance).getHull(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((VesselDimensionCharacteristic)instance).setHull((WatercraftHull)value); } }
				);
			
			//Features of VesselSafetyCharacteristic
			mVesselSafetyCharacteristic_underKeelClearance = UMetaBuilder.manual().createFeature("underKeelClearance", UnitsPackage.theInstance.getLength(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVesselSafetyCharacteristic_underKeelClearance, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((VesselSafetyCharacteristic)instance).getUnderKeelClearance(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((VesselSafetyCharacteristic)instance).setUnderKeelClearance((Length)value); } }
				);
				mVesselSafetyCharacteristic_underKeelClearance.setDocumentation("\r\n * Under Keel Clearance, space between keel and ground\r\n ");
			mVesselSafetyCharacteristic_personalSpace = UMetaBuilder.manual().createFeature("personalSpace", UnitsPackage.theInstance.getLength(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVesselSafetyCharacteristic_personalSpace, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((VesselSafetyCharacteristic)instance).getPersonalSpace(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((VesselSafetyCharacteristic)instance).setPersonalSpace((Length)value); } }
				);
				mVesselSafetyCharacteristic_personalSpace.setDocumentation("\r\n * Space around a ship where no other ship should be \r\n ");
			
			//Features of EngineBuildInformation
			mEngineBuildInformation_model = UMetaBuilder.manual().createFeature("model", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEngineBuildInformation_model, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((EngineBuildInformation)instance).getModel(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((EngineBuildInformation)instance).setModel((String)value); } }
				);
			mEngineBuildInformation_manufactor = UMetaBuilder.manual().createFeature("manufactor", CorePackage.theInstance.getRSIdentifier(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEngineBuildInformation_manufactor, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((EngineBuildInformation)instance).getManufactor(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((EngineBuildInformation)instance).setManufactor((RSIdentifier)value); } }
				);
			mEngineBuildInformation_buildYear = UMetaBuilder.manual().createFeature("buildYear", UnitsPackage.theInstance.getTime(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEngineBuildInformation_buildYear, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((EngineBuildInformation)instance).getBuildYear(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((EngineBuildInformation)instance).setBuildYear((Time)value); } }
				);
			
			//Features of Engine
			mEngine_buildInformation = UMetaBuilder.manual().createFeature("buildInformation", VesselPackage.theInstance.getEngineBuildInformation(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEngine_buildInformation, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Engine)instance).getBuildInformation(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Engine)instance).setBuildInformation((EngineBuildInformation)value); } }
				);
			mEngine_acceleration = UMetaBuilder.manual().createFeature("acceleration", UnitsPackage.theInstance.getAcceleration(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEngine_acceleration, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Engine)instance).getAcceleration(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Engine)instance).setAcceleration((Acceleration)value); } }
				);
			mEngine_commandedEngineForce = UMetaBuilder.manual().createFeature("commandedEngineForce", VesselPackage.theInstance.getCommandedValue(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEngine_commandedEngineForce, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Engine)instance).getCommandedEngineForce(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Engine)instance).setCommandedEngineForce((CommandedValue)value); } }
				);
			
			//Features of InternalCombustionEngine
			mInternalCombustionEngine_rpm = UMetaBuilder.manual().createFeature("rpm", UnitsPackage.theInstance.getAngularSpeed(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mInternalCombustionEngine_rpm, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((InternalCombustionEngine)instance).getRpm(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((InternalCombustionEngine)instance).setRpm((AngularSpeed)value); } }
				);
			mInternalCombustionEngine_maximumRpm = UMetaBuilder.manual().createFeature("maximumRpm", UnitsPackage.theInstance.getAngularSpeed(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mInternalCombustionEngine_maximumRpm, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((InternalCombustionEngine)instance).getMaximumRpm(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((InternalCombustionEngine)instance).setMaximumRpm((AngularSpeed)value); } }
				);
			mInternalCombustionEngine_minimumRpm = UMetaBuilder.manual().createFeature("minimumRpm", UnitsPackage.theInstance.getAngularSpeed(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mInternalCombustionEngine_minimumRpm, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((InternalCombustionEngine)instance).getMinimumRpm(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((InternalCombustionEngine)instance).setMinimumRpm((AngularSpeed)value); } }
				);
			mInternalCombustionEngine_commandedRpm = UMetaBuilder.manual().createFeature("commandedRpm", VesselPackage.theInstance.getCommandedEngineRpm(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mInternalCombustionEngine_commandedRpm, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((InternalCombustionEngine)instance).getCommandedRpm(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((InternalCombustionEngine)instance).setCommandedRpm((CommandedEngineRpm)value); } }
				);
			
			//Features of Propeller
			mPropeller_pitch = UMetaBuilder.manual().createFeature("pitch", UnitsPackage.theInstance.getAngle(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mPropeller_pitch, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Propeller)instance).getPitch(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Propeller)instance).setPitch((Angle)value); } }
				);
			mPropeller_minimumPitch = UMetaBuilder.manual().createFeature("minimumPitch", UnitsPackage.theInstance.getAngle(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mPropeller_minimumPitch, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Propeller)instance).getMinimumPitch(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Propeller)instance).setMinimumPitch((Angle)value); } }
				);
			mPropeller_maximumPitch = UMetaBuilder.manual().createFeature("maximumPitch", UnitsPackage.theInstance.getAngle(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mPropeller_maximumPitch, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Propeller)instance).getMaximumPitch(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Propeller)instance).setMaximumPitch((Angle)value); } }
				);
			mPropeller_commandedPitch = UMetaBuilder.manual().createFeature("commandedPitch", VesselPackage.theInstance.getCommandedPropellerPitch(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mPropeller_commandedPitch, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Propeller)instance).getCommandedPitch(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Propeller)instance).setCommandedPitch((CommandedPropellerPitch)value); } }
				);
			
			//Features of Rudder
			mRudder_rateOfTurn = UMetaBuilder.manual().createFeature("rateOfTurn", UnitsPackage.theInstance.getAngularSpeed(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRudder_rateOfTurn, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Rudder)instance).getRateOfTurn(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Rudder)instance).setRateOfTurn((AngularSpeed)value); } }
				);
			mRudder_maximumAngle = UMetaBuilder.manual().createFeature("maximumAngle", UnitsPackage.theInstance.getAngle(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRudder_maximumAngle, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Rudder)instance).getMaximumAngle(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Rudder)instance).setMaximumAngle((Angle)value); } }
				);
			mRudder_commandedAngle = UMetaBuilder.manual().createFeature("commandedAngle", VesselPackage.theInstance.getCommandedRudderAngle(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRudder_commandedAngle, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Rudder)instance).getCommandedAngle(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Rudder)instance).setCommandedAngle((CommandedRudderAngle)value); } }
				);
			
			//Features of PropulsionSystem
			mPropulsionSystem_maximumVelocity = UMetaBuilder.manual().createFeature("maximumVelocity", UnitsPackage.theInstance.getVelocity(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mPropulsionSystem_maximumVelocity, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((PropulsionSystem)instance).getMaximumVelocity(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((PropulsionSystem)instance).setMaximumVelocity((Velocity)value); } }
				);
			mPropulsionSystem_minimumVelocity = UMetaBuilder.manual().createFeature("minimumVelocity", UnitsPackage.theInstance.getVelocity(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mPropulsionSystem_minimumVelocity, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((PropulsionSystem)instance).getMinimumVelocity(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((PropulsionSystem)instance).setMinimumVelocity((Velocity)value); } }
				);
				mPropulsionSystem_minimumVelocity.setDocumentation(" may be negativ to indicate backwards movements ");
			mPropulsionSystem_maximumAcceleration = UMetaBuilder.manual().createFeature("maximumAcceleration", UnitsPackage.theInstance.getAcceleration(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mPropulsionSystem_maximumAcceleration, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((PropulsionSystem)instance).getMaximumAcceleration(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((PropulsionSystem)instance).setMaximumAcceleration((Acceleration)value); } }
				);
			mPropulsionSystem_minimumAcceleration = UMetaBuilder.manual().createFeature("minimumAcceleration", UnitsPackage.theInstance.getAcceleration(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mPropulsionSystem_minimumAcceleration, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((PropulsionSystem)instance).getMinimumAcceleration(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((PropulsionSystem)instance).setMinimumAcceleration((Acceleration)value); } }
				);
			mPropulsionSystem_engines = UMetaBuilder.manual().createFeature("engines", VesselPackage.theInstance.getEngine(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mPropulsionSystem_engines, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((PropulsionSystem)instance).getEngines(); } }, 
						null
				);
			mPropulsionSystem_propellers = UMetaBuilder.manual().createFeature("propellers", VesselPackage.theInstance.getPropeller(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mPropulsionSystem_propellers, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((PropulsionSystem)instance).getPropellers(); } }, 
						null
				);
			
			//Features of ControlSurfaces
			mControlSurfaces_maximumRateOfTurn = UMetaBuilder.manual().createFeature("maximumRateOfTurn", UnitsPackage.theInstance.getAngularSpeed(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mControlSurfaces_maximumRateOfTurn, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ControlSurfaces)instance).getMaximumRateOfTurn(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((ControlSurfaces)instance).setMaximumRateOfTurn((AngularSpeed)value); } }
				);
			mControlSurfaces_rudders = UMetaBuilder.manual().createFeature("rudders", VesselPackage.theInstance.getRudder(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mControlSurfaces_rudders, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((ControlSurfaces)instance).getRudders(); } }, 
						null
				);
			
			//Features of DynamicSystem
			mDynamicSystem_propulsion = UMetaBuilder.manual().createFeature("propulsion", VesselPackage.theInstance.getPropulsionSystem(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mDynamicSystem_propulsion, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((DynamicSystem)instance).getPropulsion(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((DynamicSystem)instance).setPropulsion((PropulsionSystem)value); } }
				);
			mDynamicSystem_control = UMetaBuilder.manual().createFeature("control", VesselPackage.theInstance.getControlSurfaces(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mDynamicSystem_control, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((DynamicSystem)instance).getControl(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((DynamicSystem)instance).setControl((ControlSurfaces)value); } }
				);
			
			//Features of CapacityCharacteristic
			mCapacityCharacteristic_teu = UMetaBuilder.manual().createFeature("teu", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCapacityCharacteristic_teu, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CapacityCharacteristic)instance).getTeu(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CapacityCharacteristic)instance).setTeu((int)value); } }
				);
			mCapacityCharacteristic_passengers = UMetaBuilder.manual().createFeature("passengers", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCapacityCharacteristic_passengers, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CapacityCharacteristic)instance).getPassengers(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CapacityCharacteristic)instance).setPassengers((int)value); } }
				);
			mCapacityCharacteristic_cars = UMetaBuilder.manual().createFeature("cars", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCapacityCharacteristic_cars, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CapacityCharacteristic)instance).getCars(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CapacityCharacteristic)instance).setCars((int)value); } }
				);
			mCapacityCharacteristic_trucks = UMetaBuilder.manual().createFeature("trucks", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCapacityCharacteristic_trucks, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CapacityCharacteristic)instance).getTrucks(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CapacityCharacteristic)instance).setTrucks((int)value); } }
				);
			mCapacityCharacteristic_roRoLanes = UMetaBuilder.manual().createFeature("roRoLanes", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCapacityCharacteristic_roRoLanes, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CapacityCharacteristic)instance).getRoRoLanes(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CapacityCharacteristic)instance).setRoRoLanes((int)value); } }
				);
			mCapacityCharacteristic_liquids = UMetaBuilder.manual().createFeature("liquids", UnitsPackage.theInstance.getVolume(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCapacityCharacteristic_liquids, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CapacityCharacteristic)instance).getLiquids(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CapacityCharacteristic)instance).setLiquids((Volume)value); } }
				);
			mCapacityCharacteristic_ligquidGas = UMetaBuilder.manual().createFeature("ligquidGas", UnitsPackage.theInstance.getVolume(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCapacityCharacteristic_ligquidGas, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CapacityCharacteristic)instance).getLigquidGas(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CapacityCharacteristic)instance).setLigquidGas((Volume)value); } }
				);
			mCapacityCharacteristic_oil = UMetaBuilder.manual().createFeature("oil", UnitsPackage.theInstance.getVolume(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCapacityCharacteristic_oil, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CapacityCharacteristic)instance).getOil(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CapacityCharacteristic)instance).setOil((Volume)value); } }
				);
			
			//Features of VoyageCharacteristic
			mVoyageCharacteristic_routes = UMetaBuilder.manual().createFeature("routes", Iec61174Package.theInstance.getRoute(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mVoyageCharacteristic_routes, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((VoyageCharacteristic)instance).getRoutes(); } }, 
						null
				);
				mVoyageCharacteristic_routes.setDocumentation(" all routes assigned to this Voyage, each route may have a name and a schedule ");
			mVoyageCharacteristic_activeRoute = UMetaBuilder.manual().createFeature("activeRoute", Iec61174Package.theInstance.getRoute(), UAssociationType.AGGREGATION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mVoyageCharacteristic_activeRoute, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((VoyageCharacteristic)instance).getActiveRoute(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((VoyageCharacteristic)instance).setActiveRoute((Route)value); } }
				);
				mVoyageCharacteristic_activeRoute.setDocumentation(" current active route or null, if no route is active. \r\n * @note this route shall point to a route inside the routes[*] list\r\n ");
			
			//Features of AutopilotConfiguration
			mAutopilotConfiguration_angleLimit = UMetaBuilder.manual().createFeature("angleLimit", UnitsPackage.theInstance.getAngle(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAutopilotConfiguration_angleLimit, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AutopilotConfiguration)instance).getAngleLimit(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AutopilotConfiguration)instance).setAngleLimit((Angle)value); } }
				);
				mAutopilotConfiguration_angleLimit.setDocumentation(" Maximum rudder angle the autopilot is allowed to use ");
			mAutopilotConfiguration_maxTurnRate = UMetaBuilder.manual().createFeature("maxTurnRate", UnitsPackage.theInstance.getAngularSpeed(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAutopilotConfiguration_maxTurnRate, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AutopilotConfiguration)instance).getMaxTurnRate(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AutopilotConfiguration)instance).setMaxTurnRate((AngularSpeed)value); } }
				);
				mAutopilotConfiguration_maxTurnRate.setDocumentation(" Maximum rate of turn, the autopilot ia allowed to use ");
			mAutopilotConfiguration_minSpeed = UMetaBuilder.manual().createFeature("minSpeed", UnitsPackage.theInstance.getSpeed(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAutopilotConfiguration_minSpeed, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AutopilotConfiguration)instance).getMinSpeed(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AutopilotConfiguration)instance).setMinSpeed((Speed)value); } }
				);
				mAutopilotConfiguration_minSpeed.setDocumentation(" minimum speed (sog), the autopilot is allowed to use ");
			mAutopilotConfiguration_maxSpeed = UMetaBuilder.manual().createFeature("maxSpeed", UnitsPackage.theInstance.getSpeed(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAutopilotConfiguration_maxSpeed, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AutopilotConfiguration)instance).getMaxSpeed(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AutopilotConfiguration)instance).setMaxSpeed((Speed)value); } }
				);
				mAutopilotConfiguration_maxSpeed.setDocumentation(" maximum speed (sog), the autopliot is allowed to use ");
			
			//Features of AutopilotAlarm
			mAutopilotAlarm_message = UMetaBuilder.manual().createFeature("message", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAutopilotAlarm_message, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AutopilotAlarm)instance).getMessage(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AutopilotAlarm)instance).setMessage((String)value); } }
				);
			mAutopilotAlarm_creationTime = UMetaBuilder.manual().createFeature("creationTime", UnitsPackage.theInstance.getTime(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAutopilotAlarm_creationTime, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((AutopilotAlarm)instance).getCreationTime(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((AutopilotAlarm)instance).setCreationTime((Time)value); } }
				);
			
			//Features of Autopilot
			mAutopilot_trajectory = UMetaBuilder.manual().createFeature("trajectory", VehiclePackage.theInstance.getTrajectory(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAutopilot_trajectory, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Autopilot)instance).getTrajectory(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Autopilot)instance).setTrajectory((Trajectory)value); } }
				);
				mAutopilot_trajectory.setDocumentation(" Trajectory the autopilot should follow ");
			mAutopilot_configuration = UMetaBuilder.manual().createFeature("configuration", VesselPackage.theInstance.getAutopilotConfiguration(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAutopilot_configuration, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Autopilot)instance).getConfiguration(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Autopilot)instance).setConfiguration((AutopilotConfiguration)value); } }
				);
			mAutopilot_alarms = UMetaBuilder.manual().createFeature("alarms", VesselPackage.theInstance.getAutopilotAlarm(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mAutopilot_alarms, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Autopilot)instance).getAlarms(); } }, 
						null
				);
			mAutopilot_lastCommand = UMetaBuilder.manual().createFeature("lastCommand", VesselPackage.theInstance.getCommandedValue(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAutopilot_lastCommand, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Autopilot)instance).getLastCommand(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Autopilot)instance).setLastCommand((CommandedValue)value); } }
				);
			mAutopilot_segment = UMetaBuilder.manual().createFeature("segment", VehiclePackage.theInstance.getTrajectorySegment(), UAssociationType.AGGREGATION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mAutopilot_segment, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Autopilot)instance).getSegment(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Autopilot)instance).setSegment((TrajectorySegment)value); } }
				);
				mAutopilot_segment.setDocumentation(" current active trajectory segment that is used by this autopilot \r\n\t\t\t\t\t\t\t\t * @note this segment has to be part of the current trajectory ");
			
			//Features of StandingCommands
			mStandingCommands_commands = UMetaBuilder.manual().createFeature("commands", VesselPackage.theInstance.getSteeringCommand(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mStandingCommands_commands, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((StandingCommands)instance).getCommands(); } }, 
						null
				);
				mStandingCommands_commands.setDocumentation(" List of currently valid steering commands ");
			
			//Features of CommandedValue
			mCommandedValue_creationTime = UMetaBuilder.manual().createFeature("creationTime", UnitsPackage.theInstance.getTime(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCommandedValue_creationTime, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CommandedValue)instance).getCreationTime(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CommandedValue)instance).setCreationTime((Time)value); } }
				);
				mCommandedValue_creationTime.setDocumentation(" time of creation ");
			mCommandedValue_source = UMetaBuilder.manual().createFeature("source", CorePackage.theInstance.getModelReference(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCommandedValue_source, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CommandedValue)instance).getSource(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CommandedValue)instance).setSource((ModelReference)value); } }
				);
				mCommandedValue_source.setDocumentation(" Optional source of the command (for example the captain) ");
			
			//Features of SpeedCommand
			mSpeedCommand_speed = UMetaBuilder.manual().createFeature("speed", UnitsPackage.theInstance.getSpeed(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mSpeedCommand_speed, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((SpeedCommand)instance).getSpeed(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((SpeedCommand)instance).setSpeed((Speed)value); } }
				);
			
			//Features of HeadingCommand
			mHeadingCommand_heading = UMetaBuilder.manual().createFeature("heading", UnitsPackage.theInstance.getAngle(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mHeadingCommand_heading, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((HeadingCommand)instance).getHeading(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((HeadingCommand)instance).setHeading((Angle)value); } }
				);
			
			//Features of CommandedRudderAngle
			mCommandedRudderAngle_angle = UMetaBuilder.manual().createFeature("angle", UnitsPackage.theInstance.getAngle(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCommandedRudderAngle_angle, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CommandedRudderAngle)instance).getAngle(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CommandedRudderAngle)instance).setAngle((Angle)value); } }
				);
			
			//Features of CommandedEngineRpm
			mCommandedEngineRpm_rpm = UMetaBuilder.manual().createFeature("rpm", UnitsPackage.theInstance.getAngularSpeed(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCommandedEngineRpm_rpm, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CommandedEngineRpm)instance).getRpm(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CommandedEngineRpm)instance).setRpm((AngularSpeed)value); } }
				);
			
			//Features of CommandedPropellerPitch
			mCommandedPropellerPitch_pitch = UMetaBuilder.manual().createFeature("pitch", UnitsPackage.theInstance.getAngle(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCommandedPropellerPitch_pitch, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CommandedPropellerPitch)instance).getPitch(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CommandedPropellerPitch)instance).setPitch((Angle)value); } }
				);
			
		}
		{ //assign features
			mNavigationInformation.getStructuralFeatures().add(mNavigationInformation_status);
			mVessel.getStructuralFeatures().add(mVessel_mmsi);
			mVessel.getStructuralFeatures().add(mVessel_imo);
			mVessel.getStructuralFeatures().add(mVessel_callSign);
			mVessel.getStructuralFeatures().add(mVessel_type);
			mVesselBuildInformation.getStructuralFeatures().add(mVesselBuildInformation_hullNumber);
			mVesselBuildInformation.getStructuralFeatures().add(mVesselBuildInformation_manufactor);
			mVesselBuildInformation.getStructuralFeatures().add(mVesselBuildInformation_buildTime);
			mDisplacement.getStructuralFeatures().add(mDisplacement_light);
			mDisplacement.getStructuralFeatures().add(mDisplacement_loaded);
			mDisplacement.getStructuralFeatures().add(mDisplacement_deadweight);
			mDisplacement.getStructuralFeatures().add(mDisplacement_cargoDeadweigthTons);
			mWatercraftHull.getStructuralFeatures().add(mWatercraftHull_beam);
			mWatercraftHull.getStructuralFeatures().add(mWatercraftHull_lengthAtWaterline);
			mWatercraftHull.getStructuralFeatures().add(mWatercraftHull_overAllLength);
			mWatercraftHull.getStructuralFeatures().add(mWatercraftHull_draft);
			mWatercraftHull.getStructuralFeatures().add(mWatercraftHull_mouldedDepth);
			mWatercraftHull.getStructuralFeatures().add(mWatercraftHull_freeboard);
			mVesselDimensionCharacteristic.getStructuralFeatures().add(mVesselDimensionCharacteristic_neadTonnage);
			mVesselDimensionCharacteristic.getStructuralFeatures().add(mVesselDimensionCharacteristic_blockCoefficient);
			mVesselDimensionCharacteristic.getStructuralFeatures().add(mVesselDimensionCharacteristic_displacement);
			mVesselDimensionCharacteristic.getStructuralFeatures().add(mVesselDimensionCharacteristic_hull);
			mVesselSafetyCharacteristic.getStructuralFeatures().add(mVesselSafetyCharacteristic_underKeelClearance);
			mVesselSafetyCharacteristic.getStructuralFeatures().add(mVesselSafetyCharacteristic_personalSpace);
			mEngineBuildInformation.getStructuralFeatures().add(mEngineBuildInformation_model);
			mEngineBuildInformation.getStructuralFeatures().add(mEngineBuildInformation_manufactor);
			mEngineBuildInformation.getStructuralFeatures().add(mEngineBuildInformation_buildYear);
			mEngine.getStructuralFeatures().add(mEngine_buildInformation);
			mEngine.getStructuralFeatures().add(mEngine_acceleration);
			mEngine.getStructuralFeatures().add(mEngine_commandedEngineForce);
			mInternalCombustionEngine.getStructuralFeatures().add(mInternalCombustionEngine_rpm);
			mInternalCombustionEngine.getStructuralFeatures().add(mInternalCombustionEngine_maximumRpm);
			mInternalCombustionEngine.getStructuralFeatures().add(mInternalCombustionEngine_minimumRpm);
			mInternalCombustionEngine.getStructuralFeatures().add(mInternalCombustionEngine_commandedRpm);
			mPropeller.getStructuralFeatures().add(mPropeller_pitch);
			mPropeller.getStructuralFeatures().add(mPropeller_minimumPitch);
			mPropeller.getStructuralFeatures().add(mPropeller_maximumPitch);
			mPropeller.getStructuralFeatures().add(mPropeller_commandedPitch);
			mRudder.getStructuralFeatures().add(mRudder_rateOfTurn);
			mRudder.getStructuralFeatures().add(mRudder_maximumAngle);
			mRudder.getStructuralFeatures().add(mRudder_commandedAngle);
			mPropulsionSystem.getStructuralFeatures().add(mPropulsionSystem_maximumVelocity);
			mPropulsionSystem.getStructuralFeatures().add(mPropulsionSystem_minimumVelocity);
			mPropulsionSystem.getStructuralFeatures().add(mPropulsionSystem_maximumAcceleration);
			mPropulsionSystem.getStructuralFeatures().add(mPropulsionSystem_minimumAcceleration);
			mPropulsionSystem.getStructuralFeatures().add(mPropulsionSystem_engines);
			mPropulsionSystem.getStructuralFeatures().add(mPropulsionSystem_propellers);
			mControlSurfaces.getStructuralFeatures().add(mControlSurfaces_maximumRateOfTurn);
			mControlSurfaces.getStructuralFeatures().add(mControlSurfaces_rudders);
			mDynamicSystem.getStructuralFeatures().add(mDynamicSystem_propulsion);
			mDynamicSystem.getStructuralFeatures().add(mDynamicSystem_control);
			mCapacityCharacteristic.getStructuralFeatures().add(mCapacityCharacteristic_teu);
			mCapacityCharacteristic.getStructuralFeatures().add(mCapacityCharacteristic_passengers);
			mCapacityCharacteristic.getStructuralFeatures().add(mCapacityCharacteristic_cars);
			mCapacityCharacteristic.getStructuralFeatures().add(mCapacityCharacteristic_trucks);
			mCapacityCharacteristic.getStructuralFeatures().add(mCapacityCharacteristic_roRoLanes);
			mCapacityCharacteristic.getStructuralFeatures().add(mCapacityCharacteristic_liquids);
			mCapacityCharacteristic.getStructuralFeatures().add(mCapacityCharacteristic_ligquidGas);
			mCapacityCharacteristic.getStructuralFeatures().add(mCapacityCharacteristic_oil);
			mVoyageCharacteristic.getStructuralFeatures().add(mVoyageCharacteristic_routes);
			mVoyageCharacteristic.getStructuralFeatures().add(mVoyageCharacteristic_activeRoute);
			mAutopilotConfiguration.getStructuralFeatures().add(mAutopilotConfiguration_angleLimit);
			mAutopilotConfiguration.getStructuralFeatures().add(mAutopilotConfiguration_maxTurnRate);
			mAutopilotConfiguration.getStructuralFeatures().add(mAutopilotConfiguration_minSpeed);
			mAutopilotConfiguration.getStructuralFeatures().add(mAutopilotConfiguration_maxSpeed);
			mAutopilotAlarm.getStructuralFeatures().add(mAutopilotAlarm_message);
			mAutopilotAlarm.getStructuralFeatures().add(mAutopilotAlarm_creationTime);
			mAutopilot.getStructuralFeatures().add(mAutopilot_trajectory);
			mAutopilot.getStructuralFeatures().add(mAutopilot_configuration);
			mAutopilot.getStructuralFeatures().add(mAutopilot_alarms);
			mAutopilot.getStructuralFeatures().add(mAutopilot_lastCommand);
			mAutopilot.getStructuralFeatures().add(mAutopilot_segment);
			mStandingCommands.getStructuralFeatures().add(mStandingCommands_commands);
			mCommandedValue.getStructuralFeatures().add(mCommandedValue_creationTime);
			mCommandedValue.getStructuralFeatures().add(mCommandedValue_source);
			mSpeedCommand.getStructuralFeatures().add(mSpeedCommand_speed);
			mHeadingCommand.getStructuralFeatures().add(mHeadingCommand_heading);
			mCommandedRudderAngle.getStructuralFeatures().add(mCommandedRudderAngle_angle);
			mCommandedEngineRpm.getStructuralFeatures().add(mCommandedEngineRpm_rpm);
			mCommandedPropellerPitch.getStructuralFeatures().add(mCommandedPropellerPitch_pitch);
		}
		
		UMetaBuilder.manual().addLiteral(mVesselType, "DryCargo", 0, VesselType.DryCargo);
		UMetaBuilder.manual().addLiteral(mVesselType, "DeckCargo", 1, VesselType.DeckCargo);
		UMetaBuilder.manual().addLiteral(mVesselType, "GeneralCargo", 2, VesselType.GeneralCargo);
		UMetaBuilder.manual().addLiteral(mVesselType, "HeavyLiftShip", 3, VesselType.HeavyLiftShip);
		UMetaBuilder.manual().addLiteral(mVesselType, "LivestockCarrier", 4, VesselType.LivestockCarrier);
		UMetaBuilder.manual().addLiteral(mVesselType, "BulkCarrier", 5, VesselType.BulkCarrier);
		UMetaBuilder.manual().addLiteral(mVesselType, "OpenHatch", 6, VesselType.OpenHatch);
		UMetaBuilder.manual().addLiteral(mVesselType, "OreCarrier", 7, VesselType.OreCarrier);
		UMetaBuilder.manual().addLiteral(mVesselType, "OreOilCarrier", 8, VesselType.OreOilCarrier);
		UMetaBuilder.manual().addLiteral(mVesselType, "WoodChipCarrier", 9, VesselType.WoodChipCarrier);
		UMetaBuilder.manual().addLiteral(mVesselType, "Container", 10, VesselType.Container);
		UMetaBuilder.manual().addLiteral(mVesselType, "RoRo", 11, VesselType.RoRo);
		UMetaBuilder.manual().addLiteral(mVesselType, "VehicleCarrier", 12, VesselType.VehicleCarrier);
		UMetaBuilder.manual().addLiteral(mVesselType, "Tanker", 13, VesselType.Tanker);
		UMetaBuilder.manual().addLiteral(mVesselType, "AsphaltTanker", 14, VesselType.AsphaltTanker);
		UMetaBuilder.manual().addLiteral(mVesselType, "ChemicalOilTanker", 15, VesselType.ChemicalOilTanker);
		UMetaBuilder.manual().addLiteral(mVesselType, "CrudeOilTanker", 16, VesselType.CrudeOilTanker);
		UMetaBuilder.manual().addLiteral(mVesselType, "LNGTanker", 17, VesselType.LNGTanker);
		UMetaBuilder.manual().addLiteral(mVesselType, "LPGTanker", 18, VesselType.LPGTanker);
		UMetaBuilder.manual().addLiteral(mVesselType, "WaterTanker", 19, VesselType.WaterTanker);
		UMetaBuilder.manual().addLiteral(mVesselType, "Reefer", 20, VesselType.Reefer);
		UMetaBuilder.manual().addLiteral(mVesselType, "RefrigeratedFishCarrier", 21, VesselType.RefrigeratedFishCarrier);
		UMetaBuilder.manual().addLiteral(mVesselType, "Passenger", 22, VesselType.Passenger);
		UMetaBuilder.manual().addLiteral(mVesselType, "CruiseShip", 23, VesselType.CruiseShip);
		UMetaBuilder.manual().addLiteral(mVesselType, "PassengerCargoShip", 24, VesselType.PassengerCargoShip);
		UMetaBuilder.manual().addLiteral(mVesselType, "PassengerFerry", 25, VesselType.PassengerFerry);
		UMetaBuilder.manual().addLiteral(mVesselType, "HighSpeedCraft", 26, VesselType.HighSpeedCraft);
		UMetaBuilder.manual().addLiteral(mVesselType, "WinginGround", 27, VesselType.WinginGround);
		UMetaBuilder.manual().addLiteral(mVesselType, "Other", 28, VesselType.Other);
		UMetaBuilder.manual().addLiteral(mVesselType, "AntiPollution", 29, VesselType.AntiPollution);
		UMetaBuilder.manual().addLiteral(mVesselType, "Barge", 30, VesselType.Barge);
		UMetaBuilder.manual().addLiteral(mVesselType, "CableShip", 31, VesselType.CableShip);
		UMetaBuilder.manual().addLiteral(mVesselType, "Crewboat", 32, VesselType.Crewboat);
		UMetaBuilder.manual().addLiteral(mVesselType, "DivingShip", 33, VesselType.DivingShip);
		UMetaBuilder.manual().addLiteral(mVesselType, "Dredger", 34, VesselType.Dredger);
		UMetaBuilder.manual().addLiteral(mVesselType, "Drillship", 35, VesselType.Drillship);
		UMetaBuilder.manual().addLiteral(mVesselType, "Fishing", 36, VesselType.Fishing);
		UMetaBuilder.manual().addLiteral(mVesselType, "FloatingDock", 37, VesselType.FloatingDock);
		UMetaBuilder.manual().addLiteral(mVesselType, "Icebreaker", 38, VesselType.Icebreaker);
		UMetaBuilder.manual().addLiteral(mVesselType, "NavalMilitaryShip", 39, VesselType.NavalMilitaryShip);
		UMetaBuilder.manual().addLiteral(mVesselType, "NavigationAid", 40, VesselType.NavigationAid);
		UMetaBuilder.manual().addLiteral(mVesselType, "OceanographicResearch", 41, VesselType.OceanographicResearch);
		UMetaBuilder.manual().addLiteral(mVesselType, "OffshoreConstruction", 42, VesselType.OffshoreConstruction);
		UMetaBuilder.manual().addLiteral(mVesselType, "OilWellShip", 43, VesselType.OilWellShip);
		UMetaBuilder.manual().addLiteral(mVesselType, "PatrolVessel", 44, VesselType.PatrolVessel);
		UMetaBuilder.manual().addLiteral(mVesselType, "PilotShip", 45, VesselType.PilotShip);
		UMetaBuilder.manual().addLiteral(mVesselType, "PipeCarrierPipelay", 46, VesselType.PipeCarrierPipelay);
		UMetaBuilder.manual().addLiteral(mVesselType, "Pontoon", 47, VesselType.Pontoon);
		UMetaBuilder.manual().addLiteral(mVesselType, "PortPoliceLawEnforce", 48, VesselType.PortPoliceLawEnforce);
		UMetaBuilder.manual().addLiteral(mVesselType, "RescueSalvageShip", 49, VesselType.RescueSalvageShip);
		UMetaBuilder.manual().addLiteral(mVesselType, "SailingVessel", 50, VesselType.SailingVessel);
		UMetaBuilder.manual().addLiteral(mVesselType, "SupplyShip", 51, VesselType.SupplyShip);
		UMetaBuilder.manual().addLiteral(mVesselType, "TrainingShip", 52, VesselType.TrainingShip);
		UMetaBuilder.manual().addLiteral(mVesselType, "Tug", 53, VesselType.Tug);
		UMetaBuilder.manual().addLiteral(mVesselType, "YachtPleasureCraft", 54, VesselType.YachtPleasureCraft);
		UMetaBuilder.manual().addLiteral(mNavigationStatus, "UnderwayUsingEngine", 0, NavigationStatus.UnderwayUsingEngine);
		UMetaBuilder.manual().addLiteral(mNavigationStatus, "AtAnchor", 1, NavigationStatus.AtAnchor);
		UMetaBuilder.manual().addLiteral(mNavigationStatus, "NotUnderCommand", 2, NavigationStatus.NotUnderCommand);
		UMetaBuilder.manual().addLiteral(mNavigationStatus, "RestrictedManoeuverability", 3, NavigationStatus.RestrictedManoeuverability);
		UMetaBuilder.manual().addLiteral(mNavigationStatus, "ConstrainedByHerDraught", 4, NavigationStatus.ConstrainedByHerDraught);
		UMetaBuilder.manual().addLiteral(mNavigationStatus, "Moored", 5, NavigationStatus.Moored);
		UMetaBuilder.manual().addLiteral(mNavigationStatus, "Aground", 6, NavigationStatus.Aground);
		UMetaBuilder.manual().addLiteral(mNavigationStatus, "EngagedInFising", 7, NavigationStatus.EngagedInFising);
		UMetaBuilder.manual().addLiteral(mNavigationStatus, "UnderwaySailing", 8, NavigationStatus.UnderwaySailing);
		UMetaBuilder.manual().addLiteral(mNavigationStatus, "ReservedForFutureUse9", 9, NavigationStatus.ReservedForFutureUse9);
		UMetaBuilder.manual().addLiteral(mNavigationStatus, "ReservedForFutureUse10", 10, NavigationStatus.ReservedForFutureUse10);
		UMetaBuilder.manual().addLiteral(mNavigationStatus, "ReservedForFutureUse11", 11, NavigationStatus.ReservedForFutureUse11);
		UMetaBuilder.manual().addLiteral(mNavigationStatus, "ReservedForFutureUse12", 12, NavigationStatus.ReservedForFutureUse12);
		UMetaBuilder.manual().addLiteral(mNavigationStatus, "ReservedForFutureUse13", 13, NavigationStatus.ReservedForFutureUse13);
		UMetaBuilder.manual().addLiteral(mNavigationStatus, "ReservedForFutureUse14", 14, NavigationStatus.ReservedForFutureUse14);
		UMetaBuilder.manual().addLiteral(mNavigationStatus, "NotDefined", 15, NavigationStatus.NotDefined);
	}
	/**
	* @generated
	*/
	public UClass getVesselDimensionCharacteristic(){
		if (mVesselDimensionCharacteristic == null){
			mVesselDimensionCharacteristic = UCoreMetaRepository.getUClass(VesselDimensionCharacteristic.class);
		}
		return mVesselDimensionCharacteristic;
	}
	/**
	* @generated
	*/
	public UClass getEngineBuildInformation(){
		if (mEngineBuildInformation == null){
			mEngineBuildInformation = UCoreMetaRepository.getUClass(EngineBuildInformation.class);
		}
		return mEngineBuildInformation;
	}
	/**
	* @generated
	*/
	public UClass getEngine(){
		if (mEngine == null){
			mEngine = UCoreMetaRepository.getUClass(Engine.class);
		}
		return mEngine;
	}
	/**
	* @generated
	*/
	public UClass getRudder(){
		if (mRudder == null){
			mRudder = UCoreMetaRepository.getUClass(Rudder.class);
		}
		return mRudder;
	}
	/**
	* @generated
	*/
	public UClass getPropulsionSystem(){
		if (mPropulsionSystem == null){
			mPropulsionSystem = UCoreMetaRepository.getUClass(PropulsionSystem.class);
		}
		return mPropulsionSystem;
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mNavigationInformation.setSuperType(VesselPackage.theInstance.getVesselCharacteristic());
		mVessel.setSuperType(VehiclePackage.theInstance.getWatercraft());
		mVesselCharacteristic.setSuperType(PhysicsPackage.theInstance.getCharacteristic());
		mVesselBuildInformation.setSuperType(VesselPackage.theInstance.getVesselCharacteristic());
		mVesselDimensionCharacteristic.setSuperType(PhysicsPackage.theInstance.getMultiViewObjectSurfaceInforamtion());
		mVesselSafetyCharacteristic.setSuperType(VesselPackage.theInstance.getVesselCharacteristic());
		mEngine.setSuperType(PhysicsPackage.theInstance.getPhysicalObject());
		mInternalCombustionEngine.setSuperType(VesselPackage.theInstance.getEngine());
		mPropeller.setSuperType(PhysicsPackage.theInstance.getPhysicalObject());
		mRudder.setSuperType(PhysicsPackage.theInstance.getPhysicalObject());
		mDynamicSystem.setSuperType(VesselPackage.theInstance.getVesselCharacteristic());
		mCapacityCharacteristic.setSuperType(VesselPackage.theInstance.getVesselCharacteristic());
		mVoyageCharacteristic.setSuperType(VesselPackage.theInstance.getVesselCharacteristic());
		mAutopilot.setSuperType(VesselPackage.theInstance.getVesselCharacteristic());
		mStandingCommands.setSuperType(VesselPackage.theInstance.getVesselCharacteristic());
		mSteeringCommand.setSuperType(VesselPackage.theInstance.getCommandedValue());
		mSpeedCommand.setSuperType(VesselPackage.theInstance.getSteeringCommand());
		mHeadingCommand.setSuperType(VesselPackage.theInstance.getSteeringCommand());
		mMachineCommand.setSuperType(VesselPackage.theInstance.getCommandedValue());
		mCommandedRudderAngle.setSuperType(VesselPackage.theInstance.getMachineCommand());
		mCommandedEngineRpm.setSuperType(VesselPackage.theInstance.getMachineCommand());
		mCommandedPropellerPitch.setSuperType(VesselPackage.theInstance.getMachineCommand());
		
	}
    /**
	* @generated
	*/
	public UClass getControlSurfaces(){
		if (mControlSurfaces == null){
			mControlSurfaces = UCoreMetaRepository.getUClass(ControlSurfaces.class);
		}
		return mControlSurfaces;
	}
    /**
	* @generated
	*/
	public UClass getVesselSafetyCharacteristic(){
		if (mVesselSafetyCharacteristic == null){
			mVesselSafetyCharacteristic = UCoreMetaRepository.getUClass(VesselSafetyCharacteristic.class);
		}
		return mVesselSafetyCharacteristic;
	}
	/**
	 * @generated
	 */
	private UStructuralFeature mCommandedEngineRpm_rpm = null;
	/**
	* @generated
	*/
	public UClass getDynamicSystem(){
		if (mDynamicSystem == null){
			mDynamicSystem = UCoreMetaRepository.getUClass(DynamicSystem.class);
		}
		return mDynamicSystem;
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mVesselType = UMetaBuilder.manual().createEnumeration("VesselType", VesselType.class);
		
		mNavigationStatus = UMetaBuilder.manual().createEnumeration("NavigationStatus", NavigationStatus.class);
		
		mNavigationInformation = UMetaBuilder.manual().createClass("NavigationInformation", false, NavigationInformation.class, NavigationInformationImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mNavigationInformation, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new NavigationInformationImpl();
				}
			});
			//Annotations for NavigationInformation
			mNavigationInformation.createAnnotation("InformationType");
		
		mVessel = UMetaBuilder.manual().createClass("Vessel", false, Vessel.class, VesselImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mVessel, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new VesselImpl();
				}
			});
			mVessel.setDocumentation("\r\n *  a nautical term for all kinds of craft designed for transportation on water, such as ships or boats.\r\n");
			//Annotations for Vessel
			mVessel.createAnnotation("FeatureType");
		
		mVesselCharacteristic = UMetaBuilder.manual().createClass("VesselCharacteristic", true, VesselCharacteristic.class, VesselCharacteristicImpl.class);
		
		mVesselBuildInformation = UMetaBuilder.manual().createClass("VesselBuildInformation", false, VesselBuildInformation.class, VesselBuildInformationImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mVesselBuildInformation, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new VesselBuildInformationImpl();
				}
			});
			//Annotations for VesselBuildInformation
			mVesselBuildInformation.createAnnotation("InformationType");
		
		mDisplacement = UMetaBuilder.manual().createClass("Displacement", false, Displacement.class, DisplacementImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mDisplacement, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new DisplacementImpl();
				}
			});
			mDisplacement.setDocumentation(" \r\n * A measurement of the weight of the vessel, usually used for warships. \r\n * Merchant ships are usually measured based on the volume of cargo space; see tonnage. \r\n ");
			//Annotations for Displacement
			mDisplacement.createAnnotation("struct");
		
		mWatercraftHull = UMetaBuilder.manual().createClass("WatercraftHull", false, WatercraftHull.class, WatercraftHullImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mWatercraftHull, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new WatercraftHullImpl();
				}
			});
			//Annotations for WatercraftHull
			mWatercraftHull.createAnnotation("struct");
		
		mVesselDimensionCharacteristic = UMetaBuilder.manual().createClass("VesselDimensionCharacteristic", false, VesselDimensionCharacteristic.class, VesselDimensionCharacteristicImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mVesselDimensionCharacteristic, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new VesselDimensionCharacteristicImpl();
				}
			});
			//Annotations for VesselDimensionCharacteristic
			mVesselDimensionCharacteristic.createAnnotation("struct");
		
		mVesselSafetyCharacteristic = UMetaBuilder.manual().createClass("VesselSafetyCharacteristic", false, VesselSafetyCharacteristic.class, VesselSafetyCharacteristicImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mVesselSafetyCharacteristic, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new VesselSafetyCharacteristicImpl();
				}
			});
			//Annotations for VesselSafetyCharacteristic
			mVesselSafetyCharacteristic.createAnnotation("struct");
		
		mEngineBuildInformation = UMetaBuilder.manual().createClass("EngineBuildInformation", false, EngineBuildInformation.class, EngineBuildInformationImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mEngineBuildInformation, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new EngineBuildInformationImpl();
				}
			});
			//Annotations for EngineBuildInformation
			mEngineBuildInformation.createAnnotation("InformationType");
			mEngineBuildInformation.createAnnotation("struct");
		
		mEngine = UMetaBuilder.manual().createClass("Engine", false, Engine.class, EngineImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mEngine, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new EngineImpl();
				}
			});
		
		mInternalCombustionEngine = UMetaBuilder.manual().createClass("InternalCombustionEngine", false, InternalCombustionEngine.class, InternalCombustionEngineImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mInternalCombustionEngine, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new InternalCombustionEngineImpl();
				}
			});
			mInternalCombustionEngine.setDocumentation("\r\n * An engine that converts chemical energy to mechanical energy\r\n ");
		
		mPropeller = UMetaBuilder.manual().createClass("Propeller", false, Propeller.class, PropellerImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mPropeller, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new PropellerImpl();
				}
			});
			mPropeller.setDocumentation("\r\n * A propeller of a ship, used for converting mechanical energy to kinetic energy\r\n ");
		
		mRudder = UMetaBuilder.manual().createClass("Rudder", false, Rudder.class, RudderImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mRudder, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new RudderImpl();
				}
			});
		
		mPropulsionSystem = UMetaBuilder.manual().createClass("PropulsionSystem", false, PropulsionSystem.class, PropulsionSystemImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mPropulsionSystem, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new PropulsionSystemImpl();
				}
			});
			mPropulsionSystem.setDocumentation("\r\n * Propulsion is a means of creating force leading to movement. \r\n * \r\n * A propulsion system consists of a source of mechanical power, and a propulsor (means of converting this power into propulsive force).\r\n * A technological system uses an engine or motor as the power source, and wheels and axles, propellers, or a propulsive nozzle to generate the force. \r\n * Components such as clutches or gearboxes may be needed to connect the motor to axles, wheels, or propellors.\r\n * \r\n * \\source wikipedia\r\n ");
		
		mControlSurfaces = UMetaBuilder.manual().createClass("ControlSurfaces", false, ControlSurfaces.class, ControlSurfacesImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mControlSurfaces, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ControlSurfacesImpl();
				}
			});
			mControlSurfaces.setDocumentation("\r\n * These forces and moments arise due to the Control Surfaces (CS) like rudder, fins, etc. movement\r\n * \r\n * \\source Mathematical Ship Modeling for Control Applications, 2002, Perez, Blanket\r\n ");
		
		mDynamicSystem = UMetaBuilder.manual().createClass("DynamicSystem", false, DynamicSystem.class, DynamicSystemImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mDynamicSystem, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new DynamicSystemImpl();
				}
			});
			mDynamicSystem.setDocumentation("\r\n * \\source Mathematical Ship Modeling for Control Applications, 2002, Perez, Blanket\r\n ");
		
		mCapacityCharacteristic = UMetaBuilder.manual().createClass("CapacityCharacteristic", false, CapacityCharacteristic.class, CapacityCharacteristicImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mCapacityCharacteristic, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new CapacityCharacteristicImpl();
				}
			});
			//Annotations for CapacityCharacteristic
			mCapacityCharacteristic.createAnnotation("ComplexAttributeType");
		
		mVoyageCharacteristic = UMetaBuilder.manual().createClass("VoyageCharacteristic", false, VoyageCharacteristic.class, VoyageCharacteristicImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mVoyageCharacteristic, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new VoyageCharacteristicImpl();
				}
			});
		
		mAutopilotConfiguration = UMetaBuilder.manual().createClass("AutopilotConfiguration", false, AutopilotConfiguration.class, AutopilotConfigurationImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mAutopilotConfiguration, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new AutopilotConfigurationImpl();
				}
			});
			//Annotations for AutopilotConfiguration
			mAutopilotConfiguration.createAnnotation("struct");
		
		mAutopilotAlarm = UMetaBuilder.manual().createClass("AutopilotAlarm", false, AutopilotAlarm.class, AutopilotAlarmImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mAutopilotAlarm, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new AutopilotAlarmImpl();
				}
			});
			//Annotations for AutopilotAlarm
			mAutopilotAlarm.createAnnotation("struct");
		
		mAutopilot = UMetaBuilder.manual().createClass("Autopilot", false, Autopilot.class, AutopilotImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mAutopilot, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new AutopilotImpl();
				}
			});
		
		mStandingCommands = UMetaBuilder.manual().createClass("StandingCommands", false, StandingCommands.class, StandingCommandsImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mStandingCommands, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new StandingCommandsImpl();
				}
			});
			mStandingCommands.setDocumentation("\r\n * The StandingCommands characteristic holds all currently valid commands\r\n * which are not related to the machines (e.g. contains no MachineCommands)\r\n * Commands within this characteristic are typically given by an responsible nautical officer. \r\n ");
		
		mCommandedValue = UMetaBuilder.manual().createClass("CommandedValue", true, CommandedValue.class, CommandedValueImpl.class);
			mCommandedValue.setDocumentation("\r\n * Represents a generic value that has been commanded\r\n * (typically by the captain) to be achieved in the future\r\n ");
		
		mSteeringCommand = UMetaBuilder.manual().createClass("SteeringCommand", true, SteeringCommand.class, SteeringCommandImpl.class);
			mSteeringCommand.setDocumentation(" Abstract command, related to steering, like direction / heading / speed\r\n * typically a command that is given and executed by some nautical officer\r\n ");
		
		mSpeedCommand = UMetaBuilder.manual().createClass("SpeedCommand", false, SpeedCommand.class, SpeedCommandImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mSpeedCommand, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new SpeedCommandImpl();
				}
			});
			mSpeedCommand.setDocumentation("\r\n * The commanded speed for the vessel, which is not directly\r\n * depended on any specific engine rpm or propeller pitch\r\n ");
		
		mHeadingCommand = UMetaBuilder.manual().createClass("HeadingCommand", false, HeadingCommand.class, HeadingCommandImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mHeadingCommand, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new HeadingCommandImpl();
				}
			});
			mHeadingCommand.setDocumentation("\r\n * The commanded heading, which is not directly depended\r\n * on any specific rudder angle.\r\n ");
		
		mMachineCommand = UMetaBuilder.manual().createClass("MachineCommand", true, MachineCommand.class, MachineCommandImpl.class);
			mMachineCommand.setDocumentation(" An concrete command for a machine\r\n * typically this commands are read by an machine and produced \r\n * by an controlling instance of the vessel (for example a nautical officer) as result / decomposition of steering commands\r\n ");
		
		mCommandedRudderAngle = UMetaBuilder.manual().createClass("CommandedRudderAngle", false, CommandedRudderAngle.class, CommandedRudderAngleImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mCommandedRudderAngle, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new CommandedRudderAngleImpl();
				}
			});
			mCommandedRudderAngle.setDocumentation("\r\n * A specific command for a rudder angle change. Directed\r\n * to only one rudder.\r\n ");
		
		mCommandedEngineRpm = UMetaBuilder.manual().createClass("CommandedEngineRpm", false, CommandedEngineRpm.class, CommandedEngineRpmImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mCommandedEngineRpm, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new CommandedEngineRpmImpl();
				}
			});
			mCommandedEngineRpm.setDocumentation("\r\n * A command directed to change the rpm of a single engine\r\n ");
		
		mCommandedPropellerPitch = UMetaBuilder.manual().createClass("CommandedPropellerPitch", false, CommandedPropellerPitch.class, CommandedPropellerPitchImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mCommandedPropellerPitch, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new CommandedPropellerPitchImpl();
				}
			});
			mCommandedPropellerPitch.setDocumentation("\r\n * A command directed to change the pitch of a specific\r\n * propeller\r\n ");
		
	}
	/**
	* @generated
	*/
	public UClass getCapacityCharacteristic(){
		if (mCapacityCharacteristic == null){
			mCapacityCharacteristic = UCoreMetaRepository.getUClass(CapacityCharacteristic.class);
		}
		return mCapacityCharacteristic;
	}



	/**
	* @generated
	*/
	public UClass getSpeedCommand(){
		if (mSpeedCommand == null){
			mSpeedCommand = UCoreMetaRepository.getUClass(SpeedCommand.class);
		}
		return mSpeedCommand;
	}
	/**
	* @generated
	*/
	public UClass getVoyageCharacteristic(){
		if (mVoyageCharacteristic == null){
			mVoyageCharacteristic = UCoreMetaRepository.getUClass(VoyageCharacteristic.class);
		}
		return mVoyageCharacteristic;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getNavigationInformation_status(){
		if (mNavigationInformation_status == null)
			mNavigationInformation_status = getNavigationInformation().getFeature("status");
		return mNavigationInformation_status;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVessel_mmsi(){
		if (mVessel_mmsi == null)
			mVessel_mmsi = getVessel().getFeature("mmsi");
		return mVessel_mmsi;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVessel_imo(){
		if (mVessel_imo == null)
			mVessel_imo = getVessel().getFeature("imo");
		return mVessel_imo;
	}
	/**
	* @generated
	*/
	public UClass getStandingCommands(){
		if (mStandingCommands == null){
			mStandingCommands = UCoreMetaRepository.getUClass(StandingCommands.class);
		}
		return mStandingCommands;
	}



	/**
	* @generated
	*/
	public UClass getSteeringCommand(){
		if (mSteeringCommand == null){
			mSteeringCommand = UCoreMetaRepository.getUClass(SteeringCommand.class);
		}
		return mSteeringCommand;
	}



	/**
	* @generated
	*/
	public UClass getHeadingCommand(){
		if (mHeadingCommand == null){
			mHeadingCommand = UCoreMetaRepository.getUClass(HeadingCommand.class);
		}
		return mHeadingCommand;
	}



	/**
	* @generated
	*/
	public UClass getMachineCommand(){
		if (mMachineCommand == null){
			mMachineCommand = UCoreMetaRepository.getUClass(MachineCommand.class);
		}
		return mMachineCommand;
	}
	/**
	* @generated
	*/
	public UClass getAutopilotAlarm(){
		if (mAutopilotAlarm == null){
			mAutopilotAlarm = UCoreMetaRepository.getUClass(AutopilotAlarm.class);
		}
		return mAutopilotAlarm;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVessel_callSign(){
		if (mVessel_callSign == null)
			mVessel_callSign = getVessel().getFeature("callSign");
		return mVessel_callSign;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVessel_type(){
		if (mVessel_type == null)
			mVessel_type = getVessel().getFeature("type");
		return mVessel_type;
	}
	/**
	* @generated
	*/
	public UClass getAutopilotConfiguration(){
		if (mAutopilotConfiguration == null){
			mAutopilotConfiguration = UCoreMetaRepository.getUClass(AutopilotConfiguration.class);
		}
		return mAutopilotConfiguration;
	}



	/**
	* @generated
	*/
	public UClass getInternalCombustionEngine(){
		if (mInternalCombustionEngine == null){
			mInternalCombustionEngine = UCoreMetaRepository.getUClass(InternalCombustionEngine.class);
		}
		return mInternalCombustionEngine;
	}
	/**
	 * @generated
	 */
	private UStructuralFeature mCommandedRudderAngle_angle = null;
	/**
	* @generated
	*/
	public UStructuralFeature getVesselBuildInformation_hullNumber(){
		if (mVesselBuildInformation_hullNumber == null)
			mVesselBuildInformation_hullNumber = getVesselBuildInformation().getFeature("hullNumber");
		return mVesselBuildInformation_hullNumber;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVesselBuildInformation_manufactor(){
		if (mVesselBuildInformation_manufactor == null)
			mVesselBuildInformation_manufactor = getVesselBuildInformation().getFeature("manufactor");
		return mVesselBuildInformation_manufactor;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVesselBuildInformation_buildTime(){
		if (mVesselBuildInformation_buildTime == null)
			mVesselBuildInformation_buildTime = getVesselBuildInformation().getFeature("buildTime");
		return mVesselBuildInformation_buildTime;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getDisplacement_light(){
		if (mDisplacement_light == null)
			mDisplacement_light = getDisplacement().getFeature("light");
		return mDisplacement_light;
	}



	/**
	* @generated
	*/
	public UClass getCommandedValue(){
		if (mCommandedValue == null){
			mCommandedValue = UCoreMetaRepository.getUClass(CommandedValue.class);
		}
		return mCommandedValue;
	}



	/**
	* @generated
	*/
	public UClass getCommandedRudderAngle(){
		if (mCommandedRudderAngle == null){
			mCommandedRudderAngle = UCoreMetaRepository.getUClass(CommandedRudderAngle.class);
		}
		return mCommandedRudderAngle;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getDisplacement_loaded(){
		if (mDisplacement_loaded == null)
			mDisplacement_loaded = getDisplacement().getFeature("loaded");
		return mDisplacement_loaded;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getDisplacement_deadweight(){
		if (mDisplacement_deadweight == null)
			mDisplacement_deadweight = getDisplacement().getFeature("deadweight");
		return mDisplacement_deadweight;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getDisplacement_cargoDeadweigthTons(){
		if (mDisplacement_cargoDeadweigthTons == null)
			mDisplacement_cargoDeadweigthTons = getDisplacement().getFeature("cargoDeadweigthTons");
		return mDisplacement_cargoDeadweigthTons;
	}
	/**
	* @generated
	*/
	public UClass getAutopilot(){
		if (mAutopilot == null){
			mAutopilot = UCoreMetaRepository.getUClass(Autopilot.class);
		}
		return mAutopilot;
	}



	/**
	* @generated
	*/
	public UClass getPropeller(){
		if (mPropeller == null){
			mPropeller = UCoreMetaRepository.getUClass(Propeller.class);
		}
		return mPropeller;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getWatercraftHull_beam(){
		if (mWatercraftHull_beam == null)
			mWatercraftHull_beam = getWatercraftHull().getFeature("beam");
		return mWatercraftHull_beam;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getWatercraftHull_lengthAtWaterline(){
		if (mWatercraftHull_lengthAtWaterline == null)
			mWatercraftHull_lengthAtWaterline = getWatercraftHull().getFeature("lengthAtWaterline");
		return mWatercraftHull_lengthAtWaterline;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getWatercraftHull_overAllLength(){
		if (mWatercraftHull_overAllLength == null)
			mWatercraftHull_overAllLength = getWatercraftHull().getFeature("overAllLength");
		return mWatercraftHull_overAllLength;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getWatercraftHull_draft(){
		if (mWatercraftHull_draft == null)
			mWatercraftHull_draft = getWatercraftHull().getFeature("draft");
		return mWatercraftHull_draft;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getWatercraftHull_mouldedDepth(){
		if (mWatercraftHull_mouldedDepth == null)
			mWatercraftHull_mouldedDepth = getWatercraftHull().getFeature("mouldedDepth");
		return mWatercraftHull_mouldedDepth;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getWatercraftHull_freeboard(){
		if (mWatercraftHull_freeboard == null)
			mWatercraftHull_freeboard = getWatercraftHull().getFeature("freeboard");
		return mWatercraftHull_freeboard;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVesselDimensionCharacteristic_neadTonnage(){
		if (mVesselDimensionCharacteristic_neadTonnage == null)
			mVesselDimensionCharacteristic_neadTonnage = getVesselDimensionCharacteristic().getFeature("neadTonnage");
		return mVesselDimensionCharacteristic_neadTonnage;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getVesselDimensionCharacteristic_blockCoefficient(){
		if (mVesselDimensionCharacteristic_blockCoefficient == null)
			mVesselDimensionCharacteristic_blockCoefficient = getVesselDimensionCharacteristic().getFeature("blockCoefficient");
		return mVesselDimensionCharacteristic_blockCoefficient;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVesselDimensionCharacteristic_displacement(){
		if (mVesselDimensionCharacteristic_displacement == null)
			mVesselDimensionCharacteristic_displacement = getVesselDimensionCharacteristic().getFeature("displacement");
		return mVesselDimensionCharacteristic_displacement;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVesselDimensionCharacteristic_hull(){
		if (mVesselDimensionCharacteristic_hull == null)
			mVesselDimensionCharacteristic_hull = getVesselDimensionCharacteristic().getFeature("hull");
		return mVesselDimensionCharacteristic_hull;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEngineBuildInformation_model(){
		if (mEngineBuildInformation_model == null)
			mEngineBuildInformation_model = getEngineBuildInformation().getFeature("model");
		return mEngineBuildInformation_model;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEngineBuildInformation_manufactor(){
		if (mEngineBuildInformation_manufactor == null)
			mEngineBuildInformation_manufactor = getEngineBuildInformation().getFeature("manufactor");
		return mEngineBuildInformation_manufactor;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEngineBuildInformation_buildYear(){
		if (mEngineBuildInformation_buildYear == null)
			mEngineBuildInformation_buildYear = getEngineBuildInformation().getFeature("buildYear");
		return mEngineBuildInformation_buildYear;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEngine_buildInformation(){
		if (mEngine_buildInformation == null)
			mEngine_buildInformation = getEngine().getFeature("buildInformation");
		return mEngine_buildInformation;
	}
    /**
	* @generated
	*/
	public UStructuralFeature getVesselSafetyCharacteristic_personalSpace(){
		if (mVesselSafetyCharacteristic_personalSpace == null)
			mVesselSafetyCharacteristic_personalSpace = getVesselSafetyCharacteristic().getFeature("personalSpace");
		return mVesselSafetyCharacteristic_personalSpace;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEngine_acceleration(){
		if (mEngine_acceleration == null)
			mEngine_acceleration = getEngine().getFeature("acceleration");
		return mEngine_acceleration;
	}



	/**
	* @generated
	*/
	public UClass getCommandedPropellerPitch(){
		if (mCommandedPropellerPitch == null){
			mCommandedPropellerPitch = UCoreMetaRepository.getUClass(CommandedPropellerPitch.class);
		}
		return mCommandedPropellerPitch;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getRudder_rateOfTurn(){
		if (mRudder_rateOfTurn == null)
			mRudder_rateOfTurn = getRudder().getFeature("rateOfTurn");
		return mRudder_rateOfTurn;
	}
    /**
	* @generated
	*/
	public UStructuralFeature getVesselSafetyCharacteristic_underKeelClearance(){
		if (mVesselSafetyCharacteristic_underKeelClearance == null)
			mVesselSafetyCharacteristic_underKeelClearance = getVesselSafetyCharacteristic().getFeature("underKeelClearance");
		return mVesselSafetyCharacteristic_underKeelClearance;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getPropulsionSystem_maximumVelocity(){
		if (mPropulsionSystem_maximumVelocity == null)
			mPropulsionSystem_maximumVelocity = getPropulsionSystem().getFeature("maximumVelocity");
		return mPropulsionSystem_maximumVelocity;
	}



	/**
	* @generated
	*/
	public UClass getCommandedEngineRpm(){
		if (mCommandedEngineRpm == null){
			mCommandedEngineRpm = UCoreMetaRepository.getUClass(CommandedEngineRpm.class);
		}
		return mCommandedEngineRpm;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getPropulsionSystem_minimumVelocity(){
		if (mPropulsionSystem_minimumVelocity == null)
			mPropulsionSystem_minimumVelocity = getPropulsionSystem().getFeature("minimumVelocity");
		return mPropulsionSystem_minimumVelocity;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getPropulsionSystem_maximumAcceleration(){
		if (mPropulsionSystem_maximumAcceleration == null)
			mPropulsionSystem_maximumAcceleration = getPropulsionSystem().getFeature("maximumAcceleration");
		return mPropulsionSystem_maximumAcceleration;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getInternalCombustionEngine_maximumRpm(){
		if (mInternalCombustionEngine_maximumRpm == null)
			mInternalCombustionEngine_maximumRpm = getInternalCombustionEngine().getFeature("maximumRpm");
		return mInternalCombustionEngine_maximumRpm;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getPropulsionSystem_minimumAcceleration(){
		if (mPropulsionSystem_minimumAcceleration == null)
			mPropulsionSystem_minimumAcceleration = getPropulsionSystem().getFeature("minimumAcceleration");
		return mPropulsionSystem_minimumAcceleration;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getPropulsionSystem_engines(){
		if (mPropulsionSystem_engines == null)
			mPropulsionSystem_engines = getPropulsionSystem().getFeature("engines");
		return mPropulsionSystem_engines;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getControlSurfaces_maximumRateOfTurn(){
		if (mControlSurfaces_maximumRateOfTurn == null)
			mControlSurfaces_maximumRateOfTurn = getControlSurfaces().getFeature("maximumRateOfTurn");
		return mControlSurfaces_maximumRateOfTurn;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getControlSurfaces_rudders(){
		if (mControlSurfaces_rudders == null)
			mControlSurfaces_rudders = getControlSurfaces().getFeature("rudders");
		return mControlSurfaces_rudders;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getDynamicSystem_propulsion(){
		if (mDynamicSystem_propulsion == null)
			mDynamicSystem_propulsion = getDynamicSystem().getFeature("propulsion");
		return mDynamicSystem_propulsion;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getPropeller_pitch(){
		if (mPropeller_pitch == null)
			mPropeller_pitch = getPropeller().getFeature("pitch");
		return mPropeller_pitch;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getDynamicSystem_control(){
		if (mDynamicSystem_control == null)
			mDynamicSystem_control = getDynamicSystem().getFeature("control");
		return mDynamicSystem_control;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCapacityCharacteristic_teu(){
		if (mCapacityCharacteristic_teu == null)
			mCapacityCharacteristic_teu = getCapacityCharacteristic().getFeature("teu");
		return mCapacityCharacteristic_teu;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCapacityCharacteristic_passengers(){
		if (mCapacityCharacteristic_passengers == null)
			mCapacityCharacteristic_passengers = getCapacityCharacteristic().getFeature("passengers");
		return mCapacityCharacteristic_passengers;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCapacityCharacteristic_cars(){
		if (mCapacityCharacteristic_cars == null)
			mCapacityCharacteristic_cars = getCapacityCharacteristic().getFeature("cars");
		return mCapacityCharacteristic_cars;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCapacityCharacteristic_trucks(){
		if (mCapacityCharacteristic_trucks == null)
			mCapacityCharacteristic_trucks = getCapacityCharacteristic().getFeature("trucks");
		return mCapacityCharacteristic_trucks;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getInternalCombustionEngine_rpm(){
		if (mInternalCombustionEngine_rpm == null)
			mInternalCombustionEngine_rpm = getInternalCombustionEngine().getFeature("rpm");
		return mInternalCombustionEngine_rpm;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getCapacityCharacteristic_roRoLanes(){
		if (mCapacityCharacteristic_roRoLanes == null)
			mCapacityCharacteristic_roRoLanes = getCapacityCharacteristic().getFeature("roRoLanes");
		return mCapacityCharacteristic_roRoLanes;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getPropeller_minimumPitch(){
		if (mPropeller_minimumPitch == null)
			mPropeller_minimumPitch = getPropeller().getFeature("minimumPitch");
		return mPropeller_minimumPitch;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCapacityCharacteristic_liquids(){
		if (mCapacityCharacteristic_liquids == null)
			mCapacityCharacteristic_liquids = getCapacityCharacteristic().getFeature("liquids");
		return mCapacityCharacteristic_liquids;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCapacityCharacteristic_ligquidGas(){
		if (mCapacityCharacteristic_ligquidGas == null)
			mCapacityCharacteristic_ligquidGas = getCapacityCharacteristic().getFeature("ligquidGas");
		return mCapacityCharacteristic_ligquidGas;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getPropulsionSystem_propellers(){
		if (mPropulsionSystem_propellers == null)
			mPropulsionSystem_propellers = getPropulsionSystem().getFeature("propellers");
		return mPropulsionSystem_propellers;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getInternalCombustionEngine_commandedRpm(){
		if (mInternalCombustionEngine_commandedRpm == null)
			mInternalCombustionEngine_commandedRpm = getInternalCombustionEngine().getFeature("commandedRpm");
		return mInternalCombustionEngine_commandedRpm;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCapacityCharacteristic_oil(){
		if (mCapacityCharacteristic_oil == null)
			mCapacityCharacteristic_oil = getCapacityCharacteristic().getFeature("oil");
		return mCapacityCharacteristic_oil;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getPropeller_commandedPitch(){
		if (mPropeller_commandedPitch == null)
			mPropeller_commandedPitch = getPropeller().getFeature("commandedPitch");
		return mPropeller_commandedPitch;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getSpeedCommand_speed(){
		if (mSpeedCommand_speed == null)
			mSpeedCommand_speed = getSpeedCommand().getFeature("speed");
		return mSpeedCommand_speed;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getHeadingCommand_heading(){
		if (mHeadingCommand_heading == null)
			mHeadingCommand_heading = getHeadingCommand().getFeature("heading");
		return mHeadingCommand_heading;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getPropeller_maximumPitch(){
		if (mPropeller_maximumPitch == null)
			mPropeller_maximumPitch = getPropeller().getFeature("maximumPitch");
		return mPropeller_maximumPitch;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getRudder_commandedAngle(){
		if (mRudder_commandedAngle == null)
			mRudder_commandedAngle = getRudder().getFeature("commandedAngle");
		return mRudder_commandedAngle;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getEngine_commandedEngineForce(){
		if (mEngine_commandedEngineForce == null)
			mEngine_commandedEngineForce = getEngine().getFeature("commandedEngineForce");
		return mEngine_commandedEngineForce;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getInternalCombustionEngine_minimumRpm(){
		if (mInternalCombustionEngine_minimumRpm == null)
			mInternalCombustionEngine_minimumRpm = getInternalCombustionEngine().getFeature("minimumRpm");
		return mInternalCombustionEngine_minimumRpm;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAutopilot_trajectory(){
		if (mAutopilot_trajectory == null)
			mAutopilot_trajectory = getAutopilot().getFeature("trajectory");
		return mAutopilot_trajectory;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getVoyageCharacteristic_activeRoute(){
		if (mVoyageCharacteristic_activeRoute == null)
			mVoyageCharacteristic_activeRoute = getVoyageCharacteristic().getFeature("activeRoute");
		return mVoyageCharacteristic_activeRoute;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getStandingCommands_commands(){
		if (mStandingCommands_commands == null)
			mStandingCommands_commands = getStandingCommands().getFeature("commands");
		return mStandingCommands_commands;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getRudder_maximumAngle(){
		if (mRudder_maximumAngle == null)
			mRudder_maximumAngle = getRudder().getFeature("maximumAngle");
		return mRudder_maximumAngle;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getVoyageCharacteristic_routes(){
		if (mVoyageCharacteristic_routes == null)
			mVoyageCharacteristic_routes = getVoyageCharacteristic().getFeature("routes");
		return mVoyageCharacteristic_routes;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAutopilot_segment(){
		if (mAutopilot_segment == null)
			mAutopilot_segment = getAutopilot().getFeature("segment");
		return mAutopilot_segment;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getCommandedRudderAngle_angle(){
		if (mCommandedRudderAngle_angle == null)
			mCommandedRudderAngle_angle = getCommandedRudderAngle().getFeature("angle");
		return mCommandedRudderAngle_angle;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getCommandedPropellerPitch_pitch(){
		if (mCommandedPropellerPitch_pitch == null)
			mCommandedPropellerPitch_pitch = getCommandedPropellerPitch().getFeature("pitch");
		return mCommandedPropellerPitch_pitch;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getCommandedEngineRpm_rpm(){
		if (mCommandedEngineRpm_rpm == null)
			mCommandedEngineRpm_rpm = getCommandedEngineRpm().getFeature("rpm");
		return mCommandedEngineRpm_rpm;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAutopilotConfiguration_minSpeed(){
		if (mAutopilotConfiguration_minSpeed == null)
			mAutopilotConfiguration_minSpeed = getAutopilotConfiguration().getFeature("minSpeed");
		return mAutopilotConfiguration_minSpeed;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAutopilot_alarms(){
		if (mAutopilot_alarms == null)
			mAutopilot_alarms = getAutopilot().getFeature("alarms");
		return mAutopilot_alarms;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAutopilotAlarm_message(){
		if (mAutopilotAlarm_message == null)
			mAutopilotAlarm_message = getAutopilotAlarm().getFeature("message");
		return mAutopilotAlarm_message;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAutopilotConfiguration_maxTurnRate(){
		if (mAutopilotConfiguration_maxTurnRate == null)
			mAutopilotConfiguration_maxTurnRate = getAutopilotConfiguration().getFeature("maxTurnRate");
		return mAutopilotConfiguration_maxTurnRate;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAutopilotAlarm_creationTime(){
		if (mAutopilotAlarm_creationTime == null)
			mAutopilotAlarm_creationTime = getAutopilotAlarm().getFeature("creationTime");
		return mAutopilotAlarm_creationTime;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAutopilotConfiguration_maxSpeed(){
		if (mAutopilotConfiguration_maxSpeed == null)
			mAutopilotConfiguration_maxSpeed = getAutopilotConfiguration().getFeature("maxSpeed");
		return mAutopilotConfiguration_maxSpeed;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAutopilot_lastCommand(){
		if (mAutopilot_lastCommand == null)
			mAutopilot_lastCommand = getAutopilot().getFeature("lastCommand");
		return mAutopilot_lastCommand;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAutopilot_configuration(){
		if (mAutopilot_configuration == null)
			mAutopilot_configuration = getAutopilot().getFeature("configuration");
		return mAutopilot_configuration;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCommandedValue_source(){
		if (mCommandedValue_source == null)
			mCommandedValue_source = getCommandedValue().getFeature("source");
		return mCommandedValue_source;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getAutopilotConfiguration_angleLimit(){
		if (mAutopilotConfiguration_angleLimit == null)
			mAutopilotConfiguration_angleLimit = getAutopilotConfiguration().getFeature("angleLimit");
		return mAutopilotConfiguration_angleLimit;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCommandedValue_creationTime(){
		if (mCommandedValue_creationTime == null)
			mCommandedValue_creationTime = getCommandedValue().getFeature("creationTime");
		return mCommandedValue_creationTime;
	}
}
