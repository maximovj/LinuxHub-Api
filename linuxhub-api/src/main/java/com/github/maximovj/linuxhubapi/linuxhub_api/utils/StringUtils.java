package com.github.maximovj.linuxhubapi.linuxhub_api.utils;

public class StringUtils {
    public static String toSlug(String input) {
        // Convierte a minúsculas y reemplaza espacios y caracteres especiales
        return input.trim()                      // Elimina espacios en blanco al principio y final
                    .toLowerCase()                // Convierte a minúsculas
                    .replaceAll("[^a-z0-9-]", "-")  // Reemplaza todo lo que no sea alfanumérico o guion por guiones
                    .replaceAll("-+", "-")         // Reemplaza múltiples guiones seguidos por uno solo
                    .replaceAll("^-|-$", "");      // Elimina guiones al principio y final
    }
}