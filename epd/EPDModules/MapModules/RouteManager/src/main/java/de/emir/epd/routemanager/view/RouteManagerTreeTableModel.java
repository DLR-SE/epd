package de.emir.epd.routemanager.view;

import de.emir.epd.routemanager.IRouteManager;
import de.emir.epd.routemanager.IRouteManager.IRouteAccessModel;
import de.emir.epd.routemanager.view.treetable.DescriptionColumn;
import de.emir.epd.routemanager.view.treetable.TargetColumn;
import de.emir.epd.routemanager.view.treetable.VisibleColumn;
import de.emir.ui.utils.treetable.AbstractTreeTableModel;
import de.emir.ui.utils.treetable.umodel.IColumnProvider;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class RouteManagerTreeTableModel extends AbstractTreeTableModel {

    private static String[] getColumnNames(IColumnProvider[] c) {
        String[] n = new String[c.length + 1];
        n[0] = "Name";
        for (int i = 0; i < c.length; i++) n[i + 1] = c[i].getColumnName();
        return n;
    }

    private Disposable mStructureDisposable;
    private IColumnProvider[] mColumnProvider;
    protected IRouteManager mRouteManager;
    protected ArrayList<IRouteAccessModel> mRoot;


    public RouteManagerTreeTableModel(IRouteManager root) {
        super(new ArrayList<IRouteAccessModel>());
        mRoot = (ArrayList<IRouteAccessModel>) this.root;

        mRouteManager = root;
        mStructureDisposable = root.subscribeStructureChanges(new Consumer<PropertyChangeEvent>() {
            @Override
            public void accept(PropertyChangeEvent t) throws Exception {
                updateRoot();
            }
        });
        mColumnProvider = new IColumnProvider[]{
                new TargetColumn(), new DescriptionColumn(), new VisibleColumn(mRouteManager)
        };

        updateRoot();
    }

    protected void updateRoot() {
        mRoot.clear();

        for (IRouteAccessModel rm : mRouteManager.getRouteModels())
            mRoot.add(rm);
    }

    public void addColumn(IColumnProvider column) {
        mColumnProvider = Arrays.copyOf(mColumnProvider, mColumnProvider.length + 1);
        mColumnProvider[mColumnProvider.length - 1] = column;
    }


    @Override
    public Class getColumnClass(int column) {
        return mColumnProvider[column].getColumnClass();
    }

    public int getColumnCount() {
        return mColumnProvider.length;
    }

    public String getColumnName(int column) {
        return mColumnProvider[column].getColumnName();
    }

    public Object getValueAt(Object node, int column) {
        if (node == mRoot) {
            return null;
        }
        return mColumnProvider[column].getColumnValue(node);
    }

    @Override
    public TableCellEditor getCellEditor(Object node, int column) {
        return mColumnProvider[column].getCellEditor(node);
    }

    @Override
    public TableCellRenderer getCellRenderer(Object node, int column) {
        return mColumnProvider[column].getCellRenderer(node);
    }

    @Override
    public boolean isCellEditable(Object node, int column) {
        if (column == 0) return true;
        if (node == mRoot) return false;
        return mColumnProvider[column].isEditable(node);
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent == mRoot)
            return mRoot.get(index);
        if (parent instanceof IRouteAccessModel) {
            IRouteAccessModel ram = (IRouteAccessModel) parent;
            if (ram.getChildren() != null)
                return ram.getChildren().get(index);
            else
                return ram.getOwnedRoutes().get(index);
        }
        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent == mRoot)
            return mRoot.size();

        if (parent instanceof IRouteAccessModel) {
            IRouteAccessModel ram = (IRouteAccessModel) parent;
            if (ram.getChildren() != null)
                return ram.getChildren().size();
            else
                return ram.getOwnedRoutes().size();
        }
        return 0;
    }

    @Override
    public void setValueAt(Object aValue, Object node, int column) {
        if (node != null && column != 0)
            mColumnProvider[column].setValue(node, aValue);
    }


}
