package at.fhtw.mtcg.service.login;

import at.fhtw.database.ConnectionFactory;
import at.fhtw.mtcg.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAL {
    public LoginDAL() {
    }

    public User loginUser(User user) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM userData WHERE username=? AND password=?");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                connection.close();
                return user;
            } else {
                return null;
            }
        } catch (SQLException var5) {
            return null;
        }
    }
}
