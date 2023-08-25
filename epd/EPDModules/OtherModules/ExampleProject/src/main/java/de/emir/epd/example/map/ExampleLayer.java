package de.emir.epd.example.map;

import de.emir.epd.example.basic.ExampleBasic;
import de.emir.epd.mapview.views.map.AbstractMapLayer;
import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.rcp.manager.SelectionManager;
import de.emir.rcp.manager.util.PlatformUtil;
import io.reactivex.rxjava3.functions.Consumer;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Optional;

/**
 * Displays a coordinate on a new map layer. The coordinate object is received via the SelectionManager and
 * is repainted each time the selection changed or the coordinate itself changes. The point is displayed as
 * a circle with a message in a text box.
 *
 * This layer also makes the coordinate object available for the ExampleTool. It checks if the mouse hovers
 * over the coordinate and broadcasts the object via the SelectionManager.
 */
public class ExampleLayer extends AbstractMapLayer {
    // message to display near the coordinate
    protected final String MESSAGE = "I AM HERE";

    // current position received via selection context
    protected Coordinate curPos;

    // circle to draw
    protected Arc2D current;

    public ExampleLayer() {
        SelectionManager manager = PlatformUtil.getSelectionManager();
        manager.subscribe(ExampleBasic.EXAMPLE_SELECTION_CONTEXT, new Consumer<>() {
            @Override
            public void accept(Optional<Object> o) {
                if (o.isPresent() && o.get() instanceof Coordinate) {
                    curPos = (Coordinate) o.get();
                    // set layer dirty, if this is not set paint won't be called
                    setDirty(true);
                }
            }
        });

        setDirty(true);
    }

    @Override
    public boolean isVisibilityUserControlled() {
        return false;
    }

    @Override
    public void modelChanged() {

    }

    @Override
    public void paint(BufferingGraphics2D g, IDrawContext c) {
        if (curPos != null) {
            // convert current position
            Point2D point = c.convert(curPos.getLongitude(), curPos.getLatitude());

            // create a shape for current position
            current = new Arc2D.Double(
                    point.getX() - 10, point.getY() - 10,
                    20.0, 20.0, 0,
                    360, Arc2D.CHORD
            );

            // set drawing color
            g.setColor(Color.RED);
            // fill shape
            g.fill(current);

            Rectangle2D rect = new Rectangle2D.Double(0.0, 0.0, 100, 10 );
            g.setColor(Color.BLACK);
            g.draw(rect);

            Rectangle2D life = new Rectangle2D.Double(0.0, 0.0, 50, 10 );
            g.setColor(Color.RED);
            g.fill(life);

            // translate Graphics2D to the current position
            AffineTransform transform = g.getTransform();
            g.translate(point.getX(), point.getY());

            // calculate font height, width
            FontMetrics metrics = g.getFontMetrics();
            int offX = -metrics.stringWidth(MESSAGE) / 2;

            // draw string
            g.drawString(MESSAGE, offX, -20);

            // reset transformation
            g.setTransform(transform);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (current != null) {
            // check if the mouse is on the drawn shape
            if (current.intersects(e.getX(), e.getY(), 10, 10)) {
                // broadcast shape, example tool will get the coordinate for modifications
                PlatformUtil.getSelectionManager().setSelection(
                        ExampleBasic.EXAMPLE_SELECTION_COORDINATE_CONTEXT, curPos
                );
            } else {
                PlatformUtil.getSelectionManager().setSelection(
                        ExampleBasic.EXAMPLE_SELECTION_COORDINATE_CONTEXT, null
                );
            }
        }else {
            PlatformUtil.getSelectionManager().setSelection(
                    ExampleBasic.EXAMPLE_SELECTION_COORDINATE_CONTEXT, null
            );
        }
    }
}
