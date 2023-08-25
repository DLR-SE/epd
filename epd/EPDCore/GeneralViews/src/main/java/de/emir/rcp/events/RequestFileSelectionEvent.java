package de.emir.rcp.events;

import java.io.File;
import java.util.List;

public class RequestFileSelectionEvent {

	private List<File> files;

	public RequestFileSelectionEvent(List<File> files) {
		this.files = files;
	}

	public List<File> getFiles() {
		return files;
	}
	
}
