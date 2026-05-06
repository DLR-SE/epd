/**
 * 
 */
package de.emir.epd.routemanager.cmd;

import java.lang.reflect.Field;

import org.apache.logging.log4j.Logger;
import org.jdesktop.swingx.JXTreeTable;

import de.emir.epd.routemanager.view.RouteListView;
import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * Button to refresh the RouteManager tree table UI.
 */
public class RefreshRoutesCommand extends AbstractCommand {
	private static final Logger LOG = ULog.getLogger(RefreshRoutesCommand.class);

	public RefreshRoutesCommand() {
	}

	/**
	 *	Selects first element in tree and updates the tree view. (Note: it does not update the data model) 
	 */
	@Override
	public void execute() {
        try {
        	RouteListView rlv = PlatformUtil.getViewManager().getView(RouteListView.class);
        	Field mR1 = rlv.getClass().getDeclaredField("mTreeTable");
        	mR1.setAccessible(true);
        	JXTreeTable mTree = (JXTreeTable) mR1.get(rlv);
        	if (mTree == null) {
        		LOG.debug("No TREEEEEE!");
        		return;
        	}
        	mTree.clearSelection();
        	if (mTree.getRowCount() >= 1) {
        		mTree.changeSelection(0, 0, false, false);        		
        		mTree.updateUI();
        	}
        	mTree.clearSelection();
        }
        catch ( Exception e ){
        	e.printStackTrace();
        }
        
        
	}

}
