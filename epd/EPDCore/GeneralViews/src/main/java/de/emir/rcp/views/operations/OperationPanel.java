package de.emir.rcp.views.operations;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import de.emir.rcp.manager.OperationManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.impl.ParameterProperty;
import de.emir.rcp.views.properties.JPropertySheet;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UParameter;
import de.emir.tuml.ucore.runtime.UType;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;

public class OperationPanel extends JPanel {

	private JPropertySheet mResultPanel;
	private UOperation mOperation;
	private UObject mInstance;
	private JPropertySheet mInputPropertySheet;

	/**
	 * Create the panel.
	 */
	public OperationPanel(UObject instance, UOperation operation) {
		mOperation = operation;
		mInstance = instance;
		setup();
	}

	private void setup() {
		removeAll();
		
		OperationManager omgr = ExtensionPointManager.getExtensionPoint(OperationManager.class);
		String classifierName = ((UClassifier)mOperation.getUContainer()).getName();
		String operationName = omgr.getOperationName(mOperation);
		String returnTypeName = omgr.getReturnTypeName(mOperation);
				
		setBorder(new TitledBorder(null, classifierName + " : " + operationName + " : " + returnTypeName, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Input Parameter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		mInputPropertySheet = new JPropertySheet();
		scrollPane.setViewportView(mInputPropertySheet);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Result", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		mResultPanel = new JPropertySheet();
		JScrollPane scrollPane_1 = new JScrollPane(mResultPanel);
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		JButton btnInvoke = new JButton("Invoke");
		add(btnInvoke, BorderLayout.EAST);

		
		List<IProperty> inputProperties = omgr.getInputParameterAsProperty(mInstance, mOperation);
		mInputPropertySheet.addProperties(inputProperties);
		
		if (mInstance != null) {
			btnInvoke.addActionListener(al -> {
				Object[] params = null;
				if (inputProperties != null && inputProperties.isEmpty() == false) {
					//@note: this assumes, that the order of the properties remains the same as defined in the TUML-File and expected by the operation
					params = new Object[inputProperties.size()];
					for (int i = 0; i < params.length; i++)
						params[i] = inputProperties.get(i).getValue();
				}
				Object result = mOperation.invoke(mInstance, params);
				if (result != null) {
					UParameter p = mOperation.getReturnParameter();
					ParameterProperty pp = new ParameterProperty(p);
					pp.setValue(result);
					mResultPanel.clear();
					mResultPanel.addProperties(Arrays.asList(pp));
				}else {
					mResultPanel.clear();
				}
				mResultPanel.updateUI();
			});
		}else {
			btnInvoke.setEnabled(false);
		}
	}
	
}
