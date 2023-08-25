package de.emir.epd.mapview.cmds;

import de.emir.epd.mapview.manager.MapViewManager;
import de.emir.epd.mapview.views.tools.AbstractMapViewTool;
import de.emir.rcp.commands.AbstractCheckableCommand;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import io.reactivex.rxjava3.disposables.Disposable;

public class SetToolActiveStateCommand extends AbstractCheckableCommand {

	private String toolID;
	private Disposable subscription;

	public SetToolActiveStateCommand(String toolID) {
		this.toolID = toolID;
		
		MapViewManager mvn =  ServiceManager.get(MapViewManager.class);
		
		AbstractMapViewTool tool = mvn.getTools().get(toolID);
		if(tool != null) {
			
			bindTool();
			
		} else {
			
			subscription = mvn.subscribeToolCreated(sub -> {
				
				if(sub.equals(toolID)) {
					
					bindTool();
					
				}
				
			});
			
		}
		
		mvn.subscribeActiveToolChanged(opt -> {
			
			if(opt.isPresent() == false) {
				setChecked(false);
				return;
			}
			
			AbstractMapViewTool theTool = opt.get();
			setChecked(theTool != null && theTool.getId().equals(toolID));
			
		});

	}
	
	private void bindTool() {
		
		AbstractMapViewTool tool2 =  ServiceManager.get(MapViewManager.class).getTools().get(toolID);
		if(tool2 != null) {
			
			tool2.subscribeUseable(evt -> updateCanExecute());
			updateCanExecute();
			
			if(subscription != null) {
				subscription.dispose();
			}
			
		}
		
	}
	
	private void updateCanExecute() {
		
		AbstractMapViewTool tool =  ServiceManager.get(MapViewManager.class).getTools().get(toolID);

		if(tool == null) {
			setCanExecute(false);
		} else {
			setCanExecute(tool.isUseable());
		}
		
	}

	@Override
	public void execute() {
		
		 ServiceManager.get(MapViewManager.class).setActiveTool(toolID);

	}

}
