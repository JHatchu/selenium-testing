

---

# 🧩 Selenium Testing Project


A **Java + Maven** automation project with:

* 🧠 **OrangeHRM** (UI Automation using Selenium)
* 🔗 **ReqRes API** (API Testing using RestAssured)
* ⚖️ **BMI Calculator** (Cucumber BDD Tests)

---

## 📂 Project Structure

```
selenium-testing-project/
│
├── orangehrm/        # Selenium UI Tests
├── reqres-api/       # REST API Tests (Mocked)
├── bmi-cucumber/     # Cucumber BDD Tests
└── pom.xml           # Parent Maven file
```

---



---

## ▶️ Run Tests

### 🧠 OrangeHRM

```bash
mvn clean test -pl orangehrm
```

### 🔗 ReqRes API

```bash
mvn clean test -pl reqres-api
```

### ⚖️ BMI Calculator

```bash
mvn clean test -pl bmi-cucumber
```

---

## 🧱 Key Features

* ✅ Modular Maven structure
* 🌐 Selenium Headless Web Tests
* 🔒 Mocked ReqRes API (offline execution)
* 🧩 Cucumber BDD for BMI Calculator
* 📊 TestNG reporting

---



