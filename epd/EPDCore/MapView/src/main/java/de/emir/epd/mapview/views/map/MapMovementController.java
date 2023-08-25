package de.emir.epd.mapview.views.map;

import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.mapview.manager.MapViewManager;
import de.emir.model.universal.physics.PhysicalObject;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.prop.IProperty;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Handles the movement of the displayed map section. E.g. to lock the map view
 * on a selected target
 * 
 * @author Florian
 *
 */
public class MapMovementController {

	private MapViewerWithTools mv;

	private IProperty lockOnSelectionProperty;

	protected Object currentSelection;

	private Thread moveThread;

	public MapMovementController(MapViewerWithTools mv) {

		this.mv = mv;
		createLockOnSelectionHandler();

	}

	private void createLockOnSelectionHandler() {

		PropertyContext ctx = PropertyStore.getContext(MVBasic.MAP_VIEW_PROP_CONTEXT);
		lockOnSelectionProperty = ctx.getProperty(MVBasic.MAP_VIEW_PROP_LOCK_VIEW_ON_CURRENT_SELECTION, false);


		PlatformUtil.getSelectionManager().subscribe(MVBasic.MAP_SELECTION_CTX, sub -> {

			currentSelection = sub.isPresent() ? sub.get() : null;


		});

		 ServiceManager.get(MapViewManager.class).addMapManuallyMovedListener(new IMapManuallyMovedListener() {
			
			@Override
			public void mapManuallyMoved() {
				// Disable locks on manually drag
				lockOnSelectionProperty.setValue(false);
				
			}
		});
		
		startMoveThread();

	}



	private void startMoveThread() {

		moveThread = new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {

					if ((boolean) lockOnSelectionProperty.getValue() == true && currentSelection instanceof PhysicalObject) {

						Pose pose = ((PhysicalObject) currentSelection).getPose();

						if (pose != null) {
							Coordinate c = pose.getCoordinate();

							if (c != null) {

								double lat = c.getLatitude();
								double lon = c.getLongitude();

								Point2D center = mv.getJXMapViewer().getCenter();

								Point2D px = mv.convert(lon, lat);
								Rectangle bounds = mv.getJXMapViewer().getViewportBounds();

								px.setLocation(px.getX() + bounds.getX(), px.getY() + bounds.getY());
								
								if (center.getX() != px.getX() || center.getY() != px.getY()) {
									mv.getJXMapViewer().setCenter(px);
								}

							}
						}

					}

					try {
						Thread.sleep(25);
					} catch (InterruptedException e) {

					}
				}

			}
		});
		moveThread.setName("MapMovementThread");
		moveThread.start();

	}

}
