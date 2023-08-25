package de.emir.rcp.parts.vesseleditor.view.parts;

import de.emir.model.domain.maritime.vessel.HeadingCommand;
import de.emir.model.domain.maritime.vessel.VesselPackage;
import de.emir.rcp.util.WidgetUtils;
import de.emir.tuml.ucore.runtime.pointer.PointerOperations;
import de.emir.tuml.ucore.runtime.utils.Pointer;

import javax.swing.*;
import java.awt.*;

public class HeadingCommandPart extends JPanel {

    private HeadingCommand mCommand;

    public HeadingCommandPart(HeadingCommand value) {
        mCommand = value;
        setLayout(new GridBagLayout());
        GridBagConstraints gcL = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0);
        GridBagConstraints gcE = new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);

        Pointer headingPtr = PointerOperations.create(mCommand, VesselPackage.Literals.HeadingCommand_heading);
        Pointer creationTimePtr = PointerOperations.create(mCommand, VesselPackage.Literals.CommandedValue_creationTime);

        WidgetUtils.addEditor(this, "Heading", gcL, headingPtr, gcE);
        WidgetUtils.addEditor(this, "Creation Time", gcL, creationTimePtr, gcE);
    }
}
