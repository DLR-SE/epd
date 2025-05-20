package de.emir.model.domain.maritime.iec61174;

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

import de.emir.model.domain.maritime.iec61174.DefaultWayPoint;
import de.emir.model.domain.maritime.iec61174.Extension;
import de.emir.model.domain.maritime.iec61174.IECElementWithExtension;
import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.model.domain.maritime.iec61174.Leg;
import de.emir.model.domain.maritime.iec61174.LegGeometryType;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.RouteSchedule;
import de.emir.model.domain.maritime.iec61174.WayPoints;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.iec61174.impl.DefaultWayPointImpl;
import de.emir.model.domain.maritime.iec61174.impl.ExtensionImpl;
import de.emir.model.domain.maritime.iec61174.impl.IECElementWithExtensionImpl;
import de.emir.model.domain.maritime.iec61174.impl.LegImpl;
import de.emir.model.domain.maritime.iec61174.impl.RouteImpl;
import de.emir.model.domain.maritime.iec61174.impl.RouteScheduleImpl;
import de.emir.model.domain.maritime.iec61174.impl.WayPointsImpl;
import de.emir.model.domain.maritime.iec61174.impl.WaypointImpl;
import de.emir.model.universal.SpatialModel;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Length;
import de.emir.model.universal.units.Speed;
import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;

/**
 *	@generated 
 */
public class Iec61174Package  
{
	
	/**
	 * @generated
	 */
	public static Iec61174Package theInstance = new Iec61174Package().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier Extension
		*/
		UClass Extension = Iec61174Package.theInstance.getExtension();
		/**
		* @generated
		* @return meta type for classifier IECElementWithExtension
		*/
		UClass IECElementWithExtension = Iec61174Package.theInstance.getIECElementWithExtension();
		/**
		* @generated
		* @return meta type for classifier WayPoints
		*/
		UClass WayPoints = Iec61174Package.theInstance.getWayPoints();
		/**
		* @generated
		* @return meta type for classifier Waypoint
		*/
		UClass Waypoint = Iec61174Package.theInstance.getWaypoint();
		/**
		* @generated
		* @return meta type for enumeration LegGeometryType
		*/
		UEnum LegGeometryType = Iec61174Package.theInstance.getLegGeometryType();
		/**
		* @generated
		* @return meta type for classifier Leg
		*/
		UClass Leg = Iec61174Package.theInstance.getLeg();
		/**
		* @generated
		* @return meta type for classifier DefaultWayPoint
		*/
		UClass DefaultWayPoint = Iec61174Package.theInstance.getDefaultWayPoint();
		/**
		* @generated
		* @return meta type for classifier Route
		*/
		UClass Route = Iec61174Package.theInstance.getRoute();
		/**
		* @generated
		* @return meta type for classifier RouteSchedule
		*/
		UClass RouteSchedule = Iec61174Package.theInstance.getRouteSchedule();
		
