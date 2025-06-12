# 📚 BookStore API Test Automation (RestAssured + TestNG + ExtentReports)

This project automates API testing for a FastAPI-based BookStore service using:
- ✅ Java
- ✅ RestAssured
- ✅ TestNG
- ✅ Maven
- ✅ GitHub Actions CI/CD
- ✅ ExtentReports
- ✅ Configurable environments (Dev, QA, Prod)

---

## 📦 Project Structure

```
bookstore/
├── src/
│   ├── test/java/
│   │   ├── api/test/       # Test classes
│   │   ├── api/endpoints/  # Endpoint layer
│   │   ├── api/payload/    # POJO models
│   │   ├── api/base/       # BaseTest & config loader
│   │   └── api/utilities/  # ExtentReports, helpers
├── config/
│   ├── qa.properties
│   ├── dev.properties
│   └── prod.properties
├── testng.xml              # TestNG suite
├── pom.xml                 # Maven config
└── reports/                # Extent HTML reports
```

---

## 🚀 How to Run Tests

### ✅ Prerequisites
- Java 17+
- Maven
- Internet (for GitHub or dependency downloads)

### 🔧 Run Tests (Default `qa`)

```bash
mvn clean test
```

### 🌍 Run with Specific Environment

```bash
mvn clean test -Denv=dev
```

### 🔍 Run Specific Test Groups

```bash
mvn clean test -Dgroups=smoke -Denv=qa
```

---

## 🧪 Test Coverage

### ✅ User Auth

| Endpoint     | Status |
|--------------|--------|
| `POST /signup` | ✅ Covered |
| `POST /signin` | ✅ Covered |

### ✅ Book Management

| Endpoint         | Status |
|------------------|--------|
| `POST /books/`   | ✅ Create |
| `GET /books/`    | ✅ All |
| `GET /books/{id}`| ✅ Single |
| `DELETE /books/{id}` | ✅ Delete |
| `GET /health`    | ✅ Health Check |

---

## 🛠️ CI/CD - GitHub Actions

This project is configured to auto-run tests on:
- Push to `main`
- Manual trigger

📄 Workflow file: `.github/workflows/api-tests.yml`

🔗 View Actions: [GitHub Actions tab](https://github.com/mohdaamir11/bookstore/actions)

---

## 📊 Reports

Generated after every test run:
- Format: **Extent HTML**
- Location: 'reports/Test-Report-*.html`

In CI/CD: Artifacts are uploaded and downloadable via Actions tab.

---

## ⚙️ Config Management

Use '/config' folder to manage environment-level properties:
- 'base_url=http://127.0.0.1:8000'
- `env_name=QA`

Set environment with:

```bash
mvn test -Denv=qa
```

---

## 👤 Author

- **Name:** Mohd Aamir
- **LinkedIn:** [linkedin.com/in/mohdaamir11](https://linkedin.com/in/mohdaamir11)
- **GitHub:** [github.com/mohdaamir11](https://github.com/mohdaamir11)

---

## 💡 Tips

- Use 'git commit --allow-empty -m "Trigger CI"' to manually re-run GitHub Actions.
- Clean up local `.git` if accidentally pushing to wrong repo.

---

## 📌 License

MIT License ( NA )