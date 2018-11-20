package pl.database.rum.app;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import pl.database.rum.entities.Producent;
import pl.database.rum.entities.Rum;
import pl.database.rum.init.HibernateUtil;

import javax.swing.table.AbstractTableModel;
import java.util.List;

class TableRums extends AbstractTableModel {

    SessionFactory sessionFactory;
    Session session;

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

    Object[][] data;

    List<Rum> rums;

    public TableRums() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        this.data = getAllRums();
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
            rumArray[i][7] = rumList.get(i).getProducent()!= null ? rumList.get(i).getProducent() : 0;
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
//        System.out.println(data[row][0].getClass());
        long id = Long.parseLong(String.valueOf(data[row][0]));
        if (col == 9) {
            Rum rum = getRumById(10).get(0);
            Producent examleProducter = new Producent("Fiszczuv2", "POLSKA", 10100);

            Rum newRum = new Rum("SIEMA", 413, "MOJTYP", 6.6, "MATOWY", 1000, examleProducter);
            newRum.setID(id);
            System.out.println(rum);
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(newRum);
            session.getTransaction().commit();
            session.close();
//            Transaction tx =  session.beginTransaction();
//            try {
//                session.update(newRum);
//                tx.commit();
//            } catch (Exception e) {
//                if (tx != null) tx.rollback();
//                throw e;
//            } finally {
//                session.close();
////            }

            ////            Query query = session.createSQLQuery("UPDATE rums SET name = 'DUPA' WHERE id = 10");
////            Query query = session.createQuery("UPDATE rums SET name = :name WHERE id = :id");
////            query.setParameter("name", "wooodka");
////            query.setParameter("id", 10);
//            int result = query.executeUpdate();
//            System.out.println("Rows affected: " + result);
//            System.out.println(newRum);
//            System.out.println("dupa");
//            session.getTransaction().commit();
        }
        else if (col == 8) {
            removeRumFromDatabase((Long)data[row][0]);
            getAllRums();
        } else
            data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    private void removeRumFromDatabase(Long id){
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

}