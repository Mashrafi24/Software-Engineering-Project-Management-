<div align="center">

# 🧮 MASHRAFI MATH WHIZ

### A Java Servlet & JDBC Math Quiz Game with a Top 10 Leaderboard

![Java](https://img.shields.io/badge/Java-Servlet%20%26%20JSP-orange?logo=openjdk&logoColor=white)
![Tomcat](https://img.shields.io/badge/Apache%20Tomcat-11-yellow?logo=apachetomcat&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-JDBC-blue?logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Build-Maven-red?logo=apachemaven&logoColor=white)
![IntelliJ](https://img.shields.io/badge/IDE-IntelliJ%20IDEA-black?logo=intellijidea&logoColor=white)

**Java Web Application Development · Live Lab Test #2**

</div>

---

## 📋 Project Information

| Field | Details |
|---|---|
| 👤 **Student Name** | Md. Mashrafi Rahman |
| 🆔 **Student ID** | IT-24034 |
| 📌 **Project Title** | Mashrafi Math Whiz |
| 🧩 **Project Type** | Java Servlet & JDBC Web Application |
| 🎚️ **Difficulty** | NORMAL |
| 🏆 **Maximum Score** | 10 marks |
| 🖥️ **Application Server** | Apache Tomcat 11 |
| 🗄️ **Database** | MySQL (`math_whiz_db`) |

---

## 🧭 Quick Navigation – All 10 Tasks

> 👇 Click any task to jump directly to its section.

| # | Task | Jump |
|:-:|---|:-:|
| 01 | 🛠️ Project Setup | [Go →](#task-01) |
| 02 | 🎨 Design the Game Interface | [Go →](#task-02) |
| 03 | 🔢 Prime Number Function | [Go →](#task-03) |
| 04 | ➗ Modulus Function | [Go →](#task-04) |
| 05 | 📐 Trigonometric Functions | [Go →](#task-05) |
| 06 | 🎮 Game Logic | [Go →](#task-06) |
| 07 | 🗄️ Database Design Using JDBC | [Go →](#task-07) |
| 08 | 🏅 Top 10 Leaderboard | [Go →](#task-08) |
| 09 | ✅ Testing and Validation | [Go →](#task-09) |
| 10 | 📦 Documentation & GitHub Submission | [Go →](#task-10) |

**Also:** [📖 Overview](#overview) · [🧰 Technologies](#technologies) · [☑️ Final Checklist](#checklist) · [🎓 Learning Outcomes](#outcomes) · [✍️ Author](#author)


Project: https://github.com/Mashrafi24/Software-Engineering-Project-Management-/tree/main/Every_Days_Exercise_ICT-3107-main/Lab_Thread_Counter/MathWhiz
---

<a id="overview"></a>
## 📖 Project Overview

**Mashrafi Math Whiz** is a web-based mathematical quiz game developed with **Java Servlets, JSP, JDBC, and MySQL**.


<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/82dfd2f7-5469-4248-a8c2-f16825c68d22" />


A player enters a name and answers **four mathematical questions** from three areas: **prime numbers, modulus operations, and trigonometry**. The server evaluates the submitted answers using Java methods, calculates a **score out of 10**, saves the result in MySQL, and provides a **Top 10 leaderboard**.

flowchart TB
    A(["👤 PLAYER<br/>Enter Name"]) --> B["🌐 WELCOME PAGE<br/>JSP / HTML / CSS"]
    B --> C["🎮 START GAME<br/>Java Servlet"]

    <img width="1882" height="2984" alt="image" src="https://github.com/user-attachments/assets/49f3a1a2-13a8-435f-909c-61e89052e159" />

The purpose of this lab is to demonstrate mathematical method development, server-side processing, JDBC database operations, and leaderboard generation — not simply to display a quiz interface.

<a id="technologies"></a>
## 🧰 Technologies Used

| Technology | Purpose |
|---|---|
| ☕ Java | Mathematical logic and application processing |
| 🌐 Java Servlet | Server-side request handling |
| 📄 JSP | Dynamic web pages |
| 🔌 JDBC | Java-to-MySQL connectivity |
| 🐬 MySQL | Persistent score storage |
| 🐱 Apache Tomcat 11 | Web application server |
| 📦 Maven | Dependency and build management |
| 💡 IntelliJ IDEA | Development environment |
| 🎨 HTML & CSS | User interface |

[⬆️ Back to top](#-mashrafi-math-whiz)

---

<a id="task-01"></a>
## 🛠️ Task 01 – Project Setup

**Objective:** Create and configure the Java web application environment.

**Architecture:** The project uses a Servlet/JSP structure. Servlets receive browser requests, call the application logic and DAO classes, and forward data to JSP pages.

```text
MathWhiz/
├── database/
│   └── math_whiz_db.sql
├── src/
│   └── main/
│       ├── java/
│       │   └── com/mathwhiz/
│       │       ├── dao/
│       │       │   └── ScoreDAO.java
│       │       ├── model/
│       │       │   ├── GameQuestion.java
│       │       │   └── GameResult.java
│       │       ├── service/
│       │       │   └── MathService.java
│       │       └── servlet/
│       │           ├── GameServlet.java
│       │           └── LeaderboardServlet.java
│       └── webapp/
│           ├── css/
│           │   └── style.css
│           ├── index.jsp
│           ├── game.jsp
│           ├── result.jsp
│           └── leaderboard.jsp
├── pom.xml
├── .gitignore
└── README.md
```

**📸 Screenshot 01 – IntelliJ IDEA Project Structure**

<img width="488" height="860" alt="image" src="https://github.com/user-attachments/assets/a9d99693-073a-4676-99f6-efd226bf9ebb" />

[⬆️ Back to navigation](#-quick-navigation--all-10-tasks)

---

<a id="task-02"></a>
## 🎨 Task 02 – Design the Game Interface

**Objective:** Create a user-friendly welcome page where a player can enter their name and start the challenge.

**Implemented interface elements:**

- ✅ Project title: **MASHRAFI MATH WHIZ**
- ✅ Player name input field
- ✅ **Start Game** button
- ✅ Link to the Top 10 Leaderboard
- ✅ Difficulty level and maximum score information

The welcome form sends the player's name to the server using an **HTTP POST** request.

**📸 Screenshot 02 – Welcome Page**

<img width="1451" height="860" alt="image-1" src="https://github.com/user-attachments/assets/eac62219-d87e-4030-818e-5bbd1ec4a605" /><img width="690" height="245" alt="image-2" src="https://github.com/user-attachments/assets/0addcbf8-b6eb-4ea0-a1c2-4dec98c6d895" />


[⬆️ Back to navigation](#-quick-navigation--all-10-tasks)

---

<a id="task-03"></a>
## 🔢 Task 03 – Prime Number Function

**Objective:** Implement a reusable Java method that determines whether an integer is prime.

```java
public static int isPrime(int number)
```

**Expected behaviour**

- Returns `1` when the number is prime.
- Returns `0` when the number is not prime.
- Numbers less than 2 are not prime.

| Input | Expected Output |
|:-:|:-:|
| 7 | 1 |
| 8 | 0 |
| 1 | 0 |
| 2 | 1 |
| 13 | 1 |

The game uses this method to evaluate **Question 1**.

**📸 Screenshot 03 – Prime Number Function**

<img width="690" height="245" alt="image-2" src="https://github.com/user-attachments/assets/71a50146-5fe2-4032-bf74-194e06197bcc" />

[⬆️ Back to navigation](#-quick-navigation--all-10-tasks)

---

<a id="task-04"></a>
## ➗ Task 04 – Modulus Function

**Objective:** Implement a Java method that calculates the remainder of integer division.

```java
public static int calculateMod(int a, int b)
```

**Expected behaviour**

- Accepts two integer operands.
- Uses Java's `%` operator to calculate the remainder.
- Handles a divisor of zero through **validation** instead of attempting the operation.

| Expression | Expected Result |
|:-:|:-:|
| 17 % 5 | 2 |
| 20 % 3 | 2 |
| 15 % 4 | 3 |
| 10 % 0 | ❌ Validation error |

The game uses this method to evaluate **Question 2**.

**📸 Screenshot 04 – Modulus Function**

<img width="929" height="240" alt="image-3" src="https://github.com/user-attachments/assets/347d22fb-8c01-4229-9741-2e7d4668af20" />

[⬆️ Back to navigation](#-quick-navigation--all-10-tasks)

---

<a id="task-05"></a>
## 📐 Task 05 – Trigonometric Functions

**Objective:** Implement reusable Java methods to calculate sine, cosine, and tangent values.

```java
public static double calculateSin(double degree);
public static double calculateCos(double degree);
public static double calculateTan(double degree);
```

**Implementation requirements**

- Accept angles in **degrees**.
- Convert degrees into radians using `Math.toRadians()`.
- Use `Math.sin()`, `Math.cos()`, and `Math.tan()`.
- Handle undefined tangent values appropriately.
- Compare decimal answers using a suitable **tolerance**.

| Function | Input | Expected Result |
|:-:|:-:|:-:|
| Sine | 30° | 0.5 |
| Cosine | 60° | 0.5 |
| Tangent | 45° | 1.0 |

These methods are used to evaluate **Questions 3 and 4**.

**📸 Screenshot 05 – Trigonometric Functions**

!<img width="919" height="204" alt="image-4" src="https://github.com/user-attachments/assets/6149c46e-5d43-47d6-9c25-5c1c598808c6" />

[⬆️ Back to navigation](#-quick-navigation--all-10-tasks)

---

<a id="task-06"></a>
## 🎮 Task 06 – Implement the Game Logic

**Objective:** Integrate the mathematical methods into a complete four-question game.

| Question | Mathematical Area | Marks |
|:-:|---|:-:|
| Q1 | Prime number | 2 |
| Q2 | Modulus | 2 |
| Q3 | Trigonometry | 3 |
| Q4 | Trigonometry | 3 |
| **Total** | | **10** |

**Game workflow**

1. The player enters their name.
2. The server receives the player's name.
3. The application presents four mathematical questions.
4. The player enters an answer for each question.
5. The server evaluates the answers using the Java mathematical methods.
6. The application calculates the total score.
7. The result is displayed to the player.

**Scoring logic**

```java
int totalScore = 0;

if (primeCorrect) {
    totalScore += 2;
}

if (modCorrect) {
    totalScore += 2;
}

if (trig1Correct) {
    totalScore += 3;
}

if (trig2Correct) {
    totalScore += 3;
}
```

**Maximum score: 10 marks.**

**📸 Screenshot 06 – Game Page**

<img width="1046" height="961" alt="image-5" src="https://github.com/user-attachments/assets/a826de5a-d6c5-40ae-a42b-59c6e96690cd" />


**📸 Screenshot 07 – Result Page**



<img width="993" height="782" alt="image-6" src="https://github.com/user-attachments/assets/0733ef9a-4c33-4a11-9fe4-66f88d14ce11" />

---

<a id="task-07"></a>

## 🗄️ Task 07 – Database Design Using JDBC

**Objective:** Connect the application to MySQL and store each completed game's score.

| Item | Value |
|---|---|
| Database name | `math_whiz_db` |
| Table name | `game_scores` |

**Table structure**

| Field | Data Type | Description |
|---|---|---|
| `id` | INT | Primary key, auto increment |
| `player_name` | VARCHAR(100) | Player's name |
| `score` | INT | Final score |
| `difficulty` | VARCHAR(20) | Difficulty level |
| `played_at` | TIMESTAMP | Date and time of play |

**Database creation SQL** (file: [`database/math_whiz_db.sql`](database/math_whiz_db.sql))

```sql
CREATE DATABASE IF NOT EXISTS math_whiz_db;

USE math_whiz_db;

CREATE TABLE IF NOT EXISTS game_scores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    player_name VARCHAR(100) NOT NULL,
    score INT NOT NULL,
    difficulty VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**JDBC operations**

- Establish a connection to MySQL.
- Insert a player's name, score, difficulty, and playing date/time.
- Retrieve saved scores.
- Use `PreparedStatement` for parameterized SQL operations.

**📸 Screenshot 08 – MySQL Database and Table**

<img width="796" height="762" alt="image-8" src="https://github.com/user-attachments/assets/38450e89-5c4f-4e4e-be83-003821f4a4c1" />

**📸 Screenshot 08B – Saved Game Records**

```sql
USE math_whiz_db;

SELECT * FROM game_scores;
```

<img width="796" height="762" alt="image-8" src="https://github.com/user-attachments/assets/4ca06df4-e7e1-4382-9004-ddf03de0b70d" />

[⬆️ Back to navigation](#-quick-navigation--all-10-tasks)

---

<a id="task-08"></a>
## 🏅 Task 08 – Display the Top 10 Leaderboard

**Objective:** Retrieve and display the ten highest scores from the database.

```sql
SELECT *
FROM game_scores
ORDER BY score DESC, played_at ASC
LIMIT 10;
```

**Leaderboard requirements**

- Display no more than **ten** records.
- Sort scores in **descending** order.
- Use `played_at` in **ascending** order as the secondary sorting criterion.
- Display player name, score, difficulty, and date/time.

**📸 Screenshot 09 – Top 10 Leaderboard**

<img width="1058" height="502" alt="image-9" src="https://github.com/user-attachments/assets/f13f5d19-d2bb-495f-bb40-21a1095ffe36" />



[⬆️ Back to navigation](#-quick-navigation--all-10-tasks)

---

<a id="task-09"></a>
## ✅ Task 09 – Testing and Validation

**Objective:** Test the mathematical functions, game scoring, input validation, and database operations.

| Test Case | Input | Expected Result |
|---|---|---|
| Prime number | 7 | 1 |
| Prime number | 8 | 0 |
| Prime number | 1 | 0 |
| Modulus | 17 % 5 | 2 |
| Sine | 30° | 0.5 |
| Cosine | 60° | 0.5 |
| Tangent | 45° | 1.0 |
| Zero divisor | 10 % 0 | Validation error |
| All answers correct | Four correct answers | 10 marks |
| Incorrect answers | One or more wrong answers | Corresponding marks are not awarded |

**Validation rules**

- Invalid modulus operations must be rejected.
- Trigonometric values must be calculated from degree inputs.
- Decimal answers must be checked using an appropriate tolerance.
- Scores must be calculated on the server.
- Completed game records must be checked in the database.

**📸 Screenshot 10A – Mathematical Testing**

<img width="796" height="762" alt="image-8" src="https://github.com/user-attachments/assets/8f87b21a-6585-4812-8349-a7ec54c3d42e" />



## 📦 Task 10 – Final Documentation and GitHub Submission

**Objective:** Prepare the complete project for submission and document its implementation.

### 🧱 Software Requirements

- Java Development Kit (JDK)
- IntelliJ IDEA
- Apache Tomcat 11
- MySQL Server
- MySQL Workbench
- MySQL Connector/J
- Maven

### ▶️ How to Run the Project

1. Open the project in **IntelliJ IDEA**.
2. Configure the **JDK**.
3. Reload **Maven** dependencies.
4. Start the **MySQL** server.
5. Execute `database/math_whiz_db.sql` in **MySQL Workbench**.
6. Configure the database connection URL, username, and password in the application's database connection settings.
7. Configure **Apache Tomcat 11** in IntelliJ IDEA.
8. Deploy the web application to Tomcat.
9. Start the Tomcat server.
10. Open the deployed application in a browser.

### 🌍 Application URL

The context path depends on the artifact name configured in IntelliJ IDEA/Tomcat.

```text
http://localhost:8080/<your-context-path>/
```

Replace `<your-context-path>` with the actual deployed context path.

### 🔗 GitHub Submission

| Item | Link |
|---|---|
| GitHub Repository : 
https://github.com/Mashrafi24/Software-Engineering-Project-Management-/tree/main/Every_Days_Exercise_ICT-3107-main/Lab_Thread_Counter/MathWhiz/src/main

**📸  – GitHub Repository**
https://github.com/Mashrafi24/Software-Engineering-Project-Management-/tree/main/Every_Days_Exercise_ICT-3107-main/Lab_Thread_Counter/MathWhiz/src/main

[⬆️ Back to navigation](#-quick-navigation--all-10-tasks)

---

<a id="checklist"></a>
## ☑️ Final Submission Checklist

- [x] [Task 01](#task-01) – Java web application setup and project structure
- [x] [Task 02](#task-02) – Welcome page and player name form
- [x] [Task 03](#task-03) – Prime number method
- [x] [Task 04](#task-04) – Modulus method and zero-divisor validation
- [x] [Task 05](#task-05) – Sine, cosine, and tangent methods
- [x] [Task 06](#task-06) – Four-question game and 10-mark scoring
- [x] [Task 07](#task-07) – JDBC connection and MySQL score storage
- [x] [Task 08](#task-08) – Top 10 leaderboard
- [x] [Task 09](#task-09) – Mathematical, validation, and database testing
- [x] [Task 10](#task-10) – README, SQL file, screenshots, and GitHub submission

---

<a id="outcomes"></a>
## 🎓 Learning Outcomes

This project provides practical experience in:

- Developing reusable Java mathematical methods.
- Applying conditional statements and decision-making logic.
- Processing HTTP requests and responses using Servlets.
- Connecting Java applications to MySQL using JDBC.
- Using `PreparedStatement` for parameterized SQL operations.
- Retrieving, sorting, and displaying database records.
- Developing a Java Servlet/JSP web application.
- Documenting and submitting a software project through GitHub.

---

<a id="author"></a>
## ✍️ Author

<div align="center">

**Md. Mashrafi Rahman**
Student ID: **IT-24034**
Project: **Mashrafi Math Whiz**
Lab: **Live Lab Test #2**
Technology: **Java Servlet, JSP, JDBC, and MySQL**

## Github: https://github.com/Mashrafi24

[⬆️ Back to top](#-mashrafi-math-whiz)

</div>
