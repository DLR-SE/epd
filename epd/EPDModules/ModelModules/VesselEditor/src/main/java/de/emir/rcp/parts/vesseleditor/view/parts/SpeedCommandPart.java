package de.emir.rcp.parts.vesseleditor.view.parts;

import de.emir.model.domain.maritime.vessel.SpeedCommand;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.rcp.util.WidgetUtils;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

import javax.swing.*;
import java.awt.*;

public class SpeedCommandPart extends JPanel {

    private SpeedCommand mCommand;

    public SpeedCommandPart(SpeedCommand value) {
        mCommand = value;
        setLayout(new GridBagLayout());
        GridBagConstraints gcL = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0);
        GridBagConstraints gcE = new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);

        Pointer speedPtr = PointerOperations.create(mCommand, VesselPackage.Literals.SpeedCommand_speed);
        Pointer creationTimePtr = PointerOperations.create(mCommand, VesselPackage.Literals.CommandedValue_creationTime);

        WidgetUtils.addEditor(this, "Speed", gcL, speedPtr, gcE);
        WidgetUtils.addEditor(this, "Creation Time", gcL, creationTimePtr, gcE);
    }
}
