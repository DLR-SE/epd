package de.emir.rcp.pluginmanager.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.emir.tuml.runtime.epf.ObservableRepository;
import de.emir.tuml.runtime.epf.ProductFile;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * 
 * This handler drops all Dependency Data (information if a defined dependency is a plugin or just a lib) if the product
 * file changes or if the repository definitions change
 * 
 * @author Florian
 *
 */
public class ProductFileChangedConsumer implements Consumer<Optional<ProductFile>> {

    private List<Disposable> subscriptions = new ArrayList<>();
    private List<Disposable> repoSubscriptions = new ArrayList<>();

    @Override
    public void accept(Optional<ProductFile> opt) throws Exception {

        disposeSubscriptions();
        disposeRepoSubscriptions();

        PmManager pm = ServiceManager.get(PmManager.class);

        pm.resetDependencyData();

        if (opt.isPresent() == true) {
            ProductFile pf = opt.get();
            subscriptions.add(pf.subscribeRepositories(ors -> {

                pm.resetDependencyData();
                disposeRepoSubscriptions();
                subscribeRepositories(pm, pf);

            }));

            subscribeRepositories(pm, pf);

        }

    }

    private void subscribeRepositories(PmManager pm, ProductFile pf) {

        List<ObservableRepository> repos = pf.getRemoteRepositories();

        for (ObservableRepository or : repos) {
            subscriptions.add(or.subscribeAll(ors -> pm.resetDependencyData()));
        }

    }

    private void disposeRepoSubscriptions() {

        for (Disposable d : repoSubscriptions) {
            d.dispose();
        }

        repoSubscriptions.clear();

    }

    private void disposeSubscriptions() {

        for (Disposable d : subscriptions) {
            d.dispose();
        }

        subscriptions.clear();

    }

}
