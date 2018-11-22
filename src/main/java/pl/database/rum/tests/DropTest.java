package pl.database.rum.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import pl.database.rum.app.TableProducents;
import pl.database.rum.app.TableRums;
import pl.database.rum.entities.Producent;
import pl.database.rum.entities.Rum;
import pl.database.rum.init.HibernateUtil;

import java.util.List;

public class DropTest {

    @org.junit.Test
    public void dropProducent() throws Exception {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Producent producent = new Producent("name", "country", 1990);

        long id = TableProducents.addNewProducentToDatabase(producent, session);


        TableProducents.getInstance().removeProducentFromDatabase(id);

        List<Producent> selectResultDB = TableProducents.getInstance().getProducentById(id);

        Assert.assertEquals(0, selectResultDB.size());
    }


    @org.junit.Test
    public void dropRum() throws Exception {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        String name = "Capitan Morgan";
        Integer alcoholPercentage = 35;
        String rumType = "WHITE";
        Double rating = 8.2;
        String finish = "Morgans";
        Integer minimalAge = 24;

        Rum rum = new Rum(name, alcoholPercentage, rumType, rating, finish, minimalAge, null);

        long id = TableRums.addNewRumToDatabase(rum, session);

        TableRums.getInstance().removeRumFromDatabase(id);

        List<Rum> selectResultDB = TableRums.getInstance().getRumById(id);

        Assert.assertEquals(0, selectResultDB.size());
    }
}