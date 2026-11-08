
package com.mashrafi.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class StudyRepository {

 // ==========================================
 // 1. INITIALIZE DATABASE TABLES
 // ==========================================

 public void initialize() throws SQLException {

  try (Connection c = Database.getConnection();
       Statement s = c.createStatement()) {

   // Calculator History Table
   s.executeUpdate(
           "CREATE TABLE IF NOT EXISTS calc_history (" +
                   "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                   "expression_text VARCHAR(500) NOT NULL, " +
                   "result_text VARCHAR(255) NOT NULL, " +
                   "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
   );

   // Gantt Tasks Table
   s.executeUpdate(
           "CREATE TABLE IF NOT EXISTS gantt_tasks (" +
                   "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                   "day_no INT NOT NULL, " +
                   "title VARCHAR(255) NOT NULL, " +
                   "task_date DATE NOT NULL, " +
                   "description VARCHAR(1000), " +
                   "progress INT NOT NULL DEFAULT 0, " +
                   "status VARCHAR(30) NOT NULL DEFAULT 'Pending', " +
                   "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
   );

   // Upgrade existing tables if necessary
   try {
    s.executeUpdate(
            "ALTER TABLE gantt_tasks " +
                    "ADD COLUMN progress INT NOT NULL DEFAULT 0"
    );
   } catch (SQLException ignored) {
    // Column may already exist
   }

   try {
    s.executeUpdate(
            "ALTER TABLE gantt_tasks " +
                    "ADD COLUMN status VARCHAR(30) " +
                    "NOT NULL DEFAULT 'Pending'"
    );
   } catch (SQLException ignored) {
    // Column may already exist
   }
  }
 }

 // ==========================================
 // 2. SAVE CALCULATION
 // ==========================================

 public void saveCalculation(
         String expression,
         String result
 ) throws SQLException {

  String sql =
          "INSERT INTO calc_history " +
                  "(expression_text, result_text) VALUES (?, ?)";

  try (Connection c = Database.getConnection();
       PreparedStatement p = c.prepareStatement(sql)) {

   p.setString(1, expression);
   p.setString(2, result);

   p.executeUpdate();
  }
 }

 // ==========================================
 // 3. RETRIEVE CALCULATION HISTORY
 // ==========================================

 public List<Calculation> getRecentCalculations(
         int limit
 ) throws SQLException {

  List<Calculation> calculations = new ArrayList<>();

  String sql =
          "SELECT id, expression_text, result_text, created_at " +
                  "FROM calc_history ORDER BY id DESC LIMIT ?";

  try (Connection c = Database.getConnection();
       PreparedStatement p = c.prepareStatement(sql)) {

   p.setInt(1, Math.max(1, Math.min(limit, 200)));

   try (ResultSet r = p.executeQuery()) {

    while (r.next()) {

     Timestamp timestamp = r.getTimestamp("created_at");

     calculations.add(
             new Calculation(
                     r.getLong("id"),
                     r.getString("expression_text"),
                     r.getString("result_text"),
                     timestamp == null
                             ? null
                             : timestamp.toLocalDateTime()
             )
     );
    }
   }
  }

  return calculations;
 }

 // ==========================================
 // 4. SAVE 30-DAY GANTT PLAN
 // ==========================================

 public void savePlan(List<GanttTask> tasks)
         throws SQLException {

  String sql =
          "INSERT INTO gantt_tasks " +
                  "(day_no, title, task_date, description, progress, status) " +
                  "VALUES (?, ?, ?, ?, ?, ?)";

  try (Connection c = Database.getConnection()) {

   c.setAutoCommit(false);

   try (PreparedStatement p = c.prepareStatement(sql)) {

    for (GanttTask task : tasks) {

     p.setInt(1, task.dayNumber());

     p.setString(2, task.title());

     // FIXED: Explicit SQL Date class
     p.setDate(
             3,
             java.sql.Date.valueOf(task.taskDate())
     );

     p.setString(4, task.description());

     p.setInt(5, task.progress());

     p.setString(6, task.status());

     p.addBatch();
    }

    p.executeBatch();

    c.commit();

   } catch (SQLException e) {

    c.rollback();

    throw e;
   }
  }
 }

 // ==========================================
 // 5. RETRIEVE ALL GANTT TASKS
 // ==========================================

 public List<GanttTask> getTasks()
         throws SQLException {

  List<GanttTask> tasks = new ArrayList<>();

  String sql =
          "SELECT id, day_no, task_date, title, " +
                  "description, progress, status " +
                  "FROM gantt_tasks ORDER BY task_date, id";

  try (Connection c = Database.getConnection();
       Statement s = c.createStatement();
       ResultSet r = s.executeQuery(sql)) {

   while (r.next()) {

    tasks.add(
            new GanttTask(
                    r.getLong("id"),
                    r.getInt("day_no"),
                    r.getDate("task_date").toLocalDate(),
                    r.getString("title"),
                    r.getString("description"),
                    r.getInt("progress"),
                    r.getString("status")
            )
    );
   }
  }

  return tasks;
 }

 // ==========================================
 // 6. UPDATE TASK PROGRESS
 // ==========================================

 public void updateTask(
         long id,
         int progress,
         String status
 ) throws SQLException {

  if (progress < 0 || progress > 100) {
   throw new IllegalArgumentException(
           "Progress must be between 0 and 100"
   );
  }

  String sql =
          "UPDATE gantt_tasks " +
                  "SET progress = ?, status = ? WHERE id = ?";

  try (Connection c = Database.getConnection();
       PreparedStatement p = c.prepareStatement(sql)) {

   p.setInt(1, progress);
   p.setString(2, status);
   p.setLong(3, id);

   p.executeUpdate();
  }
 }

 // ==========================================
 // 7. DELETE GANTT TASK
 // ==========================================

 public void deleteTask(long id)
         throws SQLException {

  String sql =
          "DELETE FROM gantt_tasks WHERE id = ?";

  try (Connection c = Database.getConnection();
       PreparedStatement p = c.prepareStatement(sql)) {

   p.setLong(1, id);

   p.executeUpdate();
  }
 }
}
