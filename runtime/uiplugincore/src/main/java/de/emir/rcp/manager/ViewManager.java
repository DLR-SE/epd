package de.emir.rcp.manager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import bibliothek.gui.dock.common.MultipleCDockable;
import bibliothek.gui.dock.common.MultipleCDockableFactory;
import de.emir.rcp.manager.util.InstanceManager;
import de.emir.rcp.views.AbstractViewFactory;
import de.emir.rcp.views.ep.*;
import org.apache.logging.log4j.Logger;

import bibliothek.gui.dock.common.SingleCDockable;
import bibliothek.gui.dock.common.SingleCDockableFactory;
import bibliothek.gui.dock.common.event.CDockableStateListener;
import bibliothek.gui.dock.common.intern.CDockable;
import bibliothek.gui.dock.common.mode.ExtendedMode;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.AbstractView;
import de.emir.tuml.ucore.runtime.extension.IService;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * Manages all registered views and contains utility methods to access views.
 */
public class ViewManager implements IService {

    private static final Logger log = ULog.getLogger(ViewManager.class);

    private final ViewExtensionPoint viewEP = new ViewExtensionPoint();

    private final Map<String, AbstractView> visibleViews = new ConcurrentHashMap<>();

    // This flag should be set to true if loading the layout from XML is finished. As long as it is set to false,
    // making the views visible when using the createView method is not possible. This is to prevent the ViewManager
    // and CControl from both registering dockables to the Control which is not allowed. Only after loading of the
    // layout is complete, newly added views can be added to CControl. This is done by the MainWindow by calling
    // makeViewsVisible and setLoadComplete.
    private boolean loadComplete;
    private final InstanceManager instanceManager = new InstanceManager();

    /**
     * Gets the ViewExtensionPoint used for managing views.
     *
     * @return ViewExtensionPoint.
     */
    public ViewExtensionPoint getViewExtensionPoint() {
        return viewEP;
    }

    /**
     * Gets all currently visible/active views.
     *
     * @return Map of all visible/active views and their corresponding ID.
     */
    public Map<String, AbstractView> getActiveViews() {
        return visibleViews;
    }

    /**
     * Registers the default factory used for instantiating views to the main CControl. This method should be called before
     * loading the layout from XML since all AbstractViews use this factory.
     */
    public void fillViews() {
        PlatformUtil.getWindowManager().getMainWindow().getMainControl().addMultipleDockableFactory("AbstractViewFactory", new AbstractViewFactory());
    }

    /**
     * Creates a new instance of a view based on the ViewDescriptor.
     *
     * @param epView ViewDescriptor to load view information from.
     * @return Created instance of the view.
     * @implNote If the view is not reopenable, the ID of the ViewDescriptor is assigned to the view. If the view is reopenable,
     * * each view is assigned the ViewDescriptor ID + _InstanceX with X being an incremental value. For example,
     * * when registering a view AISView which is reopenable, the view instances are named AISView_Instance1, AISView_Instance2 etc.
     */
    public AbstractView createView(ViewDescriptor epView) {
        return createView(epView, "");
    }

