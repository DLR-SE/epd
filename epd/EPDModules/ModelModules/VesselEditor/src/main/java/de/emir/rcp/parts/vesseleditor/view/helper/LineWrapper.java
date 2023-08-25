package de.emir.rcp.parts.vesseleditor.view.helper;

import java.awt.*;

public class LineWrapper {
    private Shape shape;
    private int before;
    private int after;

    public LineWrapper(Shape shape, int before, int after) {
        this.shape = shape;
        this.before = before;
        this.after = after;
    }

    public Shape getShape() {
        return shape;
    }

    public int getBefore() {
        return before;
    }

    public int getAfter() {
        return after;
    }
}
