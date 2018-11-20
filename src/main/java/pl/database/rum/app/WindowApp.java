package pl.database.rum.app;

import javax.swing.*;
import java.awt.*;

public class WindowApp extends JFrame {

    public WindowApp() {
        super("Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1250, 1000);
        setLocation(300,0);

        setLayout(new FlowLayout());

        JTabbedPane tabs = new JTabbedPane();
        tabs.setBounds(0,0,1250,1000);
        tabs.add("Rums", new RumsTab());
        tabs.add("Producents", new ProducentsTab());

        add(tabs);

        setVisible(true);
    }
}