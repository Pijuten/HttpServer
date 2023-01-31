package at.fhtw.mtcg.service.deck;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.utils.AuthTokenHandler;
import at.fhtw.mtcg.controller.Controller;
import at.fhtw.mtcg.model.Cards;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.List;

public class DeckController extends Controller {
    private DeckDAL deckDAL;

    public DeckController(DeckDAL deckDAL) {
        this.deckDAL = deckDAL;
    }

    public Response showDeck(Request request) {
        String username=request.getHeaderMap().getHeader("Authorization");
        AuthTokenHandler authTokenHandler = new AuthTokenHandler(username);
        try {
            List<Cards> cards = this.deckDAL.showDeck(authTokenHandler.compareToken());
            if (cards != null) {
                if(request.getParams()==null){
                    String cardJson = new Gson().toJson(cards);
                    return new Response(HttpStatus.OK, ContentType.JSON, "{ \"message\" : \"Success\" , \"cards:\"" + cardJson + "}");
                } else if(request.getParams().contains("format=plain")){
                    return new Response(HttpStatus.OK, ContentType.JSON, "{\""+cards.get(0).getName()+"\" "+cards.get(0).getDamage()+",\n \""+cards.get(1).getName()+"\" "+cards.get(1).getDamage()+",\n \""+cards.get(2).getName()+"\" "+cards.get(2).getDamage()+",\n \""+cards.get(3).getName()+"\" "+cards.get(3).getDamage()+",\n}");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "{ \"message\" : \"GET Failed\" }");
    }

    public Response editDeck(String userToken, Request request) {
        AuthTokenHandler authTokenHandler = new AuthTokenHandler(userToken);
        String username = authTokenHandler.createToken();
        if (username != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode cards = mapper.readTree(request.getBody());
                if (this.deckDAL.editDeck(username, cards)) {
                    return new Response(HttpStatus.CREATED, ContentType.JSON, "{ message: \"Deck edit Success\" }");
                }
            } catch (JsonProcessingException var5) {
                var5.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "{ \"message\" : \"Deck edit Failed\" }");
    }
}
