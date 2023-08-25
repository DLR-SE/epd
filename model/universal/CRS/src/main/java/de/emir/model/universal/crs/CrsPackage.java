package de.emir.model.universal.crs;

import de.emir.model.universal.CoreModel;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.model.universal.MathModel;
import de.emir.model.universal.core.CorePackage;
import de.emir.model.universal.crs.impl.CoordinateReferenceSystemImpl;
import de.emir.model.universal.crs.impl.CoordinateSystemAxisImpl;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.model.universal.crs.impl.CoordinateSystemImpl;
import de.emir.model.universal.crs.impl.Engineering2DImpl;
import de.emir.model.universal.crs.impl.Engineering3DImpl;
import de.emir.model.universal.crs.impl.EngineeringCRSImpl;
import de.emir.model.universal.crs.impl.LocalCRSImpl;
import de.emir.model.universal.crs.impl.NativeCRSImpl;
import de.emir.model.universal.crs.impl.Polar2DImpl;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.model.universal.crs.impl.Polar3DImpl;
import de.emir.model.universal.crs.impl.PolarCRSImpl;
import de.emir.model.universal.crs.impl.WGS842DImpl;
import de.emir.model.universal.crs.impl.WGS843DImpl;
import de.emir.model.universal.crs.AxisDirection;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.crs.CoordinateSystem;
import de.emir.model.universal.crs.impl.WGS84CRSImpl;
import de.emir.model.universal.crs.CoordinateSystemAxis;
import de.emir.model.universal.math.MathPackage;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.crs.EngineeringCRS;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.model.universal.crs.LocalCRS;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.model.universal.crs.PolarCRS;
import de.emir.model.universal.crs.Engineering2D;
import de.emir.model.universal.crs.NativeCRS;
import de.emir.model.universal.crs.Engineering3D;
import de.emir.model.universal.crs.Polar2D;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.model.universal.crs.WGS842D;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.model.universal.crs.WGS84CRS;
import de.emir.model.universal.crs.WGS843D;
import de.emir.model.universal.crs.Polar3D;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;
import java.util.List;

/**

 * \note for now we skip the definition of ComboundCRS as defined in S-100 Part 6 to keep the model as simple as possible
 * if there is a need to add these CRS they will be added here. 
 * @generated 
 */
