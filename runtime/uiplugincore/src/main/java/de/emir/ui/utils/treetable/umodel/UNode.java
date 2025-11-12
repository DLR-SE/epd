package de.emir.ui.utils.treetable.umodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.tree.TreeNode;

import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import javax.swing.tree.TreePath;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;
import org.jdesktop.swingx.treetable.TreeTableNode;

/**
 * UNode is the special TreeTableNode for eMIR UCore model objects. It is used in the EModEditor.
 */
public class UNode extends DefaultMutableTreeTableNode implements IValueChangeListener {
	/** 
	 * Display options for UNodes depending on their structural traits.
	 */
	public static class UTTNodeOptions {
		public boolean expandCompositions = true;
		public boolean expandComplexProperties = true;
		public boolean expandAssociations = true;
		
		public boolean expandAttributes = false;
		public boolean expandAggregations = false;
		
		/** if set to true, the tree (table) will also display features that have currently no value (e.g == null). */
		public boolean showNullValues = false;

		public boolean isChild(UStructuralFeature feature, Object value) {
			switch(feature.getAggregation()){
			case AGGREGATION:
				return expandAggregations;
			case ASSOCIATION:
				return expandAssociations;
			case COMPOSITION:
				return expandCompositions;
			case PROPERTY:
				if (feature.isAttribute())
					return expandAttributes;
				return expandComplexProperties;
			}
			return false;
		}
	}
	
	protected UTTNodeOptions mOptions = new UTTNodeOptions();
	protected UTTModel mModel;
	
	protected UStructuralFeature mFeature;
	/** Index of this node within its list of UStructuralFeatures (or -1). */
	protected int mListIndex;
	/** Index of this node within its SubTree. */
	protected int mSubTreeIndex;
	
	/** True for the first time, later on it will be set to true, when the node's value changes. */
	protected boolean mRequiresUpdate = true;
	/**  Is current value change an addition? */
	protected boolean mChangeAdd = false;
	/** Is current value change a deletion? */
	protected boolean mChangeDelete = false;
	protected Map<Object, UNode> mChildMap = new HashMap<Object, UNode>();
	protected Pointer mPointer;
	
	/** Logger. */
	private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(UNode.class);
	
	public UNode(Object userObject, UTTModel model, UStructuralFeature feature, int listIndex, UTTNodeOptions options) {
		super(userObject, true);
		mModel = model;
		mFeature = feature;
		mListIndex = listIndex;
		mOptions = options;
	}
	
	@Override
	public void removeFromParent() {
		checkChildren();
		super.removeFromParent();
	}

	@Override
	public int getChildCount() {
		checkChildren();
		return super.getChildCount();
	}
	
	@Override
	public TreeTableNode getChildAt(int index) {
		checkChildren();
		return super.getChildAt(index);
	}
	
