package de.emir.rcp.properties.ui.editors;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.emir.rcp.editor.model.impl.EMIRLabelProvider;
import de.emir.rcp.editors.AbstractEditor;
import de.emir.rcp.editors.transactions.AbstractEditorTransaction;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.cmd.AddListEntryTransaction;
import de.emir.rcp.properties.cmd.RemoveListEntryTransaction;
import de.emir.rcp.properties.impl.AssociationProperty;
import de.emir.rcp.properties.impl.AttributeProperty;
import de.emir.rcp.properties.impl.DelegateReadOnlyProperty;
import de.emir.rcp.properties.impl.UCoreListProperty;
import de.emir.rcp.properties.impl.UCoreProperty;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.utils.FeaturePointer;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class ListEditor extends AbstractPropertyEditor<List> {

	private static EMIRLabelProvider 	sLabelProvider = new EMIRLabelProvider();
	
	JPanel 					mEditor = null;
	
	@SuppressWarnings("rawtypes")
	private JList mList;
	
	private IProperty emptyProperty;
	@SuppressWarnings("rawtypes")
	private DefaultListModel mListModel;

	public IProperty createProperty(UCoreListProperty lp){
		Pointer ptr = lp.getPointer();
		UStructuralFeature feature = ptr.getPointedFeature();
		if (feature.isAttribute()){
			return new AttributeProperty(ptr){
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
		}else{
			return new AssociationProperty(ptr){
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
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0};
			gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			
			
			
			JButton addButton = new JButton("+");
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
			gbc_btnNewButton.gridx = 1;
			gbc_btnNewButton.gridy = 0;
			panel.add(addButton, gbc_btnNewButton);
			addButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					addEntry(emptyProperty.getValue());
				}
			});
			
			JButton delButton = new JButton("-");
			GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
			gbc_btnNewButton_1.gridx = 2;
			gbc_btnNewButton_1.gridy = 0;
			panel.add(delButton, gbc_btnNewButton_1);
			delButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					removeEntries(mList.getSelectedIndices());
				}
			});
			
			if (getProperty().getSubProperties() != null){
				for (Object obj_listEl : getProperty().getSubProperties()){
					if (obj_listEl == null) continue;
					IProperty<?> listEl = (IProperty<?>)obj_listEl;
					String label = sLabelProvider.getLabel(PointerOperations.create((UObject) listEl.getValue()));
					mListModel.addElement(label);
					System.out.println(label);
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
				gbc_btnNewButton.gridx = 0;
				gbc_btnNewButton.fill = GridBagConstraints.BOTH;
				gbc_btnNewButton.anchor = GridBagConstraints.WEST;
				panel.add(editor_comp, gbc_btnNewButton);
			}
			
			updateList(getValue());
		}
		return mEditor;
	}

	protected void addEntry(Object value) {
		if (value != null) {
			UCoreProperty property = getUCoreProperty();
			Pointer ptr = property.getPointer().copy();
			applyEditorTransaction(new AddListEntryTransaction(ptr, value));
		}
		updateList(getValue());
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
		}else {
			AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();
			if (mp != null) {
				mp.getTransactionStack().run(trans);
				return ;
			}
		}
		ULog.error("Could not identify context for command");
	}

	protected void updateList(List value) {
		mListModel.clear();
		for (Object obj : value)
			mListModel.addElement(obj);
	}

	@Override
	public void dispose() {

	}

}
