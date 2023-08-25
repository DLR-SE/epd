package de.emir.epd.mapview.views.map.cache;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.thoughtworks.xstream.XStream;

import de.emir.epd.mapview.ids.MVBasic;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;

public class CacheFolder {

	private static XStream xs = new XStream();
	private static PropertyContext ctx = PropertyStore.getContext(MVBasic.MAP_VIEW_PROP_CONTEXT);

	static {
        xs.allowTypes(new Class[] {de.emir.epd.mapview.views.map.cache.UriImageFileData.class});
		xs.setClassLoader(CacheFolder.class.getClassLoader());
	}
	
	public static int fileCount = 0;

	// There can't be two cache folders with the same path so this can be static
	// without any problems
	public static ReentrantLock lock = new ReentrantLock(true);

	public static Path getCachePath() {
		ResourceManager rmgr = ResourceManager.get(StoreableTileCache.class);
		return rmgr.getHomePath().resolve(".tilecache");
	}

	public static void saveImage(IProgressMonitor monitor, URI uri, BufferedImage img) {

		if (CacheFolder.lock.isLocked() == true) {
			// We have only one writing and one deleting thread, so if it is locked, the
			// cache currently gets dropped by the delete thread. This happens if the tile
			// source changes, and we can terminate this job

			return;
		}

		CacheFolder.lock.lock();

		try {
			Path cachePath = getCachePath();

			if (cachePath.toFile().getAbsoluteFile().exists() == false) {

				cachePath.toFile().getAbsoluteFile().mkdirs();

			}

			int maxCachedTiles = ctx.getValue(MVBasic.MAP_VIEW_PROP_MAX_HARD_DRIVE_CACHED_TILES, 500);

			while(fileCount >= maxCachedTiles) {

				deleteOldestTile();

			}

			String hashedURI = getHashedURI(uri);

			Path out = cachePath.resolve(getImageName(hashedURI));
			Path outInfo = cachePath.resolve(getInfoName(hashedURI));

			cachePath.toFile().listFiles();

			ImageIO.write(img, "png", out.toFile());

			FileOutputStream os = new FileOutputStream(outInfo.toFile());
			xs.toXML(new UriImageFileData(uri, getImageName(hashedURI)), os);
			os.close();
			fileCount++;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			CacheFolder.lock.unlock();
		}
	}

	private static void deleteOldestTile() {

		try {
			Optional<Path> oldest = Files.list(getCachePath()).filter(n -> n.toString().endsWith(".tic"))
					.min(Comparator.comparingLong(f -> f.toFile().lastModified()));

			if (oldest.isPresent()) {

				UriImageFileData uifd = loadInfoWithoutCounting(oldest.get());
				
				if(uifd != null) {
					String imgFilePathPart = uifd.getImageFile();
					Path imgPath = getCachePath().resolve(imgFilePathPart);
					
					if(imgPath.toFile().exists() == true) {
						
						Files.delete(imgPath);
						
					}
					
				}
				
				Files.delete(oldest.get());
				
			}
			fileCount--;

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static UriImageFileData loadInfo(Path infoPath) {

		UriImageFileData uifd = loadInfoWithoutCounting(infoPath);
		fileCount++;
		return uifd;

	}
	
	public static UriImageFileData loadInfoWithoutCounting(Path infoPath) {
		return (UriImageFileData) xs.fromXML(infoPath.toFile());
	}

	public static void clearTileCache(IProgressMonitor monitor) {

		monitor.setMessage("Deleting cached tiles...");

		try {
			//waits a few seconds until cache folder is unlocked again
			boolean success = CacheFolder.lock.tryLock(3,TimeUnit.SECONDS);
			if (success == false){
				throw new RuntimeException("Cannot delete tilecaches, its locked by another thread");
			}
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		
		// Drop cache
        Path cachePath = getCachePath();

        if (cachePath.toFile().exists() == false) {
            CacheFolder.lock.unlock();
            return;
        }

		try {
			try {

				Object[] paths = Files.walk(cachePath).toArray();

				monitor.setProgress(0);

				int i = 0;

				for (Object o : paths) {

					try {
						Path p = (Path) o;

						if (p.toFile().exists()) {
							FileUtils.forceDelete(p.toFile());
						}

						i++;
						monitor.setProgress(i / (float) paths.length * 100);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			} catch (IOException e1) {

				e1.printStackTrace();
			}

			try {
				FileUtils.deleteDirectory(cachePath.toFile());
				CacheFolder.fileCount = 0;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			CacheFolder.lock.unlock();
		}
	}

	private static String getInfoName(String hash) {
		return "info" + hash + ".tic";
	}

	private static String getImageName(String hash) {
		return "img" + hash + ".png";
	}

	private static String getHashedURI(URI uri) {
		return uri.toString().replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
	}
}
