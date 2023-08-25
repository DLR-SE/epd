package de.emir.rcp.model.adapter;

import java.util.HashSet;
import java.util.List;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;


/**
 * 
 * @author sschweigert
 *@deprecated use the UModelAdapter from ucore
 */
@Deprecated
public class UModelAdapter extends de.emir.tuml.ucore.runtime.adapter.UModelAdapter {

	public static class AdapterOptions extends de.emir.tuml.ucore.runtime.adapter.UModelAdapter.AdapterOptions{

		public AdapterOptions(Class<?> clazz) {
			super(clazz);
		}
		public AdapterOptions(UClassifier classifier) {
			super(classifier);
		}
	}
	
	public interface IObjectCallback extends de.emir.tuml.ucore.runtime.adapter.UModelAdapter.IObjectCallback {}
}
