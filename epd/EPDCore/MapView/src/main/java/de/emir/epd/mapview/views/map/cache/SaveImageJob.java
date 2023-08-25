package de.emir.epd.mapview.views.map.cache;

import java.awt.image.BufferedImage;
import java.net.URI;

import de.emir.rcp.jobs.IJob;
import de.emir.tuml.ucore.runtime.progress.IProgressMonitor;

public class SaveImageJob implements IJob {

	private URI uri;
	private BufferedImage img;

	public SaveImageJob(URI uri, BufferedImage img) {
		this.uri = uri;
		this.img = img;
	}

	@Override
	public void run(IProgressMonitor monitor) {

		CacheFolder.saveImage(monitor, uri, img);

	}

	@Override
	public boolean isCancelable() {
		return false;
	}

	@Override
	public boolean isBlocking() {
		return false;
	}

	@Override
	public String getTitle() {
		return "Store Tile Image";
	}

	@Override
	public void cancel() {
	}

	@Override
	public boolean isBackground() {
		return false;
	}
}
