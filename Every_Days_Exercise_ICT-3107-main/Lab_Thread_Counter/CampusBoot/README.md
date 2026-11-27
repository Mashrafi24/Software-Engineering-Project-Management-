# CampusBoot — Java Reflection & Custom Annotations

A small Java 11+ mini-framework demonstrating how annotation-driven frameworks use **custom annotations** and **Java Reflection**. It implements a limited version of component registration, configuration injection, field validation, and URL routing without Spring or external libraries.

## Requirements
- JDK 11 or later
- IntelliJ IDEA (or another Java IDE)
- No external dependencies

## Project Structure
```text
CampusBoot/
├── src/main/java/campusboot/
│   ├── annotation/ (Component, Value, Range, Route)
│   ├── example/ (GradeController, ReportPrinter, Student)
│   ├── Container.java
│   ├── Validator.java
│   ├── Router.java
│   └── Main.java
├── answers.txt
└── README.md
```

## Run in IntelliJ IDEA
1. Extract the ZIP file.
2. Open IntelliJ IDEA → **File → Open** → select the `CampusBoot` folder.
3. Set Project SDK to JDK 11 or later.
4. Open `src/main/java/campusboot/Main.java`.
5. Click the green Run icon beside `main`, or right-click the file and choose **Run 'Main.main()'**.

## Run from Terminal
From the `CampusBoot` folder:
```bash
mkdir -p out
javac -d out $(find src/main/java -name "*.java")
java -cp out campusboot.Main
```

## Annotation Specification
| Annotation | Target | Retention | Purpose |
|---|---|---|---|
| `@Component` | Class | Runtime | Marks a class for registration in the container |
| `@Value` | Field | Runtime | Injects a config value into a field |
| `@Range` | Field | Runtime | Validates an integer against an inclusive range |
| `@Route` | Method | Runtime | Maps a path to a no-argument String method |

## Implementation Summary
- **Part A — Annotations:** Four custom annotations use the specified targets, runtime retention, elements, and defaults.
- **Part B — Container:** `scan()` registers annotated classes and names beans; `injectValues()` injects `String` and `int` configuration values using Reflection.
- **Part C — Validator:** `validate()` checks `@Range` fields and returns violations in the required format.
- **Part D — Router:** `dispatch()` invokes a matching `@Route` method or returns `404 Not Found: <path>`.
- **Part E — Reflection answers:** See [`answers.txt`](answers.txt).

## Expected Output
```text
[Container] Registered: gradeController (GradeController)
[Container] Skipped: ReportPrinter (no @Component)
[Container] Injected: app.name -> GradeController.appName
[Container] Injected: app.passMark -> GradeController.passMark
--- Validation ---
VIOLATION: score - Score must be between 0 and 100 (actual: 120)
OK
VIOLATION: semester - Value out of range (actual: 15)
--- Routing ---
[Router] /hello -> Welcome to CampusBoot
[Router] /status -> Pass mark is 40
[Router] /unknown -> 404 Not Found: /unknown
```

**Assignment:** CampusBoot — Java Reflection & Custom Annotations  
**Language:** Java 11+ | **External libraries:** None
