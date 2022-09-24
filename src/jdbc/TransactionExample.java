package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionExample {
  public static Connection connect = null;

  public static void main(String[] args) {

    boolean success = false;
    try {
      CreateConnection con = new CreateConnection();
      connect = con.getConnection();

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
} 