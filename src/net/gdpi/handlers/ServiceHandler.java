package net.gdpi.handlers;

import net.gdpi.gui.MainGui;

public class ServiceHandler {

    private MainGui mainGui;
    private String currentDir = System.getProperty("user.dir");
    private String cmdFilePath = currentDir + "\\dpi\\";
    private String installFile = MainGui.customRedir ? "service_install_custom.cmd" : "service_install.cmd";
    private String removeFile = "service_remove.cmd";
    private String fullInstallPath = cmdFilePath + installFile;
    private String fullRemovePath = cmdFilePath + removeFile;

    private FileHandler fileHandler = new FileHandler(mainGui);

    public ServiceHandler(MainGui mainGui) {
        this.mainGui = mainGui;
    }

    public void installService() {
        fileHandler.runFileAsAdmin(fullInstallPath);
    }

    public void removeService() {
        fileHandler.runFileAsAdmin(fullRemovePath);
    }

}
