package de.emir.epd.targetview.lib;

import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

/**
 * Rendering component which provides the rendering of SN.1/Circ.243 compliant target symbols.
 */
public class TargetShapeRenderer {
    private final Path2D lostTargetSymbol;
    private final Path2D acquisitionStateSymbol;
    private final Path2D selectedTargetSymbol;
    private final Arc2D trackMarker;
    private final Arc2D minimizedTargetSymbol;
    private final Arc2D targetSymbol;
    private final Path2D plottedTargetSymbol;

    /**
     * Sets up the TargetShapeRenderer.
     */
    public TargetShapeRenderer() {
        selectedTargetSymbol = new Path2D.Double();
        selectedTargetSymbol.append(new Line2D.Double(-15, 15, -5, 15), false);
        selectedTargetSymbol.append(new Line2D.Double(-15, 15, -15, 5), false);
        selectedTargetSymbol.append(new Line2D.Double(15, 15, 5, 15), false);
        selectedTargetSymbol.append(new Line2D.Double(15, 15, 15, 5), false);
        selectedTargetSymbol.append(new Line2D.Double(-15, -15, -15, -5), false);
        selectedTargetSymbol.append(new Line2D.Double(-15, -15, -5, -15), false);
        selectedTargetSymbol.append(new Line2D.Double(15, -15, 5, -15), false);
        selectedTargetSymbol.append(new Line2D.Double(15, -15, 15, -5), false);

        trackMarker = new Arc2D.Double(-2, -2, 4, 4, 0, 360, Arc2D.CHORD);
        minimizedTargetSymbol = new Arc2D.Double(-4, -4, 8, 8, 0, 360, Arc2D.CHORD);
        acquisitionStateSymbol = new Path2D.Double();
        for (int i = 0; i < 4; i++) {
            acquisitionStateSymbol.append(new Arc2D.Double(-10, -10, 20, 20, i * 90, 60, Arc2D.OPEN), false);
        }
        lostTargetSymbol = new Path2D.Double();
        lostTargetSymbol.append(new Line2D.Double(-10, 10, 10, -10), false);
        lostTargetSymbol.append(new Line2D.Double(-10, -10, 10, 10), false);
        targetSymbol = new Arc2D.Double(-8, -8, 16, 16, 0, 360, Arc2D.CHORD);
        plottedTargetSymbol = new Path2D.Double();
        plottedTargetSymbol.append(new Arc2D.Double(-8, -8, 16, 16, 0, 360, Arc2D.CHORD), false);
        plottedTargetSymbol.append(new Line2D.Double(0, 8, 0, -8), false);
        plottedTargetSymbol.append(new Line2D.Double(-8, 0, 8, 0), false);
    }

    /**
     * Gets the symbol for a lost target.
     *
     * @return Target symbol.
     */
    public Path2D getLostTargetSymbol() {
        return lostTargetSymbol;
    }

    /**
     * Gets the symbol for a target in acquisition state.
     *
     * @return Target symbol.
     */
    public Path2D getAcquisitionStateSymbol() {
        return acquisitionStateSymbol;
    }

    /**
     * Gets the symbol for a selected target.
     *
     * @return Target symbol.
     */
    public Path2D getSelectedTargetSymbol() {
        return selectedTargetSymbol;
    }

    /**
     * Gets the symbol for a track point.
     *
     * @return Track point symbol.
     */
    public Arc2D getTrackMarker() {
        return trackMarker;
    }

    /**
     * Gets the symbol for a minimized target.
     *
     * @return Target symbol.
     */
    public Arc2D getMinimizedTargetSymbol() {
        return minimizedTargetSymbol;
    }

    /**
     * Gets the symbol for a target.
     *
     * @return Target symbol.
     */
    public Arc2D getTargetSymbol() {
        return targetSymbol;
    }

    /**
     * Gets the symbol for a plotted target.
     *
     * @return Target symbol.
     */
    public Path2D getPlottedTargetSymbol() {
        return plottedTargetSymbol;
    }
}
