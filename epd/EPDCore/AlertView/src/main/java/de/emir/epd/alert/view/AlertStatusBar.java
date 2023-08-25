package de.emir.epd.alert.view;

import de.emir.epd.alert.manager.Alert;
import de.emir.epd.alert.manager.AlertManager;
import de.emir.rcp.statusbar.AbstractStatusBarElement;
import de.emir.rcp.views.AbstractView;
import io.reactivex.rxjava3.disposables.Disposable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AlertStatusBar extends AbstractStatusBarElement {
    private AlertComponent alertComponent;

    @Override
    public Component createContents() {
        alertComponent = new AlertComponent();
        AlertManager.subscribeAlertChange(propertyChangeEvent -> {
            Alert newAlert = (Alert) propertyChangeEvent.getNewValue();
            if (newAlert != null) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        alertComponent.changeIcon(newAlert.getId(),
                                newAlert.getState().getIcon());
                    }
                });
            }
        });

        AlertManager.subscribeVisibleAlerts(propertyChangeEvent -> {
                    List<Alert> alerts = AlertManager.getVisibleAlerts();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            alertComponent.removeAllAlerts();
                            alertComponent
                                    .addAlert(alerts.toArray(new Alert[0]));
                            alertComponent.revalidate();
                            alertComponent.repaint();
                        }
                    });

                });
        return alertComponent;
    }

    private class AlertComponent extends JPanel {
        private List<AlertPanel> alertPanelList;

        public AlertComponent() {
            super();
            alertPanelList = new ArrayList<>();
            FlowLayout layout = new FlowLayout();
            this.setLayout(layout);
        }

        private void removeAllAlerts() {

            for (AlertPanel component : alertPanelList) {
                this.remove(component);
            }

            this.alertPanelList.clear();
        }

        public void addAlert(Alert... alerts) {
            for (Alert alert : alerts) {
                AlertPanel panel = new AlertPanel(alert.getId(),
                        alert.getState().getIcon(), alert.getText());
                alertPanelList.add(panel);
                this.add(panel);
            }
        }

        public AlertPanel getByID(String id) {
            for (AlertPanel alertPanel : alertPanelList) {
                if (alertPanel.getId().equals(id)) {
                    return alertPanel;
                }
            }

            return null;
        }

        public void changeIcon(String id, Icon icon) {
            for (AlertPanel panel : alertPanelList) {
                if (panel.getId().equals(id)) {
                    panel.changeIcon(icon);
                    break;
                }
            }
        }
    }
}
