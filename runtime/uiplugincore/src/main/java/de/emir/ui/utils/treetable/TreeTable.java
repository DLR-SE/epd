package de.emir.ui.utils.treetable;
/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2003 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

/*
 * this file is derived from the "Creating TreeTable" article at
 * http://java.sun.com/products/jfc/tsc/articles/treetable2/index.html
 */

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.plaf.TreeUI;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.apache.log4j.lf5.viewer.configure.MRUFileManager;

import de.emir.tuml.ucore.runtime.utils.ObjectPointer;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.PointerChain;
import de.emir.ui.utils.treetable.umodel.UNode;

/**
 * This example shows how to create a simple JTreeTable component, by using a
 * JTree as a renderer (and editor) for the cells in a particular column in the
 * JTable.
 *
 * @version 1.2 10/27/98
 *
 * @author Philip Milne
 * @author Scott Violet
 */
public class TreeTable extends JTable {

	private static final long serialVersionUID = 1;

	/** A subclass of JTree. */
	protected TreeTableCellRenderer tree;
	private TreeTableModel treeTableModel;
	private SortingModel sortingModel;

	public TreeTable(TreeTableModel treeTableModel) {
		super();

		// Create the tree. It will be used as a renderer and editor.
		// First we create a dummy model for the tree and set later the
		// real model with setModel(). This way JTree's TreeModelListener
		// will be called first and we can update our table.
		tree = new TreeTableCellRenderer(new DefaultTreeModel(new DefaultMutableTreeNode()));
		
		
		// Install a tableModel representing the visible rows in the tree.
		setTreeTableModel(treeTableModel);

		// Force the JTable and JTree to share their row selection models.
		ListToTreeSelectionModelWrapper selectionWrapper = new ListToTreeSelectionModelWrapper();
		tree.setSelectionModel(selectionWrapper);
		setSelectionModel(selectionWrapper.getListSelectionModel());

		// Install the tree editor renderer and editor.
		setDefaultRenderer(TreeTableModel.class, tree);
		setDefaultEditor(TreeTableModel.class, new TreeTableCellEditor());

		// No grid.
		setShowGrid(true);
	

		// No intercell spacing
		setIntercellSpacing(new Dimension(0, 0));

		// And update the height of the trees row to match that of
		// the table.
		if (tree.getRowHeight() < getRowHeight()) {
			setRowHeight(getRowHeight());
		}

		addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName() == "rowMargin") {
					tree.intercellSpacing = getIntercellSpacing();
				}
			}
		});

		this.sortingModel = new SortingModel();
		tree.setShowsRootHandles(true);
		tree.setRootVisible(true);
		
		TreeUI treeUI = tree.getUI();
//		((BasicTreeUI)treeUI).setLeftChildIndent(200);
		
//		InputMap imp2 = getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
//		ActionMap am = getActionMap();
//
//		// copied from TreeView which tried to fix #18292
//		// by doing this
//		imp2.put(KeyStroke.getKeyStroke("control C"), "none"); // NOI18N
//		imp2.put(KeyStroke.getKeyStroke("control V"), "none"); // NOI18N
//		imp2.put(KeyStroke.getKeyStroke("control X"), "none"); // NOI18N
//		imp2.put(KeyStroke.getKeyStroke("COPY"), "none"); // NOI18N
//		imp2.put(KeyStroke.getKeyStroke("PASTE"), "none"); // NOI18N
//		imp2.put(KeyStroke.getKeyStroke("CUT"), "none"); // NOI18N
	}

	/**
	 * Expands all nodes
	 */
	public void expandAll() {
		TreePath tp = new TreePath(tree.getModel().getRoot());
		expandAllUnder(tp);
	}
	
	public void expandFirst() {
		TreePath tp = new TreePath(tree.getModel().getRoot());
		expand(tp);
	}

	/**
	 * Expands all nodes under the specified path
	 *
	 * @param tp
	 *            the path
	 */
	public void expandAllUnder(TreePath tp) {
		tree.expandPath(tp);
		Object last = tp.getLastPathComponent();
		for (int i = 0; i < tree.getModel().getChildCount(last); i++) {
			Object child = tree.getModel().getChild(last, i);
			expandAllUnder(tp.pathByAddingChild(child));
		}
	}
	public void expand(TreePath tp) {
		if (treeTableModel.isLeaf(tp.getLastPathComponent()))
			tree.expandPath(tp.getParentPath());
		else
			tree.expandPath(tp);
	}
	
