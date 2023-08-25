package de.emir.rcp.parts.vesseleditor.view.panels.transferable;

import de.emir.rcp.parts.vesseleditor.provider.equip.EquipmentProviderExtensionPoint;
import de.emir.rcp.parts.vesseleditor.provider.equip.IEquipmentProvider;
import de.emir.rcp.parts.vesseleditor.view.parts.AbstractPhysicalObjectPart;
import de.emir.tuml.ucore.runtime.extension.ExtensionPointManager;
import de.emir.tuml.ucore.runtime.resources.IconManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class EquipmentPanel extends AbstractTransferablePanel {

    public EquipmentPanel(AbstractPhysicalObjectPart editor) {
        super(editor);

        setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Equipment"));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        EquipmentProviderExtensionPoint ep = ExtensionPointManager
                .getExtensionPoint(EquipmentProviderExtensionPoint.class);
        Map<String, ArrayList<IEquipmentProvider>> providers = ep.getEquipmentProvider();
        ArrayList<String> keys = new ArrayList<>(providers.keySet());
        Collections.sort(keys);
        for (String k : keys) {
            ArrayList<IEquipmentProvider> equipList = providers.get(k);
            if (equipList != null && equipList.isEmpty() == false) {
                JPanel group = new JPanel();
                group.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), k));
                group.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                for (IEquipmentProvider prov : equipList) {
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

                    group.add(btn);
                }

                add(group, gbc);
                gbc.gridy++;
            }
        }
        // placeholder to avoid big gabs between general and pose
        JPanel empty = new JPanel();
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(empty, gbc);

        initListeners();
    }

}
