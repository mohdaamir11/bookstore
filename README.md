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


---

## 🧪 Testing Strategy

### ✅ Approach to Writing Test Flows

- **Layered Structure**: The test suite follows a clean separation of concerns:
  - `payload`: POJOs for request bodies
  - `endpoints`: Contains reusable RestAssured logic for API operations
  - `tests`: Contains actual TestNG test cases organized by modules (user, book, etc.)
- **Scenario-Based Testing**:
  - **Positive cases** (valid user signup, login, book creation)
  - **Negative cases** (missing fields, invalid credentials, unauthorized access)
  - **Edge cases** (duplicate signup, missing tokens)
- **BaseTest Class**: Handles shared setup like generating unique users, logging in, and storing JWT tokens for reuse.

### 🔁 Ensuring Test Reliability & Maintainability

- **Dynamic Data Generation**: Uses `Faker` to avoid hardcoded/duplicate inputs.
- **Configurable Environments**: `qa.properties`, `dev.properties`, etc., allow switching base URLs via `-Denv=qa`.
- **Centralized Routes**: All endpoints are maintained in a single `Routes.java` file for easy updates.
- **Reusable Request Methods**: The `UserEndPoint` and `BookEndPoint` classes help avoid repeating RestAssured logic.
- **Extent Reports Integration**: Full HTML reports with logs and test metadata are auto-generated.
- **CI/CD Integration**: Ensures tests are automatically run on GitHub push using `api-tests.yml`.

### 🚧 Challenges & How They Were Overcome

| Challenge                          | Resolution                                                                 |
|-----------------------------------|----------------------------------------------------------------------------|
| FastAPI not running in GitHub     | Added workflow step to start `uvicorn` server before tests                 |
| Duplicate email error in signup   | Timestamp-based dynamic email generation using `Faker`                     |
| Workflow not triggering on push   | Corrected branches from `main` to `master`, used `--allow-empty` commits   |
| Dynamic environment configs       | Created `ConfigManager` to load `.properties` based on `-Denv`             |


## 👤 Author

- **Name:** Mohd Aamir
- **LinkedIn:** [linkedin.com/in/mohdaamir11](https://linkedin.com/in/mohdaamir11)
- **GitHub:** [github.com/mohdaamir11](https://github.com/mohdaamir11)

---

## 💡 Tips

- Use 'git commit --allow-empty -m "Trigger CI"' to manually re-run GitHub Actions.
- Clean up local `.git` if accidentally pushing to wrong repo.

---

Execution Triggers:
1. Execute using testng.xml
     right click on testng.xml and run as testng suite 

2. Manual using bash command on terminal - (FastAPI server needs to be started manually before bash command to execute test cases )
   execute using pom.xml  - (mvn clean test / mvn clean -Denv=prod ) 

3. Push or pull to this repository - (FastAPI server will be configured and start by itself using yml file configuration then test cases will be executed )
     git add .
     git commit -m "any comment"  or  git commit -m --allow-empty "any comment" 
     git push

## 📌 License

MIT License ( NA )
