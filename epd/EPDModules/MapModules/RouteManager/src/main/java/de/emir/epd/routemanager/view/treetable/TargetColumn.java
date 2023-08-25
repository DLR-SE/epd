package de.emir.epd.routemanager.view.treetable;

import de.emir.epd.routemanager.IRouteManager.IRouteAccessModel;
import de.emir.ui.utils.treetable.TreeTableModel;
import de.emir.ui.utils.treetable.umodel.AbstractColumnProvider;
import de.emir.ui.utils.treetable.umodel.IColumnProvider;

public class TargetColumn extends AbstractColumnProvider implements IColumnProvider {

	public TargetColumn() {
		super("Targets", TreeTableModel.class, true);
	}

	@Override
	public Object getColumnValue(Object value) {
		if (value instanceof IRouteAccessModel)
			return ((IRouteAccessModel) value).getName();
		return value; 
	}
}
