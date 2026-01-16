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
                System.err.println("Configuration file not found. Please create 'db.config' file.");
                System.err.println("Contact the project owner for setup instructions.");
                return false;
            }
            
            props = new Properties();
            props.load(new FileInputStream(configFile));
            
            if (!props.containsKey("db.url") || !props.containsKey("db.user") || !props.containsKey("db.pass")) {
                System.err.println("Invalid configuration file format.");
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
