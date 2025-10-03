package com.safe_security.model;

/**
 * Representa a un usuario. Solo contiene atributos y métodos de acceso.
 * NO contiene lógica de negocio, hashing o validación de formatos.
 */
public class Usuario {
    private String id;
    private String email;
    // Solo almacena el HASH de la contraseña (recibido del API)
    private String contrasenaHash; 
    private String codigoSeguridad; 

    public Usuario(String id, String email, String contrasenaHash, String codigoSeguridad) {
        this.id = id;
        this.email = email;
        this.contrasenaHash = contrasenaHash;
        this.codigoSeguridad = codigoSeguridad;
    }

    // --- Getters y Setters (Añade todos los necesarios) ---
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getContrasenaHash() { return contrasenaHash; }
    public String getCodigoSeguridad() { return codigoSeguridad; }
    // ...
}

// **Nota:** Asegúrate de crear también Alerta.java y Contacto.java en esta carpeta, siguiendo este formato simple.