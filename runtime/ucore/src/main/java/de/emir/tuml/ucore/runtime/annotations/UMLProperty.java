package de.emir.tuml.ucore.runtime.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface UMLProperty {

    public enum AssociationType {
        NONE, SHARED, COMPOSITE, PROPERTY
    }

    AssociationType associationType() default AssociationType.NONE;

    String name() default "";

    int lowerBound() default 0;

    int upperBound() default 1;

}
