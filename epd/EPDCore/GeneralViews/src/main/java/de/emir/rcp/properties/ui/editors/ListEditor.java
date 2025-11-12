package de.emir.rcp.properties.ui.editors;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.emir.model.universal.crs.CoordinateReferenceSystem;
import de.emir.rcp.editor.model.impl.EMIRLabelProvider;
import de.emir.rcp.editor.model.transactions.CreateChildTransaction;
import de.emir.rcp.editors.AbstractEditor;
import de.emir.rcp.editors.transactions.AbstractEditorTransaction;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.cmd.AddListEntryTransaction;
import de.emir.rcp.properties.cmd.MoveListEntryTransaction;
import de.emir.rcp.properties.cmd.RemoveListEntryTransaction;
import de.emir.rcp.properties.impl.AssociationProperty;
import de.emir.rcp.properties.impl.AttributeProperty;
import de.emir.rcp.properties.impl.DelegateReadOnlyProperty;
import de.emir.rcp.properties.impl.UCoreListProperty;
import de.emir.rcp.properties.impl.UCoreProperty;
import de.emir.rcp.util.WidgetUtils;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import de.emir.tuml.ucore.runtime.utils.FeaturePointer;
import de.emir.tuml.ucore.runtime.utils.Pointer;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ListEditor extends AbstractPropertyEditor<List> {
	private static EMIRLabelProvider sLabelProvider = new EMIRLabelProvider();
	private JPanel mEditor = null;
	
	@SuppressWarnings("rawtypes")
	private volatile JList mList;
	
	private IProperty emptyProperty;
	@SuppressWarnings("rawtypes")
	private volatile DefaultListModel mListModel;

    private static final Logger LOG = LogManager.getLogger(ListEditor.class);
    
	public IProperty createProperty(UCoreListProperty lp) {
		Pointer ptr = lp.getPointer();
		UStructuralFeature feature = ptr.getPointedFeature();
		if (feature.isAttribute()) {
			return new AttributeProperty(ptr) {
				private Object mValue = null;
				@Override
				public Object getValue() {
					return mValue;
				}
				@Override
				public void setValue(Object value) {
					mValue = value;
				}
			};
		} else {
			return new AssociationProperty(ptr) {
				private Object mValue = null;
				@Override
				public Object getValue() {
					return mValue;
				}
				@Override
				public void setValue(Object value) {
					mValue = value;
				}
			};
		}
	}
	
	protected void initListeners() {
		getProperty().addPropertyChangeListener(pcl -> {
			updateList(getValue());
		});
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component createComponent() {
		if (mEditor == null){
			mEditor = new JPanel();
			mEditor.setLayout(new BorderLayout());
			
			mList = new JList();
			
			JScrollPane lsp = new JScrollPane(mList);
			mEditor.add(lsp, BorderLayout.CENTER);
			mListModel = new DefaultListModel<>();
			mList.setModel(mListModel);
			
			JPanel panel = new JPanel();
			mEditor.add(panel, BorderLayout.SOUTH);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0};
			gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			
			JButton addButton = new JButton();
			addButton.setIcon(new ImageIcon(IconManager.getImage(ListEditor.class, "icons/emiricons/32/add.png", IconManager.preferedSmallIconSize())));
			addButton.setToolTipText("Add item");
			GridBagConstraints gbc_addButton = new GridBagConstraints();
			gbc_addButton.insets = new Insets(0, 0, 0, 5);
			gbc_addButton.gridx = 1;
			gbc_addButton.gridy = 0;
			panel.add(addButton, gbc_addButton);
			addButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					addEntry(emptyProperty.getValue());
				}
			});
			
			JButton delButton = new JButton();
			delButton.setEnabled(false);
			delButton.setIcon(new ImageIcon(IconManager.getImage(ListEditor.class, "icons/emiricons/32/delete.png", IconManager.preferedSmallIconSize())));
			delButton.setToolTipText("Delete item");
			GridBagConstraints gbc_delButton = new GridBagConstraints();
			gbc_delButton.insets = new Insets(0, 0, 0, 5);
			gbc_delButton.gridx = 2;
			gbc_delButton.gridy = 0;
			panel.add(delButton, gbc_delButton);
			delButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					removeEntries(mList.getSelectedIndices());
				}
			});
			
			JButton topButton = new JButton("");
			topButton.setEnabled(false);
			topButton.setIcon(new ImageIcon(IconManager.getImage(ListEditor.class, "icons/emiricons/32/move_top.png", IconManager.preferedSmallIconSize())));
			topButton.setToolTipText("Move item to top");
			GridBagConstraints gbc_topButton = new GridBagConstraints();
			gbc_topButton.insets = new Insets(0, 0, 0, 5);
			gbc_topButton.gridx = 3;
			gbc_topButton.gridy = 0;
			panel.add(topButton, gbc_topButton);
			topButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					moveEntry(mList.getSelectedIndices(), Integer.MIN_VALUE);
				}
			});
			
			JButton upButton = new JButton();
			upButton.setEnabled(false);
			upButton.setIcon(new ImageIcon(IconManager.getImage(ListEditor.class, "icons/emiricons/32/move_upward.png", IconManager.preferedSmallIconSize())));
			upButton.setToolTipText("Move item up");
			GridBagConstraints gbc_upButton = new GridBagConstraints();
			gbc_upButton.insets = new Insets(0, 0, 0, 5);
			gbc_upButton.gridx = 4;
			gbc_upButton.gridy = 0;
			panel.add(upButton, gbc_upButton);
			upButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					moveEntry(mList.getSelectedIndices(), -1);
				}
			});
			
			JButton downButton = new JButton();
			downButton.setEnabled(false);
			downButton.setIcon(new ImageIcon(IconManager.getImage(ListEditor.class, "icons/emiricons/32/move_downward.png", IconManager.preferedSmallIconSize())));
			downButton.setToolTipText("Move item down");
			GridBagConstraints gbc_downButton = new GridBagConstraints();
			gbc_downButton.insets = new Insets(0, 0, 0, 5);
			gbc_downButton.gridx = 5;
			gbc_downButton.gridy = 0;
			panel.add(downButton, gbc_downButton);
			downButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					moveEntry(mList.getSelectedIndices(), 1);
				}
			});
			
			JButton bottomButton = new JButton("");
			bottomButton.setEnabled(false);
			bottomButton.setIcon(new ImageIcon(IconManager.getImage(ListEditor.class, "icons/emiricons/32/move_bottom.png", IconManager.preferedSmallIconSize())));
			bottomButton.setToolTipText("Move item to bottom");
			GridBagConstraints gbc_bottomButton = new GridBagConstraints();
			gbc_bottomButton.gridx = 6;
			gbc_bottomButton.gridy = 0;
			panel.add(bottomButton, gbc_bottomButton);
			bottomButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					moveEntry(mList.getSelectedIndices(), mList.getModel().getSize() + 1);
				}
			});
			
			if (getProperty().getSubProperties() != null){
				for (Object obj_listEl : getProperty().getSubProperties()){
					if (obj_listEl == null) continue;
					IProperty<?> listEl = (IProperty<?>) obj_listEl;
					String label = sLabelProvider.getLabel(PointerOperations.create((UObject) listEl.getValue()));
					mListModel.addElement(label);
					LOG.debug("Added list element " + label);
				}
			}
			
			emptyProperty = createProperty((UCoreListProperty) getProperty());
			IPropertyEditor editor = PropertyManager.getInstance().getFirstEditor(emptyProperty);
			if (editor == null){
				editor = new StringEditor();
				editor.setProperty(new DelegateReadOnlyProperty(emptyProperty));
			}
			Component editor_comp = editor.getEditor();
			if (editor_comp != null){
				gbc_upButton.gridx = 0;
				gbc_upButton.fill = GridBagConstraints.BOTH;
				gbc_upButton.anchor = GridBagConstraints.WEST;
				panel.add(editor_comp, gbc_upButton);
			}
			
			mList.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					boolean itemSelected = mList.getSelectedIndex() > -1;
					int firstSelected = mList.getSelectedIndices().length > 0 ? mList.getSelectedIndices()[0] : -1;
					int lastSelected = mList.getSelectedIndices().length > 0 ? mList.getSelectedIndices()[mList.getSelectedIndices().length - 1] : -1;
					topButton.setEnabled(itemSelected && firstSelected > 0);
					upButton.setEnabled(itemSelected && firstSelected > 0);
					downButton.setEnabled(itemSelected && lastSelected < mList.getModel().getSize() - 1);
					bottomButton.setEnabled(itemSelected && lastSelected < mList.getModel().getSize() - 1);
					delButton.setEnabled(itemSelected);
				}
			});
			
			updateList(getValue());
			initListeners();
		}
		return mEditor;
	}

	/**
	 * Move the selected entry of this list.
	 * @param selectedIndices The selected items
	 * @param delta The number of indices this item should be moved. Use negative values to move up.
	 */
	protected void moveEntry(int[] selectedIndices, int delta) {
		UCoreProperty property = getUCoreProperty();
		List selectedObjects = new ArrayList();
		int numSelected = selectedIndices.length;
		for (int idx = 0; idx < selectedIndices.length; idx++) {
			selectedObjects.add(mList.getModel().getElementAt(selectedIndices[idx]));
			Pointer ptr = property.getPointer().copy();
			if (ptr instanceof FeaturePointer) {
				((FeaturePointer)ptr).setListIndex(selectedIndices[idx]);
				applyEditorTransaction(new MoveListEntryTransaction(ptr, delta));
			}
		}
		updateList(getValue());
		int[] indices = new int[numSelected];
		int j = 0;
		for (Object obj : selectedObjects) {
			for (int i = 0; i < mList.getModel().getSize(); i++) {
				if (mList.getModel().getElementAt(i).equals(obj)) {
					indices[j] = i;
					j++;
				}
			}
		}
		mList.setSelectedIndices(indices);
	}

	protected void addEntry(Object value) {
		if (value != null) {
			UCoreProperty property = getUCoreProperty();
			Pointer ptr = property.getPointer().copy();
			applyEditorTransaction(new AddListEntryTransaction(ptr, value));
		} else {
			showCreateDialog();
			return;
		}
		updateList(getValue());
	}
	
	protected <T extends UObject> void showCreateDialog() {
		UCoreProperty property = getUCoreProperty();
		Pointer ptr = property.getPointer().copy();
		UClassifier expectedCL = (UClassifier) ptr.getExpectedType(); //exception is wanted
		
		JDialog createDialog = new JDialog(PlatformUtil.getWindowManager().getMainWindow(), "New Child UObject", true);
		
		Function<UClass, Void> func = new Function<UClass, Void>() {
			@Override
			public Void apply(UClass t) {
				T uobj = null;
				if (PlatformUtil.getModelManager().getModelProvider() != null
						&& PlatformUtil.getModelManager().getModelProvider().getTransactionStack() != null) {
					CreateChildTransaction cct = new CreateChildTransaction(ptr, t);
					PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(cct);
					uobj = cct.getCreatedModel();
				} else {
					ULog.debug("Create new instance of: " + ptr + " without ModelTransition");
					uobj = (T) t.createNewInstance();
					ptr.assign(uobj, false);
				}
				
				createDialog.setVisible(false);
				updateList(getValue());
				return null;
			}
		};
		JPanel ctg = WidgetUtils.createLazyLoadingPanel(UCoreMetaRepository.getClassesInheritFrom(expectedCL, true),
				func);
		JScrollPane ctgSP = new JScrollPane(ctg);
		
		createDialog.getContentPane().add(ctgSP);
		createDialog.setSize(640, 480);
		createDialog.setLocationRelativeTo(PlatformUtil.getWindowManager().getMainWindow());
		createDialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
		createDialog.setVisible(true);
	}

	protected void removeEntries(int[] selectedIndices) {
		//removes the selected entries (indices) from the list and update the view
		UCoreProperty property = getUCoreProperty();
		for (int idx = 0; idx < selectedIndices.length; idx++) {
			Pointer ptr = property.getPointer().copy();
			if (ptr instanceof FeaturePointer) {
				((FeaturePointer)ptr).setListIndex(selectedIndices[idx]);
				applyEditorTransaction(new RemoveListEntryTransaction(ptr));
			}
		}
		updateList(getValue());
	}

	private void applyEditorTransaction(AbstractEditorTransaction trans) {
		AbstractEditor editor = PlatformUtil.getEditorManager().getActiveEditor();
		if (editor != null) {
			editor.getTransactionStack().run(trans);
			return ;
		} else {
			AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
			if (mp != null) {
				mp.getTransactionStack().run(trans);
				return ;
			}
		}
		ULog.error("Could not identify context for command");
	}

	protected synchronized void updateList(List value) {
		mListModel.clear();
		for (Object obj : value)
			mListModel.addElement(obj);
	}

	@Override
	public void dispose() {

	}

}
