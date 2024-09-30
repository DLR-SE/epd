package de.emir.epd.nmeasensor.ui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import de.emir.epd.nmeasensor.ids.NMEASensorIds;
import de.emir.epd.nmeasensor.settings.NMEASensorSettingsPage;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class ReceiversList extends JList<IProperty> {
	protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);
    protected PropertyContext context = PropertyStore.getContext(NMEASensorIds.NMEA_SENSOR_PROP_CONTEXT);

	public ReceiversList() {
		CellRenderer cr = new CellRenderer();
		setCellRenderer(cr);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	protected class CellRenderer implements ListCellRenderer<IProperty> {
		public Component getListCellRendererComponent(JList<? extends IProperty> list, IProperty value, int index, boolean isSelected, boolean cellHasFocus) {
//            if (value == null || value.getValue() == null) return null;
            String namePath = NMEASensorIds.NMEA_SENSOR_PROP + "." + value.getName();
            String name = (String) value.getValue();
            IProperty<String> type = context.getProperty(namePath + '.' + NMEASensorIds.NMEA_SENSOR_PROP_TYPE, "UDP");
            JLabel label = new JLabel(type.getValue() + " " + name);
            IProperty<Boolean> active = context.getProperty(namePath + '.' + NMEASensorIds.NMEA_SENSOR_PROP_ACTIVE, false);
			if (active.getValue()) {
				label.setIcon(new ImageIcon(NMEASensorSettingsPage.class.getResource("/icons/emiricons/32/gps_fixed.png")));
			} else {
				label.setIcon(new ImageIcon(NMEASensorSettingsPage.class.getResource("/icons/emiricons/32/gps_off.png")));
			}
			label.setBackground(isSelected ? UIManager.getColor("List.selectionBackground") : getBackground());
			label.setForeground(isSelected ? UIManager.getColor("List.selectionForeground") : getForeground());
			label.setEnabled(isEnabled());
			label.setFont(getFont());
            label.setOpaque(true);
//			label.setBorder(isSelected ? UIManager.getBorder("List.focusCellHighlightBorder") : noFocusBorder);
			
			return label;
		}
	}
}