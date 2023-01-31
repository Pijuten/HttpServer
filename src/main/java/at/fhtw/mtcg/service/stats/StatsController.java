package at.fhtw.mtcg.service.stats;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.utils.AuthTokenHandler;
import at.fhtw.mtcg.controller.Controller;

import java.util.List;

public class StatsController extends Controller {

    private StatsDAL statsDAL;

    public StatsController(StatsDAL statsDAL){this.statsDAL=statsDAL;}
    public Response getStats(Request request) {
        AuthTokenHandler authTokenHandler = new AuthTokenHandler(request.getHeaderMap().getHeader("Authorization"));
        String username = authTokenHandler.compareToken();
        if ( username != null) {
            List <Integer> stats=this.statsDAL.getStats(username);
            if(stats!=null){
                return new Response(HttpStatus.OK, ContentType.JSON, "{ message: \"Success\", \"Wins/Draws/Losses\": "+stats.get(0)+"/"+stats.get(1)+"/ }");
            }
        }
        return null;
    }
}
