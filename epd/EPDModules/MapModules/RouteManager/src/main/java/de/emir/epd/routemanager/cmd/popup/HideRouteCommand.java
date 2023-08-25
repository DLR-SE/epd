package de.emir.epd.routemanager.cmd.popup;


import de.emir.epd.routemanager.IRouteManager;

public class HideRouteCommand extends AbstractRoutePopUpCommand {

    private IRouteManager mRouteManager;

	public HideRouteCommand(IRouteManager rm) {
        super();
        mRouteManager = rm;
    }

    @Override
    public void execute() {
        mRouteManager.setVisible(getSelectedRoute(), false);
    }
}
