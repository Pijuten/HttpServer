package at.fhtw.mtcg.service.user;

import at.fhtw.mtcg.controller.Controller;
import at.fhtw.mtcg.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;

import java.util.List;
public class UserController extends Controller {
    private UserDAL userDAL;
    public UserController(UserDAL userDAL){this.userDAL=userDAL;}

    // GET /user(:id
    public Response getUser(String id) {
        try {
            User userData = this.userDAL.getUser(Integer.parseInt(id));
            String userDataJSON = this.getObjectMapper().writeValueAsString(userData);

            return new Response(
                    HttpStatus.OK,
                    ContentType.JSON,
                    userDataJSON
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"message\" : \"Internal Server Error\" }"
            );
        }
    }

    // GET /user
    public Response getUser() {
        try {
            List userData = this.userDAL.getUser();
            // "[ { \"id\": 1, \"city\": \"Vienna\", \"temperature\": 9.0 }, { ... }, { ... } ]"
            String userDataJSON = this.getObjectMapper().writeValueAsString(userData);

            return new Response(
                    HttpStatus.OK,
                    ContentType.JSON,
                    userDataJSON
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"message\" : \"Internal Server Error\" }"
            );
        }
    }

    // POST /user
    public Response addUser(Request request) {
        try {

            // request.getBody() => "{ \"id\": 4, \"city\": \"Graz\", ... }
            User user = this.getObjectMapper().readValue(request.getBody(), User.class);
            this.userDAL.addUser(user);

            return new Response(
                    HttpStatus.CREATED,
                    ContentType.JSON,
                    "{ message: \"Success\" }"
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new Response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ContentType.JSON,
                "{ \"message\" : \"Internal Server Error\" }"
        );
    }
}


