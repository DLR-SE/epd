package de.emir.epd.mapview.views.map;

public class MapViewerDrawRunnable implements Runnable {

	private static final int FPS = 40;

	private int sleepMS = 1000 / FPS;

	private boolean stop = false;

	private MapViewerWithTools viewer;

	public MapViewerDrawRunnable(MapViewerWithTools mapViewerWithTools) {
		viewer = mapViewerWithTools;
	}

	public void stop() {

		stop = true;

	}

	@Override
	public void run() {

		stop = false;

		while (stop == false) {

			long start = System.currentTimeMillis();

			// we do not want to redraw if nothing has changed, in this case we would simply
			// redraw what is already on the screen.
			if (needsRepaint()) {
				// Important! Set dirty false first
				viewer.setDirty(false);
				viewer.redrawLayers();
				viewer.getJXMapViewer().doPaint();

			}

			long end = System.currentTimeMillis();

			try {

				Thread.sleep(Math.max(0, sleepMS - (end - start)));

			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

	private boolean needsRepaint() {

		if (viewer.isDirty() == true) {
			return true;
		}

		for (LayerController lc : viewer.getLayerController()) {
			if (lc.isVisible() && lc.isDirty())
				return true;
		}

		LayerController lc = viewer.getActiveToolLayerController();

		if (lc != null) {
			if (lc.isVisible() && lc.isDirty()) {
				return true;
			}
		}

		return false;
	}

}
