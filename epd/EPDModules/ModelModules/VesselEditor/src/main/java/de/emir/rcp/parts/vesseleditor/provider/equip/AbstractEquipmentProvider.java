package de.emir.rcp.parts.vesseleditor.provider.equip;

import de.emir.tuml.ucore.runtime.resources.ResourceManager;

import java.net.URL;


public abstract class AbstractEquipmentProvider implements IEquipmentProvider {
    private String mLabel;
    private URL mIconPath;
    private String mToolTip;

    protected AbstractEquipmentProvider(String label, String iconPath, String toolTip) {
        mLabel = label;
        mToolTip = toolTip;
        setIconPath(iconPath);
    }

    protected void setTooltip(String tp) {
        mToolTip = tp;
    }

    protected void setIconPath(String iconPath) {
        if (iconPath != null && iconPath.isEmpty() == false)
            mIconPath = ResourceManager.get(getClass()).getResource(iconPath);
    }

    @Override
    public String getLabel() {
        return mLabel;
    }

    protected void setLabel(String label) {
        mLabel = label;
    }

    @Override
    public URL getIconURL() {
        return mIconPath;
    }

    @Override
    public String getToolTip() {
        return mToolTip;
    }

}
