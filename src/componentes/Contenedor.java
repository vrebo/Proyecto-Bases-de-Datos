package componentes;

import interfaces.Intercambiable;
import java.awt.CardLayout;

import enums.TipoVista;
import java.awt.Component;
import java.awt.Container;

/**
 * La función de los objetos de la clase <code>Contenedor</code> es intercambiar
 * las vistas <code>VistaConexion</code> , <code>VistaPestanas</code> que contienen.
 *
 * @author VREBO
 */
public class Contenedor extends Container {

    public Contenedor() {
        setLayout(new CardLayout());
    }

    /**
     * Agrega la vista al contenedor.
     *
     * @param o
     */
    public void agregaVista(Intercambiable o) {
        add(o.getTipoVista() + "", (Component)o);
    }

    /**
     * Cambia la vista que en el contenedor se muestra.
     *
     * @param tipo La vista que se mostrará.
     */
    public void muestraVista(TipoVista tipo) {
        CardLayout cards = (CardLayout) this.getLayout();
        cards.show(this, tipo + "");
    }
}
