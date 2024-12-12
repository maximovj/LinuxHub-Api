package com.github.maximovj.linuxhubapi.linuxhub_api.validation.valid_enum;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.stream.Stream;

public class EnumValidator implements ConstraintValidator<ValidEnum, String> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Permitir valores nulos; cámbialo según tus requisitos
        }
        // Verifica si el valor coincide con algún nombre del enum (case-insensitive)
        return Stream.of(enumClass.getEnumConstants())
                .map(Enum::name)
                .anyMatch(enumValue -> enumValue.equalsIgnoreCase(value));
    }
}
