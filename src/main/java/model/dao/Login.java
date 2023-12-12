package model.dao;

import java.sql.*;

public class Login {
    public boolean isVaildUser(String username, String password) {
        try {
            if (username == null || password == null)
                return false;

            boolean result = false;

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                config.Config.dbUrl,
                config.Config.dbUser,
                config.Config.dbPass
            );    
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM Account WHERE User = '" +  username + "'";            
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                if (rs.getString("User").toLowerCase().equals(username.toLowerCase())) {
                    if (rs.getString("Pass").equals(password)) {
                        result = true;
                        break;
                    }
                }
            }

            return result;
        }
        catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
}
