package at.fhtw.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

class ConnectionFactoryTest {
    @Test
    void testGetConnection(){
        Connection connection = ConnectionFactory.getConnection();
        System.out.println(connection);
        assertNotEquals(connection,"Error connecting to the database");
    }
}