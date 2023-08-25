package de.emir.epd.routemanager.view.treetable;

import de.emir.epd.routemanager.IRouteManager.IRouteAccessModel;
import de.emir.ui.utils.treetable.umodel.AbstractColumnProvider;
import de.emir.ui.utils.treetable.umodel.IColumnProvider;

public class DescriptionColumn extends AbstractColumnProvider implements IColumnProvider {

	public DescriptionColumn() {
		super("Description", String.class, true);
	}

	@Override
	public Object getColumnValue(Object node) {
		if (node instanceof IRouteAccessModel)
			return ((IRouteAccessModel)node).getDescription();
		return node;
	}

}
