package at.fhtw.mtcg.service.transaction;

import at.fhtw.database.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAL {
    public TransactionDAL() {
    }

    public boolean acquirePackage(String username) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement("select currency as geld from userdata where username=?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next() && rs.getInt("geld") < 10) {
            return false;
        } else {
            PreparedStatement ps1 = connection.prepareStatement("UPDATE cards set username=? where packageid=(SELECT min(packageid) from cards where username is null)");
            ps1.setString(1, username);
            int e = ps1.executeUpdate();
            if (e <= 0) {
                connection.close();
                return false;
            } else {
                PreparedStatement ps2 = connection.prepareStatement("UPDATE userdata set currency=currency-10 where username=?");
                ps2.setString(1, username);
                e = ps2.executeUpdate();
                if (e <= 0) {
                    connection.close();
                    return false;
                } else {
                    connection.close();
                    return true;
                }
            }
        }
    }
}
