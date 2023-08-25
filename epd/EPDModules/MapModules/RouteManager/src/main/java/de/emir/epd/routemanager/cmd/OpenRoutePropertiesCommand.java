package de.emir.epd.routemanager.cmd;

import de.emir.epd.routemanager.editor.RoutePropertiesDialog;
import de.emir.model.domain.maritime.iec61174.Route;

public class OpenRoutePropertiesCommand extends AbstractSelectedSingleRouteCommand {

	public OpenRoutePropertiesCommand() {
        super();
    }

    @Override
    public void execute() {
        Route route = getSelectedRoute();
        if (route != null) {
            RoutePropertiesDialog.showRouteEditor(route);
        }
    }

}
