# 🎓 Student Management System (JDBC + MySQL)

A console-based **Student Management System** built using **Java** and **JDBC** that enables Students, Teachers, and Management to interact with a MySQL database through role-based menus and perform CRUD operations efficiently.

---

## 📌 Project Overview

This project demonstrates practical implementation of:
- **Database Connectivity**: Java–MySQL integration using JDBC
- **Role-Based Access Control**: Separate interfaces for different user types
- **CRUD Operations**: Complete Create, Read, Update, Delete functionality
- **Prepared Statements**: Secure database operations preventing SQL injection
- **ResultSet Handling**: Efficient data retrieval and display

---

## 🛠️ Tech Stack

| Category | Technology |
|----------|-----------|
| **Language** | Java |
| **Database** | MySQL 5.7+ |
| **Database Driver** | JDBC (MySQL Connector/J 8.0+) |
| **JDK Version** | Java 8 or above |
| **Build Tool** | Maven / Manual Compilation |
| **IDE** | VS Code / IntelliJ IDEA / Eclipse |

---

## ✨ Features

### 👨‍🎓 Student Module
- 🔍 View personal details using Roll Number
- 📊 Access academic information
- 🎯 View enrolled courses

### 👩‍🏫 Teacher Module
- ➕ Insert new student records
- 📋 Display all students in the database
- ✏️ Update student section/details
- 🗑️ Delete student records
- 📚 View course information

### 🏫 Management Module
- 👥 Complete student management (CRUD)
- 📖 Course management (CRUD operations)
- 👨‍🏫 Teacher management (CRUD operations)
- 📈 View comprehensive reports

---

## 🗃️ Database Schema

The system uses the following tables:

### `STUDENTS` Table
- Student ID (Primary Key)
- Roll Number
- Name
- Section
- Course ID (Foreign Key)

### `COURSE` Table
- Course ID (Primary Key)
- Course Name
- Duration
- Credits

### `TEACHER` Table
- Teacher ID (Primary Key)
- Name
- Department
- Experience
- Course ID (Foreign Key)

---

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- MySQL Server 5.7 or higher
- MySQL Connector/J (JDBC Driver)
- Git (for cloning the repository)

### Installation Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/jdbc-sql-sdms.git
   cd jdbc-sql-sdms
   ```

2. **Set Up MySQL Database**
   - Create a database named `student_db`
   - Run the SQL scripts to create tables
   - Configure database credentials in the application

3. **Configure JDBC Connection**
   - Update database URL, username, and password in the source code
   - Ensure MySQL Connector/J is in the classpath

4. **Compile and Run**
   ```bash
   cd project/src
   javac -cp "../lib/*" App.java
   java -cp "../lib/*;." App
   ```

---

## 💡 Usage

1. Run the application
2. Select your role (Student/Teacher/Management)
3. Follow the menu-driven interface
4. Perform desired operations
5. Exit when done

---

## 📸 Screenshots

Check the `screenshots/` folder for application demos and output examples.

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📝 License

This project is open source and available for educational purposes.

---

## 📧 Contact

For questions or suggestions, please open an issue in the repository.

---

## 🙏 Acknowledgments

- Java Documentation
- MySQL Documentation
- JDBC API Reference




