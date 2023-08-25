package de.emir.epd.example.cmd;

import de.emir.epd.example.basic.ExampleBasic;
import de.emir.epd.mapview.views.map.MapView;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.manager.SelectionManager;
import de.emir.rcp.manager.util.PlatformUtil;
import io.reactivex.rxjava3.functions.Consumer;

import java.util.Optional;

/**
 * Command to zoom into the plugins coordinate. Once executed this command checks if the SelectionManager contains
 * a coordinate. If this is the case, the current MapView is retrieved using the ViewManager. Finally, the MapView
 * is used to move the map center to our coordinate.
 */
public class ExampleCommand extends AbstractCommand {

    public ExampleCommand() {
        SelectionManager manager = PlatformUtil.getSelectionManager();
        manager.subscribe(ExampleBasic.EXAMPLE_SELECTION_CONTEXT, new Consumer<>() {
            @Override
            public void accept(Optional<Object> o) throws Exception {
                setCanExecute(o.isPresent() && o.get() instanceof Coordinate);
            }
        });
    }



    @Override
    public void execute() {
        // check if a position got broadcast
        SelectionManager manager = PlatformUtil.getSelectionManager();

        MapView mapView = PlatformUtil.getViewManager().getView(MapView.class);
        //check if map view is visible and if a coordinate is stored
        if (mapView.isVisible() && manager.getSelectedObject(ExampleBasic.EXAMPLE_SELECTION_CONTEXT) instanceof Coordinate){
            Coordinate coordinate = (Coordinate) manager.getSelectedObject(ExampleBasic.EXAMPLE_SELECTION_CONTEXT);
            // tell mapview to go to a specific position
            mapView.getViewer().gotoPosition(coordinate.getLongitude(), coordinate.getLatitude(), 1);
        }
    }
}
