package de.emir.epd.ownship;

import com.google.common.collect.Lists;

import de.emir.epd.ais.AisLayer;
import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.mapview.views.map.AbstractMapLayer;
import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.model.EPDModelUtils;
import de.emir.epd.model.ais.AisTarget;
import de.emir.model.application.track.Track;
import de.emir.model.application.track.TrackPoint;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.units.*;
import de.emir.rcp.manager.ModelManager;
import de.emir.rcp.manager.SelectionManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.logging.ULog;
import org.apache.logging.log4j.Logger;
import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.Color;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Map layer for displaying the ownship marker on the MapViewer
 */
public class OwnshipLayer extends AbstractMapLayer implements Observer, IValueChangeListener {
	/** Logger. */
	private static Logger LOG = ULog.getLogger(OwnshipLayer.class);

	class ArrowHead extends Polygon {
		public ArrowHead(int x, int y) {
			super(new int[] { -3 + x, 0 + x, 3 + x, -3 + x }, new int[] { 3 + y, -6 + y, 3 + y, 3 + y }, 4);
		}
	}

	private Color shipColor = new Color(78, 78, 78);
	private Color trackColor = new Color(78, 78, 78, 96);
	private Vessel ownship;
	private BasicStroke normalStroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
	private BasicStroke cogStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1,
			new float[] { 5, 3 }, 0);
	private BasicStroke trueHeadingStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
	private BasicStroke trackStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

	/*
	 * Used for storing the last selected and last focused vessel on the MapViewer.
	 * If no vessel (such as AIS target) is selected,
	 * the current selected vessel will switch to the ownship to enable focusing of
	 * the ownship marker in the MapViewer.
	 */
	protected UObject lastSelectedTarget;
	protected Vessel lastFocusedTarget;
	protected Shape outline;
	protected boolean manualSelect;

	private long lastDrawTime = -1;

	/**
	 * Creates the OwnShip map layer
	 */
	public OwnshipLayer() {
		lastDrawTime = System.currentTimeMillis();

		ModelManager mm = PlatformUtil.getModelManager();

		if (mm.getModelProvider() != null && mm.getModelProvider().getModel() != null)
			try {
				modelChanged();
				addListeners();
			} catch (Exception e) {
				e.printStackTrace();
			}

	}

	/**
	 * Sets the dirty flag of the ownshipLayer to true 30 seconds after last drawing
	 * the layer
	 */
	private void startTimeoutCheckerThread() {

		Thread timeOutThread = new Thread(() -> {

			long sleepTimeMS = 29_000;

			while (true) {
				setDirty(true);
				long sleep = sleepTimeMS - (System.currentTimeMillis() - lastDrawTime);

				if (sleep > 0) {
					try {
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
						ULog.error(e);
					}
				}
			}
		});

		timeOutThread.setName("OwnshipLayerTimeoutThread");

		timeOutThread.start();

	}

	private List<TrackPoint> getTrackPoints(Track track) {
		List<TrackPoint> copy = null;
		if (track == null || track.getElements() == null)
			return new ArrayList<TrackPoint>(); // no track, return empty
		synchronized (track.getElements()) {
			copy = new ArrayList<TrackPoint>(track.getElements());
		}
		return Lists.reverse(copy);
	}

	/**
	 * Paints the ownship layer
	 * 
	 * @param g Graphicsbuffer
	 * @param c DrawContext of the MapViewer
	 */
	@Override
	public void paint(BufferingGraphics2D g, IDrawContext c) {

		if (ownship == null)
			return;

		Rectangle bounds = c.getBounds();

		// Get the position from own ship
		Vessel v = ownship;
		Pose pose = v.getPose();
		Coordinate coord = pose.getCoordinate();

		double lat = coord.getLatitude();
		double lon = coord.getLongitude();

		// Converts coordinate from own ship to pixels
		Point2D px = c.convert(lon, lat);

		List<TrackPoint> track = getTrackPoints(AisTarget.getTrack(v));
		if (track != null) {

			Point2D lastpoint = (Point2D) px.clone();

			for (TrackPoint tp : track) {
				// Draw own ship marker
				if (tp != null && tp.getCoordinate() != null) {
					g.setColor(trackColor);
					g.setStroke(trackStroke);

					Point2D point = c.convert(
							tp.getCoordinate().getLongitude(),
							tp.getCoordinate().getLatitude());

					Line2D line = new Line2D.Double(
							lastpoint.getX(), lastpoint.getY(),
							point.getX(), point.getY());

					// check if line is visible, otherwise ignore
					if (line.intersects(bounds)) {
						g.draw(line);
					}

					// check if point is visible, otherwise ignore
					if (bounds.contains(point)) {
						g.fillOval((int) point.getX() - 2, (int) point.getY() - 2, 4, 4);
					}

					lastpoint = (Point2D) point.clone();
				}

			}
		}

		g.setColor(shipColor);
		AffineTransform transform = g.getTransform();
		g.translate(px.getX(), px.getY());

		int zoom = c.getZoom();

		boolean showCogSogHeading = zoom < 11;

		if (showCogSogHeading == false) {
			g.scale(4 / (double) zoom, 4 / (double) zoom);
		}

		// Draw the name of the own ship
		// String name = v.getNameAsString();
		// if (name != null) {
		// g.drawString(name, 0, 0);
		// }

		g.setColor(shipColor);
		g.setStroke(normalStroke);

		Ellipse2D outerRing = new Ellipse2D.Double(-9, -9, 18, 18);
		g.draw(outerRing);
		outline = g.getTransform().createTransformedShape(outerRing);
		g.drawOval(-4, -4, 8, 8);

		// Gets the timestamp of the last position. If the last sent position was over
		// 30 seconds ago, set the target to lost
		Long timestamp = AisTarget.getTimestamp(v);
		long ts = timestamp != null ? timestamp : 0L;

		boolean lostTarget = System.currentTimeMillis() - ts >= 30000;

		double cogRad = 0;

		Angle course = PhysicalObjectUtils.getCOG(v);
		Speed speed = PhysicalObjectUtils.getSOG(v);

		if (course != null && speed != null) {
			float cog = (float) course.getAs(AngleUnit.DEGREE);
			float sog = (float) speed.getAs(SpeedUnit.KNOTS);

			// If course is not available (i.e. 360) do not draw course lines.
			if (cog != 360.0) {
				if (sog < 0.5) {
					g.setColor(Color.GRAY);
				}

				cogRad = Math.toRadians(cog);
				g.rotate(cogRad);

				if (lostTarget == false && showCogSogHeading == true) {
					g.fillPolygon(new ArrowHead(0, -12 - (int) (sog * 10)));
					g.setStroke(cogStroke);
					g.drawLine(0, -12, 0, -12 - (int) (sog * 10));
				}

				g.rotate(-cogRad);
			}
		}

		if (lostTarget) {
			g.drawLine(-10, 0, 10, 0);
		}

		if (PhysicalObjectUtils.getHeading(v) != null && lostTarget == false && showCogSogHeading == true) {
			int heading = (int) PhysicalObjectUtils.getHeading(v).getAs(AngleUnit.DEGREE);

			// If heading is not available (i.e. 511) do not draw heading lines.
			if (heading != 511) {
				double headingRad = Math.toRadians(heading);

				// Draw true heading
				g.rotate(headingRad);
				g.setStroke(trueHeadingStroke);
				g.drawLine(0, 0, 0, -40);
				g.drawLine(-20, 0, -10, 0);
				g.drawLine(10, 0, 20, 0);

				g.rotate(-headingRad);
			}
		} else {
			// Draw center point
			g.drawLine(0, 0, 0, 2);
		}

		// Sets the selected target to the ownship if currently no target (i.e. AIS
		// target) is selected on the MapViewer
		if(manualSelect && lastSelectedTarget == ownship) {
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
			g.draw(getSelectedBox());
		}
		setSelectedTargetIfNoneSelected(ownship);

		g.setTransform(transform);

		lastDrawTime = System.currentTimeMillis();
	}

	@Override
	public boolean isVisibilityUserControlled() {
		return true;
	}

	/**
	 * Changes the ownship model to a new model if it was changed and reloads the
	 * listeners
	 */
	@Override
	public void modelChanged() {
		ownship = EPDModelUtils.retrieveOwnship();
		registerOwnshipListener();

		EPDModelUtils.subscribeModelChange("ownship", event -> {
			if (event.getNewValue() instanceof Vessel) {
				Vessel v = (Vessel) event.getNewValue();
				if (ownship != v && v != null) {
					// System.err.println("ownship changed " + ownship + " to " +
					// NMEAVesselManager.getOwnShip());
					if (ownship != null) {
						try {
							ownship.removeListener(this);
						} catch (Exception e) {
							LOG.error("Unable to unregister listener from ownship", e);
						}
					}
					ownship = v;
					registerOwnshipListener();

					setDirty(true);
					forceSelectedTarget(ownship);
				}
			}
		});

		// We need a thread that sets the layer dirty 30 sec. after last draw
		startTimeoutCheckerThread();
	}

	/**
	 * Selects the ownship if a mouse click event was executed on the ownship symbol.
	 * @param e the event to be processed
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(outline != null) {
			if(outline.intersects(e.getX(), e.getY(), 3, 3)) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					manualSelect = true;
					if(ownship != lastSelectedTarget) {
						PlatformUtil.getSelectionManager().setSelection(MVBasic.MAP_SELECTION_CTX, ownship);
					}
					e.consume();
				}
				if(e.getButton() == MouseEvent.BUTTON3) {
					manualSelect = false;
					PlatformUtil.getSelectionManager().setSelection(MVBasic.MAP_SELECTION_CTX, null);
					e.consume();
				}
			} else {
				manualSelect = false;
			}
		}
	}

	/**
	 * Focuses the ownship if a mouse move event was executed on the ownship symbol.
	 * @param e the event to be processed
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if(outline != null) {
			if (outline.intersects(e.getX(), e.getY(), 3, 3)) {
				if (ownship == lastFocusedTarget) {
					return;
				}
				PlatformUtil.getSelectionManager().setSelection(MVBasic.MAP_FOCUS_CTX, ownship);
				if (ownship != null) {
					cursorAdapter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				e.consume();
			}
		}
	}

	/**
	 * Registers the OwnshipLayer as a listener for changes in the ownship model
	 */
	private void registerOwnshipListener() {
		if (ownship != null) {
			ownship.registerListener(this);
			try {
				Pose pose = ownship.getPose();
				if (pose != null) {
					Coordinate coordinate = pose.getCoordinate();
					if (coordinate != null) {
						ownship.getPose().getCoordinate().registerListener(this);
					}
					Orientation orientation = pose.getOrientation();
					if (orientation != null) {
						ownship.getPose().getOrientation().registerListener(this);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		setDirty(true);
	}

	@Override
	public void onValueChange(Notification arg0) {
		setDirty(true);
	}

	/**
	 * Sets the selected target of the MapViewer if no target (i.e. AIS target) is
	 * currently selected on the map
	 * 
	 * @param t Target to set if no target is currently selected
	 */
	private void setSelectedTargetIfNoneSelected(Vessel t) {
		if (lastSelectedTarget == null && !manualSelect) {
			PlatformUtil.getSelectionManager().setSelection(MVBasic.MAP_SELECTION_CTX, t);
		}
	}

	/**
	 * Forces the selected target to change to the new ownship.
	 * 
	 * @param t Vessel to change selected target to.
	 */
	private void forceSelectedTarget(Vessel t) {
		PlatformUtil.getSelectionManager().setSelection(MVBasic.MAP_SELECTION_CTX, t);
	}

	/**
	 * Subscribes to selection change events (for example selecting another AIS
	 * target on the MapViewer) and updates the current selected model in the
	 * OwnshipLayer.
	 */
	private void addListeners() {

		SelectionManager sm = PlatformUtil.getSelectionManager();
		// Listen for selecton change events of the MapViewer and get the currently
		// selected Vessel.
		sm.subscribe(MVBasic.MAP_SELECTION_CTX, oo -> {
			UObject old = lastSelectedTarget;
			if (oo.isPresent() && oo.get() instanceof UObject) {
				lastSelectedTarget = (UObject) oo.get();
			} else {
				lastSelectedTarget = null;
			}

			if (old != lastSelectedTarget) {
				setDirty(true);
			}
		});

	}

	/**
	 * Draws a SN.1/Circ.243 compliant selection box for signaling the selection of the ownship object.
	 * @return Selection box.
	 */
	private Path2D getSelectedBox() {
		Path2D selectedTargetSymbol = new Path2D.Double();
		selectedTargetSymbol.append(new Line2D.Double(-15, 15, -5, 15), false);
		selectedTargetSymbol.append(new Line2D.Double(-15, 15, -15, 5), false);
		selectedTargetSymbol.append(new Line2D.Double(15, 15, 5, 15), false);
		selectedTargetSymbol.append(new Line2D.Double(15, 15, 15, 5), false);
		selectedTargetSymbol.append(new Line2D.Double(-15, -15, -15, -5), false);
		selectedTargetSymbol.append(new Line2D.Double(-15, -15, -5, -15), false);
		selectedTargetSymbol.append(new Line2D.Double(15, -15, 5, -15), false);
		selectedTargetSymbol.append(new Line2D.Double(15, -15, 15, -5), false);
		return selectedTargetSymbol;
	}

}
