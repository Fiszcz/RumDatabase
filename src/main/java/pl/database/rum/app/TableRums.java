package pl.database.rum.app;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import pl.database.rum.entities.Producent;
import pl.database.rum.entities.Rum;
import pl.database.rum.init.HibernateUtil;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableRums extends AbstractTableModel {

    SessionFactory sessionFactory;
    Session session;
    static TableRums instance = null;

    String[] columnNames = {"Id",
            "Name",
            "Alcohol Percentage",
            "Type",
            "Finish",
            "Rating",
            "Minimal Age",
            "Producent",
            "Delete",
            "Update"};

    public Object[][] data;

    List<Producent> producents;
    List<Rum> rums;

    public TableRums() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        getAllProducents();
        this.data = getAllRums();
    }

    private void getAllProducents() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        producents = session.createCriteria(Producent.class).list();
        session.close();
    }

    public static final TableRums getInstance(){
        if (instance==null)
            instance = new TableRums();
        return instance;
    }

    public Object[][] getAllRums() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        rums = session.createCriteria(Rum.class).list();
        session.close();

        Object[][] rumArray = convertRumListTo2DArray(rums);
        return rumArray;
    }

    public Object[][] convertRumListTo2DArray(List<Rum> rumList){
        Object[][] rumArray = new Object[rumList.size()][10];
        for (int i = 0; i < rumList.size(); i++){
            rumArray[i][0] = rumList.get(i).getId();
            rumArray[i][1] = rumList.get(i).getName() != null ? rumList.get(i).getName() : "";
            rumArray[i][2] = rumList.get(i).getAlcoholPercentage()!= null ? rumList.get(i).getAlcoholPercentage() : 0;
            rumArray[i][3] = rumList.get(i).getRumType()!= null ? rumList.get(i).getRumType() : "";
            rumArray[i][4] = rumList.get(i).getFinish()!= null ? rumList.get(i).getFinish() : "";
            rumArray[i][5] = rumList.get(i).getRating()!= null ? rumList.get(i).getRating() : 0;
            rumArray[i][6] = rumList.get(i).getMinimalAge()!= null ? rumList.get(i).getMinimalAge() : 0;
            rumArray[i][7] = rumList.get(i).getProducent()!= null ? rumList.get(i).getProducent().getName() : "";
            rumArray[i][8] = false;
            rumArray[i][9] = false;
        }
        return rumArray;
    }


    public List<Rum> getRumById(long id) {
        session.beginTransaction();
        Criteria crit = session.createCriteria(Rum.class);
        crit.add(Restrictions.eq("id", id));
        List<Rum> rums = crit.list();
        session.close();
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
        if (col == 8) {
            removeRumFromDatabase((Long) data[row][0]);
            this.data = getAllRums();
        } else if (col == 9) {
            updateRumById((Long) data[row][0], row);
        } else {
            data[row][col] = value;
        }
        fireTableDataChanged();
    }

    public void removeRumFromDatabase(Long id){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for(Rum rum : rums) {
            if(rum.getId().equals(id)) {
                session.delete(rum);
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    public void updateRumById(Long id, int row){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Rum rum = new Rum(data[row][1].toString(), (Integer)data[row][2], data[row][3].toString(),
        (Double)data[row][5], data[row][4].toString(), (Integer)data[row][6], findProducent((String)data[row][7]));
        rum.setId(id);
        session.update(rum);
        session.getTransaction().commit();
        session.close();
    }

    private Producent findProducent(String name){
        for(Producent producent : producents){
            if(producent.getName().equals(name)){
                return producent;
            }
        }
        return null;
    }

    public static long addNewRumToDatabase(Rum rum, Session session){
        session.beginTransaction();
        long id = (Long) session.save(rum);
        session.close();
        return id;
    }
}