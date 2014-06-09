package interfaces;

import enums.TipoVista;

/**
 * Interface que permite el cambio entre los diferentes tipos de vista del enum
 * <code>TipoVista</code>.
 *
 * @author VREBO
 */
public interface Intercambiable {

    public TipoVista getTipoVista();
}
