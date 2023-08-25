package de.emir.tuml.ucore.runtime.extension;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;

import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class LocalServiceManager {
	
	private static final Logger log = ULog.getLogger(LocalServiceManager.class.getName());
	
	
	private final String	mName;
	
    private Map<String, IService> 								mExtensionPoints = new HashMap<String, IService>();
    private Map<Class<? extends IService>, IService> 			mExtensionPointsByClass = new HashMap<Class<? extends IService>, IService>();
    

    public LocalServiceManager(String name) {
    	mName = name;
    }
    public String getName() { return mName; }
    
    
    public void register(IService ep) {
        register(ep.getClass().getName(), ep);
    }

    public void register(String id, IService ep) {
        if (mExtensionPoints.containsKey(id) == true) {
            log.error("Extension Point with id [" + id + "] already exists.");
            return;
        }
        mExtensionPointsByClass.put(ep.getClass(), ep);
        mExtensionPoints.put(id, ep);
        if (log.isDebugEnabled())
        	log.debug("Extension Point with ID [" + id + "] registered.");
    }

    @SuppressWarnings("unchecked")
    public <T extends IService> T getByID(String id) {
        return (T) mExtensionPoints.get(id);
    }

    @SuppressWarnings("unchecked")
    public <T extends IService> T get(Class<T> extClass) {
        return (T) mExtensionPointsByClass.get(extClass);
    }
    
    
    /**
     * Removes all registered services and dispose them if they do implement the IDisposable Interface
     */
	public void dispose() {
		for (Entry<String, IService> e : mExtensionPoints.entrySet()) {
			if (e.getValue() instanceof IDisposable)
				if (((IDisposable)e.getValue()).isDisposed() == false)
					((IDisposable)e.getValue()).dispose();
		}
		mExtensionPoints.clear();
		for (Entry<Class<? extends IService>, IService> e : mExtensionPointsByClass.entrySet())
			if (e.getValue() instanceof IDisposable)
				if (((IDisposable)e.getValue()).isDisposed() == false)
					((IDisposable)e.getValue()).dispose();
		mExtensionPointsByClass.clear();
	}

}
