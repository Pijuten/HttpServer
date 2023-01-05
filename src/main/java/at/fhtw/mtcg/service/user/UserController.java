package at.fhtw.mtcg.service.user;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.mtcg.controller.Controller;
import at.fhtw.mtcg.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.SQLException;

public class UserController extends Controller {
    private UserDAL userDAL;

    public UserController(UserDAL userDAL) {
        this.userDAL = userDAL;
    }

    public Response addUser(Request request) {
        try {
            User user = (User)this.getObjectMapper().readValue(request.getBody(), User.class);
            return this.userDAL.addUser(user) ? new Response(HttpStatus.CREATED, ContentType.JSON, "{ message: \"Registration Success\" }") : new Response(HttpStatus.FORBIDDEN, ContentType.JSON, "{ \"message\" : \"Registration Failed\" }");
        } catch (JsonProcessingException var3) {
            var3.printStackTrace();
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "{ \"message\" : \"Registration Failed\" }");
        } catch (SQLException var4) {
            throw new RuntimeException(var4);
        }
    }
}
