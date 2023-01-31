package at.fhtw.mtcg.service.user;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.utils.AuthTokenHandler;
import at.fhtw.mtcg.controller.Controller;
import at.fhtw.mtcg.model.User;
import at.fhtw.mtcg.model.UserData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import java.sql.SQLException;

public class UserController extends Controller {
    private UserDAL userDAL;

    public UserController(UserDAL userDAL) {
        this.userDAL = userDAL;
    }

    public Response addUser(Request request) {
        try {
            User user = this.getObjectMapper().readValue(request.getBody(), User.class);
            return this.userDAL.addUser(user) ? new Response(HttpStatus.CREATED, ContentType.JSON, "{ message: \"Registration Success\" }") : new Response(HttpStatus.FORBIDDEN, ContentType.JSON, "{ \"message\" : \"Registration Failed\" }");
        } catch (JsonProcessingException var3) {
            var3.printStackTrace();
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "{ \"message\" : \"Registration Failed\" }");
        } catch (SQLException var4) {
            var4.printStackTrace();
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "{ \"message\" : \"Registration Failed\" }");
        }
    }

    public Response getUserInfo(Request request) {
        AuthTokenHandler authTokenHandler = new AuthTokenHandler(request.getHeaderMap().getHeader("Authorization"));
        if (authTokenHandler.compareToken() != null) {
            UserData userData = this.userDAL.getUserInfo(authTokenHandler.compareToken());
            if (userData != null) {
                String cardJson = new Gson().toJson(userData);
                return new Response(HttpStatus.OK, ContentType.JSON, "{ \"message\" : \"Success\" , \"Info:\"" + cardJson + "}");
            }
        }
        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "{ \"message\" : \"Edit Failed\" }");
    }

    public Response editUserData(Request request) {
        try {
            AuthTokenHandler authTokenHandler = new AuthTokenHandler(request.getHeaderMap().getHeader("Authorization"));
            UserData userData = this.getObjectMapper().readValue(request.getBody(), UserData.class);
            if (this.userDAL.editUserData(userData, authTokenHandler.compareToken())) {
                return new Response(HttpStatus.OK, ContentType.JSON, "{ \"message\" : \"Edit Success\" }");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new Response(HttpStatus.FORBIDDEN, ContentType.JSON, "{ \"message\" : \"Edit Failed\" }");
    }
}
