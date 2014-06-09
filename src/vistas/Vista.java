package vistas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPanel;


public abstract class Vista extends JPanel {

    protected GridBagConstraints gbc;

    public Vista() {
        setPreferredSize(new Dimension(400, 400));
        gbc = new GridBagConstraints();
    }

    /**
     * Su función es agregar los componentes a la interfaz.
     */
    protected abstract void addComponentes();

    /**
     * Asigna valores a los atributos <code>gridx</code> y <code>gridy</code>
     * que determinan la posición del componente que será agregado al contenedor
     * con el objeto <code>GridBagConstrains</code> usado para el diseño de la
     * interfaz en <code>addComponentes()</code>.
     *
     * @param gridx
     * @param gridy
     */
    protected void setGBC(int gridx, int gridy) {
        setGBC(gridx, gridy, gbc.anchor);
    }

    /**
     * Asigna valores a los atributos <code>gridx</code>, <code>gridy</code> y
     * <code>anchor</code> que determinan la posición del componente que será
     * agregado al contenedor con el objeto <code>GridBagConstrains</code> usado
     * para el diseño de la interfaz en <code>addComponentes()</code>.
     *
     * @param gridx
     * @param gridy
     * @param anchor
     */
    protected void setGBC(int gridx, int gridy, int anchor) {
        setGBC(gridx, gridy, anchor, gbc.insets);
    }

    /**
     * Asigna valores a los atributos <code>gridx</code>, <code>gridy</code> que
     * determinan la posición del componente e <code>insets</code> que determina
     * el "espaciado" entre el componente y el área en que se muestra. Estos
     * atributos pertenecen al objeto <code>GridBagConstrains</code> usado para
     * el diseño de la interfaz en <code>addComponentes()</code>.
     *
     * @param gridx
     * @param gridy
     * @param insets
     */
    protected void setGBC(int gridx, int gridy, Insets insets) {
        setGBC(gridx, gridy, gbc.anchor, insets);
    }

    /**
     * Su funcion es la misma que <code>setGBC(int, int)</code>,
     * <code>setGBC(int, int, int)</code> y
     * <code>setGBC(int, int, Insets)</code> conmbinando sus funciones en un
     * solo método.
     *
     * @param gridx
     * @param gridy
     * @param anchor
     * @param insets
     */
    protected void setGBC(int gridx, int gridy, int anchor, Insets insets) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.insets = insets;
    }

    /**
     * Modifica las variables <code>gridwith</code> y <code>gridheigth</code>
     * que determinan el número de celdas que ocupará en ancho y largo el
     * componente en el área en que se muestra.
     *
     * @param gridwidth
     * @param gridheigt
     */
    protected void setGBCSize(int gridwidth, int gridheigt) {
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheigt;
    }
}
