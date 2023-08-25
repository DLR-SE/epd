package de.emir.epd.alert.view;

import de.emir.epd.alert.manager.AlertManager;
import de.emir.epd.alert.view.details.AbstractAlertDetailsProvider;
import de.emir.rcp.manager.util.PlatformUtil;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlertPanel extends JPanel {
    private String id;
    private JLabel iconLabel;

    public AlertPanel(String id, Icon icon, String label) {
        this.id = id;
        this.iconLabel = new JLabel(icon);
        this.iconLabel.setText(label);
        this.add(this.iconLabel);

        this.iconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    AbstractAlertDetailsProvider alertDialogAdapter = AlertManager.getViewFor(id);
                    if (alertDialogAdapter != null) {
                        JDialog dialog = new JDialog(PlatformUtil.getWindowManager().getMainWindow());
                        dialog.setSize(300, 200);
                        dialog.setContentPane(alertDialogAdapter.create());
                        dialog.setVisible(true);
                    }
                }
                super.mouseClicked(e);
            }
        });
    }

    public void changeIcon(Icon icon) {
        this.iconLabel.setIcon(icon);
    }

    public void changeText(String text) {
        this.iconLabel.setText(text);
    }

    public String getId() {
        return id;
    }

    public JLabel getIconLabel() {
        return iconLabel;
    }
}