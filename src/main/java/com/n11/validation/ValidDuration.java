package com.n11.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = com.n11.validation.ValidDurationValidator.class)
@Documented
public @interface ValidDuration {
	String message() default "Not a valid duration";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	int min() default 5;
}
