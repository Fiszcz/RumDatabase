package pl.database.rum.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import pl.database.rum.entities.Producent;

import java.util.ArrayList;
import java.util.List;

import pl.database.rum.app.*;
import pl.database.rum.init.HibernateUtil;

/**
 * Created by miki on 2018-11-21.
 */
public class CreateTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void createProducent() {
        String name = "name";
        String country = "country";
        Integer yearFounding = 1990;
        Producent producent = new Producent(name, country,yearFounding);
        Assert.assertSame(name, producent.getName());
        Assert.assertSame(country, producent.getCountry());
        Assert.assertEquals(yearFounding, producent.getYearFounding());
    }

    @org.junit.Test
    public void testAddNewProducentToDatabase() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Producent producent = new Producent("name", "country", 1990);

        long id = TableProducents.addNewProducentToDatabase(producent, session);

        TableProducents tableProducents = new TableProducents();
        Producent selectResult = tableProducents.getProducentById(id).get(0);
        session.close();

        System.out.println(producent.toString());
        System.out.println(id);
        System.out.println(selectResult.toString());

        Assert.assertEquals(producent, selectResult);
    }
}