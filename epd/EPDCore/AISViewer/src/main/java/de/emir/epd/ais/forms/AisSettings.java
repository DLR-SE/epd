/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.emir.epd.ais.forms;

import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import de.emir.epd.ais.ids.AisBasics;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author stefanb
 */
public class AisSettings extends javax.swing.JPanel {
    private IProperty trackTimeoutProp;
    private IProperty lookaheadProp;
    private int initialLookahead = 6;
    private int initialTimeout = 10;
	private IProperty<Integer> aisTimeoutProp;
	private int initialAisTimeout = 30;

    /**
     * Creates new form AisSettingsForm
     */
    public AisSettings() {
        PropertyContext context = PropertyStore.getContext(AisBasics.AIS_VIEWER_PROP_CONTEXT);
        trackTimeoutProp = context.getProperty(AisBasics.AIS_VIEWER_PROP_TRACK_TIMEOUT, 10);
        lookaheadProp = context.getProperty(AisBasics.AIS_VIEWER_PROP_LOOKAHEAD, 6);
        aisTimeoutProp = context.getProperty(AisBasics.AIS_VIEWER_PROP_TARGET_LOSTTIME, 30);
        initialTimeout = (int) trackTimeoutProp.getValue();
        initialLookahead = (int) lookaheadProp.getValue();
        initialAisTimeout = (int) aisTimeoutProp.getValue();
        initComponents();
        SpinnerModel model = new SpinnerNumberModel((int) initialTimeout, 0, 2880, 1);
        timeoutSpinner.setModel(model);
        SpinnerModel model2 = new SpinnerNumberModel((int) initialLookahead, 0, 60, 1);
        lookaheadSpinner.setModel(model2);
        SpinnerModel model3 = new SpinnerNumberModel((int) initialAisTimeout, 0, 2880, 1);
        aisTimeoutSpinner.setModel(model3);
    }
    
    public boolean isDirty() {
        int val = (int) timeoutSpinner.getModel().getValue();
        int val2 = (int) lookaheadSpinner.getModel().getValue();
        int val3 = (int) aisTimeoutSpinner.getModel().getValue();
		return !(initialTimeout == val && initialLookahead == val2 && initialAisTimeout  == val3);
    }

    public void finish() {
        int val = (int) timeoutSpinner.getModel().getValue();
        trackTimeoutProp.setValue(val);
        int val2 = (int) lookaheadSpinner.getModel().getValue();
        lookaheadProp.setValue(val2);
        int val3 = (int) aisTimeoutSpinner.getModel().getValue();
        aisTimeoutProp.setValue(val3);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tracksHeadingLabel = new javax.swing.JLabel();
        timeoutLabel = new javax.swing.JLabel();
        timeoutSpinner = new javax.swing.JSpinner();
        timeoutLabel2 = new javax.swing.JLabel();
        lookaheadLabel = new javax.swing.JLabel();
        lookaheadSpinner = new javax.swing.JSpinner();
        lookaheadLabel2 = new javax.swing.JLabel();
        lookaheadHeadingLabel = new javax.swing.JLabel();

        tracksHeadingLabel.setText("Tracks");
        tracksHeadingLabel.setName(""); // NOI18N

        timeoutLabel.setText("Display tracks not older than");

        timeoutLabel2.setText("minutes");

        lookaheadLabel.setText("AIS lookahead for");

        lookaheadLabel2.setText("minutes");

        lookaheadHeadingLabel.setText("Lookahead");
        
        lblTimeout = new JLabel();
        lblTimeout.setText("Timeout");
        
        lblAisTargetTimeout = new JLabel();
        lblAisTargetTimeout.setText("AIS target timeout after");
        
        aisTimeoutSpinner = new JSpinner();
        
        lblSeconds = new JLabel();
        lblSeconds.setText("seconds");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(tracksHeadingLabel, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lookaheadHeadingLabel)
        				.addComponent(lblTimeout, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lookaheadLabel)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lookaheadSpinner, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lookaheadLabel2))
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(timeoutLabel)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(timeoutSpinner, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(timeoutLabel2))
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblAisTargetTimeout)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(aisTimeoutSpinner, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblSeconds, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(178, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(tracksHeadingLabel)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(timeoutLabel)
        				.addComponent(timeoutSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(timeoutLabel2))
        			.addGap(16)
        			.addComponent(lookaheadHeadingLabel)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lookaheadSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lookaheadLabel)
        				.addComponent(lookaheadLabel2))
        			.addGap(16)
        			.addComponent(lblTimeout)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(lblAisTargetTimeout)
        					.addComponent(aisTimeoutSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(3)
        					.addComponent(lblSeconds)))
        			.addContainerGap(148, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lookaheadHeadingLabel;
    private javax.swing.JLabel lookaheadLabel;
    private javax.swing.JLabel lookaheadLabel2;
    private javax.swing.JSpinner lookaheadSpinner;
    private javax.swing.JLabel timeoutLabel;
    private javax.swing.JLabel timeoutLabel2;
    private javax.swing.JSpinner timeoutSpinner;
    private javax.swing.JLabel tracksHeadingLabel;
    private JLabel lblTimeout;
    private JLabel lblAisTargetTimeout;
    private JSpinner aisTimeoutSpinner;
    private JLabel lblSeconds;
}
