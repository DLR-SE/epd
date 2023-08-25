package de.emir.epd.routemanager.cmd;

import java.util.ArrayList;
import java.util.List;

import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;

public abstract class AbstractSelectedRouteCommand extends AbstractCommand {

    public AbstractSelectedRouteCommand() {

        PlatformUtil.getSelectionManager().subscribe(RouteManagerBasic.CTX_ROUTE_SELECTION, sub -> handleCanExecute());
        handleCanExecute();
    }

    protected void handleCanExecute() {

        List<?> selection = PlatformUtil.getSelectionManager().getSelectedObjectAsList(RouteManagerBasic.CTX_ROUTE_SELECTION);

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

    protected List<Route> getSelectedRoutes() {
        List<Route> result = new ArrayList<>();
        List<?> selection = PlatformUtil.getSelectionManager().getSelectedObjectAsList(RouteManagerBasic.CTX_ROUTE_SELECTION);

        for (Object object : selection) {
            if (object instanceof Route) {
                result.add((Route) object);
            }
        }

        return result;
    }

}
