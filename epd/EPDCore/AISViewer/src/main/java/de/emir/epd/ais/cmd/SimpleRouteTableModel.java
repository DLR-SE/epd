package de.emir.epd.ais.cmd;

import de.emir.epd.routemanager.IRouteManager;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VoyageCharacteristic;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SimpleRouteTableModel extends AbstractTableModel {
    private List<Route> routes;

    public SimpleRouteTableModel(Vessel vessel){
        this.routes = new ArrayList<>();

        VoyageCharacteristic vc = vessel.getFirstCharacteristic(VoyageCharacteristic.class, true);

        if(vc == null) {
            return;
        }

        routes.add(vc.getActiveRoute());
        routes.addAll(vc.getRoutes());
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Route Name";
            case 1:
                return "Visibility";
        }

        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(columnIndex == 1 && aValue instanceof Boolean){
        	IRouteManager.getDefaultRouteManager().setVisible(routes.get(rowIndex), (boolean) aValue);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }

    @Override
    public int getRowCount() {
        return routes.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Route route = routes.get(rowIndex);

        switch (columnIndex){
            case 0:
                return route.getName() != null ? route.getName() : "";
            case 1:
                return IRouteManager.getDefaultRouteManager().isVisible(route);
        }

        return null;
    }

    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return Boolean.class;
        }

        return null;
    }
}
