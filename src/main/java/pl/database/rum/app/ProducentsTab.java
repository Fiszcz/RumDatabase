package pl.database.rum.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.database.rum.entities.Producent;
import pl.database.rum.init.HibernateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class ProducentsTab extends JPanel {

    SessionFactory sessionFactory;
    Session session;

    ProducentsTab() {
        super(new BorderLayout());

        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();

        JLabel nameLabel, countryLabel, yearFoundingLabel;

        final JTextField nameInput = new JTextField("", 15);

        final JTextField countryInput = new JTextField("", 10);

        SpinnerModel yearFoundingSpinnerValue = new SpinnerNumberModel(1900, //initial value
                0, //minimum value
                Calendar.getInstance().get(Calendar.YEAR), //maximum value
                1); //step
        final JSpinner yearFoundingSpinner = new JSpinner(yearFoundingSpinnerValue);

        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.add(nameInput);
        nameLabel = new JLabel("Name");
        nameLabel.setLabelFor(nameInput);
        namePanel.add(nameLabel, BorderLayout.NORTH);

        JPanel countryPanel = new JPanel(new BorderLayout());
        countryPanel.add(countryInput);
        countryLabel = new JLabel("Country");
        countryLabel.setLabelFor(countryInput);
        countryPanel.add(countryLabel, BorderLayout.NORTH);

        JPanel yearFoundingPanel = new JPanel(new BorderLayout());
        yearFoundingPanel.add(yearFoundingSpinner);
        yearFoundingLabel = new JLabel("Year Founding");
        yearFoundingLabel.setLabelFor(yearFoundingSpinner);
        yearFoundingPanel.add(yearFoundingLabel, BorderLayout.NORTH);

        JPanel panelForm = new JPanel();

        panelForm.add(namePanel);
        panelForm.add(countryPanel);
        panelForm.add(yearFoundingPanel);
        JButton addButton = new JButton("Add new producent");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(e);
            }
        });
        addButton.setPreferredSize(new Dimension(175, 40));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameInput.getText();
                String country = countryInput.getText();
                Integer yearFounding = (Integer) yearFoundingSpinner.getValue();

                Producent producent = new Producent(name, country, yearFounding);

                TableProducents.addNewProducentToDatabase(producent, session);
                TableProducents tableProducents = TableProducents.getInstance();
                tableProducents.data = tableProducents.getAllProducents();
                tableProducents.fireTableDataChanged();
            }
        });
        panelForm.add(addButton);

        add(new TableView("PRODUCTS"));
        add(panelForm, BorderLayout.NORTH);
    }


}
