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
        if (request.getMethod() == Method.POST) {
            return this.userController.addUser(request);
        } else if (request.getMethod() == Method.GET && request.getHeaderMap().getHeader("Authorization")!=null && request.getPathParts().size()>1) {
            return this.userController.getUserInfo(request);
        } else if (request.getMethod() == Method.PUT && request.getHeaderMap().getHeader("Authorization")!=null) {
            return this.userController.editUserData(request);
        }
        return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "[]");
    }
}