	/**
	 * This methods determines which children UNodes are updated and how they should be ordered in the EModEditor tree. 
	 */
	// FIXME: This method messed up the order of elements between different feature types
	protected void checkChildren() {
		if (!(mChangeDelete || mChangeAdd)) {
			initSubTree(); // No changes yet, just build the subtree model
			return;
		}
		if (mRequiresUpdate) {
			mRequiresUpdate = false; // required to avoid recursive calls
			if (getUserObject() instanceof UObject uobj) {
				Map<Object, UNode> toDelete = new HashMap<Object, UNode>(mChildMap);
				Map<UNode, Integer> toAdd = new HashMap<UNode, Integer>();
				UClassifier cl = (UClassifier) uobj.getUClassifier();
				int childIndex = 0;
				for (UStructuralFeature feature : cl.getAllStructuralFeatures()) {
					Object value = feature.get(uobj);
					if (value != null || mOptions.showNullValues) {
						if (mOptions.isChild(feature, value)) { // decide upon settings for this provider if we add it
							if (feature.isMany()) {
								List<?> l = (List<?>) value;
								for (int i = 0; i < l.size(); i++) {
									Object childObj = l.get(i);
									if (toDelete.remove(childObj) == null) { // did not exist yet
										UNode childNode = createNewChild(childObj, mModel, feature, i);
										childNode.mSubTreeIndex = i + childIndex;
										toAdd.put(childNode, i);
									}
									childIndex++;
								}
							} else {
								if (toDelete.remove(value) == null) { // did not exist yet
									UNode childNode = createNewChild(value, mModel, feature, childIndex);
									childNode.mSubTreeIndex = childIndex;
									toAdd.put(childNode, childIndex);
								}
								childIndex++;
							}
						}
					}
				}

				if (!toDelete.isEmpty()) { // Have something to delete
					for (UNode rem : toDelete.values()) {
						LOG.trace("removing " + rem + " from index " + rem.mListIndex + "/" + getIndex(rem));
						mModel.removeNodeFromParent(rem);

						// Output new order of children
						int j = 0;
						for (int i = 0; i < getChildCount(); i++) {
							// Goes through all StructuralFeatures and fixes the order if items of the same type have 
							// been deleted.
							if (getChildAt(i) instanceof UNode cn) {
								LOG.trace("Child " + cn + " at " + i + " with mSubTreeIndex " + cn.mSubTreeIndex
										+ " and mIndex " + cn.mListIndex);
								if (rem.mFeature.equals(cn.mFeature)) {
									if (cn.mListIndex != j && cn.mListIndex > -1) {
										cn.mListIndex = j;
									}
									j++;
								}

								if (cn.mSubTreeIndex != i && cn.mSubTreeIndex > -1) {
									cn.mSubTreeIndex = i;
								}

								LOG.trace(PointerOperations.toPointerString(cn.mPointer));
							}
						}
					}
				}

				if (!toAdd.isEmpty()) { // Have something to add
					// Order the new children by preassigned index
					Map<UNode, Integer> sortedToAdd = toAdd.entrySet().stream()
							.sorted((i1, i2) -> i1.getValue().compareTo(i2.getValue())).collect(Collectors
									.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

					for (Map.Entry<UNode, Integer> add : sortedToAdd.entrySet()) {
						LOG.trace("adding " + add.getKey() + " at index "
								+ ((add.getValue() > 0 && add.getValue() <= getChildCount()) ? add.getValue() : 0)
								+ " should be " + add.getValue());
						mModel.insertNodeInto(add.getKey(), this,
								(add.getValue() > 0 && add.getValue() <= getChildCount()) ? add.getValue() : 0);

						// Output new order of children
						int j = 0;
						for (int i = 0; i < getChildCount(); i++) {
							// Goes through all StructuralFeatures and fixes the order if items of the same
							// type have been added.
							if (getChildAt(i) instanceof UNode cn) {
								LOG.trace("Child " + cn + " at " + i + " with mSubTreeIndex " + cn.mSubTreeIndex
										+ " and mIndex " + cn.mListIndex);
								if (add.getKey().mFeature.equals(cn.mFeature)) {
									if (cn.mListIndex != j && cn.mListIndex > -1) {
										cn.mListIndex = j;
									}
									j++;
								}

								if (cn.mSubTreeIndex != i && cn.mSubTreeIndex > -1) {
									cn.mSubTreeIndex = i;
								}

								LOG.trace(PointerOperations.toPointerString(cn.mPointer));
							}
						}
					}
				}
			}

			mChangeDelete = false;
			mChangeAdd = false;

			if (children == null || children.size() == 0) {
				mRequiresUpdate = true;
			}
		}
	}

	/**
	 * Updates the UNode children on first expansion.
	 */
	protected void initSubTree() {
		if (mRequiresUpdate) {
			mRequiresUpdate = false; // required to avoid recursive calls
			if (getUserObject() instanceof UObject) {
				UObject uobj = (UObject) getUserObject();
				Map<Object, UNode> toDelete = new HashMap<Object, UNode>(mChildMap);
				List<UNode> toAdd = new ArrayList<UNode>();
				UClassifier cl = (UClassifier) uobj.getUClassifier();
				for (UStructuralFeature feature : cl.getAllStructuralFeatures()) {
					Object value = feature.get(uobj);
					if (value != null || mOptions.showNullValues) {
						if (mOptions.isChild(feature, value)) { // decide upon settings for this provider if we add it
							if (feature.isMany()) {
								List<?> l = (List<?>) value;
								for (int i = 0; i < l.size(); i++) {
									Object childObj = l.get(i);
									if (toDelete.remove(childObj) == null) { // did not exist yet
										UNode childNode = createNewChild(childObj, mModel, feature, i);
										toAdd.add(childNode);
									}
								}
							} else {
								if (toDelete.remove(value) == null) { // did not exist yet
									UNode childNode = createNewChild(value, mModel, feature, -1);
									toAdd.add(childNode);
								}
							}
						}
					}
				}
				for (UNode rem : toDelete.values()) {
					mModel.removeNodeFromParent(rem);
				}
				for (UNode add : toAdd) {
					mModel.insertNodeInto(add, this, getChildCount());
				}
			}
			mChangeDelete = false;
			mChangeAdd = false;
			if (children == null || children.size() == 0) {
				mRequiresUpdate = true;
			}
		}
	}
	
	/**
	 * Create a new child UNode.
	 * 
	 * @param childObj the object handled by the new UNode
	 * @param model the model containing the new UNode
	 * @param feature possible parent structural feature if this object is part an aggregation or composition
	 * @param i the list index if the new UNode is part of a List type (e.g. when it is in a structural feature)
	 * @return the new UNode
	 */
	protected UNode createNewChild(Object childObj, UTTModel model, UStructuralFeature feature, int i) {
		return new UNode(childObj, model, feature, i, mOptions);
	}

	@Override
	public void insert(MutableTreeTableNode newChild, int childIndex) {
		mChildMap.put(((UNode) newChild).getUserObject(), ((UNode) newChild));
		super.insert(newChild, childIndex);
	}
	
	@Override
	public void remove(int childIndex) {
		UNode n = (UNode) children.get(childIndex);
		mChildMap.remove(n.getUserObject());
		super.remove(childIndex);
	}
	
	@Override
	public void remove(MutableTreeTableNode aChild) {
		mChildMap.remove(((UNode) aChild).getUserObject());
		super.remove(aChild);
	}
	
	/**
	 * Register update listeners when this trees part is about to expand. Also set the mRequiresUpdate to trigger an 
	 * update of the child nodes.
	 * 
	 * @param event the TreeExpansionEvent
	 */
	public void willExpand(TreeExpansionEvent event) {
		mRequiresUpdate = true;
		if (getUserObject() instanceof UObject) {
			((UObject) getUserObject()).registerListener(this);
		}
	}

	/**
	 * This method gets executed when this UNode or its children gets changed (add, remove...).
	 * 
	 * @param notification the events data
	 */
	public void onValueChange(Notification notification) {
		LOG.trace("ValueChange oldValue: " + (notification.getOldValue() != null ? notification.getOldValue() : "null")
		 + " newValue: " + (notification.getNewValue() != null ? notification.getNewValue() : "null"));
		if (notification.getNewValue() == null && notification.getOldValue() != null) { //removal
			mChangeDelete = true;
		} else if (notification.getNewValue() != null && notification.getOldValue() == null) { //addition
			mChangeAdd = true;
		}
		mRequiresUpdate = true;
		checkChildren();
	}

	public void collapsed(TreeExpansionEvent event) {
		mRequiresUpdate = false; //no need and it will be set to true if the node will re-expand
		if (getUserObject() instanceof UObject)
			((UObject)getUserObject()).removeListener(this);	
	}

	/**
	 * Get the Pointer to this UNodes contained object or create one if it does not exist.
	 * 
	 * @return Pointer to this UNodes contained object
	 */
	public Pointer getPointer() {
		// FIXME: This pointer should not be newly created every time, but keeping the old pointers around makes their
		// stored index invalid on edits in the tree. This solution is good enough for now.
//		if (mPointer == null) {
			TreeNode p = getParent();
			Pointer pp = null;
			if (p instanceof UNode)
				pp = ((UNode) p).getPointer();
			if (pp != null) {
				mPointer = PointerOperations.createChain(pp, mFeature, mListIndex);
			} else if (getUserObject() instanceof UObject)
				mPointer = PointerOperations.create((UObject) getUserObject());
//		}
		return mPointer;
	}
	
	/**
	 * Get this UNodes parents within the tree.
	 * 
	 * @param item the node to find the parents for
	 * @return a List of parent nodes
	 */
    protected List<TreeTableNode> getParents(TreeTableNode item) {
        List<TreeTableNode> result = new ArrayList<>();
        if (item.getParent() != null) {
            result.addAll(getParents(item.getParent()));
        }
        result.add(item);
        return result;
    }
    
    /**
     * Get this UNodes TreePath within the TreeTable model.
     * 
     * @return the TreePath to this UNode
     */
	public TreePath getPath() {
        TreePath result = new TreePath(getParents(parent).toArray(new TreeTableNode[0]));
		return result;
    }
	
}
