package net.gdpi.gui;

import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import net.gdpi.handlers.ConfigHandler;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsGui extends JDialog {

    private JTabbedPane tabbedPane;
    private JPanel generalPanel;
    private JPanel securityPanel;
    private JPanel customizePanel;
    private JButton saveButton;
    private JButton okButton;
    private JButton cancelButton;

    private JCheckBox customRedirCheckBox;
    private JTextField customParamField;

    private ConfigHandler configHandler;

    public SettingsGui(MainGui mainGui) {
        super(mainGui, "Settings", true); //Make the Settings GUI modal
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        setupFrame();
        createTabs();
        createButtons();
        setLocationRelativeTo(null);

        configHandler = new ConfigHandler();
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
        customizeTab();
    }

    private void createButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        saveButton = new JButton("Save");
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        buttonPanel.add(saveButton);
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveConfig();
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveConfig();
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void generalTab() {
        // General Tab
        generalPanel = new JPanel();
        generalPanel.setLayout(new BorderLayout());
        generalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Config Panel
        JPanel configPanel = new JPanel();
        configPanel.setLayout(new BoxLayout(configPanel, BoxLayout.Y_AXIS));
        configPanel.setBorder(BorderFactory.createTitledBorder("Custom DNS Redir Settings"));

        customRedirCheckBox = new JCheckBox("Enable Custom DNS Redir");
        customRedirCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        customParamField = new JTextField(20);
        customParamField.setToolTipText("Enter custom parameters for DNS Redir");
        customParamField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Add components to the config panel
        configPanel.add(Box.createVerticalStrut(5));
        configPanel.add(customRedirCheckBox);
        configPanel.add(Box.createVerticalStrut(10));
        configPanel.add(new JLabel("Custom Parameter:"));
        configPanel.add(customParamField);
        configPanel.add(Box.createVerticalStrut(10));

        // Add the config panel to the general panel
        generalPanel.add(configPanel, BorderLayout.NORTH);

        // Add the General tab to the tabbed pane
        tabbedPane.addTab("General", generalPanel);
    }

    private void customizeTab() {
        // Customize Tab
        customizePanel = new JPanel();
        customizePanel.setLayout(new BorderLayout());
        customizePanel.add(new JLabel("Customize Content"), BorderLayout.NORTH);
        customizePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tabbedPane.addTab("Customize", securityPanel);
    }

    private void saveConfig() {
        boolean customRedir = customRedirCheckBox.isSelected();
        String customParam = customParamField.getText();

        // Save values to ConfigHandler
        configHandler.saveConfig(customRedir, customParam);
        MainGui.customRedir = true;

        JOptionPane.showMessageDialog(this,
                "Configuration saved successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }
}