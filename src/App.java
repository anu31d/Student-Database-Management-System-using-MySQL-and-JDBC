import java.sql.*;
import java.util.Scanner;

public class App
{
    static Connection con;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        String url = "jdbc:mysql://localhost:3306/STUDENT_MANAGEMENT_SYSTEM";
        String user = "root";
        String password = "Ikigai@2603!";

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection Successful");

            while (true)
            {
                System.out.println("\nCHOOSE YOUR ROLE");
                System.out.println("1. Student");
                System.out.println("2. Teacher");
                System.out.println("3. Management");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");

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
                        System.out.println("Exited Successfully");
                        return;
                    }

                    default:
                    {
                        System.out.println("Invalid Choice");
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
        System.out.print("Enter Roll Number: ");
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
                System.out.println("\nROLL NO : " + rs.getInt("ROLL_NO"));
                System.out.println("NAME    : " + rs.getString("NAME"));
                System.out.println("DEPT    : " + rs.getString("DEPARTMENT"));
                System.out.println("SECTION : " + rs.getString("SECTION"));
            }
            else
            {
                System.out.println("Student not found");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //TEACHER

    static void teacherMenu()
    {
        while (true)
        {
            System.out.println("\n--- TEACHER MENU ---");
            System.out.println("1. Insert Student");
            System.out.println("2. Display Students");
            System.out.println("3. Update Student Section");
            System.out.println("4. Delete Student");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");

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
            System.out.println("\n--- MANAGEMENT MENU ---");
            System.out.println("1. Student Operations");
            System.out.println("2. Course Operations");
            System.out.println("3. Teacher Operations");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

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
            System.out.print("Roll: ");
            int roll = sc.nextInt();

            System.out.print("Name: ");
            String name = sc.next();

            System.out.print("Department: ");
            String dept = sc.next();

            System.out.print("Section: ");
            String sec = sc.next();

            String query =
                    "INSERT INTO STUDENTS VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, roll);
            ps.setString(2, name);
            ps.setString(3, dept);
            ps.setString(4, sec);

            ps.executeUpdate();
            System.out.println("Student Inserted");
        }
        catch (Exception e)
        {
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

            System.out.println("\nROLL  NAME  DEPT  SECTION");

            while (rs.next())
            {
                System.out.println(
                        rs.getInt("ROLL_NO") + "   " +
                        rs.getString("NAME") + "   " +
                        rs.getString("DEPARTMENT") + "   " +
                        rs.getString("SECTION")
                );
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static void updateStudent()
    {
        try
        {
            System.out.print("Enter Roll No: ");
            int roll = sc.nextInt();

            System.out.print("New Section: ");
            String sec = sc.next();

            String query =
                    "UPDATE STUDENTS SET SECTION = ? WHERE ROLL_NO = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, sec);
            ps.setInt(2, roll);

            ps.executeUpdate();
            System.out.println("Student Updated");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static void deleteStudent()
    {
        try
        {
            System.out.print("Enter Roll No: ");
            int roll = sc.nextInt();

            String query =
                    "DELETE FROM STUDENTS WHERE ROLL_NO = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, roll);

            ps.executeUpdate();
            System.out.println("Student Deleted");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //COURSE CRUD

    static void courseMenu()
    {
        while (true)
        {
            System.out.println("\n--- COURSE MENU ---");
            System.out.println("1. Insert Course");
            System.out.println("2. Display Courses");
            System.out.println("3. Delete Course");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

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
            System.out.print("Department: ");
            String dept = sc.next();

            System.out.print("Course Name: ");
            String cname = sc.next();

            System.out.print("Course ID: ");
            String cid = sc.next();

            String query =
                    "INSERT INTO COURSE VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, dept);
            ps.setString(2, cname);
            ps.setString(3, cid);

            ps.executeUpdate();
            System.out.println("Course Inserted");
        }
        catch (Exception e)
        {
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
            System.out.println("\nDEPARTMENT  COURSE NAME  COURSE ID");
            while (rs.next())
            {
                System.out.println(
                        rs.getString(("DEPARTMENT") + "   " )+
                        rs.getString(("COURSE_NAME") + "   ") +
                        rs.getString("COURSE_ID")
                );
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static void deleteCourse()
    {
        try
        {
            System.out.print("Course ID: ");
            String cid = sc.next();

            String query =
                    "DELETE FROM COURSE WHERE COURSE_ID = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, cid);

            ps.executeUpdate();
            System.out.println("Course Deleted");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //TEACHER CRUD 

    static void teacherTableMenu()
    {
        while (true)
        {
            System.out.println("\n--- TEACHER MENU ---");
            System.out.println("1. Insert Teacher");
            System.out.println("2. Display Teachers");
            System.out.println("3. Delete Teacher");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

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
            System.out.print("Teacher ID: ");
            int tid = sc.nextInt();

            System.out.print("Teacher Name: ");
            String tname = sc.next();

            System.out.print("Course ID: ");
            String cid = sc.next();

            String query =
                    "INSERT INTO TEACHER VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, cid);
            ps.setString(2, tname);
            ps.setInt(3, tid);

            ps.executeUpdate();
            System.out.println("Teacher Inserted");
        }
        catch (Exception e)
        {
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

            System.out.println("\nTEACHER ID  NAME  COURSE ID");

            while (rs.next())
            {
                System.out.println(
                        rs.getInt("TEACHER_ID") + "   " +
                        rs.getString("TEACHER_NAME") + "   " +
                        rs.getString("COURSE_ID")
                );
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static void deleteTeacher()
    {
        try
        {
            System.out.print("Teacher ID: ");
            int tid = sc.nextInt();

            String query = "DELETE FROM TEACHER WHERE TEACHER_ID = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, tid);

            ps.executeUpdate();
            System.out.println("Teacher Deleted");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}