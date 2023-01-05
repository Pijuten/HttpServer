package at.fhtw.mtcg.service.packages;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;

public class PackageService implements Service {
    private final PackageController packageController = new PackageController(new PackageDAL());

    public PackageService() {
    }

    public Response handleRequest(Request request) {
        return request.getMethod() == Method.POST ? this.packageController.addPackage(request) : new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "[]");
    }
}