		/**
		 * @generated
		 * @return feature descriptor manufactor in type Extension
		 */
		 UStructuralFeature Extension_manufactor = Iec61174Package.theInstance.getExtension_manufactor();
		/**
		 * @generated
		 * @return feature descriptor name in type Extension
		 */
		 UStructuralFeature Extension_name = Iec61174Package.theInstance.getExtension_name();
		/**
		 * @generated
		 * @return feature descriptor version in type Extension
		 */
		 UStructuralFeature Extension_version = Iec61174Package.theInstance.getExtension_version();
		/**
		 * @generated
		 * @return feature descriptor extensions in type IECElementWithExtension
		 */
		 UStructuralFeature IECElementWithExtension_extensions = Iec61174Package.theInstance.getIECElementWithExtension_extensions();
		/**
		 * @generated
		 * @return feature descriptor defaultWaypoint in type WayPoints
		 */
		 UStructuralFeature WayPoints_defaultWaypoint = Iec61174Package.theInstance.getWayPoints_defaultWaypoint();
		/**
		 * @generated
		 * @return feature descriptor waypoints in type WayPoints
		 */
		 UStructuralFeature WayPoints_waypoints = Iec61174Package.theInstance.getWayPoints_waypoints();
		/**
		 * @generated
		 * @return feature descriptor id in type Waypoint
		 */
		 UStructuralFeature Waypoint_id = Iec61174Package.theInstance.getWaypoint_id();
		/**
		 * @generated
		 * @return feature descriptor name in type Waypoint
		 */
		 UStructuralFeature Waypoint_name = Iec61174Package.theInstance.getWaypoint_name();
		/**
		 * @generated
		 * @return feature descriptor radius in type Waypoint
		 */
		 UStructuralFeature Waypoint_radius = Iec61174Package.theInstance.getWaypoint_radius();
		/**
		 * @generated
		 * @return feature descriptor revision in type Waypoint
		 */
		 UStructuralFeature Waypoint_revision = Iec61174Package.theInstance.getWaypoint_revision();
		/**
		 * @generated
		 * @return feature descriptor position in type Waypoint
		 */
		 UStructuralFeature Waypoint_position = Iec61174Package.theInstance.getWaypoint_position();
		/**
		 * @generated
		 * @return feature descriptor leg in type Waypoint
		 */
		 UStructuralFeature Waypoint_leg = Iec61174Package.theInstance.getWaypoint_leg();
		/**
		 * @generated
		 * @return feature descriptor starboardXTD in type Leg
		 */
		 UStructuralFeature Leg_starboardXTD = Iec61174Package.theInstance.getLeg_starboardXTD();
		/**
		 * @generated
		 * @return feature descriptor portsideXTD in type Leg
		 */
		 UStructuralFeature Leg_portsideXTD = Iec61174Package.theInstance.getLeg_portsideXTD();
		/**
		 * @generated
		 * @return feature descriptor safetyContour in type Leg
		 */
		 UStructuralFeature Leg_safetyContour = Iec61174Package.theInstance.getLeg_safetyContour();
		/**
		 * @generated
		 * @return feature descriptor safetyDepth in type Leg
		 */
		 UStructuralFeature Leg_safetyDepth = Iec61174Package.theInstance.getLeg_safetyDepth();
		/**
		 * @generated
		 * @return feature descriptor geometryType in type Leg
		 */
		 UStructuralFeature Leg_geometryType = Iec61174Package.theInstance.getLeg_geometryType();
		/**
		 * @generated
		 * @return feature descriptor planSpeedMin in type Leg
		 */
		 UStructuralFeature Leg_planSpeedMin = Iec61174Package.theInstance.getLeg_planSpeedMin();
		/**
		 * @generated
		 * @return feature descriptor planSpeedMax in type Leg
		 */
		 UStructuralFeature Leg_planSpeedMax = Iec61174Package.theInstance.getLeg_planSpeedMax();
		/**
		 * @generated
		 * @return feature descriptor draughtForward in type Leg
		 */
		 UStructuralFeature Leg_draughtForward = Iec61174Package.theInstance.getLeg_draughtForward();
		/**
		 * @generated
		 * @return feature descriptor draughtAft in type Leg
		 */
		 UStructuralFeature Leg_draughtAft = Iec61174Package.theInstance.getLeg_draughtAft();
		/**
		 * @generated
		 * @return feature descriptor legInfo in type Leg
		 */
		 UStructuralFeature Leg_legInfo = Iec61174Package.theInstance.getLeg_legInfo();
		/**
		 * @generated
		 * @return feature descriptor legNote1 in type Leg
		 */
		 UStructuralFeature Leg_legNote1 = Iec61174Package.theInstance.getLeg_legNote1();
		/**
		 * @generated
		 * @return feature descriptor legNote2 in type Leg
		 */
		 UStructuralFeature Leg_legNote2 = Iec61174Package.theInstance.getLeg_legNote2();
		/**
		 * @generated
		 * @return feature descriptor radius in type DefaultWayPoint
		 */
		 UStructuralFeature DefaultWayPoint_radius = Iec61174Package.theInstance.getDefaultWayPoint_radius();
		/**
		 * @generated
		 * @return feature descriptor name in type Route
		 */
		 UStructuralFeature Route_name = Iec61174Package.theInstance.getRoute_name();
		/**
		 * @generated
		 * @return feature descriptor version in type Route
		 */
		 UStructuralFeature Route_version = Iec61174Package.theInstance.getRoute_version();
		/**
		 * @generated
		 * @return feature descriptor waypoints in type Route
		 */
		 UStructuralFeature Route_waypoints = Iec61174Package.theInstance.getRoute_waypoints();
		/**
		 * @generated
		 * @return feature descriptor schedule in type Route
		 */
		 UStructuralFeature Route_schedule = Iec61174Package.theInstance.getRoute_schedule();
		/**
		 * @generated
		 * @return feature descriptor estimatedTimeOfDepature in type RouteSchedule
		 */
		 UStructuralFeature RouteSchedule_estimatedTimeOfDepature = Iec61174Package.theInstance.getRouteSchedule_estimatedTimeOfDepature();
		/**
		 * @generated
		 * @return feature descriptor estimatedTimeOfArrival in type RouteSchedule
		 */
		 UStructuralFeature RouteSchedule_estimatedTimeOfArrival = Iec61174Package.theInstance.getRouteSchedule_estimatedTimeOfArrival();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mExtension = null;
	/**
	* @generated
	*/
	private UClass mIECElementWithExtension = null;
	/**
	* @generated
	*/
	private UClass mWayPoints = null;
	/**
	* @generated
	*/
	private UClass mWaypoint = null;
	/**
	* @generated
	*/
	private UEnum mLegGeometryType = null;
	/**
	* @generated
	*/
	private UClass mLeg = null;
	/**
	* @generated
	*/
	private UClass mDefaultWayPoint = null;
	/**
	* @generated
	*/
	private UClass mRoute = null;
	/**
	* @generated
	*/
	private UClass mRouteSchedule = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	//Features for classifier Extension
	/**
	 * @generated
	 */
	private UStructuralFeature mExtension_manufactor = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mExtension_name = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mExtension_version = null;
	
