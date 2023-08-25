package de.emir.tuml.runtime.epf;

import java.util.Optional;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.tesla.aether.Repository;

public class ObservableRepository extends Repository {

    private PublishSubject<Optional<String>> idSubject = PublishSubject.create();
    private PublishSubject<Optional<String>> urlSubject = PublishSubject.create();
    private PublishSubject<Optional<String>> usernameSubject = PublishSubject.create();
    private PublishSubject<Optional<String>> passwordSubject = PublishSubject.create();
    private PublishSubject<Optional<String>> allSubject = PublishSubject.create();

    public ObservableRepository(String url) {
        super(url);

    }

    public Disposable subscribeId(Consumer<Optional<String>> c) {
        return idSubject.subscribe(c);
    }

    public Disposable subscribeUrl(Consumer<Optional<String>> c) {
        return urlSubject.subscribe(c);
    }

    public Disposable subscribeUsername(Consumer<Optional<String>> c) {
        return usernameSubject.subscribe(c);
    }

    public Disposable subscribePassword(Consumer<Optional<String>> c) {
        return passwordSubject.subscribe(c);
    }

    @Override
    public void setId(String id) {
        super.setId(id);
        idSubject.onNext(Optional.ofNullable(id));
        allSubject.onNext(Optional.ofNullable(id));
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
        passwordSubject.onNext(Optional.ofNullable(password));
        allSubject.onNext(Optional.ofNullable(password));
    }

    @Override
    public void setUrl(String url) {
        super.setUrl(url);
        urlSubject.onNext(Optional.ofNullable(url));
        allSubject.onNext(Optional.ofNullable(url));
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
        usernameSubject.onNext(Optional.ofNullable(username));
        allSubject.onNext(Optional.ofNullable(username));
    }

    public Disposable subscribeAll(Consumer<Optional<String>> c) {
        return allSubject.subscribe(c);
    }

}
