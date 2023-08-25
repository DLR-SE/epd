package de.emir.ui.utils.treetable.umodel.impl;

import javax.swing.table.TableCellEditor;

import de.emir.tuml.ucore.runtime.UObject;
import de.emir.ui.utils.treetable.umodel.AbstractColumnProvider;
import de.emir.ui.utils.treetable.umodel.IColumnProvider;
import de.emir.ui.utils.treetable.umodel.UNode;

public class UTypeColumnProvider extends AbstractColumnProvider implements IColumnProvider {

    public UTypeColumnProvider() {
        super("Type", String.class, false);
    }

    public Object getColumnValue(Object node) {
        UNode value = (UNode) node;
        if (value.getUserObject() instanceof UObject)
            return ((UObject) value.getUserObject()).getUClassifier().getName();
        return null;
    }

    @Override
    public TableCellEditor getCellEditor(Object node) {
        return null;
    }

}
