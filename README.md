# 💸 Smart Budget Guardian

**Smart Budget Guardian** is a production-ready, containerized Scala 3 application designed to help users track personal expenses, set monthly budgets, and get alerts when they are nearing or exceeding their budget. Built with modern tools and architectural patterns to showcase backend engineering, concurrent programming, and big data analytics.

---

## 🚀 Features

- 🔹 RESTful APIs built using **Akka HTTP**
- 🔹 Persistent storage with **MySQL** (via Slick)
- 🔹 **Akka Actors** for concurrency and background budget alerts
- 🔹 **Spark Batch Job** to analyze expenses and detect anomalies
- 🔹 **Dockerized** with full `docker-compose` support
- 🔹 Modular & clean codebase using **Scala 3**
- 🔹 Unit + Integration testing for API and services

---

## 🏗️ Project Structure

```text
smart-budget-guardian/
├── build.sbt
├── project/
├── docker-compose.yml
├── src/
│   └── main/
│       ├── scala/
│       │   └── com.smartbudgetguardian/
│       │       ├── api/              # Akka HTTP routes
│       │       ├── db/               # Slick DB access layer
│       │       ├── domain/           # Case classes and data models
│       │       ├── service/          # Business logic
│       │       ├── actor/            # Akka actors (concurrent processing)
│       │       └── Main.scala        # App bootstrap
│       └── resources/
│           └── application.conf      # Configs
└── spark-job/
    ├── build.sbt
    └── src/
        └── main/
            └── scala/
                └── BudgetAnalyzer.scala  # Spark job to analyze budget vs spend
```

---

## ⚙️ Technologies Used

| Tech           | Purpose                              |
|----------------|--------------------------------------|
| Scala 3        | Main backend language                |
| Akka HTTP      | REST API server                      |
| Akka Actors    | Concurrent & background processing   |
| Apache Spark   | Budget anomaly detection (batch job) |
| MySQL          | Persistent DB                        |
| Slick          | DB access layer                      |
| Docker         | Containerization                     |
| sbt            | Build tool                           |

---

## 🐳 Run with Docker

Make sure Docker and Docker Compose are installed and WSL2 is set up correctly (on Windows).

### 📦 Build & Start

```bash
docker-compose up --build
```

This will:
- Start MySQL DB container
- Start the Scala Akka HTTP API server
- Initialize the DB schema automatically

---

## 🔁 API Endpoints

| Method | Endpoint             | Description                        |
|--------|----------------------|------------------------------------|
| GET    | `/expenses`          | Fetch all expenses                 |
| POST   | `/expenses`          | Add a new expense                  |
| GET    | `/budget`            | Get budget for current month       |
| POST   | `/budget`            | Set or update monthly budget       |
| GET    | `/alerts`            | Get current alerts                 |

Use Postman or `curl` to test them.

---

## 🧪 Run Tests

From the root directory:

```bash
sbt test
```

---

## 🔎 Run Spark Batch Job

The job reads expenses and budgets from DB and prints warnings for over-budget categories.

```bash
sbt "project spark-job" run
```

Or use Spark Submit:

```bash
spark-submit --class BudgetAnalyzer ./target/scala-3.X/spark-job-assembly.jar
```

---

## 🗄️ Database Schema

The following tables will be created:

1. `expenses`  
2. `budgets`  
3. `alerts`

> Tables are auto-created via Slick schema definitions when the app starts.

---

## 🧰 Environment Variables

Use `.env` or set them manually for development:

```env
MYSQL_ROOT_PASSWORD=root
MYSQL_DATABASE=budget_db
MYSQL_USER=budget_user
MYSQL_PASSWORD=budget_pass
```
