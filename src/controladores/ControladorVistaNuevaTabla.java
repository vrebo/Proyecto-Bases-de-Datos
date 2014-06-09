package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import vistas.VistaNuevaTabla;

import componentes.ModeloTabla;
import componentes.Tabla;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelos.ConexionSQL;

public class ControladorVistaNuevaTabla extends KeyAdapter implements ActionListener {

    private final VistaNuevaTabla vista;
    private final ConexionSQL conexion;

    public ControladorVistaNuevaTabla(VistaNuevaTabla vista, ConexionSQL conexion) {
        this.vista = vista;
        this.vista.addEventos(this);
        this.conexion = conexion;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_ENTER) {
            Tabla tabla = vista.getTabla();
            int filaSeleccionada = tabla.getSelectedRow();
            int filas = tabla.getRowCount();
            if (filaSeleccionada + 1 == filas) {
                ModeloTabla modelo = (ModeloTabla) tabla.getModel();
                modelo.addRow();
            }
        } else if (tecla == KeyEvent.VK_DELETE) {
            Tabla tabla = vista.getTabla();
            int filaSeleccionada = tabla.getSelectedRow();
            ModeloTabla modelo = (ModeloTabla) tabla.getModel();
            modelo.removeRow(filaSeleccionada);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String origen = e.getActionCommand();
        switch (origen) {
            case VistaNuevaTabla.ETQ_BTN_CREAR:
                String datos = vista.getDatos();
                if (datos != null) {
                    try {
                        conexion.crearTabla(datos);
                        vista.dispose();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case VistaNuevaTabla.ETQ_BTN_CANCELAR:
                int opcion = JOptionPane.showConfirmDialog(vista, "¿Esta seguro que desea cancelar?", "Alto",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (opcion == JOptionPane.YES_OPTION) {
                    vista.dispose();
                }
        }
    }
}
