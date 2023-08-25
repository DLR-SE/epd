package de.emir.rcp.properties;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.collections.*;
import com.thoughtworks.xstream.security.AnyTypePermission;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PropertyStore {
    private static final String PROPERTY_FILE = "properties.data";
    private static Map<String, PropertyContext> contexts = new HashMap<>();

    public static PropertyContext getContext(String contextName) {
        PropertyContext context = contexts.get(contextName);
        if (context == null) {
            context = new PropertyContext();
            contexts.put(contextName, context);
        }
        return context;
    }

    public static void load() {
        ResourceManager rmgr = ResourceManager.get(PropertyStore.class);
        File f = rmgr.getHomePath().resolve(PROPERTY_FILE).toFile();
        load(f);
    }

    @SuppressWarnings("unchecked")
    public static void load(File propertyFile) {
        XStream xs = new XStream();
        xs.allowTypes(
                new Class[]{
                        de.emir.rcp.properties.PropertyContext.class,
                        java.awt.Rectangle.class,
                        java.util.HashMap.class,
                        java.util.ArrayList.class,
                        de.emir.tuml.ucore.runtime.prop.internal.GenericProperty.class,
                        org.apache.log4j.Level.class
                }
        );
        xs.allowTypesByWildcard(new String[]{"de.emir.tuml.ucore.runtime.prop.**"});
        xs.allowTypesByWildcard(new String[]{"de.emir.rcp.pluginmanager.model.**"});
        xs.allowTypeHierarchy(de.emir.tuml.ucore.runtime.prop.IProperty.class);
        xs.allowTypeHierarchy(de.emir.tuml.ucore.runtime.prop.AbstractProperty.class);
        xs.allowTypeHierarchy(de.emir.tuml.ucore.runtime.prop.internal.GenericProperty.class);
        xs.allowTypeHierarchy(de.emir.rcp.properties.PropertyContext.class);
        xs.allowTypeHierarchy(Collection.class);
        xs.allowTypeHierarchy(Map.class);

        xs.addPermission(AnyTypePermission.ANY); // TODO: please use proper type permissions and fix what is stored

        xs.registerConverter(new CollectionConverter(xs.getMapper()));
        xs.registerConverter(new ArrayConverter(xs.getMapper()));
        xs.registerConverter(new PropertiesConverter());
        xs.registerConverter(new MapConverter(xs.getMapper()));
        xs.registerConverter(new TreeMapConverter(xs.getMapper()));
        xs.registerConverter(new TreeSetConverter(xs.getMapper()));

        try {
            contexts = (HashMap<String, PropertyContext>) xs.fromXML(new FileInputStream(propertyFile));
        } catch (FileNotFoundException e) {
            // do nothing
        } catch (Exception e) {
            ULog.error(e);
        }
    }

    public static void save() {
        ResourceManager rmgr = ResourceManager.get(PropertyStore.class);
        File f = rmgr.getHomePath().resolve(PROPERTY_FILE).toFile();
        save(f);
    }

    public static void save(File propertyFile) {

        if (propertyFile.getAbsoluteFile().getParentFile().exists() == false) {
            propertyFile.getAbsoluteFile().getParentFile().mkdirs();
        }

        // write to file
        try (FileOutputStream stream = new FileOutputStream(propertyFile)) {
            XStream xs = new XStream();
            // Note: do not use x.toXML(contexts, stream), this will corrupt the file if an error
            // occurs as it writes as a stream
            String xml = xs.toXML(contexts);
            stream.write(xml.getBytes());
        } catch (Exception e) {
            ULog.error(e);
        }
    }

}
