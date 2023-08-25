package test.de.emir.tuml.ucore.runtime;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.de.emir.tuml.ucore.runtime.sanity.NotificationImplTest;
import test.de.emir.tuml.ucore.runtime.sanity.UAnnotationDetailImplTest;
import test.de.emir.tuml.ucore.runtime.sanity.UAnnotationImplTest;
import test.de.emir.tuml.ucore.runtime.sanity.UClassImplTest;
import test.de.emir.tuml.ucore.runtime.sanity.UEnumImplTest;
import test.de.emir.tuml.ucore.runtime.sanity.UEnumeratorImplTest;
import test.de.emir.tuml.ucore.runtime.sanity.UExceptionImplTest;
import test.de.emir.tuml.ucore.runtime.sanity.UInterfaceImplTest;
import test.de.emir.tuml.ucore.runtime.sanity.UModelImplTest;
import test.de.emir.tuml.ucore.runtime.sanity.UMultiplicityImplTest;
import test.de.emir.tuml.ucore.runtime.sanity.UOperationImplTest;
import test.de.emir.tuml.ucore.runtime.sanity.UPackageImplTest;
import test.de.emir.tuml.ucore.runtime.sanity.UPrimitiveTypeImplTest;
import test.de.emir.tuml.ucore.runtime.sanity.UStructuralFeatureImplTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ UAnnotationDetailImplTest.class, UAnnotationImplTest.class, UModelImplTest.class,
        UPackageImplTest.class, NotificationImplTest.class, UPrimitiveTypeImplTest.class, UInterfaceImplTest.class,
        UClassImplTest.class, UEnumImplTest.class, UEnumeratorImplTest.class, UExceptionImplTest.class,
        UMultiplicityImplTest.class, UStructuralFeatureImplTest.class, UOperationImplTest.class })
public class RuntimePackageSanityTestSuite {
}
