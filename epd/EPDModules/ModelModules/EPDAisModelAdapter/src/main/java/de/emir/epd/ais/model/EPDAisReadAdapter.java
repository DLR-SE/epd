package de.emir.epd.ais.model;

import java.util.Optional;

import de.emir.epd.model.EPDModel;
import de.emir.model.universal.physics.Environment;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class EPDAisReadAdapter implements IAisReadAdapter, ITreeValueChangeListener /*, Observer*/ {

	private BehaviorSubject<Optional<Object>> mainSubject = BehaviorSubject.createDefault(Optional.empty());

	// TODO: Remove this temporary reference only used for listening on on targetset
	// changes
	private Environment targetSet;

	public EPDAisReadAdapter() {

		AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();

		if (mp == null) {
			return;
		}

		mp.subscribeModel(c -> {
			
//			// TODO: Remove this observer structure. It is only necessary because target set uses this
//			// old observer way to notify about changes
			if (targetSet != null) {
//				targetSet.deleteObserver(this);
				targetSet.removeTreeListener(this);
			}
//
//			targetSet = getTargetSet();
//
//			if (targetSet != null) {
//				targetSet.addObserver(this);
//			}
			targetSet = getTargetSet();
			if (targetSet != null){
				targetSet.registerTreeListener(this);
			}

			mainSubject.onNext(Optional.ofNullable(targetSet));
		});

	}

	@Override
	public String getId() {
		return "de.emir.epd.ais.model.EPDAisReadAdapter";
	}

	@Override
	public String getName() {
		return "EPD AIS Read Adapter";
	}

	@Override
	public Environment getTargetSet() {
		AbstractModelProvider mp = PlatformUtil.getModelManager().getModelProvider();

		if (mp == null) {
			return null;
		}

		Object model = mp.getModel();

		if (model instanceof EPDModel == false) {
			return null;
		}

		return ((EPDModel) model).getAisTargetSet();
	}

	@Override
	public Disposable subscribeChanged(Consumer<Optional<Object>> c) {
		return mainSubject.subscribe(c);
	}

	@Override
	public void onValueChange(Notification<Object> notification) {
		mainSubject.onNext(Optional.ofNullable(getTargetSet()));
	}

}
