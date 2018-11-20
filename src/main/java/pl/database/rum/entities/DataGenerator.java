package pl.database.rum.entities;

import org.hibernate.Session;

import java.io.Serializable;

public class DataGenerator {
    public void generate(Session session){
        Producent examleProducter = new Producent("Fiszczu", "POLSKA", 1000);
        session.save(new Rum("Rum1", 43, "MOJTYP", 6.6, "MATOWY", 1000, examleProducter));
        session.save(new Rum("Rum2", 43, "MOJTYP", 6.6, "MATOWY", 1000, examleProducter));
        session.save(new Rum("Rum13", 43, "MOJTYP", 6.6, "MATOWY", 1000, examleProducter));
        session.save(new Rum("Rum3", 43, "MOJTYP", 6.6, "MATOWY", 1000, examleProducter));
        session.save(new Rum("Rum4", 43, "MOJTYP", 6.6, "MATOWY", 1000, examleProducter));
        session.save(new Rum("Rum5", 43, "MOJTYP", 6.6, "MATOWY", 1000, examleProducter));
        session.close();

    }
}
