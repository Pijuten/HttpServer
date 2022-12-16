package at.fhtw.mtcg.service.user;

import at.fhtw.mtcg.model.User;
import at.fhtw.database.ConnectionFactory;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserDAL {
    private List<User> userData = new ArrayList<>();
    private User user = new User();
    public UserDAL(){
    }
    public User getUser(String username) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM userData WHERE username=?");
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {

                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        connection.close();
        return null;
    }
    public List<User> getUser() throws SQLException  {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM userData");
            if(rs.next())
            {
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                userData.add(user);
            }
            return userData;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        connection.close();
        return null;
    }

    public void addUser(User user) {
        userData.add(user);
    }
}
