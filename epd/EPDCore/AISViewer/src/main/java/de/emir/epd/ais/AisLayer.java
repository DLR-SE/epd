package de.emir.epd.ais;

import com.google.common.collect.Lists;
import de.emir.epd.ais.ids.AisBasics;
import de.emir.epd.ais.manager.AisTargetManager;
import de.emir.epd.ais.model.IAisReadAdapter;
import de.emir.epd.mapview.basics.utils.SetLayerDirtyPropertyChangeListener;
import de.emir.epd.mapview.ids.MVBasic;
import de.emir.epd.mapview.views.map.AbstractMapLayer;
import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.model.EPDModelUtils;
import de.emir.epd.model.ais.AisTarget;
import de.emir.model.application.track.Track;
import de.emir.model.application.track.TrackPoint;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.universal.crs.util.CRSUtils;
import de.emir.model.universal.physics.Environment;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.units.*;
import de.emir.model.universal.units.impl.DistanceImpl;
import de.emir.rcp.manager.MenuManager;
import de.emir.rcp.manager.SelectionManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AisLayer extends AbstractMapLayer implements Observer {

	protected Color shipColor = new Color(78, 78, 78);
	protected Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 11);

	protected Polygon polyActiveAisTarget = new Polygon(new int[] { -6, 0, 6, -6 }, new int[] { 6, -12, 6, 6 }, 4);

	protected Environment targetSet;
	protected BasicStroke polyActiveAisTargetStroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
	protected BasicStroke trueHeadingStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1,
			new float[] { 5, 3 }, 0);
	protected BasicStroke trackStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
	protected BasicStroke aisLookaheadThin = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

	protected BasicStroke selectionStroke = new BasicStroke(10);
	protected Color selectionColor = new Color(0, 100, 0, 50);
	protected Color trackColor = new Color(78, 78, 78, 96);
	protected Color focusColor = new Color(0, 114, 9);

	protected long lastDrawTime = -1;

	protected ArrayList<ShapeAISTarget> currentShapes = new ArrayList<>();

	protected Vessel lastFocusedTarget;
	protected Vessel lastSelectedTarget;
	protected IProperty<Boolean> propNames;
	protected IProperty<Boolean> propShowTimedOut;
	protected IProperty<Boolean> propTracks;
	protected IProperty<Boolean> propRoutes;
	protected IProperty<Integer> propTrackTimeout;
	protected IProperty<Long> propTrackCleartime;
	protected IProperty<Integer> propTargetLosttime;
	protected IProperty<Integer> propLookahead;

	protected JPopupMenu popupMenu;
	
	public AisLayer() {
		lastDrawTime = System.currentTimeMillis();

		modelChanged();
		addListeners();
		// We need a thread that sets the layer dirty 30 sec. after last draw
		startTimeoutCheckerThread();

		popupMenu = new JPopupMenu();
		MenuManager mm = PlatformUtil.getMenuManager();
		mm.fillPopup(popupMenu, AisBasics.AIS_VIEWER_POPUP_ID);

	}

	private void addListeners() {

		SelectionManager sm = PlatformUtil.getSelectionManager();
		
		sm.subscribe(MVBasic.MAP_SELECTION_CTX, oo -> {

			Vessel old = lastSelectedTarget;

			if (oo.isPresent() && oo.get() instanceof Vessel) {
				lastSelectedTarget = (Vessel) oo.get();
			} else {
				lastSelectedTarget = null;
			}

			if (old != lastSelectedTarget) {
				setDirty(true);
			}

		});

		sm.subscribe(MVBasic.MAP_FOCUS_CTX, oo -> {
			Vessel old = lastFocusedTarget;

			if (oo.isPresent() && oo.get() instanceof Vessel) {
				lastFocusedTarget = (Vessel) oo.get();
			} else {
				lastFocusedTarget = null;
			}

			if (old != lastFocusedTarget) {
				setDirty(true);
			}
		});

		PropertyContext ctx = PropertyStore.getContext(AisBasics.AIS_VIEWER_PROP_CONTEXT);
		propNames = ctx.getProperty(AisBasics.AIS_VIEWER_PROP_SHOW_NAMES, true);
		propShowTimedOut = ctx.getProperty(AisBasics.AIS_VIEWER_PROP_SHOW_TIMED_OUT, true);
		propTracks = ctx.getProperty(AisBasics.AIS_VIEWER_PROP_SHOW_TRACKS, true);
		propRoutes = ctx.getProperty(AisBasics.AIS_VIEWER_PROP_SHOW_INTENDED_ROUTES, true);
		propTrackTimeout = ctx.getProperty(AisBasics.AIS_VIEWER_PROP_TRACK_TIMEOUT, 10);
        propTrackCleartime = ctx.getProperty(AisBasics.AIS_VIEWER_PROP_TRACK_CLEARTIME, 0L);
        propTargetLosttime = ctx.getProperty(AisBasics.AIS_VIEWER_PROP_TARGET_LOSTTIME, 30);
        propLookahead = ctx.getProperty(AisBasics.AIS_VIEWER_PROP_LOOKAHEAD, 6);

		propNames.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
		propShowTimedOut.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
		propTracks.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
		propRoutes.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
		propTrackTimeout.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
        propTrackCleartime.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
        propTargetLosttime.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
        propLookahead.addPropertyChangeListener(new SetLayerDirtyPropertyChangeListener(this));
	}

	private void startTimeoutCheckerThread() {

		Thread timeOutThread = new Thread(() -> {

			long sleepTimeMS = 29_000;

			while (true) {
				setDirty(true);
				long sleep = sleepTimeMS - (System.currentTimeMillis() - lastDrawTime);

				if (sleep > 0){
					try {
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
						ULog.error(e);
					}
				}
			}
		});

		timeOutThread.setName("AisLayerTimeoutThread");

		timeOutThread.start();

	}
	
	protected List<TrackPoint> getTrackPoints(Track track) {
		List<TrackPoint> copy = null;
		synchronized(track.getElements()) {
			copy = new ArrayList<TrackPoint>(track.getElements());
		}
		return Lists.reverse(copy);
	}

	@Override
	public void paint(BufferingGraphics2D g, IDrawContext c) {
		if (targetSet == null || EPDModelUtils.getAisTargets(targetSet) == null)
			return;
		ArrayList<ShapeAISTarget> shapes = new ArrayList<>();
		Rectangle bounds = c.getBounds();

		for (Vessel v : EPDModelUtils.getAisTargets(targetSet)) {
			if (v == null || v.getPose() == null || v.getPose().getCoordinate() == null)
				continue;
			long ts = AisTarget.getTimestamp(v) != null ? AisTarget.getTimestamp(v) : 0L;
			
			boolean lostTarget = System.currentTimeMillis() - ts >= (long) (propTargetLosttime.getValue() * 1000);
			boolean showNames = propNames.getValue();
			boolean showTimedOut = propShowTimedOut.getValue();
			boolean showTracks = propTracks.getValue();

			if (showTimedOut == false && lostTarget == true) {
				continue;
			}

			double lat = v.getPose().getCoordinate().getLatitude();
			double lon = v.getPose().getCoordinate().getLongitude();

			Point2D px = c.convert(lon, lat);

			// draw historic positions of a vessel
			if (showTracks) {

				Track track = AisTarget.getTrack(v);
				if(track != null) {

					// last point to draw lines between points
					Point2D lastpoint = (Point2D) px.clone();

					for (TrackPoint tp : getTrackPoints(track)) {

						if (tp.getCoordinate() != null) {
							long tpTime = (long) tp.getTime().getAs(TimeUnit.MILLISECOND);
							long diff = System.currentTimeMillis() - tpTime;

							// check if we want to draw this point based on track timeout property
							if ((diff > propTrackTimeout.getValue() * 60 * 1000) || tpTime < propTrackCleartime.getValue()) {
								continue;
							}

							g.setColor(trackColor);
							g.setStroke(trackStroke);
							Point2D point = c.convert(
								tp.getCoordinate().getLongitude(),
								tp.getCoordinate().getLatitude()
							);

							Line2D line = new Line2D.Double(
								lastpoint.getX(), lastpoint.getY(),
								point.getX(), point.getY()
							);

							// check if line is visible, otherwise ignore
							if (line.intersects(bounds)){
								g.draw(line);
							}
							// check if point is visible, otherwise ignore
							if (bounds.contains(point)){
								g.fillOval((int) point.getX() - 2, (int) point.getY() - 2, 4, 4);
							}
							lastpoint = (Point2D) point.clone();
						}

					}
				}
			}
			
			int zoom = c.getZoom();
			
			g.setColor(shipColor);
			AffineTransform transform = g.getTransform();
			g.translate(px.getX(), px.getY());
			
			boolean showCogSogHeading = zoom < 11;

			if (showCogSogHeading == false) {
				g.scale(4 / (double) zoom, 4 / (double) zoom);
			}

			// Draw name
			String name = v.getNameAsString();
			if (name != null && showNames == true && zoom < 9) {

				int offY = 20;

				if (PhysicalObjectUtils.getCOG(v) != null) {
					float cog = (float) PhysicalObjectUtils.getCOG(v).getAs(AngleUnit.DEGREE);

					if (cog > 90 && cog < 270) {

						offY = -15;

					}

				}
				FontMetrics metrics = g.getFontMetrics(font);
				int offX = -metrics.stringWidth(name) / 2;

				g.drawString(name, offX, offY);

			}

			g.setStroke(polyActiveAisTargetStroke);

			double cogRad = 0;

			Angle course = PhysicalObjectUtils.getCOG(v);
			Speed speed = PhysicalObjectUtils.getSOG(v);

			// draw lookahead
			if (course != null && speed != null) {
				float cog = (float) course.getAs(AngleUnit.DEGREE);
				float sog = (float) speed.getAs(SpeedUnit.KNOTS);

				if (sog < 0.5) {
					g.setColor(Color.GRAY);
				}

				cogRad = Math.toRadians(cog);
				g.rotate(cogRad);

				if (!lostTarget && showCogSogHeading && zoom < 8 && Float.compare(sog, 102.3f) != 0) {
					int vec = propLookahead.getValue();
					double[] nleft = CRSUtils.getTarget(
							new double[] {lat,  lon},
							new DistanceImpl(sog, DistanceUnit.NAUTICAL_MILES)
									.getAs(DistanceUnit.METER) / 60,
							cogRad,
							CRSUtils.WGS84_2D
					);
					GeoPosition dest = new GeoPosition(nleft);
					Point2D lp = c.convert(dest);
					
					double x = px.getX() - lp.getX();
					double y = px.getY() - lp.getY();

					double distance = Math.sqrt(Math.pow((x), 2) + Math.pow((y), 2));
					if ((int) distance > 0) {
						int spdv = (int) distance;
						int veclen = (spdv * vec) -12;

						g.setStroke(aisLookaheadThin);
						g.drawLine(0, -12, 0, -12 - veclen);
						g.drawLine(0, -12 - veclen, 4, -8 - veclen);
						g.drawLine(0, -12 - veclen, -4, -8 - veclen);
						for (int i = 1; i < vec; i++) {
							int pos = (int) (veclen - (spdv * i));
							g.drawLine(-2, -12 - pos, 2, -12 - pos);
						}
					}
				}
			}

			shapes.add(
					new ShapeAISTarget(
							g.getTransform().createTransformedShape(polyActiveAisTarget),
							v
					)
			);

			if (lastFocusedTarget == v) {
				g.setColor(focusColor);
			}

			// Draw ship
			if (zoom < 11) {
				g.drawPolygon(polyActiveAisTarget);
			} else {
				g.drawOval(-4, -4, 8, 8);
			}

			if (lostTarget) {
				g.drawLine(-10, 0, 10, 0);
			}

			g.rotate(-cogRad);

			if (v.getPose().getOrientation() != null && lostTarget == false && showCogSogHeading == true) {
				int heading = (int) v.getPose().getOrientation().toEuler().getZ().getAs(AngleUnit.DEGREE);

				double headingRad = Math.toRadians(heading);

				// Draw true heading
				g.rotate(headingRad);
				g.setStroke(trueHeadingStroke);
				g.drawLine(0, 0, 0, -50);

				// IProperty rotProp = v.getProperty(NMEAFieldIds.NMEA_RATE_OF_TURN);

				AngularSpeed rateOfTurn = PhysicalObjectUtils.getRateOfTurn(v);
				if (rateOfTurn != null) {

					g.setStroke(polyActiveAisTargetStroke);
					// Rate of turn
					int rot = (int) rateOfTurn.getAs(AngularSpeedUnit.DEGREES_PER_MINUTE);

					// 128 := not available
					if (rot >= -127 && rot <= 127) {
						g.drawLine(0, -50, rot, -50);
					}

				}

				g.rotate(-headingRad);

			} else {
				// Draw center point
				g.drawLine(0, 0, 0, 2);
			}

			if (lastSelectedTarget == v) {
				g.setColor(selectionColor);
				g.setStroke(selectionStroke);
				g.drawArc(
						-20, -20,
						40, 40,
						0, 360
				);
			}

			g.setTransform(transform);

		}

		lastDrawTime = System.currentTimeMillis();
		currentShapes = shapes;
	}

	@Override
	public boolean isVisibilityUserControlled() {
		return true;
	}

	@Override
	public void modelChanged() {

		IAisReadAdapter mra = ServiceManager.get(AisTargetManager.class).getModelReadAdapter();

		if (mra == null) {
			return;
		}

		mra.subscribeChanged(oo -> {
			targetSet = mra.getTargetSet();
			setDirty(true);
		});

	}

	@Override
	public void update(Observable o, Object arg) {
		setDirty(true);

	}

	public class ShapeAISTarget {

		public Shape shape;
		public Vessel vessel;

		public ShapeAISTarget(Shape shape, Vessel vessel) {
			this.shape = shape;
			this.vessel = vessel;
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		for (ShapeAISTarget sat : currentShapes) {

			if (sat.shape.intersects(e.getX(), e.getY(), 3, 3)) {
				setFocusedTarget(sat.vessel);
				e.consume();
				return;
			}

		}

		setFocusedTarget(null);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if(e.getButton() == MouseEvent.BUTTON3){
			for (ShapeAISTarget sat : currentShapes) {
				if (sat.shape.intersects(e.getX(), e.getY(), 3, 3)) {
					PlatformUtil.getSelectionManager().setSelection(AisBasics.AIS_SELECTION_ID, sat.vessel);
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
					return;
				}

			}
        } else {
			for (ShapeAISTarget sat : currentShapes) {

				if (sat.shape.intersects(e.getX(), e.getY(), 3, 3)) {
					setSelectedTarget(sat.vessel);
					e.consume();
					return;
				}

			}

			setSelectedTarget(null);
		}
	}

	private void setSelectedTarget(Vessel t) {

		if (t == lastSelectedTarget) {
			return;
		}

		PlatformUtil.getSelectionManager().setSelection(MVBasic.MAP_SELECTION_CTX, t);

	}

	private void setFocusedTarget(Vessel t) {

		if (t == lastFocusedTarget) {
			return;
		}

		PlatformUtil.getSelectionManager().setSelection(MVBasic.MAP_FOCUS_CTX, t);

		if (t != null) {
			cursorAdapter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		setFocusedTarget(null);
	}

}
