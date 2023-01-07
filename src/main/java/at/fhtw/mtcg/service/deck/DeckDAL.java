package at.fhtw.mtcg.service.deck;

import at.fhtw.database.ConnectionFactory;
import at.fhtw.mtcg.model.Cards;
import com.fasterxml.jackson.databind.JsonNode;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeckDAL {

    private List<Cards> queryDeck(String username, Connection connection) throws SQLException {
        PreparedStatement getDeckCards = connection.prepareStatement("SELECT * from cards where username=? and deck=true");
        getDeckCards.setString(1, username);
        ResultSet rs = getDeckCards.executeQuery();
        List<Cards> cards = new ArrayList<>();
        if (!rs.isBeforeFirst()) {
            return null;
        } else {
            while (rs.next()) {
                Cards cards1 = new Cards();
                cards1.setId(rs.getString("cardid"));
                cards1.setDamage(rs.getFloat("damage"));
                cards1.setName(rs.getString("cardname"));
                cards.add(cards1);
            }
            return cards;
        }
    }

    public List<Cards> showDeck(String username) throws SQLException {
        if (username != null) {
            Connection connection = ConnectionFactory.getConnection();
            List<Cards> cards = queryDeck(username, connection);
            if (cards == null) {
                PreparedStatement prSetDeck = connection.prepareStatement("UPDATE cards SET deck = true WHERE cardid IN(SELECT cardid from cards where username =? Order BY damage desc LIMIT 4)");
                prSetDeck.setString(1, username);
                int updatedRows = prSetDeck.executeUpdate();
                if (updatedRows == 4) {
                    cards = queryDeck(username, connection);
                    return cards;
                }
                connection.close();
                return null;
            }
            connection.close();
            return cards;
        }
        return null;
    }

    public boolean editDeck(String username, JsonNode cards) throws SQLException {
        if (username != null) {
            if (cards.size() != 4) {
                return false;
            }
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement prCheckCardCount = connection.prepareStatement("SELECT count(cardid) as validCards from cards where username=? and (cardid=? or cardid=? or cardid=? or cardid=?)");
            String cardId1 = cards.get(0).toString().replace("\"", "");
            String cardId2 = cards.get(1).toString().replace("\"", "");
            String cardId3 = cards.get(2).toString().replace("\"", "");
            String cardId4 = cards.get(3).toString().replace("\"", "");
            prCheckCardCount.setString(1, username);
            prCheckCardCount.setString(2, cardId1);
            prCheckCardCount.setString(3, cardId2);
            prCheckCardCount.setString(4, cardId3);
            prCheckCardCount.setString(5, cardId4);
            ResultSet rs = prCheckCardCount.executeQuery();
            if (rs.next()) {
                if (rs.getInt("validCards") != 4) {
                    connection.close();
                    return false;
                }
            }
            PreparedStatement prSetFalse = connection.prepareStatement("UPDATE cards set deck=false where username=?");
            prSetFalse.setString(1, username);
            prSetFalse.executeUpdate();

            PreparedStatement prSetDeck = connection.prepareStatement("Update Cards set deck=true where username=? and (cardid=? or cardid=? or cardid=? or cardid=?)");
            prSetDeck.setString(1, username);
            prSetDeck.setString(2, cardId1);
            prSetDeck.setString(3, cardId2);
            prSetDeck.setString(4, cardId3);
            prSetDeck.setString(5, cardId4);
            int updateCheck = prSetDeck.executeUpdate();
            if(updateCheck!=4){
                connection.rollback();
                return false;
            }
            connection.close();
            return true;
        }

        return false;
    }
}