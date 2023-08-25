package de.emir.rcp.settings;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import de.emir.rcp.commands.basics.ExitCommand;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.properties.SettingsPropertyEditor;
import de.emir.rcp.util.WidgetUtils;
import de.emir.rcp.views.console.ConsolePanel;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class ConsoleSettingsPage extends AbstractSettingsPage {

	private SettingsPropertyEditor speMaxMsgEditor;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component fillPage() {

		JPanel page = new JPanel();
		GridBagLayout gbl_page = new GridBagLayout();
		gbl_page.columnWidths = new int[]{0, 0, 0};
		gbl_page.rowHeights = new int[]{0, 0, 0};
		gbl_page.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_page.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		page.setLayout(gbl_page);

		PropertyContext context = PropertyStore.getContext(ConsolePanel.PROPERTY_CONTEXT);
		IProperty<Integer> maxMsgProp = context.getProperty(ConsolePanel.PROP_MAXIMUM_MESSAGES, 1000);
		speMaxMsgEditor 		= new SettingsPropertyEditor(maxMsgProp);
		

		
		GridBagConstraints gbc_L = new GridBagConstraints();
		gbc_L.insets = new Insets(0, 0, 0, 5);
		gbc_L.anchor = GridBagConstraints.EAST;
		gbc_L.gridx = 0;
		gbc_L.gridy = 1;
		
		GridBagConstraints gbc_E = new GridBagConstraints();
		gbc_E.fill = GridBagConstraints.HORIZONTAL;
		gbc_E.gridx = 1;
		gbc_E.gridy = 1;

		WidgetUtils.addEditor(page, "Message Count", gbc_L, speMaxMsgEditor, gbc_E);

		return page;
	}

	@Override
	public boolean isDirty() {
		return speMaxMsgEditor.isDirty();
	}

	@Override
	public void finish() {
		speMaxMsgEditor.finish();
	}

}
