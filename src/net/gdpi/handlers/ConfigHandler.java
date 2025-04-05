package net.gdpi.handlers;

import java.io.FileWriter;
import java.io.IOException;

import net.gdpi.gui.MainGui;

public class ConfigHandler {

    public void saveConfig(boolean customRedir, String customParam) {
            writeConfig(customRedir, customParam);
        }

    private MainGui mainGui;

    private void writeConfig(boolean customRedir, String customParam) {
        try {
            String filePath = System.getProperty("user.dir") + "\\dpi\\custom_dnsredir.cmd";

            FileWriter writer = new FileWriter(filePath);
            writer.write("@ECHO OFF\n");
            writer.write("PUSHD \"%~dp0\"\n");
            writer.write("set _arch=x86\n");
            writer.write("IF \"%PROCESSOR_ARCHITECTURE%\"==\"AMD64\" (set _arch=x86_64)\n");
            writer.write("IF DEFINED PROCESSOR_ARCHITEW6432 (set _arch=x86_64)\n");
            writer.write("PUSHD \"%_arch%\"\n");
            writer.write("echo deneme\n");
            writer.write("start \"\" goodbyedpi.exe " + customParam + "\n");
            writer.write("POPD\n");
            writer.write("POPD\n");
            writer.close();
        } catch (IOException e) {
            mainGui.log("Error while writing the file : " + e.getMessage());
        }
    }

}
