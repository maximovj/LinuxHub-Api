package com.github.maximovj.linuxhubapi.linuxhub_api.validation.strong_password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

    @Override
    public void initialize(StrongPassword constraintAnnotation) {
        // Aquí podrías hacer alguna inicialización si fuera necesario
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        // Verificar que la contraseña tiene al menos una letra mayúscula
        boolean hasUpperCase = password.chars().anyMatch(Character::isUpperCase);
        // Verificar que la contraseña tiene al menos una letra minúscula
        boolean hasLowerCase = password.chars().anyMatch(Character::isLowerCase);
        // Verificar que la contraseña tiene al menos un número
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        // Verificar que la contraseña tiene al menos un carácter especial
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        // La contraseña es fuerte si cumple con todos los requisitos
        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }
}

