import java.io.*;
import java.util.Scanner;

public class ConfigGenerator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== Database Configuration Generator ===");
        System.out.println("This is a one-time setup. Keep db.config file private.");
        System.out.println();
        
        System.out.print("Enter Database URL (e.g., jdbc:mysql://localhost:3306/STUDENT_MANAGEMENT_SYSTEM): ");
        String url = sc.nextLine();
        
        System.out.print("Enter Database Username: ");
        String user = sc.nextLine();
        
        System.out.print("Enter Database Password: ");
        String pass = sc.nextLine();
        
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("db.config"));
            
            writer.println("# Database Configuration - DO NOT SHARE");
            writer.println("# Generated for: " + System.getProperty("user.name"));
            writer.println();
            writer.println("db.url=" + Config.encode(url));
            writer.println("db.user=" + Config.encode(user));
            writer.println("db.pass=" + Config.encode(pass));
            writer.println("project.owner=" + Config.encode("Anuska Dasgupta - 2026"));
            
            writer.close();
            
            System.out.println();
            System.out.println("✓ Configuration file created successfully!");
            System.out.println("✓ File: db.config");
            System.out.println();
            System.out.println("IMPORTANT:");
            System.out.println("1. Keep this file private and secure");
            System.out.println("2. Add 'db.config' to .gitignore");
            System.out.println("3. Never share this file publicly");
            
        } catch (Exception e) {
            System.err.println("Error creating configuration file!");
            e.printStackTrace();
        }
        
        sc.close();
    }
}
