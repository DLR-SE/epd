package de.emir.epd.routemanager.cmd.popup;

import java.util.List;
import java.util.Optional;

import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.routemanager.ids.RouteManagerBasic;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.iec61174.Waypoint;
import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.utils.UCoreUtils;

public abstract class AbstractWpPopUpCommand extends AbstractCommand {

    private Waypoint mLastWaypoint;

	public AbstractWpPopUpCommand() {
        PlatformUtil.getSelectionManager().subscribe(MVBasic.MAP_FOCUS_CTX, sub -> handleCanExecute(sub));
        handleCanExecute(Optional.ofNullable(PlatformUtil.getSelectionManager().getSelectedObject(MVBasic.MAP_FOCUS_CTX)));
    }

    private void handleCanExecute(Optional<Object> sel) {

    	if (sel == null || sel.isPresent() == false) { 
    		setCanExecute(false);
    		return ;
    	}
    	Object obj = sel.get();
    	if (obj instanceof Waypoint) {
    		setCanExecute(true);
    		mLastWaypoint = (Waypoint)obj;
    	}else {
    		setCanExecute(false);
    		mLastWaypoint = null;
    	}
//    	PlatformUtil.getSelectionManager().getSelectedObjectAsList(RouteManagerBasic.CTX_WAYPOINT_MAP_SELECTION);
//        List<?> selection = PlatformUtil.getSelectionManager().getSelectedObjectAsList(MVBasic.MAP_FOCUS_CTX);
//
//        if (selection.size() > 1) {
//            setCanExecute(false);
//            return;
//        }
//
//        for (Object object : selection) {
//            if (object instanceof Waypoint) {
//                setCanExecute(true);
//                return;
//            }
//        }
//
//        setCanExecute(false);
    }

    protected Waypoint getSelectedWaypoint() {
       return mLastWaypoint;
    }

    protected Route getSelectedRoute() {
        if (mLastWaypoint == null) return null;
        return UCoreUtils.getFirstParent(mLastWaypoint, Route.class);
    }
}
