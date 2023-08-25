package de.emir.model.universal.spatial.impl;

import java.util.List;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.GraphEdge;
import de.emir.model.universal.spatial.GraphNode;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.lists.ListUtils;


/**
 *	@generated 
 */
@UMLImplementation(classifier = GraphNode.class)
public class GraphNodeImpl extends UObjectImpl implements GraphNode  
{
	
	
	/**
	 *	@generated 
	 */
	private long mId = 0;
	/**
	 *	@generated 
	 */
	private Coordinate mCoordinate = null;
	/**
	 *	@generated 
	 */
	private List<GraphEdge> mEdges = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public GraphNodeImpl(){
		super();
		//set the default values and assign them to this instance 
		setCoordinate(mCoordinate);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public GraphNodeImpl(final GraphNode _copy) {
		mId = _copy.getId();
		mCoordinate = _copy.getCoordinate();
		mEdges = _copy.getEdges();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public GraphNodeImpl(long _id, Coordinate _coordinate, List<GraphEdge> _edges) {
		mId = _id;
		mCoordinate = _coordinate; 
		mEdges = _edges; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SpatialPackage.Literals.GraphNode;
	}

	/**
	 *	@generated 
	 */
	public void setId(long _id) {
		if (needNotification(SpatialPackage.Literals.GraphNode_id)){
			long _oldValue = mId;
			mId = _id;
			notify(_oldValue, mId, SpatialPackage.Literals.GraphNode_id, NotificationType.SET);
		}else{
			mId = _id;
		}
	}

	/**
	 *	@generated 
	 */
	public long getId() {
		return mId;
	}

	/**
	 *	@generated 
	 */
	public void setCoordinate(Coordinate _coordinate) {
		if (needNotification(SpatialPackage.Literals.GraphNode_coordinate)){
			Coordinate _oldValue = mCoordinate;
			mCoordinate = _coordinate;
			notify(_oldValue, mCoordinate, SpatialPackage.Literals.GraphNode_coordinate, NotificationType.SET);
		}else{
			mCoordinate = _coordinate;
		}
	}

	/**
	 *	@generated 
	 */
	public Coordinate getCoordinate() {
		return mCoordinate;
	}

	/**
	 *	@generated 
	 */
	public List<GraphEdge> getEdges() {
		if (mEdges == null) {
			mEdges = ListUtils.<GraphEdge>asList(this, SpatialPackage.theInstance.getGraphNode_edges()); 
		}
		return mEdges;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public CoordinateReferenceSystem getCRS()
	{
		return getCoordinate() != null ? getCoordinate().getCrs() : null;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void applyCRS(final CoordinateReferenceSystem crs)
	{
		if (getCoordinate() != null)
			getCoordinate().set(getCoordinate().get(crs));
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "GraphNodeImpl{" +
		" id = " + getId() + 
		"}";
	}
}
