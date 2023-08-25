package de.emir.rcp.commands;

/**
 * 
 * @author fklein This is an abstract command that is used for radio groups. It stores a value of type <T> The execute
 *         method is replaced by an extended version, taking the radio group selection into account See type hierarchy
 *         for an example.
 * 
 */
public abstract class AbstractRadioGroupCommand<T> extends AbstractCommand {

    private T value;

    @Override
    public final void execute() {
    }

    public void setValue(T value) {
        this.value = value;
        setChanged();
        notifyObservers(value);
    }

    public T getValue() {
        return value;
    }

    public abstract void execute(T userObject);

    public abstract boolean isSelected(T value);

}
