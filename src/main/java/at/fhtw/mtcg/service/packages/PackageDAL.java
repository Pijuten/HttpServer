//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package at.fhtw.mtcg.service.packages;

import at.fhtw.database.ConnectionFactory;
import at.fhtw.mtcg.model.Cards;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PackageDAL {
    public PackageDAL() {
    }

    public boolean addPackages(List<Cards> cards) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        if (cards.size() != 5) {
            connection.close();
            return false;
        } else {
            int packageId = 0;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select MAX(packageid) as maxLevel from cards");
            if (rs.next() && !rs.wasNull()) {
                packageId = rs.getInt("maxLevel") + 1;
            }

            for(int i = 0; i < 5; ++i) {
                int e = 0;
                PreparedStatement ps = connection.prepareStatement("INSERT INTO cards(cardid,cardname,damage,packageid) VALUES (?,?,?,?)");
                ps.setString(1, ((Cards)cards.get(i)).getId());
                ps.setString(2, ((Cards)cards.get(i)).getName());
                ps.setFloat(3, ((Cards)cards.get(i)).getDamage());
                ps.setInt(4, packageId);
                e = ps.executeUpdate();
                if (e != 1) {
                    connection.close();
                    return false;
                }
            }

            connection.close();
            return true;
        }
    }
}
