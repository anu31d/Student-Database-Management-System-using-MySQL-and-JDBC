import java.sql.*;
import java.util.Scanner;

public class App
{
    static Connection con;
    static Scanner sc = new Scanner(System.in);
    
    // ANSI Color Codes
    static final String RESET = "\u001B[0m";
    static final String BLUE = "\u001B[34m";
    static final String GREEN = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String RED = "\u001B[31m";
    static final String CYAN = "\u001B[36m";
    static final String BOLD = "\u001B[1m";
    
    // Utility methods for formatted output
    static void printHeader(String title) {
        System.out.println("\n" + CYAN + "═══════════════════════════════════════════════════════" + RESET);
        System.out.println(BOLD + CYAN + "   " + title + RESET);
        System.out.println(CYAN + "═══════════════════════════════════════════════════════" + RESET);
    }
    
    static void printSubHeader(String title) {
        System.out.println("\n" + BLUE + "╔═══════════════════════════════════════════════════════╗" + RESET);
        System.out.println(BLUE + "║" + RESET + BOLD + "  " + title + RESET);
        System.out.println(BLUE + "╚═══════════════════════════════════════════════════════╝" + RESET);
    }
    
    static void printSuccess(String message) {
        System.out.println(GREEN + "✓ " + message + RESET);
    }
    
    static void printError(String message) {
        System.out.println(RED + "✗ " + message + RESET);
    }
    
    static void printInfo(String message) {
        System.out.println(YELLOW + "ℹ " + message + RESET);
    }
    
    static void printLine() {
        System.out.println(BLUE + "───────────────────────────────────────────────────────" + RESET);
    }
    
    static void printTableRow(String... columns) {
        System.out.print(BLUE + "│" + RESET);
        for (String col : columns) {
            System.out.printf(" %-18s " + BLUE + "│" + RESET, col);
        }
        System.out.println();
    }
    
    static void printTableHeader(String... headers) {
        System.out.println(BLUE + "┌────────────────────┬────────────────────┬────────────────────┬────────────────────┐" + RESET);
        printTableRow(headers);
        System.out.println(BLUE + "├────────────────────┼────────────────────┼────────────────────┼────────────────────┤" + RESET);
    }
    
    static void printTableFooter() {
        System.out.println(BLUE + "└────────────────────┴────────────────────┴────────────────────┴────────────────────┘" + RESET);
    }
    
    static void printTableHeader3(String... headers) {
        System.out.println(BLUE + "┌────────────────────┬────────────────────┬────────────────────┐" + RESET);
        System.out.print(BLUE + "│" + RESET);
        for (String header : headers) {
            System.out.printf(" %-18s " + BLUE + "│" + RESET, header);
        }
        System.out.println();
        System.out.println(BLUE + "├────────────────────┼────────────────────┼────────────────────┤" + RESET);
    }
    
    static void printTableFooter3() {
        System.out.println(BLUE + "└────────────────────┴────────────────────┴────────────────────┘" + RESET);
    }
    
