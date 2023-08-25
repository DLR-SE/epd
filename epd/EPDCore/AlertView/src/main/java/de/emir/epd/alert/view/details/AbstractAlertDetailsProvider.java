package de.emir.epd.alert.view.details;

import de.emir.epd.alert.manager.Alert;
import de.emir.epd.alert.manager.AlertManager;

import java.awt.*;

public abstract class AbstractAlertDetailsProvider {

    private String id;

    public AbstractAlertDetailsProvider(String id) {
        this.id = id;
    }

    public Alert getAlert() {
        return AlertManager.getByID(id);
    }

    public abstract Container create();

}
