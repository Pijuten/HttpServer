package at.fhtw.mtcg.service.user;

import at.fhtw.database.ConnectionFactory;
import at.fhtw.mtcg.model.User;
import at.fhtw.mtcg.model.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            throw new RuntimeException();
        }

        connection.close();
        return false;
    }

    public UserData getUserInfo(String username) {
        if(username!=null){
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement prGetInfo;
            try {
                prGetInfo = connection.prepareStatement("SELECT displayname as dsn, Bio as bio, profileimage as ping from userdata where username=?");
                prGetInfo.setString(1,username);
                ResultSet resultSet = prGetInfo.executeQuery();
                UserData userData = new UserData();
                if(resultSet.next()){
                    userData.setName(resultSet.getString("dsn"));
                    userData.setBio(resultSet.getString("bio"));
                    userData.setImage(resultSet.getString("ping"));
                }
                return userData;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public boolean editUserData(UserData userData, String username) {
        if (username != null) {
            Connection connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement prEditData = connection.prepareStatement("UPDATE userdata set displayname=?, bio=?, profileimage=? where username=?");
                prEditData.setString(1, userData.getName());
                prEditData.setString(2, userData.getBio());
                prEditData.setString(3, userData.getImage());
                prEditData.setString(4, username);
                int checkName = prEditData.executeUpdate();
                System.out.println(prEditData);
                if (checkName != 0) {
                    connection.close();
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
