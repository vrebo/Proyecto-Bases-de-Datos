package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controladores.ControladorVistas;

public final class VistaConsola extends Vista {

    private final String ETQ_LBL_TEXTO = "Selecciona la BD a usar";
    public static final String ETQ_BTN_CONSULTAR = "Consultar";
    public static final String ETQ_BTN_LIMPIAR = "Limpiar";
    private JComboBox<String> jcbxBases;
    private JTextArea jtaConsola;
    private JButton btnConsultar;
    private JButton btnLimpiar;

    public VistaConsola() {
//        tipoVista = TipoVista.CONSOLA;
        setLayout(new BorderLayout());
        addComponentes();
    }

    @Override
    public void addComponentes() {
        JPanel panelInterno = new JPanel(new GridBagLayout());
        setGBC(0, 0, new Insets(5, 20, 5, 20));
        panelInterno.add(new JLabel(ETQ_LBL_TEXTO), gbc);
        setGBC(1, 0);
        jcbxBases = new JComboBox<>();
        panelInterno.add(jcbxBases, gbc);
        add(panelInterno, BorderLayout.NORTH);

        jtaConsola = new JTextArea(10, 50);
        jtaConsola.setBackground(Color.BLACK);
        jtaConsola.setForeground(Color.GREEN);
        jtaConsola.setCaretColor(Color.GREEN);
        jtaConsola.setFont(new Font("Consolas", Font.PLAIN, 15));
        jtaConsola.setLineWrap(true);
        jtaConsola.setSelectionColor(Color.LIGHT_GRAY);

        JScrollPane pane = new JScrollPane(jtaConsola);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(pane, BorderLayout.CENTER);

        btnConsultar = new JButton(ETQ_BTN_CONSULTAR);
        btnLimpiar = new JButton(ETQ_BTN_LIMPIAR);
        panelInterno = new JPanel(new GridBagLayout());
        setGBC(0, 0);
        panelInterno.add(btnConsultar, gbc);
        setGBC(1, 0);
        panelInterno.add(btnLimpiar, gbc);
        add(panelInterno, BorderLayout.SOUTH);
    }

    public void addEventos(ControladorVistas controlador) {
        btnConsultar.addActionListener(controlador);
        btnLimpiar.addActionListener(controlador);
    }

    public JComboBox<String> getJcbxBases() {
        return jcbxBases;
    }

    public JTextArea getConsola() {
        return jtaConsola;
    }
}
