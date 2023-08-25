package de.emir.epd.mapview.ep;

import de.emir.epd.mapview.views.map.AbstractTileSource;

public class TileSource implements ITileSource {

	private String id;
	private Class<? extends AbstractTileSource> tileSourceClass;
	private String label;
	private String icon;

	public TileSource(String id) {
		this.id = id;
	}

	public void setTileSourceClass(Class<? extends AbstractTileSource> tsClass) {
		this.tileSourceClass = tsClass;
		
	}
	
	public Class<? extends AbstractTileSource> getTileSourceClass() {
		return tileSourceClass;
	}
	
	public String getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getIcon() {
		return icon;
	}
	
	/* (non-Javadoc)
	 * @see de.emir.epd.mapview.ep.ITileSource#label(java.lang.String)
	 */
	@Override
	public ITileSource label(String label) {
		this.label = label;
		return this;
	}
	
	/* (non-Javadoc)
	 * @see de.emir.epd.mapview.ep.ITileSource#icon(java.lang.String)
	 */
	@Override
	public ITileSource icon(String icon) {
		this.icon = icon;
		return this;
	}

}
