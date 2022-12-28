package at.fhtw.mtcg.service.packages;

import at.fhtw.database.ConnectionFactory;
import at.fhtw.mtcg.model.Cards;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PackageDAL {
    public boolean addPackages(List<Cards> cards) throws SQLException {
        if(cards.size()!=5){
            return false;
        }else{
            for(int i=0; i<4; i++){
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement("INSERT INTO CARDS(id,name,damage) VALUES (?,?,?)");
                ps.setString(1,cards.get(i).getId());
                ps.setString(2,cards.get(i).getName());
                ps.setFloat(3,cards.get(i).getDamage());
                ps.executeUpdate();
            }
            return true;
        }
    };
}
