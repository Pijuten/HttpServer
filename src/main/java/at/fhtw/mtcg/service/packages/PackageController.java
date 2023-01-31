package at.fhtw.mtcg.service.packages;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.utils.AuthTokenHandler;
import at.fhtw.mtcg.controller.Controller;
import at.fhtw.mtcg.model.Cards;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.SQLException;
import java.util.List;

public class PackageController extends Controller {
    private PackageDAL packageDAL;

    public PackageController(PackageDAL packageDAL) {
        this.packageDAL = packageDAL;
    }

    public Response addPackage(Request request) {
        AuthTokenHandler authTokenHandler = new AuthTokenHandler(request.getHeaderMap().getHeader("Authorization"));
        if (authTokenHandler.compareToken().equals("admin")) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                List<Cards> cards = mapper.readValue(request.getBody(), new TypeReference<List<Cards>>() {
                });
                if (this.packageDAL.addPackages(cards)) {
                    return new Response(HttpStatus.CREATED, ContentType.JSON, "{ message: \"Package Creation Success\" }");
                }
            } catch (JsonProcessingException var5) {
                var5.printStackTrace();
                return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "{ \"message\" : \"Package Creation Failed\" }");
            } catch (SQLException var6) {
                return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "{ \"message\" : \"Package Creation Failed\" }");
            }
        }

        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "{ \"message\" : \"Package Creation Failed\" }");
    }
}
