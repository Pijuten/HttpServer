package at.fhtw.mtcg.service.login;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.mtcg.controller.Controller;
import at.fhtw.mtcg.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.SQLException;

public class LoginController extends Controller {
    private LoginDAL loginDAL;

    public LoginController(LoginDAL loginDAL) {
        this.loginDAL = loginDAL;
    }

    public Response loginUser(Request request) {
        try {
            User user = (User)this.getObjectMapper().readValue(request.getBody(), User.class);
            if (this.loginDAL.loginUser(user) != null) {
                return new Response(HttpStatus.CREATED, ContentType.JSON, "{ ,message: \"Login Success\",  token: \"" + user.getUsername() + "-mtcgToken\"}");
            }
        } catch (JsonProcessingException var3) {
            var3.printStackTrace();
        } catch (SQLException var4) {
            throw new RuntimeException(var4);
        }

        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "{ \"message\" : \"Login Failed\" }");
    }
}
