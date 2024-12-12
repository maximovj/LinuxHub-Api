package com.github.maximovj.linuxhubapi.linuxhub_api.validation.strong_password;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StrongPasswordValidator.class) // Define el validador
public @interface StrongPassword {
    String message() default "La contraseña es débil, debe contener al menos una mayúscula, una minúscula, un número y un carácter especial.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}