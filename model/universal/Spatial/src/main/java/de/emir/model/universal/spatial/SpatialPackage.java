package de.emir.model.universal.spatial;

import de.emir.model.universal.CRSModel;
import de.emir.tuml.ucore.runtime.UDirectionType;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.model.universal.CoreModel;
import de.emir.tuml.ucore.runtime.UEnum;
import de.emir.tuml.ucore.runtime.UEnumerator;
import de.emir.model.universal.MathModel;
import de.emir.model.universal.UnitsModel;
import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.tuml.ucore.runtime.impl.UStructuralFeatureImpl;
import de.emir.model.universal.crs.CrsPackage;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.spatial.impl.EFeatureImpl;
import de.emir.model.universal.spatial.impl.EnvelopeImpl;
import de.emir.model.universal.spatial.impl.GenericEFeatureImpl;
import de.emir.model.universal.spatial.impl.GeometryImpl;
import de.emir.model.universal.spatial.impl.GraphEdgeImpl;
import de.emir.model.universal.spatial.impl.GraphImpl;
import de.emir.tuml.ucore.runtime.access.IOperationInvoker;
import de.emir.model.universal.spatial.impl.GraphNodeImpl;
import de.emir.model.universal.spatial.impl.PoseImpl;
import de.emir.model.universal.spatial.impl.SpatialLayerImpl;
import de.emir.model.universal.units.Orientation;
import de.emir.model.universal.units.UnitsPackage;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.model.universal.math.Vector;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.math.MathPackage;
import de.emir.model.universal.spatial.CoordinateSequence;
import de.emir.model.universal.math.Vector2D;
import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.math.Vector3D;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.model.universal.spatial.GenericEFeature;
import de.emir.model.universal.spatial.EFeature;
import de.emir.tuml.ucore.runtime.UInterface;
import de.emir.model.universal.spatial.Graph;
import de.emir.model.universal.spatial.Geometry;
import de.emir.model.universal.spatial.GraphNode;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.spatial.SpatialLayerContainer;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.model.universal.spatial.impl.CoordinateSequenceImpl;
import de.emir.model.universal.spatial.GraphEdge;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.access.IFeatureGetter;
import de.emir.tuml.ucore.runtime.access.IFeatureSetter;
import de.emir.model.universal.spatial.SpatialLayer;
import de.emir.tuml.ucore.runtime.access.IInstanceCreator;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.Length;
import de.emir.tuml.ucore.runtime.utils.UMetaBuilder;
import de.emir.model.universal.units.Distance;
import de.emir.model.universal.units.Angle;
import de.emir.model.universal.units.Rotation;
import java.util.List;

/**
 *	@generated 
 */
public class SpatialPackage  
{
	
	/**
	 * @generated
	 */
	public static SpatialPackage theInstance = new SpatialPackage().init();
	
	/**
	 * @generated
	 */
	public interface Literals {
		/**
		* @generated
		* @return meta type for classifier Coordinate
		*/
		UClass Coordinate = SpatialPackage.theInstance.getCoordinate();
		/**
		* @generated
		* @return meta type for classifier Pose
		*/
		UClass Pose = SpatialPackage.theInstance.getPose();
		/**
		* @generated
		* @return meta type for classifier Envelope
		*/
		UClass Envelope = SpatialPackage.theInstance.getEnvelope();
		/**
		* @generated
		* @return meta type for classifier Geometry
		*/
		UClass Geometry = SpatialPackage.theInstance.getGeometry();
		/**
		* @generated
		* @return meta type for classifier CoordinateSequence
		*/
		UClass CoordinateSequence = SpatialPackage.theInstance.getCoordinateSequence();
		/**
		* @generated
		* @return meta type for classifier EFeature
		*/
		UClass EFeature = SpatialPackage.theInstance.getEFeature();
		/**
		* @generated
		* @return meta type for classifier GenericEFeature
		*/
		UClass GenericEFeature = SpatialPackage.theInstance.getGenericEFeature();
		/**
		* @generated
		* @return meta type for classifier SpatialLayer
		*/
		UClass SpatialLayer = SpatialPackage.theInstance.getSpatialLayer();
		/**
		 * @generated
		 * @return meta type for interface SpatialLayerContainer
		 */
		UInterface SpatialLayerContainer = SpatialPackage.theInstance.getSpatialLayerContainer();
		/**
		* @generated
		* @return meta type for classifier GraphEdge
		*/
		UClass GraphEdge = SpatialPackage.theInstance.getGraphEdge();
		/**
		* @generated
		* @return meta type for classifier GraphNode
		*/
		UClass GraphNode = SpatialPackage.theInstance.getGraphNode();
		/**
		* @generated
		* @return meta type for classifier Graph
		*/
		UClass Graph = SpatialPackage.theInstance.getGraph();
		/**
		 * @generated
		 * @return feature descriptor x in type Coordinate
		 */
		 UStructuralFeature Coordinate_x = SpatialPackage.theInstance.getCoordinate_x();
		/**
		 * @generated
		 * @return feature descriptor y in type Coordinate
		 */
		 UStructuralFeature Coordinate_y = SpatialPackage.theInstance.getCoordinate_y();
		/**
		 * @generated
		 * @return feature descriptor z in type Coordinate
		 */
		 UStructuralFeature Coordinate_z = SpatialPackage.theInstance.getCoordinate_z();
		/**
		 * @generated
		 * @return feature descriptor crs in type Coordinate
		 */
		 UStructuralFeature Coordinate_crs = SpatialPackage.theInstance.getCoordinate_crs();
		/**
		 * @generated
		 * @return feature descriptor coordinate in type Pose
		 */
		 UStructuralFeature Pose_coordinate = SpatialPackage.theInstance.getPose_coordinate();
		/**
		 * @generated
		 * @return feature descriptor orientation in type Pose
		 */
		 UStructuralFeature Pose_orientation = SpatialPackage.theInstance.getPose_orientation();
		/**
		 * @generated
		 * @return feature descriptor minPoint in type Envelope
		 */
		 UStructuralFeature Envelope_minPoint = SpatialPackage.theInstance.getEnvelope_minPoint();
		/**
		 * @generated
		 * @return feature descriptor maxPoint in type Envelope
		 */
		 UStructuralFeature Envelope_maxPoint = SpatialPackage.theInstance.getEnvelope_maxPoint();
		/**
		 * @generated
		 * @return feature descriptor crs in type CoordinateSequence
		 */
		 UStructuralFeature CoordinateSequence_crs = SpatialPackage.theInstance.getCoordinateSequence_crs();
		/**
		 * @generated
		 * @return feature descriptor xCoordinates in type CoordinateSequence
		 */
		 UStructuralFeature CoordinateSequence_xCoordinates = SpatialPackage.theInstance.getCoordinateSequence_xCoordinates();
		/**
		 * @generated
		 * @return feature descriptor yCoordinates in type CoordinateSequence
		 */
		 UStructuralFeature CoordinateSequence_yCoordinates = SpatialPackage.theInstance.getCoordinateSequence_yCoordinates();
		/**
		 * @generated
		 * @return feature descriptor zCoordinates in type CoordinateSequence
		 */
		 UStructuralFeature CoordinateSequence_zCoordinates = SpatialPackage.theInstance.getCoordinateSequence_zCoordinates();
		/**
		 * @generated
		 * @return feature descriptor featureType in type EFeature
		 */
		 UStructuralFeature EFeature_featureType = SpatialPackage.theInstance.getEFeature_featureType();
		/**
		 * @generated
		 * @return feature descriptor geometry in type EFeature
		 */
		 UStructuralFeature EFeature_geometry = SpatialPackage.theInstance.getEFeature_geometry();
		/**
		 * @generated
		 * @return feature descriptor name in type SpatialLayer
		 */
		 UStructuralFeature SpatialLayer_name = SpatialPackage.theInstance.getSpatialLayer_name();
		/**
		 * @generated
		 * @return feature descriptor features in type SpatialLayer
		 */
		 UStructuralFeature SpatialLayer_features = SpatialPackage.theInstance.getSpatialLayer_features();
		/**
		 * @generated
		 * @return feature descriptor coordinate in type GraphNode
		 */
		 UStructuralFeature GraphNode_coordinate = SpatialPackage.theInstance.getGraphNode_coordinate();
		/**
		 * @generated
		 * @return feature descriptor nodeA in type GraphEdge
		 */
		 UStructuralFeature GraphEdge_nodeA = SpatialPackage.theInstance.getGraphEdge_nodeA();
		/**
		 * @generated
		 * @return feature descriptor nodeB in type GraphEdge
		 */
		 UStructuralFeature GraphEdge_nodeB = SpatialPackage.theInstance.getGraphEdge_nodeB();
		/**
		 * @generated
		 * @return feature descriptor id in type GraphNode
		 */
		 UStructuralFeature GraphNode_id = SpatialPackage.theInstance.getGraphNode_id();
		/**
		 * @generated
		 * @return feature descriptor nodes in type Graph
		 */
		 UStructuralFeature Graph_nodes = SpatialPackage.theInstance.getGraph_nodes();
		/**
		 * @generated
		 * @return feature descriptor id in type GraphEdge
		 */
		 UStructuralFeature GraphEdge_id = SpatialPackage.theInstance.getGraphEdge_id();
		/**
		 * @generated
		 * @return feature descriptor edges in type Graph
		 */
		 UStructuralFeature Graph_edges = SpatialPackage.theInstance.getGraph_edges();
		/**
		 * @generated
		 * @return feature descriptor edges in type GraphNode
		 */
		 UStructuralFeature GraphNode_edges = SpatialPackage.theInstance.getGraphNode_edges();
		
	} 
	
