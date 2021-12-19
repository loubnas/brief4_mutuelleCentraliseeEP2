package com.example.brief3_mutuellecentralisee.dao.implimentation;

import com.example.brief3_mutuellecentralisee.dao.dao;
import com.example.brief3_mutuellecentralisee.dao.interfaces.UsersInt;
import com.example.brief3_mutuellecentralisee.helpers.alertHelper;
import com.example.brief3_mutuellecentralisee.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;


public class UsersImp implements UsersInt {


    @Override
    public User Connect(String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        User user=null;

        try {
            conn = dao.getConnection();
            stmt = conn.prepareStatement(dao.GET_USER);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if(BCrypt.checkpw(password,rs.getString("password")))
                {
                    user = new User();
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setCin(rs.getString("cin"));
                    user.setPhone(rs.getString("phone"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setEntity(rs.getString("entity"));

                }
            }
        } catch (SQLException e) {
            alertHelper.ShowError("Erreur","Erreur MySQL.",e.getMessage());
        } finally {
            dao.close(stmt);
            dao.close(conn);
        }

        return user;
    }
}
