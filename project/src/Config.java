import java.io.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;

public class Config {
    private static final String CONFIG_FILE = "../db.config";
    private static final byte[] KEY = {0x41, 0x6E, 0x75, 0x73, 0x6B, 0x61, 0x44, 0x47, 
                                       0x50, 0x72, 0x6F, 0x6A, 0x32, 0x30, 0x32, 0x36};
    
    private static Properties props = null;
    private static String envValidation = null;
    
    private Config() {}
    
    public static boolean initialize() {
        try {
            envValidation = System.getenv("USER") + System.getProperty("user.name");
            if (envValidation == null || envValidation.length() < 3) {
                return false;
            }
            
            File configFile = new File(CONFIG_FILE);
            if (!configFile.exists()) {
                System.err.println("\n" + "=".repeat(60));
                System.err.println("⚠  CONFIGURATION FILE NOT FOUND");
                System.err.println("=".repeat(60));
                System.err.println("\nThe 'db.config' file is required to run this application.");
                System.err.println("This file is NOT included in the repository for security.");
                System.err.println("\nTO SET UP:");
                System.err.println("  1. Navigate to: project/src");
                System.err.println("  2. Compile: javac -cp \"../lib/*\" Config.java ConfigGenerator.java");
                System.err.println("  3. Run: java -cp \"../lib/*;.\" ConfigGenerator");
                System.err.println("  4. Follow prompts to enter your MySQL database credentials");
                System.err.println("\nNote: You need your own MySQL database setup first.");
                System.err.println("See SETUP.md for detailed instructions.");
                System.err.println("=".repeat(60) + "\n");
                return false;
            }
            
            props = new Properties();
            props.load(new FileInputStream(configFile));
            
            if (!props.containsKey("db.url") || !props.containsKey("db.user") || !props.containsKey("db.pass")) {
                System.err.println("\n⚠  Invalid configuration file format.");
                System.err.println("The db.config file appears to be corrupted or incomplete.");
                System.err.println("Please re-run ConfigGenerator to create a new configuration.\n");
                return false;
            }
            
            return true;
        } catch (Exception e) {
            System.err.println("Configuration initialization failed.");
            return false;
        }
    }
    
    public static String getDbUrl() {
        return props != null ? decode(props.getProperty("db.url")) : null;
    }
    
    public static String getDbUser() {
        return props != null ? decode(props.getProperty("db.user")) : null;
    }
    
    public static String getDbPassword() {
        return props != null ? decode(props.getProperty("db.pass")) : null;
    }
    
    private static String decode(String encoded) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encoded);
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted);
        } catch (Exception e) {
            return encoded;
        }
    }
    
    public static String encode(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean validate() {
        if (props == null) return false;
        
        String checksum = props.getProperty("project.owner");
        if (checksum == null) return false;
        
        try {
            String decoded = decode(checksum);
            return decoded.contains("Anuska") && decoded.contains("Dasgupta");
        } catch (Exception e) {
            return false;
        }
    }
}
