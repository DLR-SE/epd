package de.emir.epd.targetview;

import de.emir.epd.mapview.views.map.AbstractMapLayerSettingsPanel;
import de.emir.epd.targetview.ids.TargetBasics;
import de.emir.rcp.ui.utils.properties.IPropertyWidget;
import de.emir.rcp.ui.utils.properties.PropertyCheckboxWidget;

import javax.swing.*;
import java.awt.*;

/**
 * Settings panel which should be displayed next to the TargetLayer map layer.
 */
public class TargetViewMapSettingsPanel extends AbstractMapLayerSettingsPanel {

    /**
     * Creates the content of the map settings panel.
     */
    @Override
    public Container createContent() {
        JPanel parent = new JPanel();
        parent.setPreferredSize(new Dimension(172, 200));
        PropertyCheckboxWidget checkBoxShowNames = new PropertyCheckboxWidget(
                "Show Names", // label
                TargetBasics.TARGET_VIEWER_PROP_CONTEXT,
                TargetBasics.TARGET_VIEWER_PROP_SHOW_NAMES,
                true // default
        );
        checkBoxShowNames.setOpaque(false);
        checkBoxShowNames.addPropertyChangeListener(
                evt -> {
                    if (IPropertyWidget.PROPERTY_VALUE_CHANGE_NAME.equals(evt.getPropertyName())){
                        checkBoxShowNames.finish();
                    }
                }
        );
        PropertyCheckboxWidget checkBoxShowTargets = new PropertyCheckboxWidget(
                "Show Targets", // label
                TargetBasics.TARGET_VIEWER_PROP_CONTEXT,
                TargetBasics.TARGET_VIEWER_PROP_SHOW_TARGETS,
                true // default
        );
        checkBoxShowTargets.setOpaque(false);
        checkBoxShowTargets.addPropertyChangeListener(
                evt -> {
                    if (IPropertyWidget.PROPERTY_VALUE_CHANGE_NAME.equals(evt.getPropertyName())){
                        checkBoxShowTargets.finish();
                    }
                }
        );
        PropertyCheckboxWidget checkBoxShowTrackedTargets = new PropertyCheckboxWidget(
                "Show Tracked Targets", // label
                TargetBasics.TARGET_VIEWER_PROP_CONTEXT,
                TargetBasics.TARGET_VIEWER_PROP_SHOW_TRACKED_TARGETS,
                true // default
        );
        checkBoxShowTrackedTargets.setOpaque(false);
        checkBoxShowTrackedTargets.addPropertyChangeListener(
                evt -> {
                    if (IPropertyWidget.PROPERTY_VALUE_CHANGE_NAME.equals(evt.getPropertyName())){
                        checkBoxShowTrackedTargets.finish();
                    }
                }
        );
        PropertyCheckboxWidget checkBoxShowTargetLoss = new PropertyCheckboxWidget(
                "Display Target loss", // label
                TargetBasics.TARGET_VIEWER_PROP_CONTEXT,
                TargetBasics.TARGET_VIEWER_PROP_DISPLAY_TARGET_LOSS,
                false // default
        );
        checkBoxShowTargetLoss.setOpaque(false);
        checkBoxShowTargetLoss.addPropertyChangeListener(
                evt -> {
                    if (IPropertyWidget.PROPERTY_VALUE_CHANGE_NAME.equals(evt.getPropertyName())){
                        checkBoxShowTargetLoss.finish();
                    }
                }
        );

        GroupLayout gl_parent = new GroupLayout(parent);
        gl_parent.setHorizontalGroup(
                gl_parent.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_parent.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_parent.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(gl_parent.createSequentialGroup()
                                                .addGroup(gl_parent.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(checkBoxShowNames, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(checkBoxShowTargets, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(checkBoxShowTrackedTargets, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(checkBoxShowTargetLoss, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(19, Short.MAX_VALUE))))
        );
        gl_parent.setVerticalGroup(
                gl_parent.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_parent.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(checkBoxShowNames, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkBoxShowTargets, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkBoxShowTrackedTargets, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkBoxShowTargetLoss, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGap(28))
        );
        parent.setLayout(gl_parent);
        return parent;
    }
}