    /**
     * Creates a new instance of a view based on the ViewDescriptor.
     *
     * @param epView   ViewDescriptor to load view information from.
     * @param uniqueID Unique ID of the view. This differs from the ViewDescriptor ID as it only specifies the ID and not
     *                 the current instance. This is only reflected in the uniqueID and is used for loading
     *                 elements from XML.
     * @return Created instance of the view.
     * @implNote If the view is not reopenable, the ID of the ViewDescriptor is assigned to the view. If the view is reopenable,
     * each view is assigned the ViewDescriptor ID + _InstanceX with X being an incremental value. For example,
     * when registering a view AISView which is reopenable, the view instances are named AISView_Instance1, AISView_Instance2 etc.
     */
    public AbstractView createView(ViewDescriptor epView, String uniqueID) {
        Class<? extends AbstractView> viewClass = epView.getViewClass();
        String viewId = epView.getId();
        AbstractView view = null;
        String instanceId = null;

        try {
            Constructor<? extends AbstractView> constructor = viewClass.getConstructor(String.class);
            if (epView.isReopenable()) {
                if (uniqueID.isEmpty()) {
                    view = constructor.newInstance(instanceManager.create(epView.getId()));
                } else {
                    instanceManager.loadExistingName(uniqueID);
                    view = constructor.newInstance(uniqueID);
                }
                String[] parts = view.getUniqueId().split("_Instance");
        		if (parts.length == 2) {
        			instanceId = parts[1];
        		}
            } else {
                view = constructor.newInstance(viewId);
            }
        } catch (NoSuchMethodException | SecurityException e) {
            try {
                view = viewClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e1) {
                log.error(String.format("Can't create view with id [%s].", viewId), e1);
            }

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                 | InvocationTargetException e) {
            log.error(String.format("Can't create view with id [%s].", viewId), e);
        }

        if (view != null) {
            log.debug("Created view with id [{}]", viewId);

            view.setExternalizable(epView.isExternalizable());
            view.setMinimizable(epView.isMinimizable());
            view.setMaximizable(epView.isMaximizable());
            view.setCloseable(epView.isCloseable());
    		if (instanceId != null) {
   				view.setTitleText(epView.getLabel() + "[" + instanceId + "]");
    		}
    		else {
    			view.setTitleText(epView.getLabel());
    		}

            ImageIcon icon = epView.getIcon();
            if (icon != null) {
                view.setTitleIcon(icon);
            }

            JPanel parent = view.getParentPanel();

            try {
                Component content = view.createContent();
                parent.add(content, BorderLayout.CENTER);
                if (loadComplete) {
                    addVisibleViewToMap(view);
                }
            } catch (Exception | Error e) {
                log.warn("Failed to create content for {}:{}.", view.getUniqueId(), e.getMessage());
            }
        }
        return view;
    }

    /**
     * Sets the load complete flag. If set to true, views created using the createView method will automatically be
     * registered to the Dockable management and set to be visible. During initialization before loading the XML layout,
     * this flag is set to false to prevent multiple calls to addDockable on the main CControl which causes exceptions.
     * After finish loading the layout, this flag needs to be set to true.
     *
     * @param complete True if views should be made visible during creation.
     */
    public void setLoadComplete(boolean complete) {
        this.loadComplete = complete;
    }

    /**
     * Calls the addVisibleViewToMap method for all registered views. This is only called during loading of the layout
     * to initially load all views which were parsed from the layout XML.
     */
    public void makeViewsVisible() {
        for (CDockable dockable : PlatformUtil.getWindowManager().getMainWindow().getMainControl().getRegister().getDockables()) {
            if (dockable instanceof AbstractView av) {
                addVisibleViewToMap(av);
            }
        }
    }

    /**
     * Registers a view to the main CControl if not done already, adds it to the data structure and registers listeners
     * for object managemenet of the CDockables. Afterwards, the view is set to visible and will be displayed in the
     * main CControl.
     *
     * @param view View to make visible and add to data structure.
     */
    private void addVisibleViewToMap(AbstractView view) {
        if (!PlatformUtil.getWindowManager().getMainWindow().getMainControl().getRegister().getDockables().contains(view)) {
            PlatformUtil.getWindowManager().getMainWindow().getMainControl().addDockable(view);
        }
        // Loads view to the data structure.
        visibleViews.put(view.getUniqueId(), view);
        if(getViewDescriptor(view.getUniqueId()) != null && getViewDescriptor(view.getUniqueId()).isReopenable()) {
            instanceManager.loadExistingName(view.getUniqueId());
        }


        view.addCDockableStateListener(new CDockableStateListener() {
            /**
             * Registers listeners for view removal and adding based on the visibility property.
             * @param dockable the source of the event
             */
            @Override
            public void visibilityChanged(CDockable dockable) {
                if (dockable.isVisible()) {
                    visibleViews.put(view.getUniqueId(), view);
                } else {
                    if(getViewDescriptor(view.getUniqueId()) != null && getViewDescriptor(view.getUniqueId()).isReopenable()) {
                        instanceManager.delete(view.getUniqueId());
                    }
                    visibleViews.remove(view.getUniqueId());
                    PlatformUtil.getWindowManager().getMainWindow().getMainControl().removeDockable(view);
                }
            }

            @Override
            public void extendedModeChanged(CDockable dockable, ExtendedMode mode) {
            }
        });
        view.setVisible(true);
    }

