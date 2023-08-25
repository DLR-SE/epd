package de.emir.rcp.commands;

/**
 * 
 * @author fklein This is an abstract command that has a checked state in addition to the basic functionality of a
 *         command. Within the execute method, the state can be accessed via isChecked (). Via setChecked (...) the
 *         state can be set. Attention: The isChecked () state is not set automatically by a menu item. It must always
 *         be adjusted manually within the Execute method. Only then the button state is updated.
 */
public abstract class AbstractCheckableCommand extends AbstractCommand {

    private boolean checked = false;

    /**
     * Returns checked state
     * 
     * @return
     */
    public final boolean isChecked() {
        return checked;
    }

    /**
     * Sets the checked state of the commands. Coupled menu entries are automatically notified.
     * 
     * @param checked
     */
    public final void setChecked(boolean checked) {
        if (this.checked == checked)
            return;
        this.checked = checked;
        setChanged();
        notifyObservers(checked);
    }

}
