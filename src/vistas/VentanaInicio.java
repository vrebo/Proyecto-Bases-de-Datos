package vistas;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import componentes.BarraMenu;
import controladores.ControladorMenu;
import javax.swing.ImageIcon;

public class VentanaInicio extends JFrame{

    public VentanaInicio() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                InstantiationException | IllegalAccessException e) {
        }
        
        setTitle("Semi Sistema Gestor de Bases de Datos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(50, 50);
        setSize(600, 600);    
        setResizable(false);
        String url = "/iconos/basedatos.png";
        setIconImage(new ImageIcon(getClass().getResource(url)).getImage());
        
        VistaPrincipal vp = new VistaPrincipal();     
        BarraMenu menu = new BarraMenu();
        setJMenuBar(menu);
        add(vp);
        new ControladorMenu(vp, menu);
        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaInicio();
    }

}
