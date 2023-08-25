package de.emir.epd.mapview.views.map.cache;

import java.nio.file.Path;
import java.util.concurrent.CountDownLatch;

import de.emir.rcp.jobs.IJob;
import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;

public class LoadInfoJob implements IJob {

	private StoreableTileCache cache;
	private Path infoPath;

	private CountDownLatch latch;

	public LoadInfoJob(StoreableTileCache cache, Path infoPath, CountDownLatch latch) {
		this.cache = cache;
		this.infoPath = infoPath;
		this.latch = latch;
	}

	@Override
	public void run(IProgressMonitor monitor) {
		
		try {
			UriImageFileData uifd = CacheFolder.loadInfo(infoPath);
			
			if (uifd == null) {
				return;
			}
	
			Path cachePath = CacheFolder.getCachePath();
			
			Path imgPath = cachePath.resolve(uifd.getImageFile());

			cache.putURIFilePath(uifd.getUri(), imgPath);
			latch.countDown();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			latch.countDown();
			return ;
		}

	}

	@Override
	public boolean isBlocking() {
		return false;
	}

	@Override
	public boolean isCancelable() {
		return false;
	}

	@Override
	public void cancel() {

	}

	@Override
	public String getTitle() {
		return "Load Image Job";
	}

	@Override
	public boolean isBackground() {
		return false;
	}

}
