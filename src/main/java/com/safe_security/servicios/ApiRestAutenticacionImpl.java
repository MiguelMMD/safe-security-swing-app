package com.safe_security.servicios;

import com.safe_security.model.Usuario;
import com.safe_security.model.Contacto;
import java.util.Optional;

/**
 * IMPLEMENTACIÓN FINAL Y SIMPLIFICADA DEL CLIENTE API REST (Modo Mock/Simulación).
 */
public class ApiRestAutenticacionImpl implements IServicioAutenticacion {

    private static final String TEST_EMAIL = "test@example.com";
    private static final String TEST_PASSWORD = "123456"; 

    @Override
    public boolean registrarUsuario(Usuario usuario, String passwordSinHash) {
        System.out.println("SIMULACIÓN: Procesando registro de usuario...");
        if (ServicioSeguridad.esEmailValido(usuario.getEmail()) && passwordSinHash.length() >= 6) {
             System.out.println("SIMULACIÓN EXITOSA: El registro fue exitoso.");
             return true; 
        } else {
             System.err.println("SIMULACIÓN FALLIDA: Datos inválidos.");
             return false;
        }
    }

    @Override
    public Optional<Usuario> iniciarSesion(String email, String passwordSinHash) {
        System.out.println("--- INICIO DE SESIÓN (MODO SIMULACIÓN) ---");
        if (TEST_EMAIL.equals(email) && TEST_PASSWORD.equals(passwordSinHash)) {
            Usuario mockUsuario = new Usuario("user-id-mock-1", email, "mocked-hash-from-api", "999999");
            System.out.println("SIMULACIÓN EXITOSA: Autenticación exitosa.");
            return Optional.of(mockUsuario);
        } else {
            System.err.println("SIMULACIÓN FALLIDA: Credenciales incorrectas.");
            return Optional.empty();
        }
    }

    @Override
    public void bloquearSistema() {
        System.out.println("SIMULACIÓN: El servidor ha registrado un intento de bloqueo.");
    }
    
    @Override
    public boolean enviarAlerta(String nivel, String userId) {
        System.out.println("API SIMULADA: Enviando alerta de nivel: " + nivel + " para el usuario: " + userId);
        return true; 
    }

    @Override
    public boolean vincularDispositivo(String userId, String tokenDispositivo) {
        System.out.println("API SIMULADA: Iniciando proceso de Vinculación de Dispositivo para: " + userId);
        return true;
    }
    
    @Override
    public boolean agregarContacto(String userId, Contacto contacto) {
        // Mock de la API: la llamada siempre es exitosa
        System.out.println("API SIMULADA: Recibida solicitud para agregar contacto (" + contacto.getNombre() + ").");
        return true; 
    }
}