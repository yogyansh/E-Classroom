package Classroom;

import java.sql.*;

public class ConnectionDB {

    public Connection connection;
    public Statement statement;

    public ConnectionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz", "root", "root");
            statement = connection.createStatement();

        } catch (Exception e) {
            
        }
    }
}
