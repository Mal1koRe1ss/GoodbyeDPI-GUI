package net.gdpi.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main GUI class for the application.
 */
public class MainGui extends JFrame implements ActionListener {

    // Menu components
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem settings = new JMenuItem("Settings");

    // Buttons
    JButton startButton = new JButton("Open DNSRedir");
    JButton installButton = new JButton("Install service");
    JButton removeButton = new JButton("Remove service");

    // Panels
    JPanel buttonPanel = new JPanel();
    JPanel statusPanel = new JPanel();
    JPanel wrapperPanel_1 = new JPanel();
    JPanel toolsPanel = new JPanel();

    // Text area for logging
    JTextArea logArea;

    // Labels
    JLabel toolsLabel = new JLabel("Tools");
    JLabel logLabel = new JLabel("Log Area");

    /**
     * Constructor for the MainGui class.
     */
    public MainGui() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Look and feel ayarlanırken hata oluştu: " + e.getMessage());
        }

        // Add settings to the menu and set up the menu bar
        menu.add(settings);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
        settings.addActionListener(this);

        // Set up buttons
        setupButtons();

        // Configure the tools panel
        toolsPanel.setLayout(new BorderLayout());
        toolsPanel.add(toolsLabel, BorderLayout.NORTH);
        toolsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        toolsLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        logLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Configure the button panel
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Align buttons to the center
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        installButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons to the button panel
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(installButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(removeButton);

        // Add the button panel to the tools panel
        toolsPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the tools panel to the west of the frame
        this.add(toolsPanel, BorderLayout.WEST);

        // Status panel settings
        statusPanel.setLayout(new BorderLayout());
        statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a text area for logging
        logArea = new JTextArea(20, 50);
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Create a scroll pane for the text area
        JScrollPane logScrollPane = new JScrollPane(logArea);

        // Add log label and scroll pane to the status panel
        statusPanel.add(logLabel, BorderLayout.NORTH);
        statusPanel.add(logScrollPane, BorderLayout.CENTER);

        // Set the size of the status panel
        statusPanel.setPreferredSize(new Dimension(0, 300));
        statusPanel.setMinimumSize(new Dimension(0, 300));
        statusPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));

        // Create a wrapper panel to hold the status panel
        wrapperPanel_1.setLayout(new BorderLayout());
        wrapperPanel_1.add(statusPanel, BorderLayout.NORTH);

        // Add the wrapper panel to the center of the frame
        this.add(wrapperPanel_1, BorderLayout.CENTER);

        // Window settings
        this.setTitle("Goodbye DPI GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 650);
        Dimension minSize = new Dimension(650, 650);
        this.setMinimumSize(minSize);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle menu item actions
        if (e.getSource() == settings) {
            new SettingsGui();
        } else if (e.getSource() == startButton) {
            // TODO Redir openner
        } else if (e.getSource() == installButton) {
            // TODO Service Installer (net.gdpi.service.install.java)
        } else if (e.getSource() == removeButton) {
            // TODO Service Remover (net.gdpi.service.install.java)
        }
    }

    /**
     * Set up buttons by calling setButtonSize and setButtons methods.
     */
    public void setupButtons() {
        setButtonSize();
        setButtons();
    }

    /**
     * Set the size of buttons.
     */
    public void setButtonSize() {
        int width = 180;
        int height = 40;

        Dimension size = new Dimension(width, height);

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

    /**
     * Set the text position of buttons to center.
     */
    public void setButtons() {
        startButton.setHorizontalTextPosition(SwingConstants.CENTER);
        installButton.setHorizontalTextPosition(SwingConstants.CENTER);
        removeButton.setHorizontalTextPosition(SwingConstants.CENTER);
    }

    /**
     * Log a message to the text area.
     *
     * @param message The message to log.
     */
    public void log(String message) {
        logArea.append(message + "\n");
    }
}