    /**
     * Returns information about a view. Attention: The view instance is not returned here, but the same data structure
     * that is used in the ViewExtensionPoint.
     *
     * @param viewID unique identifier of the ViewDescriptor.
     * @return Descriptor which corresponds to the ViewDescriptor ID. If not found, null is returned.
     */
    public ViewDescriptor getViewDescriptor(String viewID) {
        Map<String, ViewGroup> viewsFromEP = viewEP.getViewDescriptors();
        if(viewID.contains("_Instance")) {
            viewID = viewID.split("_Instance")[0];
        }

        for (ViewGroup group : viewsFromEP.values()) {
            ViewDescriptor descriptor = GroupUtils.find(viewID, group);
            if (descriptor != null) {
                return descriptor;
            }
        }
        return null;
    }

    /**
     * Sets the visibility of a view to true
     *
     * @param id ID of the ViewDescriptor for which a view should be loaded and set to visible.
     */
    public void openView(String id) {
        ViewDescriptor viewData = getViewDescriptor(id);
        if (viewData == null) {
            log.error("Tried to open view with id [{}] but it cannot be found.", id);
            return;
        }
        if (isViewVisible(viewData)) {
            AbstractView view = getVisibleView(viewData);
            view.show();
            return;
        }
        AbstractView view = createView((ViewDescriptor) viewData);
        if (view == null) {
            return;
        }
        if(!visibleViews.containsKey(view.getUniqueId())) {
            addVisibleViewToMap(view);
        }

        view.setVisible(true);
    }

    /**
     * Returns the view with the corresponding ID. In contrast to getViewDescriptor(...), the concrete view instance is
     * returned here.
     *
     * @param viewID ID of the view which should be retrieved. Attention: for views which can be opened multiple times,
     *               the naming scheme _InstanceX needs to be used. Refer to the createView method.
     * @return The view or null if not active.
     */
    public AbstractView getVisibleView(String viewID) {
        return visibleViews.get(viewID);
    }

    /**
     * Returns the view with the corresponding view descriptor. In contrast to getViewDescriptor(...), the concrete view
     * instance is returned here. If the view is reopenable and multiple views are loaded, the first instance
     * is returned here. Refer to getVisibleViews to get all instances.
     *
     * @param vd the view descriptor as used in the extension point
     * @return The view or null if not active
     */
    public AbstractView getVisibleView(ViewDescriptor vd) {
        if (vd.isReopenable()) {
            return visibleViews.values().stream().filter(view -> {
                String originalID = view.getUniqueId().split("_Instance")[0];
                return originalID.equals(vd.getId());
            }).findFirst().orElse(null);
        } else {
            return visibleViews.get(vd.getId());
        }
    }

    /**
     * Returns the views with the corresponding view descriptor. This includes all instances of a view for a given
     * view descriptor when reopenable of the view is set.
     *
     * @param vd the view descriptor as used in the extension point
     * @return List of views matching the ViewDescriptor.
     */
    public List<AbstractView> getVisibleViews(ViewDescriptor vd) {
        if (vd.isReopenable()) {
            return visibleViews.values().stream().filter(view -> {
                String originalID = view.getUniqueId().split("_Instance")[0];
                return originalID.equals(vd.getId());
            }).toList();
        } else {
            return Arrays.asList(visibleViews.get(vd.getId()));
        }
    }

