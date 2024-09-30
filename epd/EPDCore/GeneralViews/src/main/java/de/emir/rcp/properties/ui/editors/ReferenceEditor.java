package de.emir.rcp.properties.ui.editors;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Collection;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.tree.TreePath;

import de.emir.rcp.editor.model.impl.EMIRLabelProvider;
import de.emir.rcp.editor.model.impl.EModTreeCellRenderer;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.impl.AssociationProperty;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.UCoreUtils;
import de.emir.ui.utils.treetable.umodel.UNode;
import de.emir.ui.utils.treetable.umodel.UTTModel;
import java.awt.ComponentOrientation;
import java.awt.Dialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import org.jdesktop.swingx.JXComboBox;
import org.jdesktop.swingx.JXTreeTable;

public class ReferenceEditor extends AbstractPropertyEditor<UObject> {

	JPanel mEditor = null;
	private UObject mRoot;
	private static EMIRLabelProvider sLabelProvider = new EMIRLabelProvider();
    private JDialog mReferenceDialog;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component createComponent() {
		if (mEditor == null) {
			mEditor = new JPanel();
			mEditor.setLayout(new BorderLayout(0, 0));
            
            Object model_root = PlatformUtil.getModelManager().getModelProvider() != null
					? PlatformUtil.getModelManager().getModelProvider().getModel()
					: null;
			mRoot = (model_root != null && model_root instanceof UObject) ? (UObject) model_root : null;

			Pointer ptr = getUCoreProperty().getPointer();
			UObject pointed_obj = ptr.getPointedContainer();
			if (pointed_obj != null && mRoot == null)
				mRoot = UCoreUtils.getRoot(pointed_obj);

			if (getProperty() instanceof AssociationProperty) {
				// if we got an object hint we will use this as root
				AssociationProperty ap = (AssociationProperty) getUCoreProperty();
				if (ap.getValueHint() != null && ap.getValueHint() instanceof UObject)
					mRoot = (UObject) ap.getValueHint();
			}

			Collection<UObject> newItems = UCoreUtils.collectTypedChildren(mRoot, ptr.getExpectedType());
			JXComboBox comboBox = new JXComboBox(newItems.toArray());
			comboBox.setRenderer(new DefaultListCellRenderer() {
				@Override
				public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
						boolean cellHasFocus) {
					super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value == null) {
                        setText("Null");
                        return this;
                    }
					String label = sLabelProvider.getLabel(value);
//                    ULog.info(label);
					URL icon = sLabelProvider.getIcon(value);
					String tooltip = sLabelProvider.getTooltip(value);
					if (label != null)
                        if (!label.startsWith("<html>")) {
                            setText("<html>" + label + "</html>");
                        } else {
                            setText(label);
                        }
					if (icon != null) {
						setIcon(IconManager.getIcon(icon, 16));
                    } else {
                        setIcon(UIManager.getIcon("Tree.leafIcon"));
                    }
					if (tooltip != null)
						setToolTipText(tooltip);
					return this;
				}
			});

			mEditor.add(comboBox, BorderLayout.CENTER);
			
			comboBox.addItem(null);
			comboBox.setSelectedItem(getValue());

			comboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Object item = comboBox.getSelectedItem();
					setValue((UObject) comboBox.getSelectedItem());
				}
			});

			UType expectedType = getUCoreProperty().getPointer().getExpectedType();

			JButton button = new JButton("...");
			button.setEnabled(true);
			mEditor.add(button, BorderLayout.EAST);
		          button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mReferenceDialog = new JDialog(PlatformUtil.getWindowManager().getMainWindow(), "Select Reference", true);
                    mReferenceDialog.getContentPane().setLayout(new BorderLayout());
                    JButton selButton = new JButton("Select");

                    Pointer ptr = PointerOperations.createPointerToObject(mRoot);
                    if (ptr == null && mRoot != null) {
                        ptr = PointerOperations.create(mRoot);
                    }

                    UTTModel mTreeTableModel = new UTTModel() {
                    };

                    JXTreeTable treeTable = new JXTreeTable(mTreeTableModel);
                    treeTable.setRootVisible(true);
                    mTreeTableModel.install(treeTable);
                    treeTable.setTreeCellRenderer(new EModTreeCellRenderer(new EMIRLabelProvider()));
                    mTreeTableModel.setInput(mRoot);

                    TreePath selectedTp = mTreeTableModel.find((UNode) mTreeTableModel.getRoot(), getValue());
                    if (selectedTp != null) {
                        treeTable.expandPath(selectedTp);
                        treeTable.getTreeSelectionModel().setSelectionPath(selectedTp);
                    }

                    treeTable.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent evt) {
                            int idx = treeTable.getSelectedRow();
                            ULog.trace("Selected row " + idx);
                            if (evt.getClickCount() == 2) {
                                if (treeTable.isCollapsed(idx)) {
                                    treeTable.expandRow(idx);
                                } else {
                                    treeTable.collapseRow(idx);
                                }
                            }
                        }
                    });

                    treeTable.getTreeSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
                        @Override
                        public void valueChanged(TreeSelectionEvent e) {
                            if (e.getPath() != null) {
                                TreePath tp = treeTable.getTreeSelectionModel().getSelectionPath();
                                if (tp == null || tp.getPathCount() <= 1) {
                                    return; //not grabbing the root
                                }
                                Object node_obj = tp.getLastPathComponent();
                                if (node_obj != null && node_obj instanceof UNode) {
                                    UType type = ((UNode) node_obj).getPointer().getType();
                                    if (type != null && type.inherits(expectedType)) {
                                        selButton.setEnabled(true);
                                    } else {
                                        selButton.setEnabled(false);
                                    }
                                }
                            }
                        }
                    });

                    selButton.setEnabled(false);
                    selButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mReferenceDialog.setVisible(false);
                            TreePath tp = treeTable.getTreeSelectionModel().getSelectionPath();
                            if (tp == null || tp.getPathCount() <= 1) {
                                return; //not grabbing the root
                            }
                            Object node_obj = tp.getLastPathComponent();
                            if (node_obj != null && node_obj instanceof UNode) {
                                UObject value = (UObject) ((UNode) node_obj).getPointer().getValue();
                                setValue(value);
                                comboBox.setSelectedItem(value);
                            }
                        }
                    });
                    JPanel panel = new JPanel();
                    JToolBar toolBar = new JToolBar();

                    toolBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    toolBar.setFloatable(false);
                    toolBar.setBackground(UIManager.getColor("TableHeader.background"));

                    toolBar.setEnabled(true);

                    Component glue1 = Box.createHorizontalGlue();
                    toolBar.add(glue1);

                    JLabel lblModel = new JLabel("Model");
                    lblModel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    lblModel.setHorizontalAlignment(SwingConstants.CENTER);
                    lblModel.setFont(UIManager.getFont("TableHeader.font"));
                    lblModel.setForeground(UIManager.getColor("TableHeader.foreground"));
                    lblModel.setBackground(UIManager.getColor("TableHeader.background"));
                    toolBar.add(lblModel);

                    Component glue2 = Box.createHorizontalGlue();
                    toolBar.add(glue2);

                    JButton btnExpandAll = new JButton("");
                    btnExpandAll.setIcon(IconManager.getIcon(ReferenceEditor.class,
                            "icons/emiricons/32/arrow_drop_down.png", IconManager.preferedSmallIconSize()));
                    btnExpandAll.setToolTipText("Expand all");
                    btnExpandAll.addActionListener((ActionEvent evt) -> {
                        treeTable.expandAll();
                    });

                    toolBar.add(btnExpandAll);
                    btnExpandAll.setEnabled(true);

                    JButton btnCollapseAll = new JButton("");
                    btnCollapseAll.setIcon(IconManager.getIcon(ReferenceEditor.class,
                            "icons/emiricons/32/arrow_drop_up.png", IconManager.preferedSmallIconSize()));
                    btnCollapseAll.setToolTipText("Collapse all");
                    btnCollapseAll.addActionListener((ActionEvent evt) -> {
                        treeTable.collapseAll();
                        treeTable.expandRow(0);
                    });

                    toolBar.add(btnCollapseAll);
                    btnCollapseAll.setEnabled(true);

                    treeTable.setTableHeader(null);
                    JScrollPane sp = new JScrollPane(treeTable);
                    mReferenceDialog.getContentPane().add(toolBar, BorderLayout.NORTH);
                    mReferenceDialog.getContentPane().add(sp, BorderLayout.CENTER);
                    mReferenceDialog.getContentPane().add(selButton, BorderLayout.SOUTH);
                    mReferenceDialog.setSize(640, 480);
                    mReferenceDialog.setLocationRelativeTo(PlatformUtil.getWindowManager().getMainWindow());
                    mReferenceDialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
                    mReferenceDialog.setVisible(true);
                }
            });
            createExtensions(mEditor);
		}
		return mEditor;
	}

	protected void createExtensions(JPanel panel) {
		
	}

	@Override
	protected void setValue(UObject value) {
		super.setValue(value);
	}

	@Override
	public void dispose() {

	}
}
