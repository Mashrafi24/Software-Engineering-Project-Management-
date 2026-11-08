# Mashrafi Study Tools — Professional Edition 2.0

**Student:** Md. Mashrafi Rahman | **ID:** IT-24034  
**Stack:** Java 26, JavaFX 26, Jakarta Servlet 6.1, Tomcat 11, JDBC, MySQL, Maven.

## Included features
- Modern JavaFX tabbed desktop interface.
- Scientific operations: arithmetic, trigonometry, inverse sine/cosine, roots, logarithms, powers, factorial and percentage.
- Calculation history saved in MySQL and displayed in a table.
- 30-day project schedule with daily dates and deliverables.
- Gantt task table with progress and status.
- Update selected task progress (0–100%), automatic Pending / In Progress / Completed status, and delete task.
- Progress overview chart.
- Servlet web interface with calculator, history and saved task list.
- Shared `core` module for calculator, planner and JDBC repository.

## Database setup
1. Start MySQL Server.
2. Execute `database/setup.sql` in MySQL Workbench.
3. Default local connection: `127.0.0.1:3306`, database `mashrafi_tools`, user `root`, password `sql@123`.
4. Override using environment variables `DB_URL`, `DB_USER`, `DB_PASSWORD` if needed. Do not publish real credentials to a public repository; change the default before sharing.

## Open in IntelliJ
Open the folder containing the top-level `pom.xml`. Set Project SDK to JDK 26. Reload the Maven project.

## Build all modules
Use IntelliJ Maven panel → root `mashrafi-tools` → Lifecycle → `clean`, then `install`.
Or use IntelliJ's bundled Maven executable from the project root:
```bash
"/Applications/IntelliJ IDEA.app/Contents/plugins/maven-plugin/lib/maven3/bin/mvn" -U clean install
```

## Run JavaFX desktop app
```bash
"/Applications/IntelliJ IDEA.app/Contents/plugins/maven-plugin/lib/maven3/bin/mvn" -f desktop/pom.xml org.openjfx:javafx-maven-plugin:0.0.8:run
```
Do not run `DesktopApp.main()` directly; use the Maven JavaFX plugin.

## Run Servlet on Tomcat 11
1. Build from the root using `clean install`.
2. Deploy `web/target/mashrafi-tools.war` in IntelliJ Tomcat 11.
3. Set Application Context to `/mashrafi-tools`.
4. Open `http://localhost:8080/mashrafi-tools/tools`.

## Database tables
- `calc_history`: expression, result and timestamp.
- `gantt_tasks`: day, task date, title, description, progress and status.

The application initializes tables on startup. Existing `gantt_tasks` tables are upgraded with progress/status columns where possible.

## Troubleshooting
- `mvn: command not found`: use IntelliJ bundled Maven command above or Maven tool window.
- `core` dependency missing: run root `clean install`, not a goal from desktop alone.
- JavaFX runtime missing: run through the full JavaFX Maven plugin command.
- MySQL access denied: verify server status and credentials.
- Tomcat 404: use `/mashrafi-tools/tools` and verify WAR deployment.
- MySQL Connector missing: reload Maven and build all modules from the root POM.
