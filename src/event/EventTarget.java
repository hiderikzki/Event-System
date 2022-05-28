package event;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Class Method Filter Annotation for Events
 * @author Hideri : 2022
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface EventTarget {
    /** Event Execution Priority (Highest = First) */
    int priority() default 0;
}