    /**
     * Returns the first instance of a view specified by its class
     *
     * @param viewClass The view class
     * @return the view instance or null if not found or inactive
     */
    @SuppressWarnings("unchecked")
    public <T extends AbstractView> T getView(Class<T> viewClass) {
        return (T) visibleViews.values().stream().filter(abstractView -> abstractView.getClass() == viewClass).findFirst().orElse(null);
    }

    /**
     * Returns the all instances of a view specified by its class.
     *
     * @param viewClass The view class.
     * @return List of views of the given class.
     */
    public <T extends AbstractView> List<T> getViews(Class<T> viewClass) {
        return visibleViews.values().stream().filter(abstractView -> abstractView.getClass() == viewClass).map(abstractView -> (T) abstractView).toList();
    }

    /**
     * Triggers the onShutdown() methods of the views. For internal use only.
     */
    public void runOnViewShutdown() {
        for (AbstractView view : visibleViews.values()) {
            view.onShutdown();
        }
    }

    /**
     * Gets all registered ViewDescriptors.
     *
     * @return Map of all ViewDescriptors and their IDs.
     */
    public Map<String, ViewDescriptor> getRegisteredViews() {
        return GroupUtils.convert(viewEP.getViewDescriptors());
    }

    /**
     * Gets a list of all ViewDescriptors which are not visible or are reopenable.
     *
     * @return List of all ViewDescriptors which are not visible or are reopenable.
     */
    public List<ViewDescriptor> getInvisibleViewDescriptors() {
        return GroupUtils.convert(viewEP.getViewDescriptors())
                .values()
                .stream()
                .filter(p -> (!isViewVisible(p) || p.isReopenable()))
                .collect(Collectors.toList());
    }

    /**
     * Gets a list of all ViewDescriptors which are not visible or are reopenable.
     *
     * @param views VideDescriptors to check.
     * @return List of all ViewDescriptors which are not visible or are reopenable.
     */
    public List<ViewDescriptor> getInvisibleViewDescriptors(Map<String, ViewDescriptor> views) {
        return views
                .values()
                .stream()
                .filter(p -> (!isViewVisible(p) || p.isReopenable()))
                .collect(Collectors.toList());
    }

    /**
     * Gets all groups which are currently not visible.
     *
     * @return List of Groups which are not visible.
     */
    public List<ViewGroup> getInvisibleGroups() {
        return viewEP.getViewDescriptors()
                .values()
                .stream()
                .filter(p -> (!isGroupVisible(p)))
                .collect(Collectors.toList());
    }

    /**
     * Checks if a view is visible. For views which are reopenable, the ID follows the naming scheme _InstanceX which
     * is explained in the createView method.
     *
     * @param id ID to check.
     * @return True if the view is visible.
     */
    public boolean isViewVisible(String id) {
        return visibleViews.containsKey(id);
    }

    /**
     * Checks if a view is visible based on the ViewDescriptor.
     *
     * @param v ViewDescriptor to check.
     * @return True if the view is visible.
     */
    public boolean isViewVisible(ViewDescriptor v) {
        return isViewVisible(v.getId());
    }

    /**
     * Checks if a group is visible.
     *
     * @param group ViewGroup to check.
     * @return True if is visible.
     */
    public boolean isGroupVisible(ViewGroup group) {
        Map<String, ViewDescriptor> descriptorMap = GroupUtils.convert(group.getSubGroups());
        descriptorMap.putAll(group.getViews());
        int visibleCounter = 0;
        for (ViewDescriptor descriptor : descriptorMap.values()) {
            if (isViewVisible(descriptor) && !descriptor.isReopenable()) {
                visibleCounter++;
            }
        }
        return descriptorMap.size() == visibleCounter;
    }

}
