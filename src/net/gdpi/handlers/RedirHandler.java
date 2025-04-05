package net.gdpi.handlers;

import net.gdpi.gui.MainGui;

public class RedirHandler {

    private MainGui mainGui;
    private String currentDir = System.getProperty("user.dir");
    private String cmdFilePath = currentDir + "\\dpi\\";
    private String cmdFileToRun = MainGui.customRedir ? "custom_dnsredir.cmd" : "any_country_dnsredir.cmd";
    private String fullCmdFilePath = cmdFilePath + cmdFileToRun;

    private FileHandler fileHandler = new FileHandler(mainGui);

    public RedirHandler(MainGui mainGui) {
        this.mainGui = mainGui;
    }

    public void runRedir() {
        fileHandler.runFile(fullCmdFilePath);
    }

}
