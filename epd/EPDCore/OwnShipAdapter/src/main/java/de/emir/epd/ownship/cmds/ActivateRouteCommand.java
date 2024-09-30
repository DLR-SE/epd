package de.emir.epd.ownship.cmds;

import de.emir.epd.model.EPDModelUtils;
import de.emir.epd.model.ais.AisTarget;
import de.emir.epd.routemanager.cmd.AbstractSelectedSingleRouteCommand;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VoyageCharacteristic;
import de.emir.model.domain.maritime.vessel.impl.VoyageCharacteristicImpl;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.logging.ULog;
import org.slf4j.Logger;

public class ActivateRouteCommand extends AbstractSelectedSingleRouteCommand {
    private static final Logger LOG = ULog.getLogger(ActivateRouteCommand.class);

    @Override
    public void execute() {
//		if (transponder == null || transponder.getOwnship() == null || transponder.getCurrentTarget() == null) return;
        if (getSelectedRoute() == null) return;
        Route route = getSelectedRoute();
        Vessel ownship = EPDModelUtils.retrieveOwnship();
        if(ownship == null) {
            return;
        }
        AisTarget.getVoyage(ownship);
        VoyageCharacteristic vc = null;
        vc = ownship.getFirstCharacteristic(VoyageCharacteristic.class, true);
        if (vc == null) {
            vc = new VoyageCharacteristicImpl();
        }
        vc.getRoutes().add(route);
        vc.setActiveRoute(route);
        ownship.getCharacteristics().add(vc);
    }
}
