package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import vistas.VistaConexion;
import vistas.VistaPrincipal;

import componentes.BarraMenu;
import componentes.Contenedor;
import enums.TipoVista;

public class ControladorMenu implements ActionListener {

    private final VistaPrincipal vPrincipal;
    private final BarraMenu bMenu;

    public ControladorMenu(VistaPrincipal vistaPrincipal, BarraMenu menu) {
        super();
        this.vPrincipal = vistaPrincipal;
        this.bMenu = menu;
        this.bMenu.addEventos(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String origen = e.getActionCommand();
        switch (origen) {
            case BarraMenu.ETQ_JMI_NUEVA:
                agregaNuevaConexion();
                break;
            case BarraMenu.ETQ_JMI_EDITAR:
                if (vPrincipal.getJtpPestanas().getSelectedIndex() != 0) {
                    editaConexion();
                }
                break;
            case BarraMenu.ETQ_JMI_ELIMINAR:
                if (vPrincipal.getJtpPestanas().getSelectedIndex() != 0) {
                quitaConexion();
                }
                break;
        }
    }

    public void agregaNuevaConexion() {
        Contenedor contenedor = new Contenedor();
        VistaConexion vConexion = new VistaConexion();
        contenedor.agregaVista(vConexion);
        new ControladorConexion(vPrincipal, vConexion);
        JTabbedPane pestanas = vPrincipal.getJtpPestanas();
        pestanas.add(BarraMenu.ETQ_JMI_NUEVA, contenedor);
        pestanas.setSelectedIndex(pestanas.getComponentCount() - 1);
    }

    public void editaConexion() {
        JTabbedPane pestanas = vPrincipal.getJtpPestanas();
        Contenedor contenedor = (Contenedor) pestanas.getSelectedComponent();
        contenedor.muestraVista(TipoVista.CONEXION);
    }

    public void quitaConexion() {
        JTabbedPane pestanas = vPrincipal.getJtpPestanas();
        int index = pestanas.getSelectedIndex();
        pestanas.remove(index);
    }
}
