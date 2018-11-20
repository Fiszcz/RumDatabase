package pl.database.rum.app;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import pl.database.rum.entities.Producent;
import pl.database.rum.entities.Rum;
import pl.database.rum.init.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
            "Delete",
            "Update"};

    List<Rum> rums;
    List<Rum> rum;

    Object[][] data = {
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false, false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false, false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false, false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false, false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false, false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false, false},
            {1, "B.Tech", 33, "White", "Wooden", 6.2, 53, "Scotland Rum", false, false},
    };

    public TableRums(){
        this.rums = getAllRums();
        for (Rum rum : this.rums){
            System.out.println(rum.toString());
        }
//        this.rums = getRumById(3);
//        System.out.println(this.rum.get(0).toString());
    }

    public List<Rum> getAllRums(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Rum> rums = session.createCriteria(Rum.class).list();
        session.getTransaction().commit();
        session.close();
        return rums;
    }

    public List<Rum> getRumById(long id){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
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
        } else {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

}