package de.emir.ui.utils.treetable.umodel;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public abstract class AbstractColumnProvider implements IColumnProvider {

    private String mName;
    private Class<?> mClazz;
    private boolean mEditable;

    // optional parameter
    private TableCellRenderer mCellRenderer;
    private TableCellEditor mCellEditor;

    protected AbstractColumnProvider(String name, Class<?> clazz, boolean editable) {
        mName = name;
        mClazz = clazz;
        mEditable = editable;
    }

    public void setCellRenderer(TableCellRenderer renderer) {
        mCellRenderer = renderer;
    }

    public void setCellEditor(TableCellEditor editor) {
        mCellEditor = editor;
    }

    @Override
    public String getColumnName() {
        return mName;
    }

    @Override
    public Class<?> getColumnClass() {
        return mClazz;
    }

    @Override
    public boolean isEditable(Object node) {
        return mEditable;
    }

    @Override
    public TableCellRenderer getCellRenderer(Object node) {
        return mCellRenderer;
    }

    @Override
    public TableCellEditor getCellEditor(Object node) {
        return mCellEditor;
    }

    @Override
    public void setValue(Object node, Object aValue) {
    }

}
