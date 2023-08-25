package de.emir.epd.mapview.views.map.cache;

import java.net.URI;

public class UriImageFileData {
	private URI uri;
	private String imageFile;

	public UriImageFileData() {
		
	}
	
	public UriImageFileData(URI uri, String imageFile) {
		this.uri = uri;
		this.imageFile = imageFile;
	}
	
	public URI getUri() {
		return uri;
	}
	
	public String getImageFile() {
		return imageFile;
	}
	
	public void setUri(URI uri) {
		this.uri = uri;
	}
	
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	
	
}
