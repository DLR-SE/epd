package de.emir.rcp.views;

import de.emir.rcp.ui.widgets.IFilterMatcher;
import de.emir.rcp.views.ep.ViewDescriptor;
import de.emir.rcp.views.ep.ViewGroup;

public class OpenViewFilterMatcher implements IFilterMatcher {

    @Override
    public boolean matches(Object o, String filter) {
        /*if (o instanceof ViewGroup){
            ViewGroup view = (ViewGroup) o;
            return view.getLabel().toLowerCase().contains(filter.toLowerCase());
        }else */if (o instanceof ViewDescriptor) {
            ViewDescriptor view = (ViewDescriptor) o;
            return view.getLabel().toLowerCase().contains(filter.toLowerCase());
        }
        return false;
    }

}
