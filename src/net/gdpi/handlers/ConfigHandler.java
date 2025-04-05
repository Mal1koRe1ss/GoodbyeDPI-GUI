package net.gdpi.handlers;

import java.io.FileWriter;
import java.io.IOException;

import net.gdpi.gui.MainGui;

public class ConfigHandler {

    private MainGui mainGui;

    public ConfigHandler(MainGui mainGui) {
        this.mainGui = mainGui;
    }

    public void saveConfig(boolean customRedir, boolean customService,
            String dnsRedirParam, String serviceParam) {
        writeRedirConfig(customRedir, dnsRedirParam);
        writeServiceInstallConfig(customService, serviceParam);
    }

    private void writeRedirConfig(boolean customRedir, String dnsRedirParam) {
        try {
            String filePath = System.getProperty("user.dir") + "\\dpi\\custom_dnsredir.cmd";
            FileWriter writer = new FileWriter(filePath);

            writer.write("@ECHO OFF\r\n");
            writer.write("PUSHD \"%~dp0\"\r\n");
            writer.write("set _arch=x86\r\n");
            writer.write("IF \"%PROCESSOR_ARCHITECTURE%\"==\"AMD64\" (set _arch=x86_64)\r\n");
            writer.write("IF DEFINED PROCESSOR_ARCHITEW6432 (set _arch=x86_64)\r\n");
            writer.write("PUSHD \"%_arch%\"\r\n");
            writer.write("start \"\" goodbyedpi.exe " +
                    (dnsRedirParam.equals("None") ? "" : dnsRedirParam) + "\r\n"); // None ise parametre ekleme
            writer.write("POPD\r\n");
            writer.write("POPD\r\n");
            writer.close();
        } catch (IOException e) {
            mainGui.log("Error writing DNSRedir file: " + e.getMessage());
        }
    }

    private void writeServiceInstallConfig(boolean customService, String serviceParam) {
        try {
            String filePath = System.getProperty("user.dir") + "\\dpi\\custom_service_install.cmd";
            FileWriter writer = new FileWriter(filePath);

            writer.write("@ECHO OFF\r\n");
            writer.write("PUSHD \"%~dp0\"\r\n");
            writer.write("set _arch=x86\r\n");
            writer.write("IF \"%PROCESSOR_ARCHITECTURE%\"==\"AMD64\" (set _arch=x86_64)\r\n");
            writer.write("IF DEFINED PROCESSOR_ARCHITEW6432 (set _arch=x86_64)\r\n");
            writer.write("\r\n");
            writer.write("sc stop \"GoodbyeDPI\"\r\n");
            writer.write("sc delete \"GoodbyeDPI\"\r\n");
            writer.write("sc create \"GoodbyeDPI\" binPath= \"\\\"%CD%\\%_arch%\\goodbyedpi.exe\\\" " +
                    (serviceParam.equals("None") ? "" : serviceParam) + "\"\r\n"); // None ise parametre ekleme
            writer.write("sc description \"GoodbyeDPI\" \"Started via GoodbyeDPI-Gui (Custom)\"\r\n");
            writer.write("sc start \"GoodbyeDPI\"\r\n");
            writer.write("POPD\r\n");
            writer.close();
        } catch (IOException e) {
            mainGui.log("Error writing service install file: " + e.getMessage());
        }
    }
}