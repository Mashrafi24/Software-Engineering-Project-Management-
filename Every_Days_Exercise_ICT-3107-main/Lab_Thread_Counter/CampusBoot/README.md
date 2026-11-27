# 🧩 CampusBoot — Custom Annotations & Java Reflection

> **Understanding how Spring Boot works under the hood**

| Item | Details |
|---|---|
| Assignment | Build CampusBoot with Custom Annotations |
| Student | **Md.Mashrafi Rahman** |
| Student ID | **[IT-24034** |
| Language | Java 11+ |
| IDE | IntelliJ IDEA |
| External libraries | None |
| Total marks | 10 |

---

## 1. Project Overview

**CampusBoot** is a small Java framework developed to demonstrate how annotation-driven frameworks work internally. It uses Java's built-in **Reflection API** to inspect custom annotations at runtime and perform component registration, configuration value injection, field validation, and method routing.

The project recreates a few basic framework features without using Spring or external libraries.

### Main Features

1. **Component registration:** Detect classes marked with `@Component` and register their objects in a container.
2. **Value injection:** Read `@Value` annotations and inject values from a configuration map.
3. **Field validation:** Check fields marked with `@Range`.
4. **Request routing:** Find methods marked with `@Route` and invoke the method associated with a requested path.

---

## 2. Learning Outcomes

- Declare custom annotations using `@Target` and `@Retention`.
- Make annotations visible at runtime.
- Inspect classes, fields, and methods using `Class`, `Field`, and `Method`.
- Build a basic Inversion of Control (IoC) container.
- Inject `String` and `int` configuration values.
- Validate integer fields using a custom annotation.
- Map request paths to Java methods and invoke them dynamically.
- Explain how the mini-framework relates to Spring Boot.

---

## 3. Technologies Used

- Java 11 or later
- Java Reflection API
- Custom Java Annotations
- IntelliJ IDEA
- Java Collections Framework (`Map`, `HashMap`, `List`, `ArrayList`)
- No external libraries or frameworks

---

## 4. Custom Annotation Specification

| Annotation | Target | Elements | Purpose |
|---|---|---|---|
| `@Component` | Class | `String value() default ""` | Marks a class for registration in the container. |
| `@Value` | Field | `String value()` | Identifies a configuration key to inject. |
| `@Range` | Field | `int min()`, `int max()`, `String message() default "Value out of range"` | Defines the valid range for an integer field. |
| `@Route` | Method | `String path()` | Maps a URL path to a method. |

All four annotations must use runtime retention so Reflection can inspect them while the program is running.

### Related Spring Concepts

| CampusBoot annotation | Related Spring annotation / concept |
|---|---|
| `@Component` | `@Component` |
| `@Value` | `@Value` |
| `@Range` | Similar validation purpose to Bean Validation constraints such as `@Min` and `@Max` |
| `@Route` | Similar request-mapping purpose to `@GetMapping` or `@RequestMapping` |

These are conceptual mappings. CampusBoot does not implement the full functionality of Spring or Jakarta Bean Validation.

---

## 5. Project Structure

Use the structure that matches your actual IntelliJ project and Java package declarations. One possible organization is:

```text
CampusBoot/
├── src/
│   └── campusboot/
│       ├── annotations/
│       │   ├── Component.java
│       │   ├── Value.java
│       │   ├── Range.java
│       │   └── Route.java
│       ├── Container.java
│       ├── Validator.java
│       ├── Router.java
│       ├── GradeController.java
│       ├── ReportPrinter.java
│       ├── Student.java
│       └── Main.java
├── screenshots/
├── answers.txt
└── README.md
```

If your working project keeps all classes in one `campusboot` package, retain that structure rather than moving files without updating package declarations.

---

## 6. Implementation Explanation

### Part A — Custom Annotations

Create the four annotations with the specified targets and runtime retention:

- `@Component` applies to classes.
- `@Value` applies to fields.
- `@Range` applies to fields.
- `@Route` applies to methods.

### Part B — Container: IoC and Value Injection

The `Container` class has three main methods:

- `scan(Class<?>... classes)`: checks whether each class has `@Component`. If present, it creates an instance using the no-argument constructor and stores it in a `Map<String, Object>`.
- `injectValues(Map<String, String> config)`: finds fields marked with `@Value`, reads the matching configuration value, and assigns it to the field.
- `getBean(String name)`: returns the registered object associated with the bean name.

The implementation supports `String` and `int` fields. Reflection access is enabled using `setAccessible(true)` where required. The bean name is the annotation's `value()` or, if empty, the class's simple name with its first letter in lowercase.

### Part C — Validator

The `Validator` class provides:

```java
static List<String> validate(Object obj)
```

It inspects fields marked with `@Range`, reads their integer values, and checks whether each value is within the declared minimum and maximum.

Required violation format:

```text
fieldName - message (actual: value)
```

If every annotated field is valid, the method returns an empty list.

### Part D — Router

The `Router` class provides:

```java
String dispatch(Container c, String path)
```

It searches registered beans for a method whose `@Route` path matches the requested path. It invokes the matching method and returns its `String` result.

If no method matches, it returns:

```text
404 Not Found: /requested-path
```

### Part E — Reflection Questions

The answers are included in [`answers.txt`](answers.txt).

---

## 7. Sample Configuration and Test Data

### Configuration

| Configuration key | Value |
|---|---|
| `app.name` | `CampusBoot` |
| `app.passMark` | `40` |

### Validator Test Cases

| Student | Score | Semester | Expected result |
|---|---:|---:|---|
| Student 1 | 120 | 5 | Score violation |
| Student 2 | 85 | 5 | Valid |
| Student 3 | 70 | 15 | Semester violation |

### Router Test Paths

- `/hello`
- `/status`
- `/unknown`

---

## 8. How to Run in IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Select **File → Open** and open the `CampusBoot` project folder.
3. Make sure the project uses **JDK 11 or later**.
4. Confirm that all Java source files are in the correct source directory and that package declarations match.
5. Open `Main.java`.
6. Click the green **Run** icon beside the `main()` method, or right-click `Main.java` and select **Run 'Main.main()'**.
7. Check the Run/Console window and compare the result with the expected output below.

No database, web server, Maven dependency, or external framework is required.

---

## 9. Expected Console Output

The program should produce output matching the assignment sample:

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

### Run Verification

The console output shared for this project shows that:

- `GradeController` was registered.
- `ReportPrinter` was skipped because it has no `@Component`.
- Both configuration values were injected.
- Invalid student values were detected, while the valid student produced `OK`.
- `/hello` and `/status` returned the expected messages.
- `/unknown` returned the required 404 message.
- The program finished with exit code `0`.

**Insert your own IntelliJ Run-window screenshot here:**

```markdown
![CampusBoot Console Output](screenshots/campusboot-console.png)
```

---

## 10. Reflection Questions — Answers

### Q1. What would happen if `@Retention(RetentionPolicy.RUNTIME)` were forgotten?

Without runtime retention, Java does not keep the annotation available for runtime Reflection. The framework would therefore be unable to find it using methods such as `getAnnotation()`. As a result, registration, value injection, validation, or routing based on that annotation would not work.

### Q2. Map each CampusBoot annotation to its Spring equivalent. Name one thing real Spring does that CampusBoot does not.

`@Component` and `@Value` correspond to Spring annotations with the same names. `@Range` has a similar validation purpose to constraints such as `@Min` and `@Max`, while `@Route` has a similar request-mapping purpose to `@GetMapping` or `@RequestMapping`. Real Spring provides a much more complete dependency-injection container, component scanning, configuration system, web infrastructure, and many production-ready features.

### Q3. Why is `setAccessible(true)` both powerful and risky?

`setAccessible(true)` allows Reflection to access fields or methods that are not normally accessible, including private members. This is useful for injecting values into private fields, but it can weaken encapsulation and expose internal implementation details. It should therefore be used carefully and only when necessary.

---

## 11. Screenshots to Include

Create a folder named `screenshots` in the project root. Add genuine screenshots from your own IntelliJ project.

| No. | Screenshot filename | What it should show |
|---:|---|---|
| 1 | `project-structure.png` | IntelliJ project tree showing the Java files. |
| 2 | `annotations.png` | The four custom annotation definitions. |
| 3 | `container.png` | Container scanning and value-injection implementation. |
| 4 | `validator.png` | Validator implementation using `@Range`. |
| 5 | `router.png` | Router implementation using `@Route`. |
| 6 | `campusboot-console.png` | Full console output matching the sample. |

Do not use sample screenshots as proof that your own code ran.

---

## 12. Submission Checklist

- [ ] Four custom annotations are implemented.
- [ ] Runtime retention and correct targets are used.
- [ ] `Container.scan()` registers annotated classes and skips unannotated classes.
- [ ] `Container.injectValues()` injects `String` and `int` values.
- [ ] `Validator.validate()` checks `@Range` fields.
- [ ] `Router.dispatch()` invokes matching `@Route` methods.
- [ ] Unknown paths return the required 404 message.
- [ ] All three reflection questions are answered in `answers.txt`.
- [ ] The project compiles and runs successfully.
- [ ] Screenshots are added to the `screenshots/` folder.
- [ ] The final ZIP is named `StudentID_CampusBoot.zip`.

### Final ZIP Structure

```text
StudentID_CampusBoot.zip
└── CampusBoot/
    ├── src/
    │   └── ... all required .java files ...
    ├── screenshots/
    │   └── ... project screenshots ...
    ├── answers.txt
    └── README.md
```

---

## 13. Conclusion

CampusBoot demonstrates how custom annotations and Java Reflection can be combined to build a small annotation-driven framework. Through component registration, configuration value injection, field validation, and method routing, the assignment illustrates the basic ideas behind features commonly used in Spring Boot.

The project reinforces that annotations provide metadata, while Reflection makes it possible to inspect that metadata and perform actions at runtime.

---

**Assignment:** CampusBoot with Custom Annotations  
**Prepared by:** Mashrafi  
**Instructor:** Ms. Nadia
