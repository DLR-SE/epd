package de.emir.epd.example.map;

import de.emir.epd.example.basic.ExampleBasic;
import de.emir.epd.mapview.views.map.MapViewerWithTools;
import de.emir.epd.mapview.views.tools.AbstractMapViewTool;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.rcp.manager.util.PlatformUtil;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.event.MouseEvent;

/**
 * Map tool implementation for the modification of a coordinate. This tool is used in conjunction with the ExampleLayer
 * which displays the coordinate. The coordinate is picked up using the SelectionManager. When the mouse is dragged, the
 * object is modified. The selection is cleared then the mouse is released.
 */
public class ExampleTool extends AbstractMapViewTool {

    private Coordinate currentSelection;
    private MapViewerWithTools viewer;


    public ExampleTool() {
    }

    @Override
    public void init(MapViewerWithTools viewer) {
        this.viewer = viewer;
    }

    @Override
    public void activate() {
        reset();
    }

    @Override
    public void deactivate() {
        reset();
    }

    @Override
    public void modelChanged() {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            //check if mouse was over a coordinate, this is done by ExampleLayer
            Object obj = PlatformUtil.getSelectionManager().getSelectedObject(ExampleBasic.EXAMPLE_SELECTION_COORDINATE_CONTEXT);
            if (obj instanceof Coordinate) {
                //save coordinate for modifications
                currentSelection = (Coordinate) obj;
                e.consume();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (currentSelection != null) {
            //convert pixel coordinate to global
            GeoPosition geoPosition = viewer.convert(e.getPoint());

            //set values in current selection
            currentSelection.setLatLon(
                geoPosition.getLatitude(),
                geoPosition.getLongitude()
            );

            PlatformUtil.getSelectionManager().setSelection(
                    ExampleBasic.EXAMPLE_SELECTION_CONTEXT,
                    currentSelection
            );

            e.consume();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //modification is complete
        reset();
    }

    private void reset() {
        currentSelection = null;
    }
}
