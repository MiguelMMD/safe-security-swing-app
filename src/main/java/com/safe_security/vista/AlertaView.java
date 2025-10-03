package com.safe_security.vista;

import com.safe_security.controlador.Controlador;
import javax.swing.*;
import java.awt.*;

public class AlertaView extends JFrame {

    private final Controlador controlador;
    private JRadioButton nivel1, nivel2, nivel3;
    private JButton btnEnviar;
    private ButtonGroup grupoNiveles;

    public AlertaView(Controlador controlador) {
        // Corrección 1: Usar 'this.' al inicializar
        this.controlador = controlador;
        setTitle("Generar Alerta de Seguridad");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Paneles y configuración
        JPanel radioPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        radioPanel.setBorder(BorderFactory.createTitledBorder("Selecciona Nivel de Alerta"));

        // Opciones de alerta
        nivel1 = new JRadioButton("1. FAMILIA (Contactos cercanos)");
        nivel2 = new JRadioButton("2. BARRIO (Grupos vecinales)");
        nivel3 = new JRadioButton("3. AUTORIDADES (Policía/Emergencias)");
        
        grupoNiveles = new ButtonGroup();
        grupoNiveles.add(nivel1);
        grupoNiveles.add(nivel2);
        grupoNiveles.add(nivel3);
        
        nivel1.setSelected(true); 

        radioPanel.add(nivel1);
        radioPanel.add(nivel2);
        radioPanel.add(nivel3);

        btnEnviar = new JButton("🚨 ENVIAR ALERTA A NIVEL SELECCIONADO");
        btnEnviar.setBackground(Color.RED);
        btnEnviar.setForeground(Color.WHITE);
        btnEnviar.setFont(new Font("Arial", Font.BOLD, 14));
        
        btnEnviar.addActionListener(e -> {
            String nivel = getNivelSeleccionado();
            
            // Corrección 2: Usar 'this.' al llamar al método
            boolean exito = this.controlador.manejarAlerta(nivel); 
            
            if(exito) {
                JOptionPane.showMessageDialog(this, "Alerta de nivel " + nivel + " enviada con éxito (Vía API).", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Fallo al enviar la alerta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(radioPanel, BorderLayout.CENTER);
        add(btnEnviar, BorderLayout.SOUTH);
    }
    
    private String getNivelSeleccionado() {
        if (nivel1.isSelected()) return "FAMILIA";
        if (nivel2.isSelected()) return "BARRIO";
        if (nivel3.isSelected()) return "AUTORIDADES";
        return "DESCONOCIDO";
    }
}