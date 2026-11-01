package com.mashrafi.app;
import com.mashrafi.dao.PrimeDAO;
import com.mashrafi.model.PrimeNumber;
import java.sql.SQLException;
import java.util.Scanner;
public class PrimeJdbcApplication {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            PrimeDAO dao = new PrimeDAO();
            try { dao.createTableIfNeeded(); System.out.println("MySQL connected; table ready."); }
            catch (SQLException e) { System.err.println("MySQL connection/table error. Check server and credentials."); e.printStackTrace(); return; }
            System.out.print("Enter N (1 or greater): ");
            if (!in.hasNextInt()) { System.out.println("Please enter a whole number."); return; }
            int n = in.nextInt();
            try {
                long prime = PrimeNumber.findNthPrime(n);
                System.out.println("The " + n + "-th prime number is: " + prime);
                dao.insert(n, prime);
                System.out.println("Record inserted successfully.\nSaved records:");
                dao.printAllRecords();
            } catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
              catch (SQLException e) { System.err.println("Database operation failed."); e.printStackTrace(); }
        }
    }
}