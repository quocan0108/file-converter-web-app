package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.bean.PDF2WORD;

public class Data2 {
    public List<PDF2WORD> getStatusFromUser(String user) {
        try {
            List<PDF2WORD> list = new ArrayList<PDF2WORD>();
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                config.Config.dbUrl,
                config.Config.dbUser,
                config.Config.dbPass
            );    
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM PDF2WORD WHERE User = '" + user + "'";
            ResultSet rs = stmt.executeQuery(query);         

            while (rs.next()) {
            	PDF2WORD item = new PDF2WORD();
                item.setID(rs.getInt("ID"));
                item.setUser(rs.getString("User"));
                item.setSourceName(rs.getString("SourceName"));
                item.setSourcePath(rs.getString("SourcePath"));
                item.setTargetPath(rs.getString("TargetPath"));
                item.setDateStart(rs.getTimestamp("DateStart"));
                item.setDateCompleted(rs.getTimestamp("DateCompleted"));
                item.setResult(rs.getInt("Result"));

                list.add(item);
            }

            return list;
        }
        catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public void addStatus(PDF2WORD word) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                config.Config.dbUrl,
                config.Config.dbUser,
                config.Config.dbPass
            );
            // https://stackoverflow.com/a/10167435    
            String query = "INSERT INTO PDF2WORD (User, SourceName, SourcePath, TargetPath, DateStart, Result) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, word.getUser());
            ps.setString(2, word.getSourceName());
            ps.setString(3, word.getSourcePath());
            ps.setString(4, word.getTargetPath());
            ps.setTimestamp(5, word.getDateStart());
            ps.setInt(6, word.getResult());

            ps.execute();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public PDF2WORD getStatusByID(Integer ID) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                config.Config.dbUrl,
                config.Config.dbUser,
                config.Config.dbPass
            );
            String query = "SELECT * FROM WORD2PDF WHERE ID = " + ID.toString();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            PDF2WORD word = new PDF2WORD();
            while (rs.next()) {
            	word.setID(rs.getInt("ID"));
            	word.setUser(rs.getString("User"));
            	word.setSourceName(rs.getString("SourceName"));
            	word.setSourcePath(rs.getString("SourcePath"));
            	word.setTargetPath(rs.getString("TargetPath"));
            	word.setDateStart(rs.getTimestamp("DateStart"));
            	word.setDateCompleted(rs.getTimestamp("DateCompleted"));
            	word.setResult(rs.getInt("Result"));
                break;
            }
            rs.close();

            return word;
        }
        catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public void setStatusResult(String sourcePath, Timestamp ts, Integer result) {
        try {
            // If result code is not from below code, return
            List<Integer> acceptable = new ArrayList<Integer>();
            acceptable.add(-1); // Error
            acceptable.add(0); // Pending
            acceptable.add(1); // Converting
            acceptable.add(2); // Successful
            if (!acceptable.contains(result))
                return;

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                config.Config.dbUrl,
                config.Config.dbUser,
                config.Config.dbPass
            );
            String query = "UPDATE PDF2WORD SET Result = ?, DateCompleted = ? WHERE SourcePath = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, result);
            ps.setTimestamp(2, ts);
            ps.setString(3, sourcePath);
            ps.execute();

            
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
