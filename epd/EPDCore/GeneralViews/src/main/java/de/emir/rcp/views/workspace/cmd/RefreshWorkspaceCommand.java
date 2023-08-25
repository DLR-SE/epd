package de.emir.rcp.views.workspace.cmd;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.workspace.WorkspaceView;

public class RefreshWorkspaceCommand extends AbstractCommand {

	@Override
	public void execute() {
		
		WorkspaceView view = PlatformUtil.getViewManager().getView(WorkspaceView.class);

		if(view != null) {
			view.refresh();
		}
		

	}

}
