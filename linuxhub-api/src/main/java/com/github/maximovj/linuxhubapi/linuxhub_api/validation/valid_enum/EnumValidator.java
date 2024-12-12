package com.github.maximovj.linuxhubapi.linuxhub_api.validation.valid_enum;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidator implements ConstraintValidator<ValidEnum, String> {

    private Class<? extends Enum<?>> enumClass;
    private String message;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
        this.message = constraintAnnotation.message(); // Obtener el mensaje personalizado de la anotación
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Permitir valores nulos; cámbialo según tus requisitos
        }

        // Verifica si el valor coincide con algún nombre del enum (case-insensitive)
        boolean valid = Stream.of(enumClass.getEnumConstants())
                .map(Enum::name)
                .anyMatch(enumValue -> enumValue.equalsIgnoreCase(value));

        if (!valid) {
            // Construye una cadena con todos los valores del enum separados por coma
            String enumValues = Stream.of(enumClass.getEnumConstants())
                                      .map(Enum::name)
                                      .collect(Collectors.joining(", "));

            // Desactiva el mensaje predeterminado
            context.disableDefaultConstraintViolation(); 
            // Establece el mensaje personalizado, incluyendo los valores del enum
            context.buildConstraintViolationWithTemplate(
                        !message.isEmpty() ? message + "." : "" + "valores válidos: " + enumValues)
                   .addConstraintViolation(); // Agrega el error
        }

        return valid;
    }
}
