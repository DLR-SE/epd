package de.emir.epd.mapview.views.map.cache;

import de.emir.rcp.jobs.IJob;
import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;

public class DeleteTileCacheJob implements IJob {

	@Override
	public void run(IProgressMonitor monitor) {

		CacheFolder.clearTileCache(monitor);

	}

	@Override
	public boolean isBlocking() {
		return true;
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
		return "Delete Cached Tiles";
	}

	@Override
	public boolean isBackground() {
		return false;
	}

}
