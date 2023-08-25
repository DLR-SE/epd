package de.emir.epd.routemanager.editor;

import java.awt.Color; 
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.routemanager.IRouteManager;
import de.emir.epd.routemanager.cmd.ZoomToRouteCommand;
import de.emir.epd.routemanager.cmd.ZoomToWayPointCommand;
import de.emir.epd.routemanager.cmd.popup.DeleteWayPointCommand;
import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.model.domain.maritime.iec61174.Iec61174Package;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.model.domain.maritime.iec61174.impl.LegImpl;
import de.emir.model.domain.maritime.iec61174.impl.RouteScheduleImpl;
import de.emir.model.universal.units.DistanceUnit;
import de.emir.model.universal.units.SpeedUnit;
import de.emir.model.universal.units.impl.SpeedImpl;
import de.emir.rcp.manager.CommandManager;
import de.emir.rcp.manager.PropertyManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.transactions.CompoundTransaction;
import de.emir.rcp.model.transactions.SetValueTransaction;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import io.reactivex.rxjava3.disposables.Disposable;
import net.miginfocom.swing.MigLayout;

public class RoutePropertiesDialog extends JPanel {

    private static final long serialVersionUID = -19650719719082433L;
    private static RoutePropertiesDialog instance;
    private static JDialog dialog;
    private JButton zoomToWayPointButton;
    private JButton zoomToRouteButton;
    private JButton setButton;
    //    private JTextField originField;
//    private JTextField destinationField;
    private JTextField speedAllLegsField;
    private JTable waypointTable;
    private Component routeNameEdit;
    private Component estimatedTimeOfArrivalEdit;
    private Component estimatedTimeOfDepatureEdit;
    private WaypointTableModel model;
    private JScrollPane wayointTableScrollPane;
    private JButton deleteButton;
    private JCheckBox visibleCheckBox;
    private ITreeValueChangeListener routeListener;
    private Disposable routeVisibilityListener;
    private Route current;
    private JLabel routeLengthLabel;


    private RoutePropertiesDialog(Route route) {
        current = route;
        init();
    }

    public static void showRouteEditor(Route route) {
        if (instance == null) {
            instance = new RoutePropertiesDialog(route);
            dialog = new JDialog(PlatformUtil.getWindowManager().getMainWindow());
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
            instance.setRoute(route);
            dialog.requestFocus();
        }
    }

    private void init() {
        initGUI();
        initListeners();
        update();
        updateButtons();
    }

