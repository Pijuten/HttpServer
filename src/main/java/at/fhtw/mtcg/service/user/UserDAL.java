package at.fhtw.mtcg.service.user;

import at.fhtw.database.ConnectionFactory;
import at.fhtw.mtcg.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAL {
    public UserDAL() {
    }

    public boolean addUser(User user) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO userData(username,password) VALUES (?,?);");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException var5) {
        }

        connection.close();
        return false;
    }
}
