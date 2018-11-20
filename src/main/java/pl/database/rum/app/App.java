package pl.database.rum.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pl.database.rum.entities.DataGenerator;
import pl.database.rum.entities.Producent;
import pl.database.rum.init.HibernateUtil;

import java.awt.*;

public class App {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
//        session.beginTransaction();

        DataGenerator g = new DataGenerator();
        g.generate(session);


        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WindowApp();
            }
        });
    }

}
