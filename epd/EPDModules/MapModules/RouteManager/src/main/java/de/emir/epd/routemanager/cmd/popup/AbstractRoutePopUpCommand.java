package de.emir.epd.routemanager.cmd.popup;

import java.util.List;

import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;

public abstract class AbstractRoutePopUpCommand extends AbstractCommand {

    public AbstractRoutePopUpCommand() {
        PlatformUtil.getSelectionManager().subscribe(RouteManagerBasic.CTX_ROUTE_SELECTION, sub -> handleCanExecute());
        handleCanExecute();
    }

    private void handleCanExecute() {

        List<?> selection = PlatformUtil.getSelectionManager().getSelectedObjectAsList(RouteManagerBasic.CTX_ROUTE_SELECTION);

        if (selection.size() > 1) {
            setCanExecute(false);
            return;
        }

        for (Object object : selection) {
            if (object instanceof Route) {
                setCanExecute(true);
                return;
            }
        }

        setCanExecute(false);
    }

    @Override
    public void execute() {

    }

    protected Route getSelectedRoute() {
        Object selection = PlatformUtil.getSelectionManager().getSelectedObject(RouteManagerBasic.CTX_ROUTE_SELECTION);
        if (selection instanceof Route) {
            return (Route) selection;
        }

        return null;
    }
}
