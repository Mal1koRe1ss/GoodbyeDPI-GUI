package net.gdpi.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;
import net.gdpi.handlers.ConfigHandler;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SettingsGui extends JDialog {

    private JTabbedPane tabbedPane;
    private JPanel generalPanel;
    private JPanel customizePanel;
    private JButton saveButton;
    private JButton okButton;
    private JButton cancelButton;

    private JCheckBox customRedirCheckBox;
    private JTextField customParamField;
    private JTextField serviceParamField;

    private ConfigHandler configHandler;

    public SettingsGui(MainGui mainGui) {
        super(mainGui, "Settings", true);
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
        loadConfig();
    }

    private void setupFrame() {
        setTitle("Settings");
        setSize(500, 500);
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

    private void generalTab() {
        generalPanel = new JPanel();
        generalPanel.setLayout(new BorderLayout());
        generalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // General Setting Content
        JPanel content = new JPanel();
        content.add(new JLabel("General Application Settings"));
        generalPanel.add(content, BorderLayout.CENTER);
        
        tabbedPane.addTab("General", generalPanel);
    }

    private void customizeTab() {
        customizePanel = new JPanel();
        customizePanel.setLayout(new BorderLayout());
        customizePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // DNS Adjustment Panel
        JPanel dnsPanel = new JPanel();
        dnsPanel.setLayout(new BoxLayout(dnsPanel, BoxLayout.Y_AXIS));
        dnsPanel.setBorder(BorderFactory.createTitledBorder("Custom configuration"));

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        customRedirCheckBox = new JCheckBox("Enable Custom DNS Redir");
        checkboxPanel.add(customRedirCheckBox);

        customParamField = new JTextField(20);
        customParamField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        String fieldText_1 = "Format: --dns-server=1.1.1.1";
        customParamField.setToolTipText(fieldText_1);
        addPlaceHolder(customParamField, fieldText_1);

        JPanel paramPanel = new JPanel();
        paramPanel.setLayout(new GridLayout(1, 2));
        paramPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel paramLabel = new JLabel("DNS Parameters:");
        paramPanel.add(paramLabel, BorderLayout.WEST);
        paramPanel.add(customParamField, BorderLayout.CENTER);

        serviceParamField = new JTextField(20);
        serviceParamField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        String fieldText_2 = "Format: --dns-server=1.1.1.1";
        serviceParamField.setToolTipText(fieldText_2);
        addPlaceHolder(serviceParamField, fieldText_2);

        JPanel servicePanel = new JPanel();
        servicePanel.setLayout(new GridLayout(1, 2));
        servicePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel serviceLabel = new JLabel("Service Parameters:");
        servicePanel.add(serviceLabel, BorderLayout.WEST);
        servicePanel.add(serviceParamField, BorderLayout.CENTER);

        dnsPanel.add(Box.createVerticalStrut(5));
        dnsPanel.add(checkboxPanel);
        dnsPanel.add(Box.createVerticalStrut(5));
        dnsPanel.add(paramPanel);
        dnsPanel.add(Box.createVerticalStrut(5));
        dnsPanel.add(servicePanel);
        dnsPanel.add(Box.createVerticalGlue());

        customizePanel.add(dnsPanel, BorderLayout.NORTH);
        tabbedPane.addTab("Customize", customizePanel);
    }

    private void addPlaceHolder(JTextField field, String text) {
        field.setText(text);
        field.setForeground(Color.GRAY);
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(text)) {
                    field.setText("");
                    field.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(text);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void createButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        saveButton = new JButton("Save");
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        buttonPanel.add(saveButton);
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners
        saveButton.addActionListener(e -> saveConfig());
        okButton.addActionListener(e -> { saveConfig(); dispose(); });
        cancelButton.addActionListener(e -> dispose());
    }

    private void loadConfig() {
        customRedirCheckBox.setSelected(MainGui.customRedir);
        // customParamField.setText(configHandler.getCustomDnsParams());
    }

    private void saveConfig() {
        boolean customRedir = customRedirCheckBox.isSelected();
        boolean customService = customRedirCheckBox.isSelected();
        String customParam = customParamField.getText();

        // Save ConfigHandler
        configHandler.saveConfig(customRedir, customService, customParam);
        MainGui.customRedir = customRedir;
        MainGui.customService = customService;

        JOptionPane.showMessageDialog(this,
            "Settings saved successfully!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
}