    static void printTableRow3(String... columns) {
        System.out.print(BLUE + "│" + RESET);
        for (String col : columns) {
            System.out.printf(" %-18s " + BLUE + "│" + RESET, col);
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/STUDENT_MANAGEMENT_SYSTEM";
        String user = "root";
        String password = "Ikigai@2603!";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            printSuccess("Database Connected Successfully!");

            while (true)
            {
                printHeader("STUDENT MANAGEMENT SYSTEM");
                System.out.println("\n  " + BOLD + "Choose Your Role:" + RESET);
                System.out.println("  " + YELLOW + "[1]" + RESET + " Student");
                System.out.println("  " + YELLOW + "[2]" + RESET + " Teacher");
                System.out.println("  " + YELLOW + "[3]" + RESET + " Management");
                System.out.println("  " + YELLOW + "[4]" + RESET + " Exit");
                System.out.print("\n  " + CYAN + "➤" + RESET + " Enter your choice: ");

                int role = sc.nextInt();

                switch (role)
                {
                    case 1:
                    {
                        studentMenu();
                        break;
                    }

                    case 2:
                    {
                        teacherMenu();
                        break;
                    }

                    case 3:
                    {
                        managementMenu();
                        break;
                    }

                    case 4:
                    {
                        con.close();
                        printLine();
                        printSuccess("System Exited Successfully. Goodbye!");
                        printLine();
                        return;
                    }

                    default:
                    {
                        printError("Invalid Choice! Please select 1-4.");
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // STUDENT

    static void studentMenu()
    {
        printSubHeader("STUDENT PORTAL");
        System.out.print("  " + CYAN + "➤" + RESET + " Enter Roll Number: ");
        int roll = sc.nextInt();

        try
        {
            String query =
                    "SELECT * FROM STUDENTS WHERE ROLL_NO = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, roll);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println();
                printLine();
                System.out.println(BOLD + "  Student Information" + RESET);
                printLine();
                System.out.println("  Roll No    : " + YELLOW + rs.getInt("ROLL_NO") + RESET);
                System.out.println("  Name       : " + YELLOW + rs.getString("NAME") + RESET);
                System.out.println("  Department : " + YELLOW + rs.getString("DEPARTMENT") + RESET);
                System.out.println("  Section    : " + YELLOW + rs.getString("SECTION") + RESET);
                printLine();
            }
            else
            {
                printError("Student not found with Roll No: " + roll);
            }
        }
        catch (Exception e)
        {
            printError("Error retrieving student information!");
            e.printStackTrace();
        }
    }

    //TEACHER

    static void teacherMenu()
    {
        while (true)
        {
            printSubHeader("TEACHER PORTAL - Student Management");
            System.out.println("  " + YELLOW + "[1]" + RESET + " Insert Student");
            System.out.println("  " + YELLOW + "[2]" + RESET + " Display Students");
            System.out.println("  " + YELLOW + "[3]" + RESET + " Update Student Section");
            System.out.println("  " + YELLOW + "[4]" + RESET + " Delete Student");
            System.out.println("  " + YELLOW + "[5]" + RESET + " Back to Main Menu");
            System.out.print("\n  " + CYAN + "➤" + RESET + " Enter your choice: ");

            int ch = sc.nextInt();

            switch (ch)
            {
                case 1:
                {
                    insertStudent();
                    break;
                }

                case 2:
                {
                    displayStudents();
                    break;
                }

                case 3:
                {
                    updateStudent();
                    break;
                }

                case 4:
                {
                    deleteStudent();
                    break;
                }

                case 5:
                {
                    return;
                }
            }
        }
    }

    //MANAGEMENT

    static void managementMenu()
    {
        while (true)
        {
            printSubHeader("MANAGEMENT PORTAL");
            System.out.println("  " + YELLOW + "[1]" + RESET + " Student Operations");
            System.out.println("  " + YELLOW + "[2]" + RESET + " Course Operations");
            System.out.println("  " + YELLOW + "[3]" + RESET + " Teacher Operations");
            System.out.println("  " + YELLOW + "[4]" + RESET + " Back to Main Menu");
            System.out.print("\n  " + CYAN + "➤" + RESET + " Enter your choice: ");

            int ch = sc.nextInt();

            switch (ch)
            {
                case 1:
                {
                    teacherMenu();
                    break;
                }

                case 2:
                {
                    courseMenu();
                    break;
                }

                case 3:
                {
                    teacherTableMenu();
                    break;
                }

                case 4:
                {
                    return;
                }
            }
        }
    }

    //STUDENT CRUD

    static void insertStudent()
    {
        try
        {
            printInfo("Enter Student Details:");
            System.out.print("  Roll Number: ");
            int roll = sc.nextInt();

            System.out.print("  Name: ");
            String name = sc.next();

            System.out.print("  Department: ");
            String dept = sc.next();

            System.out.print("  Section: ");
            String sec = sc.next();

            String query =
                    "INSERT INTO STUDENTS VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, roll);
            ps.setString(2, name);
            ps.setString(3, dept);
            ps.setString(4, sec);

            ps.executeUpdate();
            printSuccess("Student Inserted Successfully!");
        }
        catch (Exception e)
        {
            printError("Error inserting student!");
            e.printStackTrace();
        }
    }

    static void displayStudents()
    {
        try
        {
            String query = "SELECT * FROM STUDENTS";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println();
            printTableHeader("ROLL NO", "NAME", "DEPARTMENT", "SECTION");

            int count = 0;
            while (rs.next())
            {
                printTableRow(
                        String.valueOf(rs.getInt("ROLL_NO")),
                        rs.getString("NAME"),
                        rs.getString("DEPARTMENT"),
                        rs.getString("SECTION")
                );
                count++;
            }
            printTableFooter();
            printInfo("Total Students: " + count);
        }
        catch (Exception e)
        {
            printError("Error displaying students!");
            e.printStackTrace();
        }
    }

    static void updateStudent()
    {
        try
        {
            printInfo("Update Student Section:");
            System.out.print("  Enter Roll No: ");
            int roll = sc.nextInt();

            System.out.print("  New Section: ");
            String sec = sc.next();

            String query =
                    "UPDATE STUDENTS SET SECTION = ? WHERE ROLL_NO = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, sec);
            ps.setInt(2, roll);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                printSuccess("Student Section Updated Successfully!");
            } else {
                printError("Student not found with Roll No: " + roll);
            }
        }
        catch (Exception e)
        {
            printError("Error updating student!");
            e.printStackTrace();
        }
    }

    static void deleteStudent()
    {
        try
        {
            printInfo("Delete Student:");
            System.out.print("  Enter Roll No: ");
            int roll = sc.nextInt();

            String query =
                    "DELETE FROM STUDENTS WHERE ROLL_NO = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, roll);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                printSuccess("Student Deleted Successfully!");
            } else {
                printError("Student not found with Roll No: " + roll);
            }
        }
        catch (Exception e)
        {
            printError("Error deleting student!");
            e.printStackTrace();
        }
    }

    //COURSE CRUD

    static void courseMenu()
    {
        while (true)
        {
            printSubHeader("COURSE MANAGEMENT");
            System.out.println("  " + YELLOW + "[1]" + RESET + " Insert Course");
            System.out.println("  " + YELLOW + "[2]" + RESET + " Display Courses");
            System.out.println("  " + YELLOW + "[3]" + RESET + " Delete Course");
            System.out.println("  " + YELLOW + "[4]" + RESET + " Back");
            System.out.print("\n  " + CYAN + "➤" + RESET + " Enter your choice: ");

            int ch = sc.nextInt();

            switch (ch)
            {
                case 1:
                {
                    insertCourse();
                    break;
                }

                case 2:
                {
                    displayCourses();
                    break;
                }

                case 3:
                {
                    deleteCourse();
                    break;
                }

                case 4:
                {
                    return;
                }
            }
        }
    }

    static void insertCourse()
    {
        try
        {
            printInfo("Enter Course Details:");
            System.out.print("  Department: ");
            String dept = sc.next();

            System.out.print("  Course Name: ");
            String cname = sc.next();

            System.out.print("  Course ID: ");
            String cid = sc.next();

            String query =
                    "INSERT INTO COURSE VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, dept);
            ps.setString(2, cname);
            ps.setString(3, cid);

            ps.executeUpdate();
            printSuccess("Course Inserted Successfully!");
        }
        catch (Exception e)
        {
            printError("Error inserting course!");
            e.printStackTrace();
        }
    }
    static void displayCourses()
    {
        try
        {
            String query = "SELECT * FROM COURSE";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            System.out.println();
            printTableHeader3("DEPARTMENT", "COURSE NAME", "COURSE ID");
            
            int count = 0;
            while (rs.next())
            {
                printTableRow3(
                        rs.getString("DEPARTMENT"),
                        rs.getString("COURSE_NAME"),
                        rs.getString("COURSE_ID")
                );
                count++;
            }
            printTableFooter3();
            printInfo("Total Courses: " + count);
        }
        catch (Exception e)
        {
            printError("Error displaying courses!");
            e.printStackTrace();
        }
    }

    static void deleteCourse()
    {
        try
        {
            printInfo("Delete Course:");
            System.out.print("  Course ID: ");
            String cid = sc.next();

            String query =
                    "DELETE FROM COURSE WHERE COURSE_ID = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, cid);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                printSuccess("Course Deleted Successfully!");
            } else {
                printError("Course not found with ID: " + cid);
            }
        }
        catch (Exception e)
        {
            printError("Error deleting course!");
            e.printStackTrace();
        }
    }

    //TEACHER CRUD 

    static void teacherTableMenu()
    {
        while (true)
        {
            printSubHeader("TEACHER MANAGEMENT");
            System.out.println("  " + YELLOW + "[1]" + RESET + " Insert Teacher");
            System.out.println("  " + YELLOW + "[2]" + RESET + " Display Teachers");
            System.out.println("  " + YELLOW + "[3]" + RESET + " Delete Teacher");
            System.out.println("  " + YELLOW + "[4]" + RESET + " Back");
            System.out.print("\n  " + CYAN + "➤" + RESET + " Enter your choice: ");

            int ch = sc.nextInt();

            switch (ch)
            {
                case 1:
                {
                    insertTeacher();
                    break;
                }

                case 2:
                {
                    displayTeachers();
                    break;
                }

                case 3:
                {
                    deleteTeacher();
                    break;
                }

                case 4:
                {
                    return;
                }
            }
        }
    }

    static void insertTeacher()
    {
        try
        {
            printInfo("Enter Teacher Details:");
            System.out.print("  Teacher ID: ");
            int tid = sc.nextInt();

            System.out.print("  Teacher Name: ");
            String tname = sc.next();

            System.out.print("  Course ID: ");
            String cid = sc.next();

            String query =
                    "INSERT INTO TEACHER VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, cid);
            ps.setString(2, tname);
            ps.setInt(3, tid);

            ps.executeUpdate();
            printSuccess("Teacher Inserted Successfully!");
        }
        catch (Exception e)
        {
            printError("Error inserting teacher!");
            e.printStackTrace();
        }
    }

    static void displayTeachers()
    {
        try
        {
            String query = "SELECT * FROM TEACHER";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println();
            printTableHeader3("TEACHER ID", "NAME", "COURSE ID");

            int count = 0;
            while (rs.next())
            {
                printTableRow3(
                        String.valueOf(rs.getInt("TEACHER_ID")),
                        rs.getString("TEACHER_NAME"),
                        rs.getString("COURSE_ID")
                );
                count++;
            }
            printTableFooter3();
            printInfo("Total Teachers: " + count);
        }
        catch (Exception e)
        {
            printError("Error displaying teachers!");
            e.printStackTrace();
        }
    }

    static void deleteTeacher()
    {
        try
        {
            printInfo("Delete Teacher:");
            System.out.print("  Teacher ID: ");
            int tid = sc.nextInt();

            String query = "DELETE FROM TEACHER WHERE TEACHER_ID = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, tid);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                printSuccess("Teacher Deleted Successfully!");
            } else {
                printError("Teacher not found with ID: " + tid);
            }
        }
        catch (Exception e)
        {
            printError("Error deleting teacher!");
            e.printStackTrace();
        }
    }
}