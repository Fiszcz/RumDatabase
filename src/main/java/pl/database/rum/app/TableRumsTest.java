package pl.database.rum.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import pl.database.rum.entities.Producent;
import pl.database.rum.entities.Rum;
import pl.database.rum.init.HibernateUtil;

import static org.junit.Assert.*;

public class TableRumsTest {



    @Test
    public void getAllRums() {
        TableRums tb = new TableRums();
        Object[][] allRums = tb.getAllRums();
        for (Object[] row : allRums) {
            Long rumID = (Long) row[0];
            tb.removeRumFromDatabase(rumID);
        }
        SessionFactory sessionFactory;
        Session session;

        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        Producent prod = new Producent("FISzCZ", "PL", 2132);
        Rum r = new Rum("asdasd", 21, "M", 1.2, "MA", 22, prod);
        tb.addNewRumToDatabase(r,session );

        Object[][] newtable = tb.getAllRums();
        int l = newtable.length;
        Assert.assertEquals(l,1);

    }

    @Test
    public void updateRumById(){
        TableRums tb = new TableRums();
        Object[][] allRums = tb.getAllRums();
        allRums[0][1]="siema";
        System.out.print(allRums[0][0]);
        
        tb.updateRumById((Long) allRums[0][0],0);
        tb= new TableRums();

        allRums=tb.getAllRums();
        Assert.assertEquals(allRums[0][1],"siema");

    }
}