package pl.database.rum.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class ProducentsTab extends JPanel {

    ProducentsTab() {
        super(new BorderLayout());

        JLabel nameLabel, countryLabel, yearFoundingLabel;

        JTextField nameInput = new JTextField("", 15);

        JTextField countryInput = new JTextField("", 10);

        SpinnerModel yearFoundingSpinnerValue = new SpinnerNumberModel(1900, //initial value
                0, //minimum value
                Calendar.getInstance().get(Calendar.YEAR), //maximum value
                1); //step
        JSpinner yearFoundingSpinner = new JSpinner(yearFoundingSpinnerValue);

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
        panelForm.add(addButton);

        add(new TableView("PRODUCTS"));
        add(panelForm, BorderLayout.NORTH);
    }
}
