package de.emir.rcp.menu.ep;

import java.util.ArrayList;
import java.util.List;

public class RadioGroup<T> extends MenuEntry implements IRadioGroup<T> {

    private String id;
    private String commandID;

    private List<RadioGroupElement<T>> elements = new ArrayList<>();

    protected RadioGroup(String id, String commandID) {
        this.id = id;
        this.commandID = commandID;
    }

    @Override
    public IRadioGroup<T> option(String label, T value) {
        return option(label, value, null);
    }

    @Override
    public IRadioGroup<T> option(String label, String tooltip, T value) {
        return option(label, value, null, tooltip);
    }

    @Override
    public IRadioGroup<T> option(String label, T value, String icon) {
        return option(label, value, icon, null);
    }

    @Override
    public IRadioGroup<T> option(String label, T value, String icon, String tooltip) {

        RadioGroupElement<T> element = new RadioGroupElement<>();
        element.label = label;
        element.value = value;
        element.icon = icon;
        element.tooltip = tooltip;
        elements.add(element);

        return this;
    }

    @Override
    public IRadioGroup<T> after(String siblingID) {

        if (siblingID.contains(".")) {
            throw new IllegalArgumentException("Menu paths must not contain a dot");
        }

        after = siblingID;
        before = null;
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.emir.rcp.menu.ep.IMenuItem#before(java.lang.String)
     */
    @Override
    public IRadioGroup<T> before(String siblingID) {

        if (siblingID.contains(".")) {
            throw new IllegalArgumentException("Menu paths must not contain a dot");
        }

        before = siblingID;
        after = null;
        return this;
    }

    public String getCommandID() {
        return commandID;
    }

    public String getId() {
        return id;
    }

    public List<RadioGroupElement<T>> getElements() {
        return elements;
    }

}
