package com.github.maximovj.linuxhubapi.linuxhub_api.validation.valid_role;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.EnumSet;
import java.util.stream.Collectors;

import com.github.maximovj.linuxhubapi.linuxhub_api.data.account.Role;

public class RoleValidator implements ConstraintValidator<ValidRole, String> {
    private EnumSet<Role> validRoles;

    @Override
    public void initialize(ValidRole constraintAnnotation) {
        validRoles = EnumSet.allOf(Role.class);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // o true, dependiendo si quieres permitir valores nulos
        }
        return validRoles.stream()
                .map(Enum::name)
                .collect(Collectors.toSet())
                .contains(value.toUpperCase());
    }
}
