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
## 🔒 Security Features

- **Encrypted Configuration**: Database credentials stored in encrypted format using AES encryption
- **Environment Validation**: Project ownership verification system
- **Secure Setup**: One-time configuration required per installation
- **Protected Access**: Requires manual configuration - cannot be used by simply forking
- **Git Protection**: Sensitive config files automatically excluded from version control

### For the Project Owner (You)

Your `db.config` file is already set up with encrypted credentials. The project will run normally:

```bash
cd project/src
java -cp ".;..\lib\mysql-connector-j-9.5.0.jar" App
```

### For Others Who Fork This Repository

Simply forking will **NOT** provide a working copy. The `db.config` file is excluded from git for security.

**They must:**
1. Have their own MySQL database set up
2. Run `ConfigGenerator` to create their own `db.config`
3. Provide their own database credentials

**See [SETUP.md](SETUP.md) for setup instructions.**

---
## � Screenshots

Check the `screenshots/` folder for application demos and output examples.

---

## 📝 License

This project is created by Anuska Dasgupta and is available for educational purposes.

---

## 🙏 Acknowledgments

- Java Documentation
- MySQL Documentation
- JDBC API Reference




