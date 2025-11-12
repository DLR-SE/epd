package de.emir.epd.mapview.settings;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.emir.epd.mapview.ep.TileSource;
import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.mapview.manager.MapViewManager;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.settings.AbstractSettingsPage;
import de.emir.rcp.ui.utils.properties.PropertyCheckboxWidget;
import de.emir.rcp.ui.utils.properties.PropertySpinnerWidget;
import de.emir.rcp.ui.utils.properties.PropertyTextWidget;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.prop.IProperty;

public class MapViewSettingsPage extends AbstractSettingsPage {

	private JList<TileSource> list;

	private IProperty tileSourceProp;

	private TileSource selectedTileSource;

	private TileSource initialTileSource = null;

	private PropertyTextWidget wmsUrlText;
	private PropertyTextWidget wmsLayerText;
	private PropertyTextWidget wmsAttributionText;
	private JPanel wmsPanel;

	private PropertyCheckboxWidget chckbxCacheTilesLocally;
	private PropertySpinnerWidget maxCachedTiles;

	private JPanel p;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component fillPage() {



		PropertyContext context = PropertyStore.getContext(MVBasic.MAP_VIEW_PROP_CONTEXT);
		tileSourceProp = context.getProperty(MVBasic.MAP_VIEW_PROP_TILE_SOURCE, MVBasic.OPEN_STREET_MAP_TILE_SOURCE_ID);

		Object initialTileSourceId = tileSourceProp.getValue();
		initialTileSource =  ServiceManager.get(MapViewManager.class).getTileSource((String) initialTileSourceId);
		selectedTileSource = initialTileSource;
		p = new JPanel();

		GridBagLayout gbl_p = new GridBagLayout();

		gbl_p.columnWeights = new double[] { 1.0 };
		gbl_p.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0 };
		p.setLayout(gbl_p);

		JLabel lblTileSource = new JLabel("Tile Source");
		lblTileSource.setFont(lblTileSource.getFont().deriveFont(Font.BOLD, 13));
		GridBagConstraints gbc_lblTileSource = new GridBagConstraints();
		gbc_lblTileSource.anchor = GridBagConstraints.WEST;
		gbc_lblTileSource.insets = new Insets(10, 5, 5, 0);
		gbc_lblTileSource.gridx = 0;
		gbc_lblTileSource.gridy = 0;
		p.add(lblTileSource, gbc_lblTileSource);

