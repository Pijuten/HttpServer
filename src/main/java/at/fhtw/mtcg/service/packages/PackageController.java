package at.fhtw.mtcg.service.packages;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.mtcg.controller.Controller;
import at.fhtw.mtcg.model.Cards;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.List;

public class PackageController extends Controller {
    private PackageDAL packageDAL;
    public PackageController(PackageDAL packageDAL) {this.packageDAL = packageDAL;}

    public Response addPackage(Request request) {
        if(request.getParams().equals("Authorization=Basic%20admin-mtcgToken")){
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Cards> cards = mapper.readValue(request.getBody(), new TypeReference<List<Cards>>(){});
            if(this.packageDAL.addPackages(cards)){
                return new Response(
                        HttpStatus.CREATED,
                        ContentType.JSON,
                        "{ message: \"Package Creation Success\" }"
                );
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }
        return new Response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ContentType.JSON,
                "{ \"message\" : \"Package Creation Failed\" }"
        );
    }
}
