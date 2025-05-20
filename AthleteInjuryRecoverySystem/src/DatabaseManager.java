import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private Connection connection;

    public void connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/athlete_injury";
            String user = "root"; // Replace with your MySQL username
            String password = "yourpassword"; // Replace with your MySQL password
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            System.out.println("Disconnection failed: " + e.getMessage());
        }
    }

    public void createTable() {
        if (connection == null) {
            System.out.println("No connection to the database.");
            return;
        }

        String sql = "CREATE TABLE IF NOT EXISTS injury_records (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY," +
                     "athlete_name VARCHAR(255) NOT NULL," +
                     "injury_type VARCHAR(255) NOT NULL," +
                     "recovery_time INT NOT NULL)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created successfully!");
        } catch (SQLException e) {
            System.out.println("Table creation failed: " + e.getMessage());
        }
    }

    public void insertRecord(InjuryRecord record) {
        if (connection == null) {
            System.out.println("No connection to the database.");
            return;
        }

        String sql = "INSERT INTO injury_records(athlete_name, injury_type, recovery_time) VALUES(?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, record.getAthleteName());
            pstmt.setString(2, record.getInjuryType());
            pstmt.setInt(3, record.getRecoveryTime());
            pstmt.executeUpdate();
            System.out.println("Record inserted successfully!");
        } catch (SQLException e) {
            System.out.println("Record insertion failed: " + e.getMessage());
        }
    }

    public void viewAllRecords() {
        if (connection == null) {
            System.out.println("No connection to the database.");
            return;
        }

        String sql = "SELECT * FROM injury_records";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Athlete Name: " + rs.getString("athlete_name"));
                System.out.println("Injury Type: " + rs.getString("injury_type"));
                System.out.println("Recovery Time: " + rs.getInt("recovery_time") + " days");
                System.out.println("--------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Record retrieval failed: " + e.getMessage());
        }
    }
}
