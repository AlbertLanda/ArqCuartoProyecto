/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.albertlandaprojectmvc;

import com.Albert.Registro.Controlador.PersonaController;
import com.Albert.Registro.Vista.VentanaMVC;
import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author LENOVO
 */
public class AlbertLandaProjectMVC {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Error al establecer el Look and Feel: " + ex.getMessage());
        }

        java.awt.EventQueue.invokeLater(() -> {
            VentanaMVC vista = new VentanaMVC();
            PersonaController controlador = new PersonaController(vista);
            vista.setVisible(true);
        });
    }
}
