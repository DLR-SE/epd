package de.emir.rcp.parts.vesseleditor.view.helper;

import de.emir.tuml.ucore.runtime.UClass;

public class DragProperties {
    private boolean transformX;
    private boolean transformY;
    private boolean transformZ;
    private UClass uClass;

    public DragProperties(boolean transformX, boolean transformY, boolean transformZ, UClass uClass) {
        this.transformX = transformX;
        this.transformY = transformY;
        this.transformZ = transformZ;
        this.uClass = uClass;
    }

    public DragProperties() {
        this.transformX = false;
        this.transformY = false;
        this.transformZ = false;
        this.uClass = null;
    }

    public boolean isTransformX() {
        return transformX;
    }

    public boolean isTransformY() {
        return transformY;
    }

    public boolean isTransformZ() {
        return transformZ;
    }

    public UClass getuClass() {
        return uClass;
    }

    public boolean isTransform3D() {
        return transformX && transformY && transformZ;
    }

    public boolean isTransform2D() {
        int check = ((transformX) ? 1 : 0) + ((transformY) ? 1 : 0) + ((transformZ) ? 1 : 0);
        return (check % 2) == 0;
    }

    public boolean isTransformSingle() {
        int check = ((transformX) ? 1 : 0) + ((transformY) ? 1 : 0) + ((transformZ) ? 1 : 0);
        return check == 1;
    }


}
