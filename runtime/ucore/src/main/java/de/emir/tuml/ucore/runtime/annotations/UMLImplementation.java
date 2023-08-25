package de.emir.tuml.ucore.runtime.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Java Classes annotated with this annotation serve as Implementation of an class defined in UML (and marked
 * with @UMLClass)
 * 
 * @author sschweigert
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UMLImplementation {
    /**
     * Reference to the java class, marked with @UMLClass that is implemented by this class.
     * 
     * @return
     */
    Class<?> classifier();
}
