import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionExamples {
  static String mySqlUrl = "jdbc:mysql://localhost:3306/testone";

  public static void main(String[] args) throws SQLException {

    try (Connection conect = getConnection(mySqlUrl)) {
      createTable(conect);
    }
    System.out.println("Success!");
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

  public static void createTable(Connection con) throws SQLException {

    String createSQL =
         "create table PERSON (PERSON_ID INTEGER NOT NULL, " +
              "NAME varchar(255) NOT NULL,AGE INTEGER NOT NULL, PRIMARY KEY (PERSON_ID))";
    try (Statement stmt = con.createStatement()) {
      stmt.execute(createSQL);
    }
  }

  public static List<Person> getPersonList(Connection conn) throws SQLException {
    String sql = "select * from PERSON ";
    List<Person> data = new ArrayList<>();

    try (Statement stmt = conn.createStatement(
         ResultSet.TYPE_SCROLL_INSENSITIVE,
         ResultSet.CONCUR_UPDATABLE)) {

      ResultSet resultSet = stmt.executeQuery(sql);
      resultSet.afterLast();
      while (resultSet.previous()) {
        data.add(new Person(resultSet.getInt("PERSON_ID"),
                            resultSet.getString("NAME"),
                            resultSet.getInt("AGE")));  }    }
    return data;     }   }

class Person {
  private int id;
  private String name;
  private int age;

  // constructor
  public Person(int id, String name, int age) {

    this.id = id;
    this.name = name;
    this.age = age;
  }

  public String toString() {

    return "Person{" +
         "id=" + id +
         ", name='" + name + '\'' +
         ", age=" + age +
         '}';
  }
}