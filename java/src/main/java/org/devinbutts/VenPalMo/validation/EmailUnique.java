package org.devinbutts.VenPalMo.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @interface for Email Unique Bean.
 */
@Documented
@Constraint(validatedBy = EmailUniqueValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailUnique {

    String message() default "Email already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}