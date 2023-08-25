package de.emir.epd.ais.cmd.impl;

import de.emir.epd.ais.cmd.AbstractAISRouteCommand;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VoyageCharacteristic;

public class ShowAllAisRoutesCommand extends AbstractAISRouteCommand {
    @Override
    public void execute() {
    	//TODO: how to identify AIS Routes?
    	throw new UnsupportedOperationException("Not yet implemented");
//        Vessel vessel = getVessel();
//        VoyageCharacteristic vc = vessel.getFirstCharacteristic(VoyageCharacteristic.class, true);
//        if(vc == null){
//            return;
//        }
//
//        Route active = vc.getActiveRoute();
//        if(active != null){
//        	RouteManager.getDefaultRouteManager().setAISRouteVisible(active, true);
//        }
//
//        for (Route route : vc.getRoutes()){
//        	RouteManager.getDefaultRouteManager().setAISRouteVisible(route, true);
//        }
    }
}
