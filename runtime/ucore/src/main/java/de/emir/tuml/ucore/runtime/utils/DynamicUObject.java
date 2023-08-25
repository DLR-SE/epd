package de.emir.tuml.ucore.runtime.utils;

import java.beans.PropertyChangeListener;
import java.util.Collection;

import de.emir.tuml.ucore.runtime.IDelegateInterface;
import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.IValueChangeListener;
import de.emir.tuml.ucore.runtime.UAssociationType;
import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UOperation;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import de.emir.tuml.ucore.runtime.utils.internal.TreeObserverUtil.ITreeObserverOptions;
import de.emir.tuml.ucore.runtime.utils.internal.TreeObserverUtil.TreeObserverOptions;

public class DynamicUObject implements UObject {

    @Override
    public UClass getUClassifier() {
        // TODO
        return null;
    }

    @Override
    public UObject getUContainer() {
        // TODO
        return null;
    }

    @Override
    public UStructuralFeature getUContainingFeature() {
        // TODO
        return null;
    }

    @Override
    public boolean needNotification(UStructuralFeature _feature) {
        //TODO
        return false;
    }

    @Override
    public Object unset(UStructuralFeature _feature) {
        //TODO
        return null;
    }

    @Override
    public Object uGet(UStructuralFeature _feature) {
        //TODO
        return null;
    }

    @Override
    public void uSet(UStructuralFeature _feature, Object _value) {
        //TODO

    }

    @Override
    public void uAdd(UStructuralFeature _feature, Object _value) {
        //TODO

    }

    @Override
    public void uAdd(UStructuralFeature _feature, int _index, Object _value) {
        //TODO

    }

    @Override
    public Object uGet(UStructuralFeature _feature, int _index) {
        return null;
    }

    @Override
    public IDisposable registerListener(IValueChangeListener _listener) {
        //TODO
        return null;
    }

    @Override
    public IDisposable registerListener(UStructuralFeature _feature, IValueChangeListener _listener) {
        //TODO
        return null;
    }

    @Override
    public void removeListener(IValueChangeListener _listener) {
        //TODO

    }

    @Override
    public void removeListener(UStructuralFeature _feature, IValueChangeListener _listener) {
        //TODO

    }

    @Override
    public void removeAllListener(UStructuralFeature _feature) {
        //TODO

    }

    @Override
    public void removeAllClassifierListener() {
        //TODO

    }

    @Override
    public void removeClassifierListener(IValueChangeListener _listener) {
        //TODO

    }

    @Override
    public Iterable<UObject> getContentIterator(UAssociationType type) {
        //TODO
        return null;
    }

    @Override
    public Iterable<UObject> getContentIterator() {
        //TODO
        return null;
    }

    @Override
    public <T extends IDelegateInterface> T getDelegate() {
        //TODO
        return null;
    }

    @Override
    public IProperty addProperty(String qualifiedPropertyName, String description) {
        //TODO
        return null;
    }

    @Override
    public IProperty addProperty(String qualifiedPropertyName, String description, boolean editable) {
        //TODO
        return null;
    }

    @Override
    public IProperty removeProperty(IProperty property) {
        //TODO
        return null;
    }

    @Override
    public IProperty getProperty(String qualifiedPropertyName) {
        //TODO
        return null;
    }

    @Override
    public boolean hasProperty(String qualifiedPropertyName) {
        //TODO
        return false;
    }

    @Override
    public void setPropertyValue(String qualifiedPropertyName, String value) {
        //TODO

    }

    @Override
    public void setPropertyValue(String qualifiedPropertyName, Number value) {
        //TODO

    }

    @Override
    public void setPropertyValue(String qualifiedPropertyName, boolean value) {
        //TODO

    }

    @Override
    public String getPropertyValueAsString(String qualifiedPropertyName) {
        //TODO
        return null;
    }

    @Override
    public <T extends Number> T getPropertyValueAsNumber(String qualifiedPropertyName) {
        //TODO
        return null;
    }

    @Override
    public boolean getPropertyValueAsBoolean(String qualifiedPropertyName) {
        //TODO
        return false;
    }

    @Override
    public Collection<IProperty> getAllProperties() {
        //TODO
        return null;
    }

    @Override
    public IDisposable registerTreeListener(ITreeValueChangeListener listener) {
        //TODO
        return null;
    }

    @Override
    public void removeTreeListener(ITreeValueChangeListener listener) {
        //TODO

    }

    @Override
    public IDisposable registerTreeListener(ITreeValueChangeListener listener, ITreeObserverOptions options) {
        //TODO
        return null;
    }

    @Override
    public Object invoke(UOperation operation, Object value) {
        //TODO
        return null;
    }

    @Override
    public <T extends UObject> T uComponent(Class<T> clazz, int index) {
        //TODO
        return null;
    }

	@Override
	public IDisposable oberserveProperty(String fqPropName, PropertyChangeListener pcl) {
		//TODO
		return null;
	}
}
