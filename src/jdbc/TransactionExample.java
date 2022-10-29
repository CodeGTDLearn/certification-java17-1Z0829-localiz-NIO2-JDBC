package jdbc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class TransactionExample {
  static String mySqlUrl = "jdbc:mysql://localhost:3306/testone";
  static Connection connect;
  public static void main(String[] args) throws SQLException {
     connect = getConnection(mySqlUrl);

    boolean success = false;
    try {
      Connection con = getConnection(mySqlUrl);
      connect = getConnection(mySqlUrl);

      connect.setAutoCommit(false);

      success = insertRecord("12-6", "bo", "te", connect);

      // Commit Transactions
      if (success) connect.commit();
      else         connect.rollback();

      connect.setAutoCommit(true);
    }
    catch (java.sql.SQLException ex) {
      System.out.println(ex);
    }
    finally {
      if (connect != null) {
        try { connect.close(); }
        catch (SQLException ex) { ex.printStackTrace(); }
      }
    }
  }

  private static void queryDbbooks() {

    String sql = "SELECT ID, book_NUMBER, book_NAME, DESCRIPTION " +
         "FROM bookS";
    try (PreparedStatement pstmt = connect.prepareStatement(sql);) {
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        System.out.println(rs.getString(2) + ": " +
                                rs.getString(3) + " - " + rs.getString(4));
      }
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  private static boolean insertRecord(String num, String title, String desc, Connection conn) {
    boolean success = false;

    String sql = "INSERT INTO bookS VALUES(NULL, ?,?,?)";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, num);
      ps.setString(2, title);
      ps.setString(3, desc);

      ps.executeUpdate();

      System.out.println("Record successfully inserted.");
      success = true;
    }
    catch (SQLException ex) {
      success = false;
      ex.printStackTrace();}
    return success;
  }
  public static Connection getConnection(String connection) throws SQLException {

    Connection c;

    // Prior to JDBC 4.0 (Java 6) classloader uses Class.forName
    // It does nothing here (we are using Java 11)
    try {
      Class.forName("org.apache.derby.iapi.jdbc.InternalDriver");
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (connection.contains("mysql")) {
      String username = "newuser";
      String password = "new_user_2020";

      Properties properties = new Properties();
      properties.setProperty("user", username);
      properties.setProperty("password", password);

      c = DriverManager.getConnection(connection, properties);
      c = DriverManager.getConnection(connection, username, password);
    } else {
      if (connection.contains("sqlite")) {
        Path p = Paths.get(connection.split(":")[2]);
        try {
          Files.createDirectories(p.getParent());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      c = DriverManager.getConnection(connection);
    }
    return c;
  }
} 