package de.emir.rcp.ui.widgets;

public interface IFilterMatcher {

    /**
     * 
     * @param o
     *            The element to check
     * @param filter
     *            A filter string
     * @return
     */
    public boolean matches(Object o, String filter);

}
