package de.emir.epd.routemanager.view.treetable;

import de.emir.epd.routemanager.IRouteManager;
import de.emir.epd.routemanager.IRouteManager.IRouteAccessModel;
import de.emir.epd.routemanager.IRouteManager.VisibilityState;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.ui.utils.treetable.umodel.AbstractColumnProvider;
import de.emir.ui.utils.treetable.umodel.IColumnProvider;

public class VisibleColumn extends AbstractColumnProvider implements IColumnProvider {

	private IRouteManager 					mRouteMgr;
//	private TriStateTreeTableEditor			mEditor = new TriStateTreeTableEditor();

	public VisibleColumn(IRouteManager rm) {
		super("Visible", Boolean.class, true);
		mRouteMgr = rm;
	}
	
	@Override
	public Object getColumnValue(Object node) {
		if (node instanceof IRouteAccessModel) {
			return getState(mRouteMgr.isVisible((IRouteAccessModel)node)); 
		}else if (node instanceof Route) {
			return mRouteMgr.isVisible((Route)node);// ? TristateState.SELECTED : TristateState.DESELECTED;
		}
		return node;
	}

	private Boolean getState(VisibilityState visible) {
		switch(visible) {
		case Invisible : return false; //TristateState.DESELECTED;
		case PartialVisible : return true; //TristateState.INDETERMINATE;
		}
		return true; //TristateState.SELECTED;
	}
	
	@Override
	public void setValue(Object node, Object aValue) {
		if (node instanceof IRouteAccessModel) {
			mRouteMgr.setVisible((IRouteAccessModel)node, (Boolean)aValue);
		}else if (node instanceof Route) {
			mRouteMgr.setVisible((Route)node, (Boolean)aValue);
		}
	}
		
//	@Override
//	public TableCellEditor getCellEditor(Object node) {
//		return mEditor;
//	}
//	@Override
//	public TableCellRenderer getCellRenderer(Object node) {
//		return mEditor;
//	}

}
