import java.sql.*;
//TAsk3
public class Main{
    public static void main(String[] args) {
        String jdbcURL = "jdbc:sqlite:sample.db";

        try (Connection connection = DriverManager.getConnection(jdbcURL)) {
            System.out.println("Connected to SQLite database!");

            String createTableSQL = """
                CREATE TABLE IF NOT EXISTS Employees (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    first TEXT,
                    last TEXT,
                    age INTEGER
                );
            """;
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(createTableSQL);
                System.out.println("Table 'Employees' created.");
            }


            String insertSQL = "INSERT INTO Employees (first, last, age) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
                pstmt.setString(1, "Taylor");
                pstmt.setString(2, "Swift");
                pstmt.setInt(3, 33);
                pstmt.executeUpdate();

                pstmt.setString(1, "Joe");
                pstmt.setString(2, "Bowery");
                pstmt.setInt(3, 32);
                pstmt.executeUpdate();

                System.out.println("Data inserted.");
            }


            String selectSQL = "SELECT id, first, last, age FROM Employees";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(selectSQL)) {

                System.out.println("Employee Records:");
                while (rs.next()) {
                    System.out.print("ID: " + rs.getInt("id"));
                    System.out.print(", First: " + rs.getString("first"));
                    System.out.print(", Last: " + rs.getString("last"));
                    System.out.println(", Age: " + rs.getInt("age"));
                }
            }


            String updateSQL = "UPDATE Employees SET age = ? WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
                pstmt.setInt(1, 34);
                pstmt.setInt(2, 1);
                pstmt.executeUpdate();
                System.out.println("Employee ID 1 (Taylor Swift) updated.");
            }


            String deleteSQL = "DELETE FROM Employees WHERE id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
                pstmt.setInt(1, 2);
                pstmt.executeUpdate();
                System.out.println("Employee ID 2 (Joe Bowery) deleted.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
