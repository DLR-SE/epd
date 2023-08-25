package de.emir.model.universal.spatial.sf;

import de.emir.model.universal.CoreModel;
import de.emir.model.universal.SpatialModel;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.model.universal.spatial.sf.impl.LineStringImpl;
import de.emir.model.universal.spatial.sf.impl.LinearRingImpl;
import de.emir.model.universal.spatial.sf.impl.MultiGeometryImpl;
import de.emir.model.universal.spatial.sf.impl.MultiLineStringImpl;
import de.emir.model.universal.spatial.sf.impl.MultiPolygonImpl;
import de.emir.model.universal.spatial.sf.impl.PointImpl;
import de.emir.model.universal.spatial.sf.impl.PolygonImpl;
import de.emir.model.universal.spatial.sf.impl.WKTGeometryImpl;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;

/**
 *	@generated 
 */
public class SfPackage  
{
	
	/**
	 * @generated
	 */
	public static SfPackage theInstance = new SfPackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier MultiGeometry
		*/
		UClass MultiGeometry = SfPackage.theInstance.getMultiGeometry();
		/**
		* @generated
		* @return meta type for classifier Point
		*/
		UClass Point = SfPackage.theInstance.getPoint();
		/**
		* @generated
		* @return meta type for classifier LineString
		*/
		UClass LineString = SfPackage.theInstance.getLineString();
		/**
		* @generated
		* @return meta type for classifier MultiLineString
		*/
		UClass MultiLineString = SfPackage.theInstance.getMultiLineString();
		/**
		* @generated
		* @return meta type for classifier LinearRing
		*/
		UClass LinearRing = SfPackage.theInstance.getLinearRing();
		/**
		* @generated
		* @return meta type for classifier Polygon
		*/
		UClass Polygon = SfPackage.theInstance.getPolygon();
		/**
		* @generated
		* @return meta type for classifier MultiPolygon
		*/
		UClass MultiPolygon = SfPackage.theInstance.getMultiPolygon();
		/**
		* @generated
		* @return meta type for classifier WKTGeometry
		*/
		UClass WKTGeometry = SfPackage.theInstance.getWKTGeometry();
		
		/**
		 * @generated
		 * @return feature descriptor coordinate in type Point
		 */
		 UStructuralFeature Point_coordinate = SfPackage.theInstance.getPoint_coordinate();
		/**
		 * @generated
		 * @return feature descriptor points in type LineString
		 */
		 UStructuralFeature LineString_points = SfPackage.theInstance.getLineString_points();
		/**
		 * @generated
		 * @return feature descriptor lines in type MultiLineString
		 */
		 UStructuralFeature MultiLineString_lines = SfPackage.theInstance.getMultiLineString_lines();
		/**
		 * @generated
		 * @return feature descriptor shell in type Polygon
		 */
		 UStructuralFeature Polygon_shell = SfPackage.theInstance.getPolygon_shell();
		/**
		 * @generated
		 * @return feature descriptor holes in type Polygon
		 */
		 UStructuralFeature Polygon_holes = SfPackage.theInstance.getPolygon_holes();
		/**
		 * @generated
		 * @return feature descriptor polygons in type MultiPolygon
		 */
		 UStructuralFeature MultiPolygon_polygons = SfPackage.theInstance.getMultiPolygon_polygons();
		/**
		 * @generated
		 * @return feature descriptor wkt in type WKTGeometry
		 */
		 UStructuralFeature WKTGeometry_wkt = SfPackage.theInstance.getWKTGeometry_wkt();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mMultiGeometry = null;
	/**
	* @generated
	*/
	private UClass mPoint = null;
	/**
	* @generated
	*/
	private UClass mLineString = null;
	/**
	* @generated
	*/
	private UClass mMultiLineString = null;
	/**
	* @generated
	*/
	private UClass mLinearRing = null;
	/**
	* @generated
	*/
	private UClass mPolygon = null;
	/**
	* @generated
	*/
	private UClass mMultiPolygon = null;
	/**
	* @generated
	*/
	private UClass mWKTGeometry = null;
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	
	//Features for classifier Point
	/**
	 * @generated
	 */
	private UStructuralFeature mPoint_coordinate = null;
	
	//Features for classifier LineString
	/**
	 * @generated
	 */
	private UStructuralFeature mLineString_points = null;
	
	//Features for classifier MultiLineString
	/**
	 * @generated
	 */
	private UStructuralFeature mMultiLineString_lines = null;
	
	
	//Features for classifier Polygon
	/**
	 * @generated
	 */
	private UStructuralFeature mPolygon_shell = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mPolygon_holes = null;
	
	//Features for classifier MultiPolygon
	/**
	 * @generated
	 */
	private UStructuralFeature mMultiPolygon_polygons = null;
	
