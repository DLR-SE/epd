package de.emir.rcp.properties;

import de.emir.model.universal.PluginCoreModel;
import de.emir.model.universal.plugincore.var.VarPackage;
import de.emir.tuml.ucore.runtime.UModelElement;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.impl.UPackageImpl;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;
import org.apache.logging.log4j.Level;
import de.emir.tuml.ucore.runtime.serialization.AbstractSerializer;
import de.emir.tuml.ucore.runtime.serialization.xml.XMLSerializer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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
        PluginCoreModel.init();
        VarPackage.init();
        AbstractSerializer ser = AbstractSerializer.createSerializerForFile(propertyFile);
        try {
            if (ser instanceof XMLSerializer) {
                ((XMLSerializer) ser).setAcceptMissingReferences(true);
            }
            UObject obj = ser.readFile(propertyFile);
            if (obj instanceof UPackage) {
                UPackageImpl rootPkg = (UPackageImpl) obj;

                for (UObject ctxObject : rootPkg.getContent()) {
                    // Every context is in one subpackage
                    if (ctxObject instanceof UPackage) {
                        UPackageImpl ctxPackage = (UPackageImpl) ctxObject;
                        PropertyContext ctx = getContext(ctxPackage.getName());
                        contexts.put(ctxPackage.getName(), ctx);
                        if (ctxPackage.getContent() != null) {
                            // subpackage has further subsubpackages, each one contains a complex UObject based type
                            for (UObject subsubPackage : ctxPackage.getContent()) {
                                if (subsubPackage instanceof UPackage) {
                                    UPackageImpl objectPackage = (UPackageImpl) subsubPackage;
                                    ctx.getProperty(objectPackage.getName(), objectPackage.getContent().getFirst()); // Only one element allowed
                                }
                            }
                        }
                        if (ctxPackage.getAllProperties() != null) {
                            for (IProperty prop : ctxPackage.getAllProperties()) {
                                ctx.addProperty(prop);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        UPackageImpl pkg = new UPackageImpl() {
            @Override
            public void build() {

            }
        };
        for (Map.Entry<String, PropertyContext> context : contexts.entrySet()) {
            String key = context.getKey();
            UPackage subPkg = pkg.createPackage(key);
            Iterator it = context.getValue().getAllProperties().iterator();
            while (it.hasNext()) {
                IProperty prop = (IProperty) it.next();
                if (prop.getValue() instanceof UObject) {
                    // store as UObject rather than property
                    UPackage subsubPkg = subPkg.createPackage(prop.getName());
                    subsubPkg.getContent().add((UModelElement) prop.getValue());
                } else {
                    if (prop.getParentProperty() == null) {
                        // Only add top level properties directly. Subproperties follow implcitely.
                        subPkg.addProperty(prop);
                    }
                }
            }
            pkg.getContent().add(subPkg);
        }

        assert (pkg != null);
        File f = propertyFile;
        try {
            AbstractSerializer ser = AbstractSerializer.createSerializerForFile(f);
            assert (ser != null);
            ser.writeFile(pkg, f);
        } catch (IOException e) {
            ULog.error("Failed to write file");
            e.printStackTrace();
        }
    }
}
