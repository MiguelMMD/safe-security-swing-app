package com.safe_security.app;

import com.safe_security.controlador.Controlador;
import com.safe_security.servicios.ApiRestAutenticacionImpl;
import com.safe_security.servicios.IServicioAutenticacion;
import com.safe_security.vista.LoginView;

/**
 * Clase principal que inicializa el Cliente (Frontend) del proyecto.
 * Define qué implementación de servicio (local o API) se usará.
 */
public class Main {
    public static void main(String[] args) {
        
        // --- INYECCIÓN DE DEPENDENCIA DEL SERVICIO API ---
        // Al usar ApiRestAutenticacionImpl, el proyecto se convierte en un CLIENTE
        IServicioAutenticacion authService = new ApiRestAutenticacionImpl();

        // El Controlador usa la implementación API sin saber sus detalles
        Controlador controlador = new Controlador(authService);

        // Inicializar la Interfaz Gráfica (Vista) en el hilo de Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView(controlador);
            loginView.setVisible(true);
        });

        System.out.println("Aplicación Safe Security (Cliente API REST) Iniciada.");
    }
}