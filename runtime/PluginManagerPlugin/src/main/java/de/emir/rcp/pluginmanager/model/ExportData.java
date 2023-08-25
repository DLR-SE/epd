package de.emir.rcp.pluginmanager.model;

public class ExportData {

    private String entryPointPath;
    private String entryPointPomPath;
    private String layoutFilePath;
    private String propertiesFilePath;
    private String outputPath;
    private String plantUMLPath;
    private String doxygenPath;

    private boolean copyLayout = true;
    private boolean copyProperties = true;
    private boolean copyAll = false;
    private boolean addDocumentation = false;

    private boolean resolveLocally = true;
    private boolean removeAllRepositories = true;

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
}
