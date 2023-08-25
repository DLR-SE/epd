package de.emir.rcp.parts.vesseleditor.view.panels.transferable;

import de.emir.rcp.parts.vesseleditor.provider.ITransferableProvider;
import de.emir.rcp.parts.vesseleditor.provider.ReferencePointProvider;
import de.emir.rcp.parts.vesseleditor.view.parts.AbstractPhysicalObjectPart;
import de.emir.tuml.ucore.runtime.resources.IconManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class ReferencePointPanel extends AbstractTransferablePanel {

    public ReferencePointPanel(AbstractPhysicalObjectPart editor) {
        super(editor);

        setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Reference Point"));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        ITransferableProvider prov = new ReferencePointProvider();

        String label = prov.getLabel();
        URL iconURL = prov.getIconURL();
        Icon icon = null;
        if (iconURL != null) {
            icon = IconManager.getIcon(iconURL, IconManager.preferedMidIconSize());
        }
        JButton btn = icon != null ? new JButton(icon) : new JButton(label);
        btn.setToolTipText(prov.getToolTip());
        // enable drag & drop
        btn.setTransferHandler(new GenericExportHandler(prov));
        btn.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                btn.getTransferHandler().exportAsDrag(btn, e, TransferHandler.COPY);
            }
        });

        add(btn, gbc);

        gbc.gridy++;
        // place holder to avoid big gabs between general and pose
        JPanel empty = new JPanel();
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(empty, gbc);

        initListeners();
    }
}
