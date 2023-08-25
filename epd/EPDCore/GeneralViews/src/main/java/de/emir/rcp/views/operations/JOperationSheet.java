package de.emir.rcp.views.operations;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.emir.rcp.manager.OperationManager;
import de.emir.rcp.manager.OperationManager.CallableOperation;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.properties.IPropertyEditor;
import de.emir.rcp.properties.impl.DelegateReadOnlyProperty;
import de.emir.rcp.properties.ui.editors.StringEditor;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import javax.swing.JTree;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JList;

/**
 * The OperationSheet displays the operations available for one classifier. 
 * Each Operation that shall be displayed has to fullfill one of the following two conditions: 
 * 1) the operation is annoted with the following annotation: 
 * 		@UserCallable
 * 		with the optional attribute: MethodName 
 * 		@UserCallable(MethodName := "CallableMethod42)
 * 2) The operation is part of an class that inherits de.emir.model.universal.physics.ICapability. 
 * 
 * regardless of the both methods to publish an operation, the @Hidden annotation hides an annotation
 * this goes also for operations defined in an ICapability or (why ever) annotated as UserCallable
 * 
 * @author sschweigert
 *
 */
public class JOperationSheet extends JPanel 
{

	private int mGridY = 0;
	private JTextField textField;
	
	private HashSet<PropertyChangeListener>		mListeners = new HashSet<>();
	private JList<CallableOperation> mOperationOverviewListPanel;
	
	/**
	 * Create the panel.
	 */
	public JOperationSheet() {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		mOperationOverviewListPanel = new JList<CallableOperation>();
		mOperationOverviewListPanel.setModel(new DefaultListModel<>());
		mOperationOverviewListPanel.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (value instanceof CallableOperation) {
					setText(((CallableOperation)value).operation.getName());
					setToolTipText(((CallableOperation)value).operation.getDocumentation());
				}
				return this;
			}
		});
		scrollPane.setViewportView(mOperationOverviewListPanel);
		
		
		
		JPanel mOperationPanel = new JPanel();
		mOperationPanel.setLayout(new BorderLayout());
		JScrollPane scrollPane_1 = new JScrollPane(mOperationPanel);		
		splitPane.setRightComponent(scrollPane_1);
		
		
		mOperationOverviewListPanel.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				mOperationPanel.removeAll();
				mOperationPanel.setLayout(new BorderLayout());
				int fidx = e.getFirstIndex();
				ListModel<CallableOperation> model = mOperationOverviewListPanel.getModel();
				if (model.getSize() > fidx) {
					CallableOperation op = model.getElementAt(e.getFirstIndex());
					mOperationPanel.add(new OperationPanel(op.instance, op.operation), BorderLayout.CENTER);
					updateUI();
				}
			}
		});
	}
	
	

	
	
	public void clear() {
		this.removeAll();
		mGridY = 0;
	}

	
	public void setInput(UObject uobj) {
		OperationManager omgr = ExtensionPointManager.getExtensionPoint(OperationManager.class);
		List<CallableOperation> operations = omgr.getOperations(uobj);
		DefaultListModel<CallableOperation> model = (DefaultListModel<CallableOperation>) mOperationOverviewListPanel.getModel();
		model.clear();
		for (CallableOperation op : operations)
			model.addElement(op);
	}
	







}