	//Features for classifier IECElementWithExtension
	/**
	 * @generated
	 */
	private UStructuralFeature mIECElementWithExtension_extensions = null;
	
	//Features for classifier WayPoints
	/**
	 * @generated
	 */
	private UStructuralFeature mWayPoints_defaultWaypoint = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mWayPoints_waypoints = null;
	
	//Features for classifier Waypoint
	/**
	 * @generated
	 */
	private UStructuralFeature mWaypoint_id = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mWaypoint_name = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mWaypoint_radius = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mWaypoint_revision = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mWaypoint_position = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mWaypoint_leg = null;
	
	//Features for classifier Leg
	/**
	 * @generated
	 */
	private UStructuralFeature mLeg_starboardXTD = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mLeg_portsideXTD = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mLeg_safetyContour = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mLeg_safetyDepth = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mLeg_geometryType = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mLeg_planSpeedMin = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mLeg_planSpeedMax = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mLeg_draughtForward = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mLeg_draughtAft = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mLeg_legInfo = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mLeg_legNote1 = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mLeg_legNote2 = null;
	
	//Features for classifier DefaultWayPoint
	/**
	 * @generated
	 */
	private UStructuralFeature mDefaultWayPoint_radius = null;
	
	//Features for classifier Route
	/**
	 * @generated
	 */
	private UStructuralFeature mRoute_name = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mRoute_version = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mRoute_waypoints = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mRoute_schedule = null;
	
	//Features for classifier RouteSchedule
	/**
	 * @generated
	 */
	private UStructuralFeature mRouteSchedule_estimatedTimeOfDepature = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mRouteSchedule_estimatedTimeOfArrival = null;
	
	
	
	
	/**
	 * @generated
	 */
	public static Iec61174Package init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package Iec61174Package ...");
		theInstance = new Iec61174Package();
		//initialize referenced models
		SpatialModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.domain.maritime.iec61174");
		p.getContent().add(theInstance.mExtension);
		p.getContent().add(theInstance.mIECElementWithExtension);
		p.getContent().add(theInstance.mWayPoints);
		p.getContent().add(theInstance.mWaypoint);
		p.getContent().add(theInstance.mLegGeometryType);
		p.getContent().add(theInstance.mLeg);
		p.getContent().add(theInstance.mDefaultWayPoint);
		p.getContent().add(theInstance.mRoute);
		p.getContent().add(theInstance.mRouteSchedule);
		p.freeze();
		
		
		
		ULog.debug("... package Iec61174Package initialized");
		
