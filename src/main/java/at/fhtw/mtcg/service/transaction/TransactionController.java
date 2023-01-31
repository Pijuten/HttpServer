package at.fhtw.mtcg.service.transaction;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.utils.AuthTokenHandler;
import at.fhtw.mtcg.controller.Controller;
import java.sql.SQLException;

public class TransactionController extends Controller {
    private TransactionDAL transactionDAL;

    public TransactionController(TransactionDAL transactionDAL) {
        this.transactionDAL = transactionDAL;
    }

    public Response acquirePackage(String username) {
        try {
            AuthTokenHandler authTokenHandler = new AuthTokenHandler(username);
            String authUsername=authTokenHandler.compareToken();
            if(authUsername!=null){
            if (this.transactionDAL.acquirePackage(authUsername)) {
                return new Response(HttpStatus.OK, ContentType.JSON, "{ \"message\" : \"Package acquisition success\" }");
            }}return new Response(HttpStatus.FORBIDDEN, ContentType.JSON, "{ \"message\" : \"Package acquisition Failed\" }");
        } catch (SQLException var3) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "{ \"message\" : \"Package acquisition Failed\" }");
        }
    }
}
