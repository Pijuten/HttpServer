package at.fhtw.mtcg.service.user;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;

public class UserService implements Service {
    private final UserController userController = new UserController(new UserDAL());

    public UserService() {
    }

    public Response handleRequest(Request request) {
        return request.getMethod() == Method.POST ? this.userController.addUser(request) : new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "[]");
    }
}