public class CrsPackage  
{
	/**
	 * @generated
	 */
	public static CrsPackage theInstance = new CrsPackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for enumeration AxisDirection
		*/
		UEnum AxisDirection = CrsPackage.theInstance.getAxisDirection();
		/**
		* @generated
		* @return meta type for classifier CoordinateSystemAxis
		*/
		UClass CoordinateSystemAxis = CrsPackage.theInstance.getCoordinateSystemAxis();
		/**
		* @generated
		* @return meta type for classifier CoordinateSystem
		*/
		UClass CoordinateSystem = CrsPackage.theInstance.getCoordinateSystem();
		/**
		* @generated
		* @return meta type for classifier CoordinateReferenceSystem
		*/
		UClass CoordinateReferenceSystem = CrsPackage.theInstance.getCoordinateReferenceSystem();
		/**
		* @generated
		* @return meta type for classifier WGS84CRS
		*/
		UClass WGS84CRS = CrsPackage.theInstance.getWGS84CRS();
		/**
		* @generated
		* @return meta type for classifier LocalCRS
		*/
		UClass LocalCRS = CrsPackage.theInstance.getLocalCRS();
		/**
		* @generated
		* @return meta type for classifier EngineeringCRS
		*/
		UClass EngineeringCRS = CrsPackage.theInstance.getEngineeringCRS();
		/**
		* @generated
		* @return meta type for classifier PolarCRS
		*/
		UClass PolarCRS = CrsPackage.theInstance.getPolarCRS();
		/**
		* @generated
		* @return meta type for classifier WGS842D
		*/
		UClass WGS842D = CrsPackage.theInstance.getWGS842D();
		/**
		* @generated
		* @return meta type for classifier WGS843D
		*/
		UClass WGS843D = CrsPackage.theInstance.getWGS843D();
		/**
		* @generated
		* @return meta type for classifier Polar2D
		*/
		UClass Polar2D = CrsPackage.theInstance.getPolar2D();
		/**
		* @generated
		* @return meta type for classifier Polar3D
		*/
		UClass Polar3D = CrsPackage.theInstance.getPolar3D();
		/**
		* @generated
		* @return meta type for classifier Engineering2D
		*/
		UClass Engineering2D = CrsPackage.theInstance.getEngineering2D();
		/**
		* @generated
		* @return meta type for classifier Engineering3D
		*/
		UClass Engineering3D = CrsPackage.theInstance.getEngineering3D();
		/**
		* @generated
		* @return meta type for classifier NativeCRS
		*/
		UClass NativeCRS = CrsPackage.theInstance.getNativeCRS();
		/**
		 * @generated
		 * @return feature descriptor direction in type CoordinateSystemAxis
		 */
		 UStructuralFeature CoordinateSystemAxis_direction = CrsPackage.theInstance.getCoordinateSystemAxis_direction();
		/**
		 * @generated
		 * @return feature descriptor name in type CoordinateSystemAxis
		 */
		 UStructuralFeature CoordinateSystemAxis_name = CrsPackage.theInstance.getCoordinateSystemAxis_name();
		/**
		 * @generated
		 * @return feature descriptor minimumRange in type CoordinateSystemAxis
		 */
		 UStructuralFeature CoordinateSystemAxis_minimumRange = CrsPackage.theInstance.getCoordinateSystemAxis_minimumRange();
		/**
		 * @generated
		 * @return feature descriptor maximumRange in type CoordinateSystemAxis
		 */
		 UStructuralFeature CoordinateSystemAxis_maximumRange = CrsPackage.theInstance.getCoordinateSystemAxis_maximumRange();
		/**
		 * @generated
		 * @return feature descriptor axes in type CoordinateSystem
		 */
		 UStructuralFeature CoordinateSystem_axes = CrsPackage.theInstance.getCoordinateSystem_axes();
		/**
		 * @generated
		 * @return feature descriptor cs in type CoordinateReferenceSystem
		 */
		 UStructuralFeature CoordinateReferenceSystem_cs = CrsPackage.theInstance.getCoordinateReferenceSystem_cs();
		/**
		 * @generated
		 * @return feature descriptor origin in type LocalCRS
		 */
		 UStructuralFeature LocalCRS_origin = CrsPackage.theInstance.getLocalCRS_origin();
		/**
		 * @generated
		 * @return feature descriptor orientationOffset in type LocalCRS
		 */
		 UStructuralFeature LocalCRS_orientationOffset = CrsPackage.theInstance.getLocalCRS_orientationOffset();
		/**
		 * @generated
		 * @return feature descriptor wkt in type NativeCRS
		 */
		 UStructuralFeature NativeCRS_wkt = CrsPackage.theInstance.getNativeCRS_wkt();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UEnum mAxisDirection = null;
	/**
	* @generated
	*/
	private UClass mCoordinateSystemAxis = null;
	/**
	* @generated
	*/
	private UClass mCoordinateSystem = null;
	/**
	* @generated
	*/
	private UClass mCoordinateReferenceSystem = null;
	/**
	* @generated
	*/
	private UClass mWGS84CRS = null;
	/**
	* @generated
	*/
	private UClass mLocalCRS = null;
	/**
	* @generated
	*/
	private UClass mEngineeringCRS = null;
	/**
	* @generated
	*/
	private UClass mPolarCRS = null;
	/**
	* @generated
	*/
	private UClass mWGS842D = null;
	/**
	* @generated
	*/
	private UClass mWGS843D = null;
	/**
	* @generated
	*/
	private UClass mPolar2D = null;
	/**
	* @generated
	*/
	private UClass mPolar3D = null;
	/**
	* @generated
	*/
	private UClass mEngineering2D = null;
	/**
	* @generated
	*/
	private UClass mEngineering3D = null;
	/**
	* @generated
	*/
	private UClass mNativeCRS = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	//Features for classifier CoordinateSystemAxis
	/**
	 * @generated
	 */
	private UStructuralFeature mCoordinateSystemAxis_direction = null;
	//Features for classifier CoordinateSystemAxis
	/**
	 * @generated
	 */
	private UStructuralFeature mCoordinateSystemAxis_name = null;
	//Features for classifier CoordinateSystemAxis
	/**
	 * @generated
	 */
	private UStructuralFeature mCoordinateSystemAxis_minimumRange = null;
	//Features for classifier CoordinateSystemAxis
	/**
	 * @generated
	 */
	private UStructuralFeature mCoordinateSystemAxis_maximumRange = null;
	//Features for classifier CoordinateSystem
	/**
	 * @generated
	 */
	private UStructuralFeature mCoordinateSystem_axes = null;
	//Features for classifier CoordinateReferenceSystem
	/**
	 * @generated
	 */
	private UStructuralFeature mCoordinateReferenceSystem_cs = null;
	//Features for classifier LocalCRS
	/**
	 * @generated
	 */
	private UStructuralFeature mLocalCRS_origin = null;
	//Features for classifier LocalCRS
	/**
	 * @generated
	 */
	private UStructuralFeature mLocalCRS_orientationOffset = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mNativeCRS_wkt = null;
	/**
	 * @generated
	 */
	public static CrsPackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package CrsPackage ...", 1);
		theInstance = new CrsPackage();
		//initialize referenced models
		CoreModel.init();
		MathModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.universal.crs");
		p.getContent().add(theInstance.mAxisDirection);
		p.getContent().add(theInstance.mCoordinateSystemAxis);
		p.getContent().add(theInstance.mCoordinateSystem);
		p.getContent().add(theInstance.mCoordinateReferenceSystem);
		p.getContent().add(theInstance.mWGS84CRS);
		p.getContent().add(theInstance.mLocalCRS);
		p.getContent().add(theInstance.mEngineeringCRS);
		p.getContent().add(theInstance.mPolarCRS);
		p.getContent().add(theInstance.mWGS842D);
		p.getContent().add(theInstance.mWGS843D);
		p.getContent().add(theInstance.mPolar2D);
		p.getContent().add(theInstance.mPolar3D);
		p.getContent().add(theInstance.mEngineering2D);
		p.getContent().add(theInstance.mEngineering3D);
		p.getContent().add(theInstance.mNativeCRS);
		p.freeze();
		
		
		