    private void initGUI() {
        JPanel wrapper = new JPanel();
        wrapper.setAlignmentY(0.0f);
        wrapper.setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Route Properties"));
        setLayout(new MigLayout("", "[450px,grow]", "[100px][200px,grow,fill][100px,center]"));
        add(wrapper, "cell 0 0,grow");
        wrapper.setLayout(new MigLayout("", "[46px][169px,grow][137px][29px,grow][grow][29px]", "[20px][20px][14px][][]"));

        JLabel routeNameLabel = new JLabel("Name");
        wrapper.add(routeNameLabel, "cell 0 0,alignx right,aligny center");

        routeNameEdit = PropertyManager.getInstance().getDefaultEditor(PointerOperations.create(current, Iec61174Package.Literals.Route_name)).getEditor();
        wrapper.add(routeNameEdit, "cell 1 0,growx,aligny center");

        JLabel timeOfDepartureLabel = new JLabel("Estimated Time of Departure: ");
        wrapper.add(timeOfDepartureLabel, "cell 2 0,alignx trailing,aligny center");

        if (this.current.getSchedule() == null)
            this.current.setSchedule(new RouteScheduleImpl()); //ensure that we do have an schedule, otherwise the pointer below will point to nothing
        estimatedTimeOfDepatureEdit = PropertyManager.getInstance().getDefaultEditor(PointerOperations.create(current, Iec61174Package.Literals.Route_schedule, Iec61174Package.Literals.RouteSchedule_estimatedTimeOfDepature)).getEditor();
        wrapper.add(estimatedTimeOfDepatureEdit, "cell 3 0,growx");

        JLabel originLabel = new JLabel("Origin");
        wrapper.add(originLabel, "cell 0 1,alignx right,aligny center");

        //  ---------------------------------- 		ENAV 2018 	FIX 	---------------------------------------------
        //for the moment we do not have an extra field for origin and destination, thus we are using properties here
        //TODO: change the IEC61174 data model or at least our implementation :)
        //@note this solution is just a bad!! bugfix for the enav and beside that those properties will not notify the 
        //RouteManger to store the route (the other bugfix for the ENAV)
        //hoever if the route has been changed, after adding the properties they will be serialized
        if (current.getProperty("origin") == null) current.setPropertyValue("origin", "");
        if (current.getProperty("destination") == null) current.setPropertyValue("destination", "");

//        originField = new JTextField();
        wrapper.add(PropertyManager.getInstance().getFirstEditor(current.getProperty("origin")).getEditor(), "cell 1 1,growx,aligny center");
//        originField.setColumns(10);
        //  ---------------------------------- 		ENAV 2018 	FIX 	---------------------------------------------


        JLabel estimatedTimeLabel = new JLabel("Estimated Time of Arrival: ");
        wrapper.add(estimatedTimeLabel, "cell 2 1,alignx left,aligny center");

        estimatedTimeOfArrivalEdit = PropertyManager.getInstance().getDefaultEditor(PointerOperations.create(current, Iec61174Package.Literals.Route_schedule, Iec61174Package.Literals.RouteSchedule_estimatedTimeOfArrival)).getEditor();
        wrapper.add(estimatedTimeOfArrivalEdit, "cell 3 1,growx");

        JLabel destinationLabel = new JLabel("Destination");
        wrapper.add(destinationLabel, "cell 0 2,alignx trailing,aligny center");

        //  ---------------------------------- 		ENAV 2018 	FIX 	---------------------------------------------
//        destinationField = new JTextField();
        wrapper.add(PropertyManager.getInstance().getFirstEditor(current.getProperty("destination")).getEditor(), "cell 1 2,growx");
//        destinationField.setColumns(10);

        //  ---------------------------------- 		ENAV 2018 	FIX 	---------------------------------------------

        JLabel totalDistanceLbel = new JLabel("Total Distance: ");
        wrapper.add(totalDistanceLbel, "cell 0 3");

        routeLengthLabel = new JLabel();
        wrapper.add(routeLengthLabel, "cell 1 3");

        JLabel speedAllLegsLabel = new JLabel("Speed all legs:");
        wrapper.add(speedAllLegsLabel, "cell 2 4,alignx left");

        speedAllLegsField = new JTextField("10.00");
        wrapper.add(speedAllLegsField, "cell 3 4,growx");
        speedAllLegsField.setColumns(10);

        setButton = new JButton("Set");
        wrapper.add(setButton, "cell 5 4");

        JPanel routeDetailsWrapper = new JPanel();
        routeDetailsWrapper.setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Route Details"));
        add(routeDetailsWrapper, "cell 0 1,grow");

        routeDetailsWrapper.setLayout(new MigLayout("", "[531px,grow]", "[100px,grow]"));
        model = new WaypointTableModel(current.getWaypoints().getWaypoints());
        waypointTable = new JTable(model);

        wayointTableScrollPane = new JScrollPane(waypointTable);
        routeDetailsWrapper.add(wayointTableScrollPane, "cell 0 0,grow");

        JPanel buttonWrapper = new JPanel();
        add(buttonWrapper, "cell 0 2,growx,aligny bottom");
        buttonWrapper.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][]", "[]"));

        zoomToRouteButton = new JButton("Zoom to Route");
        buttonWrapper.add(zoomToRouteButton, "cell 0 0");

        zoomToWayPointButton = new JButton("Zoom to Way Point");
        buttonWrapper.add(zoomToWayPointButton, "cell 1 0");

        deleteButton = new JButton("Delete");
        buttonWrapper.add(deleteButton, "cell 2 0");

