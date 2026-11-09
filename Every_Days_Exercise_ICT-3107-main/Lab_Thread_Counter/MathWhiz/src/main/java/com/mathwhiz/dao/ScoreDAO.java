
package com.mathwhiz.dao;

import java.sql.*;
import java.util.*;

public class ScoreDAO {

    private static String setting(String key, String fallback) {
        String value = System.getenv(key);
        return value == null || value.isBlank() ? fallback : value;
    }

    private Connection connect() throws SQLException {

        String url = setting(
                "DB_URL",
                "jdbc:mysql://127.0.0.1:3306/math_whiz_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
        );

        String user = setting("DB_USER", "root");

        // MySQL password
        String password = setting("DB_PASSWORD", "sql@123");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "MySQL Connector/J missing.", e
            );
        }

        return DriverManager.getConnection(
                url, user, password
        );
    }

    public void initialize() throws SQLException {

        String sql = """
            CREATE TABLE IF NOT EXISTS game_scores (
                id INT PRIMARY KEY AUTO_INCREMENT,
                player_name VARCHAR(100) NOT NULL,
                score INT NOT NULL,
                difficulty VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
                played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
            """;

        try (Connection c = connect();
             Statement s = c.createStatement()) {

            s.executeUpdate(sql);
        }
    }

    public void saveScore(
            String name, int score, String difficulty
    ) throws SQLException {

        String sql = """
            INSERT INTO game_scores
            (player_name, score, difficulty)
            VALUES (?, ?, ?)
            """;

        try (Connection c = connect();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setString(1, name);
            p.setInt(2, score);
            p.setString(3, difficulty);

            p.executeUpdate();
        }
    }

    public List<ScoreRecord> getTopTen()
            throws SQLException {

        List<ScoreRecord> records = new ArrayList<>();

        String sql = """
            SELECT player_name, score, difficulty, played_at
            FROM game_scores
            ORDER BY score DESC, played_at ASC
            LIMIT 10
            """;

        try (Connection c = connect();
             PreparedStatement p = c.prepareStatement(sql);
             ResultSet rs = p.executeQuery()) {

            while (rs.next()) {
                records.add(new ScoreRecord(
                        rs.getString("player_name"),
                        rs.getInt("score"),
                        rs.getString("difficulty"),
                        rs.getTimestamp("played_at")
                ));
            }
        }

        return records;
    }

    public record ScoreRecord(
            String playerName,
            int score,
            String difficulty,
            Timestamp playedAt
    ) {}
}
