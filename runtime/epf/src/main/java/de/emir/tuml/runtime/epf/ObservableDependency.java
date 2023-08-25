package de.emir.tuml.runtime.epf;

import java.util.Optional;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class ObservableDependency {
    private String groupId;
    private String artifactId;
    private String version;

    private PublishSubject<Optional<String>> groupIdSubject = PublishSubject.create();
    private PublishSubject<Optional<String>> artifactIdSubject = PublishSubject.create();
    private PublishSubject<Optional<String>> versionSubject = PublishSubject.create();

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
        versionSubject.onNext(Optional.ofNullable(version));
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
        artifactIdSubject.onNext(Optional.ofNullable(artifactId));
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
        groupIdSubject.onNext(Optional.ofNullable(groupId));
    }

    public Disposable subscribeGroupId(Consumer<Optional<String>> c) {
        return groupIdSubject.subscribe(c);
    }

    public Disposable subscribeArtifactId(Consumer<Optional<String>> c) {
        return artifactIdSubject.subscribe(c);
    }

    public Disposable subscribeVersion(Consumer<Optional<String>> c) {
        return versionSubject.subscribe(c);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof ObservableDependency == false) {
            return false;
        }

        ObservableDependency od = (ObservableDependency) obj;

        String odGroupId = od.getGroupId();
        String odArtifactId = od.getArtifactId();
        String odVersion = od.getVersion();

        return odGroupId.equals(this.groupId) && odArtifactId.equals(this.artifactId)
                && odVersion.equals(this.version);

    }

    public String getCoordinate() {
        return groupId + ProductFile.DEPENDENCY_SEPARATOR + artifactId + ProductFile.DEPENDENCY_SEPARATOR + version;
    }

    public static ObservableDependency fromCoordinate(String coordinate) {
        ObservableDependency od = new ObservableDependency();

        String[] parts = coordinate.split(ProductFile.DEPENDENCY_SEPARATOR);

        od.setGroupId(parts[0]);
        od.setArtifactId(parts[1]);
        od.setVersion(parts[2]);
        return od;
    }

    public ObservableDependency copy() {

        return ObservableDependency.fromCoordinate(getCoordinate());

    }

    @Override
    public int hashCode() {
        return (groupId + artifactId + version).hashCode();
    }

}
