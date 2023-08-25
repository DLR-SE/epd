package de.emir.epd.mapview.views.map;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import org.jxmapviewer.JXMapViewer;

import de.emir.epd.mapview.manager.MapViewManager;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;

/**
 * Used to pan using press and drag mouse gestures
 * @author joshy
 */
public class CustomPanMouseInputListener extends MouseInputAdapter
{
	private Point prev;
	private JXMapViewer viewer;
	private Cursor priorCursor;
	
	/**
	 * @param viewer the jxmapviewer
	 */
	public CustomPanMouseInputListener(JXMapViewer viewer)
	{
		this.viewer = viewer;
	}

	@Override
	public void mousePressed(MouseEvent evt)
	{
		if (!SwingUtilities.isLeftMouseButton(evt))
			return;

		prev = evt.getPoint();
		priorCursor = viewer.getCursor();
		viewer.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
	}

	@Override
	public void mouseDragged(MouseEvent evt)
	{
		if (!SwingUtilities.isLeftMouseButton(evt))
			return;

		Point current = evt.getPoint();
		double x = viewer.getCenter().getX();
		double y = viewer.getCenter().getY();

		if(prev != null){
				x += prev.x - current.x;
				y += prev.y - current.y;
		}

		if (!viewer.isNegativeYAllowed())
		{
			if (y < 0)
			{
				y = 0;
			}
		}

		int maxHeight = (int) (viewer.getTileFactory().getMapSize(viewer.getZoom()).getHeight() * viewer
				.getTileFactory().getTileSize(viewer.getZoom()));
		if (y > maxHeight)
		{
			y = maxHeight;
		}

		prev = current;
		viewer.setCenter(new Point2D.Double(x, y));

		List<IMapManuallyMovedListener> listeners =  ServiceManager.get(MapViewManager.class).getMapManuallyMovedListeners();
		
		for (IMapManuallyMovedListener l : listeners) {
			l.mapManuallyMoved();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent evt)
	{
		if (!SwingUtilities.isLeftMouseButton(evt))
			return;

		prev = null;
		viewer.setCursor(priorCursor);
	}
}
