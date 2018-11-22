package pl.database.rum.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import pl.database.rum.app.TableProducents;
import pl.database.rum.entities.Producent;
import pl.database.rum.init.HibernateUtil;

public class UpdateTest {

    @org.junit.Test
    public void updateProducent() throws Exception {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();


        Producent producent = new Producent("name", "country", 1990);

        long id = TableProducents.addNewProducentToDatabase(producent, session);

        TableProducents tableProducents = new TableProducents();
        Producent selectResult = tableProducents.getProducentById(id).get(0);

        String name = "Cyber";
        String country = "Punk";
        Integer yearFounding = 2077;

        selectResult.setName(name);
        selectResult.setCountry(country);
        selectResult.setYearFounding(yearFounding);

        updateProdById(id, name, country, yearFounding);

        Producent selectResultDB = tableProducents.getProducentById(id).get(0);


        Assert.assertEquals(selectResult, selectResultDB);

        tableProducents.removeProducentFromDatabase(id);

    }

    public void updateProdById(Long id, String name, String Country, Integer year){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Producent producent = new Producent(name, Country, year);
        producent.setId(id);
        session.update(producent);
        session.getTransaction().commit();
        session.close();
    }
}