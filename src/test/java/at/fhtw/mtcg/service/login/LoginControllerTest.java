package at.fhtw.mtcg.service.login;

import at.fhtw.mtcg.model.User;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {
    @Test
    void TestLogin() throws Exception {
        LoginDAL loginDAL = new LoginDAL();
        User user = new User("kienboec","daniel");
        assertNotNull(loginDAL.loginUser(user));
    }

}