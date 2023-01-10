package at.fhtw.mtcg.service.stats;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;

public class StatsService implements Service {
    private  final StatsController statsController = new StatsController(new StatsDAL());
    public Response handleRequest(Request request){
        if(request.getMethod()== Method.GET&&request.getHeaderMap().getHeader("Authorization")!=null){
            return this.statsController.getStats(request);
        }
        return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "[]");
    }
}
