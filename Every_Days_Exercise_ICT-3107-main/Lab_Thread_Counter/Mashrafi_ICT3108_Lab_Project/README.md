# ICT 3108 SEPM Lab — Prime Number Application

**Name:** Md. Mashrafi Rahman  
**Student ID:** IT-24034  
**Department:** ICT, MBSTU  
**Course:** ICT 3108 — Software Engineering Project Management

This Maven WAR project includes:
- **Task 2:** Java JDBC console program to calculate the n-th prime, insert it into MySQL, and retrieve saved records.
- **Task 4:** Jakarta Servlet deployed on Tomcat to check prime/non-prime numbers in a browser.

## Requirements
- JDK 26
- IntelliJ IDEA Ultimate
- MySQL Server (Workbench is only a GUI client)
- Apache Tomcat 11
- Internet for the first Maven dependency download

## MySQL setup
1. Start MySQL Server.
2. Open MySQL Workbench and connect to your local server.
3. Execute `database/setup.sql`.

The connection defaults are in `DatabaseConnection.java`:
- URL: `jdbc:mysql://127.0.0.1:3306/mashrafi_lab`
- User: `root`
- Password: `CHANGE_ME`

Replace `CHANGE_ME` with your actual MySQL password. Do not commit your real password to GitHub. Alternatively set environment variables `MYSQL_URL`, `MYSQL_USER`, and `MYSQL_PASSWORD` in IntelliJ Run Configurations.

## Run Task 2
1. Open this folder in IntelliJ.
2. Import/reload Maven dependencies.
3. Make sure MySQL Server is running and credentials are correct.
4. Run `com.mashrafi.app.PrimeJdbcApplication`.
5. Enter `10`; expected 10th prime is `29`.
6. Verify in Workbench:
```sql
USE mashrafi_lab;
SELECT * FROM mashrafi_rahman_prime ORDER BY id;
```

## Configure Tomcat 11
1. Download and extract Tomcat 11 from https://tomcat.apache.org/.
2. IntelliJ: **Run → Edit Configurations → + → Tomcat Server → Local**.
3. Set Application Server to the extracted Tomcat folder.
4. Open Deployment → **+ → Artifact → mashrafi-prime:war exploded**.
5. Set Application context to `/mashrafi-prime`.
6. Apply and Run.

## Run Task 4
Open:
`http://localhost:8080/mashrafi-prime/prime`

Test:
- `13` → prime
- `15` → not a prime number
- `1` → not a prime number
- `2` → prime

The servlet mapping is `@WebServlet("/prime")` in `PrimeServlet.java`.

## Structure
```text
database/setup.sql
src/main/java/com/mashrafi/
  app/PrimeJdbcApplication.java
  config/DatabaseConnection.java
  dao/PrimeDAO.java
  model/PrimeNumber.java
  servlet/PrimeServlet.java
src/main/webapp/index.html
pom.xml
README.md
```

**Notes:** Task 4's servlet does not need MySQL. Task 2 requires MySQL Server and valid credentials. The n-th-prime method uses `long`, suitable for normal lab inputs. Capture your own actual screenshots for submission; do not use sample images as evidence.
