package de.emir.service.geometry.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Rectangle;
import com.github.davidmoten.rtree.internal.EntryDefault;

import de.emir.model.universal.spatial.Envelope;
import de.emir.model.universal.spatial.ops.EnvelopeOperations;
import de.emir.service.geometry.GeometryPackage;
import de.emir.service.geometry.ISpatialIndex;
import de.emir.service.geometry.ISpatialIndexVisitor;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import rx.Observable;


/**
 *	@generated 
 */
@UMLImplementation(classifier = ISpatialIndex.class)
abstract public class SpatialIndex extends UObjectImpl implements ISpatialIndex  
{
	
	
			
	/**
	 *	Default constructor
	 *	@generated
	 */
	public SpatialIndex(){
		super();
	}
	
	/**
	 *	Default copy constructor
	 *	@generated
	 */
	public SpatialIndex(final ISpatialIndex _copy) {
	}
	
	
	/**
	 * @generated
	 */
	@Override
	public UClass getUClassifier() {
		return GeometryPackage.Literals.SpatialIndex;
	}
	
	
	//////////////////////////////////////////////////////////////////
	//							 Operations							//
	//////////////////////////////////////////////////////////////////
	
	private RTree<Object, Rectangle>	mRTree = RTree.maxChildren(5).create();
//	private Quadtree		mQuadTree = new Quadtree();
	private EnvelopeOperations	mOps = new EnvelopeOperations();
	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void insert(final Envelope env, final Object value)
	{
		mRTree = mRTree.add(new EntryDefault<Object, Rectangle>(value, getGeometry(env)));
//		mQuadTree.insert(mOps.getNativeEnvelope(env), value);
	}

	private Rectangle getGeometry(Envelope env) {
		env.correct();
        return Geometries.rectangle(env.getMinPoint().getX(), env.getMinPoint().getY(), env.getMaxPoint().getX(), env.getMaxPoint().getY());
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public boolean remove(final Envelope env, final Object value)
	{
		RTree<Object, Rectangle> removed = mRTree.delete(value, getGeometry(env));
		return true; 
//		return mQuadTree.remove(mOps.getNativeEnvelope(env), value);
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public List<Object> query(final Envelope env)
	{
		Observable<Entry<Object, Rectangle>> result = mRTree.nearest(getGeometry(env), 2, 100);
		ArrayList<Object> out = new ArrayList<>();
		for (Entry<Object, Rectangle> o : result.toBlocking().toIterable())
			out.add(o.value());
		return out;
//		return mQuadTree.query(mOps.getNativeEnvelope(env));
	}

	/**
	 * @inheritDoc
	 * @generated not
	 */
	@Override
	public void query(final Envelope env, final ISpatialIndexVisitor visitor) {
		//TODO
//		mQuadTree.query(mOps.getNativeEnvelope(env), new ItemVisitor() {
//			
//			@Override
//			public void visitItem(Object item) {
//				visitor.visit(item);
//			}
//		});
	}

	/**
	* @generated
	*/
	@Override
	public String toString() {
		return "SpatialIndex{" +
		"}";
	}
}
