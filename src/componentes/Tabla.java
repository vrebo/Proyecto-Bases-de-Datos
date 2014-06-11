package componentes;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class Tabla extends JTable {

    protected String[] columnToolTips = {"Introduce el nombre de la columna",
        "Selecciona el tipo de dato",
        "Selecciona si el campo será llave primaria",
        "Selecciona si el campo no puede ser nulo",
        "Selecciona si el campo será autoincrementable (enteros)"};
    private final String[] datos = {"INT", "FLOAT", "BOOLEAN", "CHAR(40)", "DATE"};
    private JComboBox<String> tiposDato;

    public Tabla(TableModel modelo) {
        setModel(modelo);
        setTableEditor();
        setColumnsSize();
        setTamano();
    }

    @Override
    protected JTableHeader createDefaultTableHeader() {
        return new JTableHeader(columnModel) {
            @Override
            public String getToolTipText(MouseEvent e) {
                java.awt.Point p = e.getPoint();
                int index = columnModel.getColumnIndexAtX(p.x);
                int realIndex = columnModel.getColumn(index).getModelIndex();
                return columnToolTips[realIndex];
            }
        };
    }

    private void setTableEditor() {
        tiposDato = new JComboBox<>(datos);
//        tiposDato.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if(((String)tiposDato.getSelectedItem()).matches("CHAR\\(((0-9)|)\\)")){
//                    tiposDato.setEditable(true);
//                }else{
//                    tiposDato.setEditable(false);
//                }
//            }
//        });
        tiposDato.setEditable(true);
        getColumnModel().getColumn(1).setCellEditor(
                new DefaultCellEditor(tiposDato));
    }

    private void setTamano() {
        Dimension dimension = this.getPreferredSize();
        dimension = new Dimension(dimension.width, 400);
        setPreferredSize(dimension);
    }

    private void setColumnsSize() {
        for (int i = 0; i < 5; i++) {
            TableColumn columna = getColumnModel().getColumn(i);
            if (i == 0 || i == 4) {
                columna.setPreferredWidth(100);
            } else {
                columna.setPreferredWidth(50);
            }
        }
    }

    public String[] getDatos() {
        return datos;
    }
}
