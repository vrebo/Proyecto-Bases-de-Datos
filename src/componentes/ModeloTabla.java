package componentes;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class ModeloTabla extends AbstractTableModel {

    private boolean DEBUG = true;

    private final String[] columnNames = {"Nombre Columna", "Tipo Dato", "Primaria",
        "Not Null", "Autoincrementable"};
    private final Vector<Object[]> data = new Vector<>();
    private final Vector<boolean[]> editables = new Vector<>();

    public ModeloTabla() {
        addRow();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data.get(row)[col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return editables.get(row)[col];
    }

    public final void addRow() {
        Object[] row = {"nuevo_campo" + "_" + (getRowCount() + 1), "INT", false, false, false};
        boolean[] edit = {true, true, true, true, true};
        data.add(row);
        editables.add(edit);
        fireTableRowsInserted(0, data.size() - 1);
    }

    public void removeRow(int index) {
        if (data.size() > 1) {
            data.remove(index);
            fireTableRowsDeleted(0, data.size() - 1);
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {

        if (DEBUG) {
            System.out.println("Setting value at " + row + "," + col + " to "
                    + value + " (an instance of " + value.getClass() + ")");
        }

        data.get(row)[col] = value;
        fireTableCellUpdated(row, col);
        if (col == 1) {
            String valor = (String) value;
            
            if (!valor.matches("INT")) {
                setValueAt(false, row, 4);
                editables.get(row)[4] = false;
            } else {
                editables.get(row)[4] = true;
            }
        }

        if (DEBUG) {
            System.out.println("New value of data:");
            printDebugData();
        }
    }

    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + data.get(i)[j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
