package at.fhtw.mtcg.service.deck;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;


public class DeckService implements Service {
    private final DeckController deckController = new DeckController(new DeckDAL());
    public Response handleRequest(Request request){
        if(request.getMethod() == Method.GET && request.getHeaderMap().getHeader("Authorization")!=null){
            return deckController.showDeck(request.getHeaderMap().getHeader("Authorization"));
        }else if(request.getMethod() == Method.PUT && request.getHeaderMap().getHeader("Authorization")!=null){
            return deckController.editDeck(request.getHeaderMap().getHeader("Authorization"), request);
        }
        return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "[]");
    }
}
