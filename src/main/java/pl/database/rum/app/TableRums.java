package pl.database.rum.app;

import javax.swing.table.AbstractTableModel;

class TableRums extends AbstractTableModel {

    String[] columnNames = {"Id",
            "Name",
            "Alcohol Percentage",
            "Type",
            "Finish",
            "Rating",
            "Minimal Age",
            "Producent",
            "Delete"};

    Object[][] data = {
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false},
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