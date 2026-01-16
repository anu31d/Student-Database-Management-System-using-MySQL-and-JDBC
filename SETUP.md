# Setup Instructions

## ⚠️ Important Notice

This project is protected and requires individual configuration for each installation. Simply cloning or forking this repository will **not** provide a working application.

---

## For the Project Owner

If you are the project owner (Anuska Dasgupta), your `db.config` file is already set up. Simply run:

```bash
cd project/src
java -cp ".;..\lib\mysql-connector-j-9.5.0.jar" App
```

Your encrypted credentials are already configured and the project will work normally.

---

## For Others (Who Fork This Repository)

If you've forked or cloned this repository, you'll need to set up your own database configuration. Follow these steps:

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

To request access to this project or for support:
- Contact: **Anuska Dasgupta**
- For: Setup assistance or questions

## Troubleshooting

**Error: "Configuration file not found"**
- You need to create your own db.config file
- Run `ConfigGenerator` as shown above
- Make sure you have your own MySQL database set up first

**Error: "Configuration initialization failed"**
- Verify db.config exists and is properly formatted
- Re-run ConfigGenerator if needed

**Error: "Invalid project configuration"**
- This indicates a configuration integrity check failed
- Make sure you're using the ConfigGenerator provided with the project
- Re-run the setup process

---

**Important**: This project is protected. Unauthorized redistribution or use without proper setup is not permitted.
