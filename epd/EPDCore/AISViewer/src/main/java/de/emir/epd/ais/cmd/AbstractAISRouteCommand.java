package de.emir.epd.ais.cmd;

import de.emir.epd.ais.ids.AisBasics;
import de.emir.epd.mapview.ids.MVBasic;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;

public abstract class AbstractAISRouteCommand extends AbstractCommand {

    public AbstractAISRouteCommand() {
        PlatformUtil.getSelectionManager().subscribe(MVBasic.MAP_FOCUS_CTX, sub -> handleCanExecute());
        handleCanExecute();
    }

    protected void handleCanExecute() {

        Object obj = PlatformUtil.getSelectionManager().getSelectedObject(AisBasics.AIS_SELECTION_ID);

        if(obj instanceof Vessel){
            setCanExecute(true);
            return;
        }

        setCanExecute(false);
    }

    protected Vessel getVessel(){
        Object obj = PlatformUtil.getSelectionManager().getSelectedObject(AisBasics.AIS_SELECTION_ID);

        if(obj instanceof Vessel){
            return (Vessel) obj;
        }

        return null;
    }



}