		JLabel lblNewLabel = new JLabel(
				"<html>A Tile Source is used to load the map in the Map View. Select a source. If necessary, further parameters must be set.</html>");
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(Font.ITALIC, 11));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.insets = new Insets(0, 5, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		p.add(lblNewLabel, gbc_lblNewLabel);

		chckbxCacheTilesLocally = new PropertyCheckboxWidget("Enable Persistent Caching", MVBasic.MAP_VIEW_PROP_CONTEXT,
				MVBasic.MAP_VIEW_PROP_CACHE_TILES_ON_HARD_DRIVE, false);
		GridBagConstraints gbc_chckbxCacheTilesLocally = new GridBagConstraints();
		gbc_chckbxCacheTilesLocally.anchor = GridBagConstraints.WEST;
		gbc_chckbxCacheTilesLocally.insets = new Insets(10, 0, 0, 0);
		gbc_chckbxCacheTilesLocally.gridx = 0;
		gbc_chckbxCacheTilesLocally.gridy = 2;
		p.add(chckbxCacheTilesLocally, gbc_chckbxCacheTilesLocally);
		chckbxCacheTilesLocally.setFont(chckbxCacheTilesLocally.getFont().deriveFont(Font.BOLD, 11));
		chckbxCacheTilesLocally.setText("Hard Drive Caching");

		JLabel lblstoresTileData = new JLabel(
				"<html>Caches tile image data on your hard drive. Note: Changes within tile source may not be considered.</html>");
		lblstoresTileData.setFont(lblstoresTileData.getFont().deriveFont(Font.ITALIC, 11));
		GridBagConstraints gbc_lblstoresTileData = new GridBagConstraints();
		gbc_lblstoresTileData.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblstoresTileData.insets = new Insets(0, 5, 5, 0);
		gbc_lblstoresTileData.gridx = 0;
		gbc_lblstoresTileData.gridy = 3;
		p.add(lblstoresTileData, gbc_lblstoresTileData);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 5, 15, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		p.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[] { 0.0, 1.0 };
		gbl_panel.rowWeights = new double[] { 0.0 };
		panel.setLayout(gbl_panel);

		JLabel lblMaxFiles = new JLabel("Max. Tiles:");
		GridBagConstraints gbc_lblMaxFiles = new GridBagConstraints();
		gbc_lblMaxFiles.insets = new Insets(0, 0, 0, 5);
		gbc_lblMaxFiles.gridx = 0;
		gbc_lblMaxFiles.gridy = 0;
		panel.add(lblMaxFiles, gbc_lblMaxFiles);

		maxCachedTiles = new PropertySpinnerWidget(MVBasic.MAP_VIEW_PROP_CONTEXT,
				MVBasic.MAP_VIEW_PROP_MAX_HARD_DRIVE_CACHED_TILES, 500);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel.add(maxCachedTiles, gbc_textField);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 5, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 5;

		p.add(scrollPane, gbc_scrollPane);

		chckbxCacheTilesLocally.addItemListener(e -> maxCachedTiles.setEnabled(chckbxCacheTilesLocally.isSelected()));
		maxCachedTiles.setEnabled(chckbxCacheTilesLocally.isSelected());
		list = new JList<>();
		scrollPane.setViewportView(list);

		TileSourceListCellRenderer renderer = new TileSourceListCellRenderer();
		list.setCellRenderer(renderer);

		DefaultListModel<TileSource> model = new DefaultListModel<>();

		Map<String, TileSource> sources =  ServiceManager.get(MapViewManager.class).getExtensionPoint().getTileSources();

		for (TileSource ts : sources.values()) {
			model.addElement(ts);
		}

		list.setModel(model);

		wmsPanel = new JPanel();
		GridBagConstraints gbc_wmsPanel = new GridBagConstraints();
		gbc_wmsPanel.anchor = GridBagConstraints.NORTH;
		gbc_wmsPanel.insets = new Insets(0, 5, 0, 5);
		gbc_wmsPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_wmsPanel.gridx = 0;
		gbc_wmsPanel.gridy = 6;
		p.add(wmsPanel, gbc_wmsPanel);
		GridBagLayout gbl_wmsPanel = new GridBagLayout();

		gbl_wmsPanel.columnWeights = new double[] { 0.0, 1.0 };
		gbl_wmsPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0 };
		wmsPanel.setLayout(gbl_wmsPanel);

		JLabel lblWmsSettings = new JLabel("WMS Settings");
		lblWmsSettings.setFont(lblWmsSettings.getFont().deriveFont(Font.BOLD, 11));
		GridBagConstraints gbc_lblWmsSettings = new GridBagConstraints();
		gbc_lblWmsSettings.anchor = GridBagConstraints.WEST;
		gbc_lblWmsSettings.gridwidth = 2;
		gbc_lblWmsSettings.insets = new Insets(0, 0, 5, 0);
		gbc_lblWmsSettings.gridx = 0;
		gbc_lblWmsSettings.gridy = 0;
		wmsPanel.add(lblWmsSettings, gbc_lblWmsSettings);

		JLabel lblNewLabel_1 = new JLabel("URL: ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		wmsPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		wmsUrlText = new PropertyTextWidget(MVBasic.MAP_VIEW_PROP_CONTEXT, MVBasic.MAP_VIEW_PROP_WMS_URL, "");

		GridBagConstraints gbc_wmsUrlText = new GridBagConstraints();
		gbc_wmsUrlText.fill = GridBagConstraints.HORIZONTAL;
		gbc_wmsUrlText.insets = new Insets(0, 0, 5, 0);
		gbc_wmsUrlText.gridx = 1;
		gbc_wmsUrlText.gridy = 1;
		wmsPanel.add(wmsUrlText, gbc_wmsUrlText);

		JLabel lblNewLabel_2 = new JLabel("Layer:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		wmsPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		wmsLayerText = new PropertyTextWidget(MVBasic.MAP_VIEW_PROP_CONTEXT, MVBasic.MAP_VIEW_PROP_WMS_LAYER, "");
		GridBagConstraints gbc_wmsLayerText = new GridBagConstraints();
		gbc_wmsLayerText.insets = new Insets(0, 0, 5, 0);
		gbc_wmsLayerText.fill = GridBagConstraints.HORIZONTAL;
		gbc_wmsLayerText.gridx = 1;
		gbc_wmsLayerText.gridy = 2;
		wmsPanel.add(wmsLayerText, gbc_wmsLayerText);
		
		JLabel lblNewLabel_3 = new JLabel("Attribution:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		wmsPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		wmsAttributionText = new PropertyTextWidget("de.emir.epd.mapview.propertiesContext.MapViewPropertiesContext", "de.emir.epd.mapview.property.WMSAttribution", "");
		GridBagLayout gbl_wmsAttributionText = (GridBagLayout) wmsAttributionText.getLayout();
		gbl_wmsAttributionText.rowWeights = new double[]{0.0};
		gbl_wmsAttributionText.rowHeights = new int[]{0};
		gbl_wmsAttributionText.columnWeights = new double[]{1.0};
		gbl_wmsAttributionText.columnWidths = new int[]{0};
		GridBagConstraints gbc_wmsAttributionText = new GridBagConstraints();
		gbc_wmsAttributionText.fill = GridBagConstraints.BOTH;
		gbc_wmsAttributionText.gridx = 1;
		gbc_wmsAttributionText.gridy = 3;
		wmsPanel.add(wmsAttributionText, gbc_wmsAttributionText);

		list.setSelectedValue(initialTileSource, true);

		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
                selectedTileSource = list.getSelectedValue();
				handleWMSPanel();
			}
		});
		handleWMSPanel();
		return p;	
	}

	private void handleWMSPanel() {
		if (selectedTileSource == null) {
			wmsPanel.setVisible(false);
			return;
		}
		String id = selectedTileSource.getId();
		if (id == null) {
			wmsPanel.setVisible(false);
			return;
		}
		wmsPanel.setVisible(id.equals(MVBasic.WMS_TILE_SOURCE_ID));
	}

	@Override
	public boolean isDirty() {
		return selectedTileSource != initialTileSource || wmsUrlText.isDirty() || wmsLayerText.isDirty()
				|| chckbxCacheTilesLocally.isDirty() || maxCachedTiles.isDirty() || wmsAttributionText.isDirty();
	}

	@Override
	public void finish() {
		if (selectedTileSource == null) {
			return;
		}

		String id = selectedTileSource.getId();

		tileSourceProp.setValue(id);
		chckbxCacheTilesLocally.finish();
		wmsUrlText.finish();
		wmsLayerText.finish();
		maxCachedTiles.finish();
		wmsAttributionText.finish();
	}
}
