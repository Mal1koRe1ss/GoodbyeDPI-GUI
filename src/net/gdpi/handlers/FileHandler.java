package net.gdpi.handlers;

import java.io.IOException;

import net.gdpi.gui.MainGui;

public class FileHandler {

    private MainGui mainGui;

    public FileHandler(MainGui mainGui) {
        this.mainGui = mainGui;
    }
    
    public void runFile(String filename) {
        try {
            ProcessBuilder pb = new ProcessBuilder(filename);
            pb.start();
        } catch (IOException e) {
            mainGui.log("Error while running file: " + e.getMessage());
        }
    }
}
