package de.emir.epd.ais;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.UIManager;

import de.emir.epd.ais.ids.AisBasics;
import de.emir.epd.ais.manager.AisTargetManager;
import de.emir.epd.ais.model.IAisReadAdapter;
import de.emir.epd.mapview.views.map.AbstractMapLayerSettingsPanel;
import de.emir.epd.mapview.views.map.MapView;
import de.emir.epd.model.EPDModelUtils;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.universal.physics.Environment;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.ui.utils.databinding.PropertyJCheckBox;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dimension;
import javax.swing.JTextField;

/**
 * Settings panel component for the AIS layer.
 */
public class AisLayerSettingsPanel extends AbstractMapLayerSettingsPanel {
	private JTextField textField;
	private Environment targetSet;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public Container createContent() {

		// Subscribes to changes of the ais targets in the EPDModel.
		EPDModelUtils.subscribeModelChange("aisTargetSet", event -> {
			if(event.getNewValue() instanceof Environment) {
				targetSet = (Environment) event.getNewValue();
			}
		});
		
		JPanel parent = new JPanel();
		parent.setPreferredSize(new Dimension(172, 200));
		
		JCheckBox chckbxShowNames = new PropertyJCheckBox("Show Names", AisBasics.AIS_VIEWER_PROP_CONTEXT, AisBasics.AIS_VIEWER_PROP_SHOW_NAMES, true);
		chckbxShowNames.setOpaque(false);
		JCheckBox chckbxFollowSelectedVessel = new PropertyJCheckBox("Show Timed Out Targets", AisBasics.AIS_VIEWER_PROP_CONTEXT, AisBasics.AIS_VIEWER_PROP_SHOW_TIMED_OUT, true);
		chckbxFollowSelectedVessel.setOpaque(false);
		JCheckBox chckbxShowTracks = new PropertyJCheckBox("Show Target Tracks", AisBasics.AIS_VIEWER_PROP_CONTEXT, AisBasics.AIS_VIEWER_PROP_SHOW_TRACKS, true);
		chckbxShowTracks.setOpaque(false);
		JCheckBox chckbxShowIntendedRoutes = new PropertyJCheckBox("Show Intended Routes", AisBasics.AIS_VIEWER_PROP_CONTEXT, AisBasics.AIS_VIEWER_PROP_SHOW_INTENDED_ROUTES, true);
		chckbxShowIntendedRoutes.setOpaque(false);
        JButton clearButton = new JButton("Clear track display");
        clearButton.setOpaque(false);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnFind = new JButton("Find");
		btnFind.setOpaque(false);
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String findStr = textField.getText();
				if (findStr == null || findStr.isEmpty()) return;
				try {
					long id = Long.parseLong(findStr);
					if (id > 0L) {
						Vessel v = EPDModelUtils.getVesselById(targetSet, findStr);
						if (v == null || v.getPose() == null || v.getPose().getCoordinate() == null) return;
						MapView mapView = PlatformUtil.getViewManager().getView(MapView.class);
						mapView.centerOnCoordinate(v.getPose().getCoordinate());
					}
				}
				catch (NumberFormatException e) {
					return;
				}
			}
		});
		GroupLayout gl_parent = new GroupLayout(parent);
		gl_parent.setHorizontalGroup(
			gl_parent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_parent.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_parent.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_parent.createSequentialGroup()
							.addGroup(gl_parent.createParallelGroup(Alignment.LEADING)
								.addComponent(chckbxShowNames, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(chckbxFollowSelectedVessel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(chckbxShowTracks, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(chckbxShowIntendedRoutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(19, Short.MAX_VALUE))
						.addGroup(gl_parent.createSequentialGroup()
							.addComponent(clearButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(317))
						.addGroup(gl_parent.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFind)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
		);
		gl_parent.setVerticalGroup(
			gl_parent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_parent.createSequentialGroup()
					.addContainerGap()
					.addComponent(chckbxShowNames, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxFollowSelectedVessel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxShowTracks, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxShowIntendedRoutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(clearButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_parent.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFind))
					.addGap(28))
		);
		parent.setLayout(gl_parent);
	    clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PropertyContext context = PropertyStore.getContext(AisBasics.AIS_VIEWER_PROP_CONTEXT);
                IProperty clearTimeProp = context.getProperty(AisBasics.AIS_VIEWER_PROP_TRACK_CLEARTIME, 0L);
                clearTimeProp.setValue(System.currentTimeMillis());
            }
        });
		return parent;
	}
}
