package vistas;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import modelos.ConexionSQL;

/**
 * Los objetos de la clase <code>VistaPrincipal</code> solo sirve como
 * contenedor de las vistas <code>VistaConexion</code> ,
 * <code>VistaInterfaz</code> y <code>VistaConsola</code> y puede cambiar
 *
 * @author VREBO
 */
public class VistaPrincipal extends JPanel {

    private JTabbedPane jtpPestanas;

    public VistaPrincipal() {
        addComponentes();
    }

    private void addComponentes() {
        this.setLayout(new BorderLayout());
        jtpPestanas = new JTabbedPane();
        String url = "/iconos/inicio.jpg";
        JLabel imagen = new JLabel(new ImageIcon(getClass().getResource(url)));
        jtpPestanas.add("Inicio",imagen);
        this.add(jtpPestanas);
    }

    public JTabbedPane getJtpPestanas() {
        return jtpPestanas;
    }
}
