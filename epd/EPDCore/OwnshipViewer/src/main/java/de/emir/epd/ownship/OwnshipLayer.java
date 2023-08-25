package de.emir.epd.ownship;

import com.google.common.collect.Lists;
import de.emir.epd.ais.manager.AisTargetManager;
import de.emir.epd.ais.model.IAisReadAdapter;
import de.emir.epd.mapview.views.map.AbstractMapLayer;
import de.emir.epd.mapview.views.map.BufferingGraphics2D;
import de.emir.epd.mapview.views.map.IDrawContext;
import de.emir.epd.model.EPDModelUtils;
import de.emir.epd.model.ais.AisTarget;
import de.emir.epd.nmeasensor.NMEAVesselManager;
import de.emir.model.application.track.Track;
import de.emir.model.application.track.TrackPoint;
import de.emir.model.domain.maritime.vessel.Vessel;
import de.emir.model.universal.physics.Environment;
import de.emir.model.universal.physics.PhysicalObjectUtils;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.Pose;
import de.emir.model.universal.units.*;
import de.emir.rcp.manager.ModelManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;
import org.slf4j.Logger;

import java.awt.*;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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
	private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 11);

	private Polygon polyActiveAisTarget = new Polygon(new int[] { -6, 0, 6, -6 }, new int[] { 6, -12, 6, 6 }, 4);
	private Polygon polyCOG = new Polygon(new int[] { -6, 0, 6, -6 }, new int[] { 6, -12, 6, 6 }, 4);

	private ArrayList<Vessel> vessels = new ArrayList<>();
	private Vessel ownship;
	private BasicStroke normalStroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
	private BasicStroke cogStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1,
			new float[] { 5, 3 }, 0);
	private BasicStroke trueHeadingStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
	private BasicStroke trackStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

	private long lastDrawTime = -1;

	public OwnshipLayer() {
		lastDrawTime = System.currentTimeMillis();

		ModelManager mm = PlatformUtil.getModelManager();

		if (mm.getModelProvider() != null && mm.getModelProvider().getModel() != null)
			try {
				modelChanged();
			} catch (Exception e) {
				e.printStackTrace();
			}

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

		timeOutThread.setName("OwnshipLayerTimeoutThread");

		timeOutThread.start();

	}

	private List<TrackPoint> getTrackPoints(Track track) {
		List<TrackPoint> copy = null;
		synchronized (track.getElements()) {
			copy = new ArrayList<TrackPoint>(track.getElements());
		}
		return Lists.reverse(copy);
	}

	@Override
	public void paint(BufferingGraphics2D g, IDrawContext c) {

		if (ownship == null)
			return;

		Rectangle bounds = c.getBounds();

		Vessel v = ownship;
		Pose pose = v.getPose();
		Coordinate coord = pose.getCoordinate();

		double lat = coord.getLatitude();
		double lon = coord.getLongitude();

		Point2D px = c.convert(lon, lat);


		Track track = AisTarget.getTrack(v);
		if (track != null) {

			Point2D lastpoint = (Point2D) px.clone();

			for (TrackPoint tp : getTrackPoints(track)) {

				if (tp.getCoordinate() != null) {
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

		g.setColor(shipColor);
		AffineTransform transform = g.getTransform();
		g.translate(px.getX(), px.getY());

		int zoom = c.getZoom();

		boolean showCogSogHeading = zoom < 11;

		if (showCogSogHeading == false) {

			g.scale(4 / (double) zoom, 4 / (double) zoom);

		}

		String name = v.getNameAsString();
		if (name != null) {
			g.drawString(name, 0, 0);
		}

		g.setColor(shipColor);
		g.setStroke(normalStroke);

		g.drawOval(-9, -9, 18, 18);
		g.drawOval(-4, -4, 8, 8);

		long ts = AisTarget.getTimestamp(v) != null ? AisTarget.getTimestamp(v) : 0L;

		boolean lostTarget = System.currentTimeMillis() - ts >= 30000;

		double cogRad = 0;

		Angle course = PhysicalObjectUtils.getCOG(v);
		Speed speed = PhysicalObjectUtils.getSOG(v);

		if (course != null && speed != null) {
			float cog = (float) course.getAs(AngleUnit.DEGREE);
			float sog = (float) speed.getAs(SpeedUnit.KNOTS);

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

		}

		if (lostTarget) {
			g.drawLine(-10, 0, 10, 0);
		}

		g.rotate(-cogRad);

		if (PhysicalObjectUtils.getHeading(v) != null && lostTarget == false && showCogSogHeading == true) {
			int heading = (int) PhysicalObjectUtils.getHeading(v).getAs(AngleUnit.DEGREE);

			double headingRad = Math.toRadians(heading);

			// Draw true heading
			g.rotate(headingRad);
			g.setStroke(trueHeadingStroke);
			g.drawLine(0, 0, 0, -40);
			g.drawLine(-20, 0, -10, 0);
			g.drawLine(10, 0, 20, 0);

			g.rotate(-headingRad);

		} else {
			// Draw center point
			g.drawLine(0, 0, 0, 2);
		}

		g.setTransform(transform);

		lastDrawTime = System.currentTimeMillis();
	}

	@Override
	public boolean isVisibilityUserControlled() {
		return true;
	}

	@Override
	public void modelChanged() {		
		ownship = NMEAVesselManager.getOwnShip();
		registerOwnshipListener();

		IAisReadAdapter mra = ServiceManager.get(AisTargetManager.class).getModelReadAdapter();

		if (mra == null) {
			return;
		}

		mra.subscribeChanged(oo -> {
			Environment env = mra.getTargetSet();
			Vessel v = EPDModelUtils.getOwnship(env);
			synchronized(this) {
			if (ownship != v && v != null) {
//				System.err.println("ownship changed " + ownship + " to " + NMEAVesselManager.getOwnShip());
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
			}
			}
		});

		// We need a thread that sets the layer dirty 30 sec. after last draw
		startTimeoutCheckerThread();
	}
	
	private void registerOwnshipListener() {
	    if (ownship != null) {
	        ownship.registerListener(this);
	        try{
	            Pose pose = ownship.getPose();
	            if(pose != null){
	                Coordinate coordinate = pose.getCoordinate();
	                if (coordinate != null) {
	                    ownship.getPose().getCoordinate().registerListener(this);
	                }
	                Orientation orientation = pose.getOrientation();
	                if (orientation != null) {
	                    ownship.getPose().getOrientation().registerListener(this);
	                }
	            }
	            
	        }catch (Exception e) {
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

}
