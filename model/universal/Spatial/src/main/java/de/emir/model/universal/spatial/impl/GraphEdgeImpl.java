package de.emir.model.universal.spatial.impl;

import de.emir.model.universal.spatial.GraphEdge;
import de.emir.model.universal.spatial.GraphNode;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.NotificationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;


/**
 *	@generated 
 */
@UMLImplementation(classifier = GraphEdge.class)
public class GraphEdgeImpl extends UObjectImpl implements GraphEdge  
{
	
	
	/**
	 *	@generated 
	 */
	private long mId = 0;
	/**
	 *	@generated 
	 */
	private GraphNode mNodeA = null;
	/**
	 *	@generated 
	 */
	private GraphNode mNodeB = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public GraphEdgeImpl(){
		super();
		//set the default values and assign them to this instance 
		setNodeA(mNodeA);
		setNodeB(mNodeB);
	}

	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public GraphEdgeImpl(final GraphEdge _copy) {
		mId = _copy.getId();
		mNodeA = _copy.getNodeA();
		mNodeB = _copy.getNodeB();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public GraphEdgeImpl(long _id, GraphNode _nodeA, GraphNode _nodeB) {
		mId = _id;
		mNodeA = _nodeA; 
		mNodeB = _nodeB; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SpatialPackage.Literals.GraphEdge;
	}

	/**
	 *	@generated 
	 */
	public void setId(long _id) {
		if (needNotification(SpatialPackage.Literals.GraphEdge_id)){
			long _oldValue = mId;
			mId = _id;
			notify(_oldValue, mId, SpatialPackage.Literals.GraphEdge_id, NotificationType.SET);
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
	public void setNodeA(GraphNode _nodeA) {
		Notification<GraphNode> notification = basicSet(mNodeA, _nodeA, SpatialPackage.Literals.GraphEdge_nodeA);
		mNodeA = _nodeA;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public GraphNode getNodeA() {
		return mNodeA;
	}

	/**
	 *	@generated 
	 */
	public void setNodeB(GraphNode _nodeB) {
		Notification<GraphNode> notification = basicSet(mNodeB, _nodeB, SpatialPackage.Literals.GraphEdge_nodeB);
		mNodeB = _nodeB;
		if (notification != null){
			dispatchNotification(notification);
		}
	}

	/**
	 *	@generated 
	 */
	public GraphNode getNodeB() {
		return mNodeB;
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "GraphEdgeImpl{" +
		" id = " + getId() + 
		"}";
	}
}
