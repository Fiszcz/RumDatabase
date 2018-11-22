package pl.database.rum.app;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import pl.database.rum.entities.Producent;
import pl.database.rum.entities.Rum;
import pl.database.rum.init.HibernateUtil;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableProducents extends AbstractTableModel {

    String[] columnNames = {"Id",
            "Name",
            "Country",
            "Year Founding",
            "Delete",
            "Update"};

    private List<Producent> producents;
    static TableProducents instance = null;

    Object[][] data;

    public TableProducents() {
        this.data = getAllProducents();
    }

    public static final TableProducents getInstance(){
        if (instance==null)
            instance = new TableProducents();
        return instance;
    }

    public Object[][] getAllProducents() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        producents = session.createCriteria(Producent.class).list();
        session.getTransaction().commit();
        session.close();

        Object[][] producentArray = convertProducentListTo2DArray(producents);
        return producentArray;
    }

    public Object[][] convertProducentListTo2DArray(List<Producent> producentList) {
        Object[][] rumArray = new Object[producentList.size()][10];
        for (int i = 0; i < producentList.size(); i++) {
            rumArray[i][0] = producentList.get(i).getId();
            rumArray[i][1] = producentList.get(i).getName() != null ? producentList.get(i).getName() : "";
            rumArray[i][2] = producentList.get(i).getCountry() != null ? producentList.get(i).getCountry() : 0;
            rumArray[i][3] = producentList.get(i).getYearFounding() != null ? producentList.get(i).getYearFounding() : "";
            rumArray[i][4] = false;
            rumArray[i][5] = false;
        }
        return rumArray;
    }

    public List<Producent> getProducentById(long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria crit = session.createCriteria(Producent.class);
        crit.add(Restrictions.eq("id", id));
        List<Producent> producents = crit.list();
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
        if (col == 4) {
            removeProducentFromDatabase((Long) data[row][0]);
            this.data = getAllProducents();
        } else if (col == 5){
            updateProducentById((Long) data[row][0], row);
        } else
            data[row][col] = value;
        fireTableDataChanged();
    }

    public void removeProducentFromDatabase(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Producent producent : producents) {
            if (producent.getId().equals(id)) {
                session.delete(producent);
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    public void updateProducentById(Long id, int row){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Producent producent = new Producent(data[row][1].toString(), data[row][2].toString(), (Integer) data[row][3]);
        producent.setId(id);
        session.update(producent);
        session.getTransaction().commit();
        session.close();
    }

    public static long addNewProducentToDatabase(Producent producent, Session session){
        session.beginTransaction();
        long id = (Long)session.save(producent);
        session.close();
        return id;
    }
}