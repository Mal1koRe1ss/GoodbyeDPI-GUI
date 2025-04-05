package net.gdpi.gui;

import javax.swing.*;

import net.gdpi.handlers.RedirHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

/**
 * Main GUI class for the application.
 */
public class MainGui extends JFrame implements ActionListener {
    // Constants
    private static final String GITHUB_URL = "https://github.com/Mal1koRe1ss/GoodbyeDPI-GUI";
    public static boolean customRedir = false;
    public static boolean customService = false;

    // Menu components
    private JMenuBar menuBar = new JMenuBar();
    private JMenu file = new JMenu("File");
    private JMenuItem settings = new JMenuItem("Settings");
    private JMenuItem exit = new JMenuItem("Exit");
    private JMenu help = new JMenu("Help");
    private JMenuItem github = new JMenuItem("Github");

    // Buttons
    private JButton startButton = new JButton("Open DNSRedir");
    private JButton installButton = new JButton("Install service");
    private JButton removeButton = new JButton("Remove service");

    // Panels
    private JPanel buttonPanel = new JPanel();
    private JPanel statusPanel = new JPanel();
    private JPanel wrapperPanel = new JPanel();
    private JPanel toolsPanel = new JPanel();

    // Text area for logging
    private JTextArea logArea;

    // Labels
    private JLabel toolsLabel = new JLabel("Tools");
    private JLabel logLabel = new JLabel("Log Area");
    private JLabel copyRightLabel = new JLabel("Copyright 2025 © Goodbye DPI GUI, Pounter & Mal1kore1ss");

    public MainGui() {
        initializeLookAndFeel();
        setupMenu();
        setupMainWindow();
    }

    private void initializeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Look and feel error: " + e.getMessage());
        }
    }

    private void setupMenu() {
        file.add(settings);
        file.add(exit);
        help.add(github);

        menuBar.add(file);
        menuBar.add(help);
        this.setJMenuBar(menuBar);

        menuListeners();
    }

    private void setupMainWindow() {
        ImageIcon icon = new ImageIcon("assets/gdpigui.png");
        setIconImage(icon.getImage());
        setupToolPanel();
        setupStatusPanel();
        setupCopyrightLabel();
        configureWindow();
        setLocationRelativeTo(null);
    }

    private void setupToolPanel() {
        toolsPanel.setLayout(new BorderLayout());
        toolsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        toolsLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        setupButtons();
        toolsPanel.add(toolsLabel, BorderLayout.NORTH);
        toolsPanel.add(buttonPanel, BorderLayout.CENTER);

        this.add(toolsPanel, BorderLayout.WEST);
    }

    private void setupButtons() {
        setButtonSize();
        setButtons();

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        installButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(startButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(installButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(removeButton);
    }

    private void setupStatusPanel() {
        statusPanel.setLayout(new BorderLayout());
        statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        logLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        logArea = new JTextArea(20, 50);
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane logScrollPane = new JScrollPane(logArea);
        statusPanel.add(logLabel, BorderLayout.NORTH);
        statusPanel.add(logScrollPane, BorderLayout.CENTER);

        statusPanel.setPreferredSize(new Dimension(0, 300));
        statusPanel.setMinimumSize(new Dimension(0, 300));
        statusPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));

        wrapperPanel.setLayout(new BorderLayout());
        wrapperPanel.add(statusPanel, BorderLayout.NORTH);
        this.add(wrapperPanel, BorderLayout.CENTER);
    }

    private void setupCopyrightLabel() {
        copyRightLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        copyRightLabel.setFont(new Font("Sans Serif", Font.PLAIN, 11));
        this.add(copyRightLabel, BorderLayout.SOUTH);
    }

    private void configureWindow() {
        this.setTitle("Goodbye DPI GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 360);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void menuListeners() {
        settings.addActionListener(e -> new SettingsGui(this).setVisible(true));

        exit.addActionListener(e -> System.exit(0));

        github.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI(GITHUB_URL));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "GitHub sayfası açılamadı: " + ex.getMessage(),
                        "Hata", JOptionPane.ERROR_MESSAGE);
                log("GitHub bağlantı hatası: " + ex.getMessage());
            }
        });

        startButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            RedirHandler handler = new RedirHandler(this); 
            handler.runRedir();
        } else if (e.getSource() == installButton) {
            // TODO Service Installer
        } else if (e.getSource() == removeButton) {
            // TODO Service Remover
        }
    }

    private void setButtonSize() {
        Dimension size = new Dimension(180, 40);

        startButton.setPreferredSize(size);
        startButton.setMinimumSize(size);
        startButton.setMaximumSize(size);

        installButton.setPreferredSize(size);
        installButton.setMinimumSize(size);
        installButton.setMaximumSize(size);

        removeButton.setPreferredSize(size);
        removeButton.setMinimumSize(size);
        removeButton.setMaximumSize(size);
    }

    private void setButtons() {
        startButton.setHorizontalTextPosition(SwingConstants.CENTER);
        installButton.setHorizontalTextPosition(SwingConstants.CENTER);
        removeButton.setHorizontalTextPosition(SwingConstants.CENTER);

        startButton.setFocusPainted(false);
        installButton.setFocusPainted(false);
        removeButton.setFocusPainted(false);
    }

    public void log(String message) {
        logArea.append(message + "\n");
    }
}