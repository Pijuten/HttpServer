package at.fhtw.httpserver.utils;

import at.fhtw.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthTokenHandlerDAL {
    String getUser(String token){
        Connection connection = ConnectionFactory.getConnection();
        try {
         PreparedStatement   ps = connection.prepareStatement("Select username from userdata where authtoken= ? ");

        ps.setString(1,token);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }
}
