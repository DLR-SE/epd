package de.emir.rcp.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import de.emir.tuml.ucore.runtime.extension.IService;
import de.emir.tuml.ucore.runtime.logging.ULog;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class SelectionManager implements IService {

    private Map<String, PublishSubject<Optional<Object>>> contexts = new HashMap<>();

    public static final String GLOBAL = "GlobalSelectionContext";

    private Map<String, Object> lastEmitted = new HashMap<String, Object>();

    public Disposable subscribe(String contextID, Consumer<Optional<Object>> selectionObserver) {
        PublishSubject<Optional<Object>> selectionContext = getSelection(contextID);
        return selectionContext.subscribe(selectionObserver);
    }

    public Disposable subscribe(Consumer<Optional<Object>> selectionObserver) {
        return subscribe(GLOBAL, selectionObserver);
    }

    private PublishSubject<Optional<Object>> getSelection(String contextID) {

        PublishSubject<Optional<Object>> selectionContext = contexts.get(contextID);

        if (selectionContext == null) {

            selectionContext = PublishSubject.create();
            selectionContext.doOnError(new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    ULog.error(throwable.getMessage());
                }
            });
            contexts.put(contextID, selectionContext);

        }

        return selectionContext;
    }

    public Object getSelectedObject(String contextID) {
        return lastEmitted.get(contextID);
    }

    public Object getSelectedObject() {
        return getSelectedObject(GLOBAL);
    }

    public Object getFirstSelectedObject(String contextID) {

        Object lastSelected = getSelectedObject(contextID);

        if (lastSelected instanceof List) {

            if (((List<?>) lastSelected).size() == 0) {
                return null;
            }

            return ((List<?>) lastSelected).get(0);

        } else {

            return lastSelected;

        }

    }

    public List<?> getSelectedObjectAsList(String contextID) {
        Object lastSelected = getSelectedObject(contextID);

        if (lastSelected instanceof List) {
            return (List<?>) lastSelected;

        } else if (lastSelected == null) {
            return new ArrayList<>();
        } else {

            ArrayList<Object> tmp = new ArrayList<>();
            tmp.add(lastSelected);
            return tmp;
        }
    }

    public List<?> getSelectedObjectAsList() {
        return getSelectedObjectAsList(GLOBAL);
    }

    public void setSelection(Object value) {
        PublishSubject<Optional<Object>> globalSelection = getSelection(GLOBAL);
        lastEmitted.put(GLOBAL, value);
        globalSelection.onNext(Optional.ofNullable(value));
    }

    public void setSelection(String contextID, Object value) {
        PublishSubject<Optional<Object>> selection = getSelection(contextID);

        lastEmitted.put(contextID, value);
        try {
            selection.onNext(Optional.ofNullable(value));
        } catch (Exception e) {
            ULog.warn("Unhandeled error while selection.");
        }

        if (GLOBAL.equals(contextID) == false) {
            setSelection(value);
        }
    }

    public Object getFirstSelectedObject() {
        return getFirstSelectedObject(GLOBAL);
    }

}
