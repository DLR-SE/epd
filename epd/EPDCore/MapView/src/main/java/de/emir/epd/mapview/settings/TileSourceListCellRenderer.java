package de.emir.epd.mapview.settings;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

import de.emir.epd.mapview.ep.TileSource;
import de.emir.tuml.ucore.runtime.resources.IconManager;

class TileSourceListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = -7799441088157759804L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		TileSource ts = (TileSource) value;

		String iconPath = ts.getIcon();
		if (iconPath != null) {

			ImageIcon icon = IconManager.getIcon(this, iconPath, IconManager.preferedSmallIconSize());


			if (icon != null) {
				setIcon(icon);
			}
		}

		setText(ts.getLabel() != null ? ts.getLabel() : ts.getId());

		return this;
	}
}