package de.emir.rcp.manager.util;

import java.util.Map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The InstanceManager handles generation of view names based on auto-incrementing numbers for views which are
 * reopenable. For each ViewDescriptor ID, it generates descriptorID+_InstanceX, for example AISViewer_Instance1,
 * AISViewer_Instance2 etc.
 */
public class InstanceManager {
    private final Map<String, Set<Integer>> assigned = new ConcurrentHashMap<>();

    /**
     * Loads existing unique IDs of views to the model.
     * @param uniqueID Unique ID of the view to load.
     * @throws IllegalArgumentException If the unique ID is not in the Format XX_InstanceX.
     * @return the last assigned id
     */
    public void loadExistingName(String uniqueID) {
        String[] parts = uniqueID.split("_Instance");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid name format: " + uniqueID);
        }
        String descriptorID = parts[0];
        int number = Integer.parseInt(parts[1]);
        if(!assigned.containsKey(descriptorID)) {
            assigned.put(descriptorID, new HashSet<>());
        }
        assigned.get(descriptorID).add(number);
    }

    /**
     * Creates a unique ID based on the number of instances and the descriptor ID.
     * @param descriptorID ID of the ViewDescriptor for the view to create.
     * @return Unique identifier in the format descriptorID+_InstanceX.
     * @implNote This method checks whether instances with the same name exists and fills gaps in the increment number.
     * For example if _Instance2 was deleted, it is added again as number 2 is missing from the sequence.
     */
    public String create(String descriptorID) {
        int number = 1;
        synchronized (assigned) {
            if(!assigned.containsKey(descriptorID)) {
                assigned.put(descriptorID, new HashSet<>());
            }
            if(assigned.get(descriptorID).contains(number)) {
                while (assigned.get(descriptorID).contains(number)) {
                    number++;
                }
            }
            return descriptorID + "_Instance"+number;
        }
    }

    /**
     * Deletes a uniqueID from the model.
     * @param uniqueID UniqueID to remove from the model.
     */
    public void delete(String uniqueID) {
        synchronized (assigned) {
            String[] parts = uniqueID.split("_Instance");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid name format: " + uniqueID);
            }
            String descriptorID = parts[0];
            int number = Integer.parseInt(parts[1]);
            if(assigned.containsKey(descriptorID)) {
                assigned.get(descriptorID).remove(number);
            }
        }
    }
}


