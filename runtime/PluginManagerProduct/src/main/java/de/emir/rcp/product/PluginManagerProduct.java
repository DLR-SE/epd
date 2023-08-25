package de.emir.rcp.product;

import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicLookAndFeel;

import com.formdev.flatlaf.util.SystemInfo;

import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.pluginmanager.model.PmModelProvider;
import de.emir.tuml.ucore.runtime.resources.IconManager;
import javax.swing.UIManager;

public class PluginManagerProduct extends AbstractUIProduct {

    private AbstractModelProvider mp;

    public PluginManagerProduct(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        new PluginManagerProduct(args);
    }

    @Override
    public boolean hasEditorArea() {
        return false;
    }

    @Override
    public boolean hasStatusBar() {
        return true;
    }

    @Override
    public AbstractModelProvider getModelProvider() {

        if (mp == null) {
            mp = new PmModelProvider();
        }
        return mp;
    }

    @Override
    public void initProduct() {

    }

    @Override
    public String getSplashImagePath() {
        return "PluginManagerSplash.png";
    }

    public String getTitle() {
        return "eMIR Plugin Manager";
    }

    @Override
    public Image getApplicationImage() {
        return IconManager.getIcon(this, "icons/sbe_extensions.png", IconManager.preferedSmallIconSize())
                .getImage();
    }
    
    
	@Override
	public LookAndFeel getLAF() {
		// Remove this to keep the default look and feel.
//		BasicLookAndFeel flatlaf = new com.formdev.flatlaf.FlatIntelliJLaf();
//        BasicLookAndFeel flatlaf = new com.formdev.flatlaf.intellijthemes.FlatHiberbeeDarkIJTheme(); // Theme for EPD
//        BasicLookAndFeel flatlaf = new com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme(); // Theme for HAGGIS
		if (SystemInfo.isLinux) {
		    // enable custom window decorations
		    JFrame.setDefaultLookAndFeelDecorated(true);
		    JDialog.setDefaultLookAndFeelDecorated(true);
		}
        return new com.formdev.flatlaf.FlatLightLaf();
	}


}
