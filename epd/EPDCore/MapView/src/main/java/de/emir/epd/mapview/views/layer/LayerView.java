package de.emir.epd.mapview.views.layer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;

import de.emir.epd.mapview.ep.MapLayer;
import de.emir.epd.mapview.ep.MapViewExtensionPoint;
import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.mapview.manager.MapViewManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.AbstractView;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;

public class LayerView extends AbstractView {

	public LayerView(String id) {
		super(id);

	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component createContent() {

		Container parent = new JPanel();
		
		MapViewExtensionPoint ep = ServiceManager.get(MapViewManager.class).getExtensionPoint();

		List<MapLayer> layers = ep.getOrderedLayers();

		parent.setLayout(new BorderLayout());

		JToolBar mToolBar = new JToolBar();

		parent.add(mToolBar, BorderLayout.NORTH);
		mToolBar.setMargin(new Insets(0, 0, 0, 0));

		mToolBar.setFloatable(false);
		
		PlatformUtil.getMenuManager().fillToolbar(mToolBar, MVBasic.LAYER_VIEW_TOOLBAR_ID);

		JPanel scrolledPanel = new JPanel();
		scrolledPanel.setLayout(new BoxLayout(scrolledPanel, BoxLayout.Y_AXIS));

		JScrollPane sc = new JScrollPane();
		sc.setBorder(null);
		sc.setViewportBorder(null);
		sc.getVerticalScrollBar().setUnitIncrement(20);

		for (MapLayer layer : layers) {

			LayerWidget w = new LayerWidget(layer);

			scrolledPanel.add(w);
			w.setMaximumSize(new Dimension(Integer.MAX_VALUE, w.getPreferredSize().height));
		}
		sc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sc.setViewportView(scrolledPanel);
		parent.add(sc);
		
		return parent;

	}

	@Override
	public void onOpen() {

	}

	@Override
	public void onClose() {

	}

}
