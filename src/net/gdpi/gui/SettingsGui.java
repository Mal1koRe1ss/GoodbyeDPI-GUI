package net.gdpi.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;

public class SettingsGui extends JFrame implements ActionListener {

    private JPanel mainContent;
    private JPanel modesetPanel;
    private JPanel configPanel;
    private JButton btnSwitch;
    private CardLayout cardLayout;
    private String[] modesets = {"1 (Legacy)","2 (Legacy)","3 (Legacy)","4 (Legacy)",
                                "5 (Modern)","6 (Modern)","7 (Modern)","8 (Modern)","9 (Modern)"};
    private ButtonGroup modesetButtonGroup;

    public SettingsGui() {
        setupFrame();
        createPanels();
        setVisible(true);
    }

    private void setupFrame() {
        setTitle("Settings");
        setSize(400, 300);
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        mainContent = new JPanel(cardLayout);
        add(mainContent, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void createPanels() {
        // Ana modeset paneli
        modesetPanel = new JPanel();
        modesetPanel.setLayout(new BoxLayout(modesetPanel, BoxLayout.Y_AXIS));
        modesetPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        modesetButtonGroup = new ButtonGroup();

        for(String mode : modesets) {
            JCheckBox check = new JCheckBox(mode);
            check.setAlignmentX(Component.LEFT_ALIGNMENT);
            modesetButtonGroup.add(check);
            modesetPanel.add(check);
            modesetPanel.add(Box.createVerticalStrut(3));
        }

        // Custom config paneli
        configPanel = new JPanel(new BorderLayout());
        configPanel.add(new JTextField(), BorderLayout.CENTER);
        
        // Geçiş butonu
        btnSwitch = new JButton("Custom Config");
        btnSwitch.setPreferredSize(new Dimension(150, 30));
        btnSwitch.addActionListener(this);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Sol üstte hizalama
        buttonPanel.add(btnSwitch);
        
        // Ana düzen
        mainContent.add(createMainPanel(modesetPanel), "normal");
        mainContent.add(configPanel, "config");
        add(buttonPanel, BorderLayout.NORTH);
    }

    private JPanel createMainPanel(JComponent content) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(content, BorderLayout.CENTER);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(btnSwitch.getText().equals("Custom Config")) {
            cardLayout.show(mainContent, "config");
            btnSwitch.setText("Modesets");
        } else {
            cardLayout.show(mainContent, "normal");
            btnSwitch.setText("Custom Config");
        }
    }
}
