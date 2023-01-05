package at.fhtw.mtcg.service.card;

import at.fhtw.database.ConnectionFactory;
import at.fhtw.mtcg.model.Cards;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDAL {
    public CardDAL() {
    }

    public List<Cards> showCards(String username) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT cardid as cardid, cardname as cardname, damage as damage from cards where username=?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        List<Cards> cards = new ArrayList<>();
        if (!rs.isBeforeFirst()) {
            System.out.println("Nothing");
            return null;
        } else {
            while(rs.next()) {
                Cards cards1 = new Cards();
                cards1.setId(rs.getString("cardid"));
                cards1.setDamage(rs.getFloat("damage"));
                cards1.setName(rs.getString("cardname"));
                cards.add(cards1);
            }

            return cards;
        }
    }
}
