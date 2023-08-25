package de.emir.rcp.views.ep;

import java.util.HashMap;
import java.util.Map;

public class GroupUtils {

    public static ViewDescriptor find(String viewID, ViewGroup group){
        if (group.getViews().containsKey(viewID)){
            return (ViewDescriptor) group.getViews().get(viewID);
        }

        for (IViewGroup sub : group.getSubGroups().values()){
            ViewDescriptor desc = find(viewID, (ViewGroup) sub);
            if (desc != null){
                return desc;
            }
        }

        return null;
    }

    public static Map<String, ViewDescriptor> convert(Map<String, ViewGroup> groups){
        Map<String, ViewDescriptor> converted = new HashMap<>();
        for (ViewGroup group : groups.values()){
           converted.putAll(convert(group));
        }

        return converted;
    }

    public static Map<String, ViewDescriptor> convert(ViewGroup group){
        Map<String, ViewDescriptor> converted = new HashMap<>();
        converted.putAll(group.getViews());
        converted.putAll(convert(group.getSubGroups()));

        return converted;
    }
}
