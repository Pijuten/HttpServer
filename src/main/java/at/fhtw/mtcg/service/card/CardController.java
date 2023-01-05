package at.fhtw.mtcg.service.card;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.utils.AuthTokenHandler;
import at.fhtw.mtcg.controller.Controller;
import at.fhtw.mtcg.model.Cards;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;

public class CardController extends Controller {
    private CardDAL cardDAL;

    public CardController(CardDAL cardDAL) {
        this.cardDAL = cardDAL;
    }

    public Response showCards(String username) {
        AuthTokenHandler authTokenHandler = new AuthTokenHandler(username);

        try {
            List<Cards> cards = this.cardDAL.showCards(authTokenHandler.getName());
            if (cards != null) {
                String cardJson = new Gson().toJson(cards);
                return new Response(HttpStatus.OK, ContentType.JSON, "{ \"message\": success, \"cards:\"" + cardJson + "}");
            }
        } catch (SQLException var5) {
            System.out.println(var5);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "{ \"message\" : \"Card Lookup faild\" }");
        }

        return new Response(HttpStatus.INTERNAL_SERVER_ERROR, ContentType.JSON, "{ \"message\" : \"Card Lookup faild\" }");
    }
}
