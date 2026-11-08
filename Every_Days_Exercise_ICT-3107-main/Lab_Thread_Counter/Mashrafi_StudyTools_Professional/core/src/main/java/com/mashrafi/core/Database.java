package com.mashrafi.core;
import java.sql.*;
public final class Database {
 private static String env(String k,String d){String v=System.getenv(k);return v==null||v.isBlank()?d:v;}
 private static final String URL=env("DB_URL","jdbc:mysql://127.0.0.1:3306/mashrafi_tools?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
 private static final String USER=env("DB_USER","root"), PASSWORD=env("DB_PASSWORD","sql@123");
 private Database(){}
 public static Connection getConnection() throws SQLException {
  try{Class.forName("com.mysql.cj.jdbc.Driver");}catch(ClassNotFoundException e){throw new SQLException("MySQL Connector/J is missing. Reload Maven.",e);}
  return DriverManager.getConnection(URL,USER,PASSWORD);
 }
}