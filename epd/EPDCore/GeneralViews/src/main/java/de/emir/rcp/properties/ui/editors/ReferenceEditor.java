package de.emir.rcp.properties.ui.editors;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Collection;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxUI;
import javax.swing.tree.TreePath;

import de.emir.rcp.editor.model.impl.EMIRLabelProvider;
import de.emir.rcp.editor.model.impl.EModTreeCellRenderer;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.impl.AssociationProperty;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.UCoreUtils;
import de.emir.ui.utils.treetable.TreeTable;
import de.emir.ui.utils.treetable.umodel.UNode;
import de.emir.ui.utils.treetable.umodel.UTTModel;
import de.emir.ui.utils.treetable.umodel.UNode.UTTNodeOptions;

public class ReferenceEditor extends AbstractPropertyEditor<UObject> {

	JPanel mEditor = null;
	private UObject mRoot;
	private static EMIRLabelProvider sLabelProvider = new EMIRLabelProvider();

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component createComponent() {
		if (mEditor == null) {
			mEditor = new JPanel();
			mEditor.setLayout(new BorderLayout(0, 0));

			JComboBox<UObject> comboBox = new JComboBox<>();
			comboBox.setRenderer(new DefaultListCellRenderer() {
				@Override
				public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
						boolean cellHasFocus) {
					super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

					String label = sLabelProvider.getLabel(value);
					URL icon = sLabelProvider.getIcon(value);
					String tooltip = sLabelProvider.getTooltip(value);
					if (label != null)
						setText("<html>" + label + "</html>");
					if (icon != null)
						setIcon(IconManager.getIcon(icon, 16));
					if (tooltip != null)
						setToolTipText(tooltip);
					return this;
				}
			});
			mEditor.add(comboBox, BorderLayout.CENTER);
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
			comboBox.addItem(null);
			for (UObject uobj : newItems)
				comboBox.addItem(uobj);
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
					JDialog dialog = new JDialog(PlatformUtil.getWindowManager().getMainWindow(), "Select Reference", true);
					dialog.getContentPane().setLayout(new BorderLayout());
					JButton selButton = new JButton("Select");

					Pointer ptr = PointerOperations.createPointerToObject(mRoot);
					if (ptr == null && mRoot != null)
						ptr = PointerOperations.create(mRoot);

					UTTModel mTreeTableModel = new UTTModel() {
						
					};
					
			        TreeTable mTreeTable = new TreeTable(mTreeTableModel);
			        mTreeTable.setRootVisible(true);
			        mTreeTableModel.install(mTreeTable);
					mTreeTable.setTreeCellRenderer(new EModTreeCellRenderer(new EMIRLabelProvider()));
					mTreeTableModel.setInput(mRoot);

					mTreeTable.getTree().getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
						@Override
						public void valueChanged(TreeSelectionEvent e) {
							if (e.getPath() != null) {
								TreePath tp = mTreeTable.getTree().getSelectionPath();
								if (tp == null || tp.getPathCount() <= 1) return ; //not grabbing the root
								Object node_obj = tp.getLastPathComponent();
								if (node_obj != null && node_obj instanceof UNode) {
									UType type = ((UNode)node_obj).getPointer().getType();
									if (type != null && type.inherits(expectedType))
										selButton.setEnabled(true);
									else
										selButton.setEnabled(false);
								}
							}
						}
					});

					selButton.setEnabled(false);
					selButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							dialog.setVisible(false);
							TreePath tp = mTreeTable.getTree().getSelectionPath();
							if (tp == null || tp.getPathCount() <= 1) return ; //not grabbing the root
							Object node_obj = tp.getLastPathComponent();
							if (node_obj != null && node_obj instanceof UNode) {
								UObject value = (UObject)((UNode)node_obj).getPointer().getValue();
								setValue(value);
								comboBox.setSelectedItem(value);
							}
						}
					});

					JScrollPane sp = new JScrollPane(mTreeTable);
					dialog.getContentPane().add(sp, BorderLayout.CENTER);
					dialog.getContentPane().add(selButton, BorderLayout.SOUTH);
					dialog.setSize(640, 480);
					dialog.setVisible(true);
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

	class SteppedComboBox extends JComboBox {
		protected int popupWidth;

		public SteppedComboBox(ComboBoxModel aModel) {
			super(aModel);
			setUI(new SteppedComboBoxUI());
			popupWidth = 0;
		}

		public SteppedComboBox(final Object[] items) {
			super(items);
			setUI(new SteppedComboBoxUI());
			popupWidth = 0;
		}

		public SteppedComboBox(Vector items) {
			super(items);
			setUI(new SteppedComboBoxUI());
			popupWidth = 0;
		}

		public void setPopupWidth(int width) {
			popupWidth = width;
		}

		public Dimension getPopupSize() {
			Dimension size = getSize();
			if (popupWidth < 1)
				popupWidth = size.width;
			return new Dimension(popupWidth, size.height);
		}
	}

	class SteppedComboBoxUI extends MetalComboBoxUI {
		@Override
		protected ComboPopup createPopup() {
			BasicComboPopup popup = new BasicComboPopup(comboBox) {

				@Override
				public void show() {
					Dimension popupSize = ((SteppedComboBox) comboBox).getPopupSize();
					popupSize.setSize(popupSize.width, getPopupHeightForRowCount(comboBox.getMaximumRowCount()));
					Rectangle popupBounds = computePopupBounds(0, comboBox.getBounds().height, popupSize.width,
							popupSize.height);
					scroller.setMaximumSize(popupBounds.getSize());
					scroller.setPreferredSize(popupBounds.getSize());
					scroller.setMinimumSize(popupBounds.getSize());
					list.invalidate();
					int selectedIndex = comboBox.getSelectedIndex();
					if (selectedIndex == -1) {
						list.clearSelection();
					} else {
						list.setSelectedIndex(selectedIndex);
					}
					list.ensureIndexIsVisible(list.getSelectedIndex());
					setLightWeightPopupEnabled(comboBox.isLightWeightPopupEnabled());

					show(comboBox, popupBounds.x, popupBounds.y);
				}
			};
			popup.getAccessibleContext().setAccessibleParent(comboBox);
			return popup;
		}
	}
}
