package de.emir.rcp.editor.model;

import java.net.URL;

import de.emir.tuml.ucore.runtime.utils.Pointer;

public interface ILabelProvider {
	
	/** returns the label for the given Object under the pointer
	 * @deprecated use getLabel(final Pointer pointer) instead
	 * */
	@Deprecated
	public String getLabel(final Object value);
	
	/** returns the label for the given Object under the pointer
	 **/
	public String getLabel(final Pointer pointer);
		
	/** returns the tooltip for the given object under the pointer 
	 * @note this method is only called, if the method <code>hasTooltip(object)</code> did return true
	 */
	public String getTooltip(final Object pointer);
	
	/**
	 * Optional: returns an url to an icon for this given object under the pointer
	 * @param pointer pointer to create the url for
	 * @return url to an image or null
	 */
	public URL getIcon(final Object pointer);
}
