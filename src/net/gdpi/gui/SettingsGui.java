package net.gdpi.gui;

import javax.swing.*;

import net.gdpi.handlers.ConfigHandler;

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

        JPanel configPanel = new JPanel();
        configPanel.setLayout(new BoxLayout(configPanel, BoxLayout.Y_AXIS));

        // Örnek UI bileşenleri
        JCheckBox customRedirCheckBox = new JCheckBox("Custom DNS Redir");
        JTextField customParamField = new JTextField(20);
        customParamField.setToolTipText("Custom parameter for DNS Redir");

        JButton saveConfigButton = new JButton("Save Config");

        configPanel.add(new JLabel("Custom DNS Redir:"));
        configPanel.add(customRedirCheckBox);
        configPanel.add(new JLabel("Custom Parameter:"));
        configPanel.add(customParamField);
        configPanel.add(saveConfigButton);

        customizePanel.add(configPanel, BorderLayout.NORTH);
        customizePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tabbedPane.addTab("Customize", customizePanel);

        ConfigHandler configHandler = new ConfigHandler();

        saveConfigButton.addActionListener(e -> {
            boolean customRedir = customRedirCheckBox.isSelected();
            String customParam = customParamField.getText();

            // ConfigHandler'a değerleri kaydetme
            configHandler.saveConfig(customRedir, customParam);
            MainGui.customRedir = true;
        });
    }
}