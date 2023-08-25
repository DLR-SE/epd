package de.emir.tuml.ucore.runtime.extension;

/**
 * @addgroup ExtensionPoint
 * 
 *           An ExtensionPoint is used by a plugin to provide other plugins the option to extend the Logic according to
 *           a given Interface (defined by the concrete ExtensionPoint). An ExtensionPoint shall be registered at the
 *           ExtensionPointManager and may also be accessed through this Manager.
 * 
 * @code{java} //register a new ExtensionPoint ExtensionPointManager.registerExtensionPoint("SOME_UNIQUE_ID", new
 *             SOME_CLASS<implements IExtensionPoint>()) //Access an existing ExtensionPoint by Class
 *             SOME_CLASS<implements IExtensionPoint> ep = ExtensionPointManager.getExtensionPoint(SOME_CLASS<implements
 *             IExtensionPoint>.class);
 * @endcode
 * @author sschweigert
 *
 */
public interface IExtensionPoint {

}
