package pl.database.rum.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.database.rum.entities.Producent;
import pl.database.rum.init.HibernateUtil;

import java.awt.*;

public class App {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();


        Producent exampleProducent = new Producent("Fiszcz Company", "poland", 2131);
        session.save(exampleProducent);
        session.getTransaction().commit();
        session.close();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WindowApp();
            }
        });
    }

}
