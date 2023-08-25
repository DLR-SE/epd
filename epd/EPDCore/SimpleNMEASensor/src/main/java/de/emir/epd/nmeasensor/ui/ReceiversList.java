package de.emir.epd.nmeasensor.ui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import de.emir.epd.nmeasensor.data.ReceiverProperty;
import de.emir.epd.nmeasensor.settings.NMEASensorSettingsPage;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class ReceiversList extends JList<ReceiverProperty> {
	protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

	public ReceiversList() {
		CellRenderer cr = new CellRenderer();
		setCellRenderer(cr);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	protected class CellRenderer implements ListCellRenderer<ReceiverProperty> {
		public Component getListCellRendererComponent(JList list, ReceiverProperty value, int index, boolean isSelected,
				boolean cellHasFocus) {
            if (value == null || !(value instanceof ReceiverProperty)) return null;
            JLabel label = new JLabel(((ReceiverProperty) value).getReceiverType().name() + " " + ((ReceiverProperty) value).getLabel());
			if (((ReceiverProperty) value).isActive()) {
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