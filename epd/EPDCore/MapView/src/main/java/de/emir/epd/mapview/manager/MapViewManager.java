package de.emir.epd.mapview.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jxmapviewer.viewer.GeoPosition;

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
	
	private final MapViewExtensionPoint ep = new MapViewExtensionPoint();

	private final List<IMapManuallyMovedListener> mapManMovedListeners = new ArrayList<>();
	
	private final Map<String, AbstractMapViewTool> tools = new HashMap<>();
	
	private final PublishSubject<String> toolCreatedSubject = PublishSubject.create();
	private final PublishSubject<Optional<AbstractMapViewTool>> activeToolSubject = PublishSubject.create();

	private final PublishSubject<GeoPosition> cursorPositionSubject = PublishSubject.create();

    private AbstractMapViewTool activeTool;
    private AbstractMapViewTool defaultTool;

    private boolean toolsInitialized = false;

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
			sourceInstance = sourceClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            ULog.error(e);
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
			ULog.warn("Tool with id [{}] can't be found.", toolID);
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
			ULog.warn("Tool with id [{}] can't be found.", toolID);
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
		
		if (toolsInitialized) {
			return;
		}
		toolsInitialized = true;
		
		Map<String, MapTool> epTools = ep.getTools();
		
		for (MapTool epTool : epTools.values()) {
			
			Class<? extends AbstractMapViewTool> toolClass = epTool.getToolClass();
			String id = epTool.getId();
			
			try {
				
				AbstractMapViewTool newTool = toolClass.getDeclaredConstructor().newInstance();
				newTool.setId(id);
				tools.put(id, newTool);
				toolCreatedSubject.onNext(id);
				
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                ULog.error("Can't create Tool. Error calling default constructor [{}].", id, e);
			}
        }
	}
	
	/**
	 * Use this method when the cursor position is changed.
	 * 
	 * @param position The GeoPosition containing the cursors world coordinates
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
