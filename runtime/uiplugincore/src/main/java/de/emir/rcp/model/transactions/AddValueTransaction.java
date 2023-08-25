package de.emir.rcp.model.transactions;

import java.util.List;

import org.slf4j.Logger;

import de.emir.rcp.model.AbstractModelTransaction;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.FeaturePointer;
import de.emir.tuml.ucore.runtime.utils.Pointer;

public class AddValueTransaction extends AbstractModelTransaction {
    
    private Pointer		target;
    private Object 		value;    
    private boolean 	executed = false;

    private static final Logger log = ULog.getLogger(AddValueTransaction.class);

    public AddValueTransaction(UObject parent, UStructuralFeature feature, Object value) {
    	this(parent, feature, value, -1);
    }

    public AddValueTransaction(UObject parent, UStructuralFeature feature, Object value, int index) {
        this(PointerOperations.create(parent, feature, index), value);
    }

    public AddValueTransaction(Pointer target, Object value) {
    	this.target = target;
    	this.value = value;
	}

	private boolean isValidRequest() {
		if (target == null || target.isValid() == false) return false;
		if (target.getPointedFeature().isMany() == false) return false;
		return true;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void run() {

        if (isValidRequest() == false) {
            log.error("Invalid transaction [" + PointerOperations.toPointerString(target) + " value:" + value + "]");
            return;
        }

//        target.setValue(value); //@note we can not use setValue, as this will set and not add the value if the index < 0
        
        int index = -1;
        List list = null;
        if (target instanceof FeaturePointer) {
        	index = ((FeaturePointer)target).getListIndex();
        	list = (List)PointerOperations.getValue(target.getPointedContainer(), target.getPointedFeature(), -1);
        }else
        	list = (List) target.get();	
        
        
        if (index != -1) {
            list.add(index, value);
        } else {
            list.add(value);
        }

        executed = true;

    }

    @SuppressWarnings("rawtypes")
    @Override
    public void undo() {
        if (isValidRequest() == false) {
            log.error("Invalid transaction [" + PointerOperations.toPointerString(target) + " value:" + value + "]");
            return;
        }

        int index = -1;
        if (target instanceof FeaturePointer) index = ((FeaturePointer)target).getListIndex();
        UObject parent = target.getPointedContainer();
        UStructuralFeature feature = target.getPointedFeature();
        List list = (List) parent.uGet(feature);

        if (index != -1) {
            list.remove(index);
        } else {
            list.remove(value);
        }

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