		ULog.debug(-1, "... package CrsPackage initialized");
		
		return theInstance;
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mAxisDirection = UMetaBuilder.manual().createEnumeration("AxisDirection", AxisDirection.class);
		
		mCoordinateSystemAxis = UMetaBuilder.manual().createClass("CoordinateSystemAxis", false, CoordinateSystemAxis.class, CoordinateSystemAxisImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mCoordinateSystemAxis, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new CoordinateSystemAxisImpl();
				}
			});
			//Annotations for CoordinateSystemAxis
			mCoordinateSystemAxis.createAnnotation("ComplexAttributeType");
			mCoordinateSystemAxis.createAnnotation("struct");
		
		mCoordinateSystem = UMetaBuilder.manual().createClass("CoordinateSystem", true, CoordinateSystem.class, CoordinateSystemImpl.class);
			mCoordinateSystem.setDocumentation("The set of coordinate system axes that spans a given coordinate space. A coordinate system (CS)\r\n * is derived from a set of (mathematical) rules for specifying how coordinates in a given space\r\n * are to be assigned to points. The coordinate values in a coordinate tuple shall be recorded in\r\n * the order in which the coordinate system axes associations are recorded, whenever those\r\n * coordinates use a coordinate reference system that uses this coordinate system.\r\n * [GeoAPI]\r\n ");
		
		mCoordinateReferenceSystem = UMetaBuilder.manual().createClass("CoordinateReferenceSystem", true, CoordinateReferenceSystem.class, CoordinateReferenceSystemImpl.class);
			mCoordinateReferenceSystem.setDocumentation(" \r\n * coordinate reference system is a coordinate system that is related to the real world by a datum. \r\n * Generally, the real world will be the Earth although the principles are not restricted to the Earth.\r\n * \\source S-100 Version 2 Part 6 - CRS \r\n ");
		
		mWGS84CRS = UMetaBuilder.manual().createClass("WGS84CRS", true, WGS84CRS.class, WGS84CRSImpl.class);
			mWGS84CRS.setDocumentation(" @Brief Global WGS84 coordinate reference system, commonly used in most domains.  \r\n * the WGS84 coordinate reference system is one of the most frequently used systems, \r\n * therefore we provide optimized implementations\r\n ");
			//Annotations for WGS84CRS
			mWGS84CRS.createAnnotation("ComplexAttributeType");
		
		mLocalCRS = UMetaBuilder.manual().createClass("LocalCRS", true, LocalCRS.class, LocalCRSImpl.class);
			mLocalCRS.setDocumentation(" @Brief local coordinate reference system, only valid in a \"small\" area around its global position\r\n * The abstract local CRS indicates is a CRS that is only valid in a certain area (at least in terms of accuracy)\r\n  * therefore it contains a field to define its position within a global coordinate reference system\r\n * @warn since units are not known yet (defined in unit package) in this class, all distances are defined in Meters (distances), radians (angles) or WGS84 (coordinates)  \r\n ");
		
		mEngineeringCRS = UMetaBuilder.manual().createClass("EngineeringCRS", true, EngineeringCRS.class, EngineeringCRSImpl.class);
			mEngineeringCRS.setDocumentation("\r\n * A contextually local coordinate reference system which can be divided into two broad categories: \r\n * - earth-fixed systems applied to engineering activities on or near the surface of the earth; \r\n * - CRSs on moving platforms such as road vehicles, vessels, aircraft or spacecraft.\r\n * \\source ISO 19111:2007\r\n ");
			//Annotations for EngineeringCRS
			mEngineeringCRS.createAnnotation("ComplexAttributeType");
		
		mPolarCRS = UMetaBuilder.manual().createClass("PolarCRS", true, PolarCRS.class, PolarCRSImpl.class);
			//Annotations for PolarCRS
			mPolarCRS.createAnnotation("ComplexAttributeType");
		
		mWGS842D = UMetaBuilder.manual().createClass("WGS842D", false, WGS842D.class, WGS842DImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mWGS842D, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new WGS842DImpl();
				}
			});
			mWGS842D.setDocumentation(" Defines the default global coordinate reference system (WGS84) for eMIR with 2 dimensions.\r\n * The WGS842D CRS corresponds to the EPSG code: urn:ogc:def:crs:EPSG:6.6:4326 (defined by GeoTools)\r\n * @note since, the order of WGS84 coordinates differes between different applications (either lat/lon or lon/lat)\r\n * we define the lat/lon order to be the default order for WGS84 in eMIR (thats the reason to not use the EPSG:4326) \r\n * Thereby we follow the chartographic order instead of the informatics/mathematics point of view. \r\n ");
		
		mWGS843D = UMetaBuilder.manual().createClass("WGS843D", false, WGS843D.class, WGS843DImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mWGS843D, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new WGS843DImpl();
				}
			});
			mWGS843D.setDocumentation(" Defines the default global CRS to be used in eMIR, for 3 dimensions. \r\n * The WGS843D differes from its 2D version (WGS842D) that the z component of a coordinate is interpreted as altitude (in meters). \r\n ");
		
		mPolar2D = UMetaBuilder.manual().createClass("Polar2D", false, Polar2D.class, Polar2DImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mPolar2D, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new Polar2DImpl();
				}
			});
		
		mPolar3D = UMetaBuilder.manual().createClass("Polar3D", false, Polar3D.class, Polar3DImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mPolar3D, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new Polar3DImpl();
				}
			});
		
		mEngineering2D = UMetaBuilder.manual().createClass("Engineering2D", false, Engineering2D.class, Engineering2DImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mEngineering2D, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new Engineering2DImpl();
				}
			});
		
		mEngineering3D = UMetaBuilder.manual().createClass("Engineering3D", false, Engineering3D.class, Engineering3DImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mEngineering3D, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new Engineering3DImpl();
				}
			});
		
		mNativeCRS = UMetaBuilder.manual().createClass("NativeCRS", false, NativeCRS.class, NativeCRSImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mNativeCRS, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new NativeCRSImpl();
				}
			});
			mNativeCRS.setDocumentation(" generic coordinate reference system, that can either be specified using an EPSG code (like 4326 for WGS84) or an WKT string ");
		
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of CoordinateSystemAxis
			mCoordinateSystemAxis_direction = UMetaBuilder.manual().createFeature("direction", CrsPackage.theInstance.getAxisDirection(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCoordinateSystemAxis_direction, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CoordinateSystemAxis)instance).getDirection(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CoordinateSystemAxis)instance).setDirection((AxisDirection)value); } }
				);
			mCoordinateSystemAxis_name = UMetaBuilder.manual().createFeature("name", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCoordinateSystemAxis_name, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CoordinateSystemAxis)instance).getName(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CoordinateSystemAxis)instance).setName((String)value); } }
				);
				mCoordinateSystemAxis_name.setDocumentation(" symbolic name of this axis (axisSymbol in S100) ");
			mCoordinateSystemAxis_minimumRange = UMetaBuilder.manual().createFeature("minimumRange", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCoordinateSystemAxis_minimumRange, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CoordinateSystemAxis)instance).getMinimumRange(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CoordinateSystemAxis)instance).setMinimumRange((double)value); } }
				);
				mCoordinateSystemAxis_minimumRange.setDocumentation(" defines the minimum valid scope of this axis and thus the minimum value of the valid envelope, created by this cs ");
			mCoordinateSystemAxis_maximumRange = UMetaBuilder.manual().createFeature("maximumRange", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCoordinateSystemAxis_maximumRange, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CoordinateSystemAxis)instance).getMaximumRange(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CoordinateSystemAxis)instance).setMaximumRange((double)value); } }
				);
				mCoordinateSystemAxis_maximumRange.setDocumentation(" defines the maximum valid scope of this axis and thus the maximum value of the valid envelope, created by this cs ");
			
			//Features of CoordinateSystem
			mCoordinateSystem_axes = UMetaBuilder.manual().createFeature("axes", CrsPackage.theInstance.getCoordinateSystemAxis(), UAssociationType.COMPOSITION, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCoordinateSystem_axes, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CoordinateSystem)instance).getAxes(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CoordinateSystem)instance).setAxes((CoordinateSystemAxis)value); } }
				);
			
			//Features of CoordinateReferenceSystem
			mCoordinateReferenceSystem_cs = UMetaBuilder.manual().createFeature("cs", CrsPackage.theInstance.getCoordinateSystem(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCoordinateReferenceSystem_cs, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CoordinateReferenceSystem)instance).getCs(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CoordinateReferenceSystem)instance).setCs((CoordinateSystem)value); } }
				);
				mCoordinateReferenceSystem_cs.setDocumentation(" the relevant coordinate system instance \r\n * this is defined by ISO 19111 ( for SingleCRS)  \r\n *");
			
			//Features of LocalCRS
			mLocalCRS_origin = UMetaBuilder.manual().createFeature("origin", MathPackage.theInstance.getVector(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLocalCRS_origin, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((LocalCRS)instance).getOrigin(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((LocalCRS)instance).setOrigin((Vector)value); } }
				);
				mLocalCRS_origin.setDocumentation(" returns the origin of the CRS within a global CRS (e.g. WGS84)\r\n *  @note the dimension of the origin vector has to be the same as CoordinateReferenceSystem.dimension()\r\n * ");
			mLocalCRS_orientationOffset = UMetaBuilder.manual().createFeature("orientationOffset", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 1, -1);
				UMetaBuilder.manual().setFeatureAccessor(mLocalCRS_orientationOffset, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((LocalCRS)instance).getOrientationOffset(); } }, 
						null
				);
				mLocalCRS_orientationOffset.setDocumentation(" returns the orientation offset of this CRS, relativ to a global CRS (e.g. WGS84) \r\n * @return a one dimensional list with the azimuth in radians, if the dimension is 2, otherwise a 3 dimensional rotation in the order [pitch, roll, yaw] (also in radians)\r\n ");
			
			//Features of NativeCRS
			mNativeCRS_wkt = UMetaBuilder.manual().createFeature("wkt", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mNativeCRS_wkt, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((NativeCRS)instance).getWkt(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((NativeCRS)instance).setWkt((String)value); } }
				);
				mNativeCRS_wkt.setDocumentation(" wkt string, describing the native coordinate reference system ");
			
		}
		{ //assign features
			mCoordinateSystemAxis.getStructuralFeatures().add(mCoordinateSystemAxis_direction);
			mCoordinateSystemAxis.getStructuralFeatures().add(mCoordinateSystemAxis_name);
			mCoordinateSystemAxis.getStructuralFeatures().add(mCoordinateSystemAxis_minimumRange);
			mCoordinateSystemAxis.getStructuralFeatures().add(mCoordinateSystemAxis_maximumRange);
			mCoordinateSystem.getStructuralFeatures().add(mCoordinateSystem_axes);
			mCoordinateReferenceSystem.getStructuralFeatures().add(mCoordinateReferenceSystem_cs);
			mLocalCRS.getStructuralFeatures().add(mLocalCRS_origin);
			mLocalCRS.getStructuralFeatures().add(mLocalCRS_orientationOffset);
			mNativeCRS.getStructuralFeatures().add(mNativeCRS_wkt);
		}
		
		UMetaBuilder.manual().addLiteral(mAxisDirection, "NORTH", 0, AxisDirection.NORTH);
			mAxisDirection.getLiteral("NORTH").setDocumentation("\r\n     * Axis positive direction is north. In a geographic or projected CRS,\r\n     * north is defined through the geodetic datum. In an engineering CRS,\r\n     * north may be defined with respect to an engineering object rather\r\n     * than a geographical direction.\r\n     * [GeoAPI]\r\n     ");
		UMetaBuilder.manual().addLiteral(mAxisDirection, "EAST", 1, AxisDirection.EAST);
			mAxisDirection.getLiteral("EAST").setDocumentation("\r\n     * Axis positive direction is &pi;/2 radians clockwise from north.\r\n     * This is usually used for Grid X coordinates and Longitude.\r\n     * [GeoAPI]\r\n     ");
		UMetaBuilder.manual().addLiteral(mAxisDirection, "SOUTH", 2, AxisDirection.SOUTH);
			mAxisDirection.getLiteral("SOUTH").setDocumentation("\r\n     * Axis positive direction is &pi; radians clockwise from north.\r\n     * [GeoAPI]\r\n     ");
		UMetaBuilder.manual().addLiteral(mAxisDirection, "WEST", 3, AxisDirection.WEST);
			mAxisDirection.getLiteral("WEST").setDocumentation("\r\n     * Axis positive direction is 3&pi;/2 radians clockwise from north.\r\n     * This is usually used for Grid X coordinates and Longitude.\r\n     * [GeoAPI]\r\n     ");
		UMetaBuilder.manual().addLiteral(mAxisDirection, "UP", 4, AxisDirection.UP);
			mAxisDirection.getLiteral("UP").setDocumentation("\r\n     * Axis positive direction is up relative to gravity.\r\n     * [GeoAPI]\r\n     ");
		UMetaBuilder.manual().addLiteral(mAxisDirection, "DOWN", 5, AxisDirection.DOWN);
			mAxisDirection.getLiteral("DOWN").setDocumentation("\r\n     * Axis positive direction is down relative to gravity.\r\n     * [GeoAPI]\r\n     ");
		UMetaBuilder.manual().addLiteral(mAxisDirection, "GEOCENTRIC_X", 6, AxisDirection.GEOCENTRIC_X);
			mAxisDirection.getLiteral("GEOCENTRIC_X").setDocumentation("\r\n     * Axis positive direction is in the equatorial plane from the centre of the\r\n     * modelled earth towards the intersection of the equator with the prime meridian.\r\n     * [GeoAPI]\r\n     ");
		UMetaBuilder.manual().addLiteral(mAxisDirection, "GEOCENTRIC_Y", 7, AxisDirection.GEOCENTRIC_Y);
			mAxisDirection.getLiteral("GEOCENTRIC_Y").setDocumentation("\r\n     * Axis positive direction is in the equatorial plane from the centre of the\r\n     * modelled earth towards the intersection of the equator and the meridian &pi;/2\r\n     * radians eastwards from the prime meridian.\r\n     * [GeoAPI]\r\n     ");
		UMetaBuilder.manual().addLiteral(mAxisDirection, "GEOCENTRIC_Z", 8, AxisDirection.GEOCENTRIC_Z);
			mAxisDirection.getLiteral("GEOCENTRIC_Z").setDocumentation("\r\n     * Axis positive direction is from the centre of the modelled earth parallel to\r\n     * its rotation axis and towards its north pole.\r\n     * [GeoAPI]\r\n     ");
		UMetaBuilder.manual().addLiteral(mAxisDirection, "DISPLAY_LEFT", 9, AxisDirection.DISPLAY_LEFT);
			mAxisDirection.getLiteral("DISPLAY_LEFT").setDocumentation("\r\n     * Axis positive direction is right in display.\r\n     * [GeoAPI]\r\n     ");
		UMetaBuilder.manual().addLiteral(mAxisDirection, "DISPLAY_RIGHT", 10, AxisDirection.DISPLAY_RIGHT);
			mAxisDirection.getLiteral("DISPLAY_RIGHT").setDocumentation("\r\n     * Axis positive direction is left in display.\r\n     * [GeoAPI]\r\n     ");
		UMetaBuilder.manual().addLiteral(mAxisDirection, "DISPLAY_UP", 11, AxisDirection.DISPLAY_UP);
			mAxisDirection.getLiteral("DISPLAY_UP").setDocumentation("\r\n     * Axis positive direction is towards top of approximately vertical display surface.\r\n     * [GeoAPI]\r\n     ");
		UMetaBuilder.manual().addLiteral(mAxisDirection, "DISPLAY_DOWN", 12, AxisDirection.DISPLAY_DOWN);
			mAxisDirection.getLiteral("DISPLAY_DOWN").setDocumentation("\r\n     * Axis positive direction is towards bottom of approximately vertical display surface.\r\n     * [GeoAPI]\r\n     ");
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
		{		//Operations of CoordinateReferenceSystem
			UOperation operation = null;
			//operation : dimension(int)
			operation = UMetaBuilder.manual().createOperation("dimension", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((CoordinateReferenceSystem)instance).dimension();
				}
			});
				operation.setDocumentation(" returns the dimension of this CRS ");
				//Annotations for CoordinateReferenceSystem:dimension(int)
				operation.createAnnotation("const");
				mCoordinateReferenceSystem.getOperations().add(operation);
			//operation : getDirectionToNorth(Vector)
			operation = UMetaBuilder.manual().createOperation("getDirectionToNorth", false, MathPackage.theInstance.getVector(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((CoordinateReferenceSystem)instance).getDirectionToNorth();
				}
			});
				operation.setDocumentation(" returns a direction vector (x,y[,z]) that points to north and has a length of 1 meter");
				//Annotations for CoordinateReferenceSystem:getDirectionToNorth(Vector)
				operation.createAnnotation("const");
				mCoordinateReferenceSystem.getOperations().add(operation);
			//operation : directionToBearing(double, double, double, double)
			operation = UMetaBuilder.manual().createOperation("directionToBearing", false, TypeUtils.getPrimitiveType(double.class), 1, -1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((CoordinateReferenceSystem)instance).directionToBearing((double)parameter[0], (double)parameter[1], (double)parameter[2]);
				}
			});
				operation.setDocumentation(" returns the bearing of an direction vector as set of angles (radian) \r\n * @note the bearing refer to: the angle(s) away from North of a distant point as observed at the current point.\r\n * @returns double[1] if z != z (NaN) or double[2] if z == z\r\n *");
				//Annotations for CoordinateReferenceSystem:directionToBearing(double, double, double, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "x", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "y", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "z", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mCoordinateReferenceSystem.getOperations().add(operation);
			//operation : bearingToDirection(Vector, double, double)
			operation = UMetaBuilder.manual().createOperation("bearingToDirection", false, MathPackage.theInstance.getVector(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((CoordinateReferenceSystem)instance).bearingToDirection((double)parameter[0], (double)parameter[1]);
				}
			});
				operation.setDocumentation(" creates a direction vector that is rotated around yaw (radian) and pitch (radian)\r\n *  that points towards north, if both angles are 0 and rotates clockwise\r\n *  @note pitch is ignored if this is an 2D coordinate reference system\r\n * @param yaw rotation around the UP axis of the CRS (sometimes called azimuth) \r\n * @param pitch rotation around the RIGHT axis of the CRS\r\n * @return a 2 or 3 dimensional vector that corresponds to the vector against north, rotated with yaw and pitch radians and has a length of 1 meter\r\n ");
				//Annotations for CoordinateReferenceSystem:bearingToDirection(Vector, double, double)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "yaw", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "pitch", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mCoordinateReferenceSystem.getOperations().add(operation);
			//operation : copy(CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("copy", false, CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((CoordinateReferenceSystem)instance).copy();
				}
			});
				//Annotations for CoordinateReferenceSystem:copy(CoordinateReferenceSystem)
				operation.createAnnotation("const");
				mCoordinateReferenceSystem.getOperations().add(operation);
			//operation : toWKT(String)
			operation = UMetaBuilder.manual().createOperation("toWKT", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((CoordinateReferenceSystem)instance).toWKT();
				}
			});
				operation.setDocumentation(" Converts the CoordinateReferenceSystem into a WKT (Well Known Text) String, that can be parsed from external tools / api's, like geotools.  ");
				//Annotations for CoordinateReferenceSystem:toWKT(String)
				operation.createAnnotation("const");
				mCoordinateReferenceSystem.getOperations().add(operation);
			//operation : getDistance(double, Vector, Vector)
			operation = UMetaBuilder.manual().createOperation("getDistance", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((CoordinateReferenceSystem)instance).getDistance((Vector)parameter[0], (Vector)parameter[1]);
				}
			});
				operation.setDocumentation(" \r\n * returns the distance between two coordinates, given in \"this\" CRS \r\n * @return the distance in meters between the given points \r\n *");
				UMetaBuilder.manual().addParameter(operation, "loc1", MathPackage.theInstance.getVector(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "loc2", MathPackage.theInstance.getVector(), 0, 1, UDirectionType.IN);
				mCoordinateReferenceSystem.getOperations().add(operation);
			//operation : getDistanceAndAzimuth(Vector, Vector, Vector)
			operation = UMetaBuilder.manual().createOperation("getDistanceAndAzimuth", false, MathPackage.theInstance.getVector(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((CoordinateReferenceSystem)instance).getDistanceAndAzimuth((Vector)parameter[0], (Vector)parameter[1]);
				}
			});
				operation.setDocumentation("\r\n * Returns the distance and azimuth between both coordinates and thus is a convinience method for \r\n * getDistance(...) and getAzimuth(...)\r\n * @note this method should be used if both values are needed, since both calculations often perform almost the same steps\r\n * @return double[2] with return[0] == Distance in meter and return[1] == azimuth in radians. \r\n ");
				UMetaBuilder.manual().addParameter(operation, "loc1", MathPackage.theInstance.getVector(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "loc2", MathPackage.theInstance.getVector(), 0, 1, UDirectionType.IN);
				mCoordinateReferenceSystem.getOperations().add(operation);
		}
		{		//Operations of PolarCRS
			UOperation operation = null;
			//operation : toPolarCoordinates(Vector, Vector)
			operation = UMetaBuilder.manual().createOperation("toPolarCoordinates", false, MathPackage.theInstance.getVector(), 0, -1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((PolarCRS)instance).toPolarCoordinates((List<Vector>)parameter[0]);
				}
			});
				//Annotations for PolarCRS:toPolarCoordinates(Vector, Vector)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "vertices", MathPackage.theInstance.getVector(), 0, -1, UDirectionType.IN);
				mPolarCRS.getOperations().add(operation);
			//operation : toPolarCoordinates(Vector, Vector, Vector)
			operation = UMetaBuilder.manual().createOperation("toPolarCoordinates", false, MathPackage.theInstance.getVector(), 0, -1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((PolarCRS)instance).toPolarCoordinates((Vector)parameter[0], (List<Vector>)parameter[1]);
				}
			});
				//Annotations for PolarCRS:toPolarCoordinates(Vector, Vector, Vector)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "center", MathPackage.theInstance.getVector(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "vertices", MathPackage.theInstance.getVector(), 0, -1, UDirectionType.IN);
				mPolarCRS.getOperations().add(operation);
		}
	}



	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mCoordinateSystem.setSuperType(CorePackage.theInstance.getIdentifiedObject());
		mCoordinateReferenceSystem.setSuperType(CorePackage.theInstance.getIdentifiedObject());
		mWGS84CRS.setSuperType(CrsPackage.theInstance.getCoordinateReferenceSystem());
		mLocalCRS.setSuperType(CrsPackage.theInstance.getCoordinateReferenceSystem());
		mEngineeringCRS.setSuperType(CrsPackage.theInstance.getLocalCRS());
		mPolarCRS.setSuperType(CrsPackage.theInstance.getLocalCRS());
		mWGS842D.setSuperType(CrsPackage.theInstance.getWGS84CRS());
		mWGS843D.setSuperType(CrsPackage.theInstance.getWGS84CRS());
		mPolar2D.setSuperType(CrsPackage.theInstance.getPolarCRS());
		mPolar3D.setSuperType(CrsPackage.theInstance.getPolarCRS());
		mEngineering2D.setSuperType(CrsPackage.theInstance.getEngineeringCRS());
		mEngineering3D.setSuperType(CrsPackage.theInstance.getEngineeringCRS());
		mNativeCRS.setSuperType(CrsPackage.theInstance.getCoordinateReferenceSystem());
		
	}



	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UEnum getAxisDirection(){
		if (mAxisDirection == null){
			mAxisDirection = UCoreMetaRepository.getUEnumeration(AxisDirection.class);
		}
		return mAxisDirection;
	}
	/**
	* @generated
	*/
	public UClass getCoordinateSystemAxis(){
		if (mCoordinateSystemAxis == null){
			mCoordinateSystemAxis = UCoreMetaRepository.getUClass(CoordinateSystemAxis.class);
		}
		return mCoordinateSystemAxis;
	}
	/**
	* @generated
	*/
	public UClass getCoordinateSystem(){
		if (mCoordinateSystem == null){
			mCoordinateSystem = UCoreMetaRepository.getUClass(CoordinateSystem.class);
		}
		return mCoordinateSystem;
	}
	/**
	* @generated
	*/
	public UClass getCoordinateReferenceSystem(){
		if (mCoordinateReferenceSystem == null){
			mCoordinateReferenceSystem = UCoreMetaRepository.getUClass(CoordinateReferenceSystem.class);
		}
		return mCoordinateReferenceSystem;
	}
	/**
	* @generated
	*/
	public UClass getWGS84CRS(){
		if (mWGS84CRS == null){
			mWGS84CRS = UCoreMetaRepository.getUClass(WGS84CRS.class);
		}
		return mWGS84CRS;
	}
	/**
	* @generated
	*/
	public UClass getLocalCRS(){
		if (mLocalCRS == null){
			mLocalCRS = UCoreMetaRepository.getUClass(LocalCRS.class);
		}
		return mLocalCRS;
	}
	/**
	* @generated
	*/
	public UClass getEngineeringCRS(){
		if (mEngineeringCRS == null){
			mEngineeringCRS = UCoreMetaRepository.getUClass(EngineeringCRS.class);
		}
		return mEngineeringCRS;
	}
	/**
	* @generated
	*/
	public UClass getPolarCRS(){
		if (mPolarCRS == null){
			mPolarCRS = UCoreMetaRepository.getUClass(PolarCRS.class);
		}
		return mPolarCRS;
	}
	/**
	* @generated
	*/
	public UClass getWGS842D(){
		if (mWGS842D == null){
			mWGS842D = UCoreMetaRepository.getUClass(WGS842D.class);
		}
		return mWGS842D;
	}
	/**
	* @generated
	*/
	public UClass getWGS843D(){
		if (mWGS843D == null){
			mWGS843D = UCoreMetaRepository.getUClass(WGS843D.class);
		}
		return mWGS843D;
	}
	/**
	* @generated
	*/
	public UClass getPolar2D(){
		if (mPolar2D == null){
			mPolar2D = UCoreMetaRepository.getUClass(Polar2D.class);
		}
		return mPolar2D;
	}
	/**
	* @generated
	*/
	public UClass getPolar3D(){
		if (mPolar3D == null){
			mPolar3D = UCoreMetaRepository.getUClass(Polar3D.class);
		}
		return mPolar3D;
	}
	/**
	* @generated
	*/
	public UClass getEngineering2D(){
		if (mEngineering2D == null){
			mEngineering2D = UCoreMetaRepository.getUClass(Engineering2D.class);
		}
		return mEngineering2D;
	}
	/**
	* @generated
	*/
	public UClass getEngineering3D(){
		if (mEngineering3D == null){
			mEngineering3D = UCoreMetaRepository.getUClass(Engineering3D.class);
		}
		return mEngineering3D;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getCoordinateSystemAxis_direction(){
		if (mCoordinateSystemAxis_direction == null)
			mCoordinateSystemAxis_direction = getCoordinateSystemAxis().getFeature("direction");
		return mCoordinateSystemAxis_direction;
	}



	/**
	* @generated
	*/
	public UClass getNativeCRS(){
		if (mNativeCRS == null){
			mNativeCRS = UCoreMetaRepository.getUClass(NativeCRS.class);
		}
		return mNativeCRS;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCoordinateSystemAxis_name(){
		if (mCoordinateSystemAxis_name == null)
			mCoordinateSystemAxis_name = getCoordinateSystemAxis().getFeature("name");
		return mCoordinateSystemAxis_name;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCoordinateSystemAxis_minimumRange(){
		if (mCoordinateSystemAxis_minimumRange == null)
			mCoordinateSystemAxis_minimumRange = getCoordinateSystemAxis().getFeature("minimumRange");
		return mCoordinateSystemAxis_minimumRange;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCoordinateSystemAxis_maximumRange(){
		if (mCoordinateSystemAxis_maximumRange == null)
			mCoordinateSystemAxis_maximumRange = getCoordinateSystemAxis().getFeature("maximumRange");
		return mCoordinateSystemAxis_maximumRange;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCoordinateSystem_axes(){
		if (mCoordinateSystem_axes == null)
			mCoordinateSystem_axes = getCoordinateSystem().getFeature("axes");
		return mCoordinateSystem_axes;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCoordinateReferenceSystem_cs(){
		if (mCoordinateReferenceSystem_cs == null)
			mCoordinateReferenceSystem_cs = getCoordinateReferenceSystem().getFeature("cs");
		return mCoordinateReferenceSystem_cs;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLocalCRS_origin(){
		if (mLocalCRS_origin == null)
			mLocalCRS_origin = getLocalCRS().getFeature("origin");
		return mLocalCRS_origin;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLocalCRS_orientationOffset(){
		if (mLocalCRS_orientationOffset == null)
			mLocalCRS_orientationOffset = getLocalCRS().getFeature("orientationOffset");
		return mLocalCRS_orientationOffset;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getNativeCRS_wkt(){
		if (mNativeCRS_wkt == null)
			mNativeCRS_wkt = getNativeCRS().getFeature("wkt");
		return mNativeCRS_wkt;
	}
}