	//////////////////////////////////////////////////////////////////////
	//						Classifiers									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	private UClass mCoordinate = null;
	/**
	* @generated
	*/
	private UClass mPose = null;
	/**
	* @generated
	*/
	private UClass mEnvelope = null;
	/**
	* @generated
	*/
	private UClass mGeometry = null;
	/**
	* @generated
	*/
	private UClass mCoordinateSequence = null;
	/**
	* @generated
	*/
	private UClass mEFeature = null;
	/**
	* @generated
	*/
	private UClass mGenericEFeature = null;
	/**
	* @generated
	*/
	private UClass mSpatialLayer = null;
	/**
	 * @generated
	 */
	private UInterface mSpatialLayerContainer = null;
	/**
	* @generated
	*/
	private UClass mGraphEdge = null;
	/**
	* @generated
	*/
	private UClass mGraphNode = null;
	/**
	* @generated
	*/
	private UClass mGraph = null;
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures									//
	//////////////////////////////////////////////////////////////////////
	
	
	//Features for classifier Coordinate
	/**
	 * @generated
	 */
	private UStructuralFeature mCoordinate_x = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mCoordinate_y = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mCoordinate_z = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mCoordinate_crs = null;
	
	//Features for classifier Pose
	/**
	 * @generated
	 */
	private UStructuralFeature mPose_coordinate = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mPose_orientation = null;
	
	//Features for classifier Envelope
	/**
	 * @generated
	 */
	private UStructuralFeature mEnvelope_minPoint = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mEnvelope_maxPoint = null;
	
	/**
	 * @generated
	 */
	private UStructuralFeature mCoordinateSequence_crs = null;
	
	//Features for classifier EFeature
	/**
	 * @generated
	 */
	private UStructuralFeature mEFeature_featureType = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mCoordinateSequence_xCoordinates = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mCoordinateSequence_yCoordinates = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mEFeature_geometry = null;
	
	
	//Features for classifier SpatialLayer
	/**
	 * @generated
	 */
	private UStructuralFeature mSpatialLayer_name = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mCoordinateSequence_zCoordinates = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mSpatialLayer_features = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mGraphEdge_id = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mGraphEdge_nodeA = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mGraphEdge_nodeB = null;
	
	/**
	 * @generated
	 */
	private UStructuralFeature mGraphNode_id = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mGraph_nodes = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mGraph_edges = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mGraphNode_edges = null;
	/**
	 * @generated
	 */
	private UStructuralFeature mGraphNode_coordinate = null;
	/**
	 * @generated
	 */
	public static SpatialPackage init(){
		if (theInstance != null)
			return theInstance;
		
		ULog.debug("initialize package SpatialPackage ...");
		theInstance = new SpatialPackage();
		//initialize referenced models
		MathModel.init();
		UnitsModel.init();
		CRSModel.init();
		
		theInstance.createClassifier();
		theInstance.createFeatures();
		theInstance.createOperations();
		theInstance.buildHierarchies();
		UPackage p = UCoreMetaRepository.getPackage("de.emir.model.universal.spatial");
		p.getContent().add(theInstance.mCoordinate);
		p.getContent().add(theInstance.mPose);
		p.getContent().add(theInstance.mEnvelope);
		p.getContent().add(theInstance.mGeometry);
		p.getContent().add(theInstance.mCoordinateSequence);
		p.getContent().add(theInstance.mEFeature);
		p.getContent().add(theInstance.mGenericEFeature);
		p.getContent().add(theInstance.mSpatialLayer);
		p.getContent().add(theInstance.mSpatialLayerContainer);
		p.getContent().add(theInstance.mGraphEdge);
		p.getContent().add(theInstance.mGraphNode);
		p.getContent().add(theInstance.mGraph);
		p.freeze();
		
		
		
		ULog.debug("... package SpatialPackage initialized");
		
		return theInstance;
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createClassifier(){
		mCoordinate = UMetaBuilder.manual().createClass("Coordinate", false, Coordinate.class, CoordinateImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mCoordinate, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new CoordinateImpl();
				}
			});
			//Annotations for Coordinate
			mCoordinate.createAnnotation("ComplexAttributeType");
			mCoordinate.createAnnotation("struct");
		
