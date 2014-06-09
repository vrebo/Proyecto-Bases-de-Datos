package vistas;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import componentes.ModeloTabla;
import componentes.Tabla;
import controladores.ControladorVistaNuevaTabla;
import java.awt.Dimension;
import javax.swing.JFrame;

import javax.swing.JOptionPane;

public final class VistaNuevaTabla extends JFrame {

    public static final String ETQ_BTN_CREAR = "Crear";
    public static final String ETQ_BTN_CANCELAR = "Cancelar";
    private final String ETQ_LBL_NOMBRE_TABLA = "Nombre de la nueva tabla:";

    private Tabla tabla;
    private JTextField nombreTabla;
    private JButton btnCrearTabla;
    private JButton btnCancelar;

    public VistaNuevaTabla() {
        addComponentes();
        setVisible(true);
        setSize(getPreferredSize());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public String getDatos() {
        String nombre = nombreTabla.getText();
        if (nombre.equals("")) {
            JOptionPane.showMessageDialog(this, "Introduzca el nombre de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        int filas = tabla.getRowCount();
        String datos = nombre + " (";
        for (int i = 0; i < filas; i++) {
            String regex = "(INT)|(FLOAT)|(DATE)|(BOOLEAN)|((CHAR)\\([0-9]+\\))";
            String tipoDato = (String) tabla.getValueAt(i, 1);
            if (tipoDato.matches(regex)) {
                datos += tabla.getValueAt(i, 0) + " " + tipoDato + " ";
            } else {
                JOptionPane.showMessageDialog(this, "Tipo de dato invalido.", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if ((boolean) tabla.getValueAt(i, 2)) {
                datos += " primary key";
            }
            if ((boolean) tabla.getValueAt(i, 3)) {
                datos += " not null";
            }
            if ((boolean) tabla.getValueAt(i, 4)) {
                datos += " auto_increment";
            }

            if (i != filas - 1) {
                datos += ", ";
            }
        }
        datos += ")";
        return datos;
    }

    private void addComponentes() {
        nombreTabla = new JTextField(20);
        tabla = new Tabla(new ModeloTabla());
        btnCrearTabla = new JButton(ETQ_BTN_CREAR);
        btnCancelar = new JButton(ETQ_BTN_CANCELAR);
        Dimension dim = tabla.getPreferredSize();
        dim.setSize(dim.width + 150, dim.height + 150);
        setPreferredSize(dim);
        JPanel panelInterno = new JPanel();
        panelInterno.add(new JLabel(ETQ_LBL_NOMBRE_TABLA));
        panelInterno.add(nombreTabla);
        add(panelInterno, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        panelInterno = new JPanel();
        panelInterno.add(btnCrearTabla);
        panelInterno.add(btnCancelar);
        add(panelInterno, BorderLayout.SOUTH);
    }

    public void addEventos(ControladorVistaNuevaTabla controlador) {
        btnCrearTabla.addActionListener(controlador);
        btnCancelar.addActionListener(controlador);
        tabla.addKeyListener(controlador);
    }

    public Tabla getTabla() {
        return tabla;
    }
}
