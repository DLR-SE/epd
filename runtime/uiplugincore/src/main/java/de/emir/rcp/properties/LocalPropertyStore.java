package de.emir.rcp.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class LocalPropertyStore {

    private final String PROPERTY_FILE = "properties.data";

    private Map<String, PropertyContext> contexts = new HashMap<>();

    private XmlMapper mapper = new XmlMapper();

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


    public void load(File propertyFile) {
        try {
            TypeFactory factory = mapper.getTypeFactory();
            MapType mapType = factory.constructMapType(HashMap.class, String.class, PropertyContext.class);
            contexts = mapper.readValue(new FileInputStream(propertyFile), mapType);
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
            if (propertyFile.getAbsoluteFile().getParentFile().exists() == false) {
                propertyFile.getAbsoluteFile().getParentFile().mkdirs();
            }

            mapper.writeValue(new FileOutputStream(propertyFile), contexts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
