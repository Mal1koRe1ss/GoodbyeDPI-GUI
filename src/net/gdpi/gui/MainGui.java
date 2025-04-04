package net.gdpi.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainGui extends JFrame {

    JLabel label = new JLabel("Only this cuz i have no time left...");
    JLabel label2 = new JLabel("Me and my homie(@p0unter) gonna start developing this project.");
    JLabel label3 = new JLabel("Probably tomorrow");

    public MainGui() {
        this.setLayout(null);
        
        label.setBounds(150, 10, 480, 40);
        this.add(label);

        label2.setBounds(60, 30, 480, 40);
        this.add(label2);

        label3.setBounds(175, 50, 480, 40);
        this.add(label3);

        this.setSize(480,150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
