package de.emir.ui.utils.treetable.umodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class UNode extends DefaultMutableTreeTableNode implements IValueChangeListener{

	public static class UTTNodeOptions {
		public boolean expandCompositions = true;
		public boolean expandComplexProperties = true;
		public boolean expandAssociations = true;
		
		public boolean expandAttributes = false;
		public boolean expandAggregations = false;
		
		/** if set to true, the tree (table) will also display 
		 * features that have currently no value (e.g == null)
		 */
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
	
	protected UTTNodeOptions			mOptions = new UTTNodeOptions();
	protected UTTModel 					mModel;
	
	protected UStructuralFeature 		mFeature;
	protected int				 		mListIndex;
	
	protected boolean 					mRequiresUpdate = true; //true for the first time, later on it will be set to true, when the node's value changes	
	protected Map<Object, UNode> 		mChildMap = new HashMap<Object, UNode>();
	protected Pointer 					mPointer;
	
	
	public UNode(Object userObject, UTTModel model, UStructuralFeature feature, int listIndex, UTTNodeOptions options) {
		super(userObject, true);
		mModel = model;
		mFeature = feature;
		mListIndex = listIndex;
		mOptions = options;
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
	


	protected void checkChildren() {
		if (mRequiresUpdate) {
			mRequiresUpdate = false; //required to avoid recursive calls
			if (getUserObject() instanceof UObject) {
				UObject uobj = (UObject)getUserObject();
				Map<Object, UNode> toDelete = new HashMap<Object, UNode>(mChildMap);
				List<UNode> toAdd = new ArrayList<UNode>();
				UClassifier cl = (UClassifier)uobj.getUClassifier();
				for (UStructuralFeature feature : cl.getAllStructuralFeatures()){
					Object value = feature.get(uobj);
					if (value != null || mOptions.showNullValues){
						if (mOptions.isChild(feature, value)){ //decide upon settings for this provider if we add it
							if (feature.isMany()){
								List<?> l = (List<?>)value;
								for (int i = 0; i < l.size(); i++){
									Object childObj = l.get(i);
									if (toDelete.remove(childObj) == null) { //did not exist yet
										UNode childNode = createNewChild(childObj, mModel, feature, i);
										toAdd.add(childNode);
									}
								}
							}else {
								if (toDelete.remove(value) == null) { //did not exist yet
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
			if (children == null || children.size() == 0) {
				mRequiresUpdate = true;
			}
		}
		
	}
	
	
	protected UNode createNewChild(Object childObj, UTTModel mModel2, UStructuralFeature feature, int i) {
		return new UNode(childObj, mModel2, feature, i, mOptions);
	}



	@Override
	public void insert(MutableTreeTableNode newChild, int childIndex) {
		mChildMap.put(((UNode)newChild).getUserObject(), ((UNode)newChild));
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
		mChildMap.remove(((UNode)aChild).getUserObject());
		super.remove(aChild);
	}
	
	

	



	public void willExpand(TreeExpansionEvent event) {
		mRequiresUpdate = true;
		if (getUserObject() instanceof UObject)
			((UObject)getUserObject()).registerListener(this);
	}


	public void onValueChange(Notification notification) {
		mRequiresUpdate = true;
		checkChildren();
	}

	public void collapsed(TreeExpansionEvent event) {
		mRequiresUpdate = false; //no need and it will be set to true if the node will re-expand
		if (getUserObject() instanceof UObject)
			((UObject)getUserObject()).removeListener(this);	
	}


	public Pointer getPointer() {
//		if (mPointer == null) {
			TreeNode p = getParent();
			Pointer pp = null;
			if (p instanceof UNode)
				pp = ((UNode) p).getPointer();
			if (pp != null) {
				mPointer = PointerOperations.createChain(pp, mFeature, mListIndex);
			}else if (getUserObject() instanceof UObject)
				mPointer = PointerOperations.create((UObject) getUserObject());
//		}
		return mPointer;
	}
	
    protected List<TreeTableNode> getParents(TreeTableNode item) {
        List<TreeTableNode> result = new ArrayList<>();
        if (item.getParent() != null) {
            result.addAll(getParents(item.getParent()));
        }
        result.add(item);
        return result;
    }
    
	public TreePath getPath() {
        TreePath result = new TreePath(getParents(parent).toArray(new TreeTableNode[0]));
		return result;
    }
	
}
