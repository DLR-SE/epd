package de.emir.rcp.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.manager.util.PlatformUtil;

/**
 * @author Florian
 * 
 *         An ActionListener executing a command. Can be used to bind a command to a simple UI Component
 *
 */
public class ExecuteCommandAction implements ActionListener {

    private String commandID;

    public ExecuteCommandAction(String commandID) {
        this.commandID = commandID;
    }

    public ExecuteCommandAction(ICommandDescriptor cd) {
        this.commandID = cd.getId();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        PlatformUtil.getCommandManager().executeCommand(commandID);

    }

}
