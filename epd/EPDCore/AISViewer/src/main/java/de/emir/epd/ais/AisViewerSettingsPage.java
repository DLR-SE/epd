/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.emir.epd.ais;

import de.emir.epd.ais.forms.AisSettings;
import java.awt.Component;
import de.emir.rcp.settings.AbstractSettingsPage;

/**
 *
 * @author stefanb
 */
public class AisViewerSettingsPage extends AbstractSettingsPage {
    AisSettings panel;
    
    @Override
    public Component fillPage() { 
    	panel = new AisSettings();
    	return panel;
    }

    @Override
    public boolean isDirty() {
        return panel.isDirty();
    }

    @Override
    public void finish() {
        panel.finish();
    }
    
}
