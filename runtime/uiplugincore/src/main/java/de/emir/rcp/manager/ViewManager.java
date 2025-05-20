package de.emir.rcp.manager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

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
 * Manages all registered views
 * 
 * @author fklein
 *
 */
public class ViewManager implements IService {

    private static Logger log = ULog.getLogger(ViewManager.class);

    private ViewExtensionPoint viewEP = new ViewExtensionPoint();

    private Map<String, AbstractView> visibleViews = new HashMap<>();
    private Map<Class<?>, AbstractView> visibleViewsByClass = new HashMap<>();

    public ViewExtensionPoint getViewExtensionPoint() {
        return viewEP;
    }

    public Map<String, AbstractView> getActiveViews() {
        return visibleViews;
    }

    public void fillViews() {

        PlatformUtil.getWindowManager().getMainWindow().getMainControl().addSingleDockableFactory(f -> {
            return true;
        }, new SingleCDockableFactory() {

            @Override
            public SingleCDockable createBackup(String id) {

                IViewDescriptor viewData = getViewDescriptor(id);

                if (viewData == null) {
                    return null;
                }

                return createView((ViewDescriptor) viewData);

            }
        });

    }

    private AbstractView createView(ViewDescriptor epView) {

        Class<? extends AbstractView> viewClass = epView.getViewClass();
        String viewId = epView.getId();
        AbstractView view = null;

        try {

            Constructor<? extends AbstractView> constructor = viewClass.getConstructor(String.class);
            view = constructor.newInstance(viewId);

        } catch (NoSuchMethodException | SecurityException e) {

            try {
                view = viewClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e1) {
                log.error("Can't create view with id [" + viewId + "]");
                e1.printStackTrace();
            }

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            log.error("Can't create view with id [" + viewId + "]");
            e.printStackTrace();
        }

        if (view != null) {
            log.debug("Created view with id [" + viewId + "]");

            view.setExternalizable(epView.isExternalizable());
            view.setMinimizable(epView.isMinimizable());
            view.setMaximizable(epView.isMaximizable());
            view.setCloseable(epView.isCloseable());
            view.setTitleText(epView.getLabel());

            ImageIcon icon = epView.getIcon();
            if (icon != null) {
                view.setTitleIcon(icon);
            }

            JPanel parent = view.getParentPanel();

            try {
                Component content = view.createContent();
                parent.add(content, BorderLayout.CENTER);
                addVisibleViewToMap(view);
            } catch (Exception | Error e) {
                log.warn("Failed to create content for: " + view.getUniqueId() + " Error: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return view;

    }

    private void addVisibleViewToMap(AbstractView view) {

        PlatformUtil.getWindowManager().getMainWindow().getMainControl().addDockable(view);

        view.addCDockableStateListener(new CDockableStateListener() {

            @Override
            public void visibilityChanged(CDockable dockable) {
                if (dockable.isVisible() == true) {

                    visibleViews.put(view.getUniqueId(), view);
                    visibleViewsByClass.put(view.getClass(), view);

                } else {

                    visibleViews.remove(view.getUniqueId());
                    visibleViewsByClass.remove(view.getClass());

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
     * @param viewID
     *            unique identifier of the view
     * @return
     */
    public ViewDescriptor getViewDescriptor(String viewID) {
        Map<String, ViewGroup> viewsFromEP = viewEP.getViewDescriptors();

        for (ViewGroup group : viewsFromEP.values()){
            ViewDescriptor descriptor = GroupUtils.find(viewID, group);
            if (descriptor != null){
                return descriptor;
            }

        }

        return null;

    }

    /**
     * Sets the visibility of a view to true
     * 
     * @param id
     */
    public void openView(String id) {

        ViewDescriptor viewData = getViewDescriptor(id);

        if (viewData == null) {
            log.error("Tried to open view with id [" + id + "] but it cannot be found");
            return;
        }

        if (isViewVisible(viewData) == true) {

            AbstractView view = getVisibleView(viewData);
            view.show();

            return;
        }

        AbstractView view = createView((ViewDescriptor) viewData);

        if (view == null) {

            return;
        }

        view.setVisible(true);

    }

    /**
     * Returns the view with the corresponding ID. In contrast to getViewDescriptor(...), the concrete view instance is
     * returned here.
     * 
     * @param viewID
     * @return The view or null if not active
     */
    public AbstractView getVisibleView(String viewID) {
        return visibleViews.get(viewID);
    }

    /**
     * Returns the view with the corresponding view descriptor. In contrast to getViewDescriptor(...), the concrete view
     * instance is returned here.
     * 
     * @param vd
     *            the view descriptor as used in the extension point
     * @return The view or null if not active
     */
    public AbstractView getVisibleView(ViewDescriptor vd) {
        return visibleViews.get(vd.getId());
    }

    /**
     * Returns the view specified by its class
     * 
     * @param viewClass
     *            The view class
     * @return the view instance or null if not found or inactive
     */
    @SuppressWarnings("unchecked")
    public <T extends AbstractView> T getView(Class<T> viewClass) {
        return (T) visibleViewsByClass.get(viewClass);
    }

    /**
     * Triggers the onShutdown() methods of the views. For internal use only.
     */
    public void runOnViewShutdown() {

        for (AbstractView view : visibleViews.values()) {
            view.onShutdown();
        }

    }

    public Map<String, ViewDescriptor> getRegisteredViews() {
        return GroupUtils.convert(viewEP.getViewDescriptors());
    }

    public List<ViewDescriptor> getInvisibleViewDescriptors() {
        return GroupUtils.convert(viewEP.getViewDescriptors())
                .values()
                .stream()
                .filter(p -> (isViewVisible(p) == false))
                .collect(Collectors.toList());
    }

    public List<ViewDescriptor> getInvisibleViewDescriptors(Map<String, ViewDescriptor> views) {
        return views
                .values()
                .stream()
                .filter(p -> (isViewVisible(p) == false))
                .collect(Collectors.toList());
    }

    public List<ViewGroup> getInvisibleGroups() {
        return viewEP.getViewDescriptors()
                .values()
                .stream()
                .filter(p -> (isGroupVisible(p) == false))
                .collect(Collectors.toList());
    }

    public boolean isViewVisible(String id) {

        return visibleViews.containsKey(id);

    }

    public boolean isViewVisible(ViewDescriptor v) {

        return isViewVisible(v.getId());

    }

    public boolean isGroupVisible(ViewGroup group){
        Map<String, ViewDescriptor> descriptorMap = GroupUtils.convert(group.getSubGroups());
        descriptorMap.putAll(group.getViews());
        int visibleCounter = 0;
        for (ViewDescriptor descriptor : descriptorMap.values()){
            if (isViewVisible(descriptor)){
                visibleCounter++;
            }
        }

        return descriptorMap.size() == visibleCounter;
    }

}
