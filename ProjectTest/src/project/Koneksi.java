package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {
    
    public static Connection Koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/penjualankuota", "root", "");
            
            return koneksi;
        } catch(ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            
            return null;
        }
    }
    
     public boolean isValidUser(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        
        try (Connection cn = Koneksi(); 
             java.sql.PreparedStatement pst = cn.prepareStatement(query)) {
            
            pst.setString(1, username);
            pst.setString(2, password);

            try (var rs = pst.executeQuery()) {
                return rs.next(); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
     
    public String getNamaByUsernamePassword(String username, String password) {
        String query = "SELECT name FROM user WHERE username = ? AND password = ?";
        
        try (Connection cn = Koneksi();
             java.sql.PreparedStatement pst = cn.prepareStatement(query)) {
            
            pst.setString(1, username);
            pst.setString(2, password);

            try (var rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; 
        }
    }
    
    public String getIdUserByUsernamePassword(String username, String password) {
        String query = "SELECT id FROM user WHERE username = ? AND password = ?";
        
        try (Connection cn = Koneksi();
             java.sql.PreparedStatement pst = cn.prepareStatement(query)) {
            
            pst.setString(1, username);
            pst.setString(2, password);

            try (var rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("id");
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; 
        }
    }
    
}