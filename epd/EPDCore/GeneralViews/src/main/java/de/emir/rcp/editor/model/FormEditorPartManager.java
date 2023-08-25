package de.emir.rcp.editor.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.emir.rcp.editor.model.form.part.PropertySheetFormProvider;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class FormEditorPartManager implements IExtensionPoint {

	public static final String FORM_PROVIDER_EXTENSIONPOINT_ID = "de.emir.ui.emod.formprovider";

	private static FormEditorPartManager sTheInstance = null;

	private HashMap<UClassifier, List<IFormEditorPart>> mProvider = new HashMap<>();
	private boolean mInitialized = false;

	public static FormEditorPartManager getInstance() {
		return sTheInstance;
	}

	public FormEditorPartManager() throws IllegalAccessException {
		if (sTheInstance != null)
			throw new IllegalAccessException("There is allready an instance of the FormEditorPartManager available");
		sTheInstance = this;
	}

	/**
	 * returns true if a provider for the given classifier has been registered
	 * 
	 * @param cl
	 * @return
	 */
	public boolean hasEditorPart(UClassifier cl) {
		return mProvider.containsKey(cl);
	}

	public void registerEditorPart(UClassifier cl, IFormEditorPart part) {
		if (mProvider.containsKey(cl) == false)
			mProvider.put(cl, new ArrayList<>());
		mProvider.get(cl).add(part);
	}

	/**
	 * Removes one specific editor part provider for a given classifier
	 * 
	 * @param cl   classifier that should no longer be displayed, using the editor
	 *             part
	 * @param part editor part provider that should be removed
	 * @note if the part provider has been registered for other classifier, they
	 *       will still be returned by provideEditorPart()
	 */
	public void removeEditorPart(UClassifier cl, IFormEditorPart part) {
		if (mProvider.containsKey(cl) == false)
			return;
		mProvider.get(cl).remove(part);
		if (mProvider.get(cl) == null)
			mProvider.remove(cl);
	}

	/**
	 * removes the given part for ALL registered classifier
	 * 
	 * @param part
	 */
	public void removeEditorPart(IFormEditorPart part) {
		for (UClassifier cl : mProvider.keySet())
			removeEditorPart(cl, part);
	}

	/**
	 * checks the registered FormEditorPartProvider and returns one if one has been
	 * registered for this class
	 * 
	 * @param cl            classifier that shall be displayed
	 * @param ptr           pointer to be represented by this editor part (maybe
	 *                      null)
	 * @param returnDefault if set to true, the default form editor is returned,
	 *                      that is a PropertySheet panel, displaying the same
	 *                      informations as the
	 *                      de.emir.rcp.views.properties.PropertyView
	 * @return a form provider that can generate a form for this type of object or
	 *         null, if no provider has been registered
	 * @note if more than one editor part has been registered for this classifier,
	 *       the first one is returned
	 */
	public IFormEditorPart provideEditorPart(UClassifier cl, Pointer ptr, boolean returnDefault) {
		if (mProvider.containsKey(cl)) {
			if (ptr == null)
				return mProvider.get(cl).get(0);
			for (IFormEditorPart fep : mProvider.get(cl))
				if (fep.supports(ptr))
					return fep;
		}
		if (returnDefault) {
			return getDefaultProvider();
		}
		return null;
	}

	/**
	 * checks the registered FormEditorPartProvider and returns one if one has been
	 * registered for this class
	 * 
	 * @param cl            classifier that shall be displayed
	 * @param returnDefault if set to true, the default form editor is returned,
	 *                      that is a PropertySheet panel, displaying the same
	 *                      informations as the
	 *                      de.emir.rcp.views.properties.PropertyView
	 * @return a form provider that can generate a form for this type of object or
	 *         null, if no provider has been registered
	 * @note if more than one editor part has been registered for this classifier,
	 *       the first one is returned
	 */
	public IFormEditorPart provideEditorPart(UClassifier cl, boolean returnDefault) {
		return provideEditorPart(cl, null, returnDefault);
	}

	private IFormEditorPart sDefaultProvider = null;

	private IFormEditorPart getDefaultProvider() {
		if (sDefaultProvider == null)
			sDefaultProvider = new PropertySheetFormProvider();
		return sDefaultProvider;
	}

}
