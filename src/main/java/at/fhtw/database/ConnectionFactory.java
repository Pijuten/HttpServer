package at.fhtw.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.Driver;

public class ConnectionFactory {
    public static final String URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    public static final String USER = "swen1user";
    public static final String PASS = "swen1psw";

    public ConnectionFactory() {
    }

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "swen1user", "swen1psw");
        } catch (SQLException var1) {
            throw new RuntimeException("Error connecting to the database", var1);
        }
    }
}
