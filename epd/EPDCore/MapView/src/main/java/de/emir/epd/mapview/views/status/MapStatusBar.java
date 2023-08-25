/**
 * 
 */
package de.emir.epd.mapview.views.status;

import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import de.emir.epd.mapview.manager.MapViewManager;
import de.emir.epd.mapview.views.map.LayerController;
import de.emir.epd.mapview.views.tools.AbstractMapViewTool;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.rcp.statusbar.AbstractStatusBarElement;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import javax.swing.JLabel;

/**
 * This status bar displays the current cursor coordinate.
 * 
 * @author Stefan Behrensen <stefan.behrensen@dlr.de>
 *
 */
public class MapStatusBar extends AbstractStatusBarElement {
	private JLabel positionLabel;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component createContents() {
		JPanel parent = new JPanel();
		
		positionLabel = new JLabel("Lat | Lon");
		parent.add(positionLabel);
		createCursorPositionListener();
		return parent;
	}
	
	/**
	 * Register the listener for cursor position updates.
	 */
	private void createCursorPositionListener() {
		ServiceManager.get(MapViewManager.class).subscribeCursorPosition(gp -> {
			String text = CRSUtils.toDegreeMinuteSecond(gp.getLatitude()) + " | "
					+ CRSUtils.toDegreeMinuteSecond(gp.getLongitude());
			positionLabel.setText(text);
		});

	}

}
