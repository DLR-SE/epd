package de.emir.rcp.editor.model;


import java.awt.Component;

import de.emir.tuml.ucore.runtime.utils.Pointer;

/**
 * Provides an editor for a given type of UClassifier. 
 * 
 * FormEditorParts shall be registered in the FormEditorPartManager, using the FormEditorPartManager.registerEditorPart() method. 
 * FormEditorParts are always registered for one or more specific UClassifier. 
 * @note if an IFormEditorPart shall be used for all UClassifier that inherit from an UCLassifier it has to be registered for all of them manually
 * @author sschweigert
 * @ingroup ExtensionPoint
 */
public interface IFormEditorPart {
	
	/**
	 * Creates a form editor for the pointed object
	 * @param pointer pointer to the object, that should be displayed
	 * @return created (and added) composite
	 */
	Component createComposite(Pointer pointer);
	
	/** 
	 * tells the caller if the editor part shall be added to a JScrollPane or not. 
	 * 
	 * it may be usefull to overwrite this method if the editor itself creates a scrollpane
	 * @return
	 */
	default boolean createScrollpane() { return true; }
	
	/**
	 * checks if the component under the pointer can realy be displayed by this form provider. 
	 * @note in most cases the class should be fine, thus we return true by default. However, there are
	 * 		some types like the Asset or PhysicalObject which are defined through its characteristics 
	 * 		to support only a subset of those objects, this method may be overwritten. 
	 * @param pointer
	 * @return
	 */
	default boolean supports(Pointer pointer) { return true; }
}
