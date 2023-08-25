package test.de.emir.tuml.ucore.runtime.utils;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.de.emir.tuml.ucore.runtime.utils.sanity.FeaturePointerImplTest;
import test.de.emir.tuml.ucore.runtime.utils.sanity.ObjectPointerImplTest;
import test.de.emir.tuml.ucore.runtime.utils.sanity.PointerChainImplTest;
import test.de.emir.tuml.ucore.runtime.utils.sanity.QualifiedNameImplTest;
import test.de.emir.tuml.ucore.runtime.utils.sanity.QualifiedNameProviderExtImplTest;
import test.de.emir.tuml.ucore.runtime.utils.sanity.QualifiedNameProviderImplTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ QualifiedNameImplTest.class, QualifiedNameProviderImplTest.class,
        QualifiedNameProviderExtImplTest.class, ObjectPointerImplTest.class, FeaturePointerImplTest.class,
        PointerChainImplTest.class })
public class UtilsPackageSanityTestSuite {
}
