package de.emir.rcp.views.targets;

import de.emir.epd.mapview.views.map.MapView;
import de.emir.epd.model.EPDModel;
import de.emir.epd.model.EPDModelUtils;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.views.AbstractView;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.utils.UCoreUtils;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * View wrapper for the target table. This table displays PhysicalObjects stored in the UCore model and their
 * corresponding values.
 */
public class TargetTableView extends AbstractView implements ITreeValueChangeListener, IValueChangeListener<PhysicalObject> {
    private TargetTablePanel panel = new TargetTablePanel();
    public static final String UNIQUE_ID = "Target Table";
    protected UObject currentRoot;

    /**
     * Creates a new TargetTableView instance.
     */
    public TargetTableView() {
    	this(UNIQUE_ID);
    }
    
    /**
     * Creates a new TargetTableView instance with given id.
     */
    public TargetTableView(String id) {
        super(id);
        AbstractModelProvider modelProvider = PlatformUtil.getModelManager().getModelProvider();
        if (modelProvider != null) {
            modelProvider.subscribeModel(sub -> rebuildModel());
        }
        panel.addGoToListener(o -> {
            if (o != null && o.getPose() != null && o.getPose().getCoordinate() != null) {
                MapView mapView = PlatformUtil.getViewManager().getView(MapView.class);
                if (mapView != null) {
                    mapView.centerOnCoordinate(o.getPose().getCoordinate());
                }
            }
        });
        rebuildModel();
    }

    /**
     * Rebuilds the views model, and re-registers all listeners. This should be called when the underlying
     * model has been swapped.
     */
    private void rebuildModel() {
        if (PlatformUtil.getModelManager().getModelProvider() != null) {
            Object content = PlatformUtil.getModelManager().getModelProvider().getModel();
            UObject root;
            if (content instanceof UObject uo) {
                root = uo;
                currentRoot = root;
            } else if (content instanceof EPDModel) {
                EPDModelUtils.subscribeModelChange("ownship", e -> {
                    // TODO currently only works using EPDModelUtils. Find a UCore compliant way to get notified on ownship changes.
                    if (e.getNewValue() instanceof Vessel v) this.panel.setReference(v);
                    this.reloadTargets();
                });
                root = EPDModelUtils.retrieveObjectLayer(EPDModelUtils.getDefaultEnvironment());
                if (currentRoot != root) {
                    if (currentRoot != null) currentRoot.removeTreeListener(this);
                    root.registerTreeListener(this);
                    currentRoot = root;
                }
            }
            reloadTargets();
        }
    }

    /**
     * Reloads all targets. This removes all current targets from the table and inserts all found PhysicalObjects in Ucore.
     */
    public void reloadTargets() {
        if (currentRoot != null) {
            Set<PhysicalObject> trackedTargetSet = new HashSet<>(UCoreUtils.collectTypedChildren(currentRoot, PhysicalObject.class));
            this.panel.clearTargets();
            for (PhysicalObject object : trackedTargetSet) {
                this.panel.addTarget(object);
            }
        }
    }

    /**
     * Fires when a model value was changed.
     * @param notification Notification to process.
     */
    @Override
    public void onValueChange(Notification notification) {
        // Here we filter for changes on the PhysicalObject directly.
        if (notification.getNewValue() instanceof PhysicalObject po) {
            switch (notification.getType()) {
                case ADD, SET -> {
                    this.panel.addTarget(po);
                }
                case REMOVE -> {
                    this.panel.removeTarget(po);
                }
            }
        }
    }


    /**
     * Populates the content of the view.
     *
     * @return Content of the view which should be displayed.
     */
    @Override
    public Component createContent() {
        return panel;
    }

    /**
     * OnOpen handler which is called when the view is opened.
     */
    @Override
    public void onOpen() {

    }

    /**
     * OnClose handler which is called when the view is closed.
     */
    @Override
    public void onClose() {

    }
}
