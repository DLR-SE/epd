package de.emir.epd.alert.manager;

import de.emir.tuml.ucore.runtime.resources.ResourceManager;

import javax.swing.*;

public enum AlertState {
    OK("/icons/emiricons/32/gps_fixed.png", "Ok"),
    PARTIAL("/icons/emiricons/32/gps_not_fixed.png", "Partially Ok"),
    ERROR("/icons/emiricons/32/gps_off.png", "Error"),
    UNKNOWN("/icons/emiricons/32/sync_problem.png", "Unknown");


    private final String iconPath;
    private final String label;

    AlertState(String iconPath, String label) {
        this.iconPath = iconPath;
        this.label = label;
    }

    public Icon getIcon() {
        return ResourceManager.get(AlertState.class).getImageIcon(iconPath);
    }

    public String getLabel() {
        return label;
    }
}
