package de.emir.tuml.ucore.runtime.utils.internal.builder;

import de.emir.tuml.ucore.runtime.UPackage;
import de.emir.tuml.ucore.runtime.impl.UPackageImpl;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;
import de.emir.tuml.ucore.runtime.utils.UCoreMetaRepository;

public class PackageBuilder {

    public static UPackage createPackage(QualifiedName qn) {
        if (qn == null || qn.numSegments() == 0)
            return null;
        if (qn.numSegments() == 1) {
            return new UPackageImpl(qn.toString());
        } else {
            UPackage parent_package = UCoreMetaRepository.getPackage(qn.removeSegmentsFromEnd(1));
            if (parent_package == null)
                return null;
            return parent_package.createPackage(qn.lastSegment());
        }
    }

}
