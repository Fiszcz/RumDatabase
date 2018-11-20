package pl.database.rum.app;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.database.rum.entities.Producent;
import pl.database.rum.entities.Rum;
import pl.database.rum.init.HibernateUtil;

import java.awt.*;
import java.util.List;

public class App {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

//        Producent exampleProducent = new Producent();
//        session.save(exampleProducent);
//        session.getTransaction().commit();
        Transaction t = session.beginTransaction();
        Criteria cr = session.createCriteria(Rum.class);
        List aaa = cr.list();
        System.out.println(aaa.get(0).toString());

//        session.close();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WindowApp();
            }
        });
    }
    public void addNewRum(){
        String insertQ = "INSERT INTO Rum(id, alcoholPercentage, finish, " +
                "minimalAge, name, rating, rumType, producent_id) " +
                "VALUES(2, 40, 'aaa', 18, 'rumek', 10, 'WHITE', 1";
        Query query = session.createQuery(insertQ);
        int result = query.executeUpdate();
    }


}
