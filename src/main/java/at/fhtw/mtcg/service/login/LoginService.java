package at.fhtw.mtcg.service.login;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;

public class LoginService implements Service {
    private final LoginController loginController = new LoginController(new LoginDAL());

    public LoginService() {
    }

    public Response handleRequest(Request request) {
        return request.getMethod() == Method.POST ? this.loginController.loginUser(request) : new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "[]");
    }
}
