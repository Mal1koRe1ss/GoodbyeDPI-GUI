package net.gdpi.gui;

import javax.swing.*;
import java.awt.*;

public class SettingsGui extends JDialog {

    private JTabbedPane tabbedPane;
    private JPanel generalPanel;
    private JPanel securityPanel;
    private JPanel customizePanel;

    public SettingsGui(MainGui mainGui) {
        super(mainGui, "Settings", true); //Make the Settings GUI modal
        setupFrame();
        createTabs();
    }

    private void setupFrame() {
        setTitle("Settings");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void createTabs() {
        generalTab();
        securityTab();
        customizeTab();
    }

    private void generalTab() {
        // General Tab
        generalPanel = new JPanel();
        generalPanel.setLayout(new BorderLayout());
        generalPanel.add(new JLabel("General Settings Content"), BorderLayout.NORTH);
        generalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tabbedPane.addTab("General", generalPanel);
    }

    private void securityTab() {
        // Security Tab
        securityPanel = new JPanel();
        securityPanel.setLayout(new BorderLayout());
        securityPanel.add(new JLabel("Security Settings Content"), BorderLayout.NORTH);
        securityPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tabbedPane.addTab("Security", securityPanel);
    }

    private void customizeTab() {
        // Customize Tab
        customizePanel = new JPanel();
        customizePanel.setLayout(new BorderLayout());
        customizePanel.add(new JLabel("Customize Settings Content"), BorderLayout.NORTH);
        customizePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tabbedPane.addTab("Customize", customizePanel);
    }
}