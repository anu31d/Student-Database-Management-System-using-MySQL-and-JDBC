# Setup Instructions

## ⚠️ Important Notice

This project is protected and requires individual configuration for each installation. Simply cloning or forking this repository will **not** provide a working application.

---

## Initial Setup (For Authorized Users Only)

This project requires proper configuration before use. Follow these steps:

### 1. Generate Configuration File

First, compile and run the configuration generator:

```bash
cd project/src
javac -cp "../lib/*" Config.java ConfigGenerator.java
java -cp "../lib/*;." ConfigGenerator
```

This will prompt you for:
- Database URL
- Database Username  
- Database Password

The tool will create an encrypted `db.config` file.

### 2. Verify Configuration

The `db.config` file should be created in the project root. This file contains encrypted credentials and should **NEVER** be shared or committed to git.

### 3. Run the Application

```bash
javac -cp "../lib/*" *.java
java -cp "../lib/*;." App
```

## Security Notes

- The `db.config` file is automatically excluded from git via `.gitignore`
- Database credentials are encrypted using AES encryption
- The application validates configuration integrity on startup
- Each installation requires individual configuration setup

## For Authorized Users

Contact **Anuska Dasgupta** for:
- Project access authorization
- Setup assistance
- Database schema information

## Troubleshooting

**Error: "Configuration file not found"**
- Run `ConfigGenerator` to create the db.config file

**Error: "Configuration initialization failed"**
- Verify db.config exists and is properly formatted
- Re-run ConfigGenerator if needed

**Error: "Invalid project configuration"**
- Contact project owner for authorized access

---

**Important**: This project is protected. Unauthorized redistribution or use without proper setup is not permitted.
