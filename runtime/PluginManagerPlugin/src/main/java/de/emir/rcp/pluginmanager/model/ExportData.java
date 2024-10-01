package de.emir.rcp.pluginmanager.model;

import de.emir.model.universal.plugincore.var.impl.ConfigMapImpl;
import de.emir.model.universal.plugincore.var.impl.ConfigObjectImpl;

/**
 * Configuration which is used for the product export.
 */
public class ExportData {
    private String entryPointPath = ""; // make sure all attributes are initialised otherwise toConfigMap breaks
    private String entryPointPomPath = "";
    private String layoutFilePath = "";
    private String propertiesFilePath = "";
    private String outputPath = "";
    private String plantUMLPath = "";
    private String doxygenPath = "";

    private boolean copyLayout = true;
    private boolean copyProperties = true;
    private boolean copyAll = false;
    private boolean addDocumentation = false;

    private boolean resolveLocally = true;
    private boolean removeAllRepositories = false;
    private boolean removeCredentials = true;

    private boolean leanRelease = true;
    private boolean onlineRelease = false;

    private boolean createZip = false;

    public String getEntryPointPath() {
        return entryPointPath;
    }

    public String getEntryPointPomPath() {
        return entryPointPomPath;
    }

    public String getLayoutFilePath() {
        return layoutFilePath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getPropertiesFilePath() {
        return propertiesFilePath;
    }

    public boolean isCopyAdditionalFiles() {
        return copyAll;
    }

    public boolean isCopyLayout() {
        return copyLayout;
    }

    public boolean isCopyProperties() {
        return copyProperties;
    }

    public void setCopyAll(boolean copyAll) {
        this.copyAll = copyAll;
    }

    public void setCopyLayout(boolean copyLayout) {
        this.copyLayout = copyLayout;
    }

    public void setCopyProperties(boolean copyProperties) {
        this.copyProperties = copyProperties;
    }

    public void setEntryPointPath(String entryPointPath) {
        this.entryPointPath = entryPointPath;
    }

    public void setEntryPointPomPath(String entryPointPomPath) {
        this.entryPointPomPath = entryPointPomPath;
    }

    public void setLayoutFilePath(String layoutFilePath) {
        this.layoutFilePath = layoutFilePath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public void setPropertiesFilePath(String propertiesFilePath) {
        this.propertiesFilePath = propertiesFilePath;
    }

    public boolean isLeanRelease() {
        return leanRelease;
    }

    public void setLeanRelease(boolean leanRelease) {
        this.leanRelease = leanRelease;
        if (leanRelease) {
            this.setResolveLocally(false);
            this.setOnlineRelease(false);
        }
    }

    public boolean isRemoveAllRepositories() {
        return removeAllRepositories;
    }

    public boolean isRemoveCredentials() {
        return removeCredentials;
    }

    public boolean isResolveLocally() {
        return resolveLocally;
    }

    public void setResolveLocally(boolean resolveLocally) {
        this.resolveLocally = resolveLocally;
        if (resolveLocally) {
            this.setOnlineRelease(false);
            this.setLeanRelease(false);
        }
    }

    public void setRemoveAllRepositories(boolean removeAllRepositories) {
        this.removeAllRepositories = removeAllRepositories;
    }

    public void setRemoveCredentials(boolean removeCredentials) {
        this.removeCredentials = removeCredentials;
    }

    public String getPlantUMLPath() {
        return plantUMLPath;
    }

    public void setPlantUMLPath(String plantUMLPath) {
        this.plantUMLPath = plantUMLPath;
    }

    public String getDoxygenPath() {
        return doxygenPath;
    }

    public void setDoxygenPath(String doxygenPath) {
        this.doxygenPath = doxygenPath;
    }

    public boolean isAddDocumentation() {
        return addDocumentation;
    }

    public void setAddDocumentation(boolean addDocumentation) {
        this.addDocumentation = addDocumentation;
    }

    public boolean isOnlineRelease() {
        return onlineRelease;
    }

    public void setOnlineRelease(boolean onlineRelease) {
        this.onlineRelease = onlineRelease;
        if (onlineRelease) {
            this.setLeanRelease(false);
            this.setResolveLocally(false);
        }
    }

    public boolean isCopyAll() {
        return copyAll;
    }

    public boolean isCreateZip() {
        return createZip;
    }

    public void setCreateZip(boolean createZip) {
        this.createZip = createZip;
    }
    
    public static ExportData fromConfigMap(ConfigMapImpl map) {
        ExportData result = new ExportData();
        if (map == null || (map.getMap().isEmpty() && map.getMapSimple().isEmpty())) {
            return result;
        }
        result.setAddDocumentation(((ConfigObjectImpl) map.get("addDocumentation")).getAsBoolean());
        result.setCopyAll(((ConfigObjectImpl) map.get("copyAll")).getAsBoolean());
        result.setCopyLayout(((ConfigObjectImpl) map.get("copyLayout")).getAsBoolean());
        result.setCopyProperties(((ConfigObjectImpl) map.get("copyProperties")).getAsBoolean());
        result.setCreateZip(((ConfigObjectImpl) map.get("createZip")).getAsBoolean());
        result.setDoxygenPath(((ConfigObjectImpl) map.get("doxygenPath")).getValue());
        result.setEntryPointPath(((ConfigObjectImpl) map.get("entryPointPath")).getValue());
        result.setEntryPointPomPath(((ConfigObjectImpl) map.get("entryPointPomPath")).getValue());
        result.setLayoutFilePath(((ConfigObjectImpl) map.get("layoutFilePath")).getValue());
        result.setLeanRelease(((ConfigObjectImpl) map.get("leanRelease")).getAsBoolean());
        result.setOnlineRelease(((ConfigObjectImpl) map.get("onlineRelease")).getAsBoolean());
        result.setOutputPath(((ConfigObjectImpl) map.get("outputPath")).getValue());
        result.setPropertiesFilePath(((ConfigObjectImpl) map.get("propertiesFilePath")).getValue());
        result.setPlantUMLPath(((ConfigObjectImpl) map.get("plantUMLPath")).getValue());
        result.setRemoveAllRepositories(((ConfigObjectImpl) map.get("removeAllRepositories")).getAsBoolean());
        result.setRemoveCredentials(((ConfigObjectImpl) map.get("removeCredentials")).getAsBoolean());
        result.setResolveLocally(((ConfigObjectImpl) map.get("resolveLocally")).getAsBoolean());
        return result;
    }
    
    public ConfigMapImpl toConfigMap() {
        ConfigMapImpl result = new ConfigMapImpl();
        result.put("addDocumentation", addDocumentation);
        result.put("copyAll", copyAll);
        result.put("copyLayout", copyLayout);
        result.put("copyProperties", copyProperties);
        result.put("createZip", createZip);
        result.put("doxygenPath", doxygenPath);
        result.put("entryPointPath", entryPointPath);
        result.put("entryPointPomPath", entryPointPomPath);
        result.put("layoutFilePath", layoutFilePath);
        result.put("leanRelease", leanRelease);
        result.put("onlineRelease", onlineRelease);
        result.put("outputPath", outputPath);
        result.put("plantUMLPath", plantUMLPath);
        result.put("propertiesFilePath", propertiesFilePath);
        result.put("removeAllRepositories", removeAllRepositories);
        result.put("removeCredentials", removeCredentials);
        result.put("resolveLocally", resolveLocally);
        return result;
    }
}
