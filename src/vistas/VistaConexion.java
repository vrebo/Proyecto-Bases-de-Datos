package vistas;

import Excepciones.DatosFaltantesException;
import interfaces.Intercambiable;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controladores.ControladorConexion;
import enums.TipoVista;
import java.util.ArrayList;

public class VistaConexion extends Vista implements Intercambiable {

    public final String EQT_CONEXION = "Nombre Conexión: ";
    private final String EQT_USUARIO = "Usuario: ";
    private final String EQT_PASSWORD = "Password: ";
    private final String EQT_HOST = "Nombre Host:";
    private final String EQT_PUERTO = "Puerto:";
    private final String EQT_CONEXION_REMOTA = "Conexion Remota";
    public static final String EQT_BTN_CREAR = "Crear Conexión";
    public static final String EQT_BTN_CANCELAR = "Cancelar";
    public static final String EQT_TITULO = "Nueva Conexión";
    private JTextField jtfConexion;
    private JTextField jtfHost;
    private JTextField jtfPuerto;
    private JTextField jtfUsuario;
    private JPasswordField jpfPassword;
    private JCheckBox jcbOpcionRemota;
    private JButton jbtnCrear;
    private JButton jbtnCancelar;
    private final TipoVista tipoVista;

    public VistaConexion() {
        tipoVista = TipoVista.CONEXION;
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new GridBagLayout());
        addComponentes();
    }

    @Override
    protected final void addComponentes() {
        jcbOpcionRemota = new JCheckBox(EQT_CONEXION_REMOTA);
        jcbOpcionRemota.setSelected(false);
        jtfHost = new JTextField(13);
        jtfHost.setEnabled(false);
        jtfPuerto = new JTextField(5);
        jtfPuerto.setEnabled(false);
        jtfConexion = new JTextField(30);
        jtfUsuario = new JTextField(30);
        jpfPassword = new JPasswordField(30);
        jbtnCrear = new JButton(EQT_BTN_CREAR);
        jbtnCancelar = new JButton(EQT_BTN_CANCELAR);

        setGBC(0, 0, GridBagConstraints.FIRST_LINE_END, new Insets(5, 5, 5, 5));
        add(new JLabel(EQT_CONEXION), gbc);
        setGBC(1, 0);
        add(jtfConexion, gbc);

        JPanel panelInterno = new JPanel(new GridBagLayout());
        panelInterno.setBorder(BorderFactory.createEtchedBorder());
        setGBCSize(2, 1);
        setGBC(0, 1, GridBagConstraints.FIRST_LINE_END);
        add(panelInterno, gbc);
        setGBC(0, 0);
        setGBCSize(1, 1);
        panelInterno.add(jcbOpcionRemota, gbc);
        setGBC(0, 2);
        panelInterno.add(new JLabel(EQT_HOST), gbc);
        setGBC(1, 2);
        panelInterno.add(jtfHost, gbc);
        setGBC(2, 2);
        panelInterno.add(new JLabel(EQT_PUERTO), gbc);
        setGBC(3, 2);
        panelInterno.add(jtfPuerto, gbc);

        setGBCSize(1, 1);
        setGBC(0, 2);
        add(new JLabel(EQT_USUARIO), gbc);
        setGBC(1, 2);
        add(jtfUsuario, gbc);
        setGBC(0, 3);
        add(new JLabel(EQT_PASSWORD), gbc);
        setGBC(1, 3);
        add(jpfPassword, gbc);

        panelInterno = new JPanel(new GridLayout(1, 2, 10, 10));
        panelInterno.add(jbtnCrear);
        panelInterno.add(jbtnCancelar);
        setGBC(0, 4, GridBagConstraints.CENTER);
        setGBCSize(2, 1);
        add(panelInterno, gbc);
    }

    public void addEventos(ControladorConexion controlador) {
        jcbOpcionRemota.addItemListener(controlador);
        jbtnCrear.addActionListener(controlador);
        jbtnCancelar.addActionListener(controlador);
    }

    public final ArrayList<String> getDatos() throws DatosFaltantesException {
        ArrayList<String> data = new ArrayList<>();
        if (!jtfConexion.getText().equals("")) {
            data.add(jtfConexion.getText());
        } else {
            jtfConexion.setText("Conexión");
            throw new DatosFaltantesException();
        }
        data.add(jtfUsuario.getText());
        String password = "";
        for (int i = 0; i < jpfPassword.getPassword().length; i++) {
            password += jpfPassword.getPassword()[i];
        }
        data.add(password);
        if (!jtfHost.getText().equals("")) {
            data.add(jtfHost.getText());
        }
        if (!jtfPuerto.getText().equals("")) {
            data.add(jtfPuerto.getText());
        }
        return data;
    }

    public JTextField getJtfConexion() {
        return jtfConexion;
    }

    public JTextField getJtfHost() {
        return jtfHost;
    }

    public JTextField getJtfPuerto() {
        return jtfPuerto;
    }

    public JTextField getJtfUsuario() {
        return jtfUsuario;
    }

    public JPasswordField getJpfPassword() {
        return jpfPassword;
    }

    public JCheckBox getJcbOpcionRemota() {
        return jcbOpcionRemota;
    }

    public JButton getJbtnCrear() {
        return jbtnCrear;
    }

    public JButton getJbtnCancelar() {
        return jbtnCancelar;
    }

    @Override
    public TipoVista getTipoVista() {
        return tipoVista;
    }
}