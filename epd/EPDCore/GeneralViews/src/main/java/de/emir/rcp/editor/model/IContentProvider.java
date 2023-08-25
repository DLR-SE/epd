package de.emir.rcp.editor.model;

import java.util.List;

import de.emir.tuml.ucore.runtime.utils.Pointer;

public interface IContentProvider {

	/** returns true, if the given object has children, that should appear as own icon*/
	public boolean hasChildren(final Pointer object);
	
	/** returns the children of the given object
	 * @note this method is only called, if <code>hasChildren(object)</code> returned true
	 * @param object
	 * @return the children that should appear as own element
	 */
	public List<Pointer> getChildren(final Pointer object);
}