        visibleCheckBox = new JCheckBox("Visible");
        buttonWrapper.add(visibleCheckBox, "cell 3 0");
    }

    private void initListeners() {
        zoomToWayPointButton.addActionListener(e -> zoomToWaypoint());

        zoomToRouteButton.addActionListener(e -> zoomToRoute());

        setButton.addActionListener(e -> setAllLegSpeed());

        deleteButton.addActionListener(e -> deleteWaypoint());

        visibleCheckBox.addActionListener(e -> IRouteManager.getDefaultRouteManager().setVisible(current, visibleCheckBox.isSelected()));

        waypointTable.getSelectionModel().addListSelectionListener(e -> updateButtons());

        routeListener = notification -> update();

        current.registerTreeListener(routeListener);

        routeVisibilityListener = IRouteManager.getDefaultRouteManager().subscribeRouteChanges(route -> {
            if (route == current) {
                updateButtons();
            }
        });


    }

    private void deleteWaypoint() {
        PlatformUtil.getSelectionManager().setSelection(MVBasic.MAP_FOCUS_CTX, current.getWaypoints().getWaypoints().get(waypointTable.getSelectedRow()));
        PlatformUtil.getSelectionManager().setSelection(RouteManagerBasic.CTX_ROUTE_MAP_SELECTION, current);
        CommandManager cm = ServiceManager.get(CommandManager.class);
        cm.executeCommand(new DeleteWayPointCommand());
    }

    private void zoomToRoute() {
        PlatformUtil.getSelectionManager().setSelection(RouteManagerBasic.CTX_ROUTE_SELECTION, current);
        CommandManager cm = ServiceManager.get(CommandManager.class);
        System.out.println(IRouteManager.getDefaultRouteManager());
        cm.executeCommand(new ZoomToRouteCommand(IRouteManager.getDefaultRouteManager()));
    }

    private void zoomToWaypoint() {
        PlatformUtil.getSelectionManager().setSelection(MVBasic.MAP_FOCUS_CTX, current.getWaypoints().getWaypoints().get(waypointTable.getSelectedRow()));
        PlatformUtil.getSelectionManager().setSelection(RouteManagerBasic.CTX_ROUTE_MAP_SELECTION, current);
        CommandManager cm = ServiceManager.get(CommandManager.class);
        cm.executeCommand(new ZoomToWayPointCommand());
    }

    private void setAllLegSpeed() {
        CompoundTransaction ct = new CompoundTransaction();

        for (Waypoint point : current.getWaypoints().getWaypoints()) {
            SetValueTransaction minPlanSpeed = new SetValueTransaction(
                    point.getLeg(),
                    Iec61174Package.Literals.Leg_planSpeedMin,
                    new SpeedImpl(Double.parseDouble(speedAllLegsField.getText()),
                            de.emir.model.universal.units.SpeedUnit.KNOTS));
            SetValueTransaction maxPlanSpeed = new SetValueTransaction(
                    point.getLeg(),
                    Iec61174Package.Literals.Leg_planSpeedMax,
                    new SpeedImpl(Double.parseDouble(speedAllLegsField.getText()),
                            de.emir.model.universal.units.SpeedUnit.KNOTS));
            ct.add(minPlanSpeed);
            ct.add(maxPlanSpeed);
        }

        PlatformUtil.getModelManager().getModelProvider().getTransactionStack().run(ct);
    }

    private void update() {
        routeLengthLabel.setText((int) current.getLength().getValue() + " " + current.getLength().getUnit().getName());
        try {
            speedAllLegsField.setText(String.valueOf(current.getWaypoints().getWaypoints().get(0).getLeg().getPlanSpeedMin().getValue()));
        } catch (NullPointerException e) {
            List<Waypoint> wayPoints = current.getWaypoints().getWaypoints();
            if (wayPoints.get(0).getLeg() == null) {
                wayPoints.get(0).setLeg(new LegImpl());
            }

            current.getWaypoints().getWaypoints().get(0).getLeg().setPlanSpeedMin(new SpeedImpl(10.00, SpeedUnit.KNOTS));
            speedAllLegsField.setText(String.valueOf(current.getWaypoints().getWaypoints().get(0).getLeg().getPlanSpeedMin().getValue()));
        }
        model.fireTableDataChanged();
    }

    private void updateButtons() {
        visibleCheckBox.setSelected(IRouteManager.getDefaultRouteManager().isVisible(current));
        zoomToRouteButton.setEnabled(visibleCheckBox.isSelected());

        boolean zoomToWayPoint = visibleCheckBox.isSelected() && (waypointTable.getSelectedRow() != -1);
        zoomToWayPointButton.setEnabled(zoomToWayPoint);
    }

    private void setRoute(Route route) {
        current.removeTreeListener(routeListener);
        routeVisibilityListener.dispose();
        removeAll();
        current = route;
        init();

    }
}
