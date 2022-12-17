package at.fhtw.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionFactoryTest {
    @Test
    void testGetConnection() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        assertFalse(connection.isClosed());
    }
}