import com.formdev.flatlaf.FlatDarkLaf;
import net.gdpi.gui.MainGui;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // FlatLaf & GUI
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new MainGui().setVisible(true);
        });
    }

}
