package de.emir.rcp.commands;

import java.util.Observable;

/**
 * Commands are used to execute logic initiated by the user. By encapsulating this logic, it is possible to link both
 * menu entries and key bindings to the same execution. However, a command can also be executed programmatically. See
 * CommandManager.executeCommand(...)
 * 
 * 
 * @author fklein
 */
public abstract class AbstractCommand extends Observable {

    private boolean canExecute = true;
    private boolean isVisible = true;

    /**
     * @param canExecute
     */
    public final void setCanExecute(boolean canExecute) {

        if (this.canExecute == canExecute) {
            return;
        }

        this.canExecute = canExecute;
        setChanged();
        notifyObservers(canExecute);
    }

    /**
     * @return
     */
    public final boolean canExecute() {
        return canExecute;
    }

    /**
     * @param isVisible
     */
    public final void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
        setChanged();
        notifyObservers(isVisible);

    }

    /**
     * @return
     */
    public final boolean isVisible() {
        return isVisible;
    }

    /**
     * 
     */
    public abstract void execute();

}
