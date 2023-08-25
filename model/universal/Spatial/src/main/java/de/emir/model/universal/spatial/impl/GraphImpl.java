package de.emir.model.universal.spatial.impl;

import java.util.List;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.model.universal.spatial.Graph;
import de.emir.model.universal.spatial.GraphEdge;
import de.emir.model.universal.spatial.GraphNode;
import de.emir.model.universal.spatial.SpatialPackage;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.lists.ListUtils;


/**
 *	@generated 
 */
@UMLImplementation(classifier = Graph.class)
public class GraphImpl extends UObjectImpl implements Graph  
{
	
	
	/**
	 *	@generated 
	 */
	private List<GraphNode> mNodes = null;
	/**
	 *	@generated 
	 */
	private List<GraphEdge> mEdges = null;
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public GraphImpl(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public GraphImpl(final Graph _copy) {
		mNodes = _copy.getNodes();
		mEdges = _copy.getEdges();
	}
	
	/**
	 *	Default attribute constructor
	 *	@generated
	 */
	public GraphImpl(List<GraphNode> _nodes, List<GraphEdge> _edges) {
		mNodes = _nodes; 
		mEdges = _edges; 
	}
	
	/**
	 * @generated
	 */
	public UClass getUClassifier() {
		return SpatialPackage.Literals.Graph;
	}

	/**
	 *	@generated 
	 */
	public List<GraphNode> getNodes() {
		if (mNodes == null) {
			mNodes = ListUtils.<GraphNode>asList(this, SpatialPackage.theInstance.getGraph_nodes()); 
		}
		return mNodes;
	}

	/**
	 *	@generated 
	 */
	public List<GraphEdge> getEdges() {
		if (mEdges == null) {
			mEdges = ListUtils.<GraphEdge>asList(this, SpatialPackage.theInstance.getGraph_edges()); 
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
		return getNodes().isEmpty() == false ? getNodes().get(0).getCRS() : null;
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void applyCRS(final CoordinateReferenceSystem crs)
	{
		for (GraphNode gn : getNodes())
			gn.applyCRS(crs);
	}
	

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "GraphImpl{" +
		"}";
	}
}
