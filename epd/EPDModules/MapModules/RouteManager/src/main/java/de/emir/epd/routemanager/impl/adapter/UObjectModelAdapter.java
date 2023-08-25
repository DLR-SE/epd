package de.emir.epd.routemanager.impl.adapter;

import java.util.HashMap;

import de.emir.epd.routemanager.IRouteManager.IRouteAdapter;
import de.emir.epd.routemanager.IRouteManager.IRouteManagerAccess;
import de.emir.epd.routemanager.impl.model.VesselBasedRouteModel;
import de.emir.epd.routemanager.impl.model.VoyageBasedRouteModel;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VoyageCharacteristic;
import de.emir.rcp.model.adapter.UModelAdapter;
import de.emir.rcp.model.adapter.UModelAdapter.AdapterOptions;
import de.emir.rcp.model.adapter.UModelAdapter.IObjectCallback;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class UObjectModelAdapter implements IRouteAdapter{

	@Override
	public void onModelChange(Object rootModel, IRouteManagerAccess mgr) {
		if (rootModel == null || rootModel instanceof UObject == false)
			return ;
		UModelAdapter adapter = new UModelAdapter();
		AdapterOptions options = new AdapterOptions(VoyageCharacteristic.class);
		options.maximumDepth = 17;
		adapter.visitModel((UObject) rootModel, new IObjectCallback() {
			@Override
			public boolean accept(Object value, UObject parent, UStructuralFeature feature, int listIndex, int depth) {
				return _accept(value, parent, feature, listIndex, depth, mgr);
			}			
		}, options);
	}

	
	private boolean _accept(Object value, UObject parent, UStructuralFeature feature, int listIndex, int depth, IRouteManagerAccess mgr) {
		if(!(value instanceof VoyageCharacteristic))
			return true;
		VoyageCharacteristic voyage = (VoyageCharacteristic) value;
		if (parent instanceof Vessel) {
			mgr.addModel(new VesselBasedRouteModel((Vessel)parent));
			if (parent.getUContainer() != null)
				observeParent(parent.getUContainer(), parent.getUContainingFeature(), mgr); //checks if the model is already observed
		}else {
			mgr.addModel(new VoyageBasedRouteModel(voyage));
			observeParent(parent, feature, mgr);
		}
		return true;
	}
	protected void _remove(Object oldValue, IRouteManagerAccess mgr) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	private HashMap<UObject, IValueChangeListener>		mParentListener = new HashMap<>();
	
	protected void observeParent(UObject uContainer, UStructuralFeature uStructuralFeature, IRouteManagerAccess mgr) {
		if (mParentListener.containsKey(uContainer))
			return ;//already observed
		IValueChangeListener listener = new IValueChangeListener() {
			@Override
			public void onValueChange(Notification notification) {
				ULog.warn("UNTESTED");
				if (notification.getNewValue() != null) {
					_accept(notification.getNewValue(), notification.getInstance(), notification.getFeature(), -1, 1, mgr);
				}
				if (notification.getOldValue() != null)
					_remove(notification.getOldValue(), mgr);
			}
		};
		mParentListener.put(uContainer, listener);
		uContainer.registerListener(listener);
	}


	

	

}
