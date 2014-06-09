/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vistas;

import interfaces.Intercambiable;
import componentes.VerticalLabelUI;
import controladores.ControladorVistas;
import enums.TipoVista;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import modelos.ConexionSQL;

/**
 *
 * @author VREBO
 */
public class VistaPestanas extends JTabbedPane implements Intercambiable{
    
    private final ConexionSQL conexion;
    private final TipoVista tipoVista;
    
    public VistaPestanas(ConexionSQL conexion) {
        this.conexion = conexion;
        tipoVista = TipoVista.VISTAS;
        setTabPlacement(JTabbedPane.LEFT);
        addComponentes();
    }
    
    private void addComponentes(){
        JLabel interfaz = new JLabel("Interface");
        JLabel consola = new JLabel("Consola");
        interfaz.setUI(new VerticalLabelUI(false));
        consola.setUI(new VerticalLabelUI(false));
        VistaInterfaz vInterfaz = new VistaInterfaz(conexion.getUser());
        VistaConsola vConsola = new VistaConsola();
        add(vInterfaz);
        add(vConsola);
        new ControladorVistas(vInterfaz, vConsola, conexion);
        setTabComponentAt(0, interfaz);        
        setTabComponentAt(1, consola);
        String url = "/iconos/consola.png";
        consola.setIcon(new ImageIcon(getClass().getResource(url)));
        url = "/iconos/cursor.png";
        interfaz.setIcon(new ImageIcon(getClass().getResource(url)));
    }

    @Override
    public TipoVista getTipoVista() {
        return tipoVista;
    }
}