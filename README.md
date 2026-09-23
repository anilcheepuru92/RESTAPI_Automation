# REST API Automation

Automation framework for testing REST APIs using Java, Maven, REST Assured, JUnit, and Cucumber.

## Overview

This project provides reusable utilities and example tests for validating REST API behavior, including:

- GET requests
- POST requests
- PUT requests
- DELETE requests
- Query parameters
- JSON request and response payloads
- Data-driven POST testing
- Cucumber-based test support
- REST API response validation

## Technology Stack

- **Java**
- **Maven**
- **REST Assured**
- **JUnit 4**
- **Cucumber**
- **Apache HttpClient**
- **Gson**
- **Jackson**
- **Hamcrest**

## Project Structure

```text
RESTAPI_Automation/
├── pom.xml
├── PostRequestFile
├── testdata/
│   └── testdata.txt
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── com/api/rest/
│   │       │   ├── api/
│   │       │   └── ...
│   │       ├── com/api/util/
│   │       │   ├── RestModel.java
│   │       │   └── RestUtil.java
│   │       └── com/cucumber/
│   │           └── hooks/
│   │               └── GeneralHooks.java
│   └── test/
│       └── java/
│           └── restassuredhelper/
│               ├── BaseClass.java
│               ├── Features.java
│               ├── LaptopBag.java
│               ├── TestDelete.java
│               ├── TestGet.java
│               ├── TestPost.java
│               ├── TestPostWithDataDriver.java
│               ├── TestPut.java
│               └── TestQueryParameter.java
└── target/
```

## Prerequisites

Install the following before running the project:

- Java Development Kit (JDK)
- Apache Maven
- Git

Verify the installations:

```bash
java -version
mvn -version
```

## Getting Started

Clone the repository:

```bash
git clone https://github.com/anilcheepuru92/RESTAPI_Automation.git
cd RESTAPI_Automation
```

Install the project dependencies:

```bash
mvn clean install
```

## Running the Tests

Run the complete test suite:

```bash
mvn test
```

Run the tests and generate Maven reports:

```bash
mvn clean test
```

Test reports are generated under:

```text
target/surefire-reports/
```

## Test Coverage

The test suite contains examples covering the following REST API operations:

| Test Class | Description |
|---|---|
| `TestGet` | Validates GET requests and response data |
| `TestPost` | Validates POST requests using JSON payloads |
| `TestPostWithDataDriver` | Executes data-driven POST requests |
| `TestPut` | Validates PUT requests |
| `TestDelete` | Validates DELETE requests |
| `TestQueryParameter` | Validates requests using query parameters |

## Request Payloads

The `PostRequestFile` file contains a sample JSON payload used by POST tests:

```json
{
  "BrandName": "Apple",
  "Features": {
    "Feature": [
      "8GB RAM",
      "1TB Hard Drive",
      "15.5 inch LCD",
      "This is from File"
    ]
  },
  "Id": 2,
  "LaptopName": "Macbook"
}
```

Update the payload and test data files as needed for the API under test.

## Framework Utilities

Reusable API functionality is organized under the `com.api` packages. Utility classes such as `RestUtil` and `RestModel` support request execution, response handling, and API data modeling.

Cucumber-related support includes hooks, transformations, and dependency-injection components under the Cucumber packages.

## Adding a New API Test

1. Add or update the request payload in `PostRequestFile` or the appropriate test-data file.
2. Create a test class under:

   ```text
   src/test/java/restassuredhelper/
   ```

3. Configure the request method, endpoint, headers, parameters, and body.
4. Add assertions for the expected status code and response content.
5. Run the test suite:

   ```bash
   mvn test
   ```

## Dependencies

The project dependencies are managed through `pom.xml`, including:

- REST Assured
- JUnit
- Cucumber
- Gson
- Jackson
- Apache HTTP components
- Hamcrest

Run the following command whenever dependencies change:

```bash
mvn clean test
```

## Notes

- Configure the API base URL and endpoints in the relevant test or framework configuration before execution.
- Do not commit credentials, tokens, or other sensitive information.
- Keep test data separate from test logic where possible.
- The `target/` directory contains generated Maven build artifacts and reports.
