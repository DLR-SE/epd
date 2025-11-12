package de.emir.epd.ownship.settings;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import de.emir.epd.model.EPDModelUtils;
import de.emir.epd.ownship.ids.OwnshipBasics;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.universal.physics.ObjectSurfaceInformation;
import de.emir.model.universal.physics.impl.ObjectSurfaceInformationImpl;
import de.emir.model.universal.spatial.Geometry;
import de.emir.rcp.model.transactions.CompoundTransaction;
import de.emir.rcp.parts.vesseleditor.utils.PredefinedGeometryItem;
import de.emir.rcp.parts.vesseleditor.view.parts.VesselEditorPart;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.settings.AbstractSettingsPage;
import de.emir.rcp.ui.utils.properties.PropertyTextWidget;
import de.emir.service.geometry.impl.WKTUtil;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OwnshipViewerSettingsPage extends AbstractSettingsPage {
	private IProperty ownshipSourceProp;
	private ButtonModel initialSource = null;
	private PropertyTextWidget aisTargetMMSI;
	private JPanel sourcePanel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnAisTargetBy;
	private JRadioButton rdbtnOwnshipNmeaSentences;
	private JRadioButton rdbtnNoProcessing;
	private Vessel ownship;
	private JPanel vePanel = new JPanel();
	private String oldMMSI = "211876480";

	public enum OwnshipSource {NO_PROCESSING, AISTARGET, INTERNAL}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Component fillPage() {
		PropertyContext context = PropertyStore.getContext(OwnshipBasics.OWNSHIP_VIEWER_PROP_CONTEXT);
		ownshipSourceProp = context.getProperty(OwnshipBasics.OWNSHIP_VIEWER_PROP_OWNSHIP_SOURCE, OwnshipSource.AISTARGET.name());

		JPanel p = new JPanel();
	
		GridBagLayout gbl_p = new GridBagLayout();
		gbl_p.rowHeights = new int[]{0, 0, 0, 0, 0, 0};

		gbl_p.columnWeights = new double[] { 1.0 };
		gbl_p.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
		p.setLayout(gbl_p);

		JLabel lblOwnshipSource = new JLabel("Ownship Source");
		lblOwnshipSource.setFont(lblOwnshipSource.getFont().deriveFont(Font.BOLD, 13));
		GridBagConstraints gbc_lblOwnshipSource = new GridBagConstraints();
		gbc_lblOwnshipSource.anchor = GridBagConstraints.WEST;
		gbc_lblOwnshipSource.insets = new Insets(10, 5, 5, 0);
		gbc_lblOwnshipSource.gridx = 0;
		gbc_lblOwnshipSource.gridy = 0;
		p.add(lblOwnshipSource, gbc_lblOwnshipSource);

		JLabel lblNewLabel = new JLabel(
				"<html>Select if the ownship information should be received from an AIS Target (for development and testing) of from the ship internal NMEA sentences.</html>");
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(Font.ITALIC, 11));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.insets = new Insets(0, 5, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		p.add(lblNewLabel, gbc_lblNewLabel);

		sourcePanel = new JPanel();
		GridBagConstraints gbc_sourcePanel = new GridBagConstraints();
		gbc_sourcePanel.insets = new Insets(0, 5, 5, 0);
		gbc_sourcePanel.fill = GridBagConstraints.BOTH;
		gbc_sourcePanel.gridx = 0;
		gbc_sourcePanel.gridy = 2;
		p.add(sourcePanel, gbc_sourcePanel);
		GridBagLayout gbl_sourcePanel = new GridBagLayout();

		gbl_sourcePanel.columnWeights = new double[] { 0.0, 1.0 };
		gbl_sourcePanel.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		sourcePanel.setLayout(gbl_sourcePanel);

		JLabel lblSourceSettings = new JLabel("Source Settings");
		lblSourceSettings.setFont(lblSourceSettings.getFont().deriveFont(Font.BOLD, 13));
		GridBagConstraints gbc_lblSourceSettings = new GridBagConstraints();
		gbc_lblSourceSettings.anchor = GridBagConstraints.WEST;
		gbc_lblSourceSettings.gridwidth = 2;
		gbc_lblSourceSettings.insets = new Insets(0, 0, 5, 0);
		gbc_lblSourceSettings.gridx = 0;
		gbc_lblSourceSettings.gridy = 0;
		sourcePanel.add(lblSourceSettings, gbc_lblSourceSettings);

		JLabel lblTargetMMSI = new JLabel("Ownship MMSI");
		GridBagConstraints gbc_lblTargetMMSI = new GridBagConstraints();
		gbc_lblTargetMMSI.anchor = GridBagConstraints.WEST;
		gbc_lblTargetMMSI.insets = new Insets(0, 0, 5, 0);
		gbc_lblTargetMMSI.gridx = 0;
		gbc_lblTargetMMSI.gridy = 1;
		sourcePanel.add(lblTargetMMSI, gbc_lblTargetMMSI);
		
		rdbtnAisTargetBy = new JRadioButton("Use AIS messages as ownship reference");
		rdbtnAisTargetBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ownship = EPDModelUtils.retrieveOwnship();
			}
		});
		buttonGroup.add(rdbtnAisTargetBy);
		GridBagConstraints gbc_rdbtnAisTargetBy = new GridBagConstraints();
		gbc_rdbtnAisTargetBy.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAisTargetBy.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAisTargetBy.gridx = 0;
		gbc_rdbtnAisTargetBy.gridy = 2;
		sourcePanel.add(rdbtnAisTargetBy, gbc_rdbtnAisTargetBy);

		aisTargetMMSI = new PropertyTextWidget(OwnshipBasics.OWNSHIP_VIEWER_PROP_CONTEXT, OwnshipBasics.OWNSHIP_VIEWER_PROP_AIS_TARGET, "211876480");

		oldMMSI = aisTargetMMSI.getValue();

		GridBagConstraints gbc_aisTargetMMSI = new GridBagConstraints();
		gbc_aisTargetMMSI.anchor = GridBagConstraints.WEST;
		gbc_aisTargetMMSI.insets = new Insets(0, 0, 5, 5);
		gbc_aisTargetMMSI.gridx = 1;
		gbc_aisTargetMMSI.gridy = 1;
		sourcePanel.add(aisTargetMMSI, gbc_aisTargetMMSI);
		
		rdbtnOwnshipNmeaSentences = new JRadioButton("Use NMEA sentences as ownship reference");
		rdbtnOwnshipNmeaSentences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ownship = EPDModelUtils.retrieveOwnship();
				if(ownship != null){
					ObjectSurfaceInformation osi = ownship.getFirstCharacteristic(ObjectSurfaceInformation.class, true);
					if (osi == null) {
						PredefinedGeometryItem geometryItem = (PredefinedGeometryItem) PredefinedGeometryItem.getPredefinedGeometryItems().get("Simple");
						WKTUtil wktUtil = new WKTUtil();
						Geometry topGeometry = wktUtil.loadWKT(geometryItem.wktTop);
						osi = new ObjectSurfaceInformationImpl(topGeometry);
						ownship.getCharacteristics().add(osi);
					}
		            vePanel = new VesselEditorPart(ownship);
		            vePanel.setSize(new Dimension(100,100));
		            vePanel.setPreferredSize(new Dimension(100, 100));
		        } else {
		            vePanel.setLayout(new BorderLayout(0, 0));
		            JLabel infoLabel = new JLabel("No Ownship found");
		            infoLabel.setFont(infoLabel.getFont().deriveFont(Font.PLAIN, 18));
		            vePanel.add(infoLabel, BorderLayout.CENTER);
		        }
			}
		});
		buttonGroup.add(rdbtnOwnshipNmeaSentences);
		GridBagConstraints gbc_rdbtnOwnshipNmeaSentences = new GridBagConstraints();
		gbc_rdbtnOwnshipNmeaSentences.anchor = GridBagConstraints.WEST;
		gbc_rdbtnOwnshipNmeaSentences.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnOwnshipNmeaSentences.gridx = 0;
		gbc_rdbtnOwnshipNmeaSentences.gridy = 3;
		sourcePanel.add(rdbtnOwnshipNmeaSentences, gbc_rdbtnOwnshipNmeaSentences);

		rdbtnNoProcessing = new JRadioButton("Do not filter ownship sources");
		rdbtnNoProcessing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ownship = EPDModelUtils.retrieveOwnship();
				if(ownship != null){
					ObjectSurfaceInformation osi = ownship.getFirstCharacteristic(ObjectSurfaceInformation.class, true);
					if (osi == null) {
						PredefinedGeometryItem geometryItem = (PredefinedGeometryItem) PredefinedGeometryItem.getPredefinedGeometryItems().get("Simple");
						WKTUtil wktUtil = new WKTUtil();
						Geometry topGeometry = wktUtil.loadWKT(geometryItem.wktTop);
						osi = new ObjectSurfaceInformationImpl(topGeometry);
						ownship.getCharacteristics().add(osi);
					}
		            vePanel = new VesselEditorPart(ownship);
		            vePanel.setSize(new Dimension(100,100));
		            vePanel.setPreferredSize(new Dimension(100, 100));
		        } else {
		            vePanel.setLayout(new BorderLayout(0, 0));
		            JLabel infoLabel = new JLabel("No Ownship found");
		            infoLabel.setFont(infoLabel.getFont().deriveFont(Font.PLAIN, 18));
		            vePanel.add(infoLabel, BorderLayout.CENTER);
		        }
			}
		});
		buttonGroup.add(rdbtnNoProcessing);
		GridBagConstraints gbc_rdbtnNoProcessing = new GridBagConstraints();
		gbc_rdbtnNoProcessing.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNoProcessing.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNoProcessing.gridx = 0;
		gbc_rdbtnNoProcessing.gridy = 4;
		sourcePanel.add(rdbtnNoProcessing, gbc_rdbtnNoProcessing);
		
		CollapsiblePanel vesselEditorPanel = new CollapsiblePanel("VesselEditor");
		
		ownship = EPDModelUtils.retrieveOwnship();
		
		if(ownship != null){
            ObjectSurfaceInformation osi = ownship.getFirstCharacteristic(ObjectSurfaceInformation.class, true);
            if (osi == null) {
                PredefinedGeometryItem geometryItem = (PredefinedGeometryItem) PredefinedGeometryItem.getPredefinedGeometryItems().get("Simple");
                WKTUtil wktUtil = new WKTUtil();
                Geometry topGeometry = wktUtil.loadWKT(geometryItem.wktTop);
                //topGeometry.recursiveSetCRS(editor.getPhysicalObject().getOwnedCoordinateSystem());
                osi = new ObjectSurfaceInformationImpl(topGeometry);
                ownship.getCharacteristics().add(osi);
            }
            vePanel = new VesselEditorPart(ownship);
            vePanel.setSize(new Dimension(100,100));
            vePanel.setPreferredSize(new Dimension(100, 100));
        } else {
            vePanel.setLayout(new BorderLayout(0, 0));
            JLabel infoLabel = new JLabel("No Ownship found");
            infoLabel.setFont(infoLabel.getFont().deriveFont(Font.PLAIN, 18));
            vePanel.add(infoLabel, BorderLayout.CENTER);
        }
        
        vesselEditorPanel.add(vePanel);
        vesselEditorPanel.toggleVisibility(false);
		
		GridBagConstraints gbc_vesselEditorPanel = new GridBagConstraints();
		gbc_vesselEditorPanel.fill = GridBagConstraints.BOTH;
		gbc_vesselEditorPanel.gridx = 0;
		gbc_vesselEditorPanel.gridy = 5;
		p.add(vesselEditorPanel, gbc_vesselEditorPanel);
		
		handleSettings();
		initialSource = buttonGroup.getSelection();
		
		return p;
	}

	
	private void handleSettings() {
		rdbtnAisTargetBy.setSelected(ownshipSourceProp.getValue().equals(OwnshipSource.AISTARGET.name()));
		rdbtnOwnshipNmeaSentences.setSelected(ownshipSourceProp.getValue().equals(OwnshipSource.INTERNAL.name()));
		rdbtnNoProcessing.setSelected(ownshipSourceProp.getValue().equals(OwnshipSource.NO_PROCESSING.name()));
	}

	@Override
	public boolean isDirty() {
		return !(buttonGroup.getSelection() == null || buttonGroup.getSelection().equals(initialSource)) || aisTargetMMSI.isDirty();
	}

	@Override
	public void finish() {
		// Map switches to setting the ownship source property
		if(rdbtnAisTargetBy.isSelected()) {
			ownshipSourceProp.setValue(OwnshipSource.AISTARGET.name());
		} else if (rdbtnOwnshipNmeaSentences.isSelected()) {
			ownshipSourceProp.setValue(OwnshipSource.INTERNAL.name());
		} else {
			ownshipSourceProp.setValue(OwnshipSource.NO_PROCESSING.name());
		}
		// If MMSI is castable (is valid MMSI), set the ownship mmsi to the new value, else revert to the old one.
		try {
			Long.valueOf(aisTargetMMSI.getValue());
			EPDModelUtils.retrieveOwnship(aisTargetMMSI.getValue());
			aisTargetMMSI.finish();
		} catch (ClassCastException e) {
			ULog.error("No valid MMSI specified. Reverting to old one.");
			EPDModelUtils.setOwnship(EPDModelUtils.retrieveById(oldMMSI));
		}
	}

}
