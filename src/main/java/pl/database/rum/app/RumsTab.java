package pl.database.rum.app;

import pl.database.rum.entities.TypeRum;

import javax.swing.*;
import java.awt.*;

public class RumsTab extends JPanel {

    RumsTab() {
        super(new BorderLayout());

        JLabel nameLabel, alcoholPercentageLabel, rumTypeLabel, ratingLabel, finishLabel, minimalAgeLabel, producentLabel;

        JTextField nameInput = new JTextField("", 15);

        SpinnerModel alcoholPercentageSpinnerValue = new SpinnerNumberModel(0, //initial value
                0, //minimum value
                100, //maximum value
                1); //step
        JSpinner alcoholPercentageSpinner = new JSpinner(alcoholPercentageSpinnerValue);

        JComboBox rumTypeCombo = new JComboBox(TypeRum.values());

        SpinnerModel ratingSpinnerValue = new SpinnerNumberModel(0, //initial value
                0, //minimum value
                10, //maximum value
                0.1); //step
        JSpinner ratingSpinner = new JSpinner(ratingSpinnerValue);

        JTextField finishInput = new JTextField("", 27);

        SpinnerModel minimalAgeSpinnerValue = new SpinnerNumberModel(0, //initial value
                0, //minimum value
                100000, //maximum value
                1); //step
        JSpinner minimalAgeSpinner = new JSpinner(minimalAgeSpinnerValue);

        JComboBox producentsCombo = new JComboBox();
        producentsCombo.setPreferredSize(new Dimension(130, 20));

        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.add(nameInput);
        nameLabel = new JLabel("Name");
        nameLabel.setLabelFor(nameInput);
        namePanel.add(nameLabel, BorderLayout.NORTH);

        JPanel alcoholPercentagePanel = new JPanel(new BorderLayout());
        alcoholPercentagePanel.add(alcoholPercentageSpinner);
        alcoholPercentageLabel = new JLabel("Alcohol Percentage");
        alcoholPercentageLabel.setLabelFor(alcoholPercentageSpinner);
        alcoholPercentagePanel.add(alcoholPercentageLabel, BorderLayout.NORTH);

        JPanel rumTypePanel = new JPanel(new BorderLayout());
        rumTypePanel.add(rumTypeCombo);
        rumTypeLabel = new JLabel("Rum type");
        rumTypeLabel.setLabelFor(rumTypeCombo);
        rumTypePanel.add(rumTypeLabel, BorderLayout.NORTH);

        JPanel finishPanel = new JPanel(new BorderLayout());
        finishPanel.add(finishInput);
        finishLabel = new JLabel("Finish");
        finishLabel.setLabelFor(finishInput);
        finishPanel.add(finishLabel, BorderLayout.NORTH);

        JPanel minimalAgePanel = new JPanel(new BorderLayout());
        minimalAgePanel.add(minimalAgeSpinner);
        minimalAgeLabel = new JLabel("Minimal Age");
        minimalAgeLabel.setLabelFor(minimalAgeSpinner);
        minimalAgePanel.add(minimalAgeLabel, BorderLayout.NORTH);

        JPanel ratingPanel = new JPanel(new BorderLayout());
        ratingPanel.add(ratingSpinner);
        ratingLabel = new JLabel("Ratings");
        ratingLabel.setLabelFor(ratingSpinner);
        ratingPanel.add(ratingLabel, BorderLayout.NORTH);

        JPanel producentPanel = new JPanel(new BorderLayout());
        producentPanel.add(producentsCombo);
        producentLabel = new JLabel("Producent");
        producentLabel.setLabelFor(ratingSpinner);
        producentPanel.add(producentLabel, BorderLayout.NORTH);

        JPanel panelForm = new JPanel();

        panelForm.add(namePanel);
        panelForm.add(alcoholPercentagePanel);
        panelForm.add(rumTypePanel);
        panelForm.add(finishPanel);
        panelForm.add(minimalAgePanel);
        panelForm.add(ratingPanel);
        panelForm.add(producentPanel);
        JButton addButton = new JButton("Add new rum");
        addButton.setPreferredSize(new Dimension(175, 40));
        panelForm.add(addButton);

        add(new TableView("RUMS"));
        add(panelForm, BorderLayout.NORTH);
    }
}
