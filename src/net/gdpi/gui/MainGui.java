package net.gdpi.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class MainGui extends JFrame implements ActionListener {

    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem settings = new JMenuItem("Settings");
    JButton redir = new JButton("Open DNSRedir");
    JButton service_install = new JButton("Install service");
    JButton service_remove = new JButton("Remove service");

    public MainGui() {
        
        menu.add(settings);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
        settings.addActionListener(this);

        setupButtons();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        redir.setAlignmentX(Component.CENTER_ALIGNMENT);
        service_install.setAlignmentX(Component.CENTER_ALIGNMENT);
        service_remove.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(redir);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(service_install);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(service_remove);

        this.add(buttonPanel, BorderLayout.EAST);
        
        this.setTitle("Main");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==settings) {
            new SettingsGui();
        } else if (e.getSource()==redir) {
            // TODO Redir openner
        } else if (e.getSource()==service_install) {
            // TODO Service Installer (net.gdpi.service.install.java)
        } else if (e.getSource()==service_remove) {
            // TODO Service Remover (net.gdpi.service.install.java)
        }
    }

    public void setupButtons() {
        setButtonSize();
        setButtonAlign();
    }

    public void setButtonSize() {
        int width = 200;
        int height = 50;
        
        Dimension size = new Dimension(width, height);
        
        // Tüm boyutları aynı yap
        redir.setPreferredSize(size);
        redir.setMinimumSize(size);
        redir.setMaximumSize(size);
        
        service_install.setPreferredSize(size);
        service_install.setMinimumSize(size);
        service_install.setMaximumSize(size);
        
        service_remove.setPreferredSize(size);
        service_remove.setMinimumSize(size);
        service_remove.setMaximumSize(size);
    }

    public void setButtonAlign() {
        redir.setHorizontalTextPosition(SwingConstants.CENTER);
        service_install.setHorizontalTextPosition(SwingConstants.CENTER);
        service_remove.setHorizontalTextPosition(SwingConstants.CENTER);
    }

}
