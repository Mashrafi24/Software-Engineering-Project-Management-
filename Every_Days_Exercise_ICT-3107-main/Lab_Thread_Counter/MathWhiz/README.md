# MATH WHIZ – Java Servlet & JDBC Game

A four-question mathematical game built with Java Servlets, JSP, HTML, CSS, JDBC and MySQL. All four answers are evaluated on the server using Java methods, not stored answer keys.

## Features
- Player name and start page
- One prime question (2 marks), one modulus question (2 marks), two trigonometry questions (3 marks each)
- Independent Java methods in `MathService`
- Degree-to-radian conversion using `Math.toRadians`
- Decimal comparison tolerance: 0.01
- NORMAL difficulty saved to MySQL
- JDBC `PreparedStatement` score insertion
- Top 10 leaderboard: score descending, then played time ascending

## Requirements
- JDK 17 or newer
- Maven
- MySQL Server / Workbench
- Apache Tomcat 11
- IntelliJ IDEA or another Java IDE

## Database setup
Run `database/math_whiz_db.sql` in MySQL Workbench. Configure environment variables before starting Tomcat:
- `DB_URL` (optional; default: `jdbc:mysql://127.0.0.1:3306/math_whiz_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`)
- `DB_USER` (default: `root`)
- `DB_PASSWORD` (default: empty)

Never commit a real database password to GitHub. The table is also initialized by the application on startup.

## Run in IntelliJ
1. Open this folder as a Maven project and reload Maven dependencies.
2. Add a local Tomcat 11 configuration.
3. Add `math-whiz:war exploded` under Deployment.
4. Set Application context to `/math-whiz`.
5. Start Tomcat and visit `http://localhost:8080/math-whiz/`.

## Build with Maven
Run `mvn clean package` in the project root. Deploy `target/math-whiz.war` to Tomcat 11 `webapps`, then open `http://localhost:8080/math-whiz/`.

## Mathematical methods
- `MathService.isPrime(int)`
- `MathService.calculateMod(int,int)`
- `MathService.calculateSin(double)`
- `MathService.calculateCos(double)`
- `MathService.calculateTan(double)`

## Project structure
```text
MathWhiz/
├── pom.xml
├── README.md
├── database/math_whiz_db.sql
└── src/main/
    ├── java/com/mathwhiz/
    │   ├── service/MathService.java
    │   ├── model/GameQuestion.java
    │   ├── model/GameResult.java
    │   ├── dao/ScoreDAO.java
    │   └── servlet/GameServlet.java
    │       servlet/LeaderboardServlet.java
    └── webapp/
        ├── index.jsp
        ├── game.jsp
        ├── result.jsp
        ├── leaderboard.jsp
        └── css/style.css
```

## Screenshots
After running, add screenshots to `screenshots/`: `home.png`, `game.png`, `result.png`, and `leaderboard.png`.

## Instructor requirement
The four questions are evaluated through actual Java calculations on the server. Client-side scoring and hard-coded answer keys are not used.
