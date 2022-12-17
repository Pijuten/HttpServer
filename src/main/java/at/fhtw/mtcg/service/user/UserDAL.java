package at.fhtw.mtcg.service.user;

import at.fhtw.mtcg.model.User;
import at.fhtw.database.ConnectionFactory;

import java.sql.*;
import java.sql.SQLException;


public class UserDAL {
    public boolean addUser(User user) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO userData(username,password) VALUES (?,?);");
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        connection.close();
        return false;
    }
}
