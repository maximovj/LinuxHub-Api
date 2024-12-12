package com.github.maximovj.linuxhubapi.linuxhub_api.validation.valid_role;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RoleValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRole {
    String message() default "El rol debe ser uno de: admin, maintainer, user, guest";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}