package at.fhtw.mtcg.service.stats;

import at.fhtw.database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatsDAL {

    public List<Integer> getStats(String name) {
        List <Integer> stats = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement prGetStats = connection.prepareStatement("Select wins as wins, losses as losses from userdata where username=?");
            prGetStats.setString(1,name);
            ResultSet rs = prGetStats.executeQuery();
            if(rs.next()){
                stats.add(rs.getInt("wins"));
                stats.add(rs.getInt("losses"));
                return stats;
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return null;
    }
}
