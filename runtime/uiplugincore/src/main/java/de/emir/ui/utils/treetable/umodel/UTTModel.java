package de.emir.ui.utils.treetable.umodel;

import java.util.Arrays;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;
import de.emir.tuml.ucore.runtime.utils.impl.QualifiedNameImpl;
import de.emir.ui.utils.treetable.umodel.UNode.UTTNodeOptions;
import de.emir.ui.utils.treetable.umodel.impl.ModelTreeColumnProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;
import org.jdesktop.swingx.treetable.TreeTableNode;

public class UTTModel extends DefaultTreeTableModel implements TreeWillExpandListener, TreeExpansionListener {

	static class RootNode extends UNode{
		public RootNode() {
			super(null, null, null, -1, new UNode.UTTNodeOptions());
		}
	}
	private static List<String> getColumnNames(IColumnProvider[] c) {
		List<String> n = new ArrayList<>();//new String[c.length+1];
		n.add("Name");
		for (int i = 0; i < c.length; i++) n.add(c[i].getColumnName());
		return n;
	}


	private IColumnProvider[] 		mColumnProvider;
	private UNode					mRoot;

	
	public UTTModel() {
		this(new IColumnProvider[] {new ModelTreeColumnProvider()});
	}

	public UTTModel(IColumnProvider[] iColumnProviders) {
		super(new RootNode(), getColumnNames(iColumnProviders));
		mRoot = (RootNode)super.getRoot();
		mColumnProvider = iColumnProviders;
	}
	public void addColumn(IColumnProvider column) {
		mColumnProvider = Arrays.copyOf(mColumnProvider, mColumnProvider.length+1);
		mColumnProvider[mColumnProvider.length-1] = column;
	}
	
	public void setInput(UObject... objects) {
		while(mRoot.getChildCount() > 0)
			removeNodeFromParent((MutableTreeTableNode) mRoot.getChildAt(0));
		
		if (objects == null) return ;
		if (objects.length == 1) {
			setRoot(mRoot = createNewNode(objects[0]));
			mRoot.willExpand(null);
		}else {
			setRoot(mRoot = new RootNode());
			for (UObject uobj : objects)
				insertNodeInto(createNewNode(uobj), mRoot, mRoot.getChildCount());
		}
	}
	
	protected UNode createNewNode(UObject value) {
		return new UNode(value, this, null, -1, new UTTNodeOptions());
	}
	
	@Override
	public Class getColumnClass(int column) {
		return mColumnProvider[column].getColumnClass();
	}
	public int getColumnCount() {
		return mColumnProvider.length;
	}
	public String getColumnName(int column) {
		return mColumnProvider[column].getColumnName();
	}	
	public Object getValueAt(Object node, int column) {
		return mColumnProvider[column].getColumnValue(node);
	}
	
	@Override
	public void setValueAt(Object aValue, Object node, int column) {
		if (node != null && column != 0)
			mColumnProvider[column].setValue(node, aValue);
	}
	
	@Override
	public boolean isCellEditable(Object node, int column) {
		if (node == mRoot) return false;
        if (column == 0) return true;
		return mColumnProvider[column].isEditable(node);
	}

	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//							Expansion 
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void install(JXTreeTable tt) {
		tt.addTreeExpansionListener(this);
		tt.addTreeWillExpandListener(this);
	}
	public void uninstall(JXTreeTable tt) {
		tt.removeTreeExpansionListener(this);
		tt.removeTreeWillExpandListener(this);
		//do not remove the model, otherwise the tree will be invalid
	}
	
    @Override
	public void treeExpanded(TreeExpansionEvent event) {

	}
    
    @Override
	public void treeCollapsed(TreeExpansionEvent event) {
		Object lpc = event.getPath().getLastPathComponent();
		if (lpc == null) return ;
		if (lpc == this.root) return ;
		UNode node = (UNode) lpc;
		if (node != null)
			node.collapsed(event);
	}	

    @Override
	public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
		Object lpc = event.getPath().getLastPathComponent();
		if (lpc == null) return ;
		if (lpc == this.root) return ;
		UNode node = (UNode) lpc;
		if (node != null)
			node.willExpand(event);
	}

    @Override
	public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {

	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//							Renderer & Editors 
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//	@Override
	public TableCellEditor getCellEditor(Object node, int column) {
		return mColumnProvider[column].getCellEditor(node);
	}

//	@Override
	public TableCellRenderer getCellRenderer(Object node, int column) {
		return mColumnProvider[column].getCellRenderer(node);
	}

	public UObject getInput(int i) {
		if (mRoot instanceof RootNode)
			return (UObject) mRoot.getChildAt(i);
		return (UObject) mRoot.getUserObject();
	}

	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//							TreePath queries 
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	public TreePath getTreePath(Pointer ptr) {
		if (PointerOperations.getPointerRoot(ptr) != getRoot())
			return null;
		return getTreePath(PointerOperations.toPointerString(ptr));
	}

	public TreePath getTreePath(String ptrStr) {
		QualifiedName queryQN = QualifiedNameImpl.createWithRegEx(ptrStr, "\\.");
		return getTreePath(mRoot, queryQN);
	}

	private TreePath getTreePath(UNode parent, QualifiedName queryQN) {
		if (queryQN.empty()) {
            List<TreeTableNode> parents = getParents(parent);
            TreePath result = new TreePath(parents.toArray(new TreeTableNode[0]));
			return result;
        }
		String first = queryQN.firstSegment();
		int nc = parent.getChildCount();
		for (int i = 0; i < nc; i++) {
			UNode c = (UNode) parent.getChildAt(i);
			Pointer cp = c.getPointer();
			String cps = PointerOperations.toPointerString(cp);
			QualifiedName cpqn = QualifiedNameImpl.createWithRegEx(cps, "\\.");
			if (cpqn.lastSegment().equals(first)) {
				return getTreePath(c, queryQN.removeSegmentsFromStart(1));
			}
		}
		return null;
	}

	public Pointer getPointer(String ptrStr) {
		TreePath tp = getTreePath(ptrStr);
		if (tp == null)
			return null;
		Object lpc = tp.getLastPathComponent();
		if (lpc != null && lpc instanceof UNode)
			return ((UNode)lpc).getPointer();
		return null;
	}
    
    public List<TreeTableNode> getParents(TreeTableNode item) {
        List<TreeTableNode> result = new ArrayList<>();
        if (item.getParent() != null) {
            result.addAll(getParents(item.getParent()));
        }
        result.add(item);
        return result;
    }

    public TreePath find(UNode root, UObject uobj) {
        if (uobj != null && uobj.equals(root.getUserObject())) {
            return root.getPath();
        }
        if (root != null) {
            for (Iterator it = root.children().asIterator(); it.hasNext(); ) {
              UNode unode = (UNode) it.next();
              TreePath tp = find(unode, uobj);
              if (tp != null) return tp;
            }
        }
        return null;
    }
}
