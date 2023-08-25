package de.emir.epd.mapview.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jxmapviewer.viewer.GeoPosition;
import org.slf4j.Logger;

import de.emir.epd.mapview.ep.MapTool;
import de.emir.epd.mapview.ep.MapViewExtensionPoint;
import de.emir.epd.mapview.ep.TileSource;
import de.emir.epd.mapview.views.map.AbstractTileSource;
import de.emir.epd.mapview.views.map.IMapManuallyMovedListener;
import de.emir.epd.mapview.views.tools.AbstractMapViewTool;
import de.emir.tuml.ucore.runtime.extension.IService;
import de.emir.tuml.ucore.runtime.logging.ULog;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class MapViewManager implements IService {
	
	private MapViewExtensionPoint ep = new MapViewExtensionPoint();
	private AbstractMapViewTool activeTool;
	private AbstractMapViewTool defaultTool;
	
	private List<IMapManuallyMovedListener> mapManMovedListeners = new ArrayList<>();
	
	private Map<String, AbstractMapViewTool> tools = new HashMap<>();
	
	private static Logger log = ULog.getLogger(MapViewManager.class);

	private PublishSubject<String> toolCreatedSubject = PublishSubject.create();
	private PublishSubject<Optional<AbstractMapViewTool>> activeToolSubject = PublishSubject.create();
	
	private boolean toolsInitialized = false;
	
	private PublishSubject<GeoPosition> cursorPositionSubject = PublishSubject.create();
	
	public MapViewExtensionPoint getExtensionPoint() {
		return ep;
	}

	public AbstractTileSource getTileSourceInstance(String id) {

		Map<String, TileSource> sources = ep.getTileSources();

		TileSource source = sources.get(id);

		if (source == null) {
			return null;
		}

		Class<? extends AbstractTileSource> sourceClass = source.getTileSourceClass();

		AbstractTileSource sourceInstance = null;
		try {
			sourceInstance = sourceClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {

			e.printStackTrace();
		}

		return sourceInstance;
	}

	public TileSource getTileSource(String id) {
		Map<String, TileSource> sources = ep.getTileSources();

		return sources.get(id);
	}
	
	public void setActiveTool(String toolID) {
		
		AbstractMapViewTool tool = getTools().get(toolID);
		
		if(tool == null) {
			log.warn("Tool with id [" + toolID + "] can't be found.");
		}
		
		setActiveTool(tool);
		
	}
	
	public void setActiveTool(AbstractMapViewTool tool) {
		if(activeTool != null) {
			activeTool.deactivate();
		}
		
		activeTool = tool;
		
		if(activeTool != null) {
			activeTool.activate();
		}
		
		activeToolSubject.onNext(Optional.ofNullable(tool));
	}
	
	public AbstractMapViewTool getActiveTool() {
		return activeTool;
	}
	
	public void setDefaultTool(String toolID) {
		
		AbstractMapViewTool tool = getTools().get(toolID);
		
		if(tool == null) {
			log.warn("Tool with id [" + toolID + "] can't be found.");
		}
		
		setDefaultTool(tool);
		
	}
	
	public void setDefaultTool(AbstractMapViewTool tool) {
		defaultTool = tool;
	}
	
	public AbstractMapViewTool getDefaultTool() {
		return defaultTool;
	}
	
	public Disposable subscribeActiveToolChanged(Consumer<Optional<AbstractMapViewTool>> c) {
		return activeToolSubject.subscribe(c);
	}
	
	public void addMapManuallyMovedListener(IMapManuallyMovedListener l) {
		mapManMovedListeners.add(l);
	}
	
	public void removeMapManuallyMovedListener(IMapManuallyMovedListener l) {
		mapManMovedListeners.remove(l);
	}
	
	public List<IMapManuallyMovedListener> getMapManuallyMovedListeners() {
		return mapManMovedListeners;
	}
	
	public Map<String, AbstractMapViewTool> getTools() {
		return tools;
	}
	
	public Disposable subscribeToolCreated(Consumer<String> c) {
		return toolCreatedSubject.subscribe(c);
	}
	
	public void fillToolsList() {
		
		if(toolsInitialized == true) {
			return;
		}
		toolsInitialized = true;
		
		Map<String, MapTool> epTools = ep.getTools();
		
		for (MapTool epTool : epTools.values()) {
			
			Class<? extends AbstractMapViewTool> toolClass = epTool.getToolClass();
			String id = epTool.getId();
			
			try {
				
				AbstractMapViewTool newTool = toolClass.newInstance();
				newTool.setId(id);
				tools.put(id, newTool);
				toolCreatedSubject.onNext(id);
				
			} catch (InstantiationException | IllegalAccessException e) {
				
				log.error("Can't create Tool. Error calling default constructor [" + id + "].");
				
				e.printStackTrace();
			}
			
		}
		
	}
	
	/**
	 * Use this method when the cursor position is changed.
	 * 
	 * @param position The GeoPosition containting the cursors world coordinates
	 */
	public void setCursorPosition(GeoPosition position) {
		cursorPositionSubject.onNext(position);
	}
	
	/**
	 * Subscribe to the cursor position updates.
	 * 
	 * @param c the consumer to deliver the updated GeoPosition
	 * @return the subscription
	 */
	public Disposable subscribeCursorPosition(Consumer<GeoPosition> c) {
		return cursorPositionSubject.subscribe(c);
	}
	
	
}
