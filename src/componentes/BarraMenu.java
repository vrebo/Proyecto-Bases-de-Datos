package componentes;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controladores.ControladorMenu;
import javax.swing.ImageIcon;

public class BarraMenu extends JMenuBar {

    public static final String ETQ_JM_CONEXION = "Conexión";
    public static final String ETQ_JMI_NUEVA = "Nueva Conexión";
    public static final String ETQ_JMI_EDITAR = "Editar Conexión";
    public static final String ETQ_JMI_ELIMINAR = "Cerrar Conexión";
    private JMenu jmConexion;
    private JMenuItem jmiNueva;
    private JMenuItem jmiEditar;
    private JMenuItem jmiCerrar;

    public BarraMenu() {
        addMenus();
    }

    private void addMenus() {
        jmConexion = new JMenu(ETQ_JM_CONEXION);
        jmiNueva = new JMenuItem(ETQ_JMI_NUEVA);
        jmiEditar = new JMenuItem(ETQ_JMI_EDITAR);
        jmiCerrar = new JMenuItem(ETQ_JMI_ELIMINAR);
        jmConexion.add(jmiNueva);
        jmConexion.add(jmiEditar);
        jmConexion.add(jmiCerrar);
        String url = "/iconos/nueva-conexion.png";
        jmiNueva.setIcon(new ImageIcon(getClass().getResource(url)));
        url = "/iconos/configuraciones.png";
        jmiEditar.setIcon(new ImageIcon(getClass().getResource(url)));
        url = "/iconos/cerrar-conexion.png";
        jmiCerrar.setIcon(new ImageIcon(getClass().getResource(url)));
        url = "/iconos/menu-conexiones.png";
        jmConexion.setIcon(new ImageIcon(getClass().getResource(url)));
        this.add(jmConexion);
    }

    public void addEventos(ControladorMenu controlador) {
        jmiNueva.addActionListener(controlador);
        jmiEditar.addActionListener(controlador);
        jmiCerrar.addActionListener(controlador);
    }
}