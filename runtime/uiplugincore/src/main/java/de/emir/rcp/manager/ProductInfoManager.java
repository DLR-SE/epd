package de.emir.rcp.manager;

import javax.swing.ImageIcon;

import org.slf4j.Logger;

import de.emir.rcp.views.AboutDialog;
import de.emir.tuml.ucore.runtime.extension.IService;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * Basic product info is stored here so it can be accessed from all plugins.
 * Use this with caution as nothing prevents plugins from overwriting information.
 */
public class ProductInfoManager implements IService {
	private static final Logger LOG = ULog.getLogger(ProductInfoManager.class);
	private String productName;
	private String productVersion;
	private ImageIcon productIcon;
	private AboutDialog aboutDialog;
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductVersion() {
		return productVersion;
	}
	
	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}
	
	public ImageIcon getProductIcon() {
		return productIcon;
	}
	
	public void setProductIcon(ImageIcon productIcon) {
		this.productIcon = productIcon;
	}
	
	public AboutDialog getAboutDialog() {
		if (aboutDialog == null) {
			aboutDialog = new AboutDialog();
		}
		return aboutDialog;
	}
	
	public void setAboutDialog(AboutDialog aboutDialog) {
		this.aboutDialog = aboutDialog;
	}

}
