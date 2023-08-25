package de.emaritime.epd.product;

import de.emir.epd.model.EPDModelProvider;
import de.emir.rcp.model.AbstractModelProvider;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import com.formdev.flatlaf.util.SystemInfo;

import de.emir.rcp.product.AbstractUIProduct;

public class EPDCommunity extends AbstractUIProduct {

	public EPDCommunity(String[] args) {
		super(args);
	}
	
	public static void main(String[] args) {
		new EPDCommunity(args);
	}
    
    @Override
	public AbstractModelProvider getModelProvider() {
		return new EPDModelProvider();
	}
	
	@Override
	public boolean hasEditorArea() {
		return true;
	}

	@Override
	public boolean hasStatusBar() {
		return true;
	}

	@Override
	public void initProduct() {
		
		
	}
	
	@Override
	public String getTitle() {
		return "EPD";
	}

	@Override
	public LookAndFeel getLAF() {
		// Remove this to keep the default look and feel.
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel".equals(info.getClassName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				} else if ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel".equals(info.getClassName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
		} catch (Exception e) {
			// Could not set metal LaF, try others.
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if (SystemInfo.isLinux) {
		    // enable custom window decorations
		    JFrame.setDefaultLookAndFeelDecorated(true);
		    JDialog.setDefaultLookAndFeelDecorated(true);
		}
		
		return UIManager.getLookAndFeel();
	}
}
