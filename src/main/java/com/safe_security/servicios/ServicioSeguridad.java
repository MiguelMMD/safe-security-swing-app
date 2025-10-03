package com.safe_security.servicios;

/**
 * Clase estática para la validación de formatos (separada del modelo de datos).
 */
public class ServicioSeguridad {

    // Lógica de validación de Email
    public static boolean esEmailValido(String email) {
        // Validación básica
        return email != null && email.matches("^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$");
    }

    // Lógica de validación de Código de Seguridad
    public static boolean esCodigoSeguridadValido(String codigo) {
        return codigo != null && codigo.length() == 6 && codigo.matches("\\d+");
    }
}