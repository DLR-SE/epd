package de.emir.rcp.model.transactions;

import java.util.List;

import org.slf4j.Logger;

import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.logging.ULog;

public class RemoveValueTransaction extends AbstractModelTransaction {

    private UObject parent;
    private UStructuralFeature feature;
    private Object value;

    private boolean executed = false;
    private int oldIndex;

    private static final Logger log = ULog.getLogger(RemoveValueTransaction.class);

    public RemoveValueTransaction(UObject parent, UStructuralFeature feature, Object value) {
        this.parent = parent;
        this.feature = feature;
        this.value = value;

    }

    private boolean isValidRequest() {

        return parent != null && feature != null && value != null && feature.isMany() == true;

    }

    @SuppressWarnings({ "rawtypes" })
    @Override
    public void run() {

        if (isValidRequest() == false) {
            log.error("Invalid transaction [parent:" + parent + " feature:" + feature + " value:" + value + "]");
            return;
        }

        List list = (List) parent.uGet(feature);

        oldIndex = list.indexOf(value);
        list.remove(value);

        executed = true;

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void undo() {
        if (isValidRequest() == false) {
            log.error("Invalid transaction [parent:" + parent + " feature:" + feature + " value:" + value + "]");
            return;
        }

        List list = (List) parent.uGet(feature);
        list.add(oldIndex, value);

        executed = false;

    }

    @Override
    public void redo() {
        run();
    }

    @Override
    public boolean canUndo() {
        return executed;
    }

    @Override
    public boolean canRedo() {
        return executed == false;
    }

}
