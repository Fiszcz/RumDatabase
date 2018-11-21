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

public class CreateTest {

    @org.junit.Test
    public void createProducent() throws Exception {
        String name = "name";
        String country = "country";
        Integer yearFounding = 1990;
        Producent producent = new Producent(name, country,yearFounding);
        Assert.assertEquals(name, producent.getName());
        Assert.assertEquals(country, producent.getCountry());
        Assert.assertEquals(yearFounding, producent.getYearFounding());
    }

    @org.junit.Test
    public void testAddNullProducent() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Producent producent = new Producent(null, null, null);

        long id = TableProducents.addNewProducentToDatabase(producent, session);

        TableProducents tableProducents = new TableProducents();
        Producent selectResult = tableProducents.getProducentById(id).get(0);
        tableProducents.removeProducentFromDatabase(id);

        Assert.assertNotNull(selectResult);
    }

    @org.junit.Test
    public void testAddNewProducent() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Producent producent = new Producent("name", "country", 1990);

        long id = TableProducents.addNewProducentToDatabase(producent, session);

        TableProducents tableProducents = new TableProducents();
        Producent selectResult = tableProducents.getProducentById(id).get(0);
        tableProducents.removeProducentFromDatabase(id);


        Assert.assertEquals(producent, selectResult);
    }

    @org.junit.Test
    public void createRum() {
        String name = "Capitan Morgan";
        Integer alcoholPercentage = 35;
        String rumType = "WHITE";
        Double rating = 8.2;
        String finish = "Morgans";
        Integer minimalAge = 24;

        Rum rum = new Rum(name, alcoholPercentage, rumType, rating, finish, minimalAge, null);

        Assert.assertEquals(name, rum.getName());
        Assert.assertEquals(alcoholPercentage, rum.getAlcoholPercentage());
        Assert.assertEquals(rumType, rum.getRumType());
        Assert.assertEquals(rating, rum.getRating());
        Assert.assertEquals(finish, rum.getFinish());
        Assert.assertEquals(minimalAge, rum.getMinimalAge());
    }

    @org.junit.Test
    public void testAddNullRum() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Rum rum = new Rum(null, null, null, null, null,
                null, null);
        long id = TableRums.addNewRumToDatabase(rum, session);
        TableRums tableRums = new TableRums();
        List<Rum> RumList = tableRums.getRumById(id);
        session.close();

        tableRums.removeRumFromDatabase(id);

        Assert.assertEquals(1, RumList.size());
    }

    @org.junit.Test
    public void testAddNewRum() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Rum rum = new Rum("Capitan Morgan", 35, "WHITE",
                8.2, "Morgans", 24, null);

        long id = TableRums.addNewRumToDatabase(rum, session);
        TableRums tableRums = new TableRums();
        Rum selectedRum = tableRums.getRumById(id).get(0);
        session.close();

        tableRums.removeRumFromDatabase(id);

        Assert.assertEquals(rum, selectedRum);
    }

    @org.junit.Test
    public void testAddNewRumWithProducent() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        TableProducents tableProducents = new TableProducents();
        TableRums tableRums = new TableRums();
        Producent producent = new Producent("Bacardi", "USA", 2005);
        long producentId = tableProducents.addNewProducentToDatabase(producent, session);

        session = sessionFactory.openSession();
        Rum rum = new Rum("Superior Carta Blanca ", 37, "WHITE",
                8.2, "Rum Bacardi", 25, producent);
        long rumId = TableRums.addNewRumToDatabase(rum, session);
        Rum selectedRum = tableRums.getRumById(rumId).get(0);

        session.close();

        tableRums.removeRumFromDatabase(producentId);
        tableRums.removeRumFromDatabase(rumId);

        Assert.assertEquals(rum, selectedRum);
    }
}