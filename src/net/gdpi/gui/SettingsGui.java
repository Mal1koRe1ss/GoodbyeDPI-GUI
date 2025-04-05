package net.gdpi.gui;

import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import net.gdpi.handlers.ConfigHandler;
import java.awt.*;

public class SettingsGui extends JDialog {

    private JTabbedPane tabbedPane;
    private JPanel generalPanel;
    private JPanel customizePanel;
    private JButton saveButton;
    private JButton okButton;
    private JButton cancelButton;

    private JCheckBox customRedirCheckBox;
    private JTextField customParamField;

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
        loadConfig(); // Mevcut ayarları yükle
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

    private void generalTab() {
        generalPanel = new JPanel();
        generalPanel.setLayout(new BorderLayout());
        generalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Genel ayarlar içeriği
        JPanel content = new JPanel();
        content.add(new JLabel("General Application Settings"));
        generalPanel.add(content, BorderLayout.CENTER);
        
        tabbedPane.addTab("General", generalPanel);
    }

    private void customizeTab() {
        customizePanel = new JPanel();
        customizePanel.setLayout(new BorderLayout());
        customizePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // DNS Özelleştirme Paneli
        JPanel dnsPanel = new JPanel();
        dnsPanel.setLayout(new BoxLayout(dnsPanel, BoxLayout.Y_AXIS));
        dnsPanel.setBorder(BorderFactory.createTitledBorder("Custom configuration"));

        customRedirCheckBox = new JCheckBox("Enable Custom DNS Redir");
        customRedirCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        customParamField = new JTextField(20);
        customParamField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        customParamField.setToolTipText("Format: --dns-server=1.1.1.1");

        dnsPanel.add(Box.createVerticalStrut(5));
        dnsPanel.add(customRedirCheckBox);
        dnsPanel.add(Box.createVerticalStrut(10));
        dnsPanel.add(new JLabel("Custom DNS Parameters:"));
        dnsPanel.add(customParamField);
        dnsPanel.add(Box.createVerticalGlue());

        customizePanel.add(dnsPanel, BorderLayout.NORTH);
        tabbedPane.addTab("Customize", customizePanel);
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
        // ConfigHandler'dan ayarları yükle
        customRedirCheckBox.setSelected(MainGui.customRedir);
        // customParamField.setText(configHandler.getCustomDnsParams());
    }

    private void saveConfig() {
        boolean customRedir = customRedirCheckBox.isSelected();
        boolean customService = customRedirCheckBox.isSelected();
        String customParam = customParamField.getText();

        // ConfigHandler'a kaydet
        configHandler.saveConfig(customRedir, customService, customParam);
        MainGui.customRedir = customRedir;
        MainGui.customService = customService;

        JOptionPane.showMessageDialog(this,
            "Settings saved successfully!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
}