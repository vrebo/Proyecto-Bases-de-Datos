package controladores;

import componentes.VentanaResultado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelos.ConexionSQL;
import vistas.VistaConsola;

import vistas.VistaInterfaz;
import static vistas.VistaInterfaz.*;
import static vistas.VistaConsola.*;
import vistas.VistaNuevaTabla;

public class ControladorVistas implements ActionListener, ItemListener {

    private final String ETQ_TABLAS = "Tablas en la BD: ";
    private final VistaInterfaz vInterfaz;
    private final VistaConsola vConsola;
    private final ConexionSQL conexion;
    private String baseSeleccionada;

    public ControladorVistas(VistaInterfaz vInterfaz, VistaConsola vConsola, ConexionSQL flujoSGBD) {
        super();
        this.vInterfaz = vInterfaz;
        this.vConsola = vConsola;
        this.conexion = flujoSGBD;
        vInterfaz.addEventos(this);
        vConsola.addEventos(this);
        try {
            actualizaBases();
        } catch (SQLException ex) {
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        try {
            Object origen = e.getSource();
            if (origen == vInterfaz.getJcbxBases()) {
                baseSeleccionada = (String) e.getItem();
                actualizaTablas();
            }
        } catch (SQLException ex) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String origen = e.getActionCommand();
        try {
            switch (origen) {
                case ETQ_AGREGAR_BD:
                    String instruccion = JOptionPane.showInputDialog(vInterfaz,
                            "Nombre de la nueva Base");
                    int filas = -1;
                    if (instruccion != null) {
                        filas = creaBase(instruccion);
                    }
                    if (filas == 1) {
                        JOptionPane.showMessageDialog(vInterfaz,
                                "Base de datos creada con exito", "Info",
                                JOptionPane.INFORMATION_MESSAGE);
                        actualizaBases();
                    }
                    break;
                case ETQ_ELIMINAR_BD:
                    eliminaBase();
                    actualizaBases();
                    break;
                case ETQ_AGREGAR_TABLA:
                    VistaNuevaTabla vNuevaTabla = new VistaNuevaTabla();
                    new ControladorVistaNuevaTabla(vNuevaTabla, conexion);
                    break;
                case ETQ_DESCRIBIR_TABLA:
                    System.out.println("Describiendo tabla");
                    describeTabla();
                    break;
                case ETQ_ELIMINAR_TABLA:
                    eliminaTabla();
                    actualizaTablas();
                    break;
                case ETQ_BTN_CONSULTAR:
                    consulta();
                    break;
                case ETQ_BTN_LIMPIAR:
                    vConsola.getConsola().setText("");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vInterfaz, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizaBases() throws SQLException {
        JComboBox<String> jcbxBasesInterfaz = vInterfaz.getJcbxBases();
        DefaultComboBoxModel<String> bases = conexion.showEstructuraSimple("DATABASES");
        if (bases != null) {
            Object objeto = jcbxBasesInterfaz.getSelectedItem();
            jcbxBasesInterfaz.setModel(bases);
            vConsola.getJcbxBases().setModel(bases);
            if (objeto == null) {
                jcbxBasesInterfaz.setSelectedIndex(0);
            } else {
                jcbxBasesInterfaz.setSelectedItem(objeto);
            }
        }
        actualizaTablas();
    }

    private void actualizaTablas() throws SQLException {
        JComboBox<String> jcbxTablasInterfaz = vInterfaz.getJcbxTablas();
        seleccionaBase();
        vInterfaz.getJlBases().setText(ETQ_TABLAS + baseSeleccionada);
        DefaultComboBoxModel<String> tablas = conexion.showEstructuraSimple("TABLES");
        jcbxTablasInterfaz.setModel(tablas);
    }

    private void describeTabla() throws SQLException {
        String nombreTabla = (String) vInterfaz.getJcbxTablas().getSelectedItem();
        JTable tabla = conexion.describeTabla(nombreTabla);
        tabla.setEnabled(false);
        new VentanaResultado("Descripción de " + nombreTabla, tabla);
    }

    private void consulta() throws SQLException {
        conexion.seleccionaBase(baseSeleccionada);
        String consulta = vConsola.getConsola().getText().trim();
        consulta = consulta.replaceAll(";", "");
        JTable tabla = conexion.consulta(consulta);
        tabla.setEnabled(false);
        new VentanaResultado("Consulta", tabla);
    }

    private int creaBase(String instruccion) throws SQLException {
        instruccion = instruccion.trim();
        instruccion = instruccion.replaceAll(" ", "_");
        return conexion.crearBaseDatos(instruccion);
    }

    private void seleccionaBase() throws SQLException {
        baseSeleccionada = (String) vInterfaz.getJcbxBases().getSelectedItem();
        conexion.seleccionaBase(baseSeleccionada);
    }

    private void eliminaBase() throws SQLException {
        String base = (String) vInterfaz.getJcbxBases().getSelectedItem();
        int opcion = JOptionPane.showConfirmDialog(vInterfaz, "¿Esta seguro que desea eliminar"
                + " la Base de datos \"" + base + "\"?", "Eliminando Base de Datos",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            if (conexion.eliminaBase(base) > 0) {
                JOptionPane.showMessageDialog(vInterfaz, "Base eliminada con éxito.", "", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void eliminaTabla() throws SQLException {
        String tabla = (String) vInterfaz.getJcbxTablas().getSelectedItem();
        if (tabla != null) {
            int opcion = JOptionPane.showConfirmDialog(vInterfaz, "¿Esta seguro que desea eliminar"
                    + " tabla \"" + tabla + "\"?", "Eliminando Tabla",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcion == JOptionPane.YES_OPTION) {
                if (conexion.eliminaTabla(tabla) >= 0) {
                    JOptionPane.showMessageDialog(vInterfaz, "Tabla eliminada con éxito.", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}