		return theInstance;
	}
	
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mExtension = UMetaBuilder.manual().createClass("Extension", false, Extension.class, ExtensionImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mExtension, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new ExtensionImpl();
				}
			});
		
		mIECElementWithExtension = UMetaBuilder.manual().createClass("IECElementWithExtension", true, IECElementWithExtension.class, IECElementWithExtensionImpl.class);
		
		mWayPoints = UMetaBuilder.manual().createClass("WayPoints", false, WayPoints.class, WayPointsImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mWayPoints, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new WayPointsImpl();
				}
			});
			//Annotations for WayPoints
			mWayPoints.createAnnotation("FeatureType");
		
		mWaypoint = UMetaBuilder.manual().createClass("Waypoint", false, Waypoint.class, WaypointImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mWaypoint, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new WaypointImpl();
				}
			});
			//Annotations for Waypoint
			mWaypoint.createAnnotation("ComplexAttributeType");
		
		mLegGeometryType = UMetaBuilder.manual().createEnumeration("LegGeometryType", LegGeometryType.class);
		
		mLeg = UMetaBuilder.manual().createClass("Leg", false, Leg.class, LegImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mLeg, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new LegImpl();
				}
			});
			mLeg.setDocumentation("\r\n * For documentation see IEC61174\r\n ");
			//Annotations for Leg
			mLeg.createAnnotation("FeatureType");
		
		mDefaultWayPoint = UMetaBuilder.manual().createClass("DefaultWayPoint", false, DefaultWayPoint.class, DefaultWayPointImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mDefaultWayPoint, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new DefaultWayPointImpl();
				}
			});
			//Annotations for DefaultWayPoint
			mDefaultWayPoint.createAnnotation("ComplexAttributeType");
		
		mRoute = UMetaBuilder.manual().createClass("Route", false, Route.class, RouteImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mRoute, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new RouteImpl();
				}
			});
			//Annotations for Route
			mRoute.createAnnotation("FeatureType");
		
		mRouteSchedule = UMetaBuilder.manual().createClass("RouteSchedule", false, RouteSchedule.class, RouteScheduleImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mRouteSchedule, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new RouteScheduleImpl();
				}
			});
			//Annotations for RouteSchedule
			mRouteSchedule.createAnnotation("struct");
		
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of Extension
			mExtension_manufactor = UMetaBuilder.manual().createFeature("manufactor", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mExtension_manufactor, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Extension)instance).getManufactor(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Extension)instance).setManufactor((String)value); } }
				);
			mExtension_name = UMetaBuilder.manual().createFeature("name", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mExtension_name, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Extension)instance).getName(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Extension)instance).setName((String)value); } }
				);
			mExtension_version = UMetaBuilder.manual().createFeature("version", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mExtension_version, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Extension)instance).getVersion(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Extension)instance).setVersion((String)value); } }
				);
			
			//Features of IECElementWithExtension
			mIECElementWithExtension_extensions = UMetaBuilder.manual().createFeature("extensions", Iec61174Package.theInstance.getExtension(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mIECElementWithExtension_extensions, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((IECElementWithExtension)instance).getExtensions(); } }, 
						null
				);
			
			//Features of WayPoints
			mWayPoints_defaultWaypoint = UMetaBuilder.manual().createFeature("defaultWaypoint", Iec61174Package.theInstance.getDefaultWayPoint(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mWayPoints_defaultWaypoint, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((WayPoints)instance).getDefaultWaypoint(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((WayPoints)instance).setDefaultWaypoint((DefaultWayPoint)value); } }
				);
			mWayPoints_waypoints = UMetaBuilder.manual().createFeature("waypoints", Iec61174Package.theInstance.getWaypoint(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mWayPoints_waypoints, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((WayPoints)instance).getWaypoints(); } }, 
						null
				);
			
			//Features of Waypoint
			mWaypoint_id = UMetaBuilder.manual().createFeature("id", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mWaypoint_id, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Waypoint)instance).getId(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Waypoint)instance).setId((int)value); } }
				);
			mWaypoint_name = UMetaBuilder.manual().createFeature("name", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mWaypoint_name, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Waypoint)instance).getName(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Waypoint)instance).setName((String)value); } }
				);
			mWaypoint_radius = UMetaBuilder.manual().createFeature("radius", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mWaypoint_radius, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Waypoint)instance).getRadius(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Waypoint)instance).setRadius((double)value); } }
				);
			mWaypoint_revision = UMetaBuilder.manual().createFeature("revision", TypeUtils.getPrimitiveType(int.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mWaypoint_revision, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Waypoint)instance).getRevision(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Waypoint)instance).setRevision((int)value); } }
				);
			mWaypoint_position = UMetaBuilder.manual().createFeature("position", SpatialPackage.theInstance.getCoordinate(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mWaypoint_position, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Waypoint)instance).getPosition(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Waypoint)instance).setPosition((Coordinate)value); } }
				);
			mWaypoint_leg = UMetaBuilder.manual().createFeature("leg", Iec61174Package.theInstance.getLeg(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mWaypoint_leg, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Waypoint)instance).getLeg(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Waypoint)instance).setLeg((Leg)value); } }
				);
			
			//Features of Leg
			mLeg_starboardXTD = UMetaBuilder.manual().createFeature("starboardXTD", UnitsPackage.theInstance.getDistance(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLeg_starboardXTD, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Leg)instance).getStarboardXTD(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Leg)instance).setStarboardXTD((Distance)value); } }
				);
			mLeg_portsideXTD = UMetaBuilder.manual().createFeature("portsideXTD", UnitsPackage.theInstance.getDistance(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLeg_portsideXTD, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Leg)instance).getPortsideXTD(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Leg)instance).setPortsideXTD((Distance)value); } }
				);
			mLeg_safetyContour = UMetaBuilder.manual().createFeature("safetyContour", UnitsPackage.theInstance.getDistance(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLeg_safetyContour, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Leg)instance).getSafetyContour(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Leg)instance).setSafetyContour((Distance)value); } }
				);
			mLeg_safetyDepth = UMetaBuilder.manual().createFeature("safetyDepth", UnitsPackage.theInstance.getDistance(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLeg_safetyDepth, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Leg)instance).getSafetyDepth(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Leg)instance).setSafetyDepth((Distance)value); } }
				);
			mLeg_geometryType = UMetaBuilder.manual().createFeature("geometryType", Iec61174Package.theInstance.getLegGeometryType(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLeg_geometryType, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Leg)instance).getGeometryType(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Leg)instance).setGeometryType((LegGeometryType)value); } }
				);
			mLeg_planSpeedMin = UMetaBuilder.manual().createFeature("planSpeedMin", UnitsPackage.theInstance.getSpeed(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLeg_planSpeedMin, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Leg)instance).getPlanSpeedMin(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Leg)instance).setPlanSpeedMin((Speed)value); } }
				);
			mLeg_planSpeedMax = UMetaBuilder.manual().createFeature("planSpeedMax", UnitsPackage.theInstance.getSpeed(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLeg_planSpeedMax, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Leg)instance).getPlanSpeedMax(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Leg)instance).setPlanSpeedMax((Speed)value); } }
				);
			mLeg_draughtForward = UMetaBuilder.manual().createFeature("draughtForward", UnitsPackage.theInstance.getDistance(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLeg_draughtForward, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Leg)instance).getDraughtForward(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Leg)instance).setDraughtForward((Distance)value); } }
				);
			mLeg_draughtAft = UMetaBuilder.manual().createFeature("draughtAft", UnitsPackage.theInstance.getDistance(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLeg_draughtAft, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Leg)instance).getDraughtAft(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Leg)instance).setDraughtAft((Distance)value); } }
				);
			mLeg_legInfo = UMetaBuilder.manual().createFeature("legInfo", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLeg_legInfo, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Leg)instance).getLegInfo(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Leg)instance).setLegInfo((String)value); } }
				);
			mLeg_legNote1 = UMetaBuilder.manual().createFeature("legNote1", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLeg_legNote1, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Leg)instance).getLegNote1(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Leg)instance).setLegNote1((String)value); } }
				);
			mLeg_legNote2 = UMetaBuilder.manual().createFeature("legNote2", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLeg_legNote2, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Leg)instance).getLegNote2(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Leg)instance).setLegNote2((String)value); } }
				);
			
			//Features of DefaultWayPoint
			mDefaultWayPoint_radius = UMetaBuilder.manual().createFeature("radius", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mDefaultWayPoint_radius, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((DefaultWayPoint)instance).getRadius(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((DefaultWayPoint)instance).setRadius((double)value); } }
				);
			
			//Features of Route
			mRoute_name = UMetaBuilder.manual().createFeature("name", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRoute_name, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Route)instance).getName(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Route)instance).setName((String)value); } }
				);
			mRoute_version = UMetaBuilder.manual().createFeature("version", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRoute_version, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Route)instance).getVersion(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Route)instance).setVersion((String)value); } }
				);
			mRoute_waypoints = UMetaBuilder.manual().createFeature("waypoints", Iec61174Package.theInstance.getWayPoints(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRoute_waypoints, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Route)instance).getWaypoints(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Route)instance).setWaypoints((WayPoints)value); } }
				);
			mRoute_schedule = UMetaBuilder.manual().createFeature("schedule", Iec61174Package.theInstance.getRouteSchedule(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRoute_schedule, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Route)instance).getSchedule(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Route)instance).setSchedule((RouteSchedule)value); } }
				);
			
			//Features of RouteSchedule
			mRouteSchedule_estimatedTimeOfDepature = UMetaBuilder.manual().createFeature("estimatedTimeOfDepature", UnitsPackage.theInstance.getTime(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRouteSchedule_estimatedTimeOfDepature, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((RouteSchedule)instance).getEstimatedTimeOfDepature(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((RouteSchedule)instance).setEstimatedTimeOfDepature((Time)value); } }
				);
				//Annotations for RouteSchedule:estimatedTimeOfDepature
				mRouteSchedule_estimatedTimeOfDepature.createAnnotation("DateTime");
			mRouteSchedule_estimatedTimeOfArrival = UMetaBuilder.manual().createFeature("estimatedTimeOfArrival", UnitsPackage.theInstance.getTime(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mRouteSchedule_estimatedTimeOfArrival, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((RouteSchedule)instance).getEstimatedTimeOfArrival(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((RouteSchedule)instance).setEstimatedTimeOfArrival((Time)value); } }
				);
				//Annotations for RouteSchedule:estimatedTimeOfArrival
				mRouteSchedule_estimatedTimeOfArrival.createAnnotation("DateTime");
			
		}
		{ //assign features
			mExtension.getStructuralFeatures().add(mExtension_manufactor);
			mExtension.getStructuralFeatures().add(mExtension_name);
			mExtension.getStructuralFeatures().add(mExtension_version);
			mIECElementWithExtension.getStructuralFeatures().add(mIECElementWithExtension_extensions);
			mWayPoints.getStructuralFeatures().add(mWayPoints_defaultWaypoint);
			mWayPoints.getStructuralFeatures().add(mWayPoints_waypoints);
			mWaypoint.getStructuralFeatures().add(mWaypoint_id);
			mWaypoint.getStructuralFeatures().add(mWaypoint_name);
			mWaypoint.getStructuralFeatures().add(mWaypoint_radius);
			mWaypoint.getStructuralFeatures().add(mWaypoint_revision);
			mWaypoint.getStructuralFeatures().add(mWaypoint_position);
			mWaypoint.getStructuralFeatures().add(mWaypoint_leg);
			mLeg.getStructuralFeatures().add(mLeg_starboardXTD);
			mLeg.getStructuralFeatures().add(mLeg_portsideXTD);
			mLeg.getStructuralFeatures().add(mLeg_safetyContour);
			mLeg.getStructuralFeatures().add(mLeg_safetyDepth);
			mLeg.getStructuralFeatures().add(mLeg_geometryType);
			mLeg.getStructuralFeatures().add(mLeg_planSpeedMin);
			mLeg.getStructuralFeatures().add(mLeg_planSpeedMax);
			mLeg.getStructuralFeatures().add(mLeg_draughtForward);
			mLeg.getStructuralFeatures().add(mLeg_draughtAft);
			mLeg.getStructuralFeatures().add(mLeg_legInfo);
			mLeg.getStructuralFeatures().add(mLeg_legNote1);
			mLeg.getStructuralFeatures().add(mLeg_legNote2);
			mDefaultWayPoint.getStructuralFeatures().add(mDefaultWayPoint_radius);
			mRoute.getStructuralFeatures().add(mRoute_name);
			mRoute.getStructuralFeatures().add(mRoute_version);
			mRoute.getStructuralFeatures().add(mRoute_waypoints);
			mRoute.getStructuralFeatures().add(mRoute_schedule);
			mRouteSchedule.getStructuralFeatures().add(mRouteSchedule_estimatedTimeOfDepature);
			mRouteSchedule.getStructuralFeatures().add(mRouteSchedule_estimatedTimeOfArrival);
		}
		
		UMetaBuilder.manual().addLiteral(mLegGeometryType, "Loxodome", 0, LegGeometryType.Loxodome);
		UMetaBuilder.manual().addLiteral(mLegGeometryType, "Orthodome", 1, LegGeometryType.Orthodome);
	}

	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
		{		//Operations of Route
			UOperation operation = null;
			//operation : getLength(Length)
			operation = UMetaBuilder.manual().createOperation("getLength", false, UnitsPackage.theInstance.getLength(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Route)instance).getLength();
				}
			});
				operation.setDocumentation("\r\n * returns the length of the overall route, from the first to the last waypoint\r\n ");
				//Annotations for Route:getLength(Length)
				operation.createAnnotation("const");
				mRoute.getOperations().add(operation);
			//operation : getLength(Length, Coordinate)
			operation = UMetaBuilder.manual().createOperation("getLength", false, UnitsPackage.theInstance.getLength(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Route)instance).getLength((Coordinate)parameter[0]);
				}
			});
				operation.setDocumentation(" returns the length from the coordinate to the end of the route. \r\n\t\t\t\t\t\t\t\t ");
				//Annotations for Route:getLength(Length, Coordinate)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "coord", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mRoute.getOperations().add(operation);
			//operation : getEnvelope(Envelope)
			operation = UMetaBuilder.manual().createOperation("getEnvelope", false, SpatialPackage.theInstance.getEnvelope(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Route)instance).getEnvelope();
				}
			});
				operation.setDocumentation("\r\n * Returns the envelope containing all waypoints of this route\r\n ");
				//Annotations for Route:getEnvelope(Envelope)
				operation.createAnnotation("const");
				mRoute.getOperations().add(operation);
		}
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mWayPoints.setSuperType(Iec61174Package.theInstance.getIECElementWithExtension());
		mWaypoint.setSuperType(Iec61174Package.theInstance.getIECElementWithExtension());
		mDefaultWayPoint.setSuperType(Iec61174Package.theInstance.getIECElementWithExtension());
		mRoute.setSuperType(Iec61174Package.theInstance.getIECElementWithExtension());
		
	}
	
	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getExtension(){
		if (mExtension == null){
			mExtension = UCoreMetaRepository.getUClass(Extension.class);
		}
		return mExtension;
	}
	/**
	* @generated
	*/
	public UClass getIECElementWithExtension(){
		if (mIECElementWithExtension == null){
			mIECElementWithExtension = UCoreMetaRepository.getUClass(IECElementWithExtension.class);
		}
		return mIECElementWithExtension;
	}
	/**
	* @generated
	*/
	public UClass getWayPoints(){
		if (mWayPoints == null){
			mWayPoints = UCoreMetaRepository.getUClass(WayPoints.class);
		}
		return mWayPoints;
	}
	/**
	* @generated
	*/
	public UClass getWaypoint(){
		if (mWaypoint == null){
			mWaypoint = UCoreMetaRepository.getUClass(Waypoint.class);
		}
		return mWaypoint;
	}
	/**
	* @generated
	*/
	public UEnum getLegGeometryType(){
		if (mLegGeometryType == null){
			mLegGeometryType = UCoreMetaRepository.getUEnumeration(LegGeometryType.class);
		}
		return mLegGeometryType;
	}
	/**
	* @generated
	*/
	public UClass getLeg(){
		if (mLeg == null){
			mLeg = UCoreMetaRepository.getUClass(Leg.class);
		}
		return mLeg;
	}
	/**
	* @generated
	*/
	public UClass getDefaultWayPoint(){
		if (mDefaultWayPoint == null){
			mDefaultWayPoint = UCoreMetaRepository.getUClass(DefaultWayPoint.class);
		}
		return mDefaultWayPoint;
	}
	/**
	* @generated
	*/
	public UClass getRoute(){
		if (mRoute == null){
			mRoute = UCoreMetaRepository.getUClass(Route.class);
		}
		return mRoute;
	}
	/**
	* @generated
	*/
	public UClass getRouteSchedule(){
		if (mRouteSchedule == null){
			mRouteSchedule = UCoreMetaRepository.getUClass(RouteSchedule.class);
		}
		return mRouteSchedule;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getExtension_manufactor(){
		if (mExtension_manufactor == null)
			mExtension_manufactor = getExtension().getFeature("manufactor");
		return mExtension_manufactor;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getExtension_name(){
		if (mExtension_name == null)
			mExtension_name = getExtension().getFeature("name");
		return mExtension_name;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getExtension_version(){
		if (mExtension_version == null)
			mExtension_version = getExtension().getFeature("version");
		return mExtension_version;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getIECElementWithExtension_extensions(){
		if (mIECElementWithExtension_extensions == null)
			mIECElementWithExtension_extensions = getIECElementWithExtension().getFeature("extensions");
		return mIECElementWithExtension_extensions;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getWayPoints_defaultWaypoint(){
		if (mWayPoints_defaultWaypoint == null)
			mWayPoints_defaultWaypoint = getWayPoints().getFeature("defaultWaypoint");
		return mWayPoints_defaultWaypoint;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getWayPoints_waypoints(){
		if (mWayPoints_waypoints == null)
			mWayPoints_waypoints = getWayPoints().getFeature("waypoints");
		return mWayPoints_waypoints;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getWaypoint_id(){
		if (mWaypoint_id == null)
			mWaypoint_id = getWaypoint().getFeature("id");
		return mWaypoint_id;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getWaypoint_name(){
		if (mWaypoint_name == null)
			mWaypoint_name = getWaypoint().getFeature("name");
		return mWaypoint_name;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getWaypoint_radius(){
		if (mWaypoint_radius == null)
			mWaypoint_radius = getWaypoint().getFeature("radius");
		return mWaypoint_radius;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getWaypoint_revision(){
		if (mWaypoint_revision == null)
			mWaypoint_revision = getWaypoint().getFeature("revision");
		return mWaypoint_revision;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getWaypoint_position(){
		if (mWaypoint_position == null)
			mWaypoint_position = getWaypoint().getFeature("position");
		return mWaypoint_position;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getWaypoint_leg(){
		if (mWaypoint_leg == null)
			mWaypoint_leg = getWaypoint().getFeature("leg");
		return mWaypoint_leg;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLeg_starboardXTD(){
		if (mLeg_starboardXTD == null)
			mLeg_starboardXTD = getLeg().getFeature("starboardXTD");
		return mLeg_starboardXTD;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLeg_portsideXTD(){
		if (mLeg_portsideXTD == null)
			mLeg_portsideXTD = getLeg().getFeature("portsideXTD");
		return mLeg_portsideXTD;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLeg_safetyContour(){
		if (mLeg_safetyContour == null)
			mLeg_safetyContour = getLeg().getFeature("safetyContour");
		return mLeg_safetyContour;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLeg_safetyDepth(){
		if (mLeg_safetyDepth == null)
			mLeg_safetyDepth = getLeg().getFeature("safetyDepth");
		return mLeg_safetyDepth;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLeg_geometryType(){
		if (mLeg_geometryType == null)
			mLeg_geometryType = getLeg().getFeature("geometryType");
		return mLeg_geometryType;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLeg_planSpeedMin(){
		if (mLeg_planSpeedMin == null)
			mLeg_planSpeedMin = getLeg().getFeature("planSpeedMin");
		return mLeg_planSpeedMin;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLeg_planSpeedMax(){
		if (mLeg_planSpeedMax == null)
			mLeg_planSpeedMax = getLeg().getFeature("planSpeedMax");
		return mLeg_planSpeedMax;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLeg_draughtForward(){
		if (mLeg_draughtForward == null)
			mLeg_draughtForward = getLeg().getFeature("draughtForward");
		return mLeg_draughtForward;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLeg_draughtAft(){
		if (mLeg_draughtAft == null)
			mLeg_draughtAft = getLeg().getFeature("draughtAft");
		return mLeg_draughtAft;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLeg_legInfo(){
		if (mLeg_legInfo == null)
			mLeg_legInfo = getLeg().getFeature("legInfo");
		return mLeg_legInfo;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLeg_legNote1(){
		if (mLeg_legNote1 == null)
			mLeg_legNote1 = getLeg().getFeature("legNote1");
		return mLeg_legNote1;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLeg_legNote2(){
		if (mLeg_legNote2 == null)
			mLeg_legNote2 = getLeg().getFeature("legNote2");
		return mLeg_legNote2;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getDefaultWayPoint_radius(){
		if (mDefaultWayPoint_radius == null)
			mDefaultWayPoint_radius = getDefaultWayPoint().getFeature("radius");
		return mDefaultWayPoint_radius;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getRoute_name(){
		if (mRoute_name == null)
			mRoute_name = getRoute().getFeature("name");
		return mRoute_name;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getRoute_version(){
		if (mRoute_version == null)
			mRoute_version = getRoute().getFeature("version");
		return mRoute_version;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getRoute_waypoints(){
		if (mRoute_waypoints == null)
			mRoute_waypoints = getRoute().getFeature("waypoints");
		return mRoute_waypoints;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getRoute_schedule(){
		if (mRoute_schedule == null)
			mRoute_schedule = getRoute().getFeature("schedule");
		return mRoute_schedule;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getRouteSchedule_estimatedTimeOfDepature(){
		if (mRouteSchedule_estimatedTimeOfDepature == null)
			mRouteSchedule_estimatedTimeOfDepature = getRouteSchedule().getFeature("estimatedTimeOfDepature");
		return mRouteSchedule_estimatedTimeOfDepature;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getRouteSchedule_estimatedTimeOfArrival(){
		if (mRouteSchedule_estimatedTimeOfArrival == null)
			mRouteSchedule_estimatedTimeOfArrival = getRouteSchedule().getFeature("estimatedTimeOfArrival");
		return mRouteSchedule_estimatedTimeOfArrival;
	}
}
