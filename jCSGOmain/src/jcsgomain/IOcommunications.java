package jcsgomain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;

/**
 *
 * @author martin
 */
public class IOcommunications {

    private String defaultFolder="/home/steam/LinuxGSM-master";
    private String file;
    private String line;
    private String change;

    public IOcommunications() {
    }

    public IOcommunications(String file, String line, String change) {
        this.file = file;
        this.line = line;
        this.change = change;
    }

    public String getFile() {
        return file;
    }

    public String getLine() {
        return line;
    }

    public String getChange() {
        return change;
    }

    public String getDefaultFolder() {
        return defaultFolder;
    }

    public void setDefaultFolder(String defaultFolder) {
        this.defaultFolder = defaultFolder;
    }

//    public void replaceSelected(String file, String lines, String change) {
    public void replaceSelected(String filePath, String oldString, String newString) {
        File fileToBeModified = new File(filePath);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            //Reading all the lines of input text file into oldContent
            String lines = reader.readLine();
            while (lines != null) {
                String[] str = lines.split("=");
                if (str[0].equals(oldString)) {
                    lines = str[0] + "=\"" + newString + "\"";
                }
                oldContent = oldContent + lines + System.lineSeparator();
                lines = reader.readLine();
            }

            //Rewriting the input text file with newContent
            writer = new FileWriter(fileToBeModified);
            writer.write(oldContent);
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        IOcommunications io = new IOcommunications();
        io.setDefaultFolder("/home/steam/LinuxGSM-master");
        System.out.println(io.getDefaultFolder() + File.separator
                + FileSystems.getDefault().getPath("serverfiles", "csgo", "cfg", "atest.cfg"));
        System.out.println(System.getProperty("user.dir"));
        io.replaceSelected(io.getDefaultFolder() + File.separator
                + FileSystems.getDefault().getPath("serverfiles", "csgo", "cfg", "atest.cfg"),
                "gametype","192.168.1.114");
    }
}
