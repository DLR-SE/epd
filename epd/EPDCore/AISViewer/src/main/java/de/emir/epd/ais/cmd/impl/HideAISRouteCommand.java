package de.emir.epd.ais.cmd.impl;

import de.emir.epd.ais.cmd.AbstractAISRouteCommand;
import de.emir.epd.ais.cmd.RouteSelectionDialog;
import de.emir.model.domain.maritime.vessel.Vessel;

public class HideAISRouteCommand extends AbstractAISRouteCommand {


    @Override
    public void execute() {
        Vessel vessel = getVessel();

        if(vessel == null){
            return;
        }

        RouteSelectionDialog.showRouteSelectionDialog(vessel);
    }
}
