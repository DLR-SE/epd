package de.emir.epd.mapview.views.map;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.mapview.manager.MapViewManager;
import de.emir.epd.mapview.views.map.cache.DeleteTileCacheJob;
import de.emir.epd.mapview.views.tools.AbstractMapViewTool;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Envelope;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.views.AbstractView;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import java.awt.BorderLayout;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class MapView extends AbstractView {

	private MapViewerWithTools mapViewer;

	private PropertyContext context = PropertyStore.getContext(MVBasic.MAP_VIEW_PROP_CONTEXT);
	private IProperty<String> tileSourceProperty = context.getProperty(MVBasic.MAP_VIEW_PROP_TILE_SOURCE,
			MVBasic.OPEN_STREET_MAP_TILE_SOURCE_ID);
	private String attribution;
	private MapViewerWithTools mapView;
	private JXMapViewer mv = new JXMapViewer();
	private JToolBar mapToolBar = new JToolBar();
	private final JPanel overlayPanel = new JPanel();
	private final JPanel tbPanel = new JPanel();

	private JLabel dtrpnMapAttribution = new JLabel();
	private final JPanel attributionPanel = new JPanel();
	private final JToolBar toolBar = new JToolBar();
	private final JButton zoomOutButton = new JButton("");
	private final JButton zoomInButton = new JButton("");
	
	public MapView(String id) {
		super(id);

		mapViewer = new MapViewerWithTools();
		mv = mapViewer.getJXMapViewer();
	}

	@Override
	public void onOpen() {
		initTools();
		mapViewer.startDrawThread();
	}

	@Override
	public void onClose() {
		mapViewer.stopDrawThread();

	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component createContent() {
		Container parent = new JPanel();

		updateTileSource();
		loadCurrentPosition();

		
		addListeners();
		parent.setLayout(new BorderLayout(0, 0));
		
		mv.setPreferredSize(parent.getPreferredSize());
		mv.setLayout(new BorderLayout(0, 0));
		parent.add(mv, BorderLayout.CENTER);
		overlayPanel.setOpaque(false);
		
		mv.add(overlayPanel, BorderLayout.CENTER);
		overlayPanel.setLayout(new BorderLayout(0, 0));
		dtrpnMapAttribution.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), new EmptyBorder(4, 4, 4, 4)));
		dtrpnMapAttribution.setBackground(SystemColor.info);
		dtrpnMapAttribution.setForeground(SystemColor.infoText);
		dtrpnMapAttribution.setOpaque(true);
		FlowLayout flowLayout_1 = (FlowLayout) attributionPanel.getLayout();
		flowLayout_1.setAlignOnBaseline(true);
		flowLayout_1.setAlignment(FlowLayout.LEADING);
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		attributionPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		attributionPanel.setOpaque(false);
		attributionPanel.add(dtrpnMapAttribution);
		dtrpnMapAttribution.setText(mv.getTileFactory().getInfo().getAttribution());
		dtrpnMapAttribution.setToolTipText(mv.getTileFactory().getInfo().getAttribution());
		overlayPanel.add(attributionPanel, BorderLayout.SOUTH);
		toolBar.setOpaque(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		
		overlayPanel.add(toolBar, BorderLayout.WEST);
		zoomOutButton.setOpaque(false);
		zoomOutButton.setIcon(new ImageIcon(MapView.class.getResource("/icons/emir/zoom_out.png")));
		zoomOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mv.setZoom(mv.getZoom() + 1);
			}
		});
		
		toolBar.add(zoomOutButton);
		zoomInButton.setOpaque(false);
		zoomInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mv.setZoom(mv.getZoom() - 1);
			}
		});
		zoomInButton.setIcon(new ImageIcon(MapView.class.getResource("/icons/emir/zoom_in.png")));
		
		toolBar.add(zoomInButton);

		FlowLayout flowLayout = (FlowLayout) tbPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		tbPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		tbPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
				
		parent.add(tbPanel, BorderLayout.NORTH);
		tbPanel.add(mapToolBar);
		mapToolBar.setMargin(new Insets(0, 0, 0, 0));
				
		PlatformUtil.getMenuManager().fillToolbar(mapToolBar, MVBasic.MAP_VIEW_TOOLBAR_ID);
		
		new MapMovementController(mapViewer);

		return parent;
	}

	private void initTools() {

		ServiceManager.get(MapViewManager.class).fillToolsList();

		Map<String, AbstractMapViewTool> tools =  ServiceManager.get(MapViewManager.class).getTools();

		for (AbstractMapViewTool tool : tools.values()) {
			tool.init(mapViewer);
		}

		ServiceManager.get(MapViewManager.class).setDefaultTool(MVBasic.TOOLS_BASIC_SELECTION);
		ServiceManager.get(MapViewManager.class).setActiveTool(MVBasic.TOOLS_BASIC_SELECTION);
	}

	private void addListeners() {

		tileSourceProperty.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {

				PlatformUtil.getJobManager().schedule(new DeleteTileCacheJob(), cb -> updateTileSource());

			}
		});

	}

	private void updateTileSource() {

		Object propValue = tileSourceProperty.getValue();

		if (propValue instanceof String == false) {
			return;
		}

		AbstractTileSource ts = ServiceManager.get(MapViewManager.class)
				.getTileSourceInstance((String) propValue);

		if (ts == null) {
			return;
		}

		mapViewer.setTileSource(ts);
		attribution = ts.getAttribution();
		dtrpnMapAttribution.setText(attribution);
		dtrpnMapAttribution.setToolTipText(attribution);
	}

	@Override
	public void onShutdown() {
		storeCurrentPosition();
	}

	private void storeCurrentPosition() {

		JXMapViewer viewer = mapViewer.getJXMapViewer();
		int zoom = viewer.getZoom();
		GeoPosition position = viewer.getCenterPosition();

		IProperty positionLatProperty = context.getProperty(MVBasic.MAP_VIEW_POSITION_PROP_LAT);
		IProperty positionLonProperty = context.getProperty(MVBasic.MAP_VIEW_POSITION_PROP_LON);

		double lat = position.getLatitude();
		double lon = position.getLongitude();

		positionLatProperty.setValue(lat);
		positionLonProperty.setValue(lon);

		IProperty zoomProperty = context.getProperty(MVBasic.MAP_VIEW_ZOOM_PROP);
		zoomProperty.setValue(zoom);
	}

	private void loadCurrentPosition() {
		JXMapViewer viewer = mapViewer.getJXMapViewer();

		IProperty positionLatProperty = context.getProperty(MVBasic.MAP_VIEW_POSITION_PROP_LAT, 0d);
		IProperty positionLonProperty = context.getProperty(MVBasic.MAP_VIEW_POSITION_PROP_LON, 0d);

		double lat = (double) positionLatProperty.getValue();
		double lon = (double) positionLonProperty.getValue();

		GeoPosition position = new GeoPosition(lat, lon);

		IProperty zoomProperty = context.getProperty(MVBasic.MAP_VIEW_ZOOM_PROP);

		Object zoomObj = zoomProperty.getValue();

		if (zoomObj == null) {
			return;
		}

		int zoom = (int) zoomObj;

		viewer.setCenterPosition(position);
		viewer.setZoom(zoom);

	}

	public void centerOnEnvelope(Envelope envelope) {
		Coordinate center = envelope.getCenter();
		mapViewer.gotoPosition(center.getLongitude(), center.getLatitude(), mapViewer.getZoom());
	}

	public void zoomToEnvelope(Envelope envelope) {

		Set<GeoPosition> geoPositions = new HashSet<>();
		for (Coordinate vertex : envelope.getVertices()) {

			geoPositions.add(new GeoPosition(vertex.getLatitude(), vertex.getLongitude()));
		}
        if (geoPositions.size() > 1) {
            mapViewer.getJXMapViewer().zoomToBestFit(geoPositions, 0.95);
        } else {
            if (geoPositions.iterator().hasNext()) {
                mapViewer.getJXMapViewer().setCenterPosition(geoPositions.iterator().next());
                mapViewer.getJXMapViewer().setZoom(10);
            }
        }
	}

	public void centerOnCoordinate(Coordinate coordinate) {
		zoomToCoordinate(coordinate, mapViewer.getZoom());
	}

	public void zoomToCoordinate(Coordinate coordinate) {
		zoomToCoordinate(coordinate, 100);
	}

	public void zoomToCoordinate(Coordinate coordinate, int zoom) {
		mapViewer.gotoPosition(coordinate.getLongitude(), coordinate.getLatitude(), zoom);
	}

	public MapViewerWithTools getViewer() {
		return mapViewer;
	}

}
