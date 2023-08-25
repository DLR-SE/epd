package de.emir.rcp.other.cmd;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import de.emir.rcp.commands.AbstractCommand;

public class ShowNavigationTool extends AbstractCommand {

	private Action mAction;

	public ShowNavigationTool(Action action) {
		mAction = action;
	}

	@Override
	public void execute() {
		mAction.actionPerformed(new ActionEvent(this, 0, "execute"));
	}

}
