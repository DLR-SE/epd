package de.emir.rcp.keybindings.ep;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.CommandManager;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;

public class CommandActionAdapter implements Action {

    private AbstractCommand cmd;

    public CommandActionAdapter(AbstractCommand cmd) {
        this.cmd = cmd;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        CommandManager cm = ServiceManager.get(CommandManager.class);
        cm.executeCommand(cmd);
    }

    @Override
    public Object getValue(String key) {
        // TODO
        return null;
    }

    @Override
    public void putValue(String key, Object value) {
        // TODO

    }

    @Override
    public void setEnabled(boolean b) {
        // TODO

    }

    @Override
    public boolean isEnabled() {
        return cmd.canExecute();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        // TODO

    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        // TODO

    }

}
