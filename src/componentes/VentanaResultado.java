package componentes;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaResultado extends JFrame {

    public VentanaResultado(String titulo, JTable componente) {
        setTitle(titulo);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JScrollPane scroll = new JScrollPane(componente);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scroll);
        setSize(600, 300);
        setVisible(true);

    }
}