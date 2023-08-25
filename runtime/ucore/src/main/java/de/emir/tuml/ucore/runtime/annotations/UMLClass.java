package de.emir.tuml.ucore.runtime.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Classes and Interfaces, using this Annotation have been declared as Class in UML
 * 
 * @author sschweigert
 *
 */
@Target(java.lang.annotation.ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UMLClass {

    /**
     * Name of the Class as defined in UML
     * 
     * @return
     */
    String name() default "";

    /**
     * True if the Class has been defined as abstract, e.g. not to be instantiated
     * 
     * @return
     */
    boolean isAbstract() default false;

    /**
     * reference to another class that has been defined as super type, e.g. as general for this Class
     * 
     * @return
     */
    Class<?> parent() default Object.class;
}
