package com.academiadodesenvolvedor.market.validations;

import com.academiadodesenvolvedor.market.validations.rules.EmailIsUniqueValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailIsUniqueValidation.class)
public @interface EmailIsUnique {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
    Class<?> entity();
    String field() default  "email";
    String message() default "O email já está sendo usado";
}
