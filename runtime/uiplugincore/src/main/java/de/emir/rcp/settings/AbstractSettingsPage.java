package de.emir.rcp.settings;

import java.awt.Component;

public abstract class AbstractSettingsPage {
	public abstract Component fillPage();
	
	public abstract boolean isDirty();
	
	/**
	 * Store the changes
	 */
	public abstract void finish();

	/** Called if the page is closed, e.g. another page is opened 
	 * this method is always called, even if the page is not dirty. if the page was dirty, the method will be called after the finish() method*/
	public void close() {
	}
	
}
