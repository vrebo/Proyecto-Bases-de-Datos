package vistas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controladores.ControladorVistas;

public final class VistaInterfaz extends Vista {

    public static final String ETQ_AGREGAR_BD = "Agregar BD";
    public static final String ETQ_ELIMINAR_BD = "Eliminar BD";
    public static final String ETQ_AGREGAR_TABLA = "Agregar";
    public static final String ETQ_DESCRIBIR_TABLA = "Describir";
    public static final String ETQ_ELIMINAR_TABLA = "Eliminar";
    private final String[] etiquetasBotones = {ETQ_AGREGAR_BD, ETQ_ELIMINAR_BD,
        ETQ_AGREGAR_TABLA, ETQ_DESCRIBIR_TABLA, ETQ_ELIMINAR_TABLA};
    private final String EQT_BASES_DATOS = "Bases de Datos en el Servidor";
    private final String EQT_USUARIO;
    private JLabel jlBases;
    private JComboBox<String> jcbxBases;
    private JComboBox<String> jcbxTablas;
    private final JButton[] botones = new JButton[5];

    public VistaInterfaz(String usuario) {
        this.EQT_USUARIO = "Usuario: " + usuario;
        setLayout(new GridLayout(2, 1, 20, 20));
        setBorder(BorderFactory.createEtchedBorder());
        addComponentes();
    }

    @Override
    protected void addComponentes() {
        jlBases = new JLabel();
        jcbxTablas = new JComboBox<>();
        jcbxBases = new JComboBox<>();
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel panelInterno = new JPanel(new GridLayout(3, 1, 10, 10));
        panelInterno.add(new JLabel(EQT_USUARIO));
        panelInterno.add(new JLabel(EQT_BASES_DATOS));
        panelInterno.add(jcbxBases);
        setGBC(0, 0, GridBagConstraints.FIRST_LINE_START, new Insets(10, 10,
                10, 50));
        panel.add(panelInterno, gbc);

        panelInterno = new JPanel(new GridLayout(2, 1, 10, 10));
        for (int i = 0; i < etiquetasBotones.length / 2; i++) {
            botones[i] = new JButton(etiquetasBotones[i]);
            panelInterno.add(botones[i]);
        }
        setGBC(1, 0);
        panel.add(panelInterno);
        add(panel);

        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEtchedBorder());
        panelInterno = new JPanel(new GridLayout(2, 1));
        panelInterno.add(jlBases);
        panelInterno.add(jcbxTablas);
        setGBC(0, 0);
        panel.add(panelInterno, gbc);

        panelInterno = new JPanel(new GridLayout(3, 1, 10, 10));
        for (int i = 2; i < etiquetasBotones.length; i++) {
            botones[i] = new JButton(etiquetasBotones[i]);
            panelInterno.add(botones[i]);
        }

        setGBC(1, 0);
        panel.add(panelInterno, gbc);
        add(panel);
    }

    public void addEventos(ControladorVistas controlador) {
        for (JButton boton : botones) {
            boton.addActionListener(controlador);
        }
        jcbxBases.addItemListener(controlador);
    }

    public JComboBox<String> getJcbxBases() {
        return jcbxBases;
    }

    public JComboBox<String> getJcbxTablas() {
        return jcbxTablas;
    }

    public JLabel getJlBases() {
        return jlBases;
    }
}