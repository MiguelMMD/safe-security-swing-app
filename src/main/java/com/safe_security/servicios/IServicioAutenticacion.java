package com.safe_security.servicios;

import com.safe_security.model.Usuario;
import com.safe_security.model.Contacto; // NUEVO IMPORT
import java.util.Optional;

public interface IServicioAutenticacion {
    
    // Autenticación
    boolean registrarUsuario(Usuario usuario, String passwordSinHash);
    Optional<Usuario> iniciarSesion(String email, String passwordSinHash);
    void bloquearSistema(); 
    
    // Funcionalidades
    boolean enviarAlerta(String nivel, String userId); 
    boolean vincularDispositivo(String userId, String tokenDispositivo);

    // Contactos (Añadido para que el Controlador no marque error)
    boolean agregarContacto(String userId, Contacto contacto);
}