/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Albert.Registro.Controlador;

import com.Albert.Registro.Modelo.PersonaMVC;
import com.Albert.Registro.Vista.VentanaMVC;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class PersonaController {

    private VentanaMVC vista;
    private ArrayList<PersonaMVC> personas;

    public PersonaController(VentanaMVC vista) {
        this.vista = vista;
        this.personas = new ArrayList<>();
        iniciarEventos();
    }

    private void iniciarEventos() {
        vista.getBtnAgregar().addActionListener(evt -> agregarPersona());
        vista.getBtnEliminar().addActionListener(evt -> eliminarPersona());
        vista.getBtnActualizar().addActionListener(evt -> actualizarPersona());
        vista.getBtnBorrar().addActionListener(evt -> borrarTodo());
        vista.getDatechooserFecha().addPropertyChangeListener("date", evt -> calcularEdad());
    }

    private void agregarPersona() {
        String nombre = vista.getTxtNombre().getText();
        int edad = Integer.parseInt(vista.getTxtEdad().getText());
        Date fechaNacimiento = vista.getDatechooserFecha().getDate();
        String direccion = vista.getTxtDireccion().getText();
        String telefono = vista.getTxtTelefono().getText();

        if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || edad <= 0 || fechaNacimiento == null) {
            JOptionPane.showMessageDialog(vista, "Por favor, complete todos los campos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PersonaMVC persona = new PersonaMVC(nombre, edad, fechaNacimiento, direccion, telefono);
        personas.add(persona);
        actualizarDisplay();
    }

    public void eliminarPersona() {
        int selectedRow = vista.getTablaPersonas().getSelectedRow();
        if (selectedRow != -1) {
            personas.remove(selectedRow);
            actualizarDisplay();
        } else {
            JOptionPane.showMessageDialog(vista, "Por favor, seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarPersona() {
        int selectedRow = vista.getTablaPersonas().getSelectedRow();
        if (selectedRow != -1) {
            String nuevoNombre = vista.getTxtNombre().getText();
            int nuevaEdad = Integer.parseInt(vista.getTxtEdad().getText());
            Date nuevaFechaNacimiento = vista.getDatechooserFecha().getDate();
            String nuevaDireccion = vista.getTxtDireccion().getText();
            String nuevoTelefono = vista.getTxtTelefono().getText();

            PersonaMVC persona = personas.get(selectedRow);
            persona.setNombre(nuevoNombre);
            persona.setEdad(nuevaEdad);
            persona.setFechaNacimiento(nuevaFechaNacimiento);
            persona.setDireccion(nuevaDireccion);
            persona.setTelefono(nuevoTelefono);

            actualizarDisplay();
        } else {
            JOptionPane.showMessageDialog(vista, "Por favor, seleccione una fila para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void borrarTodo() {
        personas.clear();
        actualizarDisplay();
    }

    private void calcularEdad() {
        Date fechaNacimiento = vista.getDatechooserFecha().getDate();
        if (fechaNacimiento != null) {
            // Obtener la fecha actual
            Date fechaActual = new Date();

            // Calcular la edad
            int edad = calcularEdad(fechaNacimiento, fechaActual);

            // Mostrar la edad en el campo txtEdad
            vista.getTxtEdad().setText(String.valueOf(edad));
        }
    }

    private int calcularEdad(Date fechaNacimiento, Date fechaActual) {
        java.util.Calendar fechaNac = java.util.Calendar.getInstance();
        fechaNac.setTime(fechaNacimiento);

        java.util.Calendar fechaAct = java.util.Calendar.getInstance();
        fechaAct.setTime(fechaActual);

        int edad = fechaAct.get(java.util.Calendar.YEAR) - fechaNac.get(java.util.Calendar.YEAR);

        // Ajustar si aún no ha cumplido años este año
        if (fechaAct.get(java.util.Calendar.DAY_OF_YEAR) < fechaNac.get(java.util.Calendar.DAY_OF_YEAR)) {
            edad--;
        }

        return edad;
    }

    private void actualizarDisplay() {
        vista.limpiarTabla(); // Método que deberías crear en la vista para limpiar la tabla
        for (PersonaMVC persona : personas) {
            vista.agregarFilaTabla(persona); // Método que deberías crear en la vista para agregar filas a la tabla
        }
        vista.limpiar(); // Llamar al método que limpia los campos de entrada
    }
}