package at.fhtw.mtcg.service.card;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;

public class CardService implements Service {
    private final CardController cardController = new CardController(new CardDAL());

    public CardService() {
    }

    public Response handleRequest(Request request) {
        if(request.getMethod()==Method.GET && request.getHeaderMap().getHeader("Authorization")!=null){
            return this.cardController.showCards(request.getHeaderMap().getHeader("Authorization"));
        }
        return new Response(HttpStatus.BAD_REQUEST, ContentType.JSON, "[]");
    }
}
