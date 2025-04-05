package net.gdpi.handlers;

import net.gdpi.gui.MainGui;

public class ServiceHandler {

    private MainGui mainGui;
    private String currentDir = System.getProperty("user.dir");
    private String cmdFilePath = currentDir + "\\dpi\\";
    private String installFile = MainGui.customRedir ? "custom_service_install.cmd" : "service_install.cmd"; // Servis için ayrı dosya
    private String removeFile = "service_remove.cmd";
    private String fullInstallPath = cmdFilePath + installFile;
    private String fullRemovePath = cmdFilePath + removeFile;

    private FileHandler fileHandler = new FileHandler(mainGui);

    public ServiceHandler(MainGui mainGui) {
        this.mainGui = mainGui;
        this.fileHandler = new FileHandler(mainGui);
    }

    public void installService() {
        fileHandler.runFileAsAdmin(fullInstallPath);
        mainGui.log("GoodbyeDPI service installed!");
    }

    public void removeService() {
        fileHandler.runFileAsAdmin(fullRemovePath);
        mainGui.log("GoodbyeDPI service removed!");
    }
}