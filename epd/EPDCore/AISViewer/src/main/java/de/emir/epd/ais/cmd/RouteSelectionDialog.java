package de.emir.epd.ais.cmd;

import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.rcp.manager.util.PlatformUtil;

import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;

public class RouteSelectionDialog extends JPanel{
    private static JDialog dialog;
    private static RouteSelectionDialog instance;
    private Vessel vessel;
    private JTable routeTable;
    private SimpleRouteTableModel routesTableModel;

    private RouteSelectionDialog(Vessel vessel){
        this.vessel = vessel;

        setVessel(vessel);
    }

    public static void showRouteSelectionDialog(Vessel vessel) {
        if (instance == null) {
            instance = new RouteSelectionDialog(vessel);
//            dialog = new JDialog(PlatformUtil.getWindowManager().getMainWindow());
            dialog = new JDialog();
            dialog.setContentPane(instance);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setTitle("Route Properties");
            dialog.pack();
            dialog.setVisible(true);
            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    super.windowClosed(e);
                    instance = null;
                }
            });
        } else {
            instance.setVessel(vessel);
            dialog.requestFocus();
        }
    }

    private void init(){
        setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane);

        routeTable = new JTable();
        routesTableModel = new SimpleRouteTableModel(vessel);
        routeTable.setModel(routesTableModel);

        scrollPane.setViewportView(routeTable);
    }

    private void setVessel(Vessel vessel){
        removeAll();

        this.vessel = vessel;

        init();

    }

}
