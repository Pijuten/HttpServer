package at.fhtw.mtcg.service.transaction;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;

public class TransactionService implements Service {
    private final TransactionController transactionController = new TransactionController(new TransactionDAL());

    public TransactionService() {
    }

    public Response handleRequest(Request request) {
        return request.getMethod() == Method.POST && ((String)request.getPathParts().get(1)).equals("packages") ? this.transactionController.acquirePackage(request.getHeaderMap().getHeader("Authorization")) : new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "[]");
    }
}
