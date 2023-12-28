package com.berkemaktav.flightsearchapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCityValidator.class)
public @interface UniqueCity {
    String message() default "City already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}