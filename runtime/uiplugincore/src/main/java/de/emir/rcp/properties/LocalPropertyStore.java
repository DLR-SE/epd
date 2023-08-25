package de.emir.rcp.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;

import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class LocalPropertyStore {

    private final String PROPERTY_FILE = "properties.data";

    private Map<String, PropertyContext> contexts = new HashMap<>();

    public PropertyContext getContext(String contextName) {

        PropertyContext context = contexts.get(contextName);

        if (context == null) {

            context = new PropertyContext();
            contexts.put(contextName, context);
        }

        return context;

    }

    public void load() {
        ResourceManager rmgr = ResourceManager.get(LocalPropertyStore.class);
        File f = rmgr.getHomePath().resolve(PROPERTY_FILE).toFile();
        load(f);
    }

    @SuppressWarnings("unchecked")
    public void load(File propertyFile) {

        XStream xs = new XStream();

        // Omit unknown elements
        xs.registerConverter(new CollectionConverter(xs.getMapper()) {

            @Override
            protected Object readItem(HierarchicalStreamReader reader, UnmarshallingContext context, Object current) {
                try {
                    return super.readItem(reader, context, current);
                } catch (Exception e) {
                    return null;
                }

            }

        });
        xs.ignoreUnknownElements();

        try {
            contexts = (HashMap<String, PropertyContext>) xs.fromXML(new FileInputStream(propertyFile));
        } catch (FileNotFoundException e) {
            // do nothing
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        ResourceManager rmgr = ResourceManager.get(LocalPropertyStore.class);
        File f = rmgr.getHomePath().resolve(PROPERTY_FILE).toFile();
        save(f);
    }

    public void save(File propertyFile) {
        try {

            XStream xs = new XStream();

            if (propertyFile.getAbsoluteFile().getParentFile().exists() == false) {

                propertyFile.getAbsoluteFile().getParentFile().mkdirs();

            }

            xs.toXML(contexts, new FileOutputStream(propertyFile));

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

}
