package at.fhtw.mtcg.service.user;

import at.fhtw.mtcg.controller.Controller;
import at.fhtw.mtcg.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;

import java.sql.SQLException;
public class UserController extends Controller {
    private UserDAL userDAL;
    public UserController(UserDAL userDAL){this.userDAL=userDAL;}


    // POST /user
    public Response addUser(Request request) {
        try {
            User user = this.getObjectMapper().readValue(request.getBody(), User.class);
            if(this.userDAL.addUser(user)){
            return new Response(
                    HttpStatus.CREATED,
                    ContentType.JSON,
                    "{ message: \"Registration Success\" }"
            );
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new Response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ContentType.JSON,
                "{ \"message\" : \"Registration Failed\" }"
        );
    }
}


