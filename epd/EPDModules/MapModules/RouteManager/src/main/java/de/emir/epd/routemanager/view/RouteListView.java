package de.emir.epd.routemanager.view;

import de.emir.epd.routemanager.IRouteManager;
import de.emir.epd.routemanager.IRouteManager.IRouteAccessModel;
import de.emir.epd.routemanager.cmd.OpenRoutePropertiesCommand;
import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.rcp.manager.CommandManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.AbstractView;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.ui.utils.treetable.TreeTable;
import de.emir.ui.utils.treetable.TreeTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class RouteListView extends AbstractView {
    private TreeTable					mTreeTable;
    private TreeTableModel				routeTableModel;

    private boolean 					selectionListenerDisabled;
    private boolean 					modelListenerDisabled;
	private IRouteManager 				mRouteManager;
	private RouteManagerTreeTableModel mTreeTableModel;

	public RouteListView(String id) {
		this(id, ServiceManager.getByID(RouteManagerBasic.DEFAULT_ROUTE_MANAGER)); 
	}
    public RouteListView(String id, IRouteManager defaultRouteManager) {
    	super(id);
    	mRouteManager = defaultRouteManager;
	}

	/**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component createContent() {

        JPanel parent = new JPanel();
        GridBagLayout gbl_parent = new GridBagLayout();
        gbl_parent.columnWidths = new int[]{0, 0, 0};
        gbl_parent.rowHeights = new int[]{0, 0, 0};
        gbl_parent.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        gbl_parent.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        parent.setLayout(gbl_parent);

        JPanel infoPanel = new JPanel();
        GridBagConstraints gbc_infoPanel = new GridBagConstraints();
        gbc_infoPanel.insets = new Insets(0, 0, 5, 5);
        gbc_infoPanel.fill = GridBagConstraints.BOTH;
        gbc_infoPanel.gridx = 0;
        gbc_infoPanel.gridy = 0;
        parent.add(infoPanel, gbc_infoPanel);
        GridBagLayout gbl_infoPanel = new GridBagLayout();
        gbl_infoPanel.columnWidths = new int[]{0, 0};
        gbl_infoPanel.rowHeights = new int[]{0, 0};
        gbl_infoPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_infoPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        infoPanel.setLayout(gbl_infoPanel);

        JLabel lblVeoyageXy = new JLabel("Voyage");
        GridBagConstraints gbc_lblVeoyageXy = new GridBagConstraints();
        gbc_lblVeoyageXy.insets = new Insets(5, 5, 5, 5);
        gbc_lblVeoyageXy.gridx = 0;
        gbc_lblVeoyageXy.gridy = 0;
        infoPanel.add(lblVeoyageXy, gbc_lblVeoyageXy);

        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false);

        GridLayout tbLayout = new GridLayout();
        tbLayout.setColumns(1);
        tbLayout.setVgap(5);
        tbLayout.setRows(0);
        toolBar.setLayout(tbLayout);
        GridBagConstraints gbc_toolBar = new GridBagConstraints();
        gbc_toolBar.anchor = GridBagConstraints.NORTH;
        gbc_toolBar.insets = new Insets(0, 0, 5, 5);
        gbc_toolBar.fill = GridBagConstraints.HORIZONTAL;
        gbc_toolBar.gridx = 1;
        gbc_toolBar.gridy = 1;
        parent.add(toolBar, gbc_toolBar);

        PlatformUtil.getMenuManager().fillToolbar(toolBar, RouteManagerBasic.ROUTE_LIST_VIEW_TOOLBAR);

        JScrollPane sc = new JScrollPane();
        sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        GridBagConstraints gbc_sc = new GridBagConstraints();
        gbc_sc.insets = new Insets(1, 2, 2, 5);
        gbc_sc.fill = GridBagConstraints.BOTH;
        gbc_sc.gridx = 0;
        gbc_sc.gridy = 1;
        parent.add(sc, gbc_sc);

        
        mTreeTableModel = new RouteManagerTreeTableModel(mRouteManager);
        mTreeTable = new TreeTable(mTreeTableModel);
        mTreeTable.setTreeCellRenderer(new DefaultTreeCellRenderer() {
        	@Override
        	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
        			boolean leaf, int row, boolean hasFocus) {
        		
        		JLabel l = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        		if (value instanceof IRouteAccessModel) {
        			l.setText(((IRouteAccessModel) value).getName());
        		}else if (value instanceof Route)
        			l.setText(((Route) value).getName());
        		else if (value instanceof ArrayList<?>) {
        			l.setText("Groups");
        		}
        		return l;
        	}
        });
        sc.setViewportView(mTreeTable);
        
        
        mRouteManager.subscribeStructureChanges(this::_updateUI);
        mRouteManager.subscribeRouteChanges(this::_updateUI);
        registerListeners();
        return parent;
    }

    private void _updateUI(PropertyChangeEvent event) {
        //TODO verify if this will work with changes or if there's another way to handle this
    		//FIXME @jm: No it does not work. Changeing the editor / the eMod File does not work
    		//please add short description why this change was done, e.g. the observed error
		String pName = event.getPropertyName();
//		try {
//		System.out.println(String.format("%s %s %s", event.getPropertyName(), event.getOldValue().toString(),
//				event.getNewValue().toString()));
//		} catch (Exception e) {
//
//		}
		  //checks whether the change needs to be displayed or if a value changed thats irrelevant for RouteListView
        if ("name".equals(pName) || "value".equals(pName) || "Routes".equals(pName)) {
            SwingUtilities.invokeLater(new Runnable() {         
                @Override
                public void run() {
                    mTreeTable.updateUI();
                }
            });
        }
	}
	private void registerListeners() {

        mTreeTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    CommandManager cm = ServiceManager.get(CommandManager.class);
                    cm.executeCommand(new OpenRoutePropertiesCommand());
                }
            }
        });
        mTreeTable.getSelectionModel().addListSelectionListener(e -> handleTableSelection(e));

        PlatformUtil.getSelectionManager().subscribe(RouteManagerBasic.CTX_ROUTE_SELECTION, c -> setRowSelection());
	}

	private void setRowSelection() {
		if (modelListenerDisabled == true) {
			return;
		}

	    Object selected = PlatformUtil.getSelectionManager().getSelectedObjectAsList(RouteManagerBasic.CTX_ROUTE_SELECTION);
        List<Route> routesToSelect = new ArrayList<>();
        if (selected instanceof List) {
            List<?> list = (List<?>) selected;
            for (Object object : list) {
                if (object instanceof Route) {
                    routesToSelect.add((Route) object);
                }
            }
        }

        if (routesToSelect.size() == 0) {
            return;
        }

        selectionListenerDisabled = true;
        mTreeTable.getSelectionModel().clearSelection();

//        for (Route route : routesToSelect) {
//            int index = routeTableModel.getRowIndexOf(route);
//            if (index != -1) {
//            	mTreeTable.getSelectionModel().addSelectionInterval(index, index);
//            }
//        }
        selectionListenerDisabled = false;

    }

    private void handleTableSelection(ListSelectionEvent e) {
        if (selectionListenerDisabled == false) {
            int[] selectedRows = mTreeTable.getSelectedRows();
            List<Route> selectedRoutes = getRoutes(selectedRows);
            if (selectedRoutes.size() == 0) {
                selectedRoutes = null;
            }
            modelListenerDisabled = true;
            PlatformUtil.getSelectionManager().setSelection(RouteManagerBasic.CTX_ROUTE_SELECTION, selectedRoutes);
            modelListenerDisabled = false;
        }
    }

    public List<Route> getRoutes(int[] selectedRows) {
		ArrayList<Route> out = new ArrayList<>();
		for (int row : selectedRows) {
			Object obj = mTreeTable.getNodeForRow(row);
			if (obj instanceof Route)
				out.add((Route)obj);
		}
		return out;
	}
	private void handleModelChanged() {
//    	List<IRouteGroup> groups = mRouteManager.getRouteGroups();
//    	
//    	Object root = routeTableModel.getRoot();
//        if (root instanceof RootModelData) {
//            RootModelData rootModelData = (RootModelData) root;
//            rootModelData.setGroups(groups);
//        }
    	
//        List<Vessel> vesselList = new ArrayList<>();
//        VoyageCharacteristic ownShipVC = mRouteManager.getVoyageCharacteristic();
//        if (ownShipVC != null && ownShipVC.getUContainer() instanceof Vessel) {
//            vesselList.add((Vessel) ownShipVC.getUContainer());
//        }
//
//        List<VoyageCharacteristic> aisShipVCs = mRouteManager.getAisVoyageCharacteristics();
//        for (VoyageCharacteristic vc : aisShipVCs) {
//            if (vc != null && vc.getUContainer() instanceof Vessel) {
//                vesselList.add((Vessel) vc.getUContainer());
//            }
//        }
//
//        Object root = routeTableModel.getRoot();
//        if (root instanceof RootModelData) {
//            RootModelData rootModelData = (RootModelData) root;
//            rootModelData.setVessels(vesselList);
//        }
//        routeTable.updateUI();
    }

    @Override
    public void onOpen() {

    }

    @Override
    public void onClose() {

    }

}
