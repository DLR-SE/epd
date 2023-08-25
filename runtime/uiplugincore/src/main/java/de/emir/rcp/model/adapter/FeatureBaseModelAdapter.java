package de.emir.rcp.model.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.TypeUtils;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

/**
 * 
 * @author sschweigert
 *@deprecated use the FeatureBasedModelAdapter from ucore
 */
@Deprecated
public class FeatureBaseModelAdapter extends de.emir.tuml.ucore.runtime.adapter.FeatureBaseModelAdapter{

	public interface IAcceptanceCallback extends de.emir.tuml.ucore.runtime.adapter.FeatureBaseModelAdapter.IAcceptanceCallback{
	}
	
	public interface IValueCallback extends de.emir.tuml.ucore.runtime.adapter.FeatureBaseModelAdapter.IValueCallback{
	}
	
	public FeatureBaseModelAdapter(String id, IValueCallback callback) {
		super(id, callback);
		
	}
	
	
}
