package de.emir.epd.mapview.views.layer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import de.emir.epd.mapview.ep.MapLayer;
import de.emir.epd.mapview.ep.MapLayerSettingsPanel;
import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.mapview.views.map.AbstractMapLayerSettingsPanel;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class LayerWidget extends JPanel {

	private static final long serialVersionUID = -2652799213280854000L;

	private MapLayer layer;

	private IProperty layerVisibleProperty;
	private IProperty layerExpandedProperty;

	private JCheckBox chckbxVisible;

	private ImageIcon expandIcon = IconManager.getIcon(this, "icons/emiricons/32/arrow-right.png",
			IconManager.preferedSmallIconSize());
	private ImageIcon collapseIcon = IconManager.getIcon(this, "icons/emiricons/32/arrow_drop_down.png",
			IconManager.preferedSmallIconSize());
	private ImageIcon disabledExpandIcon = IconManager.getIcon(this, "icons/emiricons/32/arrow_right_disabled.png",
			IconManager.preferedSmallIconSize());
	private JLabel expandButton;
	private JPanel expandablePanel;
	private JPanel panel_1;
	private JPanel panel_2;

	private boolean hasSettings;

	public LayerWidget(MapLayer layer) {
		setBorder(new MatteBorder(new Insets(0, 0, 1, 0), UIManager.getColor("controlDkShadow")));

		this.layer = layer;

		PropertyContext context = PropertyStore.getContext(MVBasic.MAP_VIEW_PROP_CONTEXT);
		layerVisibleProperty = context.getProperty(MVBasic.MAP_VIEW_VISIBLE_LAYERS_PROP + "_" + layer.getId(), true);
		layerExpandedProperty = context.getProperty(MVBasic.MAP_VIEW_EXPANDED_LAYERS_PROP + "_" + layer.getId(), false);
		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0 };
		setLayout(gridBagLayout);

		panel_1 = new JPanel();

		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		
		MouseListener expandMouseListener = new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				setExpandedPropertyFromViewState();

			}
		};
		
		expandButton = new JLabel();
		this.expandButton.setHorizontalAlignment(SwingConstants.CENTER);
		expandButton.setIcon(UIManager.getIcon("Tree.collapsedIcon"));//expandIcon);
		expandButton.setDisabledIcon(null);//UIManager.getIcon("Tree.leafIcon"));//disabledExpandIcon);
		expandButton.setPreferredSize(new Dimension(25, 25));
		expandButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		expandButton.addMouseListener(expandMouseListener);

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(4, 2, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel_1.add(expandButton, gbc_btnNewButton);

		JLabel lblXyzLayer = new JLabel(layer.getLabel() == null ? layer.getId() : layer.getLabel());
		GridBagConstraints gbc_lblXyzLayer = new GridBagConstraints();
		gbc_lblXyzLayer.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblXyzLayer.insets = new Insets(0, 0, 0, 5);
		gbc_lblXyzLayer.gridx = 1;
		gbc_lblXyzLayer.gridy = 0;
		lblXyzLayer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblXyzLayer.addMouseListener(expandMouseListener);
		
		panel_1.add(lblXyzLayer, gbc_lblXyzLayer);
		lblXyzLayer.setFont(new Font("Tahoma", Font.BOLD, 13));

		chckbxVisible = new JCheckBox();
		GridBagConstraints gbc_chckbxVisible = new GridBagConstraints();
		gbc_chckbxVisible.gridx = 2;
		gbc_chckbxVisible.gridy = 0;
		panel_1.add(chckbxVisible, gbc_chckbxVisible);
		chckbxVisible.setToolTipText("Visible");

		chckbxVisible.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setVisiblePropertyFromCheckbox();
			}
		});

		String iconPath = layer.getIconPath();

		if (iconPath != null) {
			ImageIcon icon = IconManager.getIcon(this, iconPath, IconManager.preferedSmallIconSize());

			if (icon != null) {
				lblXyzLayer.setIcon(icon);
			}
		}

		expandablePanel = new JPanel();
		this.expandablePanel.setBorder(new EmptyBorder(4, 4, 4, 4));
		expandablePanel.setBackground(UIManager.getColor("Table.background"));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(expandablePanel, gbc_panel);
		this.expandablePanel.setLayout(new BorderLayout(0, 0));

//		panel_2 = new JPanel();
//		panel_2.setBackground(UIManager.getColor("windowBorder"));
//		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
//		gbc_panel_2.insets = new Insets(5, 5, 5, 5);
//		gbc_panel_2.fill = GridBagConstraints.BOTH;
//		gbc_panel_2.gridx = 0;
//		gbc_panel_2.gridy = 0;
//		expandablePanel.add(panel_2, gbc_panel_2);
//		
//		panel_2.setLayout(new BorderLayout());

		// JSeparator separator = new JSeparator();
		// GridBagConstraints gbc_separator = new GridBagConstraints();
		// gbc_separator.insets = new Insets(0, 0, 5, 0);
		// gbc_separator.gridwidth = 3;
		// gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		// gbc_separator.gridx = 0;
		// gbc_separator.gridy = 1;
		// add(separator, gbc_separator);

		MapLayerSettingsPanel sp = layer.getSettingsPanel();

		hasSettings = false;

		if (sp != null) {

			Class<? extends AbstractMapLayerSettingsPanel> spClass = sp.getSettingsPanelClass();

			AbstractMapLayerSettingsPanel settingsPanel = null;

			try {
				settingsPanel = spClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				ULog.error(e1);
			}

			if (settingsPanel != null) {

				Container content = settingsPanel.createContent();
				
				if(content != null) {
					content.setBackground(UIManager.getColor("Table.background"));
					expandablePanel.add(content, BorderLayout.CENTER);
					hasSettings = true;
				}
				
				
				
			}

		}

		if (hasSettings == true) {
			setExpandedFromProperty();
		} else {
			
			expandButton.setEnabled(false);
			
			expandablePanel.setVisible(false);
		}

		setCheckboxFromVisibleProperty();

		layerVisibleProperty.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				setCheckboxFromVisibleProperty();

			}
		});

		layerExpandedProperty.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				setExpandedFromProperty();

			}
		});

	}

	protected void setExpandedFromProperty() {
		if (!hasSettings) return;
		
		boolean value = (boolean) layerExpandedProperty.getValue();

		if (value == true) {
			expandButton.setIcon(UIManager.getIcon("Tree.expandedIcon"));

		} else {
			expandButton.setIcon(UIManager.getIcon("Tree.collapsedIcon"));

		}

		expandablePanel.setVisible(value);

		setMaximumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));

	}

	private void setCheckboxFromVisibleProperty() {

		boolean value = (boolean) layerVisibleProperty.getValue();
		chckbxVisible.setSelected(value);

	}

	private void setVisiblePropertyFromCheckbox() {

		layerVisibleProperty.setValue(chckbxVisible.isSelected());

	}

	private void setExpandedPropertyFromViewState() {

		layerExpandedProperty.setValue(!expandablePanel.isVisible());

	}

}
