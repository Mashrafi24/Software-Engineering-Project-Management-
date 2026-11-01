package com.mashrafi.dao;
import com.mashrafi.config.DatabaseConnection;
import java.sql.*;
public class PrimeDAO {
    public void createTableIfNeeded() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS mashrafi_rahman_prime (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, n_value INT NOT NULL, " +
                "prime_number BIGINT NOT NULL, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        try (Connection c = DatabaseConnection.getConnection(); Statement s = c.createStatement()) {
            s.executeUpdate(sql);
        }
    }
    public void insert(int n, long prime) throws SQLException {
        String sql = "INSERT INTO mashrafi_rahman_prime (n_value, prime_number) VALUES (?, ?)";
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, n); p.setLong(2, prime); p.executeUpdate();
        }
    }
    public void printAllRecords() throws SQLException {
        String sql = "SELECT id, n_value, prime_number, created_at FROM mashrafi_rahman_prime ORDER BY id";
        try (Connection c = DatabaseConnection.getConnection(); Statement s = c.createStatement();
             ResultSet r = s.executeQuery(sql)) {
            System.out.printf("%-8s %-12s %-16s %-25s%n", "ID", "N VALUE", "PRIME", "CREATED AT");
            while (r.next()) System.out.printf("%-8d %-12d %-16d %-25s%n",
                    r.getLong("id"), r.getInt("n_value"), r.getLong("prime_number"), r.getTimestamp("created_at"));
        }
    }
}