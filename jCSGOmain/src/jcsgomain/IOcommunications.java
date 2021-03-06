package jcsgomain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martin
 */
public class IOcommunications {

    private ArrayList<String> settings = new ArrayList<>();
    private String defaultFolder;// = "/home/steam/LinuxGSM-master";
    private String config;
    private String config2;
    private String file;
    private String line;
    private String change;
    ;

    public IOcommunications() {
        File f = new File(".");

        String add;
        try {
            settings = readFile(f.getCanonicalPath(), "/settings.csv");
        } catch (IOException ex) {
            Logger.getLogger(IOcommunications.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] s;
        for (String setting : settings) {
            s = setting.split(",");
            switch (s[0]) {
                case "Default server folder":
                    setDefaultFolder(s[1]);
                    break;

                case "Server config gamemodes":
                    setConfig(s[1]);
                    break;

                case "Server config info":
                    setConfig2(s[1]);
                    break;

                default:
                    System.out.println("No settings found!");
                    break;

            }
        }
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

    public void setConfig(String config) {
        this.config = config;
    }

    public void setConfig2(String config2) {
        this.config2 = config2;
    }

    public void setDefaultFolder(String defaultFolder) {
        this.defaultFolder = defaultFolder;
    }

    public String getConfig() {
        return config;
    }

    public String getConfig2() {
        return config2;
    }

//    public void replaceSelected(String filePath; String file, String lines, String change) {
    public void replaceSelected(String filePath, String file, String oldString, String newString) {
        String oldContent = "";
        BufferedReader reader = null;
        String lines = "";

        try {
            File fileToBeModified = new File(filePath + "/" + file);
            reader = new BufferedReader(new FileReader(fileToBeModified));
            //Reading all the lines of input text file into oldContent

        } catch (IOException ex) {
            System.out.println(ex);
            File fileToBeModified = new File(filePath + "/src/" + file);
            try {
                reader = new BufferedReader(new FileReader(fileToBeModified));
            } catch (FileNotFoundException ex1) {
                System.out.println("1. " + ex);
            }
        }
        try {
            lines = reader.readLine();
        } catch (IOException ex) {
            System.out.println("2. " + ex);
        }
        while (lines != null) {
            String[] str = lines.split("=");
            if (str[0].equals(oldString)) {
                lines = str[0] + "=\"" + newString + "\"";
            }
            oldContent = oldContent + lines + System.lineSeparator();
            try {
                lines = reader.readLine();
            } catch (IOException ex) {
                System.out.println("3. " + ex);
            }
        }

        writeFile(filePath,file, oldContent);
        try {
            //Closing the resources
            reader.close();
        } catch (IOException e) {
            System.out.println("4. " + e);
        }
    }

    public ArrayList<String> readFile(String filePath, String file) {
        ArrayList<String> fileContent = new ArrayList<>();
        File fileToBeModified = new File(filePath + "/" + file);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String lines = reader.readLine();
            while (lines != null) {
                fileContent.add(lines);
                lines = reader.readLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("5. " + ex);
        } catch (IOException ex) {
            System.out.println("6. " + ex);
        }
        return fileContent;
    }

    public void writeFile(String filePath, String file, String content) {
        File fileToBeModified = new File(filePath + "/" + file);
        try {
            FileWriter writer = new FileWriter(fileToBeModified);
            writer.write(content);
            writer.close();
        } catch (IOException ex) {
            System.out.println("7. " + ex);
        }

    }

    public ArrayList<String> fileInfoArray(String filePath, String file) {
        String s;
        File f;
        BufferedReader br = null;
        ArrayList<String> maps = new ArrayList<>();
        try {
            f = new File(filePath + "/" + file);
            br = new BufferedReader(new FileReader(f));

            while ((s = br.readLine()) != null) {
                maps.add(s);
            }
        } catch (Exception e) {
            System.out.println("8. " + e);
        }
        return maps;
    }

    public static void main(String[] args) {
        IOcommunications io = new IOcommunications();
//        io.setDefaultFolder("/home/steam/LinuxGSM-master");
//        System.out.println(io.getDefaultFolder() + File.separator
//                + FileSystems.getDefault().getPath("serverfiles", "csgo", "cfg", "atest.cfg"));
        System.out.println("1" + System.getProperty("user.dir"));
        System.out.println("2" + io.getDefaultFolder());
        System.out.println("3");
        for (String string : io.readFile(io.getDefaultFolder(), "settings.csv")) {
            System.out.println(string);
        }
//        io.replaceSelected(io.getDefaultFolder() + File.separator
//                + FileSystems.getDefault().getPath("serverfiles", "csgo", "cfg", "atest.cfg"),
//                "gametype", "192.168.1.13332");
    }
}
