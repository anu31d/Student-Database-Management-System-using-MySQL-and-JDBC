import java.io.*;
import java.util.*;

public class TestConfig {
    public static void main(String[] args) {
        try {
            File configFile = new File("../db.config");
            System.out.println("Config file exists: " + configFile.exists());
            System.out.println("Config file path: " + configFile.getAbsolutePath());
            
            if (configFile.exists()) {
                Properties props = new Properties();
                props.load(new FileInputStream(configFile));
                
                System.out.println("\nProperties loaded:");
                System.out.println("db.url exists: " + props.containsKey("db.url"));
                System.out.println("db.user exists: " + props.containsKey("db.user"));
                System.out.println("db.pass exists: " + props.containsKey("db.pass"));
                System.out.println("project.owner exists: " + props.containsKey("project.owner"));
                
                System.out.println("\nAll keys: " + props.keySet());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
