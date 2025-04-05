package net.gdpi.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;
import net.gdpi.handlers.ConfigHandler;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import net.gdpi.handlers.FileHandler;

public class SettingsGui extends JDialog {

    private String[] modesets = {
        "1 (Legacy)",
        "2 (Legacy)",
        "3 (Legacy)",
        "4 (Legacy)",
        "5 (Modern)",
        "6 (Modern)",
        "7 (Modern)",
        "8 (Modern)",
        "9 (DEFAULT)"};

    private String[] modesetToolTip = {
        "-p -r -s -f 2 -k 2 -n -e 2",
        "-p -r -s -f 2 -k 2 -n -e 40",
        "-p -r -s -e 40",
        "-p -r -s",
        "-f 2 -e 2 --auto-ttl --reverse-frag --max-payload",
        "-f 2 -e 2 --wrong-seq --reverse-frag --max-payload",
        "-f 2 -e 2 --wrong-chksum --reverse-frag --max-payload",
        "-f 2 -e 2 --wrong-seq --wrong-chksum --reverse-frag --max-payload",
        "-f 2 -e 2 --wrong-seq --wrong-chksum --reverse-frag --max-payload -q"};

    private JTabbedPane tabbedPane;
    private JPanel generalPanel;
    private JPanel customizePanel;
    private JPanel modesetPanel;
    private JButton saveButton;
    private JButton okButton;
    private JButton cancelButton;
    private MainGui mainGui;

    private java.util.List<JCheckBox> modesetCheckboxes = new ArrayList<>();
    private JCheckBox customRedirCheckBox;
    private JTextField customParamField;
    private JTextField serviceParamField;

    private ConfigHandler configHandler;

    public SettingsGui(MainGui mainGui) {
        super(mainGui, "Settings", true);
        this.mainGui = mainGui;

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        setupFrame();
        createTabs();
        createButtons();
        setLocationRelativeTo(null);

        configHandler = new ConfigHandler(mainGui);
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
        modesetTab();
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

    private void modesetTab() {
        modesetPanel = new JPanel(new BorderLayout());
        modesetPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));

        modesetCheckboxes.clear(); // Listede eski checkbox'ları temizle

        for (int i = 0; i < modesets.length; i++) { // modesets dizisinin tamamı için
            JCheckBox modesetCheckBox = new JCheckBox(modesets[i]);
            modesetCheckBox.setToolTipText(modesetToolTip[i]);
            modesetCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Checkbox seçilirse diğerlerini temizle VE customRedir'i kapat
            modesetCheckBox.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    for (JCheckBox cb : modesetCheckboxes) {
                        if (cb != e.getSource()) cb.setSelected(false);
                    }
                    customRedirCheckBox.setSelected(false); // Custom seçenekleri kapat
                }
            });

            modesetCheckboxes.add(modesetCheckBox);
            checkboxPanel.add(modesetCheckBox);
            checkboxPanel.add(Box.createVerticalStrut(5));
        }

        modesetPanel.add(checkboxPanel, BorderLayout.NORTH);
        tabbedPane.addTab("Modesets", modesetPanel);
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
        customRedirCheckBox = new JCheckBox("Enable Custom Config");
        checkboxPanel.add(customRedirCheckBox);

        customParamField = new JTextField(20);
        customParamField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        String fieldText_1 = "Format: --dns-server=1.1.1.1";
        customParamField.setToolTipText(fieldText_1);
        addPlaceHolder(customParamField, fieldText_1);

        JPanel paramPanel = new JPanel();
        paramPanel.setLayout(new GridLayout(1, 2));
        paramPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel paramLabel = new JLabel("DNSRedir Parameters:");
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

        // Custom seçilirse modeset'leri temizle
        customRedirCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                for (JCheckBox cb : modesetCheckboxes) {
                    cb.setSelected(false);
                }
            }
        });

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
    }

    private void saveConfig() {
        boolean anyModesetSelected = modesetCheckboxes.stream()
            .anyMatch(JCheckBox::isSelected);
    
        boolean customRedir;
        boolean customService;
        String dnsRedirParam = "None"; // Varsayılan değer
        String serviceParam = "None";   // Varsayılan değer
    
        if (anyModesetSelected) {
            customRedir = false;
            customService = false;
            customRedirCheckBox.setSelected(false);
    
            // Seçilen modeset'in index'ini bul
            int selectedIndex = -1;
            for (int i = 0; i < modesetCheckboxes.size(); i++) {
                if (modesetCheckboxes.get(i).isSelected()) {
                    selectedIndex = i;
                    break;
                }
            }
            dnsRedirParam = "-" + (selectedIndex + 1); // DNSRedir parametresi
            serviceParam = "-" + (selectedIndex + 1);   // Service parametresi
            mainGui.log("Selected Modeset: " + (selectedIndex + 1));
        } else {
            customRedir = customRedirCheckBox.isSelected();
        customService = customRedirCheckBox.isSelected();

        // DNSRedir Parametresi (Placeholder ise "None" yap)
        dnsRedirParam = (customParamField.getForeground() == Color.GRAY || 
                        customParamField.getText().trim().isEmpty()) ? 
                        "None" : customParamField.getText();

        // Service Parametresi (Placeholder ise "None" yap)
        serviceParam = (serviceParamField.getForeground() == Color.GRAY || 
                       serviceParamField.getText().trim().isEmpty()) ? 
                       "None" : serviceParamField.getText();

        // Konsola "None" veya gerçek değeri yaz
        mainGui.log("DNSRedir Params: " + dnsRedirParam);
        mainGui.log("Service Params: " + serviceParam);
        }
    
        configHandler.saveConfig(customRedir, customService, dnsRedirParam, serviceParam);
        MainGui.customRedir = customRedir;
        MainGui.customService = customService;
    
        JOptionPane.showMessageDialog(this,
            "Settings saved successfully!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
}