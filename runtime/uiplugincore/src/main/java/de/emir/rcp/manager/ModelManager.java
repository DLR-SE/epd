package de.emir.rcp.manager;

import org.apache.logging.log4j.Logger;

import de.emir.rcp.model.AbstractModelProvider;
import de.emir.tuml.ucore.runtime.extension.IService;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class ModelManager implements IService {

    private AbstractModelProvider mp;

    private static final Logger log = ULog.getLogger(ModelManager.class);

    public void setModelProvider(AbstractModelProvider p) {

        if (mp != null) {
            log.error("ModelProvider already set. This method should only be called by a product.");
            return;
        }

        mp = p;
    }

    public AbstractModelProvider getModelProvider() {
        return mp;
    }
}
