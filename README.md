# 💰 Expense Tracker

A full-stack personal finance management application that helps users track **income, expenses, savings, and budgets** with interactive analytics and filtering.

🔗 **Live Demo:** https://pragya2110.github.io/Expense-Tracker/

🔗 **GitHub Repository:** https://github.com/pragya2110/Expense-Tracker

---

## 📌 Project Overview

**Expense Tracker** is a full-stack web application developed to simplify personal financial management.

The application allows users to:

* Add, edit, and delete expenses
* Track total income
* Calculate savings automatically
* Set and monitor category-wise budgets
* Compare spending against budgets
* Search and filter expenses
* Analyze spending by category
* Analyze monthly spending
* Visualize financial data using interactive charts

The project uses a **Java Spring Boot backend**, **MySQL database**, and a responsive **HTML, CSS, and JavaScript frontend**.

---

## ✨ Features

### 💸 Expense Management

* Add new expenses
* Edit existing expenses
* Delete expenses
* View recent expenses
* Store expense title, amount, category, and date

### 💰 Income & Savings

* Track total income
* Calculate total expenses
* Automatically calculate savings

**Savings = Total Income − Total Expenses**

### 📊 Budget Management

* Category-wise budget tracking
* Displays amount spent against budget
* Shows remaining budget
* Displays percentage of budget used
* Provides budget status:
  * Within Budget
  * Approaching Budget Limit
  * Budget Exceeded
* Animated progress indicators

### 🔎 Search & Filtering

Users can filter expenses using:

* Expense title
* Category
* Start date
* End date

A **Clear Filters** option is also provided.

### 📈 Expense Analytics

The application provides:

* Total expense summary
* Highest spending category
* Category-wise spending chart
* Monthly spending chart

Charts are implemented using **Chart.js**.

### 📱 Responsive Interface

The application is designed to work across:

* Desktop
* Tablet
* Mobile devices

---

## 🛠️ Tech Stack

### Frontend
* HTML5
* CSS3
* JavaScript
* Chart.js

### Backend
* Java
* Spring Boot
* Spring Web
* Spring JDBC
* REST APIs
* Maven

### Database
* MySQL
* MySQL Workbench

### Development Tools
* IntelliJ IDEA
* Visual Studio Code
* Git
* GitHub

## 🌍 Deployment

This project is fully deployed and live, using a modern three-service cloud architecture.

### Frontend — GitHub Pages
* **Live URL:** https://pragya2110.github.io/Expense-Tracker/
* Hosted directly from the `expense-tracker-frontend/` folder
* Automatically redeployed via a **GitHub Actions** workflow (`.github/workflows/deploy-frontend.yml`) on every push to `main`
* No build step required — static HTML/CSS/JS served directly

### Backend — Railway
* **Live API Base URL:** https://expense-tracker-production-2e17.up.railway.app
* Spring Boot REST API deployed directly from the `expense-tracker-backend/` folder (configured as the Root Directory in Railway)
* Automatically redeployed on every push to `main`
* Environment variables (database host, port, credentials) are injected securely via Railway's variable references — no secrets are stored in the codebase

### Database — Railway MySQL
* Managed MySQL instance hosted on Railway
* Connected to the backend service via Railway's internal private networking
* Schema defined in `database/schema.sql`

### Deployment Flow

```text
git push origin main
        │
        ├──→ GitHub Actions builds & deploys frontend → GitHub Pages
        │
        └──→ Railway detects push → rebuilds & redeploys backend
                        │
                        ▼
                  Connects to Railway MySQL
```

Every code change pushed to `main` automatically updates the live site — no manual deployment steps required.

---

## 🏗️ Project Architecture

```text
                    Expense Tracker
                          │
              ┌───────────┴───────────┐
              │                       │
          Frontend                 Backend
              │                       │
      HTML / CSS / JS           Spring Boot
              │                       │
              │                  Controller
              │                       │
              │                    Service
              │                       │
              │                  Repository
              │                       │
              │                  JdbcTemplate
              │                       │
              └──── REST API ─────────┤
                                      │
                                    MySQL
```

