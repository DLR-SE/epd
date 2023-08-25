package de.emir.rcp.pluginmanager.model;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.model.AbstractModelProvider;
import de.emir.rcp.model.IDirtyStateProvider;
import de.emir.rcp.model.ModelTransactionStack;
import de.emir.rcp.pluginmanager.ids.PMBasics;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.runtime.epf.utils.MavenUtil;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class PmModelProvider extends AbstractModelProvider implements IDirtyStateProvider {

    private boolean dirty = false;

    private ProductFile productFile;

    private MavenUtil mu;
    private PublishSubject<Optional<ProductFile>> productFileSubject = PublishSubject.create();

    private boolean isLocked;

    public PmModelProvider() {

        isLocked = PlatformUtil.getArgumentsManager().exists(PMBasics.LOCK_ARG);

    }

    public boolean isLocked() {
        return isLocked;
    }

    public ProductFile getProductFile() {
        return productFile;
    }

    public Disposable subscribeProductFile(Consumer<Optional<ProductFile>> c) {
        return productFileSubject.subscribe(c);
    }

    public void setProductFile(ProductFile productFile) {

        if (isLocked && this.productFile != null) {
            throw new IllegalStateException("The Application is locked on the current Product File");
        }

        if (dirty == true) {

            // TODO: Ask for save

        }

        this.productFile = productFile;
        dirty = false;
        setTransactionStack(new ModelTransactionStack(this));
        createMavenUtil();

        writeProductFileProperty();

        productFileSubject.onNext(Optional.ofNullable(productFile));
    }

    private void writeProductFileProperty() {

        PropertyContext ctx = PropertyStore.getContext(PMBasics.PM_PROP_CTX);

        String value = null;

        if (productFile != null) {

            File file = productFile.getFile();

            if (file != null) {

                value = file.getAbsolutePath();

            }

        }

        ctx.setValue(PMBasics.PM_PROP_LAST_PRODUCT_PATH, value);

    }

    public MavenUtil getMavenUtil() {
        return mu;
    }

    private void createMavenUtil() {

        if (productFile == null) {
            mu = null;
            return;
        }

        mu = new MavenUtil(productFile, false);

    }

    @Override
    public String getModelIdentifier() {

        return productFile == null ? "PluginManagerModel" : productFile.getFile().getName();
    }

    @Override
    public void reloadActiveModel() {

    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    @Override
    public void setDirty(boolean isDirty) {
        this.dirty = isDirty;

    }

    @Override
    public boolean save() {

        try {
            productFile.write();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
