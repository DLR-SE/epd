package de.emir.epd.routemanager.impl.model;

import de.emir.epd.routemanager.IRouteManager.IRouteAccessModel;
import de.emir.model.domain.maritime.iec61174.Route;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.domain.maritime.vessel.VoyageCharacteristic;
import de.emir.model.universal.physics.ICharacteristic;
import de.emir.model.universal.physics.PhysicsPackage;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VesselBasedRouteModel implements IRouteAccessModel, IValueChangeListener {

    private BehaviorSubject<PropertyChangeEvent> mStructureSubscription = BehaviorSubject.create();

    private Vessel mVessel;
    private ArrayList<VoyageBasedRouteModel> mChildren = new ArrayList<>();

    public VesselBasedRouteModel(Vessel parent) {
        mVessel = parent;
        List<ICharacteristic> voyages = mVessel.getAllCharacteristics(UCoreMetaRepository.getClassifier(VoyageCharacteristic.class), true);
        for (ICharacteristic vc : voyages) {
            addCharacteristic((VoyageCharacteristic) vc);
        }
        mVessel.registerListener(PhysicsPackage.Literals.PhysicalObject_characteristics, this);
    }

    @Override
    public void dispose() {
        mVessel.removeListener(this);
    }

    private void addCharacteristic(VoyageCharacteristic vc) {
        for (VoyageBasedRouteModel vbrm : mChildren) {
            if (vbrm.getVoyage() == vc)
                return; //already there
        }
        VoyageBasedRouteModel vbrm = new VoyageBasedRouteModel(vc);
        mChildren.add(vbrm);
        try {
            mStructureSubscription.onNext(new PropertyChangeEvent(this, "Voyage", null, vbrm));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "Vessel: " + mVessel.getNameAsString();
    }

    @Override
    public String getDescription() {
        return "Vessel";
    }

    @Override
    public List<IRouteAccessModel> getChildren() {
        return Collections.unmodifiableList(mChildren);
    }

    @Override
    public List<Route> getOwnedRoutes() {
        return null;
    }

    @Override
    public List<Route> getAllRoutes() {
        ArrayList<Route> all = new ArrayList<>();
        for (IRouteAccessModel c : getChildren()) {
            List<Route> tmp = c.getAllRoutes();
            if (tmp != null) all.addAll(tmp);
        }
        return all;
    }

    @Override
    public Disposable subscribeStructureChanges(Consumer<PropertyChangeEvent> consumer) {
        return mStructureSubscription.subscribe(consumer);
    }

    @Override
    public void onValueChange(Notification notification) {
        Object ov = notification.getOldValue();
        Object nv = notification.getNewValue();
        if ((ov != null && ov instanceof VoyageCharacteristic) || (nv != null && nv instanceof VoyageCharacteristic))
            mStructureSubscription.onNext(new PropertyChangeEvent(mVessel, "Characteristics", ov, nv));
    }

    @Override
    public boolean containsRoute(Route route) {
        for (IRouteAccessModel c : getChildren()) {
            if (c.containsRoute(route))
                return true;
        }
        return false;
    }

    @Override
    public void assignRoute(Route route) {
        if (getChildren() != null && getChildren().isEmpty() == false)
            getChildren().get(0).assignRoute(route);
    }

    @Override
    public void deleteRoute(Route route) {
        for (IRouteAccessModel c : getChildren())
            c.deleteRoute(route);
    }

}