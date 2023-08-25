package de.emir.epd.mapview.views.map;

import java.util.Timer;
import java.util.TimerTask;

import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.impl.TimeImpl;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.prop.internal.GenericProperty;

/**
 * The AbstractTimedMapLayer adds a timer (and an update frequency) as property and changes the dirty state
 * in regular intervalls to dirty, to trigger a repaint. 
 * This way there is no need to observe a model or parts of the model.
 * 
 * @note this layer does not provide any property pages for the LayerView but allows access to the UpdateTime Property
 * 
 * @warn if using the TimedMapLayer or using some observing mechanism may be critical regarding the performance. 
 * In general, if you expect high frequency updates of the relevant (observed) features, it may is a good idea to use a timed layer.
 * 
 * @author sschweigert
 *
 */
public abstract class AbstractTimedMapLayer extends AbstractMapLayer {

	
	
	protected class AbstractTimedMapLayerTimerTask extends TimerTask {
		@Override
		public void run() {
			markDirty();
		}
	}
	
	private Timer							mMapUpdateTimer = null;
	private GenericProperty<Time>			mUpdateTime = null;
	private AbstractTimedMapLayerTimerTask 	mUpdateTask; 

	protected AbstractTimedMapLayer() {
		this(1.0);
	}
	protected AbstractTimedMapLayer(double frequency) {
		this(new TimeImpl(1.0/frequency, TimeUnit.SECOND));
	}
	protected AbstractTimedMapLayer(Time updateInterval) {
		super();
		mUpdateTime = new GenericProperty<>("Layer Update Time", "delay between two repaints", true, updateInterval);
		mMapUpdateTimer = createNewTimer();
		mUpdateTask = createUpdateTask();
		long delay = (long)mUpdateTime.get().getAs(TimeUnit.MILLISECOND);
		mMapUpdateTimer.schedule(mUpdateTask, delay, delay);
		mUpdateTime.addPropertyChangeListener(pcl -> {
			//remove old scheduler
			mMapUpdateTimer.cancel();
			mMapUpdateTimer.purge();
			//create new and schedule
			mMapUpdateTimer = createNewTimer();
			mUpdateTask = createUpdateTask();
			long delay2 = (long)mUpdateTime.get().getAs(TimeUnit.MILLISECOND);
			mMapUpdateTimer.schedule(mUpdateTask, delay2, delay2);
		});
	}
	
	public void setUpdateTime(final Time time) {
		mUpdateTime.set(new TimeImpl(time));
	}
	public void setUpdateFrequency(final double frequency) {
		mUpdateTime.set(new TimeImpl(1.0 / frequency, TimeUnit.SECOND));
	}
	
	public IProperty getUpdateTimeProperty() {
		return mUpdateTime;
	}
	
	protected Timer createNewTimer() {
		return new Timer("TimedMapLayerUpdate_" + getClass().getName(), true);
	}
	protected AbstractTimedMapLayerTimerTask createUpdateTask() {
		return new AbstractTimedMapLayerTimerTask();
	}
	
	

}
