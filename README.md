# Student Management System

**Author:** anu31d

This repository is a browser-based Student Management System website that is easy to share, fork, and publish. It runs without private config files and includes a working student record demo in the browser.

## Live site

- https://anu31d.github.io/Student-Database-Management-System-using-MySQL-and-JDBC/

## Project description

Designed and implemented a Java-based backend system to manage academic records using relational schema design and JDBC. Built role-based access control, executed secure CRUD operations, and implemented modular server-side workflows following OOP and system design fundamentals. The published site is a portable browser dashboard that demonstrates the core UI and CRUD flows using browser `localStorage` for demo persistence.

## Tech stack

- Java (optional local launcher and original backend implementation)
- JDBC / SQL (relational schema design — original backend)
- HTML & CSS (site layout and styling)
- JavaScript (client-side CRUD and UI behavior)
- Browser localStorage (demo persistence for the live site)
- GitHub Pages (hosting)

## What’s included

- Student, Teacher, and Management sections
- A clean dashboard layout for presentation
- A working student record demo with add, edit, delete, and search
- Static HTML, CSS, and JavaScript for easy sharing
- An optional Java launcher that opens the page locally

## Run locally

1. Clone or download the repository.
2. Open `index.html` in any browser.
3. Optional: run `project/src/App.java` if you want the page to open automatically from Java.

The demo stores records in browser local storage, so changes stay in that browser until you reset the sample data.

## Download & setup

To get a copy and run the site locally:

1. Clone the repository:

	```powershell
	git clone https://github.com/anu31d/Student-Database-Management-System-using-MySQL-and-JDBC.git
	Set-Location "Student-Database-Management-System-using-MySQL-and-JDBC"
	```

2. Open the site in your browser by double-clicking `index.html`, or serve it over a local HTTP server (recommended):

	- Using Python 3:

	```powershell
	python -m http.server 8000
	# then open http://localhost:8000 in your browser
	```

	- Using Node (http-server):

	```powershell
	npx http-server . -p 8080
	```

3. Optional Java launcher (opens the site in your default browser):

	```powershell
	Set-Location project\src
	javac App.java
	java App
	```

 

## Project files

- `index.html` — main website page
- `styles.css` — visual styling
- `script.js` — browser-based CRUD demo
- `project/src/App.java` — optional local launcher

## Share links

- Repository link: source code on GitHub: https://github.com/anu31d/Student-Database-Management-System-using-MySQL-and-JDBC.git
 





