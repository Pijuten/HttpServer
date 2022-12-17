package at.fhtw.mtcg.service.user;

import org.junit.jupiter.api.Test;

import at.fhtw.mtcg.model.User;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDALTest {

    @Test
    void getUserDataTestWithName() throws SQLException {
        UserDAL userDAL = new UserDAL();
        User user=userDAL.getUser("dummy");
        assertEquals(user.getUsername(), "dummy");
        assertEquals(user.getPassword(), "dummyPasswd");
    }@Test
    void getUserDataTest() throws SQLException {
        UserDAL userDAL = new UserDAL();
        List<User> user=userDAL.getUser();
        System.out.println(user.get(0).getUsername());
        System.out.println(user.get(0).getUsername());
    }
}