//	public TreePath expand(Pointer ptr) {
//		if (ptr.isValid() == false)
//			return null;
//		TreePath tp = null;
//		if (ptr instanceof PointerChain) {
//			tp = expand(((PointerChain)ptr).getParent());
//		}
//		if (ptr instanceof ObjectPointer) {
//			getTreeTableModel().getCellEditor(node, column)
//		}
//		return tp;
//	}
	
	/**
	 * Sets new sorting model
	 *
	 * @param sm
	 *            new sorting model or null
	 */
	public void setSortingModel(SortingModel sm) {
		SortingModel old = this.sortingModel;
		this.sortingModel = sm;
		firePropertyChange("sortingModel", old, sm);
	}

	/**
	 * Returns sorting model
	 *
	 * @return sorting model or null if not supported
	 */
	public SortingModel getSortingModel() {
		return sortingModel;
	}

	/**
	 * Sets new TreeTableModel
	 *
	 * @param treeTableModel
	 *            a model
	 */
	public void setTreeTableModel(TreeTableModel treeTableModel) {
		this.treeTableModel = treeTableModel;
		setModel(new TreeTableModelAdapter(treeTableModel, tree));
		tree.setModel(treeTableModel);
	}

	
	public void setTreeCellRenderer(final TreeCellRenderer tcr) {
		tree.setCellRenderer(tcr);
	}
	
	/**
	 * Returns the current model
	 *
	 * @return model
	 */
	public TreeTableModel getTreeTableModel() {
		return treeTableModel;
	}

	/**
	 * Returns the object for the specified row
	 *
	 * @param row
	 *            row number
	 */
	public Object getNodeForRow(int row) {
		TreePath tp = tree.getPathForRow(row);
		if (tp == null)
			return null;
		return tp.getLastPathComponent();
	}

	/**
	 * Returns the row corresponding to the specified path
	 *
	 * @param path
	 *            path to a node
	 * @return corresponding row in the table
	 */
	public int getRowForPath(TreePath path) {
		return tree.getRowForPath(path);
	}

	/**
	 * Overridden to message super and forward the method to the tree. Since the
	 * tree is not actually in the component hieachy it will never receive this
	 * unless we forward it in this manner.
	 */
	@Override
	public void updateUI() {
		super.updateUI();
		if (tree != null) {
			tree.updateUI();
		}
		// Use the tree's default foreground and background colors in the
		// table.
		LookAndFeel.installColorsAndFont(this, "Tree.background", "Tree.foreground", "Tree.font");
	}

	/*
	 * Workaround for BasicTableUI anomaly. Make sure the UI never tries to paint
	 * the editor. The UI currently uses different techniques to paint the renderers
	 * and editors and overriding setBounds() below is not the right thing to do for
	 * an editor. Returning -1 for the editing row in this case, ensures the editor
	 * is never painted.
	 */
	@Override
	public int getEditingRow() {
		return (getColumnClass(editingColumn) == TreeTableModel.class) ? -1 : editingRow;
	}

	/**
	 * Overridden to pass the new rowHeight to the tree.
	 */
	@Override
	public void setRowHeight(int rowHeight) {
		super.setRowHeight(rowHeight);
		if (tree != null && tree.getRowHeight() != rowHeight) {
			tree.setRowHeight(getRowHeight());
		}
	}

	/**
	 * Returns the tree that is being shared between the model.
	 */
	public JTree getTree() {
		return tree;
	}

	@Override
	protected javax.swing.table.JTableHeader createDefaultTableHeader() {
		return new SortableTableHeader(columnModel);
	}

	/**
	 * A TreeCellRenderer that displays a JTree.
	 */
	public class TreeTableCellRenderer extends JTree implements TableCellRenderer {

		private static final long serialVersionUID = 1;

		/** Last table/tree row asked to renderer. */
		protected int visibleRow;
		private Border border;
		private Dimension intercellSpacing = new Dimension(1, 1);

		public TreeTableCellRenderer(TreeModel model) {
			super(model);
		}

		/**
		 * updateUI is overridden to set the colors of the Tree's renderer to match that
		 * of the table.
		 */
		@Override
		public void updateUI() {
			super.updateUI();
			// Make the tree's cell renderer use the table's cell selection
			// colors.
			TreeCellRenderer tcr = getCellRenderer();
			if (tcr instanceof DefaultTreeCellRenderer) {
				DefaultTreeCellRenderer dtcr = ((DefaultTreeCellRenderer) tcr);
				// For 1.1 uncomment this, 1.2 has a bug that will cause an
				// exception to be thrown if the border selection color is
				// null.
				// dtcr.setBorderSelectionColor(null);
				dtcr.setTextSelectionColor(UIManager.getColor("Table.selectionForeground"));
				dtcr.setBackgroundSelectionColor(UIManager.getColor("Table.selectionBackground"));
			}
		}

		/**
		 * Sets the row height of the tree, and forwards the row height to the table.
		 */
		@Override
		public void setRowHeight(int rowHeight) {
			if (rowHeight > 0) {
				super.setRowHeight(rowHeight);
				if (TreeTable.this != null && TreeTable.this.getRowHeight() != rowHeight) {
					TreeTable.this.setRowHeight(getRowHeight());
				}
			}
		}

		/**
		 * This is overridden to set the height to match that of the JTable.
		 */
		@Override
		public void setBounds(int x, int y, int w, int h) {
			super.setBounds(x, 0, w, TreeTable.this.getHeight());
		}

		/**
		 * Sublcassed to translate the graphics such that the last visible row will be
		 * drawn at 0,0.
		 */
		@Override
		public void paint(Graphics g) {
			Rectangle oldClip = g.getClipBounds();
			Rectangle clip;
			// if (border == null)
			clip = oldClip.intersection(
					new Rectangle(0, 0, getWidth() - intercellSpacing.width, getRowHeight() - intercellSpacing.height));
			/*
			 * else clip = oldClip.intersection( new Rectangle(1, 1, getWidth() - 2,
			 * getRowHeight() - 2));
			 */
			g.setClip(clip);
			g.translate(0, -visibleRow * getRowHeight());
			super.paint(g);
			g.translate(0, visibleRow * getRowHeight());
			g.setClip(oldClip);
			if (border != null)
				border.paintBorder(this, g, 0, 0, getWidth(), getRowHeight());
		}

		/**
		 * TreeCellRenderer method. Overridden to update the visible row.
		 */
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (hasFocus) {
				border = UIManager.getBorder("Table.focusCellHighlightBorder");
				if (table.isCellEditable(row, column)) {
					super.setForeground(UIManager.getColor("Table.focusCellForeground"));
					super.setBackground(UIManager.getColor("Table.focusCellBackground"));
				}
			} else {
				border = null;
			}

			if (isSelected)
				setBackground(table.getSelectionBackground());
			else
				setBackground(table.getBackground());

			visibleRow = row;
			return this;
		}
	}

	/**
	 * TreeTableCellEditor implementation. Component returned is the JTree.
	 */
	public class TreeTableCellEditor extends AbstractCellEditor implements TableCellEditor {
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int r, int c) {
			return tree;
		}

		/**
		 * Overridden to return false, and if the event is a mouse event it is forwarded
		 * to the tree.
		 * 
		 * The behavior for this is debatable, and should really be offered as a
		 * property. By returning false, all keyboard actions are implemented in terms
		 * of the table. By returning true, the tree would get a chance to do something
		 * with the keyboard events. For the most part this is ok. But for certain keys,
		 * such as left/right, the tree will expand/collapse where as the table focus
		 * should really move to a different column. Page up/down should also be
		 * implemented in terms of the table. By returning false this also has the added
		 * benefit that clicking outside of the bounds of the tree node, but still in
		 * the tree column will select the row, whereas if this returned true that
		 * wouldn't be the case.
		 * 
		 * 
		 * By returning false we are also enforcing the policy that the tree will never
		 * be editable (at least by a key sequence).
		 */
		@Override
		public boolean isCellEditable(EventObject e) {
			if (e instanceof MouseEvent) {
				for (int counter = getColumnCount() - 1; counter >= 0; counter--) {
					if (getColumnClass(counter) == TreeTableModel.class) {
						MouseEvent me = (MouseEvent) e;
						MouseEvent newME = new MouseEvent(tree, me.getID(), me.getWhen(), me.getModifiers(),
								me.getX() - getCellRect(0, counter, true).x, me.getY(), me.getClickCount(),
								me.isPopupTrigger());
						tree.dispatchEvent(newME);
						break;
					}
				}
			}
			return false;
		}
	}

	/**
	 * ListToTreeSelectionModelWrapper extends DefaultTreeSelectionModel to listen
	 * for changes in the ListSelectionModel it maintains. Once a change in the
	 * ListSelectionModel happens, the paths are updated in the
	 * DefaultTreeSelectionModel.
	 */
	class ListToTreeSelectionModelWrapper extends DefaultTreeSelectionModel {

		private static final long serialVersionUID = 1;

		/** Set to true when we are updating the ListSelectionModel. */
		protected boolean updatingListSelectionModel;

		public ListToTreeSelectionModelWrapper() {
			super();
			getListSelectionModel().addListSelectionListener(createListSelectionListener());
		}

		/**
		 * Returns the list selection model. ListToTreeSelectionModelWrapper listens for
		 * changes to this model and updates the selected paths accordingly.
		 */
		ListSelectionModel getListSelectionModel() {
			return listSelectionModel;
		}

		/**
		 * This is overridden to set updatingListSelectionModel and message super. This
		 * is the only place DefaultTreeSelectionModel alters the ListSelectionModel.
		 */
		@Override
		public void resetRowSelection() {
			if (!updatingListSelectionModel) {
				updatingListSelectionModel = true;
				try {
					super.resetRowSelection();
				} finally {
					updatingListSelectionModel = false;
				}
			}
			// Notice how we don't message super if
			// updatingListSelectionModel is true. If
			// updatingListSelectionModel is true, it implies the
			// ListSelectionModel has already been updated and the
			// paths are the only thing that needs to be updated.
		}

		/**
		 * Creates and returns an instance of ListSelectionHandler.
		 */
		protected ListSelectionListener createListSelectionListener() {
			return new ListSelectionHandler();
		}

		/**
		 * If updatingListSelectionModel is false, this will reset the selected paths
		 * from the selected rows in the list selection model.
		 */
		protected void updateSelectedPathsFromSelectedRows() {
			if (!updatingListSelectionModel) {
				updatingListSelectionModel = true;
				try {
					// This is way expensive, ListSelectionModel needs an
					// enumerator for iterating.
					int min = listSelectionModel.getMinSelectionIndex();
					int max = listSelectionModel.getMaxSelectionIndex();

					clearSelection();
					if (min != -1 && max != -1) {
						for (int counter = min; counter <= max; counter++) {
							if (listSelectionModel.isSelectedIndex(counter)) {
								TreePath selPath = tree.getPathForRow(counter);

								if (selPath != null) {
									addSelectionPath(selPath);
								}
							}
						}
					}
				} finally {
					updatingListSelectionModel = false;
				}
			}
		}

		/**
		 * Class responsible for calling updateSelectedPathsFromSelectedRows when the
		 * selection of the list changse.
		 */
		class ListSelectionHandler implements ListSelectionListener {
			public void valueChanged(ListSelectionEvent e) {
				updateSelectedPathsFromSelectedRows();
			}
		}
	}

	public void addTreeExpansionListener(TreeExpansionListener listener) {
		tree.addTreeExpansionListener(listener);		
	}
	public void removeTreeExpansionListener(TreeExpansionListener listener) {
		tree.removeTreeExpansionListener(listener);
	}

	public void addTreeWillExpandListener(TreeWillExpandListener listener) {
		tree.addTreeWillExpandListener(listener);		
	}
	public void removeTreeWillExpandListener(TreeWillExpandListener listener) {
		tree.removeTreeWillExpandListener(listener);
	}

	
	
	@Override
	public TableCellEditor getCellEditor(int row, int column) {
		TableCellEditor editor = getTreeTableModel().getCellEditor(getNodeForRow(row), column);
		if (editor == null)
			return super.getCellEditor(row, column);
		return editor;
	}

	@Override
	public TableCellRenderer getCellRenderer(int row, int column) {
		TableCellRenderer renderer = getTreeTableModel().getCellRenderer(getNodeForRow(row), column);
		if (renderer != null)
			return renderer;
		return super.getCellRenderer(row, column);
	}

	public void setRootVisible(boolean visible) {
		tree.setRootVisible(visible);
		
	}

	
	/**
	 * returns the pointer at a given position, and if exact == false, it search for the nearest element to this position. 
	 * @param x mouse position
	 * @param y mouse position
	 * @param exact search exactly at this position (true) or return the nearest one (false)
	 * @return a pointer to the element at the given position or null if no tree element could be found
	 */
	public Pointer getPointerForLocation(int x, int y, boolean exact) {
		TreePath tp = exact ? getTree().getPathForLocation(x, y) : getTree().getClosestPathForLocation(x, y);
		if (tp == null) return null;
		Object lpc = tp.getLastPathComponent();
		if (lpc instanceof UNode)
			return ((UNode)lpc).getPointer();
		return null;
	}



	

}