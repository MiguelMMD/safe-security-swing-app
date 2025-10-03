package com.safe_security.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa un contacto de emergencia (POJO).
 */
public class Contacto {
    private String nombre;
    private String telefono;
    private String email;

    public Contacto(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    // --- Getters ---
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }

    /**
     * Genera una lista simulada de contactos para la prueba.
     */
    public static List<Contacto> generarContactosSimulados() {
        List<Contacto> lista = new ArrayList<>();
        lista.add(new Contacto("Mamá", "555-1234", "mama@example.com"));
        lista.add(new Contacto("Hermano", "555-5678", "hermano@example.com"));
        lista.add(new Contacto("Vecino Pedro", "555-9012", "pedro@barrio.com"));
        return lista;
    }
}