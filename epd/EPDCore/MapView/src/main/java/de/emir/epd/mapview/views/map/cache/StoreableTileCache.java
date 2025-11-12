package de.emir.epd.mapview.views.map.cache;

import de.emir.epd.mapview.ids.MVBasic;
import de.emir.rcp.jobs.IJob;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.progress.NullProgressMonitor;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class StoreableTileCache extends CustomTileCache {

	private static final int LOADER_THREAD_COUNT = 5;

	private Thread saveThread;

	private Map<URI, Path> fileMap = new HashMap<>();

	private PropertyContext propCtx = PropertyStore.getContext(MVBasic.MAP_VIEW_PROP_CONTEXT);

	private IProperty cacheProp = propCtx.getProperty(MVBasic.MAP_VIEW_PROP_CACHE_TILES_ON_HARD_DRIVE, false);
	private PropertyChangeListener cachePropListener;

	private BlockingQueue<IJob> saveJobQueue = new LinkedBlockingQueue<>();
	private BlockingQueue<IJob> loadJobQueue = new LinkedBlockingQueue<>();

	public StoreableTileCache() {

		saveThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {

					try {
						IJob job = saveJobQueue.take();
						job.run(new NullProgressMonitor());
					} catch (InterruptedException e) {
						ULog.info("Job interrupted. " + e.getMessage());
					}

				}

			}
		});
		saveThread.setName("TileCacheSaveThread");
		saveThread.start();

		load();

		cachePropListener = p -> {

			if ((boolean) p.getNewValue() == false) {
				PlatformUtil.getJobManager().schedule(new DeleteTileCacheJob());
			}

		};

		cacheProp.addPropertyChangeListener(cachePropListener);
	}

	@Override
	public void put(URI uri, byte[] bimg, BufferedImage img) {
		super.put(uri, bimg, img);

		if ((boolean) cacheProp.getValue() == false) {
			return;
		}

		try {
			saveJobQueue.put(new SaveImageJob(uri, img));
		} catch (InterruptedException e) {

		}

	}

	/**
	 * We need to reload the info files otherwise the cache would stop using our
	 * hard drive cached tiles after dropping the map
	 */
	public void needMoreMemory() {
		imgmap.clear();
		load();
	}

	public void putWithoutSaving(URI uri, byte[] bimg, BufferedImage img) {
		super.put(uri, bimg, img);
	}

	@Override
	public BufferedImage get(URI uri) throws IOException {

		if (fileMap.containsKey(uri) == true) {

			try {

				byte[] imgBytes = IOUtils.toByteArray(new FileInputStream(fileMap.remove(uri).toFile()));

				BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgBytes));

				if (img != null) {
					putWithoutSaving(uri, imgBytes, img);
					return img;
				}
			} catch (Exception e2) {

			}
		}

		return super.get(uri);
	}

	public void load() {

		if ((boolean) cacheProp.getValue() == false) {
			return;
		}

		Path folderPath = CacheFolder.getCachePath();

		for (int i = 0; i < LOADER_THREAD_COUNT; i++) {
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {

						try {
							IJob job = loadJobQueue.take();
							job.run(new NullProgressMonitor());
						} catch (InterruptedException e) {

						}

					}
				}
			});
			t.start();
		}

		try {

			Object[] paths = Files.walk(folderPath).filter(n -> n.toString().endsWith(".tic")).toArray();
			CountDownLatch latch = new CountDownLatch(paths.length);
			for (Object path : paths) {
				try {
					loadJobQueue.put(new LoadInfoJob(this, (Path) path, latch));
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}

			latch.await();

		} catch (IOException | InterruptedException e) {

		}

	}

	public void dispose() {
		cacheProp.removePropertyChangeListener(cachePropListener);

	}

	public void putURIFilePath(URI uri, Path imgPath) {
		fileMap.put(uri, imgPath);
	}

}
