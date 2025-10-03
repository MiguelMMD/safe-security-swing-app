package com.safe_security.vista;

import com.safe_security.controlador.Controlador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Interfaz de Inicio de Sesión y Registro. 
 * Ahora pasa el Controlador a la MainMenuView al iniciar sesión.
 */
public class LoginView extends JFrame {
    private final Controlador controlador;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegistro;

    public LoginView(Controlador controlador) {
        this.controlador = controlador;
        setTitle("Safe Security - Cliente Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 

        txtEmail = new JTextField(20);
        txtPassword = new JPasswordField(20);
        btnLogin = new JButton("Iniciar Sesión");
        btnRegistro = new JButton("Registrarse");

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtPassword, gbc);

        gbc.gridx = 1; gbc.gridy = 2; gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegistro);
        panel.add(buttonPanel, gbc);
        
        add(panel);

        // --- LÓGICA DE BOTONES ---
        btnLogin.addActionListener((ActionEvent e) -> {
            String email = txtEmail.getText();
            String password = new String(txtPassword.getPassword());
            
            if (controlador.manejarInicioSesion(email, password)) {
                JOptionPane.showMessageDialog(this, "¡Bienvenido! Sesión Exitosa.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                // SOLUCIÓN: Pasamos el controlador a la ventana de menú
                new MainMenuView(this.controlador).setVisible(true); 
                this.dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnRegistro.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this, "Función de Registro lista para implementar.");
        });
    }
}