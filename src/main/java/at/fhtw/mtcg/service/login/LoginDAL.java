package at.fhtw.mtcg.service.login;

import at.fhtw.database.ConnectionFactory;
import at.fhtw.httpserver.utils.AuthTokenHandler;
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
                AuthTokenHandler authTokenHandler = new AuthTokenHandler(user.getUsername());
                user.setAuthtoken(authTokenHandler.createToken());
                PreparedStatement psInsertToken = connection.prepareStatement("Update userdata set authtoken=? where username=?");
                psInsertToken.setString(1,user.getAuthtoken());
                psInsertToken.setString(2,user.getUsername());
                psInsertToken.executeUpdate();
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
