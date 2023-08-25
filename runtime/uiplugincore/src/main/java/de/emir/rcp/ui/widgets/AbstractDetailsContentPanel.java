package de.emir.rcp.ui.widgets;

import java.awt.Component;

import javax.swing.Icon;

public abstract class AbstractDetailsContentPanel<T> {

    private T object;

    public AbstractDetailsContentPanel(T o) {
        this.object = o;
    }

    /**
     * @return The object represented by this content panel
     */
    protected T getObject() {
        return object;
    }

    /**
     * @return The title of this panel
     */
    public abstract String getTitle();

    /**
     * @return The icon of this panel
     */
    public abstract Icon getIcon();

    /**
     * Create the ui contents of this panel.
     * 
     * @return The root element of the ui structure
     */
    public abstract Component createContents();

    /**
     * Fired when this panel is shown
     */
    public abstract void onOpen();

    /**
     * Fired when this panel will be hidden
     */
    public abstract void onClose();

}
