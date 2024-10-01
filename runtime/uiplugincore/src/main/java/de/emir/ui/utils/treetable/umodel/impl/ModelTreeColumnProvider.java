package de.emir.ui.utils.treetable.umodel.impl;


import de.emir.ui.utils.treetable.umodel.AbstractColumnProvider;
import de.emir.ui.utils.treetable.umodel.IColumnProvider;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.TreeTableModel;

public class ModelTreeColumnProvider extends AbstractColumnProvider implements IColumnProvider {

    public ModelTreeColumnProvider() {
        super("Model", TreeTableModel.class, false);
    }

    @Override
    public Object getColumnValue(Object value) {
        return ((DefaultMutableTreeTableNode) value).getUserObject();
    }
}
