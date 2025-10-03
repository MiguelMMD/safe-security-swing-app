package com.safe_security.controlador;

import com.safe_security.model.Usuario;
import com.safe_security.model.Contacto;
import com.safe_security.servicios.IServicioAutenticacion;
import com.safe_security.servicios.ServicioSeguridad;
import java.util.List;
import java.util.Optional;

/**
 * Clase que coordina la Vista y la llamada a los Servicios (API).
 */
public class Controlador {

    private final IServicioAutenticacion servicioAuth;
    private Usuario usuarioActual;
    private int intentosFallidos = 0;
    private List<Contacto> contactosUsuario;

    public Controlador(IServicioAutenticacion servicioAuth) {
        this.servicioAuth = servicioAuth;
    }

    // --- MÉTODOS DE AUTENTICACIÓN ---
    
    public boolean manejarRegistro(String email, String password, String codigo) {
        if (!ServicioSeguridad.esEmailValido(email)) {
            return false;
        }
        Usuario nuevoUsuario = new Usuario(null, email, null, codigo); 
        return servicioAuth.registrarUsuario(nuevoUsuario, password);
    }
    
    public boolean manejarInicioSesion(String email, String password) {
        if (intentosFallidos >= 3) {
            servicioAuth.bloquearSistema(); 
            return false;
        }

        Optional<Usuario> resultado = servicioAuth.iniciarSesion(email, password);
        
        if (resultado.isPresent()) {
            this.usuarioActual = resultado.get();
            this.intentosFallidos = 0;
            cargarContactosDelUsuario();
            return true;
        } else {
            this.intentosFallidos++; 
            return false;
        }
    }
    
    // --- MÉTODOS DE FUNCIONALIDADES (API) ---
    
    public boolean manejarAlerta(String nivel) {
        if (usuarioActual == null) {
            System.err.println("ERROR: No hay sesión activa para enviar alerta.");
            return false;
        }
        return servicioAuth.enviarAlerta(nivel, usuarioActual.getId());
    }

    public void manejarVinculacionDispositivo() {
        if (usuarioActual == null) {
            System.err.println("ERROR: No hay sesión activa.");
            return;
        }
        String tokenDispositivo = "MOCK_TOKEN_DEVICE_001"; 
        
        boolean exito = servicioAuth.vincularDispositivo(usuarioActual.getId(), tokenDispositivo);
        if (exito) {
            System.out.println("LOG: Vinculación de dispositivo iniciada/exitosa en la API.");
        } else {
             System.err.println("LOG: Fallo en la vinculación del dispositivo.");
        }
    }

    // --- MÉTODOS DE CONTACTOS ---

    private void cargarContactosDelUsuario() {
        System.out.println("LOG: Solicitando contactos a la API...");
        this.contactosUsuario = Contacto.generarContactosSimulados(); 
        System.out.println("LOG: " + this.contactosUsuario.size() + " contactos cargados en la memoria del cliente.");
    }

    public List<Contacto> obtenerContactos() {
        return this.contactosUsuario;
    }
    
    /**
     * Recibe los datos del formulario, los valida y simula el envío a la API.
     */
    public boolean manejarAgregarContacto(String nombre, String telefono, String email) {
        if (usuarioActual == null) {
            System.err.println("ERROR: No hay sesión activa para agregar contacto.");
            return false;
        }
        
        // 1. Validación simple
        if (nombre.trim().isEmpty() || telefono.trim().isEmpty()) {
            System.err.println("LOG: Nombre o teléfono vacíos.");
            return false;
        }
        
        Contacto nuevo = new Contacto(nombre, telefono, email);
        
        // 2. Simulación: Agregamos a la lista local para que el usuario lo vea de inmediato
        this.contactosUsuario.add(nuevo); 
        
        // 3. Llamada a la API simulada
        boolean exitoAPI = servicioAuth.agregarContacto(usuarioActual.getId(), nuevo);
        
        if (exitoAPI) {
            System.out.println("LOG: Contacto " + nombre + " enviado a la API con éxito.");
            return true;
        } else {
            // Si la API falla, podríamos querer revertir la adición local
            this.contactosUsuario.remove(nuevo);
            return false;
        }
    }
}