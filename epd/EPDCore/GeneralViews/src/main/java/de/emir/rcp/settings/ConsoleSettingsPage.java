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
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class ConsoleSettingsPage extends AbstractSettingsPage {

	private SettingsPropertyEditor speMaxMsgEditor;
	private SettingsPropertyEditor speDisplayLogIndex;
	private SettingsPropertyEditor speDisplayTimestamp;
	private SettingsPropertyEditor speDisplayLevel;
	private SettingsPropertyEditor speDisplayIcon;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component fillPage() {

		JPanel page = new JPanel();
		GridBagLayout gbl_page = new GridBagLayout();
		gbl_page.columnWidths = new int[]{0, 0, 0};
		gbl_page.rowHeights = new int[] {30, 30, 30, 30, 30, 30};
		gbl_page.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_page.rowWeights = new double[]{0.1, 0.1, 0.1, 0.1, 0.1, 1.0};
		page.setLayout(gbl_page);

		PropertyContext context = PropertyStore.getContext(ConsolePanel.PROPERTY_CONTEXT);
		IProperty<Integer> maxMsgProp = context.getProperty(ConsolePanel.PROP_MAXIMUM_MESSAGES, 1000);
		speMaxMsgEditor = new SettingsPropertyEditor(maxMsgProp);
		
		IProperty<Boolean> logIndexProp = context.getProperty(ConsolePanel.PROP_DISPLAY_LOG_INDEX, false);
		speDisplayLogIndex = new SettingsPropertyEditor(logIndexProp);
		
		IProperty<Boolean> logTimeProp = context.getProperty(ConsolePanel.PROP_DISPLAY_LOG_TIME, false);
		speDisplayTimestamp = new SettingsPropertyEditor(logTimeProp);
		
		IProperty<Boolean> logLevelProp = context.getProperty(ConsolePanel.PROP_DISPLAY_LOG_LEVEL, false);
		speDisplayLevel = new SettingsPropertyEditor(logLevelProp);
		
		IProperty<Boolean> logIconProp = context.getProperty(ConsolePanel.PROP_DISPLAY_LOG_ICON, false);
		speDisplayIcon = new SettingsPropertyEditor(logIconProp);
		
		GridBagConstraints gbc_L = new GridBagConstraints();
		gbc_L.insets = new Insets(5, 5, 5, 5);
		gbc_L.anchor = GridBagConstraints.EAST;
		gbc_L.gridx = 0;
		gbc_L.gridy = 0;
		
		GridBagConstraints gbc_E = new GridBagConstraints();
		gbc_E.fill = GridBagConstraints.HORIZONTAL;
		gbc_E.insets = new Insets(0, 5, 5, 5);
		gbc_E.gridx = 1;
		gbc_E.gridy = 0;

		WidgetUtils.addEditor(page, "Message Count", gbc_L, speMaxMsgEditor, gbc_E);
		WidgetUtils.addEditor(page, "Display Log Index", gbc_L, speDisplayLogIndex, gbc_E);
		WidgetUtils.addEditor(page, "Display Timestamp", gbc_L, speDisplayTimestamp, gbc_E);
		WidgetUtils.addEditor(page, "Display Log Level", gbc_L, speDisplayLevel, gbc_E);
		WidgetUtils.addEditor(page, "Display Icons", gbc_L, speDisplayIcon, gbc_E);

		return page;
	}

	@Override
	public boolean isDirty() {
		return speMaxMsgEditor.isDirty() || speDisplayTimestamp.isDirty() || speDisplayLogIndex.isDirty()
				|| speDisplayLevel.isDirty() || speDisplayIcon.isDirty();
	}

	@Override
	public void finish() {
		speMaxMsgEditor.finish();
		speDisplayLogIndex.finish();
		speDisplayTimestamp.finish();
		speDisplayLevel.finish();
		speDisplayIcon.finish();
	}

}