The application follows a layered backend architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
MySQL Database
```

This separation makes the application easier to maintain and extend.

---

## 📂 Project Structure

```text
Expense-Tracker/
│
├── database/
│   └── schema.sql
│
├── expense-tracker-backend/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/
│   │       │       └── project/
│   │       │           └── expense_tracker_backend/
│   │       │               ├── controller/
│   │       │               ├── model/
│   │       │               ├── repository/
│   │       │               └── service/
│   │       │
│   │       └── resources/
│   │
│   └── pom.xml
│
├── expense-tracker-frontend/
│   ├── index.html
│   ├── style.css
│   └── script.js
│
├── .github/
│   └── workflows/
│
├── .gitignore
│
└── README.md
```

---

## 🔌 REST API Endpoints

### Expenses

| Method   | Endpoint              | Description        |
| -------- | ---------------------- | ------------------ |
| `GET`    | `/api/expenses`        | Get all expenses   |
| `POST`   | `/api/expenses`        | Add a new expense  |
| `PUT`    | `/api/expenses/{id}`   | Update an expense  |
| `DELETE` | `/api/expenses/{id}`   | Delete an expense  |
| `GET`    | `/api/expenses/total`  | Get total expenses |

### Expense Analytics

| Method | Endpoint                           | Description                 |
| ------ | ----------------------------------- | ---------------------------- |
| `GET`  | `/api/expenses/analytics/category`  | Get category-wise spending  |
| `GET`  | `/api/expenses/analytics/monthly`   | Get monthly spending        |

### Income

| Method | Endpoint            | Description             |
| ------ | -------------------- | ------------------------ |
| `GET`  | `/api/income`        | Get all income records  |
| `GET`  | `/api/income/total`  | Get total income        |

### Budgets

| Method | Endpoint               | Description                 |
| ------ | ----------------------- | ---------------------------- |
| `GET`  | `/api/budgets`          | Get all budgets             |
| `GET`  | `/api/budgets/status`   | Get budget spending status  |

---

## 🗄️ Database

The application uses MySQL for persistent data storage.

### Main Tables

```text
expenses
├── id
├── title
├── amount
├── category
└── date

income
├── id
├── source
├── amount
└── date

budgets
├── id
├── category
└── amount
```

The SQL database setup is available in:

```text
database/schema.sql
```

---

## 📊 Analytics

The application uses SQL aggregation to generate financial insights.

### Category-wise Spending

Expenses are grouped by category using SQL aggregation:

```sql
SELECT category, SUM(amount)
FROM expenses
GROUP BY category;
```

### Monthly Spending

Monthly spending is calculated by grouping expenses according to their month.

These results are then sent through REST APIs and displayed using interactive charts.

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/pragya2110/Expense-Tracker.git
cd Expense-Tracker
```

### 2. Set Up MySQL

Create the database using:

```text
database/schema.sql
```

Make sure MySQL is running before starting the backend.

### 3. Configure the Backend

Open the Spring Boot backend project and configure the database connection in:

```text
expense-tracker-backend/src/main/resources/application.properties
```

Use your own local database credentials (e.g., via your IDE's run configuration environment variables).

**Do not commit database passwords or other secrets to GitHub.**

### 4. Run the Backend

From the backend directory:

```bash
cd expense-tracker-backend
mvn spring-boot:run
```

The backend will run on:

```text
http://localhost:8080
```

### 5. Run the Frontend

Open the `expense-tracker-frontend` folder and run `index.html` using a local development server such as VS Code Live Server.

---

## 🌐 Live Demo

The application is deployed and available online:

👉 **https://pragya2110.github.io/Expense-Tracker/**

---

## 📸 Screenshots

### Dashboard

<img width="1882" height="237" alt="image" src="https://github.com/user-attachments/assets/05de91c7-ae5c-42db-a231-074eb1f45873" />


### Expense Management

<img width="1896" height="865" alt="image" src="https://github.com/user-attachments/assets/9b4b015f-18f8-423f-8e4c-07d29cc91757" />


### Budget Overview

<img width="1887" height="407" alt="image" src="https://github.com/user-attachments/assets/596f464a-75b5-4290-84c2-57fb42f05b06" />


### Expense Analytics

<img width="1897" height="870" alt="image" src="https://github.com/user-attachments/assets/16509af0-7675-4f7e-b942-4887339e06ec" />


---

## 🎯 Learning Outcomes

Through this project, I worked with:

* Java and Spring Boot
* REST API development
* Spring JDBC / JdbcTemplate
* MySQL database integration
* SQL queries and aggregation
* CRUD operations
* Frontend-backend integration
* JavaScript Fetch API
* Data visualization using Chart.js
* Git and GitHub
* Application deployment
* Responsive web design

---

## 🔮 Future Improvements

Possible future enhancements include:

* User authentication and authorization
* Multiple user accounts
* Export expenses to CSV/PDF
* Dark mode
* Recurring expenses
* Advanced financial reports
* Improved cloud infrastructure
* Notifications for budget limits

---

## 👩‍💻 Author

**Pragya Bharti**

B.Tech Computer Science & Engineering

GitHub: https://github.com/pragya2110

---

## ⭐ Project Links

🌐 **Live Website:** https://pragya2110.github.io/Expense-Tracker/

💻 **GitHub Repository:** https://github.com/pragya2110/Expense-Tracker

---

## 📄 License

This project is developed for educational and portfolio purposes.
