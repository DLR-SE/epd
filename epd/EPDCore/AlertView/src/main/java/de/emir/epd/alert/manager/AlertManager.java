package de.emir.epd.alert.manager;

import de.emir.epd.alert.view.details.AbstractAlertDetailsProvider;
import de.emir.epd.alert.view.details.DefaultDetailsProvider;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class AlertManager {
    private static List<Alert> alertList = new ArrayList<>();
    private static List<Alert> visibleAlerts = new ArrayList<>();
    private static List<AbstractAlertDetailsProvider> dialogAdapters = new ArrayList<>();

    private static BehaviorSubject<PropertyChangeEvent> visibleAlertSubject = BehaviorSubject.create();
    private static BehaviorSubject<PropertyChangeEvent> alertChangeSubject = BehaviorSubject.create();

    public static void registerAlert(Alert alert) {
        alertList.add(alert);
    }

    public static void registerAdapter(AbstractAlertDetailsProvider alertDialogAdapter) {
        dialogAdapters.add(alertDialogAdapter);
    }

    public static void setState(String id, AlertState state) {
        boolean fire = false;
        Alert current = getByID(id, alertList);

        if (current != null){
            current.updateTime();
        }

        if (current != null && (current.getState().equals(state) == false)) {
            current.setState(state);
            fire = true;
        }

        current = getByID(id, visibleAlerts);

        if (current != null){
            current.updateTime();
        }

        if (current != null && (current.getState().equals(state) == false)) {
            current.setState(state);
            fire = true;
        }

        if (fire){
            visibleAlertSubject.onNext(new PropertyChangeEvent(AlertManager.class, "visibility", null, current));
        }
    }

    public static void setVisible(String id, boolean value) {
        Alert alert = null;
        if (value) {
            alert = getByID(id, alertList);
            if (alert != null) {
                if (visibleAlerts.contains(alert) == false) {
                    visibleAlerts.add(alert);
                    visibleAlertSubject.onNext(new PropertyChangeEvent(AlertManager.class, "visibility", null, alert));
                }
            }
        } else {
            alert = getByID(id, visibleAlerts);
            if (alert != null) {
                visibleAlerts.remove(alert);
                visibleAlertSubject.onNext(new PropertyChangeEvent(AlertManager.class, "visibility", null, alert));
            }
        }
    }

    public static Disposable subscribeVisibleAlerts(Consumer<PropertyChangeEvent> consumer) {
        return visibleAlertSubject.subscribe(consumer);
    }

    public static Disposable subscribeAlertChange(Consumer<PropertyChangeEvent> consumer) {
        return alertChangeSubject.subscribe(consumer);
    }

    public static List<Alert> getVisibleAlerts() {
        return new ArrayList<>(visibleAlerts);
    }

    public static List<Alert> getAlertList() {
        return alertList;
    }

    public static AbstractAlertDetailsProvider getViewFor(String id) {
        for (AbstractAlertDetailsProvider alertDialogAdapter : dialogAdapters) {
            if (alertDialogAdapter.getAlert().getId().equals(id)) {
                return alertDialogAdapter;
            }
        }

        Alert alert = getByID(id, visibleAlerts);
        if (alert != null) {
            return new DefaultDetailsProvider(alert.getId());
        } else {
            return null;
        }

    }

    private static Alert getByID(String id, List<Alert> alerts) {
        for (Alert alert : alerts) {
            if (alert.getId().equals(id)) {
                return alert;
            }
        }
        return null;
    }

    public static Alert getByID(String id) {
        return getByID(id, alertList);
    }

    public static boolean isVisible(String id) {
        for (Alert alert : visibleAlerts) {
            if (alert.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
