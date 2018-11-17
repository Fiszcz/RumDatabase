package pl.database.rum.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.database.rum.init.HibernateUtil;

import java.awt.*;

public class App {

//    public static void main(String[] args) {
////
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
////
////        List<Student> students = session.createQuery("from Student").list();
////
////        session.close();
////
////        for (Student st : students) {
////            System.out.println(st.getId()+". "+st.getFirstName()+" "+st.getAge());
////        }
////    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WindowApp();
            }
        });
    }

}
