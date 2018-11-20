package pl.database.rum.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.database.rum.entities.Rum;
import pl.database.rum.init.HibernateUtil;

import javax.swing.table.AbstractTableModel;
import java.util.List;

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

    List<Rum> rums;

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

    public TableRums(){
        this.rums = getAllRums();
        for (Rum rum : this.rums){
            System.out.println(rum.toString());
        }
    }

    public List<Rum> getAllRums(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        final Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Rum> rums = session.createCriteria(Rum.class).list();
        return rums;

    }

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