package com.safe_security.vista;

import com.safe_security.controlador.Controlador;
import javax.swing.*;
import java.awt.*;

/**
 * Ventana para ingresar los datos de un nuevo contacto.
 */
public class AgregarContactoView extends JFrame {

    private final Controlador controlador;
    private JTextField txtNombre, txtTelefono, txtEmail;
    private JButton btnGuardar;

    public AgregarContactoView(Controlador controlador) {
        // Corrección 1: Usar 'this.' al inicializar
        this.controlador = controlador; 
        setTitle("Agregar Nuevo Contacto");
        setSize(350, 250);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNombre = new JTextField(20);
        txtTelefono = new JTextField(20);
        txtEmail = new JTextField(20);
        btnGuardar = new JButton("Guardar Contacto");

        // Filas del formulario
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; 
        panel.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; 
        panel.add(txtTelefono, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; 
        panel.add(txtEmail, gbc);
        
        // Botón Guardar
        gbc.gridx = 1; gbc.gridy = 3; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnGuardar, gbc);

        // Lógica de Guardar Contacto
        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            String email = txtEmail.getText();
            
            // Corrección 2: Usar 'this.' al llamar al método
            if (this.controlador.manejarAgregarContacto(nombre, telefono, email)) {
                JOptionPane.showMessageDialog(this, "Contacto '" + nombre + "' agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar contacto. Nombre/Teléfono no deben estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(panel);
    }
}