		mPose = UMetaBuilder.manual().createClass("Pose", false, Pose.class, PoseImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mPose, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new PoseImpl();
				}
			});
			//Annotations for Pose
			mPose.createAnnotation("ComplexAttributeType");
		
		mEnvelope = UMetaBuilder.manual().createClass("Envelope", false, Envelope.class, EnvelopeImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mEnvelope, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new EnvelopeImpl();
				}
			});
			//Annotations for Envelope
			mEnvelope.createAnnotation("ComplexAttributeType");
			mEnvelope.createAnnotation("struct");
		
		mGeometry = UMetaBuilder.manual().createClass("Geometry", true, Geometry.class, GeometryImpl.class);
		
		mCoordinateSequence = UMetaBuilder.manual().createClass("CoordinateSequence", false, CoordinateSequence.class, CoordinateSequenceImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mCoordinateSequence, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new CoordinateSequenceImpl();
				}
			});
			mCoordinateSequence.setDocumentation("\r\n * A coordinate sequence serves as container for the coordinates, forming a geometry\r\n ");
			//Annotations for CoordinateSequence
			mCoordinateSequence.createAnnotation("ComplexAttributeType");
			mCoordinateSequence.createAnnotation("struct");
		
		mEFeature = UMetaBuilder.manual().createClass("EFeature", true, EFeature.class, EFeatureImpl.class);
		
		mGenericEFeature = UMetaBuilder.manual().createClass("GenericEFeature", false, GenericEFeature.class, GenericEFeatureImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mGenericEFeature, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new GenericEFeatureImpl();
				}
			});
			//Annotations for GenericEFeature
			mGenericEFeature.createAnnotation("FeatureType");
		
		mSpatialLayer = UMetaBuilder.manual().createClass("SpatialLayer", false, SpatialLayer.class, SpatialLayerImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mSpatialLayer, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new SpatialLayerImpl();
				}
			});
			//Annotations for SpatialLayer
			mSpatialLayer.createAnnotation("FeatureType");
		
		mSpatialLayerContainer = UMetaBuilder.manual().createInterface("SpatialLayerContainer", SpatialLayerContainer.class);
			mSpatialLayerContainer.setDocumentation(" \r\n * The Abstract class SpatialLayerContainer can be used to provide \r\n * SpatialLayers, which are dynamically loaded. \r\n * This abstract class could be used by a user interface to display \r\n * all relevant layers, even if they are not part of the persistent model. \r\n ");
		
		mGraphEdge = UMetaBuilder.manual().createClass("GraphEdge", false, GraphEdge.class, GraphEdgeImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mGraphEdge, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new GraphEdgeImpl();
				}
			});
		
		mGraphNode = UMetaBuilder.manual().createClass("GraphNode", false, GraphNode.class, GraphNodeImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mGraphNode, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new GraphNodeImpl();
				}
			});
		
		mGraph = UMetaBuilder.manual().createClass("Graph", false, Graph.class, GraphImpl.class);
			UMetaBuilder.manual().setInstanceCreator(mGraph, new IInstanceCreator() {
				@Override
				public UObject createNewInstance() {
					return new GraphImpl();
				}
			});
		
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createFeatures(){
		{//create features
			//Features of Coordinate
			mCoordinate_x = UMetaBuilder.manual().createFeature("x", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCoordinate_x, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Coordinate)instance).getX(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Coordinate)instance).setX((double)value); } }
				);
				//Annotations for Coordinate:x
				mCoordinate_x.createAnnotationDetail("NumberFormat", "format", "%1.7f");
			mCoordinate_y = UMetaBuilder.manual().createFeature("y", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCoordinate_y, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Coordinate)instance).getY(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Coordinate)instance).setY((double)value); } }
				);
				//Annotations for Coordinate:y
				mCoordinate_y.createAnnotationDetail("NumberFormat", "format", "%1.7f");
			mCoordinate_z = UMetaBuilder.manual().createFeature("z", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCoordinate_z, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Coordinate)instance).getZ(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Coordinate)instance).setZ((double)value); } }
				);
				//Annotations for Coordinate:z
				mCoordinate_z.createAnnotationDetail("NumberFormat", "format", "%1.7f");
			mCoordinate_crs = UMetaBuilder.manual().createFeature("crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCoordinate_crs, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Coordinate)instance).getCrs(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Coordinate)instance).setCrs((CoordinateReferenceSystem)value); } }
				);
				mCoordinate_crs.setDocumentation(" references the current coordinate system, and therefore how x,y,z has to be read\r\n * if this value is set to null, a cartesian coordinate system (EngineeringCRS - default) is assumed\r\n ");
			
			//Features of Pose
			mPose_coordinate = UMetaBuilder.manual().createFeature("coordinate", SpatialPackage.theInstance.getCoordinate(), UAssociationType.COMPOSITION, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mPose_coordinate, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Pose)instance).getCoordinate(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Pose)instance).setCoordinate((Coordinate)value); } }
				);
			mPose_orientation = UMetaBuilder.manual().createFeature("orientation", UnitsPackage.theInstance.getOrientation(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mPose_orientation, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Pose)instance).getOrientation(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Pose)instance).setOrientation((Orientation)value); } }
				);
			
			//Features of Envelope
			mEnvelope_minPoint = UMetaBuilder.manual().createFeature("minPoint", SpatialPackage.theInstance.getCoordinate(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEnvelope_minPoint, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Envelope)instance).getMinPoint(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Envelope)instance).setMinPoint((Coordinate)value); } }
				);
			mEnvelope_maxPoint = UMetaBuilder.manual().createFeature("maxPoint", SpatialPackage.theInstance.getCoordinate(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEnvelope_maxPoint, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Envelope)instance).getMaxPoint(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((Envelope)instance).setMaxPoint((Coordinate)value); } }
				);
			
			//Features of CoordinateSequence
			mCoordinateSequence_crs = UMetaBuilder.manual().createFeature("crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), UAssociationType.PROPERTY, 1, 1);
				UMetaBuilder.manual().setFeatureAccessor(mCoordinateSequence_crs, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CoordinateSequence)instance).getCrs(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((CoordinateSequence)instance).setCrs((CoordinateReferenceSystem)value); } }
				);
			mCoordinateSequence_xCoordinates = UMetaBuilder.manual().createFeature("xCoordinates", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mCoordinateSequence_xCoordinates, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CoordinateSequence)instance).getXCoordinates(); } }, 
						null
				);
				mCoordinateSequence_xCoordinates.setReadOnly(true);
			mCoordinateSequence_yCoordinates = UMetaBuilder.manual().createFeature("yCoordinates", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mCoordinateSequence_yCoordinates, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CoordinateSequence)instance).getYCoordinates(); } }, 
						null
				);
				mCoordinateSequence_yCoordinates.setReadOnly(true);
			mCoordinateSequence_zCoordinates = UMetaBuilder.manual().createFeature("zCoordinates", TypeUtils.getPrimitiveType(double.class), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mCoordinateSequence_zCoordinates, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((CoordinateSequence)instance).getZCoordinates(); } }, 
						null
				);
				mCoordinateSequence_zCoordinates.setReadOnly(true);
			
			//Features of EFeature
			mEFeature_featureType = UMetaBuilder.manual().createFeature("featureType", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEFeature_featureType, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((EFeature)instance).getFeatureType(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((EFeature)instance).setFeatureType((String)value); } }
				);
			mEFeature_geometry = UMetaBuilder.manual().createFeature("geometry", SpatialPackage.theInstance.getGeometry(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mEFeature_geometry, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((EFeature)instance).getGeometry(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((EFeature)instance).setGeometry((Geometry)value); } }
				);
			
			//Features of SpatialLayer
			mSpatialLayer_name = UMetaBuilder.manual().createFeature("name", TypeUtils.getPrimitiveType(String.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mSpatialLayer_name, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((SpatialLayer)instance).getName(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((SpatialLayer)instance).setName((String)value); } }
				);
			mSpatialLayer_features = UMetaBuilder.manual().createFeature("features", SpatialPackage.theInstance.getEFeature(), UAssociationType.COMPOSITION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mSpatialLayer_features, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((SpatialLayer)instance).getFeatures(); } }, 
						null
				);
			
			//Features of GraphEdge
			mGraphEdge_id = UMetaBuilder.manual().createFeature("id", TypeUtils.getPrimitiveType(long.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mGraphEdge_id, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((GraphEdge)instance).getId(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((GraphEdge)instance).setId((long)value); } }
				);
			mGraphEdge_nodeA = UMetaBuilder.manual().createFeature("nodeA", SpatialPackage.theInstance.getGraphNode(), UAssociationType.AGGREGATION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mGraphEdge_nodeA, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((GraphEdge)instance).getNodeA(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((GraphEdge)instance).setNodeA((GraphNode)value); } }
				);
			mGraphEdge_nodeB = UMetaBuilder.manual().createFeature("nodeB", SpatialPackage.theInstance.getGraphNode(), UAssociationType.AGGREGATION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mGraphEdge_nodeB, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((GraphEdge)instance).getNodeB(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((GraphEdge)instance).setNodeB((GraphNode)value); } }
				);
			
			//Features of GraphNode
			mGraphNode_id = UMetaBuilder.manual().createFeature("id", TypeUtils.getPrimitiveType(long.class), UAssociationType.PROPERTY, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mGraphNode_id, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((GraphNode)instance).getId(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((GraphNode)instance).setId((long)value); } }
				);
			mGraphNode_coordinate = UMetaBuilder.manual().createFeature("coordinate", SpatialPackage.theInstance.getCoordinate(), UAssociationType.COMPOSITION, 0, 1);
				UMetaBuilder.manual().setFeatureAccessor(mGraphNode_coordinate, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((GraphNode)instance).getCoordinate(); } }, 
						new IFeatureSetter() { @Override public void set(UObject instance, Object value) { ((GraphNode)instance).setCoordinate((Coordinate)value); } }
				);
			mGraphNode_edges = UMetaBuilder.manual().createFeature("edges", SpatialPackage.theInstance.getGraphEdge(), UAssociationType.AGGREGATION, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mGraphNode_edges, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((GraphNode)instance).getEdges(); } }, 
						null
				);
			
			//Features of Graph
			mGraph_nodes = UMetaBuilder.manual().createFeature("nodes", SpatialPackage.theInstance.getGraphNode(), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mGraph_nodes, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Graph)instance).getNodes(); } }, 
						null
				);
			mGraph_edges = UMetaBuilder.manual().createFeature("edges", SpatialPackage.theInstance.getGraphEdge(), UAssociationType.PROPERTY, 0, -1);
				UMetaBuilder.manual().setFeatureAccessor(mGraph_edges, 
						new IFeatureGetter() { @Override public Object get(UObject instance) { return ((Graph)instance).getEdges(); } }, 
						null
				);
			
		}
		{ //assign features
			mCoordinate.getStructuralFeatures().add(mCoordinate_x);
			mCoordinate.getStructuralFeatures().add(mCoordinate_y);
			mCoordinate.getStructuralFeatures().add(mCoordinate_z);
			mCoordinate.getStructuralFeatures().add(mCoordinate_crs);
			mPose.getStructuralFeatures().add(mPose_coordinate);
			mPose.getStructuralFeatures().add(mPose_orientation);
			mEnvelope.getStructuralFeatures().add(mEnvelope_minPoint);
			mEnvelope.getStructuralFeatures().add(mEnvelope_maxPoint);
			mCoordinateSequence.getStructuralFeatures().add(mCoordinateSequence_crs);
			mCoordinateSequence.getStructuralFeatures().add(mCoordinateSequence_xCoordinates);
			mCoordinateSequence.getStructuralFeatures().add(mCoordinateSequence_yCoordinates);
			mCoordinateSequence.getStructuralFeatures().add(mCoordinateSequence_zCoordinates);
			mEFeature.getStructuralFeatures().add(mEFeature_featureType);
			mEFeature.getStructuralFeatures().add(mEFeature_geometry);
			mSpatialLayer.getStructuralFeatures().add(mSpatialLayer_name);
			mSpatialLayer.getStructuralFeatures().add(mSpatialLayer_features);
			mGraphEdge.getStructuralFeatures().add(mGraphEdge_id);
			mGraphEdge.getStructuralFeatures().add(mGraphEdge_nodeA);
			mGraphEdge.getStructuralFeatures().add(mGraphEdge_nodeB);
			mGraphNode.getStructuralFeatures().add(mGraphNode_id);
			mGraphNode.getStructuralFeatures().add(mGraphNode_coordinate);
			mGraphNode.getStructuralFeatures().add(mGraphNode_edges);
			mGraph.getStructuralFeatures().add(mGraph_nodes);
			mGraph.getStructuralFeatures().add(mGraph_edges);
		}
		
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void createOperations()
	{
		{		//Operations of Coordinate
			UOperation operation = null;
			//operation : getAzimuth(Angle, Coordinate)
			operation = UMetaBuilder.manual().createOperation("getAzimuth", false, UnitsPackage.theInstance.getAngle(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Coordinate)instance).getAzimuth((Coordinate)parameter[0]);
				}
			});
				//Annotations for Coordinate:getAzimuth(Angle, Coordinate)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mCoordinate.getOperations().add(operation);
			//operation : getDistance(Distance, Coordinate)
			operation = UMetaBuilder.manual().createOperation("getDistance", false, UnitsPackage.theInstance.getDistance(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Coordinate)instance).getDistance((Coordinate)parameter[0]);
				}
			});
				//Annotations for Coordinate:getDistance(Distance, Coordinate)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mCoordinate.getOperations().add(operation);
			//operation : getTarget(Coordinate, Distance, Angle)
			operation = UMetaBuilder.manual().createOperation("getTarget", false, SpatialPackage.theInstance.getCoordinate(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Coordinate)instance).getTarget((Distance)parameter[0], (Angle)parameter[1]);
				}
			});
				//Annotations for Coordinate:getTarget(Coordinate, Distance, Angle)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "distance", UnitsPackage.theInstance.getDistance(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "azimuth", UnitsPackage.theInstance.getAngle(), 0, 1, UDirectionType.IN);
				mCoordinate.getOperations().add(operation);
			//operation : dimension(int)
			operation = UMetaBuilder.manual().createOperation("dimension", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Coordinate)instance).dimension();
				}
			});
				//Annotations for Coordinate:dimension(int)
				operation.createAnnotation("const");
				mCoordinate.getOperations().add(operation);
			//operation : getLatitude(double)
			operation = UMetaBuilder.manual().createOperation("getLatitude", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Coordinate)instance).getLatitude();
				}
			});
				operation.setDocumentation(" \r\n * returns the longitude (WGS84) of this coordinate.\r\n * depending on the current crs, this includes a coordinate transformation \r\n ");
				//Annotations for Coordinate:getLatitude(double)
				operation.createAnnotation("const");
				mCoordinate.getOperations().add(operation);
			//operation : getLongitude(double)
			operation = UMetaBuilder.manual().createOperation("getLongitude", false, TypeUtils.getPrimitiveType(double.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Coordinate)instance).getLongitude();
				}
			});
				operation.setDocumentation(" \r\n * returns the longitude (WGS84) of this coordinate.\r\n * depending on the current crs, this includes a coordinate transformation \r\n ");
				//Annotations for Coordinate:getLongitude(double)
				operation.createAnnotation("const");
				mCoordinate.getOperations().add(operation);
			//operation : get(Coordinate, CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("get", false, SpatialPackage.theInstance.getCoordinate(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Coordinate)instance).get((CoordinateReferenceSystem)parameter[0]);
				}
			});
				operation.setDocumentation(" returns a copy of this coordinate, that has been transformed into the given crs. \r\n * if parameter crs is the same as the member crs, a copy is returned. \r\n * \\note if the member crs is set to null, the coordinate is assumed to be defined as WGS84 coordinate \r\n ");
				//Annotations for Coordinate:get(Coordinate, CoordinateReferenceSystem)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, UDirectionType.IN);
				mCoordinate.getOperations().add(operation);
			//operation : setLatLon(void, double, double)
			operation = UMetaBuilder.manual().createOperation("setLatLon", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Coordinate)instance).setLatLon((double)parameter[0], (double)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "lat", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "lon", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mCoordinate.getOperations().add(operation);
			//operation : setLatLonAlt(void, double, double, double)
			operation = UMetaBuilder.manual().createOperation("setLatLonAlt", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Coordinate)instance).setLatLonAlt((double)parameter[0], (double)parameter[1], (double)parameter[2]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "lat", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "lon", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "alt", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mCoordinate.getOperations().add(operation);
			//operation : setXY(void, double, double)
			operation = UMetaBuilder.manual().createOperation("setXY", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Coordinate)instance).setXY((double)parameter[0], (double)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "x", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "y", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mCoordinate.getOperations().add(operation);
			//operation : setXYZ(void, double, double, double)
			operation = UMetaBuilder.manual().createOperation("setXYZ", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Coordinate)instance).setXYZ((double)parameter[0], (double)parameter[1], (double)parameter[2]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "x", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "y", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "z", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mCoordinate.getOperations().add(operation);
			//operation : copy(Coordinate)
			operation = UMetaBuilder.manual().createOperation("copy", false, SpatialPackage.theInstance.getCoordinate(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Coordinate)instance).copy();
				}
			});
				//Annotations for Coordinate:copy(Coordinate)
				operation.createAnnotation("const");
				mCoordinate.getOperations().add(operation);
			//operation : toVector(Vector)
			operation = UMetaBuilder.manual().createOperation("toVector", false, MathPackage.theInstance.getVector(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Coordinate)instance).toVector();
				}
			});
				operation.setDocumentation("\r\n * Returns either an Vector2D or Vector3D, depending on the value of dimension()\r\n ");
				//Annotations for Coordinate:toVector(Vector)
				operation.createAnnotation("const");
				mCoordinate.getOperations().add(operation);
			//operation : toVector2D(Vector2D)
			operation = UMetaBuilder.manual().createOperation("toVector2D", false, MathPackage.theInstance.getVector2D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Coordinate)instance).toVector2D();
				}
			});
				operation.setDocumentation(" returns a 2D vector, skips the z value, if not NaN ");
				//Annotations for Coordinate:toVector2D(Vector2D)
				operation.createAnnotation("const");
				mCoordinate.getOperations().add(operation);
			//operation : toVector3D(Vector3D)
			operation = UMetaBuilder.manual().createOperation("toVector3D", false, MathPackage.theInstance.getVector3D(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Coordinate)instance).toVector3D();
				}
			});
				operation.setDocumentation(" returns a 3D vector, fills the z value with 0 if dimension() == 2 ");
				//Annotations for Coordinate:toVector3D(Vector3D)
				operation.createAnnotation("const");
				mCoordinate.getOperations().add(operation);
			//operation : fromVector(void, Vector, CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("fromVector", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Coordinate)instance).fromVector((Vector)parameter[0], (CoordinateReferenceSystem)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", MathPackage.theInstance.getVector(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, UDirectionType.IN);
				mCoordinate.getOperations().add(operation);
			//operation : fromVector(void, Vector)
			operation = UMetaBuilder.manual().createOperation("fromVector", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Coordinate)instance).fromVector((Vector)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", MathPackage.theInstance.getVector(), 0, 1, UDirectionType.IN);
				mCoordinate.getOperations().add(operation);
			//operation : set(void, Coordinate)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Coordinate)instance).set((Coordinate)parameter[0]);
					return null;
				}
			});
				operation.setDocumentation(" takes over the values from <code>value</code> ");
				UMetaBuilder.manual().addParameter(operation, "value", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mCoordinate.getOperations().add(operation);
			//operation : set(void, double, double, double, CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Coordinate)instance).set((double)parameter[0], (double)parameter[1], (double)parameter[2], (CoordinateReferenceSystem)parameter[3]);
					return null;
				}
			});
				operation.setDocumentation(" utility method to set all coordinate values and the crs \r\n * @note this method calls the corresponding setter and thus produces IValueChange events\r\n ");
				UMetaBuilder.manual().addParameter(operation, "x", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "y", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "z", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, UDirectionType.IN);
				mCoordinate.getOperations().add(operation);
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Coordinate)instance).readableString();
				}
			});
				//Annotations for Coordinate:readableString(String)
				operation.createAnnotation("const");
				mCoordinate.getOperations().add(operation);
		}
		{		//Operations of Pose
			UOperation operation = null;
			//operation : readableString(String)
			operation = UMetaBuilder.manual().createOperation("readableString", false, TypeUtils.getPrimitiveType(String.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Pose)instance).readableString();
				}
			});
				//Annotations for Pose:readableString(String)
				operation.createAnnotation("const");
				mPose.getOperations().add(operation);
			//operation : copy(Pose)
			operation = UMetaBuilder.manual().createOperation("copy", false, SpatialPackage.theInstance.getPose(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Pose)instance).copy();
				}
			});
				//Annotations for Pose:copy(Pose)
				operation.createAnnotation("const");
				mPose.getOperations().add(operation);
			//operation : set(void, Coordinate, Orientation)
			operation = UMetaBuilder.manual().createOperation("set", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Pose)instance).set((Coordinate)parameter[0], (Orientation)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "coord", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "ori", UnitsPackage.theInstance.getOrientation(), 0, 1, UDirectionType.IN);
				mPose.getOperations().add(operation);
		}
		{		//Operations of Envelope
			UOperation operation = null;
			//operation : getCenter(Coordinate)
			operation = UMetaBuilder.manual().createOperation("getCenter", false, SpatialPackage.theInstance.getCoordinate(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Envelope)instance).getCenter();
				}
			});
				//Annotations for Envelope:getCenter(Coordinate)
				operation.createAnnotation("const");
				mEnvelope.getOperations().add(operation);
			//operation : getSize(Vector, DistanceUnit)
			operation = UMetaBuilder.manual().createOperation("getSize", false, MathPackage.theInstance.getVector(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Envelope)instance).getSize((DistanceUnit)parameter[0]);
				}
			});
				//Annotations for Envelope:getSize(Vector, DistanceUnit)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "unit", UnitsPackage.theInstance.getDistanceUnit(), 0, 1, UDirectionType.IN);
				mEnvelope.getOperations().add(operation);
			//operation : correct(void)
			operation = UMetaBuilder.manual().createOperation("correct", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Envelope)instance).correct();
					return null;
				}
			});
				mEnvelope.getOperations().add(operation);
			//operation : copy(Envelope)
			operation = UMetaBuilder.manual().createOperation("copy", false, SpatialPackage.theInstance.getEnvelope(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Envelope)instance).copy();
				}
			});
				mEnvelope.getOperations().add(operation);
			//operation : setLatLon(void, double, double, double, double)
			operation = UMetaBuilder.manual().createOperation("setLatLon", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Envelope)instance).setLatLon((double)parameter[0], (double)parameter[1], (double)parameter[2], (double)parameter[3]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "min_lat", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "min_lon", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "max_lat", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "max_lon", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mEnvelope.getOperations().add(operation);
			//operation : setXY(void, double, double, double, double)
			operation = UMetaBuilder.manual().createOperation("setXY", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Envelope)instance).setXY((double)parameter[0], (double)parameter[1], (double)parameter[2], (double)parameter[3]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "min_x", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "min_y", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "max_x", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "max_y", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mEnvelope.getOperations().add(operation);
			//operation : setXYZ(void, double, double, double, double, double, double)
			operation = UMetaBuilder.manual().createOperation("setXYZ", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Envelope)instance).setXYZ((double)parameter[0], (double)parameter[1], (double)parameter[2], (double)parameter[3], (double)parameter[4], (double)parameter[5]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "min_x", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "min_y", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "min_z", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "max_x", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "max_y", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "max_z", TypeUtils.getPrimitiveType(double.class), 0, 1, UDirectionType.IN);
				mEnvelope.getOperations().add(operation);
			//operation : setCRS(void, CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("setCRS", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Envelope)instance).setCRS((CoordinateReferenceSystem)parameter[0]);
					return null;
				}
			});
				operation.setDocumentation(" set the crs to min and max point ");
				UMetaBuilder.manual().addParameter(operation, "crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, UDirectionType.IN);
				mEnvelope.getOperations().add(operation);
			//operation : applyCRS(void, CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("applyCRS", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Envelope)instance).applyCRS((CoordinateReferenceSystem)parameter[0]);
					return null;
				}
			});
				operation.setDocumentation(" Applys the CRS to min and max point, by changing their x,y, and z values but without changing the instances ");
				UMetaBuilder.manual().addParameter(operation, "crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, UDirectionType.IN);
				mEnvelope.getOperations().add(operation);
			//operation : intersects(boolean, Envelope)
			operation = UMetaBuilder.manual().createOperation("intersects", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Envelope)instance).intersects((Envelope)parameter[0]);
				}
			});
				operation.setDocumentation("\r\n * Checks weather both envelopes intersect with each other. \r\n * Fails if one envelope is totally contained inside the other\r\n ");
				//Annotations for Envelope:intersects(boolean, Envelope)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", SpatialPackage.theInstance.getEnvelope(), 0, 1, UDirectionType.IN);
				mEnvelope.getOperations().add(operation);
			//operation : containsOrIntersects(boolean, Envelope)
			operation = UMetaBuilder.manual().createOperation("containsOrIntersects", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Envelope)instance).containsOrIntersects((Envelope)parameter[0]);
				}
			});
				operation.setDocumentation("\r\n * Checks if the other envelope is totally or partially inside this envelope\r\n ");
				//Annotations for Envelope:containsOrIntersects(boolean, Envelope)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", SpatialPackage.theInstance.getEnvelope(), 0, 1, UDirectionType.IN);
				mEnvelope.getOperations().add(operation);
			//operation : contains(boolean, Coordinate)
			operation = UMetaBuilder.manual().createOperation("contains", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Envelope)instance).contains((Coordinate)parameter[0]);
				}
			});
				//Annotations for Envelope:contains(boolean, Coordinate)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "coord", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mEnvelope.getOperations().add(operation);
			//operation : contains(boolean, Envelope)
			operation = UMetaBuilder.manual().createOperation("contains", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Envelope)instance).contains((Envelope)parameter[0]);
				}
			});
				operation.setDocumentation("\r\n * Checks if the other envelope is inside this envelope, \r\n * fails if they do intersect but the other envelope is not totally within this\r\n ");
				//Annotations for Envelope:contains(boolean, Envelope)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", SpatialPackage.theInstance.getEnvelope(), 0, 1, UDirectionType.IN);
				mEnvelope.getOperations().add(operation);
			//operation : expandLocal(void, Envelope)
			operation = UMetaBuilder.manual().createOperation("expandLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Envelope)instance).expandLocal((Envelope)parameter[0]);
					return null;
				}
			});
				operation.setDocumentation("\r\n * Expand the current instance to also covers the other envelope\r\n ");
				UMetaBuilder.manual().addParameter(operation, "other", SpatialPackage.theInstance.getEnvelope(), 0, 1, UDirectionType.IN);
				mEnvelope.getOperations().add(operation);
			//operation : expand(Envelope, Envelope)
			operation = UMetaBuilder.manual().createOperation("expand", false, SpatialPackage.theInstance.getEnvelope(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Envelope)instance).expand((Envelope)parameter[0]);
				}
			});
				operation.setDocumentation("\r\n * returns a new envelope that covers the current instance as well as the other envelope\r\n ");
				//Annotations for Envelope:expand(Envelope, Envelope)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", SpatialPackage.theInstance.getEnvelope(), 0, 1, UDirectionType.IN);
				mEnvelope.getOperations().add(operation);
			//operation : expandLocal(void, Coordinate)
			operation = UMetaBuilder.manual().createOperation("expandLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Envelope)instance).expandLocal((Coordinate)parameter[0]);
					return null;
				}
			});
				operation.setDocumentation("\r\n * expands the current envelope to also cover the given coordinate\r\n ");
				UMetaBuilder.manual().addParameter(operation, "other", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mEnvelope.getOperations().add(operation);
			//operation : expand(Envelope, Coordinate)
			operation = UMetaBuilder.manual().createOperation("expand", false, SpatialPackage.theInstance.getEnvelope(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Envelope)instance).expand((Coordinate)parameter[0]);
				}
			});
				operation.setDocumentation("\r\n * creates a new envelope that covers the current envelope as well as the given coordinate\r\n ");
				//Annotations for Envelope:expand(Envelope, Coordinate)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "other", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mEnvelope.getOperations().add(operation);
			//operation : tranformLocal(void, Coordinate, Rotation)
			operation = UMetaBuilder.manual().createOperation("tranformLocal", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Envelope)instance).tranformLocal((Coordinate)parameter[0], (Rotation)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "translate", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "rotation", UnitsPackage.theInstance.getRotation(), 0, 1, UDirectionType.IN);
				mEnvelope.getOperations().add(operation);
			//operation : transform(Envelope, Coordinate, Rotation)
			operation = UMetaBuilder.manual().createOperation("transform", false, SpatialPackage.theInstance.getEnvelope(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Envelope)instance).transform((Coordinate)parameter[0], (Rotation)parameter[1]);
				}
			});
				//Annotations for Envelope:transform(Envelope, Coordinate, Rotation)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "translate", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "rotation", UnitsPackage.theInstance.getRotation(), 0, 1, UDirectionType.IN);
				mEnvelope.getOperations().add(operation);
			//operation : getVertices(Coordinate)
			operation = UMetaBuilder.manual().createOperation("getVertices", false, SpatialPackage.theInstance.getCoordinate(), 0, -1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Envelope)instance).getVertices();
				}
			});
				operation.setDocumentation("\r\n * returns the four corners of the bounding box\r\n * - vertices[0] = mMinPoint;\r\n * - vertices[1] = Vec2d(mMaxPoint.X, mMinPoint.Y);\r\n * - vertices[2] = mMaxPoint;\r\n * - vertices[3] = Vec2d(mMinPoint.X, mMaxPoint.Y);\r\n ");
				//Annotations for Envelope:getVertices(Coordinate)
				operation.createAnnotation("const");
				mEnvelope.getOperations().add(operation);
		}
		{		//Operations of Geometry
			UOperation operation = null;
			//operation : getCoordinates(CoordinateSequence)
			operation = UMetaBuilder.manual().createOperation("getCoordinates", true, SpatialPackage.theInstance.getCoordinateSequence(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Geometry)instance).getCoordinates();
				}
			});
				mGeometry.getOperations().add(operation);
			//operation : getDimension(int)
			operation = UMetaBuilder.manual().createOperation("getDimension", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Geometry)instance).getDimension();
				}
			});
				//Annotations for Geometry:getDimension(int)
				operation.createAnnotation("const");
				mGeometry.getOperations().add(operation);
			//operation : numCoordinates(int)
			operation = UMetaBuilder.manual().createOperation("numCoordinates", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Geometry)instance).numCoordinates();
				}
			});
				//Annotations for Geometry:numCoordinates(int)
				operation.createAnnotation("const");
				mGeometry.getOperations().add(operation);
			//operation : getCoordinate(Coordinate, int)
			operation = UMetaBuilder.manual().createOperation("getCoordinate", false, SpatialPackage.theInstance.getCoordinate(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Geometry)instance).getCoordinate((int)parameter[0]);
				}
			});
				//Annotations for Geometry:getCoordinate(Coordinate, int)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "index", TypeUtils.getPrimitiveType(int.class), 0, 1, UDirectionType.IN);
				mGeometry.getOperations().add(operation);
			//operation : setCoordinate(void, int, Coordinate)
			operation = UMetaBuilder.manual().createOperation("setCoordinate", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Geometry)instance).setCoordinate((int)parameter[0], (Coordinate)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "index", TypeUtils.getPrimitiveType(int.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "coord", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mGeometry.getOperations().add(operation);
			//operation : removeCoordinate(void, int)
			operation = UMetaBuilder.manual().createOperation("removeCoordinate", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Geometry)instance).removeCoordinate((int)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "index", TypeUtils.getPrimitiveType(int.class), 0, 1, UDirectionType.IN);
				mGeometry.getOperations().add(operation);
			//operation : getNumGeometries(int)
			operation = UMetaBuilder.manual().createOperation("getNumGeometries", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Geometry)instance).getNumGeometries();
				}
			});
				//Annotations for Geometry:getNumGeometries(int)
				operation.createAnnotation("const");
				mGeometry.getOperations().add(operation);
			//operation : getGeometry(Geometry, int)
			operation = UMetaBuilder.manual().createOperation("getGeometry", false, SpatialPackage.theInstance.getGeometry(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Geometry)instance).getGeometry((int)parameter[0]);
				}
			});
				//Annotations for Geometry:getGeometry(Geometry, int)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "idx", TypeUtils.getPrimitiveType(int.class), 0, 1, UDirectionType.IN);
				mGeometry.getOperations().add(operation);
			//operation : getEnvelope(Envelope)
			operation = UMetaBuilder.manual().createOperation("getEnvelope", false, SpatialPackage.theInstance.getEnvelope(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Geometry)instance).getEnvelope();
				}
			});
				//Annotations for Geometry:getEnvelope(Envelope)
				operation.createAnnotation("const");
				mGeometry.getOperations().add(operation);
			//operation : intersects(boolean, Geometry)
			operation = UMetaBuilder.manual().createOperation("intersects", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Geometry)instance).intersects((Geometry)parameter[0]);
				}
			});
				//Annotations for Geometry:intersects(boolean, Geometry)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "geom", SpatialPackage.theInstance.getGeometry(), 0, 1, UDirectionType.IN);
				mGeometry.getOperations().add(operation);
			//operation : isConvex(boolean)
			operation = UMetaBuilder.manual().createOperation("isConvex", false, TypeUtils.getPrimitiveType(boolean.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Geometry)instance).isConvex();
				}
			});
				//Annotations for Geometry:isConvex(boolean)
				operation.createAnnotation("const");
				mGeometry.getOperations().add(operation);
			//operation : applyCRS(void, CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("applyCRS", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Geometry)instance).applyCRS((CoordinateReferenceSystem)parameter[0]);
					return null;
				}
			});
				operation.setDocumentation(" Applys the coordinate reference system to all coordinates in this geometry, without changing their instance\r\n * e.g. change the values of the coordinate\r\n ");
				UMetaBuilder.manual().addParameter(operation, "crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, UDirectionType.IN);
				mGeometry.getOperations().add(operation);
			//operation : recursiveSetCRS(void, CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("recursiveSetCRS", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Geometry)instance).recursiveSetCRS((CoordinateReferenceSystem)parameter[0]);
					return null;
				}
			});
				operation.setDocumentation(" sets the coordinate system for the geometrie and all contained coordinates ");
				UMetaBuilder.manual().addParameter(operation, "crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, UDirectionType.IN);
				mGeometry.getOperations().add(operation);
			//operation : getCRS(CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("getCRS", false, CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Geometry)instance).getCRS();
				}
			});
				mGeometry.getOperations().add(operation);
		}
		{		//Operations of CoordinateSequence
			UOperation operation = null;
			//operation : dimension(int)
			operation = UMetaBuilder.manual().createOperation("dimension", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((CoordinateSequence)instance).dimension();
				}
			});
				//Annotations for CoordinateSequence:dimension(int)
				operation.createAnnotation("const");
				mCoordinateSequence.getOperations().add(operation);
			//operation : numCoordinates(int)
			operation = UMetaBuilder.manual().createOperation("numCoordinates", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((CoordinateSequence)instance).numCoordinates();
				}
			});
				//Annotations for CoordinateSequence:numCoordinates(int)
				operation.createAnnotation("const");
				mCoordinateSequence.getOperations().add(operation);
			//operation : getCoordinate(Coordinate, int)
			operation = UMetaBuilder.manual().createOperation("getCoordinate", false, SpatialPackage.theInstance.getCoordinate(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((CoordinateSequence)instance).getCoordinate((int)parameter[0]);
				}
			});
				//Annotations for CoordinateSequence:getCoordinate(Coordinate, int)
				operation.createAnnotation("const");
				UMetaBuilder.manual().addParameter(operation, "idx", TypeUtils.getPrimitiveType(int.class), 0, 1, UDirectionType.IN);
				mCoordinateSequence.getOperations().add(operation);
			//operation : setCoordinate(void, int, Coordinate)
			operation = UMetaBuilder.manual().createOperation("setCoordinate", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((CoordinateSequence)instance).setCoordinate((int)parameter[0], (Coordinate)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "idx", TypeUtils.getPrimitiveType(int.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "value", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mCoordinateSequence.getOperations().add(operation);
			//operation : addCoordinate(void, Coordinate)
			operation = UMetaBuilder.manual().createOperation("addCoordinate", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((CoordinateSequence)instance).addCoordinate((Coordinate)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "value", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mCoordinateSequence.getOperations().add(operation);
			//operation : addCoordinate(void, int, Coordinate)
			operation = UMetaBuilder.manual().createOperation("addCoordinate", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((CoordinateSequence)instance).addCoordinate((int)parameter[0], (Coordinate)parameter[1]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "idx", TypeUtils.getPrimitiveType(int.class), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "value", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mCoordinateSequence.getOperations().add(operation);
			//operation : removeCoordinate(void, int)
			operation = UMetaBuilder.manual().createOperation("removeCoordinate", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((CoordinateSequence)instance).removeCoordinate((int)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "idx", TypeUtils.getPrimitiveType(int.class), 0, 1, UDirectionType.IN);
				mCoordinateSequence.getOperations().add(operation);
			//operation : removeCoordinate(void, Coordinate)
			operation = UMetaBuilder.manual().createOperation("removeCoordinate", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((CoordinateSequence)instance).removeCoordinate((Coordinate)parameter[0]);
					return null;
				}
			});
				UMetaBuilder.manual().addParameter(operation, "coord", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mCoordinateSequence.getOperations().add(operation);
			//operation : getIndexOf(int, Coordinate)
			operation = UMetaBuilder.manual().createOperation("getIndexOf", false, TypeUtils.getPrimitiveType(int.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((CoordinateSequence)instance).getIndexOf((Coordinate)parameter[0]);
				}
			});
				UMetaBuilder.manual().addParameter(operation, "coord", SpatialPackage.theInstance.getCoordinate(), 0, 1, UDirectionType.IN);
				mCoordinateSequence.getOperations().add(operation);
			//operation : getEnvelope(Envelope)
			operation = UMetaBuilder.manual().createOperation("getEnvelope", false, SpatialPackage.theInstance.getEnvelope(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((CoordinateSequence)instance).getEnvelope();
				}
			});
				operation.setDocumentation(" returns the boundingbox containing all coordinates within this sequence ");
				mCoordinateSequence.getOperations().add(operation);
		}
		{		//Operations of SpatialLayer
			UOperation operation = null;
			//operation : queryFeatures(EFeature, Geometry, boolean)
			operation = UMetaBuilder.manual().createOperation("queryFeatures", false, SpatialPackage.theInstance.getEFeature(), 0, -1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((SpatialLayer)instance).queryFeatures((Geometry)parameter[0], (boolean)parameter[1]);
				}
			});
				operation.setDocumentation("\r\n * returns all features that intersect with the given query geometry (geom). \r\n * if exactQuery is set to false, only the corresponding envelopes will be checked, otherwise a full intersection test is performed (slower)\r\n ");
				UMetaBuilder.manual().addParameter(operation, "geom", SpatialPackage.theInstance.getGeometry(), 0, 1, UDirectionType.IN);
				UMetaBuilder.manual().addParameter(operation, "exactQuery", TypeUtils.getPrimitiveType(boolean.class), 0, 1, UDirectionType.IN);
				mSpatialLayer.getOperations().add(operation);
		}
		{		//Operations of SpatialLayerContainer
			UOperation operation = null;
			//operation : provideSpatialLayer(SpatialLayer)
			operation = UMetaBuilder.manual().createOperation("provideSpatialLayer", false, SpatialPackage.theInstance.getSpatialLayer(), 0, -1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((SpatialLayerContainer)instance).provideSpatialLayer();
				}
			});
				//Annotations for SpatialLayerContainer:provideSpatialLayer(SpatialLayer)
				operation.createAnnotation("const");
				mSpatialLayerContainer.getOperations().add(operation);
		}
		{		//Operations of GraphNode
			UOperation operation = null;
			//operation : getCRS(CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("getCRS", false, CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((GraphNode)instance).getCRS();
				}
			});
				operation.setDocumentation("\r\n * Returns the CRS of the coordinate\r\n ");
				//Annotations for GraphNode:getCRS(CoordinateReferenceSystem)
				operation.createAnnotation("const");
				mGraphNode.getOperations().add(operation);
			//operation : applyCRS(void, CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("applyCRS", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((GraphNode)instance).applyCRS((CoordinateReferenceSystem)parameter[0]);
					return null;
				}
			});
				operation.setDocumentation(" Change the coordinate reference system of this node, without changing the instance of the coordinate (e.g. manipulates the values of x,y,z) ");
				UMetaBuilder.manual().addParameter(operation, "crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, UDirectionType.IN);
				mGraphNode.getOperations().add(operation);
		}
		{		//Operations of Graph
			UOperation operation = null;
			//operation : getCRS(CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("getCRS", false, CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					return ((Graph)instance).getCRS();
				}
			});
				operation.setDocumentation(" returns the CRS for this graph, that is the CRS of the first node ");
				//Annotations for Graph:getCRS(CoordinateReferenceSystem)
				operation.createAnnotation("const");
				mGraph.getOperations().add(operation);
			//operation : applyCRS(void, CoordinateReferenceSystem)
			operation = UMetaBuilder.manual().createOperation("applyCRS", false, TypeUtils.getPrimitiveType(void.class), 0, 1, new IOperationInvoker() {				
				@Override
				public Object invoke(UObject instance, Object... parameter) {
					((Graph)instance).applyCRS((CoordinateReferenceSystem)parameter[0]);
					return null;
				}
			});
				operation.setDocumentation("\r\n * Set the given CRS for all nodes, without changing the instance of the Coordinate (e.g. the values of each node's coordiante is changed)\r\n ");
				UMetaBuilder.manual().addParameter(operation, "crs", CrsPackage.theInstance.getCoordinateReferenceSystem(), 0, 1, UDirectionType.IN);
				mGraph.getOperations().add(operation);
		}
	}
	/**
	 * create all required classifiers
	 * @generated
	**/
	private void buildHierarchies(){
		mGenericEFeature.setSuperType(SpatialPackage.theInstance.getEFeature());
		
	}
	
	//////////////////////////////////////////////////////////////////////
	//				Classifier GETTER									//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UClass getCoordinate(){
		if (mCoordinate == null){
			mCoordinate = UCoreMetaRepository.getUClass(Coordinate.class);
		}
		return mCoordinate;
	}
	/**
	* @generated
	*/
	public UClass getPose(){
		if (mPose == null){
			mPose = UCoreMetaRepository.getUClass(Pose.class);
		}
		return mPose;
	}
	/**
	* @generated
	*/
	public UClass getEnvelope(){
		if (mEnvelope == null){
			mEnvelope = UCoreMetaRepository.getUClass(Envelope.class);
		}
		return mEnvelope;
	}
	/**
	* @generated
	*/
	public UClass getGeometry(){
		if (mGeometry == null){
			mGeometry = UCoreMetaRepository.getUClass(Geometry.class);
		}
		return mGeometry;
	}
	/**
	* @generated
	*/
	public UClass getEFeature(){
		if (mEFeature == null){
			mEFeature = UCoreMetaRepository.getUClass(EFeature.class);
		}
		return mEFeature;
	}
	/**
	* @generated
	*/
	public UClass getGenericEFeature(){
		if (mGenericEFeature == null){
			mGenericEFeature = UCoreMetaRepository.getUClass(GenericEFeature.class);
		}
		return mGenericEFeature;
	}
	/**
	* @generated
	*/
	public UClass getCoordinateSequence(){
		if (mCoordinateSequence == null){
			mCoordinateSequence = UCoreMetaRepository.getUClass(CoordinateSequence.class);
		}
		return mCoordinateSequence;
	}
	/**
	* @generated
	*/
	public UClass getSpatialLayer(){
		if (mSpatialLayer == null){
			mSpatialLayer = UCoreMetaRepository.getUClass(SpatialLayer.class);
		}
		return mSpatialLayer;
	}
	/**
	* @generated
	*/
	public UInterface getSpatialLayerContainer(){
		if (mSpatialLayerContainer == null){
			mSpatialLayerContainer = UCoreMetaRepository.getUInterface(SpatialLayerContainer.class);
		}
		return mSpatialLayerContainer;
	}
	
	//////////////////////////////////////////////////////////////////////
	//				StructuralFeatures	GETTER							//
	//////////////////////////////////////////////////////////////////////
	
	/**
	* @generated
	*/
	public UStructuralFeature getCoordinate_x(){
		if (mCoordinate_x == null)
			mCoordinate_x = getCoordinate().getFeature("x");
		return mCoordinate_x;
	}



	/**
	* @generated
	*/
	public UClass getGraph(){
		if (mGraph == null){
			mGraph = UCoreMetaRepository.getUClass(Graph.class);
		}
		return mGraph;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCoordinate_y(){
		if (mCoordinate_y == null)
			mCoordinate_y = getCoordinate().getFeature("y");
		return mCoordinate_y;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCoordinate_z(){
		if (mCoordinate_z == null)
			mCoordinate_z = getCoordinate().getFeature("z");
		return mCoordinate_z;
	}



	/**
	* @generated
	*/
	public UClass getGraphEdge(){
		if (mGraphEdge == null){
			mGraphEdge = UCoreMetaRepository.getUClass(GraphEdge.class);
		}
		return mGraphEdge;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCoordinate_crs(){
		if (mCoordinate_crs == null)
			mCoordinate_crs = getCoordinate().getFeature("crs");
		return mCoordinate_crs;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getPose_coordinate(){
		if (mPose_coordinate == null)
			mPose_coordinate = getPose().getFeature("coordinate");
		return mPose_coordinate;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getPose_orientation(){
		if (mPose_orientation == null)
			mPose_orientation = getPose().getFeature("orientation");
		return mPose_orientation;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEnvelope_minPoint(){
		if (mEnvelope_minPoint == null)
			mEnvelope_minPoint = getEnvelope().getFeature("minPoint");
		return mEnvelope_minPoint;
	}



	/**
	* @generated
	*/
	public UClass getGraphNode(){
		if (mGraphNode == null){
			mGraphNode = UCoreMetaRepository.getUClass(GraphNode.class);
		}
		return mGraphNode;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEnvelope_maxPoint(){
		if (mEnvelope_maxPoint == null)
			mEnvelope_maxPoint = getEnvelope().getFeature("maxPoint");
		return mEnvelope_maxPoint;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCoordinateSequence_crs(){
		if (mCoordinateSequence_crs == null)
			mCoordinateSequence_crs = getCoordinateSequence().getFeature("crs");
		return mCoordinateSequence_crs;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCoordinateSequence_xCoordinates(){
		if (mCoordinateSequence_xCoordinates == null)
			mCoordinateSequence_xCoordinates = getCoordinateSequence().getFeature("xCoordinates");
		return mCoordinateSequence_xCoordinates;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getEFeature_featureType(){
		if (mEFeature_featureType == null)
			mEFeature_featureType = getEFeature().getFeature("featureType");
		return mEFeature_featureType;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getEFeature_geometry(){
		if (mEFeature_geometry == null)
			mEFeature_geometry = getEFeature().getFeature("geometry");
		return mEFeature_geometry;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getSpatialLayer_name(){
		if (mSpatialLayer_name == null)
			mSpatialLayer_name = getSpatialLayer().getFeature("name");
		return mSpatialLayer_name;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getSpatialLayer_features(){
		if (mSpatialLayer_features == null)
			mSpatialLayer_features = getSpatialLayer().getFeature("features");
		return mSpatialLayer_features;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getGraphNode_coordinate(){
		if (mGraphNode_coordinate == null)
			mGraphNode_coordinate = getGraphNode().getFeature("coordinate");
		return mGraphNode_coordinate;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCoordinateSequence_yCoordinates(){
		if (mCoordinateSequence_yCoordinates == null)
			mCoordinateSequence_yCoordinates = getCoordinateSequence().getFeature("yCoordinates");
		return mCoordinateSequence_yCoordinates;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getGraphEdge_id(){
		if (mGraphEdge_id == null)
			mGraphEdge_id = getGraphEdge().getFeature("id");
		return mGraphEdge_id;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getGraphEdge_nodeA(){
		if (mGraphEdge_nodeA == null)
			mGraphEdge_nodeA = getGraphEdge().getFeature("nodeA");
		return mGraphEdge_nodeA;
	}
	/**
	* @generated
	*/
	public UStructuralFeature getCoordinateSequence_zCoordinates(){
		if (mCoordinateSequence_zCoordinates == null)
			mCoordinateSequence_zCoordinates = getCoordinateSequence().getFeature("zCoordinates");
		return mCoordinateSequence_zCoordinates;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getGraphNode_id(){
		if (mGraphNode_id == null)
			mGraphNode_id = getGraphNode().getFeature("id");
		return mGraphNode_id;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getGraph_edges(){
		if (mGraph_edges == null)
			mGraph_edges = getGraph().getFeature("edges");
		return mGraph_edges;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getGraph_nodes(){
		if (mGraph_nodes == null)
			mGraph_nodes = getGraph().getFeature("nodes");
		return mGraph_nodes;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getGraphEdge_nodeB(){
		if (mGraphEdge_nodeB == null)
			mGraphEdge_nodeB = getGraphEdge().getFeature("nodeB");
		return mGraphEdge_nodeB;
	}



	/**
	* @generated
	*/
	public UStructuralFeature getGraphNode_edges(){
		if (mGraphNode_edges == null)
			mGraphNode_edges = getGraphNode().getFeature("edges");
		return mGraphNode_edges;
	}
}
