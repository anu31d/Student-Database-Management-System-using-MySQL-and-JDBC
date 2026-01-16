import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.*;

public class QuickConfigSetup {
    private static final byte[] KEY = {0x41, 0x6E, 0x75, 0x73, 0x6B, 0x61, 0x44, 0x47, 
                                       0x50, 0x72, 0x6F, 0x6A, 0x32, 0x30, 0x32, 0x36};
    
    public static void main(String[] args) {
        try {
            // Your actual database credentials
            String url = "jdbc:mysql://localhost:3306/STUDENT_MANAGEMENT_SYSTEM";
            String user = "root";
            String pass = "Ikigai@2603!";
            
            String encodedUrl = encode(url);
            String encodedUser = encode(user);
            String encodedPass = encode(pass);
            String encodedOwner = encode("Anuska Dasgupta - 2026");
            
            System.out.println("# Database Configuration - DO NOT SHARE");
            System.out.println("# Generated for: " + System.getProperty("user.name"));
            System.out.println();
            System.out.println("db.url=" + encodedUrl);
            System.out.println("db.user=" + encodedUser);
            System.out.println("db.pass=" + encodedPass);
            System.out.println("project.owner=" + encodedOwner);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String encode(String plainText) {
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
}
