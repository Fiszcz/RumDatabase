package pl.database.rum.app;

import javax.swing.*;
import java.awt.*;

public class TableView extends JPanel {
    private boolean DEBUG = false;

    public TableView() {
        super(new GridLayout(1, 0));

        JTable table = new JTable(new TableRums());
        table.setPreferredScrollableViewportSize(new Dimension(800, 500));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }
}