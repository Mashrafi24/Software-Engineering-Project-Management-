CREATE DATABASE IF NOT EXISTS mashrafi_lab;
USE mashrafi_lab;
CREATE TABLE IF NOT EXISTS mashrafi_rahman_prime (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  n_value INT NOT NULL,
  prime_number BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
SELECT * FROM mashrafi_rahman_prime ORDER BY id;