package de.emir.ui.utils.treetable.umodel.impl;

import javax.swing.tree.DefaultMutableTreeNode;

import de.emir.ui.utils.treetable.TreeTableModel;
import de.emir.ui.utils.treetable.umodel.AbstractColumnProvider;
import de.emir.ui.utils.treetable.umodel.IColumnProvider;
import de.emir.ui.utils.treetable.umodel.UNode;

public class ModelTreeColumnProvider extends AbstractColumnProvider implements IColumnProvider {

    public ModelTreeColumnProvider() {
        super("Model", TreeTableModel.class, false);
    }

    public Object getColumnValue(Object value) {
        return ((DefaultMutableTreeNode) value).getUserObject();
    }
}
