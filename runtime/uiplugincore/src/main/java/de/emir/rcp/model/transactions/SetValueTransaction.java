package de.emir.rcp.model.transactions;

import org.apache.logging.log4j.Logger;

import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class SetValueTransaction extends AbstractModelTransaction {

    private UObject parent;
    private UStructuralFeature feature;
    private Object value;

    private boolean executed = false;
    private Object oldValue;

    private static final Logger log = ULog.getLogger(SetValueTransaction.class);

    public SetValueTransaction(UObject parent, UStructuralFeature feature, Object value) {
        this.parent = parent;
        this.feature = feature;
        this.value = value;
    }

    public SetValueTransaction(Pointer ptr, Object value) {
        this(ptr.getPointedContainer(), ptr.getPointedFeature(), value);
    }

    private boolean isValidRequest() {

        return parent != null && feature != null && feature.isMany() == false;

    }

    @Override
    public void run() {

        if (isValidRequest() == false) {
            log.error("Invalid transaction [parent:" + parent + " feature:" + feature + " value:" + value + "]");
            return;
        }

        oldValue = parent.uGet(feature);
        parent.uSet(feature, value);

        executed = true;

    }

    @Override
    public void undo() {
        if (isValidRequest() == false) {
            log.error("Invalid transaction [parent:" + parent + " feature:" + feature + " value:" + value + "]");
            return;
        }

        parent.uSet(feature, oldValue);

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
