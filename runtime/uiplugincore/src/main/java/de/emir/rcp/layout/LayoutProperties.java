package de.emir.rcp.layout;

import de.emir.rcp.ids.Basic;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.AbstractProperty;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;

import java.util.*;

/**
 * Stores Tab-Layout related data in the PropertyStore.
 */
public class LayoutProperties {

    private List<LayoutProperty> layouts = new ArrayList<>();
    private final AbstractProperty<String> parentProperty = (AbstractProperty<String>) PropertyStore.getContext(Basic.TAB_LAYOUT_PROP_CTX).getProperty(Basic.TAB_LAYOUT_LAYOUTS_PROP, "");

    /**
     * Gets all registered layout properties.
     *
     * @return List of all registered Layout properties.
     * @apiNote Use loadConfiguration() beforehand to load the properties from the configstore. Otherwise only
     * properties which were added during the runtime are returned.
     */
    public List<LayoutProperty> getLayouts() {
        return layouts;
    }

    /**
     * Checks if the layout name already exists in the properties.
     *
     * @param name Name to check
     * @return True if exists.
     */
    public boolean nameExists(String name) {
        return layouts.stream().anyMatch(p -> p.name != null && p.name.equals(name));
    }

    /**
     * Adds a new property to the LayoutProperties. The added property will be converted to the config once
     * saveConfiguration is called.
     *
     * @param property Property to add.
     */
    public void addProperty(LayoutProperty property) {
        synchronized (parentProperty) {
            this.layouts.add(property);
        }
    }

    /**
     * Removes a property from the LayoutProperties.
     *
     * @param property Property to remove.
     */
    public void removeProperty(LayoutProperty property) {
        synchronized (parentProperty) {
            this.layouts.remove(property);
        }
    }

    /**
     * Loads a configuration from the PropertyStore. This loads all tab layout settings from the users persistent storage.
     */
    public void loadConfiguration() {
        synchronized (parentProperty) {
            layouts.clear();
        }
        if (parentProperty.getSubProperties() != null) {
            synchronized (parentProperty) {
                for (IProperty<?> property : parentProperty.getSubProperties()) {
                    layouts.add(LayoutProperty.fromProperties(property));
                }
            }
        }
    }

    /**
     * Saves the layout configuration to the PropertyStore.
     */
    public void saveConfiguration() {
        synchronized (parentProperty) {
            if (parentProperty.getSubProperties() != null) {
                parentProperty.getSubProperties().clear();
            }
            for (LayoutProperty property : layouts) {
                parentProperty.addChild(property.toProperties());
            }
        }
    }

    /**
     * Clears the layout properties.
     */
    public void clear() {
        synchronized (parentProperty) {
            layouts.clear();
        }
    }

    /**
     * Inserts a layout property after another.
     *
     * @param item   Item to insert.
     * @param anchor Item after which the item to insert should be inserted.
     */
    public void insertAfter(LayoutProperty item, LayoutProperty anchor) {
        synchronized (parentProperty) {
            layouts.remove(item);
            int anchorIndex = layouts.indexOf(anchor);
            layouts.add(anchorIndex + 1, item);
        }
    }

    /**
     * Inserts a layout property before another.
     *
     * @param item   Item to insert.
     * @param anchor Item before where the item to insert should be inserted.
     */
    public void insertBefore(LayoutProperty item, LayoutProperty anchor) {
        synchronized (parentProperty) {
            layouts.remove(item);
            int anchorIndex = layouts.indexOf(anchor);
            layouts.add(anchorIndex, item);
        }
    }

    /**
     * Gets the current position of a layout property. The position defines the order of the layout tabs.
     *
     * @param property Property to get position for.
     * @return Position of the property.
     */
    public int getPosition(LayoutProperty property) {
        return layouts.indexOf(property);
    }

    /**
     * Wrapper class for Tab layout properties which wrap the layout file path, icon file path, layout name and id.
     */
    public static class LayoutProperty {
        private UUID id = UUID.randomUUID();
        private String filePath;
        private String iconPath;
        private String name;


        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getIconPath() {
            return iconPath;
        }

        public void setIconPath(String iconPath) {
            this.iconPath = iconPath;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /**
         * Converts the LayoutProperty to AbstractProperty for storage.
         *
         * @return Property which contains the LayoutProperty as subproperties.
         */
        IProperty<String> toProperties() {
            AbstractProperty<String> property = (AbstractProperty<String>)
                    PropertyStore.getContext(Basic.TAB_LAYOUT_PROP_CTX).getProperty("LayoutProperty" + UUID.randomUUID(), "");
            property.addChild(new GenericProperty<>("filePath", "", true, filePath));
            property.addChild(new GenericProperty<>("iconPath", "", true, iconPath));
            property.addChild(new GenericProperty<>("name", "", true, name));
            property.addChild(new GenericProperty<>("id", "", true, id.toString()));
            return property;
        }

        /**
         * Loads a LayoutProperty from a IProperty, i.e. loads the subproperties and converts them to a LayoutProperty.
         *
         * @param properties Property to load from.
         * @return LayoutProperty based on IProperty content.
         */
        static LayoutProperty fromProperties(IProperty<?> properties) {
            List<IProperty<?>> subProperties = properties.getSubProperties();
            LayoutProperty layoutProperty = new LayoutProperty();
            for (IProperty property : subProperties) {
                switch (property.getName()) {
                    case "filePath" -> layoutProperty.filePath = property.getValue().toString();
                    case "iconPath" -> layoutProperty.iconPath = property.getValue().toString();
                    case "name" -> layoutProperty.name = property.getValue().toString();
                    case "id" -> layoutProperty.id = UUID.fromString(property.getValue().toString());
                }
            }
            return layoutProperty;
        }

        /**
         * Converts property to readable format for debugging.
         *
         * @return String representation of property.
         */
        @Override
        public String toString() {
            return "LayoutProperty{" +
                    "id=" + id +
                    ", filePath='" + filePath + '\'' +
                    ", iconPath='" + iconPath + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
