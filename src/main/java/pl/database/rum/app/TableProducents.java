package pl.database.rum.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.database.rum.entities.Producent;

import pl.database.rum.init.HibernateUtil;


import javax.swing.table.AbstractTableModel;
import java.util.List;

class TableProducents extends AbstractTableModel {

    String[] columnNames = {"Id",
            "Name",
            "Country",
            "Year Founding",
            "Delete"};

    List<Producent> producents;

    Object[][] data = {
            {1, "B.Tech", "Poland", 1544, false},
            {2, "B.Nech", "Poland", 1544, false},
            {3, "B.Bech", "Poland", 1544, false},
            {4, "B.Fech", "Poland", 1544, false},
            {5, "B.Gech", "Poland", 1544, false},
    };

    public TableProducents(){
        this.producents = getAllProducents();
        for (Producent producent : this.producents){
            System.out.println(producent.toString());
        }
    }

    public List<Producent> getAllProducents() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Producent> producents = session.createCriteria(Producent.class).list();
        session.getTransaction().commit();
        session.close();
        return producents;
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

//    public void addNewProducer(){
//        data[data.length].push()
//    }

}