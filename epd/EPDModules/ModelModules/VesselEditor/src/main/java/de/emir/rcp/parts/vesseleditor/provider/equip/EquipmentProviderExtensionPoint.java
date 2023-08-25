package de.emir.rcp.parts.vesseleditor.provider.equip;

import de.emir.model.universal.physics.LocatableObject;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;

import java.util.*;

public class EquipmentProviderExtensionPoint implements IExtensionPoint {
    private HashMap<String, ArrayList<IEquipmentProvider>> sEquipmentProvider = new HashMap<>();

    public EquipmentProviderExtensionPoint() {
    }

    public void registerEquipmentProvider(String group, IEquipmentProvider provider) {
        if (provider == null || group == null || group.isEmpty())
            return;
        if (sEquipmentProvider.containsKey(group) == false)
            sEquipmentProvider.put(group, new ArrayList<>());
        if (sEquipmentProvider.get(group).contains(provider) == false)
            sEquipmentProvider.get(group).add(provider);
    }

    public void removeEquipmentProvider(String group, IEquipmentProvider provider) {
        if (group == null || sEquipmentProvider.containsKey(group) == false)
            return;
        if (provider != null)
            sEquipmentProvider.get(group).remove(provider);
    }

    public Map<String, ArrayList<IEquipmentProvider>> getEquipmentProvider() {
        return Collections.unmodifiableMap(sEquipmentProvider);
    }

    public ArrayList<EquipmentData> getEquipmentOf(PhysicalObject pobj) {
        HashSet<LocatableObject> equipment = new HashSet<>();
        ArrayList<EquipmentData> out = new ArrayList<>();
        for (String g : sEquipmentProvider.keySet()) {
            for (IEquipmentProvider p : sEquipmentProvider.get(g)) {
                Collection<LocatableObject> tmp = p.collectExistingEquipment(pobj);
                if (tmp != null)
                    for (LocatableObject eq : tmp)
                        if (equipment.add(eq)) { //only if we do not already have this equipment
                            out.add(new EquipmentData(p, eq));
                        }
            }
        }
        //do we need some sorted collection?
        return out;
    }

    public static class EquipmentData {
        public final IEquipmentProvider provider;
        public final LocatableObject equipment;

        EquipmentData(IEquipmentProvider p, LocatableObject e) {
            provider = p;
            equipment = e;
        }
    }
}
