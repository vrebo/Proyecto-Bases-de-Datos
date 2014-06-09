package controladores;

import Excepciones.DatosFaltantesException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import modelos.ConexionSQL;
import vistas.VistaConexion;
import vistas.VistaPrincipal;

import componentes.Contenedor;
import enums.TipoVista;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vistas.VistaPestanas;

public class ControladorConexion implements ActionListener, ItemListener {

    private final VistaPrincipal vPrincipal;
    private final VistaConexion vConexion;

    public ControladorConexion(VistaPrincipal vista, VistaConexion vistaConexion) {
        this.vPrincipal = vista;
        this.vConexion = vistaConexion;
        this.vConexion.addEventos(this);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JTextField jtfHost = vConexion.getJtfHost();
        JTextField jtfPuerto = vConexion.getJtfPuerto();
        if (vConexion.getJcbOpcionRemota().isSelected()) {
            jtfHost.setEnabled(true);
            jtfPuerto.setEnabled(true);
        } else {
            jtfHost.setText("");
            jtfHost.setEnabled(false);
            jtfPuerto.setText("");
            jtfPuerto.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String origen = e.getActionCommand();
        switch (origen) {
            case VistaConexion.EQT_BTN_CREAR:
                crearVistasConexion();
                break;
            case VistaConexion.EQT_BTN_CANCELAR:
                try {
                    Contenedor contenedor = (Contenedor) vPrincipal
                            .getJtpPestanas().getSelectedComponent();
                    contenedor.muestraVista(TipoVista.VISTAS);
                } catch (Exception ex) {
                }
                break;
        }
    }

    public void crearVistasConexion() {
        try {
            ArrayList<String> datos;
            JTabbedPane pestanas = vPrincipal.getJtpPestanas();
            ConexionSQL conexion = null;
            datos = vConexion.getDatos();
            if (datos.size() == 3) {
                conexion = new ConexionSQL(datos.get(0), datos.get(1), datos.get(2));
            } else if (datos.size() > 3) {
                conexion = new ConexionSQL(datos.get(0), datos.get(3), datos.get(4), datos.get(1), datos.get(2));
            }
            conexion.crearConexion();
            pestanas.setTitleAt(pestanas.getSelectedIndex(), datos.get(0));
            Contenedor panel = (Contenedor) pestanas.getSelectedComponent();
            VistaPestanas vPestanas = new VistaPestanas(conexion);
            panel.agregaVista(vPestanas);
            panel.muestraVista(TipoVista.VISTAS);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vConexion, ex.getMessage() + "\n\n"
                    + "Verifique que el estado de su servidor MySQL e intente de"
                    + " nuevo.", "Error en la conexión", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (DatosFaltantesException ex) {
            JOptionPane.showMessageDialog(vConexion, "Introduzca un nombre en el"
                    + " campo \"Nombre conexión\"", ex.getMessage(), 
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public void quitaConexion() {
        JTabbedPane pestanas = vPrincipal.getJtpPestanas();
        int index = pestanas.getSelectedIndex();
        pestanas.remove(index);
    }
}
