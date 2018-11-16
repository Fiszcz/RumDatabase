package pl.database.rum.app;

import javax.swing.*;
import java.awt.*;

public class WindowApp extends JFrame {

    public WindowApp() {
        super("Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLocation(50,50);

        setLayout(new FlowLayout());

        add(new JButton("Dodaj nowy rum"));
        add(new JButton("Dodaj nowego producenta"));
        add(new JButton("Pokaż listę rumów"));
        add(new JButton("Pokaż listę producentów"));

        setVisible(true);
    }
}