package pl.database.rum.app;

import javax.swing.table.AbstractTableModel;

class TableProducents extends AbstractTableModel {

    String[] columnNames = {"Id",
            "Name",
            "Country",
            "Year Founding"};

    Object[][] data = {
            {1, "B.Tech", "Poland", 1544},
    };

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 1) {
            return false;
        } else {
            return true;
        }
    }

    public void setValueAt(Object value, int row, int col) {

        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }


}