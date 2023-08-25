package de.emir.rcp.ui.widgets;

public interface IDetailsAreaProvider {

    /**
     * Implementations should return an ui displaying the given object
     * 
     * @param o
     * @return
     */
    public AbstractDetailsContentPanel<?> getDetailsPanel(Object o);

}