	//Features for classifier WKTGeometry
	/**
	 * @generated
	 */
	private UStructuralFeature mWKTGeometry_wkt = null;
	
	
	
	
	/**
	 * @generated
	 */
	public static SfPackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package SfPackage ...", 1);
		theInstance = new SfPackage();
		//initialize referenced models
		SpatialModel.init();
		CoreModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.universal.spatial.sf");
		p.getContent().add(theInstance.mMultiGeometry);
		p.getContent().add(theInstance.mPoint);
		p.getContent().add(theInstance.mLineString);
		p.getContent().add(theInstance.mMultiLineString);
		p.getContent().add(theInstance.mLinearRing);
		p.getContent().add(theInstance.mPolygon);
		p.getContent().add(theInstance.mMultiPolygon);
		p.getContent().add(theInstance.mWKTGeometry);
		p.freeze();
		
		
		
		ULog.debug(-1, "... package SfPackage initialized");
		
		return theInstance;
	}
	
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mMultiGeometry = UMetaBuilder.manual().createClass("MultiGeometry", true, MultiGeometry.class, MultiGeometryImpl.class);
		
		mPoint = UMetaBuilder.manual().createClass("Point", false, Point.class, PointImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mPoint, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new PointImpl();
				}
			});
			//Annotations for Point
			mPoint.createAnnotation("ComplexAttributeType");
			mPoint.createAnnotation("struct");
		
		mLineString = UMetaBuilder.manual().createClass("LineString", false, LineString.class, LineStringImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mLineString, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new LineStringImpl();
				}
			});
			//Annotations for LineString
			mLineString.createAnnotation("ComplexAttributeType");
			mLineString.createAnnotation("struct");
		
		mMultiLineString = UMetaBuilder.manual().createClass("MultiLineString", false, MultiLineString.class, MultiLineStringImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mMultiLineString, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new MultiLineStringImpl();
				}
			});
			//Annotations for MultiLineString
			mMultiLineString.createAnnotation("ComplexAttributeType");
			mMultiLineString.createAnnotation("struct");
		
		mLinearRing = UMetaBuilder.manual().createClass("LinearRing", false, LinearRing.class, LinearRingImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mLinearRing, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new LinearRingImpl();
				}
			});
			mLinearRing.setDocumentation(" Same as the LineString but has to be closed (first coordinate has to be the same as the last) ");
			//Annotations for LinearRing
			mLinearRing.createAnnotation("ComplexAttributeType");
			mLinearRing.createAnnotation("struct");
		
		mPolygon = UMetaBuilder.manual().createClass("Polygon", false, Polygon.class, PolygonImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mPolygon, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new PolygonImpl();
				}
			});
			//Annotations for Polygon
			mPolygon.createAnnotation("ComplexAttributeType");
			mPolygon.createAnnotation("struct");
		
		mMultiPolygon = UMetaBuilder.manual().createClass("MultiPolygon", false, MultiPolygon.class, MultiPolygonImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mMultiPolygon, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new MultiPolygonImpl();
				}
			});
			//Annotations for MultiPolygon
			mMultiPolygon.createAnnotation("ComplexAttributeType");
			mMultiPolygon.createAnnotation("struct");
		
		mWKTGeometry = UMetaBuilder.manual().createClass("WKTGeometry", false, WKTGeometry.class, WKTGeometryImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mWKTGeometry, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new WKTGeometryImpl();
				}
			});
			mWKTGeometry.setDocumentation(" The WKTGeometry is a helper geometry, where the geometry is specified using a WKT (Well known text) String\r\n * the internal geometry (getNativeGeometry()) is determinated at runtime\r\n ");
			//Annotations for WKTGeometry
			mWKTGeometry.createAnnotation("ComplexAttributeType");
			mWKTGeometry.createAnnotation("struct");
		
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of Point
			mPoint_coordinate = UMetaBuilder.manual().createFeature("coordinate", SpatialPackage.theInstance.getCoordinate(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mPoint_coordinate, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Point)instance).getCoordinate(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Point)instance).setCoordinate((Coordinate)value); } }
				);
			
			//Features of LineString
			mLineString_points = UMetaBuilder.manual().createFeature("points", SpatialPackage.theInstance.getCoordinateSequence(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mLineString_points, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((LineString)instance).getPoints(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((LineString)instance).setPoints((CoordinateSequence)value); } }
				);
			
			//Features of MultiLineString
			mMultiLineString_lines = UMetaBuilder.manual().createFeature("lines", SfPackage.theInstance.getLineString(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mMultiLineString_lines, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((MultiLineString)instance).getLines(); } }, 
						null
				);
			
			//Features of Polygon
			mPolygon_shell = UMetaBuilder.manual().createFeature("shell", SfPackage.theInstance.getLinearRing(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mPolygon_shell, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Polygon)instance).getShell(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Polygon)instance).setShell((LinearRing)value); } }
				);
			mPolygon_holes = UMetaBuilder.manual().createFeature("holes", SfPackage.theInstance.getLinearRing(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mPolygon_holes, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Polygon)instance).getHoles(); } }, 
						null
				);
			
			//Features of MultiPolygon
			mMultiPolygon_polygons = UMetaBuilder.manual().createFeature("polygons", SfPackage.theInstance.getPolygon(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mMultiPolygon_polygons, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((MultiPolygon)instance).getPolygons(); } }, 
						null
				);
			
			//Features of WKTGeometry
			mWKTGeometry_wkt = UMetaBuilder.manual().createFeature("wkt", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mWKTGeometry_wkt, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((WKTGeometry)instance).getWkt(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((WKTGeometry)instance).setWkt((String)value); } }
				);
			
		}
		{ //assign features
			mPoint.getStructuralFeatures().add(mPoint_coordinate);
			mLineString.getStructuralFeatures().add(mLineString_points);
			mMultiLineString.getStructuralFeatures().add(mMultiLineString_lines);
			mPolygon.getStructuralFeatures().add(mPolygon_shell);
			mPolygon.getStructuralFeatures().add(mPolygon_holes);
			mMultiPolygon.getStructuralFeatures().add(mMultiPolygon_polygons);
			mWKTGeometry.getStructuralFeatures().add(mWKTGeometry_wkt);
		}
		
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
		{		//Operations of LinearRing
			UOperation operation = null;
			//operation : isClosed(boolean)
			operation = UMetaBuilder.manual().createOperation("isClosed", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((LinearRing)instance).isClosed();
				}
			});
				mLinearRing.getOperations().add(operation);
			//operation : close(void)
			operation = UMetaBuilder.manual().createOperation("close", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((LinearRing)instance).close();
					return null;
				}
			});
				operation.setDocumentation(" closes the ring by copying the first coordinate to the point list, if not already closed ");
				mLinearRing.getOperations().add(operation);
		}
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mMultiGeometry.setSuperType(SpatialPackage.theInstance.getGeometry());
		mPoint.setSuperType(SpatialPackage.theInstance.getGeometry());
		mLineString.setSuperType(SpatialPackage.theInstance.getGeometry());
		mMultiLineString.setSuperType(SfPackage.theInstance.getMultiGeometry());
		mLinearRing.setSuperType(SfPackage.theInstance.getLineString());
		mPolygon.setSuperType(SpatialPackage.theInstance.getGeometry());
		mMultiPolygon.setSuperType(SfPackage.theInstance.getMultiGeometry());
		mWKTGeometry.setSuperType(SpatialPackage.theInstance.getGeometry());
		
	}
	
	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getMultiGeometry(){
		if (mMultiGeometry == null){
			mMultiGeometry = UCoreMetaRepository.getUClass(MultiGeometry.class);
		}
		return mMultiGeometry;
	}
	/**
	* @generated
	*/
	public UClass getPoint(){
		if (mPoint == null){
			mPoint = UCoreMetaRepository.getUClass(Point.class);
		}
		return mPoint;
	}
	/**
	* @generated
	*/
	public UClass getLineString(){
		if (mLineString == null){
			mLineString = UCoreMetaRepository.getUClass(LineString.class);
		}
		return mLineString;
	}
	/**
	* @generated
	*/
	public UClass getMultiLineString(){
		if (mMultiLineString == null){
			mMultiLineString = UCoreMetaRepository.getUClass(MultiLineString.class);
		}
		return mMultiLineString;
	}
	/**
	* @generated
	*/
	public UClass getLinearRing(){
		if (mLinearRing == null){
			mLinearRing = UCoreMetaRepository.getUClass(LinearRing.class);
		}
		return mLinearRing;
	}
	/**
	* @generated
	*/
	public UClass getPolygon(){
		if (mPolygon == null){
			mPolygon = UCoreMetaRepository.getUClass(Polygon.class);
		}
		return mPolygon;
	}
	/**
	* @generated
	*/
	public UClass getMultiPolygon(){
		if (mMultiPolygon == null){
			mMultiPolygon = UCoreMetaRepository.getUClass(MultiPolygon.class);
		}
		return mMultiPolygon;
	}
	/**
	* @generated
	*/
	public UClass getWKTGeometry(){
		if (mWKTGeometry == null){
			mWKTGeometry = UCoreMetaRepository.getUClass(WKTGeometry.class);
		}
		return mWKTGeometry;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getPoint_coordinate(){
		if (mPoint_coordinate == null)
			mPoint_coordinate = getPoint().getFeature("coordinate");
		return mPoint_coordinate;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getLineString_points(){
		if (mLineString_points == null)
			mLineString_points = getLineString().getFeature("points");
		return mLineString_points;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMultiLineString_lines(){
		if (mMultiLineString_lines == null)
			mMultiLineString_lines = getMultiLineString().getFeature("lines");
		return mMultiLineString_lines;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getPolygon_shell(){
		if (mPolygon_shell == null)
			mPolygon_shell = getPolygon().getFeature("shell");
		return mPolygon_shell;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getPolygon_holes(){
		if (mPolygon_holes == null)
			mPolygon_holes = getPolygon().getFeature("holes");
		return mPolygon_holes;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getMultiPolygon_polygons(){
		if (mMultiPolygon_polygons == null)
			mMultiPolygon_polygons = getMultiPolygon().getFeature("polygons");
		return mMultiPolygon_polygons;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getWKTGeometry_wkt(){
		if (mWKTGeometry_wkt == null)
			mWKTGeometry_wkt = getWKTGeometry().getFeature("wkt");
		return mWKTGeometry_wkt;
	}
}
