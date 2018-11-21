package pl.database.rum.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import pl.database.rum.entities.Producent;
import pl.database.rum.app.*;
import pl.database.rum.init.HibernateUtil;


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
    public void testAddNullAsNewProducent() {
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
    public void testAddNewProducentToDatabase() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Producent producent = new Producent("name", "country", 1990);

        long id = TableProducents.addNewProducentToDatabase(producent, session);

        TableProducents tableProducents = new TableProducents();
        Producent selectResult = tableProducents.getProducentById(id).get(0);
        tableProducents.removeProducentFromDatabase(id);


        Assert.assertEquals(producent, selectResult);
    }
}